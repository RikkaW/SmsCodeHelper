/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.AsyncTask
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.Context;
import android.os.AsyncTask;

final class aaj
implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ Context b;
    final /* synthetic */ String c;

    aaj(String string2, Context context, String string3) {
        this.a = string2;
        this.b = context;
        this.c = string3;
    }

    @Override
    public void run() {
        new aak(this).execute((Object[])new Void[0]);
    }
}

