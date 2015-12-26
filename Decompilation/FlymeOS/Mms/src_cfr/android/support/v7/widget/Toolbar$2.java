/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package android.support.v7.widget;

import android.support.v7.widget.Toolbar;

class Toolbar$2
implements Runnable {
    final /* synthetic */ Toolbar this$0;

    Toolbar$2(Toolbar toolbar) {
        this.this$0 = toolbar;
    }

    @Override
    public void run() {
        this.this$0.showOverflowMenu();
    }
}

