/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Handler
 *  android.os.Looper
 *  android.os.Message
 *  java.lang.Object
 */
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.ted.sdk.yellow.YellowPageEngine;

public class asn
extends Handler {
    final /* synthetic */ YellowPageEngine a;

    public asn(YellowPageEngine yellowPageEngine, Looper looper) {
        this.a = yellowPageEngine;
        super(looper);
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            default: {
                return;
            }
            case 20001: 
            case 20002: 
            case 20003: 
        }
        ((YellowPageEngine.b)message.obj).a();
    }
}

