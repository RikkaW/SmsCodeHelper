package com.android.mms.backup;

import android.content.ContentProviderOperation;
import android.content.ContentProviderOperation.Builder;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.RemoteException;
import android.provider.Telephony.Sms;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Vector;

public class SmsManager
{
  private String COLUMN_ADDRESS = "address";
  private String COLUMN_DATE = "date";
  private String COLUMN_ID = "_id";
  private Uri CONTENT_URI = Telephony.Sms.CONTENT_URI;
  private Uri INSERT_URI = CONTENT_URI.buildUpon().appendQueryParameter("need_full_insert_uri", "1").appendQueryParameter("check_duplication", "1").build();
  private ArrayList<ContentProviderOperation> mBatchOpertions = new ArrayList();
  private HashSet<String> mExistingMessages;
  private ContentResolver mResolver;
  
  public SmsManager(Context paramContext)
  {
    mResolver = paramContext.getContentResolver();
    mExistingMessages = new HashSet();
  }
  
  private String makeKey(long paramLong, String paramString)
  {
    if (paramString == null) {
      return paramLong + "$";
    }
    int j = paramString.length() - 7;
    int i = j;
    if (j < 0) {
      i = 0;
    }
    paramString = paramString.substring(i, paramString.length());
    return paramLong + "$" + paramString;
  }
  
  private ContentValues prepareContentValues(SmsProtos.Sms paramSms)
  {
    ContentValues localContentValues = new ContentValues();
    int j = paramSms.getType();
    if (j != 4)
    {
      i = j;
      if (j != 6) {}
    }
    else
    {
      i = 5;
    }
    localContentValues.put("type", Integer.valueOf(i));
    String str = paramSms.getAddress();
    if (!TextUtils.isEmpty(str)) {
      localContentValues.put("address", str);
    }
    if (paramSms.getSubject() != null) {
      localContentValues.put("subject", paramSms.getSubject());
    }
    if (paramSms.getBody() != null) {
      localContentValues.put("body", paramSms.getBody());
    }
    localContentValues.put("date", Long.valueOf(paramSms.getDate()));
    if (paramSms.getRead()) {}
    for (int i = 1;; i = 0)
    {
      localContentValues.put("read", Integer.valueOf(i));
      localContentValues.put("seen", Boolean.valueOf(paramSms.getSeen()));
      localContentValues.put("status", Integer.valueOf(paramSms.getStatus()));
      localContentValues.put("date_sent", Long.valueOf(paramSms.getServerDate()));
      if (paramSms.getServiceCenter() != null) {
        localContentValues.put("service_center", paramSms.getServiceCenter());
      }
      localContentValues.put("protocol", Integer.valueOf(paramSms.getProtocol()));
      localContentValues.put("reply_path_present", Boolean.valueOf(paramSms.getReplyPathPresent()));
      localContentValues.put("locked", Boolean.valueOf(paramSms.getLocked()));
      localContentValues.put("mx_id", Long.valueOf(paramSms.getMxId()));
      localContentValues.put("mx_status", Integer.valueOf(paramSms.getMxStatus()));
      return localContentValues;
    }
  }
  
  private void setSmsField(SmsProtos.Sms.Builder paramBuilder, Cursor paramCursor, int paramInt)
  {
    boolean bool2 = false;
    boolean bool3 = false;
    boolean bool4 = false;
    boolean bool1 = false;
    String str = paramCursor.getColumnName(paramInt);
    if (str.equals("_id")) {
      paramBuilder.setLuid(String.valueOf(paramCursor.getLong(paramInt)));
    }
    do
    {
      return;
      if (str.equals("type"))
      {
        int i = paramCursor.getInt(paramInt);
        if (i != 4)
        {
          paramInt = i;
          if (i != 6) {}
        }
        else
        {
          paramInt = 5;
        }
        paramBuilder.setType(paramInt);
        return;
      }
      if ((str.equals("address")) && (paramCursor.getString(paramInt) != null))
      {
        paramBuilder.setAddress(paramCursor.getString(paramInt));
        return;
      }
      if ((str.equals("subject")) && (paramCursor.getString(paramInt) != null))
      {
        paramBuilder.setSubject(paramCursor.getString(paramInt));
        return;
      }
      if ((str.equals("body")) && (paramCursor.getString(paramInt) != null))
      {
        paramBuilder.setBody(paramCursor.getString(paramInt));
        return;
      }
      if (str.equals("date"))
      {
        paramBuilder.setDate(paramCursor.getLong(paramInt));
        return;
      }
      if (str.equals("read"))
      {
        if (paramCursor.getInt(paramInt) == 0) {}
        for (;;)
        {
          paramBuilder.setRead(bool1);
          return;
          bool1 = true;
        }
      }
      if (str.equals("seen"))
      {
        if (paramCursor.getInt(paramInt) == 0) {}
        for (bool1 = bool2;; bool1 = true)
        {
          paramBuilder.setSeen(bool1);
          return;
        }
      }
      if (str.equals("status"))
      {
        paramBuilder.setStatus(paramCursor.getInt(paramInt));
        return;
      }
      if (str.equals("date_sent"))
      {
        paramBuilder.setServerDate(paramCursor.getLong(paramInt));
        return;
      }
      if ((str.equals("service_center")) && (paramCursor.getString(paramInt) != null))
      {
        paramBuilder.setServiceCenter(paramCursor.getString(paramInt));
        return;
      }
      if ((str.equals("protocol")) && (paramCursor.getString(paramInt) != null))
      {
        paramBuilder.setProtocol(paramCursor.getInt(paramInt));
        return;
      }
      if ((str.equals("reply_path_present")) && (paramCursor.getString(paramInt) != null))
      {
        if (paramCursor.getInt(paramInt) == 0) {}
        for (bool1 = bool3;; bool1 = true)
        {
          paramBuilder.setReplyPathPresent(bool1);
          return;
        }
      }
      if ((str.equals("locked")) && (paramCursor.getString(paramInt) != null))
      {
        if (paramCursor.getInt(paramInt) == 0) {}
        for (bool1 = bool4;; bool1 = true)
        {
          paramBuilder.setLocked(bool1);
          return;
        }
      }
      if ((str.equals("mx_id")) && (paramCursor.getString(paramInt) != null))
      {
        paramBuilder.setMxId(paramCursor.getLong(paramInt));
        return;
      }
    } while ((!str.equals("mx_status")) || (paramCursor.getString(paramInt) == null));
    paramBuilder.setMxStatus(paramCursor.getInt(paramInt));
  }
  
