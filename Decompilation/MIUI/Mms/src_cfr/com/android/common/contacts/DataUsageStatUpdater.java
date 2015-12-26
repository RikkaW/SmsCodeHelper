/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.ContentValues
 *  android.content.Context
 *  android.database.Cursor
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.provider.ContactsContract
 *  android.provider.ContactsContract$CommonDataKinds
 *  android.provider.ContactsContract$CommonDataKinds$Phone
 *  android.provider.ContactsContract$Contacts
 *  android.provider.ContactsContract$Data
 *  android.text.TextUtils
 *  android.util.Log
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.ArrayList
 *  java.util.Arrays
 *  java.util.HashSet
 */
package com.android.common.contacts;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class DataUsageStatUpdater {
    private static final String TAG = DataUsageStatUpdater.class.getSimpleName();
    private final ContentResolver mResolver;

    public DataUsageStatUpdater(Context context) {
        this.mResolver = context.getContentResolver();
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean update(Collection<Long> uri, Collection<Long> object, String string) {
        long l = System.currentTimeMillis();
        if (Build.VERSION.SDK_INT >= 14) {
            if (object.isEmpty()) {
                if (!Log.isLoggable((String)TAG, (int)3)) return false;
                {
                    Log.d((String)TAG, (String)"Given list for data IDs is null. Ignoring.");
                    return false;
                }
            } else {
                uri = DataUsageFeedback.FEEDBACK_URI.buildUpon().appendPath(TextUtils.join((CharSequence)",", (Iterable)object)).appendQueryParameter("type", string).build();
                if (this.mResolver.update(uri, new ContentValues(), null, null) > 0) {
                    return true;
                }
                if (!Log.isLoggable((String)TAG, (int)3)) return false;
                {
                    Log.d((String)TAG, (String)("update toward data rows " + object + " failed"));
                    return false;
                }
            }
        } else if (uri.isEmpty()) {
            if (!Log.isLoggable((String)TAG, (int)3)) return false;
            {
                Log.d((String)TAG, (String)"Given list for contact IDs is null. Ignoring.");
                return false;
            }
        } else {
            object = new StringBuilder();
            string = new ArrayList();
            ContentValues contentValues = new String[uri.size()];
            Iterator iterator = uri.iterator();
            while (iterator.hasNext()) {
                string.add((Object)String.valueOf((long)((Long)iterator.next())));
            }
            Arrays.fill((Object[])contentValues, (Object)"?");
            object.append("_id IN (").append(TextUtils.join((CharSequence)",", (Object[])contentValues)).append(")");
            if (Log.isLoggable((String)TAG, (int)3)) {
                Log.d((String)TAG, (String)("contactId where: " + object.toString()));
                Log.d((String)TAG, (String)("contactId selection: " + string));
            }
            contentValues = new ContentValues();
            contentValues.put("last_time_contacted", Long.valueOf((long)l));
            if (this.mResolver.update(ContactsContract.Contacts.CONTENT_URI, contentValues, object.toString(), (String[])string.toArray((Object[])new String[0])) > 0) {
                return true;
            }
            if (!Log.isLoggable((String)TAG, (int)3)) return false;
            {
                Log.d((String)TAG, (String)("update toward raw contacts " + (Object)uri + " failed"));
                return false;
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean updateWithPhoneNumber(Collection<String> contentResolver) {
        if (Log.isLoggable((String)TAG, (int)3)) {
            Log.d((String)TAG, (String)("updateWithPhoneNumber: " + Arrays.toString((Object[])contentResolver.toArray())));
        }
        if (contentResolver == null || contentResolver.isEmpty()) return false;
        HashSet hashSet = new HashSet();
        StringBuilder stringBuilder = new StringBuilder();
        Uri uri = new String[contentResolver.size()];
        hashSet.addAll((Collection)contentResolver);
        Arrays.fill((Object[])uri, (Object)"?");
        stringBuilder.append("data1 IN (").append(TextUtils.join((CharSequence)",", (Object[])uri)).append(")");
        contentResolver = this.mResolver;
        uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String string = stringBuilder.toString();
        hashSet = (String[])hashSet.toArray((Object[])new String[0]);
        contentResolver = contentResolver.query(uri, new String[]{"contact_id", "_id"}, string, (String[])hashSet, null);
        if (contentResolver == null) {
            Log.w((String)TAG, (String)"Cursor for Phone.CONTENT_URI became null.");
            return false;
        }
        hashSet = new HashSet(contentResolver.getCount());
        HashSet hashSet2 = new HashSet(contentResolver.getCount());
        try {
            contentResolver.move(-1);
            while (contentResolver.moveToNext()) {
                hashSet.add(contentResolver.getLong(0));
                hashSet2.add(contentResolver.getLong(1));
            }
            return this.update((Collection<Long>)hashSet, (Collection<Long>)hashSet2, "short_text");
        }
        finally {
            contentResolver.close();
        }
    }

    public static final class DataUsageFeedback {
        static final Uri FEEDBACK_URI = Uri.withAppendedPath((Uri)ContactsContract.Data.CONTENT_URI, (String)"usagefeedback");
    }

}

