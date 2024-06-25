package com.finnect.crm.adapter.out.persistence.company;

import com.finnect.crm.adapter.out.persistence.cell.DataCellEntity;
import com.finnect.crm.adapter.out.persistence.cell.QDataCellEntity;
import com.finnect.crm.application.port.out.company.LoadCompanyWithCellPort;
import com.finnect.crm.domain.company.CompanyCell;
import com.finnect.view.domain.state.FilterState;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@RequiredArgsConstructor
public class CompanyCellPersistenceAdapter implements LoadCompanyWithCellPort {

    @PersistenceContext
    private final EntityManager em;
    private final int BATCH_SIZE = 10;

    @Override
    public List<CompanyCell> loadCompaniesWithCellsByFilter(Long workspaceId, List<FilterState> filters, int startPage,
                                                            int columnCount) {
        QCompanyEntity company = QCompanyEntity.companyEntity;
        QDataCellEntity cell = QDataCellEntity.dataCellEntity;

        JPAQuery<CompanyCell> query = new JPAQuery<>(em);
        BooleanBuilder whereClause = new BooleanBuilder(company.workspaceId.eq(workspaceId));

        if (filters != null && !filters.isEmpty()) {
            BooleanBuilder cellFilter = new BooleanBuilder();
            for (FilterState filter : filters) {
                cellFilter.or(cell.cellId.columnId.eq(filter.getColumnId())
                        .and(cell.value.stringValue().eq(filter.getValue()))); // Ensure correct type matching
            }
            whereClause.and(new JPAQuery<>()
                    .select(cell.cellId.dataRowId)
                    .from(cell)
                    .where(cellFilter)
                    .groupBy(cell.cellId.dataRowId)
                    .having(cell.cellId.dataRowId.count().eq((long) filters.size()))
                    .exists());
        }

        List<CompanyCell> results = query.select(Projections.constructor(CompanyCell.class,
                        company.companyId, company.companyName, company.domain))
                .from(company)
                .join(cell)
                .on(company.dataRowId.eq(cell.dataRow.dataRowId))
                .where(whereClause)
                .offset((long) BATCH_SIZE * (startPage - 1))
                .limit((long) BATCH_SIZE * columnCount)
                .fetch();

        return results;
    }
}
