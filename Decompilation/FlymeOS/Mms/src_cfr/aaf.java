/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.ContentValues
 *  android.content.Context
 *  android.net.Uri
 *  android.provider.Telephony
 *  android.provider.Telephony$Sms
 *  android.provider.Telephony$Sms$Inbox
 *  android.util.Log
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.provider.Telephony;
import android.util.Log;

final class aaf
implements Runnable {
    final /* synthetic */ Context a;

    aaf(Context context) {
        this.a = context;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public void run() {
        if (!aad.b(this.a)) return;
        String string2 = this.a.getString(2131493938);
        ContentValues contentValues = new ContentValues(5);
        contentValues.put("address", "4007883333");
        contentValues.put("body", string2);
        contentValues.put("date", new Long(System.currentTimeMillis()));
        contentValues.put("read", Integer.valueOf((int)0));
        contentValues.put("seen", Integer.valueOf((int)0));
        contentValues.put("uuid", "43f66f568c0a44f678meizuassistant");
        string2 = this.a.getContentResolver().insert(Telephony.Sms.Inbox.CONTENT_URI, contentValues);
        if (string2 != null) {
            Log.d((String)"MzAssistantHelper", (String)("asyncCreateMZAssistant --> insertedUri = " + string2));
            ga.b(this.a, false);
            return;
        }
        Log.d((String)"MzAssistantHelper", (String)"asyncCreateMZAssistant --> insertedUri = null");
    }
}

