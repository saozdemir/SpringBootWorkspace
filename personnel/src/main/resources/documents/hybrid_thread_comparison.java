// Kaynak izleyici (RAM + CPU)
public class ResourceTracker {
    private long startTime;
    private long startMemory;

    public void start() {
        startTime = System.currentTimeMillis();
        startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    public void stop(String label) {
        long endTime = System.currentTimeMillis();
        long endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        long memoryUsed = (endMemory - startMemory) / (1024 * 1024); // MB
        long duration = endTime - startTime; // ms

        double cpuLoad = getProcessCpuLoad();

        System.out.printf("%s tamamlandi.\nSüre: %d ms, Bellek: %d MB, CPU: %.2f%%\n",
                label, duration, memoryUsed, cpuLoad * 100);
    }

    private double getProcessCpuLoad() {
        com.sun.management.OperatingSystemMXBean osBean =
                (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        return osBean.getProcessCpuLoad();
    }
}

// 1. Virtual Thread Only
public List<PersonnelDto> processWithVirtualOnly() {
    ResourceTracker tracker = new ResourceTracker();
    tracker.start();

    ExecutorService virtualExecutor = Executors.newVirtualThreadPerTaskExecutor();
    List<Future<PersonnelDto>> futures = new ArrayList<>();

    List<Personnel> personnelList = getAllPersonnel();
    for (Personnel p : personnelList) {
        futures.add(virtualExecutor.submit(() -> {
            PersonnelDto dto = new PersonnelDto();
            BeanUtils.copyProperties(p, dto);
            dto.setEducations(educationWebService.getEducationByPersonnelId(p.getId()));
            dto.setTasks(taskWebService.getTaskByPersonnelId(p.getId()));
            return dto;
        }));
    }

    List<PersonnelDto> result = futures.stream().map(f -> {
        try {
            return f.get();
        } catch (Exception e) {
            return null;
        }
    }).filter(Objects::nonNull).collect(Collectors.toList());

    virtualExecutor.close();
    tracker.stop("Sadece Virtual Thread");
    return result;
}

// 2. Platform Thread Only
public List<PersonnelDto> processWithPlatformOnly() {
    ResourceTracker tracker = new ResourceTracker();
    tracker.start();

    ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    List<Future<PersonnelDto>> futures = new ArrayList<>();

    List<Personnel> personnelList = getAllPersonnel();
    for (Personnel p : personnelList) {
        futures.add(pool.submit(() -> {
            PersonnelDto dto = new PersonnelDto();
            BeanUtils.copyProperties(p, dto);
            dto.setEducations(educationWebService.getEducationByPersonnelId(p.getId()));
            dto.setTasks(taskWebService.getTaskByPersonnelId(p.getId()));
            return dto;
        }));
    }

    List<PersonnelDto> result = futures.stream().map(f -> {
        try {
            return f.get();
        } catch (Exception e) {
            return null;
        }
    }).filter(Objects::nonNull).collect(Collectors.toList());

    pool.shutdown();
    tracker.stop("Sadece Platform Thread");
    return result;
}

// 3. Hybrid Virtual I/O + Platform CPU
public List<PersonnelDto> processPersonnelHybrid() {
    ResourceTracker tracker = new ResourceTracker();
    tracker.start();

    ExecutorService virtualExecutor = Executors.newVirtualThreadPerTaskExecutor();
    List<PersonnelDto> allPersonnelDtoList;
    try {
        List<Personnel> personnelList = getAllPersonnel();
        List<Future<PersonnelDto>> futures = new ArrayList<>();

        for (Personnel personnel : personnelList) {
            futures.add(virtualExecutor.submit(() -> {
                PersonnelDto dto = new PersonnelDto();
                BeanUtils.copyProperties(personnel, dto);
                dto.setEducations(educationWebService.getEducationByPersonnelId(personnel.getId()));
                dto.setTasks(taskWebService.getTaskByPersonnelId(personnel.getId()));
                return dto;
            }));
        }

        allPersonnelDtoList = new ArrayList<>();
        for (Future<PersonnelDto> future : futures) {
            allPersonnelDtoList.add(future.get());
        }
    } catch (Exception e) {
        throw new RuntimeException("Virtual thread ile veri çekme hatası", e);
    } finally {
        virtualExecutor.close();
    }

    int availableProcessors = Runtime.getRuntime().availableProcessors();
    int chunkSize = (int) Math.ceil((double) allPersonnelDtoList.size() / availableProcessors);
    ExecutorService platformExecutor = Executors.newFixedThreadPool(availableProcessors);

    List<Future<Boolean>> chunkResults = new ArrayList<>();

    for (int i = 0; i < allPersonnelDtoList.size(); i += chunkSize) {
        int fromIndex = i;
        int toIndex = Math.min(i + chunkSize, allPersonnelDtoList.size());
        List<PersonnelDto> chunk = allPersonnelDtoList.subList(fromIndex, toIndex);

        chunkResults.add(platformExecutor.submit(() -> {
            for (PersonnelDto personnel : chunk) {
                int totalScore = 0;
                int totalExperience = 0;
                for (EducationDto edu : personnel.getEducations()) {
                    if (edu.getExperiences() != null) {
                        for (ExperienceDto exp : edu.getExperiences()) {
                            totalScore += exp.getScore();
                            totalExperience++;
                        }
                    }
                }
                double averageScore = totalExperience > 0 ? ((double) totalScore / totalExperience) : 0;

                long completedTasks = personnel.getTasks().stream()
                        .filter(task -> "COMPLETED".equalsIgnoreCase(task.getStatus()))
                        .count();
                long totalTasks = personnel.getTasks().size();
                double taskCompletionRate = totalTasks > 0 ? (double) completedTasks / totalTasks : 0;

                double successRate = (averageScore * 0.6) + (taskCompletionRate * 100 * 0.4);
            }
            return true;
        }));
    }

    platformExecutor.shutdown();

    for (Future<Boolean> result : chunkResults) {
        try {
            if (!result.get()) {
                return Collections.emptyList();
            }
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    tracker.stop("Hybrid Virtual + Platform Thread İşlemi");
    return allPersonnelDtoList;
}
