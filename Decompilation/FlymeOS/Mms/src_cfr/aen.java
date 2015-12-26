/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.View$OnClickListener
 *  java.lang.Object
 */
import android.view.View;
import com.android.mms.view.MmsRichContainer;

public class aen
implements View.OnClickListener {
    final /* synthetic */ MmsRichContainer a;

    public aen(MmsRichContainer mmsRichContainer) {
        this.a = mmsRichContainer;
    }

    public void onClick(View view) {
        this.a.a(false, 0);
    }
}

