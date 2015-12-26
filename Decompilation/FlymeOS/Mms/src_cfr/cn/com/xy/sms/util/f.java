/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.BroadcastReceiver
 *  android.content.Context
 *  android.content.Intent
 *  java.lang.Object
 *  java.lang.String
 */
package cn.com.xy.sms.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import cn.com.xy.sms.sdk.db.entity.IccidInfo;
import cn.com.xy.sms.sdk.db.entity.IccidInfoManager;
import cn.com.xy.sms.sdk.iccid.IccidLocationUtil;
import cn.com.xy.sms.sdk.util.StringUtils;

final class f
extends BroadcastReceiver {
    f() {
    }

    /*
     * Enabled aggressive block sorting
     */
    public final void onReceive(Context object, Intent object2) {
        if (!"cn.com.xy.sms.iccidinfo.action".equals((Object)object2.getAction()) || StringUtils.isNull((String)(object = object2.getStringExtra("iccid"))) || (object2 = IccidInfoManager.queryIccidInfo((String)object, null)) == null) {
            return;
        }
        IccidLocationUtil.putIccidAreaCodeToCache((String)object, object2.areaCode, object2.operator, object2.userAreacode, object2.userOperator);
    }
}

