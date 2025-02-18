package com.sao.controller;

import com.sao.utils.RestPageableRequest;
import org.springframework.data.domain.Pageable;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 18 Şub 2025
 * <p>
 * @description:
 */
public interface IRestBaseController {
    Pageable toPageable(RestPageableRequest request);
}
