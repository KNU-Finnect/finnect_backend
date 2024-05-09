package com.finnect.crm.domain.cell;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CellId {
    private Long columnId;
    private Long rowId;

}
