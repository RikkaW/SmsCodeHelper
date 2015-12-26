package com.xiaomi.smack.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProviderManager
{
  private static ProviderManager instance;
  private Map<String, Object> extensionProviders = new ConcurrentHashMap();
  private Map<String, Object> iqProviders = new ConcurrentHashMap();
  
  private ProviderManager()
  {
    initialize();
  }
  
  private ClassLoader[] getClassLoaders()
  {
    int i = 0;
    ClassLoader[] arrayOfClassLoader = new ClassLoader[2];
    arrayOfClassLoader[0] = ProviderManager.class.getClassLoader();
    arrayOfClassLoader[1] = Thread.currentThread().getContextClassLoader();
    ArrayList localArrayList = new ArrayList();
    int j = arrayOfClassLoader.length;
    while (i < j)
    {
      ClassLoader localClassLoader = arrayOfClassLoader[i];
      if (localClassLoader != null) {
        localArrayList.add(localClassLoader);
      }
      i += 1;
    }
    return (ClassLoader[])localArrayList.toArray(new ClassLoader[localArrayList.size()]);
  }
  
  public static ProviderManager getInstance()
  {
    try
    {
      if (instance == null) {
        instance = new ProviderManager();
      }
      ProviderManager localProviderManager = instance;
      return localProviderManager;
    }
    finally {}
  }
  
  private String getProviderKey(String paramString1, String paramString2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("<").append(paramString1).append("/>");
    if (paramString1 != null) {
      localStringBuilder.append("<").append(paramString2).append("/>");
    }
    return localStringBuilder.toString();
  }
  
  public void addExtensionProvider(String paramString1, String paramString2, Object paramObject)
  {
    if ((!(paramObject instanceof PacketExtensionProvider)) && (!(paramObject instanceof Class))) {
      throw new IllegalArgumentException("Provider must be a PacketExtensionProvider or a Class instance.");
    }
    paramString1 = getProviderKey(paramString1, paramString2);
    extensionProviders.put(paramString1, paramObject);
  }
  
  public Object getExtensionProvider(String paramString1, String paramString2)
  {
    paramString1 = getProviderKey(paramString1, paramString2);
    return extensionProviders.get(paramString1);
  }
  
  /* Error */
  protected void initialize()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 116	com/xiaomi/smack/provider/ProviderManager:getClassLoaders	()[Ljava/lang/ClassLoader;
    //   4: astore 8
    //   6: aload 8
    //   8: arraylength
    //   9: istore 4
    //   11: iconst_0
    //   12: istore_1
    //   13: iload_1
    //   14: iload 4
    //   16: if_icmpge +414 -> 430
    //   19: aload 8
    //   21: iload_1
    //   22: aaload
    //   23: ldc 118
    //   25: invokevirtual 122	java/lang/ClassLoader:getResources	(Ljava/lang/String;)Ljava/util/Enumeration;
    //   28: astore 9
    //   30: aload 9
    //   32: invokeinterface 128 1 0
    //   37: ifeq +658 -> 695
    //   40: aload 9
    //   42: invokeinterface 132 1 0
    //   47: checkcast 134	java/net/URL
    //   50: astore 7
    //   52: aconst_null
    //   53: astore 6
    //   55: aload 7
    //   57: invokevirtual 138	java/net/URL:openStream	()Ljava/io/InputStream;
    //   60: astore 7
    //   62: aload 7
    //   64: astore 6
    //   66: invokestatic 144	org/xmlpull/v1/XmlPullParserFactory:newInstance	()Lorg/xmlpull/v1/XmlPullParserFactory;
    //   69: invokevirtual 148	org/xmlpull/v1/XmlPullParserFactory:newPullParser	()Lorg/xmlpull/v1/XmlPullParser;
    //   72: astore 10
    //   74: aload 7
    //   76: astore 6
    //   78: aload 10
    //   80: ldc -106
    //   82: iconst_1
    //   83: invokeinterface 156 3 0
    //   88: aload 7
    //   90: astore 6
    //   92: aload 10
    //   94: aload 7
    //   96: ldc -98
    //   98: invokeinterface 162 3 0
    //   103: aload 7
    //   105: astore 6
    //   107: aload 10
    //   109: invokeinterface 165 1 0
    //   114: istore_2
    //   115: iload_2
    //   116: iconst_2
    //   117: if_icmpne +215 -> 332
    //   120: aload 7
    //   122: astore 6
    //   124: aload 10
    //   126: invokeinterface 168 1 0
    //   131: ldc -86
    //   133: invokevirtual 175	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   136: ifeq +295 -> 431
    //   139: aload 7
    //   141: astore 6
    //   143: aload 10
    //   145: invokeinterface 178 1 0
    //   150: pop
    //   151: aload 7
    //   153: astore 6
    //   155: aload 10
    //   157: invokeinterface 178 1 0
    //   162: pop
    //   163: aload 7
    //   165: astore 6
    //   167: aload 10
    //   169: invokeinterface 181 1 0
    //   174: astore 12
    //   176: aload 7
    //   178: astore 6
    //   180: aload 10
    //   182: invokeinterface 178 1 0
    //   187: pop
    //   188: aload 7
    //   190: astore 6
    //   192: aload 10
    //   194: invokeinterface 178 1 0
    //   199: pop
    //   200: aload 7
    //   202: astore 6
    //   204: aload 10
    //   206: invokeinterface 181 1 0
    //   211: astore 13
    //   213: aload 7
    //   215: astore 6
    //   217: aload 10
    //   219: invokeinterface 178 1 0
    //   224: pop
    //   225: aload 7
    //   227: astore 6
    //   229: aload 10
    //   231: invokeinterface 178 1 0
    //   236: pop
    //   237: aload 7
    //   239: astore 6
    //   241: aload 10
    //   243: invokeinterface 181 1 0
    //   248: astore 11
    //   250: aload 7
    //   252: astore 6
    //   254: aload_0
    //   255: aload 12
    //   257: aload 13
    //   259: invokespecial 98	com/xiaomi/smack/provider/ProviderManager:getProviderKey	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   262: astore 12
    //   264: aload 7
    //   266: astore 6
    //   268: aload_0
    //   269: getfield 21	com/xiaomi/smack/provider/ProviderManager:iqProviders	Ljava/util/Map;
    //   272: aload 12
    //   274: invokeinterface 184 2 0
    //   279: istore 5
    //   281: iload 5
    //   283: ifne +49 -> 332
    //   286: aload 7
    //   288: astore 6
    //   290: aload 11
    //   292: invokestatic 188	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   295: astore 11
    //   297: aload 7
    //   299: astore 6
    //   301: ldc -66
    //   303: aload 11
    //   305: invokevirtual 194	java/lang/Class:isAssignableFrom	(Ljava/lang/Class;)Z
    //   308: ifeq +56 -> 364
    //   311: aload 7
    //   313: astore 6
    //   315: aload_0
    //   316: getfield 21	com/xiaomi/smack/provider/ProviderManager:iqProviders	Ljava/util/Map;
    //   319: aload 12
    //   321: aload 11
    //   323: invokevirtual 196	java/lang/Class:newInstance	()Ljava/lang/Object;
    //   326: invokeinterface 104 3 0
    //   331: pop
    //   332: aload 7
    //   334: astore 6
    //   336: aload 10
    //   338: invokeinterface 178 1 0
    //   343: istore_3
    //   344: iload_3
    //   345: istore_2
    //   346: iload_3
    //   347: iconst_1
    //   348: if_icmpne -233 -> 115
    //   351: aload 7
    //   353: invokevirtual 201	java/io/InputStream:close	()V
    //   356: goto -326 -> 30
    //   359: astore 6
    //   361: goto -331 -> 30
    //   364: aload 7
    //   366: astore 6
    //   368: ldc -53
    //   370: aload 11
    //   372: invokevirtual 194	java/lang/Class:isAssignableFrom	(Ljava/lang/Class;)Z
    //   375: ifeq -43 -> 332
    //   378: aload 7
    //   380: astore 6
    //   382: aload_0
    //   383: getfield 21	com/xiaomi/smack/provider/ProviderManager:iqProviders	Ljava/util/Map;
    //   386: aload 12
    //   388: aload 11
    //   390: invokeinterface 104 3 0
    //   395: pop
    //   396: goto -64 -> 332
    //   399: astore 11
    //   401: aload 7
    //   403: astore 6
    //   405: aload 11
    //   407: invokevirtual 206	java/lang/ClassNotFoundException:printStackTrace	()V
    //   410: goto -78 -> 332
    //   413: astore 7
    //   415: aload 6
    //   417: invokevirtual 201	java/io/InputStream:close	()V
    //   420: aload 7
    //   422: athrow
    //   423: astore 6
    //   425: aload 6
    //   427: invokevirtual 207	java/lang/Exception:printStackTrace	()V
    //   430: return
    //   431: aload 7
    //   433: astore 6
    //   435: aload 10
    //   437: invokeinterface 168 1 0
    //   442: ldc -47
    //   444: invokevirtual 175	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   447: ifeq -115 -> 332
    //   450: aload 7
    //   452: astore 6
    //   454: aload 10
    //   456: invokeinterface 178 1 0
    //   461: pop
    //   462: aload 7
    //   464: astore 6
    //   466: aload 10
    //   468: invokeinterface 178 1 0
    //   473: pop
    //   474: aload 7
    //   476: astore 6
    //   478: aload 10
    //   480: invokeinterface 181 1 0
    //   485: astore 12
    //   487: aload 7
    //   489: astore 6
    //   491: aload 10
    //   493: invokeinterface 178 1 0
    //   498: pop
    //   499: aload 7
    //   501: astore 6
    //   503: aload 10
    //   505: invokeinterface 178 1 0
    //   510: pop
    //   511: aload 7
    //   513: astore 6
    //   515: aload 10
    //   517: invokeinterface 181 1 0
    //   522: astore 13
    //   524: aload 7
    //   526: astore 6
    //   528: aload 10
    //   530: invokeinterface 178 1 0
    //   535: pop
    //   536: aload 7
    //   538: astore 6
    //   540: aload 10
    //   542: invokeinterface 178 1 0
    //   547: pop
    //   548: aload 7
    //   550: astore 6
    //   552: aload 10
    //   554: invokeinterface 181 1 0
    //   559: astore 11
    //   561: aload 7
    //   563: astore 6
    //   565: aload_0
    //   566: aload 12
    //   568: aload 13
    //   570: invokespecial 98	com/xiaomi/smack/provider/ProviderManager:getProviderKey	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   573: astore 12
    //   575: aload 7
    //   577: astore 6
    //   579: aload_0
    //   580: getfield 19	com/xiaomi/smack/provider/ProviderManager:extensionProviders	Ljava/util/Map;
    //   583: aload 12
    //   585: invokeinterface 184 2 0
    //   590: istore 5
    //   592: iload 5
    //   594: ifne -262 -> 332
    //   597: aload 7
    //   599: astore 6
    //   601: aload 11
    //   603: invokestatic 188	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   606: astore 11
    //   608: aload 7
    //   610: astore 6
    //   612: ldc 89
    //   614: aload 11
    //   616: invokevirtual 194	java/lang/Class:isAssignableFrom	(Ljava/lang/Class;)Z
    //   619: ifeq +41 -> 660
    //   622: aload 7
    //   624: astore 6
    //   626: aload_0
    //   627: getfield 19	com/xiaomi/smack/provider/ProviderManager:extensionProviders	Ljava/util/Map;
    //   630: aload 12
    //   632: aload 11
    //   634: invokevirtual 196	java/lang/Class:newInstance	()Ljava/lang/Object;
    //   637: invokeinterface 104 3 0
    //   642: pop
    //   643: goto -311 -> 332
    //   646: astore 11
    //   648: aload 7
    //   650: astore 6
    //   652: aload 11
    //   654: invokevirtual 206	java/lang/ClassNotFoundException:printStackTrace	()V
    //   657: goto -325 -> 332
    //   660: aload 7
    //   662: astore 6
    //   664: ldc -45
    //   666: aload 11
    //   668: invokevirtual 194	java/lang/Class:isAssignableFrom	(Ljava/lang/Class;)Z
    //   671: ifeq -339 -> 332
    //   674: aload 7
    //   676: astore 6
    //   678: aload_0
    //   679: getfield 19	com/xiaomi/smack/provider/ProviderManager:extensionProviders	Ljava/util/Map;
    //   682: aload 12
    //   684: aload 11
    //   686: invokeinterface 104 3 0
    //   691: pop
    //   692: goto -360 -> 332
    //   695: iload_1
    //   696: iconst_1
    //   697: iadd
    //   698: istore_1
    //   699: goto -686 -> 13
    //   702: astore 6
    //   704: goto -284 -> 420
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	707	0	this	ProviderManager
    //   12	687	1	i	int
    //   114	232	2	j	int
    //   343	6	3	k	int
    //   9	8	4	m	int
    //   279	314	5	bool	boolean
    //   53	282	6	localObject1	Object
    //   359	1	6	localException1	Exception
    //   366	50	6	localObject2	Object
    //   423	3	6	localException2	Exception
    //   433	244	6	localObject3	Object
    //   702	1	6	localException3	Exception
    //   50	352	7	localObject4	Object
    //   413	262	7	localObject5	Object
    //   4	16	8	arrayOfClassLoader	ClassLoader[]
    //   28	13	9	localEnumeration	java.util.Enumeration
    //   72	481	10	localXmlPullParser	org.xmlpull.v1.XmlPullParser
    //   248	141	11	localObject6	Object
    //   399	7	11	localClassNotFoundException1	ClassNotFoundException
    //   559	74	11	localObject7	Object
    //   646	39	11	localClassNotFoundException2	ClassNotFoundException
    //   174	509	12	str1	String
    //   211	358	13	str2	String
    // Exception table:
    //   from	to	target	type
    //   351	356	359	java/lang/Exception
    //   290	297	399	java/lang/ClassNotFoundException
    //   301	311	399	java/lang/ClassNotFoundException
    //   315	332	399	java/lang/ClassNotFoundException
    //   368	378	399	java/lang/ClassNotFoundException
    //   382	396	399	java/lang/ClassNotFoundException
    //   55	62	413	finally
    //   66	74	413	finally
    //   78	88	413	finally
    //   92	103	413	finally
    //   107	115	413	finally
    //   124	139	413	finally
    //   143	151	413	finally
    //   155	163	413	finally
    //   167	176	413	finally
    //   180	188	413	finally
    //   192	200	413	finally
    //   204	213	413	finally
    //   217	225	413	finally
    //   229	237	413	finally
    //   241	250	413	finally
    //   254	264	413	finally
    //   268	281	413	finally
    //   290	297	413	finally
    //   301	311	413	finally
    //   315	332	413	finally
    //   336	344	413	finally
    //   368	378	413	finally
    //   382	396	413	finally
    //   405	410	413	finally
    //   435	450	413	finally
    //   454	462	413	finally
    //   466	474	413	finally
    //   478	487	413	finally
    //   491	499	413	finally
    //   503	511	413	finally
    //   515	524	413	finally
    //   528	536	413	finally
    //   540	548	413	finally
    //   552	561	413	finally
    //   565	575	413	finally
    //   579	592	413	finally
    //   601	608	413	finally
    //   612	622	413	finally
    //   626	643	413	finally
    //   652	657	413	finally
    //   664	674	413	finally
    //   678	692	413	finally
    //   0	11	423	java/lang/Exception
    //   19	30	423	java/lang/Exception
    //   30	52	423	java/lang/Exception
    //   420	423	423	java/lang/Exception
    //   601	608	646	java/lang/ClassNotFoundException
    //   612	622	646	java/lang/ClassNotFoundException
    //   626	643	646	java/lang/ClassNotFoundException
    //   664	674	646	java/lang/ClassNotFoundException
    //   678	692	646	java/lang/ClassNotFoundException
    //   415	420	702	java/lang/Exception
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smack.provider.ProviderManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */