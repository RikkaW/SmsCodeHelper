/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package cn.com.xy.sms.util;

import cn.com.xy.sms.sdk.db.entity.a.e;
import cn.com.xy.sms.util.SdkCallBack;

final class o
implements SdkCallBack {
    private final /* synthetic */ SdkCallBack a;
    private final /* synthetic */ String b;

    o(SdkCallBack sdkCallBack, String string2) {
        this.a = sdkCallBack;
        this.b = string2;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    @Override
    public final /* varargs */ void execute(Object ... var1_1) {
        if (var1_1 == null) ** GOTO lbl12
        try {}
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            this.a.execute(new Object[]{0, "error:" + var1_2.getMessage()});
            return;
        }
        if (var1_1.length == 0 || var1_1[0] == null) ** GOTO lbl12
        if (var1_1[0].toString().contains((CharSequence)"action_data")) {
            this.a.execute(new Object[]{1, this.b, e.d((String)var1_1[0])});
            return;
        }
lbl12: // 4 sources:
        this.a.execute(new Object[]{0, "menu json data error"});
    }
}

