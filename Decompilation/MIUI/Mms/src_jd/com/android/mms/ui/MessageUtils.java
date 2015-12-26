package com.android.mms.ui;

import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.MiuiConfiguration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.database.Cursor;
import android.database.sqlite.SqliteWrapper;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.CamcorderProfile;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Debug;
import android.os.Environment;
import android.os.Handler;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.preference.PreferenceManager;
import android.provider.Browser;
import android.provider.CalendarContract.Events;
import android.provider.ContactsContract.Contacts;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.provider.Telephony.Mms;
import android.provider.Telephony.Mms.Inbox;
import android.provider.Telephony.MmsSms.PendingMessages;
import android.provider.Telephony.Sms;
import android.provider.Telephony.Sms.Inbox;
import android.security.ChooseLockSettingsHelper;
import android.telephony.SmsMessage;
import android.text.ClipboardManager;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
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
import com.android.mms.data.WorkingMessage.MessageStatusListener;
import com.android.mms.model.MediaModel;
import com.android.mms.model.SlideModel;
import com.android.mms.model.SlideshowModel;
import com.android.mms.model.TextModel;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.transaction.MmsMessageSender;
import com.android.mms.transaction.TransactionService;
import com.android.mms.util.AddressUtils;
import com.android.mms.util.DateParseUtils;
import com.android.mms.util.DateParseUtils.EventDate;
import com.android.mms.util.MSimUtils;
import com.android.mms.util.VoiceReportUtils;
import com.google.android.mms.MmsException;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.google.android.mms.pdu.MultimediaMessagePdu;
import com.google.android.mms.pdu.NotificationInd;
import com.google.android.mms.pdu.PduBody;
import com.google.android.mms.pdu.PduPart;
import com.google.android.mms.pdu.RetrieveConf;
import com.google.android.mms.pdu.SendReq;
import com.xiaomi.accountsdk.activate.ActivateManager;
import com.xiaomi.mms.data.MxIdCache;
import com.xiaomi.mms.data.MxIdCache.MxIdCacheItem;
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
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
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

public class MessageUtils
{
  private static List<Pattern> FILE_SHARE_URL_PATTERNS;
  public static String[] FILE_SHARE_URL_PATTERN_STRINGS = { "http://www.kuaipan.cn/file/[a-zA-Z\\d_-]+.htm$", "http://kc.cc/[a-zA-Z\\d]+" };
  private static final Object INSERT_SMS_LOCK;
  private static String INTENT_INSERT_WEBSITE;
  private static final String[] MMS_REPORT_REQUEST_PROJECTION = { "address", "d_rpt", "rr" };
  private static final String[] MMS_REPORT_STATUS_PROJECTION = { "address", "delivery_status", "read_status" };
  private static final Uri MX_SMS_STATUS_URI;
  private static final char[] NUMERIC_CHARS_SUGAR;
  private static final String PACKAGE_NAME_PHONE;
  private static final String SAVE_ATTACHMENT_DIRECTORY;
  public static final String SAVE_MMS_PART_TO_DISK_PATH;
  private static String[] SPECIAL_INTERNATIONAL_CODE;
  public static final Uri mmsUri;
  private static HashMap numericSugarMap;
  private static Pattern sIndiaSpPattern;
  private static final Map<String, String> sRecipientAddress = new ConcurrentHashMap(20);
  public static final Uri smsUri;
  
  static
  {
    NUMERIC_CHARS_SUGAR = new char[] { 45, 46, 44, 40, 41, 32, 47, 92, 42, 35, 43 };
    numericSugarMap = new HashMap(NUMERIC_CHARS_SUGAR.length);
    MX_SMS_STATUS_URI = Uri.parse("content://sms/mx_status");
    INSERT_SMS_LOCK = new Object();
    int i = 0;
    while (i < NUMERIC_CHARS_SUGAR.length)
    {
      numericSugarMap.put(Character.valueOf(NUMERIC_CHARS_SUGAR[i]), Character.valueOf(NUMERIC_CHARS_SUGAR[i]));
      i += 1;
    }
    SAVE_ATTACHMENT_DIRECTORY = Environment.DIRECTORY_DOWNLOADS;
    sIndiaSpPattern = null;
    SAVE_MMS_PART_TO_DISK_PATH = Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DOWNLOADS + "/";
    if (Build.VERSION.SDK_INT > 20) {}
    for (PACKAGE_NAME_PHONE = "com.android.server.telecom";; PACKAGE_NAME_PHONE = "com.android.phone")
    {
      INTENT_INSERT_WEBSITE = "website";
      smsUri = Uri.parse("content://sms/sent");
      mmsUri = Uri.parse("content://mms/sent");
      SPECIAL_INTERNATIONAL_CODE = new String[] { "60" };
      return;
    }
  }
  
  public static void activatePhone(Context paramContext, boolean paramBoolean)
  {
    int k = MSimUtils.getMultiSimCount();
    paramContext = ActivateManager.get(paramContext);
    int i = 0;
    if (i <= k - 1)
    {
      if (paramBoolean) {}
      for (int j = 17;; j = 1)
      {
        paramContext.startActivateSim(i, 2, null, false, null, j);
        i += 1;
        break;
      }
    }
    i = 0;
    while (i < k)
    {
      MxActivateService.setEnableAfterActivation(i, true);
      i += 1;
    }
  }
  
  public static boolean allowMenuMode()
  {
    return (!Build.IS_CM_CUSTOMIZATION_TEST) && (!Build.IS_INTERNATIONAL_BUILD) && (!Build.IS_CU_CUSTOMIZATION_TEST) && (!Build.IS_CTA_BUILD);
  }
  
  public static boolean allowPush()
  {
    return (!Build.IS_CM_CUSTOMIZATION_TEST) && (!Build.IS_INTERNATIONAL_BUILD);
  }
  
  public static int appendIntToCharArray(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    int j = 0;
    int m;
    int k;
    do
    {
      paramArrayOfChar[(paramInt1 + j)] = ((char)(paramInt2 % 10 + 48));
      m = paramInt2 / 10;
      k = j + 1;
      j = k;
      paramInt2 = m;
    } while (m > 0);
    j = paramInt1;
    paramInt2 = paramInt1 + k - 1;
    while (j < paramInt2)
    {
      int i = paramArrayOfChar[j];
      paramArrayOfChar[j] = paramArrayOfChar[paramInt2];
      paramArrayOfChar[paramInt2] = i;
      j += 1;
      paramInt2 -= 1;
    }
    return paramInt1 + k;
  }
  
  private static void appendWithSeparator(StringBuilder paramStringBuilder, String paramString, char paramChar)
  {
    if (TextUtils.isEmpty(paramString)) {
      return;
    }
    if (paramStringBuilder.length() == 0)
    {
      paramStringBuilder.append(paramString);
      return;
    }
    if (paramStringBuilder.charAt(paramStringBuilder.length() - 1) == paramChar)
    {
      paramStringBuilder.append(paramString);
      return;
    }
    paramStringBuilder.append(paramChar);
    paramStringBuilder.append(paramString);
  }
  
  public static byte[] charSequence2Byte(CharSequence paramCharSequence, String paramString)
  {
    byte[] arrayOfByte = null;
    if (!TextUtils.isEmpty(paramCharSequence)) {}
    try
    {
      arrayOfByte = paramCharSequence.toString().getBytes(paramString);
      return arrayOfByte;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      Log.e("Mms", "Unsupported encoding: " + paramString, localUnsupportedEncodingException);
    }
    return paramCharSequence.toString().getBytes();
  }
  
  public static boolean checkPrivateMessage(Context paramContext, long paramLong)
  {
    if (paramLong <= 0L) {}
    do
    {
      return false;
      paramContext = Conversation.get(paramLong);
    } while ((paramContext == null) || (!paramContext.isPrivate()));
    return true;
  }
  
  public static boolean checkPrivateMessage(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    do
    {
      return false;
      paramContext = new ArrayList(1);
      paramContext.add(paramString);
      paramContext = Conversation.get(ContactList.getByNumbers(paramContext));
    } while ((paramContext == null) || (!paramContext.isPrivate()));
    return true;
  }
  
  private static int compareSpecialIntlCode(String paramString)
  {
    int i = 0;
    while (i < SPECIAL_INTERNATIONAL_CODE.length)
    {
      String str = SPECIAL_INTERNATIONAL_CODE[i];
      if (paramString.startsWith(str)) {
        return str.length() - 1;
      }
      i += 1;
    }
    return -1;
  }
  
  private static void confirmReadReportDialog(Context paramContext, DialogInterface.OnClickListener paramOnClickListener1, DialogInterface.OnClickListener paramOnClickListener2, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    paramContext = new AlertDialog.Builder(paramContext);
    paramContext.setCancelable(true);
    paramContext.setTitle(2131361998);
    paramContext.setMessage(2131361999);
    paramContext.setPositiveButton(2131361891, paramOnClickListener1);
    paramContext.setNegativeButton(2131361892, paramOnClickListener2);
    paramContext.setOnCancelListener(paramOnCancelListener);
    paramContext.show();
  }
  
