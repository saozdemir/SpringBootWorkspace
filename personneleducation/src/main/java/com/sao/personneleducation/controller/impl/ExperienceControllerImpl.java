package com.sao.personneleducation.controller.impl;

import com.sao.personneleducation.controller.IExperienceController;
import com.sao.personneleducation.entity.Experience;
import com.sao.personneleducation.service.IExperienceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 26 May 2025
 * <p>
 * @description:
 */
@RestController
@RequestMapping(path = "/api/experience")
@Tag(name = "Ödev", description = "Ödev yönetim API")
public class ExperienceControllerImpl implements IExperienceController {
    @Autowired
    private IExperienceService experienceService;

    @PostMapping(path = "/save")
    @Operation(summary = "Yeni ödev oluştur")
    @Override
    public Experience saveExperience(@RequestBody Experience experience) {
        return experienceService.saveExperience(experience);
    }

    @GetMapping(path = "/list")
    @Operation(summary = "Tüm ödevleri getir")
    @Override
    public List<Experience> getAllExperiences() {
        return experienceService.getAllExperiences();
    }
}
