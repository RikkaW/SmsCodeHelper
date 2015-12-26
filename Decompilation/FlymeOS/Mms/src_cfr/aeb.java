/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Handler
 *  android.widget.TextView
 *  java.lang.Object
 *  java.lang.String
 */
import android.os.Handler;
import android.widget.TextView;
import com.android.mms.view.MessageListItemTalk;

public class aeb
implements Runnable {
    final /* synthetic */ MessageListItemTalk a;

    public aeb(MessageListItemTalk messageListItemTalk) {
        this.a = messageListItemTalk;
    }

    @Override
    public void run() {
        int n2;
        block4 : {
            n2 = 1;
            try {
                String string2 = MessageListItemTalk.a(this.a, MessageListItemTalk.a(this.a));
                this.a.H.setText((CharSequence)string2);
                if (ach.a().e() % 1000 >= 500) break block4;
                n2 = 0;
            }
            catch (Exception var2_3) {
                var2_3.printStackTrace();
                return;
            }
        }
        if (MessageListItemTalk.a(this.a) < n2 + ach.a().e() / 1000) {
            MessageListItemTalk.b(this.a);
            this.a.b.postDelayed((Runnable)this, 1000);
            return;
        }
        MessageListItemTalk.b(this.a, 1);
    }
}

