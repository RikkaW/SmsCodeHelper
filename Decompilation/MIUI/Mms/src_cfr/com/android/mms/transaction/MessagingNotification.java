/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.ActivityManager
 *  android.app.ActivityManager$RunningTaskInfo
 *  android.app.MiuiNotification
 *  android.app.Notification
 *  android.app.Notification$Builder
 *  android.app.NotificationManager
 *  android.app.PendingIntent
 *  android.content.BroadcastReceiver
 *  android.content.ComponentName
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.IntentFilter
 *  android.content.SharedPreferences
 *  android.content.res.Resources
 *  android.database.Cursor
 *  android.database.sqlite.SqliteWrapper
 *  android.media.RingtoneManager
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.os.Handler
 *  android.preference.PreferenceManager
 *  android.provider.MiuiSettings
 *  android.provider.MiuiSettings$AntiSpam
 *  android.provider.MiuiSettings$System
 *  android.provider.Settings
 *  android.provider.Settings$System
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  android.provider.Telephony$Mms$Inbox
 *  android.provider.Telephony$Sms
 *  android.text.SpannableString
 *  android.text.TextUtils
 *  android.text.style.StyleSpan
 *  android.util.Log
 *  android.widget.RemoteViews
 *  android.widget.Toast
 *  com.google.android.mms.pdu.EncodedStringValue
 *  com.google.android.mms.pdu.MiuiPduPersister
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  java.util.Comparator
 *  java.util.HashSet
 *  miui.os.Build
 *  miui.util.SimRingtoneUtils
 */
package com.android.mms.transaction;

import android.app.ActivityManager;
import android.app.MiuiNotification;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SqliteWrapper;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.MiuiSettings;
import android.provider.Settings;
import android.provider.Telephony;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;
import com.android.mms.MmsApp;
import com.android.mms.data.Contact;
import com.android.mms.data.Conversation;
import com.android.mms.ui.ComposeMessageRouterActivity;
import com.android.mms.ui.MessageUtils;
import com.android.mms.ui.MmsTabActivity;
import com.android.mms.ui.NewMessagePopupActivity;
import com.android.mms.understand.UnderstandFactory;
import com.android.mms.understand.UnderstandMessage;
import com.android.mms.understand.UnderstandService;
import com.android.mms.util.AddressUtils;
import com.android.mms.util.MSimUtils;
import com.android.mms.util.NotificationHelper;
import com.android.mms.util.Reminder;
import com.android.mms.util.VibratorManager;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.xiaomi.mms.transaction.MipubThread;
import com.xiaomi.mms.transaction.MmsThread;
import com.xiaomi.mms.transaction.MsgThread;
import com.xiaomi.mms.utils.MxMessageUtils;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import miui.os.Build;
import miui.util.SimRingtoneUtils;

public class MessagingNotification {
    private static String CANCEL_FLOAT_INDEX;
    public static int CANCEL_FLOAT_MSGID_DEFAULT;
    private static final MmsSmsNotificationInfoComparator INFO_COMPARATOR;
    private static final String[] MMS_STATUS_PROJECTION;
    private static final String[] SMS_STATUS_PROJECTION;
    private static final Uri UNDELIVERED_URI;
    private static Runnable mCancelFloatNotification;
    private static Handler mUIHandler;
    private static int sCurrentFloatId;
    private static long sCurrentMessageThreadId;
    private static Object sCurrentMessageThreadIdLock;
    private static BroadcastReceiver sFloatNotificationCancelReceiver;
    private static OnDeletedReceiver sNotificationDeletedReceiver;
    private static Intent sNotificationOnDeleteIntent;

    static {
        MMS_STATUS_PROJECTION = new String[]{"thread_id", "date_full", "_id", "sub", "sub_cs", "block_type", "sim_id", "mx_type"};
        SMS_STATUS_PROJECTION = new String[]{"thread_id", "date", "_id", "subject", "body", "block_type", "sim_id", "address"};
        INFO_COMPARATOR = new MmsSmsNotificationInfoComparator();
        UNDELIVERED_URI = Uri.parse((String)"content://mms-sms/undelivered").buildUpon().appendQueryParameter("privacy_flag", "0").build();
        sNotificationDeletedReceiver = new OnDeletedReceiver();
        mUIHandler = new Handler();
        sFloatNotificationCancelReceiver = new BroadcastReceiver(){

            public void onReceive(Context context, Intent intent) {
                if (intent != null && intent.getIntExtra(CANCEL_FLOAT_INDEX, MessagingNotification.CANCEL_FLOAT_MSGID_DEFAULT) == sCurrentFloatId) {
                    MessagingNotification.cancelFloatNotification(context);
                    mUIHandler.removeCallbacks(mCancelFloatNotification);
                }
            }
        };
        mCancelFloatNotification = new Runnable(){

            @Override
            public void run() {
                MessagingNotification.cancelFloatNotification((Context)MmsApp.getApp());
            }
        };
        CANCEL_FLOAT_INDEX = "float_notification_index";
        CANCEL_FLOAT_MSGID_DEFAULT = 0;
        sCurrentMessageThreadId = 0;
        sCurrentMessageThreadIdLock = new Object();
    }

    private MessagingNotification() {
    }

