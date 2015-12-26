package com.xiaomi.mms.mx.fw;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.xiaomi.mms.mx.fw.faccount.XiaoMiJIDUtils;
import com.xiaomi.mms.mx.fw.xmppmsg.AckMessage;
import com.xiaomi.mms.mx.utils.Log;
import com.xiaomi.push.service.ServiceClient;
import java.util.ArrayList;
import java.util.List;
import miui.os.Build;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class HmsChannelService
  extends IntentService
{
  private Context mContext;
  
  public HmsChannelService()
  {
    super("HmsChannelService");
  }
  
  private List<NameValuePair> bindCloudExtras()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new BasicNameValuePair("v", "2"));
    localArrayList.add(new BasicNameValuePair("f", "1"));
    return localArrayList;
  }
  
  private void closeChannel()
  {
    ServiceClient.getInstance(getApplicationContext()).closeChannel("8");
  }
  
  public static boolean isDeviceLoginOut(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("extra_flag_current_device_state", true);
  }
  
  public static boolean isHmsAvailable()
  {
    return (!Build.IS_INTERNATIONAL_BUILD) && (!Build.IS_MIONE);
  }
  
  private void openChannel()
  {
    if (!isHmsAvailable())
    {
      Log.v("HmsChannelService", "hms not available");
      return;
    }
    if (!XiaoMiJIDUtils.hasAccount(mContext))
    {
      Log.v("HmsChannelService", "trying start channel while no account, return");
      return;
    }
    String str1 = XiaoMiJIDUtils.getUserIDWithResId(mContext);
    String str2 = XiaoMiJIDUtils.tryGetServiceToken(mContext, "mipub");
    String str3 = XiaoMiJIDUtils.tryGetPSecurity(mContext, "mipub");
    ServiceClient.getInstance(mContext).openChannel(str1, "8", str2, "XIAOMI-PASS", str3, true, null, bindCloudExtras());
  }
  
  private void openChannel(String paramString1, String paramString2)
  {
    if (!isHmsAvailable())
    {
      Log.v("HmsChannelService", "hms not available");
      return;
    }
    if (!XiaoMiJIDUtils.hasAccount(mContext))
    {
      Log.v("HmsChannelService", "trying start channel while no account, return");
      return;
    }
    String str = XiaoMiJIDUtils.getUserIDWithResId(mContext);
    ServiceClient.getInstance(mContext).openChannel(str, "8", paramString1, "XIAOMI-PASS", paramString2, true, null, bindCloudExtras());
  }
  
  public static void sendAckMessage(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, boolean paramBoolean)
  {
    new AckMessage(paramString1, String.valueOf(HmsPersister.generateNewId(paramContext)), paramString2, paramString3, paramString4, paramBoolean).sendMessage(false);
  }
  
  public static void tryCloseChannel(Context paramContext)
  {
    Intent localIntent = new Intent("com.xiaomi.mms.action_close_channel");
    localIntent.setPackage(paramContext.getPackageName());
    paramContext.startService(localIntent);
  }
  
  public static void tryOpenChannel(Context paramContext)
  {
    Intent localIntent = new Intent("com.xiaomi.mms.action_open_channel");
    localIntent.setPackage(paramContext.getPackageName());
    paramContext.startService(localIntent);
  }
  
  public void onCreate()
  {
    super.onCreate();
    mContext = getBaseContext();
  }
  
  protected void onHandleIntent(Intent paramIntent)
  {
    if (paramIntent == null) {}
    do
    {
      return;
      if (paramIntent.getAction().equalsIgnoreCase("com.xiaomi.mms.action_close_channel"))
      {
        closeChannel();
        return;
      }
    } while (!paramIntent.getAction().equalsIgnoreCase("com.xiaomi.mms.action_open_channel"));
    String str = paramIntent.getStringExtra("auth_token");
    paramIntent = paramIntent.getStringExtra("auth_security");
    if ((TextUtils.isEmpty(str)) || (TextUtils.isEmpty(paramIntent)))
    {
      openChannel();
      return;
    }
    openChannel(str, paramIntent);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.fw.HmsChannelService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */