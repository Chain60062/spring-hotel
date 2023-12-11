package com.vinicius.springhotel.domain;

import java.io.Serializable;

import com.vinicius.springhotel.domain.utils.PhoneId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class Phone implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private PhoneId phoneId;
}
