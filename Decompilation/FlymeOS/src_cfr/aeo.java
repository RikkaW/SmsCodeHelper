/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.KeyEvent
 *  android.view.View
 *  android.view.View$OnKeyListener
 *  java.lang.Object
 */
import android.view.KeyEvent;
import android.view.View;
import com.android.mms.view.MmsRichContainer;

public class aeo
implements View.OnKeyListener {
    final /* synthetic */ MmsRichContainer a;

    public aeo(MmsRichContainer mmsRichContainer) {
        this.a = mmsRichContainer;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean onKey(View view, int n2, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0) return false;
        if (n2 != 67) return false;
        if (view == null) {
            return false;
        }
        if (view == MmsRichContainer.c(this.a) && (MmsRichContainer.c(this.a).length() == 0 || MmsRichContainer.c(this.a).getSelectionStart() == 0 && MmsRichContainer.c(this.a).getSelectionEnd() == 0)) {
            if (MmsRichContainer.d(this.a).getVisibility() == 0) {
                this.a.a(0);
                return true;
            }
            if (!this.a.f()) return true;
            this.a.b(false);
            return true;
        }
        if (view != MmsRichContainer.c(this.a)) return false;
        if (MmsRichContainer.c(this.a).length() <= 0) return false;
        return MmsRichContainer.e(this.a);
    }
}

