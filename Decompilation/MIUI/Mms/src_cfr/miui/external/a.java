/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  java.lang.Object
 *  java.lang.System
 */
package miui.external;

import android.content.DialogInterface;
import miui.external.SdkErrorActivity;

class a
implements DialogInterface.OnClickListener {
    final /* synthetic */ SdkErrorActivity a;

    a(SdkErrorActivity sdkErrorActivity) {
        this.a = sdkErrorActivity;
    }

    public void onClick(DialogInterface dialogInterface, int n) {
        dialogInterface.dismiss();
        this.a.finish();
        System.exit((int)0);
    }
}

