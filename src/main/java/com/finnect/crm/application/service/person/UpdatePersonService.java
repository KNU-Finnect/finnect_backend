package com.finnect.crm.application.service.person;

import com.finnect.crm.application.port.in.person.UpdatePersonCommand;
import com.finnect.crm.application.port.in.person.UpdatePersonUsecase;
import com.finnect.crm.application.port.out.person.LoadPersonPort;
import com.finnect.crm.application.port.out.person.SavePersonPort;
import com.finnect.crm.domain.person.Person;
import com.finnect.crm.domain.person.PersonState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
class UpdatePersonService implements UpdatePersonUsecase {

    private final LoadPersonPort loadPersonPort;
    private final SavePersonPort savePersonPort;

    @Override
    public PersonState update(UpdatePersonCommand command) {
        PersonState state = loadPersonPort.loadById(command.getPersonId());

        Person person = Person.from(state);

        if (command.getPersonName() != null)
            person.rename(command.getPersonName());
        if (command.getPersonRole() != null)
            person.updateRole(command.getPersonRole());
        if (command.getPersonEmail() != null)
            person.updateEmail(command.getPersonEmail());
        if (command.getPersonPhone() != null)
            person.updatePhone(command.getPersonPhone());

        return savePersonPort.save(person);
    }
}
