package com.sao.galleria.mapper;

import com.sao.galleria.dto.UserDto;
import com.sao.galleria.model.User;
import org.mapstruct.Mapper;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 07 Tem 2025
 * <p>
 * @description:
 */
@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDto> {
    @Override
    UserDto toDto(User entity);

    @Override
    User toEntity(UserDto dto);
}
