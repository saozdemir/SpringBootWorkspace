package com.sao.usermanagement.mapper;

import com.sao.usermanagement.dto.PermissionDto;
import com.sao.usermanagement.dto.iu.PermissionDtoIU;
import com.sao.usermanagement.entity.Permission;
import org.mapstruct.Mapper;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Jul 2025
 * <p>
 * @description:
 */
@Mapper(componentModel = "spring")
public interface PermissionMapper extends BaseMapper<Permission, PermissionDto, PermissionDtoIU>{
}
