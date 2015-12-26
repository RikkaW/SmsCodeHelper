/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
import cn.com.xy.sms.sdk.net.NetUtil;

final class by
implements Runnable {
    private final /* synthetic */ String a;
    private final /* synthetic */ String b;

    by(String string2, String string3) {
        this.a = string2;
        this.b = string3;
    }

    @Override
    public final void run() {
        if (NetUtil.checkAccessNetWork(1)) {
            bx.a(this.a, this.b);
        }
    }
}

