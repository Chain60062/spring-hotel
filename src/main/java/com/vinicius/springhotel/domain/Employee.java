package com.vinicius.springhotel.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;

@Entity
public class Employee extends User{
    private BigDecimal salary;
    private LocalDate hireDate;
    HotelUnit hotelUnit;
}
