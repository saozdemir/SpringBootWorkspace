package com.sao.usermanagement.mapper;

import com.sao.usermanagement.dto.RoleDto;
import com.sao.usermanagement.dto.iu.RoleDtoIU;
import com.sao.usermanagement.entity.Role;
import org.mapstruct.Mapper;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Tem 2025
 * <p>
 * @description:
 */
@Mapper(componentModel = "spring")
public interface RoleMapper extends BaseMapper <Role, RoleDto, RoleDtoIU> {

}
