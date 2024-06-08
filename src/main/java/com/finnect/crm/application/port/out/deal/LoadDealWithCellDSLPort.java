package com.finnect.crm.application.port.out.deal;

import com.finnect.crm.domain.deal.DealCellDetail;

public interface LoadDealWithCellDSLPort {

    DealCellDetail queryDSLTest(Long dealId);
}
