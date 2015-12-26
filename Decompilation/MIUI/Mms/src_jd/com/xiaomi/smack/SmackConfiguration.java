package com.xiaomi.smack;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import org.xmlpull.v1.XmlPullParser;

public final class SmackConfiguration
{
  private static Vector<String> defaultMechs;
  private static int keepAliveInterval;
  private static int packetReplyTimeout = 5000;
  private static int pingInterval;
  private static int serverShutdownTimeout;
  
  /* Error */
  static
  {
    // Byte code:
    //   0: sipush 5000
    //   3: putstatic 18	com/xiaomi/smack/SmackConfiguration:packetReplyTimeout	I
    //   6: ldc 19
    //   8: putstatic 21	com/xiaomi/smack/SmackConfiguration:keepAliveInterval	I
    //   11: ldc 22
    //   13: putstatic 24	com/xiaomi/smack/SmackConfiguration:pingInterval	I
    //   16: ldc 19
    //   18: putstatic 26	com/xiaomi/smack/SmackConfiguration:serverShutdownTimeout	I
    //   21: new 28	java/util/Vector
    //   24: dup
    //   25: invokespecial 31	java/util/Vector:<init>	()V
    //   28: putstatic 33	com/xiaomi/smack/SmackConfiguration:defaultMechs	Ljava/util/Vector;
    //   31: invokestatic 37	com/xiaomi/smack/SmackConfiguration:getClassLoaders	()[Ljava/lang/ClassLoader;
    //   34: astore 7
    //   36: aload 7
    //   38: arraylength
    //   39: istore_3
    //   40: iconst_0
    //   41: istore_0
    //   42: iload_0
    //   43: iload_3
    //   44: if_icmpge +326 -> 370
    //   47: aload 7
    //   49: iload_0
    //   50: aaload
    //   51: ldc 39
    //   53: invokevirtual 45	java/lang/ClassLoader:getResources	(Ljava/lang/String;)Ljava/util/Enumeration;
    //   56: astore 8
    //   58: aload 8
    //   60: invokeinterface 51 1 0
    //   65: ifeq +354 -> 419
    //   68: aload 8
    //   70: invokeinterface 55 1 0
    //   75: checkcast 57	java/net/URL
    //   78: astore 6
    //   80: aconst_null
    //   81: astore 5
    //   83: aconst_null
    //   84: astore 4
    //   86: aload 6
    //   88: invokevirtual 61	java/net/URL:openStream	()Ljava/io/InputStream;
    //   91: astore 6
    //   93: aload 6
    //   95: astore 4
    //   97: aload 6
    //   99: astore 5
    //   101: invokestatic 67	org/xmlpull/v1/XmlPullParserFactory:newInstance	()Lorg/xmlpull/v1/XmlPullParserFactory;
    //   104: invokevirtual 71	org/xmlpull/v1/XmlPullParserFactory:newPullParser	()Lorg/xmlpull/v1/XmlPullParser;
    //   107: astore 9
    //   109: aload 6
    //   111: astore 4
    //   113: aload 6
    //   115: astore 5
    //   117: aload 9
    //   119: ldc 73
    //   121: iconst_1
    //   122: invokeinterface 79 3 0
    //   127: aload 6
    //   129: astore 4
    //   131: aload 6
    //   133: astore 5
    //   135: aload 9
    //   137: aload 6
    //   139: ldc 81
    //   141: invokeinterface 85 3 0
    //   146: aload 6
    //   148: astore 4
    //   150: aload 6
    //   152: astore 5
    //   154: aload 9
    //   156: invokeinterface 89 1 0
    //   161: istore_1
    //   162: iload_1
    //   163: iconst_2
    //   164: if_icmpne +39 -> 203
    //   167: aload 6
    //   169: astore 4
    //   171: aload 6
    //   173: astore 5
    //   175: aload 9
    //   177: invokeinterface 93 1 0
    //   182: ldc 95
    //   184: invokevirtual 101	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   187: ifeq +52 -> 239
    //   190: aload 6
    //   192: astore 4
    //   194: aload 6
    //   196: astore 5
    //   198: aload 9
    //   200: invokestatic 105	com/xiaomi/smack/SmackConfiguration:parseClassToLoad	(Lorg/xmlpull/v1/XmlPullParser;)V
    //   203: aload 6
    //   205: astore 4
    //   207: aload 6
    //   209: astore 5
    //   211: aload 9
    //   213: invokeinterface 108 1 0
    //   218: istore_2
    //   219: iload_2
    //   220: istore_1
    //   221: iload_2
    //   222: iconst_1
    //   223: if_icmpne -61 -> 162
    //   226: aload 6
    //   228: invokevirtual 113	java/io/InputStream:close	()V
    //   231: goto -173 -> 58
    //   234: astore 4
    //   236: goto -178 -> 58
    //   239: aload 6
    //   241: astore 4
    //   243: aload 6
    //   245: astore 5
    //   247: aload 9
    //   249: invokeinterface 93 1 0
    //   254: ldc 114
    //   256: invokevirtual 101	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   259: ifeq +49 -> 308
    //   262: aload 6
    //   264: astore 4
    //   266: aload 6
    //   268: astore 5
    //   270: aload 9
    //   272: getstatic 18	com/xiaomi/smack/SmackConfiguration:packetReplyTimeout	I
    //   275: invokestatic 118	com/xiaomi/smack/SmackConfiguration:parseIntProperty	(Lorg/xmlpull/v1/XmlPullParser;I)I
    //   278: putstatic 18	com/xiaomi/smack/SmackConfiguration:packetReplyTimeout	I
    //   281: goto -78 -> 203
    //   284: astore 6
    //   286: aload 4
    //   288: astore 5
    //   290: aload 6
    //   292: invokevirtual 121	java/lang/Exception:printStackTrace	()V
    //   295: aload 4
    //   297: invokevirtual 113	java/io/InputStream:close	()V
    //   300: goto -242 -> 58
    //   303: astore 4
    //   305: goto -247 -> 58
    //   308: aload 6
    //   310: astore 4
    //   312: aload 6
    //   314: astore 5
    //   316: aload 9
    //   318: invokeinterface 93 1 0
    //   323: ldc 122
    //   325: invokevirtual 101	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   328: ifeq +43 -> 371
    //   331: aload 6
    //   333: astore 4
    //   335: aload 6
    //   337: astore 5
    //   339: aload 9
    //   341: getstatic 21	com/xiaomi/smack/SmackConfiguration:keepAliveInterval	I
    //   344: invokestatic 118	com/xiaomi/smack/SmackConfiguration:parseIntProperty	(Lorg/xmlpull/v1/XmlPullParser;I)I
    //   347: putstatic 21	com/xiaomi/smack/SmackConfiguration:keepAliveInterval	I
    //   350: goto -147 -> 203
    //   353: astore 4
    //   355: aload 5
    //   357: invokevirtual 113	java/io/InputStream:close	()V
    //   360: aload 4
    //   362: athrow
    //   363: astore 4
    //   365: aload 4
    //   367: invokevirtual 121	java/lang/Exception:printStackTrace	()V
    //   370: return
    //   371: aload 6
    //   373: astore 4
    //   375: aload 6
    //   377: astore 5
    //   379: aload 9
    //   381: invokeinterface 93 1 0
    //   386: ldc 124
    //   388: invokevirtual 101	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   391: ifeq -188 -> 203
    //   394: aload 6
    //   396: astore 4
    //   398: aload 6
    //   400: astore 5
    //   402: getstatic 33	com/xiaomi/smack/SmackConfiguration:defaultMechs	Ljava/util/Vector;
    //   405: aload 9
    //   407: invokeinterface 127 1 0
    //   412: invokevirtual 130	java/util/Vector:add	(Ljava/lang/Object;)Z
    //   415: pop
    //   416: goto -213 -> 203
    //   419: iload_0
    //   420: iconst_1
    //   421: iadd
    //   422: istore_0
    //   423: goto -381 -> 42
    //   426: astore 5
    //   428: goto -68 -> 360
    // Local variable table:
    //   start	length	slot	name	signature
    //   41	382	0	i	int
    //   161	60	1	j	int
    //   218	6	2	k	int
    //   39	6	3	m	int
    //   84	122	4	localObject1	Object
    //   234	1	4	localException1	Exception
    //   241	55	4	localObject2	Object
    //   303	1	4	localException2	Exception
    //   310	24	4	localException3	Exception
    //   353	8	4	localObject3	Object
    //   363	3	4	localException4	Exception
    //   373	24	4	localException5	Exception
    //   81	320	5	localObject4	Object
    //   426	1	5	localException6	Exception
    //   78	189	6	localObject5	Object
    //   284	115	6	localException7	Exception
    //   34	14	7	arrayOfClassLoader	ClassLoader[]
    //   56	13	8	localEnumeration	java.util.Enumeration
    //   107	299	9	localXmlPullParser	XmlPullParser
    // Exception table:
    //   from	to	target	type
    //   226	231	234	java/lang/Exception
    //   86	93	284	java/lang/Exception
    //   101	109	284	java/lang/Exception
    //   117	127	284	java/lang/Exception
    //   135	146	284	java/lang/Exception
    //   154	162	284	java/lang/Exception
    //   175	190	284	java/lang/Exception
    //   198	203	284	java/lang/Exception
    //   211	219	284	java/lang/Exception
    //   247	262	284	java/lang/Exception
    //   270	281	284	java/lang/Exception
    //   316	331	284	java/lang/Exception
    //   339	350	284	java/lang/Exception
    //   379	394	284	java/lang/Exception
    //   402	416	284	java/lang/Exception
    //   295	300	303	java/lang/Exception
    //   86	93	353	finally
    //   101	109	353	finally
    //   117	127	353	finally
    //   135	146	353	finally
    //   154	162	353	finally
    //   175	190	353	finally
    //   198	203	353	finally
    //   211	219	353	finally
    //   247	262	353	finally
    //   270	281	353	finally
    //   290	295	353	finally
    //   316	331	353	finally
    //   339	350	353	finally
    //   379	394	353	finally
    //   402	416	353	finally
    //   31	40	363	java/lang/Exception
    //   47	58	363	java/lang/Exception
    //   58	80	363	java/lang/Exception
    //   360	363	363	java/lang/Exception
    //   355	360	426	java/lang/Exception
  }
  
  public static int getCheckAliveInterval()
  {
    return keepAliveInterval;
  }
  
  private static ClassLoader[] getClassLoaders()
  {
    int i = 0;
    ClassLoader[] arrayOfClassLoader = new ClassLoader[2];
    arrayOfClassLoader[0] = SmackConfiguration.class.getClassLoader();
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
  
  public static int getPingInteval()
  {
    return pingInterval;
  }
  
  public static String getVersion()
  {
    return "3.1.0";
  }
  
  private static void parseClassToLoad(XmlPullParser paramXmlPullParser)
    throws Exception
  {
    paramXmlPullParser = paramXmlPullParser.nextText();
    try
    {
      Class.forName(paramXmlPullParser);
      return;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      System.err.println("Error! A startup class specified in smack-config.xml could not be loaded: " + paramXmlPullParser);
    }
  }
  
  private static int parseIntProperty(XmlPullParser paramXmlPullParser, int paramInt)
    throws Exception
  {
    try
    {
      int i = Integer.parseInt(paramXmlPullParser.nextText());
      return i;
    }
    catch (NumberFormatException paramXmlPullParser)
    {
      paramXmlPullParser.printStackTrace();
    }
    return paramInt;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smack.SmackConfiguration
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */