package com.finnect.view.adapter.out.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import java.io.Serializable;

@Embeddable
public class ViewColumnId implements Serializable {
    @Column(name = "view_id")
    private Long viewId;
    @Column(name = "column_id")
    private Long columnId;

    protected ViewColumnId() {
    }
    public ViewColumnId(Long viewId, Long columnId) {
        this.viewId = viewId;
        this.columnId = columnId;
    }

    public Long getViewId() {
        return viewId;
    }

    public Long getColumnId() {
        return columnId;
    }
}
