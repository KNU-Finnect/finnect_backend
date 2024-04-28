package com.finnect.deal.adapter.out;

import com.finnect.deal.adapter.out.persistence.DealEntity;
import com.finnect.deal.application.DealState;
import com.finnect.deal.application.port.out.LoadDealPort;
import com.finnect.deal.application.port.out.SaveDealPort;
import com.finnect.deal.domain.Deal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DealPersistenceAdapter implements SaveDealPort {

    private final DealRepository dealRepository;


    @Override
    public Deal saveDeal(DealState deal) {
        DealEntity dealEntity = DealEntity.toPersistence(deal);
        log.info(dealEntity.toString());
        dealEntity = dealRepository.save(dealEntity);

        log.info("saved : " + dealEntity.toString());
        return dealEntity.toDomain();
    }
}
