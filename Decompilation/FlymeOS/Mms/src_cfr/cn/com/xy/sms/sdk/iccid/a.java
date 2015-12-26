/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package cn.com.xy.sms.sdk.iccid;

import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.entity.IccidInfo;
import cn.com.xy.sms.sdk.iccid.IccidLocationUtil;

final class a
implements Runnable {
    private final /* synthetic */ IccidInfo a;

    a(IccidInfo iccidInfo) {
        this.a = iccidInfo;
    }

    @Override
    public final void run() {
        IccidLocationUtil.a(Constant.getContext(), this.a, false);
    }
}

