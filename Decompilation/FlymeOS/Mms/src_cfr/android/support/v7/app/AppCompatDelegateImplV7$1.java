/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package android.support.v7.app;

import android.support.v7.app.AppCompatDelegateImplV7;

class AppCompatDelegateImplV7$1
implements Runnable {
    final /* synthetic */ AppCompatDelegateImplV7 this$0;

    AppCompatDelegateImplV7$1(AppCompatDelegateImplV7 appCompatDelegateImplV7) {
        this.this$0 = appCompatDelegateImplV7;
    }

    @Override
    public void run() {
        if ((AppCompatDelegateImplV7.access$000(this.this$0) & 1) != 0) {
            AppCompatDelegateImplV7.access$100(this.this$0, 0);
        }
        if ((AppCompatDelegateImplV7.access$000(this.this$0) & 256) != 0) {
            AppCompatDelegateImplV7.access$100(this.this$0, 8);
        }
        AppCompatDelegateImplV7.access$202(this.this$0, false);
        AppCompatDelegateImplV7.access$002(this.this$0, 0);
    }
}

