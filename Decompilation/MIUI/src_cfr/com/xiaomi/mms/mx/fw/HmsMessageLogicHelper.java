/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.ContentValues
 *  android.content.Context
 *  android.database.sqlite.SqliteWrapper
 *  android.net.Uri
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  miui.provider.ExtraTelephony
 *  miui.provider.ExtraTelephony$Hms
 */
package com.xiaomi.mms.mx.fw;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import miui.provider.ExtraTelephony;

public class HmsMessageLogicHelper {
    /*
     * Enabled aggressive block sorting
     */
    public static boolean moveHmsToFolder(Context context, int n, String string2) {
        boolean bl;
        ContentValues contentValues = new ContentValues(2);
        boolean bl2 = bl = false;
        switch (n) {
            default: {
                return false;
            }
            case 2: 
            case 4: {
                bl2 = true;
                break;
            }
            case 5: 
            case 6: {
                bl2 = bl;
            }
            case 1: 
            case 3: 
        }
        contentValues.put("type", Integer.valueOf((int)n));
        if (false) {
            contentValues.put("read", Integer.valueOf((int)0));
        } else if (bl2) {
            contentValues.put("read", Integer.valueOf((int)1));
        }
        if (1 != SqliteWrapper.update((Context)context, (ContentResolver)context.getContentResolver(), (Uri)ExtraTelephony.Hms.CONTENT_URI, (ContentValues)contentValues, (String)string2, (String[])null)) return false;
        return true;
    }

    public static void updateHmsTypeByMessageId(Context context, String string2, int n) {
        context = context.getContentResolver();
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("type", Integer.valueOf((int)n));
        string2 = "mx_message_id = " + string2;
        context.update(ExtraTelephony.Hms.CONTENT_URI, contentValues, string2, null);
    }
}

