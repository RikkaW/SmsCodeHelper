/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.File
 *  java.lang.Object
 */
package cn.com.xy.sms.sdk.util;

import cn.com.xy.sms.sdk.util.e;
import java.io.File;

final class h
implements Runnable {
    private final /* synthetic */ File a;

    h(File file) {
        this.a = file;
    }

    @Override
    public final void run() {
        e.a(this.a);
    }
}

