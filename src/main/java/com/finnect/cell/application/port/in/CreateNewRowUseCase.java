package com.finnect.cell.application.port.in;

import com.finnect.cell.domain.Column;
import com.finnect.cell.domain.DataRow;

public interface CreateNewRowUseCase {
    DataRow createNewDataRow(Column column);

}