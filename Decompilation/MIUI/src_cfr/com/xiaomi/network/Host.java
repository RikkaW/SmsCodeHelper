/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.net.InetSocketAddress
 */
package com.xiaomi.network;

import java.net.InetSocketAddress;

public final class Host {
    private String hostAddress;
    private int port;

    public Host(String string2, int n) {
        this.hostAddress = string2;
        this.port = n;
    }

    public static InetSocketAddress from(String object, int n) {
        object = Host.parse((String)object, n);
        return new InetSocketAddress(object.getHost(), object.getPort());
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static Host parse(String string2, int n) {
        int n2;
        int n4 = string2.lastIndexOf(":");
        String string4 = string2;
        int n3 = n2 = n;
        if (n4 == -1) return new Host(string4, n3);
        String string3 = string2.substring(0, n4);
        try {
            n2 = n3 = Integer.parseInt((String)string2.substring(n4 + 1));
            string4 = string3;
            n3 = n2;
            if (n2 > 0) return new Host(string4, n3);
            n3 = n;
            string4 = string3;
        }
        catch (NumberFormatException numberFormatException) {
            string4 = string3;
            n3 = n2;
            return new Host(string4, n3);
        }
        return new Host(string4, n3);
    }

    public String getHost() {
        return this.hostAddress;
    }

    public int getPort() {
        return this.port;
    }

    public String toString() {
        if (this.port > 0) {
            return this.hostAddress + ":" + this.port;
        }
        return this.hostAddress;
    }
}

