package com.finnect.workspace.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {
    @Test
    @DisplayName("워크스페이스 ID와 사용자 ID로 멤버 정보 생성")
    void createWorkspaceTest() {
        Long givenUserId = 1L;
        Long givenWorkspaceId = 1L;
        String nickname = "현정";
        String role = "사원";
        String phone = "010-0000-1111";

        Member member = new Member(givenUserId, givenWorkspaceId, nickname, role, phone);

        assertEquals(member.getUserId(), givenUserId);
        assertEquals(member.getWorkspaceId(), givenWorkspaceId);
        assertEquals(member.getNickname(), nickname);
        assertEquals(member.getRole(), role);
        assertEquals(member.getPhone(), phone);
    }
}