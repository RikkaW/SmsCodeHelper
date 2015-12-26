/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnScrollChangedListener
 *  java.lang.Object
 */
import android.view.ViewTreeObserver;
import com.meizu.commonwidget.RecipientEdit;

public class aij
implements ViewTreeObserver.OnScrollChangedListener {
    final /* synthetic */ RecipientEdit a;

    public aij(RecipientEdit recipientEdit) {
        this.a = recipientEdit;
    }

    public void onScrollChanged() {
        if (RecipientEdit.a(this.a).isPopupShowing()) {
            RecipientEdit.a(this.a).a();
            RecipientEdit.a(this.a).showDropDown();
        }
    }
}

