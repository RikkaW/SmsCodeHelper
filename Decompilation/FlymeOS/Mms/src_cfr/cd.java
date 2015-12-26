/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.net.NetUtil;

final class cd
implements Runnable {
    private final /* synthetic */ boolean a;
    private final /* synthetic */ String b;
    private final /* synthetic */ String c;
    private final /* synthetic */ String d;
    private final /* synthetic */ String e;
    private final /* synthetic */ int f;
    private final /* synthetic */ XyCallBack g;

    cd(boolean bl2, String string2, String string3, String string4, String string5, int n2, XyCallBack xyCallBack) {
        this.a = bl2;
        this.b = string2;
        this.c = string3;
        this.d = string4;
        this.e = string5;
        this.f = n2;
        this.g = xyCallBack;
    }

    @Override
    public final void run() {
        try {
            if (NetUtil.isEnhance() && this.a || NetUtil.checkAccessNetWork(1)) {
                bs.a(false, this.b, this.c, this.d, this.e, String.valueOf((int)this.f), this.g, 0, false, false);
            }
            return;
        }
        catch (Throwable var1_1) {
            var1_1.printStackTrace();
            return;
        }
    }
}

