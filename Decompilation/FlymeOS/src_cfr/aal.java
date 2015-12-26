/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.Context;
import android.content.Intent;
import com.meizu.common.app.SlideNotice;

class aal
implements SlideNotice.OnClickNoticeListener {
    final /* synthetic */ aak a;

    aal(aak aak2) {
        this.a = aak2;
    }

    @Override
    public void onClick(SlideNotice slideNotice) {
        try {
            slideNotice = new Intent(this.a.a.a);
            slideNotice.putExtra("android.intent.extra.TITLE", this.a.a.c);
            slideNotice.putExtra("from_notify", true);
            this.a.a.b.startActivity((Intent)slideNotice);
            return;
        }
        catch (Exception var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }
}

