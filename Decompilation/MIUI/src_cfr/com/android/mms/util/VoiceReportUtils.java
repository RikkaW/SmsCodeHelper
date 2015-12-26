/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ComponentName
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.Intent
 *  android.media.AudioManager
 *  android.os.Bundle
 *  android.provider.Settings
 *  android.provider.Settings$System
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.util;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.Settings;
import com.android.mms.data.Contact;
import com.android.mms.transaction.SmsReportService;
import com.android.mms.util.MSimUtils;

public class VoiceReportUtils {
    /*
     * Enabled aggressive block sorting
     */
    public static boolean checkVoiceReport(Context context) {
        int n = Settings.System.getInt((ContentResolver)context.getContentResolver(), (String)"voiceassist_report_method", (int)2);
        int n2 = Settings.System.getInt((ContentResolver)context.getContentResolver(), (String)"voiceassist_sms_report", (int)1);
        if (n == 2 || n2 <= 0 || n == 0 && !(context = (AudioManager)context.getSystemService("audio")).isWiredHeadsetOn() && !context.isBluetoothScoOn()) {
            return false;
        }
        return true;
    }

    public static void voiceReport(Context context, String string2, String string3, int n) {
        char c2;
        Contact contact = Contact.get(string2).load(true, true);
        char c3 = c2 = '0';
        if (contact != null) {
            c3 = c2;
            if (contact.getRealName() != null) {
                c3 = c2;
                if (!contact.getRealName().trim().equals((Object)"")) {
                    c3 = '1';
                }
            }
        }
        if (!VoiceReportUtils.checkVoiceReport(context)) {
            return;
        }
        Intent intent = new Intent(context, (Class)SmsReportService.class);
        Bundle bundle = new Bundle();
        bundle.putString("sms_address", string2);
        bundle.putString("sms_body", string3);
        if (c3 == '1') {
            bundle.putString("sms_name", contact.getName());
        }
        bundle.putChar("sms_contact", c3);
        bundle.putInt(MSimUtils.SLOT_ID, n);
        intent.putExtras(bundle);
        context.startService(intent);
    }
}

