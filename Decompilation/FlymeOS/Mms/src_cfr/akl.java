/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Object
 *  java.lang.Thread
 */
import android.content.Context;

public class akl {
    private akj a;

    public akl(Context context, akn akn2, long l2) {
        this.a = new akj(context, akn2, l2);
    }

    static /* synthetic */ akj a(akl akl2) {
        return akl2.a;
    }

    public void a(boolean bl2) {
        new Thread((Runnable)new akm(this, bl2)).start();
    }
}

