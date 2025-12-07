package com.naganocake.dao;

import com.naganocake.model.Member;

public interface MemberDao {
    /**
     * 会員情報を新規登録する
     * @param member 登録する会員情報
     * @return 登録成功=true, 失敗=false
     */
    boolean insertMember(Member member); // insert
}