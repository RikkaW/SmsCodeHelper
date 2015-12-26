/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.net.Uri
 *  java.lang.Object
 */
import android.net.Uri;
import com.android.mms.view.MessageListItemTalk;

public class adx
implements ach.a {
    final /* synthetic */ MessageListItemTalk a;

    public adx(MessageListItemTalk messageListItemTalk) {
        this.a = messageListItemTalk;
    }

    @Override
    public void a(Uri uri) {
        if (this.a.M != null && uri != null && uri.equals((Object)this.a.M.t)) {
            MessageListItemTalk.a(this.a, true);
        }
    }

    @Override
    public void b(Uri uri) {
        if (this.a.M != null && uri != null && uri.equals((Object)this.a.M.t)) {
            MessageListItemTalk.a(this.a, false);
        }
    }

    @Override
    public void c(Uri uri) {
        if (this.a.M != null && uri != null && uri.equals((Object)this.a.M.t)) {
            MessageListItemTalk.a(this.a, false);
        }
    }
}

