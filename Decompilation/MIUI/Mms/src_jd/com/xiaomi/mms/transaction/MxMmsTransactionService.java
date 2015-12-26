package com.xiaomi.mms.transaction;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony.Mms;
import android.text.TextUtils;
import com.android.mms.util.MSimUtils;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import com.xiaomi.accountsdk.activate.ActivateManager;
import com.xiaomi.accountsdk.activate.ActivateManager.ActivateManagerFuture;
import com.xiaomi.micloudsdk.micloudrichmedia.MiCloudRichMediaManager;
import com.xiaomi.mms.utils.MiCloudMmsCodec;
import com.xiaomi.mms.utils.MxConfigCompat;
import com.xiaomi.mms.utils.MxMessageLogicHelper;
import com.xiaomi.mms.utils.MxMessagePduHelper;
import com.xiaomi.mms.utils.PrefUtils;
import com.xiaomi.mms.utils.logger.MyLog;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import miui.push.Message;
import miui.push.ServiceClient;

public class MxMmsTransactionService
  extends WakenService
  implements MiCloudMxMmsTransactionHandler.MiCloudMediaManagerCallback, MiCloudRichMediaManagerFactory
{
  protected ExtendedAuthToken[] mFileToken;
  protected int mInvalidTokenCount;
  protected boolean mIsSendByMxV2 = false;
  protected MxMmsTransactionHandler mMxMmsTransactionHandler;
  protected ExtendedAuthToken[] mOtherIDCFileToken;
  protected MiCloudRichMediaManager[] mOtherIDCRichMediaManager;
  protected MiCloudRichMediaManager[] mRichMediaManager;
  protected final LinkedHashSet<MxTransaction> mTransactionSet = new LinkedHashSet();
  
  private void enqueueAll()
  {
    Cursor localCursor = MxMessagePduHelper.getPendingMxMessages(this);
    if (localCursor != null)
    {
      try
      {
        localCursor.moveToPosition(-1);
        int i = 0;
        while (localCursor.moveToNext())
        {
          int j = localCursor.getColumnIndex("_id");
          int k = localCursor.getColumnIndexOrThrow("msg_box");
          MxTransaction localMxTransaction = new MxTransaction(ContentUris.withAppendedId(Telephony.Mms.CONTENT_URI, localCursor.getLong(j)), localCursor.getInt(k), null);
          if (!mTransactionSet.contains(localMxTransaction))
          {
            i += 1;
            mTransactionSet.add(localMxTransaction);
          }
        }
        MyLog.i("MxMmsTransactionService", "mx mms enqueued, count: " + i);
      }
      finally
      {
        localCursor.close();
      }
      localCursor.close();
    }
  }
  
  private void enqueueRetrieveTransaction(Uri paramUri)
  {
    if (paramUri == null) {
      MyLog.e("MxMmsTransactionService", "retrieve mms with null uri");
    }
    do
    {
      return;
      MxMessagePduHelper.markFailedMessageAsPending(this, paramUri);
      paramUri = new MxTransaction(paramUri, 1, null);
    } while (mTransactionSet.contains(paramUri));
    mTransactionSet.add(paramUri);
  }
  
  private void enqueueSendTransaction(Uri paramUri)
  {
    if (paramUri == null) {
      MyLog.e("MxMmsTransactionService", "send mms with null uri");
    }
    do
    {
      return;
      paramUri = new MxTransaction(paramUri, 2, null);
    } while (mTransactionSet.contains(paramUri));
    mTransactionSet.add(paramUri);
  }
  
  private ExtendedAuthToken getFileToken(String paramString)
    throws IOException
  {
    int i = PushSession.getInstance(this).getSimIdByMid(paramString);
    if (i == -1) {
      return null;
    }
    if (mFileToken[i] != null) {
      return mFileToken[i];
    }
    String str1 = PrefUtils.getString(this, getFileTokenPref() + i);
    mFileToken[i] = ExtendedAuthToken.parse(str1);
    if (mFileToken[i] != null) {
      return mFileToken[i];
    }
    Object localObject2;
    if (paramString != null)
    {
      localObject2 = ActivateManager.get(this).getSimAuthToken(i, getMxSid(i));
      str1 = null;
      str2 = null;
      paramString = str1;
    }
    try
    {
      localObject2 = (Bundle)((ActivateManager.ActivateManagerFuture)localObject2).getResult();
      paramString = str1;
      str1 = ((Bundle)localObject2).getString("user_token");
      paramString = str1;
      localObject2 = ((Bundle)localObject2).getString("user_security");
      paramString = (String)localObject2;
      str2 = str1;
      str1 = paramString;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        MyLog.e("MxMmsTransactionService", "failed to get file upload token from server", localException);
        Object localObject1 = str2;
        str2 = paramString;
      }
    }
    if ((str2 != null) && (str1 != null)) {
      mFileToken[i] = ExtendedAuthToken.build(str2, str1);
    }
    if (mFileToken[i] != null) {
      PrefUtils.saveString(this, getFileTokenPref() + i, mFileToken[i].toPlain());
    }
    return mFileToken[i];
  }
  
  private void receiveMms(Uri paramUri)
  {
    mMxMmsTransactionHandler.handleReceiveMxMms(paramUri);
  }
  
  private void sendMms(Uri paramUri)
  {
    mMxMmsTransactionHandler.handleSendMxMms(paramUri);
  }
  
  private boolean sendPushMessage(Uri paramUri, String paramString1, long paramLong1, String paramString2, String paramString3, String paramString4, String paramString5, long paramLong2, long paramLong3)
  {
    if (paramLong1 > 0L)
    {
      MxMessagePduHelper.moveMxMmsToOutbox(this, paramUri, System.currentTimeMillis(), paramLong1);
      paramUri = MxMessageLogicHelper.constructMmsBody(paramString2, paramLong1);
      paramString1 = MxMessageLogicHelper.obtainMessage(paramString1, paramString3);
      paramString1.setPacketID(String.valueOf(paramLong1));
      paramString2 = MxMessageLogicHelper.base64Encode(paramUri);
      if (!TextUtils.isEmpty(paramString2)) {
        break label92;
      }
      paramString1.setBody(paramUri);
    }
    for (;;)
    {
      paramString1.addExtension(MxMessageLogicHelper.constructMmsAttachment(paramString5, paramString4, paramLong2, paramLong3));
      return ServiceClient.getInstance(this).sendMessage(paramString1);
      paramLong1 = MxMessageLogicHelper.nextMiId(true, false).longValue();
      break;
      label92:
      paramString1.setBody(paramString2, "base64");
    }
  }
  
  public static void startRetrieveMms(Context paramContext, Uri paramUri)
  {
    Intent localIntent = new Intent(paramContext, MxMmsTransactionService.class);
    localIntent.setAction("com.xiaomi.mms.ACTION_RETRIEVE");
    localIntent.setData(paramUri);
    beginStartingService(paramContext, localIntent);
  }
  
  public static void startSendMms(Context paramContext, Uri paramUri)
  {
    Intent localIntent = new Intent(paramContext, MxMmsTransactionService.class);
    localIntent.setAction("com.xiaomi.mms.ACTION_SEND_MMS");
    localIntent.setData(paramUri);
    beginStartingService(paramContext, localIntent);
  }
  
  public static void startSendMms(Context paramContext, Uri paramUri, boolean paramBoolean)
  {
    Intent localIntent = new Intent(paramContext, MxMmsTransactionService.class);
    localIntent.putExtra("key_send_by_mx_V2", paramBoolean);
    localIntent.setAction("com.xiaomi.mms.ACTION_SEND_MMS");
    localIntent.setData(paramUri);
    beginStartingService(paramContext, localIntent);
  }
  
  protected String getFileTokenPref()
  {
    if (mIsSendByMxV2) {
      return "pref_mx2_file_token";
    }
    return "pref_file_token";
  }
  
  public ExtendedAuthToken getMediaManagerToken(String paramString)
    throws IOException
  {
    if (mInvalidTokenCount < 3) {
      return getFileToken(paramString);
    }
    return null;
  }
  
  public MiCloudRichMediaManager getMiCloudRichMediaManager(Context paramContext, String paramString1, ExtendedAuthToken paramExtendedAuthToken, String paramString2)
  {
    int i = PushSession.getInstance(this).getSimIdByMid(paramString1);
    if (i == -1) {
      return null;
    }
    if (mRichMediaManager[i] == null) {
      mRichMediaManager[i] = new MiCloudRichMediaManager(paramContext, paramString1, paramExtendedAuthToken, paramString2);
    }
    for (;;)
    {
      return mRichMediaManager[i];
      mRichMediaManager[i].updateAuthToken(paramExtendedAuthToken);
    }
  }
  
  protected String getMxSid(int paramInt)
  {
    String str = PushSession.getInstance(getBaseContext()).getMyMid(paramInt);
    if (mIsSendByMxV2) {
      return MxConfigCompat.getMxV2Sid(getBaseContext(), str);
    }
    return MxConfigCompat.getMxV1Sid(getBaseContext(), str);
  }
  
  public MiCloudRichMediaManager getOtherIDCMiCloudRichMediaManager(Context paramContext, String paramString1, ExtendedAuthToken paramExtendedAuthToken, String paramString2)
  {
    int i = PushSession.getInstance(this).getSimIdByMid(paramString1);
    if (i == -1) {
      return null;
    }
    if (mOtherIDCRichMediaManager[i] == null) {
      mOtherIDCRichMediaManager[i] = new MiCloudRichMediaManager(paramContext, paramString1, paramExtendedAuthToken, paramString2);
    }
    for (;;)
    {
      return mOtherIDCRichMediaManager[i];
      mOtherIDCRichMediaManager[i].updateAuthToken(paramExtendedAuthToken);
    }
  }
  
  protected void handleIntent(Intent paramIntent)
  {
    Object localObject = paramIntent.getAction();
    Uri localUri = paramIntent.getData();
    mIsSendByMxV2 = paramIntent.getBooleanExtra("key_send_by_mx_V2", false);
    mMxMmsTransactionHandler.setSendByMxV2(mIsSendByMxV2);
    if ("com.xiaomi.mms.ACTION_SEND_MMS".equals(localObject))
    {
      enqueueSendTransaction(localUri);
      paramIntent = mTransactionSet.iterator();
    }
    for (;;)
    {
      if (!paramIntent.hasNext()) {
        break label154;
      }
      localObject = (MxTransaction)paramIntent.next();
      switch (mMsgBox)
      {
      default: 
        break;
      case 1: 
        receiveMms(mUri);
        continue;
        if ("com.xiaomi.mms.ACTION_RETRIEVE".equals(localObject))
        {
          enqueueRetrieveTransaction(localUri);
          break;
        }
        enqueueAll();
        break;
      case 2: 
        sendMms(mUri);
      }
    }
    label154:
    mTransactionSet.clear();
  }
  
  protected void invalidFileToken(String paramString)
  {
    int i = PushSession.getInstance(this).getSimIdByMid(paramString);
    if (i == -1)
    {
      MyLog.e("MxMmsTransactionService", "refresh file token failed, because sim index == -1)");
      return;
    }
    mFileToken[i] = null;
    PrefUtils.remove(this, getFileTokenPref() + i);
  }
  
  public void onCreate()
  {
    super.onCreate();
    int i = MSimUtils.getMultiSimCount();
    mFileToken = new ExtendedAuthToken[i];
    mRichMediaManager = new MiCloudRichMediaManager[i];
    mMxMmsTransactionHandler = new MiCloudMxMmsTransactionHandler(this, this, this);
    mOtherIDCFileToken = new ExtendedAuthToken[i];
    mOtherIDCRichMediaManager = new MiCloudRichMediaManager[i];
  }
  
  public boolean onDownloadMediaSuccess(Uri paramUri, String paramString, byte[] paramArrayOfByte)
  {
    mInvalidTokenCount = 0;
    long l = ContentUris.parseId(paramUri);
    if (paramUri != null) {
      try
      {
        MiCloudMmsCodec.decodeMmsBody(this, l, paramArrayOfByte, false);
        return true;
      }
      catch (Exception paramString)
      {
        MxMessagePduHelper.setResponseStatus(this, paramUri, 226);
        return false;
      }
    }
    MxMessagePduHelper.setResponseStatus(this, paramUri, 228);
    return false;
  }
  
  public void onMediaManagerTokenInvalid(String paramString)
  {
    invalidFileToken(paramString);
    mInvalidTokenCount += 1;
  }
  
  public boolean onUploadMediaSuccess(Uri paramUri, long paramLong1, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, long paramLong2, long paramLong3, String paramString6)
  {
    if (TextUtils.isEmpty(paramString2))
    {
      MyLog.w("MxMmsTransactionService", "my full mid is null,push connection not established");
      MxMessagePduHelper.setResponseStatus(this, paramUri, 195);
      return false;
    }
    return sendPushMessage(paramUri, paramString2, paramLong1, paramString1, paramString3, paramString4, paramString5, paramLong2, paramLong3);
  }
  
  private static class MxTransaction
  {
    final int mMsgBox;
    final Uri mUri;
    
    private MxTransaction(Uri paramUri, int paramInt)
    {
      mUri = paramUri;
      mMsgBox = paramInt;
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {}
      do
      {
        return true;
        if ((paramObject == null) || (getClass() != paramObject.getClass())) {
          return false;
        }
        paramObject = (MxTransaction)paramObject;
        if (mMsgBox != mMsgBox) {
          return false;
        }
        if (mUri == null) {
          break;
        }
      } while (mUri.equals(mUri));
      for (;;)
      {
        return false;
        if (mUri == null) {
          break;
        }
      }
    }
    
    public int hashCode()
    {
      if (mUri != null) {}
      for (int i = mUri.hashCode();; i = 0) {
        return i * 31 + mMsgBox;
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.transaction.MxMmsTransactionService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */