package com.finnect.view.adapter.in.web.res;

import java.util.List;
import lombok.Getter;

@Getter
public class CompanyInfoResponse {
    private Long companyId;
    private String domain;
    private String companyName;
    private Long userId;
    private Long rowId;
    private List<CellInfoResponse> cells;
}
