/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  java.lang.Object
 */
import android.content.DialogInterface;
import com.android.mms.view.MessageListItem;
import com.android.mms.view.MessageListItemBase;

public class adh
implements DialogInterface.OnClickListener {
    final /* synthetic */ MessageListItem a;

    public adh(MessageListItem messageListItem) {
        this.a = messageListItem;
    }

    public void onClick(DialogInterface dialogInterface, int n2) {
        MessageListItemBase.ab = true;
        MessageListItemBase.ad = this.a.M.a();
        MessageListItem.a(this.a);
    }
}

