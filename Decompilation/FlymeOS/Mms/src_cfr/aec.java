/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.Editable
 *  android.text.TextWatcher
 *  java.lang.Object
 */
import android.text.Editable;
import android.text.TextWatcher;
import com.android.mms.view.MessagePopupReplyView;

public class aec
implements TextWatcher {
    final /* synthetic */ MessagePopupReplyView a;

    public aec(MessagePopupReplyView messagePopupReplyView) {
        this.a = messagePopupReplyView;
    }

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int n2, int n3, int n4) {
    }

    public void onTextChanged(CharSequence charSequence, int n2, int n3, int n4) {
        MessagePopupReplyView.a(this.a, charSequence);
        if (MessagePopupReplyView.a(this.a) != null) {
            MessagePopupReplyView.a(this.a).b(true);
        }
    }
}

