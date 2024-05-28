package com.finnect.crm.adapter.out.persistence.person;

import com.finnect.crm.application.port.out.person.DeletePersonPort;
import com.finnect.crm.application.port.out.person.LoadPeoplePort;
import com.finnect.crm.application.port.out.person.LoadPersonPort;
import com.finnect.crm.application.port.out.person.SavePersonPort;
import com.finnect.crm.domain.person.PersonState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class PersonPersistenceAdapter implements SavePersonPort, LoadPersonPort, LoadPeoplePort, DeletePersonPort {

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

    @Override
    public List<PersonState> loadPeopleByCompanyId(Long companyId) {
        return personRepository.findAllByCompanyIdOrderByPersonName(companyId);
    }

    @Override
    public boolean delete(Long personId) {
        personRepository.deleteById(personId);
        return true;
    }
}
