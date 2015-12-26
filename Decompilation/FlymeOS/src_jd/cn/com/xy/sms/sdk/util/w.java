package cn.com.xy.sms.sdk.util;

import cn.com.xy.sms.sdk.db.entity.SceneRule;
import cn.com.xy.sms.sdk.db.entity.p;
import cn.com.xy.sms.sdk.db.entity.v;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public final class w
  extends Thread
{
  private static boolean a;
  private static HashSet<String> c = new HashSet();
  private static boolean d = false;
  private v b;
  
  private static v a()
  {
    for (;;)
    {
      try
      {
        Iterator localIterator = c.iterator();
        v localv = null;
        ArrayList localArrayList = new ArrayList();
        Object localObject2 = localv;
        if (localIterator != null)
        {
          if (!localIterator.hasNext()) {
            localObject2 = localv;
          }
        }
        else
        {
          c.removeAll(localArrayList);
          localArrayList.clear();
          return (v)localObject2;
        }
        localObject2 = (String)localIterator.next();
        localv = cn.com.xy.sms.sdk.db.entity.w.b((String)localObject2);
        localArrayList.add(localObject2);
        if (localv != null) {
          localObject2 = localObject1;
        }
      }
      finally {}
    }
  }
  
  private static List<p> a(Document paramDocument)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramDocument != null) {}
    p localp;
    Object localObject1;
    int j;
    int m;
    label128:
    Object localObject4;
    try
    {
      paramDocument = paramDocument.getElementsByTagName("Scene");
      int i = 0;
      for (;;)
      {
        if (i >= paramDocument.getLength())
        {
          new StringBuilder("sceneconfigList=").append(localArrayList.toString());
          return localArrayList;
        }
        localp = new p();
        localObject1 = (Element)paramDocument.item(i);
        localObject2 = ((Element)localObject1).getChildNodes();
        j = 0;
        if (j < ((NodeList)localObject2).getLength()) {
          break;
        }
        localObject1 = ((Element)localObject1).getElementsByTagName("SceneRule");
        if (localObject1 != null)
        {
          m = ((NodeList)localObject1).getLength();
          j = 0;
          break label632;
        }
        localArrayList.add(localp);
        i += 1;
      }
      localObject3 = ((NodeList)localObject2).item(j);
      if (((Node)localObject3).getNodeType() != 1) {
        break label641;
      }
      localObject4 = ((Node)localObject3).getNodeName();
      if ("sceneId".equalsIgnoreCase((String)localObject4)) {
        a = x.a((Node)localObject3);
      } else if ("sceneVersion".equalsIgnoreCase((String)localObject4)) {
        b = x.a((Node)localObject3);
      }
    }
    catch (Throwable paramDocument)
    {
      paramDocument.printStackTrace();
      return localArrayList;
    }
    label229:
    Object localObject2 = new SceneRule();
    Object localObject3 = ((Element)((NodeList)localObject1).item(j)).getChildNodes();
    int k = 0;
    for (;;)
    {
      if (k >= ((NodeList)localObject3).getLength())
      {
        localp.a((SceneRule)localObject2);
        j += 1;
      }
      else
      {
        localObject4 = ((NodeList)localObject3).item(k);
        if (((Node)localObject4).getNodeType() != 1) {
          break label648;
        }
        String str = ((Node)localObject4).getNodeName();
        if (((Node)localObject4).getNodeType() != 1) {
          break label648;
        }
        if ("sceneId".equalsIgnoreCase(str))
        {
          scene_id = x.a((Node)localObject4);
          break label648;
        }
        if ("sceneRuleVersion".equalsIgnoreCase(str))
        {
          sceneruleVersion = x.a((Node)localObject4);
          break label648;
        }
        if ("province".equalsIgnoreCase(str))
        {
          province = x.a((Node)localObject4);
          break label648;
        }
        if ("id".equalsIgnoreCase(str))
        {
          id = x.a((Node)localObject4);
          break label648;
        }
        if ("operator".equalsIgnoreCase(str))
        {
          operator = x.a((Node)localObject4);
          break label648;
        }
        if ("expire_date".equalsIgnoreCase(str))
        {
          expire_date = x.a((Node)localObject4);
          break label648;
        }
        if ("fun_call".equalsIgnoreCase(str))
        {
          Func_call = Integer.parseInt(x.a((Node)localObject4));
          break label648;
        }
        if ("fun_acc_url".equalsIgnoreCase(str))
        {
          Func_acc_url = Integer.parseInt(x.a((Node)localObject4));
          break label648;
        }
        if ("fun_reply_sms".equalsIgnoreCase(str))
        {
          Func_reply_sms = Integer.parseInt(x.a((Node)localObject4));
          break label648;
        }
        if ("fun_config".equalsIgnoreCase(str))
        {
          Func_config = x.a((Node)localObject4);
          break label648;
        }
        if ("res_urls".equalsIgnoreCase(str))
        {
          res_urls = x.a((Node)localObject4);
          break label648;
        }
        if ("s_version".equalsIgnoreCase(str))
        {
          s_version = x.a((Node)localObject4);
          break label648;
        }
        if (!"scene_page_conf".equalsIgnoreCase(str)) {
          break label648;
        }
        Scene_page_config = x.a((Node)localObject4);
        break label648;
      }
      label632:
      if (j < m) {
        break label229;
      }
      break label128;
      label641:
      j += 1;
      break;
      label648:
      k += 1;
    }
  }
  
  /* Error */
  private void a(v paramv)
  {
    // Byte code:
    //   0: iconst_2
    //   1: invokestatic 223	cn/com/xy/sms/sdk/net/NetUtil:checkAccessNetWork	(I)Z
    //   4: ifne +4 -> 8
    //   7: return
    //   8: aload_1
    //   9: ifnull +81 -> 90
    //   12: aload_0
    //   13: aload_1
    //   14: invokespecial 225	cn/com/xy/sms/sdk/util/w:b	(Lcn/com/xy/sms/sdk/db/entity/v;)V
    //   17: ldc2_w 226
    //   20: invokestatic 231	java/lang/Thread:sleep	(J)V
    //   23: aload_0
    //   24: invokestatic 233	cn/com/xy/sms/sdk/util/w:b	()Lcn/com/xy/sms/sdk/db/entity/v;
    //   27: invokespecial 235	cn/com/xy/sms/sdk/util/w:a	(Lcn/com/xy/sms/sdk/db/entity/v;)V
    //   30: return
    //   31: astore_1
    //   32: aload_1
    //   33: invokevirtual 144	java/lang/Throwable:printStackTrace	()V
    //   36: ldc2_w 226
    //   39: invokestatic 231	java/lang/Thread:sleep	(J)V
    //   42: aload_0
    //   43: invokestatic 233	cn/com/xy/sms/sdk/util/w:b	()Lcn/com/xy/sms/sdk/db/entity/v;
    //   46: invokespecial 235	cn/com/xy/sms/sdk/util/w:a	(Lcn/com/xy/sms/sdk/db/entity/v;)V
    //   49: return
    //   50: astore_1
    //   51: aload_1
    //   52: invokevirtual 144	java/lang/Throwable:printStackTrace	()V
    //   55: goto -13 -> 42
    //   58: astore_1
    //   59: ldc2_w 226
    //   62: invokestatic 231	java/lang/Thread:sleep	(J)V
    //   65: aload_0
    //   66: invokestatic 233	cn/com/xy/sms/sdk/util/w:b	()Lcn/com/xy/sms/sdk/db/entity/v;
    //   69: invokespecial 235	cn/com/xy/sms/sdk/util/w:a	(Lcn/com/xy/sms/sdk/db/entity/v;)V
    //   72: aload_1
    //   73: athrow
    //   74: astore_2
    //   75: aload_2
    //   76: invokevirtual 144	java/lang/Throwable:printStackTrace	()V
    //   79: goto -14 -> 65
    //   82: astore_1
    //   83: aload_1
    //   84: invokevirtual 144	java/lang/Throwable:printStackTrace	()V
    //   87: goto -64 -> 23
    //   90: iconst_0
    //   91: putstatic 23	cn/com/xy/sms/sdk/util/w:d	Z
    //   94: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	95	0	this	w
    //   0	95	1	paramv	v
    //   74	2	2	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   12	17	31	java/lang/Throwable
    //   36	42	50	java/lang/Throwable
    //   12	17	58	finally
    //   32	36	58	finally
    //   59	65	74	java/lang/Throwable
    //   17	23	82	java/lang/Throwable
  }
  
  public static void a(String paramString)
  {
    try
    {
      if ((!StringUtils.isNull(paramString)) && (!c.contains(paramString))) {
        c.add(paramString);
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
      if (!d) {
        new w().start();
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  private static v b()
  {
    try
    {
      v localv2 = a();
      v localv1 = localv2;
      if (localv2 == null) {
        localv1 = cn.com.xy.sms.sdk.db.entity.w.b();
      }
      return localv1;
    }
    finally {}
  }
  
  /* Error */
  private void b(v paramv)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore 5
    //   3: iconst_1
    //   4: istore 6
    //   6: iconst_1
    //   7: istore_3
    //   8: aload_0
    //   9: monitorenter
    //   10: new 254	java/net/URL
    //   13: dup
    //   14: aload_1
    //   15: getfield 258	cn/com/xy/sms/sdk/db/entity/v:c	Ljava/lang/String;
    //   18: invokespecial 259	java/net/URL:<init>	(Ljava/lang/String;)V
    //   21: invokevirtual 263	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   24: checkcast 265	java/net/HttpURLConnection
    //   27: astore 9
    //   29: aload 9
    //   31: invokevirtual 268	java/net/HttpURLConnection:getResponseCode	()I
    //   34: istore_2
    //   35: new 80	java/lang/StringBuilder
    //   38: dup
    //   39: ldc_w 270
    //   42: invokespecial 85	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   45: aload_1
    //   46: getfield 258	cn/com/xy/sms/sdk/db/entity/v:c	Ljava/lang/String;
    //   49: invokevirtual 95	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   52: ldc_w 272
    //   55: invokevirtual 95	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   58: iload_2
    //   59: invokevirtual 275	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   62: pop
    //   63: iload_2
    //   64: sipush 200
    //   67: if_icmpne +303 -> 370
    //   70: iload 5
    //   72: istore 4
    //   74: iload 6
    //   76: istore_2
    //   77: aload 9
    //   79: invokevirtual 279	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   82: astore 11
    //   84: iload 5
    //   86: istore 4
    //   88: iload 6
    //   90: istore_2
    //   91: aload 11
    //   93: invokestatic 284	cn/com/xy/sms/sdk/util/d:b	(Ljava/io/InputStream;)[B
    //   96: astore 9
    //   98: iload 6
    //   100: istore_2
    //   101: aload 9
    //   103: invokestatic 288	cn/com/xy/sms/sdk/util/StringUtils:uncompressGZip	([B)[B
    //   106: astore 10
    //   108: aload 10
    //   110: astore 9
    //   112: iload 5
    //   114: istore 4
    //   116: iload 6
    //   118: istore_2
    //   119: new 52	java/lang/String
    //   122: dup
    //   123: aload 9
    //   125: ldc_w 290
    //   128: invokespecial 293	java/lang/String:<init>	([BLjava/lang/String;)V
    //   131: ldc_w 295
    //   134: invokestatic 299	cn/com/xy/sms/sdk/util/StringUtils:stringConvertXML	(Ljava/lang/String;Ljava/lang/String;)Lorg/w3c/dom/Document;
    //   137: invokestatic 301	cn/com/xy/sms/sdk/util/w:a	(Lorg/w3c/dom/Document;)Ljava/util/List;
    //   140: astore 9
    //   142: iload 5
    //   144: istore 4
    //   146: iload 6
    //   148: istore_2
    //   149: aload 11
    //   151: invokestatic 304	cn/com/xy/sms/sdk/util/d:a	(Ljava/io/Closeable;)V
    //   154: iload 5
    //   156: istore 4
    //   158: iload 6
    //   160: istore_2
    //   161: aload 9
    //   163: aload_1
    //   164: getfield 307	cn/com/xy/sms/sdk/db/entity/v:f	I
    //   167: invokestatic 313	cn/com/xy/sms/sdk/util/SceneconfigUtil:handleSceneconfig	(Ljava/util/List;I)V
    //   170: iload_3
    //   171: istore 4
    //   173: iload_3
    //   174: istore_2
    //   175: invokestatic 319	java/lang/System:currentTimeMillis	()J
    //   178: lstore 7
    //   180: iload_3
    //   181: istore_2
    //   182: new 321	android/content/ContentValues
    //   185: dup
    //   186: invokespecial 322	android/content/ContentValues:<init>	()V
    //   189: astore 9
    //   191: iload_3
    //   192: istore_2
    //   193: aload 9
    //   195: ldc_w 324
    //   198: new 80	java/lang/StringBuilder
    //   201: dup
    //   202: lload 7
    //   204: invokestatic 328	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   207: invokespecial 85	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   210: invokevirtual 329	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   213: invokevirtual 333	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   216: iload_3
    //   217: istore_2
    //   218: ldc_w 335
    //   221: aload 9
    //   223: ldc_w 337
    //   226: iconst_1
    //   227: anewarray 52	java/lang/String
    //   230: dup
    //   231: iconst_0
    //   232: new 80	java/lang/StringBuilder
    //   235: dup
    //   236: aload_1
    //   237: getfield 340	cn/com/xy/sms/sdk/db/entity/v:a	J
    //   240: invokestatic 328	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   243: invokespecial 85	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   246: invokevirtual 329	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   249: aastore
    //   250: invokestatic 346	cn/com/xy/sms/sdk/db/DBManager:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   253: pop
    //   254: iload_3
    //   255: ifeq +11 -> 266
    //   258: aload_1
    //   259: getfield 340	cn/com/xy/sms/sdk/db/entity/v:a	J
    //   262: iconst_1
    //   263: invokestatic 349	cn/com/xy/sms/sdk/db/entity/w:a	(JI)V
    //   266: aload_0
    //   267: monitorexit
    //   268: return
    //   269: astore 10
    //   271: iload 5
    //   273: istore 4
    //   275: iload 6
    //   277: istore_2
    //   278: aload 10
    //   280: invokevirtual 144	java/lang/Throwable:printStackTrace	()V
    //   283: goto -171 -> 112
    //   286: astore 9
    //   288: iload 4
    //   290: istore_2
    //   291: ldc_w 351
    //   294: ldc_w 353
    //   297: aload 9
    //   299: invokestatic 359	cn/com/xy/sms/sdk/log/LogManager:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   302: iload 4
    //   304: ifeq -38 -> 266
    //   307: aload_1
    //   308: getfield 340	cn/com/xy/sms/sdk/db/entity/v:a	J
    //   311: iconst_1
    //   312: invokestatic 349	cn/com/xy/sms/sdk/db/entity/w:a	(JI)V
    //   315: goto -49 -> 266
    //   318: astore_1
    //   319: aload_0
    //   320: monitorexit
    //   321: aload_1
    //   322: athrow
    //   323: astore 9
    //   325: iload_3
    //   326: istore 4
    //   328: iload_3
    //   329: istore_2
    //   330: aload 9
    //   332: invokevirtual 144	java/lang/Throwable:printStackTrace	()V
    //   335: goto -81 -> 254
    //   338: astore 9
    //   340: iload_2
    //   341: ifeq +11 -> 352
    //   344: aload_1
    //   345: getfield 340	cn/com/xy/sms/sdk/db/entity/v:a	J
    //   348: iconst_1
    //   349: invokestatic 349	cn/com/xy/sms/sdk/db/entity/w:a	(JI)V
    //   352: aload 9
    //   354: athrow
    //   355: astore 9
    //   357: iconst_0
    //   358: istore_2
    //   359: goto -19 -> 340
    //   362: astore 9
    //   364: iconst_0
    //   365: istore 4
    //   367: goto -79 -> 288
    //   370: iconst_0
    //   371: istore_3
    //   372: goto -202 -> 170
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	375	0	this	w
    //   0	375	1	paramv	v
    //   34	325	2	i	int
    //   7	365	3	j	int
    //   72	294	4	k	int
    //   1	271	5	m	int
    //   4	272	6	n	int
    //   178	25	7	l	long
    //   27	195	9	localObject1	Object
    //   286	12	9	localThrowable1	Throwable
    //   323	8	9	localThrowable2	Throwable
    //   338	15	9	localObject2	Object
    //   355	1	9	localObject3	Object
    //   362	1	9	localThrowable3	Throwable
    //   106	3	10	arrayOfByte	byte[]
    //   269	10	10	localThrowable4	Throwable
    //   82	68	11	localInputStream	java.io.InputStream
    // Exception table:
    //   from	to	target	type
    //   101	108	269	java/lang/Throwable
    //   77	84	286	java/lang/Throwable
    //   91	98	286	java/lang/Throwable
    //   119	142	286	java/lang/Throwable
    //   149	154	286	java/lang/Throwable
    //   161	170	286	java/lang/Throwable
    //   175	180	286	java/lang/Throwable
    //   278	283	286	java/lang/Throwable
    //   330	335	286	java/lang/Throwable
    //   258	266	318	finally
    //   307	315	318	finally
    //   344	352	318	finally
    //   352	355	318	finally
    //   182	191	323	java/lang/Throwable
    //   193	216	323	java/lang/Throwable
    //   218	254	323	java/lang/Throwable
    //   77	84	338	finally
    //   91	98	338	finally
    //   101	108	338	finally
    //   119	142	338	finally
    //   149	154	338	finally
    //   161	170	338	finally
    //   175	180	338	finally
    //   182	191	338	finally
    //   193	216	338	finally
    //   218	254	338	finally
    //   278	283	338	finally
    //   291	302	338	finally
    //   330	335	338	finally
    //   10	63	355	finally
    //   10	63	362	java/lang/Throwable
  }
  
  private static void b(String paramString)
  {
    try
    {
      c.remove(paramString);
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
      d = paramBoolean;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public final void run()
  {
    if (!d) {
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
 * Qualified Name:     cn.com.xy.sms.sdk.util.w
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */