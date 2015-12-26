package com.xiaomi.push.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.BuildSettings;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.push.service.profile.MessageProfiling;
import com.xiaomi.smack.packet.CommonPacketExtension;
import com.xiaomi.smack.packet.Message;
import java.util.Iterator;
import java.util.List;
import org.apache.http.NameValuePair;

public class ServiceClient
{
  private static ServiceClient sInstance;
  private static String sSession = null;
  private Context mContext;
  private boolean mIsMiuiPushServiceEnabled = false;
  
  private ServiceClient(Context paramContext)
  {
    mContext = paramContext.getApplicationContext();
    if (serviceInstalled())
    {
      MyLog.v("use miui push service");
      mIsMiuiPushServiceEnabled = true;
    }
  }
  
  private Intent createServiceIntent()
  {
    if (isMiuiPushServiceEnabled())
    {
      localIntent = new Intent();
      localIntent.setPackage("com.xiaomi.xmsf");
      localIntent.setClassName("com.xiaomi.xmsf", getPushServiceName());
      str = mContext.getPackageName();
      localIntent.putExtra(PushConstants.EXTRA_PACKAGE_NAME, str);
      disableMyPushService();
      return localIntent;
    }
    Intent localIntent = new Intent(mContext, XMPushService.class);
    String str = mContext.getPackageName();
    localIntent.putExtra(PushConstants.EXTRA_PACKAGE_NAME, str);
    enableMyPushService();
    return localIntent;
  }
  
  private void disableMyPushService()
  {
    mContext.getPackageManager().setComponentEnabledSetting(new ComponentName(mContext, XMPushService.class), 2, 1);
  }
  
  private void enableMyPushService()
  {
    mContext.getPackageManager().setComponentEnabledSetting(new ComponentName(mContext, XMPushService.class), 1, 1);
  }
  
  public static ServiceClient getInstance(Context paramContext)
  {
    if (sInstance == null) {
      sInstance = new ServiceClient(paramContext);
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
  
  private String joinAttributes(List<NameValuePair> paramList)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 1;
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      NameValuePair localNameValuePair = (NameValuePair)localIterator.next();
      localStringBuilder.append(localNameValuePair.getName()).append(":").append(localNameValuePair.getValue());
      if (i < paramList.size()) {
        localStringBuilder.append(",");
      }
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  private void putOpenParamsIntoIntent(Intent paramIntent, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, boolean paramBoolean, List<NameValuePair> paramList1, List<NameValuePair> paramList2)
  {
    paramIntent.putExtra(PushConstants.EXTRA_USER_ID, paramString1);
    paramIntent.putExtra(PushConstants.EXTRA_CHANNEL_ID, paramString2);
    paramIntent.putExtra(PushConstants.EXTRA_TOKEN, paramString3);
    paramIntent.putExtra(PushConstants.EXTRA_SECURITY, paramString5);
    paramIntent.putExtra(PushConstants.EXTRA_AUTH_METHOD, paramString4);
    paramIntent.putExtra(PushConstants.EXTRA_KICK, paramBoolean);
    paramIntent.putExtra(PushConstants.EXTRA_SESSION, sSession);
    if (paramList1 != null)
    {
      paramString1 = joinAttributes(paramList1);
      if (!TextUtils.isEmpty(paramString1)) {
        paramIntent.putExtra(PushConstants.EXTRA_CLIENT_ATTR, paramString1);
      }
    }
    if (paramList2 != null)
    {
      paramString1 = joinAttributes(paramList2);
      if (!TextUtils.isEmpty(paramString1)) {
        paramIntent.putExtra(PushConstants.EXTRA_CLOUD_ATTR, paramString1);
      }
    }
  }
  
  private boolean serviceInstalled()
  {
    if (BuildSettings.IsTestBuild) {}
    for (;;)
    {
      return false;
      Object localObject = mContext.getPackageManager();
      try
      {
        localObject = ((PackageManager)localObject).getPackageInfo("com.xiaomi.xmsf", 4);
        if (localObject != null)
        {
          int i = versionCode;
          if (i >= 104) {
            return true;
          }
        }
      }
      catch (Exception localException) {}
    }
    return false;
  }
  
  public boolean closeChannel(String paramString)
  {
    Intent localIntent = createServiceIntent();
    localIntent.setAction(PushConstants.ACTION_CLOSE_CHANNEL);
    localIntent.putExtra(PushConstants.EXTRA_CHANNEL_ID, paramString);
    mContext.startService(localIntent);
    return true;
  }
  
  public boolean isMiuiPushServiceEnabled()
  {
    return mIsMiuiPushServiceEnabled;
  }
  
  public int openChannel(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, boolean paramBoolean, List<NameValuePair> paramList1, List<NameValuePair> paramList2)
  {
    Intent localIntent = createServiceIntent();
    localIntent.setAction(PushConstants.ACTION_OPEN_CHANNEL);
    putOpenParamsIntoIntent(localIntent, paramString1, paramString2, paramString3, paramString4, paramString5, paramBoolean, paramList1, paramList2);
    mContext.startService(localIntent);
    return 0;
  }
  
  public boolean sendMessage(Message paramMessage, boolean paramBoolean)
  {
    if (!Network.hasNetwork(mContext)) {
      return false;
    }
    Intent localIntent = createServiceIntent();
    Object localObject = MessageProfiling.getPrefString();
    if (!TextUtils.isEmpty((CharSequence)localObject))
    {
      CommonPacketExtension localCommonPacketExtension1 = new CommonPacketExtension("pf", null, (String[])null, (String[])null);
      CommonPacketExtension localCommonPacketExtension2 = new CommonPacketExtension("sent", null, (String[])null, (String[])null);
      localCommonPacketExtension2.setText((String)localObject);
      localCommonPacketExtension1.appendChild(localCommonPacketExtension2);
      paramMessage.addExtension(localCommonPacketExtension1);
    }
    localObject = paramMessage.toBundle();
    if (localObject != null)
    {
      MyLog.v("SEND:" + paramMessage.toXML());
      localIntent.setAction(PushConstants.ACTION_SEND_MESSAGE);
      localIntent.putExtra(PushConstants.EXTRA_SESSION, sSession);
      localIntent.putExtra("ext_packet", (Bundle)localObject);
      localIntent.putExtra("ext_encrypt", paramBoolean);
      mContext.startService(localIntent);
      return true;
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.ServiceClient
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */