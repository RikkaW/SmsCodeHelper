package com.amap.api.services.core;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

abstract class bg
{
  private bk a;
  
  protected bg(Context paramContext)
  {
    try
    {
      a = a(paramContext, a());
      return;
    }
    catch (Throwable paramContext)
    {
      ay.a(paramContext, "LogProcessor", "LogUpDateProcessor");
      paramContext.printStackTrace();
    }
  }
  
  public static bg a(Context paramContext, int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return null;
    case 0: 
      return new bb(paramContext);
    case 1: 
      return new bd(paramContext);
    }
    return new az(paramContext);
  }
  
  private bk a(Context paramContext, String paramString)
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramContext.getFilesDir().getAbsolutePath());
      localStringBuilder.append(bf.a);
      localStringBuilder.append(paramString);
      paramContext = new File(localStringBuilder.toString());
      if ((!paramContext.exists()) && (!paramContext.mkdirs())) {
        return null;
      }
      paramContext = bk.a(paramContext, 1, 1, 20480L);
      return paramContext;
    }
    catch (IOException paramContext)
    {
      ay.a(paramContext, "LogProcessor", "initDiskLru");
      paramContext.printStackTrace();
      return null;
    }
    catch (Throwable paramContext)
    {
      ay.a(paramContext, "LogProcessor", "initDiskLru");
      paramContext.printStackTrace();
    }
    return null;
  }
  
  private String a(List<am> paramList, Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("{\"pinfo\":\"").append(c(paramContext)).append("\",\"els\":[");
    paramList = paramList.iterator();
    int i = 1;
    while (paramList.hasNext())
    {
      paramContext = (am)paramList.next();
      String str = c(paramContext.b());
      int j = i;
      if (str != null)
      {
        if ("".equals(str)) {
          continue;
        }
        paramContext = str + "||" + paramContext.d();
        if (i == 0) {
          break label149;
        }
        i = 0;
      }
      for (;;)
      {
        localStringBuilder.append("{\"log\":\"").append(paramContext).append("\"}");
        j = i;
        i = j;
        break;
        label149:
        localStringBuilder.append(",");
      }
    }
    if (i != 0) {
      return null;
    }
    localStringBuilder.append("]}");
    return localStringBuilder.toString();
  }
  
  private void a(ak paramak, int paramInt)
  {
    try
    {
      a(paramak.a(2, paramInt), paramak, paramInt);
      return;
    }
    catch (Throwable paramak)
    {
      ay.a(paramak, "LogProcessor", "processDeleteFail");
      paramak.printStackTrace();
    }
  }
  
  private void a(List<am> paramList, ak paramak, int paramInt)
  {
    if ((paramList != null) && (paramList.size() > 0))
    {
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        am localam = (am)paramList.next();
        if (a(localam.b()))
        {
          paramak.a(localam.b(), paramInt);
        }
        else
        {
          localam.a(2);
          paramak.a(localam, localam.a());
        }
      }
    }
  }
  
  private boolean a(String paramString)
  {
    if (a == null) {
      return false;
    }
    try
    {
      boolean bool = a.c(paramString);
      return bool;
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }
  
  private int b(String paramString)
  {
    Log.i("yiyi.qi", paramString);
    paramString = new bh(ae.b(paramString.getBytes()));
    try
    {
      paramString = bs.a(false).a(paramString);
      if (paramString == null) {
        return 0;
      }
      paramString = new String(paramString);
      try
      {
        paramString = new JSONObject(paramString);
        if (paramString.has("code"))
        {
          int i = paramString.getInt("code");
          return i;
        }
      }
      catch (JSONException paramString)
      {
        ay.a(paramString, "LogProcessor", "processUpdate");
        paramString.printStackTrace();
        return 0;
      }
      return 0;
    }
    catch (v paramString)
    {
      ay.a(paramString, "LogProcessor", "processUpdate");
      paramString.printStackTrace();
    }
  }
  
  private String c(Context paramContext)
  {
    try
    {
      String str = y.a(paramContext);
      if ("".equals(str)) {
        return null;
      }
      paramContext = y.b(paramContext, str.getBytes("UTF-8"));
      return paramContext;
    }
    catch (UnsupportedEncodingException paramContext)
    {
      ay.a(paramContext, "LogProcessor", "getPublicInfo");
      paramContext.printStackTrace();
      return null;
    }
    catch (Throwable paramContext)
    {
      ay.a(paramContext, "LogProcessor", "getPublicInfo");
      paramContext.printStackTrace();
    }
    return null;
  }
  
  /* Error */
  private String c(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 7
    //   3: aconst_null
    //   4: astore 8
    //   6: aload_0
    //   7: getfield 21	com/amap/api/services/core/bg:a	Lcom/amap/api/services/core/bk;
    //   10: astore_3
    //   11: aload_3
    //   12: ifnonnull +34 -> 46
    //   15: iconst_0
    //   16: ifeq +11 -> 27
    //   19: new 253	java/lang/NullPointerException
    //   22: dup
    //   23: invokespecial 254	java/lang/NullPointerException:<init>	()V
    //   26: athrow
    //   27: aload 8
    //   29: astore 4
    //   31: iconst_0
    //   32: ifeq +11 -> 43
    //   35: new 253	java/lang/NullPointerException
    //   38: dup
    //   39: invokespecial 254	java/lang/NullPointerException:<init>	()V
    //   42: athrow
    //   43: aload 4
    //   45: areturn
    //   46: aload_0
    //   47: getfield 21	com/amap/api/services/core/bg:a	Lcom/amap/api/services/core/bk;
    //   50: aload_1
    //   51: invokevirtual 257	com/amap/api/services/core/bk:a	(Ljava/lang/String;)Lcom/amap/api/services/core/bk$b;
    //   54: astore_1
    //   55: aload_1
    //   56: ifnonnull +50 -> 106
    //   59: iconst_0
    //   60: ifeq +11 -> 71
    //   63: new 253	java/lang/NullPointerException
    //   66: dup
    //   67: invokespecial 254	java/lang/NullPointerException:<init>	()V
    //   70: athrow
    //   71: aload 8
    //   73: astore 4
    //   75: iconst_0
    //   76: ifeq -33 -> 43
    //   79: new 253	java/lang/NullPointerException
    //   82: dup
    //   83: invokespecial 254	java/lang/NullPointerException:<init>	()V
    //   86: athrow
    //   87: astore_1
    //   88: aload_1
    //   89: ldc 23
    //   91: ldc_w 259
    //   94: invokestatic 30	com/amap/api/services/core/ay:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   97: aload 7
    //   99: astore_3
    //   100: aload_1
    //   101: invokevirtual 93	java/io/IOException:printStackTrace	()V
    //   104: aload_3
    //   105: areturn
    //   106: aload_1
    //   107: iconst_0
    //   108: invokevirtual 264	com/amap/api/services/core/bk$b:a	(I)Ljava/io/InputStream;
    //   111: astore_1
    //   112: new 266	java/io/ByteArrayOutputStream
    //   115: dup
    //   116: invokespecial 267	java/io/ByteArrayOutputStream:<init>	()V
    //   119: astore 5
    //   121: aload 5
    //   123: astore 4
    //   125: aload_1
    //   126: astore_3
    //   127: sipush 1024
    //   130: newarray <illegal type>
    //   132: astore 6
    //   134: aload 5
    //   136: astore 4
    //   138: aload_1
    //   139: astore_3
    //   140: aload_1
    //   141: aload 6
    //   143: invokevirtual 273	java/io/InputStream:read	([B)I
    //   146: istore_2
    //   147: iload_2
    //   148: iconst_m1
    //   149: if_icmpeq +90 -> 239
    //   152: aload 5
    //   154: astore 4
    //   156: aload_1
    //   157: astore_3
    //   158: aload 5
    //   160: aload 6
    //   162: iconst_0
    //   163: iload_2
    //   164: invokevirtual 277	java/io/ByteArrayOutputStream:write	([BII)V
    //   167: goto -33 -> 134
    //   170: astore 6
    //   172: aload 5
    //   174: astore 4
    //   176: aload_1
    //   177: astore_3
    //   178: aload 6
    //   180: ldc 23
    //   182: ldc_w 279
    //   185: invokestatic 30	com/amap/api/services/core/ay:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   188: aload 5
    //   190: astore 4
    //   192: aload_1
    //   193: astore_3
    //   194: aload 6
    //   196: invokevirtual 93	java/io/IOException:printStackTrace	()V
    //   199: aload 5
    //   201: ifnull +8 -> 209
    //   204: aload 5
    //   206: invokevirtual 282	java/io/ByteArrayOutputStream:close	()V
    //   209: aload 8
    //   211: astore 4
    //   213: aload_1
    //   214: ifnull -171 -> 43
    //   217: aload_1
    //   218: invokevirtual 283	java/io/InputStream:close	()V
    //   221: aconst_null
    //   222: areturn
    //   223: astore_1
    //   224: aload_1
    //   225: ldc 23
    //   227: ldc_w 259
    //   230: invokestatic 30	com/amap/api/services/core/ay:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   233: aload 7
    //   235: astore_3
    //   236: goto -136 -> 100
    //   239: aload 5
    //   241: astore 4
    //   243: aload_1
    //   244: astore_3
    //   245: aload 5
    //   247: ldc_w 285
    //   250: invokevirtual 287	java/io/ByteArrayOutputStream:toString	(Ljava/lang/String;)Ljava/lang/String;
    //   253: astore 6
    //   255: aload 6
    //   257: astore_3
    //   258: aload 5
    //   260: ifnull +8 -> 268
    //   263: aload 5
    //   265: invokevirtual 282	java/io/ByteArrayOutputStream:close	()V
    //   268: aload_3
    //   269: astore 4
    //   271: aload_1
    //   272: ifnull -229 -> 43
    //   275: aload_1
    //   276: invokevirtual 283	java/io/InputStream:close	()V
    //   279: aload_3
    //   280: areturn
    //   281: astore_1
    //   282: aload_1
    //   283: ldc 23
    //   285: ldc_w 259
    //   288: invokestatic 30	com/amap/api/services/core/ay:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   291: goto -191 -> 100
    //   294: astore 6
    //   296: aconst_null
    //   297: astore 5
    //   299: aconst_null
    //   300: astore_1
    //   301: aload 5
    //   303: astore 4
    //   305: aload_1
    //   306: astore_3
    //   307: aload 6
    //   309: ldc 23
    //   311: ldc_w 279
    //   314: invokestatic 30	com/amap/api/services/core/ay:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   317: aload 5
    //   319: astore 4
    //   321: aload_1
    //   322: astore_3
    //   323: aload 6
    //   325: invokevirtual 33	java/lang/Throwable:printStackTrace	()V
    //   328: aload 5
    //   330: ifnull +8 -> 338
    //   333: aload 5
    //   335: invokevirtual 282	java/io/ByteArrayOutputStream:close	()V
    //   338: aload 8
    //   340: astore 4
    //   342: aload_1
    //   343: ifnull -300 -> 43
    //   346: aload_1
    //   347: invokevirtual 283	java/io/InputStream:close	()V
    //   350: aconst_null
    //   351: areturn
    //   352: astore_1
    //   353: aload_1
    //   354: ldc 23
    //   356: ldc_w 259
    //   359: invokestatic 30	com/amap/api/services/core/ay:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   362: aload 7
    //   364: astore_3
    //   365: goto -265 -> 100
    //   368: astore 5
    //   370: aconst_null
    //   371: astore 4
    //   373: aconst_null
    //   374: astore_1
    //   375: aload 4
    //   377: ifnull +8 -> 385
    //   380: aload 4
    //   382: invokevirtual 282	java/io/ByteArrayOutputStream:close	()V
    //   385: aload_1
    //   386: ifnull +7 -> 393
    //   389: aload_1
    //   390: invokevirtual 283	java/io/InputStream:close	()V
    //   393: aload 5
    //   395: athrow
    //   396: astore_3
    //   397: aload_3
    //   398: ldc 23
    //   400: ldc_w 289
    //   403: invokestatic 30	com/amap/api/services/core/ay:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   406: aload_3
    //   407: invokevirtual 93	java/io/IOException:printStackTrace	()V
    //   410: goto -25 -> 385
    //   413: astore_1
    //   414: aload_1
    //   415: ldc 23
    //   417: ldc_w 259
    //   420: invokestatic 30	com/amap/api/services/core/ay:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   423: aload_1
    //   424: invokevirtual 93	java/io/IOException:printStackTrace	()V
    //   427: goto -34 -> 393
    //   430: astore_3
    //   431: aload_3
    //   432: ldc 23
    //   434: ldc_w 289
    //   437: invokestatic 30	com/amap/api/services/core/ay:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   440: aload_3
    //   441: invokevirtual 93	java/io/IOException:printStackTrace	()V
    //   444: goto -235 -> 209
    //   447: astore_3
    //   448: aload_3
    //   449: ldc 23
    //   451: ldc_w 289
    //   454: invokestatic 30	com/amap/api/services/core/ay:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   457: aload_3
    //   458: invokevirtual 93	java/io/IOException:printStackTrace	()V
    //   461: goto -123 -> 338
    //   464: astore_1
    //   465: aload_1
    //   466: ldc 23
    //   468: ldc_w 289
    //   471: invokestatic 30	com/amap/api/services/core/ay:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   474: aload_1
    //   475: invokevirtual 93	java/io/IOException:printStackTrace	()V
    //   478: goto -451 -> 27
    //   481: astore_1
    //   482: aload_1
    //   483: ldc 23
    //   485: ldc_w 259
    //   488: invokestatic 30	com/amap/api/services/core/ay:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   491: aload 7
    //   493: astore_3
    //   494: goto -394 -> 100
    //   497: astore_1
    //   498: aload_1
    //   499: ldc 23
    //   501: ldc_w 289
    //   504: invokestatic 30	com/amap/api/services/core/ay:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   507: aload_1
    //   508: invokevirtual 93	java/io/IOException:printStackTrace	()V
    //   511: goto -440 -> 71
    //   514: astore 4
    //   516: aload 4
    //   518: ldc 23
    //   520: ldc_w 289
    //   523: invokestatic 30	com/amap/api/services/core/ay:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   526: aload 4
    //   528: invokevirtual 93	java/io/IOException:printStackTrace	()V
    //   531: goto -263 -> 268
    //   534: astore 5
    //   536: aconst_null
    //   537: astore 4
    //   539: goto -164 -> 375
    //   542: astore 5
    //   544: aload_3
    //   545: astore_1
    //   546: goto -171 -> 375
    //   549: astore 6
    //   551: aconst_null
    //   552: astore 5
    //   554: goto -253 -> 301
    //   557: astore 6
    //   559: goto -258 -> 301
    //   562: astore 6
    //   564: aconst_null
    //   565: astore 5
    //   567: aconst_null
    //   568: astore_1
    //   569: goto -397 -> 172
    //   572: astore 6
    //   574: aconst_null
    //   575: astore 5
    //   577: goto -405 -> 172
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	580	0	this	bg
    //   0	580	1	paramString	String
    //   146	18	2	i	int
    //   10	355	3	localObject1	Object
    //   396	11	3	localIOException1	IOException
    //   430	11	3	localIOException2	IOException
    //   447	11	3	localIOException3	IOException
    //   493	52	3	localObject2	Object
    //   29	352	4	localObject3	Object
    //   514	13	4	localIOException4	IOException
    //   537	1	4	localObject4	Object
    //   119	215	5	localByteArrayOutputStream	java.io.ByteArrayOutputStream
    //   368	26	5	localObject5	Object
    //   534	1	5	localObject6	Object
    //   542	1	5	localObject7	Object
    //   552	24	5	localObject8	Object
    //   132	29	6	arrayOfByte	byte[]
    //   170	25	6	localIOException5	IOException
    //   253	3	6	str	String
    //   294	30	6	localThrowable1	Throwable
    //   549	1	6	localThrowable2	Throwable
    //   557	1	6	localThrowable3	Throwable
    //   562	1	6	localIOException6	IOException
    //   572	1	6	localIOException7	IOException
    //   1	491	7	localObject9	Object
    //   4	335	8	localObject10	Object
    // Exception table:
    //   from	to	target	type
    //   79	87	87	java/io/IOException
    //   127	134	170	java/io/IOException
    //   140	147	170	java/io/IOException
    //   158	167	170	java/io/IOException
    //   245	255	170	java/io/IOException
    //   217	221	223	java/io/IOException
    //   275	279	281	java/io/IOException
    //   6	11	294	java/lang/Throwable
    //   46	55	294	java/lang/Throwable
    //   106	112	294	java/lang/Throwable
    //   346	350	352	java/io/IOException
    //   6	11	368	finally
    //   46	55	368	finally
    //   106	112	368	finally
    //   380	385	396	java/io/IOException
    //   389	393	413	java/io/IOException
    //   204	209	430	java/io/IOException
    //   333	338	447	java/io/IOException
    //   19	27	464	java/io/IOException
    //   35	43	481	java/io/IOException
    //   63	71	497	java/io/IOException
    //   263	268	514	java/io/IOException
    //   112	121	534	finally
    //   127	134	542	finally
    //   140	147	542	finally
    //   158	167	542	finally
    //   178	188	542	finally
    //   194	199	542	finally
    //   245	255	542	finally
    //   307	317	542	finally
    //   323	328	542	finally
    //   112	121	549	java/lang/Throwable
    //   127	134	557	java/lang/Throwable
    //   140	147	557	java/lang/Throwable
    //   158	167	557	java/lang/Throwable
    //   245	255	557	java/lang/Throwable
    //   6	11	562	java/io/IOException
    //   46	55	562	java/io/IOException
    //   106	112	562	java/io/IOException
    //   112	121	572	java/io/IOException
  }
  
  protected abstract String a();
  
  protected abstract boolean a(Context paramContext);
  
  protected abstract int b();
  
  void b(Context paramContext)
  {
    ak localak;
    List localList;
    try
    {
      if (!a(paramContext)) {
        return;
      }
      synchronized (Looper.getMainLooper())
      {
        localak = new ak(paramContext);
        a(localak, b());
        localList = localak.a(0, b());
        if ((localList == null) || (localList.size() == 0)) {
          return;
        }
      }
      paramContext = a(localList, paramContext);
    }
    catch (Throwable paramContext)
    {
      ay.a(paramContext, "LogProcessor", "processUpdateLog");
      paramContext.printStackTrace();
      return;
    }
    if (paramContext == null) {
      return;
    }
    if (b(paramContext) == 1) {
      a(localList, localak, b());
    }
  }
  
  void c()
  {
    if ((a != null) && (!a.a())) {}
    try
    {
      a.close();
      return;
    }
    catch (IOException localIOException)
    {
      ay.a(localIOException, "LogProcessor", "closeDiskLru");
      localIOException.printStackTrace();
      return;
    }
    catch (Throwable localThrowable)
    {
      ay.a(localThrowable, "LogProcessor", "closeDiskLru");
      localThrowable.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.bg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */