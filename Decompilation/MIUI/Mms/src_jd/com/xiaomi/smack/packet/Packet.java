package com.xiaomi.smack.packet;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import com.xiaomi.smack.util.StringUtils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Packet
{
  protected static final String DEFAULT_LANGUAGE = Locale.getDefault().getLanguage().toLowerCase();
  private static String DEFAULT_XML_NS = null;
  public static final DateFormat XEP_0082_UTC_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
  private static long id = 0L;
  private static String prefix;
  private String chId = null;
  private XMPPError error = null;
  private String from = null;
  private String packageName = null;
  private List<CommonPacketExtension> packetExtensions = new CopyOnWriteArrayList();
  private String packetID = null;
  private final Map<String, Object> properties = new HashMap();
  private String to = null;
  private String xmlns = DEFAULT_XML_NS;
  
  static
  {
    XEP_0082_UTC_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
    prefix = StringUtils.randomString(5) + "-";
  }
  
  public Packet() {}
  
  public Packet(Bundle paramBundle)
  {
    to = paramBundle.getString("ext_to");
    from = paramBundle.getString("ext_from");
    chId = paramBundle.getString("ext_chid");
    packetID = paramBundle.getString("ext_pkt_id");
    Parcelable[] arrayOfParcelable = paramBundle.getParcelableArray("ext_exts");
    if (arrayOfParcelable != null)
    {
      packetExtensions = new ArrayList(arrayOfParcelable.length);
      int j = arrayOfParcelable.length;
      int i = 0;
      while (i < j)
      {
        CommonPacketExtension localCommonPacketExtension = CommonPacketExtension.parseFromBundle((Bundle)arrayOfParcelable[i]);
        if (localCommonPacketExtension != null) {
          packetExtensions.add(localCommonPacketExtension);
        }
        i += 1;
      }
    }
    paramBundle = paramBundle.getBundle("ext_ERROR");
    if (paramBundle != null) {
      error = new XMPPError(paramBundle);
    }
  }
  
  public static String getDefaultLanguage()
  {
    return DEFAULT_LANGUAGE;
  }
  
  public static String nextID()
  {
    try
    {
      Object localObject1 = new StringBuilder().append(prefix);
      long l = id;
      id = 1L + l;
      localObject1 = Long.toString(l);
      return (String)localObject1;
    }
    finally
    {
      localObject2 = finally;
      throw ((Throwable)localObject2);
    }
  }
  
  public void addExtension(CommonPacketExtension paramCommonPacketExtension)
  {
    packetExtensions.add(paramCommonPacketExtension);
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = true;
    boolean bool3 = false;
    if (this == paramObject) {
      bool1 = true;
    }
    label88:
    label129:
    label153:
    label177:
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    do
                    {
                      return bool1;
                      bool1 = bool3;
                    } while (paramObject == null);
                    bool1 = bool3;
                  } while (getClass() != paramObject.getClass());
                  paramObject = (Packet)paramObject;
                  if (error == null) {
                    break;
                  }
                  bool1 = bool3;
                } while (!error.equals(error));
                if (from == null) {
                  break label237;
                }
                bool1 = bool3;
              } while (!from.equals(from));
              bool1 = bool3;
            } while (!packetExtensions.equals(packetExtensions));
            if (packetID == null) {
              break label246;
            }
            bool1 = bool3;
          } while (!packetID.equals(packetID));
          if (chId == null) {
            break label255;
          }
          bool1 = bool3;
        } while (!chId.equals(chId));
        if (properties == null) {
          break label264;
        }
        bool1 = bool3;
      } while (!properties.equals(properties));
      if (to == null) {
        break label273;
      }
      bool1 = bool3;
    } while (!to.equals(to));
    label201:
    if (xmlns != null)
    {
      bool1 = bool2;
      if (xmlns.equals(xmlns)) {}
    }
    label224:
    for (boolean bool1 = false;; bool1 = bool2)
    {
      return bool1;
      if (error == null) {
        break;
      }
      return false;
      label237:
      if (from == null) {
        break label88;
      }
      return false;
      label246:
      if (packetID == null) {
        break label129;
      }
      return false;
      label255:
      if (chId == null) {
        break label153;
      }
      return false;
      label264:
      if (properties == null) {
        break label177;
      }
      return false;
      label273:
      if (to == null) {
        break label201;
      }
      return false;
      if (xmlns != null) {
        break label224;
      }
    }
  }
  
  public String getChannelId()
  {
    return chId;
  }
  
  public XMPPError getError()
  {
    return error;
  }
  
  public CommonPacketExtension getExtension(String paramString)
  {
    return getExtension(paramString, null);
  }
  
  public CommonPacketExtension getExtension(String paramString1, String paramString2)
  {
    Iterator localIterator = packetExtensions.iterator();
    while (localIterator.hasNext())
    {
      CommonPacketExtension localCommonPacketExtension = (CommonPacketExtension)localIterator.next();
      if (((paramString2 == null) || (paramString2.equals(localCommonPacketExtension.getNamespace()))) && (paramString1.equals(localCommonPacketExtension.getElementName()))) {
        return localCommonPacketExtension;
      }
    }
    return null;
  }
  
  /* Error */
  public java.util.Collection<CommonPacketExtension> getExtensions()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 113	com/xiaomi/smack/packet/Packet:packetExtensions	Ljava/util/List;
    //   6: ifnonnull +11 -> 17
    //   9: invokestatic 221	java/util/Collections:emptyList	()Ljava/util/List;
    //   12: astore_1
    //   13: aload_0
    //   14: monitorexit
    //   15: aload_1
    //   16: areturn
    //   17: new 143	java/util/ArrayList
    //   20: dup
    //   21: aload_0
    //   22: getfield 113	com/xiaomi/smack/packet/Packet:packetExtensions	Ljava/util/List;
    //   25: invokespecial 224	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   28: invokestatic 228	java/util/Collections:unmodifiableList	(Ljava/util/List;)Ljava/util/List;
    //   31: astore_1
    //   32: goto -19 -> 13
    //   35: astore_1
    //   36: aload_0
    //   37: monitorexit
    //   38: aload_1
    //   39: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	40	0	this	Packet
    //   12	20	1	localList	List
    //   35	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	13	35	finally
    //   17	32	35	finally
  }
  
  /* Error */
  protected String getExtensionsXML()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new 73	java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial 75	java/lang/StringBuilder:<init>	()V
    //   9: astore 7
    //   11: aload_0
    //   12: invokevirtual 235	com/xiaomi/smack/packet/Packet:getExtensions	()Ljava/util/Collection;
    //   15: invokeinterface 238 1 0
    //   20: astore_1
    //   21: aload_1
    //   22: invokeinterface 203 1 0
    //   27: ifeq +31 -> 58
    //   30: aload 7
    //   32: aload_1
    //   33: invokeinterface 207 1 0
    //   38: checkcast 148	com/xiaomi/smack/packet/CommonPacketExtension
    //   41: invokeinterface 243 1 0
    //   46: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   49: pop
    //   50: goto -29 -> 21
    //   53: astore_1
    //   54: aload_0
    //   55: monitorexit
    //   56: aload_1
    //   57: athrow
    //   58: aload_0
    //   59: getfield 118	com/xiaomi/smack/packet/Packet:properties	Ljava/util/Map;
    //   62: ifnull +464 -> 526
    //   65: aload_0
    //   66: getfield 118	com/xiaomi/smack/packet/Packet:properties	Ljava/util/Map;
    //   69: invokeinterface 248 1 0
    //   74: ifne +452 -> 526
    //   77: aload 7
    //   79: ldc -6
    //   81: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: pop
    //   85: aload_0
    //   86: invokevirtual 253	com/xiaomi/smack/packet/Packet:getPropertyNames	()Ljava/util/Collection;
    //   89: invokeinterface 238 1 0
    //   94: astore 8
    //   96: aload 8
    //   98: invokeinterface 203 1 0
    //   103: ifeq +414 -> 517
    //   106: aload 8
    //   108: invokeinterface 207 1 0
    //   113: checkcast 40	java/lang/String
    //   116: astore_1
    //   117: aload_0
    //   118: aload_1
    //   119: invokevirtual 257	com/xiaomi/smack/packet/Packet:getProperty	(Ljava/lang/String;)Ljava/lang/Object;
    //   122: astore 9
    //   124: aload 7
    //   126: ldc_w 259
    //   129: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   132: pop
    //   133: aload 7
    //   135: ldc_w 261
    //   138: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   141: aload_1
    //   142: invokestatic 264	com/xiaomi/smack/util/StringUtils:escapeForXML	(Ljava/lang/String;)Ljava/lang/String;
    //   145: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   148: ldc_w 266
    //   151: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   154: pop
    //   155: aload 7
    //   157: ldc_w 268
    //   160: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   163: pop
    //   164: aload 9
    //   166: instanceof 270
    //   169: ifeq +35 -> 204
    //   172: aload 7
    //   174: ldc_w 272
    //   177: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   180: aload 9
    //   182: invokevirtual 275	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   185: ldc_w 277
    //   188: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   191: pop
    //   192: aload 7
    //   194: ldc_w 279
    //   197: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   200: pop
    //   201: goto -105 -> 96
    //   204: aload 9
    //   206: instanceof 172
    //   209: ifeq +26 -> 235
    //   212: aload 7
    //   214: ldc_w 281
    //   217: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   220: aload 9
    //   222: invokevirtual 275	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   225: ldc_w 277
    //   228: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   231: pop
    //   232: goto -40 -> 192
    //   235: aload 9
    //   237: instanceof 283
    //   240: ifeq +26 -> 266
    //   243: aload 7
    //   245: ldc_w 285
    //   248: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   251: aload 9
    //   253: invokevirtual 275	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   256: ldc_w 277
    //   259: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   262: pop
    //   263: goto -71 -> 192
    //   266: aload 9
    //   268: instanceof 287
    //   271: ifeq +26 -> 297
    //   274: aload 7
    //   276: ldc_w 289
    //   279: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   282: aload 9
    //   284: invokevirtual 275	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   287: ldc_w 277
    //   290: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   293: pop
    //   294: goto -102 -> 192
    //   297: aload 9
    //   299: instanceof 291
    //   302: ifeq +26 -> 328
    //   305: aload 7
    //   307: ldc_w 293
    //   310: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   313: aload 9
    //   315: invokevirtual 275	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   318: ldc_w 277
    //   321: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   324: pop
    //   325: goto -133 -> 192
    //   328: aload 9
    //   330: instanceof 40
    //   333: ifeq +38 -> 371
    //   336: aload 7
    //   338: ldc_w 295
    //   341: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   344: pop
    //   345: aload 7
    //   347: aload 9
    //   349: checkcast 40	java/lang/String
    //   352: invokestatic 264	com/xiaomi/smack/util/StringUtils:escapeForXML	(Ljava/lang/String;)Ljava/lang/String;
    //   355: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   358: pop
    //   359: aload 7
    //   361: ldc_w 277
    //   364: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   367: pop
    //   368: goto -176 -> 192
    //   371: aconst_null
    //   372: astore_2
    //   373: aconst_null
    //   374: astore 6
    //   376: aconst_null
    //   377: astore_3
    //   378: aconst_null
    //   379: astore 4
    //   381: aconst_null
    //   382: astore 5
    //   384: new 297	java/io/ByteArrayOutputStream
    //   387: dup
    //   388: invokespecial 298	java/io/ByteArrayOutputStream:<init>	()V
    //   391: astore_1
    //   392: new 300	java/io/ObjectOutputStream
    //   395: dup
    //   396: aload_1
    //   397: invokespecial 303	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   400: astore_2
    //   401: aload_2
    //   402: aload 9
    //   404: invokevirtual 307	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   407: aload 7
    //   409: ldc_w 309
    //   412: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   415: pop
    //   416: aload 7
    //   418: aload_1
    //   419: invokevirtual 313	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   422: invokestatic 317	com/xiaomi/smack/util/StringUtils:encodeBase64	([B)Ljava/lang/String;
    //   425: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   428: ldc_w 277
    //   431: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   434: pop
    //   435: aload_2
    //   436: ifnull +7 -> 443
    //   439: aload_2
    //   440: invokevirtual 320	java/io/ObjectOutputStream:close	()V
    //   443: aload_1
    //   444: ifnull -252 -> 192
    //   447: aload_1
    //   448: invokevirtual 321	java/io/ByteArrayOutputStream:close	()V
    //   451: goto -259 -> 192
    //   454: astore_1
    //   455: goto -263 -> 192
    //   458: astore 4
    //   460: aload 6
    //   462: astore_1
    //   463: aload_1
    //   464: astore_2
    //   465: aload 5
    //   467: astore_3
    //   468: aload 4
    //   470: invokevirtual 324	java/lang/Exception:printStackTrace	()V
    //   473: aload 5
    //   475: ifnull +8 -> 483
    //   478: aload 5
    //   480: invokevirtual 320	java/io/ObjectOutputStream:close	()V
    //   483: aload_1
    //   484: ifnull -292 -> 192
    //   487: aload_1
    //   488: invokevirtual 321	java/io/ByteArrayOutputStream:close	()V
    //   491: goto -299 -> 192
    //   494: astore_1
    //   495: goto -303 -> 192
    //   498: astore_1
    //   499: aload_3
    //   500: ifnull +7 -> 507
    //   503: aload_3
    //   504: invokevirtual 320	java/io/ObjectOutputStream:close	()V
    //   507: aload_2
    //   508: ifnull +7 -> 515
    //   511: aload_2
    //   512: invokevirtual 321	java/io/ByteArrayOutputStream:close	()V
    //   515: aload_1
    //   516: athrow
    //   517: aload 7
    //   519: ldc_w 326
    //   522: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   525: pop
    //   526: aload 7
    //   528: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   531: astore_1
    //   532: aload_0
    //   533: monitorexit
    //   534: aload_1
    //   535: areturn
    //   536: astore_2
    //   537: goto -94 -> 443
    //   540: astore_2
    //   541: goto -58 -> 483
    //   544: astore_3
    //   545: goto -38 -> 507
    //   548: astore_2
    //   549: goto -34 -> 515
    //   552: astore 5
    //   554: aload_1
    //   555: astore_2
    //   556: aload 4
    //   558: astore_3
    //   559: aload 5
    //   561: astore_1
    //   562: goto -63 -> 499
    //   565: astore 4
    //   567: aload_2
    //   568: astore_3
    //   569: aload_1
    //   570: astore_2
    //   571: aload 4
    //   573: astore_1
    //   574: goto -75 -> 499
    //   577: astore 4
    //   579: goto -116 -> 463
    //   582: astore 4
    //   584: aload_2
    //   585: astore 5
    //   587: goto -124 -> 463
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	590	0	this	Packet
    //   20	13	1	localIterator1	Iterator
    //   53	4	1	localObject1	Object
    //   116	332	1	localObject2	Object
    //   454	1	1	localException1	Exception
    //   462	26	1	localObject3	Object
    //   494	1	1	localException2	Exception
    //   498	18	1	localObject4	Object
    //   531	43	1	localObject5	Object
    //   372	140	2	localObject6	Object
    //   536	1	2	localException3	Exception
    //   540	1	2	localException4	Exception
    //   548	1	2	localException5	Exception
    //   555	30	2	localObject7	Object
    //   377	127	3	localObject8	Object
    //   544	1	3	localException6	Exception
    //   558	11	3	localObject9	Object
    //   379	1	4	localObject10	Object
    //   458	99	4	localException7	Exception
    //   565	7	4	localObject11	Object
    //   577	1	4	localException8	Exception
    //   582	1	4	localException9	Exception
    //   382	97	5	localObject12	Object
    //   552	8	5	localObject13	Object
    //   585	1	5	localObject14	Object
    //   374	87	6	localObject15	Object
    //   9	518	7	localStringBuilder	StringBuilder
    //   94	13	8	localIterator2	Iterator
    //   122	281	9	localObject16	Object
    // Exception table:
    //   from	to	target	type
    //   2	21	53	finally
    //   21	50	53	finally
    //   58	96	53	finally
    //   96	192	53	finally
    //   192	201	53	finally
    //   204	232	53	finally
    //   235	263	53	finally
    //   266	294	53	finally
    //   297	325	53	finally
    //   328	368	53	finally
    //   439	443	53	finally
    //   447	451	53	finally
    //   478	483	53	finally
    //   487	491	53	finally
    //   503	507	53	finally
    //   511	515	53	finally
    //   515	517	53	finally
    //   517	526	53	finally
    //   526	532	53	finally
    //   447	451	454	java/lang/Exception
    //   384	392	458	java/lang/Exception
    //   487	491	494	java/lang/Exception
    //   384	392	498	finally
    //   468	473	498	finally
    //   439	443	536	java/lang/Exception
    //   478	483	540	java/lang/Exception
    //   503	507	544	java/lang/Exception
    //   511	515	548	java/lang/Exception
    //   392	401	552	finally
    //   401	435	565	finally
    //   392	401	577	java/lang/Exception
    //   401	435	582	java/lang/Exception
  }
  
  public String getFrom()
  {
    return from;
  }
  
  public String getPackageName()
  {
    return packageName;
  }
  
  public String getPacketID()
  {
    if ("ID_NOT_AVAILABLE".equals(packetID)) {
      return null;
    }
    if (packetID == null) {
      packetID = nextID();
    }
    return packetID;
  }
  
  /* Error */
  public Object getProperty(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 118	com/xiaomi/smack/packet/Packet:properties	Ljava/util/Map;
    //   6: astore_2
    //   7: aload_2
    //   8: ifnonnull +9 -> 17
    //   11: aconst_null
    //   12: astore_1
    //   13: aload_0
    //   14: monitorexit
    //   15: aload_1
    //   16: areturn
    //   17: aload_0
    //   18: getfield 118	com/xiaomi/smack/packet/Packet:properties	Ljava/util/Map;
    //   21: aload_1
    //   22: invokeinterface 337 2 0
    //   27: astore_1
    //   28: goto -15 -> 13
    //   31: astore_1
    //   32: aload_0
    //   33: monitorexit
    //   34: aload_1
    //   35: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	36	0	this	Packet
    //   0	36	1	paramString	String
    //   6	2	2	localMap	Map
    // Exception table:
    //   from	to	target	type
    //   2	7	31	finally
    //   17	28	31	finally
  }
  
  /* Error */
  public java.util.Collection<String> getPropertyNames()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 118	com/xiaomi/smack/packet/Packet:properties	Ljava/util/Map;
    //   6: ifnonnull +11 -> 17
    //   9: invokestatic 341	java/util/Collections:emptySet	()Ljava/util/Set;
    //   12: astore_1
    //   13: aload_0
    //   14: monitorexit
    //   15: aload_1
    //   16: areturn
    //   17: new 343	java/util/HashSet
    //   20: dup
    //   21: aload_0
    //   22: getfield 118	com/xiaomi/smack/packet/Packet:properties	Ljava/util/Map;
    //   25: invokeinterface 346 1 0
    //   30: invokespecial 347	java/util/HashSet:<init>	(Ljava/util/Collection;)V
    //   33: invokestatic 351	java/util/Collections:unmodifiableSet	(Ljava/util/Set;)Ljava/util/Set;
    //   36: astore_1
    //   37: goto -24 -> 13
    //   40: astore_1
    //   41: aload_0
    //   42: monitorexit
    //   43: aload_1
    //   44: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	45	0	this	Packet
    //   12	25	1	localSet	java.util.Set
    //   40	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	13	40	finally
    //   17	37	40	finally
  }
  
  public String getTo()
  {
    return to;
  }
  
  public String getXmlns()
  {
    return xmlns;
  }
  
  public int hashCode()
  {
    int i1 = 0;
    int i;
    int j;
    label33:
    int k;
    label48:
    int m;
    if (xmlns != null)
    {
      i = xmlns.hashCode();
      if (packetID == null) {
        break label161;
      }
      j = packetID.hashCode();
      if (to == null) {
        break label166;
      }
      k = to.hashCode();
      if (from == null) {
        break label171;
      }
      m = from.hashCode();
      label64:
      if (chId == null) {
        break label177;
      }
    }
    label161:
    label166:
    label171:
    label177:
    for (int n = chId.hashCode();; n = 0)
    {
      int i2 = packetExtensions.hashCode();
      int i3 = properties.hashCode();
      if (error != null) {
        i1 = error.hashCode();
      }
      return ((((((i * 31 + j) * 31 + k) * 31 + m) * 31 + n) * 31 + i2) * 31 + i3) * 31 + i1;
      i = 0;
      break;
      j = 0;
      break label33;
      k = 0;
      break label48;
      m = 0;
      break label64;
    }
  }
  
  public void setChannelId(String paramString)
  {
    chId = paramString;
  }
  
  public void setError(XMPPError paramXMPPError)
  {
    error = paramXMPPError;
  }
  
  public void setFrom(String paramString)
  {
    from = paramString;
  }
  
  public void setPackageName(String paramString)
  {
    packageName = paramString;
  }
  
  public void setPacketID(String paramString)
  {
    packetID = paramString;
  }
  
  public void setTo(String paramString)
  {
    to = paramString;
  }
  
  public Bundle toBundle()
  {
    Bundle localBundle1 = new Bundle();
    if (!TextUtils.isEmpty(xmlns)) {
      localBundle1.putString("ext_ns", xmlns);
    }
    if (!TextUtils.isEmpty(from)) {
      localBundle1.putString("ext_from", from);
    }
    if (!TextUtils.isEmpty(to)) {
      localBundle1.putString("ext_to", to);
    }
    if (!TextUtils.isEmpty(packetID)) {
      localBundle1.putString("ext_pkt_id", packetID);
    }
    if (!TextUtils.isEmpty(chId)) {
      localBundle1.putString("ext_chid", chId);
    }
    if (error != null) {
      localBundle1.putBundle("ext_ERROR", error.toBundle());
    }
    if (packetExtensions != null)
    {
      Bundle[] arrayOfBundle = new Bundle[packetExtensions.size()];
      int i = 0;
      Iterator localIterator = packetExtensions.iterator();
      while (localIterator.hasNext())
      {
        Bundle localBundle2 = ((CommonPacketExtension)localIterator.next()).toBundle();
        if (localBundle2 != null)
        {
          arrayOfBundle[i] = localBundle2;
          i += 1;
        }
      }
      localBundle1.putParcelableArray("ext_exts", arrayOfBundle);
    }
    return localBundle1;
  }
  
  public abstract String toXML();
}

/* Location:
 * Qualified Name:     com.xiaomi.smack.packet.Packet
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */