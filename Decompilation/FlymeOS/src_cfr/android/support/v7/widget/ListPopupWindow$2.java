/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.IBinder
 *  android.view.View
 *  java.lang.Object
 */
package android.support.v7.widget;

import android.os.IBinder;
import android.support.v7.widget.ListPopupWindow;
import android.view.View;

class ListPopupWindow$2
implements Runnable {
    final /* synthetic */ ListPopupWindow this$0;

    ListPopupWindow$2(ListPopupWindow listPopupWindow) {
        this.this$0 = listPopupWindow;
    }

    @Override
    public void run() {
        View view = this.this$0.getAnchorView();
        if (view != null && view.getWindowToken() != null) {
            this.this$0.show();
        }
    }
}

