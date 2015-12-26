package com.android.mms.transaction;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.MiuiNotification;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SqliteWrapper;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.MiuiSettings.AntiSpam;
import android.provider.MiuiSettings.System;
import android.provider.Settings.System;
import android.provider.Telephony.Mms;
import android.provider.Telephony.Mms.Inbox;
import android.provider.Telephony.Sms;
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
import com.android.mms.understand.UnderstandMessage.Item;
import com.android.mms.understand.UnderstandService;
import com.android.mms.util.AddressUtils;
import com.android.mms.util.MSimUtils;
import com.android.mms.util.NotificationHelper;
import com.android.mms.util.Reminder;
import com.android.mms.util.VibratorManager;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.xiaomi.mms.transaction.MipubThread;
import com.xiaomi.mms.transaction.MipubThread.Mipub;
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

public class MessagingNotification
{
  private static String CANCEL_FLOAT_INDEX = "float_notification_index";
  public static int CANCEL_FLOAT_MSGID_DEFAULT = 0;
  private static final MmsSmsNotificationInfoComparator INFO_COMPARATOR;
  private static final String[] MMS_STATUS_PROJECTION = { "thread_id", "date_full", "_id", "sub", "sub_cs", "block_type", "sim_id", "mx_type" };
  private static final String[] SMS_STATUS_PROJECTION = { "thread_id", "date", "_id", "subject", "body", "block_type", "sim_id", "address" };
  private static final Uri UNDELIVERED_URI;
  private static Runnable mCancelFloatNotification;
  private static Handler mUIHandler;
  private static int sCurrentFloatId;
  private static long sCurrentMessageThreadId = 0L;
  private static Object sCurrentMessageThreadIdLock = new Object();
  private static BroadcastReceiver sFloatNotificationCancelReceiver;
  private static OnDeletedReceiver sNotificationDeletedReceiver;
  private static Intent sNotificationOnDeleteIntent;
  
