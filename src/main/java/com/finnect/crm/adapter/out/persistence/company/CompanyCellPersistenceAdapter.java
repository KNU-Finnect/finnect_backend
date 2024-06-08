package com.finnect.crm.adapter.out.persistence.company;

import com.finnect.crm.adapter.out.persistence.cell.DataCellEntity;
import com.finnect.crm.application.port.out.company.LoadCompanyWithCellPort;
import com.finnect.crm.domain.company.CompanyCell;
import com.finnect.view.domain.state.FilterState;
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
        String queryString = generateQuery(filters);
        log.info(queryString);
        log.info("filter count : " + filters.size());
        TypedQuery<Object[]> query = em.createQuery(generateQuery(filters), Object[].class);
        query.setParameter("workspaceId", workspaceId);
        if(!filters.isEmpty()){
            setParam(filters, query);
        }
        query.setFirstResult(BATCH_SIZE * (startPage - 1));
        query.setMaxResults(BATCH_SIZE * columnCount);
        List<Object[]> objects = query.getResultList();

        for(Object[] object : objects){
            log.info("object = " + Arrays.toString(object));
        }
        return toDomain(objects);
    }

    private String generateQuery(List<FilterState> filters){
        log.info("filter : " + filters);
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT co, c ")
                .append("FROM company co ")
                .append("JOIN data_cell c ON co.dataRowId = c.cellId.dataRowId ")
                .append("WHERE co.workspaceId = :workspaceId ");

        int valueIndex = 0;

        if (filters != null && !filters.isEmpty()) {
            queryBuilder.append("AND c.cellId.dataRowId IN ( ")
                    .append("SELECT c2.cellId.dataRowId ")
                    .append("FROM data_cell c2 ")
                    .append("WHERE ");
            for (int i = 0; i < filters.size(); i++) {
                FilterState filter = filters.get(i);

                // 쿼리 조건 추가
                if (i > 0) {
                    queryBuilder.append(" OR ");
                }
                queryBuilder.append("(c2.cellId.columnId = :columnId")
                        .append(valueIndex)
                        .append(" AND ")
                        .append("c2.value ")
                        .append(filter.getFilterCondition().getOperator())
                        .append(" :value")
                        .append(valueIndex++)
                        .append(" ) ");
            }
            queryBuilder.append("GROUP BY c2.cellId.dataRowId ")
                    .append("HAVING COUNT(c2.cellId.dataRowId) = :filterCount");
            queryBuilder.append(")");
        }
        return queryBuilder.toString();
    }

    private void setParam(List<FilterState> filters, Query query){
        if(filters.isEmpty()){
            return;
        }
        String columnBase = "columnId";
        String valueBase = "value";
        for(int i = 0; i < filters.size(); i++){
            FilterState filter = filters.get(i);
            query.setParameter(columnBase + i, filter.getColumnId());
            query.setParameter(valueBase + i, filter.getValue());

            log.info("Set parameter: " + columnBase + i + " = " + filter.getColumnId());
            log.info("Set parameter: " + valueBase + i + " = " + filter.getValue());
        }
        query.setParameter("filterCount", filters.size());

        log.info("Set parameter: filterCount = " + filters.size());
    }

    private List<CompanyCell> toDomain(List<Object[]> objects){
        Map<Long, CompanyCell> companyMap = new HashMap<>();
        for(Object[] object : objects){
            CompanyEntity company = (CompanyEntity) object[0];
            DataCellEntity cell = (DataCellEntity) object[1];
            companyMap.putIfAbsent(company.getDataRowId(), new CompanyCell(company.getCompanyId(), company.getCompanyName(), company.getDomain()));

            CompanyCell companyCell = companyMap.get(company.getDataRowId());
            companyCell.addCell(cell.toDomain());
        }
        return companyMap.values().stream().toList();
    }
}
