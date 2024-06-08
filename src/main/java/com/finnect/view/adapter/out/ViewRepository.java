package com.finnect.view.adapter.out;

import com.finnect.crm.domain.column.DataType;
import com.finnect.view.adapter.out.persistence.ViewEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewRepository extends JpaRepository<ViewEntity, Long> {

    List<ViewEntity> findViewEntitiesByWorkspaceIdAndViewType(Long workspaceId, DataType dType);
    ViewEntity findByWorkspaceIdAndViewType(Long workspaceId, DataType dType);
    Optional<ViewEntity> findByWorkspaceIdAndViewTypeAndIsMain(Long workspaceId, DataType dType, Boolean isMain);
}
