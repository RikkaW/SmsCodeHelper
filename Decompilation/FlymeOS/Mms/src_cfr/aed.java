/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.View$OnClickListener
 *  java.lang.Object
 */
import android.view.View;
import com.android.mms.view.MessagePopupReplyView;

public class aed
implements View.OnClickListener {
    final /* synthetic */ MessagePopupReplyView a;

    public aed(MessagePopupReplyView messagePopupReplyView) {
        this.a = messagePopupReplyView;
    }

    public void onClick(View view) {
        MessagePopupReplyView.a(this.a).f();
    }
}

