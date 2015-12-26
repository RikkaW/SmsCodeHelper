/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.KeyEvent
 *  android.view.View
 */
import android.view.KeyEvent;
import android.view.View;
import com.android.mms.view.MmsRichContainer;

public class aeq
extends ma {
    final /* synthetic */ MmsRichContainer a;

    public aeq(MmsRichContainer mmsRichContainer) {
        this.a = mmsRichContainer;
    }

    @Override
    protected boolean a(View view, int n2, KeyEvent keyEvent) {
        if (MmsRichContainer.f(this.a) != null) {
            return MmsRichContainer.f(this.a).a(view, n2, keyEvent);
        }
        return false;
    }
}

