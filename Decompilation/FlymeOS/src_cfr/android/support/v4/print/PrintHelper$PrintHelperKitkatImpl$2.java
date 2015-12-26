/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package android.support.v4.print;

import android.support.v4.print.PrintHelper;
import android.support.v4.print.PrintHelperKitkat;

class PrintHelper$PrintHelperKitkatImpl$2
implements PrintHelperKitkat.OnPrintFinishCallback {
    final /* synthetic */ PrintHelper.PrintHelperKitkatImpl this$0;
    final /* synthetic */ PrintHelper.OnPrintFinishCallback val$callback;

    PrintHelper$PrintHelperKitkatImpl$2(PrintHelper.PrintHelperKitkatImpl printHelperKitkatImpl, PrintHelper.OnPrintFinishCallback onPrintFinishCallback) {
        this.this$0 = printHelperKitkatImpl;
        this.val$callback = onPrintFinishCallback;
    }

    @Override
    public void onFinish() {
        this.val$callback.onFinish();
    }
}

