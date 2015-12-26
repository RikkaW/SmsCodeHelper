/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package android.support.v4.widget;

import android.support.v4.widget.ContentLoadingProgressBar;

class ContentLoadingProgressBar$1
implements Runnable {
    final /* synthetic */ ContentLoadingProgressBar this$0;

    ContentLoadingProgressBar$1(ContentLoadingProgressBar contentLoadingProgressBar) {
        this.this$0 = contentLoadingProgressBar;
    }

    @Override
    public void run() {
        ContentLoadingProgressBar.access$002(this.this$0, false);
        ContentLoadingProgressBar.access$102(this.this$0, -1);
        this.this$0.setVisibility(8);
    }
}

