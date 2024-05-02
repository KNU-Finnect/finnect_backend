package com.finnect.company.adapter.out.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "people")
public class PeopleEntity {

    @Id
    private Long peopleId;
}
