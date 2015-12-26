/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  org.json.JSONObject
 */
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.db.entity.a.e;
import cn.com.xy.sms.sdk.util.JsonUtil;
import org.json.JSONObject;

final class bt
implements XyCallBack {
    private final /* synthetic */ boolean a;
    private final /* synthetic */ String b;
    private final /* synthetic */ String c;
    private final /* synthetic */ String d;
    private final /* synthetic */ String e;
    private final /* synthetic */ XyCallBack f;
    private final /* synthetic */ int g;
    private final /* synthetic */ String h;

    bt(boolean bl2, String string2, String string3, String string4, String string5, XyCallBack xyCallBack, int n2, String string6) {
        this.a = bl2;
        this.b = string2;
        this.c = string3;
        this.d = string4;
        this.e = string5;
        this.f = xyCallBack;
        this.g = n2;
        this.h = string6;
    }

    @Override
    public final /* varargs */ void execute(Object ... object) {
        boolean bl2 = this.a;
        String string2 = this.b;
        string2 = this.c;
        string2 = this.d;
        if (bs.a(bl2, this.e, this.f, this.g, (Object[])object) != null && this.g == 0) {
            object = e.a(this.b, this.d, Integer.valueOf((String)this.h));
            if (this.f != null) {
                object = JsonUtil.PubInfoToJson((JSONObject)object);
                this.f.execute(object);
            }
        }
    }
}

