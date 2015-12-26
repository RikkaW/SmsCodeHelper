package cn.com.xy.sms.util;

import java.lang.reflect.Method;
import java.util.UUID;

public final class u
{
  private static String a = null;
  private static char[] b = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
  private static short c = 20;
  
  private static String a()
  {
    if (a != null) {
      return a;
    }
    String str = a("ro.aliyun.clouduuid", null);
    a = str;
    if ((str == null) || (a.trim().length() == 0)) {
      a = a("ro.sys.aliyun.clouduuid", null);
    }
    return a;
  }
  
  private static String a(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = (String)Class.forName("android.os.SystemProperties").getMethod("get", new Class[] { String.class, String.class }).invoke(null, new Object[] { paramString1, null });
      return paramString1;
    }
    catch (Throwable paramString1) {}
    return null;
  }
  
  private static String b()
  {
    char[] arrayOfChar = new char[c];
    long l1 = System.currentTimeMillis() - 936748800000L >> 1;
    int i = 7;
    if (i <= 0)
    {
      arrayOfChar[0] = b[((int)(l1 % 26L) + 10)];
      UUID localUUID = UUID.randomUUID();
      l1 = localUUID.getLeastSignificantBits();
      long l2 = localUUID.getMostSignificantBits() ^ l1;
      l1 = l2;
      if (l2 < 0L) {
        l1 = -l2;
      }
      i = 8;
    }
    for (;;)
    {
      if (i >= c)
      {
        return new String(arrayOfChar);
        arrayOfChar[i] = b[((int)(l1 % 36L))];
        l1 /= 36L;
        i -= 1;
        break;
      }
      arrayOfChar[i] = b[((int)(l1 % 36L))];
      l1 /= 36L;
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.util.u
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */