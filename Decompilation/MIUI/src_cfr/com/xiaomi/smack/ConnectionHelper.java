/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.xiaomi.smack;

import com.xiaomi.smack.XMPPException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class ConnectionHelper {
    public static int asErrorCode(Throwable throwable) {
        Object object;
        Object object2 = object = throwable;
        if (object instanceof XMPPException) {
            object2 = object;
            if (((XMPPException)((Object)object)).getWrappedThrowable() != null) {
                object2 = ((XMPPException)((Object)object)).getWrappedThrowable();
            }
        }
        object = object2.getMessage();
        if (object2.getCause() != null) {
            object = object2.getCause().getMessage();
        }
        if (object2 instanceof SocketTimeoutException) {
            return 105;
        }
        if (object2 instanceof SocketException) {
            if (object.indexOf("Network is unreachable") != -1) {
                return 102;
            }
            if (object.indexOf("Connection refused") != -1) {
                return 103;
            }
            if (object.indexOf("Connection timed out") != -1) {
                return 105;
            }
            if (object.endsWith("EACCES (Permission denied)")) {
                return 101;
            }
            if (object.indexOf("Connection reset by peer") != -1) {
                return 109;
            }
            if (object.indexOf("Broken pipe") != -1) {
                return 110;
            }
            if (object.indexOf("No route to host") != -1) {
                return 104;
            }
            if (object.endsWith("EINVAL (Invalid argument)")) {
                return 106;
            }
            return 199;
        }
        if (object2 instanceof UnknownHostException) {
            return 107;
        }
        if (throwable instanceof XMPPException) {
            return 399;
        }
        return 0;
    }
}

