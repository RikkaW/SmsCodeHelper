/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.net.Uri
 *  android.telephony.SmsMessage
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 */
import android.net.Uri;
import android.telephony.SmsMessage;
import cn.com.xy.sms.sdk.ui.popu.util.XySdkUtil;

final class aax
implements Runnable {
    final /* synthetic */ Uri a;
    final /* synthetic */ SmsMessage[] b;
    final /* synthetic */ int c;

    aax(Uri uri, SmsMessage[] arrsmsMessage, int n2) {
        this.a = uri;
        this.b = arrsmsMessage;
        this.c = n2;
    }

    @Override
    public void run() {
        long l2 = Long.parseLong((String)this.a.getLastPathSegment());
        XySdkUtil.parseMsg("" + l2 + "", this.b, 0, this.c);
    }
}

