import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.provider.Settings.Global;
import android.provider.Settings.System;
import android.telephony.CellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.widget.Toast;
import com.amap.api.location.core.c;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.http.params.HttpParams;

public class ahz
{
  static float a(double[] paramArrayOfDouble)
  {
    if (paramArrayOfDouble.length != 4) {
      return 0.0F;
    }
    float[] arrayOfFloat = new float[1];
    Location.distanceBetween(paramArrayOfDouble[0], paramArrayOfDouble[1], paramArrayOfDouble[2], paramArrayOfDouble[3], arrayOfFloat);
    return arrayOfFloat[0];
  }
  
  static int a(int paramInt)
  {
    return paramInt * 2 - 113;
  }
  
  static int a(CellLocation paramCellLocation, Context paramContext)
  {
    if (a(paramContext))
    {
      a(new Object[] { "air plane mode on" });
      return 9;
    }
    if ((paramCellLocation instanceof GsmCellLocation)) {
      return 1;
    }
    try
    {
      Class.forName("android.telephony.cdma.CdmaCellLocation");
      return 2;
    }
    catch (Throwable paramCellLocation)
    {
      paramCellLocation.printStackTrace();
    }
    return 9;
  }
  
  static long a()
  {
    return System.currentTimeMillis();
  }
  
  static void a(Context paramContext, String paramString)
  {
    String str = paramString;
    if (paramString == null) {
      str = "null";
    }
    int i;
    if (c.j().indexOf("test") != -1) {
      i = 1;
    }
    for (;;)
    {
      if ((i != 0) && (paramContext != null))
      {
        Toast.makeText(paramContext, str, 0).show();
        a(new Object[] { str });
      }
      return;
      if (ahk.d.indexOf("test") != -1)
      {
        i = 1;
      }
      else
      {
        paramString = null;
        if (c.j().length() > 0) {
          paramString = c.j().substring(7, 8).toCharArray();
        }
        if ((paramString == null) || (!Character.isLetter(paramString[0]))) {
          i = 1;
        } else {
          i = 0;
        }
      }
    }
  }
  
  public static void a(Throwable paramThrowable) {}
  
  static void a(HttpParams paramHttpParams, int paramInt)
  {
    paramHttpParams.setIntParameter("http.connection.timeout", paramInt);
    paramHttpParams.setIntParameter("http.socket.timeout", paramInt);
    paramHttpParams.setLongParameter("http.conn-manager.timeout", paramInt);
  }
  
  public static void a(Object... paramVarArgs) {}
  
  static boolean a(ahf paramahf)
  {
    if (paramahf == null) {}
    double d1;
    double d2;
    float f;
    do
    {
      do
      {
        return false;
      } while ((paramahf.j().equals("5")) || (paramahf.j().equals("6")));
      d1 = paramahf.e();
      d2 = paramahf.f();
      f = paramahf.g();
    } while ((d1 == 0.0D) && (d2 == 0.0D) && (f == 0.0D));
    return true;
  }
  
  static boolean a(Context paramContext)
  {
    bool2 = true;
    bool1 = true;
    if (paramContext == null) {
      return false;
    }
    paramContext = paramContext.getContentResolver();
    if (c() < 17) {
      try
      {
        int i = Settings.System.getInt(paramContext, "airplane_mode_on", 0);
        if (i == 1) {}
        for (;;)
        {
          return bool1;
          bool1 = false;
        }
        try
        {
          i = Settings.Global.getInt(paramContext, "airplane_mode_on", 0);
          if (i == 1) {}
          for (bool1 = bool2;; bool1 = false) {
            return bool1;
          }
          return false;
        }
        catch (Throwable paramContext)
        {
          paramContext.printStackTrace();
        }
      }
      catch (Throwable paramContext)
      {
        paramContext.printStackTrace();
        return false;
      }
    }
  }
  
  public static boolean a(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    while (!TextUtils.isDigitsOnly(paramString)) {
      return false;
    }
    return ",111,123,134,199,202,204,206,208,212,213,214,216,218,219,220,222,225,226,228,230,231,232,234,235,238,240,242,244,246,247,248,250,255,257,259,260,262,266,268,270,272,274,276,278,280,282,283,284,286,288,289,290,292,293,294,295,297,302,308,310,311,312,313,314,315,316,310,330,332,334,338,340,342,344,346,348,350,352,354,356,358,360,362,363,364,365,366,368,370,372,374,376,400,401,402,404,405,406,410,412,413,414,415,416,417,418,419,420,421,422,424,425,426,427,428,429,430,431,432,434,436,437,438,440,441,450,452,454,455,456,457,466,467,470,472,502,505,510,514,515,520,525,528,530,534,535,536,537,539,540,541,542,543,544,545,546,547,548,549,550,551,552,553,555,560,598,602,603,604,605,606,607,608,609,610,611,612,613,614,615,616,617,618,619,620,621,622,623,624,625,626,627,628,629,630,631,632,633,634,635,636,637,638,639,640,641,642,643,645,646,647,648,649,650,651,652,653,654,655,657,659,665,702,704,706,708,710,712,714,716,722,724,730,732,734,736,738,740,742,744,746,748,750,850,901,".contains("," + paramString + ",");
  }
  
