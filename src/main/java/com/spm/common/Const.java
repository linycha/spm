package com.spm.common;

/**
 * 一些常量
 * @author linyuc
 * @date 2022/01/12 11:34
 */
public class Const {
    public static final String CURRENT_USER = "currentUser";
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final Integer PAGE_DEFAULT_NUM = 1;
    public static final Integer PAGE_DEFAULT_SIZE = 10;
    /**
     * 用户账号状态：0：禁用，1：正常
     */
    public static final String USER_STATUS_ENABLE = "1";
    public static final String USER_STATUS_DISABLE = "0";

    public static void initPage(int current, int size){

    }
    public static long generateOrderNo(){
        long currentTime = System.currentTimeMillis();
        return currentTime + currentTime%10;
    }
}
