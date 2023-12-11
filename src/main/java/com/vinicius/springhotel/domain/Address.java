package com.vinicius.springhotel.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String CEP;
    private String state;
    private String city;
    private String neighborhood;
    @Column(name = "house_humber")
    private Integer number;
    private String complement;
}
