package com.xiaomi.mms.transaction;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Telephony.MmsSms;
import android.provider.Telephony.Sms.Inbox;
import android.provider.Telephony.Threads;
import android.text.TextUtils;
import com.android.mms.data.Contact;
import com.android.mms.transaction.DownloadMxV2FileService;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.ui.MessageUtils;
import com.android.mms.util.DownloadManager;
import com.android.mms.util.MSimUtils;
import com.miui.gallery.lib.MiuiGalleryUtils;
import com.xiaomi.accountsdk.activate.ActivateManager;
import com.xiaomi.accountsdk.activate.ActivateManager.ActivateManagerFuture;
import com.xiaomi.mms.data.MidPhoneMap;
import com.xiaomi.mms.data.MxIdCache;
import com.xiaomi.mms.data.RecentMessageCache;
import com.xiaomi.mms.utils.B2cMessageUtils;
import com.xiaomi.mms.utils.MxCapabilityText;
import com.xiaomi.mms.utils.MxMessageLogicHelper;
import com.xiaomi.mms.utils.MxMessagePduHelper;
import com.xiaomi.mms.utils.logger.MyLog;
import java.util.Iterator;
import java.util.Set;
import miui.provider.ExtraTelephony;
import miui.push.PushAttributes;
import org.json.JSONException;
import org.json.JSONObject;

