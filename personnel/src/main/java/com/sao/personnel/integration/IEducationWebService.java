package com.sao.personnel.integration;

import com.sao.personnel.dto.EducationDto;
import com.sao.personnel.dto.TaskDto;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 26 Haz 2025
 * <p>
 * @description:
 */
public interface IEducationWebService {
    List<EducationDto> getEducationByPersonnelId(Long personnelId);
}
