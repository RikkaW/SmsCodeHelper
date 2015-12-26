/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  com.meizu.android.mms.pdu.MzGenericPdu
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.HashMap
 */
import android.util.Log;
import com.meizu.android.mms.pdu.MzGenericPdu;
import java.util.HashMap;
import java.util.Set;

class aap
implements Runnable {
    final /* synthetic */ MzGenericPdu a;
    final /* synthetic */ lr b;
    final /* synthetic */ Throwable c;
    final /* synthetic */ aan.b d;

    aap(aan.b b2, MzGenericPdu mzGenericPdu, lr lr2, Throwable throwable) {
        this.d = b2;
        this.a = mzGenericPdu;
        this.b = lr2;
        this.c = throwable;
    }

    @Override
    public void run() {
        Set set = (Set)this.d.aan.this.b.get((Object)aan.b.a(this.d));
        if (set != null) {
            for (zy zy2 : zh.a(set)) {
                if (Log.isLoggable((String)"Mms:PduLoaderManager", (int)3)) {
                    Log.d((String)"Mms:PduLoaderManager", (String)("Invoking pdu callback " + zy2));
                }
                zy2.a(new aan.a(this.a, this.b), this.c);
            }
        }
        if (this.b != null) {
            aan.c(this.d.aan.this).a(aan.b.a(this.d), this.b);
        }
        this.d.aan.this.b.remove((Object)aan.b.a(this.d));
        this.d.aan.this.a.remove((Object)aan.b.a(this.d));
        if (Log.isLoggable((String)"Mms:pducache", (int)3)) {
            Log.d((String)"Mms:PduLoaderManager", (String)("Pdu task for " + (Object)aan.b.a(this.d) + "exiting; " + this.d.aan.this.a.size() + " remain"));
        }
    }
}

