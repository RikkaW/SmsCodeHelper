/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.amap.api.mapcore2d;

import android.content.Context;
import com.amap.api.mapcore2d.ed;
import com.amap.api.mapcore2d.ek$1;
import com.amap.api.mapcore2d.ek$2;
import com.amap.api.mapcore2d.en;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

public class ek {
    static final String a = "/a/";
    static final String b = "b";
    static final String c = "c";
    static final String d = "d";

    static void a(Context context) {
        en en2;
        block3 : {
            try {
                en2 = en.a(2);
                if (en2 != null) break block3;
                return;
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
                return;
            }
        }
        en2.a(context);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    static void a(Context context, Throwable throwable, int n2, String string2, String string3) {
        ExecutorService executorService = ed.a();
        if (executorService == null) return;
        try {
            if (executorService.isShutdown()) {
                return;
            }
            executorService.submit(new ek$1(n2, context, throwable, string2, string3));
            return;
        }
        catch (RejectedExecutionException var0_1) {
            return;
        }
        catch (Throwable var0_2) {
            var0_2.printStackTrace();
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    static void b(Context context) {
        ExecutorService executorService = ed.a();
        if (executorService == null) return;
        try {
            if (executorService.isShutdown()) {
                return;
            }
            executorService.submit(new ek$2(context));
            return;
        }
        catch (Throwable var0_1) {
            ed.a(var0_1, "Log", "processLog");
            var0_1.printStackTrace();
        }
    }
}

