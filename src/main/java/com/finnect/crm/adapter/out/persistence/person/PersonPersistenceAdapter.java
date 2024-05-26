package com.finnect.crm.adapter.out.persistence.person;

import com.finnect.crm.application.port.out.person.LoadPersonPort;
import com.finnect.crm.application.port.out.person.SavePersonPort;
import com.finnect.crm.domain.person.PersonState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class PersonPersistenceAdapter implements SavePersonPort, LoadPersonPort {

    private final PersonRepository personRepository;

    @Override
    public PersonState save(PersonState state) {
        return personRepository.save(PersonEntity.from(state));
    }

    @Override
    public PersonState loadById(Long personId) {
        PersonEntity person = personRepository.findById(personId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 Person입니다."));
        return person;
    }
}
