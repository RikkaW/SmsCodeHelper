/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package cn.com.xy.sms.sdk.util;

import cn.com.xy.sms.sdk.db.ParseItemManager;

final class g
implements Runnable {
    g() {
    }

    @Override
    public final void run() {
        ParseItemManager.updateStatue(0, -2);
    }
}

