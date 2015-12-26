package com.xiaomi.mms.transaction;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.xiaomi.mms.utils.MxConfigCompat;
import com.xiaomi.mms.utils.MxMessageLogicHelper;
import com.xiaomi.mms.utils.MxMessagePduHelper;
import com.xiaomi.mms.utils.logger.MyLog;
import miui.push.Message;
import miui.push.ServiceClient;

public class Mx2MmsTransactionService
  extends MxMmsTransactionService
{
  private boolean sendPushMessage(Uri paramUri, String paramString1, long paramLong1, String paramString2, String paramString3, String paramString4, String paramString5, long paramLong2, long paramLong3, String paramString6)
  {
    if (paramLong1 > 0L)
    {
      MxMessagePduHelper.moveMxMmsToOutbox(this, paramUri, System.currentTimeMillis(), paramLong1);
      paramUri = MxMessageLogicHelper.constructMxBody(paramString2, paramLong1, paramString4);
      paramString2 = MxMessageLogicHelper.constructMxAttachment(paramString5, paramString4, paramLong2, paramLong3, paramString6);
      paramString1 = MxMessageLogicHelper.obtainMessage(paramString1, paramString3);
      paramString1.setPacketID(String.valueOf(paramLong1));
      paramString3 = MxMessageLogicHelper.base64Encode(paramUri);
      if (!TextUtils.isEmpty(paramString3)) {
        break label100;
      }
      paramString1.setBody(paramUri);
    }
    for (;;)
    {
      paramString1.addExtension(paramString2);
      return ServiceClient.getInstance(this).sendMessage(paramString1);
      paramLong1 = MxMessageLogicHelper.nextMiId(true, false).longValue();
      break;
      label100:
      paramString1.setBody(paramString3, "base64");
    }
  }
  
  public static void startRetrieveMx2(Context paramContext, Uri paramUri)
  {
    Intent localIntent = new Intent(paramContext, Mx2MmsTransactionService.class);
    localIntent.setAction("com.xiaomi.mms.ACTION_RETRIEVE");
    localIntent.setData(paramUri);
    beginStartingService(paramContext, localIntent);
  }
  
  public static void startSendMx2(Context paramContext, Uri paramUri)
  {
    Intent localIntent = new Intent(paramContext, Mx2MmsTransactionService.class);
    localIntent.setAction("com.xiaomi.mms.ACTION_SEND_MMS");
    localIntent.setData(paramUri);
    beginStartingService(paramContext, localIntent);
  }
  
  protected String getFileTokenPref()
  {
    return "pref_mx2_file_token";
  }
  
  protected String getMxSid(int paramInt)
  {
    String str = PushSession.getInstance(getBaseContext()).getMyMid(paramInt);
    return MxConfigCompat.getMxV2Sid(getBaseContext(), str);
  }
  
  public void onCreate()
  {
    super.onCreate();
    mMxMmsTransactionHandler = new MiCloudMx2MmsTransactionHandler(this, this, this);
  }
  
  public boolean onDownloadMediaSuccess(Uri paramUri, String paramString, byte[] paramArrayOfByte)
  {
    throw new UnsupportedOperationException("UnsupportedOperationException : Mx2Message should not go here");
  }
  
  public boolean onUploadMediaSuccess(Uri paramUri, long paramLong1, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, long paramLong2, long paramLong3, String paramString6)
  {
    if (paramString2 == null)
    {
      MyLog.w("Mx2MmsTransactionService.RICH", "my full mid is null,push connection not established");
      MxMessagePduHelper.setResponseStatus(this, paramUri, 195);
      return false;
    }
    return sendPushMessage(paramUri, paramString2, paramLong1, paramString1, paramString3, paramString4, paramString5, paramLong2, paramLong3, paramString6);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.transaction.Mx2MmsTransactionService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */