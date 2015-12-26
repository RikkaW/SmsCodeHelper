/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 */
package cn.com.xy.sms.sdk.queue;

import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.db.entity.n;
import cn.com.xy.sms.sdk.net.util.h;

final class d
implements XyCallBack {
    private final /* synthetic */ int a;

    d(int n2) {
        this.a = n2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public final /* varargs */ void execute(Object ... object) {
        if (object == null) return;
        try {
            if (!object[0].toString().equals((Object)"0") || object.length != 2) return;
            object = object[1].toString();
            int n2 = this.a;
            SysParamEntityManager.setParam("LastCheckResourseTime_" + n2, String.valueOf((long)System.currentTimeMillis()));
            n.a(h.f((String)object));
            return;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }
}

