package com.finnect.view.adapter.out.persistence;

import com.finnect.view.domain.constant.SortCondition;
import com.finnect.view.domain.state.ViewColumnState;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Builder;
import lombok.ToString;

@Entity(name = "view_column")
@ToString(exclude = "view")
public class ViewColumnEntity implements ViewColumnState {

    @EmbeddedId
    private ViewColumnId viewColumnId;
    @MapsId("viewId")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "view_id")
    private ViewEntity view;

    private Double columIndex;
    private Boolean hided;
    @Enumerated(EnumType.STRING)
    private SortCondition sorting;

    protected ViewColumnEntity() {
    }
    @Builder
    public ViewColumnEntity(Long columnId, Long viewId, Double showIndex, Boolean hided, SortCondition sorting, ViewEntity view) {
        this.view = view;
        this.viewColumnId = new ViewColumnId(view.getViewId(), columnId);
        this.columIndex = showIndex;
        this.hided = hided;
        this.sorting = sorting;
    }

    @Override
    public Long getColumnId() {
        return this.viewColumnId.getColumnId();
    }

    @Override
    public Long getViewId() {
        return this.viewColumnId.getViewId();
    }

    @Override
    public Double getIndex() {
        return this.columIndex;
    }

    @Override
    public SortCondition getSort() {
        return this.sorting;
    }

    @Override
    public Boolean isHided() {
        return this.hided;
    }
}
