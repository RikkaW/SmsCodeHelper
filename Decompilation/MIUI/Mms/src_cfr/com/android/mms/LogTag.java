/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.StackTraceElement
 *  java.lang.String
 *  java.lang.Thread
 *  java.lang.Throwable
 */
package com.android.mms;

import android.app.Activity;
import android.util.Log;

public class LogTag {
    public static /* varargs */ void debug(String string, Object ... arrobject) {
        Log.d((String)"Mms", (String)LogTag.logFormat(string, arrobject));
    }

    public static /* varargs */ void error(String string, Object ... arrobject) {
        Log.e((String)"Mms", (String)LogTag.logFormat(string, arrobject));
    }

    public static /* varargs */ String logFormat(String object, Object ... object2) {
        int n;
        int n2 = 0;
        for (n = 0; n < object2.length; ++n) {
            if (!(object2[n] instanceof String[])) continue;
            object2[n] = LogTag.prettyArray((String[])object2[n]);
        }
        object2 = String.format((String)object, (Object[])object2);
        Thread thread = Thread.currentThread();
        StackTraceElement[] arrstackTraceElement = thread.getStackTrace();
        n = n2;
        do {
            n2 = n;
            if (n >= arrstackTraceElement.length) break;
            n2 = n;
            if (arrstackTraceElement[n].getClassName().equals((Object)LogTag.class.getName())) break;
            ++n;
        } while (true);
        while (n2 < arrstackTraceElement.length && arrstackTraceElement[n2].getClassName().equals((Object)LogTag.class.getName())) {
            ++n2;
        }
        object = object2;
        if (n2 < arrstackTraceElement.length) {
            object = arrstackTraceElement[n2].getFileName() + "(" + arrstackTraceElement[n2].getLineNumber() + "): " + (String)object2;
        }
        return "[" + thread.getId() + "] " + (String)object;
    }

    private static String prettyArray(String[] arrstring) {
        if (arrstring.length == 0) {
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder("[");
        int n = arrstring.length - 1;
        for (int i = 0; i < n; ++i) {
            stringBuilder.append(arrstring[i]);
            stringBuilder.append(", ");
        }
        stringBuilder.append(arrstring[n]);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public static /* varargs */ void verbose(String string, Object ... arrobject) {
        Log.v((String)"Mms", (String)LogTag.logFormat(string, arrobject));
    }

    public static /* varargs */ void warn(String string, Object ... arrobject) {
        Log.w((String)"Mms", (String)LogTag.logFormat(string, arrobject));
    }

    public static void warnPossibleRecipientMismatch(String string, Activity activity) {
        Log.e((String)"Mms", (String)("WARNING!!!! " + string), (Throwable)new RuntimeException());
    }
}

