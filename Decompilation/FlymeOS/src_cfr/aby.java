/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.Context;
import android.content.DialogInterface;
import com.ted.android.core.ReplyMsgItem;
import com.ted.android.data.bubbleAction.ActionBase;
import com.ted.android.data.bubbleAction.PhoneNumberAction;
import com.ted.android.data.bubbleAction.QuickReplyAction;
import java.util.List;

class aby
implements DialogInterface.OnClickListener {
    final /* synthetic */ List a;
    final /* synthetic */ abu b;

    aby(abu abu2, List list) {
        this.b = abu2;
        this.a = list;
    }

    public void onClick(DialogInterface object, int n2) {
        object = (ActionBase)this.a.get(n2);
        if (object instanceof QuickReplyAction) {
            object = ((QuickReplyAction)object).getItem();
            abu.a(this.b, object.message, object.number);
            return;
        }
        if (object instanceof PhoneNumberAction) {
            abu.a(this.b, (PhoneNumberAction)object);
            return;
        }
        object.doAction(abu.c(this.b));
    }
}

