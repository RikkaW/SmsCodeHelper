package com.android.mms.service;

import android.app.PendingIntent;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Binder;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.Telephony.MmsSms.PendingMessages;
import android.service.carrier.ICarrierMessagingService;
import android.telephony.CarrierMessagingServiceManager;
import android.util.Log;
import com.android.mms.service.exception.MmsHttpException;
import com.meizu.android.mms.pdu.MzPduParser;
import com.meizu.android.mms.pdu.MzPduPersister;
import com.meizu.android.mms.pdu.MzSendConf;
import com.meizu.android.mms.util.MzSqliteWrapper;
import com.meizu.mms.utils.MzMmsServiceUtils;

public class SendRequest
  extends MmsRequest
{
  private final String mLocationUrl;
  private byte[] mPduData;
  private final Uri mPduUri;
  private final PendingIntent mSentIntent;
  
  public SendRequest(MmsRequest.RequestManager paramRequestManager, int paramInt, Uri paramUri1, Uri paramUri2, String paramString1, PendingIntent paramPendingIntent, String paramString2, Bundle paramBundle)
  {
    super(paramRequestManager, paramUri2, paramInt, paramString2, paramBundle);
    mPduUri = paramUri1;
    mPduData = null;
    mLocationUrl = paramString1;
    mSentIntent = paramPendingIntent;
  }
  
  public SendRequest(MmsRequest.RequestManager paramRequestManager, int paramInt, byte[] paramArrayOfByte, Uri paramUri, String paramString1, PendingIntent paramPendingIntent, String paramString2, Bundle paramBundle)
  {
    super(paramRequestManager, paramUri, paramInt, paramString2, paramBundle);
    mPduUri = null;
    mPduData = paramArrayOfByte;
    mLocationUrl = paramString1;
    mSentIntent = paramPendingIntent;
  }
  
  private boolean readPduFromContentUri()
  {
    StringBuilder localStringBuilder = new StringBuilder().append("readPduFromContentUri");
    if (mPduUri == null) {}
    for (String str = null;; str = mPduUri.toString())
    {
      Log.d("MmsService", str);
      if (mPduData == null) {
        break;
      }
      return true;
    }
    int i = mMmsConfig.getMaxMessageSize();
    mPduData = mRequestManager.readPduFromContentUri(mPduUri, i);
    if (mPduData != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  protected int checkResponse(byte[] paramArrayOfByte)
  {
    int i = 1;
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0))
    {
      Log.d("MmsService", "checkResponse get response is null, maybe is not a normal sending");
      i = -1;
    }
    int j;
    do
    {
      return i;
      Log.d("MmsService", "SendConf length = " + paramArrayOfByte.length);
      paramArrayOfByte = (MzSendConf)new MzPduParser(paramArrayOfByte).parse();
      if (paramArrayOfByte == null)
      {
        Log.e("MmsService", "No M-Send.conf received.");
        return 1;
      }
      j = paramArrayOfByte.getResponseStatus();
      Log.d("MmsService", "checkResponse get response  = " + j);
    } while (j != 128);
    return -1;
  }
  
  protected byte[] doHttp(Context paramContext, MmsNetworkManager paramMmsNetworkManager, ApnSettings paramApnSettings)
    throws MmsHttpException
  {
    paramMmsNetworkManager = paramMmsNetworkManager.getOrCreateHttpClient();
    if (paramMmsNetworkManager == null)
    {
      Log.e("MmsService", "MMS network is not ready!");
      throw new MmsHttpException(0, "MMS network is not ready");
    }
    if (mLocationUrl != null) {}
    for (paramContext = mLocationUrl;; paramContext = paramApnSettings.getMmscUrl()) {
      return paramMmsNetworkManager.execute(paramContext, mPduData, "POST", paramApnSettings.isProxySet(), paramApnSettings.getProxyAddress(), paramApnSettings.getProxyPort(), mMmsConfig);
    }
  }
  
  protected PendingIntent getPendingIntent()
  {
    return mSentIntent;
  }
  
  public int getRetryIndex(Context paramContext, Uri paramUri)
  {
    long l = ContentUris.parseId(paramUri);
    paramUri = Telephony.MmsSms.PendingMessages.CONTENT_URI.buildUpon();
    paramUri.appendQueryParameter("protocol", "mms");
    paramUri.appendQueryParameter("message", String.valueOf(l));
    paramContext = MzSqliteWrapper.query(paramContext, paramContext.getContentResolver(), paramUri.build(), null, null, null, null);
    int j = -1;
    int i = j;
    if (paramContext != null) {
      i = j;
    }
    try
    {
      if (paramContext.getCount() == 1)
      {
        i = j;
        if (paramContext.moveToFirst())
        {
          i = paramContext.getInt(paramContext.getColumnIndexOrThrow("retry_index"));
          j = paramContext.getInt(paramContext.getColumnIndexOrThrow("err_type"));
          Log.d("MmsService", "queryPendingMessageRetryIndex, retryIndex = " + i + ", errorType = " + j);
        }
      }
      return i;
    }
    finally
    {
      paramContext.close();
    }
  }
  
  public void markPendingMessageErrorType(Context paramContext, Uri paramUri)
  {
    ContentValues localContentValues = new ContentValues(2);
    long l = ContentUris.parseId(paramUri);
    localContentValues.put("err_type", Integer.valueOf(19));
    localContentValues.put("retry_index", Integer.valueOf(4));
    MzSqliteWrapper.update(paramContext, paramContext.getContentResolver(), Telephony.MmsSms.PendingMessages.CONTENT_URI, localContentValues, "msg_id=" + l, null);
  }
  
  protected Uri persistIfRequired(Context paramContext, int paramInt, byte[] paramArrayOfByte)
  {
    if (mMessageUri == null) {
      return null;
    }
    l = Binder.clearCallingIdentity();
    Object localObject2 = null;
    localObject1 = localObject2;
    if (paramArrayOfByte != null) {
      localObject1 = localObject2;
    }
    for (;;)
    {
      try
      {
        if (paramArrayOfByte.length > 0)
        {
          paramArrayOfByte = new MzPduParser(paramArrayOfByte).parse();
          localObject1 = localObject2;
          if (paramArrayOfByte != null)
          {
            localObject1 = localObject2;
            if ((paramArrayOfByte instanceof MzSendConf)) {
              localObject1 = (MzSendConf)paramArrayOfByte;
            }
          }
        }
        paramArrayOfByte = new ContentValues();
        i = getRetryIndex(paramContext, mMessageUri);
        if (localObject1 != null) {
          continue;
        }
        if (i >= 4) {
          continue;
        }
        paramArrayOfByte.put("resp_st", Integer.valueOf(134));
      }
      catch (SQLiteException paramContext)
      {
        int i;
        Log.e("MmsService", "SendRequest.persistIfRequired: can not update message", paramContext);
        return null;
        if (paramInt != -1) {
          continue;
        }
        paramArrayOfByte.put("msg_box", Integer.valueOf(2));
        paramArrayOfByte.put("resp_st", Integer.valueOf(((MzSendConf)localObject1).getResponseStatus()));
        if (!MzMmsServiceUtils.checkIsFailedRespStatus(((MzSendConf)localObject1).getResponseStatus())) {
          continue;
        }
        markPendingMessageErrorType(paramContext, mMessageUri);
        Log.e("MmsService", "SendRequest.persistIfRequired: Server returned an error code: " + ((MzSendConf)localObject1).getResponseStatus());
        paramArrayOfByte.put("m_id", MzPduPersister.toIsoString(((MzSendConf)localObject1).getMessageId()));
        continue;
      }
      catch (RuntimeException paramContext)
      {
        Log.e("MmsService", "SendRequest.persistIfRequired: can not parse response", paramContext);
        Binder.restoreCallingIdentity(l);
        continue;
      }
      finally
      {
        Binder.restoreCallingIdentity(l);
      }
      Log.d("MmsService", "MzMmsServiceUtils.isCTVersion() = " + MzMmsServiceUtils.isCTVersion() + ", result = " + paramInt + ", retryIndex = " + i);
      if ((MzMmsServiceUtils.isCTVersion()) && (paramInt != -1))
      {
        paramArrayOfByte.put("resp_st", Integer.valueOf(9527));
        markPendingMessageErrorType(paramContext, mMessageUri);
      }
      MzSqliteWrapper.update(paramContext, paramContext.getContentResolver(), mMessageUri, paramArrayOfByte, null, null);
      paramContext = mMessageUri;
      Binder.restoreCallingIdentity(l);
      return paramContext;
      paramArrayOfByte.put("resp_st", Integer.valueOf(9527));
      paramArrayOfByte.put("msg_box", Integer.valueOf(5));
    }
  }
  
  protected boolean prepareForHttpRequest()
  {
    return readPduFromContentUri();
  }
  
  protected void putOriginalUrl(Intent paramIntent)
  {
    if (mMessageUri != null)
    {
      paramIntent.putExtra("oriuri", mMessageUri.toString());
      return;
    }
    paramIntent.putExtra("oriuri", "");
  }
  
  protected void revokeUriPermission(Context paramContext)
  {
    if (mPduUri != null) {
      paramContext.revokeUriPermission(mPduUri, 1);
    }
  }
  
  /* Error */
  public void storeInOutbox(Context paramContext)
  {
    // Byte code:
    //   0: invokestatic 275	android/os/Binder:clearCallingIdentity	()J
    //   3: lstore_2
    //   4: aload_0
    //   5: invokespecial 333	com/android/mms/service/SendRequest:readPduFromContentUri	()Z
    //   8: ifne +17 -> 25
    //   11: ldc 53
    //   13: ldc_w 355
    //   16: invokestatic 110	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   19: pop
    //   20: lload_2
    //   21: invokestatic 300	android/os/Binder:restoreCallingIdentity	(J)V
    //   24: return
    //   25: aload_0
    //   26: getfield 269	com/android/mms/service/SendRequest:mMessageUri	Landroid/net/Uri;
    //   29: ifnonnull +223 -> 252
    //   32: new 96	com/meizu/android/mms/pdu/MzPduParser
    //   35: dup
    //   36: aload_0
    //   37: getfield 29	com/android/mms/service/SendRequest:mPduData	[B
    //   40: invokespecial 99	com/meizu/android/mms/pdu/MzPduParser:<init>	([B)V
    //   43: invokevirtual 103	com/meizu/android/mms/pdu/MzPduParser:parse	()Lcom/meizu/android/mms/pdu/MzGenericPdu;
    //   46: astore 4
    //   48: aload 4
    //   50: ifnonnull +17 -> 67
    //   53: ldc 53
    //   55: ldc_w 357
    //   58: invokestatic 110	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   61: pop
    //   62: lload_2
    //   63: invokestatic 300	android/os/Binder:restoreCallingIdentity	(J)V
    //   66: return
    //   67: aload 4
    //   69: instanceof 359
    //   72: ifne +17 -> 89
    //   75: ldc 53
    //   77: ldc_w 361
    //   80: invokestatic 63	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   83: pop
    //   84: lload_2
    //   85: invokestatic 300	android/os/Binder:restoreCallingIdentity	(J)V
    //   88: return
    //   89: aload_0
    //   90: aload_1
    //   91: invokestatic 365	com/meizu/android/mms/pdu/MzPduPersister:getPduPersister	(Landroid/content/Context;)Lcom/meizu/android/mms/pdu/MzPduPersister;
    //   94: aload 4
    //   96: getstatic 368	android/provider/Telephony$Mms$Outbox:CONTENT_URI	Landroid/net/Uri;
    //   99: iconst_1
    //   100: iconst_1
    //   101: aconst_null
    //   102: invokevirtual 372	com/meizu/android/mms/pdu/MzPduPersister:persist	(Lcom/meizu/android/mms/pdu/MzGenericPdu;Landroid/net/Uri;ZZLjava/util/HashMap;)Landroid/net/Uri;
    //   105: putfield 269	com/android/mms/service/SendRequest:mMessageUri	Landroid/net/Uri;
    //   108: aload_0
    //   109: getfield 269	com/android/mms/service/SendRequest:mMessageUri	Landroid/net/Uri;
    //   112: ifnonnull +17 -> 129
    //   115: ldc 53
    //   117: ldc_w 374
    //   120: invokestatic 110	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   123: pop
    //   124: lload_2
    //   125: invokestatic 300	android/os/Binder:restoreCallingIdentity	(J)V
    //   128: return
    //   129: new 239	android/content/ContentValues
    //   132: dup
    //   133: iconst_5
    //   134: invokespecial 242	android/content/ContentValues:<init>	(I)V
    //   137: astore 4
    //   139: aload 4
    //   141: ldc_w 376
    //   144: invokestatic 381	java/lang/System:currentTimeMillis	()J
    //   147: ldc2_w 382
    //   150: ldiv
    //   151: invokestatic 388	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   154: invokevirtual 391	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Long;)V
    //   157: aload 4
    //   159: ldc_w 393
    //   162: iconst_1
    //   163: invokestatic 247	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   166: invokevirtual 251	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   169: aload 4
    //   171: ldc_w 395
    //   174: iconst_1
    //   175: invokestatic 247	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   178: invokevirtual 251	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   181: aload_0
    //   182: getfield 398	com/android/mms/service/SendRequest:mCreator	Ljava/lang/String;
    //   185: invokestatic 404	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   188: ifne +15 -> 203
    //   191: aload 4
    //   193: ldc_w 406
    //   196: aload_0
    //   197: getfield 398	com/android/mms/service/SendRequest:mCreator	Ljava/lang/String;
    //   200: invokevirtual 328	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   203: aload 4
    //   205: ldc_w 408
    //   208: aload_0
    //   209: getfield 412	com/android/mms/service/SendRequest:mSubId	I
    //   212: invokestatic 247	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   215: invokevirtual 251	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   218: aload_1
    //   219: aload_1
    //   220: invokevirtual 198	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   223: aload_0
    //   224: getfield 269	com/android/mms/service/SendRequest:mMessageUri	Landroid/net/Uri;
    //   227: aload 4
    //   229: aconst_null
    //   230: aconst_null
    //   231: invokestatic 260	com/meizu/android/mms/util/MzSqliteWrapper:update	(Landroid/content/Context;Landroid/content/ContentResolver;Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   234: iconst_1
    //   235: if_icmpeq +12 -> 247
    //   238: ldc 53
    //   240: ldc_w 414
    //   243: invokestatic 110	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   246: pop
    //   247: lload_2
    //   248: invokestatic 300	android/os/Binder:restoreCallingIdentity	(J)V
    //   251: return
    //   252: new 239	android/content/ContentValues
    //   255: dup
    //   256: iconst_3
    //   257: invokespecial 242	android/content/ContentValues:<init>	(I)V
    //   260: astore 4
    //   262: aload 4
    //   264: ldc_w 376
    //   267: invokestatic 381	java/lang/System:currentTimeMillis	()J
    //   270: ldc2_w 382
    //   273: ldiv
    //   274: invokestatic 388	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   277: invokevirtual 391	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Long;)V
    //   280: aload 4
    //   282: ldc_w 302
    //   285: iconst_4
    //   286: invokestatic 247	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   289: invokevirtual 251	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   292: aload 4
    //   294: ldc_w 408
    //   297: aload_0
    //   298: getfield 412	com/android/mms/service/SendRequest:mSubId	I
    //   301: invokestatic 247	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   304: invokevirtual 251	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   307: aload_1
    //   308: aload_1
    //   309: invokevirtual 198	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   312: aload_0
    //   313: getfield 269	com/android/mms/service/SendRequest:mMessageUri	Landroid/net/Uri;
    //   316: aload 4
    //   318: aconst_null
    //   319: aconst_null
    //   320: invokestatic 260	com/meizu/android/mms/util/MzSqliteWrapper:update	(Landroid/content/Context;Landroid/content/ContentResolver;Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   323: iconst_1
    //   324: if_icmpeq -77 -> 247
    //   327: ldc 53
    //   329: ldc_w 414
    //   332: invokestatic 110	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   335: pop
    //   336: goto -89 -> 247
    //   339: astore_1
    //   340: ldc 53
    //   342: ldc_w 416
    //   345: aload_1
    //   346: invokestatic 307	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   349: pop
    //   350: lload_2
    //   351: invokestatic 300	android/os/Binder:restoreCallingIdentity	(J)V
    //   354: return
    //   355: astore_1
    //   356: ldc 53
    //   358: ldc_w 418
    //   361: aload_1
    //   362: invokestatic 307	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   365: pop
    //   366: lload_2
    //   367: invokestatic 300	android/os/Binder:restoreCallingIdentity	(J)V
    //   370: return
    //   371: astore_1
    //   372: lload_2
    //   373: invokestatic 300	android/os/Binder:restoreCallingIdentity	(J)V
    //   376: aload_1
    //   377: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	378	0	this	SendRequest
    //   0	378	1	paramContext	Context
    //   3	370	2	l	long
    //   46	271	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   4	20	339	com/google/android/mms/MmsException
    //   25	48	339	com/google/android/mms/MmsException
    //   53	62	339	com/google/android/mms/MmsException
    //   67	84	339	com/google/android/mms/MmsException
    //   89	124	339	com/google/android/mms/MmsException
    //   129	203	339	com/google/android/mms/MmsException
    //   203	247	339	com/google/android/mms/MmsException
    //   252	336	339	com/google/android/mms/MmsException
    //   4	20	355	java/lang/RuntimeException
    //   25	48	355	java/lang/RuntimeException
    //   53	62	355	java/lang/RuntimeException
    //   67	84	355	java/lang/RuntimeException
    //   89	124	355	java/lang/RuntimeException
    //   129	203	355	java/lang/RuntimeException
    //   203	247	355	java/lang/RuntimeException
    //   252	336	355	java/lang/RuntimeException
    //   4	20	371	finally
    //   25	48	371	finally
    //   53	62	371	finally
    //   67	84	371	finally
    //   89	124	371	finally
    //   129	203	371	finally
    //   203	247	371	finally
    //   252	336	371	finally
    //   340	350	371	finally
    //   356	366	371	finally
  }
  
  protected boolean transferResponse(Intent paramIntent, byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte != null) {
      paramIntent.putExtra("android.telephony.extra.MMS_DATA", paramArrayOfByte);
    }
    return true;
  }
  
  public void trySendingByCarrierApp(Context paramContext, String paramString)
  {
    CarrierSendManager localCarrierSendManager = new CarrierSendManager(null);
    localCarrierSendManager.sendMms(paramContext, paramString, new CarrierSendCompleteCallback(paramContext, localCarrierSendManager));
  }
  
  private final class CarrierSendCompleteCallback
    extends MmsRequest.CarrierMmsActionCallback
  {
    private final SendRequest.CarrierSendManager mCarrierSendManager;
    private final Context mContext;
    
    public CarrierSendCompleteCallback(Context paramContext, SendRequest.CarrierSendManager paramCarrierSendManager)
    {
      super();
      mContext = paramContext;
      mCarrierSendManager = paramCarrierSendManager;
    }
    
    public void onDownloadMmsComplete(int paramInt)
    {
      Log.e("MmsService", "Unexpected onDownloadMmsComplete call with result: " + paramInt);
    }
    
    public void onSendMmsComplete(int paramInt, byte[] paramArrayOfByte)
    {
      Log.d("MmsService", "Carrier app result for send: " + paramInt);
      mCarrierSendManager.disposeConnection(mContext);
      if (!maybeFallbackToRegularDelivery(paramInt)) {
        processResult(mContext, MmsRequest.toSmsManagerResult(paramInt), paramArrayOfByte, 0);
      }
    }
  }
  
  private final class CarrierSendManager
    extends CarrierMessagingServiceManager
  {
    private volatile SendRequest.CarrierSendCompleteCallback mCarrierSendCompleteCallback;
    
    private CarrierSendManager() {}
    
    protected void onServiceReady(ICarrierMessagingService paramICarrierMessagingService)
    {
      Uri localUri = null;
      try
      {
        if (mLocationUrl != null) {
          localUri = Uri.parse(mLocationUrl);
        }
        paramICarrierMessagingService.sendMms(mPduUri, mSubId, localUri, mCarrierSendCompleteCallback);
        return;
      }
      catch (RemoteException paramICarrierMessagingService)
      {
        Log.e("MmsService", "Exception sending MMS using the carrier messaging service: " + paramICarrierMessagingService);
        mCarrierSendCompleteCallback.onSendMmsComplete(1, null);
      }
    }
    
    void sendMms(Context paramContext, String paramString, SendRequest.CarrierSendCompleteCallback paramCarrierSendCompleteCallback)
    {
      mCarrierSendCompleteCallback = paramCarrierSendCompleteCallback;
      if (bindToCarrierMessagingService(paramContext, paramString))
      {
        Log.v("MmsService", "bindService() for carrier messaging service succeeded");
        return;
      }
      Log.e("MmsService", "bindService() for carrier messaging service failed");
      paramCarrierSendCompleteCallback.onSendMmsComplete(1, null);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.service.SendRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */