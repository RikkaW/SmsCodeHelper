/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Handler
 *  android.os.Message
 */
import android.os.Handler;
import android.os.Message;

class aae
extends Handler {
    final /* synthetic */ aad a;

    aae(aad aad2) {
        this.a = aad2;
    }

    public void handleMessage(Message message) {
        wd.a(aad.a(this.a, message.what), aad.c(this.a), 0, 1, true, 0, aaa.b());
    }
}

