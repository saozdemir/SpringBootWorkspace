package com.sao.personneleducation.controller.impl;

import com.sao.personneleducation.controller.IEducationController;
import com.sao.personneleducation.dto.EducationDto;
import com.sao.personneleducation.entity.Education;
import com.sao.personneleducation.service.IEducationService;
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
@RequestMapping(path = "/api/education")
@Tag(name = "Egitim Programı", description = "Egitim Programı yönetim API")
public class EducationControllerImpl implements IEducationController {
    @Autowired
    private IEducationService educationService;


    @GetMapping(path = "/list")
    @Operation(summary = "Tüm eğitim programlarını getir")
    @Override
    public List<EducationDto> getAllEducations() {
        return educationService.getAllEducations();
    }

    @GetMapping("/{personnelId}/educations")
    @Override
    public List<EducationDto> getEducationsByPersonnelId(@PathVariable Long personnelId) {
        return educationService.getEducationsByPersonnelId(personnelId);
    }
}
