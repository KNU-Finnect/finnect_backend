package com.finnect.crm.adapter.out.persistence.company;

import com.finnect.crm.domain.company.CompanyState;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name = "company")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE) @Builder
@Getter
public class CompanyEntity implements CompanyState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    private Long workspaceId;

    private Long dataRowId;

    private String domain;

    private String companyName;

    public static CompanyEntity from(CompanyState state) {
        return CompanyEntity.builder()
                .companyId(state.getCompanyId())
                .workspaceId(state.getWorkspaceId())
                .dataRowId(state.getDataRowId())
                .domain(state.getDomain())
                .companyName(state.getCompanyName())
                .build();
    }
}
