package com.xiaomi.mms.utils;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.preference.PreferenceManager;
import android.provider.Telephony.Mms;
import android.provider.Telephony.Mms.Inbox;
import android.provider.Telephony.Threads;
import android.text.TextUtils;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.ui.MessageUtils;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.xiaomi.mms.mx.utils.Mx2ExtentionHelper;
import com.xiaomi.mms.utils.logger.MyLog;

public class MxMessagePduHelper
{
  private static final String[] NOTIFICATION_IND_PROJECTION = { "thread_id", "date", "date_ms_part", "date_sent", "exp", "sub", "sub_cs", "ct_t", "m_size", "mx_id", "sim_id", "block_type", "mx_type" };
  private static final String[] THREAD_ID_PROJECT = { "thread_id" };
  
  protected static long findThreadIdForMms(ContentResolver paramContentResolver, long paramLong)
  {
    paramContentResolver = paramContentResolver.query(Telephony.Mms.CONTENT_URI, THREAD_ID_PROJECT, "mx_id=" + paramLong, null, null);
    if (paramContentResolver != null)
    {
      try
      {
        if (paramContentResolver.moveToFirst())
        {
          paramLong = paramContentResolver.getLong(0);
          return paramLong;
        }
      }
      finally
      {
        paramContentResolver.close();
      }
      paramContentResolver.close();
    }
    return -1L;
  }
  
  public static Cursor getExpiredMxMms(Context paramContext)
  {
    return paramContext.getContentResolver().query(Telephony.Mms.CONTENT_URI, null, "(mx_status=16 OR mx_status=1) AND (out_time>0 AND out_time<" + (System.currentTimeMillis() - 300000L) + ") AND (" + "mx_id" + ">0) AND (" + "mx_type" + "=0)", null, "out_time");
  }
  
  protected static String getIncomingMmsAddress(Context paramContext, Uri paramUri)
  {
    Object localObject2 = null;
    Object localObject1 = null;
    long l = ContentUris.parseId(paramUri);
    paramUri = Uri.parse("content://mms/" + l + "/addr");
    paramUri = com.google.android.mms.util.SqliteWrapper.query(paramContext, paramContext.getContentResolver(), paramUri, null, "type=137", null, null);
    paramContext = (Context)localObject2;
    if (paramUri != null) {
      paramContext = (Context)localObject1;
    }
    try
    {
      if (paramUri.moveToFirst()) {
        paramContext = paramUri.getString(paramUri.getColumnIndexOrThrow("address"));
      }
      return paramContext;
    }
    finally
    {
      paramUri.close();
    }
  }
  
  public static Cursor getIncompleteMxMms(Context paramContext)
  {
    return paramContext.getContentResolver().query(Telephony.Mms.CONTENT_URI, null, "(mx_status=16 OR mx_status=1) AND (out_time>0) AND (mx_id>0) AND (m_type=128) AND (mx_type=0)", null, "out_time");
  }
  
  public static int getMessageMx2Type(Context paramContext, long paramLong)
  {
    int i = 0;
    int j = 0;
    ContentResolver localContentResolver = paramContext.getContentResolver();
    Uri localUri = Telephony.Mms.CONTENT_URI;
    String str = "_id=" + paramLong;
    paramContext = com.google.android.mms.util.SqliteWrapper.query(paramContext, localContentResolver, localUri, new String[] { "mx_type" }, str, null, null);
    if (paramContext != null) {
      i = j;
    }
    try
    {
      if (paramContext.moveToFirst()) {
        i = paramContext.getInt(0);
      }
      return i;
    }
    finally
    {
      paramContext.close();
    }
  }
  
