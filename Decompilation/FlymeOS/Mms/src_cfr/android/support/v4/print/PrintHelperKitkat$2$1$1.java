/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.CancellationSignal
 *  android.os.CancellationSignal$OnCancelListener
 *  java.lang.Object
 */
package android.support.v4.print;

import android.os.CancellationSignal;
import android.support.v4.print.PrintHelperKitkat$2;
import android.support.v4.print.PrintHelperKitkat$2$1;

class PrintHelperKitkat$2$1$1
implements CancellationSignal.OnCancelListener {
    final /* synthetic */ PrintHelperKitkat$2$1 this$2;

    PrintHelperKitkat$2$1$1(PrintHelperKitkat$2$1 var1_1) {
        this.this$2 = var1_1;
    }

    public void onCancel() {
        PrintHelperKitkat$2.access$100(this.this$2.this$1);
        this.this$2.cancel(false);
    }
}

