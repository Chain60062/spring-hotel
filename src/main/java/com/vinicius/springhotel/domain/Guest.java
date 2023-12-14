package com.vinicius.springhotel.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Guest implements Serializable {

    public Guest(@Size(min = 11, max = 11) String cpf, String firstName, String lastName) {
        this.cpf = cpf;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @Size(min = 11, max = 11)
    @Column(length = 11)
    private String cpf;
    private String firstName;
    private String lastName;
    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    @ManyToMany
    private Set<Booking> bookings = new HashSet<>();
}