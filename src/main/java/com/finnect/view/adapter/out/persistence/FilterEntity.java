package com.finnect.view.adapter.out.persistence;

import com.finnect.view.domain.constant.FilterCondition;
import com.finnect.view.domain.state.FilterState;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.ToString;

@Entity(name = "filter")
@ToString
public class FilterEntity implements FilterState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long filterId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "view_id")
    private ViewEntity view;
    private Long columnId;
    @Enumerated(EnumType.STRING)
    private FilterCondition filterCondition;
    private String value;

    protected FilterEntity(){}
    @Builder
    public FilterEntity(Long filterId, Long columnId, FilterCondition filterCondition, String value) {
        this.filterId = filterId;
        this.columnId = columnId;
        this.filterCondition = filterCondition;
        this.value = value;
    }


    @Override
    public Long getFilterId() {
        return this.filterId;
    }

    @Override
    public Long getViewId() {
        return this.view.getViewId();
    }

    @Override
    public Long getColumnId() {
        return this.columnId;
    }

    @Override
    public FilterCondition getFilterCondition() {
        return this.filterCondition;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
