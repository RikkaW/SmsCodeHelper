/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 */
package cn.com.xy.sms.sdk.net;

import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.net.util.i;
import java.util.HashMap;

final class f
implements XyCallBack {
    f() {
    }

    @Override
    public final /* varargs */ void execute(Object ... object) {
        String string2;
        if (object != null && (string2 = object[0].toString()).equals((Object)"0") && object.length == 2) {
            object = object[1].toString();
            new StringBuilder("resutCode=").append(string2).append("response =").append((String)object);
            object = i.d((String)object);
            if (object != null) {
                SysParamEntityManager.setParam("HTTPTOKEN", (String)object);
                SysParamEntityManager.cacheMap.put((Object)"HTTPTOKEN", object);
            }
        }
    }
}

