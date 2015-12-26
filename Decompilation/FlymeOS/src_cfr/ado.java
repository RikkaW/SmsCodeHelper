/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Object
 */
import android.content.Context;
import com.android.mms.ui.ComposeMessageActivity;
import com.android.mms.view.MessageListItemBase;

public class ado
implements Runnable {
    final /* synthetic */ MessageListItemBase a;

    public ado(MessageListItemBase messageListItemBase) {
        this.a = messageListItemBase;
    }

    @Override
    public void run() {
        ((ComposeMessageActivity)this.a.U).a(this.a);
    }
}

