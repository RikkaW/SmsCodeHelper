/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.accounts.Account
 *  android.accounts.AccountManager
 *  android.app.Activity
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.content.ActivityNotFoundException
 *  android.content.ComponentName
 *  android.content.ContentResolver
 *  android.content.ContentUris
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnCancelListener
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.pm.PackageInfo
 *  android.content.pm.PackageManager
 *  android.content.pm.PackageManager$NameNotFoundException
 *  android.content.res.MiuiConfiguration
 *  android.content.res.Resources
 *  android.content.res.Resources$Theme
 *  android.database.Cursor
 *  android.database.sqlite.SqliteWrapper
 *  android.graphics.drawable.AnimationDrawable
 *  android.graphics.drawable.Drawable
 *  android.media.CamcorderProfile
 *  android.net.Uri
 *  android.os.AsyncTask
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Bundle
 *  android.os.Debug
 *  android.os.Environment
 *  android.os.Handler
 *  android.os.Parcelable
 *  android.os.StatFs
 *  android.os.storage.StorageManager
 *  android.preference.PreferenceManager
 *  android.provider.Browser
 *  android.provider.CalendarContract
 *  android.provider.CalendarContract$Events
 *  android.provider.ContactsContract
 *  android.provider.ContactsContract$Contacts
 *  android.provider.Settings
 *  android.provider.Settings$Secure
 *  android.provider.Settings$System
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  android.provider.Telephony$Mms$Inbox
 *  android.provider.Telephony$MmsSms
 *  android.provider.Telephony$MmsSms$PendingMessages
 *  android.provider.Telephony$Sms
 *  android.provider.Telephony$Sms$Inbox
 *  android.security.ChooseLockSettingsHelper
 *  android.telephony.SmsMessage
 *  android.text.ClipboardManager
 *  android.text.SpannableString
 *  android.text.TextUtils
 *  android.util.Base64
 *  android.util.Log
 *  android.util.TypedValue
 *  android.view.View
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnPreDrawListener
 *  android.view.Window
 *  android.view.WindowManager
 *  android.view.WindowManager$LayoutParams
 *  android.view.inputmethod.InputMethodManager
 *  android.widget.AbsListView
 *  android.widget.ImageView
 *  android.widget.TextView
 *  android.widget.Toast
 *  com.google.android.mms.MmsException
 *  com.google.android.mms.pdu.EncodedStringValue
 *  com.google.android.mms.pdu.GenericPdu
 *  com.google.android.mms.pdu.MiuiPduPersister
 *  com.google.android.mms.pdu.MultimediaMessagePdu
 *  com.google.android.mms.pdu.NotificationInd
 *  com.google.android.mms.pdu.PduBody
 *  com.google.android.mms.pdu.PduPart
 *  com.google.android.mms.pdu.RetrieveConf
 *  com.google.android.mms.pdu.SendReq
 *  com.xiaomi.accountsdk.activate.ActivateManager
 *  java.io.File
 *  java.lang.Character
 *  java.lang.Class
 *  java.lang.Float
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  java.util.Date
 *  java.util.HashMap
 *  java.util.HashSet
 *  java.util.Locale
 *  java.util.Map$Entry
 *  java.util.concurrent.ConcurrentHashMap
 *  java.util.regex.Matcher
 *  java.util.regex.Pattern
 *  miui.app.ProgressDialog
 *  miui.date.Calendar
 *  miui.date.DateUtils
 *  miui.os.Build
 *  miui.provider.ExtraTelephony
 *  miui.security.SecurityManager
 *  miui.telephony.PhoneNumberUtils
 *  miui.view.menu.ContextMenuDialog
 *  miui.yellowpage.MiPubUtils
 *  miui.yellowpage.YellowPagePhone
 *  miui.yellowpage.YellowPageUtils
 */
package com.android.mms.ui;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.MiuiConfiguration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SqliteWrapper;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.CamcorderProfile;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.os.Environment;
import android.os.Handler;
import android.os.Parcelable;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.preference.PreferenceManager;
import android.provider.Browser;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.provider.Telephony;
import android.security.ChooseLockSettingsHelper;
import android.telephony.SmsMessage;
import android.text.ClipboardManager;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.mms.MmsApp;
import com.android.mms.MmsConfig;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import com.android.mms.data.WorkingMessage;
import com.android.mms.model.ImageModel;
import com.android.mms.model.SlideModel;
import com.android.mms.model.SlideshowModel;
import com.android.mms.model.TextModel;
import com.android.mms.model.VideoModel;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.transaction.MmsMessageSender;
import com.android.mms.transaction.TransactionService;
import com.android.mms.ui.DeliveryReportItem;
import com.android.mms.ui.MessageItem;
import com.android.mms.ui.MessageListItem;
import com.android.mms.ui.MessagingPreferenceActivity;
import com.android.mms.ui.NewMessageActivity;
import com.android.mms.ui.SimplePduDoc;
import com.android.mms.ui.SimplePduPage;
import com.android.mms.ui.SlideshowActivity;
import com.android.mms.ui.TextSizeAdjustSpan;
import com.android.mms.ui.UriImage;
import com.android.mms.util.AddressUtils;
import com.android.mms.util.DateParseUtils;
import com.android.mms.util.MSimUtils;
import com.android.mms.util.VoiceReportUtils;
import com.google.android.mms.MmsException;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.GenericPdu;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.google.android.mms.pdu.MultimediaMessagePdu;
import com.google.android.mms.pdu.NotificationInd;
import com.google.android.mms.pdu.PduBody;
import com.google.android.mms.pdu.PduPart;
import com.google.android.mms.pdu.RetrieveConf;
import com.google.android.mms.pdu.SendReq;
import com.xiaomi.accountsdk.activate.ActivateManager;
import com.xiaomi.mms.data.MxIdCache;
import com.xiaomi.mms.mx.data.Attachment;
import com.xiaomi.mms.transaction.MiCloudMxMmsTransactionHandler;
import com.xiaomi.mms.transaction.Mx2MmsTransactionService;
import com.xiaomi.mms.transaction.MxActivateService;
import com.xiaomi.mms.transaction.MxMmsTransactionService;
import com.xiaomi.mms.transaction.PushSession;
import com.xiaomi.mms.utils.MxMessageLogicHelper;
import com.xiaomi.mms.utils.MxMessagePduHelper;
import com.xiaomi.mms.utils.MxMessageUtils;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import miui.app.ProgressDialog;
import miui.date.Calendar;
import miui.date.DateUtils;
import miui.os.Build;
import miui.provider.ExtraTelephony;
import miui.security.SecurityManager;
import miui.telephony.PhoneNumberUtils;
import miui.view.menu.ContextMenuDialog;
import miui.yellowpage.MiPubUtils;
import miui.yellowpage.YellowPagePhone;
import miui.yellowpage.YellowPageUtils;

public class MessageUtils {
    private static List<Pattern> FILE_SHARE_URL_PATTERNS;
    public static String[] FILE_SHARE_URL_PATTERN_STRINGS;
    private static final Object INSERT_SMS_LOCK;
    private static String INTENT_INSERT_WEBSITE;
    private static final String[] MMS_REPORT_REQUEST_PROJECTION;
    private static final String[] MMS_REPORT_STATUS_PROJECTION;
    private static final Uri MX_SMS_STATUS_URI;
    private static final char[] NUMERIC_CHARS_SUGAR;
    private static final String PACKAGE_NAME_PHONE;
    private static final String SAVE_ATTACHMENT_DIRECTORY;
    public static final String SAVE_MMS_PART_TO_DISK_PATH;
    private static String[] SPECIAL_INTERNATIONAL_CODE;
    public static final Uri mmsUri;
    private static HashMap numericSugarMap;
    private static Pattern sIndiaSpPattern;
    private static final Map<String, String> sRecipientAddress;
    public static final Uri smsUri;

    /*
     * Enabled aggressive block sorting
     */
    static {
        FILE_SHARE_URL_PATTERN_STRINGS = new String[]{"http://www.kuaipan.cn/file/[a-zA-Z\\d_-]+.htm$", "http://kc.cc/[a-zA-Z\\d]+"};
        MMS_REPORT_REQUEST_PROJECTION = new String[]{"address", "d_rpt", "rr"};
        MMS_REPORT_STATUS_PROJECTION = new String[]{"address", "delivery_status", "read_status"};
        sRecipientAddress = new ConcurrentHashMap(20);
        NUMERIC_CHARS_SUGAR = new char[]{'-', '.', ',', '(', ')', ' ', '/', '\\', '*', '#', '+'};
        numericSugarMap = new HashMap(NUMERIC_CHARS_SUGAR.length);
        MX_SMS_STATUS_URI = Uri.parse((String)"content://sms/mx_status");
        INSERT_SMS_LOCK = new Object();
        for (int i = 0; i < NUMERIC_CHARS_SUGAR.length; ++i) {
            numericSugarMap.put((Object)Character.valueOf((char)NUMERIC_CHARS_SUGAR[i]), (Object)Character.valueOf((char)NUMERIC_CHARS_SUGAR[i]));
        }
        SAVE_ATTACHMENT_DIRECTORY = Environment.DIRECTORY_DOWNLOADS;
        sIndiaSpPattern = null;
        SAVE_MMS_PART_TO_DISK_PATH = (Object)Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DOWNLOADS + "/";
        PACKAGE_NAME_PHONE = Build.VERSION.SDK_INT > 20 ? "com.android.server.telecom" : "com.android.phone";
        INTENT_INSERT_WEBSITE = "website";
        smsUri = Uri.parse((String)"content://sms/sent");
        mmsUri = Uri.parse((String)"content://mms/sent");
        SPECIAL_INTERNATIONAL_CODE = new String[]{"60"};
    }

