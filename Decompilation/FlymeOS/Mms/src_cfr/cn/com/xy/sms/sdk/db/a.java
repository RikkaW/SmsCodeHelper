/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Thread
 */
package cn.com.xy.sms.sdk.db;

import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.AirManager;

final class a
extends Thread {
    a() {
    }

    public final void run() {
        AirManager.importAirData(Constant.getContext());
    }
}

