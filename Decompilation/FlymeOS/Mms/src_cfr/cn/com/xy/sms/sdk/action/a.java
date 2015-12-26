/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.util.concurrent.FutureTask
 *  java.util.concurrent.TimeUnit
 */
package cn.com.xy.sms.sdk.action;

import cn.com.xy.sms.sdk.action.b;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

final class a {
    a() {
    }

    public static String a(String string2) {
        string2 = new FutureTask((Callable)new b(string2));
        new Thread((Runnable)((Object)string2)).start();
        return (String)string2.get(5000, TimeUnit.MILLISECONDS);
    }
}

