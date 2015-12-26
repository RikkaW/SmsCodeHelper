package com.android.mms.service;

import android.app.ActivityManagerNative;
import android.app.IActivityManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.UserInfo;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Telephony.Mms;
import android.provider.Telephony.Mms.Inbox;
import android.service.carrier.ICarrierMessagingService;
import android.telephony.CarrierMessagingServiceManager;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.service.exception.MmsHttpException;
import com.google.android.mms.MmsException;
import com.meizu.android.mms.pdu.MzPduParser;
import com.meizu.android.mms.pdu.MzPduPersister;
import com.meizu.android.mms.pdu.MzRetrieveConf;
import com.meizu.android.mms.util.MzSqliteWrapper;
import com.meizu.mms.utils.MzMmsServiceUtils;

public class DownloadRequest
  extends MmsRequest
{
  private final Uri mContentUri;
  private final PendingIntent mDownloadedIntent;
  private final String mLocationUrl;
  
  public DownloadRequest(MmsRequest.RequestManager paramRequestManager, int paramInt, String paramString1, Uri paramUri, PendingIntent paramPendingIntent, String paramString2, Bundle paramBundle)
  {
    super(paramRequestManager, null, paramInt, paramString2, paramBundle);
    mLocationUrl = paramString1;
    mDownloadedIntent = paramPendingIntent;
    mContentUri = paramUri;
  }
  
  private void notifyOfDownload(Context paramContext)
  {
    Intent localIntent = new Intent("android.provider.Telephony.MMS_DOWNLOADED");
    localIntent.addFlags(134217728);
    Object localObject1 = null;
    try
    {
      localObject2 = ActivityManagerNative.getDefault().getRunningUserIds();
      localObject1 = localObject2;
    }
    catch (RemoteException localRemoteException)
    {
      Object localObject2;
      int i;
      UserHandle localUserHandle;
      for (;;) {}
    }
    localObject2 = localObject1;
    if (localObject1 == null)
    {
      localObject2 = new int[1];
      localObject2[0] = UserHandle.ALL.getIdentifier();
    }
    localObject1 = (UserManager)paramContext.getSystemService("user");
    i = localObject2.length - 1;
    if (i >= 0)
    {
      localUserHandle = new UserHandle(localObject2[i]);
      if (localObject2[i] != 0) {
        if (!((UserManager)localObject1).hasUserRestriction("no_sms", localUserHandle)) {}
      }
      for (;;)
      {
        i -= 1;
        break;
        UserInfo localUserInfo = ((UserManager)localObject1).getUserInfo(localObject2[i]);
        if ((localUserInfo != null) && (!localUserInfo.isManagedProfile())) {
          paramContext.sendOrderedBroadcastAsUser(localIntent, localUserHandle, "android.permission.RECEIVE_MMS", 18, null, null, -1, null, null);
        }
      }
    }
  }
  
  protected int checkResponse(byte[] paramArrayOfByte)
  {
    return -1;
  }
  
  protected byte[] doHttp(Context paramContext, MmsNetworkManager paramMmsNetworkManager, ApnSettings paramApnSettings)
    throws MmsHttpException
  {
    paramContext = paramMmsNetworkManager.getOrCreateHttpClient();
    if (paramContext == null)
    {
      Log.e("MmsService", "MMS network is not ready!");
      throw new MmsHttpException(0, "MMS network is not ready");
    }
    return paramContext.execute(mLocationUrl, null, "GET", paramApnSettings.isProxySet(), paramApnSettings.getProxyAddress(), paramApnSettings.getProxyPort(), mMmsConfig);
  }
  
  protected PendingIntent getPendingIntent()
  {
    return mDownloadedIntent;
  }
  
  protected Uri persistIfRequired(Context paramContext, int paramInt, byte[] paramArrayOfByte)
  {
    notifyOfDownload(paramContext);
    if ((!mRequestManager.getAutoPersistingPref()) && (!"com.android.mms".equals(mCreator))) {
      return null;
    }
    Log.d("MmsService", "DownloadRequest.persistIfRequired");
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length < 1))
    {
      Log.e("MmsService", "DownloadRequest.persistIfRequired: empty response");
      return null;
    }
    long l = Binder.clearCallingIdentity();
    try
    {
      paramArrayOfByte = new MzPduParser(paramArrayOfByte).parse();
      if ((paramArrayOfByte == null) || (!(paramArrayOfByte instanceof MzRetrieveConf)))
      {
        Log.e("MmsService", "DownloadRequest.persistIfRequired: invalid parsed PDU");
        return null;
      }
      MzSqliteWrapper.delete(paramContext, paramContext.getContentResolver(), Telephony.Mms.CONTENT_URI, "m_type=? AND ct_l =?", new String[] { Integer.toString(130), mLocationUrl });
      paramArrayOfByte = MzPduPersister.getPduPersister(paramContext).persist(paramArrayOfByte, Telephony.Mms.Inbox.CONTENT_URI, true, true, null);
      if (paramArrayOfByte == null)
      {
        Log.e("MmsService", "DownloadRequest.persistIfRequired: can not persist message");
        return null;
      }
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("date", Long.valueOf(System.currentTimeMillis() / 1000L));
      localContentValues.put("read", Integer.valueOf(0));
      localContentValues.put("seen", Integer.valueOf(0));
      if (!TextUtils.isEmpty(mCreator)) {
        localContentValues.put("creator", mCreator);
      }
      localContentValues.put("sub_id", Integer.valueOf(mSubId));
      localContentValues.put("ct_l", mLocationUrl);
      localContentValues.put("association_id", new Long(System.currentTimeMillis()));
      localContentValues.put("imsi", MzMmsServiceUtils.getSubscriberIdBySubId(mSubId));
      localContentValues.put("sim_id", Integer.valueOf(MzMmsServiceUtils.getSlotIdBySubId(mSubId)));
      if (MzSqliteWrapper.update(paramContext, paramContext.getContentResolver(), paramArrayOfByte, localContentValues, null, null) != 1) {
        Log.e("MmsService", "DownloadRequest.persistIfRequired: can not update message");
      }
      return paramArrayOfByte;
    }
    catch (MmsException paramContext)
    {
      Log.e("MmsService", "DownloadRequest.persistIfRequired: can not persist message", paramContext);
      return null;
    }
    catch (SQLiteException paramContext)
    {
      for (;;)
      {
        Log.e("MmsService", "DownloadRequest.persistIfRequired: can not update message", paramContext);
        Binder.restoreCallingIdentity(l);
      }
    }
    catch (RuntimeException paramContext)
    {
      for (;;)
      {
        Log.e("MmsService", "DownloadRequest.persistIfRequired: can not parse response", paramContext);
        Binder.restoreCallingIdentity(l);
      }
    }
    finally
    {
      Binder.restoreCallingIdentity(l);
    }
  }
  
  protected boolean prepareForHttpRequest()
  {
    return true;
  }
  
  protected void putOriginalUrl(Intent paramIntent)
  {
    if (mLocationUrl != null)
    {
      paramIntent.putExtra("oriuri", mLocationUrl.toString());
      return;
    }
    paramIntent.putExtra("oriuri", "");
  }
  
  protected void revokeUriPermission(Context paramContext)
  {
    if (mContentUri != null) {
      paramContext.revokeUriPermission(mContentUri, 2);
    }
  }
  
  protected boolean transferResponse(Intent paramIntent, byte[] paramArrayOfByte)
  {
    return mRequestManager.writePduToContentUri(mContentUri, paramArrayOfByte);
  }
  
  public void tryDownloadingByCarrierApp(Context paramContext, String paramString)
  {
    CarrierDownloadManager localCarrierDownloadManager = new CarrierDownloadManager(null);
    localCarrierDownloadManager.downloadMms(paramContext, paramString, new CarrierDownloadCompleteCallback(paramContext, localCarrierDownloadManager));
  }
  
  private final class CarrierDownloadCompleteCallback
    extends MmsRequest.CarrierMmsActionCallback
  {
    private final DownloadRequest.CarrierDownloadManager mCarrierDownloadManager;
    private final Context mContext;
    
    public CarrierDownloadCompleteCallback(Context paramContext, DownloadRequest.CarrierDownloadManager paramCarrierDownloadManager)
    {
      super();
      mContext = paramContext;
      mCarrierDownloadManager = paramCarrierDownloadManager;
    }
    
    public void onDownloadMmsComplete(int paramInt)
    {
      Log.d("MmsService", "Carrier app result for download: " + paramInt);
      mCarrierDownloadManager.disposeConnection(mContext);
      if (!maybeFallbackToRegularDelivery(paramInt)) {
        processResult(mContext, MmsRequest.toSmsManagerResult(paramInt), null, 0);
      }
    }
    
    public void onSendMmsComplete(int paramInt, byte[] paramArrayOfByte)
    {
      Log.e("MmsService", "Unexpected onSendMmsComplete call with result: " + paramInt);
    }
  }
  
  private final class CarrierDownloadManager
    extends CarrierMessagingServiceManager
  {
    private volatile DownloadRequest.CarrierDownloadCompleteCallback mCarrierDownloadCallback;
    
    private CarrierDownloadManager() {}
    
    void downloadMms(Context paramContext, String paramString, DownloadRequest.CarrierDownloadCompleteCallback paramCarrierDownloadCompleteCallback)
    {
      mCarrierDownloadCallback = paramCarrierDownloadCompleteCallback;
      if (bindToCarrierMessagingService(paramContext, paramString))
      {
        Log.v("MmsService", "bindService() for carrier messaging service succeeded");
        return;
      }
      Log.e("MmsService", "bindService() for carrier messaging service failed");
      paramCarrierDownloadCompleteCallback.onDownloadMmsComplete(1);
    }
    
    protected void onServiceReady(ICarrierMessagingService paramICarrierMessagingService)
    {
      try
      {
        paramICarrierMessagingService.downloadMms(mContentUri, mSubId, Uri.parse(mLocationUrl), mCarrierDownloadCallback);
        return;
      }
      catch (RemoteException paramICarrierMessagingService)
      {
        Log.e("MmsService", "Exception downloading MMS using the carrier messaging service: " + paramICarrierMessagingService);
        mCarrierDownloadCallback.onDownloadMmsComplete(1);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.service.DownloadRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */