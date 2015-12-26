/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.Intent
 *  android.database.Cursor
 *  android.database.sqlite.SqliteWrapper
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.provider.Telephony
 *  android.provider.Telephony$Sms
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.HashMap
 *  miui.yellowpage.MiPubUtils
 */
package com.xiaomi.mms.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.provider.Telephony;
import android.text.TextUtils;
import com.android.mms.data.Contact;
import java.util.HashMap;
import java.util.List;
import miui.yellowpage.MiPubUtils;

public class B2cMessageUtils {
    private static final Uri B2C_ADDRESS_URI = Uri.parse((String)"content://b2c-address");

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static HashMap<String, String> getB2cAddessDisplayNames(Context var0, List<String> var1_2) {
        var11_3 = new HashMap();
        var5_4 = var1_2.size();
        var2_5 = 0;
        var8_6 = new StringBuilder();
        var3_7 = 0;
        while (var3_7 < var5_4) {
            if (var8_6.length() > 0) {
                var8_6.append(",");
            }
            var8_6.append("'").append(var1_2.get(var3_7)).append("'");
            var4_8 = var2_5 + 1;
            if (var4_8 >= 50) ** GOTO lbl-1000
            var9_11 = var8_6;
            var2_5 = var4_8;
            if (var3_7 == var5_4 - 1) lbl-1000: // 2 sources:
            {
                block11 : {
                    var9_11 = var10_12 = null;
                    var12_13 = "address IN (" + var8_6.toString() + ")";
                    var9_11 = var10_12;
                    var10_12 = SqliteWrapper.query((Context)var0, (ContentResolver)var0.getContentResolver(), (Uri)B2cMessageUtils.B2C_ADDRESS_URI, (String[])null, (String)var12_13, (String[])null, (String)null);
                    if (var10_12 != null) {
                        var9_11 = var10_12;
                        if (var10_12.moveToFirst()) {
                            var9_11 = var10_12;
                            var2_5 = var10_12.getColumnIndex("display_name");
                            var9_11 = var10_12;
                            var6_9 = var10_12.getColumnIndex("address");
                            do {
                                var9_11 = var10_12;
                                var12_13 = var10_12.getString(var6_9);
                                var9_11 = var10_12;
                                var13_14 = var10_12.getString(var2_5);
                                var9_11 = var10_12;
                                if (!TextUtils.isEmpty((CharSequence)var13_14)) {
                                    var9_11 = var10_12;
                                    if (!TextUtils.isEmpty((CharSequence)var12_13)) {
                                        var9_11 = var10_12;
                                        var11_3.put((Object)var12_13, (Object)var13_14);
                                    }
                                }
                                var9_11 = var10_12;
                            } while (var7_10 = var10_12.moveToNext());
                        }
                    }
                    if (var10_12 == null) break block11;
                    var10_12.close();
                }
                var9_11 = var8_6;
                var2_5 = var4_8;
                if (var3_7 < var5_4 - 1) {
                    var2_5 = 0;
                    var9_11 = new StringBuilder();
                }
            }
            ++var3_7;
            var8_6 = var9_11;
        }
        return var11_3;
        catch (Throwable var0_1) {
            if (var9_11 == null) throw var0_1;
            var9_11.close();
            throw var0_1;
        }
    }

    public static String getB2cAddressDisplayName(Context object, String string2) {
        Uri uri;
        block6 : {
            Object var2_3 = null;
            uri = B2C_ADDRESS_URI.buildUpon().appendPath(string2).build();
            string2 = null;
            try {
                uri = SqliteWrapper.query((Context)object, (ContentResolver)object.getContentResolver(), (Uri)uri, (String[])null, (String)null, (String[])null, (String)null);
                object = var2_3;
                if (uri == null) break block6;
                object = var2_3;
                string2 = uri;
            }
            catch (Throwable var0_1) {
                if (string2 != null) {
                    string2.close();
                }
                throw var0_1;
            }
            if (!uri.moveToFirst()) break block6;
            string2 = uri;
            object = uri.getString(uri.getColumnIndex("display_name"));
        }
        if (uri != null) {
            uri.close();
        }
        return object;
    }

    public static Intent getB2cContactDetailIntent(String string2) {
        return new Intent("android.intent.action.VIEW", Uri.parse((String)String.format((String)"yellowpage://details?vipid=%s&source=miMessage", (Object[])new Object[]{Uri.encode((String)string2)})));
    }

    private static String getLastB2cNumber(Context object, String string2) {
        block8 : {
            Object var3_3 = null;
            String string3 = null;
            try {
                string2 = object.getContentResolver().query(Telephony.Sms.CONTENT_URI, new String[]{"b2c_numbers"}, "address=?", new String[]{string2}, "type,date desc limit 1");
                object = var3_3;
                if (string2 == null) break block8;
                object = var3_3;
                string3 = string2;
            }
            catch (Throwable var0_1) {
                if (string3 != null) {
                    string3.close();
                }
                throw var0_1;
            }
            if (!string2.moveToFirst()) break block8;
            string3 = string2;
            String string4 = string2.getString(0);
            object = var3_3;
            string3 = string2;
            if (TextUtils.isEmpty((CharSequence)string4)) break block8;
            string3 = string2;
            object = string4.split(";")[0];
        }
        if (string2 != null) {
            string2.close();
        }
        return object;
    }

    public static String getPossibleLastB2cNumber(Context context, String string2) {
        if (B2cMessageUtils.isB2cNumber(Contact.get(string2))) {
            return B2cMessageUtils.getLastB2cNumber(context, string2);
        }
        return null;
    }

    public static void insertOrUpdateB2cAddress(Context context, String string2, String string3) {
        ContentValues contentValues = new ContentValues();
        Uri uri = B2C_ADDRESS_URI.buildUpon().appendPath(string2).build();
        contentValues.put("address", string2);
        contentValues.put("display_name", string3);
        SqliteWrapper.insert((Context)context, (ContentResolver)context.getContentResolver(), (Uri)uri, (ContentValues)contentValues);
    }

    public static boolean isB2cNumber(Contact contact) {
        if (contact != null && (contact.isB2cNumber() || B2cMessageUtils.isB2cNumber(contact.getNumber()))) {
            return true;
        }
        return false;
    }

    public static boolean isB2cNumber(String string2) {
        if (!TextUtils.isEmpty((CharSequence)string2) && MiPubUtils.isXiaomiAccount((String)string2)) {
            return true;
        }
        return false;
    }
}