    private MessageUtils() {
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void activatePhone(Context context, boolean bl) {
        int n;
        int n2 = MSimUtils.getMultiSimCount();
        context = ActivateManager.get((Context)context);
        for (n = 0; n <= n2 - 1; ++n) {
            int n3 = bl ? 17 : 1;
            context.startActivateSim(n, 2, null, false, null, n3);
        }
        n = 0;
        while (n < n2) {
            MxActivateService.setEnableAfterActivation(n, true);
            ++n;
        }
    }

    public static boolean allowMenuMode() {
        if (Build.IS_CM_CUSTOMIZATION_TEST || Build.IS_INTERNATIONAL_BUILD || Build.IS_CU_CUSTOMIZATION_TEST || Build.IS_CTA_BUILD) {
            return false;
        }
        return true;
    }

    public static boolean allowPush() {
        if (Build.IS_CM_CUSTOMIZATION_TEST || Build.IS_INTERNATIONAL_BUILD) {
            return false;
        }
        return true;
    }

    public static int appendIntToCharArray(char[] arrc, int n, int n2) {
        int n3;
        int n4;
        int n5 = 0;
        do {
            arrc[n + n5] = (char)(n2 % 10 + 48);
            n3 = n2 / 10;
            n5 = n4 = n5 + 1;
            n2 = n3;
        } while (n3 > 0);
        n5 = n;
        for (n2 = n + n4 - 1; n5 < n2; ++n5, --n2) {
            char c2 = arrc[n5];
            arrc[n5] = arrc[n2];
            arrc[n2] = c2;
        }
        return n + n4;
    }

    private static void appendWithSeparator(StringBuilder stringBuilder, String string2, char c2) {
        if (TextUtils.isEmpty((CharSequence)string2)) {
            return;
        }
        if (stringBuilder.length() == 0) {
            stringBuilder.append(string2);
            return;
        }
        if (stringBuilder.charAt(stringBuilder.length() - 1) == c2) {
            stringBuilder.append(string2);
            return;
        }
        stringBuilder.append(c2);
        stringBuilder.append(string2);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static byte[] charSequence2Byte(CharSequence charSequence, String string2) {
        byte[] object = null;
        if (TextUtils.isEmpty((CharSequence)charSequence)) return object;
        try {
            return charSequence.toString().getBytes(string2);
        }
        catch (UnsupportedEncodingException unsupportedEncodingException) {
            Log.e((String)"Mms", (String)("Unsupported encoding: " + string2), (Throwable)unsupportedEncodingException);
            return charSequence.toString().getBytes();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean checkPrivateMessage(Context object, long l) {
        if (l <= 0 || (object = Conversation.get(l)) == null || !object.isPrivate()) {
            return false;
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static boolean checkPrivateMessage(Context object, String string2) {
        if (TextUtils.isEmpty((CharSequence)string2)) {
            return false;
        }
        object = new ArrayList(1);
        object.add((Object)string2);
        if ((object = Conversation.get(ContactList.getByNumbers(object))) == null) return false;
        if (!object.isPrivate()) return false;
        return true;
    }

    private static int compareSpecialIntlCode(String string2) {
        for (int i = 0; i < SPECIAL_INTERNATIONAL_CODE.length; ++i) {
            String string3 = SPECIAL_INTERNATIONAL_CODE[i];
            if (!string2.startsWith(string3)) continue;
            return string3.length() - 1;
        }
        return -1;
    }

    private static void confirmReadReportDialog(Context context, DialogInterface.OnClickListener onClickListener, DialogInterface.OnClickListener onClickListener2, DialogInterface.OnCancelListener onCancelListener) {
        context = new AlertDialog.Builder(context);
        context.setCancelable(true);
        context.setTitle(2131361998);
        context.setMessage(2131361999);
        context.setPositiveButton(2131361891, onClickListener);
        context.setNegativeButton(2131361892, onClickListener2);
        context.setOnCancelListener(onCancelListener);
        context.show();
    }

    private static String constructForwardBody(Context context, String string2, String string3) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(context.getString(2131362238));
        stringBuilder.append(string2);
        stringBuilder.append("\n");
        stringBuilder.append(string3);
        return stringBuilder.toString();
    }

    public static void copyMessageTextToClipboard(Context context, List<MessageItem> list) {
        ((ClipboardManager)context.getSystemService("clipboard")).setText((CharSequence)MessageUtils.getMessagesText(list));
        Toast.makeText((Context)context, (int)2131361821, (int)0).show();
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static void deleteMxMmsOnServer(Context context, Uri object) {
        int n;
        Object object2;
        long l;
        block6 : {
            block5 : {
                try {
                    object2 = SqliteWrapper.query((Context)context, (ContentResolver)context.getContentResolver(), (Uri)object, (String[])new String[]{"sim_id", "mx_id"}, (String)null, (String[])null, (String)null);
                    if (object2 == null) break block5;
                }
                catch (Throwable var0_1) {
                    void var0_2;
                    object2 = null;
                    if (object2 == null) throw var0_2;
                    object2.close();
                    throw var0_2;
                }
                if (!object2.moveToFirst()) break block5;
                n = object2.getInt(0);
                l = object2.getLong(1);
                break block6;
                {
                    catch (Throwable throwable) {}
                }
            }
            n = -1;
            l = -1;
        }
        if (object2 != null) {
            object2.close();
        }
        if (l <= 0) return;
        if (n == -1) return;
        if (TextUtils.isEmpty((CharSequence)(object = MiCloudMxMmsTransactionHandler.getAddr((Uri)object, context)))) return;
        object2 = MxIdCache.getOrQuery(context, (String)object);
        if (object2 == null) return;
        if ((n = MSimUtils.getSlotIdBySimInfoId(n)) <= -1) return;
        object = PushSession.getInstance(context).getMyFullMid(n);
        if (TextUtils.isEmpty((CharSequence)(object2 = object2.getMId()))) return;
        if (TextUtils.isEmpty((CharSequence)object)) return;
        MxMessageLogicHelper.sendDeleteCommandToServer(context, (String)object, (String)object2, String.valueOf((long)l));
        Log.v((String)"Mms", (String)("sendDeleteCommandToServer mxId " + l));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String deleteSpecialIntlCode(String string2) {
        String string3 = string2;
        if (!Build.IS_INTERNATIONAL_BUILD) {
            return string3;
        }
        String string4 = string3;
        if (TextUtils.isEmpty((CharSequence)string2)) return string4;
        int n = string2.charAt(0);
        if (n == 43) {
            string4 = string3;
            if (string2.length() <= 1) return string4;
            string2 = string2.substring(1);
            n = MessageUtils.compareSpecialIntlCode(string2);
            string4 = string3;
            if (n < 0) return string4;
            return string2.substring(n);
        }
        string4 = string3;
        if (n != 48) return string4;
        string4 = string3;
        if (string2.length() <= 2) return string4;
        string4 = string3;
        if (string2.charAt(1) != '0') return string4;
        string2 = string2.substring(2);
        n = MessageUtils.compareSpecialIntlCode(string2);
        string4 = string3;
        if (n < 0) return string4;
        return string2.substring(n);
    }

    public static void enableYellowPage(Context context) {
        if (!Build.IS_INTERNATIONAL_BUILD && !MiPubUtils.isYellowPageNetworkAllowed((Context)context)) {
            MiPubUtils.setYellowPageNetworkAllowed((Context)context);
        }
    }

    private static String extractEncStr(Context context, EncodedStringValue encodedStringValue) {
        if (encodedStringValue != null) {
            return encodedStringValue.getString();
        }
        return "";
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String extractEncStrFromCursor(Cursor object, int n, int n2) {
        String string2 = object.getString(n);
        n = object.getInt(n2);
        if (TextUtils.isEmpty((CharSequence)string2)) {
            return "";
        }
        object = string2;
        if (n == 0) return object;
        return new EncodedStringValue(n, MiuiPduPersister.getBytes((String)string2)).getString();
    }

    public static void forceSync(Context arraccount) {
        arraccount = AccountManager.get((Context)arraccount).getAccountsByType("com.xiaomi");
        Bundle bundle = new Bundle();
        bundle.putBoolean("force", true);
        for (int i = 0; i < arraccount.length; ++i) {
            ContentResolver.requestSync((Account)arraccount[i], (String)"sms", (Bundle)bundle);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void forwardMessage(final Context context, List<MessageItem> object, boolean bl) {
        Intent intent = new Intent(context, (Class)NewMessageActivity.class);
        intent.putExtra("forwarded_message", true);
        intent.putExtra("orig_message_is_private", bl);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences((Context)context);
        bl = Build.IS_CM_CUSTOMIZATION ? sharedPreferences.getBoolean("pref_key_fwd_sender_info", false) : false;
        if (object.size() > 1) {
            intent.putExtra("sms_body", MessageUtils.getForwardMessagesText(context, bl, object));
            context.startActivity(intent);
            return;
        }
        if (!(object = (MessageItem)object.get(0)).isSms()) {
            new AsyncTask<Void, Void, Void>((MessageItem)object, intent, ProgressDialog.show((Context)context, (CharSequence)null, (CharSequence)context.getString(2131362161))){
                final /* synthetic */ ProgressDialog val$dialog;
                final /* synthetic */ Intent val$intent;
                final /* synthetic */ MessageItem val$msgItem;

                protected /* varargs */ Void doInBackground(Void ... object) {
                    String string2;
                    object = string2 = context.getString(2131361856);
                    if (this.val$msgItem.getSubject() != null) {
                        object = string2 + this.val$msgItem.getSubject();
                    }
                    this.val$intent.putExtra("msg_uri", (Parcelable)this.val$msgItem.getMessageUri());
                    this.val$intent.putExtra("subject", (String)object);
                    this.val$intent.putExtra("mx2type", this.val$msgItem.getMx2Type());
                    context.startActivity(this.val$intent);
                    return null;
                }

                protected void onPostExecute(Void void_) {
                    this.val$dialog.dismiss();
                }
            }.execute((Object[])new Void[0]);
            return;
        }
        object = bl ? MessageUtils.constructForwardBody(context, object.getContactName(), object.getBody()) : object.getBody();
        intent.putExtra("sms_body", (String)object);
        context.startActivity(intent);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static long getAttachmentSize(Context context) {
        long l;
        block6 : {
            block5 : {
                Object object = Uri.parse((String)"content://mms/part/");
                if ((context = context.getContentResolver().query((Uri)object, new String[]{"_data"}, "mid IN (select _id from pdu where deleted = 0)", null, null)) == null) {
                    return 0;
                }
                try {
                    boolean bl;
                    if (!context.moveToFirst()) break block5;
                    long l2 = 0;
                    do {
                        object = context.getString(0);
                        l = l2;
                        if (object != null) {
                            l = l2 + new File((String)object).length();
                        }
                        bl = context.moveToNext();
                        l2 = l;
                    } while (bl);
                    break block6;
                }
                catch (Throwable var6_2) {
                    context.close();
                    throw var6_2;
                }
            }
            l = 0;
        }
        context.close();
        return l;
    }

    private static long getAvailableSpace(File file) {
        file = new StatFs(file.getAbsolutePath());
        return (long)file.getAvailableBlocks() * (long)file.getBlockSize();
    }

    public static String getContactsPackageName() {
        return "com.android.contacts";
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean getConversationGroupByTime(SharedPreferences sharedPreferences, String string2) {
        boolean bl = true;
        boolean bl2 = sharedPreferences.getBoolean("pref_key_time_style_auto_group", true);
        boolean bl3 = TextUtils.equals((CharSequence)string2, (CharSequence)MessageListItem.Style.bubble.toString());
        boolean bl4 = MiuiConfiguration.getScaleMode() != 11;
        if (MiuiConfiguration.getScaleMode() != 15) {
            return bl2 & bl3 & bl4 & bl;
        }
        bl = false;
        return bl2 & bl3 & bl4 & bl;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static int getCurrentMmsSize(CharSequence object, SlideshowModel slideshowModel) {
        int n;
        if (!TextUtils.isEmpty((CharSequence)object)) {
            n = (object = (Object)MessageUtils.charSequence2Byte((CharSequence)object, "utf-8")) != null ? object.length : 0;
            Log.v((String)"Mms", (String)("getMmsTotalSize text size = " + n));
        } else {
            n = 0;
        }
        int n2 = n;
        if (slideshowModel == null) return n2;
        if (slideshowModel.size() == 1) {
            n2 = n + slideshowModel.getCurrentMessageSize();
            object = slideshowModel.get(0).getText();
            n = n2;
            if (object != null) {
                n = n2 - object.getMediaSize();
            }
        } else {
            n = slideshowModel.getCurrentMessageSize();
        }
        Log.v((String)"Mms", (String)("getMmsTotalSize size of all slides = " + n));
        return n;
    }

    public static String getDateType(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("pref_key_date_type", "0");
    }

    /*
     * Enabled aggressive block sorting
     */
    public static int getDeliverReportMode(SharedPreferences sharedPreferences, boolean bl) {
        int n;
        int n2 = !Build.IS_CM_CUSTOMIZATION_TEST && sharedPreferences.getBoolean("pref_key_delivery_reports", true) && bl ? 20 : 0;
        long l = MSimUtils.getSimIdBySlotId(0);
        int n3 = n2;
        if (l >= 0) {
            n = n2;
            if (Build.IS_CM_CUSTOMIZATION_TEST) {
                n = n2;
                if (sharedPreferences.getBoolean(MSimUtils.createKeyWithSimId(l, "pref_key_delivery_reports"), false)) {
                    n = n2;
                    if (bl) {
                        n = n2 | 16 | 4;
                    }
                }
            }
            n3 = n;
            if (sharedPreferences.getBoolean(MSimUtils.createKeyWithSimId(l, "pref_key_mms_read_reports"), false)) {
                n3 = n | 1;
            }
        }
        n = n3;
        if (!MSimUtils.isMSim()) return n;
        l = MSimUtils.getSimIdBySlotId(1);
        n = n3;
        if (l <= 0) return n;
        n2 = n3;
        if (Build.IS_CM_CUSTOMIZATION_TEST) {
            n2 = n3;
            if (sharedPreferences.getBoolean(MSimUtils.createKeyWithSimId(l, "pref_key_delivery_reports"), false)) {
                n2 = n3;
                if (bl) {
                    n2 = n3 | 32 | 8;
                }
            }
        }
        n = n2;
        if (!sharedPreferences.getBoolean(MSimUtils.createKeyWithSimId(l, "pref_key_mms_read_reports"), false)) return n;
        return n2 | 2;
    }

    public static String getEncryptedPhone(String object, String string2) {
        try {
            object = new SecretKeySpec(Base64.decode((String)object, (int)2), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(new byte[]{100, 23, 84, 114, 72, 0, 4, 97, 73, 97, 2, 52, 84, 102, 18, 32});
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(1, (Key)object, ivParameterSpec);
            object = Base64.encodeToString((byte[])cipher.doFinal(string2.getBytes("UTF-8")), (int)2);
            return object;
        }
        catch (NoSuchAlgorithmException var0_1) {
            Log.v((String)"Mms", (String)("get cipher exception " + (Object)((Object)var0_1)));
            return null;
        }
        catch (NoSuchPaddingException var0_2) {
            Log.v((String)"Mms", (String)("get cipher exception " + (Object)((Object)var0_2)));
            return null;
        }
        catch (InvalidAlgorithmParameterException var0_3) {
            Log.v((String)"Mms", (String)("get pid exception " + (Object)((Object)var0_3)));
            return null;
        }
        catch (InvalidKeyException var0_4) {
            Log.v((String)"Mms", (String)("get pid exception " + (Object)((Object)var0_4)));
            return null;
        }
        catch (IllegalStateException var0_5) {
            Log.v((String)"Mms", (String)("encrypt pid exception " + (Object)((Object)var0_5)));
            return null;
        }
        catch (IllegalBlockSizeException var0_6) {
            Log.v((String)"Mms", (String)("encrypt pid exception " + (Object)((Object)var0_6)));
            return null;
        }
        catch (BadPaddingException var0_7) {
            Log.v((String)"Mms", (String)("encrypt pid exception " + (Object)((Object)var0_7)));
            return null;
        }
        catch (UnsupportedEncodingException var0_8) {
            Log.v((String)"Mms", (String)("encode pid exception " + (Object)((Object)var0_8)));
            return null;
        }
        catch (IllegalArgumentException var0_9) {
            Log.v((String)"Mms", (String)("encode key exception " + (Object)((Object)var0_9)));
            return null;
        }
    }

    public static Cursor getExpiredMxSms(Context context) {
        return context.getContentResolver().query(Telephony.Sms.CONTENT_URI, null, "(mx_status=16 or mx_status=1 or mx_status=196609) and (out_time>0 and out_time<" + (System.currentTimeMillis() - 300000) + ") and (" + "mx_id" + ">0)", null, "out_time");
    }

    private static String getFileNameFromPath(String string2, String string3) {
        int n = string2.lastIndexOf(47);
        if (n >= 0) {
            if (n < string2.length() - 1) {
                string3 = string2.substring(n + 1);
            }
            return string3;
        }
        return string2;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static String getFileShareMessage(String string2) {
        Pattern pattern;
        Object object;
        if (TextUtils.isEmpty((CharSequence)string2)) {
            return null;
        }
        if (FILE_SHARE_URL_PATTERNS == null) {
            FILE_SHARE_URL_PATTERNS = new ArrayList(2);
            object = FILE_SHARE_URL_PATTERN_STRINGS;
            int n = object.length;
            for (int i = 0; i < n; ++i) {
                pattern = Pattern.compile((String)object[i]);
                FILE_SHARE_URL_PATTERNS.add(pattern);
            }
        }
        if (FILE_SHARE_URL_PATTERNS.isEmpty()) return null;
        string2 = string2.substring(0, Math.min((int)string2.length(), (int)1000));
        object = FILE_SHARE_URL_PATTERNS.iterator();
        do {
            if (!object.hasNext()) return null;
        } while (!(pattern = ((Pattern)object.next()).matcher((CharSequence)string2)).find());
        return pattern.group(0);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private static String getForwardMessagesText(Context var0, boolean var1_1, List<MessageItem> var2_2) {
        var4_3 = new StringBuilder();
        var5_4 = var2_2.iterator();
        block0 : do {
            if (var5_4.hasNext() == false) return var4_3.toString();
            var2_2 = (MessageItem)var5_4.next();
            if (var4_3.length() > 0) {
                var4_3.append("\n\n");
            }
            if (var2_2.isSms()) {
                var2_2 = var1_1 != false ? MessageUtils.constructForwardBody(var0, var2_2.getContactName(), var2_2.getBody()) : var2_2.getBody();
                MessageUtils.appendWithSeparator(var4_3, (String)var2_2, '\n');
                continue;
            }
            MessageUtils.appendWithSeparator(var4_3, var2_2.getSubject(), '\n');
            var2_2 = var2_2.getSimplePduDoc();
            var3_5 = 0;
            do {
                if (var3_5 < var2_2.getPageSize()) ** break;
                continue block0;
                MessageUtils.appendWithSeparator(var4_3, var2_2.getPage(var3_5).getText(), '\n');
                ++var3_5;
            } while (true);
            break;
        } while (true);
    }

    private static String getHumanReadableSize(long l) {
        float f2 = l;
        if (l < 1024) {
            return String.valueOf((float)f2) + "B";
        }
        if (l < 0x100000) {
            return String.format((Locale)Locale.ENGLISH, (String)"%.2f", (Object[])new Object[]{Float.valueOf((float)(f2 /= 1024.0f))}) + "KB";
        }
        return String.format((Locale)Locale.ENGLISH, (String)"%.2f", (Object[])new Object[]{Float.valueOf((float)(f2 /= 1048576.0f))}) + "MB";
    }

    public static String getMessageDetails(Context context, Cursor cursor, MessageItem messageItem) {
        if (cursor == null) {
            return null;
        }
        if ("mms".equals((Object)cursor.getString(0))) {
            switch (cursor.getInt(20)) {
                default: {
                    Log.w((String)"Mms", (String)"No details could be retrieved.");
                    return "";
                }
                case 130: {
                    return MessageUtils.getNotificationIndDetails(context, cursor);
                }
                case 128: 
                case 132: 
            }
            return MessageUtils.getMultimediaMessageDetails(context, cursor, messageItem);
        }
        return MessageUtils.getTextMessageDetails(context, cursor, messageItem);
    }

    public static String getMessageStats(int n, int n2) {
        if (n > 1) {
            return "" + n2 + " (" + n + ")";
        }
        return String.valueOf((int)n2);
    }

    public static String getMessageStats(CharSequence charSequence) {
        charSequence = (CharSequence)SmsMessage.calculateLength((CharSequence)charSequence, (boolean)false);
        return MessageUtils.getMessageStats((int)charSequence[0], (int)charSequence[2]);
    }

    public static String getMessagesText(List<MessageItem> object) {
        StringBuilder stringBuilder = new StringBuilder();
        object = object.iterator();
        while (object.hasNext()) {
            Object object2 = (MessageItem)object.next();
            if (object2.isSms()) {
                MessageUtils.appendWithSeparator(stringBuilder, object2.getBody(), '\n');
                continue;
            }
            MessageUtils.appendWithSeparator(stringBuilder, object2.getSubject(), '\n');
            object2 = object2.getSimplePduDoc();
            for (int i = 0; i < object2.getPageSize(); ++i) {
                MessageUtils.appendWithSeparator(stringBuilder, object2.getPage(i).getText(), '\n');
            }
        }
        return stringBuilder.toString();
    }

    public static int getMmsBlockTypeByUri(Context context, Uri uri) {
        block4 : {
            if ((context = SqliteWrapper.query((Context)context, (ContentResolver)context.getContentResolver(), (Uri)uri, (String[])new String[]{"block_type"}, (String)null, (String[])null, (String)null)) != null) {
                if (!context.moveToFirst()) break block4;
                int n = context.getInt(0);
                return n;
            }
        }
        return 0;
        finally {
            context.close();
        }
    }

    static int getMmsDeliveryStatus(Context context, MessageItem messageItem) {
        block4 : {
            messageItem = Uri.withAppendedPath((Uri)Uri.withAppendedPath((Uri)Telephony.Mms.CONTENT_URI, (String)"report-status-ext"), (String)String.valueOf((long)messageItem.getMsgId()));
            if ((context = SqliteWrapper.query((Context)context, (ContentResolver)context.getContentResolver(), (Uri)messageItem, (String[])new String[]{"delivery_status"}, (String)null, (String[])null, (String)null)) != null) {
                if (!context.moveToFirst()) break block4;
                int n = context.getInt(0);
                return n;
            }
        }
        return 0;
        finally {
            context.close();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private static List<DeliveryReportItem> getMmsReportItems(Context context, MessageItem messageItem) {
        Object object = null;
        List<MmsReportRequest> list = MessageUtils.getMmsReportRequests(context, messageItem);
        if (list != null && list.size() != 0) {
            Map<String, MmsReportStatus> map = MessageUtils.getMmsReportStatus(context, messageItem);
            messageItem = new ArrayList();
            list = list.iterator();
            do {
                object = messageItem;
                if (!list.hasNext()) break;
                object = (MmsReportRequest)list.next();
                String string2 = context.getString(2131361974) + MessageUtils.getMmsReportStatusText(context, (MmsReportRequest)object, map);
                messageItem.add(new DeliveryReportItem(context.getString(2131361973) + object.getRecipient(), string2));
            } while (true);
        }
        return object;
    }

    private static List<MmsReportRequest> getMmsReportRequests(Context context, MessageItem messageItem) {
        block6 : {
            messageItem = Uri.withAppendedPath((Uri)Telephony.Mms.REPORT_REQUEST_URI, (String)String.valueOf((long)messageItem.getMsgId()));
            if ((context = SqliteWrapper.query((Context)context, (ContentResolver)context.getContentResolver(), (Uri)messageItem, (String[])MMS_REPORT_REQUEST_PROJECTION, (String)null, (String[])null, (String)null)) == null) {
                return null;
            }
            int n = context.getCount();
            if (n > 0) break block6;
            context.close();
            return null;
        }
        try {
            messageItem = new ArrayList();
            while (context.moveToNext()) {
                messageItem.add(new MmsReportRequest(context.getString(0), context.getInt(1), context.getInt(2)));
            }
        }
        catch (Throwable var1_2) {
            throw var1_2;
        }
        finally {
            context.close();
        }
        return messageItem;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    static Map<String, MmsReportStatus> getMmsReportStatus(Context object, MessageItem messageItem) {
        messageItem = Uri.withAppendedPath((Uri)Telephony.Mms.REPORT_STATUS_URI, (String)String.valueOf((long)messageItem.getMsgId()));
        messageItem = SqliteWrapper.query((Context)object, (ContentResolver)object.getContentResolver(), (Uri)messageItem, (String[])MMS_REPORT_STATUS_PROJECTION, (String)null, (String[])null, (String)null);
        if (messageItem == null) {
            return null;
        }
        try {
            HashMap hashMap = new HashMap();
            while (messageItem.moveToNext()) {
                object = messageItem.getString(0);
                object = Telephony.Mms.isEmailAddress((String)object) ? Telephony.Mms.extractAddrSpec((String)object) : PhoneNumberUtils.stripSeparators((String)object);
                hashMap.put(object, new MmsReportStatus(messageItem.getInt(1), messageItem.getInt(2)));
            }
            return hashMap;
        }
        finally {
            messageItem.close();
        }
    }

    /*
     * Exception decompiling
     */
    private static String getMmsReportStatusText(Context var0, MmsReportRequest var1_1, Map<String, MmsReportStatus> var2_2) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[CASE]], but top level block is 4[SWITCH]
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

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static String getMultimediaMessageDetails(Context var0, Cursor var1_1, MessageItem var2_3) {
        if (var1_1.getInt(20) == 130) {
            return MessageUtils.getNotificationIndDetails(var0, (Cursor)var1_1);
        }
        var7_4 = new StringBuilder();
        var8_5 = var0.getResources();
        var4_6 = var1_1.getLong(1);
        var6_7 = ContentUris.withAppendedId((Uri)Telephony.Mms.CONTENT_URI, (long)var4_6);
        try {
            var9_8 = (MultimediaMessagePdu)MiuiPduPersister.getPduPersister(var0).load((Uri)var6_7);
        }
        catch (MmsException var1_2) {
            Log.e((String)"Mms", (String)("Failed to load the message: " + var6_7), (Throwable)var1_2);
            return var0.getResources().getString(2131361940);
        }
        var7_4.append(var8_5.getString(2131361942));
        var7_4.append(var8_5.getString(2131361944));
        if (!(var9_8 instanceof RetrieveConf)) ** GOTO lbl22
        var6_7 = MessageUtils.extractEncStr(var0, ((RetrieveConf)var9_8).getFrom());
        var7_4.append('\n');
        var7_4.append(var8_5.getString(2131361946));
        if (TextUtils.isEmpty((CharSequence)var6_7)) ** GOTO lbl20
        ** GOTO lbl21
lbl20: // 1 sources:
        var6_7 = var8_5.getString(2131361890);
lbl21: // 2 sources:
        var7_4.append((String)var6_7);
lbl22: // 2 sources:
        if ((var6_7 = var9_8.getTo()) != null && var6_7.length > 0) {
            var7_4.append('\n');
            var7_4.append(var8_5.getString(2131361947));
            var7_4.append(EncodedStringValue.concat((EncodedStringValue[])var6_7));
        } else {
            Log.w((String)"Mms", (String)"recipient list is empty!");
        }
        if (var9_8 instanceof SendReq && (var6_7 = ((SendReq)var9_8).getBcc()) != null && var6_7.length > 0) {
            var7_4.append('\n');
            var7_4.append(var8_5.getString(2131361948));
            var7_4.append(EncodedStringValue.concat((EncodedStringValue[])var6_7));
        }
        if ((var3_9 = var1_1.getInt(21)) == 1 && var2_3.getDateSent() > 0) {
            var7_4.append("" + '\n' + var8_5.getString(2131361949));
            var7_4.append(MessageUtils.getPreciseTimeStamp(var2_3.getDateSent(), false));
        }
        var7_4.append('\n');
        if (var3_9 == 3) {
            var7_4.append(var8_5.getString(2131361951));
        } else if (var3_9 == 1) {
            var7_4.append(var8_5.getString(2131361950));
        } else {
            var7_4.append(var8_5.getString(2131361949));
        }
        var7_4.append(MessageUtils.getPreciseTimeStamp(var9_8.getDate() * 1000, false));
        var7_4.append('\n');
        var7_4.append(var8_5.getString(2131361952));
        var1_1 = var9_8.getSubject();
        if (var1_1 != null) {
            var1_1 = var1_1.getString();
            var3_9 = var1_1.length() + 0;
            var7_4.append((String)var1_1);
        } else {
            var3_9 = 0;
        }
        var7_4.append('\n');
        var7_4.append(var8_5.getString(2131361954));
        var7_4.append(MessageUtils.getPriorityDescription(var0, var9_8.getPriority()));
        if (var2_3.getMx2Type() != 3) {
            var1_1 = var2_3.getSimplePduDoc();
            var3_9 = var1_1 == null ? (var3_9 += var2_3.getMessageSize()) : (var3_9 += var1_1.getCompleteSize());
        } else {
            var4_6 = var3_9;
            var3_9 = (int)(var2_3.getMx2Attachments().get((int)0).datasize + var4_6);
        }
        var7_4.append('\n');
        var7_4.append(var8_5.getString(2131361953));
        var7_4.append((var3_9 - 1) / 1024 + 1);
        var7_4.append(" KB");
        if (var9_8 instanceof SendReq == false) return var7_4.toString();
        if (var2_3 == null) return var7_4.toString();
        if (var2_3.isMms() == false) return var7_4.toString();
        if (var2_3.getDeliveryStatus() == MessageItem.DeliveryStatus.NONE) {
            if (var2_3.isReadReport() == false) return var7_4.toString();
        }
        if ((var0 = MessageUtils.getMmsReportItems(var0, var2_3)) == null) return var7_4.toString();
        if (var0.size() <= 0) return var7_4.toString();
        var7_4.append('\n');
        var7_4.append(var8_5.getString(2131362020));
        var0 = var0.iterator();
        while (var0.hasNext() != false) {
            var1_1 = (DeliveryReportItem)var0.next();
            var7_4.append('\n');
            var7_4.append(var1_1.recipient);
            var7_4.append(", ");
            var7_4.append(var1_1.status);
        }
        return var7_4.toString();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static String getNotificationIndDetails(Context var0, Cursor var1_1) {
        var4_2 = new StringBuilder();
        var5_4 = var0.getResources();
        var2_5 = var1_1.getLong(1);
        var1_1 = ContentUris.withAppendedId((Uri)Telephony.Mms.CONTENT_URI, (long)var2_5);
        try {
            var6_6 = (NotificationInd)MiuiPduPersister.getPduPersister((Context)var0).load((Uri)var1_1);
        }
        catch (MmsException var4_3) {
            Log.e((String)"Mms", (String)("Failed to load the message: " + var1_1), (Throwable)var4_3);
            return var0.getResources().getString(2131361940);
        }
        var4_2.append(var5_4.getString(2131361942));
        var4_2.append(var5_4.getString(2131361945));
        var1_1 = MessageUtils.extractEncStr(var0, var6_6.getFrom());
        var4_2.append('\n');
        var4_2.append(var5_4.getString(2131361946));
        if (TextUtils.isEmpty((CharSequence)var1_1)) ** GOTO lbl17
        ** GOTO lbl18
lbl17: // 1 sources:
        var1_1 = var5_4.getString(2131361890);
lbl18: // 2 sources:
        var4_2.append((String)var1_1);
        var4_2.append('\n');
        var4_2.append(var5_4.getString(2131361811, new Object[]{MessageUtils.getPreciseTimeStamp(var6_6.getExpiry() * 1000, false)}));
        var4_2.append('\n');
        var4_2.append(var5_4.getString(2131361952));
        var1_1 = var6_6.getSubject();
        if (var1_1 != null) {
            var4_2.append(var1_1.getString());
        }
        var4_2.append('\n');
        var4_2.append(var5_4.getString(2131361958));
        var4_2.append(new String(var6_6.getMessageClass()));
        var4_2.append('\n');
        var4_2.append(var5_4.getString(2131361953));
        var4_2.append(String.valueOf((long)((var6_6.getMessageSize() + 1023) / 1024)));
        var4_2.append(var0.getString(2131361812));
        return var4_2.toString();
    }

    public static int getNotificationIndex(Context context) {
        return PreferenceManager.getDefaultSharedPreferences((Context)context).getInt("pref_key_float_noficatin_index", MessagingNotification.CANCEL_FLOAT_MSGID_DEFAULT);
    }

    public static String getNotificationRingToneRepeat(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("pref_key_ringtone_repeat", "0");
    }

    /*
     * Exception decompiling
     */
    public static List<String> getPhoneNumbers(Context var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Started 6 blocks at once
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.getStartingBlocks(Op04StructuredStatement.java:371)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:449)
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

    public static String getPhonePackageName() {
        return PACKAGE_NAME_PHONE;
    }

    public static String getPreciseTimeStamp(long l, boolean bl) {
        return MessageUtils.getPreciseTimeStamp(l, bl, 0);
    }

    private static String getPreciseTimeStamp(long l, boolean bl, int n) {
        int n2;
        n = n2 = n | 33676;
        if (bl) {
            Calendar calendar = new Calendar();
            int n3 = calendar.get(1);
            calendar.setTimeInMillis(l);
            n = n2;
            if (n3 == calendar.get(1)) {
                n = n2 & -513;
            }
        }
        return DateUtils.formatDateTime((long)l, (int)n);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String getPreciseTimeStamp(long l, boolean bl, boolean bl2) {
        int n;
        if (bl2) {
            n = 32;
            do {
                return MessageUtils.getPreciseTimeStamp(l, bl, n);
                break;
            } while (true);
        }
        n = 0;
        return MessageUtils.getPreciseTimeStamp(l, bl, n);
    }

    public static boolean getPrefNotificationBodyEnabled(Context context) {
        if (Settings.System.getInt((ContentResolver)context.getContentResolver(), (String)"pref_key_enable_notification_body", (int)1) == 1) {
            return true;
        }
        return false;
    }

    public static boolean getPrefNotificationBodyEnabledWithSecure(Context context) {
        if (MessageUtils.getPrefNotificationBodyEnabled(context) && !MessageUtils.isAccessControlledOrPrivacyModeEnabled(context)) {
            return true;
        }
        return false;
    }

    public static boolean getPrefNotificationEnabled(Context context) {
        if (Settings.System.getInt((ContentResolver)context.getContentResolver(), (String)"pref_key_enable_notification", (int)1) == 1) {
            return true;
        }
        return false;
    }

    public static boolean getPrefPrivateLockPatternVisible(Context context) {
        if (Settings.System.getInt((ContentResolver)context.getContentResolver(), (String)"private_sms_lock_pattern_visible_pattern", (int)1) == 1) {
            return true;
        }
        return false;
    }

    public static boolean getPrefPrivateNotificationEnabled(Context context) {
        if (Settings.System.getInt((ContentResolver)context.getContentResolver(), (String)"pref_key_enable_private_notification", (int)1) == 1) {
            return true;
        }
        return false;
    }

    private static String getPriorityDescription(Context context, int n) {
        context = context.getResources();
        switch (n) {
            default: {
                return context.getString(2131361956);
            }
            case 130: {
                return context.getString(2131361955);
            }
            case 128: 
        }
        return context.getString(2131361957);
    }

    public static String getRelativeTimeStamp(long l) {
        StringBuilder stringBuilder = new StringBuilder();
        DateUtils.formatRelativeTime((StringBuilder)stringBuilder, (long)l, (boolean)false);
        return stringBuilder.toString();
    }

    public static boolean getSendDeliverReportAllowed(Context context, long l) {
        boolean bl = false;
        if (Build.IS_CM_CUSTOMIZATION_TEST) {
            bl = PreferenceManager.getDefaultSharedPreferences((Context)context).getBoolean(MSimUtils.createKeyWithSimId(l, "pref_key_send_delivery_reports"), false);
        }
        return bl;
    }

    public static int getServiceCategory(Context object, String string2, String string3) {
        if (MessageUtils.isServiceNumber((Context)object, string2)) {
            Object var3_3 = null;
            YellowPagePhone yellowPagePhone = YellowPageUtils.getPhoneInfo((Context)object, (String)string2, (boolean)false);
            object = var3_3;
            if (yellowPagePhone != null) {
                object = var3_3;
                if (yellowPagePhone.isYellowPage()) {
                    object = yellowPagePhone.getYellowPageName();
                }
            }
            if (!TextUtils.isEmpty((CharSequence)object) && ExtraTelephony.BANK_CATEGORY_PATTERN.matcher((CharSequence)object).find()) {
                return 2;
            }
            if (string2.startsWith("106") && !TextUtils.isEmpty((CharSequence)string3) && ExtraTelephony.BANK_CATEGORY_SNIPPET_PATTERN.matcher((CharSequence)string3).find()) {
                return 2;
            }
            return 1;
        }
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static int getSlotId(List<Long> iterator) {
        int n;
        int n2 = MSimUtils.SLOT_ID_INVALID;
        HashSet hashSet = new HashSet();
        iterator = iterator.iterator();
        while (iterator.hasNext()) {
            n = MSimUtils.getSlotIdBySimInfoId((Long)iterator.next());
            if (!MSimUtils.isMSimSlotIdValid(n)) continue;
            hashSet.add((Object)n);
        }
        n = hashSet.size();
        if (n >= MSimUtils.getMultiSimCount()) {
            n2 = MSimUtils.SLOT_ID_ALL;
        } else if (n == 1) {
            n2 = (Integer)hashSet.iterator().next();
        }
        hashSet.clear();
        return n2;
    }

    public static String getSnippet(String string2, String string3, int n, int n2) {
        String string4 = string2.replaceAll("\\s+", " ");
        if (string4.length() <= n) {
            return string4;
        }
        int n3 = string4.indexOf(string3);
        int n4 = Math.max((int)n2, (int)(n - string3.length()));
        int n5 = Math.min((int)n3, (int)(n4 / 2));
        int n6 = n3 - n5;
        int n7 = Math.min((int)(n4 - n5), (int)(string4.length() - string3.length() - n3));
        int n8 = string3.length() + n3 + n7;
        string2 = "";
        int n9 = n6;
        if (n6 != 0) {
            n9 = n6 + 1;
            string2 = "\u2026";
        }
        string3 = "";
        n6 = n8;
        if (n8 != string4.length()) {
            n6 = n8 - 1;
            string3 = "\u2026";
        }
        if (n6 > string4.length()) {
            Log.e((String)"Mms", (String)("limit=" + n + " minR" + n2 + " pos=" + n3 + " remaining=" + n4 + " leftRemaining=" + n5 + " leftPos=" + n9 + " rightRemaining=" + n7 + " rightPos=" + n6));
        }
        return string2 + string4.substring(n9, n6) + string3;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String getStorageStatus(Context context) {
        int n;
        ContentResolver contentResolver = context.getContentResolver();
        Resources resources = context.getResources();
        StringBuilder stringBuilder = new StringBuilder();
        long l = 0;
        Cursor cursor = contentResolver.query(Telephony.Mms.CONTENT_URI, new String[]{"_id", "m_size"}, "m_type != 134 AND deleted = 0", null, null);
        if (cursor != null) {
            block10 : {
                block9 : {
                    try {
                        boolean bl;
                        n = cursor.getCount();
                        if (!cursor.moveToFirst()) break block9;
                        do {
                            l = (long)cursor.getInt(1) + l;
                        } while (bl = cursor.moveToNext());
                        break block10;
                    }
                    catch (Throwable var0_1) {
                        cursor.close();
                        throw var0_1;
                    }
                }
                l = 0;
            }
            cursor.close();
        } else {
            l = 0;
            n = 0;
        }
        stringBuilder.append(resources.getString(2131362374, new Object[]{n}));
        stringBuilder.append("\n");
        stringBuilder.append(resources.getString(2131362375) + MessageUtils.getHumanReadableSize(l));
        stringBuilder.append("\n");
        stringBuilder.append(resources.getString(2131362376) + MessageUtils.getHumanReadableSize(MessageUtils.getAttachmentSize(context)));
        stringBuilder.append("\n");
        context = contentResolver.query(Telephony.Sms.CONTENT_URI, new String[]{"_id"}, "deleted = 0", null, null);
        if (context != null) {
            n = context.getCount();
        }
        stringBuilder.append(resources.getString(2131362377, new Object[]{n}));
        stringBuilder.append("\n");
        context = new File("/data/data/com.android.providers.telephony/databases/mmssms.db");
        stringBuilder.append(resources.getString(2131362378) + MessageUtils.getHumanReadableSize(context.length()));
        stringBuilder.append("\n");
        l = MessageUtils.getAvailableSpace(Environment.getDataDirectory());
        stringBuilder.append(resources.getString(2131362379) + MessageUtils.getHumanReadableSize(l));
        return stringBuilder.toString();
        finally {
            context.close();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private static String getTextMessageDetails(Context object, Cursor cursor, MessageItem messageItem) {
        long l;
        Log.d((String)"Mms", (String)"getTextMessageDetails");
        StringBuilder stringBuilder = new StringBuilder();
        Resources resources = object.getResources();
        stringBuilder.append(resources.getString(2131361942));
        stringBuilder.append(resources.getString(2131361943));
        stringBuilder.append('\n');
        int n = cursor.getInt(8);
        if (Telephony.Sms.isOutgoingFolder((int)n)) {
            stringBuilder.append(resources.getString(2131361947));
        } else {
            stringBuilder.append(resources.getString(2131361946));
        }
        String string2 = cursor.getString(3);
        if (messageItem.isGroup()) {
            object = object.getString(2131362055, new Object[]{cursor.getInt(41)});
            stringBuilder.append(string2 + " ( " + (String)object + " )");
        } else {
            stringBuilder.append(string2);
        }
        if (n == 1 && (l = cursor.getLong(6)) > 0) {
            stringBuilder.append('\n');
            stringBuilder.append(resources.getString(2131361949));
            stringBuilder.append(MessageUtils.getPreciseTimeStamp(l, false));
        }
        stringBuilder.append('\n');
        if (n == 3) {
            stringBuilder.append(resources.getString(2131361951));
        } else if (n == 1) {
            stringBuilder.append(resources.getString(2131361950));
        } else {
            stringBuilder.append(resources.getString(2131361949));
        }
        stringBuilder.append(MessageUtils.getPreciseTimeStamp(cursor.getLong(5), false));
        if (n == 2 && (l = cursor.getLong(6)) > 0) {
            stringBuilder.append('\n');
            stringBuilder.append(resources.getString(2131361950));
            stringBuilder.append(MessageUtils.getPreciseTimeStamp(l, false));
        }
        if ((n = cursor.getInt(11)) != 0) {
            stringBuilder.append('\n').append(resources.getString(2131361959)).append(n);
        }
        return stringBuilder.toString();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static float getTextSize(float f2) {
        Context context = MmsApp.getApp().getApplicationContext();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences((Context)context);
        float f3 = context.getResources().getDimension(2131427389);
        float f4 = context.getResources().getDimension(2131427390);
        float f5 = sharedPreferences.getFloat("message_font_size", f2);
        if (f5 == 0.0f) {
            return 0.0f;
        }
        f2 = f3;
        if (f5 < f3) return f2;
        if (f5 <= f4) return f5;
        return f4;
    }

    public static Cursor getUncompletedMxSms(Context context) {
        return context.getContentResolver().query(Telephony.Sms.CONTENT_URI, null, "(mx_status=16 or mx_status=1) and (out_time>0) and (mx_id>0)", null, "out_time");
    }

    private static File getUniqueDestination(String string2, String string3) {
        File file = new File(string2 + "." + string3);
        int n = 2;
        while (file.exists()) {
            file = new File(string2 + "_" + n + "." + string3);
            ++n;
        }
        return file;
    }

    public static UriInfo getUriInfo(String string2) {
        UriInfo uriInfo = new UriInfo();
        uriInfo.uri = Uri.parse((String)string2);
        String string3 = "";
        int n = string2.indexOf(58);
        if (n != -1) {
            string3 = string2.substring(0, n);
        }
        if (string3.equalsIgnoreCase("tel")) {
            uriInfo.scheme = 0;
            string2 = string2.substring(n + 1);
            uriInfo.content = PhoneNumberUtils.formatNumber((String)string2);
            if (uriInfo.content == null) {
                uriInfo.content = string2;
            }
            uriInfo.contact = Contact.get(string2).load(false, true);
            if (uriInfo.contact.existsInDatabase()) {
                uriInfo.title = string2 + " (" + uriInfo.contact.getName() + ")";
                return uriInfo;
            }
            uriInfo.title = string2;
            return uriInfo;
        }
        if (string3.equalsIgnoreCase("mailto")) {
            uriInfo.scheme = 1;
            uriInfo.content = string2 = string2.substring(n + 1);
            uriInfo.title = string2;
            return uriInfo;
        }
        if (string3.equalsIgnoreCase("http") || string3.equalsIgnoreCase("https") || string3.equalsIgnoreCase("rtsp")) {
            uriInfo.scheme = 2;
            uriInfo.content = string2;
            uriInfo.title = string2;
            return uriInfo;
        }
        if (string3.equalsIgnoreCase("time")) {
            uriInfo.scheme = 4;
            uriInfo.content = string2 = string2.substring(n + 1);
            uriInfo.formatContent = string2;
            uriInfo.title = string2;
            return uriInfo;
        }
        uriInfo.scheme = 3;
        uriInfo.content = string2;
        uriInfo.title = string2;
        return uriInfo;
    }

    private static int getVideoCaptureDurationLimit() {
        CamcorderProfile camcorderProfile = CamcorderProfile.get((int)0);
        if (camcorderProfile == null) {
            return 0;
        }
        return camcorderProfile.duration;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean handleFileShareMessage(Context context, String string2, String string3) {
        if (TextUtils.isEmpty((CharSequence)string2) || TextUtils.isEmpty((CharSequence)MessageUtils.getFileShareMessage(string3))) {
            return false;
        }
        Intent intent = new Intent("cn.kuaipan.mishare.SHARE_MSG");
        intent.putExtra("SENDER_NUM", string2);
        intent.putExtra("MSG", string3);
        context.sendBroadcast(intent);
        return true;
    }

    public static void handleMxSmsFailed(Context context, long l, int n, boolean bl) {
        MessageUtils.handleMxSmsFailed(context, ContentUris.withAppendedId((Uri)MX_SMS_STATUS_URI, (long)l), n, bl);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void handleMxSmsFailed(Context context, Uri uri, int n, boolean bl) {
        if (PreferenceManager.getDefaultSharedPreferences((Context)context).getBoolean("pref_key_mx_auto_resend_sms", true) && !bl) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("mx_status", Integer.valueOf((int)0));
            contentValues.put("type", Integer.valueOf((int)6));
            if (MessageUtils.requireDeliveryStatusBySlotId(context, n)) {
                contentValues.put("status", Integer.valueOf((int)32));
            }
            context.getContentResolver().update(uri, contentValues, "(mx_status!=17 AND mx_status!=0)", null);
            MSimUtils.sendQueuedMessage(context, n);
            return;
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put("mx_status", Integer.valueOf((int)0));
            contentValues.put("type", Integer.valueOf((int)5));
            contentValues.put("read", Integer.valueOf((int)0));
            contentValues.put("error_code", Integer.valueOf((int)1));
            if (context.getContentResolver().update(uri, contentValues, "(mx_status!=17 AND mx_status!=0)", null) <= 0) return;
            {
                MessagingNotification.notifySendFailed(context, true);
                return;
            }
        }
    }

    public static void handleReadReport(Context context, Collection<Long> cursor, int n, final Runnable runnable) {
        String string2;
        block8 : {
            block9 : {
                string2 = "m_type = 132 AND read = 0 AND rr = 128";
                if (cursor != null) {
                    string2 = "m_type = 132 AND read = 0 AND rr = 128" + " AND thread_id IN (" + TextUtils.join((CharSequence)",", cursor) + ")";
                }
                if ((cursor = SqliteWrapper.query((Context)context, (ContentResolver)context.getContentResolver(), (Uri)Telephony.Mms.Inbox.CONTENT_URI, (String[])new String[]{"_id", "m_id"}, (String)string2, (String[])null, (String)null)) == null) {
                    return;
                }
                string2 = new HashMap();
                if (cursor.getCount() != 0) break block8;
                if (runnable == null) break block9;
                runnable.run();
            }
            return;
        }
        try {
            while (cursor.moveToNext()) {
                Uri uri = ContentUris.withAppendedId((Uri)Telephony.Mms.CONTENT_URI, (long)cursor.getLong(0));
                string2.put(cursor.getString(1), AddressUtils.getFrom(context, uri));
            }
        }
        catch (Throwable var0_1) {
            throw var0_1;
        }
        finally {
            cursor.close();
        }
        cursor.close();
        MessageUtils.confirmReadReportDialog(context, new DialogInterface.OnClickListener((Map)((Object)string2), context, n, runnable){
            final /* synthetic */ Runnable val$callback;
            final /* synthetic */ Context val$context;
            final /* synthetic */ Map val$map;
            final /* synthetic */ int val$status;

            public void onClick(DialogInterface dialogInterface, int n) {
                for (Map.Entry entry : this.val$map.entrySet()) {
                    MmsMessageSender.sendReadRec(this.val$context, (String)entry.getValue(), (String)entry.getKey(), this.val$status);
                }
                if (this.val$callback != null) {
                    this.val$callback.run();
                }
                dialogInterface.dismiss();
            }
        }, new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int n) {
                if (runnable != null) {
                    runnable.run();
                }
                dialogInterface.dismiss();
            }
        }, new DialogInterface.OnCancelListener(){

            public void onCancel(DialogInterface dialogInterface) {
                if (runnable != null) {
                    runnable.run();
                }
                dialogInterface.dismiss();
            }
        });
    }

    public static boolean hasBlockedFlag(Uri object) {
        if (!TextUtils.isEmpty((CharSequence)(object = object.getQueryParameter("blocked_flag"))) && object.equals((Object)"1")) {
            return true;
        }
        return false;
    }

    public static boolean hasVoipPackage(Context context) {
        try {
            context.getPackageManager().getPackageInfo("com.miui.voip", 128);
            Log.v((String)"Mms", (String)"has voip package");
            return true;
        }
        catch (PackageManager.NameNotFoundException var0_1) {
            return false;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public static Uri insertUniqueMessage(Context var0, ContentValues var1_4) {
        block36 : {
            block37 : {
                block35 : {
                    block34 : {
                        var11_5 = var0.getContentResolver();
                        var13_6 = var1_4.getAsString("address");
                        var12_7 = var1_4.getAsString("body");
                        var14_8 = var1_4.getAsString("b2c_numbers");
                        var10_9 = var1_4.getAsInteger("mx_status");
                        var4_10 = var10_9 != null ? var10_9.intValue() : 0;
                        var10_9 = new ArrayList(1);
                        var10_9.add((Object)var13_6);
                        var15_11 = Conversation.get(ContactList.getByNumbers(var10_9));
                        var10_9 = MessageUtils.INSERT_SMS_LOCK;
                        // MONITORENTER : var10_9
                        var15_11 = "thread_id=" + var15_11.getThreadId() + " and " + "date" + ">=" + (System.currentTimeMillis() - 300000);
                        var15_11 = var11_5.query(Telephony.Sms.Inbox.CONTENT_URI, null, (String)var15_11, null, "date desc");
                        var2_12 = 0;
                        var3_13 = 0;
                        if (var15_11 != null) {
                            var2_12 = var3_13;
                            if (!var15_11.moveToFirst()) break block34;
                            var2_12 = var15_11.getColumnIndex("body");
                            var5_14 = var15_11.getColumnIndex("mx_status");
                            do {
                                var16_17 = var15_11.getString(var2_12);
                                var6_15 = var15_11.getInt(var5_14);
                                var7_16 = var12_7.equals((Object)var16_17);
                                if (!var7_16 || var6_15 == var4_10) continue;
                                var2_12 = 1;
                                break block34;
                            } while (var7_16 = var15_11.moveToNext());
                            var2_12 = var3_13;
                        }
                    }
                    if (var2_12 != 0) {
                        Log.w((String)"Mms", (String)"duplicated message received");
                        // MONITOREXIT : var10_9
                        return null;
                    }
                    ** GOTO lbl40
                    finally {
                        var15_11.close();
                    }
lbl40: // 1 sources:
                    var3_13 = var2_12;
                    if (var4_10 != 0) {
                        var3_13 = var2_12;
                        if (!TextUtils.isEmpty((CharSequence)var14_8)) {
                            var14_8 = var14_8.split(",");
                            var15_11 = new StringBuilder();
                            for (String var16_17 : var14_8) {
                                if (var15_11.length() > 0) {
                                    var15_11.append(",");
                                }
                                var15_11.append("'").append(var16_17).append("'");
                            }
                            var8_18 = System.currentTimeMillis();
                            var14_8 = "date>=" + (var8_18 - 300000) + " AND " + "mx_status" + " = " + 0 + " AND " + "address" + " IN (" + var15_11.toString() + ")";
                            var14_8 = var11_5.query(Telephony.Sms.Inbox.CONTENT_URI, null, (String)var14_8, null, "date desc");
                            var3_13 = var2_12;
                            if (var14_8 != null) {
                                var3_13 = var2_12;
                                if (!var14_8.moveToFirst()) break block35;
                                var3_13 = var14_8.getColumnIndex("body");
                                do {
                                    if (!(var7_16 = var12_7.equals((Object)var14_8.getString(var3_13)))) continue;
                                    var3_13 = 1;
                                    break block35;
                                } while (var7_16 = var14_8.moveToNext());
                                var3_13 = var2_12;
                            }
                        }
                    }
                }
                if (var3_13 != 0) {
                    Log.w((String)"Mms", (String)"duplicated mx sp message received");
                    // MONITOREXIT : var10_9
                    return null;
                }
                ** GOTO lbl75
                finally {
                    var14_8.close();
                }
lbl75: // 1 sources:
                if (var4_10 != 0) ** GOTO lbl82
                var8_18 = System.currentTimeMillis();
                var13_6 = "date>= (" + var8_18 + " - CASE WHEN " + "b2c_ttl" + " < " + 300000 + " THEN " + 300000 + " ELSE " + "b2c_ttl" + " END)" + " AND " + "mx_status" + " <> " + 0 + " AND " + "b2c_numbers" + " IS NOT NULL" + " AND " + "b2c_numbers" + " LIKE \"%" + var13_6 + "%\" ";
                if ((var13_6 = var11_5.query(Telephony.Sms.Inbox.CONTENT_URI, null, var13_6, null, "date desc")) == null) ** GOTO lbl82
                try {
                    if (!var13_6.moveToFirst()) ** GOTO lbl90
                    ** GOTO lbl84
lbl82: // 2 sources:
                    var2_12 = var3_13;
                    break block36;
lbl84: // 1 sources:
                    var2_12 = var13_6.getColumnIndex("body");
                    do {
                        if (!(var7_16 = var12_7.equals((Object)var13_6.getString(var2_12)))) continue;
                        var2_12 = 1;
                        break block37;
                    } while (var7_16 = var13_6.moveToNext());
lbl90: // 2 sources:
                    var2_12 = var3_13;
                }
                catch (Throwable var0_3) {
                    var13_6.close();
                    throw var0_3;
                }
            }
            var13_6.close();
        }
        if (var2_12 != 0) {
            Log.w((String)"Mms", (String)"duplicated common sp message received");
            // MONITOREXIT : var10_9
            return null;
        }
        var0 = SqliteWrapper.insert((Context)var0, (ContentResolver)var11_5, (Uri)Telephony.Sms.Inbox.CONTENT_URI, (ContentValues)var1_4);
        // MONITOREXIT : var10_9
        return var0;
    }

    private static boolean isAccessControlActived(Context context) {
        if (1 == Settings.Secure.getInt((ContentResolver)context.getContentResolver(), (String)"access_control_lock_enabled", (int)0)) {
            return true;
        }
        return false;
    }

    private static boolean isAccessControlProtected(Context context, String string2) {
        SecurityManager securityManager = (SecurityManager)context.getSystemService("security");
        if (MessageUtils.isAccessControlActived(context) && securityManager.getApplicationAccessControlEnabled(string2)) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean isAccessControlled(Context context) {
        ChooseLockSettingsHelper chooseLockSettingsHelper = MmsApp.getChooseLockSettingsHelper();
        if (chooseLockSettingsHelper == null || !chooseLockSettingsHelper.isACLockEnabled() || !MessageUtils.isAccessControlProtected(context, "com.android.mms")) {
            return false;
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean isAccessControlledOrPrivacyModeEnabled(Context context) {
        ChooseLockSettingsHelper chooseLockSettingsHelper = MmsApp.getChooseLockSettingsHelper();
        if (chooseLockSettingsHelper == null || !MessageUtils.isAccessControlled(context) && !chooseLockSettingsHelper.isPrivacyModeEnabled()) {
            return false;
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static boolean isAlias(String string2) {
        if (!MmsConfig.isAliasEnabled()) {
            return false;
        }
        int n = string2 == null ? 0 : string2.length();
        if (n < MmsConfig.getAliasMinChars()) return false;
        if (n > MmsConfig.getAliasMaxChars()) return false;
        if (!Character.isLetter((char)string2.charAt(0))) return false;
        int n2 = 1;
        while (n2 < n) {
            char c2 = string2.charAt(n2);
            if (!Character.isLetterOrDigit((char)c2)) {
                if (c2 != '.') return false;
            }
            ++n2;
        }
        return true;
    }

    public static boolean isAllowNetworkAccess(Context context) {
        return PreferenceManager.getDefaultSharedPreferences((Context)context).getBoolean("pref_key_allow_network_access", false);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean isEmail(String string2) {
        boolean bl = true;
        if (TextUtils.isEmpty((CharSequence)string2)) {
            return false;
        }
        if (string2.length() < 3) return false;
        if (string2.indexOf(64) < 1) return false;
        return bl;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static boolean isIndiaServiceNumber(String string2) {
        boolean bl;
        boolean bl2 = bl = false;
        if (!Build.checkRegion((String)"IN")) return bl2;
        if (sIndiaSpPattern == null) {
            sIndiaSpPattern = Pattern.compile((String)"^[A-Za-z][A-Za-z][-]\\S{6}$");
        }
        if (bl2 = sIndiaSpPattern.matcher((CharSequence)string2).matches()) return bl2;
        bl2 = bl;
        if (string2.startsWith("+91")) return bl2;
        bl2 = bl;
        if (2 >= string2.length()) return bl2;
        bl2 = bl;
        if (string2.length() >= 8) return bl2;
        return true;
    }

    public static boolean isMessagingTemplateAllowed(Context context) {
        if (!MessageUtils.isUnderstandSupported()) {
            return false;
        }
        if (!Build.IS_INTERNATIONAL_BUILD) {
            return PreferenceManager.getDefaultSharedPreferences((Context)context).getBoolean("pref_key_show_template", true);
        }
        return PreferenceManager.getDefaultSharedPreferences((Context)context).getBoolean("pref_key_show_template_in", true);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean isMxDisabled(Context context) {
        boolean bl;
        boolean bl2 = bl = false;
        if (MxActivateService.isActivating()) return bl2;
        if (!MxActivateService.isActivateStatusInitialized()) {
            return bl;
        }
        bl2 = bl = MessageUtils.isMxDisabledBySlotId(context, 0);
        if (!bl) return bl2;
        bl2 = bl;
        if (!MSimUtils.isMSim()) return bl2;
        return MessageUtils.isMxDisabledBySlotId(context, 1);
    }

    public static boolean isMxDisabledBySlotId(Context context, int n) {
        if (MSimUtils.isSimInserted(n) && !MxActivateService.isMxEnabled(context, n)) {
            return true;
        }
        return false;
    }

    public static boolean isNetworkRecommendDateLong(Context context) {
        long l = PreferenceManager.getDefaultSharedPreferences((Context)context).getLong("last_network_recommend_date", 0);
        l = System.currentTimeMillis() - l;
        if (l > 1209600000 || l < 0) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static boolean isPhoneNumber(String string2) {
        if (TextUtils.isEmpty((CharSequence)string2)) {
            return false;
        }
        int n = 0;
        while (n < string2.length()) {
            if ("0123456789+. -#*()".indexOf((int)string2.charAt(n)) == -1) return false;
            ++n;
        }
        return true;
    }

    public static boolean isPrivacyModeEnabled(Context context) {
        context = MmsApp.getChooseLockSettingsHelper();
        if (context == null) {
            return false;
        }
        return context.isPrivacyModeEnabled();
    }

    public static boolean isSendingContactByVCard(SharedPreferences sharedPreferences) {
        boolean bl;
        boolean bl2 = bl = false;
        if (Build.IS_CM_CUSTOMIZATION) {
            bl2 = bl;
            if (sharedPreferences.getBoolean("pref_key_card_format_vcard", false)) {
                bl2 = true;
            }
        }
        return bl2;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean isServiceNumber(Context context, String string2) {
        if (!TextUtils.isEmpty((CharSequence)string2) && (MessageUtils.isIndiaServiceNumber(string2) || MiPubUtils.isServiceNumber((Context)context, (String)string2))) {
            return true;
        }
        return false;
    }

    public static boolean isSmartMessageNotReady(Context context) {
        if (!(Build.IS_INTERNATIONAL_BUILD || MiPubUtils.isYellowPageNetworkAllowed((Context)context) && MessageUtils.isAllowNetworkAccess(context) && !MessageUtils.isMxDisabled(context) || Build.IS_CM_CUSTOMIZATION)) {
            return true;
        }
        return false;
    }

    public static boolean isStorageAvailable(Context context) {
        if (Build.IS_CM_CUSTOMIZATION_TEST) {
            context = StorageManager.from((Context)context);
            File file = Environment.getDataDirectory();
            if (MessageUtils.getAvailableSpace(file) < context.getStorageFullBytes(file)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isUnderstandSupported() {
        if (!Build.IS_CM_CUSTOMIZATION_TEST && !Build.IS_INTERNATIONAL_BUILD || Build.checkRegion((String)"IN")) {
            return true;
        }
        return false;
    }

    public static boolean isValidMmsAddress(String string2) {
        if (MessageUtils.parseMmsAddress(string2) != null) {
            return true;
        }
        return false;
    }

    public static boolean isYpRecommendEnabled(Context context) {
        return PreferenceManager.getDefaultSharedPreferences((Context)context).getBoolean("yellowpage_recommend_show", false);
    }

    public static final boolean isZhCnLanguage() {
        return Locale.CHINA.equals((Object)Locale.getDefault());
    }

    public static void launchMessagePreference(Context context) {
        context.startActivity(new Intent(context, (Class)MessagingPreferenceActivity.class));
    }

    public static void loadFontSizeConfiguration(Context context) {
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void notifyIncomingSmsMmsToBracelet(Context context, String object) {
        if (context == null || TextUtils.isEmpty((CharSequence)object)) {
            Log.e((String)"Mms", (String)"notifyIncomingSmsMmsToBracelet: context or address is null");
            return;
        }
        if ((object = Contact.get((String)object).load(false, false)) == null) {
            Log.e((String)"Mms", (String)"notifyIncomingSmsMmsToBracelet: get contact is null");
            return;
        }
        String string2 = object.getNumber();
        String string3 = object.getName();
        Intent intent = new Intent("miui.sms.ACTION_ALARM");
        intent.putExtra("number", string2);
        if (string3 != null && string3.equals((Object)string2) || object.isYellowPageNumber()) {
            intent.putExtra("contact", "");
        } else {
            intent.putExtra("contact", string3);
        }
        intent.putExtra("tag", object.getTag());
        object = object.isYellowPageNumber() ? object.getName() : "";
        intent.putExtra("yellow_page", (String)object);
        context.sendBroadcast(intent, "miui.permission.BLE_IMMEDIATE_ALERT");
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static String parseMmsAddress(String string2) {
        if (Telephony.Mms.isEmailAddress((String)string2)) {
            return string2;
        }
        String string3 = MessageUtils.parsePhoneNumberForMms(string2);
        if (string3 != null) {
            return string3;
        }
        if (MessageUtils.isAlias(string2)) return string2;
        return null;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static String parsePhoneNumberForMms(String string2) {
        StringBuilder stringBuilder = new StringBuilder();
        int n = string2.length();
        int n2 = 0;
        while (n2 < n) {
            char c2 = string2.charAt(n2);
            if (c2 == '+' && stringBuilder.length() == 0) {
                stringBuilder.append(c2);
            } else if (Character.isDigit((char)c2)) {
                stringBuilder.append(c2);
            } else if (numericSugarMap.get((Object)Character.valueOf((char)c2)) == null) {
                return null;
            }
            ++n2;
        }
        return stringBuilder.toString();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static void performUriOperation(final Context var0, final UriInfo var1_1, final MessageItem var2_2) {
        var5_3 = new ContextMenuDialog(var0);
        switch (var1_1.scheme) {
            default: {
                var4_4 = "notes";
                var3_5 = false;
                ** GOTO lbl31
            }
            case 0: {
                var5_3.addMenuItem(2131362076, new Runnable(){

                    @Override
                    public void run() {
                        MessageUtils.startIntent(var0, new Intent("android.intent.action.CALL", var1_1.uri));
                    }
                });
                var5_3.addMenuItem(2131362077, new Runnable(){

                    @Override
                    public void run() {
                        MessageUtils.startIntent(var0, new Intent("android.intent.action.SENDTO", Uri.parse((String)("smsto:" + var1_1.content))));
                    }
                });
                var4_4 = "phone";
                var3_5 = true;
                ** GOTO lbl31
            }
            case 1: {
                var5_3.addMenuItem(2131362079, new Runnable(){

                    @Override
                    public void run() {
                        MessageUtils.startIntent(var0, new Intent("android.intent.action.VIEW", var1_1.uri));
                    }
                });
                var4_4 = "email";
                var3_5 = true;
                ** GOTO lbl31
            }
            case 2: {
                var4_4 = MessageUtils.INTENT_INSERT_WEBSITE;
                var5_3.addMenuItem(2131362078, new Runnable(){

                    @Override
                    public void run() {
                        if (Build.IS_CM_CUSTOMIZATION_TEST) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(var0);
                            builder.setMessage(2131362369);
                            builder.setPositiveButton(17039370, new DialogInterface.OnClickListener(){

                                public void onClick(DialogInterface dialogInterface, int n) {
                                    MessageUtils.startIntent(var0, new Intent("android.intent.action.VIEW", var1_1.uri));
                                }
                            });
                            builder.setNegativeButton(17039360, null);
                            builder.create().show();
                            return;
                        }
                        MessageUtils.startIntent(var0, new Intent("android.intent.action.VIEW", var1_1.uri));
                    }

                });
                if (!Build.IS_CM_CUSTOMIZATION) break;
                var5_3.addMenuItem(2131362250, new Runnable(){

                    @Override
                    public void run() {
                        Browser.saveBookmark((Context)var0, (String)null, (String)var1_1.uri.toString());
                    }
                });
                var3_5 = true;
                ** GOTO lbl31
            }
            case 4: {
                var5_3.addMenuItem(2131362086, new Runnable(){

                    /*
                     * Enabled aggressive block sorting
                     */
                    @Override
                    public void run() {
                        Object object;
                        long l = 0;
                        boolean bl = true;
                        List<DateParseUtils.EventDate> list = DateParseUtils.parseDate(var1_1.formatContent);
                        if (list == null) {
                            return;
                        }
                        if (list.size() == 1) {
                            object = list.get(0);
                            bl = object == null ? false : object.isAllDayEvent();
                            list = new Intent("android.intent.action.INSERT").setData(CalendarContract.Events.CONTENT_URI).putExtra("allDay", bl);
                            if (object != null) {
                                l = object.getDate().getTime();
                            }
                            object = list = list.putExtra("beginTime", l).putExtra("endTime", 0);
                            if (var2_2 != null) {
                                String string2 = var2_2.getBody();
                                object = list;
                                if (!TextUtils.isEmpty((CharSequence)string2)) {
                                    list.putExtra("event_title", string2);
                                    object = list;
                                }
                            }
                        } else if (list.size() == 2) {
                            object = list.get(0);
                            list = list.get(1);
                            if (object == null || list == null) {
                                bl = false;
                            } else if (!object.isAllDayEvent() || !list.isAllDayEvent()) {
                                bl = false;
                            }
                            Object object2 = new Intent("android.intent.action.INSERT").setData(CalendarContract.Events.CONTENT_URI).putExtra("allDay", bl);
                            long l2 = object == null ? 0 : object.getDate().getTime();
                            object = object2.putExtra("beginTime", l2);
                            if (list != null) {
                                l = list.getDate().getTime();
                            }
                            object = list = object.putExtra("endTime", l);
                            if (var2_2 != null) {
                                object2 = var2_2.getBody();
                                object = list;
                                if (!TextUtils.isEmpty((CharSequence)object2)) {
                                    list.putExtra("event_title", (String)object2);
                                    object = list;
                                }
                            }
                        } else {
                            object = list = new Intent("android.intent.action.INSERT").setData(CalendarContract.Events.CONTENT_URI).putExtra("allDay", true).putExtra("beginTime", 0).putExtra("endTime", 0);
                            if (var2_2 != null) {
                                String string3 = var2_2.getBody();
                                object = list;
                                if (!TextUtils.isEmpty((CharSequence)string3)) {
                                    list.putExtra("event_title", string3);
                                    object = list;
                                }
                            }
                        }
                        MessageUtils.startIntent(var0, (Intent)object);
                    }
                });
                var4_4 = "";
                var3_5 = false;
                ** GOTO lbl31
            }
        }
        var3_5 = true;
lbl31: // 6 sources:
        var5_3.addMenuItem(2131362080, new Runnable(){

            @Override
            public void run() {
                ((ClipboardManager)var0.getSystemService("clipboard")).setText((CharSequence)var1_1.content);
                Toast.makeText((Context)var0, (CharSequence)var0.getResources().getString(2131362081), (int)0).show();
            }
        });
        if (var1_1.contact != null && var1_1.contact.existsInDatabase()) {
            var5_3.addMenuItem(2131362084, new Runnable(){

                @Override
                public void run() {
                    MessageUtils.startIntent(var0, new Intent("android.intent.action.VIEW", var1_1.contact.getUri()));
                }
            });
        } else if (var3_5) {
            var5_3.addMenuItem(2131362082, new Runnable(){

                @Override
                public void run() {
                    Intent intent = new Intent("android.intent.action.INSERT", ContactsContract.Contacts.CONTENT_URI);
                    intent.putExtra(var4_4, var1_1.content);
                    MessageUtils.startIntent(var0, intent);
                }
            });
            var5_3.addMenuItem(2131362083, new Runnable(){

                @Override
                public void run() {
                    Intent intent = new Intent("android.intent.action.INSERT_OR_EDIT");
                    intent.setType("vnd.android.cursor.item/contact");
                    intent.putExtra(var4_4, var1_1.content);
                    MessageUtils.startIntent(var0, intent);
                }
            });
            if (var2_2 != null && (var2_2.isSms() && var2_2.getBoxId() == 1 || var2_2.isMms() && var2_2.getBoxId() == 1) && (var6_6 = Contact.get(var2_2.getAddress())).existsInDatabase() && !Contact.isSimContact(var6_6)) {
                var5_3.addMenuItem(var0.getString(2131362208, new Object[]{var2_2.getContactName()}), new Runnable(){

                    @Override
                    public void run() {
                        Intent intent = new Intent("android.intent.action.EDIT", var6_6.getUri());
                        intent.putExtra(var4_4, var1_1.content);
                        MessageUtils.startIntent(var0, intent);
                    }
                });
            }
        }
        var5_3.setTitle(var1_1.title);
        var5_3.show();
    }

    public static void processReceivedMsgReport(Context context, String string2, String string3, int n) {
        if (MessageUtils.checkPrivateMessage(context, string2)) {
            return;
        }
        VoiceReportUtils.voiceReport(context, string2, string3, n);
    }

    private static MmsReportStatus queryStatusByRecipient(Map<String, MmsReportStatus> map, String string2) {
        for (String string3 : map.keySet()) {
            if (!(Telephony.Mms.isEmailAddress((String)string2) ? TextUtils.equals((CharSequence)string3, (CharSequence)string2) : PhoneNumberUtils.compare((String)string3, (String)string2))) continue;
            return map.get(string3);
        }
        return null;
    }

    public static void recordSound(Context context, int n, long l) {
        if (context instanceof Activity) {
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.setType("audio/amr");
            intent.setClassName("com.android.soundrecorder", "com.android.soundrecorder.SoundRecorder");
            if (l > 0) {
                intent.putExtra("android.provider.MediaStore.extra.MAX_BYTES", l);
            }
            intent.putExtra("source_name", context.getString(2131361944));
            ((Activity)context).startActivityForResult(intent, n);
        }
    }

    public static void recordVideo(Context context, int n, long l) {
        if (context instanceof Activity) {
            int n2 = MessageUtils.getVideoCaptureDurationLimit();
            Intent intent = new Intent("android.media.action.VIDEO_CAPTURE");
            intent.putExtra("android.intent.extra.videoQuality", 0);
            intent.putExtra("android.intent.extra.sizeLimit", l);
            intent.putExtra("android.intent.extra.durationLimit", n2);
            ((Activity)context).startActivityForResult(intent, n);
        }
    }

    public static void requestInputMethod(Context context, TextView textView) {
        textView.requestFocus();
        ((InputMethodManager)context.getSystemService("input_method")).showSoftInput((View)textView, 0);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean requireDeliveryStatusBySimId(Context context, long l) {
        boolean bl = false;
        if (MessageUtils.getPrefNotificationEnabled(context)) {
            if (!Build.IS_CM_CUSTOMIZATION_TEST) return PreferenceManager.getDefaultSharedPreferences((Context)context).getBoolean("pref_key_delivery_reports", true);
            if (l >= 0) {
                String string2 = MSimUtils.createKeyWithSimId(l, "pref_key_delivery_reports");
                return PreferenceManager.getDefaultSharedPreferences((Context)context).getBoolean(string2, false);
            }
        }
        Log.v((String)"Mms", (String)"requireDeliveryStatusBySlotId with error simId");
        if (Build.IS_CM_CUSTOMIZATION_TEST) return bl;
        return true;
    }

    public static boolean requireDeliveryStatusBySlotId(Context context, int n) {
        if (MessageUtils.getPrefNotificationEnabled(context)) {
            if (Build.IS_CM_CUSTOMIZATION_TEST) {
                long l = MSimUtils.getSimIdBySlotId(n);
                if (l >= 0) {
                    String string2 = MSimUtils.createKeyWithSimId(l, "pref_key_delivery_reports");
                    return PreferenceManager.getDefaultSharedPreferences((Context)context).getBoolean(string2, false);
                }
            } else {
                return PreferenceManager.getDefaultSharedPreferences((Context)context).getBoolean("pref_key_delivery_reports", true);
            }
        }
        Log.v((String)"Mms", (String)"requireDeliveryStatusBySlotId with error slotId");
        return true;
    }

    public static void resendMms(Context context, WorkingMessage.MessageStatusListener messageStatusListener, Uri uri, boolean bl) {
        MessageUtils.resendMms(context, messageStatusListener, uri, bl, MxMessagePduHelper.getMessageMx2Type(context, ContentUris.parseId((Uri)uri)));
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void resendMms(Context context, WorkingMessage.MessageStatusListener messageStatusListener, Uri uri, boolean bl, int n) {
        long l = ContentUris.parseId((Uri)uri);
        n = n > 0 ? 1 : 0;
        MxMessagePduHelper.markMmsSendAsMx(context, uri, bl);
        ContentValues contentValues = new ContentValues(4);
        contentValues.put("err_type", Integer.valueOf((int)0));
        contentValues.put("err_code", Integer.valueOf((int)0));
        contentValues.put("retry_index", Integer.valueOf((int)0));
        contentValues.put("last_try", Integer.valueOf((int)0));
        SqliteWrapper.update((Context)context, (ContentResolver)context.getContentResolver(), (Uri)Telephony.MmsSms.PendingMessages.CONTENT_URI, (ContentValues)contentValues, (String)("msg_id=" + l), (String[])null);
        if (bl) {
            if (n == 0) {
                MxMmsTransactionService.startSendMms(context, uri);
                return;
            }
            Mx2MmsTransactionService.startSendMx2(context, uri);
            return;
        } else {
            if (n != 0 && !MxMessageUtils.convertMx2toMms(context, messageStatusListener, uri, true)) return;
            {
                if (MxActivateService.isMxEnabled(context) && PushSession.getInstance(context).getConnectedSimIndex() >= 0) {
                    MessageUtils.deleteMxMmsOnServer(context, uri);
                }
                messageStatusListener = new Intent(context, (Class)TransactionService.class);
                messageStatusListener.putExtra("uri", uri.toString());
                messageStatusListener.putExtra("type", 2);
                context.startService((Intent)messageStatusListener);
                return;
            }
        }
    }

    public static void resizeImageAsync(final Context context, final Uri uri, final Handler handler, final ResizeImageResultCallback resizeImageResultCallback, final boolean bl) {
        final Runnable runnable = new Runnable(){

            @Override
            public void run() {
                Toast.makeText((Context)context, (int)2131361846, (int)0).show();
            }
        };
        handler.postDelayed(runnable, 1000);
        new Thread(new Runnable(){

            @Override
            public void run() {
                int n;
                int n2;
                UriImage uriImage;
                block5 : {
                    int n3;
                    uriImage = new UriImage(context, uri);
                    int n4 = MmsConfig.getMaxImageWidth();
                    n = n3 = MmsConfig.getMaxImageHeight();
                    n2 = n4;
                    if (uriImage.getHeight() <= uriImage.getWidth()) break block5;
                    n2 = n3;
                    n = n4;
                }
                try {
                    uriImage = uriImage.getResizedImageAsPart(n2, n, MmsConfig.getMaxMessageSize() - 5000);
                    handler.post(new Runnable((PduPart)uriImage){
                        final /* synthetic */ PduPart val$part;

                        @Override
                        public void run() {
                            resizeImageResultCallback.onResizeResult(this.val$part, bl);
                        }
                    });
                    return;
                }
                finally {
                    handler.removeCallbacks(runnable);
                }
            }

        }).start();
    }

    public static int resolveDefaultCursorDrawableRes(Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(16843618, typedValue, true);
        return typedValue.resourceId;
    }

    /*
     * Exception decompiling
     */
    public static String saveMmsPartToDisk(Context var0, PduPart var1_5, String var2_7) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 2[TRYBLOCK]
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

    public static void selectAudio(Context context, int n) {
        if (context instanceof Activity) {
            Intent intent = new Intent("android.intent.action.RINGTONE_PICKER");
            intent.putExtra("android.intent.extra.ringtone.SHOW_DEFAULT", false);
            intent.putExtra("android.intent.extra.ringtone.SHOW_SILENT", false);
            intent.putExtra("android.intent.extra.ringtone.INCLUDE_DRM", false);
            intent.putExtra("android.intent.extra.ringtone.TITLE", context.getString(2131362011));
            ((Activity)context).startActivityForResult(intent, n);
        }
    }

    public static void selectImage(Context context, int n) {
        MessageUtils.selectMediaByType(context, n, "image/*", false);
    }

    private static void selectMediaByType(Context context, int n, String string2, boolean bl) {
        if (context instanceof Activity) {
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.setType(string2);
            if (bl) {
                intent.putExtra("android.intent.extra.LOCAL_ONLY", true);
            }
            ((Activity)context).startActivityForResult(intent, n);
        }
    }

    public static void selectVideo(Context context, int n) {
        MessageUtils.selectMediaByType(context, n, "video/*", true);
    }

    public static void setAllowNetworkAccess(Context context, boolean bl) {
        PreferenceManager.getDefaultSharedPreferences((Context)context).edit().putBoolean("pref_key_allow_network_access", bl).commit();
    }

    public static void setAttachmentAnimation(final ImageView imageView, final Drawable drawable2) {
        if (drawable2 instanceof AnimationDrawable) {
            imageView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener(){

                public boolean onPreDraw() {
                    ((AnimationDrawable)drawable2).start();
                    imageView.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver.OnPreDrawListener)this);
                    return true;
                }
            });
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void setAttachmentImage(ImageView imageView, Drawable drawable2, boolean bl) {
        if (drawable2 == null) {
            imageView.setImageResource(2130837759);
            return;
        } else {
            imageView.setImageDrawable(drawable2);
            if (!bl) return;
            {
                MessageUtils.setAttachmentAnimation(imageView, drawable2);
                return;
            }
        }
    }

    public static void setListViewTouchPadding(AbsListView absListView) {
    }

    public static void setMessageSendTime(Context context, long l, long l2, long l3) {
        if (l2 == l3) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", Long.valueOf((long)l3));
        SqliteWrapper.update((Context)context, (ContentResolver)context.getContentResolver(), (Uri)smsUri, (ContentValues)contentValues, (String)("timed > 0 AND thread_id = " + Long.toString((long)l) + " AND date = " + Long.toString((long)l2)), (String[])null);
        contentValues.put("date_full", Long.valueOf((long)l3));
        SqliteWrapper.update((Context)context, (ContentResolver)context.getContentResolver(), (Uri)mmsUri, (ContentValues)contentValues, (String)("timed > 0 AND thread_id = " + Long.toString((long)l) + " AND date*1000+date_ms_part = " + Long.toString((long)l2)), (String[])null);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void setMmsSendTime(Context context, Uri uri, long l, long l2) {
        ContentValues contentValues = new ContentValues();
        if (l > 0) {
            contentValues.put("timed", Long.valueOf((long)l2));
            contentValues.put("date_full", Long.valueOf((long)l));
        } else {
            contentValues.put("timed", Integer.valueOf((int)0));
        }
        SqliteWrapper.update((Context)context, (ContentResolver)context.getContentResolver(), (Uri)uri, (ContentValues)contentValues, (String)null, (String[])null);
    }

    public static void setNetworkRecommendDate(Context context, Long l) {
        PreferenceManager.getDefaultSharedPreferences((Context)context).edit().putLong("last_network_recommend_date", l.longValue()).commit();
    }

    public static void setNotificationIndex(Context context, int n) {
        PreferenceManager.getDefaultSharedPreferences((Context)context).edit().putInt("pref_key_float_noficatin_index", n).commit();
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void setSmsSendTime(Context context, Uri uri, long l, long l2) {
        ContentValues contentValues = new ContentValues();
        if (l > 0) {
            contentValues.put("timed", Long.valueOf((long)l2));
            contentValues.put("date", Long.valueOf((long)l));
        } else {
            contentValues.put("timed", Integer.valueOf((int)0));
        }
        SqliteWrapper.update((Context)context, (ContentResolver)context.getContentResolver(), (Uri)uri, (ContentValues)contentValues, (String)null, (String[])null);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void setTextSize(float f2) {
        Context context = MmsApp.getApp().getApplicationContext();
        float f3 = context.getResources().getDimension(2131427389);
        float f4 = context.getResources().getDimension(2131427390);
        if (f2 == 0.0f) {
            f3 = 0.0f;
        } else if (f2 >= f3) {
            f3 = f2;
            if (f2 > f4) {
                f3 = f4;
            }
        }
        context = PreferenceManager.getDefaultSharedPreferences((Context)context).edit();
        context.putFloat("message_font_size", f3);
        context.commit();
    }

    public static void setWindowLayoutParams(Activity activity) {
        activity = activity.getWindow().getAttributes();
        activity.gravity = 80;
        activity.width = -1;
        activity.height = -2;
        activity.flags |= 65536;
    }

    public static void setYpRecommendEnabled(Context context, boolean bl) {
        PreferenceManager.getDefaultSharedPreferences((Context)context).edit().putBoolean("yellowpage_recommend_show", bl).commit();
    }

    public static boolean shouldShowFestival(Context context) {
        if (!(Build.IS_INTERNATIONAL_BUILD || PreferenceManager.getDefaultSharedPreferences((Context)context).getBoolean("pref_key_hide_festival", true) && !MessageUtils.isZhCnLanguage())) {
            return true;
        }
        return false;
    }

    public static void showDiscardDraftConfirmDialog(Context context, DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(context).setIconAttribute(16843605).setTitle(2131361857).setMessage(2131361858).setPositiveButton(2131361891, onClickListener).setNegativeButton(2131361892, null).show();
    }

    public static void showErrorDialog(Context context, String string2, String string3) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(2130837774);
        builder.setTitle((CharSequence)string2);
        builder.setMessage((CharSequence)string3);
        builder.setPositiveButton(17039370, new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int n) {
                if (n == -1) {
                    dialogInterface.dismiss();
                }
            }
        });
        if (context instanceof Activity) {
            if (!((Activity)context).getWindow().isDestroyed()) {
                builder.show();
            }
            return;
        }
        builder.show();
    }

    public static void showTextWithHighlight(TextView textView, String string2, String arrstring, int n) {
        if (TextUtils.isEmpty((CharSequence)arrstring)) {
            textView.setText((CharSequence)string2);
            return;
        }
        String string3 = string2.toLowerCase();
        arrstring = arrstring.toLowerCase().split(" ");
        string2 = new SpannableString((CharSequence)string2);
        for (String string4 : arrstring) {
            if (TextUtils.isEmpty((CharSequence)string4)) continue;
            int n2 = - string4.length();
            while ((n2 = string3.indexOf(string4, n2 + string4.length())) != -1) {
                string2.setSpan((Object)new TextSizeAdjustSpan(textView.getContext(), n), n2, string4.length() + n2, 33);
            }
        }
        textView.setText((CharSequence)string2);
    }

    private static void startIntent(Context context, Intent intent) {
        intent.putExtra("com.android.browser.application_id", context.getPackageName());
        intent.setFlags(524288);
        try {
            context.startActivity(intent);
            return;
        }
        catch (ActivityNotFoundException var1_2) {
            Toast.makeText((Context)context, (CharSequence)context.getString(2131362137), (int)0).show();
            return;
        }
    }

    public static void startMmsPreviewService(Context context, long l) {
        if (context != null) {
            Intent intent = new Intent("android.provider.Telephony.MAKE_MMS_PREVIEW");
            intent.putExtra("_id", l);
            intent.setPackage("com.android.providers.telephony");
            context.startService(intent);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static int updateSmsStatus(Context context, long l, int n) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("mx_status", Integer.valueOf((int)n));
        if (n == 16) {
            contentValues.put("type", Integer.valueOf((int)2));
        } else if (n == 17) {
            contentValues.put("status", Integer.valueOf((int)0));
            contentValues.put("date_sent", Long.valueOf((long)System.currentTimeMillis()));
        }
        Uri uri = ContentUris.withAppendedId((Uri)Uri.parse((String)"content://sms/mx_status"), (long)l);
        return context.getContentResolver().update(uri, contentValues, "mx_status!=0 AND mx_status!=" + n + " AND " + "type" + "!=" + 1, null);
    }

    public static void viewMmsMessageAttachment(Context context, Uri uri, SlideshowModel slideshowModel) {
        MessageUtils.viewMmsMessageAttachment(context, uri, slideshowModel, 0);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static void viewMmsMessageAttachment(Context var0, Uri var1_2, SlideshowModel var2_3, int var3_4) {
        var4_5 = var2_3 == null ? false : var2_3.isSimple();
        if (var4_5) {
            MessageUtils.viewSimpleSlideshow(var0, var2_3);
            return;
        }
        if (var2_3 != null) {
            var5_6 = MiuiPduPersister.getPduPersister((Context)var0);
            var6_7 = var2_3.toPduBody();
            var5_6.updateParts(var1_2, var6_7);
            var2_3.sync(var6_7);
        }
        var2_3 = new Intent(var0, (Class)SlideshowActivity.class);
        var2_3.setData(var1_2);
        if (var3_4 > 0 && var0 instanceof Activity) {
            ((Activity)var0).startActivityForResult((Intent)var2_3, var3_4);
            return;
        }
        ** GOTO lbl20
        catch (MmsException var0_1) {
            Log.e((String)"Mms", (String)"Unable to save message for preview");
            return;
        }
lbl20: // 1 sources:
        var0.startActivity((Intent)var2_3);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void viewSimpleSlideshow(Context context, SlideshowModel slideshowModel) {
        void var1_4;
        if (!slideshowModel.isSimple()) {
            throw new IllegalArgumentException("viewSimpleSlideshow() called on a non-simple slideshow");
        }
        SlideModel slideModel = slideshowModel.get(0);
        Object var1_2 = null;
        if (slideModel.hasImage()) {
            ImageModel imageModel = slideModel.getImage();
        } else if (slideModel.hasVideo()) {
            VideoModel videoModel = slideModel.getVideo();
        }
        slideModel = new Intent("android.intent.action.VIEW");
        slideModel.addFlags(1);
        slideModel.putExtra("SingleItemOnly", true);
        String string2 = var1_4.getContentType();
        slideModel.setDataAndType(var1_4.getUri(), string2);
        context.startActivity((Intent)slideModel);
    }

    public static void writeHprofDataToFile() {
        String string2 = (Object)Environment.getExternalStorageDirectory() + "/mms_oom_hprof_data";
        try {
            Debug.dumpHprofData((String)string2);
            Log.i((String)"Mms", (String)("##### written hprof data to " + string2));
            return;
        }
        catch (IOException var0_1) {
            Log.e((String)"Mms", (String)("writeHprofDataToFile: caught " + (Object)((Object)var0_1)));
            return;
        }
    }

    private static final class MmsReportRequest {
        private final boolean mIsDeliveryReportRequsted;
        private final boolean mIsReadReportRequested;
        private final String mRecipient;

        /*
         * Enabled aggressive block sorting
         */
        public MmsReportRequest(String string2, int n, int n2) {
            boolean bl = true;
            this.mRecipient = string2;
            boolean bl2 = n == 128;
            this.mIsDeliveryReportRequsted = bl2;
            bl2 = n2 == 128 ? bl : false;
            this.mIsReadReportRequested = bl2;
        }

        public String getRecipient() {
            return this.mRecipient;
        }

        public boolean isReadReportRequested() {
            return this.mIsReadReportRequested;
        }
    }

    static final class MmsReportStatus {
        final int deliveryStatus;
        final int readStatus;

        public MmsReportStatus(int n, int n2) {
            this.deliveryStatus = n;
            this.readStatus = n2;
        }
    }

    static interface ResizeImageResultCallback {
        public void onResizeResult(PduPart var1, boolean var2);
    }

    public static class UriInfo {
        public Contact contact;
        public String content;
        public String formatContent;
        public int scheme;
        public String title;
        public Uri uri;
    }

}

