package com.sao.personneleducation.starter;

import com.github.javafaker.Faker;

import java.util.Locale;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 29 May 2025
 * <p>
 * @description:
 */
public class FakerTest {
    public static void main(String[] args) {
        Faker faker = new Faker(new Locale("tr"));
        for (int i = 0; i < 10; i++) {
            System.out.println("1- " + faker.educator().course());//Experience Name
            System.out.println("2- " + faker.job().title());//Education Name
            System.out.println("3- " + faker.job().field());//Task Name
            System.out.println("4- " + faker.options().option("Planlı", "Devam Ediyor", "Tamamlandı"));//Task Status
            System.out.println("5- " + faker.lorem().paragraph());//Task Description
        }

    }
}
