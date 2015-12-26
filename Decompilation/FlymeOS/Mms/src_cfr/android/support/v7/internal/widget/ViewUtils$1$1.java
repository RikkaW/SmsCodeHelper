/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  java.lang.Object
 */
package android.support.v7.internal.widget;

import android.support.v7.internal.widget.ViewUtils;
import android.support.v7.internal.widget.ViewUtils$1;
import android.view.View;

class ViewUtils$1$1
implements Runnable {
    final /* synthetic */ ViewUtils$1 this$0;

    ViewUtils$1$1(ViewUtils$1 var1_1) {
        this.this$0 = var1_1;
    }

    @Override
    public void run() {
        ViewUtils.makeOptionalFitsSystemWindows(this.this$0.val$view);
    }
}

