/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.smack;

import com.xiaomi.smack.Connection;
import com.xiaomi.smack.HttpRequestProxy;
import java.util.Map;

public class ConnectionConfiguration
implements Cloneable {
    public static String XMPP_SERVER_HOST_T = "wcc-ml-test10.bj";
    public static String xmppHost = null;
    private String connectionPoint;
    private boolean debuggerEnabled = Connection.DEBUG_ENABLED;
    private String host;
    private HttpRequestProxy httpProxy;
    private int port;
    private boolean reconnectionAllowed = true;
    private String serviceName;

    public ConnectionConfiguration(Map<String, Integer> map, int n, String string2, HttpRequestProxy httpRequestProxy) {
        this.init(map, n, string2, httpRequestProxy);
    }

    public static final String getXmppServerHost() {
        if (xmppHost != null) {
            return xmppHost;
        }
        return "app.chat.xiaomi.net";
    }

    private void init(Map<String, Integer> map, int n, String string2, HttpRequestProxy httpRequestProxy) {
        this.host = ConnectionConfiguration.getXmppServerHost();
        this.port = n;
        this.serviceName = string2;
        this.httpProxy = httpRequestProxy;
    }

    public byte[] getConnectionBlob() {
        return null;
    }

    public String getConnectionPoint() {
        return this.connectionPoint;
    }

    public String getHost() {
        return this.host;
    }

    public int getPort() {
        return this.port;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public boolean isDebuggerEnabled() {
        return this.debuggerEnabled;
    }

    public void setConnectionPoint(String string2) {
        this.connectionPoint = string2;
    }

    public void setDebuggerEnabled(boolean bl) {
        this.debuggerEnabled = bl;
    }

    public void setHost(String string2) {
        this.host = string2;
    }

    public void setServiceName(String string2) {
        this.serviceName = string2;
    }
}