  public boolean add(SmsProtos.Sms paramSms)
  {
    long l = paramSms.getDate();
    String str = paramSms.getAddress();
    paramSms = prepareContentValues(paramSms);
    if (exists(l, str)) {
      return false;
    }
    paramSms = ContentProviderOperation.newInsert(INSERT_URI).withValues(paramSms).build();
    mBatchOpertions.add(paramSms);
    mExistingMessages.add(makeKey(l, str));
    return true;
  }
  
  public ContentProviderResult[] apply()
  {
    Object localObject = null;
    try
    {
      ContentProviderResult[] arrayOfContentProviderResult = mResolver.applyBatch("sms", mBatchOpertions);
      localObject = arrayOfContentProviderResult;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        Log.e("SmsManager", "RemoteException", localRemoteException);
      }
    }
    catch (OperationApplicationException localOperationApplicationException)
    {
      for (;;)
      {
        Log.e("SmsManager", "OperationApplicationException", localOperationApplicationException);
      }
    }
    mBatchOpertions.clear();
    return (ContentProviderResult[])localObject;
  }
  
  public boolean exists(long paramLong, String paramString)
  {
    return mExistingMessages.contains(makeKey(paramLong, paramString));
  }
  
  public SmsProtos.Sms load(long paramLong)
  {
    SmsProtos.Sms.Builder localBuilder = SmsProtos.Sms.newBuilder();
    Object localObject1 = null;
    try
    {
      Cursor localCursor = mResolver.query(CONTENT_URI, null, COLUMN_ID + "=" + paramLong, null, "date DESC LIMIT 1");
      if (localCursor != null)
      {
        localObject1 = localCursor;
        if (localCursor.moveToFirst())
        {
          localObject1 = localCursor;
          int i = localCursor.getColumnCount() - 1;
          while (i >= 0)
          {
            localObject1 = localCursor;
            setSmsField(localBuilder, localCursor, i);
            i -= 1;
          }
        }
      }
      if (localCursor != null) {
        localCursor.close();
      }
      return localBuilder.build();
    }
    finally
    {
      if (localObject1 != null) {
        ((Cursor)localObject1).close();
      }
    }
  }
  
  public Vector<String> prepareAllSmsIds()
  {
    Vector localVector = new Vector();
    Object localObject1 = null;
    try
    {
      Cursor localCursor = mResolver.query(Telephony.Sms.CONTENT_URI, new String[] { "_id" }, "timed=0 AND mx_status!=1 AND mx_status!=16 AND (type=1 OR type=5 OR type=2)", null, "date ASC");
      if (localCursor == null) {
        if (localCursor != null) {
          localCursor.close();
        }
      }
      do
      {
        return localVector;
        localObject1 = localCursor;
        int i = localCursor.getColumnIndexOrThrow("_id");
        localObject1 = localCursor;
        localCursor.moveToFirst();
        for (;;)
        {
          localObject1 = localCursor;
          if (localCursor.isAfterLast()) {
            break;
          }
          localObject1 = localCursor;
          localVector.add(String.valueOf(localCursor.getLong(i)));
          localObject1 = localCursor;
          localCursor.moveToNext();
        }
      } while (localObject2 == null);
    }
    finally
    {
      if (localObject1 != null) {
        ((Cursor)localObject1).close();
      }
    }
    ((Cursor)localObject2).close();
    return localVector;
  }
  
  public void prepareSms()
  {
    mExistingMessages.clear();
    Object localObject = COLUMN_DATE;
    String str = COLUMN_ADDRESS;
    localObject = mResolver.query(CONTENT_URI, new String[] { localObject, str }, null, null, null);
    if (localObject == null) {}
    do
    {
      return;
      int i = ((Cursor)localObject).getColumnIndexOrThrow(COLUMN_DATE);
      int j = ((Cursor)localObject).getColumnIndexOrThrow(COLUMN_ADDRESS);
      ((Cursor)localObject).moveToFirst();
      while (!((Cursor)localObject).isAfterLast())
      {
        long l = ((Cursor)localObject).getLong(i);
        str = ((Cursor)localObject).getString(j);
        mExistingMessages.add(makeKey(l, str));
        ((Cursor)localObject).moveToNext();
      }
    } while (localObject == null);
    ((Cursor)localObject).close();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.backup.SmsManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */