/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  java.lang.String
 */
import android.util.Log;

class abb
extends aio {
    final /* synthetic */ aba a;

    abb(aba aba2) {
        this.a = aba2;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public void a(int n2, String string2) {
        Log.v((String)"SipSmsManager", (String)("onOnlineStatusChanged, status = " + n2 + ", mCurrentSipStatu = " + aba.a(this.a) + ", ext = " + string2));
        if (aba.a(this.a) == n2) return;
        boolean bl2 = true;
        if (!bl2) {
            return;
        }
        aba.a(this.a, n2);
        if (aba.b(this.a) == null) return;
        aba.b(this.a).a(n2);
    }
}

