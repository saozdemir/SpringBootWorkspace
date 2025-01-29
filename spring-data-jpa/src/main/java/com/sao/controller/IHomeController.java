package com.sao.controller;

import com.sao.dto.HomeDto;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 30 Oca 2025
 * <p>
 * @description:
 */
public interface IHomeController {
    HomeDto findHomeById(Long id);
}
