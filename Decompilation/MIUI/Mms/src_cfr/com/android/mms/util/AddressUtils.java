/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.database.sqlite.SqliteWrapper
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  android.text.TextUtils
 *  com.google.android.mms.pdu.EncodedStringValue
 *  com.google.android.mms.pdu.MiuiPduPersister
 *  java.lang.Object
 *  java.lang.String
 *  miui.telephony.PhoneNumberUtils
 */
package com.android.mms.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.provider.Telephony;
import android.text.TextUtils;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.MiuiPduPersister;
import miui.telephony.PhoneNumberUtils;

public class AddressUtils {
    private AddressUtils() {
    }

    public static String cutPhoneNumberTail(String string2) {
        return PhoneNumberUtils.maskPhoneNumber((String)string2, (int)1);
    }

    public static String getFrom(Context object, Uri object2) {
        object2 = object2.getLastPathSegment();
        Object object3 = Telephony.Mms.CONTENT_URI.buildUpon();
        object3.appendPath((String)object2).appendPath("addr");
        object2 = SqliteWrapper.query((Context)object, (ContentResolver)object.getContentResolver(), (Uri)object3.build(), (String[])new String[]{"address", "charset"}, (String)"type=137", (String[])null, (String)null);
        if (object2 != null) {
            try {
                if (object2.moveToFirst() && !TextUtils.isEmpty((CharSequence)(object3 = object2.getString(0)))) {
                    object = MiuiPduPersister.getBytes((String)object3);
                    object = new EncodedStringValue(object2.getInt(1), (byte[])object).getString();
                    return object;
                }
            }
            finally {
                object2.close();
            }
        }
        return object.getString(2131361890);
    }

    public static String getTo(Context context, Uri object) {
        object = object.getLastPathSegment();
        Uri.Builder builder = Telephony.Mms.CONTENT_URI.buildUpon();
        builder.appendPath((String)object).appendPath("addr");
        context = SqliteWrapper.query((Context)context, (ContentResolver)context.getContentResolver(), (Uri)builder.build(), (String[])new String[]{"address", "charset"}, (String)"type=151", (String[])null, (String)null);
        if (context != null) {
            try {
                if (context.moveToFirst() && !TextUtils.isEmpty((CharSequence)(object = context.getString(0)))) {
                    object = MiuiPduPersister.getBytes((String)object);
                    object = new EncodedStringValue(context.getInt(1), (byte[])object).getString();
                    return object;
                }
            }
            finally {
                context.close();
            }
        }
        return "";
    }

    public static boolean isFetionNumber(String string2) {
        return string2.startsWith("12520");
    }
}

