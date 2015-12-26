/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.ContentUris
 *  android.content.ContentValues
 *  android.content.Context
 *  android.database.sqlite.SqliteWrapper
 *  android.net.Uri
 *  android.text.TextUtils
 *  android.util.Log
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  java.util.HashMap
 */
package com.android.mms.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.LogTag;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RecipientIdCache {
    private static Uri sAllCanonical = Uri.parse((String)"content://mms-sms/canonical-addresses");
    private static RecipientIdCache sInstance;
    private static Uri sSingleCanonicalAddressUri;
    private final Map<Long, String> mCache = new HashMap();
    private final Context mContext;

    static {
        sSingleCanonicalAddressUri = Uri.parse((String)"content://mms-sms/canonical-address");
    }

    RecipientIdCache(Context context) {
        this.mContext = context;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public static void fill() {
        LogTag.debug("[RecipientIdCache] fill: begin", new Object[0]);
        Context context = RecipientIdCache.sInstance.mContext;
        context = SqliteWrapper.query((Context)context, (ContentResolver)context.getContentResolver(), (Uri)sAllCanonical, (String[])null, (String)null, (String[])null, (String)null);
        if (context == null) {
            Log.w((String)"Mms/cache", (String)"null Cursor in fill()");
            return;
        }
        try {
            RecipientIdCache recipientIdCache = sInstance;
            // MONITORENTER : recipientIdCache
        }
        catch (Throwable var3_2) {
            context.close();
            throw var3_2;
        }
        do {
            if (!context.moveToNext()) {
                // MONITOREXIT : recipientIdCache
                context.close();
                return;
            }
            long l = context.getLong(0);
            String string = context.getString(1);
            RecipientIdCache.sInstance.mCache.put(l, string);
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static List<Entry> getAddresses(String string) {
        RecipientIdCache recipientIdCache = sInstance;
        synchronized (recipientIdCache) {
            ArrayList arrayList = new ArrayList();
            String[] arrstring = string.split(" ");
            int n = arrstring.length;
            int n2 = 0;
            while (n2 < n) {
                long l;
                String string2;
                string = arrstring[n2];
                try {
                    l = Long.parseLong((String)string);
                }
                catch (NumberFormatException var0_1) {}
                string = string2 = RecipientIdCache.sInstance.mCache.get(l);
                if (string2 == null) {
                    RecipientIdCache.fill();
                    string = RecipientIdCache.sInstance.mCache.get(l);
                }
                if (TextUtils.isEmpty((CharSequence)string)) {
                    Log.w((String)"Mms/cache", (String)("RecipientId " + l + " has empty number!"));
                } else {
                    arrayList.add(new Entry(l, string));
                }
                ++n2;
            }
            return arrayList;
        }
    }

    public static String getSingleAddressFromCanonicalAddressInDb(Context context, String string) {
        if ((context = SqliteWrapper.query((Context)context, (ContentResolver)context.getContentResolver(), (Uri)ContentUris.withAppendedId((Uri)sSingleCanonicalAddressUri, (long)Long.parseLong((String)string)), (String[])null, (String)null, (String[])null, (String)null)) == null) {
            return null;
        }
        try {
            if (context.moveToFirst()) {
                string = context.getString(0);
                return string;
            }
            return null;
        }
        finally {
            context.close();
        }
    }

    static void init(Context context) {
        context = context.getApplicationContext();
        if (sInstance == null) {
            sInstance = new RecipientIdCache(context);
        }
    }

    private void updateCanonicalAddressInDb(long l, String charSequence) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("address", charSequence);
        charSequence = new StringBuilder("_id");
        charSequence.append('=').append(l);
        Uri uri = ContentUris.withAppendedId((Uri)sSingleCanonicalAddressUri, (long)l);
        new Thread("updateCanonicalAddressInDb", this.mContext.getContentResolver(), uri, contentValues, (StringBuilder)charSequence){
            final /* synthetic */ StringBuilder val$buf;
            final /* synthetic */ ContentResolver val$cr;
            final /* synthetic */ Uri val$uri;
            final /* synthetic */ ContentValues val$values;

            public void run() {
                this.val$cr.update(this.val$uri, this.val$values, this.val$buf.toString(), null);
            }
        }.start();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void updateNumbers(long l, ContactList object) {
        object = object.iterator();
        while (object.hasNext()) {
            Object object2 = (Contact)object.next();
            if (!object2.isNumberModified()) continue;
            object2.setIsNumberModified(false);
            l = object2.getRecipientId();
            if (l == 0) continue;
            String string = object2.getNumber();
            boolean bl = false;
            object2 = sInstance;
            synchronized (object2) {
                if (!string.equalsIgnoreCase(RecipientIdCache.sInstance.mCache.get(l))) {
                    RecipientIdCache.sInstance.mCache.put(l, string);
                    bl = true;
                }
                if (!bl) continue;
            }
            sInstance.updateCanonicalAddressInDb(l, string);
        }
        return;
    }

    public static class Entry {
        public long id;
        public String number;

        public Entry(long l, String string) {
            this.id = l;
            this.number = string;
        }
    }

}

