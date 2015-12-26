package com.xiaomi.smack.util;

import android.content.Context;
import android.util.Base64;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.service.DeviceInfo;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SystemUtils
{
  private static Context sContext;
  private static String sUUID;
  
  public static Context getContext()
  {
    return sContext;
  }
  
  public static String getDeviceUUID()
  {
    String str;
    if (sUUID == null)
    {
      str = DeviceInfo.blockingGetIMEI(sContext);
      if (str == null) {}
    }
    try
    {
      sUUID = Base64.encodeToString(MessageDigest.getInstance("SHA1").digest(str.getBytes()), 8).substring(0, 16);
      return sUUID;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      for (;;)
      {
        MyLog.e(localNoSuchAlgorithmException);
      }
    }
  }
  
  public static void initialize(Context paramContext)
  {
    sContext = paramContext;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smack.util.SystemUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */