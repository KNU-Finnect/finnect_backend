package com.finnect.crm.application.port.in.cell;

import com.finnect.crm.domain.cell.DataCell;

public interface ModifyDataCellUseCase {

    void resetColumnInfoByModifyColumn(DataCell dataCell);
}
