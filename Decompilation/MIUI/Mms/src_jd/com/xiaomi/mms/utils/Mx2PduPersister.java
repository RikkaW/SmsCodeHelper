package com.xiaomi.mms.utils;

import android.content.ContentProviderOperation;
import android.content.ContentProviderOperation.Builder;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.RemoteException;
import android.provider.Telephony.Mms;
import android.provider.Telephony.Mms.Draft;
import android.provider.Telephony.Mms.Inbox;
import android.provider.Telephony.Mms.Outbox;
import android.provider.Telephony.Mms.Sent;
import android.text.TextUtils;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.xiaomi.mms.data.Mx2MessageModel;
import com.xiaomi.mms.mx.common.GlobalData;
import com.xiaomi.mms.mx.data.Attachment;
import com.xiaomi.mms.mx.utils.Mx2ExtentionHelper;
import com.xiaomi.mms.utils.logger.MyLog;
import java.util.ArrayList;
import java.util.List;

public class Mx2PduPersister
{
  private static final String[] PDU_PROJECTION = { "_id", "msg_box", "thread_id", "sub", "m_id", "m_type", "date", "date_ms_part", "date_sent", "exp", "sim_id", "locked", "mx_type", "mx_extension", "d_tm" };
  
  private static ContentValues MxMessagetoContentValue(Mx2MessageModel paramMx2MessageModel)
  {
    ContentValues localContentValues = null;
    if (paramMx2MessageModel != null)
    {
      localContentValues = new ContentValues();
      localContentValues.put("thread_id", Long.valueOf(paramMx2MessageModel.getThreadId()));
      localContentValues.put("date", Long.valueOf(paramMx2MessageModel.getDate()));
      localContentValues.put("date_ms_part", Long.valueOf(paramMx2MessageModel.getDateMsPart()));
      localContentValues.put("date_sent", Long.valueOf(paramMx2MessageModel.getDateSent()));
      if (!paramMx2MessageModel.isLocked()) {
        break label199;
      }
    }
    label199:
    for (int i = 1;; i = 0)
    {
      localContentValues.put("locked", Integer.valueOf(i));
      localContentValues.put("m_type", Integer.valueOf(128));
      localContentValues.put("sim_id", Long.valueOf(paramMx2MessageModel.getSimId()));
      localContentValues.put("exp", paramMx2MessageModel.getExpireTimestamp());
      localContentValues.put("msg_box", Integer.valueOf(paramMx2MessageModel.getBoxId()));
      localContentValues.put("mx_type", paramMx2MessageModel.getMxType());
      if (!TextUtils.isEmpty(paramMx2MessageModel.getBody()))
      {
        localContentValues.put("sub", MiuiPduPersister.toIsoString(new EncodedStringValue(paramMx2MessageModel.getBody()).getTextString()));
        localContentValues.put("sub_cs", Integer.valueOf(106));
      }
      localContentValues.put("mx_extension", paramMx2MessageModel.buildLocalExtension());
      return localContentValues;
    }
  }
  
  public static Uri buildUri(int paramInt)
  {
    Uri localUri = Telephony.Mms.CONTENT_URI;
    switch (paramInt)
    {
    default: 
      return localUri;
    case 1: 
      return Telephony.Mms.Inbox.CONTENT_URI;
    case 2: 
      return Telephony.Mms.Sent.CONTENT_URI;
    case 3: 
      return Telephony.Mms.Draft.CONTENT_URI;
    }
    return Telephony.Mms.Outbox.CONTENT_URI;
  }
  
  public static Uri insertMxMessage(Context paramContext, Mx2MessageModel paramMx2MessageModel)
  {
    Object localObject = null;
    if (paramMx2MessageModel != null)
    {
      localObject = MxMessagetoContentValue(paramMx2MessageModel);
      paramContext = paramContext.getContentResolver().insert(buildUri(paramMx2MessageModel.getType()), (ContentValues)localObject);
      localObject = paramContext;
      if (paramContext != null)
      {
        localObject = paramContext;
        if (mConversation != null)
        {
          localObject = paramContext;
          if (mConversation.getRecipients() != null)
          {
            long l = ContentUris.parseId(paramContext);
            localObject = paramContext;
            if (l > 0L)
            {
              paramMx2MessageModel = mConversation.getRecipients().getNumbers();
              localObject = paramContext;
              if (paramMx2MessageModel != null)
              {
                paramMx2MessageModel = EncodedStringValue.encodeStrings(paramMx2MessageModel);
                updateAddress(GlobalData.app(), l, 151, paramMx2MessageModel);
                localObject = paramContext;
              }
            }
          }
        }
      }
    }
    return (Uri)localObject;
  }
  
