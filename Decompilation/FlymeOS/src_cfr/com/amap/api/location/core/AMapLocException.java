/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.amap.api.location.core;

public class AMapLocException
extends Exception {
    public static final int ERROR_CODE_CONNECTION = 30;
    public static final int ERROR_CODE_FAILURE_AUTH = 32;
    public static final int ERROR_CODE_FAILURE_INFO = 33;
    public static final int ERROR_CODE_FAILURE_LOCATION = 34;
    public static final int ERROR_CODE_INVALID_PARAMETER = 24;
    public static final int ERROR_CODE_IO = 21;
    public static final int ERROR_CODE_NULL_PARAMETER = 25;
    public static final int ERROR_CODE_PROTOCOL = 29;
    public static final int ERROR_CODE_SOCKET = 22;
    public static final int ERROR_CODE_SOCKE_TIME_OUT = 23;
    public static final int ERROR_CODE_UNKNOWN = 31;
    public static final int ERROR_CODE_UNKNOW_HOST = 27;
    public static final int ERROR_CODE_UNKNOW_SERVICE = 28;
    public static final int ERROR_CODE_URL = 26;
    public static final String ERROR_CONNECTION = "http\u8fde\u63a5\u5931\u8d25 - ConnectionException";
    public static final String ERROR_FAILURE_AUTH = "key\u9274\u6743\u5931\u8d25";
    public static final String ERROR_FAILURE_INFO = "\u83b7\u53d6\u57fa\u7ad9/WiFi\u4fe1\u606f\u4e3a\u7a7a\u6216\u5931\u8d25";
    public static final String ERROR_FAILURE_LOCATION = "\u5b9a\u4f4d\u5931\u8d25\u65e0\u6cd5\u83b7\u53d6\u57ce\u5e02\u4fe1\u606f";
    public static final String ERROR_INVALID_PARAMETER = "\u65e0\u6548\u7684\u53c2\u6570 - IllegalArgumentException";
    public static final String ERROR_IO = "IO \u64cd\u4f5c\u5f02\u5e38 - IOException";
    public static final String ERROR_NULL_PARAMETER = "\u7a7a\u6307\u9488\u5f02\u5e38 - NullPointException";
    public static final String ERROR_PROTOCOL = "\u534f\u8bae\u89e3\u6790\u9519\u8bef - ProtocolException";
    public static final String ERROR_SOCKET = "socket \u8fde\u63a5\u5f02\u5e38 - SocketException";
    public static final String ERROR_SOCKE_TIME_OUT = "socket \u8fde\u63a5\u8d85\u65f6 - SocketTimeoutException";
    public static final String ERROR_UNKNOWN = "\u672a\u77e5\u7684\u9519\u8bef";
    public static final String ERROR_UNKNOW_HOST = "\u672a\u77e5\u4e3b\u673a - UnKnowHostException";
    public static final String ERROR_UNKNOW_SERVICE = "\u670d\u52a1\u5668\u8fde\u63a5\u5931\u8d25 - UnknownServiceException";
    public static final String ERROR_URL = "url\u5f02\u5e38 - MalformedURLException";
    private String a = "\u6b63\u5e38";
    private int b = 0;

    public AMapLocException() {
    }

    public AMapLocException(String string2) {
        this.a = string2;
        this.a(string2);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(String string2) {
        if ("IO \u64cd\u4f5c\u5f02\u5e38 - IOException".equals((Object)string2)) {
            this.b = 21;
            return;
        } else {
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
            if ("\u83b7\u53d6\u57fa\u7ad9/WiFi\u4fe1\u606f\u4e3a\u7a7a\u6216\u5931\u8d25".equals((Object)string2)) {
                this.b = 33;
                return;
            }
            if (!"\u5b9a\u4f4d\u5931\u8d25\u65e0\u6cd5\u83b7\u53d6\u57ce\u5e02\u4fe1\u606f".equals((Object)string2)) return;
            {
                this.b = 34;
                return;
            }
        }
    }

    public int getErrorCode() {
        return this.b;
    }

    public String getErrorMessage() {
        return this.a;
    }
}

