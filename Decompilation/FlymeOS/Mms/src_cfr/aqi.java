/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Object
 */
import android.content.Context;
import com.ted.android.contacts.bubble.SmsCoreEngine;
import com.ted.android.core.SmsParserEngine;

public class aqi
implements Runnable {
    final /* synthetic */ SmsParserEngine a;

    public aqi(SmsParserEngine smsParserEngine) {
        this.a = smsParserEngine;
    }

    @Override
    public void run() {
        SmsCoreEngine.init(SmsParserEngine.a(this.a));
        if (SmsParserEngine.b(this.a) != null) {
            SmsParserEngine.b(this.a).onCompleted();
        }
        asf.a().a(SmsParserEngine.a(this.a));
    }
}

