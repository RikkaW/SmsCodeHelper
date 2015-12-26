package com.android.mms.jwap_port;

import java.util.Hashtable;

public class TransTable
{
  private static Hashtable tables = new Hashtable();
  private Hashtable mib2str = new Hashtable();
  private Hashtable str2mib = new Hashtable();
  
  /* Error */
  public static TransTable getTable(android.content.Context paramContext, String paramString)
    throws java.lang.RuntimeException
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +5 -> 6
    //   4: aconst_null
    //   5: areturn
    //   6: getstatic 17	com/android/mms/jwap_port/TransTable:tables	Ljava/util/Hashtable;
    //   9: aload_1
    //   10: invokevirtual 35	java/util/Hashtable:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   13: astore_3
    //   14: aload_3
    //   15: ifnonnull +427 -> 442
    //   18: getstatic 17	com/android/mms/jwap_port/TransTable:tables	Ljava/util/Hashtable;
    //   21: astore 6
    //   23: aload 6
    //   25: monitorenter
    //   26: getstatic 17	com/android/mms/jwap_port/TransTable:tables	Ljava/util/Hashtable;
    //   29: aload_1
    //   30: invokevirtual 35	java/util/Hashtable:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   33: astore 4
    //   35: aload 4
    //   37: astore_3
    //   38: aload 4
    //   40: ifnonnull +356 -> 396
    //   43: new 2	com/android/mms/jwap_port/TransTable
    //   46: dup
    //   47: invokespecial 36	com/android/mms/jwap_port/TransTable:<init>	()V
    //   50: astore 4
    //   52: aconst_null
    //   53: astore_3
    //   54: aload_0
    //   55: invokevirtual 42	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   58: aload_1
    //   59: invokevirtual 48	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   62: astore_0
    //   63: aload_0
    //   64: ifnonnull +57 -> 121
    //   67: getstatic 17	com/android/mms/jwap_port/TransTable:tables	Ljava/util/Hashtable;
    //   70: aload_1
    //   71: iconst_0
    //   72: newarray <illegal type>
    //   74: invokevirtual 52	java/util/Hashtable:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   77: pop
    //   78: new 27	java/lang/RuntimeException
    //   81: dup
    //   82: new 54	java/lang/StringBuilder
    //   85: dup
    //   86: invokespecial 55	java/lang/StringBuilder:<init>	()V
    //   89: aload_1
    //   90: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   93: ldc 61
    //   95: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   98: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   101: invokespecial 68	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
    //   104: athrow
    //   105: astore_0
    //   106: aload 6
    //   108: monitorexit
    //   109: aload_0
    //   110: athrow
    //   111: astore_0
    //   112: aload_0
    //   113: invokevirtual 71	java/io/IOException:printStackTrace	()V
    //   116: aload_3
    //   117: astore_0
    //   118: goto -55 -> 63
    //   121: new 73	java/util/Properties
    //   124: dup
    //   125: invokespecial 74	java/util/Properties:<init>	()V
    //   128: astore 7
    //   130: aload 7
    //   132: aload_0
    //   133: invokevirtual 78	java/util/Properties:load	(Ljava/io/InputStream;)V
    //   136: aload_0
    //   137: invokevirtual 83	java/io/InputStream:close	()V
    //   140: getstatic 17	com/android/mms/jwap_port/TransTable:tables	Ljava/util/Hashtable;
    //   143: aload_1
    //   144: aload 4
    //   146: invokevirtual 52	java/util/Hashtable:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   149: pop
    //   150: aload 4
    //   152: checkcast 2	com/android/mms/jwap_port/TransTable
    //   155: astore 8
    //   157: aload 7
    //   159: ldc 85
    //   161: invokevirtual 89	java/util/Properties:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   164: invokestatic 95	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   167: invokevirtual 99	java/lang/Boolean:booleanValue	()Z
    //   170: istore_2
    //   171: aload 7
    //   173: ldc 101
    //   175: ldc 103
    //   177: invokevirtual 106	java/util/Properties:getProperty	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   180: astore 9
    //   182: aload 7
    //   184: invokevirtual 110	java/util/Properties:keys	()Ljava/util/Enumeration;
    //   187: astore 10
    //   189: aload 4
    //   191: astore_3
    //   192: aload 10
    //   194: invokeinterface 115 1 0
    //   199: ifeq +197 -> 396
    //   202: aload 10
    //   204: invokeinterface 119 1 0
    //   209: checkcast 121	java/lang/String
    //   212: astore 11
    //   214: ldc 85
    //   216: aload 11
    //   218: invokevirtual 125	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   221: ifne -32 -> 189
    //   224: ldc 101
    //   226: aload 11
    //   228: invokevirtual 125	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   231: ifne -42 -> 189
    //   234: aload 7
    //   236: aload 11
    //   238: invokevirtual 89	java/util/Properties:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   241: invokevirtual 128	java/lang/String:trim	()Ljava/lang/String;
    //   244: astore 5
    //   246: aconst_null
    //   247: astore_0
    //   248: aload 5
    //   250: astore_3
    //   251: iload_2
    //   252: ifeq +23 -> 275
    //   255: new 130	java/util/StringTokenizer
    //   258: dup
    //   259: aload 5
    //   261: aload 9
    //   263: invokespecial 133	java/util/StringTokenizer:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   266: astore_0
    //   267: aload_0
    //   268: invokevirtual 136	java/util/StringTokenizer:nextToken	()Ljava/lang/String;
    //   271: invokevirtual 128	java/lang/String:trim	()Ljava/lang/String;
    //   274: astore_3
    //   275: aload 11
    //   277: invokestatic 142	java/lang/Integer:decode	(Ljava/lang/String;)Ljava/lang/Integer;
    //   280: astore 5
    //   282: aload 8
    //   284: getfield 21	com/android/mms/jwap_port/TransTable:mib2str	Ljava/util/Hashtable;
    //   287: aload 5
    //   289: aload_3
    //   290: invokevirtual 52	java/util/Hashtable:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   293: pop
    //   294: aload 8
    //   296: getfield 23	com/android/mms/jwap_port/TransTable:str2mib	Ljava/util/Hashtable;
    //   299: aload_3
    //   300: invokevirtual 145	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   303: aload 5
    //   305: invokevirtual 52	java/util/Hashtable:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   308: pop
    //   309: iload_2
    //   310: ifeq -121 -> 189
    //   313: aload_0
    //   314: invokevirtual 148	java/util/StringTokenizer:hasMoreTokens	()Z
    //   317: ifeq -128 -> 189
    //   320: aload_0
    //   321: invokevirtual 136	java/util/StringTokenizer:nextToken	()Ljava/lang/String;
    //   324: invokevirtual 128	java/lang/String:trim	()Ljava/lang/String;
    //   327: invokevirtual 145	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   330: astore_3
    //   331: aload 8
    //   333: getfield 23	com/android/mms/jwap_port/TransTable:str2mib	Ljava/util/Hashtable;
    //   336: aload_3
    //   337: aload 5
    //   339: invokevirtual 52	java/util/Hashtable:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   342: pop
    //   343: goto -30 -> 313
    //   346: astore_0
    //   347: goto -158 -> 189
    //   350: astore_3
    //   351: aload_0
    //   352: invokevirtual 83	java/io/InputStream:close	()V
    //   355: aload_3
    //   356: athrow
    //   357: astore_0
    //   358: getstatic 17	com/android/mms/jwap_port/TransTable:tables	Ljava/util/Hashtable;
    //   361: aload_1
    //   362: iconst_0
    //   363: newarray <illegal type>
    //   365: invokevirtual 52	java/util/Hashtable:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   368: pop
    //   369: new 27	java/lang/RuntimeException
    //   372: dup
    //   373: new 54	java/lang/StringBuilder
    //   376: dup
    //   377: invokespecial 55	java/lang/StringBuilder:<init>	()V
    //   380: aload_1
    //   381: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   384: ldc 61
    //   386: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   389: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   392: invokespecial 68	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
    //   395: athrow
    //   396: aload 6
    //   398: monitorexit
    //   399: aload_3
    //   400: ifnull +37 -> 437
    //   403: aload_3
    //   404: instanceof 2
    //   407: ifne +30 -> 437
    //   410: new 27	java/lang/RuntimeException
    //   413: dup
    //   414: new 54	java/lang/StringBuilder
    //   417: dup
    //   418: invokespecial 55	java/lang/StringBuilder:<init>	()V
    //   421: aload_1
    //   422: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   425: ldc -106
    //   427: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   430: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   433: invokespecial 68	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
    //   436: athrow
    //   437: aload_3
    //   438: checkcast 2	com/android/mms/jwap_port/TransTable
    //   441: areturn
    //   442: goto -43 -> 399
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	445	0	paramContext	android.content.Context
    //   0	445	1	paramString	String
    //   170	140	2	bool	boolean
    //   13	324	3	localObject1	Object
    //   350	88	3	localObject2	Object
    //   33	157	4	localObject3	Object
    //   244	94	5	localObject4	Object
    //   21	376	6	localHashtable	Hashtable
    //   128	107	7	localProperties	java.util.Properties
    //   155	177	8	localTransTable	TransTable
    //   180	82	9	str1	String
    //   187	16	10	localEnumeration	java.util.Enumeration
    //   212	64	11	str2	String
    // Exception table:
    //   from	to	target	type
    //   26	35	105	finally
    //   43	52	105	finally
    //   54	63	105	finally
    //   67	105	105	finally
    //   106	109	105	finally
    //   112	116	105	finally
    //   121	130	105	finally
    //   136	140	105	finally
    //   140	189	105	finally
    //   192	246	105	finally
    //   255	275	105	finally
    //   275	309	105	finally
    //   313	343	105	finally
    //   351	357	105	finally
    //   358	396	105	finally
    //   396	399	105	finally
    //   54	63	111	java/io/IOException
    //   275	309	346	java/lang/NumberFormatException
    //   313	343	346	java/lang/NumberFormatException
    //   130	136	350	finally
    //   136	140	357	java/io/IOException
    //   351	357	357	java/io/IOException
  }
  
  public String code2str(int paramInt)
  {
    return (String)mib2str.get(new Integer(paramInt));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.jwap_port.TransTable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */