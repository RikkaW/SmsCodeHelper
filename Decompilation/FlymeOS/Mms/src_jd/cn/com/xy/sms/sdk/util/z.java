package cn.com.xy.sms.sdk.util;

import android.content.ContentValues;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.entity.l;
import cn.com.xy.sms.sdk.db.entity.m;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.net.NetUtil;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.zip.ZipException;

public final class z
  extends Thread
{
  private static boolean b;
  private static HashSet<String> d = new HashSet();
  private static boolean e = false;
  private int a = 0;
  private l c;
  
  private static l a()
  {
    for (;;)
    {
      try
      {
        Iterator localIterator = d.iterator();
        l locall = null;
        ArrayList localArrayList = new ArrayList();
        Object localObject2 = locall;
        if (localIterator != null)
        {
          if (!localIterator.hasNext()) {
            localObject2 = locall;
          }
        }
        else
        {
          d.removeAll(localArrayList);
          localArrayList.clear();
          return (l)localObject2;
        }
        localObject2 = (String)localIterator.next();
        locall = m.c((String)localObject2);
        localArrayList.add(localObject2);
        if (locall != null) {
          localObject2 = localObject1;
        }
      }
      finally {}
    }
  }
  
  /* Error */
  private void a(l paraml)
  {
    // Byte code:
    //   0: iconst_2
    //   1: invokestatic 80	cn/com/xy/sms/sdk/net/NetUtil:checkAccessNetWork	(I)Z
    //   4: ifne +4 -> 8
    //   7: return
    //   8: aload_1
    //   9: ifnull +493 -> 502
    //   12: aload_0
    //   13: aload_1
    //   14: putfield 82	cn/com/xy/sms/sdk/util/z:c	Lcn/com/xy/sms/sdk/db/entity/l;
    //   17: new 84	java/io/File
    //   20: dup
    //   21: new 86	java/lang/StringBuilder
    //   24: dup
    //   25: new 86	java/lang/StringBuilder
    //   28: dup
    //   29: invokestatic 92	cn/com/xy/sms/sdk/constant/Constant:getFilePath	()Ljava/lang/String;
    //   32: invokestatic 96	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   35: invokespecial 99	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   38: ldc 101
    //   40: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   43: invokevirtual 108	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   46: invokestatic 96	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   49: invokespecial 99	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   52: getstatic 112	java/io/File:separator	Ljava/lang/String;
    //   55: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   58: invokevirtual 108	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   61: invokespecial 113	java/io/File:<init>	(Ljava/lang/String;)V
    //   64: astore 7
    //   66: aload 7
    //   68: invokevirtual 116	java/io/File:exists	()Z
    //   71: ifne +9 -> 80
    //   74: aload 7
    //   76: invokevirtual 119	java/io/File:mkdirs	()Z
    //   79: pop
    //   80: new 86	java/lang/StringBuilder
    //   83: dup
    //   84: ldc 121
    //   86: invokespecial 99	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   89: aload 7
    //   91: invokevirtual 124	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   94: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   97: pop
    //   98: aload_1
    //   99: getfield 128	cn/com/xy/sms/sdk/db/entity/l:c	Ljava/lang/String;
    //   102: astore 8
    //   104: new 84	java/io/File
    //   107: dup
    //   108: aload 7
    //   110: aload 8
    //   112: aload 8
    //   114: bipush 47
    //   116: invokevirtual 132	java/lang/String:lastIndexOf	(I)I
    //   119: iconst_1
    //   120: iadd
    //   121: invokevirtual 136	java/lang/String:substring	(I)Ljava/lang/String;
    //   124: invokespecial 139	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   127: astore 7
    //   129: aload 7
    //   131: invokevirtual 116	java/io/File:exists	()Z
    //   134: ifeq +9 -> 143
    //   137: aload 7
    //   139: invokevirtual 142	java/io/File:delete	()Z
    //   142: pop
    //   143: getstatic 147	cn/com/xy/sms/sdk/log/LogManager:debug	Z
    //   146: ifeq +361 -> 507
    //   149: new 86	java/lang/StringBuilder
    //   152: dup
    //   153: ldc -107
    //   155: invokespecial 99	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   158: new 151	java/text/SimpleDateFormat
    //   161: dup
    //   162: ldc -103
    //   164: invokespecial 154	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
    //   167: invokestatic 160	java/lang/System:currentTimeMillis	()J
    //   170: invokestatic 165	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   173: invokevirtual 168	java/text/SimpleDateFormat:format	(Ljava/lang/Object;)Ljava/lang/String;
    //   176: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   179: ldc -86
    //   181: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   184: getstatic 173	cn/com/xy/sms/sdk/net/NetUtil:prex	Ljava/lang/String;
    //   187: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   190: aload_1
    //   191: getfield 128	cn/com/xy/sms/sdk/db/entity/l:c	Ljava/lang/String;
    //   194: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   197: ldc -81
    //   199: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   202: aload_0
    //   203: getfield 82	cn/com/xy/sms/sdk/util/z:c	Lcn/com/xy/sms/sdk/db/entity/l;
    //   206: getfield 177	cn/com/xy/sms/sdk/db/entity/l:b	Ljava/lang/String;
    //   209: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   212: ldc -77
    //   214: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   217: aload_0
    //   218: getfield 82	cn/com/xy/sms/sdk/util/z:c	Lcn/com/xy/sms/sdk/db/entity/l;
    //   221: getfield 182	cn/com/xy/sms/sdk/db/entity/l:a	J
    //   224: invokevirtual 185	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   227: pop
    //   228: goto +279 -> 507
    //   231: aload_0
    //   232: getfield 29	cn/com/xy/sms/sdk/util/z:a	I
    //   235: istore_2
    //   236: iload_2
    //   237: iconst_2
    //   238: if_icmpge +7 -> 245
    //   241: iload_3
    //   242: ifeq +57 -> 299
    //   245: iload_3
    //   246: ifeq +39 -> 285
    //   249: aload 7
    //   251: invokestatic 188	cn/com/xy/sms/sdk/constant/Constant:getDRAWBLE_PATH	()Ljava/lang/String;
    //   254: invokestatic 193	cn/com/xy/sms/sdk/util/XyUtil:upZipFile	(Ljava/io/File;Ljava/lang/String;)V
    //   257: aload_0
    //   258: getfield 82	cn/com/xy/sms/sdk/util/z:c	Lcn/com/xy/sms/sdk/db/entity/l;
    //   261: getfield 182	cn/com/xy/sms/sdk/db/entity/l:a	J
    //   264: lstore 5
    //   266: aload_0
    //   267: getfield 82	cn/com/xy/sms/sdk/util/z:c	Lcn/com/xy/sms/sdk/db/entity/l;
    //   270: getfield 128	cn/com/xy/sms/sdk/db/entity/l:c	Ljava/lang/String;
    //   273: astore_1
    //   274: lload 5
    //   276: iconst_1
    //   277: invokestatic 196	cn/com/xy/sms/sdk/db/entity/m:a	(JI)V
    //   280: aload 7
    //   282: invokestatic 201	cn/com/xy/sms/sdk/util/d:a	(Ljava/io/File;)V
    //   285: ldc2_w 202
    //   288: invokestatic 207	java/lang/Thread:sleep	(J)V
    //   291: aload_0
    //   292: invokestatic 209	cn/com/xy/sms/sdk/util/z:b	()Lcn/com/xy/sms/sdk/db/entity/l;
    //   295: invokespecial 211	cn/com/xy/sms/sdk/util/z:a	(Lcn/com/xy/sms/sdk/db/entity/l;)V
    //   298: return
    //   299: new 86	java/lang/StringBuilder
    //   302: dup
    //   303: getstatic 173	cn/com/xy/sms/sdk/net/NetUtil:prex	Ljava/lang/String;
    //   306: invokestatic 96	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   309: invokespecial 99	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   312: aload_1
    //   313: getfield 128	cn/com/xy/sms/sdk/db/entity/l:c	Ljava/lang/String;
    //   316: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   319: invokevirtual 108	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   322: astore 8
    //   324: new 86	java/lang/StringBuilder
    //   327: dup
    //   328: ldc -43
    //   330: invokespecial 99	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   333: aload 8
    //   335: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   338: ldc -41
    //   340: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   343: aload 7
    //   345: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   348: pop
    //   349: aload_0
    //   350: aload 8
    //   352: aload 7
    //   354: invokespecial 221	cn/com/xy/sms/sdk/util/z:a	(Ljava/lang/String;Ljava/io/File;)Z
    //   357: istore 4
    //   359: iload 4
    //   361: istore_3
    //   362: aload_0
    //   363: getfield 29	cn/com/xy/sms/sdk/util/z:a	I
    //   366: sipush 4000
    //   369: imul
    //   370: i2l
    //   371: invokestatic 207	java/lang/Thread:sleep	(J)V
    //   374: goto -143 -> 231
    //   377: astore 8
    //   379: aload_0
    //   380: aload_0
    //   381: getfield 29	cn/com/xy/sms/sdk/util/z:a	I
    //   384: iconst_1
    //   385: iadd
    //   386: putfield 29	cn/com/xy/sms/sdk/util/z:a	I
    //   389: goto -27 -> 362
    //   392: astore 8
    //   394: aload 8
    //   396: invokevirtual 224	java/lang/InterruptedException:printStackTrace	()V
    //   399: goto -168 -> 231
    //   402: astore_1
    //   403: aload_1
    //   404: invokevirtual 225	java/util/zip/ZipException:printStackTrace	()V
    //   407: aload 7
    //   409: invokestatic 201	cn/com/xy/sms/sdk/util/d:a	(Ljava/io/File;)V
    //   412: goto -127 -> 285
    //   415: astore_1
    //   416: aload_1
    //   417: invokevirtual 226	java/lang/Throwable:printStackTrace	()V
    //   420: ldc2_w 202
    //   423: invokestatic 207	java/lang/Thread:sleep	(J)V
    //   426: aload_0
    //   427: invokestatic 209	cn/com/xy/sms/sdk/util/z:b	()Lcn/com/xy/sms/sdk/db/entity/l;
    //   430: invokespecial 211	cn/com/xy/sms/sdk/util/z:a	(Lcn/com/xy/sms/sdk/db/entity/l;)V
    //   433: return
    //   434: astore_1
    //   435: aload_1
    //   436: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   439: aload 7
    //   441: invokestatic 201	cn/com/xy/sms/sdk/util/d:a	(Ljava/io/File;)V
    //   444: goto -159 -> 285
    //   447: astore_1
    //   448: ldc2_w 202
    //   451: invokestatic 207	java/lang/Thread:sleep	(J)V
    //   454: aload_0
    //   455: invokestatic 209	cn/com/xy/sms/sdk/util/z:b	()Lcn/com/xy/sms/sdk/db/entity/l;
    //   458: invokespecial 211	cn/com/xy/sms/sdk/util/z:a	(Lcn/com/xy/sms/sdk/db/entity/l;)V
    //   461: aload_1
    //   462: athrow
    //   463: astore_1
    //   464: aload_1
    //   465: invokevirtual 226	java/lang/Throwable:printStackTrace	()V
    //   468: aload 7
    //   470: invokestatic 201	cn/com/xy/sms/sdk/util/d:a	(Ljava/io/File;)V
    //   473: goto -188 -> 285
    //   476: astore_1
    //   477: aload_1
    //   478: invokevirtual 226	java/lang/Throwable:printStackTrace	()V
    //   481: goto -55 -> 426
    //   484: astore 7
    //   486: aload 7
    //   488: invokevirtual 226	java/lang/Throwable:printStackTrace	()V
    //   491: goto -37 -> 454
    //   494: astore_1
    //   495: aload_1
    //   496: invokevirtual 226	java/lang/Throwable:printStackTrace	()V
    //   499: goto -208 -> 291
    //   502: iconst_0
    //   503: putstatic 25	cn/com/xy/sms/sdk/util/z:e	Z
    //   506: return
    //   507: iconst_0
    //   508: istore_3
    //   509: goto -278 -> 231
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	512	0	this	z
    //   0	512	1	paraml	l
    //   235	4	2	i	int
    //   241	268	3	j	int
    //   357	3	4	bool	boolean
    //   264	11	5	l	long
    //   64	405	7	localFile	File
    //   484	3	7	localThrowable1	Throwable
    //   102	249	8	str	String
    //   377	1	8	localThrowable2	Throwable
    //   392	3	8	localInterruptedException	InterruptedException
    // Exception table:
    //   from	to	target	type
    //   299	359	377	java/lang/Throwable
    //   362	374	392	java/lang/InterruptedException
    //   249	285	402	java/util/zip/ZipException
    //   12	80	415	java/lang/Throwable
    //   80	143	415	java/lang/Throwable
    //   143	228	415	java/lang/Throwable
    //   231	236	415	java/lang/Throwable
    //   362	374	415	java/lang/Throwable
    //   379	389	415	java/lang/Throwable
    //   394	399	415	java/lang/Throwable
    //   403	412	415	java/lang/Throwable
    //   435	444	415	java/lang/Throwable
    //   464	473	415	java/lang/Throwable
    //   249	285	434	java/io/IOException
    //   12	80	447	finally
    //   80	143	447	finally
    //   143	228	447	finally
    //   231	236	447	finally
    //   249	285	447	finally
    //   299	359	447	finally
    //   362	374	447	finally
    //   379	389	447	finally
    //   394	399	447	finally
    //   403	412	447	finally
    //   416	420	447	finally
    //   435	444	447	finally
    //   464	473	447	finally
    //   249	285	463	java/lang/Throwable
    //   420	426	476	java/lang/Throwable
    //   448	454	484	java/lang/Throwable
    //   285	291	494	java/lang/Throwable
  }
  
  public static void a(String paramString)
  {
    try
    {
      if ((!StringUtils.isNull(paramString)) && (!d.contains(paramString))) {
        d.add(paramString);
      }
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public static void a(boolean paramBoolean)
  {
    try
    {
      if (!e) {
        new z().start();
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  private boolean a(String paramString, File paramFile)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 9
    //   3: aconst_null
    //   4: astore 8
    //   6: aload_1
    //   7: invokestatic 233	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   10: ifne +697 -> 707
    //   13: aload_1
    //   14: ldc -12
    //   16: invokevirtual 247	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   19: ifne +12 -> 31
    //   22: aload_1
    //   23: ldc -7
    //   25: invokevirtual 247	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   28: ifeq +30 -> 58
    //   31: aload_1
    //   32: iconst_1
    //   33: invokestatic 254	cn/com/xy/sms/sdk/net/b:a	(Ljava/lang/String;I)Ljavax/net/ssl/HttpsURLConnection;
    //   36: astore 6
    //   38: aload 6
    //   40: ifnonnull +117 -> 157
    //   43: aload 6
    //   45: ifnull +8 -> 53
    //   48: aload 6
    //   50: invokevirtual 259	java/net/HttpURLConnection:disconnect	()V
    //   53: invokestatic 262	java/lang/System:gc	()V
    //   56: iconst_0
    //   57: ireturn
    //   58: new 264	java/net/URL
    //   61: dup
    //   62: aload_1
    //   63: invokestatic 268	cn/com/xy/sms/sdk/net/NetUtil:getUrlWithPara	(Ljava/lang/String;)Ljava/lang/String;
    //   66: invokespecial 269	java/net/URL:<init>	(Ljava/lang/String;)V
    //   69: astore 7
    //   71: aload 7
    //   73: invokevirtual 273	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   76: checkcast 256	java/net/HttpURLConnection
    //   79: astore 6
    //   81: aload 6
    //   83: sipush 5000
    //   86: invokevirtual 277	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   89: aload 6
    //   91: ldc_w 279
    //   94: invokevirtual 282	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   97: aload 6
    //   99: ldc_w 284
    //   102: ldc_w 286
    //   105: invokevirtual 290	java/net/HttpURLConnection:addRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   108: aload 6
    //   110: ldc_w 292
    //   113: ldc_w 294
    //   116: invokevirtual 290	java/net/HttpURLConnection:addRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   119: aload 6
    //   121: invokevirtual 297	java/net/HttpURLConnection:connect	()V
    //   124: getstatic 147	cn/com/xy/sms/sdk/log/LogManager:debug	Z
    //   127: ifeq +19 -> 146
    //   130: new 86	java/lang/StringBuilder
    //   133: dup
    //   134: ldc_w 299
    //   137: invokespecial 99	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   140: aload 7
    //   142: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   145: pop
    //   146: goto -108 -> 38
    //   149: astore_1
    //   150: aload_1
    //   151: invokevirtual 226	java/lang/Throwable:printStackTrace	()V
    //   154: goto -101 -> 53
    //   157: aload 6
    //   159: invokevirtual 303	java/net/HttpURLConnection:getResponseCode	()I
    //   162: istore_3
    //   163: iload_3
    //   164: sipush 200
    //   167: if_icmpeq +10 -> 177
    //   170: iload_3
    //   171: sipush 206
    //   174: if_icmpne +269 -> 443
    //   177: aload 6
    //   179: invokevirtual 306	java/net/HttpURLConnection:getContentLength	()I
    //   182: i2l
    //   183: lstore 4
    //   185: getstatic 147	cn/com/xy/sms/sdk/log/LogManager:debug	Z
    //   188: ifeq +3 -> 191
    //   191: lload 4
    //   193: lconst_0
    //   194: lcmp
    //   195: ifne +79 -> 274
    //   198: aload_0
    //   199: getfield 82	cn/com/xy/sms/sdk/util/z:c	Lcn/com/xy/sms/sdk/db/entity/l;
    //   202: ifnull +22 -> 224
    //   205: aload_0
    //   206: getfield 82	cn/com/xy/sms/sdk/util/z:c	Lcn/com/xy/sms/sdk/db/entity/l;
    //   209: invokestatic 160	java/lang/System:currentTimeMillis	()J
    //   212: bipush 18
    //   214: ldc2_w 307
    //   217: invokestatic 314	cn/com/xy/sms/sdk/dex/DexUtil:getUpdateCycleByType	(IJ)J
    //   220: ladd
    //   221: invokestatic 317	cn/com/xy/sms/sdk/db/entity/m:a	(Lcn/com/xy/sms/sdk/db/entity/l;J)V
    //   224: new 86	java/lang/StringBuilder
    //   227: dup
    //   228: ldc_w 319
    //   231: invokespecial 99	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   234: aload_0
    //   235: getfield 82	cn/com/xy/sms/sdk/util/z:c	Lcn/com/xy/sms/sdk/db/entity/l;
    //   238: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   241: pop
    //   242: aload_2
    //   243: invokestatic 201	cn/com/xy/sms/sdk/util/d:a	(Ljava/io/File;)V
    //   246: aload_0
    //   247: iconst_4
    //   248: putfield 29	cn/com/xy/sms/sdk/util/z:a	I
    //   251: aload 6
    //   253: ifnull +8 -> 261
    //   256: aload 6
    //   258: invokevirtual 259	java/net/HttpURLConnection:disconnect	()V
    //   261: invokestatic 262	java/lang/System:gc	()V
    //   264: iconst_0
    //   265: ireturn
    //   266: astore_1
    //   267: aload_1
    //   268: invokevirtual 226	java/lang/Throwable:printStackTrace	()V
    //   271: goto -10 -> 261
    //   274: aload 6
    //   276: invokevirtual 323	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   279: astore 7
    //   281: sipush 1024
    //   284: newarray <illegal type>
    //   286: astore 8
    //   288: new 325	java/io/RandomAccessFile
    //   291: dup
    //   292: aload_2
    //   293: ldc_w 327
    //   296: invokespecial 328	java/io/RandomAccessFile:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   299: astore_2
    //   300: aload_2
    //   301: lconst_0
    //   302: invokevirtual 331	java/io/RandomAccessFile:seek	(J)V
    //   305: aload 7
    //   307: aload 8
    //   309: invokevirtual 337	java/io/InputStream:read	([B)I
    //   312: istore_3
    //   313: iload_3
    //   314: iconst_m1
    //   315: if_icmpne +64 -> 379
    //   318: aload_2
    //   319: invokevirtual 340	java/io/RandomAccessFile:close	()V
    //   322: aload 7
    //   324: ifnull +8 -> 332
    //   327: aload 7
    //   329: invokevirtual 341	java/io/InputStream:close	()V
    //   332: aload 6
    //   334: ifnull +8 -> 342
    //   337: aload 6
    //   339: invokevirtual 259	java/net/HttpURLConnection:disconnect	()V
    //   342: invokestatic 262	java/lang/System:gc	()V
    //   345: getstatic 147	cn/com/xy/sms/sdk/log/LogManager:debug	Z
    //   348: ifeq +29 -> 377
    //   351: new 86	java/lang/StringBuilder
    //   354: dup
    //   355: ldc_w 343
    //   358: invokespecial 99	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   361: aload_1
    //   362: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   365: ldc_w 345
    //   368: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   371: lload 4
    //   373: invokevirtual 185	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   376: pop
    //   377: iconst_1
    //   378: ireturn
    //   379: aload_2
    //   380: aload 8
    //   382: iconst_0
    //   383: iload_3
    //   384: invokevirtual 349	java/io/RandomAccessFile:write	([BII)V
    //   387: goto -82 -> 305
    //   390: astore 8
    //   392: aload 6
    //   394: astore_1
    //   395: aload 8
    //   397: astore 6
    //   399: aload 6
    //   401: athrow
    //   402: astore 8
    //   404: aload_1
    //   405: astore 6
    //   407: aload 8
    //   409: astore_1
    //   410: aload_2
    //   411: ifnull +7 -> 418
    //   414: aload_2
    //   415: invokevirtual 340	java/io/RandomAccessFile:close	()V
    //   418: aload 7
    //   420: ifnull +8 -> 428
    //   423: aload 7
    //   425: invokevirtual 341	java/io/InputStream:close	()V
    //   428: aload 6
    //   430: ifnull +8 -> 438
    //   433: aload 6
    //   435: invokevirtual 259	java/net/HttpURLConnection:disconnect	()V
    //   438: invokestatic 262	java/lang/System:gc	()V
    //   441: aload_1
    //   442: athrow
    //   443: iload_3
    //   444: sipush 404
    //   447: if_icmpne +79 -> 526
    //   450: new 86	java/lang/StringBuilder
    //   453: dup
    //   454: ldc_w 351
    //   457: invokespecial 99	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   460: aload_0
    //   461: getfield 82	cn/com/xy/sms/sdk/util/z:c	Lcn/com/xy/sms/sdk/db/entity/l;
    //   464: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   467: pop
    //   468: aload_0
    //   469: getfield 82	cn/com/xy/sms/sdk/util/z:c	Lcn/com/xy/sms/sdk/db/entity/l;
    //   472: ifnull +22 -> 494
    //   475: aload_0
    //   476: getfield 82	cn/com/xy/sms/sdk/util/z:c	Lcn/com/xy/sms/sdk/db/entity/l;
    //   479: invokestatic 160	java/lang/System:currentTimeMillis	()J
    //   482: bipush 18
    //   484: ldc2_w 307
    //   487: invokestatic 314	cn/com/xy/sms/sdk/dex/DexUtil:getUpdateCycleByType	(IJ)J
    //   490: ladd
    //   491: invokestatic 317	cn/com/xy/sms/sdk/db/entity/m:a	(Lcn/com/xy/sms/sdk/db/entity/l;J)V
    //   494: aload_2
    //   495: invokestatic 201	cn/com/xy/sms/sdk/util/d:a	(Ljava/io/File;)V
    //   498: aload_0
    //   499: iconst_4
    //   500: putfield 29	cn/com/xy/sms/sdk/util/z:a	I
    //   503: aload 6
    //   505: ifnull +8 -> 513
    //   508: aload 6
    //   510: invokevirtual 259	java/net/HttpURLConnection:disconnect	()V
    //   513: invokestatic 262	java/lang/System:gc	()V
    //   516: iconst_0
    //   517: ireturn
    //   518: astore_1
    //   519: aload_1
    //   520: invokevirtual 226	java/lang/Throwable:printStackTrace	()V
    //   523: goto -10 -> 513
    //   526: aload 6
    //   528: ifnull +8 -> 536
    //   531: aload 6
    //   533: invokevirtual 259	java/net/HttpURLConnection:disconnect	()V
    //   536: invokestatic 262	java/lang/System:gc	()V
    //   539: iconst_0
    //   540: ireturn
    //   541: astore_1
    //   542: aload_1
    //   543: invokevirtual 226	java/lang/Throwable:printStackTrace	()V
    //   546: goto -10 -> 536
    //   549: astore_2
    //   550: aload_2
    //   551: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   554: goto -136 -> 418
    //   557: astore_2
    //   558: aload_2
    //   559: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   562: goto -134 -> 428
    //   565: astore_2
    //   566: aload_2
    //   567: invokevirtual 226	java/lang/Throwable:printStackTrace	()V
    //   570: goto -132 -> 438
    //   573: astore_2
    //   574: aload_2
    //   575: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   578: goto -256 -> 322
    //   581: astore_2
    //   582: aload_2
    //   583: invokevirtual 227	java/io/IOException:printStackTrace	()V
    //   586: goto -254 -> 332
    //   589: astore_2
    //   590: aload_2
    //   591: invokevirtual 226	java/lang/Throwable:printStackTrace	()V
    //   594: goto -252 -> 342
    //   597: astore_1
    //   598: aconst_null
    //   599: astore 6
    //   601: aconst_null
    //   602: astore_2
    //   603: aload 9
    //   605: astore 7
    //   607: goto -197 -> 410
    //   610: astore_1
    //   611: aconst_null
    //   612: astore_2
    //   613: aload 9
    //   615: astore 7
    //   617: goto -207 -> 410
    //   620: astore_1
    //   621: aconst_null
    //   622: astore_2
    //   623: aload 9
    //   625: astore 7
    //   627: goto -217 -> 410
    //   630: astore_1
    //   631: aconst_null
    //   632: astore_2
    //   633: goto -223 -> 410
    //   636: astore_1
    //   637: goto -227 -> 410
    //   640: astore 6
    //   642: aconst_null
    //   643: astore_1
    //   644: aconst_null
    //   645: astore_2
    //   646: aload 8
    //   648: astore 7
    //   650: goto -251 -> 399
    //   653: astore_1
    //   654: aconst_null
    //   655: astore 7
    //   657: aload 6
    //   659: astore_2
    //   660: aload_1
    //   661: astore 6
    //   663: aload_2
    //   664: astore_1
    //   665: aload 7
    //   667: astore_2
    //   668: aload 8
    //   670: astore 7
    //   672: goto -273 -> 399
    //   675: astore 7
    //   677: aload 6
    //   679: astore_1
    //   680: aconst_null
    //   681: astore_2
    //   682: aload 7
    //   684: astore 6
    //   686: aload 8
    //   688: astore 7
    //   690: goto -291 -> 399
    //   693: astore 8
    //   695: aconst_null
    //   696: astore_2
    //   697: aload 6
    //   699: astore_1
    //   700: aload 8
    //   702: astore 6
    //   704: goto -305 -> 399
    //   707: aconst_null
    //   708: astore 6
    //   710: goto -672 -> 38
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	713	0	this	z
    //   0	713	1	paramString	String
    //   0	713	2	paramFile	File
    //   162	286	3	i	int
    //   183	189	4	l	long
    //   36	564	6	localObject1	Object
    //   640	18	6	localThrowable1	Throwable
    //   661	48	6	localObject2	Object
    //   69	602	7	localObject3	Object
    //   675	8	7	localThrowable2	Throwable
    //   688	1	7	localObject4	Object
    //   4	377	8	arrayOfByte	byte[]
    //   390	6	8	localThrowable3	Throwable
    //   402	285	8	localObject5	Object
    //   693	8	8	localThrowable4	Throwable
    //   1	623	9	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   48	53	149	java/lang/Throwable
    //   256	261	266	java/lang/Throwable
    //   300	305	390	java/lang/Throwable
    //   305	313	390	java/lang/Throwable
    //   379	387	390	java/lang/Throwable
    //   399	402	402	finally
    //   508	513	518	java/lang/Throwable
    //   531	536	541	java/lang/Throwable
    //   414	418	549	java/io/IOException
    //   423	428	557	java/io/IOException
    //   433	438	565	java/lang/Throwable
    //   318	322	573	java/io/IOException
    //   327	332	581	java/io/IOException
    //   337	342	589	java/lang/Throwable
    //   6	31	597	finally
    //   31	38	597	finally
    //   58	81	597	finally
    //   81	146	610	finally
    //   157	163	620	finally
    //   177	191	620	finally
    //   198	224	620	finally
    //   224	251	620	finally
    //   274	281	620	finally
    //   450	494	620	finally
    //   494	503	620	finally
    //   281	300	630	finally
    //   300	305	636	finally
    //   305	313	636	finally
    //   379	387	636	finally
    //   6	31	640	java/lang/Throwable
    //   31	38	640	java/lang/Throwable
    //   58	81	640	java/lang/Throwable
    //   81	146	653	java/lang/Throwable
    //   157	163	675	java/lang/Throwable
    //   177	191	675	java/lang/Throwable
    //   198	224	675	java/lang/Throwable
    //   224	251	675	java/lang/Throwable
    //   274	281	675	java/lang/Throwable
    //   450	494	675	java/lang/Throwable
    //   494	503	675	java/lang/Throwable
    //   281	300	693	java/lang/Throwable
  }
  
  private static l b()
  {
    try
    {
      l locall2 = a();
      l locall1 = locall2;
      if (locall2 == null) {
        locall1 = m.b();
      }
      return locall1;
    }
    finally {}
  }
  
  private void b(l paraml)
  {
    boolean bool2 = false;
    File localFile = new File(new StringBuilder(String.valueOf(Constant.getFilePath())).append("ziptemp").toString() + File.separator);
    if (!localFile.exists()) {
      localFile.mkdirs();
    }
    new StringBuilder("filePath =").append(localFile.getAbsolutePath());
    String str = c;
    localFile = new File(localFile, str.substring(str.lastIndexOf('/') + 1));
    if (localFile.exists()) {
      localFile.delete();
    }
    boolean bool1 = bool2;
    if (LogManager.debug)
    {
      new StringBuilder("下载时间=").append(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Long.valueOf(System.currentTimeMillis()))).append(" 下载url=").append(NetUtil.prex).append(c).append(" scene_id=").append(c.b).append(" id=").append(c.a);
      bool1 = bool2;
    }
    for (;;)
    {
      if (((a < 2) && (!bool1)) || (bool1)) {}
      try
      {
        XyUtil.upZipFile(localFile, Constant.getDRAWBLE_PATH());
        long l = c.a;
        paraml = c.c;
        try
        {
          paraml = new ContentValues();
          paraml.put("status", Integer.valueOf(1));
          DBManager.update("tb_res_download", paraml, "id = ? ", new String[] { l });
          d.a(localFile);
          return;
          try
          {
            str = NetUtil.prex + c;
            new StringBuilder("dUrl =").append(str).append(" saveFilePath: ").append(localFile);
            bool2 = a(str, localFile);
            bool1 = bool2;
          }
          catch (Throwable localThrowable)
          {
            for (;;)
            {
              a += 1;
            }
          }
          try
          {
            Thread.sleep(a * 4000);
          }
          catch (InterruptedException localInterruptedException)
          {
            localInterruptedException.printStackTrace();
          }
        }
        catch (Throwable paraml)
        {
          for (;;)
          {
            paraml.printStackTrace();
          }
        }
        return;
      }
      catch (ZipException paraml)
      {
        paraml.printStackTrace();
        d.a(localFile);
        return;
      }
      catch (IOException paraml)
      {
        paraml.printStackTrace();
        d.a(localFile);
        return;
      }
      catch (Throwable paraml)
      {
        paraml.printStackTrace();
        d.a(localFile);
      }
    }
  }
  
  private static void b(String paramString)
  {
    try
    {
      d.remove(paramString);
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  private static void b(boolean paramBoolean)
  {
    try
    {
      e = paramBoolean;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  private static String c()
  {
    return Constant.getFilePath() + "ziptemp";
  }
  
  private static String c(l paraml)
  {
    paraml = c;
    return paraml.substring(paraml.lastIndexOf('/') + 1);
  }
  
  public final void run()
  {
    if (!e) {
      b(true);
    }
    try
    {
      Thread.sleep(3000L);
      a(b());
      b(false);
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        localThrowable.printStackTrace();
      }
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.util.z
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */