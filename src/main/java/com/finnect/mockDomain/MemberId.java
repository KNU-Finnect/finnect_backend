package com.finnect.mockDomain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MemberId implements Serializable {

    @Column(name = "user_id")
    private Long userId;
    @Column(name = "workspace_id")
    private Long workspaceId;
}
