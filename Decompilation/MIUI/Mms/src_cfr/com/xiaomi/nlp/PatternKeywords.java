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
package com.xiaomi.nlp;

public enum PatternKeywords {
    Extract,
    yyyy,
    MM,
    dd,
    HH,
    mm,
    ss,
    Score,
    Remark,
    EndWithDic,
    NoEndWithDic,
    EndWithPOS,
    NoEndWithPOS,
    StartWithDic,
    NoEqualDic,
    EqualDic,
    NoStartWithDic,
    NoStartWithPOS,
    CharLenLonger,
    CharLenLess,
    WordsLenLonger,
    WordsLenLess,
    ContainPOS,
    NoContainPOS,
    ContainDic,
    NoContainDic,
    NoNull,
    Accept,
    Reject,
    OTHER;
    

    private PatternKeywords(String string3, int n2) {
    }
}

