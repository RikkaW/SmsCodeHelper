/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.database.sqlite.SqliteWrapper
 *  android.net.NetworkUtils
 *  android.net.Uri
 *  android.provider.Telephony
 *  android.provider.Telephony$Carriers
 *  android.text.TextUtils
 *  android.util.Log
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  miui.telephony.TelephonyManager
 */
package com.android.mms.transaction;

import android.content.ContentResolver;
import android.content.Context;
import android.database.sqlite.SqliteWrapper;
import android.net.NetworkUtils;
import android.net.Uri;
import android.provider.Telephony;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.util.MSimUtils;
import com.xiaomi.mms.utils.logger.MyLog;
import miui.telephony.TelephonyManager;

public class TransactionSettings {
    private static final String[] APN_PROJECTION = new String[]{"type", "mmsc", "mmsproxy", "mmsport"};
    private String mProxyAddress;
    private int mProxyPort;
    private String mServiceCenter;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public TransactionSettings(Context object, String string, int n) {
        this.mProxyPort = -1;
        CharSequence charSequence = MSimUtils.getNetworkOperator(n);
        if (TextUtils.isEmpty((CharSequence)charSequence)) {
            Log.v((String)"aaaaaa", (String)"TransactionSettings getNetworkOperator oper is null");
            charSequence = TelephonyManager.getDefault().getSimOperatorForSlot(n);
            Log.v((String)"aaaaaa", (String)("TransactionSettings getSimOperator oper is " + charSequence));
        } else {
            Log.v((String)"aaaaaa", (String)("TransactionSettings getNetworkOperator oper is " + charSequence));
        }
        String string2 = null;
        if (!TextUtils.isEmpty((CharSequence)charSequence)) {
            charSequence = string2 = "numeric='" + charSequence + "'";
            if (!TextUtils.isEmpty((CharSequence)string)) {
                charSequence = string2 + " AND " + "apn" + "='" + string.trim() + "'";
            }
        } else {
            charSequence = string2;
            if (!TextUtils.isEmpty((CharSequence)string)) {
                charSequence = "apn='" + string.trim() + "'";
            }
        }
        string = null;
        if (n == 0) {
            string2 = MSimUtils.createApnSelectionBySlotId(charSequence, n);
            string = SqliteWrapper.query((Context)object, (ContentResolver)object.getContentResolver(), (Uri)Telephony.Carriers.CONTENT_URI, (String[])APN_PROJECTION, (String)string2, (String[])null, (String)null);
        } else {
            string2 = charSequence;
            if (1 == n) {
                string2 = MSimUtils.createApnSelectionBySlotId(charSequence, n);
                string = SqliteWrapper.query((Context)object, (ContentResolver)object.getContentResolver(), (Uri)Telephony.Carriers.CONTENT_URI, (String[])APN_PROJECTION, (String)string2, (String[])null, (String)null);
            }
        }
        if (Log.isLoggable((String)"Mms:transaction", (int)2)) {
            charSequence = new StringBuilder().append("TransactionSettings looking for apn: ").append(string2).append(" returned: ");
            object = string == null ? "null cursor" : "" + string.getCount() + " hits";
            MyLog.v("TransactionSettings", charSequence.append((String)object).toString());
        }
        if (string == null) {
            MyLog.e("TransactionSettings", "Apn is not found in Database!");
            return;
        }
        n = 0;
        try {
            while (string.moveToNext() && TextUtils.isEmpty((CharSequence)this.mServiceCenter)) {
                int n2;
                NumberFormatException numberFormatException;
                block20 : {
                    if (!TransactionSettings.isValidApnType(string.getString(0), "mms")) continue;
                    n2 = 1;
                    object = charSequence = string.getString(1);
                    if (charSequence == null) {
                        object = "";
                    }
                    charSequence = string2 = string.getString(2);
                    if (string2 == null) {
                        charSequence = "";
                    }
                    this.mServiceCenter = NetworkUtils.trimV4AddrZeros((String)object.trim());
                    this.mProxyAddress = NetworkUtils.trimV4AddrZeros((String)charSequence.trim());
                    n = n2;
                    if (!this.isProxySet()) continue;
                    object = string.getString(3);
                    n = n2;
                    if (object == null) continue;
                    try {
                        this.mProxyPort = Integer.parseInt((String)object);
                        n = n2;
                        continue;
                    }
                    catch (NumberFormatException numberFormatException) {
                        if (!TextUtils.isEmpty((CharSequence)object)) break block20;
                        MyLog.w("TransactionSettings", "mms port not set!");
                        n = n2;
                        continue;
                    }
                }
                MyLog.e("TransactionSettings", "Bad port number format: " + (String)object, numberFormatException);
                n = n2;
            }
        }
        finally {
            string.close();
        }
        if (n == 0) return;
        if (!TextUtils.isEmpty((CharSequence)this.mServiceCenter)) return;
        MyLog.e("TransactionSettings", "Invalid APN setting: MMSC is empty");
    }

    /*
     * Enabled aggressive block sorting
     */
    public TransactionSettings(String string, String string2, int n) {
        this.mProxyPort = -1;
        string = string != null ? string.trim() : null;
        this.mServiceCenter = string;
        this.mProxyAddress = string2;
        this.mProxyPort = n;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private static boolean isValidApnType(String arrstring, String string) {
        if (TextUtils.isEmpty((CharSequence)arrstring)) {
            return true;
        }
        arrstring = arrstring.split(",");
        int n = arrstring.length;
        int n2 = 0;
        while (n2 < n) {
            String string2 = arrstring[n2];
            if (string2.equals((Object)string)) return true;
            if (string2.equals((Object)"*")) return true;
            ++n2;
        }
        return false;
    }

    public String getMmscUrl() {
        return this.mServiceCenter;
    }

    public String getProxyAddress() {
        return this.mProxyAddress;
    }

    public int getProxyPort() {
        return this.mProxyPort;
    }

    public boolean isProxySet() {
        if (this.mProxyAddress != null && this.mProxyAddress.trim().length() != 0) {
            return true;
        }
        return false;
    }
}

