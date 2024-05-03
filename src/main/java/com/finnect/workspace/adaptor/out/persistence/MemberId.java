package com.finnect.workspace.adaptor.out.persistence;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public
class MemberId implements Serializable {
    private Long userId;
    private Long workspaceId;
}
