package com.finnect.workspace.adaptor.out.persistence;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity(name = "member")
@NoArgsConstructor
public class MemberJpaEntity {

    @EmbeddedId
    private MemberId memberId;
    private String nickname;
    private String role;
    private String phone;

    @Builder
    public MemberJpaEntity(MemberId memberId, String nickname, String role, String phone) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.role = role;
        this.phone = phone;
    }
}