  public static Uri getMmsMessageUriFromMxId(ContentResolver paramContentResolver, long paramLong, int paramInt)
  {
    Uri localUri = Telephony.Mms.CONTENT_URI;
    Object localObject2 = null;
    Object localObject1 = null;
    Object localObject3 = "mx_id=" + paramLong + " AND " + "msg_box" + "=" + paramInt;
    localObject3 = paramContentResolver.query(localUri, new String[] { "_id" }, (String)localObject3, null, null);
    paramContentResolver = (ContentResolver)localObject2;
    if (localObject3 != null) {
      paramContentResolver = (ContentResolver)localObject1;
    }
    try
    {
      if (((Cursor)localObject3).moveToFirst()) {
        paramContentResolver = ContentUris.withAppendedId(localUri, ((Cursor)localObject3).getLong(0));
      }
      return paramContentResolver;
    }
    finally
    {
      ((Cursor)localObject3).close();
    }
  }
  
  protected static String getOutgoingMmsAddress(Context paramContext, Uri paramUri)
  {
    Object localObject2 = null;
    Object localObject1 = null;
    long l = ContentUris.parseId(paramUri);
    paramUri = Uri.parse("content://mms/" + l + "/addr");
    paramUri = com.google.android.mms.util.SqliteWrapper.query(paramContext, paramContext.getContentResolver(), paramUri, null, "type=151", null, null);
    paramContext = (Context)localObject2;
    if (paramUri != null) {
      paramContext = (Context)localObject1;
    }
    try
    {
      if (paramUri.moveToFirst()) {
        paramContext = paramUri.getString(paramUri.getColumnIndexOrThrow("address"));
      }
      return paramContext;
    }
    finally
    {
      paramUri.close();
    }
  }
  
  public static Cursor getPendingMxMessages(Context paramContext)
  {
    return com.google.android.mms.util.SqliteWrapper.query(paramContext, paramContext.getContentResolver(), Telephony.Mms.CONTENT_URI, null, "(mx_status=1 AND msg_box=2 AND out_time=0)", null, null);
  }
  
  public static void handleMxMmsFailed(Context paramContext, long paramLong)
  {
    handleMxMmsFailed(paramContext, paramLong, true, false);
  }
  
  public static void handleMxMmsFailed(Context paramContext, long paramLong, boolean paramBoolean)
  {
    handleMxMmsFailed(paramContext, paramLong, paramBoolean, false);
  }
  
  public static void handleMxMmsFailed(Context paramContext, long paramLong, boolean paramBoolean1, boolean paramBoolean2)
  {
    Uri localUri = ContentUris.withAppendedId(Telephony.Mms.CONTENT_URI, paramLong);
    boolean bool = PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("pref_key_mx_auto_resend_mms", false);
    if ((paramBoolean1) && (bool) && (!paramBoolean2))
    {
      MyLog.i("MxMessagePduHelper", "auto resend");
      MessageUtils.resendMms(paramContext, null, localUri, false);
    }
    for (;;)
    {
      return;
      Cursor localCursor = com.google.android.mms.util.SqliteWrapper.query(paramContext, paramContext.getContentResolver(), Telephony.Mms.CONTENT_URI, null, "_id=" + paramLong, null, null);
      if (localCursor != null) {}
      int k;
      for (;;)
      {
        try
        {
          if (localCursor.moveToFirst())
          {
            k = localCursor.getInt(localCursor.getColumnIndexOrThrow("resp_st"));
            j = localCursor.getInt(localCursor.getColumnIndexOrThrow("m_type"));
            paramLong = localCursor.getLong(localCursor.getColumnIndexOrThrow("out_time"));
            localCursor.close();
            i = 0;
          }
          switch (k)
          {
          default: 
            if (j != 130) {
              break label391;
            }
            k = 1;
            if ((k != 0) || (paramLong <= 0L)) {
              break label397;
            }
            j = 1;
            if (i != 0) {
              j = 0;
            }
            if (j == 0) {
              break label403;
            }
            markMmsTransientFailed(paramContext, localUri);
            return;
            MyLog.w("MxMessagePduHelper", "mms not found, id: " + paramLong);
            return;
          }
        }
        finally
        {
          localCursor.close();
        }
        MyLog.w("MxMessagePduHelper", "mms not found, cursor is null, id: " + paramLong);
        return;
        int i = 2131361860;
        continue;
        i = 2131361861;
        continue;
        i = 2131361863;
        continue;
        i = 2131361862;
        continue;
        i = 2131361863;
        continue;
        label391:
        k = 0;
        continue;
        label397:
        int j = 0;
      }
      label403:
      markMmsPermanentFailed(paramContext, localUri);
      if (k != 0)
      {
        localCursor = android.database.sqlite.SqliteWrapper.query(paramContext, paramContext.getContentResolver(), localUri, new String[] { "thread_id" }, null, null, null);
        paramLong = -1L;
        long l = paramLong;
        if (localCursor != null) {}
        try
        {
          if (localCursor.moveToFirst()) {
            paramLong = localCursor.getLong(0);
          }
          localCursor.close();
          l = paramLong;
          if (l != -1L)
          {
            MessagingNotification.notifyDownloadFailed(paramContext, l);
            return;
          }
        }
        finally
        {
          localCursor.close();
        }
      }
    }
    MessagingNotification.notifySendFailed(paramContext, true);
  }
  
