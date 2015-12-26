/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
import com.android.mms.view.MessageListItem;

public class add
implements Runnable {
    final /* synthetic */ long a;
    final /* synthetic */ long b;
    final /* synthetic */ MessageListItem c;

    public add(MessageListItem messageListItem, long l2, long l3) {
        this.c = messageListItem;
        this.a = l2;
        this.b = l3;
    }

    @Override
    public void run() {
        MessageListItem.a(this.c, this.a, this.b, false);
    }
}

