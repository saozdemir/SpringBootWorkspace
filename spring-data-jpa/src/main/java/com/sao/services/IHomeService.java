package com.sao.services;

import com.sao.dto.HomeDto;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 30 Oca 2025
 * <p>
 * @description:
 */
public interface IHomeService {
    HomeDto findHomeById(Long id);
}
