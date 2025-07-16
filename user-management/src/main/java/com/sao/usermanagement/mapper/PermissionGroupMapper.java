package com.sao.usermanagement.mapper;

import com.sao.usermanagement.dto.PermissionGroupDto;
import com.sao.usermanagement.dto.iu.PermissionGroupDtoIU;
import com.sao.usermanagement.entity.Permission;
import com.sao.usermanagement.entity.PermissionGroup;
import org.mapstruct.Mapper;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Tem 2025
 * <p>
 * @description:
 */
@Mapper(componentModel = "spring")
public interface PermissionGroupMapper extends BaseMapper<PermissionGroup, PermissionGroupDto, PermissionGroupDtoIU> {
}
