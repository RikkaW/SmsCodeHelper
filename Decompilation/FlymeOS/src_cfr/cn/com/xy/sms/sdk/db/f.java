/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Thread
 */
package cn.com.xy.sms.sdk.db;

import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.TrainManager;

final class f
extends Thread {
    f() {
    }

    public final void run() {
        TrainManager.importTrainData(Constant.getContext());
    }
}

