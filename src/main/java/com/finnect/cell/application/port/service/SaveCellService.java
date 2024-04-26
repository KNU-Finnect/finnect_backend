package com.finnect.cell.application.port.service;

import com.finnect.cell.application.port.in.SaveCellUseCase;
import com.finnect.cell.application.port.out.SaveDataRowPort;
import com.finnect.cell.domain.DataRow;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveCellService implements SaveCellUseCase {

    private final SaveDataRowPort saveDataRowPort;
    @Override
    public DataRow createNewDatarow() {
        return saveDataRowPort.createNewDataRow();
    }
}
