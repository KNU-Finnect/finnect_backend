package com.finnect.workspace.adaptor.out.persistence;

import com.finnect.workspace.MemberState;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "member")
@NoArgsConstructor
@Getter
public class MemberEntity implements MemberState {

    @EmbeddedId
    private MemberId memberId;
    private String nickname;
    private String role;
    private String phone;

    @Builder
    public MemberEntity(MemberId memberId, String nickname, String role, String phone) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.role = role;
        this.phone = phone;
    }

    @Override
    public Long getUserId() {
        return this.memberId.getUserId();
    }

    @Override
    public Long getWorkspaceId() {
        return this.memberId.getWorkspaceId();
    }
}
