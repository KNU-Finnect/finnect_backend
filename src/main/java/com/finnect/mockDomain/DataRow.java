package com.finnect.mockDomain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Row {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rowId;
}
