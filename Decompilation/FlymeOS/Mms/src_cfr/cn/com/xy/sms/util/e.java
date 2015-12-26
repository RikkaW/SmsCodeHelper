/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package cn.com.xy.sms.util;

import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.util.XyUtil;
import cn.com.xy.sms.util.SdkCallBack;

final class e
implements XyCallBack {
    private final /* synthetic */ SdkCallBack a;

    e(SdkCallBack sdkCallBack) {
        this.a = sdkCallBack;
    }

    @Override
    public final /* varargs */ void execute(Object ... arrobject) {
        if (arrobject == null || arrobject.length < 2) {
            XyUtil.doXycallBackResult(this.a, new Object[0]);
            return;
        }
        XyUtil.doXycallBackResult(this.a, arrobject);
    }
}

