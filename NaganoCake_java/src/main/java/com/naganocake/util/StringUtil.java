package com.naganocake.util;

public class StringUtil {
	
    // null または "" または "   " を空とみなす
    public static boolean isNullOrBlank(String s) {
    	
    	// 先頭と末尾のすべてのスペースを削除
        return s == null || s.strip() .isEmpty();
        
       // trim()メソッドを使用していたがこれは全角スペースを消してくれない古いライブラリなので、
       // 最新（Java11から追加）のstrip()メソッドを使用
    }

    // null または "" を空とみなす（空白は許容）
    public static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }
	
}