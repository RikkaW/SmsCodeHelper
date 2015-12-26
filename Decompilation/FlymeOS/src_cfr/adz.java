/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Handler
 *  android.os.Message
 */
import android.os.Handler;
import android.os.Message;
import com.android.mms.view.MessageListItemTalk;

public class adz
extends Handler {
    final /* synthetic */ MessageListItemTalk a;

    public adz(MessageListItemTalk messageListItemTalk) {
        this.a = messageListItemTalk;
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            default: {
                return;
            }
            case 0: {
                this.a.setTalkBackground(true);
                return;
            }
            case 1: 
        }
        this.a.setTalkBackground(false);
    }
}

