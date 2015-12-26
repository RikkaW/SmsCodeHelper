/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.Intent
 *  android.database.sqlite.SqliteWrapper
 *  android.net.Uri
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Bundle
 *  android.os.SystemProperties
 *  android.provider.MiuiSettings
 *  android.provider.MiuiSettings$VirtualSim
 *  android.text.TextUtils
 *  android.util.Log
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  miui.telephony.CloudTelephonyManager
 *  miui.telephony.SmsManager
 *  miui.telephony.SubscriptionInfo
 *  miui.telephony.SubscriptionManager
 *  miui.telephony.SubscriptionManager$OnSubscriptionsChangedListener
 *  miui.telephony.TelephonyManager
 *  miui.telephony.exception.IllegalDeviceException
 *  miui.util.FeatureParser
 */
package com.android.mms.util;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemProperties;
import android.provider.MiuiSettings;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.MmsApp;
import com.android.mms.data.Contact;
import com.android.mms.transaction.SmsReceiver;
import com.xiaomi.mms.utils.B2cMessageUtils;
import miui.telephony.CloudTelephonyManager;
import miui.telephony.SmsManager;
import miui.telephony.SubscriptionInfo;
import miui.telephony.SubscriptionManager;
import miui.telephony.TelephonyManager;
import miui.telephony.exception.IllegalDeviceException;
import miui.util.FeatureParser;

public class MSimUtils {
    public static final long NONE_SIM_ID;
    public static final String SLOT_ID;
    public static final int SLOT_ID_ALL;
    public static final int SLOT_ID_INVALID;

