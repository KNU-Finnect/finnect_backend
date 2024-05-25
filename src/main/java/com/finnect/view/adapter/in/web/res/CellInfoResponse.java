package com.finnect.view.adapter.in.web.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class CellInfoResponse {
    private Long columnId;
    private String value;
    private Long peopleId;
    private Long companyId;
    private Long userId;
}
