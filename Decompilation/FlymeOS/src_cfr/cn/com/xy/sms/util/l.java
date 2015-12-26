/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  org.json.JSONObject
 */
package cn.com.xy.sms.util;

import cn.com.xy.sms.sdk.db.base.BaseManager;
import cn.com.xy.sms.sdk.db.entity.MatchCacheManager;
import cn.com.xy.sms.sdk.util.StringUtils;
import org.json.JSONObject;

final class l
implements Runnable {
    private final /* synthetic */ String a;
    private final /* synthetic */ String b;
    private final /* synthetic */ JSONObject c;
    private final /* synthetic */ String d;
    private final /* synthetic */ String e;

    l(String string2, String string3, JSONObject jSONObject, String string4, String string5) {
        this.a = string2;
        this.b = string3;
        this.c = jSONObject;
        this.d = string4;
        this.e = string5;
    }

    @Override
    public final void run() {
        String string2 = MatchCacheManager.getMD5(this.a, this.b);
        if (StringUtils.isNull(string2)) {
            return;
        }
        MatchCacheManager.removeUselessKey(this.c);
        MatchCacheManager.insertOrUpdate(BaseManager.getContentValues(null, "msg_num_md5", string2, "phonenum", StringUtils.getPhoneNumberNo86(this.a), "scene_id", this.d, "msg_id", this.e, "bubble_result", this.c.toString(), "save_time", String.valueOf((long)System.currentTimeMillis()), "bubble_lasttime", String.valueOf((long)System.currentTimeMillis())), 2);
    }
}

