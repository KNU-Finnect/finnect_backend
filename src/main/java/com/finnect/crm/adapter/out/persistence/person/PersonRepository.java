package com.finnect.crm.adapter.out.persistence.person;

import com.finnect.crm.domain.person.PersonState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    List<PersonState> findAllByCompanyIdOrderByPersonName(Long companyId);

    @Query("""
        select p
        from person p
        where p.companyId in (
            select c.companyId from company c where c.workspaceId = :wid)
        order by p.personName
        """
    )
    List<PersonState> findAllByWorkspaceId(@Param("wid") Long workspaceId);
}
