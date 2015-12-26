/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 */
package cn.com.xy.sms.sdk.db.entity.a;

import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.util.SdkCallBack;
import java.util.HashMap;

final class f
implements SdkCallBack {
    private final /* synthetic */ SdkCallBack a;
    private final /* synthetic */ HashMap b;

    f(SdkCallBack sdkCallBack, HashMap hashMap) {
        this.a = sdkCallBack;
        this.b = hashMap;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public final /* varargs */ void execute(Object ... arrobject) {
        if (this.a != null && arrobject != null && arrobject.length == 2) {
            String string2 = arrobject[1] == null ? null : arrobject[1].toString();
            if (StringUtils.isNull(string2)) return;
            if (!string2.equals(this.b.get((Object)"pubId"))) {
                this.a.execute(new Object[]{arrobject[0]});
            }
        }
    }
}