  static
  {
    INFO_COMPARATOR = new MmsSmsNotificationInfoComparator(null);
    UNDELIVERED_URI = Uri.parse("content://mms-sms/undelivered").buildUpon().appendQueryParameter("privacy_flag", "0").build();
    sNotificationDeletedReceiver = new OnDeletedReceiver();
    mUIHandler = new Handler();
    sFloatNotificationCancelReceiver = new BroadcastReceiver()
    {
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        if ((paramAnonymousIntent != null) && (paramAnonymousIntent.getIntExtra(MessagingNotification.CANCEL_FLOAT_INDEX, MessagingNotification.CANCEL_FLOAT_MSGID_DEFAULT) == MessagingNotification.sCurrentFloatId))
        {
          MessagingNotification.cancelFloatNotification(paramAnonymousContext);
          MessagingNotification.mUIHandler.removeCallbacks(MessagingNotification.mCancelFloatNotification);
        }
      }
    };
    mCancelFloatNotification = new Runnable()
    {
      public void run()
      {
        MessagingNotification.cancelFloatNotification(MmsApp.getApp());
      }
    };
  }
  
  private static final int accumulateNotificationInfo(SortedSet paramSortedSet, MmsSmsNotificationInfo paramMmsSmsNotificationInfo)
  {
    if (paramMmsSmsNotificationInfo != null)
    {
      paramSortedSet.add(paramMmsSmsNotificationInfo);
      return mCount;
    }
    return 0;
  }
  
  public static void blockingUpdateAllNotifications(Context paramContext)
  {
    if (Thread.currentThread().isInterrupted()) {}
    do
    {
      do
      {
        return;
        blockingUpdateNewMessageIndicator(paramContext, false, false);
      } while (Thread.currentThread().isInterrupted());
      updateSendFailedNotification(paramContext);
    } while (Thread.currentThread().isInterrupted());
    updateDownloadFailedNotification(paramContext);
  }
  
  public static void blockingUpdateNewMessageIndicator(Context paramContext, boolean paramBoolean1, boolean paramBoolean2)
  {
    TreeSet localTreeSet = new TreeSet(INFO_COMPARATOR);
    HashSet localHashSet = new HashSet(4);
    int i = 0 + accumulateNotificationInfo(localTreeSet, getMmsNewMessageNotificationInfo(paramContext, localHashSet)) + accumulateNotificationInfo(localTreeSet, getSmsNewMessageNotificationInfo(paramContext, localHashSet)) + accumulateNotificationInfo(localTreeSet, getMipubNewMessageNotificationInfo(paramContext, localHashSet));
    cancelNotification(paramContext, 123);
    cancelNotification(paramContext, 124);
    if (!localTreeSet.isEmpty())
    {
      if (Log.isLoggable("Mms:app", 2)) {
        Log.d("Mms:app", "blockingUpdateNewMessageIndicator: count=" + i + ", isNew=" + paramBoolean1);
      }
      ((MmsSmsNotificationInfo)localTreeSet.first()).deliver(paramContext, paramBoolean1, i, localHashSet);
    }
  }
  
  private static final Intent buildCopyTextIntent(Context paramContext, String paramString, Uri paramUri)
  {
    Intent localIntent = null;
    if (!TextUtils.isEmpty(paramString))
    {
      localIntent = new Intent(paramContext, UnderstandService.class);
      localIntent.setPackage(paramContext.getPackageName());
      localIntent.putExtra("extra_text", paramString);
      localIntent.setData(paramUri);
    }
    return localIntent;
  }
  
  private static final Intent buildFullScreenIntent(Context paramContext, String paramString1, String paramString2, boolean paramBoolean, long paramLong, int paramInt, Uri paramUri)
  {
    Intent localIntent = null;
    if (!TextUtils.isEmpty(paramString1))
    {
      localIntent = new Intent(paramContext, NewMessagePopupActivity.class);
      localIntent.putExtra("from", paramString1);
      localIntent.putExtra("body", paramString2);
      localIntent.putExtra("time", paramLong);
      localIntent.putExtra(MSimUtils.SLOT_ID, paramInt);
      localIntent.putExtra("showBody", paramBoolean);
      localIntent.setData(paramUri);
      localIntent.setFlags(872415232);
    }
    return localIntent;
  }
  
  private static RemoteViews buildRemoteViewsWithButton(Context paramContext, int paramInt, PendingIntent paramPendingIntent)
  {
    RemoteViews localRemoteViews = new RemoteViews(paramContext.getPackageName(), 2130968623);
    localRemoteViews.setTextViewText(2131820659, paramContext.getString(paramInt));
    localRemoteViews.setOnClickPendingIntent(2131820659, paramPendingIntent);
    return localRemoteViews;
  }
  
  protected static CharSequence buildTickerMessage(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    paramContext = Contact.get(paramString1).load(true, true).getName();
    if (paramContext == null) {}
    for (paramContext = "";; paramContext = paramContext.replace('\n', ' ').replace('\r', ' '))
    {
      paramContext = new StringBuilder(paramContext);
      paramContext.append(':').append(' ');
      int i = paramContext.length();
      if (!TextUtils.isEmpty(paramString2))
      {
        paramContext.append(paramString2.replace('\n', ' ').replace('\r', ' '));
        paramContext.append(' ');
      }
      if (!TextUtils.isEmpty(paramString3)) {
        paramContext.append(paramString3.replace('\n', ' ').replace('\r', ' '));
      }
      paramContext = new SpannableString(paramContext.toString());
      paramContext.setSpan(new StyleSpan(1), 0, i, 33);
      return paramContext;
    }
  }
  
  public static void cancelFloatNotification(Context paramContext)
  {
    ((NotificationManager)paramContext.getSystemService("notification")).cancel(124);
  }
  
  public static void cancelNotification(Context paramContext, int paramInt)
  {
    VibratorManager.cancel();
    ((NotificationManager)paramContext.getSystemService("notification")).cancel(paramInt);
    if (paramInt == 123) {
      Reminder.cancelReminder(paramContext);
    }
  }
  
  private static int getDownloadFailedMessageCount(Context paramContext)
  {
    paramContext = SqliteWrapper.query(paramContext, paramContext.getContentResolver(), Telephony.Mms.Inbox.CONTENT_URI, null, "m_type=" + String.valueOf(130) + " AND " + "st" + "=" + String.valueOf(135), null, null);
    if (paramContext == null) {
      return 0;
    }
    int i = paramContext.getCount();
    paramContext.close();
    return i;
  }
  
  private static int[] getLedPwmOffOn(int paramInt)
  {
    int[] arrayOfInt = new int[2];
    arrayOfInt[0] = (paramInt / 4 * 3);
    arrayOfInt[1] = (paramInt - arrayOfInt[0]);
    return arrayOfInt;
  }
  
  private static final MmsSmsNotificationInfo getMipubNewMessageNotificationInfo(Context paramContext, Set<MsgThread> paramSet)
  {
    String str1 = null;
    Cursor localCursor = SqliteWrapper.query(paramContext, paramContext.getContentResolver(), MipubThread.Mipub.CONTENT_URI, MipubThread.Mipub.STATUS_PROJECTION, "(seen = 0)", null, "date desc");
    Object localObject = str1;
    if (localCursor != null)
    {
      localObject = str1;
      for (;;)
      {
        try
        {
          if (!localCursor.moveToFirst()) {
            break;
          }
          int j = localCursor.getColumnIndex("thread_id");
          long l1 = localCursor.getLong(j);
          long l2 = localCursor.getLong(localCursor.getColumnIndex("date"));
          long l3 = localCursor.getLong(localCursor.getColumnIndex("_id"));
          str1 = localCursor.getString(localCursor.getColumnIndex("snippet"));
          String str2 = localCursor.getString(localCursor.getColumnIndex("address"));
          if (MiuiSettings.AntiSpam.isQuietModeEnable(paramContext))
          {
            i = 1;
            Uri localUri = MipubThread.Mipub.CONTENT_URI.buildUpon().appendPath(Long.toString(l3)).build();
            localObject = new MipubThread(l1, str2);
            paramContext = getNewMessageNotificationInfo(str2, str1, paramContext, 2130838002, null, (MsgThread)localObject, l2, localCursor.getCount(), i, 0, localUri, false, true);
            paramSet.add(localObject);
            localObject = paramContext;
            if (!localCursor.moveToNext()) {
              break;
            }
            paramSet.add(new MipubThread(localCursor.getLong(j), null));
            continue;
          }
          int i = 0;
        }
        finally
        {
          if ((localCursor != null) && (!localCursor.isClosed())) {
            localCursor.close();
          }
        }
      }
    }
    if ((localCursor != null) && (!localCursor.isClosed())) {
      localCursor.close();
    }
    return (MmsSmsNotificationInfo)localObject;
  }
  
  private static final MmsSmsNotificationInfo getMmsNewMessageNotificationInfo(Context paramContext, Set<MsgThread> paramSet)
  {
    Cursor localCursor = SqliteWrapper.query(paramContext, paramContext.getContentResolver(), Telephony.Mms.CONTENT_URI, MMS_STATUS_PROJECTION, "(msg_box=1 AND seen=0 AND (m_type=130 OR m_type=132))", null, "date_full desc");
    if (localCursor == null) {
      return null;
    }
    Object localObject1;
    for (;;)
    {
      try
      {
        boolean bool = localCursor.moveToFirst();
        if (!bool) {
          return null;
        }
        long l1 = localCursor.getLong(2);
        Uri localUri = Telephony.Mms.CONTENT_URI.buildUpon().appendPath(Long.toString(l1)).build();
        String str2 = AddressUtils.getFrom(paramContext, localUri);
        Contact localContact = Contact.get(str2).load(true, true);
        bool = localContact.getSendToVoicemail();
        if (bool) {
          return null;
        }
        String str1 = getMmsSubject(localCursor.getString(3), localCursor.getInt(4));
        int i = localCursor.getInt(7);
        localObject1 = str1;
        if (i > 0)
        {
          localObject2 = MxMessageUtils.getNotificationDescByMx2Type(i);
          localObject1 = str1;
          if (!TextUtils.isEmpty((CharSequence)localObject2)) {
            localObject1 = (String)localObject2 + str1;
          }
        }
        l1 = localCursor.getLong(0);
        long l2 = localCursor.getLong(1);
        i = localCursor.getInt(5);
        int j = MSimUtils.getSlotIdBySimInfoId(localCursor.getLong(6));
        str1 = null;
        Object localObject2 = new MmsThread(l1, str2);
        if (MessageUtils.checkPrivateMessage(paramContext, localContact.getNumber()))
        {
          localObject1 = str1;
          if (MessageUtils.getPrefPrivateNotificationEnabled(paramContext)) {
            localObject1 = getNewMessageNotificationInfo("", "", paramContext, 2130838002, null, (MsgThread)localObject2, l2, localCursor.getCount(), i, j, localUri, true, MessageUtils.getPrefNotificationBodyEnabledWithSecure(paramContext));
          }
          paramSet.add(localObject2);
          if (!localCursor.moveToNext()) {
            break;
          }
          paramSet.add(new MmsThread(localCursor.getLong(0), null));
          continue;
        }
        localObject1 = getNewMessageNotificationInfo(str2, (String)localObject1, paramContext, 2130838002, null, (MsgThread)localObject2, l2, localCursor.getCount(), i, j, localUri, false, MessageUtils.getPrefNotificationBodyEnabledWithSecure(paramContext));
      }
      finally
      {
        localCursor.close();
      }
    }
    localCursor.close();
    return (MmsSmsNotificationInfo)localObject1;
  }
  
  private static String getMmsSubject(String paramString, int paramInt)
  {
    if (TextUtils.isEmpty(paramString)) {
      return "";
    }
    return new EncodedStringValue(paramInt, MiuiPduPersister.getBytes(paramString)).getString();
  }
  
  private static int getMsgThreadClassCounts(Set<MsgThread> paramSet)
  {
    HashSet localHashSet = new HashSet();
    paramSet = paramSet.iterator();
    while (paramSet.hasNext()) {
      localHashSet.add(((MsgThread)paramSet.next()).getClass().getSimpleName());
    }
    return localHashSet.size();
  }
  
  private static final MmsSmsNotificationInfo getNewMessageNotificationInfo(String paramString1, String paramString2, Context paramContext, int paramInt1, String paramString3, MsgThread paramMsgThread, long paramLong, int paramInt2, int paramInt3, int paramInt4, Uri paramUri, boolean paramBoolean1, boolean paramBoolean2)
  {
    Intent localIntent1;
    Intent localIntent2;
    if ((mThreadId > 0L) && (!paramBoolean1))
    {
      localIntent1 = paramMsgThread.getConversationClickIntent(paramContext);
      localIntent2 = buildFullScreenIntent(paramContext, paramString1, paramString2, paramBoolean2, paramLong, paramInt4, paramUri);
      if ((paramBoolean2) && (!TextUtils.isEmpty(paramString2))) {
        break label136;
      }
      paramString2 = paramContext.getString(2131362056);
    }
    label136:
    for (;;)
    {
      String str = paramContext.getString(2131362003);
      if (!TextUtils.isEmpty(paramString1)) {
        str = paramMsgThread.getSenderName(paramContext, paramString1);
      }
      return new MmsSmsNotificationInfo(localIntent1, localIntent2, paramString2, paramInt1, buildTickerMessage(paramContext, paramString1, paramString3, paramString2), paramLong, str, paramInt2, paramInt3, paramMsgThread, paramInt4, paramUri);
      localIntent1 = new Intent(paramContext, MmsTabActivity.class);
      break;
    }
  }
  
  /* Error */
  private static final MmsSmsDeliveryInfo getSmsNewDeliveryInfo(Context paramContext, Uri paramUri)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +5 -> 6
    //   4: aconst_null
    //   5: areturn
    //   6: aload_0
    //   7: aload_0
    //   8: invokevirtual 454	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   11: aload_1
    //   12: getstatic 86	com/android/mms/transaction/MessagingNotification:SMS_STATUS_PROJECTION	[Ljava/lang/String;
    //   15: aconst_null
    //   16: aconst_null
    //   17: aconst_null
    //   18: invokestatic 476	android/database/sqlite/SqliteWrapper:query	(Landroid/content/Context;Landroid/content/ContentResolver;Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   21: astore 6
    //   23: aload 6
    //   25: ifnonnull +5 -> 30
    //   28: aconst_null
    //   29: areturn
    //   30: aload 6
    //   32: invokeinterface 499 1 0
    //   37: istore_3
    //   38: iload_3
    //   39: ifne +12 -> 51
    //   42: aload 6
    //   44: invokeinterface 484 1 0
    //   49: aconst_null
    //   50: areturn
    //   51: aload 6
    //   53: bipush 7
    //   55: invokeinterface 510 2 0
    //   60: astore 4
    //   62: aload 4
    //   64: invokestatic 393	com/android/mms/data/Contact:get	(Ljava/lang/String;)Lcom/android/mms/data/Contact;
    //   67: iconst_1
    //   68: iconst_1
    //   69: invokevirtual 397	com/android/mms/data/Contact:load	(ZZ)Lcom/android/mms/data/Contact;
    //   72: astore_1
    //   73: aload_1
    //   74: invokevirtual 664	com/android/mms/data/Contact:getRealName	()Ljava/lang/String;
    //   77: astore 5
    //   79: aload 6
    //   81: bipush 6
    //   83: invokeinterface 507 2 0
    //   88: invokestatic 578	com/android/mms/util/MSimUtils:getSlotIdBySimInfoId	(J)I
    //   91: istore_2
    //   92: aload_1
    //   93: invokevirtual 667	com/android/mms/data/Contact:isB2cNumber	()Z
    //   96: ifeq +64 -> 160
    //   99: aload 5
    //   101: invokestatic 309	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   104: ifeq +50 -> 154
    //   107: aload_0
    //   108: ldc_w 668
    //   111: invokevirtual 377	android/content/Context:getString	(I)Ljava/lang/String;
    //   114: astore_1
    //   115: new 16	com/android/mms/transaction/MessagingNotification$MmsSmsDeliveryInfo
    //   118: dup
    //   119: aload_0
    //   120: ldc_w 669
    //   123: invokevirtual 377	android/content/Context:getString	(I)Ljava/lang/String;
    //   126: iconst_1
    //   127: anewarray 4	java/lang/Object
    //   130: dup
    //   131: iconst_0
    //   132: aload_1
    //   133: aastore
    //   134: invokestatic 673	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   137: ldc2_w 674
    //   140: iload_2
    //   141: invokespecial 678	com/android/mms/transaction/MessagingNotification$MmsSmsDeliveryInfo:<init>	(Ljava/lang/CharSequence;JI)V
    //   144: astore_0
    //   145: aload 6
    //   147: invokeinterface 484 1 0
    //   152: aload_0
    //   153: areturn
    //   154: aload 5
    //   156: astore_1
    //   157: goto -42 -> 115
    //   160: aload 4
    //   162: astore_1
    //   163: aload 5
    //   165: invokestatic 309	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   168: ifne -53 -> 115
    //   171: new 272	java/lang/StringBuilder
    //   174: dup
    //   175: invokespecial 273	java/lang/StringBuilder:<init>	()V
    //   178: aload 5
    //   180: invokevirtual 279	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   183: ldc_w 680
    //   186: invokevirtual 279	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   189: aload 4
    //   191: invokevirtual 279	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   194: ldc_w 682
    //   197: invokevirtual 279	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   200: invokevirtual 290	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   203: astore_1
    //   204: goto -89 -> 115
    //   207: astore_0
    //   208: aload 6
    //   210: invokeinterface 484 1 0
    //   215: aload_0
    //   216: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	217	0	paramContext	Context
    //   0	217	1	paramUri	Uri
    //   91	50	2	i	int
    //   37	2	3	bool	boolean
    //   60	130	4	str1	String
    //   77	102	5	str2	String
    //   21	188	6	localCursor	Cursor
    // Exception table:
    //   from	to	target	type
    //   30	38	207	finally
    //   51	115	207	finally
    //   115	145	207	finally
    //   163	204	207	finally
  }
  
  private static final MmsSmsNotificationInfo getSmsNewMessageNotificationInfo(Context paramContext, Set<MsgThread> paramSet)
  {
    Cursor localCursor = SqliteWrapper.query(paramContext, paramContext.getContentResolver(), Telephony.Sms.CONTENT_URI, SMS_STATUS_PROJECTION, "(type = 1 AND seen = 0)", null, "date desc");
    if (localCursor == null) {
      return null;
    }
    MmsSmsNotificationInfo localMmsSmsNotificationInfo;
    for (;;)
    {
      try
      {
        boolean bool = localCursor.moveToFirst();
        if (!bool) {
          return null;
        }
        String str1 = localCursor.getString(7);
        Contact localContact = Contact.get(str1).load(true, true);
        bool = localContact.getSendToVoicemail();
        if (bool) {
          return null;
        }
        long l1 = localCursor.getLong(2);
        Uri localUri = Telephony.Sms.CONTENT_URI.buildUpon().appendPath(Long.toString(l1)).build();
        String str2 = localCursor.getString(4);
        l1 = localCursor.getLong(0);
        long l2 = localCursor.getLong(1);
        int i = localCursor.getInt(5);
        int j = MSimUtils.getSlotIdBySimInfoId(localCursor.getLong(6));
        localMmsSmsNotificationInfo = null;
        MmsThread localMmsThread = new MmsThread(l1, str1);
        if (MessageUtils.checkPrivateMessage(paramContext, localContact.getNumber()))
        {
          if (MessageUtils.getPrefPrivateNotificationEnabled(paramContext)) {
            localMmsSmsNotificationInfo = getNewMessageNotificationInfo("", "", paramContext, 2130838003, null, localMmsThread, l2, localCursor.getCount(), i, j, localUri, true, MessageUtils.getPrefNotificationBodyEnabledWithSecure(paramContext));
          }
          paramSet.add(localMmsThread);
          if (!localCursor.moveToNext()) {
            break;
          }
          paramSet.add(new MmsThread(localCursor.getLong(0), null));
          continue;
        }
        localMmsSmsNotificationInfo = getNewMessageNotificationInfo(str1, str2, paramContext, 2130838003, null, localMmsThread, l2, localCursor.getCount(), i, j, localUri, false, MessageUtils.getPrefNotificationBodyEnabledWithSecure(paramContext));
      }
      finally
      {
        localCursor.close();
      }
    }
    localCursor.close();
    return localMmsSmsNotificationInfo;
  }
  
  private static Uri getSmsSound(Context paramContext, int paramInt1, int paramInt2)
  {
    switch (paramInt1)
    {
    default: 
      return null;
    case 16: 
      return SimRingtoneUtils.getDefaultSmsReceivedUri(paramContext, paramInt2);
    }
    return SimRingtoneUtils.getDefaultSmsDeliveredUri(paramContext, paramInt2);
  }
  
  private static int getUndeliveredMessageCount(Context paramContext, long[] paramArrayOfLong)
  {
    paramContext = SqliteWrapper.query(paramContext, paramContext.getContentResolver(), UNDELIVERED_URI, new String[] { "thread_id" }, "read=0", null, null);
    if (paramContext == null) {
      return 0;
    }
    int i = paramContext.getCount();
    if (paramArrayOfLong != null) {}
    try
    {
      if (paramContext.moveToFirst())
      {
        paramArrayOfLong[0] = paramContext.getLong(0);
        if (paramArrayOfLong.length >= 2)
        {
          long l2 = paramArrayOfLong[0];
          do
          {
            l1 = l2;
            if (!paramContext.moveToNext()) {
              break;
            }
          } while (paramContext.getLong(0) == l2);
          long l1 = 0L;
          paramArrayOfLong[1] = l1;
        }
      }
      return i;
    }
    finally
    {
      paramContext.close();
    }
  }
  
  public static void init(Context paramContext)
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("com.android.mms.NOTIFICATION_DELETED_ACTION");
    paramContext.registerReceiver(sNotificationDeletedReceiver, localIntentFilter);
    localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("com.android.mms.NOTIFICATION_FLOAT_NOTIFICATION_CANCEL_ACTION");
    paramContext.registerReceiver(sFloatNotificationCancelReceiver, localIntentFilter);
    sNotificationOnDeleteIntent = new Intent("com.android.mms.NOTIFICATION_DELETED_ACTION");
    sCurrentFloatId = MessageUtils.getNotificationIndex(paramContext);
  }
  
  private static boolean isCurrentMessageThreadId(MsgThread paramMsgThread)
  {
    for (;;)
    {
      synchronized (sCurrentMessageThreadIdLock)
      {
        if ((mThreadId > 0L) && (mThreadId == sCurrentMessageThreadId) && ((paramMsgThread instanceof MmsThread)))
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
  
  public static boolean isFailedToDeliver(Intent paramIntent)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramIntent != null)
    {
      bool1 = bool2;
      if (paramIntent.getBooleanExtra("undelivered_flag", false)) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static boolean isFailedToDownload(Intent paramIntent)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramIntent != null)
    {
      bool1 = bool2;
      if (paramIntent.getBooleanExtra("failed_download_flag", false)) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  private static boolean isFloatNotificationEnabled(Context paramContext)
  {
    if ((!MessageUtils.isAccessControlledOrPrivacyModeEnabled(paramContext)) && (MessageUtils.getPrefNotificationEnabled(paramContext)) && (!MiuiSettings.AntiSpam.isQuietModeEnable(paramContext))) {}
    for (boolean bool = true;; bool = false)
    {
      Log.d("Mms:app", "isFloatNotificationEnabled is " + String.valueOf(bool));
      return bool;
    }
  }
  
  private static boolean isMmsActivityOnTop(Context paramContext)
  {
    Object localObject = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(1);
    if (((List)localObject).isEmpty()) {
      return false;
    }
    paramContext = get0topActivity;
    localObject = get0baseActivity;
    if ("com.android.mms".equals(paramContext.getPackageName()))
    {
      paramContext = paramContext.getClassName();
      if (isMmsSettingsActivityOnTop(paramContext)) {
        return "com.android.mms".equals(((ComponentName)localObject).getPackageName());
      }
      if (!"com.android.mms.ui.NewMessagePopupActivity".equals(paramContext)) {
        return true;
      }
    }
    return false;
  }
  
  private static boolean isMmsSettingsActivityOnTop(String paramString)
  {
    if ((paramString.equals("com.android.mms.ui.MessagingAdvancedPreferenceActivity")) || (paramString.equals("com.android.mms.ui.MessagingPreferenceActivity")) || (paramString.equals("com.android.mms.ui.ManageSimMessages")) || (paramString.equals("com.android.mms.ui.MxFeedbackActivity")) || (paramString.equals("com.android.mms.ui.MxPreferenceActivity"))) {}
    while ((MSimUtils.isMSim()) && ((paramString.equals("com.android.mms.ui.SelectCardPreferenceActivity")) || (paramString.equals("com.android.mms.ui.MultiSimPreferenceAcitvity")))) {
      return true;
    }
    return false;
  }
  
  private static boolean isNewMessagePopupOnTop(Context paramContext)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(1);
    if (paramContext.isEmpty()) {
      return false;
    }
    paramContext = get0topActivity;
    return (paramContext.getPackageName().equals("com.android.mms")) && (paramContext.getClassName().equals("com.android.mms.ui.NewMessagePopupActivity"));
  }
  
  public static void nonBlockingUpdateDeliveryInfo(Context paramContext, final Uri paramUri)
  {
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
    if (Build.IS_CM_CUSTOMIZATION_TEST) {}
    for (boolean bool = false; !localSharedPreferences.getBoolean("pref_key_delivery_reports", bool); bool = true) {
      return;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        MessagingNotification.MmsSmsDeliveryInfo localMmsSmsDeliveryInfo = MessagingNotification.getSmsNewDeliveryInfo(val$context, paramUri);
        if (localMmsSmsDeliveryInfo != null) {
          localMmsSmsDeliveryInfo.deliver(val$context, true);
        }
      }
    }).start();
  }
  
  public static void nonBlockingUpdateNewMessageIndicator(Context paramContext, final boolean paramBoolean1, final boolean paramBoolean2)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        MessagingNotification.blockingUpdateNewMessageIndicator(val$context, paramBoolean1, paramBoolean2);
      }
    }).start();
  }
  
  public static void notifyDownloadFailed(Context paramContext, long paramLong)
  {
    notifyFailed(paramContext, true, paramLong, true, 0);
  }
  
  private static void notifyFailed(Context paramContext, boolean paramBoolean1, long paramLong, boolean paramBoolean2, int paramInt)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext);
    if (!MessageUtils.getPrefNotificationEnabled(paramContext)) {}
    NotificationManager localNotificationManager;
    long[] arrayOfLong;
    int j;
    do
    {
      do
      {
        return;
      } while (MessageUtils.checkPrivateMessage(paramContext, paramLong));
      localNotificationManager = (NotificationManager)paramContext.getSystemService("notification");
      arrayOfLong = new long[2];
      long[] tmp40_38 = arrayOfLong;
      tmp40_38[0] = 0L;
      long[] tmp44_40 = tmp40_38;
      tmp44_40[1] = 1L;
      tmp44_40;
      j = getUndeliveredMessageCount(paramContext, tmp40_38);
    } while ((j == 0) && (!paramBoolean1));
    int i;
    Notification localNotification;
    Object localObject1;
    label110:
    Object localObject2;
    label123:
    Object localObject3;
    if ((tmp40_38[1] != 0L) || (paramBoolean1))
    {
      i = 1;
      localNotification = new Notification();
      if (j <= 1) {
        break label306;
      }
      if (!paramBoolean1) {
        break label270;
      }
      localObject1 = paramContext.getString(2131362008);
      if (!paramBoolean1) {
        break label294;
      }
      localObject2 = paramContext.getString(2131362007);
      localObject3 = localObject2;
      localObject2 = localObject1;
      if (i == 0) {
        break label379;
      }
      if (!paramBoolean1) {
        break label347;
      }
      localObject1 = ComposeMessageRouterActivity.createIntent(paramContext, paramLong);
      ((Intent)localObject1).putExtra("failed_download_flag", true);
      ((Intent)localObject1).setAction("downloading_failed_action");
    }
    for (;;)
    {
      ((Intent)localObject1).addFlags(67108864);
      localObject1 = PendingIntent.getActivity(paramContext, 0, (Intent)localObject1, 134217728);
      icon = 2130838004;
      tickerText = ((CharSequence)localObject3);
      localNotification.setLatestEventInfo(paramContext, (CharSequence)localObject3, (CharSequence)localObject2, (PendingIntent)localObject1);
      if (paramBoolean2)
      {
        VibratorManager.vibrate(paramContext);
        sound = getSmsSound(paramContext, 16, paramInt);
      }
      if (!paramBoolean1) {
        break label395;
      }
      flags |= 0x10;
      localNotificationManager.notify(531, localNotification);
      return;
      i = 0;
      break;
      label270:
      localObject1 = paramContext.getString(2131361987, new Object[] { Integer.toString(j) });
      break label110;
      label294:
      localObject2 = paramContext.getString(2131361988);
      break label123;
      label306:
      if (paramBoolean1) {}
      for (localObject1 = paramContext.getString(2131362007);; localObject1 = paramContext.getString(2131362008))
      {
        localObject2 = paramContext.getString(2131362009);
        localObject3 = localObject1;
        break;
      }
      label347:
      localObject1 = ComposeMessageRouterActivity.createIntent(paramContext, tmp40_38[0]);
      ((Intent)localObject1).putExtra("undelivered_flag", true);
      ((Intent)localObject1).setAction("sending_failed_action");
      continue;
      label379:
      localObject1 = new Intent(paramContext, MmsTabActivity.class);
    }
    label395:
    localNotificationManager.notify(789, localNotification);
  }
  
  public static void notifySendFailed(Context paramContext, boolean paramBoolean)
  {
    notifyFailed(paramContext, false, 0L, paramBoolean, 0);
  }
  
  public static void notifySendFailed(Context paramContext, boolean paramBoolean, int paramInt)
  {
    notifyFailed(paramContext, false, 0L, paramBoolean, paramInt);
  }
  
  private static void playDeliveryReportRingTone(Context paramContext, int paramInt1, int paramInt2)
  {
    Log.d("Mms:app", "play delivery report ring tone with block type = " + paramInt1);
    playSmsSoundRingTone(paramContext, paramInt1, 8, paramInt2);
  }
  
  private static void playReceivedRingTone(Context paramContext, int paramInt1, int paramInt2)
  {
    Log.d("Mms:app", "play received ring tone with block type = " + paramInt1);
    playSmsSoundRingTone(paramContext, paramInt1, 16, paramInt2);
  }
  
  private static void playSmsSoundRingTone(Context paramContext, int paramInt1, int paramInt2, int paramInt3)
  {
    if ((MiuiSettings.AntiSpam.isQuietModeEnable(paramContext)) || (paramInt1 == 1)) {
      Log.d("Mms:app", "enable Quiet mode or blockType is NONE_BUT_MUTE");
    }
    do
    {
      Uri localUri;
      do
      {
        return;
        localUri = getSmsSound(paramContext, paramInt2, paramInt3);
      } while (localUri == null);
      paramContext = RingtoneManager.getRingtone(paramContext, localUri);
    } while (paramContext == null);
    paramContext.setStreamType(5);
    paramContext.play();
  }
  
  public static void setCurrentMessageThreadId(long paramLong)
  {
    synchronized (sCurrentMessageThreadIdLock)
    {
      Log.d("Mms:app", "set sCurrentMessageThreadId = " + paramLong);
      sCurrentMessageThreadId = paramLong;
      return;
    }
  }
  
  private static void setNotificationDefault(Context paramContext, Notification paramNotification)
  {
    flags |= 0x1;
    int i = Settings.System.getInt(paramContext.getContentResolver(), "mms_breathing_light_color", MiuiSettings.System.CALL_BREATHING_LIGHT_COLOR_DEFAULT);
    int j = Settings.System.getInt(paramContext.getContentResolver(), "mms_breathing_light_freq", MiuiSettings.System.CALL_BREATHING_LIGHT_FREQ_DEFAULT);
    ledARGB = i;
    paramContext = getLedPwmOffOn(j);
    ledOffMS = paramContext[0];
    ledOnMS = paramContext[1];
    defaults &= 0xFFFFFFFB;
  }
  
  private static void updateDeliveryNotification(Context paramContext, boolean paramBoolean, final CharSequence paramCharSequence, final long paramLong, final int paramInt)
  {
    if (!paramBoolean) {}
    while (!MessageUtils.getPrefNotificationEnabled(paramContext)) {
      return;
    }
    mUIHandler.post(new Runnable()
    {
      public void run()
      {
        MessagingNotification.playDeliveryReportRingTone(val$context, 0, paramInt);
        Toast.makeText(val$context, paramCharSequence, (int)paramLong).show();
      }
    });
  }
  
  public static void updateDownloadFailedNotification(Context paramContext)
  {
    if (getDownloadFailedMessageCount(paramContext) < 1) {
      cancelNotification(paramContext, 531);
    }
  }
  
  private static void updateFloatNotification(Context paramContext, Intent paramIntent1, Intent paramIntent2, String paramString1, int paramInt, boolean paramBoolean, long paramLong, String paramString2, Uri paramUri)
  {
    Notification.Builder localBuilder;
    int i;
    if ((isFloatNotificationEnabled(paramContext)) && (paramBoolean) && (paramIntent2 != null))
    {
      mUIHandler.removeCallbacks(mCancelFloatNotification);
      paramIntent1 = PendingIntent.getActivity(paramContext, 0, paramIntent1, 134217728);
      localBuilder = new Notification.Builder(paramContext);
      localBuilder.setDefaults(4);
      localBuilder.setContentTitle(paramString2);
      localBuilder.setContentText(paramString1);
      localBuilder.setContentIntent(paramIntent1);
      localBuilder.setSmallIcon(paramInt);
      localBuilder.setWhen(paramLong);
      localBuilder.setAutoCancel(true);
      paramBoolean = paramIntent2.getBooleanExtra("showBody", true);
      paramIntent1 = Contact.get(paramIntent2.getStringExtra("from"));
      if ((!paramBoolean) || (paramInt != 2130838003) || (isMmsActivityOnTop(paramContext)) || (paramIntent1.isB2cNumber())) {
        break label412;
      }
      i = 1;
      List localList = UnderstandFactory.getUnderstandMessageList(paramString2, "", paramString1, -1L, 2);
      paramIntent1 = null;
      if ((localList == null) || (localList.size() <= 0)) {
        break label418;
      }
      paramIntent1 = (UnderstandMessage)localList.get(0);
      paramIntent2 = UnderstandFactory.getTextWithUnderstand(paramContext, paramString1, localList, 2131689551, paramContext.getResources().getInteger(2131558404), "…");
      paramIntent1 = buildRemoteViewsWithButton(paramContext, 2131362080, PendingIntent.getService(paramContext, 0, buildCopyTextIntent(paramContext, mItems.get(0)).mValue, paramUri), 134217728));
      label252:
      paramIntent1.setImageViewResource(16908294, paramInt);
      paramIntent1.setTextViewText(2131820657, paramString2);
      if (paramIntent2 == null) {
        break label473;
      }
      paramIntent1.setTextViewText(2131820658, paramIntent2);
    }
    for (;;)
    {
      localBuilder.setContent(paramIntent1);
      paramIntent1 = localBuilder.build();
      setNotificationDefault(paramContext, paramIntent1);
      if (sCurrentFloatId == Integer.MAX_VALUE) {
        sCurrentFloatId = 0;
      }
      sCurrentFloatId += 1;
      paramIntent2 = new Intent("com.android.mms.NOTIFICATION_FLOAT_NOTIFICATION_CANCEL_ACTION");
      paramIntent2.putExtra(CANCEL_FLOAT_INDEX, sCurrentFloatId);
      extraNotification.setExitFloatingIntent(PendingIntent.getBroadcast(paramContext, sCurrentFloatId, paramIntent2, 0));
      extraNotification.setMessageCount(0);
      ((NotificationManager)paramContext.getSystemService("notification")).notify(124, paramIntent1);
      mUIHandler.postDelayed(mCancelFloatNotification, extraNotification.getFloatTime());
      MessageUtils.setNotificationIndex(MmsApp.getApp(), sCurrentFloatId);
      return;
      label412:
      i = 0;
      break;
      label418:
      if (i != 0)
      {
        paramUri = buildRemoteViewsWithButton(paramContext, 2131362306, PendingIntent.getActivity(paramContext, 0, paramIntent2, 134217728));
        paramIntent2 = paramIntent1;
        paramIntent1 = paramUri;
        break label252;
      }
      paramUri = new RemoteViews(paramContext.getPackageName(), 2130968624);
      paramIntent2 = paramIntent1;
      paramIntent1 = paramUri;
      break label252;
      label473:
      paramIntent1.setTextViewText(2131820658, paramString1);
    }
  }
  
  private static void updateNotification(Context paramContext, Intent paramIntent1, Intent paramIntent2, String paramString1, int paramInt1, boolean paramBoolean, CharSequence paramCharSequence, long paramLong, String paramString2, int paramInt2, MsgThread paramMsgThread, Set<MsgThread> paramSet, int paramInt3, int paramInt4)
  {
    if (!MessageUtils.getPrefNotificationEnabled(paramContext)) {}
    label312:
    label330:
    for (;;)
    {
      return;
      Notification.Builder localBuilder = new Notification.Builder(paramContext);
      if (!TextUtils.isEmpty(mAddress))
      {
        paramMsgThread = paramMsgThread.getPeoplePreferenceUri(mAddress);
        if (paramMsgThread != null) {
          NotificationHelper.addPerson(localBuilder, paramMsgThread.toString());
        }
      }
      localBuilder.setSmallIcon(paramInt1);
      localBuilder.setTicker(paramCharSequence);
      localBuilder.setWhen(paramLong);
      if (getMsgThreadClassCounts(paramSet) > 1)
      {
        paramString2 = paramContext.getString(2131361986);
        paramIntent1 = new Intent("android.intent.action.MAIN");
        paramIntent1.setClass(paramContext, MmsTabActivity.class);
        paramIntent1.setType("vnd.android-dir/mms-sms");
        if (paramInt2 > 1) {
          paramString1 = paramContext.getString(2131361985, new Object[] { Integer.toString(paramInt2) });
        }
        paramIntent1 = PendingIntent.getActivity(paramContext, 0, paramIntent1, 134217728);
        localBuilder.setContentTitle(paramString2);
        localBuilder.setContentText(paramString1);
        localBuilder.setContentIntent(paramIntent1);
        paramIntent1 = localBuilder.build();
        setNotificationDefault(paramContext, paramIntent1);
        deleteIntent = PendingIntent.getBroadcast(paramContext, 0, sNotificationOnDeleteIntent, 0);
        extraNotification.setEnableFloat(false);
        extraNotification.setMessageCount(paramInt2);
        if (!paramBoolean) {
          break;
        }
        if (paramInt3 != 1) {
          break label312;
        }
        ((NotificationManager)paramContext.getSystemService("notification")).notify(123, paramIntent1);
      }
      for (;;)
      {
        if (paramIntent2 == null) {
          break label330;
        }
        MessageUtils.notifyIncomingSmsMmsToBracelet(paramContext, paramIntent2.getStringExtra("from"));
        return;
        if (paramSet.size() <= 1) {
          break;
        }
        paramString2 = paramContext.getString(2131361986);
        paramIntent1 = ((MsgThread)paramSet.iterator().next()).getCvListClickIntent(paramContext);
        break;
        sound = getSmsSound(paramContext, 16, paramInt4);
        Reminder.newNotification(paramContext, paramIntent1);
      }
    }
    ((NotificationManager)paramContext.getSystemService("notification")).notify(123, paramIntent1);
  }
  
  public static void updateSendFailedNotification(Context paramContext)
  {
    if (getUndeliveredMessageCount(paramContext, null) < 1) {
      cancelNotification(paramContext, 789);
    }
  }
  
  public static void updateSendFailedNotificationForThread(Context paramContext, long paramLong)
  {
    long[] arrayOfLong = new long[2];
    long[] tmp5_4 = arrayOfLong;
    tmp5_4[0] = 0L;
    long[] tmp9_5 = tmp5_4;
    tmp9_5[1] = 0L;
    tmp9_5;
    if ((getUndeliveredMessageCount(paramContext, tmp5_4) > 0) && (tmp5_4[0] == paramLong) && (tmp5_4[1] != 0L)) {
      cancelNotification(paramContext, 789);
    }
  }
  
  private static final class MmsSmsDeliveryInfo
  {
    public int mSlotId;
    public CharSequence mTicker;
    public long mTimeMillis;
    
    public MmsSmsDeliveryInfo(CharSequence paramCharSequence, long paramLong, int paramInt)
    {
      mTicker = paramCharSequence;
      mTimeMillis = paramLong;
      mSlotId = paramInt;
    }
    
    public void deliver(Context paramContext, boolean paramBoolean)
    {
      MessagingNotification.updateDeliveryNotification(paramContext, paramBoolean, mTicker, mTimeMillis, mSlotId);
    }
  }
  
  private static final class MmsSmsNotificationInfo
  {
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
    
    public MmsSmsNotificationInfo(Intent paramIntent1, Intent paramIntent2, String paramString1, int paramInt1, CharSequence paramCharSequence, long paramLong, String paramString2, int paramInt2, int paramInt3, MsgThread paramMsgThread, int paramInt4, Uri paramUri)
    {
      mClickIntent = paramIntent1;
      mFullScreenIntent = paramIntent2;
      mDescription = paramString1;
      mIconResourceId = paramInt1;
      mTicker = paramCharSequence;
      mTimeMillis = paramLong;
      mTitle = paramString2;
      mCount = paramInt2;
      mBlockType = paramInt3;
      mThread = paramMsgThread;
      mSlotId = paramInt4;
      mMsgUri = paramUri;
    }
    
    public void deliver(final Context paramContext, boolean paramBoolean, int paramInt, Set<MsgThread> paramSet)
    {
      if ((paramBoolean) && (MessagingNotification.isCurrentMessageThreadId(mThread)))
      {
        Log.d("Mms:app", "deliver notification for threaId " + mThread.mThreadId + " but only play sms sound, package is:" + mThread.getPackageName());
        MessagingNotification.mUIHandler.post(new Runnable()
        {
          public void run()
          {
            MessagingNotification.playReceivedRingTone(paramContext, mBlockType, mSlotId);
            if (mBlockType != 1) {
              VibratorManager.vibrate(paramContext);
            }
          }
        });
        if ((MessagingNotification.isNewMessagePopupOnTop(paramContext)) && (MessagingNotification.isFloatNotificationEnabled(paramContext)) && (mFullScreenIntent != null)) {
          paramContext.startActivity(mFullScreenIntent);
        }
        return;
      }
      MessagingNotification.updateFloatNotification(paramContext, mClickIntent, mFullScreenIntent, mDescription, mIconResourceId, paramBoolean, mTimeMillis, mTitle, mMsgUri);
      Intent localIntent1 = mClickIntent;
      Intent localIntent2 = mFullScreenIntent;
      String str = mDescription;
      int i = mIconResourceId;
      if (paramBoolean) {}
      for (CharSequence localCharSequence = mTicker;; localCharSequence = null)
      {
        MessagingNotification.updateNotification(paramContext, localIntent1, localIntent2, str, i, paramBoolean, localCharSequence, mTimeMillis, mTitle, paramInt, mThread, paramSet, mBlockType, mSlotId);
        return;
      }
    }
    
    public long getTime()
    {
      return mTimeMillis;
    }
  }
  
  private static final class MmsSmsNotificationInfoComparator
    implements Comparator<MessagingNotification.MmsSmsNotificationInfo>
  {
    public int compare(MessagingNotification.MmsSmsNotificationInfo paramMmsSmsNotificationInfo1, MessagingNotification.MmsSmsNotificationInfo paramMmsSmsNotificationInfo2)
    {
      return Long.signum(paramMmsSmsNotificationInfo2.getTime() - paramMmsSmsNotificationInfo1.getTime());
    }
  }
  
  public static class OnDeletedReceiver
    extends BroadcastReceiver
  {
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (Log.isLoggable("Mms:app", 2)) {
        Log.d("Mms:app", "[MessagingNotification] clear notification: mark all msgs seen");
      }
      Conversation.markAllConversationsAsSeen(paramContext);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.MessagingNotification
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */