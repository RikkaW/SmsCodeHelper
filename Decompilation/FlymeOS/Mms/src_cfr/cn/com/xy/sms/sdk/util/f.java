/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package cn.com.xy.sms.sdk.util;

import cn.com.xy.sms.sdk.db.ParseItemManager;

final class f
implements Runnable {
    f() {
    }

    @Override
    public final void run() {
        ParseItemManager.deleteRepeatData();
        ParseItemManager.updateStatue(-2, 0);
    }
}

