package com.sao.usermanagement.mapper;

import com.sao.usermanagement.dto.UserDto;
import com.sao.usermanagement.dto.iu.UserDtoIU;
import com.sao.usermanagement.entity.User;
import org.mapstruct.Mapper;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Jul 2025
 * <p>
 * @description:
 */
@Mapper(componentModel = "spring")
public interface UserMapper  extends BaseMapper<User, UserDto, UserDtoIU>{

}
