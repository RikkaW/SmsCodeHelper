/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.View$OnClickListener
 *  java.lang.Object
 */
import android.view.View;
import com.android.mms.ui.ComposeMessageActivity;
import com.android.mms.view.MmsRichContainer;

public class aep
implements View.OnClickListener {
    final /* synthetic */ MmsRichContainer a;

    public aep(MmsRichContainer mmsRichContainer) {
        this.a = mmsRichContainer;
    }

    public void onClick(View view) {
        ((ComposeMessageActivity)MmsRichContainer.a(this.a)).f(true);
    }
}

