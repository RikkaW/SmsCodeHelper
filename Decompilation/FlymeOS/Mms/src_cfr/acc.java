/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import com.android.mms.ui.ComposeMessageActivity;

class acc
implements DialogInterface.OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ abu c;

    acc(abu abu2, String string2, String string3) {
        this.c = abu2;
        this.a = string2;
        this.b = string3;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void onClick(DialogInterface object, int n2) {
        int n3;
        if (this.a == abu.a((abu)this.c).m) {
            ((ComposeMessageActivity)abu.c(this.c)).d(this.b);
            return;
        }
        object = TextUtils.split((String)this.a, (String)";");
        object = new ot(abu.c(this.c), (String[])object, this.b, 0);
        n2 = n3 = abu.a(this.c).ag();
        if (n3 < 0) {
            n2 = 0;
        }
        object.a(n2);
        try {
            object.a(0);
        }
        catch (Exception var1_2) {
            var1_2.printStackTrace();
        }
        abu.a(this.c, this.a, n2);
    }
}

