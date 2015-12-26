/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.View$OnClickListener
 *  java.lang.Object
 */
import android.view.View;

class aqz
implements View.OnClickListener {
    final /* synthetic */ aqy a;
    final /* synthetic */ aqy.a b;

    aqz(aqy.a a2, aqy aqy2) {
        this.b = a2;
        this.a = aqy2;
    }

    public void onClick(View view) {
        if (aqy.a(this.b.aqy.this) != null) {
            aqy.a(this.b.aqy.this).onClick(view);
        }
    }
}

