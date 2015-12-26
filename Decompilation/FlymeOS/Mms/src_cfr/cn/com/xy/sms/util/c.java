/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
package cn.com.xy.sms.util;

import cn.com.xy.sms.sdk.db.base.BaseManager;
import cn.com.xy.sms.sdk.db.entity.MatchCacheManager;
import cn.com.xy.sms.sdk.util.StringUtils;

final class c
implements Runnable {
    private final /* synthetic */ String a;
    private final /* synthetic */ String b;
    private final /* synthetic */ String c;
    private final /* synthetic */ String d;
    private final /* synthetic */ long e;

    c(String string2, String string3, String string4, String string5, long l2) {
        this.a = string2;
        this.b = string3;
        this.c = string4;
        this.d = string5;
        this.e = l2;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public final void run() {
        String string2 = MatchCacheManager.getMD5(this.a, this.b);
        if (!StringUtils.isNull(string2)) {
            String string3 = StringUtils.getPhoneNumberNo86(this.a);
            String string4 = this.c;
            String string5 = this.d == null ? "" : this.d;
            MatchCacheManager.insertOrUpdate(BaseManager.getContentValues(null, "msg_num_md5", string2, "phonenum", string3, "msg_id", string4, "session_reuslt", string5, "save_time", String.valueOf((long)this.e), "session_lasttime", String.valueOf((long)System.currentTimeMillis())), 1);
        }
    }
}

