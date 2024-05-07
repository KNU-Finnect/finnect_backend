package com.finnect.crm.application.port.in.cell;

import com.finnect.crm.domain.cell.DataColumn;
import com.finnect.crm.domain.cell.DataRow;

public interface CreateNewRowUseCase {
    DataRow createNewDataRow(DataColumn dataColumn);
}