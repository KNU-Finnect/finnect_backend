package com.finnect.company.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import com.finnect.mockDomain.Company;
import com.finnect.mockDomain.Member;
import com.finnect.mockDomain.DataRow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealTest {
    Company company;
    DataRow dataRow;
    Member member;

    @BeforeEach
    void setUp() {
        company = mock(Company.class);
        dataRow = mock(DataRow.class);
        member = mock(Member.class);
    }

    @Test
    @DisplayName("딜 생성 테스트")
    void createDealTest(){
        //given

        //when
        Deal deal = Deal.builder()
                .dealId(1)
                .dealName("test")
                .company(company)
                .dataRow(dataRow)
                .member(member)
                .build();
        //then
        assertEquals(deal.getCompany(), company);
        assertEquals(deal.getDataRow(), dataRow);
        assertEquals(deal.getMember(), member);
    }

    @Test
    @DisplayName("딜 이름 변경 테스트")
    void setDealNameTest() {
        Deal deal = Deal.builder()
                .dealId(1)
                .dealName("test")
                .company(company)
                .dataRow(dataRow)
                .member(member)
                .build();

        deal.setDealName("Test2");

        //then

        assertEquals(deal.getCompany(), company);
        assertEquals(deal.getDataRow(), dataRow);
        assertEquals(deal.getMember(), member);
        assertEquals(deal.getDealName(), "Test2");
    }
}