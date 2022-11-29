package com.ivan.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "fib")
public class Fibonacci {
    @Id
    @SequenceGenerator(name = "sequence_one", sequenceName = "sequence_one", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_one")
    private Integer id;
    private String index;
    private String value;
}
