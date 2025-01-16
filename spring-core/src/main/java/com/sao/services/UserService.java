package com.sao.services;

import com.sao.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 16 Oca 2025
 * <p>
 * @description:
 */
@Getter
@Setter
public class UserService {
    private List<User> userList;
}
