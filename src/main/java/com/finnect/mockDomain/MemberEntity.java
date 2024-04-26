package com.finnect.mockDomain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class MemberEntity {
    @EmbeddedId
    private MemberId memberId;
}
