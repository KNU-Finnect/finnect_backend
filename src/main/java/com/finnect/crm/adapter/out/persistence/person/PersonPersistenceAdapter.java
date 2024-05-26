package com.finnect.crm.adapter.out.persistence.person;

import com.finnect.crm.application.port.out.person.SavePersonPort;
import com.finnect.crm.domain.person.PersonState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class PersonPersistenceAdapter implements SavePersonPort {

    private final PersonRepository personRepository;

    @Override
    public PersonState save(PersonState state) {
        return personRepository.save(PersonEntity.from(state));
    }
}
