package com.vinicius.springhotel.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address implements Serializable {

    public Address(Long id, String cep, String state, String city, String neighborhood, Integer number,
            String complement) {
        this.id = id;
        this.cep = cep;
        this.state = state;
        this.city = city;
        this.neighborhood = neighborhood;
        this.number = number;
        this.complement = complement;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cep;
    private String state;
    private String city;
    private String neighborhood;
    @Column(name = "house_humber")
    private Integer number;
    private String complement;
    @JoinColumn(name = "customer_id")
    private User customer;
    @OneToOne(mappedBy = "address")
    private HotelUnit hotelUnit;
}
