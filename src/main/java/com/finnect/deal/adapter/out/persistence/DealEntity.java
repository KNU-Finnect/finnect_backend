package com.finnect.company.adapter.out.persistence.entity;

import com.finnect.mockDomain.Member;
import com.finnect.mockDomain.DataRow;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Builder
@Entity(name = "deal")
public class DealEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dealId;

    @Setter
    @Getter
    private String dealName;


    // Identified By Company
    // Mock Entity
    @OneToOne(fetch = FetchType.EAGER)
    @Getter
    private CompanyEntity companyEntity;
    // Identified By userId & workspaceId
    // Mock Entity

    @OneToOne(fetch = FetchType.EAGER)
    @Getter
    private Member member;
    // Identified By rowId
    // Mock Entity

    @OneToOne(fetch = FetchType.EAGER)
    @Getter
    private DataRow dataRow;

    protected DealEntity() {}
}
