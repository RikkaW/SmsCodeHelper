/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
import com.android.mms.view.MessageListItemSms;

public class adu
implements Runnable {
    final /* synthetic */ MessageListItemSms a;

    public adu(MessageListItemSms messageListItemSms) {
        this.a = messageListItemSms;
    }

    @Override
    public void run() {
        this.a.z();
    }
}

