package com.naganocake.util;

import jakarta.servlet.http.HttpServletRequest;

import com.naganocake.model.Member;

// member情報をJSPから取得する共通処理
public class MemberUtil {
	
    public static Member createMemberFromRequest(HttpServletRequest request) {
        Member member = new Member();
        member.setEmail(request.getParameter("email"));
        member.setPassword(request.getParameter("password"));
        member.setLastName(request.getParameter("lastName"));
        member.setFirstName(request.getParameter("firstName"));
        member.setLastNameKana(request.getParameter("lastNameKana"));
        member.setFirstNameKana(request.getParameter("firstNameKana"));
        member.setPostalCode(request.getParameter("postalCode"));
        member.setAddress(request.getParameter("address"));
        member.setPhoneNumber(request.getParameter("phoneNumber"));
        return member;
    }
}
