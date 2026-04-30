package com.example.medicalStoreManager.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor

@Table(name = "medicines")
public class Medicines {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int medicinId;
    @Column
    private String name;
    @Column
    private double price;
    @Column
    private String company;

    @Column
    private LocalDate expiryDate;

    @Column
    private LocalDate manufacturingDate;

}
