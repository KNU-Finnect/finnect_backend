package com.finnect.crm.adapter.out.persistence.company;

import com.finnect.crm.adapter.out.persistence.cell.DataRowEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;

@Entity(name = "company")
class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long companyId;


    @OneToOne(fetch = FetchType.EAGER)
    @Getter
    private DataRowEntity dataRowEntity;

}
