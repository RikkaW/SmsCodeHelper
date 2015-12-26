/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.BroadcastReceiver
 *  android.content.ContentResolver
 *  android.content.ContentValues
 *  android.content.Context
 *  android.net.Uri
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  android.provider.Telephony$Mms$Rate
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.provider.Telephony;

public class aar {
    private static aar a;
    private final Context b;
    private int c;
    private final BroadcastReceiver d;

    private aar(Context context) {
        this.d = new aas(this);
        this.b = context;
    }

    static /* synthetic */ int a(aar aar2, int n2) {
        aar2.c = n2;
        return n2;
    }

    public static aar a(Context context) {
        if (a == null) {
            a = new aar(context);
        }
        return a;
    }

    public final void a() {
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("sent_time", Long.valueOf((long)System.currentTimeMillis()));
        this.b.getContentResolver().insert(Telephony.Mms.Rate.CONTENT_URI, contentValues);
    }
}

