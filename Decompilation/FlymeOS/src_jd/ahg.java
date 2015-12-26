import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import com.amap.api.location.core.c;
import com.amap.api.location.core.e;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ahg
{
  String a = null;
  private LinkedHashMap<String, List<ahg.a>> b = new LinkedHashMap();
  private ahu c = null;
  private long d = 0L;
  
  ahg(Context paramContext)
  {
    try
    {
      if (a == null) {
        a = e.a("MD5", c.b());
      }
      if (paramContext != null)
      {
        paramContext = a(paramContext);
        if (paramContext != null) {
          c = ahu.a(paramContext, 1, 1048576L);
        }
      }
      return;
    }
    catch (Throwable paramContext) {}
  }
  
  private double a(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
  {
    double d2 = 0.0D;
    int i = 0;
    double d3 = 0.0D;
    double d1 = 0.0D;
    while (i < paramArrayOfDouble1.length)
    {
      d3 += paramArrayOfDouble1[i] * paramArrayOfDouble1[i];
      d2 += paramArrayOfDouble2[i] * paramArrayOfDouble2[i];
      d1 += paramArrayOfDouble1[i] * paramArrayOfDouble2[i];
      i += 1;
    }
    return d1 / (Math.sqrt(d3) * Math.sqrt(d2));
  }
  
  private ahg.a a(String paramString1, StringBuilder paramStringBuilder, String paramString2, String paramString3)
  {
    Object localObject1 = null;
    Hashtable localHashtable1 = new Hashtable();
    Hashtable localHashtable2 = new Hashtable();
    Hashtable localHashtable3 = new Hashtable();
    if (paramString3.equals("mem")) {}
    for (Iterator localIterator1 = b.entrySet().iterator();; localIterator1 = null)
    {
      int i = 1;
      Object localObject2;
      String str1;
      label119:
      do
      {
        if ((localIterator1 != null) && (localIterator1.hasNext()))
        {
          if (i == 0) {
            break label119;
          }
          localObject2 = (List)b.get(paramString1);
          str1 = paramString1;
          i = 0;
        }
        while (localObject1 != null)
        {
          localHashtable1.clear();
          localHashtable2.clear();
          localHashtable3.clear();
          return (ahg.a)localObject1;
          localObject2 = (Map.Entry)localIterator1.next();
          str1 = (String)((Map.Entry)localObject2).getKey();
          localObject2 = (List)((Map.Entry)localObject2).getValue();
        }
      } while (localObject2 == null);
      int k = 0;
      label169:
      ahg.a locala;
      if (k < ((List)localObject2).size())
      {
        locala = (ahg.a)((List)localObject2).get(k);
        if ((!TextUtils.isEmpty(locala.b())) && (!TextUtils.isEmpty(paramStringBuilder))) {}
      }
      label213:
      label262:
      label456:
      label498:
      label601:
      for (;;)
      {
        k += 1;
        break label169;
        if (str1.indexOf(paramString2) != -1)
        {
          int j;
          double d1;
          if (a(locala.b(), paramStringBuilder))
          {
            if (locala.a().g() > 300.0F) {}
            for (j = 0;; j = 1)
            {
              a(locala.b(), localHashtable1);
              a(paramStringBuilder.toString(), localHashtable2);
              localHashtable3.clear();
              localObject3 = localHashtable1.keySet().iterator();
              while (((Iterator)localObject3).hasNext()) {
                localHashtable3.put((String)((Iterator)localObject3).next(), "");
              }
            }
            Object localObject3 = localHashtable2.keySet().iterator();
            while (((Iterator)localObject3).hasNext()) {
              localHashtable3.put((String)((Iterator)localObject3).next(), "");
            }
            localObject3 = localHashtable3.keySet();
            double[] arrayOfDouble1 = new double[((Set)localObject3).size()];
            double[] arrayOfDouble2 = new double[((Set)localObject3).size()];
            Iterator localIterator2 = ((Set)localObject3).iterator();
            int m = 0;
            if (localIterator2.hasNext())
            {
              String str2 = (String)localIterator2.next();
              if (localHashtable1.containsKey(str2))
              {
                d1 = 1.0D;
                arrayOfDouble1[m] = d1;
                if (!localHashtable2.containsKey(str2)) {
                  break label498;
                }
              }
              for (d1 = 1.0D;; d1 = 0.0D)
              {
                arrayOfDouble2[m] = d1;
                m += 1;
                break;
                d1 = 0.0D;
                break label456;
              }
            }
            ((Set)localObject3).clear();
            d1 = a(arrayOfDouble1, arrayOfDouble2);
            if (paramString3.equals("mem")) {
              if ((j != 0) && (d1 > 0.8500000238418579D)) {
                localObject1 = locala;
              }
            }
          }
          for (;;)
          {
            break;
            if (d1 <= 0.8500000238418579D) {
              break label601;
            }
            localObject1 = locala;
            continue;
            if ((!paramString3.equals("db")) || (d1 <= 0.8500000238418579D)) {
              break label213;
            }
            localObject1 = locala;
            continue;
            j = 0;
            break label262;
          }
        }
      }
    }
  }
  
  private File a(Context paramContext)
  {
    String str = Environment.getExternalStorageState();
    Object localObject = null;
    if ("mounted".equals(str))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramContext.getExternalCacheDir().getAbsolutePath()).append(File.separator);
      ((StringBuilder)localObject).append("locationCache");
    }
    try
    {
      a(new File(((StringBuilder)localObject).toString()));
      ((StringBuilder)localObject).delete(0, ((StringBuilder)localObject).length());
      ((StringBuilder)localObject).append(paramContext.getExternalCacheDir().getAbsolutePath()).append(File.separator);
      ((StringBuilder)localObject).append("newlocationCache");
      localObject = new File(((StringBuilder)localObject).toString());
      return (File)localObject;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        localThrowable.printStackTrace();
      }
    }
  }
  
  private void a(File paramFile)
  {
    if ((paramFile != null) && (paramFile.exists()))
    {
      if (!paramFile.isFile()) {
        break label24;
      }
      paramFile.delete();
    }
    label24:
    while (!paramFile.isDirectory()) {
      return;
    }
    File[] arrayOfFile = paramFile.listFiles();
    int i = 0;
    while (i < arrayOfFile.length)
    {
      a(arrayOfFile[i]);
      i += 1;
    }
    paramFile.delete();
  }
  
  private void a(String paramString, Hashtable<String, String> paramHashtable)
  {
    paramHashtable.clear();
    paramString = paramString.split("#");
    int j = paramString.length;
    int i = 0;
    while (i < j)
    {
      Object localObject = paramString[i];
      if (((String)localObject).length() > 0) {
        paramHashtable.put(localObject, "");
      }
      i += 1;
    }
  }
  
  private boolean a(String paramString1, String paramString2)
  {
    Hashtable localHashtable1 = new Hashtable();
    Hashtable localHashtable2 = new Hashtable();
    Hashtable localHashtable3 = new Hashtable();
    a(paramString2, localHashtable1);
    a(paramString1, localHashtable2);
    localHashtable3.clear();
    paramString1 = localHashtable1.keySet().iterator();
    while (paramString1.hasNext()) {
      localHashtable3.put((String)paramString1.next(), "");
    }
    paramString1 = localHashtable2.keySet().iterator();
    while (paramString1.hasNext()) {
      localHashtable3.put((String)paramString1.next(), "");
    }
    paramString1 = localHashtable3.keySet();
    paramString2 = new double[paramString1.size()];
    double[] arrayOfDouble = new double[paramString1.size()];
    Iterator localIterator = paramString1.iterator();
    int i = 0;
    if (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (localHashtable1.containsKey(str))
      {
        d1 = 1.0D;
        label196:
        paramString2[i] = d1;
        if (!localHashtable2.containsKey(str)) {
          break label233;
        }
      }
      label233:
      for (d1 = 1.0D;; d1 = 0.0D)
      {
        arrayOfDouble[i] = d1;
        i += 1;
        break;
        d1 = 0.0D;
        break label196;
      }
    }
    paramString1.clear();
    double d1 = a(paramString2, arrayOfDouble);
    localHashtable1.clear();
    localHashtable2.clear();
    localHashtable3.clear();
    return d1 > 0.8500000238418579D;
  }
  
  private boolean a(String paramString, StringBuilder paramStringBuilder)
  {
    int i = paramString.indexOf(",access");
    if ((i == -1) || (i < 17)) {}
    do
    {
      do
      {
        return false;
      } while (paramStringBuilder.indexOf(",access") == -1);
      paramString = paramString.substring(i - 17, i);
    } while (paramStringBuilder.toString().indexOf(paramString + ",access") == -1);
    return true;
  }
  
  /* Error */
  ahf a(String paramString1, StringBuilder paramStringBuilder, String paramString2)
  {
    // Byte code:
    //   0: aload_3
    //   1: ldc 69
    //   3: invokevirtual 75	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   6: ifeq +15 -> 21
    //   9: getstatic 260	ahk:k	Z
    //   12: ifne +9 -> 21
    //   15: aload_0
    //   16: invokevirtual 262	ahg:a	()V
    //   19: aconst_null
    //   20: areturn
    //   21: aload_1
    //   22: ifnull +264 -> 286
    //   25: aload_1
    //   26: ldc_w 264
    //   29: invokevirtual 130	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   32: iconst_m1
    //   33: if_icmpeq +253 -> 286
    //   36: aload_0
    //   37: aload_1
    //   38: aload_2
    //   39: ldc_w 264
    //   42: aload_3
    //   43: invokespecial 266	ahg:a	(Ljava/lang/String;Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)Lahg$a;
    //   46: astore_3
    //   47: aload_3
    //   48: ifnull +235 -> 283
    //   51: aload_0
    //   52: getfield 33	ahg:a	Ljava/lang/String;
    //   55: ifnonnull +10 -> 65
    //   58: aload_0
    //   59: invokestatic 40	com/amap/api/location/core/c:b	()Ljava/lang/String;
    //   62: putfield 33	ahg:a	Ljava/lang/String;
    //   65: aload_3
    //   66: ifnonnull +500 -> 566
    //   69: aload_2
    //   70: ifnull +13 -> 83
    //   73: aload_2
    //   74: astore 4
    //   76: aload_2
    //   77: invokevirtual 208	java/lang/StringBuilder:length	()I
    //   80: ifne +15 -> 95
    //   83: new 148	java/lang/StringBuilder
    //   86: dup
    //   87: ldc_w 268
    //   90: invokespecial 269	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   93: astore 4
    //   95: aload_0
    //   96: getfield 29	ahg:c	Lahu;
    //   99: ifnull +462 -> 561
    //   102: aload_0
    //   103: getfield 29	ahg:c	Lahu;
    //   106: aload_1
    //   107: invokevirtual 272	ahu:a	(Ljava/lang/String;)Ljava/util/Map;
    //   110: astore_2
    //   111: aload_2
    //   112: ifnull +446 -> 558
    //   115: aload_2
    //   116: invokeinterface 275 1 0
    //   121: invokeinterface 85 1 0
    //   126: astore 5
    //   128: aload_3
    //   129: astore_2
    //   130: aload_2
    //   131: astore_3
    //   132: aload 5
    //   134: ifnull +138 -> 272
    //   137: aload_2
    //   138: astore_3
    //   139: aload 5
    //   141: invokeinterface 91 1 0
    //   146: ifeq +126 -> 272
    //   149: aload 5
    //   151: invokeinterface 104 1 0
    //   156: checkcast 106	java/util/Map$Entry
    //   159: astore_3
    //   160: aload_0
    //   161: aload_3
    //   162: invokeinterface 109 1 0
    //   167: checkcast 71	java/lang/String
    //   170: aload_0
    //   171: getfield 33	ahg:a	Ljava/lang/String;
    //   174: invokestatic 277	com/amap/api/location/core/e:b	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   177: aload 4
    //   179: invokevirtual 151	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   182: invokespecial 279	ahg:a	(Ljava/lang/String;Ljava/lang/String;)Z
    //   185: ifeq +370 -> 555
    //   188: new 138	ahf
    //   191: dup
    //   192: new 281	org/json/JSONObject
    //   195: dup
    //   196: aload_3
    //   197: invokeinterface 112 1 0
    //   202: checkcast 71	java/lang/String
    //   205: aload_0
    //   206: getfield 33	ahg:a	Ljava/lang/String;
    //   209: invokestatic 277	com/amap/api/location/core/e:b	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   212: invokespecial 282	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   215: invokespecial 285	ahf:<init>	(Lorg/json/JSONObject;)V
    //   218: astore 6
    //   220: aload 6
    //   222: ldc 69
    //   224: invokevirtual 287	ahf:g	(Ljava/lang/String;)V
    //   227: new 6	ahg$a
    //   230: dup
    //   231: invokespecial 288	ahg$a:<init>	()V
    //   234: astore_3
    //   235: aload_3
    //   236: aload 6
    //   238: invokevirtual 291	ahg$a:a	(Lahf;)V
    //   241: aload_3
    //   242: aload 4
    //   244: invokevirtual 151	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   247: invokevirtual 293	ahg$a:a	(Ljava/lang/String;)V
    //   250: aload_0
    //   251: getfield 27	ahg:b	Ljava/util/LinkedHashMap;
    //   254: ifnonnull +14 -> 268
    //   257: aload_0
    //   258: new 24	java/util/LinkedHashMap
    //   261: dup
    //   262: invokespecial 25	java/util/LinkedHashMap:<init>	()V
    //   265: putfield 27	ahg:b	Ljava/util/LinkedHashMap;
    //   268: aload_1
    //   269: ifnonnull +130 -> 399
    //   272: aload_3
    //   273: astore_1
    //   274: aload_1
    //   275: ifnull -256 -> 19
    //   278: aload_1
    //   279: invokevirtual 136	ahg$a:a	()Lahf;
    //   282: areturn
    //   283: goto -232 -> 51
    //   286: aload_1
    //   287: ifnull +35 -> 322
    //   290: aload_1
    //   291: ldc_w 295
    //   294: invokevirtual 130	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   297: iconst_m1
    //   298: if_icmpeq +24 -> 322
    //   301: aload_0
    //   302: aload_1
    //   303: aload_2
    //   304: ldc_w 295
    //   307: aload_3
    //   308: invokespecial 266	ahg:a	(Ljava/lang/String;Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;)Lahg$a;
    //   311: astore_3
    //   312: aload_3
    //   313: ifnull +6 -> 319
    //   316: goto -265 -> 51
    //   319: goto -268 -> 51
    //   322: aload_1
    //   323: ifnull +253 -> 576
    //   326: aload_1
    //   327: ldc_w 297
    //   330: invokevirtual 130	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   333: iconst_m1
    //   334: if_icmpeq +242 -> 576
    //   337: aload_3
    //   338: ldc 69
    //   340: invokevirtual 75	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   343: ifeq +228 -> 571
    //   346: aload_0
    //   347: getfield 27	ahg:b	Ljava/util/LinkedHashMap;
    //   350: aload_1
    //   351: invokevirtual 95	java/util/LinkedHashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   354: checkcast 97	java/util/List
    //   357: astore_3
    //   358: aload_3
    //   359: ifnull +212 -> 571
    //   362: aload_3
    //   363: invokeinterface 116 1 0
    //   368: ifle +203 -> 571
    //   371: aload_3
    //   372: aload_3
    //   373: invokeinterface 116 1 0
    //   378: iconst_1
    //   379: isub
    //   380: invokeinterface 119 2 0
    //   385: checkcast 6	ahg$a
    //   388: astore_3
    //   389: aload_3
    //   390: ifnull +6 -> 396
    //   393: goto -342 -> 51
    //   396: goto -345 -> 51
    //   399: aload_0
    //   400: getfield 27	ahg:b	Ljava/util/LinkedHashMap;
    //   403: aload_1
    //   404: invokevirtual 298	java/util/LinkedHashMap:containsKey	(Ljava/lang/Object;)Z
    //   407: ifeq +63 -> 470
    //   410: aload_0
    //   411: getfield 27	ahg:b	Ljava/util/LinkedHashMap;
    //   414: aload_1
    //   415: invokevirtual 95	java/util/LinkedHashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   418: checkcast 97	java/util/List
    //   421: astore_2
    //   422: aload_2
    //   423: ifnull +21 -> 444
    //   426: aload_2
    //   427: aload_3
    //   428: invokeinterface 301 2 0
    //   433: ifne +11 -> 444
    //   436: aload_2
    //   437: iconst_0
    //   438: aload_3
    //   439: invokeinterface 305 3 0
    //   444: aload_2
    //   445: ifnull +136 -> 581
    //   448: aload_0
    //   449: getfield 27	ahg:b	Ljava/util/LinkedHashMap;
    //   452: aload_1
    //   453: invokevirtual 308	java/util/LinkedHashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   456: pop
    //   457: aload_0
    //   458: getfield 27	ahg:b	Ljava/util/LinkedHashMap;
    //   461: aload_1
    //   462: aload_2
    //   463: invokevirtual 309	java/util/LinkedHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   466: pop
    //   467: goto +114 -> 581
    //   470: new 311	java/util/ArrayList
    //   473: dup
    //   474: invokespecial 312	java/util/ArrayList:<init>	()V
    //   477: astore_2
    //   478: aload_2
    //   479: aload_3
    //   480: invokeinterface 314 2 0
    //   485: pop
    //   486: aload_0
    //   487: getfield 27	ahg:b	Ljava/util/LinkedHashMap;
    //   490: aload_1
    //   491: aload_2
    //   492: invokevirtual 309	java/util/LinkedHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   495: pop
    //   496: aload_3
    //   497: astore_2
    //   498: goto +85 -> 583
    //   501: astore_1
    //   502: aload_3
    //   503: astore_1
    //   504: goto -230 -> 274
    //   507: astore_1
    //   508: aload_2
    //   509: astore_1
    //   510: goto -236 -> 274
    //   513: astore_1
    //   514: aload_3
    //   515: astore_1
    //   516: goto -242 -> 274
    //   519: astore_1
    //   520: aload_3
    //   521: astore_1
    //   522: goto -248 -> 274
    //   525: astore_1
    //   526: aload_2
    //   527: astore_1
    //   528: goto -254 -> 274
    //   531: astore_1
    //   532: aload_3
    //   533: astore_1
    //   534: goto -260 -> 274
    //   537: astore_1
    //   538: aload_3
    //   539: astore_1
    //   540: goto -266 -> 274
    //   543: astore_1
    //   544: aload_2
    //   545: astore_1
    //   546: goto -272 -> 274
    //   549: astore_1
    //   550: aload_3
    //   551: astore_1
    //   552: goto -278 -> 274
    //   555: goto +28 -> 583
    //   558: goto -286 -> 272
    //   561: aconst_null
    //   562: astore_2
    //   563: goto -452 -> 111
    //   566: aload_3
    //   567: astore_1
    //   568: goto -294 -> 274
    //   571: aconst_null
    //   572: astore_3
    //   573: goto -184 -> 389
    //   576: aconst_null
    //   577: astore_3
    //   578: goto -527 -> 51
    //   581: aload_3
    //   582: astore_2
    //   583: goto -453 -> 130
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	586	0	this	ahg
    //   0	586	1	paramString1	String
    //   0	586	2	paramStringBuilder	StringBuilder
    //   0	586	3	paramString2	String
    //   74	169	4	localStringBuilder	StringBuilder
    //   126	24	5	localIterator	Iterator
    //   218	19	6	localahf	ahf
    // Exception table:
    //   from	to	target	type
    //   76	83	501	java/lang/Throwable
    //   83	95	501	java/lang/Throwable
    //   95	111	501	java/lang/Throwable
    //   115	128	501	java/lang/Throwable
    //   139	235	507	java/lang/Throwable
    //   235	268	513	java/lang/Throwable
    //   399	422	513	java/lang/Throwable
    //   426	444	513	java/lang/Throwable
    //   448	467	513	java/lang/Throwable
    //   470	496	513	java/lang/Throwable
    //   76	83	519	java/io/IOException
    //   83	95	519	java/io/IOException
    //   95	111	519	java/io/IOException
    //   115	128	519	java/io/IOException
    //   139	235	525	java/io/IOException
    //   235	268	531	java/io/IOException
    //   399	422	531	java/io/IOException
    //   426	444	531	java/io/IOException
    //   448	467	531	java/io/IOException
    //   470	496	531	java/io/IOException
    //   76	83	537	org/json/JSONException
    //   83	95	537	org/json/JSONException
    //   95	111	537	org/json/JSONException
    //   115	128	537	org/json/JSONException
    //   139	235	543	org/json/JSONException
    //   235	268	549	org/json/JSONException
    //   399	422	549	org/json/JSONException
    //   426	444	549	org/json/JSONException
    //   448	467	549	org/json/JSONException
    //   470	496	549	org/json/JSONException
  }
  
  void a()
  {
    d = 0L;
    b.clear();
  }
  
  void a(String paramString, ahf paramahf, StringBuilder paramStringBuilder)
  {
    if (!ahk.k)
    {
      a();
      break label10;
      break label10;
      break label10;
    }
    label10:
    label136:
    label275:
    label518:
    label681:
    for (;;)
    {
      return;
      Object localObject1;
      if ((a(paramString, paramahf)) && (!paramahf.i().equals("mem"))) {
        if ((paramString != null) && (paramString.contains("wifi")))
        {
          if (TextUtils.isEmpty(paramStringBuilder)) {
            break;
          }
          if (paramahf.g() >= 300.0F)
          {
            localObject1 = paramStringBuilder.toString().split("#");
            int m = localObject1.length;
            i = 0;
            int k;
            for (int j = 0; i < m; j = k)
            {
              k = j;
              if (localObject1[i].contains(",")) {
                k = j + 1;
              }
              i += 1;
            }
            if (j >= 6) {
              break;
            }
            d = ahz.b();
            localObject1 = new ahg.a();
            paramahf.g("mem");
            ((ahg.a)localObject1).a(paramahf);
            if (paramStringBuilder != null) {
              ((ahg.a)localObject1).a(paramStringBuilder.toString());
            }
            if (b == null) {
              b = new LinkedHashMap();
            }
            if (paramString == null) {
              break;
            }
            if (!b.containsKey(paramString)) {
              break label518;
            }
            localObject2 = (List)b.get(paramString);
            if ((localObject2 != null) && (!((List)localObject2).contains(localObject1))) {
              ((List)localObject2).add(0, localObject1);
            }
            if (localObject2 != null)
            {
              b.remove(paramString);
              b.put(paramString, localObject2);
            }
          }
        }
      }
      try
      {
        if (a == null) {
          a = e.a("MD5", c.b());
        }
        if (paramStringBuilder != null)
        {
          localObject1 = paramStringBuilder;
          if (paramStringBuilder.length() != 0) {}
        }
        else
        {
          localObject1 = new StringBuilder("cell#");
        }
        if (c == null) {
          break label697;
        }
        paramStringBuilder = c.a(paramString);
      }
      catch (IOException paramString)
      {
        for (;;) {}
      }
      catch (Exception paramString)
      {
        for (;;)
        {
          continue;
          i = 1;
          continue;
          paramStringBuilder = null;
        }
      }
      Object localObject2 = e.d(((StringBuilder)localObject1).toString().getBytes(), a);
      paramahf = e.d(paramahf.u().getBytes(), a);
      if ((paramStringBuilder == null) || (paramStringBuilder.size() == 0))
      {
        paramStringBuilder = new HashMap();
        paramStringBuilder.put(localObject2, paramahf);
        if (c != null) {
          c.b(paramString, paramStringBuilder);
        }
      }
      for (;;)
      {
        if (b.size() <= 360) {
          break label681;
        }
        paramString = b.entrySet().iterator();
        if ((paramString == null) || (!paramString.hasNext())) {
          break;
        }
        paramString = (String)((Map.Entry)paramString.next()).getKey();
        b.remove(paramString);
        return;
        if (paramahf.g() > 10.0F) {
          break label136;
        }
        return;
        if ((paramString == null) || (!paramString.contains("cell")) || (paramStringBuilder.indexOf(",") == -1)) {
          break label136;
        }
        return;
        localObject2 = new ArrayList();
        ((List)localObject2).add(localObject1);
        b.put(paramString, localObject2);
        break label275;
        Iterator localIterator = paramStringBuilder.entrySet().iterator();
        Map.Entry localEntry;
        do
        {
          if ((localIterator == null) || (!localIterator.hasNext())) {
            break;
          }
          localEntry = (Map.Entry)localIterator.next();
        } while (!a(e.b((String)localEntry.getKey(), a), ((StringBuilder)localObject1).toString()));
        paramStringBuilder.remove(localEntry.getKey());
        paramStringBuilder.put(localObject2, paramahf);
        c.b(paramString, paramStringBuilder);
        i = 0;
        if (i != 0)
        {
          paramStringBuilder.put(localObject2, paramahf);
          c.b(paramString, paramStringBuilder);
        }
      }
    }
  }
  
  boolean a(String paramString, ahf paramahf)
  {
    if ((paramString == null) || (paramahf == null)) {}
    while ((paramString.indexOf("#network") == -1) || (paramahf.e() == 0.0D)) {
      return false;
    }
    return true;
  }
  
  void b()
  {
    if (c != null) {
      c.a();
    }
  }
  
  static class a
  {
    private ahf a = null;
    private String b = null;
    
    public ahf a()
    {
      return a;
    }
    
    public void a(ahf paramahf)
    {
      a = paramahf;
    }
    
    public void a(String paramString)
    {
      b = paramString.replace("##", "#");
    }
    
    public String b()
    {
      return b;
    }
  }
}

/* Location:
 * Qualified Name:     ahg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */