package com.sao.controller;

import com.sao.model.entity.Personnel;
import com.sao.utils.RestPageableRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 18 Şub 2025
 * <p>
 * @description:
 */
public interface IRestPersonnelController {

    //Page<Personnel> findAllPageable(int pageNumber, int pageSize);
    Page<Personnel> findAllPageable(RestPageableRequest pageableRequest);
}
