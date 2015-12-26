/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.View$OnClickListener
 *  java.lang.Object
 *  java.lang.String
 */
import android.view.View;
import com.android.mms.view.ConversationListItem;

public class acx
implements View.OnClickListener {
    final /* synthetic */ ConversationListItem.a a;

    public acx(ConversationListItem.a a2) {
        this.a = a2;
    }

    public void onClick(View object) {
        object = ConversationListItem.b(this.a.b).h();
        if (object != null && object.size() > 0) {
            object = (gm)object.get(0);
            wd.a(ConversationListItem.a(this.a.b), object.d(), object.k(), object.n(), false, object.p());
        }
    }
}

