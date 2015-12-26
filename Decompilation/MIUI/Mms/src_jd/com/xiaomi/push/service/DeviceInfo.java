package com.xiaomi.push.service;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.string.XMStringUtils;

public class DeviceInfo
{
  private static String sCachedDeviceId = null;
  private static String sCachedIMEI = null;
  private static String sCachedSimpleDeviceId = null;
  
  /* Error */
  public static String blockingGetIMEI(Context paramContext)
  {
    // Byte code:
    //   0: getstatic 12	com/xiaomi/push/service/DeviceInfo:sCachedIMEI	Ljava/lang/String;
    //   3: ifnull +9 -> 12
    //   6: getstatic 12	com/xiaomi/push/service/DeviceInfo:sCachedIMEI	Ljava/lang/String;
    //   9: astore_2
    //   10: aload_2
    //   11: areturn
    //   12: aload_0
    //   13: ldc 28
    //   15: invokevirtual 34	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   18: checkcast 36	android/telephony/TelephonyManager
    //   21: astore_2
    //   22: aload_2
    //   23: invokevirtual 40	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   26: astore_0
    //   27: bipush 10
    //   29: istore_1
    //   30: aload_0
    //   31: ifnonnull +48 -> 79
    //   34: iload_1
    //   35: ifle +21 -> 56
    //   38: ldc2_w 41
    //   41: invokestatic 48	java/lang/Thread:sleep	(J)V
    //   44: aload_2
    //   45: invokevirtual 40	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   48: astore_0
    //   49: iload_1
    //   50: iconst_1
    //   51: isub
    //   52: istore_1
    //   53: goto -23 -> 30
    //   56: aload_0
    //   57: astore_2
    //   58: aload_0
    //   59: ifnull -49 -> 10
    //   62: aload_0
    //   63: putstatic 12	com/xiaomi/push/service/DeviceInfo:sCachedIMEI	Ljava/lang/String;
    //   66: aload_0
    //   67: areturn
    //   68: astore_0
    //   69: aload_0
    //   70: invokestatic 54	com/xiaomi/channel/commonutils/logger/MyLog:e	(Ljava/lang/Throwable;)V
    //   73: aconst_null
    //   74: areturn
    //   75: astore_0
    //   76: goto -32 -> 44
    //   79: goto -23 -> 56
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	82	0	paramContext	Context
    //   29	24	1	i	int
    //   9	49	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   12	27	68	java/lang/Throwable
    //   38	44	68	java/lang/Throwable
    //   44	49	68	java/lang/Throwable
    //   62	66	68	java/lang/Throwable
    //   38	44	75	java/lang/InterruptedException
  }
  
  public static String getDeviceId(Context paramContext)
  {
    String str2;
    String str1;
    if (sCachedDeviceId == null)
    {
      str2 = blockingGetIMEI(paramContext);
      str1 = null;
    }
    try
    {
      paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      str1 = null;
      if (Build.VERSION.SDK_INT > 8) {
        str1 = Build.SERIAL;
      }
      sCachedDeviceId = "a-" + XMStringUtils.getSHA1Digest(new StringBuilder().append(str2).append(paramContext).append(str1).toString());
      return sCachedDeviceId;
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        MyLog.e(paramContext);
        paramContext = str1;
      }
    }
  }
  
  public static String getSimOperatorName(Context paramContext)
  {
    return ((TelephonyManager)paramContext.getSystemService("phone")).getSimOperatorName();
  }
  
  /* Error */
  public static String getSimpleDeviceId(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 16	com/xiaomi/push/service/DeviceInfo:sCachedSimpleDeviceId	Ljava/lang/String;
    //   6: ifnull +12 -> 18
    //   9: getstatic 16	com/xiaomi/push/service/DeviceInfo:sCachedSimpleDeviceId	Ljava/lang/String;
    //   12: astore_0
    //   13: ldc 2
    //   15: monitorexit
    //   16: aload_0
    //   17: areturn
    //   18: aconst_null
    //   19: astore_1
    //   20: aload_0
    //   21: invokevirtual 60	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   24: ldc 62
    //   26: invokestatic 68	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   29: astore_0
    //   30: aconst_null
    //   31: astore_1
    //   32: getstatic 74	android/os/Build$VERSION:SDK_INT	I
    //   35: bipush 8
    //   37: if_icmple +7 -> 44
    //   40: getstatic 79	android/os/Build:SERIAL	Ljava/lang/String;
    //   43: astore_1
    //   44: new 81	java/lang/StringBuilder
    //   47: dup
    //   48: invokespecial 82	java/lang/StringBuilder:<init>	()V
    //   51: aload_0
    //   52: invokevirtual 88	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   55: aload_1
    //   56: invokevirtual 88	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: invokevirtual 91	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   62: invokestatic 97	com/xiaomi/channel/commonutils/string/XMStringUtils:getSHA1Digest	(Ljava/lang/String;)Ljava/lang/String;
    //   65: putstatic 16	com/xiaomi/push/service/DeviceInfo:sCachedSimpleDeviceId	Ljava/lang/String;
    //   68: getstatic 16	com/xiaomi/push/service/DeviceInfo:sCachedSimpleDeviceId	Ljava/lang/String;
    //   71: astore_0
    //   72: goto -59 -> 13
    //   75: astore_0
    //   76: aload_0
    //   77: invokestatic 54	com/xiaomi/channel/commonutils/logger/MyLog:e	(Ljava/lang/Throwable;)V
    //   80: aload_1
    //   81: astore_0
    //   82: goto -52 -> 30
    //   85: astore_0
    //   86: ldc 2
    //   88: monitorexit
    //   89: aload_0
    //   90: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	91	0	paramContext	Context
    //   19	62	1	str	String
    // Exception table:
    //   from	to	target	type
    //   20	30	75	java/lang/Throwable
    //   3	13	85	finally
    //   20	30	85	finally
    //   32	44	85	finally
    //   44	72	85	finally
    //   76	80	85	finally
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.DeviceInfo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */