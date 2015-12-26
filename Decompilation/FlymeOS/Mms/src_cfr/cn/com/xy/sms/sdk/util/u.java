/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 */
package cn.com.xy.sms.sdk.util;

import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.net.util.h;

final class u
implements XyCallBack {
    u() {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public final /* varargs */ void execute(Object ... arrobject) {
        if (arrobject == null || !arrobject[0].toString().equals((Object)"0") || arrobject.length != 2 || !h.c(arrobject[1].toString())) return;
        SysParamEntityManager.setParam("LastPostIccidSceneTime", String.valueOf((long)System.currentTimeMillis()));
        SysParamEntityManager.setParam("PostCount", String.valueOf((long)(SysParamEntityManager.getLongParam("PostCount", 0, Constant.getContext()) + 1)));
        try {
            DBManager.delete("tb_count_scene", null, null);
            return;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }
}

