/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  java.lang.Object
 */
import android.view.View;
import com.android.mms.view.MessageListItem;
import com.android.mms.view.MmsFoldableTextView;

public class adb
implements MmsFoldableTextView.a {
    final /* synthetic */ MessageListItem a;

    public adb(MessageListItem messageListItem) {
        this.a = messageListItem;
    }

    @Override
    public boolean a(MmsFoldableTextView mmsFoldableTextView) {
        if (this.a.M == null || this.a.M.q()) {
            return false;
        }
        MessageListItem.a(this.a, this.a.M, true, (View)mmsFoldableTextView);
        return false;
    }
}

