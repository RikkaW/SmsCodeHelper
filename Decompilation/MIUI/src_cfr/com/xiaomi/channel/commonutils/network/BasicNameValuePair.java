/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.channel.commonutils.network;

import com.xiaomi.channel.commonutils.network.NameValuePair;

public class BasicNameValuePair
implements NameValuePair {
    private final String name;
    private final String value;

    public BasicNameValuePair(String string2, String string3) {
        if (string2 == null) {
            throw new IllegalArgumentException("Name may not be null");
        }
        this.name = string2;
        this.value = string3;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}

