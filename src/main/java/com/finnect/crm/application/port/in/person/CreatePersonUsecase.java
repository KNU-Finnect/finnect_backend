package com.finnect.crm.application.port.in.person;

import com.finnect.crm.domain.person.PersonState;

public interface CreatePersonUsecase {

    PersonState createPerson(CreatePersonCommand command);
}
