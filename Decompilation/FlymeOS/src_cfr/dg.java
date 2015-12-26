/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.view.View
 *  android.view.ViewGroup
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 */
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import cn.com.xy.sms.sdk.ui.popu.util.ViewManger;
import java.util.HashMap;

public abstract class dg {
    public View e;
    public Activity f;
    public BusinessSmsMessage g;
    public XyCallBack h;
    public HashMap<String, Object> i = null;

    public dg(Activity activity, BusinessSmsMessage businessSmsMessage, XyCallBack xyCallBack, int n2, ViewGroup viewGroup, int n3) {
        this.a(activity, businessSmsMessage, xyCallBack, n2, viewGroup);
    }

    public Object a(String string2) {
        if (this.i != null) {
            return this.i.get((Object)string2);
        }
        return null;
    }

    public void a() {
    }

    void a(Activity activity, BusinessSmsMessage businessSmsMessage, XyCallBack xyCallBack, int n2, ViewGroup viewGroup) {
        this.f = activity;
        this.g = businessSmsMessage;
        this.h = xyCallBack;
        this.e = ViewManger.createContextByLayoutId((Context)this.f, n2, null);
    }

    public void a(BusinessSmsMessage businessSmsMessage, boolean bl2) {
    }

    public void a(String string2, Object object) {
        if (string2 != null && object != null) {
            if (this.i == null) {
                this.i = new HashMap();
            }
            this.i.put((Object)string2, object);
        }
    }

    public void d() {
        this.a();
        this.e();
        if (this.g.messageBody != null) {
            this.a(this.g, false);
        }
    }

    public void e() {
    }
}

