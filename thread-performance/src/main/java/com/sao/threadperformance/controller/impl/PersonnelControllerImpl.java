package com.sao.threadperformance.controller.impl;

import com.sao.threadperformance.controller.IPersonnelController;
import com.sao.threadperformance.entity.Personnel;
import com.sao.threadperformance.service.IPersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 23 May 2025
 * <p>
 * @description:
 */
@RestController
@RequestMapping("/personnel-api")
public class PersonnelControllerImpl implements IPersonnelController {
    @Autowired
    private IPersonnelService personnelService;

    // Test verisi oluşturma
    @PostMapping("/generate")
    public String createTestData() {
        personnelService.createTestData();
        return "1000 test verisi oluşturuldu";
    }

    @GetMapping("/get-all-personnel")
    List<Personnel> getAllPersonnel(){
        try {
            return personnelService.getAllPersonnel();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
