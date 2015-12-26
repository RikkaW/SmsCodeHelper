/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.KeyEvent
 *  android.view.View
 *  java.lang.Object
 */
import android.view.KeyEvent;
import android.view.View;
import com.meizu.commonwidget.RecipientEdit;

public class aik
implements RecipientEdit.RecipientAutoCompleteTextView.a {
    final /* synthetic */ RecipientEdit a;

    public aik(RecipientEdit recipientEdit) {
        this.a = recipientEdit;
    }

    @Override
    public boolean a(View view, int n2, KeyEvent keyEvent) {
        if (RecipientEdit.b(this.a) != null) {
            return RecipientEdit.b(this.a).a((View)this.a, n2, keyEvent);
        }
        return false;
    }
}

