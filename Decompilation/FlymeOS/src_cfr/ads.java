/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Long
 *  java.lang.Object
 *  org.json.JSONArray
 */
import com.android.mms.view.MessageListItemSms;
import org.json.JSONArray;

class ads
implements Runnable {
    final /* synthetic */ Object[] a;
    final /* synthetic */ adr b;

    ads(adr adr2, Object[] arrobject) {
        this.b = adr2;
        this.a = arrobject;
    }

    @Override
    public void run() {
        MessageListItemSms.a(this.b.b, new MessageListItemSms.a(this.b.b, this.b.a.e, (JSONArray)this.a[1]));
        if (MessageListItemSms.b(this.b.b) != null && MessageListItemSms.b((MessageListItemSms)this.b.b).g > 0) {
            this.b.b.a(MessageListItemSms.b(this.b.b));
            return;
        }
        this.b.b.z();
    }
}

