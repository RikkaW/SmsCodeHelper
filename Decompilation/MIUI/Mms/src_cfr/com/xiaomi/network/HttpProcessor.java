/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.network;

import android.content.Context;
import com.xiaomi.channel.commonutils.network.NameValuePair;
import java.io.IOException;
import java.util.List;

public abstract class HttpProcessor {
    private int httpRequest;

    public HttpProcessor(int n) {
        this.httpRequest = n;
    }

    public int getRequestType() {
        return this.httpRequest;
    }

    public boolean prepare(Context context, String string2, List<NameValuePair> list) throws IOException {
        return true;
    }

    public abstract String visit(Context var1, String var2, List<NameValuePair> var3) throws IOException;
}

