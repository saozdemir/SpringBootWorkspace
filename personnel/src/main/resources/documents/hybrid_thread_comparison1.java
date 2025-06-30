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

    ExecutorService pool = new ThreadPoolExecutor(
        Runtime.getRuntime().availableProcessors(),
        Runtime.getRuntime().availableProcessors() * 10,
        60L, TimeUnit.SECONDS,
        new ArrayBlockingQueue<>(5000),
        new ThreadPoolExecutor.CallerRunsPolicy()
    );
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
public List<PersonnelDto> processPersonnelHybrid() throws InterruptedException {
    ResourceTracker tracker = new ResourceTracker();
    tracker.start();

    ExecutorService virtualExecutor = Executors.newVirtualThreadPerTaskExecutor();
    List<Future<PersonnelDto>> ioFutures = new ArrayList<>();

    List<Personnel> personnelList = getAllPersonnel();
    for (Personnel p : personnelList) {
        ioFutures.add(virtualExecutor.submit(() -> {
            PersonnelDto dto = new PersonnelDto();
            BeanUtils.copyProperties(p, dto);
            dto.setEducations(educationWebService.getEducationByPersonnelId(p.getId()));
            dto.setTasks(taskWebService.getTaskByPersonnelId(p.getId()));
            return dto;
        }));
    }

    List<PersonnelDto> ioResults = ioFutures.stream().map(f -> {
        try {
            return f.get();
        } catch (Exception e) {
            return null;
        }
    }).filter(Objects::nonNull).collect(Collectors.toList());

    int cores = Runtime.getRuntime().availableProcessors();
    int chunkSize = (int) Math.ceil((double) ioResults.size() / cores);
    ExecutorService cpuPool = Executors.newFixedThreadPool(cores);

    List<Future<Boolean>> computeFutures = new ArrayList<>();
    for (int i = 0; i < ioResults.size(); i += chunkSize) {
        int from = i;
        int to = Math.min(i + chunkSize, ioResults.size());
        computeFutures.add(cpuPool.submit(() -> {
            for (int j = from; j < to; j++) {
                PersonnelDto dto = ioResults.get(j);
                int experienceScoreSum = dto.getEducations().stream()
                        .flatMap(e -> e.getExperiences().stream())
                        .mapToInt(ExperienceDto::getScore).sum();
                long completedTasks = dto.getTasks().stream()
                        .filter(t -> "COMPLETED".equalsIgnoreCase(t.getStatus())).count();
                dto.setSuccessRate((experienceScoreSum + completedTasks) / 2.0);
            }
            return true;
        }));
    }

    for (Future<Boolean> f : computeFutures) f.get();

    virtualExecutor.close();
    cpuPool.shutdown();
    tracker.stop("Hybrid Thread Yapısı");
    return ioResults;
}
