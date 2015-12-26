/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  org.json.JSONObject
 */
package cn.com.xy.sms.util;

import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import cn.com.xy.sms.util.SdkCallBack;
import org.json.JSONObject;

final class t
implements SdkCallBack {
    private final /* synthetic */ int a;
    private final /* synthetic */ SdkCallBack b;

    t(int n2, SdkCallBack sdkCallBack) {
        this.a = n2;
        this.b = sdkCallBack;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public final /* varargs */ void execute(Object ... object) {
        if (object == null) return;
        {
            try {
                object = (String)object[0];
                if (!StringUtils.isNull((String)object)) {
                    object = new JSONObject((String)object);
                    if (this.a == 1) {
                        XyUtil.doXycallBack(this.b, object.optString("logo"));
                        return;
                    }
                    XyUtil.doXycallBack(this.b, object.optString("logoc"));
                    return;
                }
                XyUtil.doXycallBack(this.b, "");
                return;
            }
            catch (Throwable var1_2) {
                XyUtil.doXycallBack(this.b, "");
                return;
            }
        }
    }
}

