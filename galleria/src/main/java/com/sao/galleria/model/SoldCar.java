package com.sao.galleria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 07 Jul 2025
 * <p>
 * @description:
 */
@Entity
@Table(name = "sold_car",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"gallerist_id", "car_id", "customer_id"}, name = "uq_gallerist_car_customer")})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoldCar extends BaseEntity {
    @ManyToOne
    private Gallerist gallerist;

    @ManyToOne
    private Car car;

    @ManyToOne
    private Customer customer;
}
