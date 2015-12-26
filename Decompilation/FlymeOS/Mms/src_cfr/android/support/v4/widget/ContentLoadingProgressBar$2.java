/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.System
 */
package android.support.v4.widget;

import android.support.v4.widget.ContentLoadingProgressBar;

class ContentLoadingProgressBar$2
implements Runnable {
    final /* synthetic */ ContentLoadingProgressBar this$0;

    ContentLoadingProgressBar$2(ContentLoadingProgressBar contentLoadingProgressBar) {
        this.this$0 = contentLoadingProgressBar;
    }

    @Override
    public void run() {
        ContentLoadingProgressBar.access$202(this.this$0, false);
        if (!ContentLoadingProgressBar.access$300(this.this$0)) {
            ContentLoadingProgressBar.access$102(this.this$0, System.currentTimeMillis());
            this.this$0.setVisibility(0);
        }
    }
}

