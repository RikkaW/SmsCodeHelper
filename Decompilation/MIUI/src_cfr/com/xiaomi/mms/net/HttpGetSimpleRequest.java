/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.String
 *  java.net.HttpURLConnection
 */
package com.xiaomi.mms.net;

import com.xiaomi.mms.net.HttpSimpleRequest;
import java.net.HttpURLConnection;
import java.net.ProtocolException;

class HttpGetSimpleRequest
extends HttpSimpleRequest {
    protected HttpGetSimpleRequest(String string2) {
        super(string2);
    }

    @Override
    public String getMethod() {
        return "GET";
    }

    @Override
    protected void setupConnection() throws ProtocolException {
        super.setupConnection();
        this.getConnection().setDoOutput(false);
    }
}

