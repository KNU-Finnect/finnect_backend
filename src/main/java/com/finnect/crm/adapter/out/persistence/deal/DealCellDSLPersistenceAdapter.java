package com.finnect.crm.adapter.out.persistence.deal;

import com.finnect.crm.adapter.out.persistence.cell.QDataCellEntity;
import com.finnect.crm.adapter.out.persistence.column.QDataColumnEntity;
import com.finnect.crm.adapter.out.persistence.company.QCompanyEntity;
import com.finnect.crm.application.port.out.deal.LoadDealWithCellDSLPort;
import com.finnect.crm.domain.company.CompanyState;
import com.finnect.crm.domain.deal.DealCellDetail;
import com.finnect.workspace.adaptor.out.persistence.QMemberEntity;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@RequiredArgsConstructor
public class DealCellDSLPersistenceAdapter implements LoadDealWithCellDSLPort {

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public DealCellDetail queryDSLTest(Long dealId) {
        QDealEntity deal = QDealEntity.dealEntity;
        QDataCellEntity dataCell = QDataCellEntity.dataCellEntity;
        QCompanyEntity company = QCompanyEntity.companyEntity;
        QDataColumnEntity dataColumn = QDataColumnEntity.dataColumnEntity;
        QMemberEntity member = QMemberEntity.memberEntity;
        List<Tuple> results = jpaQueryFactory
                .select(deal, company, dataCell, dataColumn, member)
                .from(deal)
                .leftJoin(dataCell).on(deal.dataRowId.eq(dataCell.cellId.dataRowId))
                .leftJoin(company).on(deal.companyId.eq(company.companyId))
                .leftJoin(dataColumn).on(dataCell.cellId.columnId.eq(dataColumn.columnId))
                .leftJoin(member).on(deal.userId.eq(member.memberId.userId))
                .where(deal.dealId.eq(dealId))
                .fetch();
        DealCellDetail dealCellDetail = DealCellDetail.builder()
                .dealId(results.get(0).get(deal).getDealId())
                .dealName(results.get(0).get(deal).getDealName())
                .companyId(results.get(0).get(deal).getCompanyId())
                .companyName(results.get(0).get(company).getCompanyName())
                .userId(results.get(0).get(member).getUserId())
                .userName(results.get(0).get(member).getNickname())
                .build();

        for(Tuple tuple : results){
            dealCellDetail.appendCell(
                    tuple.get(dataCell).toDomain()
            );
            dealCellDetail.appendColumn(
                    tuple.get(dataColumn).toDomain()
            );
        }

        log.info(dealCellDetail.toString());

        return dealCellDetail;
    }
}
