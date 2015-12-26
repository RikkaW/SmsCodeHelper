/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.net.Uri
 *  android.widget.ProgressBar
 *  java.lang.Math
 *  java.lang.Object
 */
import android.net.Uri;
import android.widget.ProgressBar;
import com.android.mms.view.MessageListItem;

public class adj
implements vv.b {
    final /* synthetic */ MessageListItem a;

    public adj(MessageListItem messageListItem) {
        this.a = messageListItem;
    }

    @Override
    public void a(vv vv2, Uri uri, long l2, long l3) {
        if (vv2 != null && this.a.M != null && vv2.M() == this.a.M.M()) {
            if (this.a.G != null && this.a.G.getVisibility() != 0) {
                this.a.G.setVisibility(0);
            }
            MessageListItem.a(this.a, Math.max((long)l3, (long)this.a.M.C), l2);
        }
    }
}

