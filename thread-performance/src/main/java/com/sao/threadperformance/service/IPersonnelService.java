package com.sao.threadperformance.service;

import com.sao.threadperformance.entity.Personnel;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 23 May 2025
 * <p>
 * @description:
 */
public interface IPersonnelService {
    void createTestData();

    List<Personnel> getAllPersonnel() throws InterruptedException, ExecutionException;
}