    static {
        SLOT_ID = SubscriptionManager.SLOT_KEY;
        SLOT_ID_ALL = SubscriptionManager.DEFAULT_SLOT_ID;
        SLOT_ID_INVALID = SubscriptionManager.INVALID_SLOT_ID;
        NONE_SIM_ID = SubscriptionManager.INVALID_SUBSCRIPTION_ID;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static Uri addMessageToUri(ContentResolver contentResolver, Uri uri, String string2, String string3, String string4, Long l, boolean bl, boolean bl2, long l2, long l3) {
        ContentValues contentValues = new ContentValues(7);
        contentValues.put("address", string2);
        if (l != null) {
            contentValues.put("date", l);
        }
        string2 = bl ? Integer.valueOf((int)1) : Integer.valueOf((int)0);
        contentValues.put("read", (Integer)string2);
        contentValues.put("subject", string4);
        contentValues.put("body", string3);
        if (bl2) {
            contentValues.put("status", Integer.valueOf((int)32));
        }
        if (l2 != -1) {
            contentValues.put("thread_id", Long.valueOf((long)l2));
        }
        if (l3 != -1) {
            contentValues.put("sim_id", Long.valueOf((long)l3));
        }
        return contentResolver.insert(uri, contentValues);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static Uri addMiMessageToUri(ContentResolver contentResolver, Uri uri, String string2, String string3, String string4, Long l, boolean bl, boolean bl2, long l2, long l3, String string5) {
        ContentValues contentValues = new ContentValues(10);
        contentValues.put("address", string2);
        if (l != null) {
            contentValues.put("date", l);
        }
        l = bl ? Integer.valueOf((int)1) : Integer.valueOf((int)0);
        contentValues.put("read", (Integer)l);
        contentValues.put("subject", string4);
        contentValues.put("body", string3);
        if (bl2) {
            contentValues.put("status", Integer.valueOf((int)32));
        }
        if (l2 != -1) {
            contentValues.put("thread_id", Long.valueOf((long)l2));
        }
        contentValues.put("mx_status", Integer.valueOf((int)1));
        if (l3 != -1) {
            contentValues.put("sim_id", Long.valueOf((long)l3));
        }
        if (!TextUtils.isEmpty((CharSequence)string5)) {
            contentValues.put("b2c_numbers", string5);
            return contentResolver.insert(uri, contentValues);
        }
        if (!B2cMessageUtils.isB2cNumber(Contact.get(string2))) return contentResolver.insert(uri, contentValues);
        contentValues.put("b2c_numbers", string2);
        return contentResolver.insert(uri, contentValues);
    }

    public static String blockingGetSimId(Context context, int n) throws IllegalDeviceException {
        return CloudTelephonyManager.blockingGetSimId((Context)context, (int)n);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String createApnSelectionBySlotId(String string2, int n) {
        String string3 = string2;
        if (!MSimUtils.isMSim()) return string3;
        string3 = string2;
        if (MSimUtils.isAndroidM()) return string3;
        string3 = string2;
        if (!MSimUtils.isMSimSlotIdValid(n)) return string3;
        string3 = "slot_id=" + String.valueOf((int)n);
        if (!TextUtils.isEmpty((CharSequence)string2)) return string2 + " AND " + string3;
        return string3;
    }

    public static String createKeyWithSimId(long l, String string2) {
        String string3 = string2;
        if (MSimUtils.isMSim()) {
            string3 = Long.toString((long)l) + "_" + string2;
        }
        return string3;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static int getInsertedSimCount() {
        int n;
        int n2;
        if (MSimUtils.isMSim()) {
            n2 = n = SubscriptionManager.getDefault().getSubscriptionInfoCount();
            if (n < 0) {
                Log.e((String)"MSimUtils", (String)"getInsertedSimCount count < 0");
                n2 = n;
            }
        } else {
            n2 = TelephonyManager.getDefault().getIccCardCount();
        }
        n = n2;
        if (n2 <= 0) return n;
        n = n2;
        if (!MSimUtils.hasVirtualSim()) return n;
        return n2 - 1;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static int getInsertedSlotId() {
        int n = 0;
        if (MSimUtils.isMSim()) {
            int n2 = MSimUtils.getMultiSimCount();
            n = 0;
            while (n < n2) {
                SubscriptionInfo subscriptionInfo = SubscriptionManager.getDefault().getSubscriptionInfoForSlot(n);
                if (subscriptionInfo != null && subscriptionInfo.isActivated() && !MSimUtils.isVirtualSimbySlotId(n)) return n;
                {
                    ++n;
                    continue;
                }
            }
            return SLOT_ID_INVALID;
        }
        SubscriptionInfo subscriptionInfo = SubscriptionManager.getDefault().getSubscriptionInfoForSlot(0);
        if ((subscriptionInfo != null || !TelephonyManager.getDefault().hasIccCard()) && (subscriptionInfo == null || !subscriptionInfo.isActivated()) || MSimUtils.isVirtualSimbySlotId(0)) return SLOT_ID_INVALID;
        return n;
    }

    public static int getMultiSimCount() {
        int n = TelephonyManager.getDefault().getPhoneCount();
        if (n < 0) {
            Log.e((String)"MSimUtils", (String)"getMultiSimCount count < 0");
        }
        return n;
    }

    public static String getNetworkOperator(int n) {
        String string2 = "";
        if (!MSimUtils.isMSim()) {
            string2 = SystemProperties.get((String)"gsm.apn.sim.operator.numeric", (String)"");
        }
        return string2;
    }

    public static int getPhoneType(int n) {
        return TelephonyManager.getDefault().getPhoneTypeForSlot(n);
    }

    public static int getPreferredDataSlotId() {
        int n = 0;
        if (MSimUtils.isMSim()) {
            n = SubscriptionManager.getDefault().getDefaultDataSlotId();
        }
        return n;
    }

    public static int getPreferredSmsSlotId() {
        int n;
        int n2 = n = 0;
        if (MSimUtils.isMSim()) {
            n2 = n;
            if (MSimUtils.isMSimInserted()) {
                n2 = SubscriptionManager.getDefault().getDefaultVoiceSlotId();
            }
        }
        return n2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String getSimDisplayName(Context object, int n) {
        object = null;
        SubscriptionInfo subscriptionInfo = SubscriptionManager.getDefault().getSubscriptionInfoForSlot(n);
        if (subscriptionInfo != null) {
            return subscriptionInfo.getDisplayName().toString();
        }
        if (!MSimUtils.isMSim()) return object;
        Log.e((String)"MSimUtils", (String)"getSimDisplayName is empty");
        return null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int getSimIconResId(int n) {
        int n2;
        int n3 = n2 = 0;
        if (!MSimUtils.isMSim()) return n3;
        if (n == 0) {
            return 2130837972;
        }
        n3 = n2;
        if (n != 1) return n3;
        return 2130837973;
    }

    public static String getSimId(Context context, int n) {
        return CloudTelephonyManager.getSimId((Context)context, (int)n);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static long getSimIdBySlotId(int n) {
        long l = 0;
        SubscriptionInfo subscriptionInfo = SubscriptionManager.getDefault().getSubscriptionInfoForSlot(n);
        if (subscriptionInfo != null) {
            return subscriptionInfo.getSubscriptionId();
        }
        if (!MSimUtils.isMSim()) return l;
        return NONE_SIM_ID;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static long getSimIdFromIntent(Intent intent) {
        long l = 0;
        if (intent == null) {
            Log.e((String)"MSimUtils", (String)"getSimIdFromIntent intent is null");
            return l;
        } else {
            long l2;
            l = l2 = intent.getLongExtra("sim_id", 0);
            if (l2 >= 0) return l;
            {
                Log.e((String)"MSimUtils", (String)"getSimIdFromIntent simId < DEFAULT_SIM_ID");
                return l2;
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String getSimNumber(Context object, int n) {
        object = null;
        SubscriptionInfo subscriptionInfo = SubscriptionManager.getDefault().getSubscriptionInfoForSlot(n);
        if (subscriptionInfo != null) {
            return subscriptionInfo.getDisplayNumber();
        }
        if (!MSimUtils.isMSim()) return object;
        Log.e((String)"MSimUtils", (String)"getSimNumber is empty");
        return null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int getSlotIdBySimInfoId(long l) {
        int n = 0;
        if (!MSimUtils.isMSim()) return n;
        SubscriptionInfo subscriptionInfo = SubscriptionManager.getDefault().getSubscriptionInfoForSubscription((int)l);
        if (subscriptionInfo == null) return SLOT_ID_INVALID;
        if (subscriptionInfo.isActivated()) return subscriptionInfo.getSlotId();
        return SLOT_ID_INVALID;
    }

    public static int getSlotIdFromBundle(Bundle bundle) {
        int n = 0;
        if (MSimUtils.isMSim()) {
            int n2;
            n = n2 = bundle.getInt(SLOT_ID, 0);
            if (n2 < 0) {
                Log.e((String)"MSimUtils", (String)"getSlotIdFromBundle slotId < 0");
                n = n2;
            }
        }
        return n;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static int getSlotIdFromIntent(Intent intent) {
        int n = 0;
        if (intent == null) {
            Log.e((String)"MSimUtils", (String)"getSlotIdFromIntent intent is null");
            return n;
        } else {
            if (!MSimUtils.isMSim()) return n;
            {
                int n2;
                n = n2 = intent.getIntExtra(SLOT_ID, 0);
                if (n2 >= 0) return n;
                {
                    Log.e((String)"MSimUtils", (String)"getSlotIdFromIntent slotId < 0");
                    return n2;
                }
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static SmsManager getSmsManager(int n) {
        SmsManager smsManager = null;
        if (MSimUtils.isMSim()) {
            if (MSimUtils.isMSimSlotIdValid(n)) {
                smsManager = SmsManager.getDefault((int)n);
            }
        } else if (MSimUtils.isSingleSimSlotIdValid(n)) {
            smsManager = SmsManager.getDefault();
        }
        if (smsManager == null) {
            Log.e((String)"MSimUtils", (String)"getSmsManager is null");
        }
        return smsManager;
    }

    public static int getVirtualSimSlotId() {
        if (MiuiSettings.VirtualSim.isVirtualSimEnabled((Context)MmsApp.getApp()) && MiuiSettings.VirtualSim.getVirtualSimType((Context)MmsApp.getApp()) == 1) {
            return MiuiSettings.VirtualSim.getVirtualSimSlotId((Context)MmsApp.getApp());
        }
        return SLOT_ID_INVALID;
    }

    public static boolean hasVirtualSim() {
        if (MiuiSettings.VirtualSim.isVirtualSimEnabled((Context)MmsApp.getApp()) && MiuiSettings.VirtualSim.getVirtualSimType((Context)MmsApp.getApp()) == 1) {
            return true;
        }
        return false;
    }

    public static boolean isAndroid50() {
        if (Build.VERSION.SDK_INT >= 21) {
            return true;
        }
        return false;
    }

    public static boolean isAndroidM() {
        if (Build.VERSION.SDK_INT >= 23) {
            return true;
        }
        return false;
    }

    public static boolean isCallStateIdle() {
        for (int i = 0; i < TelephonyManager.getDefault().getPhoneCount(); ++i) {
            if (TelephonyManager.getDefault().getCallStateForSlot(i) == 0) continue;
            return false;
        }
        return true;
    }

    public static boolean isLeadcore() {
        return "leadcore".equals((Object)FeatureParser.getString((String)"vendor"));
    }

    public static boolean isMSim() {
        if (MSimUtils.getMultiSimCount() > 1) {
            return true;
        }
        return false;
    }

    public static boolean isMSimInserted() {
        if (MSimUtils.getInsertedSimCount() > 1) {
            return true;
        }
        return false;
    }

    public static boolean isMSimSlotIdValid(int n) {
        if (n >= 0 && n < MSimUtils.getMultiSimCount()) {
            return true;
        }
        return false;
    }

    public static boolean isMtk() {
        return "mediatek".equals((Object)FeatureParser.getString((String)"vendor"));
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean isSimInserted() {
        if (MSimUtils.isSimInserted(0) || MSimUtils.isSimInserted(1)) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean isSimInserted(int n) {
        boolean bl = false;
        if (!MSimUtils.isMSimSlotIdValid(n)) return false;
        SubscriptionInfo subscriptionInfo = SubscriptionManager.getDefault().getSubscriptionInfoForSlot(n);
        if (subscriptionInfo == null && !MSimUtils.isMSim()) {
            bl = TelephonyManager.getDefault().hasIccCard();
        } else {
            if (subscriptionInfo == null || !subscriptionInfo.isActivated()) {
                return false;
            }
            bl = true;
        }
        if (!bl) return false;
        {
            if (MSimUtils.isVirtualSimbySlotId(n)) return false;
            return true;
        }
    }

    public static boolean isSingleSimSlotIdValid(int n) {
        if (n == 0) {
            return true;
        }
        return false;
    }

    public static boolean isVirtualSimbySlotId(int n) {
        if (n != SLOT_ID_INVALID && MSimUtils.getVirtualSimSlotId() == n) {
            return true;
        }
        return false;
    }

    public static boolean moveMessageToFolder(Context context, Uri uri, int n, int n2, Long l, Integer n3, int n4) {
        return MSimUtils.moveMessageToFolder(context, uri, n, n2, l, n3, n4, null);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean moveMessageToFolder(Context context, Uri uri, int n, int n2, Long l, Integer n3, int n4, Long l2) {
        boolean bl;
        if (uri == null) {
            return false;
        }
        boolean bl2 = false;
        boolean bl3 = bl = false;
        boolean bl4 = bl2;
        switch (n) {
            default: {
                return false;
            }
            case 2: 
            case 4: {
                bl3 = true;
                bl4 = bl2;
                break;
            }
            case 5: 
            case 6: {
                bl4 = true;
                bl3 = bl;
            }
            case 1: 
            case 3: 
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", Integer.valueOf((int)n));
        if (bl4) {
            contentValues.put("read", Integer.valueOf((int)0));
        } else if (bl3) {
            contentValues.put("read", Integer.valueOf((int)1));
        }
        if (l != null) {
            contentValues.put("out_time", l);
        }
        if (n3 != null) {
            contentValues.put("status", n3);
        }
        contentValues.put("error_code", Integer.valueOf((int)n2));
        contentValues.put("mx_status", Integer.valueOf((int)n4));
        if (l2 != null) {
            contentValues.put("mx_id", l2);
        }
        if (1 == SqliteWrapper.update((Context)context, (ContentResolver)context.getContentResolver(), (Uri)uri, (ContentValues)contentValues, (String)null, (String[])null)) {
            return true;
        }
        return false;
    }

    public static void registerChangeListener(Context context, SubscriptionManager.OnSubscriptionsChangedListener onSubscriptionsChangedListener) {
        if (MSimUtils.isMSim()) {
            SubscriptionManager.getDefault().addOnSubscriptionsChangedListener(onSubscriptionsChangedListener);
        }
    }

    public static void sendQueuedMessage(Context context, int n) {
        if (n == SLOT_ID_ALL) {
            int n2 = MSimUtils.getMultiSimCount();
            for (n = 0; n < n2; ++n) {
                MSimUtils.sendQueuedMessageInt(context, n, true);
            }
        } else {
            MSimUtils.sendQueuedMessageInt(context, n, true);
        }
    }

    public static void sendQueuedMessageInt(Context context, int n, boolean bl) {
        Intent intent = new Intent("com.android.mms.transaction.SEND_MESSAGE", null, context, (Class)SmsReceiver.class);
        intent.putExtra(SLOT_ID, n);
        intent.putExtra("show_toast_when_offline", bl);
        Log.d((String)"MSimUtils", (String)("send queued message with broadcast with slotId " + n));
        context.sendBroadcast(intent);
    }

    public static void sendQueuedMessageNoToast(Context context, int n) {
        if (n == SLOT_ID_ALL) {
            int n2 = MSimUtils.getMultiSimCount();
            for (n = 0; n < n2; ++n) {
                MSimUtils.sendQueuedMessageInt(context, n, false);
            }
        } else {
            MSimUtils.sendQueuedMessageInt(context, n, false);
        }
    }

    public static void startSmscAddressActivity(Context context, int n) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setClassName("com.android.phone", "com.android.phone.settings.SmscAddressSettingActivity");
        intent.putExtra(SLOT_ID, n);
        context.startActivity(intent);
    }

    public static void unregisterChangeListener(Context context, SubscriptionManager.OnSubscriptionsChangedListener onSubscriptionsChangedListener) {
        if (MSimUtils.isMSim()) {
            SubscriptionManager.getDefault().removeOnSubscriptionsChangedListener(onSubscriptionsChangedListener);
        }
    }
}

