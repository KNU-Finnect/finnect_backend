package com.finnect.deal.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.finnect.mockDomain.Company;
import com.finnect.mockDomain.Member;
import com.finnect.mockDomain.Row;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealTest {
    Company company;
    Row row;
    Member member;

    @BeforeEach
    void setUp() {
        company = new Company();
        row = new Row();
        member = new Member();
    }

    @Test
    @DisplayName("딜 생성 테스트")
    void createDealTest(){
        //when
        Deal deal = Deal.builder()
                .dealId(1)
                .dealName("test")
                .company(company)
                .row(row)
                .member(member)
                .build();
        //then
        assertEquals(deal.getCompany(), company);
        assertEquals(deal.getRow(), row);
        assertEquals(deal.getMember(), member);
    }

    @Test
    @DisplayName("딜 이름 변경 테스트")
    void setDealNameTest() {
        Deal deal = Deal.builder()
                .dealId(1)
                .dealName("test")
                .company(company)
                .row(row)
                .member(member)
                .build();

        deal.setDealName("Test2");

        //then
        assertEquals(deal.getCompany(), company);
        assertEquals(deal.getRow(), row);
        assertEquals(deal.getMember(), member);
        assertEquals(deal.getDealName(), "Test2");
    }
}