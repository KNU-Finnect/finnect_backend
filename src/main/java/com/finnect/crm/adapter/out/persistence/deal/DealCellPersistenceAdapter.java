package com.finnect.crm.adapter.out.persistence.deal;

import com.finnect.crm.adapter.out.persistence.cell.DataCellEntity;
import com.finnect.crm.application.port.out.deal.LoadDealWithCellPort;
import com.finnect.crm.domain.deal.DealCell;
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
class DealCellPersistenceAdapter implements LoadDealWithCellPort {
    @PersistenceContext
    private final EntityManager em;

    private final int BATCH_SIZE = 10;
    @Override
    public List<DealCell> loadDealsWithCellsByFilter(List<FilterState> filters, final int startPage, final int columnCount) {

        String queryString = generateQuery(filters);
        log.info(queryString);
        TypedQuery<Object[]> query = em.createQuery(generateQuery(filters), Object[].class);
        setParam(filters, query);
        query.setFirstResult(BATCH_SIZE * (startPage - 1));
        query.setMaxResults(BATCH_SIZE * columnCount);
        List<Object[]> objects = query.getResultList();
        for(Object[] object : objects){
            log.info("object = " + Arrays.toString(object));
        }
        return toDomain(objects);
    }

    private String generateQuery(List<FilterState> filters){

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT d, c ")
                .append("FROM deal d ")
                .append("JOIN FETCH data_cell c ON d.dataRowId = c.cellId.dataRowId ");

        int valueIndex = 0;
        if (!filters.isEmpty()) {
            queryBuilder.append("WHERE c.cellId.dataRowId IN ( ")
                    .append("SELECT c2.cellId.dataRowId ")
                    .append("FROM data_cell c2 ")
                    .append("WHERE ");
            for (int i = 0; i < filters.size(); i++) {
                FilterState filter = filters.get(i);

                // 쿼리 조건 추가
                if (i > 0) {
                    queryBuilder.append(" AND ");
                }
                queryBuilder.append("(c2.cellId.columnId = :columnId")
                        .append(valueIndex)
                        .append(" AND ")
                        .append("c2.value ")
                        .append(filter.getFilterCondition().getOperator())
                        .append(" :value")
                        .append(valueIndex++)
                        .append(" )");
            }
            queryBuilder.append(")");
        }
        return queryBuilder.toString();
    }

    private void setParam(List<FilterState> filters, Query query){
        String columnBase = "columnId";
        String valueBase = "value";
        for(int i = 0; i < filters.size(); i++){
            FilterState filter = filters.get(i);
            query.setParameter(columnBase + i, filter.getColumnId());
            query.setParameter(valueBase + i, filter.getValue());
        }

    }

    private List<DealCell> toDomain(List<Object[]> objects){
        Map<Long, DealCell> dealMap = new HashMap<>();
        for(Object[] object : objects){
            DealEntity deal = (DealEntity) object[0];
            DataCellEntity cell = (DataCellEntity) object[1];
            if(!dealMap.containsKey(deal.getDataRowId())){
                dealMap.put(deal.getDataRowId(), new DealCell(deal.getDealId(), deal.getCompanyId(), deal.getDealName()));
            }
            DealCell dealCell = dealMap.get(deal.getDataRowId());
            dealCell.addCell(cell);
        }
        return dealMap.values().stream().toList();
    }
}
