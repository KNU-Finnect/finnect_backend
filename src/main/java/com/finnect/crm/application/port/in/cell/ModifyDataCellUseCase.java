package com.finnect.crm.application.port.in.cell;

import com.finnect.crm.domain.cell.DataCell;

public interface ModifyDataCellUseCase {

    void resetCellInfoByModifyColumn(DataCell dataCell);

    void modifyCellInfo(DataCell dataCell);
}
