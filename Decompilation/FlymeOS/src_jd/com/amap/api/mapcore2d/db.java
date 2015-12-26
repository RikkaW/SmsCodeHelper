package com.amap.api.mapcore2d;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

public class db
{
  public static int a = -1;
  public static String b = "";
  private static dh c;
  private static String d = "http://apiinit.amap.com/v3/log/init";
  private static String e = null;
  
  private static String a()
  {
    return d;
  }
  
  private static Map<String, String> a(Context paramContext)
  {
    HashMap localHashMap = new HashMap();
    try
    {
      localHashMap.put("resType", "json");
      localHashMap.put("encode", "UTF-8");
      localHashMap.put("ec", "1");
      String str = dc.a();
      localHashMap.put("ts", str);
      localHashMap.put("scode", dc.a(paramContext, str, di.a("resType=json&encode=UTF-8&ec=1")));
      return localHashMap;
    }
    catch (Throwable paramContext)
    {
      ed.a(paramContext, "Auth", "gParams");
      paramContext.printStackTrace();
    }
    return localHashMap;
  }
  
  public static void a(String paramString)
  {
    da.a(paramString);
  }
  
  public static boolean a(Context paramContext, dh paramdh)
  {
    try
    {
      c = paramdh;
      boolean bool1 = true;
      try
      {
        paramdh = a();
        HashMap localHashMap = new HashMap();
        localHashMap.put("Content-Type", "application/x-www-form-urlencoded");
        localHashMap.put("Accept-Encoding", "gzip");
        localHashMap.put("Connection", "Keep-Alive");
        localHashMap.put("User-Agent", cb);
        localHashMap.put("X-INFO", dc.a(paramContext, c, null));
        localHashMap.put("ia", "1");
        localHashMap.put("key", da.f(paramContext));
        ex localex = ex.a();
        dj localdj = new dj();
        localdj.a(dg.a(paramContext));
        localdj.a(localHashMap);
        localdj.b(a(paramContext));
        localdj.a(paramdh);
        boolean bool2 = a(localex.a(localdj));
        bool1 = bool2;
      }
      catch (Throwable paramContext)
      {
        for (;;)
        {
          ed.a(paramContext, "Auth", "getAuth");
          paramContext.printStackTrace();
        }
      }
      return bool1;
    }
    finally {}
  }
  
  /* Error */
  private static boolean a(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +5 -> 6
    //   4: iconst_1
    //   5: ireturn
    //   6: new 164	org/json/JSONObject
    //   9: dup
    //   10: new 166	java/lang/String
    //   13: dup
    //   14: aload_0
    //   15: invokespecial 169	java/lang/String:<init>	([B)V
    //   18: invokespecial 171	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   21: astore_0
    //   22: aload_0
    //   23: ldc -83
    //   25: invokevirtual 177	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   28: ifeq +19 -> 47
    //   31: aload_0
    //   32: ldc -83
    //   34: invokevirtual 181	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   37: istore_1
    //   38: iload_1
    //   39: iconst_1
    //   40: if_icmpne +49 -> 89
    //   43: iconst_1
    //   44: putstatic 16	com/amap/api/mapcore2d/db:a	I
    //   47: aload_0
    //   48: ldc -73
    //   50: invokevirtual 177	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   53: ifeq +12 -> 65
    //   56: aload_0
    //   57: ldc -73
    //   59: invokevirtual 186	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   62: putstatic 20	com/amap/api/mapcore2d/db:b	Ljava/lang/String;
    //   65: getstatic 16	com/amap/api/mapcore2d/db:a	I
    //   68: ifne +12 -> 80
    //   71: ldc -68
    //   73: getstatic 20	com/amap/api/mapcore2d/db:b	Ljava/lang/String;
    //   76: invokestatic 194	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   79: pop
    //   80: getstatic 16	com/amap/api/mapcore2d/db:a	I
    //   83: iconst_1
    //   84: if_icmpeq -80 -> 4
    //   87: iconst_0
    //   88: ireturn
    //   89: iload_1
    //   90: ifne -43 -> 47
    //   93: iconst_0
    //   94: putstatic 16	com/amap/api/mapcore2d/db:a	I
    //   97: goto -50 -> 47
    //   100: astore_0
    //   101: aload_0
    //   102: ldc 75
    //   104: ldc -60
    //   106: invokestatic 82	com/amap/api/mapcore2d/ed:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   109: aload_0
    //   110: invokevirtual 197	org/json/JSONException:printStackTrace	()V
    //   113: iconst_0
    //   114: ireturn
    //   115: astore_0
    //   116: aload_0
    //   117: ldc 75
    //   119: ldc -60
    //   121: invokestatic 82	com/amap/api/mapcore2d/ed:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   124: aload_0
    //   125: invokevirtual 198	java/lang/Exception:printStackTrace	()V
    //   128: goto -15 -> 113
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	131	0	paramArrayOfByte	byte[]
    //   37	53	1	i	int
    // Exception table:
    //   from	to	target	type
    //   6	38	100	org/json/JSONException
    //   43	47	100	org/json/JSONException
    //   47	65	100	org/json/JSONException
    //   65	80	100	org/json/JSONException
    //   80	87	100	org/json/JSONException
    //   93	97	100	org/json/JSONException
    //   6	38	115	java/lang/Exception
    //   43	47	115	java/lang/Exception
    //   47	65	115	java/lang/Exception
    //   65	80	115	java/lang/Exception
    //   80	87	115	java/lang/Exception
    //   93	97	115	java/lang/Exception
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.db
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */