/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
import com.android.mms.view.ConversationListItem;

public class acw
implements Runnable {
    final /* synthetic */ ConversationListItem a;

    public acw(ConversationListItem conversationListItem) {
        this.a = conversationListItem;
    }

    @Override
    public void run() {
        ConversationListItem.a(this.a, false);
    }
}

