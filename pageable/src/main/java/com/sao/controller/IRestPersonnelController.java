package com.sao.controller;

import com.sao.model.dto.PersonnelDto;
import com.sao.model.entity.Personnel;
import com.sao.utils.RestPageableEntity;
import com.sao.utils.RestPageableRequest;
import com.sao.utils.RestRootEntity;
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
    //RestPageableEntity<PersonnelDto> findAllPageable(RestPageableRequest pageableRequest);
    RestRootEntity<RestPageableEntity<PersonnelDto>> findAllPageable(RestPageableRequest pageableRequest);
}