    private static final int accumulateNotificationInfo(SortedSet sortedSet, MmsSmsNotificationInfo mmsSmsNotificationInfo) {
        if (mmsSmsNotificationInfo != null) {
            sortedSet.add((Object)mmsSmsNotificationInfo);
            return mmsSmsNotificationInfo.mCount;
        }
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static void blockingUpdateAllNotifications(Context context) {
        if (Thread.currentThread().isInterrupted()) {
            return;
        }
        MessagingNotification.blockingUpdateNewMessageIndicator(context, false, false);
        if (Thread.currentThread().isInterrupted()) return;
        MessagingNotification.updateSendFailedNotification(context);
        if (Thread.currentThread().isInterrupted()) return;
        MessagingNotification.updateDownloadFailedNotification(context);
    }

    public static void blockingUpdateNewMessageIndicator(Context context, boolean bl, boolean bl2) {
        TreeSet treeSet = new TreeSet(INFO_COMPARATOR);
        HashSet hashSet = new HashSet(4);
        int n = 0 + MessagingNotification.accumulateNotificationInfo(treeSet, MessagingNotification.getMmsNewMessageNotificationInfo(context, hashSet)) + MessagingNotification.accumulateNotificationInfo(treeSet, MessagingNotification.getSmsNewMessageNotificationInfo(context, hashSet)) + MessagingNotification.accumulateNotificationInfo(treeSet, MessagingNotification.getMipubNewMessageNotificationInfo(context, hashSet));
        MessagingNotification.cancelNotification(context, 123);
        MessagingNotification.cancelNotification(context, 124);
        if (!treeSet.isEmpty()) {
            if (Log.isLoggable((String)"Mms:app", (int)2)) {
                Log.d((String)"Mms:app", (String)("blockingUpdateNewMessageIndicator: count=" + n + ", isNew=" + bl));
            }
            ((MmsSmsNotificationInfo)treeSet.first()).deliver(context, bl, n, (Set<MsgThread>)hashSet);
        }
    }

    private static final Intent buildCopyTextIntent(Context context, String string, Uri uri) {
        Intent intent = null;
        if (!TextUtils.isEmpty((CharSequence)string)) {
            intent = new Intent(context, (Class)UnderstandService.class);
            intent.setPackage(context.getPackageName());
            intent.putExtra("extra_text", string);
            intent.setData(uri);
        }
        return intent;
    }

    private static final Intent buildFullScreenIntent(Context context, String string, String string2, boolean bl, long l, int n, Uri uri) {
        Intent intent = null;
        if (!TextUtils.isEmpty((CharSequence)string)) {
            intent = new Intent(context, (Class)NewMessagePopupActivity.class);
            intent.putExtra("from", string);
            intent.putExtra("body", string2);
            intent.putExtra("time", l);
            intent.putExtra(MSimUtils.SLOT_ID, n);
            intent.putExtra("showBody", bl);
            intent.setData(uri);
            intent.setFlags(872415232);
        }
        return intent;
    }

    private static RemoteViews buildRemoteViewsWithButton(Context context, int n, PendingIntent pendingIntent) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), 2130968623);
        remoteViews.setTextViewText(2131820659, (CharSequence)context.getString(n));
        remoteViews.setOnClickPendingIntent(2131820659, pendingIntent);
        return remoteViews;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected static CharSequence buildTickerMessage(Context object, String string, String string2, String string3) {
        object = Contact.get(string).load(true, true).getName();
        object = object == null ? "" : object.replace('\n', ' ').replace('\r', ' ');
        object = new StringBuilder((String)object);
        object.append(':').append(' ');
        int n = object.length();
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            object.append(string2.replace('\n', ' ').replace('\r', ' '));
            object.append(' ');
        }
        if (!TextUtils.isEmpty((CharSequence)string3)) {
            object.append(string3.replace('\n', ' ').replace('\r', ' '));
        }
        object = new SpannableString((CharSequence)object.toString());
        object.setSpan((Object)new StyleSpan(1), 0, n, 33);
        return object;
    }

    public static void cancelFloatNotification(Context context) {
        ((NotificationManager)context.getSystemService("notification")).cancel(124);
    }

    public static void cancelNotification(Context context, int n) {
        VibratorManager.cancel();
        ((NotificationManager)context.getSystemService("notification")).cancel(n);
        if (n == 123) {
            Reminder.cancelReminder(context);
        }
    }

    private static int getDownloadFailedMessageCount(Context context) {
        if ((context = SqliteWrapper.query((Context)context, (ContentResolver)context.getContentResolver(), (Uri)Telephony.Mms.Inbox.CONTENT_URI, (String[])null, (String)("m_type=" + String.valueOf((int)130) + " AND " + "st" + "=" + String.valueOf((int)135)), (String[])null, (String)null)) == null) {
            return 0;
        }
        int n = context.getCount();
        context.close();
        return n;
    }

    private static int[] getLedPwmOffOn(int n) {
        int[] arrn;
        arrn = new int[]{n / 4 * 3, n - arrn[0]};
        return arrn;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static final MmsSmsNotificationInfo getMipubNewMessageNotificationInfo(Context object, Set<MsgThread> set) {
        String string = null;
        Cursor cursor = SqliteWrapper.query((Context)object, (ContentResolver)object.getContentResolver(), (Uri)MipubThread.Mipub.CONTENT_URI, (String[])MipubThread.Mipub.STATUS_PROJECTION, (String)"(seen = 0)", (String[])null, (String)"date desc");
        Object object2 = string;
        if (cursor == null) return object2;
        {
            object2 = string;
            try {
                if (!cursor.moveToFirst()) return object2;
                {
                    int n = cursor.getColumnIndex("thread_id");
                    long l = cursor.getLong(n);
                    long l2 = cursor.getLong(cursor.getColumnIndex("date"));
                    long l3 = cursor.getLong(cursor.getColumnIndex("_id"));
                    string = cursor.getString(cursor.getColumnIndex("snippet"));
                    String string2 = cursor.getString(cursor.getColumnIndex("address"));
                    int n2 = MiuiSettings.AntiSpam.isQuietModeEnable((Context)object) ? 1 : 0;
                    Uri uri = MipubThread.Mipub.CONTENT_URI.buildUpon().appendPath(Long.toString((long)l3)).build();
                    object2 = new MipubThread(l, string2);
                    object = MessagingNotification.getNewMessageNotificationInfo(string2, string, (Context)object, 2130838002, null, (MsgThread)object2, l2, cursor.getCount(), n2, 0, uri, false, true);
                    set.add((MsgThread)object2);
                    do {
                        object2 = object;
                        if (!cursor.moveToNext()) return object2;
                        {
                            set.add(new MipubThread(cursor.getLong(n), null));
                            continue;
                        }
                        break;
                    } while (true);
                }
            }
            finally {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
            }
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static final MmsSmsNotificationInfo getMmsNewMessageNotificationInfo(Context var0, Set<MsgThread> var1_2) {
        block14 : {
            block13 : {
                var11_3 = SqliteWrapper.query((Context)var0, (ContentResolver)var0.getContentResolver(), (Uri)Telephony.Mms.CONTENT_URI, (String[])MessagingNotification.MMS_STATUS_PROJECTION, (String)"(msg_box=1 AND seen=0 AND (m_type=130 OR m_type=132))", (String[])null, (String)"date_full desc");
                if (var11_3 == null) {
                    return null;
                }
                var4_4 = var11_3.moveToFirst();
                if (var4_4) break block13;
                var11_3.close();
                return null;
            }
            var5_5 = var11_3.getLong(2);
            var12_6 = Telephony.Mms.CONTENT_URI.buildUpon().appendPath(Long.toString((long)var5_5)).build();
            var14_7 = AddressUtils.getFrom(var0, var12_6);
            var15_8 = Contact.get(var14_7).load(true, true);
            var4_4 = var15_8.getSendToVoicemail();
            if (!var4_4) break block14;
            var11_3.close();
            return null;
        }
        var10_9 = MessagingNotification.getMmsSubject(var11_3.getString(3), var11_3.getInt(4));
        var2_10 = var11_3.getInt(7);
        var9_11 = var10_9;
        if (var2_10 > 0) {
            var13_12 = MxMessageUtils.getNotificationDescByMx2Type(var2_10);
            var9_11 = var10_9;
            if (!TextUtils.isEmpty((CharSequence)var13_12)) {
                var9_11 = (String)var13_12 + var10_9;
            }
        }
        var5_5 = var11_3.getLong(0);
        var7_13 = var11_3.getLong(1);
        var2_10 = var11_3.getInt(5);
        var3_14 = MSimUtils.getSlotIdBySimInfoId(var11_3.getLong(6));
        var10_9 = null;
        var13_12 = new MmsThread(var5_5, var14_7);
        if (MessageUtils.checkPrivateMessage(var0, var15_8.getNumber())) {
            var9_11 = var10_9;
            if (MessageUtils.getPrefPrivateNotificationEnabled(var0)) {
                var9_11 = MessagingNotification.getNewMessageNotificationInfo("", "", var0, 2130838002, null, (MsgThread)var13_12, var7_13, var11_3.getCount(), var2_10, var3_14, var12_6, true, MessageUtils.getPrefNotificationBodyEnabledWithSecure(var0));
            }
lbl39: // 4 sources:
            do {
                var1_2.add((MsgThread)var13_12);
                while (var11_3.moveToNext() != false) {
                    var1_2.add(new MmsThread(var11_3.getLong(0), null));
                }
                return var9_11;
                break;
            } while (true);
        }
        var9_11 = MessagingNotification.getNewMessageNotificationInfo(var14_7, (String)var9_11, var0, 2130838002, null, (MsgThread)var13_12, var7_13, var11_3.getCount(), var2_10, var3_14, var12_6, false, MessageUtils.getPrefNotificationBodyEnabledWithSecure(var0));
        ** while (true)
        finally {
            var11_3.close();
        }
    }

    private static String getMmsSubject(String string, int n) {
        if (TextUtils.isEmpty((CharSequence)string)) {
            return "";
        }
        return new EncodedStringValue(n, MiuiPduPersister.getBytes((String)string)).getString();
    }

    private static int getMsgThreadClassCounts(Set<MsgThread> object) {
        HashSet hashSet = new HashSet();
        object = object.iterator();
        while (object.hasNext()) {
            hashSet.add(((MsgThread)object.next()).getClass().getSimpleName());
        }
        return hashSet.size();
    }

    /*
     * Enabled aggressive block sorting
     */
    private static final MmsSmsNotificationInfo getNewMessageNotificationInfo(String string, String string2, Context context, int n, String string3, MsgThread msgThread, long l, int n2, int n3, int n4, Uri uri, boolean bl, boolean bl2) {
        Intent intent = msgThread.mThreadId > 0 && !bl ? msgThread.getConversationClickIntent(context) : new Intent(context, (Class)MmsTabActivity.class);
        Intent intent2 = MessagingNotification.buildFullScreenIntent(context, string, string2, bl2, l, n4, uri);
        if (!bl2 || TextUtils.isEmpty((CharSequence)string2)) {
            string2 = context.getString(2131362056);
        }
        String string4 = context.getString(2131362003);
        if (!TextUtils.isEmpty((CharSequence)string)) {
            string4 = msgThread.getSenderName(context, string);
        }
        return new MmsSmsNotificationInfo(intent, intent2, string2, n, MessagingNotification.buildTickerMessage(context, string, string3, string2), l, string4, n2, n3, msgThread, n4, uri);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static final MmsSmsDeliveryInfo getSmsNewDeliveryInfo(Context object, Uri object2) {
        Cursor cursor;
        int n;
        block9 : {
            block8 : {
                if (object2 == null) {
                    return null;
                }
                cursor = SqliteWrapper.query((Context)object, (ContentResolver)object.getContentResolver(), (Uri)object2, (String[])SMS_STATUS_PROJECTION, (String)null, (String[])null, (String)null);
                if (cursor == null) {
                    return null;
                }
                try {
                    boolean bl = cursor.moveToFirst();
                    if (bl) break block8;
                }
                catch (Throwable var0_1) {
                    cursor.close();
                    throw var0_1;
                }
                cursor.close();
                return null;
            }
            String string = cursor.getString(7);
            object2 = Contact.get(string).load(true, true);
            String string2 = object2.getRealName();
            n = MSimUtils.getSlotIdBySimInfoId(cursor.getLong(6));
            if (object2.isB2cNumber()) {
                object2 = TextUtils.isEmpty((CharSequence)string2) ? object.getString(2131362408) : string2;
            } else {
                object2 = string;
                if (TextUtils.isEmpty((CharSequence)string2)) break block9;
                object2 = string2 + "(" + string + ")";
            }
        }
        object = new MmsSmsDeliveryInfo(String.format((String)object.getString(2131361984), (Object[])new Object[]{object2}), 3000, n);
        cursor.close();
        return object;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static final MmsSmsNotificationInfo getSmsNewMessageNotificationInfo(Context var0, Set<MsgThread> var1_2) {
        block12 : {
            block11 : {
                var10_3 = SqliteWrapper.query((Context)var0, (ContentResolver)var0.getContentResolver(), (Uri)Telephony.Sms.CONTENT_URI, (String[])MessagingNotification.SMS_STATUS_PROJECTION, (String)"(type = 1 AND seen = 0)", (String[])null, (String)"date desc");
                if (var10_3 == null) {
                    return null;
                }
                var4_4 = var10_3.moveToFirst();
                if (var4_4) break block11;
                var10_3.close();
                return null;
            }
            var13_5 = var10_3.getString(7);
            var14_6 = Contact.get(var13_5).load(true, true);
            var4_4 = var14_6.getSendToVoicemail();
            if (!var4_4) break block12;
            var10_3.close();
            return null;
        }
        var5_7 = var10_3.getLong(2);
        var12_8 = Telephony.Sms.CONTENT_URI.buildUpon().appendPath(Long.toString((long)var5_7)).build();
        var15_9 = var10_3.getString(4);
        var5_7 = var10_3.getLong(0);
        var7_10 = var10_3.getLong(1);
        var2_11 = var10_3.getInt(5);
        var3_12 = MSimUtils.getSlotIdBySimInfoId(var10_3.getLong(6));
        var9_13 = null;
        var11_14 = new MmsThread(var5_7, var13_5);
        if (MessageUtils.checkPrivateMessage(var0, var14_6.getNumber())) {
            if (MessageUtils.getPrefPrivateNotificationEnabled(var0)) {
                var9_13 = MessagingNotification.getNewMessageNotificationInfo("", "", var0, 2130838003, null, var11_14, var7_10, var10_3.getCount(), var2_11, var3_12, var12_8, true, MessageUtils.getPrefNotificationBodyEnabledWithSecure(var0));
            }
lbl31: // 4 sources:
            do {
                var1_2.add(var11_14);
                while (var10_3.moveToNext() != false) {
                    var1_2.add(new MmsThread(var10_3.getLong(0), null));
                }
                return var9_13;
                break;
            } while (true);
        }
        var9_13 = MessagingNotification.getNewMessageNotificationInfo(var13_5, var15_9, var0, 2130838003, null, var11_14, var7_10, var10_3.getCount(), var2_11, var3_12, var12_8, false, MessageUtils.getPrefNotificationBodyEnabledWithSecure(var0));
        ** while (true)
        finally {
            var10_3.close();
        }
    }

    private static Uri getSmsSound(Context context, int n, int n2) {
        switch (n) {
            default: {
                return null;
            }
            case 16: {
                return SimRingtoneUtils.getDefaultSmsReceivedUri((Context)context, (int)n2);
            }
            case 8: 
        }
        return SimRingtoneUtils.getDefaultSmsDeliveredUri((Context)context, (int)n2);
    }

    /*
     * Exception decompiling
     */
    private static int getUndeliveredMessageCount(Context var0, long[] var1_1) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [3[DOLOOP]], but top level block is 1[TRYBLOCK]
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
        // org.benf.cfr.reader.Main.doJar(Main.java:128)
        // org.benf.cfr.reader.Main.main(Main.java:178)
        throw new IllegalStateException("Decompilation failed");
    }

    public static void init(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.android.mms.NOTIFICATION_DELETED_ACTION");
        context.registerReceiver((BroadcastReceiver)sNotificationDeletedReceiver, intentFilter);
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.android.mms.NOTIFICATION_FLOAT_NOTIFICATION_CANCEL_ACTION");
        context.registerReceiver(sFloatNotificationCancelReceiver, intentFilter);
        sNotificationOnDeleteIntent = new Intent("com.android.mms.NOTIFICATION_DELETED_ACTION");
        sCurrentFloatId = MessageUtils.getNotificationIndex(context);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static boolean isCurrentMessageThreadId(MsgThread msgThread) {
        Object object = sCurrentMessageThreadIdLock;
        synchronized (object) {
            if (msgThread.mThreadId <= 0) return false;
            if (msgThread.mThreadId != sCurrentMessageThreadId) return false;
            if (!(msgThread instanceof MmsThread)) return false;
            return true;
        }
    }

    public static boolean isFailedToDeliver(Intent intent) {
        boolean bl;
        boolean bl2 = bl = false;
        if (intent != null) {
            bl2 = bl;
            if (intent.getBooleanExtra("undelivered_flag", false)) {
                bl2 = true;
            }
        }
        return bl2;
    }

    public static boolean isFailedToDownload(Intent intent) {
        boolean bl;
        boolean bl2 = bl = false;
        if (intent != null) {
            bl2 = bl;
            if (intent.getBooleanExtra("failed_download_flag", false)) {
                bl2 = true;
            }
        }
        return bl2;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static boolean isFloatNotificationEnabled(Context context) {
        boolean bl = !MessageUtils.isAccessControlledOrPrivacyModeEnabled(context) && MessageUtils.getPrefNotificationEnabled(context) && !MiuiSettings.AntiSpam.isQuietModeEnable((Context)context);
        Log.d((String)"Mms:app", (String)("isFloatNotificationEnabled is " + String.valueOf((boolean)bl)));
        return bl;
    }

    private static boolean isMmsActivityOnTop(Context object) {
        List list = ((ActivityManager)object.getSystemService("activity")).getRunningTasks(1);
        if (list.isEmpty()) {
            return false;
        }
        object = ((ActivityManager.RunningTaskInfo)list.get((int)0)).topActivity;
        list = ((ActivityManager.RunningTaskInfo)list.get((int)0)).baseActivity;
        if ("com.android.mms".equals((Object)object.getPackageName())) {
            if (MessagingNotification.isMmsSettingsActivityOnTop((String)(object = object.getClassName()))) {
                return "com.android.mms".equals((Object)list.getPackageName());
            }
            if (!"com.android.mms.ui.NewMessagePopupActivity".equals(object)) {
                return true;
            }
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static boolean isMmsSettingsActivityOnTop(String string) {
        if (string.equals((Object)"com.android.mms.ui.MessagingAdvancedPreferenceActivity") || string.equals((Object)"com.android.mms.ui.MessagingPreferenceActivity") || string.equals((Object)"com.android.mms.ui.ManageSimMessages") || string.equals((Object)"com.android.mms.ui.MxFeedbackActivity") || string.equals((Object)"com.android.mms.ui.MxPreferenceActivity") || MSimUtils.isMSim() && (string.equals((Object)"com.android.mms.ui.SelectCardPreferenceActivity") || string.equals((Object)"com.android.mms.ui.MultiSimPreferenceAcitvity"))) {
            return true;
        }
        return false;
    }

    private static boolean isNewMessagePopupOnTop(Context object) {
        if ((object = ((ActivityManager)object.getSystemService("activity")).getRunningTasks(1)).isEmpty()) {
            return false;
        }
        object = ((ActivityManager.RunningTaskInfo)object.get((int)0)).topActivity;
        if (object.getPackageName().equals((Object)"com.android.mms") && object.getClassName().equals((Object)"com.android.mms.ui.NewMessagePopupActivity")) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void nonBlockingUpdateDeliveryInfo(final Context context, final Uri uri) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences((Context)context);
        boolean bl = !Build.IS_CM_CUSTOMIZATION_TEST;
        if (!sharedPreferences.getBoolean("pref_key_delivery_reports", bl)) {
            return;
        }
        new Thread(new Runnable(){

            @Override
            public void run() {
                MmsSmsDeliveryInfo mmsSmsDeliveryInfo = MessagingNotification.getSmsNewDeliveryInfo(context, uri);
                if (mmsSmsDeliveryInfo != null) {
                    mmsSmsDeliveryInfo.deliver(context, true);
                }
            }
        }).start();
    }

    public static void nonBlockingUpdateNewMessageIndicator(final Context context, final boolean bl, final boolean bl2) {
        new Thread(new Runnable(){

            @Override
            public void run() {
                MessagingNotification.blockingUpdateNewMessageIndicator(context, bl, bl2);
            }
        }).start();
    }

    public static void notifyDownloadFailed(Context context, long l) {
        MessagingNotification.notifyFailed(context, true, l, true, 0);
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private static void notifyFailed(Context context, boolean bl, long l, boolean bl2, int n) {
        String string;
        long[] arrl;
        String string2;
        String string3;
        PreferenceManager.getDefaultSharedPreferences((Context)context);
        if (!MessageUtils.getPrefNotificationEnabled(context)) {
            return;
        }
        if (MessageUtils.checkPrivateMessage(context, l)) return;
        NotificationManager notificationManager = (NotificationManager)context.getSystemService("notification");
        long[] arrl2 = arrl = new long[2];
        arrl2[0] = 0;
        arrl2[1] = 1;
        int n2 = MessagingNotification.getUndeliveredMessageCount(context, arrl);
        if (n2 == 0) {
            if (!bl) return;
        }
        boolean bl3 = arrl[1] != 0 || bl;
        Notification notification = new Notification();
        if (n2 > 1) {
            string2 = bl ? context.getString(2131362008) : context.getString(2131361987, new Object[]{Integer.toString((int)n2)});
            string = bl ? context.getString(2131362007) : context.getString(2131361988);
            string3 = string;
            string = string2;
        } else {
            string2 = bl ? context.getString(2131362007) : context.getString(2131362008);
            string = context.getString(2131362009);
            string3 = string2;
        }
        if (bl3) {
            if (bl) {
                string2 = ComposeMessageRouterActivity.createIntent(context, l);
                string2.putExtra("failed_download_flag", true);
                string2.setAction("downloading_failed_action");
            } else {
                string2 = ComposeMessageRouterActivity.createIntent(context, arrl[0]);
                string2.putExtra("undelivered_flag", true);
                string2.setAction("sending_failed_action");
            }
        } else {
            string2 = new Intent(context, (Class)MmsTabActivity.class);
        }
        string2.addFlags(67108864);
        string2 = PendingIntent.getActivity((Context)context, (int)0, (Intent)string2, (int)134217728);
        notification.icon = 2130838004;
        notification.tickerText = string3;
        notification.setLatestEventInfo(context, (CharSequence)string3, (CharSequence)string, (PendingIntent)string2);
        if (bl2) {
            VibratorManager.vibrate(context);
            notification.sound = MessagingNotification.getSmsSound(context, 16, n);
        }
        if (bl) {
            notification.flags |= 16;
            notificationManager.notify(531, notification);
            return;
        }
        notificationManager.notify(789, notification);
    }

    public static void notifySendFailed(Context context, boolean bl) {
        MessagingNotification.notifyFailed(context, false, 0, bl, 0);
    }

    public static void notifySendFailed(Context context, boolean bl, int n) {
        MessagingNotification.notifyFailed(context, false, 0, bl, n);
    }

    private static void playDeliveryReportRingTone(Context context, int n, int n2) {
        Log.d((String)"Mms:app", (String)("play delivery report ring tone with block type = " + n));
        MessagingNotification.playSmsSoundRingTone(context, n, 8, n2);
    }

    private static void playReceivedRingTone(Context context, int n, int n2) {
        Log.d((String)"Mms:app", (String)("play received ring tone with block type = " + n));
        MessagingNotification.playSmsSoundRingTone(context, n, 16, n2);
    }

    /*
     * Enabled aggressive block sorting
     */
    private static void playSmsSoundRingTone(Context context, int n, int n2, int n3) {
        if (MiuiSettings.AntiSpam.isQuietModeEnable((Context)context) || n == 1) {
            Log.d((String)"Mms:app", (String)"enable Quiet mode or blockType is NONE_BUT_MUTE");
            return;
        } else {
            Uri uri = MessagingNotification.getSmsSound(context, n2, n3);
            if (uri == null || (context = RingtoneManager.getRingtone((Context)context, (Uri)uri)) == null) return;
            {
                context.setStreamType(5);
                context.play();
                return;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void setCurrentMessageThreadId(long l) {
        Object object = sCurrentMessageThreadIdLock;
        synchronized (object) {
            Log.d((String)"Mms:app", (String)("set sCurrentMessageThreadId = " + l));
            sCurrentMessageThreadId = l;
            return;
        }
    }

    private static void setNotificationDefault(Context object, Notification notification) {
        notification.flags |= 1;
        int n = Settings.System.getInt((ContentResolver)object.getContentResolver(), (String)"mms_breathing_light_color", (int)MiuiSettings.System.CALL_BREATHING_LIGHT_COLOR_DEFAULT);
        int n2 = Settings.System.getInt((ContentResolver)object.getContentResolver(), (String)"mms_breathing_light_freq", (int)MiuiSettings.System.CALL_BREATHING_LIGHT_FREQ_DEFAULT);
        notification.ledARGB = n;
        object = MessagingNotification.getLedPwmOffOn(n2);
        notification.ledOffMS = object[0];
        notification.ledOnMS = object[1];
        notification.defaults &= -5;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static void updateDeliveryNotification(final Context context, boolean bl, final CharSequence charSequence, final long l, final int n) {
        if (!bl || !MessageUtils.getPrefNotificationEnabled(context)) {
            return;
        }
        mUIHandler.post(new Runnable(){

            @Override
            public void run() {
                MessagingNotification.playDeliveryReportRingTone(context, 0, n);
                Toast.makeText((Context)context, (CharSequence)charSequence, (int)((int)l)).show();
            }
        });
    }

    public static void updateDownloadFailedNotification(Context context) {
        if (MessagingNotification.getDownloadFailedMessageCount(context) < 1) {
            MessagingNotification.cancelNotification(context, 531);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private static void updateFloatNotification(Context context, Intent object, Intent object2, String string, int n, boolean bl, long l, String string2, Uri uri) {
        if (MessagingNotification.isFloatNotificationEnabled(context) && bl && object2 != null) {
            mUIHandler.removeCallbacks(mCancelFloatNotification);
            object = PendingIntent.getActivity((Context)context, (int)0, (Intent)object, (int)134217728);
            Notification.Builder builder = new Notification.Builder(context);
            builder.setDefaults(4);
            builder.setContentTitle((CharSequence)string2);
            builder.setContentText((CharSequence)string);
            builder.setContentIntent((PendingIntent)object);
            builder.setSmallIcon(n);
            builder.setWhen(l);
            builder.setAutoCancel(true);
            bl = object2.getBooleanExtra("showBody", true);
            object = Contact.get(object2.getStringExtra("from"));
            boolean bl2 = bl && n == 2130838003 && !MessagingNotification.isMmsActivityOnTop(context) && !object.isB2cNumber();
            List<UnderstandMessage> list = UnderstandFactory.getUnderstandMessageList(string2, "", string, -1, 2);
            object = null;
            if (list != null && list.size() > 0) {
                object = list.get(0);
                object2 = UnderstandFactory.getTextWithUnderstand(context, string, list, 2131689551, context.getResources().getInteger(2131558404), "\u2026");
                object = MessagingNotification.buildRemoteViewsWithButton(context, 2131362080, PendingIntent.getService((Context)context, (int)0, (Intent)MessagingNotification.buildCopyTextIntent(context, ((UnderstandMessage.Item)object.mItems.get((int)0)).mValue, uri), (int)134217728));
            } else if (bl2) {
                uri = MessagingNotification.buildRemoteViewsWithButton(context, 2131362306, PendingIntent.getActivity((Context)context, (int)0, (Intent)object2, (int)134217728));
                object2 = object;
                object = uri;
            } else {
                uri = new RemoteViews(context.getPackageName(), 2130968624);
                object2 = object;
                object = uri;
            }
            object.setImageViewResource(16908294, n);
            object.setTextViewText(2131820657, (CharSequence)string2);
            if (object2 != null) {
                object.setTextViewText(2131820658, (CharSequence)object2);
            } else {
                object.setTextViewText(2131820658, (CharSequence)string);
            }
            builder.setContent((RemoteViews)object);
            object = builder.build();
            MessagingNotification.setNotificationDefault(context, (Notification)object);
            if (sCurrentFloatId == Integer.MAX_VALUE) {
                sCurrentFloatId = 0;
            }
            object2 = new Intent("com.android.mms.NOTIFICATION_FLOAT_NOTIFICATION_CANCEL_ACTION");
            object2.putExtra(CANCEL_FLOAT_INDEX, ++sCurrentFloatId);
            object.extraNotification.setExitFloatingIntent(PendingIntent.getBroadcast((Context)context, (int)sCurrentFloatId, (Intent)object2, (int)0));
            object.extraNotification.setMessageCount(0);
            ((NotificationManager)context.getSystemService("notification")).notify(124, (Notification)object);
            mUIHandler.postDelayed(mCancelFloatNotification, (long)object.extraNotification.getFloatTime());
            MessageUtils.setNotificationIndex((Context)MmsApp.getApp(), sCurrentFloatId);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private static void updateNotification(Context context, Intent intent, Intent intent2, String string, int n, boolean bl, CharSequence charSequence, long l, String string2, int n2, MsgThread msgThread, Set<MsgThread> set, int n3, int n4) {
        if (!MessageUtils.getPrefNotificationEnabled(context)) {
            return;
        }
        Notification.Builder builder = new Notification.Builder(context);
        if (!TextUtils.isEmpty((CharSequence)msgThread.mAddress) && (msgThread = msgThread.getPeoplePreferenceUri(msgThread.mAddress)) != null) {
            NotificationHelper.addPerson(builder, msgThread.toString());
        }
        builder.setSmallIcon(n);
        builder.setTicker(charSequence);
        builder.setWhen(l);
        if (MessagingNotification.getMsgThreadClassCounts(set) > 1) {
            string2 = context.getString(2131361986);
            intent = new Intent("android.intent.action.MAIN");
            intent.setClass(context, (Class)MmsTabActivity.class);
            intent.setType("vnd.android-dir/mms-sms");
        } else if (set.size() > 1) {
            string2 = context.getString(2131361986);
            intent = set.iterator().next().getCvListClickIntent(context);
        }
        if (n2 > 1) {
            string = context.getString(2131361985, new Object[]{Integer.toString((int)n2)});
        }
        intent = PendingIntent.getActivity((Context)context, (int)0, (Intent)intent, (int)134217728);
        builder.setContentTitle((CharSequence)string2);
        builder.setContentText((CharSequence)string);
        builder.setContentIntent((PendingIntent)intent);
        intent = builder.build();
        MessagingNotification.setNotificationDefault(context, (Notification)intent);
        intent.deleteIntent = PendingIntent.getBroadcast((Context)context, (int)0, (Intent)sNotificationOnDeleteIntent, (int)0);
        intent.extraNotification.setEnableFloat(false);
        intent.extraNotification.setMessageCount(n2);
        if (!bl) {
            ((NotificationManager)context.getSystemService("notification")).notify(123, (Notification)intent);
            return;
        }
        if (n3 == 1) {
            ((NotificationManager)context.getSystemService("notification")).notify(123, (Notification)intent);
        } else {
            intent.sound = MessagingNotification.getSmsSound(context, 16, n4);
            Reminder.newNotification(context, (Notification)intent);
        }
        if (intent2 == null) return;
        MessageUtils.notifyIncomingSmsMmsToBracelet(context, intent2.getStringExtra("from"));
    }

    public static void updateSendFailedNotification(Context context) {
        if (MessagingNotification.getUndeliveredMessageCount(context, null) < 1) {
            MessagingNotification.cancelNotification(context, 789);
        }
    }

    public static void updateSendFailedNotificationForThread(Context context, long l) {
        long[] arrl;
        long[] arrl2 = arrl = new long[2];
        arrl2[0] = 0;
        arrl2[1] = 0;
        if (MessagingNotification.getUndeliveredMessageCount(context, arrl) > 0 && arrl[0] == l && arrl[1] != 0) {
            MessagingNotification.cancelNotification(context, 789);
        }
    }

    private static final class MmsSmsDeliveryInfo {
        public int mSlotId;
        public CharSequence mTicker;
        public long mTimeMillis;

        public MmsSmsDeliveryInfo(CharSequence charSequence, long l, int n) {
            this.mTicker = charSequence;
            this.mTimeMillis = l;
            this.mSlotId = n;
        }

        public void deliver(Context context, boolean bl) {
            MessagingNotification.updateDeliveryNotification(context, bl, this.mTicker, this.mTimeMillis, this.mSlotId);
        }
    }

    private static final class MmsSmsNotificationInfo {
        public int mBlockType;
        public Intent mClickIntent;
        public int mCount;
        public String mDescription;
        public Intent mFullScreenIntent;
        public int mIconResourceId;
        public Uri mMsgUri;
        public int mSlotId;
        public MsgThread mThread;
        public CharSequence mTicker;
        public long mTimeMillis;
        public String mTitle;

        public MmsSmsNotificationInfo(Intent intent, Intent intent2, String string, int n, CharSequence charSequence, long l, String string2, int n2, int n3, MsgThread msgThread, int n4, Uri uri) {
            this.mClickIntent = intent;
            this.mFullScreenIntent = intent2;
            this.mDescription = string;
            this.mIconResourceId = n;
            this.mTicker = charSequence;
            this.mTimeMillis = l;
            this.mTitle = string2;
            this.mCount = n2;
            this.mBlockType = n3;
            this.mThread = msgThread;
            this.mSlotId = n4;
            this.mMsgUri = uri;
        }

        /*
         * Enabled aggressive block sorting
         */
        public void deliver(final Context context, boolean bl, int n, Set<MsgThread> set) {
            if (bl && MessagingNotification.isCurrentMessageThreadId(this.mThread)) {
                Log.d((String)"Mms:app", (String)("deliver notification for threaId " + this.mThread.mThreadId + " but only play sms sound, package is:" + this.mThread.getPackageName()));
                mUIHandler.post(new Runnable(){

                    @Override
                    public void run() {
                        MessagingNotification.playReceivedRingTone(context, MmsSmsNotificationInfo.this.mBlockType, MmsSmsNotificationInfo.this.mSlotId);
                        if (MmsSmsNotificationInfo.this.mBlockType != 1) {
                            VibratorManager.vibrate(context);
                        }
                    }
                });
                if (MessagingNotification.isNewMessagePopupOnTop(context) && MessagingNotification.isFloatNotificationEnabled(context) && this.mFullScreenIntent != null) {
                    context.startActivity(this.mFullScreenIntent);
                }
                return;
            }
            MessagingNotification.updateFloatNotification(context, this.mClickIntent, this.mFullScreenIntent, this.mDescription, this.mIconResourceId, bl, this.mTimeMillis, this.mTitle, this.mMsgUri);
            Intent intent = this.mClickIntent;
            Intent intent2 = this.mFullScreenIntent;
            String string = this.mDescription;
            int n2 = this.mIconResourceId;
            CharSequence charSequence = bl ? this.mTicker : null;
            MessagingNotification.updateNotification(context, intent, intent2, string, n2, bl, charSequence, this.mTimeMillis, this.mTitle, n, this.mThread, set, this.mBlockType, this.mSlotId);
        }

        public long getTime() {
            return this.mTimeMillis;
        }

    }

    private static final class MmsSmsNotificationInfoComparator
    implements Comparator<MmsSmsNotificationInfo> {
        private MmsSmsNotificationInfoComparator() {
        }

        public int compare(MmsSmsNotificationInfo mmsSmsNotificationInfo, MmsSmsNotificationInfo mmsSmsNotificationInfo2) {
            return Long.signum((long)(mmsSmsNotificationInfo2.getTime() - mmsSmsNotificationInfo.getTime()));
        }
    }

    public static class OnDeletedReceiver
    extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            if (Log.isLoggable((String)"Mms:app", (int)2)) {
                Log.d((String)"Mms:app", (String)"[MessagingNotification] clear notification: mark all msgs seen");
            }
            Conversation.markAllConversationsAsSeen(context);
        }
    }

}

