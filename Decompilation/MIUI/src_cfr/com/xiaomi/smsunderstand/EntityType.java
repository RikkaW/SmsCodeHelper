/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
package com.xiaomi.smsunderstand;

public enum EntityType {
    HUMAN_NAME,
    NUMBER,
    CHAR,
    ENG,
    SPECIALENTITY,
    ZHIFUBAO,
    XIAOMICAIPIAO,
    XIAOMIDINGDAN,
    DUOKANTUSHUQUAN,
    CREDITCARDHUANKUAN,
    RESPONSE,
    TOPUP,
    REALNUMBER,
    TIMESPAN,
    FLOW,
    MONEY,
    BANKCARDNUMBER,
    EXPRESSNAME,
    EXPRESSNUMBER,
    PHONENUMBER,
    URL,
    TIME,
    VERIFICATIONCODE,
    UNKNOWN,
    DROP;
    

    private EntityType(String string3, int n2) {
    }
}

