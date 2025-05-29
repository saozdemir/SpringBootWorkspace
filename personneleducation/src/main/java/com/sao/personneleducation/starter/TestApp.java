package com.sao.personneleducation.starter;

import com.github.javafaker.Faker;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 28 May 2025
 * <p>
 * @description:
 */
public class TestApp {
    public static void main(String[] args) {
        Faker faker = new Faker();
        for (int i = 0; i < 10; i++) {
            System.out.println("1- " + faker.app().name());
            System.out.println("2- " + faker.educator().course());
            System.out.println("3- " + faker.job().title());
            System.out.println("4- " + faker.job().position());
            System.out.println("5- " + faker.lorem().fixedString(30));
            System.out.println("6- " + faker.lorem().paragraph(3));
            System.out.println("7- " + faker.university().name());
        }

    }
}