  public static int markFailedMessageAsPending(Context paramContext, Uri paramUri)
  {
    ContentValues localContentValues = new ContentValues(1);
    localContentValues.put("mx_status", Integer.valueOf(1));
    return com.google.android.mms.util.SqliteWrapper.update(paramContext, paramContext.getContentResolver(), paramUri, localContentValues, "mx_status=131073", null);
  }
  
  public static void markMmsAsCommon(Context paramContext, Uri paramUri)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("msg_box", Integer.valueOf(4));
    localContentValues.put("mx_status", Integer.valueOf(0));
    localContentValues.put("pri", Integer.valueOf(129));
    localContentValues.put("v", Integer.valueOf(18));
    localContentValues.put("d_rpt", Integer.valueOf(128));
    localContentValues.putNull("st");
    paramContext.getContentResolver().update(paramUri, localContentValues, null, null);
  }
  
  public static void markMmsMxStatus(Context paramContext, Uri paramUri, int paramInt)
  {
    ContentValues localContentValues = new ContentValues(1);
    localContentValues.put("mx_status", Integer.valueOf(paramInt));
    android.database.sqlite.SqliteWrapper.update(paramContext, paramContext.getContentResolver(), paramUri, localContentValues, null, null);
  }
  
  public static void markMmsPermanentFailed(Context paramContext, Uri paramUri)
  {
    ContentValues localContentValues = new ContentValues(3);
    localContentValues.put("read", Integer.valueOf(0));
    localContentValues.put("st", Integer.valueOf(135));
    localContentValues.put("mx_status", Integer.valueOf(131073));
    com.google.android.mms.util.SqliteWrapper.update(paramContext, paramContext.getContentResolver(), paramUri, localContentValues, null, null);
  }
  
  public static void markMmsSendAsMx(Context paramContext, Uri paramUri, boolean paramBoolean)
  {
    ContentValues localContentValues = new ContentValues(3);
    if (paramBoolean)
    {
      i = 2;
      localContentValues.put("msg_box", Integer.valueOf(i));
      if (!paramBoolean) {
        break label75;
      }
    }
    label75:
    for (int i = 1;; i = 0)
    {
      localContentValues.put("mx_status", Integer.valueOf(i));
      localContentValues.put("out_time", Integer.valueOf(0));
      paramContext.getContentResolver().update(paramUri, localContentValues, null, null);
      return;
      i = 4;
      break;
    }
  }
  
  public static void markMmsStatus(Context paramContext, Uri paramUri, int paramInt)
  {
    ContentValues localContentValues = new ContentValues(1);
    localContentValues.put("st", Integer.valueOf(paramInt));
    android.database.sqlite.SqliteWrapper.update(paramContext, paramContext.getContentResolver(), paramUri, localContentValues, null, null);
  }
  
  public static void markMmsTransientFailed(Context paramContext, Uri paramUri)
  {
    ContentValues localContentValues = new ContentValues(3);
    localContentValues.put("read", Integer.valueOf(0));
    localContentValues.put("st", Integer.valueOf(130));
    localContentValues.put("mx_status", Integer.valueOf(1));
    com.google.android.mms.util.SqliteWrapper.update(paramContext, paramContext.getContentResolver(), paramUri, localContentValues, null, null);
  }
  
  public static void moveMxMmsToOutbox(Context paramContext, Uri paramUri, long paramLong1, long paramLong2)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("out_time", Long.valueOf(paramLong1));
    localContentValues.put("mx_id", Long.valueOf(paramLong2));
    localContentValues.put("m_id", String.valueOf(paramLong2));
    localContentValues.put("resp_st", Integer.valueOf(128));
    com.google.android.mms.util.SqliteWrapper.update(paramContext, paramContext.getContentResolver(), paramUri, localContentValues, null, null);
  }
  
  public static int moveUnsentMessageToPending(Context paramContext)
  {
    ContentValues localContentValues = new ContentValues(1);
    localContentValues.put("out_time", Integer.valueOf(0));
    return com.google.android.mms.util.SqliteWrapper.update(paramContext, paramContext.getContentResolver(), Telephony.Mms.CONTENT_URI, localContentValues, "(mx_status=1 AND msg_box=2 AND out_time>0)", null);
  }
  
  protected static void persistAddressTo(Context paramContext, long paramLong, String paramString, int paramInt)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("address", paramString);
    localContentValues.put("charset", Integer.valueOf(106));
    localContentValues.put("type", Integer.valueOf(paramInt));
    paramString = Uri.parse("content://mms/" + paramLong + "/addr");
    android.database.sqlite.SqliteWrapper.insert(paramContext, paramContext.getContentResolver(), paramString, localContentValues);
  }
  
  public static Uri persistNotification(Context paramContext, String paramString1, long paramLong1, long paramLong2, long paramLong3, String paramString2, String paramString3, long paramLong4, String paramString4, long paramLong5, long paramLong6, int paramInt1, int paramInt2, String paramString5)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("m_type", Integer.valueOf(130));
    localContentValues.put("thread_id", Long.valueOf(Telephony.Threads.getOrCreateThreadId(paramContext, paramString1)));
    localContentValues.put("date", Long.valueOf(paramLong1 / 1000L));
    localContentValues.put("date_ms_part", Long.valueOf(paramLong1 % 1000L));
    localContentValues.put("date_sent", Long.valueOf(paramLong2 / 1000L));
    localContentValues.put("msg_box", Integer.valueOf(1));
    localContentValues.put("st", Integer.valueOf(132));
    localContentValues.put("m_cls", "personal");
    localContentValues.put("exp", Long.valueOf(paramLong3 / 1000L));
    localContentValues.put("tr_id", paramString4);
    localContentValues.put("ct_l", paramString4);
    paramString2 = new EncodedStringValue(paramString2);
    localContentValues.put("sub", MiuiPduPersister.toIsoString(paramString2.getTextString()));
    localContentValues.put("sub_cs", Integer.valueOf(paramString2.getCharacterSet()));
    localContentValues.put("ct_t", paramString3);
    localContentValues.put("m_size", Long.valueOf(paramLong4));
    localContentValues.put("mx_id", Long.valueOf(paramLong5));
    localContentValues.put("mx_status", Integer.valueOf(1));
    localContentValues.put("sim_id", Long.valueOf(paramLong6));
    localContentValues.put("block_type", Integer.valueOf(paramInt1));
    localContentValues.put("mx_type", Integer.valueOf(paramInt2));
    if (!TextUtils.isEmpty(paramString5)) {
      localContentValues.put("mx_extension", Mx2ExtentionHelper.convertServerExtensionToLocal(paramString5));
    }
    if (MessageUtils.isServiceNumber(paramContext, paramString1)) {
      localContentValues.put("advanced_seen", Integer.valueOf(1));
    }
    for (;;)
    {
      if (paramInt2 > 0)
      {
        localContentValues.put("st", Integer.valueOf(129));
        localContentValues.put("m_type", Integer.valueOf(132));
      }
      paramString2 = com.google.android.mms.util.SqliteWrapper.insert(paramContext, paramContext.getContentResolver(), Telephony.Mms.Inbox.CONTENT_URI, localContentValues);
      persistAddressTo(paramContext, ContentUris.parseId(paramString2), paramString1, 137);
      return paramString2;
      localContentValues.put("advanced_seen", Integer.valueOf(0));
    }
  }
  
  public static Uri persistRetrieveConf(Context paramContext, Uri paramUri)
  {
    ContentResolver localContentResolver = paramContext.getContentResolver();
    Object localObject1 = com.google.android.mms.util.SqliteWrapper.query(paramContext, localContentResolver, paramUri, NOTIFICATION_IND_PROJECTION, null, null, null);
    if (localObject1 != null) {
      try
      {
        if (((Cursor)localObject1).moveToFirst())
        {
          long l1 = ((Cursor)localObject1).getLong(0);
          long l2 = ((Cursor)localObject1).getLong(1);
          long l3 = ((Cursor)localObject1).getLong(2);
          long l4 = ((Cursor)localObject1).getLong(3);
          long l5 = ((Cursor)localObject1).getLong(4);
          String str1 = ((Cursor)localObject1).getString(5);
          int i = ((Cursor)localObject1).getInt(6);
          String str2 = ((Cursor)localObject1).getString(7);
          long l6 = ((Cursor)localObject1).getLong(8);
          long l7 = ((Cursor)localObject1).getLong(9);
          int j = ((Cursor)localObject1).getInt(10);
          int k = ((Cursor)localObject1).getInt(11);
          ((Cursor)localObject1).getInt(12);
          ((Cursor)localObject1).close();
          Object localObject2 = new ContentValues();
          ((ContentValues)localObject2).put("m_type", Integer.valueOf(132));
          ((ContentValues)localObject2).put("thread_id", Long.valueOf(l1));
          ((ContentValues)localObject2).put("date", Long.valueOf(l2));
          ((ContentValues)localObject2).put("date_sent", Long.valueOf(l4));
          ((ContentValues)localObject2).put("date_ms_part", Long.valueOf(l3));
          ((ContentValues)localObject2).put("exp", Long.valueOf(l5));
          ((ContentValues)localObject2).put("sub", str1);
          ((ContentValues)localObject2).put("sub_cs", Integer.valueOf(i));
          ((ContentValues)localObject2).put("ct_t", str2);
          ((ContentValues)localObject2).put("m_size", Long.valueOf(l6));
          ((ContentValues)localObject2).put("resp_st", Integer.valueOf(128));
          ((ContentValues)localObject2).put("mx_id", Long.valueOf(l7));
          ((ContentValues)localObject2).put("sim_id", Integer.valueOf(j));
          ((ContentValues)localObject2).put("mx_status", Integer.valueOf(65537));
          ((ContentValues)localObject2).put("block_type", Integer.valueOf(k));
          localObject1 = paramUri;
          if (k > 1)
          {
            l1 = Long.valueOf(paramUri.getLastPathSegment()).longValue();
            localObject1 = ContentUris.withAppendedId(Telephony.Mms.Inbox.CONTENT_URI, l1).buildUpon().appendQueryParameter("blocked_flag", "1").build();
          }
          paramUri = com.google.android.mms.util.SqliteWrapper.insert(paramContext, localContentResolver, Telephony.Mms.CONTENT_URI, (ContentValues)localObject2);
          localObject2 = getIncomingMmsAddress(paramContext, (Uri)localObject1);
          if (!TextUtils.isEmpty((CharSequence)localObject2)) {
            persistAddressTo(paramContext, ContentUris.parseId(paramUri), (String)localObject2, 137);
          }
          com.google.android.mms.util.SqliteWrapper.delete(paramContext, localContentResolver, (Uri)localObject1, null, null);
          return paramUri;
        }
        return null;
      }
      finally
      {
        ((Cursor)localObject1).close();
      }
    }
    return null;
  }
  
  public static void setResponseStatus(Context paramContext, Uri paramUri, int paramInt)
  {
    ContentValues localContentValues = new ContentValues(1);
    localContentValues.put("resp_st", Integer.valueOf(paramInt));
    com.google.android.mms.util.SqliteWrapper.update(paramContext, paramContext.getContentResolver(), paramUri, localContentValues, null, null);
  }
  
  public static Uri updateMmsToDelivered(Context paramContext, long paramLong)
  {
    Object localObject2 = paramContext.getContentResolver();
    long l1 = findThreadIdForMms((ContentResolver)localObject2, paramLong);
    Object localObject1 = getMmsMessageUriFromMxId((ContentResolver)localObject2, paramLong, 2);
    if (l1 <= 0L) {
      localObject1 = null;
    }
    Object localObject3;
    do
    {
      return (Uri)localObject1;
      if (localObject1 == null) {
        return null;
      }
      long l2 = System.currentTimeMillis();
      localObject3 = com.google.android.mms.util.SqliteWrapper.query(paramContext, (ContentResolver)localObject2, Telephony.Mms.CONTENT_URI, null, "(mx_id=" + paramLong + ") AND (" + "msg_box" + "=" + 2 + ") AND (" + "mx_status" + "!=" + 1 + " AND " + "mx_status" + "!=" + 16 + " AND " + "mx_status" + "!=" + 131073 + ")", null, null);
      if (localObject3 != null)
      {
        try
        {
          if (((Cursor)localObject3).getCount() > 0)
          {
            MyLog.w("MxMessagePduHelper", "mms status incorrect: " + paramLong);
            return null;
          }
        }
        finally
        {
          ((Cursor)localObject3).close();
        }
        ((Cursor)localObject3).close();
      }
      getMessageMx2Type(paramContext, ContentUris.parseId((Uri)localObject1));
      localObject3 = new ContentValues();
      ((ContentValues)localObject3).put("mx_status", Integer.valueOf(17));
      ((ContentValues)localObject3).put("st", Integer.valueOf(129));
      com.google.android.mms.util.SqliteWrapper.update(paramContext, (ContentResolver)localObject2, (Uri)localObject1, (ContentValues)localObject3, null, null);
      ((ContentValues)localObject3).clear();
      ((ContentValues)localObject3).put("thread_id", Long.valueOf(l1));
      ((ContentValues)localObject3).put("date", Long.valueOf(l2 / 1000L));
      ((ContentValues)localObject3).put("m_id", Long.valueOf(paramLong));
      ((ContentValues)localObject3).put("m_type", Integer.valueOf(134));
      ((ContentValues)localObject3).put("st", Integer.valueOf(129));
      ((ContentValues)localObject3).put("mx_id", Long.valueOf(paramLong));
      ((ContentValues)localObject3).put("mx_status", Integer.valueOf(17));
      localObject2 = com.google.android.mms.util.SqliteWrapper.insert(paramContext, (ContentResolver)localObject2, Telephony.Mms.Inbox.CONTENT_URI, (ContentValues)localObject3);
      localObject3 = getOutgoingMmsAddress(paramContext, (Uri)localObject1);
      localObject1 = localObject2;
    } while (TextUtils.isEmpty((CharSequence)localObject3));
    persistAddressTo(paramContext, ContentUris.parseId((Uri)localObject2), (String)localObject3, 151);
    return (Uri)localObject2;
  }
  
  public static int updateMmsToSent(Context paramContext, long paramLong)
  {
    Uri localUri = getMmsMessageUriFromMxId(paramContext.getContentResolver(), paramLong, 2);
    if (localUri == null) {
      return 0;
    }
    ContentValues localContentValues = new ContentValues(2);
    localContentValues.put("mx_status", Integer.valueOf(16));
    localContentValues.put("msg_box", Integer.valueOf(2));
    return com.google.android.mms.util.SqliteWrapper.update(paramContext, paramContext.getContentResolver(), localUri, localContentValues, "mx_status=1", null);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.utils.MxMessagePduHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */