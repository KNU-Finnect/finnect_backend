package com.finnect.cell.application.port.in;

import com.finnect.cell.domain.DataColumn;
import com.finnect.cell.domain.DataRow;

public interface CreateNewRowUseCase {
    DataRow createNewDataRow(DataColumn dataColumn);
}