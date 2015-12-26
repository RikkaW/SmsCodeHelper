/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Thread
 */
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;

final class ca
extends Thread {
    private final /* synthetic */ String a;
    private final /* synthetic */ String b;

    ca(String string2, String string3, String string4) {
        this.a = string3;
        this.b = string4;
        super(string2);
    }

    public final void run() {
        bx.b(this.a, this.b, false);
        bx.a(this.a, this.b, false);
        Object object = bx.a;
        synchronized (object) {
            SysParamEntityManager.setParam("LastPublicUpdate", String.valueOf((long)System.currentTimeMillis()));
            bx.b = false;
            return;
        }
    }
}

