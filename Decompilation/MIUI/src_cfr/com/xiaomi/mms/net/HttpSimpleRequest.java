/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 *  java.net.HttpURLConnection
 *  java.net.URL
 *  java.net.URLConnection
 *  java.util.HashMap
 *  java.util.concurrent.atomic.AtomicBoolean
 *  miui.cloud.CloudManager
 */
package com.xiaomi.mms.net;

import android.text.TextUtils;
import com.xiaomi.mms.net.SimpleRequest;
import com.xiaomi.mms.utils.IOUtils;
import com.xiaomi.mms.utils.ObjectUtils;
import com.xiaomi.mms.utils.logger.MyLog;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import miui.cloud.CloudManager;

abstract class HttpSimpleRequest
implements SimpleRequest {
    private final Map<String, String> cookies = new HashMap();
    private final AtomicBoolean isClosed = new AtomicBoolean(false);
    private final AtomicBoolean isConnected = new AtomicBoolean(false);
    private final AtomicBoolean isConnecting = new AtomicBoolean(false);
    private HttpURLConnection mConn;
    private String url;

    protected HttpSimpleRequest(String string2) {
        this.url = string2;
        try {
            this.mConn = (HttpURLConnection)new URL(string2).openConnection();
            return;
        }
        catch (MalformedURLException var2_2) {
            throw new IllegalArgumentException("malformed url: " + string2);
        }
        catch (IOException var2_3) {
            throw new IllegalArgumentException("failed to init url for " + string2);
        }
    }

    private void checkConnection() {
        if (!this.isConnected.get()) {
            throw new IllegalStateException("connection not established");
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void checkHttpStatus() throws IOException {
        int n = -1;
        try {
            int n2;
            n = n2 = this.mConn.getResponseCode();
        }
        catch (NumberFormatException var3_3) {
            MyLog.e("HttpSimpleRequest", "check http status error");
        }
        if (n != 200 && n != 302) {
            throw new IOException("invalid http status: " + n);
        }
    }

    public void addCookie(String string2, String string3) {
        this.cookies.put(string2, string3);
    }

    @Override
    public void close() {
        if (!this.isClosed.getAndSet(true)) {
            this.mConn.disconnect();
            return;
        }
        MyLog.w("HttpSimpleRequest", "connection has been closed");
    }

    @Override
    public void connect() throws IOException {
        if (!this.isConnecting.getAndSet(true)) {
            this.setupConnection();
            this.mConn.connect();
            this.isConnected.set(true);
            return;
        }
        MyLog.w("HttpSimpleRequest", "connect has been called");
    }

    protected HttpURLConnection getConnection() {
        return this.mConn;
    }

    public abstract String getMethod();

    @Override
    public byte[] getResponseBody() throws IOException {
        this.checkConnection();
        this.checkHttpStatus();
        return IOUtils.toByteArray(this.mConn.getInputStream());
    }

    protected void setupConnection() throws ProtocolException {
        this.mConn.setInstanceFollowRedirects(false);
        this.mConn.setConnectTimeout(20000);
        this.mConn.setReadTimeout(20000);
        this.mConn.setUseCaches(false);
        this.mConn.setDoOutput(true);
        this.mConn.setDoInput(true);
        this.mConn.setRequestProperty("User-Agent", CloudManager.getUserAgent());
        String string2 = ObjectUtils.joinMap(this.cookies, "; ");
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            this.mConn.setRequestProperty("Cookie", string2);
        }
        this.mConn.setRequestMethod(this.getMethod());
    }
}

