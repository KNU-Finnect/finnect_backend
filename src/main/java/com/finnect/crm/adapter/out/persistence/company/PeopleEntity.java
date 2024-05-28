package com.finnect.crm.adapter.out.persistence.company;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "people")
class PeopleEntity {

    @Id
    private Long peopleId;
}
