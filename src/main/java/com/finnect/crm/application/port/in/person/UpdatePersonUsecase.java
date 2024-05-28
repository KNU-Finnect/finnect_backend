package com.finnect.crm.application.port.in.person;

import com.finnect.crm.domain.person.PersonState;

public interface UpdatePersonUsecase {

    PersonState update(UpdatePersonCommand command);
}
