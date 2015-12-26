package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Looper;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.channel.commonutils.string.MD5;
import com.xiaomi.push.service.PushConstants;
import com.xiaomi.push.service.PushProvision;
import com.xiaomi.xmpush.thrift.ActionType;
import com.xiaomi.xmpush.thrift.PushMetaInfo;
import com.xiaomi.xmpush.thrift.XmPushActionContainer;
import com.xiaomi.xmpush.thrift.XmPushActionRegistration;
import com.xiaomi.xmpush.thrift.XmPushActionUnRegistration;
import com.xiaomi.xmpush.thrift.XmPushThriftSerializeUtils;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.thrift.TBase;

public class PushServiceClient
{
  private static PushServiceClient sInstance;
  private static final ArrayList<BufferedRequest> sPendingRequest = new ArrayList();
  private Context mContext;
  private Integer mDeviceProvisioned = null;
  private boolean mIsMiuiPushServiceEnabled = false;
  private String mSession;
  private Intent registerTask = null;
  
  private PushServiceClient(Context paramContext)
  {
    mContext = paramContext.getApplicationContext();
    mSession = null;
    mIsMiuiPushServiceEnabled = serviceInstalled();
  }
  
  private Intent createServiceIntent()
  {
    Intent localIntent = new Intent();
    String str = mContext.getPackageName();
    if ((shouldUseMIUIPush()) && (!"com.xiaomi.xmsf".equals(str)))
    {
      localIntent.setPackage("com.xiaomi.xmsf");
      localIntent.setClassName("com.xiaomi.xmsf", getPushServiceName());
      localIntent.putExtra("mipush_app_package", str);
      disableMyPushService();
      return localIntent;
    }
    enableMyPushService();
    localIntent.setComponent(new ComponentName(mContext, "com.xiaomi.push.service.XMPushService"));
    localIntent.putExtra("mipush_app_package", str);
    return localIntent;
  }
  
  private void disableMyPushService()
  {
    try
    {
      mContext.getPackageManager().setComponentEnabledSetting(new ComponentName(mContext, "com.xiaomi.push.service.XMPushService"), 2, 1);
      return;
    }
    catch (Throwable localThrowable) {}
  }
  
  private void enableMyPushService()
  {
    try
    {
      mContext.getPackageManager().setComponentEnabledSetting(new ComponentName(mContext, "com.xiaomi.push.service.XMPushService"), 1, 1);
      return;
    }
    catch (Throwable localThrowable) {}
  }
  
  public static PushServiceClient getInstance(Context paramContext)
  {
    if (sInstance == null) {
      sInstance = new PushServiceClient(paramContext);
    }
    return sInstance;
  }
  
