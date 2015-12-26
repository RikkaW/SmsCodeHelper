/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.res.Resources
 *  android.os.Handler
 *  android.os.Message
 *  android.text.TextUtils
 *  android.view.View
 *  android.widget.TextView
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.meizu.commonwidget.RecipientEdit;

public class aim
extends Handler {
    final /* synthetic */ RecipientEdit a;

    public aim(RecipientEdit recipientEdit) {
        this.a = recipientEdit;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void handleMessage(Message object) {
        String string2 = ((RecipientEdit.a)object.obj).a;
        object = ((RecipientEdit.a)object.obj).b;
        RecipientEdit.h(this.a).put((Object)string2, object);
        int n2 = RecipientEdit.i(this.a).indexOf((Object)string2);
        if (n2 > -1) {
            if (TextUtils.isEmpty((CharSequence)object)) {
                object = string2;
            }
            RecipientEdit.ItemView itemView = (RecipientEdit.ItemView)RecipientEdit.j(this.a).getChildAt(n2 + 1);
            itemView.a((String)object + "\uff0c");
            if (!RecipientEdit.a(this.a, string2) && RecipientEdit.k(this.a)) {
                if (RecipientEdit.l(this.a) == 2) {
                    itemView.a().setTextColor(this.a.getResources().getColor(aih.a.mw_recipient_text_invalidate_calendar));
                } else {
                    itemView.a().setTextColor(this.a.getResources().getColor(aih.a.mw_recipient_text_invalidate));
                }
            }
            this.a.b(this.a.hasFocus());
        }
        RecipientEdit.a(this.a, false);
    }
}

