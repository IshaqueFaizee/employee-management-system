
package com.example.ems.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "employees")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private int age;
    private String gender;
    private String address;

    @Column(unique = true, nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;
}
