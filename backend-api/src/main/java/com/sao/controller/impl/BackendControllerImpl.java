package com.sao.controller.impl;

import com.sao.controller.IBackendController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 22 Şub 2025
 * <p>
 * @description:
 */
@RestController
@RequestMapping("/backend/api")
public class BackendControllerImpl implements IBackendController {

    @GetMapping("/message")
    @Override
    public String getMessage() {
        return "This is a backend message";
    }
}
