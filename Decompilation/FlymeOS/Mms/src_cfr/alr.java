/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnCancelListener
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.Context;
import android.content.DialogInterface;
import com.meizu.update.UpdateInfo;

class alr
implements DialogInterface.OnCancelListener {
    final /* synthetic */ alo a;

    alr(alo alo2) {
        this.a = alo2;
    }

    public void onCancel(DialogInterface dialogInterface) {
        alo.a(this.a, true);
        and.a(this.a.a).a(and.a.g, this.a.b.mVersionName);
        alo.a(this.a);
    }
}

