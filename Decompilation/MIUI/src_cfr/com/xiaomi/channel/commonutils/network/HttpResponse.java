/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 */
package com.xiaomi.channel.commonutils.network;

import java.util.HashMap;
import java.util.Map;

public class HttpResponse {
    public Map<String, String> headers = new HashMap();
    public int responseCode;
    public String responseString;

    public String getResponseString() {
        return this.responseString;
    }

    public String toString() {
        return String.format((String)"resCode = %1$d, headers = %2$s, response = %3$s", (Object[])new Object[]{this.responseCode, this.headers.toString(), this.responseString});
    }
}

