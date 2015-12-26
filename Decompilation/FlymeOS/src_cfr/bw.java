/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.Thread
 */
import cn.com.xy.sms.sdk.db.entity.s;

final class bw
implements Runnable {
    bw() {
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public final void run() {
        try {
            Thread.sleep((long)5000);
        }
        catch (Exception var1_1) {
            var1_1.printStackTrace();
        }
        s.b("2");
        Object object = bx.a;
        synchronized (object) {
            bs.a(false);
            return;
        }
    }
}

