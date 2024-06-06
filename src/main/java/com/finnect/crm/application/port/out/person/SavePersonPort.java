package com.finnect.crm.application.port.out.person;

import com.finnect.crm.domain.person.PersonState;

public interface SavePersonPort {

    PersonState save(PersonState state);
}