  public static Mx2MessageModel loadMessageByMsgId(Context paramContext, long paramLong)
  {
    localObject5 = null;
    localObject6 = null;
    Object localObject7 = null;
    localObject4 = localObject6;
    Object localObject1;
    Object localObject3;
    if (paramLong > -1L)
    {
      localObject4 = null;
      localObject1 = null;
      localObject3 = paramContext.getContentResolver();
      paramContext = (Context)localObject1;
    }
    try
    {
      localObject3 = ((ContentResolver)localObject3).query(Telephony.Mms.CONTENT_URI.buildUpon().appendQueryParameter("caller_is_syncadapter", "1").build(), PDU_PROJECTION, "_id=? AND mx_type > 0", new String[] { String.valueOf(paramLong) }, null);
      localObject1 = localObject7;
      if (localObject3 != null)
      {
        localObject1 = localObject7;
        paramContext = (Context)localObject3;
        localObject4 = localObject3;
        if (((Cursor)localObject3).moveToFirst())
        {
          paramContext = (Context)localObject3;
          localObject4 = localObject3;
          localObject1 = new Mx2MessageModel((Cursor)localObject3);
        }
      }
      localObject4 = localObject1;
      if (localObject3 == null) {
        break label152;
      }
      localObject4 = localObject1;
      if (((Cursor)localObject3).isClosed()) {
        break label152;
      }
      paramContext = (Context)localObject3;
    }
    catch (SQLException localSQLException)
    {
      for (;;)
      {
        localObject4 = paramContext;
        MyLog.e("Mx2PduPersister.RICH", "loadMessageFromPdu fail");
        localObject4 = localObject6;
        if (paramContext != null)
        {
          localObject4 = localObject6;
          if (!paramContext.isClosed()) {
            Object localObject2 = localObject5;
          }
        }
      }
    }
    finally
    {
      if ((localObject4 == null) || (((Cursor)localObject4).isClosed())) {
        break label218;
      }
      ((Cursor)localObject4).close();
    }
    paramContext.close();
    localObject4 = localObject1;
    label152:
    return (Mx2MessageModel)localObject4;
  }
  
  public static Mx2MessageModel loadMessageByUri(Context paramContext, Uri paramUri)
  {
    long l = -1L;
    if (paramUri != null) {
      l = ContentUris.parseId(paramUri);
    }
    return loadMessageByMsgId(paramContext, l);
  }
  
  private static void persistAddress(ArrayList<ContentProviderOperation> paramArrayList, long paramLong, int paramInt, EncodedStringValue[] paramArrayOfEncodedStringValue)
  {
    ContentValues localContentValues = new ContentValues(4);
    int j = paramArrayOfEncodedStringValue.length;
    int i = 0;
    while (i < j)
    {
      EncodedStringValue localEncodedStringValue = paramArrayOfEncodedStringValue[i];
      localContentValues.clear();
      localContentValues.put("address", MiuiPduPersister.toIsoString(localEncodedStringValue.getTextString()));
      localContentValues.put("charset", Integer.valueOf(localEncodedStringValue.getCharacterSet()));
      localContentValues.put("type", Integer.valueOf(paramInt));
      paramArrayList.add(ContentProviderOperation.newInsert(Telephony.Mms.CONTENT_URI.buildUpon().appendPath(String.valueOf(paramLong)).appendPath("addr").build()).withValues(localContentValues).build());
      i += 1;
    }
  }
  
  public static void updateAddress(Context paramContext, long paramLong, int paramInt, EncodedStringValue[] paramArrayOfEncodedStringValue)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(ContentProviderOperation.newDelete(Telephony.Mms.CONTENT_URI.buildUpon().appendPath(String.valueOf(paramLong)).appendPath("addr").build()).withSelection("type=" + paramInt, null).build());
    persistAddress(localArrayList, paramLong, paramInt, paramArrayOfEncodedStringValue);
    try
    {
      paramContext.getContentResolver().applyBatch("mms", localArrayList);
      return;
    }
    catch (OperationApplicationException paramContext)
    {
      MyLog.e("Mx2PduPersister.RICH", "Error while applying batch", paramContext);
      return;
    }
    catch (RemoteException paramContext)
    {
      MyLog.e("Mx2PduPersister.RICH", "Error while applying batch", paramContext);
    }
  }
  
  public static int updateExtension(Context paramContext, Uri paramUri, List<Attachment> paramList)
  {
    ContentValues localContentValues = new ContentValues(1);
    localContentValues.put("mx_extension", Mx2ExtentionHelper.generateAttachmentsExtentionString(paramList, false));
    paramUri = paramUri.buildUpon().appendQueryParameter("blocked_flag", "2").build();
    return SqliteWrapper.update(paramContext, paramContext.getContentResolver(), paramUri, localContentValues, null, null);
  }
  
  public static int updateMxMessage(Context paramContext, Mx2MessageModel paramMx2MessageModel, long paramLong)
  {
    int j = 0;
    int i = j;
    if (paramLong > 0L)
    {
      i = j;
      if (paramMx2MessageModel != null)
      {
        paramMx2MessageModel = MxMessagetoContentValue(paramMx2MessageModel);
        i = paramContext.getContentResolver().update(Telephony.Mms.CONTENT_URI, paramMx2MessageModel, "_id=? ", new String[] { String.valueOf(paramLong) });
      }
    }
    return i;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.utils.Mx2PduPersister
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */