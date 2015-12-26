/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.net.Uri
 *  android.os.Handler
 *  java.lang.Object
 */
import android.net.Uri;
import android.os.Handler;
import com.android.mms.view.MessageListItemTalk;

public class adw
implements ach.a {
    final /* synthetic */ MessageListItemTalk a;

    public adw(MessageListItemTalk messageListItemTalk) {
        this.a = messageListItemTalk;
    }

    @Override
    public void a(Uri uri) {
        if (this.a.M != null && uri != null && uri.equals((Object)this.a.M.t)) {
            MessageListItemTalk.a(true);
            MessageListItemTalk.a(this.a, true);
            if (this.a.b == null) {
                this.a.b = new Handler();
            }
        }
    }

    @Override
    public void b(Uri uri) {
        if (this.a.M != null && uri != null && uri.equals((Object)this.a.M.t)) {
            MessageListItemTalk.a(false);
            MessageListItemTalk.a(this.a, false);
        }
    }

    @Override
    public void c(Uri uri) {
        if (this.a.M != null && uri != null && uri.equals((Object)this.a.M.t)) {
            MessageListItemTalk.a(false);
            MessageListItemTalk.a(this.a, false);
            if (this.a.b != null) {
                this.a.b.removeCallbacks(this.a.c);
                this.a.b = null;
            }
        }
    }
}

