/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.DialogInterface;
import com.android.mms.view.MzContactHeaderWidget;

public class aeu
implements DialogInterface.OnClickListener {
    final /* synthetic */ MzContactHeaderWidget a;

    public aeu(MzContactHeaderWidget mzContactHeaderWidget) {
        this.a = mzContactHeaderWidget;
    }

    public void onClick(DialogInterface dialogInterface, int n2) {
        MzContactHeaderWidget.a(this.a, ((gm)this.a.b.get(n2)).d());
    }
}