  private static String constructForwardBody(Context paramContext, String paramString1, String paramString2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramContext.getString(2131362238));
    localStringBuilder.append(paramString1);
    localStringBuilder.append("\n");
    localStringBuilder.append(paramString2);
    return localStringBuilder.toString();
  }
  
  public static void copyMessageTextToClipboard(Context paramContext, List<MessageItem> paramList)
  {
    ((ClipboardManager)paramContext.getSystemService("clipboard")).setText(getMessagesText(paramList));
    Toast.makeText(paramContext, 2131361821, 0).show();
  }
  
  private static void deleteMxMmsOnServer(Context paramContext, Uri paramUri)
  {
    for (;;)
    {
      try
      {
        Object localObject = SqliteWrapper.query(paramContext, paramContext.getContentResolver(), paramUri, new String[] { "sim_id", "mx_id" }, null, null, null);
        if (localObject != null) {}
        int i = -1;
      }
      finally
      {
        try
        {
          if (!((Cursor)localObject).moveToFirst()) {
            break label215;
          }
          i = ((Cursor)localObject).getInt(0);
          l = ((Cursor)localObject).getLong(1);
          if (localObject != null) {
            ((Cursor)localObject).close();
          }
          if ((l > 0L) && (i != -1))
          {
            paramUri = MiCloudMxMmsTransactionHandler.getAddr(paramUri, paramContext);
            if (!TextUtils.isEmpty(paramUri))
            {
              localObject = MxIdCache.getOrQuery(paramContext, paramUri);
              if (localObject != null)
              {
                i = MSimUtils.getSlotIdBySimInfoId(i);
                if (i > -1)
                {
                  paramUri = PushSession.getInstance(paramContext).getMyFullMid(i);
                  localObject = ((MxIdCache.MxIdCacheItem)localObject).getMId();
                  if ((!TextUtils.isEmpty((CharSequence)localObject)) && (!TextUtils.isEmpty(paramUri)))
                  {
                    MxMessageLogicHelper.sendDeleteCommandToServer(paramContext, paramUri, (String)localObject, String.valueOf(l));
                    Log.v("Mms", "sendDeleteCommandToServer mxId " + l);
                  }
                }
              }
            }
          }
          return;
        }
        finally {}
        paramContext = finally;
        localObject = null;
        if (localObject != null) {
          ((Cursor)localObject).close();
        }
      }
      label215:
      long l = -1L;
    }
  }
  
  public static String deleteSpecialIntlCode(String paramString)
  {
    String str2 = paramString;
    if (!Build.IS_INTERNATIONAL_BUILD) {
      return str2;
    }
    String str1 = str2;
    int i;
    if (!TextUtils.isEmpty(paramString))
    {
      i = paramString.charAt(0);
      if (i != 43) {
        break label66;
      }
      str1 = str2;
      if (paramString.length() > 1)
      {
        paramString = paramString.substring(1);
        i = compareSpecialIntlCode(paramString);
        str1 = str2;
        if (i >= 0) {
          str1 = paramString.substring(i);
        }
      }
    }
    for (;;)
    {
      return str1;
      label66:
      str1 = str2;
      if (i == 48)
      {
        str1 = str2;
        if (paramString.length() > 2)
        {
          str1 = str2;
          if (paramString.charAt(1) == '0')
          {
            paramString = paramString.substring(2);
            i = compareSpecialIntlCode(paramString);
            str1 = str2;
            if (i >= 0) {
              str1 = paramString.substring(i);
            }
          }
        }
      }
    }
  }
  
  public static void enableYellowPage(Context paramContext)
  {
    if ((!Build.IS_INTERNATIONAL_BUILD) && (!MiPubUtils.isYellowPageNetworkAllowed(paramContext))) {
      MiPubUtils.setYellowPageNetworkAllowed(paramContext);
    }
  }
  
  private static String extractEncStr(Context paramContext, EncodedStringValue paramEncodedStringValue)
  {
    if (paramEncodedStringValue != null) {
      return paramEncodedStringValue.getString();
    }
    return "";
  }
  
  public static String extractEncStrFromCursor(Cursor paramCursor, int paramInt1, int paramInt2)
  {
    String str = paramCursor.getString(paramInt1);
    paramInt1 = paramCursor.getInt(paramInt2);
    if (TextUtils.isEmpty(str)) {
      paramCursor = "";
    }
    do
    {
      return paramCursor;
      paramCursor = str;
    } while (paramInt1 == 0);
    return new EncodedStringValue(paramInt1, MiuiPduPersister.getBytes(str)).getString();
  }
  
  public static void forceSync(Context paramContext)
  {
    paramContext = AccountManager.get(paramContext).getAccountsByType("com.xiaomi");
    Bundle localBundle = new Bundle();
    localBundle.putBoolean("force", true);
    int i = 0;
    while (i < paramContext.length)
    {
      ContentResolver.requestSync(paramContext[i], "sms", localBundle);
      i += 1;
    }
  }
  
  public static void forwardMessage(Context paramContext, final List<MessageItem> paramList, boolean paramBoolean)
  {
    final Intent localIntent = new Intent(paramContext, NewMessageActivity.class);
    localIntent.putExtra("forwarded_message", true);
    localIntent.putExtra("orig_message_is_private", paramBoolean);
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
    if (Build.IS_CM_CUSTOMIZATION) {}
    for (paramBoolean = localSharedPreferences.getBoolean("pref_key_fwd_sender_info", false); paramList.size() > 1; paramBoolean = false)
    {
      localIntent.putExtra("sms_body", getForwardMessagesText(paramContext, paramBoolean, paramList));
      paramContext.startActivity(localIntent);
      return;
    }
    paramList = (MessageItem)paramList.get(0);
    if (paramList.isSms())
    {
      if (paramBoolean) {}
      for (paramList = constructForwardBody(paramContext, paramList.getContactName(), paramList.getBody());; paramList = paramList.getBody())
      {
        localIntent.putExtra("sms_body", paramList);
        paramContext.startActivity(localIntent);
        return;
      }
    }
    new AsyncTask()
    {
      protected Void doInBackground(Void... paramAnonymousVarArgs)
      {
        String str = val$context.getString(2131361856);
        paramAnonymousVarArgs = str;
        if (paramList.getSubject() != null) {
          paramAnonymousVarArgs = str + paramList.getSubject();
        }
        localIntent.putExtra("msg_uri", paramList.getMessageUri());
        localIntent.putExtra("subject", paramAnonymousVarArgs);
        localIntent.putExtra("mx2type", paramList.getMx2Type());
        val$context.startActivity(localIntent);
        return null;
      }
      
      protected void onPostExecute(Void paramAnonymousVoid)
      {
        val$dialog.dismiss();
      }
    }.execute(new Void[0]);
  }
  
  private static long getAttachmentSize(Context paramContext)
  {
    Object localObject1 = Uri.parse("content://mms/part/");
    paramContext = paramContext.getContentResolver().query((Uri)localObject1, new String[] { "_data" }, "mid IN (select _id from pdu where deleted = 0)", null, null);
    if (paramContext != null) {
      for (;;)
      {
        try
        {
          if (paramContext.moveToFirst())
          {
            long l2 = 0L;
            localObject1 = paramContext.getString(0);
            l1 = l2;
            if (localObject1 != null) {
              l1 = l2 + new File((String)localObject1).length();
            }
            boolean bool = paramContext.moveToNext();
            l2 = l1;
            if (bool) {
              continue;
            }
            return l1;
          }
        }
        finally
        {
          paramContext.close();
        }
        long l1 = 0L;
      }
    }
    return 0L;
  }
  
  private static long getAvailableSpace(File paramFile)
  {
    paramFile = new StatFs(paramFile.getAbsolutePath());
    return paramFile.getAvailableBlocks() * paramFile.getBlockSize();
  }
  
  public static String getContactsPackageName()
  {
    return "com.android.contacts";
  }
  
  public static boolean getConversationGroupByTime(SharedPreferences paramSharedPreferences, String paramString)
  {
    int j = 1;
    boolean bool1 = paramSharedPreferences.getBoolean("pref_key_time_style_auto_group", true);
    boolean bool2 = TextUtils.equals(paramString, MessageListItem.Style.bubble.toString());
    int i;
    if (MiuiConfiguration.getScaleMode() != 11)
    {
      i = 1;
      if (MiuiConfiguration.getScaleMode() == 15) {
        break label59;
      }
    }
    for (;;)
    {
      return bool1 & bool2 & i & j;
      i = 0;
      break;
      label59:
      j = 0;
    }
  }
  
  public static int getCurrentMmsSize(CharSequence paramCharSequence, SlideshowModel paramSlideshowModel)
  {
    int i;
    if (!TextUtils.isEmpty(paramCharSequence))
    {
      paramCharSequence = charSequence2Byte(paramCharSequence, "utf-8");
      if (paramCharSequence != null)
      {
        i = paramCharSequence.length;
        Log.v("Mms", "getMmsTotalSize text size = " + i);
      }
    }
    for (;;)
    {
      int j = i;
      if (paramSlideshowModel != null)
      {
        if (paramSlideshowModel.size() != 1) {
          break label123;
        }
        j = i + paramSlideshowModel.getCurrentMessageSize();
        paramCharSequence = paramSlideshowModel.get(0).getText();
        i = j;
        if (paramCharSequence == null) {}
      }
      label123:
      for (i = j - paramCharSequence.getMediaSize();; i = paramSlideshowModel.getCurrentMessageSize())
      {
        Log.v("Mms", "getMmsTotalSize size of all slides = " + i);
        j = i;
        return j;
      }
      i = 0;
      break;
      i = 0;
    }
  }
  
  public static String getDateType(SharedPreferences paramSharedPreferences)
  {
    return paramSharedPreferences.getString("pref_key_date_type", "0");
  }
  
  public static int getDeliverReportMode(SharedPreferences paramSharedPreferences, boolean paramBoolean)
  {
    if ((!Build.IS_CM_CUSTOMIZATION_TEST) && (paramSharedPreferences.getBoolean("pref_key_delivery_reports", true)) && (paramBoolean)) {}
    for (int j = 20;; j = 0)
    {
      long l = MSimUtils.getSimIdBySlotId(0);
      int i = j;
      if (l >= 0L)
      {
        k = j;
        if (Build.IS_CM_CUSTOMIZATION_TEST)
        {
          k = j;
          if (paramSharedPreferences.getBoolean(MSimUtils.createKeyWithSimId(l, "pref_key_delivery_reports"), false))
          {
            k = j;
            if (paramBoolean) {
              k = j | 0x10 | 0x4;
            }
          }
        }
        i = k;
        if (paramSharedPreferences.getBoolean(MSimUtils.createKeyWithSimId(l, "pref_key_mms_read_reports"), false)) {
          i = k | 0x1;
        }
      }
      int k = i;
      if (MSimUtils.isMSim())
      {
        l = MSimUtils.getSimIdBySlotId(1);
        k = i;
        if (l > 0L)
        {
          j = i;
          if (Build.IS_CM_CUSTOMIZATION_TEST)
          {
            j = i;
            if (paramSharedPreferences.getBoolean(MSimUtils.createKeyWithSimId(l, "pref_key_delivery_reports"), false))
            {
              j = i;
              if (paramBoolean) {
                j = i | 0x20 | 0x8;
              }
            }
          }
          k = j;
          if (paramSharedPreferences.getBoolean(MSimUtils.createKeyWithSimId(l, "pref_key_mms_read_reports"), false)) {
            k = j | 0x2;
          }
        }
      }
      return k;
    }
  }
  
  public static String getEncryptedPhone(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = new SecretKeySpec(Base64.decode(paramString1, 2), "AES");
      IvParameterSpec localIvParameterSpec = new IvParameterSpec(new byte[] { 100, 23, 84, 114, 72, 0, 4, 97, 73, 97, 2, 52, 84, 102, 18, 32 });
      Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      localCipher.init(1, paramString1, localIvParameterSpec);
      paramString1 = Base64.encodeToString(localCipher.doFinal(paramString2.getBytes("UTF-8")), 2);
      return paramString1;
    }
    catch (NoSuchAlgorithmException paramString1)
    {
      Log.v("Mms", "get cipher exception " + paramString1);
      return null;
    }
    catch (NoSuchPaddingException paramString1)
    {
      Log.v("Mms", "get cipher exception " + paramString1);
      return null;
    }
    catch (InvalidAlgorithmParameterException paramString1)
    {
      Log.v("Mms", "get pid exception " + paramString1);
      return null;
    }
    catch (InvalidKeyException paramString1)
    {
      Log.v("Mms", "get pid exception " + paramString1);
      return null;
    }
    catch (IllegalStateException paramString1)
    {
      Log.v("Mms", "encrypt pid exception " + paramString1);
      return null;
    }
    catch (IllegalBlockSizeException paramString1)
    {
      Log.v("Mms", "encrypt pid exception " + paramString1);
      return null;
    }
    catch (BadPaddingException paramString1)
    {
      Log.v("Mms", "encrypt pid exception " + paramString1);
      return null;
    }
    catch (UnsupportedEncodingException paramString1)
    {
      Log.v("Mms", "encode pid exception " + paramString1);
      return null;
    }
    catch (IllegalArgumentException paramString1)
    {
      Log.v("Mms", "encode key exception " + paramString1);
    }
    return null;
  }
  
  public static Cursor getExpiredMxSms(Context paramContext)
  {
    return paramContext.getContentResolver().query(Telephony.Sms.CONTENT_URI, null, "(mx_status=16 or mx_status=1 or mx_status=196609) and (out_time>0 and out_time<" + (System.currentTimeMillis() - 300000L) + ") and (" + "mx_id" + ">0)", null, "out_time");
  }
  
  private static String getFileNameFromPath(String paramString1, String paramString2)
  {
    int i = paramString1.lastIndexOf('/');
    if (i >= 0)
    {
      if (i < paramString1.length() - 1) {
        paramString2 = paramString1.substring(i + 1);
      }
      return paramString2;
    }
    return paramString1;
  }
  
  public static String getFileShareMessage(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    Object localObject2;
    do
    {
      Object localObject1;
      while (!((Iterator)localObject1).hasNext())
      {
        do
        {
          return null;
          if (FILE_SHARE_URL_PATTERNS == null)
          {
            FILE_SHARE_URL_PATTERNS = new ArrayList(2);
            localObject1 = FILE_SHARE_URL_PATTERN_STRINGS;
            int j = localObject1.length;
            int i = 0;
            while (i < j)
            {
              localObject2 = Pattern.compile(localObject1[i]);
              FILE_SHARE_URL_PATTERNS.add(localObject2);
              i += 1;
            }
          }
        } while (FILE_SHARE_URL_PATTERNS.isEmpty());
        paramString = paramString.substring(0, Math.min(paramString.length(), 1000));
        localObject1 = FILE_SHARE_URL_PATTERNS.iterator();
      }
      localObject2 = ((Pattern)((Iterator)localObject1).next()).matcher(paramString);
    } while (!((Matcher)localObject2).find());
    return ((Matcher)localObject2).group(0);
  }
  
  private static String getForwardMessagesText(Context paramContext, boolean paramBoolean, List<MessageItem> paramList)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      paramList = (MessageItem)localIterator.next();
      if (localStringBuilder.length() > 0) {
        localStringBuilder.append("\n\n");
      }
      if (paramList.isSms())
      {
        if (paramBoolean) {}
        for (paramList = constructForwardBody(paramContext, paramList.getContactName(), paramList.getBody());; paramList = paramList.getBody())
        {
          appendWithSeparator(localStringBuilder, paramList, '\n');
          break;
        }
      }
      appendWithSeparator(localStringBuilder, paramList.getSubject(), '\n');
      paramList = paramList.getSimplePduDoc();
      int i = 0;
      while (i < paramList.getPageSize())
      {
        appendWithSeparator(localStringBuilder, paramList.getPage(i).getText(), '\n');
        i += 1;
      }
    }
    return localStringBuilder.toString();
  }
  
  private static String getHumanReadableSize(long paramLong)
  {
    float f = (float)paramLong;
    if (paramLong < 1024L) {
      return String.valueOf(f) + "B";
    }
    if (paramLong < 1048576L)
    {
      f /= 1024.0F;
      return String.format(Locale.ENGLISH, "%.2f", new Object[] { Float.valueOf(f) }) + "KB";
    }
    f /= 1048576.0F;
    return String.format(Locale.ENGLISH, "%.2f", new Object[] { Float.valueOf(f) }) + "MB";
  }
  
  public static String getMessageDetails(Context paramContext, Cursor paramCursor, MessageItem paramMessageItem)
  {
    if (paramCursor == null) {
      return null;
    }
    if ("mms".equals(paramCursor.getString(0)))
    {
      switch (paramCursor.getInt(20))
      {
      case 129: 
      case 131: 
      default: 
        Log.w("Mms", "No details could be retrieved.");
        return "";
      case 130: 
        return getNotificationIndDetails(paramContext, paramCursor);
      }
      return getMultimediaMessageDetails(paramContext, paramCursor, paramMessageItem);
    }
    return getTextMessageDetails(paramContext, paramCursor, paramMessageItem);
  }
  
  public static String getMessageStats(int paramInt1, int paramInt2)
  {
    if (paramInt1 > 1) {
      return paramInt2 + " (" + paramInt1 + ")";
    }
    return String.valueOf(paramInt2);
  }
  
  public static String getMessageStats(CharSequence paramCharSequence)
  {
    paramCharSequence = SmsMessage.calculateLength(paramCharSequence, false);
    return getMessageStats(paramCharSequence[0], paramCharSequence[2]);
  }
  
  public static String getMessagesText(List<MessageItem> paramList)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      Object localObject = (MessageItem)paramList.next();
      if (((MessageItem)localObject).isSms())
      {
        appendWithSeparator(localStringBuilder, ((MessageItem)localObject).getBody(), '\n');
      }
      else
      {
        appendWithSeparator(localStringBuilder, ((MessageItem)localObject).getSubject(), '\n');
        localObject = ((MessageItem)localObject).getSimplePduDoc();
        int i = 0;
        while (i < ((SimplePduDoc)localObject).getPageSize())
        {
          appendWithSeparator(localStringBuilder, ((SimplePduDoc)localObject).getPage(i).getText(), '\n');
          i += 1;
        }
      }
    }
    return localStringBuilder.toString();
  }
  
  public static int getMmsBlockTypeByUri(Context paramContext, Uri paramUri)
  {
    paramContext = SqliteWrapper.query(paramContext, paramContext.getContentResolver(), paramUri, new String[] { "block_type" }, null, null, null);
    if (paramContext != null) {}
    try
    {
      if (paramContext.moveToFirst())
      {
        int i = paramContext.getInt(0);
        return i;
      }
      return 0;
    }
    finally
    {
      paramContext.close();
    }
  }
  
  static int getMmsDeliveryStatus(Context paramContext, MessageItem paramMessageItem)
  {
    paramMessageItem = Uri.withAppendedPath(Uri.withAppendedPath(Telephony.Mms.CONTENT_URI, "report-status-ext"), String.valueOf(paramMessageItem.getMsgId()));
    paramContext = SqliteWrapper.query(paramContext, paramContext.getContentResolver(), paramMessageItem, new String[] { "delivery_status" }, null, null, null);
    if (paramContext != null) {}
    try
    {
      if (paramContext.moveToFirst())
      {
        int i = paramContext.getInt(0);
        return i;
      }
      return 0;
    }
    finally
    {
      paramContext.close();
    }
  }
  
  private static List<DeliveryReportItem> getMmsReportItems(Context paramContext, MessageItem paramMessageItem)
  {
    Object localObject1 = null;
    Object localObject2 = getMmsReportRequests(paramContext, paramMessageItem);
    if (localObject2 == null) {}
    while (((List)localObject2).size() == 0) {
      return (List<DeliveryReportItem>)localObject1;
    }
    Map localMap = getMmsReportStatus(paramContext, paramMessageItem);
    paramMessageItem = new ArrayList();
    localObject2 = ((List)localObject2).iterator();
    for (;;)
    {
      localObject1 = paramMessageItem;
      if (!((Iterator)localObject2).hasNext()) {
        break;
      }
      localObject1 = (MmsReportRequest)((Iterator)localObject2).next();
      String str = paramContext.getString(2131361974) + getMmsReportStatusText(paramContext, (MmsReportRequest)localObject1, localMap);
      paramMessageItem.add(new DeliveryReportItem(paramContext.getString(2131361973) + ((MmsReportRequest)localObject1).getRecipient(), str));
    }
  }
  
  private static List<MmsReportRequest> getMmsReportRequests(Context paramContext, MessageItem paramMessageItem)
  {
    paramMessageItem = Uri.withAppendedPath(Telephony.Mms.REPORT_REQUEST_URI, String.valueOf(paramMessageItem.getMsgId()));
    paramContext = SqliteWrapper.query(paramContext, paramContext.getContentResolver(), paramMessageItem, MMS_REPORT_REQUEST_PROJECTION, null, null, null);
    if (paramContext == null) {
      return null;
    }
    try
    {
      int i = paramContext.getCount();
      if (i <= 0) {
        return null;
      }
      paramMessageItem = new ArrayList();
      while (paramContext.moveToNext()) {
        paramMessageItem.add(new MmsReportRequest(paramContext.getString(0), paramContext.getInt(1), paramContext.getInt(2)));
      }
    }
    finally
    {
      paramContext.close();
    }
    return paramMessageItem;
  }
  
  static Map<String, MmsReportStatus> getMmsReportStatus(Context paramContext, MessageItem paramMessageItem)
  {
    paramMessageItem = Uri.withAppendedPath(Telephony.Mms.REPORT_STATUS_URI, String.valueOf(paramMessageItem.getMsgId()));
    paramMessageItem = SqliteWrapper.query(paramContext, paramContext.getContentResolver(), paramMessageItem, MMS_REPORT_STATUS_PROJECTION, null, null, null);
    if (paramMessageItem == null) {
      return null;
    }
    HashMap localHashMap;
    for (;;)
    {
      try
      {
        localHashMap = new HashMap();
        if (!paramMessageItem.moveToNext()) {
          break;
        }
        paramContext = paramMessageItem.getString(0);
        if (Telephony.Mms.isEmailAddress(paramContext))
        {
          paramContext = Telephony.Mms.extractAddrSpec(paramContext);
          localHashMap.put(paramContext, new MmsReportStatus(paramMessageItem.getInt(1), paramMessageItem.getInt(2)));
        }
        else
        {
          paramContext = PhoneNumberUtils.stripSeparators(paramContext);
        }
      }
      finally
      {
        paramMessageItem.close();
      }
    }
    paramMessageItem.close();
    return localHashMap;
  }
  
  private static String getMmsReportStatusText(Context paramContext, MmsReportRequest paramMmsReportRequest, Map<String, MmsReportStatus> paramMap)
  {
    if (paramMap == null) {
      return paramContext.getString(2131361967);
    }
    String str = paramMmsReportRequest.getRecipient();
    if (Telephony.Mms.isEmailAddress(str)) {}
    for (str = Telephony.Mms.extractAddrSpec(str);; str = PhoneNumberUtils.stripSeparators(str))
    {
      paramMap = queryStatusByRecipient(paramMap, str);
      if (paramMap != null) {
        break;
      }
      return paramContext.getString(2131361967);
    }
    if ((paramMmsReportRequest.isReadReportRequested()) && (readStatus != 0)) {}
    switch (readStatus)
    {
    default: 
      switch (deliveryStatus)
      {
      default: 
        return paramContext.getString(2131361970);
      }
    case 128: 
      return paramContext.getString(2131361968);
    }
    return paramContext.getString(2131361971);
    return paramContext.getString(2131361967);
    return paramContext.getString(2131361969);
    return paramContext.getString(2131361972);
  }
  
  private static String getMultimediaMessageDetails(Context paramContext, Cursor paramCursor, MessageItem paramMessageItem)
  {
    if (paramCursor.getInt(20) == 130) {
      return getNotificationIndDetails(paramContext, paramCursor);
    }
    StringBuilder localStringBuilder = new StringBuilder();
    Resources localResources = paramContext.getResources();
    long l = paramCursor.getLong(1);
    Object localObject = ContentUris.withAppendedId(Telephony.Mms.CONTENT_URI, l);
    for (;;)
    {
      int i;
      try
      {
        MultimediaMessagePdu localMultimediaMessagePdu = (MultimediaMessagePdu)MiuiPduPersister.getPduPersister(paramContext).load((Uri)localObject);
        localStringBuilder.append(localResources.getString(2131361942));
        localStringBuilder.append(localResources.getString(2131361944));
        if ((localMultimediaMessagePdu instanceof RetrieveConf))
        {
          localObject = extractEncStr(paramContext, ((RetrieveConf)localMultimediaMessagePdu).getFrom());
          localStringBuilder.append('\n');
          localStringBuilder.append(localResources.getString(2131361946));
          if (!TextUtils.isEmpty((CharSequence)localObject)) {
            localStringBuilder.append((String)localObject);
          }
        }
        else
        {
          localObject = localMultimediaMessagePdu.getTo();
          if ((localObject == null) || (localObject.length <= 0)) {
            break label739;
          }
          localStringBuilder.append('\n');
          localStringBuilder.append(localResources.getString(2131361947));
          localStringBuilder.append(EncodedStringValue.concat((EncodedStringValue[])localObject));
          if ((localMultimediaMessagePdu instanceof SendReq))
          {
            localObject = ((SendReq)localMultimediaMessagePdu).getBcc();
            if ((localObject != null) && (localObject.length > 0))
            {
              localStringBuilder.append('\n');
              localStringBuilder.append(localResources.getString(2131361948));
              localStringBuilder.append(EncodedStringValue.concat((EncodedStringValue[])localObject));
            }
          }
          i = paramCursor.getInt(21);
          if ((i == 1) && (paramMessageItem.getDateSent() > 0L))
          {
            localStringBuilder.append('\n' + localResources.getString(2131361949));
            localStringBuilder.append(getPreciseTimeStamp(paramMessageItem.getDateSent(), false));
          }
          localStringBuilder.append('\n');
          if (i != 3) {
            break label752;
          }
          localStringBuilder.append(localResources.getString(2131361951));
          localStringBuilder.append(getPreciseTimeStamp(localMultimediaMessagePdu.getDate() * 1000L, false));
          localStringBuilder.append('\n');
          localStringBuilder.append(localResources.getString(2131361952));
          paramCursor = localMultimediaMessagePdu.getSubject();
          if (paramCursor == null) {
            break label835;
          }
          paramCursor = paramCursor.getString();
          i = paramCursor.length() + 0;
          localStringBuilder.append(paramCursor);
          localStringBuilder.append('\n');
          localStringBuilder.append(localResources.getString(2131361954));
          localStringBuilder.append(getPriorityDescription(paramContext, localMultimediaMessagePdu.getPriority()));
          if (paramMessageItem.getMx2Type() == 3) {
            break label801;
          }
          paramCursor = paramMessageItem.getSimplePduDoc();
          if (paramCursor != null) {
            break label791;
          }
          i += paramMessageItem.getMessageSize();
          localStringBuilder.append('\n');
          localStringBuilder.append(localResources.getString(2131361953));
          localStringBuilder.append((i - 1) / 1024 + 1);
          localStringBuilder.append(" KB");
          if ((!(localMultimediaMessagePdu instanceof SendReq)) || (paramMessageItem == null) || (!paramMessageItem.isMms()) || ((paramMessageItem.getDeliveryStatus() == MessageItem.DeliveryStatus.NONE) && (!paramMessageItem.isReadReport()))) {
            break label829;
          }
          paramContext = getMmsReportItems(paramContext, paramMessageItem);
          if ((paramContext == null) || (paramContext.size() <= 0)) {
            break label829;
          }
          localStringBuilder.append('\n');
          localStringBuilder.append(localResources.getString(2131362020));
          paramContext = paramContext.iterator();
          if (!paramContext.hasNext()) {
            break label829;
          }
          paramCursor = (DeliveryReportItem)paramContext.next();
          localStringBuilder.append('\n');
          localStringBuilder.append(recipient);
          localStringBuilder.append(", ");
          localStringBuilder.append(status);
          continue;
        }
        localObject = localResources.getString(2131361890);
      }
      catch (MmsException paramCursor)
      {
        Log.e("Mms", "Failed to load the message: " + localObject, paramCursor);
        return paramContext.getResources().getString(2131361940);
      }
      continue;
      label739:
      Log.w("Mms", "recipient list is empty!");
      continue;
      label752:
      if (i == 1)
      {
        localStringBuilder.append(localResources.getString(2131361950));
      }
      else
      {
        localStringBuilder.append(localResources.getString(2131361949));
        continue;
        label791:
        i += paramCursor.getCompleteSize();
        continue;
        label801:
        l = i;
        i = (int)(getMx2Attachmentsget0datasize + l);
        continue;
        label829:
        return localStringBuilder.toString();
        label835:
        i = 0;
      }
    }
  }
  
  private static String getNotificationIndDetails(Context paramContext, Cursor paramCursor)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Resources localResources = paramContext.getResources();
    long l = paramCursor.getLong(1);
    for (paramCursor = ContentUris.withAppendedId(Telephony.Mms.CONTENT_URI, l);; paramCursor = localResources.getString(2131361890)) {
      try
      {
        NotificationInd localNotificationInd = (NotificationInd)MiuiPduPersister.getPduPersister(paramContext).load(paramCursor);
        localStringBuilder.append(localResources.getString(2131361942));
        localStringBuilder.append(localResources.getString(2131361945));
        paramCursor = extractEncStr(paramContext, localNotificationInd.getFrom());
        localStringBuilder.append('\n');
        localStringBuilder.append(localResources.getString(2131361946));
        if (!TextUtils.isEmpty(paramCursor))
        {
          localStringBuilder.append(paramCursor);
          localStringBuilder.append('\n');
          localStringBuilder.append(localResources.getString(2131361811, new Object[] { getPreciseTimeStamp(localNotificationInd.getExpiry() * 1000L, false) }));
          localStringBuilder.append('\n');
          localStringBuilder.append(localResources.getString(2131361952));
          paramCursor = localNotificationInd.getSubject();
          if (paramCursor != null) {
            localStringBuilder.append(paramCursor.getString());
          }
          localStringBuilder.append('\n');
          localStringBuilder.append(localResources.getString(2131361958));
          localStringBuilder.append(new String(localNotificationInd.getMessageClass()));
          localStringBuilder.append('\n');
          localStringBuilder.append(localResources.getString(2131361953));
          localStringBuilder.append(String.valueOf((localNotificationInd.getMessageSize() + 1023L) / 1024L));
          localStringBuilder.append(paramContext.getString(2131361812));
          return localStringBuilder.toString();
        }
      }
      catch (MmsException localMmsException)
      {
        Log.e("Mms", "Failed to load the message: " + paramCursor, localMmsException);
        return paramContext.getResources().getString(2131361940);
      }
    }
  }
  
  public static int getNotificationIndex(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getInt("pref_key_float_noficatin_index", MessagingNotification.CANCEL_FLOAT_MSGID_DEFAULT);
  }
  
  public static String getNotificationRingToneRepeat(SharedPreferences paramSharedPreferences)
  {
    return paramSharedPreferences.getString("pref_key_ringtone_repeat", "0");
  }
  
  /* Error */
  public static List<String> getPhoneNumbers(Context paramContext)
  {
    // Byte code:
    //   0: new 322	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 1042	java/util/ArrayList:<init>	()V
    //   7: astore 6
    //   9: invokestatic 1294	miui/telephony/TelephonyManager:getDefault	()Lmiui/telephony/TelephonyManager;
    //   12: invokevirtual 1297	miui/telephony/TelephonyManager:getIccCardCount	()I
    //   15: istore_2
    //   16: iconst_0
    //   17: istore_1
    //   18: iload_1
    //   19: iload_2
    //   20: if_icmpge +249 -> 269
    //   23: aload_0
    //   24: invokestatic 239	com/xiaomi/accountsdk/activate/ActivateManager:get	(Landroid/content/Context;)Lcom/xiaomi/accountsdk/activate/ActivateManager;
    //   27: iload_1
    //   28: invokevirtual 1301	com/xiaomi/accountsdk/activate/ActivateManager:getActivateInfo	(I)Lcom/xiaomi/accountsdk/activate/ActivateManager$ActivateManagerFuture;
    //   31: astore 4
    //   33: aload 4
    //   35: astore_3
    //   36: aload 4
    //   38: ldc2_w 1302
    //   41: getstatic 1309	java/util/concurrent/TimeUnit:MILLISECONDS	Ljava/util/concurrent/TimeUnit;
    //   44: invokeinterface 1315 4 0
    //   49: checkcast 551	android/os/Bundle
    //   52: ldc_w 1317
    //   55: invokevirtual 1319	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   58: astore 5
    //   60: aload 5
    //   62: astore_3
    //   63: aload 4
    //   65: ifnull +15 -> 80
    //   68: aload 4
    //   70: iconst_1
    //   71: invokeinterface 1323 2 0
    //   76: pop
    //   77: aload 5
    //   79: astore_3
    //   80: aload_3
    //   81: astore 4
    //   83: aload_3
    //   84: invokestatic 277	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   87: ifeq +12 -> 99
    //   90: invokestatic 1294	miui/telephony/TelephonyManager:getDefault	()Lmiui/telephony/TelephonyManager;
    //   93: iload_1
    //   94: invokevirtual 1326	miui/telephony/TelephonyManager:getLine1NumberForSlot	(I)Ljava/lang/String;
    //   97: astore 4
    //   99: aload 4
    //   101: invokestatic 277	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   104: ifne +13 -> 117
    //   107: aload 6
    //   109: aload 4
    //   111: invokeinterface 882 2 0
    //   116: pop
    //   117: iload_1
    //   118: iconst_1
    //   119: iadd
    //   120: istore_1
    //   121: goto -103 -> 18
    //   124: astore 5
    //   126: aconst_null
    //   127: astore 4
    //   129: aload 4
    //   131: astore_3
    //   132: aload 5
    //   134: invokevirtual 1329	java/io/IOException:printStackTrace	()V
    //   137: aload 4
    //   139: ifnull +157 -> 296
    //   142: aload 4
    //   144: iconst_1
    //   145: invokeinterface 1323 2 0
    //   150: pop
    //   151: aconst_null
    //   152: astore_3
    //   153: goto -73 -> 80
    //   156: astore 5
    //   158: aconst_null
    //   159: astore 4
    //   161: aload 4
    //   163: astore_3
    //   164: aload 5
    //   166: invokevirtual 1330	com/xiaomi/accountsdk/activate/OperationCancelledException:printStackTrace	()V
    //   169: aload 4
    //   171: ifnull +125 -> 296
    //   174: aload 4
    //   176: iconst_1
    //   177: invokeinterface 1323 2 0
    //   182: pop
    //   183: aconst_null
    //   184: astore_3
    //   185: goto -105 -> 80
    //   188: astore 5
    //   190: aconst_null
    //   191: astore 4
    //   193: aload 4
    //   195: astore_3
    //   196: aload 5
    //   198: invokevirtual 1331	com/xiaomi/accountsdk/activate/CloudServiceFailureException:printStackTrace	()V
    //   201: aload 4
    //   203: ifnull +93 -> 296
    //   206: aload 4
    //   208: iconst_1
    //   209: invokeinterface 1323 2 0
    //   214: pop
    //   215: aconst_null
    //   216: astore_3
    //   217: goto -137 -> 80
    //   220: astore 5
    //   222: aconst_null
    //   223: astore 4
    //   225: aload 4
    //   227: astore_3
    //   228: aload 5
    //   230: invokevirtual 1332	java/lang/Exception:printStackTrace	()V
    //   233: aload 4
    //   235: ifnull +61 -> 296
    //   238: aload 4
    //   240: iconst_1
    //   241: invokeinterface 1323 2 0
    //   246: pop
    //   247: aconst_null
    //   248: astore_3
    //   249: goto -169 -> 80
    //   252: astore_0
    //   253: aconst_null
    //   254: astore_3
    //   255: aload_3
    //   256: ifnull +11 -> 267
    //   259: aload_3
    //   260: iconst_1
    //   261: invokeinterface 1323 2 0
    //   266: pop
    //   267: aload_0
    //   268: athrow
    //   269: aload 6
    //   271: areturn
    //   272: astore_0
    //   273: goto -18 -> 255
    //   276: astore 5
    //   278: goto -53 -> 225
    //   281: astore 5
    //   283: goto -90 -> 193
    //   286: astore 5
    //   288: goto -127 -> 161
    //   291: astore 5
    //   293: goto -164 -> 129
    //   296: aconst_null
    //   297: astore_3
    //   298: goto -218 -> 80
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	301	0	paramContext	Context
    //   17	104	1	i	int
    //   15	6	2	j	int
    //   35	263	3	localObject1	Object
    //   31	208	4	localObject2	Object
    //   58	20	5	str	String
    //   124	9	5	localIOException1	IOException
    //   156	9	5	localOperationCancelledException1	com.xiaomi.accountsdk.activate.OperationCancelledException
    //   188	9	5	localCloudServiceFailureException1	com.xiaomi.accountsdk.activate.CloudServiceFailureException
    //   220	9	5	localException1	Exception
    //   276	1	5	localException2	Exception
    //   281	1	5	localCloudServiceFailureException2	com.xiaomi.accountsdk.activate.CloudServiceFailureException
    //   286	1	5	localOperationCancelledException2	com.xiaomi.accountsdk.activate.OperationCancelledException
    //   291	1	5	localIOException2	IOException
    //   7	263	6	localArrayList	ArrayList
    // Exception table:
    //   from	to	target	type
    //   23	33	124	java/io/IOException
    //   23	33	156	com/xiaomi/accountsdk/activate/OperationCancelledException
    //   23	33	188	com/xiaomi/accountsdk/activate/CloudServiceFailureException
    //   23	33	220	java/lang/Exception
    //   23	33	252	finally
    //   36	60	272	finally
    //   132	137	272	finally
    //   164	169	272	finally
    //   196	201	272	finally
    //   228	233	272	finally
    //   36	60	276	java/lang/Exception
    //   36	60	281	com/xiaomi/accountsdk/activate/CloudServiceFailureException
    //   36	60	286	com/xiaomi/accountsdk/activate/OperationCancelledException
    //   36	60	291	java/io/IOException
  }
  
  public static String getPhonePackageName()
  {
    return PACKAGE_NAME_PHONE;
  }
  
  public static String getPreciseTimeStamp(long paramLong, boolean paramBoolean)
  {
    return getPreciseTimeStamp(paramLong, paramBoolean, 0);
  }
  
  private static String getPreciseTimeStamp(long paramLong, boolean paramBoolean, int paramInt)
  {
    int i = paramInt | 0x838C;
    paramInt = i;
    if (paramBoolean)
    {
      Calendar localCalendar = new Calendar();
      int j = localCalendar.get(1);
      localCalendar.setTimeInMillis(paramLong);
      paramInt = i;
      if (j == localCalendar.get(1)) {
        paramInt = i & 0xFDFF;
      }
    }
    return DateUtils.formatDateTime(paramLong, paramInt);
  }
  
  public static String getPreciseTimeStamp(long paramLong, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramBoolean2) {}
    for (int i = 32;; i = 0) {
      return getPreciseTimeStamp(paramLong, paramBoolean1, i);
    }
  }
  
  public static boolean getPrefNotificationBodyEnabled(Context paramContext)
  {
    return Settings.System.getInt(paramContext.getContentResolver(), "pref_key_enable_notification_body", 1) == 1;
  }
  
  public static boolean getPrefNotificationBodyEnabledWithSecure(Context paramContext)
  {
    return (getPrefNotificationBodyEnabled(paramContext)) && (!isAccessControlledOrPrivacyModeEnabled(paramContext));
  }
  
  public static boolean getPrefNotificationEnabled(Context paramContext)
  {
    return Settings.System.getInt(paramContext.getContentResolver(), "pref_key_enable_notification", 1) == 1;
  }
  
  public static boolean getPrefPrivateLockPatternVisible(Context paramContext)
  {
    return Settings.System.getInt(paramContext.getContentResolver(), "private_sms_lock_pattern_visible_pattern", 1) == 1;
  }
  
  public static boolean getPrefPrivateNotificationEnabled(Context paramContext)
  {
    return Settings.System.getInt(paramContext.getContentResolver(), "pref_key_enable_private_notification", 1) == 1;
  }
  
  private static String getPriorityDescription(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getResources();
    switch (paramInt)
    {
    case 129: 
    default: 
      return paramContext.getString(2131361956);
    case 130: 
      return paramContext.getString(2131361955);
    }
    return paramContext.getString(2131361957);
  }
  
  public static String getRelativeTimeStamp(long paramLong)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    DateUtils.formatRelativeTime(localStringBuilder, paramLong, false);
    return localStringBuilder.toString();
  }
  
  public static boolean getSendDeliverReportAllowed(Context paramContext, long paramLong)
  {
    boolean bool = false;
    if (Build.IS_CM_CUSTOMIZATION_TEST) {
      bool = PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean(MSimUtils.createKeyWithSimId(paramLong, "pref_key_send_delivery_reports"), false);
    }
    return bool;
  }
  
  public static int getServiceCategory(Context paramContext, String paramString1, String paramString2)
  {
    if (isServiceNumber(paramContext, paramString1))
    {
      Object localObject = null;
      YellowPagePhone localYellowPagePhone = YellowPageUtils.getPhoneInfo(paramContext, paramString1, false);
      paramContext = (Context)localObject;
      if (localYellowPagePhone != null)
      {
        paramContext = (Context)localObject;
        if (localYellowPagePhone.isYellowPage()) {
          paramContext = localYellowPagePhone.getYellowPageName();
        }
      }
      if ((!TextUtils.isEmpty(paramContext)) && (ExtraTelephony.BANK_CATEGORY_PATTERN.matcher(paramContext).find())) {
        return 2;
      }
      if ((paramString1.startsWith("106")) && (!TextUtils.isEmpty(paramString2)) && (ExtraTelephony.BANK_CATEGORY_SNIPPET_PATTERN.matcher(paramString2).find())) {
        return 2;
      }
      return 1;
    }
    return 0;
  }
  
  public static int getSlotId(List<Long> paramList)
  {
    int i = MSimUtils.SLOT_ID_INVALID;
    HashSet localHashSet = new HashSet();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      j = MSimUtils.getSlotIdBySimInfoId(((Long)paramList.next()).longValue());
      if (MSimUtils.isMSimSlotIdValid(j)) {
        localHashSet.add(Integer.valueOf(j));
      }
    }
    int j = localHashSet.size();
    if (j >= MSimUtils.getMultiSimCount()) {
      i = MSimUtils.SLOT_ID_ALL;
    }
    for (;;)
    {
      localHashSet.clear();
      return i;
      if (j == 1) {
        i = ((Integer)localHashSet.iterator().next()).intValue();
      }
    }
  }
  
  public static String getSnippet(String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    String str = paramString1.replaceAll("\\s+", " ");
    if (str.length() <= paramInt1) {
      return str;
    }
    int m = str.indexOf(paramString2);
    int n = Math.max(paramInt2, paramInt1 - paramString2.length());
    int i1 = Math.min(m, n / 2);
    int j = m - i1;
    int i2 = Math.min(n - i1, str.length() - paramString2.length() - m);
    int k = paramString2.length() + m + i2;
    paramString1 = "";
    int i = j;
    if (j != 0)
    {
      i = j + 1;
      paramString1 = "…";
    }
    paramString2 = "";
    j = k;
    if (k != str.length())
    {
      j = k - 1;
      paramString2 = "…";
    }
    if (j > str.length()) {
      Log.e("Mms", "limit=" + paramInt1 + " minR" + paramInt2 + " pos=" + m + " remaining=" + n + " leftRemaining=" + i1 + " leftPos=" + i + " rightRemaining=" + i2 + " rightPos=" + j);
    }
    return paramString1 + str.substring(i, j) + paramString2;
  }
  
  /* Error */
  public static String getStorageStatus(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 426	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   4: astore 8
    //   6: aload_0
    //   7: invokevirtual 1115	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   10: astore 5
    //   12: new 171	java/lang/StringBuilder
    //   15: dup
    //   16: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   19: astore 6
    //   21: lconst_0
    //   22: lstore_2
    //   23: aload 8
    //   25: getstatic 1023	android/provider/Telephony$Mms:CONTENT_URI	Landroid/net/Uri;
    //   28: iconst_2
    //   29: anewarray 90	java/lang/String
    //   32: dup
    //   33: iconst_0
    //   34: ldc_w 1491
    //   37: aastore
    //   38: dup
    //   39: iconst_1
    //   40: ldc_w 1493
    //   43: aastore
    //   44: ldc_w 1495
    //   47: aconst_null
    //   48: aconst_null
    //   49: invokevirtual 661	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   52: astore 7
    //   54: aload 7
    //   56: ifnull +385 -> 441
    //   59: aload 7
    //   61: invokeinterface 1063 1 0
    //   66: istore_1
    //   67: aload 7
    //   69: invokeinterface 441 1 0
    //   74: ifeq +362 -> 436
    //   77: aload 7
    //   79: iconst_1
    //   80: invokeinterface 445 2 0
    //   85: i2l
    //   86: lload_2
    //   87: ladd
    //   88: lstore_2
    //   89: aload 7
    //   91: invokeinterface 672 1 0
    //   96: istore 4
    //   98: iload 4
    //   100: ifne +333 -> 433
    //   103: aload 7
    //   105: invokeinterface 452 1 0
    //   110: aload 6
    //   112: aload 5
    //   114: ldc_w 1496
    //   117: iconst_1
    //   118: anewarray 4	java/lang/Object
    //   121: dup
    //   122: iconst_0
    //   123: iload_1
    //   124: invokestatic 1439	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   127: aastore
    //   128: invokevirtual 1252	android/content/res/Resources:getString	(I[Ljava/lang/Object;)Ljava/lang/String;
    //   131: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   134: pop
    //   135: aload 6
    //   137: ldc_w 391
    //   140: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   143: pop
    //   144: aload 6
    //   146: new 171	java/lang/StringBuilder
    //   149: dup
    //   150: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   153: aload 5
    //   155: ldc_w 1497
    //   158: invokevirtual 1135	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   161: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   164: lload_2
    //   165: invokestatic 1499	com/android/mms/ui/MessageUtils:getHumanReadableSize	(J)Ljava/lang/String;
    //   168: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   171: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   174: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   177: pop
    //   178: aload 6
    //   180: ldc_w 391
    //   183: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   186: pop
    //   187: aload 6
    //   189: new 171	java/lang/StringBuilder
    //   192: dup
    //   193: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   196: aload 5
    //   198: ldc_w 1500
    //   201: invokevirtual 1135	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   204: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   207: aload_0
    //   208: invokestatic 1502	com/android/mms/ui/MessageUtils:getAttachmentSize	(Landroid/content/Context;)J
    //   211: invokestatic 1499	com/android/mms/ui/MessageUtils:getHumanReadableSize	(J)Ljava/lang/String;
    //   214: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   217: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   220: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   223: pop
    //   224: aload 6
    //   226: ldc_w 391
    //   229: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   232: pop
    //   233: aload 8
    //   235: getstatic 853	android/provider/Telephony$Sms:CONTENT_URI	Landroid/net/Uri;
    //   238: iconst_1
    //   239: anewarray 90	java/lang/String
    //   242: dup
    //   243: iconst_0
    //   244: ldc_w 1491
    //   247: aastore
    //   248: ldc_w 1504
    //   251: aconst_null
    //   252: aconst_null
    //   253: invokevirtual 661	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   256: astore_0
    //   257: aload_0
    //   258: ifnull +16 -> 274
    //   261: aload_0
    //   262: invokeinterface 1063 1 0
    //   267: istore_1
    //   268: aload_0
    //   269: invokeinterface 452 1 0
    //   274: aload 6
    //   276: aload 5
    //   278: ldc_w 1505
    //   281: iconst_1
    //   282: anewarray 4	java/lang/Object
    //   285: dup
    //   286: iconst_0
    //   287: iload_1
    //   288: invokestatic 1439	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   291: aastore
    //   292: invokevirtual 1252	android/content/res/Resources:getString	(I[Ljava/lang/Object;)Ljava/lang/String;
    //   295: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   298: pop
    //   299: aload 6
    //   301: ldc_w 391
    //   304: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   307: pop
    //   308: new 663	java/io/File
    //   311: dup
    //   312: ldc_w 1507
    //   315: invokespecial 666	java/io/File:<init>	(Ljava/lang/String;)V
    //   318: astore_0
    //   319: aload 6
    //   321: new 171	java/lang/StringBuilder
    //   324: dup
    //   325: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   328: aload 5
    //   330: ldc_w 1508
    //   333: invokevirtual 1135	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   336: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   339: aload_0
    //   340: invokevirtual 669	java/io/File:length	()J
    //   343: invokestatic 1499	com/android/mms/ui/MessageUtils:getHumanReadableSize	(J)Ljava/lang/String;
    //   346: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   349: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   352: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   355: pop
    //   356: aload 6
    //   358: ldc_w 391
    //   361: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   364: pop
    //   365: invokestatic 1511	android/os/Environment:getDataDirectory	()Ljava/io/File;
    //   368: invokestatic 1513	com/android/mms/ui/MessageUtils:getAvailableSpace	(Ljava/io/File;)J
    //   371: lstore_2
    //   372: aload 6
    //   374: new 171	java/lang/StringBuilder
    //   377: dup
    //   378: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   381: aload 5
    //   383: ldc_w 1514
    //   386: invokevirtual 1135	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   389: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   392: lload_2
    //   393: invokestatic 1499	com/android/mms/ui/MessageUtils:getHumanReadableSize	(J)Ljava/lang/String;
    //   396: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   399: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   402: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   405: pop
    //   406: aload 6
    //   408: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   411: areturn
    //   412: astore_0
    //   413: aload 7
    //   415: invokeinterface 452 1 0
    //   420: aload_0
    //   421: athrow
    //   422: astore 5
    //   424: aload_0
    //   425: invokeinterface 452 1 0
    //   430: aload 5
    //   432: athrow
    //   433: goto -356 -> 77
    //   436: lconst_0
    //   437: lstore_2
    //   438: goto -335 -> 103
    //   441: lconst_0
    //   442: lstore_2
    //   443: iconst_0
    //   444: istore_1
    //   445: goto -335 -> 110
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	448	0	paramContext	Context
    //   66	379	1	i	int
    //   22	421	2	l	long
    //   96	3	4	bool	boolean
    //   10	372	5	localResources	Resources
    //   422	9	5	localObject	Object
    //   19	388	6	localStringBuilder	StringBuilder
    //   52	362	7	localCursor	Cursor
    //   4	230	8	localContentResolver	ContentResolver
    // Exception table:
    //   from	to	target	type
    //   59	77	412	finally
    //   77	98	412	finally
    //   261	268	422	finally
  }
  
  private static String getTextMessageDetails(Context paramContext, Cursor paramCursor, MessageItem paramMessageItem)
  {
    Log.d("Mms", "getTextMessageDetails");
    StringBuilder localStringBuilder = new StringBuilder();
    Resources localResources = paramContext.getResources();
    localStringBuilder.append(localResources.getString(2131361942));
    localStringBuilder.append(localResources.getString(2131361943));
    localStringBuilder.append('\n');
    int i = paramCursor.getInt(8);
    String str;
    label170:
    long l;
    if (Telephony.Sms.isOutgoingFolder(i))
    {
      localStringBuilder.append(localResources.getString(2131361947));
      str = paramCursor.getString(3);
      if (!paramMessageItem.isGroup()) {
        break label385;
      }
      paramContext = paramContext.getString(2131362055, new Object[] { Integer.valueOf(paramCursor.getInt(41)) });
      localStringBuilder.append(str + " ( " + paramContext + " )");
      if (i == 1)
      {
        l = paramCursor.getLong(6);
        if (l > 0L)
        {
          localStringBuilder.append('\n');
          localStringBuilder.append(localResources.getString(2131361949));
          localStringBuilder.append(getPreciseTimeStamp(l, false));
        }
      }
      localStringBuilder.append('\n');
      if (i != 3) {
        break label396;
      }
      localStringBuilder.append(localResources.getString(2131361951));
    }
    for (;;)
    {
      localStringBuilder.append(getPreciseTimeStamp(paramCursor.getLong(5), false));
      if (i == 2)
      {
        l = paramCursor.getLong(6);
        if (l > 0L)
        {
          localStringBuilder.append('\n');
          localStringBuilder.append(localResources.getString(2131361950));
          localStringBuilder.append(getPreciseTimeStamp(l, false));
        }
      }
      i = paramCursor.getInt(11);
      if (i != 0) {
        localStringBuilder.append('\n').append(localResources.getString(2131361959)).append(i);
      }
      return localStringBuilder.toString();
      localStringBuilder.append(localResources.getString(2131361946));
      break;
      label385:
      localStringBuilder.append(str);
      break label170;
      label396:
      if (i == 1) {
        localStringBuilder.append(localResources.getString(2131361950));
      } else {
        localStringBuilder.append(localResources.getString(2131361949));
      }
    }
  }
  
  public static float getTextSize(float paramFloat)
  {
    Context localContext = MmsApp.getApp().getApplicationContext();
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(localContext);
    float f1 = localContext.getResources().getDimension(2131427389);
    float f2 = localContext.getResources().getDimension(2131427390);
    float f3 = localSharedPreferences.getFloat("message_font_size", paramFloat);
    if (f3 == 0.0F) {
      paramFloat = 0.0F;
    }
    do
    {
      return paramFloat;
      paramFloat = f1;
    } while (f3 < f1);
    if (f3 > f2) {
      return f2;
    }
    return f3;
  }
  
  public static Cursor getUncompletedMxSms(Context paramContext)
  {
    return paramContext.getContentResolver().query(Telephony.Sms.CONTENT_URI, null, "(mx_status=16 or mx_status=1) and (out_time>0) and (mx_id>0)", null, "out_time");
  }
  
  private static File getUniqueDestination(String paramString1, String paramString2)
  {
    File localFile = new File(paramString1 + "." + paramString2);
    int i = 2;
    while (localFile.exists())
    {
      localFile = new File(paramString1 + "_" + i + "." + paramString2);
      i += 1;
    }
    return localFile;
  }
  
  public static UriInfo getUriInfo(String paramString)
  {
    UriInfo localUriInfo = new UriInfo();
    uri = Uri.parse(paramString);
    String str = "";
    int i = paramString.indexOf(':');
    if (i != -1) {
      str = paramString.substring(0, i);
    }
    if (str.equalsIgnoreCase("tel"))
    {
      scheme = 0;
      paramString = paramString.substring(i + 1);
      content = PhoneNumberUtils.formatNumber(paramString);
      if (content == null) {
        content = paramString;
      }
      contact = Contact.get(paramString).load(false, true);
      if (contact.existsInDatabase())
      {
        title = (paramString + " (" + contact.getName() + ")");
        return localUriInfo;
      }
      title = paramString;
      return localUriInfo;
    }
    if (str.equalsIgnoreCase("mailto"))
    {
      scheme = 1;
      paramString = paramString.substring(i + 1);
      content = paramString;
      title = paramString;
      return localUriInfo;
    }
    if ((str.equalsIgnoreCase("http")) || (str.equalsIgnoreCase("https")) || (str.equalsIgnoreCase("rtsp")))
    {
      scheme = 2;
      content = paramString;
      title = paramString;
      return localUriInfo;
    }
    if (str.equalsIgnoreCase("time"))
    {
      scheme = 4;
      paramString = paramString.substring(i + 1);
      content = paramString;
      formatContent = paramString;
      title = paramString;
      return localUriInfo;
    }
    scheme = 3;
    content = paramString;
    title = paramString;
    return localUriInfo;
  }
  
  private static int getVideoCaptureDurationLimit()
  {
    CamcorderProfile localCamcorderProfile = CamcorderProfile.get(0);
    if (localCamcorderProfile == null) {
      return 0;
    }
    return duration;
  }
  
  public static boolean handleFileShareMessage(Context paramContext, String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString1)) {}
    while (TextUtils.isEmpty(getFileShareMessage(paramString2))) {
      return false;
    }
    Intent localIntent = new Intent("cn.kuaipan.mishare.SHARE_MSG");
    localIntent.putExtra("SENDER_NUM", paramString1);
    localIntent.putExtra("MSG", paramString2);
    paramContext.sendBroadcast(localIntent);
    return true;
  }
  
  public static void handleMxSmsFailed(Context paramContext, long paramLong, int paramInt, boolean paramBoolean)
  {
    handleMxSmsFailed(paramContext, ContentUris.withAppendedId(MX_SMS_STATUS_URI, paramLong), paramInt, paramBoolean);
  }
  
  public static void handleMxSmsFailed(Context paramContext, Uri paramUri, int paramInt, boolean paramBoolean)
  {
    ContentValues localContentValues;
    if ((PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("pref_key_mx_auto_resend_sms", true)) && (!paramBoolean))
    {
      localContentValues = new ContentValues();
      localContentValues.put("mx_status", Integer.valueOf(0));
      localContentValues.put("type", Integer.valueOf(6));
      if (requireDeliveryStatusBySlotId(paramContext, paramInt)) {
        localContentValues.put("status", Integer.valueOf(32));
      }
      paramContext.getContentResolver().update(paramUri, localContentValues, "(mx_status!=17 AND mx_status!=0)", null);
      MSimUtils.sendQueuedMessage(paramContext, paramInt);
    }
    do
    {
      return;
      localContentValues = new ContentValues();
      localContentValues.put("mx_status", Integer.valueOf(0));
      localContentValues.put("type", Integer.valueOf(5));
      localContentValues.put("read", Integer.valueOf(0));
      localContentValues.put("error_code", Integer.valueOf(1));
    } while (paramContext.getContentResolver().update(paramUri, localContentValues, "(mx_status!=17 AND mx_status!=0)", null) <= 0);
    MessagingNotification.notifySendFailed(paramContext, true);
  }
  
  public static void handleReadReport(final Context paramContext, Collection<Long> paramCollection, final int paramInt, final Runnable paramRunnable)
  {
    Object localObject = "m_type = 132 AND read = 0 AND rr = 128";
    if (paramCollection != null) {
      localObject = "m_type = 132 AND read = 0 AND rr = 128" + " AND thread_id IN (" + TextUtils.join(",", paramCollection) + ")";
    }
    paramCollection = SqliteWrapper.query(paramContext, paramContext.getContentResolver(), Telephony.Mms.Inbox.CONTENT_URI, new String[] { "_id", "m_id" }, (String)localObject, null, null);
    if (paramCollection == null) {
      return;
    }
    localObject = new HashMap();
    try
    {
      if (paramCollection.getCount() == 0)
      {
        if (paramRunnable != null) {
          paramRunnable.run();
        }
        return;
      }
      while (paramCollection.moveToNext())
      {
        Uri localUri = ContentUris.withAppendedId(Telephony.Mms.CONTENT_URI, paramCollection.getLong(0));
        ((Map)localObject).put(paramCollection.getString(1), AddressUtils.getFrom(paramContext, localUri));
      }
    }
    finally
    {
      paramCollection.close();
    }
    confirmReadReportDialog(paramContext, new DialogInterface.OnClickListener()new DialogInterface.OnClickListener
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        Iterator localIterator = val$map.entrySet().iterator();
        while (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          MmsMessageSender.sendReadRec(paramContext, (String)localEntry.getValue(), (String)localEntry.getKey(), paramInt);
        }
        if (paramRunnable != null) {
          paramRunnable.run();
        }
        paramAnonymousDialogInterface.dismiss();
      }
    }, new DialogInterface.OnClickListener()new DialogInterface.OnCancelListener
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        if (val$callback != null) {
          val$callback.run();
        }
        paramAnonymousDialogInterface.dismiss();
      }
    }, new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        if (val$callback != null) {
          val$callback.run();
        }
        paramAnonymousDialogInterface.dismiss();
      }
    });
  }
  
  public static boolean hasBlockedFlag(Uri paramUri)
  {
    paramUri = paramUri.getQueryParameter("blocked_flag");
    return (!TextUtils.isEmpty(paramUri)) && (paramUri.equals("1"));
  }
  
  public static boolean hasVoipPackage(Context paramContext)
  {
    try
    {
      paramContext.getPackageManager().getPackageInfo("com.miui.voip", 128);
      Log.v("Mms", "has voip package");
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static Uri insertUniqueMessage(Context paramContext, ContentValues paramContentValues)
  {
    ContentResolver localContentResolver = paramContext.getContentResolver();
    Object localObject2 = paramContentValues.getAsString("address");
    String str1 = paramContentValues.getAsString("body");
    Object localObject3 = paramContentValues.getAsString("b2c_numbers");
    ??? = paramContentValues.getAsInteger("mx_status");
    if (??? != null) {}
    for (int k = ((Integer)???).intValue();; k = 0)
    {
      ??? = new ArrayList(1);
      ((ArrayList)???).add(localObject2);
      Object localObject4 = Conversation.get(ContactList.getByNumbers((Iterable)???));
      int i;
      int m;
      String str2;
      boolean bool;
      synchronized (INSERT_SMS_LOCK)
      {
        localObject4 = "thread_id=" + ((Conversation)localObject4).getThreadId() + " and " + "date" + ">=" + (System.currentTimeMillis() - 300000L);
        localObject4 = localContentResolver.query(Telephony.Sms.Inbox.CONTENT_URI, null, (String)localObject4, null, "date desc");
        i = 0;
        j = 0;
        if (localObject4 != null) {
          i = j;
        }
        try
        {
          if (((Cursor)localObject4).moveToFirst())
          {
            i = ((Cursor)localObject4).getColumnIndex("body");
            m = ((Cursor)localObject4).getColumnIndex("mx_status");
            str2 = ((Cursor)localObject4).getString(i);
            int n = ((Cursor)localObject4).getInt(m);
            bool = str1.equals(str2);
            if ((!bool) || (n == k)) {
              break label274;
            }
          }
          for (i = 1;; i = j)
          {
            ((Cursor)localObject4).close();
            if (i == 0) {
              break label309;
            }
            Log.w("Mms", "duplicated message received");
            return null;
            label274:
            bool = ((Cursor)localObject4).moveToNext();
            if (bool) {
              break;
            }
          }
          paramContext = finally;
        }
        finally
        {
          ((Cursor)localObject4).close();
        }
      }
      label309:
      int j = i;
      if (k != 0)
      {
        j = i;
        if (!TextUtils.isEmpty((CharSequence)localObject3))
        {
          localObject3 = ((String)localObject3).split(",");
          localObject4 = new StringBuilder();
          m = localObject3.length;
          j = 0;
          while (j < m)
          {
            str2 = localObject3[j];
            if (((StringBuilder)localObject4).length() > 0) {
              ((StringBuilder)localObject4).append(",");
            }
            ((StringBuilder)localObject4).append("'").append(str2).append("'");
            j += 1;
          }
          l = System.currentTimeMillis();
          localObject3 = "date>=" + (l - 300000L) + " AND " + "mx_status" + " = " + 0 + " AND " + "address" + " IN (" + ((StringBuilder)localObject4).toString() + ")";
          localObject3 = localContentResolver.query(Telephony.Sms.Inbox.CONTENT_URI, null, (String)localObject3, null, "date desc");
          j = i;
          if (localObject3 != null) {
            j = i;
          }
        }
      }
      try
      {
        if (((Cursor)localObject3).moveToFirst())
        {
          j = ((Cursor)localObject3).getColumnIndex("body");
          bool = str1.equals(((Cursor)localObject3).getString(j));
          if (!bool) {
            break label588;
          }
        }
        for (j = 1;; j = i)
        {
          ((Cursor)localObject3).close();
          if (j == 0) {
            break label617;
          }
          Log.w("Mms", "duplicated mx sp message received");
          return null;
          label588:
          bool = ((Cursor)localObject3).moveToNext();
          if (bool) {
            break;
          }
        }
        if (k != 0) {
          break label909;
        }
      }
      finally
      {
        ((Cursor)localObject3).close();
      }
      label617:
      long l = System.currentTimeMillis();
      localObject2 = "date>= (" + l + " - CASE WHEN " + "b2c_ttl" + " < " + 300000L + " THEN " + 300000L + " ELSE " + "b2c_ttl" + " END)" + " AND " + "mx_status" + " <> " + 0 + " AND " + "b2c_numbers" + " IS NOT NULL" + " AND " + "b2c_numbers" + " LIKE \"%" + (String)localObject2 + "%\" ";
      localObject2 = localContentResolver.query(Telephony.Sms.Inbox.CONTENT_URI, null, (String)localObject2, null, "date desc");
      if (localObject2 != null) {}
      for (;;)
      {
        try
        {
          if (((Cursor)localObject2).moveToFirst())
          {
            i = ((Cursor)localObject2).getColumnIndex("body");
            bool = str1.equals(((Cursor)localObject2).getString(i));
            if (bool)
            {
              i = 1;
              ((Cursor)localObject2).close();
              if (i != 0)
              {
                Log.w("Mms", "duplicated common sp message received");
                return null;
              }
            }
            else
            {
              bool = ((Cursor)localObject2).moveToNext();
              if (bool) {
                continue;
              }
            }
          }
          else
          {
            i = j;
            continue;
          }
          paramContext = SqliteWrapper.insert(paramContext, localContentResolver, Telephony.Sms.Inbox.CONTENT_URI, paramContentValues);
        }
        finally
        {
          ((Cursor)localObject2).close();
        }
        return paramContext;
        label909:
        i = j;
      }
    }
  }
  
  private static boolean isAccessControlActived(Context paramContext)
  {
    return 1 == Settings.Secure.getInt(paramContext.getContentResolver(), "access_control_lock_enabled", 0);
  }
  
  private static boolean isAccessControlProtected(Context paramContext, String paramString)
  {
    SecurityManager localSecurityManager = (SecurityManager)paramContext.getSystemService("security");
    return (isAccessControlActived(paramContext)) && (localSecurityManager.getApplicationAccessControlEnabled(paramString));
  }
  
  public static boolean isAccessControlled(Context paramContext)
  {
    ChooseLockSettingsHelper localChooseLockSettingsHelper = MmsApp.getChooseLockSettingsHelper();
    if (localChooseLockSettingsHelper == null) {}
    while ((!localChooseLockSettingsHelper.isACLockEnabled()) || (!isAccessControlProtected(paramContext, "com.android.mms"))) {
      return false;
    }
    return true;
  }
  
  public static boolean isAccessControlledOrPrivacyModeEnabled(Context paramContext)
  {
    ChooseLockSettingsHelper localChooseLockSettingsHelper = MmsApp.getChooseLockSettingsHelper();
    if (localChooseLockSettingsHelper == null) {}
    while ((!isAccessControlled(paramContext)) && (!localChooseLockSettingsHelper.isPrivacyModeEnabled())) {
      return false;
    }
    return true;
  }
  
  public static boolean isAlias(String paramString)
  {
    if (!MmsConfig.isAliasEnabled()) {}
    label78:
    for (;;)
    {
      return false;
      if (paramString == null) {}
      for (int i = 0;; i = paramString.length())
      {
        if ((i < MmsConfig.getAliasMinChars()) || (i > MmsConfig.getAliasMaxChars()) || (!Character.isLetter(paramString.charAt(0)))) {
          break label78;
        }
        int j = 1;
        for (;;)
        {
          if (j >= i) {
            break label80;
          }
          char c = paramString.charAt(j);
          if ((!Character.isLetterOrDigit(c)) && (c != '.')) {
            break;
          }
          j += 1;
        }
      }
    }
    label80:
    return true;
  }
  
  public static boolean isAllowNetworkAccess(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("pref_key_allow_network_access", false);
  }
  
  public static boolean isEmail(String paramString)
  {
    boolean bool = true;
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    if ((paramString.length() >= 3) && (paramString.indexOf('@') >= 1)) {}
    for (;;)
    {
      return bool;
      bool = false;
    }
  }
  
  private static boolean isIndiaServiceNumber(String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (Build.checkRegion("IN"))
    {
      if (sIndiaSpPattern == null) {
        sIndiaSpPattern = Pattern.compile("^[A-Za-z][A-Za-z][-]\\S{6}$");
      }
      bool1 = sIndiaSpPattern.matcher(paramString).matches();
      if (bool1) {
        return bool1;
      }
      bool1 = bool2;
      if (!paramString.startsWith("+91"))
      {
        bool1 = bool2;
        if (2 < paramString.length())
        {
          bool1 = bool2;
          if (paramString.length() < 8) {
            bool1 = true;
          }
        }
      }
    }
    return bool1;
    return bool1;
  }
  
  public static boolean isMessagingTemplateAllowed(Context paramContext)
  {
    if (!isUnderstandSupported()) {
      return false;
    }
    if (!Build.IS_INTERNATIONAL_BUILD) {
      return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("pref_key_show_template", true);
    }
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("pref_key_show_template_in", true);
  }
  
  public static boolean isMxDisabled(Context paramContext)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (!MxActivateService.isActivating())
    {
      if (MxActivateService.isActivateStatusInitialized()) {
        break label20;
      }
      bool1 = bool2;
    }
    label20:
    do
    {
      do
      {
        return bool1;
        bool2 = isMxDisabledBySlotId(paramContext, 0);
        bool1 = bool2;
      } while (!bool2);
      bool1 = bool2;
    } while (!MSimUtils.isMSim());
    return isMxDisabledBySlotId(paramContext, 1);
  }
  
  public static boolean isMxDisabledBySlotId(Context paramContext, int paramInt)
  {
    return (MSimUtils.isSimInserted(paramInt)) && (!MxActivateService.isMxEnabled(paramContext, paramInt));
  }
  
  public static boolean isNetworkRecommendDateLong(Context paramContext)
  {
    long l = PreferenceManager.getDefaultSharedPreferences(paramContext).getLong("last_network_recommend_date", 0L);
    l = System.currentTimeMillis() - l;
    return (l > 1209600000L) || (l < 0L);
  }
  
  public static boolean isPhoneNumber(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    int i = 0;
    for (;;)
    {
      if (i >= paramString.length()) {
        break label41;
      }
      if ("0123456789+. -#*()".indexOf(paramString.charAt(i)) == -1) {
        break;
      }
      i += 1;
    }
    label41:
    return true;
  }
  
  public static boolean isPrivacyModeEnabled(Context paramContext)
  {
    paramContext = MmsApp.getChooseLockSettingsHelper();
    if (paramContext == null) {
      return false;
    }
    return paramContext.isPrivacyModeEnabled();
  }
  
  public static boolean isSendingContactByVCard(SharedPreferences paramSharedPreferences)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (Build.IS_CM_CUSTOMIZATION)
    {
      bool1 = bool2;
      if (paramSharedPreferences.getBoolean("pref_key_card_format_vcard", false)) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static boolean isServiceNumber(Context paramContext, String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      if (isIndiaServiceNumber(paramString)) {}
      while (MiPubUtils.isServiceNumber(paramContext, paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean isSmartMessageNotReady(Context paramContext)
  {
    return (!Build.IS_INTERNATIONAL_BUILD) && ((!MiPubUtils.isYellowPageNetworkAllowed(paramContext)) || (!isAllowNetworkAccess(paramContext)) || (isMxDisabled(paramContext))) && (!Build.IS_CM_CUSTOMIZATION);
  }
  
  public static boolean isStorageAvailable(Context paramContext)
  {
    if (Build.IS_CM_CUSTOMIZATION_TEST)
    {
      paramContext = StorageManager.from(paramContext);
      File localFile = Environment.getDataDirectory();
      if (getAvailableSpace(localFile) < paramContext.getStorageFullBytes(localFile)) {
        return false;
      }
    }
    return true;
  }
  
  public static boolean isUnderstandSupported()
  {
    return ((!Build.IS_CM_CUSTOMIZATION_TEST) && (!Build.IS_INTERNATIONAL_BUILD)) || (Build.checkRegion("IN"));
  }
  
  public static boolean isValidMmsAddress(String paramString)
  {
    return parseMmsAddress(paramString) != null;
  }
  
  public static boolean isYpRecommendEnabled(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("yellowpage_recommend_show", false);
  }
  
  public static final boolean isZhCnLanguage()
  {
    return Locale.CHINA.equals(Locale.getDefault());
  }
  
  public static void launchMessagePreference(Context paramContext)
  {
    paramContext.startActivity(new Intent(paramContext, MessagingPreferenceActivity.class));
  }
  
  public static void loadFontSizeConfiguration(Context paramContext) {}
  
  public static void notifyIncomingSmsMmsToBracelet(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (TextUtils.isEmpty(paramString)))
    {
      Log.e("Mms", "notifyIncomingSmsMmsToBracelet: context or address is null");
      return;
    }
    paramString = Contact.get(paramString).load(false, false);
    if (paramString == null)
    {
      Log.e("Mms", "notifyIncomingSmsMmsToBracelet: get contact is null");
      return;
    }
    String str1 = paramString.getNumber();
    String str2 = paramString.getName();
    Intent localIntent = new Intent("miui.sms.ACTION_ALARM");
    localIntent.putExtra("number", str1);
    if (((str2 != null) && (str2.equals(str1))) || (paramString.isYellowPageNumber()))
    {
      localIntent.putExtra("contact", "");
      localIntent.putExtra("tag", paramString.getTag());
      if (!paramString.isYellowPageNumber()) {
        break label165;
      }
    }
    label165:
    for (paramString = paramString.getName();; paramString = "")
    {
      localIntent.putExtra("yellow_page", paramString);
      paramContext.sendBroadcast(localIntent, "miui.permission.BLE_IMMEDIATE_ALERT");
      return;
      localIntent.putExtra("contact", str2);
      break;
    }
  }
  
  public static String parseMmsAddress(String paramString)
  {
    if (Telephony.Mms.isEmailAddress(paramString)) {}
    do
    {
      return paramString;
      String str = parsePhoneNumberForMms(paramString);
      if (str != null) {
        return str;
      }
    } while (isAlias(paramString));
    return null;
  }
  
  private static String parsePhoneNumberForMms(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int j = paramString.length();
    int i = 0;
    if (i < j)
    {
      char c = paramString.charAt(i);
      if ((c == '+') && (localStringBuilder.length() == 0)) {
        localStringBuilder.append(c);
      }
      label72:
      do
      {
        for (;;)
        {
          i += 1;
          break;
          if (!Character.isDigit(c)) {
            break label72;
          }
          localStringBuilder.append(c);
        }
      } while (numericSugarMap.get(Character.valueOf(c)) != null);
      return null;
    }
    return localStringBuilder.toString();
  }
  
  public static void performUriOperation(final Context paramContext, final UriInfo paramUriInfo, final MessageItem paramMessageItem)
  {
    ContextMenuDialog localContextMenuDialog = new ContextMenuDialog(paramContext);
    final String str;
    int i;
    switch (scheme)
    {
    case 3: 
    default: 
      str = "notes";
      i = 0;
    }
    for (;;)
    {
      localContextMenuDialog.addMenuItem(2131362080, new Runnable()
      {
        public void run()
        {
          ((ClipboardManager)val$context.getSystemService("clipboard")).setText(paramUriInfocontent);
          Toast.makeText(val$context, val$context.getResources().getString(2131362081), 0).show();
        }
      });
      if ((contact != null) && (contact.existsInDatabase())) {
        localContextMenuDialog.addMenuItem(2131362084, new Runnable()
        {
          public void run()
          {
            MessageUtils.startIntent(val$context, new Intent("android.intent.action.VIEW", paramUriInfocontact.getUri()));
          }
        });
      }
      for (;;)
      {
        localContextMenuDialog.setTitle(title);
        localContextMenuDialog.show();
        return;
        localContextMenuDialog.addMenuItem(2131362076, new Runnable()
        {
          public void run()
          {
            MessageUtils.startIntent(val$context, new Intent("android.intent.action.CALL", paramUriInfouri));
          }
        });
        localContextMenuDialog.addMenuItem(2131362077, new Runnable()
        {
          public void run()
          {
            MessageUtils.startIntent(val$context, new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + paramUriInfocontent)));
          }
        });
        str = "phone";
        i = 1;
        break;
        localContextMenuDialog.addMenuItem(2131362079, new Runnable()
        {
          public void run()
          {
            MessageUtils.startIntent(val$context, new Intent("android.intent.action.VIEW", paramUriInfouri));
          }
        });
        str = "email";
        i = 1;
        break;
        str = INTENT_INSERT_WEBSITE;
        localContextMenuDialog.addMenuItem(2131362078, new Runnable()
        {
          public void run()
          {
            if (Build.IS_CM_CUSTOMIZATION_TEST)
            {
              AlertDialog.Builder localBuilder = new AlertDialog.Builder(val$context);
              localBuilder.setMessage(2131362369);
              localBuilder.setPositiveButton(17039370, new DialogInterface.OnClickListener()
              {
                public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                {
                  MessageUtils.startIntent(val$context, new Intent("android.intent.action.VIEW", val$info.uri));
                }
              });
              localBuilder.setNegativeButton(17039360, null);
              localBuilder.create().show();
              return;
            }
            MessageUtils.startIntent(val$context, new Intent("android.intent.action.VIEW", paramUriInfouri));
          }
        });
        if (!Build.IS_CM_CUSTOMIZATION) {
          break label410;
        }
        localContextMenuDialog.addMenuItem(2131362250, new Runnable()
        {
          public void run()
          {
            Browser.saveBookmark(val$context, null, paramUriInfouri.toString());
          }
        });
        i = 1;
        break;
        localContextMenuDialog.addMenuItem(2131362086, new Runnable()
        {
          public void run()
          {
            long l1 = 0L;
            boolean bool = true;
            Object localObject2 = DateParseUtils.parseDate(val$info.formatContent);
            if (localObject2 == null) {
              return;
            }
            Object localObject1;
            label84:
            Object localObject3;
            if (((List)localObject2).size() == 1)
            {
              localObject1 = (DateParseUtils.EventDate)((List)localObject2).get(0);
              if (localObject1 == null)
              {
                bool = false;
                localObject2 = new Intent("android.intent.action.INSERT").setData(CalendarContract.Events.CONTENT_URI).putExtra("allDay", bool);
                if (localObject1 != null) {
                  break label166;
                }
                localObject2 = ((Intent)localObject2).putExtra("beginTime", l1).putExtra("endTime", 0);
                localObject1 = localObject2;
                if (paramMessageItem != null)
                {
                  localObject3 = paramMessageItem.getBody();
                  localObject1 = localObject2;
                  if (!TextUtils.isEmpty((CharSequence)localObject3))
                  {
                    ((Intent)localObject2).putExtra("event_title", (String)localObject3);
                    localObject1 = localObject2;
                  }
                }
              }
            }
            for (;;)
            {
              MessageUtils.startIntent(paramContext, (Intent)localObject1);
              return;
              bool = ((DateParseUtils.EventDate)localObject1).isAllDayEvent();
              break;
              label166:
              l1 = ((DateParseUtils.EventDate)localObject1).getDate().getTime();
              break label84;
              if (((List)localObject2).size() == 2)
              {
                localObject1 = (DateParseUtils.EventDate)((List)localObject2).get(0);
                localObject2 = (DateParseUtils.EventDate)((List)localObject2).get(1);
                label228:
                long l2;
                if ((localObject1 == null) || (localObject2 == null))
                {
                  bool = false;
                  localObject3 = new Intent("android.intent.action.INSERT").setData(CalendarContract.Events.CONTENT_URI).putExtra("allDay", bool);
                  if (localObject1 != null) {
                    break label355;
                  }
                  l2 = 0L;
                  label259:
                  localObject1 = ((Intent)localObject3).putExtra("beginTime", l2);
                  if (localObject2 != null) {
                    break label367;
                  }
                }
                for (;;)
                {
                  localObject2 = ((Intent)localObject1).putExtra("endTime", l1);
                  localObject1 = localObject2;
                  if (paramMessageItem == null) {
                    break;
                  }
                  localObject3 = paramMessageItem.getBody();
                  localObject1 = localObject2;
                  if (TextUtils.isEmpty((CharSequence)localObject3)) {
                    break;
                  }
                  ((Intent)localObject2).putExtra("event_title", (String)localObject3);
                  localObject1 = localObject2;
                  break;
                  if ((((DateParseUtils.EventDate)localObject1).isAllDayEvent()) && (((DateParseUtils.EventDate)localObject2).isAllDayEvent())) {
                    break label228;
                  }
                  bool = false;
                  break label228;
                  label355:
                  l2 = ((DateParseUtils.EventDate)localObject1).getDate().getTime();
                  break label259;
                  label367:
                  l1 = ((DateParseUtils.EventDate)localObject2).getDate().getTime();
                }
              }
              localObject2 = new Intent("android.intent.action.INSERT").setData(CalendarContract.Events.CONTENT_URI).putExtra("allDay", true).putExtra("beginTime", 0).putExtra("endTime", 0);
              localObject1 = localObject2;
              if (paramMessageItem != null)
              {
                localObject3 = paramMessageItem.getBody();
                localObject1 = localObject2;
                if (!TextUtils.isEmpty((CharSequence)localObject3))
                {
                  ((Intent)localObject2).putExtra("event_title", (String)localObject3);
                  localObject1 = localObject2;
                }
              }
            }
          }
        });
        str = "";
        i = 0;
        break;
        if (i != 0)
        {
          localContextMenuDialog.addMenuItem(2131362082, new Runnable()
          {
            public void run()
            {
              Intent localIntent = new Intent("android.intent.action.INSERT", ContactsContract.Contacts.CONTENT_URI);
              localIntent.putExtra(val$insert, paramUriInfocontent);
              MessageUtils.startIntent(paramContext, localIntent);
            }
          });
          localContextMenuDialog.addMenuItem(2131362083, new Runnable()
          {
            public void run()
            {
              Intent localIntent = new Intent("android.intent.action.INSERT_OR_EDIT");
              localIntent.setType("vnd.android.cursor.item/contact");
              localIntent.putExtra(val$insert, paramUriInfocontent);
              MessageUtils.startIntent(paramContext, localIntent);
            }
          });
          if ((paramMessageItem != null) && (((paramMessageItem.isSms()) && (paramMessageItem.getBoxId() == 1)) || ((paramMessageItem.isMms()) && (paramMessageItem.getBoxId() == 1))))
          {
            Contact localContact = Contact.get(paramMessageItem.getAddress());
            if ((localContact.existsInDatabase()) && (!Contact.isSimContact(localContact))) {
              localContextMenuDialog.addMenuItem(paramContext.getString(2131362208, new Object[] { paramMessageItem.getContactName() }), new Runnable()
              {
                public void run()
                {
                  Intent localIntent = new Intent("android.intent.action.EDIT", val$c.getUri());
                  localIntent.putExtra(str, paramUriInfocontent);
                  MessageUtils.startIntent(paramContext, localIntent);
                }
              });
            }
          }
        }
      }
      label410:
      i = 1;
    }
  }
  
  public static void processReceivedMsgReport(Context paramContext, String paramString1, String paramString2, int paramInt)
  {
    if (checkPrivateMessage(paramContext, paramString1)) {
      return;
    }
    VoiceReportUtils.voiceReport(paramContext, paramString1, paramString2, paramInt);
  }
  
  private static MmsReportStatus queryStatusByRecipient(Map<String, MmsReportStatus> paramMap, String paramString)
  {
    Iterator localIterator = paramMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (Telephony.Mms.isEmailAddress(paramString))
      {
        if (TextUtils.equals(str, paramString)) {
          return (MmsReportStatus)paramMap.get(str);
        }
      }
      else if (PhoneNumberUtils.compare(str, paramString)) {
        return (MmsReportStatus)paramMap.get(str);
      }
    }
    return null;
  }
  
  public static void recordSound(Context paramContext, int paramInt, long paramLong)
  {
    if ((paramContext instanceof Activity))
    {
      Intent localIntent = new Intent("android.intent.action.GET_CONTENT");
      localIntent.setType("audio/amr");
      localIntent.setClassName("com.android.soundrecorder", "com.android.soundrecorder.SoundRecorder");
      if (paramLong > 0L) {
        localIntent.putExtra("android.provider.MediaStore.extra.MAX_BYTES", paramLong);
      }
      localIntent.putExtra("source_name", paramContext.getString(2131361944));
      ((Activity)paramContext).startActivityForResult(localIntent, paramInt);
    }
  }
  
  public static void recordVideo(Context paramContext, int paramInt, long paramLong)
  {
    if ((paramContext instanceof Activity))
    {
      int i = getVideoCaptureDurationLimit();
      Intent localIntent = new Intent("android.media.action.VIDEO_CAPTURE");
      localIntent.putExtra("android.intent.extra.videoQuality", 0);
      localIntent.putExtra("android.intent.extra.sizeLimit", paramLong);
      localIntent.putExtra("android.intent.extra.durationLimit", i);
      ((Activity)paramContext).startActivityForResult(localIntent, paramInt);
    }
  }
  
  public static void requestInputMethod(Context paramContext, TextView paramTextView)
  {
    paramTextView.requestFocus();
    ((InputMethodManager)paramContext.getSystemService("input_method")).showSoftInput(paramTextView, 0);
  }
  
  public static boolean requireDeliveryStatusBySimId(Context paramContext, long paramLong)
  {
    boolean bool = false;
    if (getPrefNotificationEnabled(paramContext)) {
      if (Build.IS_CM_CUSTOMIZATION_TEST)
      {
        if (paramLong < 0L) {
          break label59;
        }
        String str = MSimUtils.createKeyWithSimId(paramLong, "pref_key_delivery_reports");
        bool = PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean(str, false);
      }
    }
    label59:
    do
    {
      return bool;
      return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("pref_key_delivery_reports", true);
      Log.v("Mms", "requireDeliveryStatusBySlotId with error simId");
    } while (Build.IS_CM_CUSTOMIZATION_TEST);
    return true;
  }
  
  public static boolean requireDeliveryStatusBySlotId(Context paramContext, int paramInt)
  {
    if (getPrefNotificationEnabled(paramContext)) {
      if (Build.IS_CM_CUSTOMIZATION_TEST)
      {
        long l = MSimUtils.getSimIdBySlotId(paramInt);
        if (l >= 0L)
        {
          String str = MSimUtils.createKeyWithSimId(l, "pref_key_delivery_reports");
          return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean(str, false);
        }
      }
      else
      {
        return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("pref_key_delivery_reports", true);
      }
    }
    Log.v("Mms", "requireDeliveryStatusBySlotId with error slotId");
    return true;
  }
  
  public static void resendMms(Context paramContext, WorkingMessage.MessageStatusListener paramMessageStatusListener, Uri paramUri, boolean paramBoolean)
  {
    resendMms(paramContext, paramMessageStatusListener, paramUri, paramBoolean, MxMessagePduHelper.getMessageMx2Type(paramContext, ContentUris.parseId(paramUri)));
  }
  
  public static void resendMms(Context paramContext, WorkingMessage.MessageStatusListener paramMessageStatusListener, Uri paramUri, boolean paramBoolean, int paramInt)
  {
    long l = ContentUris.parseId(paramUri);
    if (paramInt > 0)
    {
      paramInt = 1;
      MxMessagePduHelper.markMmsSendAsMx(paramContext, paramUri, paramBoolean);
      ContentValues localContentValues = new ContentValues(4);
      localContentValues.put("err_type", Integer.valueOf(0));
      localContentValues.put("err_code", Integer.valueOf(0));
      localContentValues.put("retry_index", Integer.valueOf(0));
      localContentValues.put("last_try", Integer.valueOf(0));
      SqliteWrapper.update(paramContext, paramContext.getContentResolver(), Telephony.MmsSms.PendingMessages.CONTENT_URI, localContentValues, "msg_id=" + l, null);
      if (!paramBoolean) {
        break label141;
      }
      if (paramInt == 0) {
        break label135;
      }
      Mx2MmsTransactionService.startSendMx2(paramContext, paramUri);
    }
    label135:
    label141:
    while ((paramInt != 0) && (!MxMessageUtils.convertMx2toMms(paramContext, paramMessageStatusListener, paramUri, true)))
    {
      return;
      paramInt = 0;
      break;
      MxMmsTransactionService.startSendMms(paramContext, paramUri);
      return;
    }
    if ((MxActivateService.isMxEnabled(paramContext)) && (PushSession.getInstance(paramContext).getConnectedSimIndex() >= 0)) {
      deleteMxMmsOnServer(paramContext, paramUri);
    }
    paramMessageStatusListener = new Intent(paramContext, TransactionService.class);
    paramMessageStatusListener.putExtra("uri", paramUri.toString());
    paramMessageStatusListener.putExtra("type", 2);
    paramContext.startService(paramMessageStatusListener);
  }
  
  public static void resizeImageAsync(Context paramContext, final Uri paramUri, final Handler paramHandler, final ResizeImageResultCallback paramResizeImageResultCallback, final boolean paramBoolean)
  {
    final Runnable local2 = new Runnable()
    {
      public void run()
      {
        Toast.makeText(val$context, 2131361846, 0).show();
      }
    };
    paramHandler.postDelayed(local2, 1000L);
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          final Object localObject1 = new UriImage(val$context, paramUri);
          int i = MmsConfig.getMaxImageWidth();
          int k = MmsConfig.getMaxImageHeight();
          int m = k;
          int j = i;
          if (((UriImage)localObject1).getHeight() > ((UriImage)localObject1).getWidth())
          {
            j = k;
            m = i;
          }
          localObject1 = ((UriImage)localObject1).getResizedImageAsPart(j, m, MmsConfig.getMaxMessageSize() - 5000);
          paramHandler.removeCallbacks(local2);
          paramHandler.post(new Runnable()
          {
            public void run()
            {
              val$cb.onResizeResult(localObject1, val$append);
            }
          });
          return;
        }
        finally
        {
          paramHandler.removeCallbacks(local2);
        }
      }
    }).start();
  }
  
  public static int resolveDefaultCursorDrawableRes(Context paramContext)
  {
    TypedValue localTypedValue = new TypedValue();
    paramContext.getTheme().resolveAttribute(16843618, localTypedValue, true);
    return resourceId;
  }
  
  /* Error */
  public static String saveMmsPartToDisk(Context paramContext, PduPart paramPduPart, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 7
    //   3: aload_1
    //   4: invokevirtual 2276	com/google/android/mms/pdu/PduPart:getDataUri	()Landroid/net/Uri;
    //   7: astore 4
    //   9: aload_0
    //   10: invokevirtual 426	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   13: aload 4
    //   15: invokevirtual 2280	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
    //   18: astore 4
    //   20: aload 4
    //   22: instanceof 2282
    //   25: ifeq +412 -> 437
    //   28: aload 4
    //   30: checkcast 2282	java/io/FileInputStream
    //   33: astore 8
    //   35: aload_1
    //   36: invokevirtual 2285	com/google/android/mms/pdu/PduPart:getFilename	()[B
    //   39: astore 6
    //   41: aload 6
    //   43: astore 5
    //   45: aload 6
    //   47: ifnonnull +9 -> 56
    //   50: aload_1
    //   51: invokevirtual 2288	com/google/android/mms/pdu/PduPart:getContentLocation	()[B
    //   54: astore 5
    //   56: aload 5
    //   58: astore 6
    //   60: aload 5
    //   62: ifnonnull +382 -> 444
    //   65: aload_1
    //   66: invokevirtual 2290	com/google/android/mms/pdu/PduPart:getName	()[B
    //   69: astore 6
    //   71: goto +373 -> 444
    //   74: aload 5
    //   76: aload_2
    //   77: invokestatic 2292	com/android/mms/ui/MessageUtils:getFileNameFromPath	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   80: astore_2
    //   81: aload_2
    //   82: ldc_w 1565
    //   85: invokevirtual 1464	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   88: istore_3
    //   89: iload_3
    //   90: iconst_m1
    //   91: if_icmpne +139 -> 230
    //   94: new 90	java/lang/String
    //   97: dup
    //   98: aload_1
    //   99: invokevirtual 2295	com/google/android/mms/pdu/PduPart:getContentType	()[B
    //   102: invokespecial 1258	java/lang/String:<init>	([B)V
    //   105: astore_1
    //   106: invokestatic 2301	android/webkit/MimeTypeMap:getSingleton	()Landroid/webkit/MimeTypeMap;
    //   109: aload_1
    //   110: invokevirtual 2304	android/webkit/MimeTypeMap:getExtensionFromMimeType	(Ljava/lang/String;)Ljava/lang/String;
    //   113: astore_1
    //   114: new 171	java/lang/StringBuilder
    //   117: dup
    //   118: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   121: getstatic 191	com/android/mms/ui/MessageUtils:SAVE_MMS_PART_TO_DISK_PATH	Ljava/lang/String;
    //   124: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   127: aload_2
    //   128: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   131: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   134: aload_1
    //   135: invokestatic 2306	com/android/mms/ui/MessageUtils:getUniqueDestination	(Ljava/lang/String;Ljava/lang/String;)Ljava/io/File;
    //   138: astore 6
    //   140: aload 6
    //   142: invokevirtual 2309	java/io/File:getParentFile	()Ljava/io/File;
    //   145: astore_1
    //   146: aload_1
    //   147: invokevirtual 1568	java/io/File:exists	()Z
    //   150: ifne +102 -> 252
    //   153: aload_1
    //   154: invokevirtual 2312	java/io/File:mkdirs	()Z
    //   157: ifne +95 -> 252
    //   160: ldc_w 298
    //   163: new 171	java/lang/StringBuilder
    //   166: dup
    //   167: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   170: ldc_w 2314
    //   173: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   176: aload_1
    //   177: invokevirtual 2317	java/io/File:getPath	()Ljava/lang/String;
    //   180: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   183: ldc_w 2319
    //   186: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   189: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   192: invokestatic 1487	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   195: pop
    //   196: aload 4
    //   198: invokestatic 2325	miui/util/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   201: aconst_null
    //   202: invokestatic 2328	miui/util/IOUtils:closeQuietly	(Ljava/io/OutputStream;)V
    //   205: aconst_null
    //   206: areturn
    //   207: new 90	java/lang/String
    //   210: dup
    //   211: aload 6
    //   213: invokespecial 1258	java/lang/String:<init>	([B)V
    //   216: ldc_w 2330
    //   219: ldc_w 1570
    //   222: invokevirtual 1461	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   225: astore 5
    //   227: goto -153 -> 74
    //   230: aload_2
    //   231: iload_3
    //   232: iconst_1
    //   233: iadd
    //   234: aload_2
    //   235: invokevirtual 343	java/lang/String:length	()I
    //   238: invokevirtual 893	java/lang/String:substring	(II)Ljava/lang/String;
    //   241: astore_1
    //   242: aload_2
    //   243: iconst_0
    //   244: iload_3
    //   245: invokevirtual 893	java/lang/String:substring	(II)Ljava/lang/String;
    //   248: astore_2
    //   249: goto -135 -> 114
    //   252: new 2332	java/io/FileOutputStream
    //   255: dup
    //   256: aload 6
    //   258: invokespecial 2335	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   261: astore 5
    //   263: aload 4
    //   265: astore_1
    //   266: aload 5
    //   268: astore_2
    //   269: sipush 8192
    //   272: newarray <illegal type>
    //   274: astore 7
    //   276: aload 4
    //   278: astore_1
    //   279: aload 5
    //   281: astore_2
    //   282: aload 8
    //   284: aload 7
    //   286: invokevirtual 2338	java/io/FileInputStream:read	([B)I
    //   289: istore_3
    //   290: iload_3
    //   291: iconst_m1
    //   292: if_icmpeq +56 -> 348
    //   295: aload 4
    //   297: astore_1
    //   298: aload 5
    //   300: astore_2
    //   301: aload 5
    //   303: aload 7
    //   305: iconst_0
    //   306: iload_3
    //   307: invokevirtual 2342	java/io/FileOutputStream:write	([BII)V
    //   310: goto -34 -> 276
    //   313: astore_1
    //   314: aload 5
    //   316: astore_0
    //   317: aload_1
    //   318: astore 5
    //   320: aload 4
    //   322: astore_1
    //   323: aload_0
    //   324: astore_2
    //   325: ldc_w 298
    //   328: ldc_w 2344
    //   331: aload 5
    //   333: invokestatic 306	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   336: pop
    //   337: aload 4
    //   339: invokestatic 2325	miui/util/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   342: aload_0
    //   343: invokestatic 2328	miui/util/IOUtils:closeQuietly	(Ljava/io/OutputStream;)V
    //   346: aconst_null
    //   347: areturn
    //   348: aload 6
    //   350: astore_1
    //   351: aload 5
    //   353: astore_2
    //   354: aload 4
    //   356: invokestatic 2325	miui/util/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   359: aload_2
    //   360: invokestatic 2328	miui/util/IOUtils:closeQuietly	(Ljava/io/OutputStream;)V
    //   363: aload_1
    //   364: ifnull -159 -> 205
    //   367: aload_0
    //   368: new 570	android/content/Intent
    //   371: dup
    //   372: ldc_w 2346
    //   375: aload_1
    //   376: invokestatic 2350	android/net/Uri:fromFile	(Ljava/io/File;)Landroid/net/Uri;
    //   379: invokespecial 2353	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   382: invokevirtual 1649	android/content/Context:sendBroadcast	(Landroid/content/Intent;)V
    //   385: aload_1
    //   386: invokevirtual 2354	java/io/File:getName	()Ljava/lang/String;
    //   389: areturn
    //   390: astore_0
    //   391: aconst_null
    //   392: astore_1
    //   393: aload 7
    //   395: astore_2
    //   396: aload_1
    //   397: invokestatic 2325	miui/util/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   400: aload_2
    //   401: invokestatic 2328	miui/util/IOUtils:closeQuietly	(Ljava/io/OutputStream;)V
    //   404: aload_0
    //   405: athrow
    //   406: astore_0
    //   407: aload 4
    //   409: astore_1
    //   410: aload 7
    //   412: astore_2
    //   413: goto -17 -> 396
    //   416: astore_0
    //   417: goto -21 -> 396
    //   420: astore 5
    //   422: aconst_null
    //   423: astore_0
    //   424: aconst_null
    //   425: astore 4
    //   427: goto -107 -> 320
    //   430: astore 5
    //   432: aconst_null
    //   433: astore_0
    //   434: goto -114 -> 320
    //   437: aconst_null
    //   438: astore_1
    //   439: aconst_null
    //   440: astore_2
    //   441: goto -87 -> 354
    //   444: aload 6
    //   446: ifnonnull -239 -> 207
    //   449: aload_2
    //   450: astore 5
    //   452: goto -378 -> 74
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	455	0	paramContext	Context
    //   0	455	1	paramPduPart	PduPart
    //   0	455	2	paramString	String
    //   88	219	3	i	int
    //   7	419	4	localObject1	Object
    //   43	309	5	localObject2	Object
    //   420	1	5	localIOException1	IOException
    //   430	1	5	localIOException2	IOException
    //   450	1	5	str	String
    //   39	406	6	localObject3	Object
    //   1	410	7	arrayOfByte	byte[]
    //   33	250	8	localFileInputStream	java.io.FileInputStream
    // Exception table:
    //   from	to	target	type
    //   269	276	313	java/io/IOException
    //   282	290	313	java/io/IOException
    //   301	310	313	java/io/IOException
    //   9	20	390	finally
    //   20	41	406	finally
    //   50	56	406	finally
    //   65	71	406	finally
    //   74	89	406	finally
    //   94	114	406	finally
    //   114	196	406	finally
    //   207	227	406	finally
    //   230	249	406	finally
    //   252	263	406	finally
    //   269	276	416	finally
    //   282	290	416	finally
    //   301	310	416	finally
    //   325	337	416	finally
    //   9	20	420	java/io/IOException
    //   20	41	430	java/io/IOException
    //   50	56	430	java/io/IOException
    //   65	71	430	java/io/IOException
    //   74	89	430	java/io/IOException
    //   94	114	430	java/io/IOException
    //   114	196	430	java/io/IOException
    //   207	227	430	java/io/IOException
    //   230	249	430	java/io/IOException
    //   252	263	430	java/io/IOException
  }
  
  public static void selectAudio(Context paramContext, int paramInt)
  {
    if ((paramContext instanceof Activity))
    {
      Intent localIntent = new Intent("android.intent.action.RINGTONE_PICKER");
      localIntent.putExtra("android.intent.extra.ringtone.SHOW_DEFAULT", false);
      localIntent.putExtra("android.intent.extra.ringtone.SHOW_SILENT", false);
      localIntent.putExtra("android.intent.extra.ringtone.INCLUDE_DRM", false);
      localIntent.putExtra("android.intent.extra.ringtone.TITLE", paramContext.getString(2131362011));
      ((Activity)paramContext).startActivityForResult(localIntent, paramInt);
    }
  }
  
  public static void selectImage(Context paramContext, int paramInt)
  {
    selectMediaByType(paramContext, paramInt, "image/*", false);
  }
  
  private static void selectMediaByType(Context paramContext, int paramInt, String paramString, boolean paramBoolean)
  {
    if ((paramContext instanceof Activity))
    {
      Intent localIntent = new Intent("android.intent.action.GET_CONTENT");
      localIntent.setType(paramString);
      if (paramBoolean) {
        localIntent.putExtra("android.intent.extra.LOCAL_ONLY", true);
      }
      ((Activity)paramContext).startActivityForResult(localIntent, paramInt);
    }
  }
  
  public static void selectVideo(Context paramContext, int paramInt)
  {
    selectMediaByType(paramContext, paramInt, "video/*", true);
  }
  
  public static void setAllowNetworkAccess(Context paramContext, boolean paramBoolean)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putBoolean("pref_key_allow_network_access", paramBoolean).commit();
  }
  
  public static void setAttachmentAnimation(final ImageView paramImageView, Drawable paramDrawable)
  {
    if ((paramDrawable instanceof AnimationDrawable)) {
      paramImageView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener()
      {
        public boolean onPreDraw()
        {
          ((AnimationDrawable)val$drawable).start();
          paramImageView.getViewTreeObserver().removeOnPreDrawListener(this);
          return true;
        }
      });
    }
  }
  
  public static void setAttachmentImage(ImageView paramImageView, Drawable paramDrawable, boolean paramBoolean)
  {
    if (paramDrawable == null) {
      paramImageView.setImageResource(2130837759);
    }
    do
    {
      return;
      paramImageView.setImageDrawable(paramDrawable);
    } while (!paramBoolean);
    setAttachmentAnimation(paramImageView, paramDrawable);
  }
  
  public static void setListViewTouchPadding(AbsListView paramAbsListView) {}
  
  public static void setMessageSendTime(Context paramContext, long paramLong1, long paramLong2, long paramLong3)
  {
    if (paramLong2 == paramLong3) {
      return;
    }
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("date", Long.valueOf(paramLong3));
    SqliteWrapper.update(paramContext, paramContext.getContentResolver(), smsUri, localContentValues, "timed > 0 AND thread_id = " + Long.toString(paramLong1) + " AND date = " + Long.toString(paramLong2), null);
    localContentValues.put("date_full", Long.valueOf(paramLong3));
    SqliteWrapper.update(paramContext, paramContext.getContentResolver(), mmsUri, localContentValues, "timed > 0 AND thread_id = " + Long.toString(paramLong1) + " AND date*1000+date_ms_part = " + Long.toString(paramLong2), null);
  }
  
  public static void setMmsSendTime(Context paramContext, Uri paramUri, long paramLong1, long paramLong2)
  {
    ContentValues localContentValues = new ContentValues();
    if (paramLong1 > 0L)
    {
      localContentValues.put("timed", Long.valueOf(paramLong2));
      localContentValues.put("date_full", Long.valueOf(paramLong1));
    }
    for (;;)
    {
      SqliteWrapper.update(paramContext, paramContext.getContentResolver(), paramUri, localContentValues, null, null);
      return;
      localContentValues.put("timed", Integer.valueOf(0));
    }
  }
  
  public static void setNetworkRecommendDate(Context paramContext, Long paramLong)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putLong("last_network_recommend_date", paramLong.longValue()).commit();
  }
  
  public static void setNotificationIndex(Context paramContext, int paramInt)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putInt("pref_key_float_noficatin_index", paramInt).commit();
  }
  
  public static void setSmsSendTime(Context paramContext, Uri paramUri, long paramLong1, long paramLong2)
  {
    ContentValues localContentValues = new ContentValues();
    if (paramLong1 > 0L)
    {
      localContentValues.put("timed", Long.valueOf(paramLong2));
      localContentValues.put("date", Long.valueOf(paramLong1));
    }
    for (;;)
    {
      SqliteWrapper.update(paramContext, paramContext.getContentResolver(), paramUri, localContentValues, null, null);
      return;
      localContentValues.put("timed", Integer.valueOf(0));
    }
  }
  
  public static void setTextSize(float paramFloat)
  {
    Object localObject = MmsApp.getApp().getApplicationContext();
    float f1 = ((Context)localObject).getResources().getDimension(2131427389);
    float f2 = ((Context)localObject).getResources().getDimension(2131427390);
    if (paramFloat == 0.0F) {
      f1 = 0.0F;
    }
    for (;;)
    {
      localObject = PreferenceManager.getDefaultSharedPreferences((Context)localObject).edit();
      ((SharedPreferences.Editor)localObject).putFloat("message_font_size", f1);
      ((SharedPreferences.Editor)localObject).commit();
      return;
      if (paramFloat >= f1)
      {
        f1 = paramFloat;
        if (paramFloat > f2) {
          f1 = f2;
        }
      }
    }
  }
  
  public static void setWindowLayoutParams(Activity paramActivity)
  {
    paramActivity = paramActivity.getWindow().getAttributes();
    gravity = 80;
    width = -1;
    height = -2;
    flags |= 0x10000;
  }
  
  public static void setYpRecommendEnabled(Context paramContext, boolean paramBoolean)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putBoolean("yellowpage_recommend_show", paramBoolean).commit();
  }
  
  public static boolean shouldShowFestival(Context paramContext)
  {
    return (!Build.IS_INTERNATIONAL_BUILD) && ((!PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("pref_key_hide_festival", true)) || (isZhCnLanguage()));
  }
  
  public static void showDiscardDraftConfirmDialog(Context paramContext, DialogInterface.OnClickListener paramOnClickListener)
  {
    new AlertDialog.Builder(paramContext).setIconAttribute(16843605).setTitle(2131361857).setMessage(2131361858).setPositiveButton(2131361891, paramOnClickListener).setNegativeButton(2131361892, null).show();
  }
  
  public static void showErrorDialog(Context paramContext, String paramString1, String paramString2)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    localBuilder.setIcon(2130837774);
    localBuilder.setTitle(paramString1);
    localBuilder.setMessage(paramString2);
    localBuilder.setPositiveButton(17039370, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        if (paramAnonymousInt == -1) {
          paramAnonymousDialogInterface.dismiss();
        }
      }
    });
    if ((paramContext instanceof Activity))
    {
      if (!((Activity)paramContext).getWindow().isDestroyed()) {
        localBuilder.show();
      }
      return;
    }
    localBuilder.show();
  }
  
  public static void showTextWithHighlight(TextView paramTextView, String paramString1, String paramString2, int paramInt)
  {
    if (TextUtils.isEmpty(paramString2))
    {
      paramTextView.setText(paramString1);
      return;
    }
    String str = paramString1.toLowerCase();
    paramString2 = paramString2.toLowerCase().split(" ");
    paramString1 = new SpannableString(paramString1);
    int k = paramString2.length;
    int i = 0;
    while (i < k)
    {
      CharSequence localCharSequence = paramString2[i];
      if (!TextUtils.isEmpty(localCharSequence))
      {
        int j = -localCharSequence.length();
        for (;;)
        {
          j = str.indexOf(localCharSequence, j + localCharSequence.length());
          if (j == -1) {
            break;
          }
          paramString1.setSpan(new TextSizeAdjustSpan(paramTextView.getContext(), paramInt), j, localCharSequence.length() + j, 33);
        }
      }
      i += 1;
    }
    paramTextView.setText(paramString1);
  }
  
  private static void startIntent(Context paramContext, Intent paramIntent)
  {
    paramIntent.putExtra("com.android.browser.application_id", paramContext.getPackageName());
    paramIntent.setFlags(524288);
    try
    {
      paramContext.startActivity(paramIntent);
      return;
    }
    catch (ActivityNotFoundException paramIntent)
    {
      Toast.makeText(paramContext, paramContext.getString(2131362137), 0).show();
    }
  }
  
  public static void startMmsPreviewService(Context paramContext, long paramLong)
  {
    if (paramContext != null)
    {
      Intent localIntent = new Intent("android.provider.Telephony.MAKE_MMS_PREVIEW");
      localIntent.putExtra("_id", paramLong);
      localIntent.setPackage("com.android.providers.telephony");
      paramContext.startService(localIntent);
    }
  }
  
  public static int updateSmsStatus(Context paramContext, long paramLong, int paramInt)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("mx_status", Integer.valueOf(paramInt));
    if (paramInt == 16) {
      localContentValues.put("type", Integer.valueOf(2));
    }
    for (;;)
    {
      Uri localUri = ContentUris.withAppendedId(Uri.parse("content://sms/mx_status"), paramLong);
      return paramContext.getContentResolver().update(localUri, localContentValues, "mx_status!=0 AND mx_status!=" + paramInt + " AND " + "type" + "!=" + 1, null);
      if (paramInt == 17)
      {
        localContentValues.put("status", Integer.valueOf(0));
        localContentValues.put("date_sent", Long.valueOf(System.currentTimeMillis()));
      }
    }
  }
  
  public static void viewMmsMessageAttachment(Context paramContext, Uri paramUri, SlideshowModel paramSlideshowModel)
  {
    viewMmsMessageAttachment(paramContext, paramUri, paramSlideshowModel, 0);
  }
  
  private static void viewMmsMessageAttachment(Context paramContext, Uri paramUri, SlideshowModel paramSlideshowModel, int paramInt)
  {
    if (paramSlideshowModel == null) {}
    for (boolean bool = false; bool; bool = paramSlideshowModel.isSimple())
    {
      viewSimpleSlideshow(paramContext, paramSlideshowModel);
      return;
    }
    MiuiPduPersister localMiuiPduPersister;
    if (paramSlideshowModel != null) {
      localMiuiPduPersister = MiuiPduPersister.getPduPersister(paramContext);
    }
    try
    {
      PduBody localPduBody = paramSlideshowModel.toPduBody();
      localMiuiPduPersister.updateParts(paramUri, localPduBody);
      paramSlideshowModel.sync(localPduBody);
      paramSlideshowModel = new Intent(paramContext, SlideshowActivity.class);
      paramSlideshowModel.setData(paramUri);
      if ((paramInt > 0) && ((paramContext instanceof Activity)))
      {
        ((Activity)paramContext).startActivityForResult(paramSlideshowModel, paramInt);
        return;
      }
    }
    catch (MmsException paramContext)
    {
      Log.e("Mms", "Unable to save message for preview");
      return;
    }
    paramContext.startActivity(paramSlideshowModel);
  }
  
  public static void viewSimpleSlideshow(Context paramContext, SlideshowModel paramSlideshowModel)
  {
    if (!paramSlideshowModel.isSimple()) {
      throw new IllegalArgumentException("viewSimpleSlideshow() called on a non-simple slideshow");
    }
    Object localObject = paramSlideshowModel.get(0);
    paramSlideshowModel = null;
    if (((SlideModel)localObject).hasImage()) {
      paramSlideshowModel = ((SlideModel)localObject).getImage();
    }
    for (;;)
    {
      localObject = new Intent("android.intent.action.VIEW");
      ((Intent)localObject).addFlags(1);
      ((Intent)localObject).putExtra("SingleItemOnly", true);
      String str = paramSlideshowModel.getContentType();
      ((Intent)localObject).setDataAndType(paramSlideshowModel.getUri(), str);
      paramContext.startActivity((Intent)localObject);
      return;
      if (((SlideModel)localObject).hasVideo()) {
        paramSlideshowModel = ((SlideModel)localObject).getVideo();
      }
    }
  }
  
  public static void writeHprofDataToFile()
  {
    String str = Environment.getExternalStorageDirectory() + "/mms_oom_hprof_data";
    try
    {
      Debug.dumpHprofData(str);
      Log.i("Mms", "##### written hprof data to " + str);
      return;
    }
    catch (IOException localIOException)
    {
      Log.e("Mms", "writeHprofDataToFile: caught " + localIOException);
    }
  }
  
  private static final class MmsReportRequest
  {
    private final boolean mIsDeliveryReportRequsted;
    private final boolean mIsReadReportRequested;
    private final String mRecipient;
    
    public MmsReportRequest(String paramString, int paramInt1, int paramInt2)
    {
      mRecipient = paramString;
      if (paramInt1 == 128)
      {
        bool1 = true;
        mIsDeliveryReportRequsted = bool1;
        if (paramInt2 != 128) {
          break label52;
        }
      }
      label52:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        mIsReadReportRequested = bool1;
        return;
        bool1 = false;
        break;
      }
    }
    
    public String getRecipient()
    {
      return mRecipient;
    }
    
    public boolean isReadReportRequested()
    {
      return mIsReadReportRequested;
    }
  }
  
  static final class MmsReportStatus
  {
    final int deliveryStatus;
    final int readStatus;
    
    public MmsReportStatus(int paramInt1, int paramInt2)
    {
      deliveryStatus = paramInt1;
      readStatus = paramInt2;
    }
  }
  
  static abstract interface ResizeImageResultCallback
  {
    public abstract void onResizeResult(PduPart paramPduPart, boolean paramBoolean);
  }
  
  public static class UriInfo
  {
    public Contact contact;
    public String content;
    public String formatContent;
    public int scheme;
    public String title;
    public Uri uri;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */