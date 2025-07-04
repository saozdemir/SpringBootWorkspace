package com.sao.personneleducation.service.impl;

import com.sao.personneleducation.dto.EducationDto;
import com.sao.personneleducation.dto.ExperienceDto;
import com.sao.personneleducation.entity.Education;
import com.sao.personneleducation.entity.Experience;
import com.sao.personneleducation.repository.EducationRepository;
import com.sao.personneleducation.service.IEducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 26 May 2025
 * <p>
 * @description:
 */
@Service
public class EducationServiceImpl implements IEducationService {
    @Autowired
    private EducationRepository educationRepository;

    @Override
    public List<EducationDto> getAllEducations() {
        return List.of();
    }

    @Override
    public List<Education> saveAllEducations(List<Education> educations) {
        return educationRepository.saveAll(educations);
    }

    /**
     * Bu metot, tüm operasyonun tek bir veritabanı işlemi (transaction) içinde
     * gerçekleşmesini sağlar. Bu, veri tutarlılığını garanti eder ve LazyInitializationException
     * gibi oturum (session) problemlerini önler.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EducationDto> getEducationsByPersonnelId(Long personnelId) {
        // Repository'deki yeni metodu çağırarak hem Education hem de Experience verilerini
        // tek seferde, güvenli bir şekilde yüklüyoruz.
        List<Education> educations = educationRepository.getEducationByPersonnelIdWithExperiences(personnelId);

        // Verileri DTO'ya dönüştürmek için Java Stream API'sini kullanmak daha modern ve okunaklıdır.
        List<EducationDto> educationDtoList = educations.stream()
                .map(education -> {
                    // Experience listesini ExperienceDto listesine dönüştür.
                    List<ExperienceDto> experienceDtoList = education.getExperiences().stream()
                            .map(experience -> {
                                ExperienceDto experienceDto = new ExperienceDto();
                                experienceDto.setId(experience.getId());
                                experienceDto.setName(experience.getName());
                                experienceDto.setScore(experience.getScore());
                                return experienceDto;
                            })
                            .collect(Collectors.toList());

                    // Ana EducationDto nesnesini oluştur ve doldur.
                    EducationDto educationDto = new EducationDto();
                    educationDto.setId(education.getId());
                    educationDto.setName(education.getName());
                    educationDto.setExperiences(experienceDtoList);
                    return educationDto;
                })
                .collect(Collectors.toList());

        // Test senaryosundaki yapay gecikmeyi koruyoruz.
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // interrupt flag'ini korumak en iyi pratiktir.
            throw new RuntimeException(e);
        }

        return educationDtoList;
    }
}