public class MxMessageService
  extends WakenService
{
  private static final String[] ALL_MESSAGE_TYPE_CONDITION = { "_id", "type", "msg_box" };
  
  public static long adjustExpiredTime(long paramLong)
  {
    long l2 = System.currentTimeMillis();
    long l1 = paramLong;
    if (paramLong < l2 - 1827387392L)
    {
      l1 = paramLong;
      if (paramLong * 1000L < 1827387392L + l2) {
        l1 = paramLong * 1000L;
      }
    }
    return l1;
  }
  
  public static int getMxMmsCount(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getInt("pref_mx_mms_count", 0);
  }
  
  public static int getMxSmsCount(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getInt("pref_mx_sms_count", 0);
  }
  
  private static Uri getSmsMessageUriFromMxId(ContentResolver paramContentResolver, long paramLong)
  {
    Object localObject2 = null;
    Object localObject1 = null;
    Uri localUri = Uri.parse("content://sms");
    Object localObject3 = "mx_id=" + Long.valueOf(paramLong);
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
  
  private int guessSlotIdByMid(String paramString)
  {
    int j = MSimUtils.getMultiSimCount();
    int i = 0;
    while (i < j)
    {
      if (MSimUtils.getSimId(this, i) != null)
      {
        ActivateManager.ActivateManagerFuture localActivateManagerFuture = ActivateManager.get(this).getActivateInfo(i);
        try
        {
          boolean bool = paramString.equals(((Bundle)localActivateManagerFuture.getResult()).getString("activate_sim_user_id"));
          if (bool) {
            return i;
          }
        }
        catch (Exception localException)
        {
          MyLog.e("MxMessageService", "failed to get sim user for sim " + i, localException);
        }
      }
      i += 1;
    }
    i = MSimUtils.getInsertedSlotId();
    if (i != -1) {
      return i;
    }
    MyLog.w("MxMessageService", "no sim is active now, save msg to slot 0");
    return 0;
  }
  
  private void increaseMxMmsCount()
  {
    int i = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getInt("pref_mx_mms_count", 0);
    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putInt("pref_mx_mms_count", i + 1).commit();
  }
  
  private void increaseMxSmsCount()
  {
    int i = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getInt("pref_mx_sms_count", 0);
    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putInt("pref_mx_sms_count", i + 1).commit();
  }
  
  public static void resetMxCount(Context paramContext)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putInt("pref_mx_sms_count", 0).commit();
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putInt("pref_mx_mms_count", 0).commit();
  }
  
  private boolean shouldAbandonReceivedMessage(int paramInt, String paramString1, String paramString2)
  {
    boolean bool1;
    if (!TextUtils.isEmpty(paramString1))
    {
      if (1 == DownloadManager.getInstance().getServiceStateForSlotId(paramInt))
      {
        MyLog.w("MxMessageService", "sim card is out of service,discard received b2c message for simIndex " + paramInt);
        bool1 = true;
      }
    }
    else {
      for (;;)
      {
        return bool1;
        if (PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean("pref_key_mx_filter_message_from_stranger", false))
        {
          paramString1 = Contact.get(paramString2);
          if (paramString1.existsInDatabase()) {
            return false;
          }
          paramString1.load(true, true);
          if (paramString1.existsInDatabase()) {
            return false;
          }
          paramString2 = Telephony.MmsSms.CONTENT_FILTER_BYPHONE_URI.buildUpon().appendPath(paramString2).build();
          paramString1 = null;
          try
          {
            paramString2 = getApplicationContext().getContentResolver().query(paramString2, ALL_MESSAGE_TYPE_CONDITION, null, null, null);
            label145:
            boolean bool2;
            if (paramString2 != null)
            {
              paramString1 = paramString2;
              if (paramString2.moveToFirst())
              {
                paramString1 = paramString2;
                if (paramString2.getType(1) != 0)
                {
                  paramString1 = paramString2;
                  paramInt = paramString2.getInt(1);
                  if ((paramInt != 2) && (paramInt != 4) && (paramInt != 5) && (paramInt != 6)) {
                    break label280;
                  }
                  paramString1 = paramString2;
                  MyLog.d("MxMessageService", "has send sms to sender");
                  bool2 = false;
                  bool1 = false;
                  if (paramString2 == null) {
                    continue;
                  }
                  bool1 = bool2;
                }
              }
            }
            for (;;)
            {
              paramString2.close();
              return bool1;
              paramString1 = paramString2;
              if (paramString2.getType(2) != 0)
              {
                paramString1 = paramString2;
                paramInt = paramString2.getInt(2);
                if ((paramInt == 4) || (paramInt == 2))
                {
                  paramString1 = paramString2;
                  MyLog.d("MxMessageService", "has send mms to sender");
                  bool2 = false;
                  bool1 = false;
                  if (paramString2 == null) {
                    break;
                  }
                  bool1 = bool2;
                  continue;
                }
              }
              label280:
              paramString1 = paramString2;
              bool1 = paramString2.moveToNext();
              if (bool1) {
                break label145;
              }
              bool2 = true;
              bool1 = true;
              if (paramString2 == null) {
                break;
              }
              bool1 = bool2;
            }
            return false;
          }
          finally
          {
            if (paramString1 != null) {
              paramString1.close();
            }
          }
        }
      }
    }
  }
  
  private void storeSms(Context paramContext, String paramString1, String paramString2, Long paramLong1, long paramLong2, int paramInt1, long paramLong3, int paramInt2, String paramString3, Long paramLong4)
  {
    if ((TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2)) || (paramLong1 == null)) {
      MyLog.e("MxMessageService", "from or pdu not nullable");
    }
    label370:
    label395:
    do
    {
      Object localObject;
      do
      {
        for (;;)
        {
          return;
          localObject = SqliteWrapper.query(paramContext, paramContext.getContentResolver(), Telephony.Sms.Inbox.CONTENT_URI, null, "mx_id=" + paramLong2, null, null);
          int i = 0;
          if (localObject != null) {}
          try
          {
            i = ((Cursor)localObject).getCount();
            if (i > 0) {}
            for (i = 1;; i = 0)
            {
              ((Cursor)localObject).close();
              if (i != 0) {
                break;
              }
              long l = System.currentTimeMillis();
              localObject = new ContentValues();
              ((ContentValues)localObject).put("address", paramString1);
              ((ContentValues)localObject).put("date", Long.valueOf(l));
              ((ContentValues)localObject).put("date_sent", paramLong1);
              ((ContentValues)localObject).put("protocol", Integer.valueOf(0));
              ((ContentValues)localObject).put("seen", Integer.valueOf(0));
              if (!MessageUtils.isServiceNumber(paramContext, paramString1)) {
                break label370;
              }
              ((ContentValues)localObject).put("advanced_seen", Integer.valueOf(1));
              ((ContentValues)localObject).put("reply_path_present", Integer.valueOf(0));
              ((ContentValues)localObject).put("service_center", Integer.valueOf(0));
              ((ContentValues)localObject).put("error_code", Integer.valueOf(0));
              ((ContentValues)localObject).put("body", paramString2);
              ((ContentValues)localObject).put("thread_id", Long.valueOf(Telephony.Threads.getOrCreateThreadId(paramContext, paramString1)));
              ((ContentValues)localObject).put("mx_id", Long.valueOf(paramLong2));
              ((ContentValues)localObject).put("mx_status", Integer.valueOf(65537));
              ((ContentValues)localObject).put("sim_id", Long.valueOf(paramLong3));
              ((ContentValues)localObject).put("block_type", Integer.valueOf(paramInt2));
              if ((paramInt2 >= 3) || ((!MiuiGalleryUtils.handleAsAlbumShareInvitation(paramContext, null, paramString2, "mms")) && (!MessageUtils.handleFileShareMessage(paramContext, paramString1, paramString2)))) {
                break label395;
              }
              ((ContentValues)localObject).put("block_type", Integer.valueOf(0));
              ((ContentValues)localObject).put("read", Integer.valueOf(1));
              return;
            }
          }
          finally
          {
            ((Cursor)localObject).close();
          }
        }
        ((ContentValues)localObject).put("read", Integer.valueOf(0));
        if (!TextUtils.isEmpty(paramString3))
        {
          ((ContentValues)localObject).put("b2c_numbers", paramString3);
          if (paramLong4 != null) {
            ((ContentValues)localObject).put("b2c_ttl", paramLong4);
          }
        }
      } while ((MessageUtils.insertUniqueMessage(paramContext, (ContentValues)localObject) == null) || (paramInt2 > 1));
      MessagingNotification.blockingUpdateNewMessageIndicator(this, true, false);
    } while (paramInt2 == 1);
    MessageUtils.processReceivedMsgReport(this, paramString1, paramString2, paramInt1);
  }
  
  protected void handleIntent(Intent paramIntent)
  {
    String str1 = paramIntent.getAction();
    Object localObject5 = paramIntent.getStringExtra("from");
    Object localObject2 = paramIntent.getStringExtra("to");
    String str2 = paramIntent.getStringExtra("packetId");
    Object localObject1 = MxMessageLogicHelper.getSimpleMid((String)localObject2);
    Object localObject4 = PushSession.getInstance(this);
    int j = ((PushSession)localObject4).getSimIdByMid((String)localObject1);
    int i = j;
    if (j < 0) {
      i = guessSlotIdByMid((String)localObject1);
    }
    localObject4 = ((PushSession)localObject4).getMyFullMid(i);
    localObject1 = localObject4;
    if (TextUtils.isEmpty((CharSequence)localObject4)) {
      localObject1 = localObject2;
    }
    long l1 = MSimUtils.getSimIdBySlotId(i);
    String str4 = MxMessageLogicHelper.getSimpleMid((String)localObject5);
    Object localObject6;
    String str3;
    if ("com.xiaomi.mms.mx.ACTION_HANDLE_MX_RECEIVED".equals(str1))
    {
      localObject6 = paramIntent.getStringExtra("b2c_number");
      str3 = paramIntent.getStringExtra("address");
      if (!shouldAbandonReceivedMessage(i, (String)localObject6, str3)) {}
    }
    label685:
    label1043:
    do
    {
      long l2;
      do
      {
        Object localObject3;
        do
        {
          do
          {
            do
            {
              return;
              if (RecentMessageCache.contain(str2))
              {
                MxMessageLogicHelper.sendReceivedAck(this, (String)localObject1, (String)localObject5, str2);
                return;
              }
              localObject2 = paramIntent.getStringExtra("body");
              localObject4 = localObject2;
              if ("base64".equals(paramIntent.getStringExtra("encoding"))) {
                localObject4 = MxMessageLogicHelper.base64Decode((String)localObject2);
              }
              String str5 = paramIntent.getStringExtra("fromType");
              str1 = null;
              localObject2 = str1;
              try
              {
                if (!TextUtils.isEmpty((CharSequence)localObject4)) {
                  localObject2 = new JSONObject((String)localObject4);
                }
                if (localObject2 == null)
                {
                  MxMessageLogicHelper.sendReceivedAck(this, (String)localObject1, (String)localObject5, str2, "malformed-body", "json exception when process msg body");
                  MyLog.e("MxMessageService", "receive mi message without body");
                  RecentMessageCache.add(str2);
                  return;
                }
              }
              catch (JSONException localJSONException)
              {
                for (;;)
                {
                  MyLog.e("MxMessageService", "receive message with malformed body", localJSONException);
                  localObject3 = str1;
                  continue;
                  localObject4 = ((JSONObject)localObject3).optString("type");
                  Long localLong = Long.valueOf(((JSONObject)localObject3).optLong("sentTime"));
                  l2 = ((JSONObject)localObject3).optLong("msgId");
                  int m = ((JSONObject)localObject3).optInt("mxType", 0);
                  if ((TextUtils.isEmpty((CharSequence)localObject4)) || (l2 == 0L))
                  {
                    MxMessageLogicHelper.sendReceivedAck(this, (String)localObject1, (String)localObject5, str2, "malformed-body", "missing type or msgId");
                    MyLog.w("MxMessageService", "missing type or msgId");
                  }
                  else
                  {
                    Object localObject7;
                    int k;
                    long l3;
                    if ("sms".equals(localObject4))
                    {
                      localObject7 = ((JSONObject)localObject3).optString("pdu");
                      k = 0;
                      j = k;
                      if (str3 != null)
                      {
                        j = k;
                        if (localObject7 != null)
                        {
                          j = k;
                          if (!B2cMessageUtils.isB2cNumber(str3)) {
                            j = ExtraTelephony.getSmsBlockType(this, str3, (String)localObject7, i);
                          }
                        }
                      }
                      if ("phone".equals(str5))
                      {
                        MxMessageLogicHelper.sendReceivedAck(this, (String)localObject1, (String)localObject5, str2);
                        localObject5 = Contact.get(str3);
                        str5 = ((Contact)localObject5).getMxPhoneNumber();
                        MxIdCache.put(str5, str4);
                        MidPhoneMap.put(str4, str5);
                        if (MxIdCache.online(str5, 1L, true)) {
                          MxTaskService.queryStatus(this, str5);
                        }
                        str1 = null;
                        localObject3 = null;
                        localObject1 = str1;
                        if (!TextUtils.isEmpty((CharSequence)localObject6))
                        {
                          localObject4 = paramIntent.getStringExtra("b2c_display_name");
                          paramIntent = paramIntent.getStringExtra("b2c_ttl");
                          localObject1 = str1;
                          localObject3 = localObject4;
                          if (TextUtils.isEmpty(paramIntent)) {}
                        }
                        try
                        {
                          l3 = Long.valueOf(paramIntent).longValue();
                          localObject1 = Long.valueOf(1000L * l3);
                          localObject3 = localObject4;
                        }
                        catch (NumberFormatException paramIntent)
                        {
                          for (;;)
                          {
                            localObject1 = null;
                            localObject3 = localObject4;
                            continue;
                            k = 0;
                          }
                        }
                        if (!TextUtils.isEmpty((CharSequence)localObject3))
                        {
                          bool1 = TextUtils.isEmpty(((Contact)localObject5).getRealName());
                          if (TextUtils.isEmpty(((Contact)localObject5).getYellowPageThumbnailName())) {
                            break label685;
                          }
                          k = 1;
                          if (bool1)
                          {
                            ((Contact)localObject5).setName((String)localObject3);
                            ((Contact)localObject5).setIsB2cNumber(true);
                          }
                          if ((bool1) || (k != 0)) {
                            B2cMessageUtils.insertOrUpdateB2cAddress(getApplicationContext(), str5, (String)localObject3);
                          }
                        }
                        storeSms(this, str3, (String)localObject7, localLong, l2, i, l1, j, (String)localObject6, (Long)localObject1);
                        continue;
                      }
                      MxMessageLogicHelper.sendReceivedAck(this, (String)localObject1, (String)localObject5, str2, "unsupported-source", "unknown source type: " + str5);
                    }
                    else
                    {
                      long l4;
                      if ("mms".equals(localObject4))
                      {
                        localObject3 = ((JSONObject)localObject3).optString("subject");
                        localObject6 = paramIntent.getBundleExtra("attachment");
                        if (localObject6 != null)
                        {
                          paramIntent = ((Bundle)localObject6).getString("mimeType");
                          localObject4 = ((Bundle)localObject6).getString("shareId");
                          str1 = ((Bundle)localObject6).getString("expireAt");
                          localObject6 = ((Bundle)localObject6).getString("msgSize");
                          if ((!TextUtils.isEmpty(paramIntent)) && (!TextUtils.isEmpty((CharSequence)localObject4)) && (TextUtils.isDigitsOnly(str1)) && (TextUtils.isDigitsOnly((CharSequence)localObject6)))
                          {
                            l3 = adjustExpiredTime(Long.valueOf(str1).longValue());
                            l4 = Long.valueOf((String)localObject6).longValue();
                            str1 = Contact.get(str3).getMxPhoneNumber();
                            MxIdCache.put(str1, str4);
                            MidPhoneMap.put(str4, str1);
                            if (MxIdCache.online(str1, 3L, true)) {
                              MxTaskService.queryStatus(this, str1);
                            }
                            k = 0;
                            j = k;
                            if (str3 != null)
                            {
                              j = k;
                              if (!B2cMessageUtils.isB2cNumber(str3)) {
                                j = ExtraTelephony.getSmsBlockType(this, str3, null, i);
                              }
                            }
                            if ("phone".equals(str5))
                            {
                              MxMessageLogicHelper.sendReceivedAck(this, (String)localObject1, (String)localObject5, str2);
                              paramIntent = MxMessagePduHelper.persistNotification(this, str3, System.currentTimeMillis(), localLong.longValue(), l3, (String)localObject3, paramIntent, l4, (String)localObject4, l2, l1, j, 0, null);
                              localObject1 = DownloadManager.getInstance();
                              bool1 = ((DownloadManager)localObject1).isAuto(l1);
                              if (j > 1) {
                                bool1 = false;
                              }
                              if (bool1) {}
                              for (i = 129;; i = 128)
                              {
                                ((DownloadManager)localObject1).markState(paramIntent, i, l1);
                                if (!bool1) {
                                  break label1043;
                                }
                                MxMmsTransactionService.startRetrieveMms(this, paramIntent);
                                break;
                              }
                              MessagingNotification.blockingUpdateNewMessageIndicator(this, true, false);
                            }
                            else
                            {
                              MxMessageLogicHelper.sendReceivedAck(this, (String)localObject1, (String)localObject5, str2, "unsupported-source", "unknown source type: " + str5);
                            }
                          }
                          else
                          {
                            MxMessageLogicHelper.sendReceivedAck(this, (String)localObject1, (String)localObject5, str2, "malformed-body", "malformed attachment");
                            MyLog.w("MxMessageService", "malformed attachment");
                          }
                        }
                        else
                        {
                          MxMessageLogicHelper.sendReceivedAck(this, (String)localObject1, (String)localObject5, str2, "malformed-body", "missing attachment");
                        }
                      }
                      else if ("mx2".equals(localObject4))
                      {
                        localObject4 = ((JSONObject)localObject3).optString("subject");
                        localObject7 = paramIntent.getBundleExtra("attachment");
                        if (localObject7 != null)
                        {
                          str1 = ((Bundle)localObject7).getString("mimeType");
                          String str6 = ((Bundle)localObject7).getString("publicUrl");
                          localObject6 = ((Bundle)localObject7).getString("mxExtension");
                          localObject3 = null;
                          paramIntent = (Intent)localObject3;
                          if (!TextUtils.isEmpty(str6))
                          {
                            String str7 = ((Bundle)localObject7).getString("fileSha1");
                            paramIntent = (Intent)localObject3;
                            if (!TextUtils.isEmpty(str7)) {
                              paramIntent = str6 + " " + str7;
                            }
                          }
                          localObject3 = paramIntent;
                          if (TextUtils.isEmpty(paramIntent)) {
                            localObject3 = ((Bundle)localObject7).getString("shareId");
                          }
                          paramIntent = ((Bundle)localObject7).getString("expireAt");
                          localObject7 = ((Bundle)localObject7).getString("msgSize");
                          if ((!TextUtils.isEmpty(str1)) && (!TextUtils.isEmpty((CharSequence)localObject3)) && (TextUtils.isDigitsOnly(paramIntent)) && (TextUtils.isDigitsOnly((CharSequence)localObject7)))
                          {
                            l3 = adjustExpiredTime(Long.valueOf(paramIntent).longValue());
                            l4 = Long.valueOf((String)localObject7).longValue();
                            paramIntent = Contact.get(str3).getMxPhoneNumber();
                            MxIdCache.put(paramIntent, str4);
                            MidPhoneMap.put(str4, paramIntent);
                            if (MxIdCache.online(paramIntent, 12884901891L, true)) {
                              MxTaskService.queryStatus(this, paramIntent);
                            }
                            k = 0;
                            j = k;
                            if (str3 != null)
                            {
                              j = k;
                              if (!B2cMessageUtils.isB2cNumber(str3)) {
                                j = ExtraTelephony.getSmsBlockType(this, str3, null, i);
                              }
                            }
                            if ("phone".equals(str5))
                            {
                              MxMessageLogicHelper.sendReceivedAck(this, (String)localObject1, (String)localObject5, str2);
                              paramIntent = MxMessagePduHelper.persistNotification(this, str3, System.currentTimeMillis(), localLong.longValue(), l3, (String)localObject4, str1, l4, (String)localObject3, l2, l1, j, m, (String)localObject6);
                              Mx2MmsTransactionService.startRetrieveMx2(this, paramIntent);
                              if (m == 3) {
                                DownloadMxV2FileService.startDownloadAudio(getApplicationContext(), (String)localObject3, i, paramIntent, true);
                              }
                              MessagingNotification.blockingUpdateNewMessageIndicator(this, true, false);
                            }
                            else
                            {
                              MxMessageLogicHelper.sendReceivedAck(this, (String)localObject1, (String)localObject5, str2, "unsupported-source", "unknown source type: " + str5);
                            }
                          }
                          else
                          {
                            MxMessageLogicHelper.sendReceivedAck(this, (String)localObject1, (String)localObject5, str2, "malformed-body", "malformed attachment");
                          }
                        }
                        else
                        {
                          MxMessageLogicHelper.sendReceivedAck(this, (String)localObject1, (String)localObject5, str2, "malformed-body", "missing attachment");
                        }
                      }
                      else
                      {
                        MxMessageLogicHelper.sendReceivedAck(this, (String)localObject1, (String)localObject5, str2, "unsupported-type", "unsupported msg type: " + (String)localObject4);
                      }
                    }
                  }
                }
              }
              if (!"com.xiaomi.mms.mx.ACTION_HANDLE_MX_SENT".equals(str1)) {
                break;
              }
              l1 = paramIntent.getLongExtra("msgId", -1L);
              if (MxMessageLogicHelper.isSms(l1))
              {
                MessageUtils.updateSmsStatus(this, l1, 16);
                return;
              }
            } while (!MxMessageLogicHelper.isMms(l1));
            MxMessagePduHelper.updateMmsToSent(this, l1);
            return;
            if (!"com.xiaomi.mms.mx.ACTION_HANDLE_MX_DELIVERED".equals(str1)) {
              break;
            }
            MxMessageLogicHelper.sendAckToServer(this, (String)localObject1, (String)localObject5, str2);
          } while (RecentMessageCache.contain(str2));
          l1 = paramIntent.getLongExtra("msgId", -1L);
          bool1 = MxMessageLogicHelper.isSms(l1);
          boolean bool2 = MxMessageLogicHelper.isB2cSms(l1);
          boolean bool3 = MxMessageLogicHelper.isMms(l1);
          if (paramIntent.getBooleanExtra("error", false)) {
            if (bool1) {
              MessageUtils.handleMxSmsFailed(this, l1, i, bool2);
            }
          }
          for (;;)
          {
            RecentMessageCache.add(str2);
            return;
            if (bool3)
            {
              paramIntent = MxMessagePduHelper.getMmsMessageUriFromMxId(getContentResolver(), l1, 2);
              if (paramIntent != null)
              {
                i = MxMessagePduHelper.getMessageMx2Type(this, ContentUris.parseId(paramIntent));
                MxMessagePduHelper.setResponseStatus(this, paramIntent, 224);
                l1 = ContentUris.parseId(paramIntent);
                if (i > 0) {}
                for (bool1 = true;; bool1 = false)
                {
                  MxMessagePduHelper.handleMxMmsFailed(this, l1, true, bool1);
                  break;
                }
                if (bool1)
                {
                  if (MessageUtils.updateSmsStatus(this, l1, 17) > 0)
                  {
                    increaseMxSmsCount();
                    paramIntent = getSmsMessageUriFromMxId(getContentResolver(), l1);
                    if (paramIntent != null) {
                      MessagingNotification.nonBlockingUpdateDeliveryInfo(getApplicationContext(), paramIntent);
                    }
                  }
                }
                else if ((bool3) && (MxMessagePduHelper.updateMmsToDelivered(this, l1) != null)) {
                  increaseMxMmsCount();
                }
              }
            }
          }
        } while (!"com.xiaomi.mms.mx.ACTION_HANDLE_PRESENCE".equals(str1));
        localObject1 = paramIntent.getStringExtra("mid");
        boolean bool1 = paramIntent.getBooleanExtra("available", false);
        paramIntent = paramIntent.getStringExtra("client_attrs");
        l1 = 1L;
        l2 = l1;
        if (paramIntent != null)
        {
          paramIntent = PushAttributes.parse(paramIntent).get("cap");
          l2 = l1;
          if (!TextUtils.isEmpty(paramIntent))
          {
            l1 = 0L;
            paramIntent = MxCapabilityText.parse(paramIntent).iterator();
            for (;;)
            {
              l2 = l1;
              if (!paramIntent.hasNext()) {
                break;
              }
              localObject3 = (String)paramIntent.next();
              if ("sms".equals(localObject3)) {
                l1 |= 1L;
              } else if ("mms".equals(localObject3)) {
                l1 |= 0x2;
              } else if ("mx2image".equals(localObject3)) {
                l1 |= 0x100000000;
              } else if ("mx2audio".equals(localObject3)) {
                l1 |= 0x200000000;
              } else if ("mxV2mms2".equals(localObject3)) {
                l1 |= 0x400000000;
              }
            }
          }
        }
        paramIntent = MidPhoneMap.get((String)localObject1);
        if (!bool1) {
          break;
        }
      } while (paramIntent == null);
      MxIdCache.online(paramIntent, l2, false);
      return;
    } while (paramIntent == null);
    MxIdCache.offline(paramIntent);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.transaction.MxMessageService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */