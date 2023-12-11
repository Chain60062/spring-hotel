package com.vinicius.springhotel.domain;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Setter
@Getter
@Entity
public class Booking implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String CPF;
    private Instant inDateTime;
    private Instant outDateTime;
    @CreationTimestamp
    private Instant createdOn;
    private User user;
}