  private String getPushServiceName()
  {
    try
    {
      if (mContext.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 4).versionCode >= 106) {
        return "com.xiaomi.push.service.XMPushService";
      }
    }
    catch (Exception localException) {}
    return "com.xiaomi.xmsf.push.service.XMPushService";
  }
  
  private boolean isAutoTry()
  {
    String str = mContext.getPackageName();
    if ((str.contains("miui")) || (str.contains("xiaomi"))) {}
    while ((mContext.getApplicationInfo().flags & 0x1) != 0) {
      return true;
    }
    return false;
  }
  
  private boolean serviceInstalled()
  {
    Object localObject = mContext.getPackageManager();
    try
    {
      localObject = ((PackageManager)localObject).getPackageInfo("com.xiaomi.xmsf", 4);
      if (localObject == null) {
        return false;
      }
      int i = versionCode;
      if (i >= 105) {
        return true;
      }
    }
    catch (Exception localException) {}
    return false;
  }
  
  public <T extends TBase<T, ?>> void addPendRequest(T arg1, ActionType paramActionType, boolean paramBoolean)
  {
    BufferedRequest localBufferedRequest = new BufferedRequest();
    message = ???;
    actionType = paramActionType;
    encrypt = paramBoolean;
    synchronized (sPendingRequest)
    {
      sPendingRequest.add(localBufferedRequest);
      if (sPendingRequest.size() > 10) {
        sPendingRequest.remove(0);
      }
      return;
    }
  }
  
  public void awakePushService()
  {
    mContext.startService(createServiceIntent());
  }
  
  public void clearLocalNotificationType()
  {
    Intent localIntent = createServiceIntent();
    localIntent.setAction("com.xiaomi.mipush.SET_NOTIFICATION_TYPE");
    localIntent.putExtra(PushConstants.EXTRA_PACKAGE_NAME, mContext.getPackageName());
    localIntent.putExtra(PushConstants.EXTRA_SIG, MD5.MD5_16(mContext.getPackageName()));
    mContext.startService(localIntent);
  }
  
  public void clearNotification(int paramInt)
  {
    Intent localIntent = createServiceIntent();
    localIntent.setAction("com.xiaomi.mipush.CLEAR_NOTIFICATION");
    localIntent.putExtra(PushConstants.EXTRA_PACKAGE_NAME, mContext.getPackageName());
    localIntent.putExtra(PushConstants.EXTRA_NOTIFY_ID, paramInt);
    mContext.startService(localIntent);
  }
  
  public boolean isProvisioned()
  {
    if ((shouldUseMIUIPush()) && (isAutoTry())) {
      if (mDeviceProvisioned == null)
      {
        mDeviceProvisioned = Integer.valueOf(PushProvision.getInstance(mContext).getProvisioned());
        if (mDeviceProvisioned.intValue() == 0)
        {
          ContentObserver local1 = new ContentObserver(new Handler(Looper.getMainLooper()))
          {
            public void onChange(boolean paramAnonymousBoolean)
            {
              PushServiceClient.access$002(PushServiceClient.this, Integer.valueOf(PushProvision.getInstance(mContext).getProvisioned()));
              if (mDeviceProvisioned.intValue() != 0)
              {
                mContext.getContentResolver().unregisterContentObserver(this);
                if (Network.hasNetwork(mContext)) {
                  processRegisterTask();
                }
              }
            }
          };
          mContext.getContentResolver().registerContentObserver(PushProvision.getInstance(mContext).getProvisionedUri(), false, local1);
        }
      }
    }
    return mDeviceProvisioned.intValue() != 0;
  }
  
  public void processPendRequest()
  {
    synchronized (sPendingRequest)
    {
      Iterator localIterator = sPendingRequest.iterator();
      if (localIterator.hasNext())
      {
        BufferedRequest localBufferedRequest = (BufferedRequest)localIterator.next();
        sendMessage(message, actionType, encrypt, false, null, true);
      }
    }
    sPendingRequest.clear();
  }
  
  public void processRegisterTask()
  {
    if (registerTask != null)
    {
      mContext.startService(registerTask);
      registerTask = null;
    }
  }
  
  public final void register(XmPushActionRegistration paramXmPushActionRegistration, boolean paramBoolean)
  {
    registerTask = null;
    Intent localIntent = createServiceIntent();
    paramXmPushActionRegistration = XmPushThriftSerializeUtils.convertThriftObjectToBytes(PushContainerHelper.generateRequestContainer(mContext, paramXmPushActionRegistration, ActionType.Registration));
    if (paramXmPushActionRegistration == null)
    {
      MyLog.warn("register fail, because msgBytes is null.");
      return;
    }
    localIntent.setAction("com.xiaomi.mipush.REGISTER_APP");
    localIntent.putExtra("mipush_app_id", AppInfoHolder.getInstance(mContext).getAppID());
    localIntent.putExtra("mipush_payload", paramXmPushActionRegistration);
    localIntent.putExtra("mipush_session", mSession);
    localIntent.putExtra("mipush_env_chanage", paramBoolean);
    localIntent.putExtra("mipush_env_type", AppInfoHolder.getInstance(mContext).getEnvType());
    if ((Network.hasNetwork(mContext)) && (isProvisioned()))
    {
      mContext.startService(localIntent);
      return;
    }
    registerTask = localIntent;
  }
  
  public final <T extends TBase<T, ?>> void sendMessage(T paramT, ActionType paramActionType, PushMetaInfo paramPushMetaInfo)
  {
    if (!paramActionType.equals(ActionType.Registration)) {}
    for (boolean bool = true;; bool = false)
    {
      sendMessage(paramT, paramActionType, bool, paramPushMetaInfo);
      return;
    }
  }
  
  public final <T extends TBase<T, ?>> void sendMessage(T paramT, ActionType paramActionType, boolean paramBoolean, PushMetaInfo paramPushMetaInfo)
  {
    sendMessage(paramT, paramActionType, paramBoolean, true, paramPushMetaInfo, true);
  }
  
  public final <T extends TBase<T, ?>> void sendMessage(T paramT, ActionType paramActionType, boolean paramBoolean1, PushMetaInfo paramPushMetaInfo, boolean paramBoolean2)
  {
    sendMessage(paramT, paramActionType, paramBoolean1, true, paramPushMetaInfo, paramBoolean2);
  }
  
  public final <T extends TBase<T, ?>> void sendMessage(T paramT, ActionType paramActionType, boolean paramBoolean1, boolean paramBoolean2, PushMetaInfo paramPushMetaInfo, boolean paramBoolean3)
  {
    sendMessage(paramT, paramActionType, paramBoolean1, paramBoolean2, paramPushMetaInfo, paramBoolean3, mContext.getPackageName(), AppInfoHolder.getInstance(mContext).getAppID());
  }
  
  public final <T extends TBase<T, ?>> void sendMessage(T paramT, ActionType paramActionType, boolean paramBoolean1, boolean paramBoolean2, PushMetaInfo paramPushMetaInfo, boolean paramBoolean3, String paramString1, String paramString2)
  {
    if (!AppInfoHolder.getInstance(mContext).appRegistered())
    {
      if (paramBoolean2)
      {
        addPendRequest(paramT, paramActionType, paramBoolean1);
        return;
      }
      MyLog.warn("drop the message before initialization.");
      return;
    }
    Intent localIntent = createServiceIntent();
    paramT = PushContainerHelper.generateRequestContainer(mContext, paramT, paramActionType, paramBoolean1, paramString1, paramString2);
    if (paramPushMetaInfo != null) {
      paramT.setMetaInfo(paramPushMetaInfo);
    }
    paramT = XmPushThriftSerializeUtils.convertThriftObjectToBytes(paramT);
    if (paramT == null)
    {
      MyLog.warn("send message fail, because msgBytes is null.");
      return;
    }
    localIntent.setAction("com.xiaomi.mipush.SEND_MESSAGE");
    localIntent.putExtra("mipush_payload", paramT);
    localIntent.putExtra("com.xiaomi.mipush.MESSAGE_CACHE", paramBoolean3);
    mContext.startService(localIntent);
  }
  
  public boolean shouldUseMIUIPush()
  {
    return (mIsMiuiPushServiceEnabled) && (1 == AppInfoHolder.getInstance(mContext).getEnvType());
  }
  
  public final void unregister(XmPushActionUnRegistration paramXmPushActionUnRegistration)
  {
    Intent localIntent = createServiceIntent();
    paramXmPushActionUnRegistration = XmPushThriftSerializeUtils.convertThriftObjectToBytes(PushContainerHelper.generateRequestContainer(mContext, paramXmPushActionUnRegistration, ActionType.UnRegistration));
    if (paramXmPushActionUnRegistration == null)
    {
      MyLog.warn("unregister fail, because msgBytes is null.");
      return;
    }
    localIntent.setAction("com.xiaomi.mipush.UNREGISTER_APP");
    localIntent.putExtra("mipush_app_id", AppInfoHolder.getInstance(mContext).getAppID());
    localIntent.putExtra("mipush_payload", paramXmPushActionUnRegistration);
    mContext.startService(localIntent);
  }
  
  static class BufferedRequest<T extends TBase<T, ?>>
  {
    ActionType actionType;
    boolean encrypt;
    T message;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mipush.sdk.PushServiceClient
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */