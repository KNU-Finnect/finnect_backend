package com.finnect.mockDomain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DataRow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rowId;
}