  public static byte[] a(byte[] paramArrayOfByte)
  {
    try
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      GZIPOutputStream localGZIPOutputStream = new GZIPOutputStream(localByteArrayOutputStream);
      localGZIPOutputStream.write(paramArrayOfByte);
      localGZIPOutputStream.close();
      paramArrayOfByte = localByteArrayOutputStream.toByteArray();
      localThrowable1.printStackTrace();
    }
    catch (Throwable localThrowable1)
    {
      try
      {
        localByteArrayOutputStream.close();
        return paramArrayOfByte;
      }
      catch (Throwable localThrowable2)
      {
        for (;;) {}
      }
      localThrowable1 = localThrowable1;
      paramArrayOfByte = null;
    }
    return paramArrayOfByte;
  }
  
  /* Error */
  public static String[] a(android.telephony.TelephonyManager paramTelephonyManager)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_1
    //   2: iconst_0
    //   3: istore_2
    //   4: iconst_2
    //   5: anewarray 58	java/lang/String
    //   8: astore 4
    //   10: aload 4
    //   12: iconst_0
    //   13: ldc -45
    //   15: aastore
    //   16: aload 4
    //   18: iconst_1
    //   19: ldc -45
    //   21: aastore
    //   22: aconst_null
    //   23: astore_3
    //   24: aload_0
    //   25: ifnull +8 -> 33
    //   28: aload_0
    //   29: invokevirtual 216	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   32: astore_3
    //   33: aload_3
    //   34: invokestatic 163	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   37: ifeq +52 -> 89
    //   40: iconst_0
    //   41: istore_1
    //   42: iload_1
    //   43: ifeq +83 -> 126
    //   46: aload 4
    //   48: iconst_0
    //   49: aload_3
    //   50: iconst_0
    //   51: iconst_3
    //   52: invokevirtual 85	java/lang/String:substring	(II)Ljava/lang/String;
    //   55: aastore
    //   56: aload_3
    //   57: iconst_3
    //   58: invokevirtual 219	java/lang/String:substring	(I)Ljava/lang/String;
    //   61: invokevirtual 89	java/lang/String:toCharArray	()[C
    //   64: astore_0
    //   65: iconst_0
    //   66: istore_1
    //   67: iload_1
    //   68: aload_0
    //   69: arraylength
    //   70: if_icmpge +44 -> 114
    //   73: aload_0
    //   74: iload_1
    //   75: caload
    //   76: invokestatic 222	java/lang/Character:isDigit	(C)Z
    //   79: ifeq +35 -> 114
    //   82: iload_1
    //   83: iconst_1
    //   84: iadd
    //   85: istore_1
    //   86: goto -19 -> 67
    //   89: aload_3
    //   90: invokestatic 166	android/text/TextUtils:isDigitsOnly	(Ljava/lang/CharSequence;)Z
    //   93: ifne +8 -> 101
    //   96: iconst_0
    //   97: istore_1
    //   98: goto -56 -> 42
    //   101: aload_3
    //   102: invokevirtual 81	java/lang/String:length	()I
    //   105: iconst_4
    //   106: if_icmpgt -64 -> 42
    //   109: iconst_0
    //   110: istore_1
    //   111: goto -69 -> 42
    //   114: aload 4
    //   116: iconst_1
    //   117: aload_3
    //   118: iconst_3
    //   119: iload_1
    //   120: iconst_3
    //   121: iadd
    //   122: invokevirtual 85	java/lang/String:substring	(II)Ljava/lang/String;
    //   125: aastore
    //   126: aload 4
    //   128: iconst_0
    //   129: aaload
    //   130: invokestatic 227	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   133: istore_1
    //   134: iload_1
    //   135: ifne +9 -> 144
    //   138: aload 4
    //   140: iconst_0
    //   141: ldc -45
    //   143: aastore
    //   144: aload 4
    //   146: areturn
    //   147: astore_0
    //   148: aload 4
    //   150: areturn
    //   151: astore_0
    //   152: iload_2
    //   153: istore_1
    //   154: goto -20 -> 134
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	157	0	paramTelephonyManager	android.telephony.TelephonyManager
    //   1	153	1	i	int
    //   3	150	2	j	int
    //   23	95	3	str	String
    //   8	141	4	arrayOfString	String[]
    // Exception table:
    //   from	to	target	type
    //   28	33	147	java/lang/Exception
    //   33	40	147	java/lang/Exception
    //   46	65	147	java/lang/Exception
    //   67	82	147	java/lang/Exception
    //   89	96	147	java/lang/Exception
    //   101	109	147	java/lang/Exception
    //   114	126	147	java/lang/Exception
    //   126	134	151	java/lang/Exception
  }
  
  static long b()
  {
    return SystemClock.elapsedRealtime();
  }
  
  static NetworkInfo b(Context paramContext)
  {
    paramContext = (ConnectivityManager)b(paramContext, "connectivity");
    if (paramContext != null) {}
    for (;;)
    {
      try
      {
        paramContext = paramContext.getActiveNetworkInfo();
        return paramContext;
      }
      catch (SecurityException paramContext)
      {
        return null;
      }
      paramContext = null;
    }
  }
  
  static Object b(Context paramContext, String paramString)
  {
    if (paramContext == null) {
      return null;
    }
    return paramContext.getApplicationContext().getSystemService(paramString);
  }
  
  static int c()
  {
    try
    {
      i = Build.VERSION.SDK_INT;
      return i;
    }
    catch (Throwable localThrowable1)
    {
      int i;
      localThrowable1.printStackTrace();
      try
      {
        i = Integer.parseInt(Build.VERSION.SDK.toString());
        return i;
      }
      catch (Throwable localThrowable2)
      {
        localThrowable2.printStackTrace();
        a(localThrowable2);
      }
    }
    return 0;
  }
}

/* Location:
 * Qualified Name:     ahz
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */