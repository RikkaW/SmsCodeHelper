/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.amap.api.mapcore2d;

public class cz
extends Exception {
    private String a = "\u672a\u77e5\u7684\u9519\u8bef";
    private int b = -1;

    public cz(String string2) {
        super(string2);
        this.a(string2);
    }

    private void a(String string2) {
        if ("IO \u64cd\u4f5c\u5f02\u5e38 - IOException".equals((Object)string2)) {
            this.b = 21;
            return;
        }
        if ("socket \u8fde\u63a5\u5f02\u5e38 - SocketException".equals((Object)string2)) {
            this.b = 22;
            return;
        }
        if ("socket \u8fde\u63a5\u8d85\u65f6 - SocketTimeoutException".equals((Object)string2)) {
            this.b = 23;
            return;
        }
        if ("\u65e0\u6548\u7684\u53c2\u6570 - IllegalArgumentException".equals((Object)string2)) {
            this.b = 24;
            return;
        }
        if ("\u7a7a\u6307\u9488\u5f02\u5e38 - NullPointException".equals((Object)string2)) {
            this.b = 25;
            return;
        }
        if ("url\u5f02\u5e38 - MalformedURLException".equals((Object)string2)) {
            this.b = 26;
            return;
        }
        if ("\u672a\u77e5\u4e3b\u673a - UnKnowHostException".equals((Object)string2)) {
            this.b = 27;
            return;
        }
        if ("\u670d\u52a1\u5668\u8fde\u63a5\u5931\u8d25 - UnknownServiceException".equals((Object)string2)) {
            this.b = 28;
            return;
        }
        if ("\u534f\u8bae\u89e3\u6790\u9519\u8bef - ProtocolException".equals((Object)string2)) {
            this.b = 29;
            return;
        }
        if ("http\u8fde\u63a5\u5931\u8d25 - ConnectionException".equals((Object)string2)) {
            this.b = 30;
            return;
        }
        if ("\u672a\u77e5\u7684\u9519\u8bef".equals((Object)string2)) {
            this.b = 31;
            return;
        }
        if ("key\u9274\u6743\u5931\u8d25".equals((Object)string2)) {
            this.b = 32;
            return;
        }
        if ("requeust is null".equals((Object)string2)) {
            this.b = 1;
            return;
        }
        if ("request url is empty".equals((Object)string2)) {
            this.b = 2;
            return;
        }
        if ("response is null".equals((Object)string2)) {
            this.b = 3;
            return;
        }
        if ("thread pool has exception".equals((Object)string2)) {
            this.b = 4;
            return;
        }
        if ("sdk name is invalid".equals((Object)string2)) {
            this.b = 5;
            return;
        }
        if ("sdk info is null".equals((Object)string2)) {
            this.b = 6;
            return;
        }
        if ("sdk packages is null".equals((Object)string2)) {
            this.b = 7;
            return;
        }
        if ("\u7ebf\u7a0b\u6c60\u4e3a\u7a7a".equals((Object)string2)) {
            this.b = 8;
            return;
        }
        this.b = -1;
    }

    public String a() {
        return this.a;
    }
}

