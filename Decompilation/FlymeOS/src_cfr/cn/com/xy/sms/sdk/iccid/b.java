/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
package cn.com.xy.sms.sdk.iccid;

import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.entity.IccidInfoManager;
import cn.com.xy.sms.sdk.db.entity.a.a;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.net.util.i;
import cn.com.xy.sms.sdk.util.StringUtils;

final class b
implements XyCallBack {
    private final /* synthetic */ String a;
    private final /* synthetic */ String b;

    b(String string2, String string3) {
        this.a = string2;
        this.b = string3;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public final /* varargs */ void execute(Object ... object) {
        String string2;
        if (object == null || !(string2 = object[0].toString()).equals((Object)"0") || object.length != 2) return;
        {
            object = object[1].toString();
            new StringBuilder("iccid=").append(this.a).append(" response=").append((String)object);
            cn.com.xy.sms.sdk.db.entity.a a2 = i.c((String)object);
            if (a2.a == 0) {
                new StringBuilder("resutCode=").append(string2).append("response =").append((String)object);
                a2.b = StringUtils.getSubString(this.b);
                a2.g = System.currentTimeMillis();
                if (!StringUtils.isNull(this.b)) {
                    a.a(a2);
                    return;
                } else {
                    if (StringUtils.isNull(this.a)) return;
                    {
                        IccidInfoManager.insertIccid(this.a, true, a2.d, a2.c, a2.e, a2.f, Constant.getContext());
                        return;
                    }
                }
            } else {
                if (a2.a != i.b) return;
                {
                    NetUtil.QueryTokenRequest(this.a);
                    return;
                }
            }
        }
    }
}

