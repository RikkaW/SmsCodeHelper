/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnDismissListener
 *  java.lang.Object
 */
import android.content.DialogInterface;

class akx
implements DialogInterface.OnDismissListener {
    final /* synthetic */ aks a;

    akx(aks aks2) {
        this.a = aks2;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        aks.a(this.a);
    }
}

