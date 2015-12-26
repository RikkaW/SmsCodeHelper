/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.smack.packet;

public class StreamError {
    private String code;

    public StreamError(String string2) {
        this.code = string2;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("stream:error (").append(this.code).append(")");
        return stringBuilder.toString();
    }
}

