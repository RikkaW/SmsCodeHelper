package com.android.mms.service;

import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.Telephony.Threads;
import android.util.Log;
import com.android.internal.telephony.IMms.Stub;
import com.android.internal.telephony.SmsApplication;

class MmsService$1
  extends IMms.Stub
{
  MmsService$1(MmsService paramMmsService) {}
  
  public Uri addMultimediaMessageDraft(String paramString, Uri paramUri)
    throws RemoteException
  {
    Log.d("MmsService", "addMultimediaMessageDraft");
    MmsService.access$500(this$0);
    return MmsService.access$1400(this$0, paramUri, paramString);
  }
  
  public Uri addTextMessageDraft(String paramString1, String paramString2, String paramString3)
    throws RemoteException
  {
    Log.d("MmsService", "addTextMessageDraft");
    MmsService.access$500(this$0);
    return MmsService.access$1300(this$0, paramString2, paramString3, paramString1);
  }
  
  public boolean archiveStoredConversation(String paramString, long paramLong, boolean paramBoolean)
    throws RemoteException
  {
    Log.d("MmsService", "archiveStoredConversation " + paramLong + " " + paramBoolean);
    if (paramLong == -1L)
    {
      Log.e("MmsService", "archiveStoredConversation: invalid thread id");
      return false;
    }
    return MmsService.access$1200(this$0, paramLong, paramBoolean);
  }
  
  public boolean deleteStoredConversation(String paramString, long paramLong)
    throws RemoteException
  {
    Log.d("MmsService", "deleteStoredConversation " + paramLong);
    MmsService.access$500(this$0);
    if (paramLong == -1L)
    {
      Log.e("MmsService", "deleteStoredConversation: invalid thread id");
      return false;
    }
    paramString = ContentUris.withAppendedId(Telephony.Threads.CONTENT_URI, paramLong);
    paramLong = Binder.clearCallingIdentity();
    try
    {
      if (this$0.getContentResolver().delete(paramString, null, null) != 1)
      {
        Log.e("MmsService", "deleteStoredConversation: failed to delete");
        return false;
      }
    }
    catch (SQLiteException paramString)
    {
      for (;;)
      {
        Log.e("MmsService", "deleteStoredConversation: failed to delete", paramString);
        Binder.restoreCallingIdentity(paramLong);
      }
    }
    finally
    {
      Binder.restoreCallingIdentity(paramLong);
    }
    return true;
  }
  
  public boolean deleteStoredMessage(String paramString, Uri paramUri)
    throws RemoteException
  {
    Log.d("MmsService", "deleteStoredMessage " + paramUri);
    MmsService.access$500(this$0);
    if (!MmsService.access$1000(paramUri))
    {
      Log.e("MmsService", "deleteStoredMessage: invalid message URI: " + paramUri.toString());
      return false;
    }
    l = Binder.clearCallingIdentity();
    try
    {
      if (this$0.getContentResolver().delete(paramUri, null, null) != 1)
      {
        Log.e("MmsService", "deleteStoredMessage: failed to delete");
        return false;
      }
    }
    catch (SQLiteException paramString)
    {
      for (;;)
      {
        Log.e("MmsService", "deleteStoredMessage: failed to delete", paramString);
        Binder.restoreCallingIdentity(l);
      }
    }
    finally
    {
      Binder.restoreCallingIdentity(l);
    }
    return true;
  }
  
  public void downloadMessage(int paramInt, String paramString1, String paramString2, Uri paramUri, Bundle paramBundle, PendingIntent paramPendingIntent)
    throws RemoteException
  {
    Log.d("MmsService", "downloadMessage: " + paramString2 + ", subId = " + paramInt + ", contentUri = " + paramUri);
    MmsService.access$500(this$0);
    paramInt = MmsService.access$600(this$0, paramInt);
    paramString1 = new DownloadRequest(this$0, paramInt, paramString2, paramUri, paramPendingIntent, paramString1, paramBundle);
    paramString2 = MmsService.access$700(this$0);
    if (paramString2 != null)
    {
      Log.d("MmsService", "downloading message by carrier app");
      paramString1.tryDownloadingByCarrierApp(this$0, paramString2);
      return;
    }
    this$0.addSimRequest(paramString1);
  }
  
  public boolean getAutoPersisting()
    throws RemoteException
  {
    Log.d("MmsService", "getAutoPersisting");
    return this$0.getAutoPersistingPref();
  }
  
  public Bundle getCarrierConfigValues(int paramInt)
  {
    Log.d("MmsService", "getCarrierConfigValues");
    paramInt = MmsService.access$600(this$0, paramInt);
    MmsConfig localMmsConfig = MmsConfigManager.getInstance().getMmsConfigBySubId(paramInt);
    if (localMmsConfig == null) {
      return new Bundle();
    }
    return localMmsConfig.getCarrierConfigValues();
  }
  
  public Uri importMultimediaMessage(String paramString1, Uri paramUri, String paramString2, long paramLong, boolean paramBoolean1, boolean paramBoolean2)
  {
    Log.d("MmsService", "importMultimediaMessage");
    MmsService.access$500(this$0);
    return MmsService.access$900(this$0, paramUri, paramString2, paramLong, paramBoolean1, paramBoolean2, paramString1);
  }
  
  public Uri importTextMessage(String paramString1, String paramString2, int paramInt, String paramString3, long paramLong, boolean paramBoolean1, boolean paramBoolean2)
  {
    Log.d("MmsService", "importTextMessage");
    MmsService.access$500(this$0);
    return MmsService.access$800(this$0, paramString2, paramInt, paramString3, paramLong, paramBoolean1, paramBoolean2, paramString1);
  }
  
  public void sendMessage(int paramInt, String paramString1, Uri paramUri, String paramString2, Bundle paramBundle, PendingIntent paramPendingIntent)
    throws RemoteException
  {
    Log.d("MmsService", "sendMessage");
    MmsService.access$500(this$0);
    paramInt = MmsService.access$600(this$0, paramInt);
    paramUri = new SendRequest(this$0, paramInt, paramUri, null, paramString2, paramPendingIntent, paramString1, paramBundle);
    if (SmsApplication.shouldWriteMessageForPackage(paramString1, this$0)) {
      paramUri.storeInOutbox(this$0);
    }
    paramString1 = MmsService.access$700(this$0);
    if (paramString1 != null)
    {
      Log.d("MmsService", "sending message by carrier app");
      paramUri.trySendingByCarrierApp(this$0, paramString1);
      return;
    }
    this$0.addSimRequest(paramUri);
  }
  
  public void sendStoredMessage(int paramInt, String paramString, Uri paramUri, Bundle paramBundle, PendingIntent paramPendingIntent)
    throws RemoteException
  {
    Log.d("MmsService", "sendStoredMessage: messageUri = " + paramUri + ", subId = " + paramInt);
    MmsService.access$500(this$0);
    paramInt = MmsService.access$600(this$0, paramInt);
    if (!MmsService.access$1500(this$0, paramUri))
    {
      Log.e("MmsService", "sendStoredMessage: not FAILED or DRAFT message");
      MmsService.access$1600(this$0, paramPendingIntent);
      return;
    }
    byte[] arrayOfByte = MmsService.access$1700(this$0, paramUri);
    if ((arrayOfByte == null) || (arrayOfByte.length < 1))
    {
      Log.e("MmsService", "sendStoredMessage: failed to load PDU data");
      MmsService.access$1600(this$0, paramPendingIntent);
      return;
    }
    paramString = new SendRequest(this$0, paramInt, arrayOfByte, paramUri, null, paramPendingIntent, paramString, paramBundle);
    paramString.storeInOutbox(this$0);
    paramUri = MmsService.access$700(this$0);
    if (paramUri != null)
    {
      Log.d("MmsService", "sending message by carrier app");
      paramString.trySendingByCarrierApp(this$0, paramUri);
      return;
    }
    this$0.addSimRequest(paramString);
  }
  
  public void setAutoPersisting(String paramString, boolean paramBoolean)
    throws RemoteException
  {
    Log.d("MmsService", "setAutoPersisting " + paramBoolean);
    MmsService.access$500(this$0);
    paramString = this$0.getSharedPreferences("mmspref", 0).edit();
    paramString.putBoolean("autopersisting", paramBoolean);
    paramString.apply();
  }
  
  public boolean updateStoredMessageStatus(String paramString, Uri paramUri, ContentValues paramContentValues)
    throws RemoteException
  {
    Log.d("MmsService", "updateStoredMessageStatus " + paramUri);
    MmsService.access$500(this$0);
    return MmsService.access$1100(this$0, paramUri, paramContentValues);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.service.MmsService.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */