/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.Context;

final class ang
implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;

    ang(Context context, String string2) {
        this.a = context;
        this.b = string2;
    }

    @Override
    public void run() {
        anf.a(this.a, this.b);
    }
}

