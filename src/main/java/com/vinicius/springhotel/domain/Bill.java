package com.vinicius.springhotel.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
public class Bill implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Integer Id;
    private Payment payment;
    private BigDecimal total;
    private Booking booking;
}