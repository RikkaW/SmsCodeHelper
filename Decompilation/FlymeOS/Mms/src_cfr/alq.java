/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  java.lang.Object
 */
import android.os.Bundle;

class alq
implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ Bundle b;
    final /* synthetic */ alp c;

    alq(alp alp2, int n2, Bundle bundle) {
        this.c = alp2;
        this.a = n2;
        this.b = bundle;
    }

    @Override
    public void run() {
        alo.a(this.c.a, this.a, this.b);
    }
}

