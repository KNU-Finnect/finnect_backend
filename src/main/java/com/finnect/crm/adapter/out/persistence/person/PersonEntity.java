package com.finnect.crm.adapter.out.persistence.person;

import com.finnect.crm.domain.person.PersonState;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "person")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
class PersonEntity implements PersonState {

    @Id
    @GeneratedValue
    private Long personId;
    private Long companyId;
    private String personName;
    private String personRole;
    private String personEmail;
    private String personPhone;

    public static PersonEntity from(PersonState state) {
        return new PersonEntity(
                state.getPersonId(),
                state.getCompanyId(),
                state.getPersonName(),
                state.getPersonRole(),
                state.getPersonEmail(),
                state.getPersonPhone()
        );
    }
}
