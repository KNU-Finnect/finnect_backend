package com.finnect.view.adapter.out;

import com.finnect.crm.domain.column.DataType;
import com.finnect.view.adapter.out.persistence.ViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewRepository extends JpaRepository<ViewEntity, Long> {

    ViewEntity findViewEntityByWorkspaceIdAndViewType(Long workspaceId, DataType dType);
}
