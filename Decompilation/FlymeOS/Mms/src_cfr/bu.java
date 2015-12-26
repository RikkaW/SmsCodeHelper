/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
import cn.com.xy.sms.sdk.Iservice.XyCallBack;

final class bu
implements XyCallBack {
    private final /* synthetic */ String a;
    private final /* synthetic */ String b;
    private final /* synthetic */ XyCallBack c;

    bu(String string2, String string3, XyCallBack xyCallBack) {
        this.a = string2;
        this.b = string3;
        this.c = xyCallBack;
    }

    @Override
    public final /* varargs */ void execute(Object ... arrobject) {
        String string2 = this.a;
        bs.a(false, this.b, this.c, 2, arrobject);
    }
}

