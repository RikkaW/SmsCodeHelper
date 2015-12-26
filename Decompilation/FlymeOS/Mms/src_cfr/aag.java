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

class aag
implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ Context b;
    final /* synthetic */ aad c;

    aag(aad aad2, String string2, Context context) {
        this.c = aad2;
        this.a = string2;
        this.b = context;
    }

    @Override
    public void run() {
        ContentValues contentValues = new ContentValues(6);
        contentValues.put("address", "4007883333");
        contentValues.put("body", this.a);
        contentValues.put("date", new Long(System.currentTimeMillis()));
        contentValues.put("read", Integer.valueOf((int)0));
        contentValues.put("seen", Integer.valueOf((int)0));
        contentValues.put("type", Integer.valueOf((int)1));
        contentValues = this.b.getContentResolver().insert(Telephony.Sms.Inbox.CONTENT_URI, contentValues);
        if (contentValues != null) {
            Log.d((String)"MzAssistantHelper", (String)("asyncInsertAssistantRecvMsg --> insertedUri = " + (Object)contentValues));
            return;
        }
        Log.d((String)"MzAssistantHelper", (String)"asyncInsertAssistantRecvMsg --> insertedUri = null");
    }
}

