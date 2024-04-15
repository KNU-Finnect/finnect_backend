package com.finnect.deal.domain;

import com.finnect.mockDomain.Company;
import com.finnect.mockDomain.Member;
import com.finnect.mockDomain.Row;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;



@Getter
@Builder
@AllArgsConstructor
public class Deal {
    private long dealId;

    @Setter
    private String dealName;


    // Identified By Company
    // Mock Entity
    private Company company;
    // Identified By userId & workspaceId
    // Mock Entity
    private Member member;
    // Identified By rowId
    // Mock Entity
    private Row row;
}
