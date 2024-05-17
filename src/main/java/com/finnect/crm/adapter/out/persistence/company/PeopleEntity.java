package com.finnect.crm.adapter.out.persistence.company;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "people")
public class PeopleEntity {

    @Id
    private Long peopleId;
}
