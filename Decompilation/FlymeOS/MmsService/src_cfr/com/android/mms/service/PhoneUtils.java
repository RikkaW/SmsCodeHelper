/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.telephony.TelephonyManager
 *  android.text.TextUtils
 *  android.util.Log
 *  com.android.i18n.phonenumbers.NumberParseException
 *  com.android.i18n.phonenumbers.PhoneNumberUtil
 *  com.android.i18n.phonenumbers.PhoneNumberUtil$PhoneNumberFormat
 *  com.android.i18n.phonenumbers.Phonenumber
 *  com.android.i18n.phonenumbers.Phonenumber$PhoneNumber
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Locale
 */
package com.android.mms.service;

import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.android.i18n.phonenumbers.NumberParseException;
import com.android.i18n.phonenumbers.PhoneNumberUtil;
import com.android.i18n.phonenumbers.Phonenumber;
import java.util.Locale;

public class PhoneUtils {
    public static String getNationalNumber(TelephonyManager telephonyManager, int n, String string) {
        String string2 = PhoneUtils.getSimOrDefaultLocaleCountry(telephonyManager, n);
        telephonyManager = PhoneNumberUtil.getInstance();
        string2 = PhoneUtils.getParsedNumber((PhoneNumberUtil)telephonyManager, string, string2);
        if (string2 == null) {
            return string;
        }
        return telephonyManager.format((Phonenumber.PhoneNumber)string2, PhoneNumberUtil.PhoneNumberFormat.NATIONAL).replaceAll("\\D", "");
    }

    private static Phonenumber.PhoneNumber getParsedNumber(PhoneNumberUtil phoneNumberUtil, String string, String string2) {
        block3 : {
            try {
                Phonenumber.PhoneNumber phoneNumber = phoneNumberUtil.parse(string, string2);
                if (!phoneNumberUtil.isValidNumber(phoneNumber)) break block3;
                return phoneNumber;
            }
            catch (NumberParseException var0_1) {
                Log.e((String)"MmsService", (String)("getParsedNumber: Not able to parse phone number " + string));
                return null;
            }
        }
        Log.e((String)"MmsService", (String)("getParsedNumber: not a valid phone number " + string + " for country " + string2));
        return null;
    }

    private static String getSimCountry(TelephonyManager object, int n) {
        if (TextUtils.isEmpty((CharSequence)(object = object.getSimCountryIso(n)))) {
            return null;
        }
        return object.toUpperCase();
    }

    private static String getSimOrDefaultLocaleCountry(TelephonyManager object, int n) {
        String string;
        object = string = PhoneUtils.getSimCountry((TelephonyManager)object, n);
        if (TextUtils.isEmpty((CharSequence)string)) {
            object = Locale.getDefault().getCountry();
        }
        return object;
    }
}

