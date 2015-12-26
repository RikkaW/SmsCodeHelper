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

public enum ParseType {
    NoPass,
    OnlyPass,
    PassAndRejectOriginal,
    PassAndAcceptOriginal,
    PassAndAcceptNew,
    PassAndCandidateOriginal,
    PassAndCandidateNew;
    

    private ParseType(String string3, int n2) {
    }
}

