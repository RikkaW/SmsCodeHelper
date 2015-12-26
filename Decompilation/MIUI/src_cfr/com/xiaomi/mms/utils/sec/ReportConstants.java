/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.mms.utils.sec;

public class ReportConstants {

    public static class ReturnCode {
        public static String NOTSUPPORT;
        public static String OK;
        public static String OPERATE_FAIL;
        public static String SERVICE_NOTAVAILABLE;
        public static String SESSION_TIMEOUT;
        public static String TOKEN_ILLEGAL;

        static {
            OK = "0";
            SERVICE_NOTAVAILABLE = "1";
            NOTSUPPORT = "2";
            OPERATE_FAIL = "3";
            TOKEN_ILLEGAL = "4";
            SESSION_TIMEOUT = "5";
        }
    }

    public static class ReturnRSA {
        public static String JSON_KEY_PUBLIC_KEY = "key";
    }

}

