package cn.com.xy.sms.sdk.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.AirManager;
import cn.com.xy.sms.sdk.db.ParseItemManager;
import cn.com.xy.sms.sdk.db.TrainManager;
import cn.com.xy.sms.sdk.db.c;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.net.util.l;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public final class e
{
  public static boolean a = false;
  private static boolean b = false;
  private static final Object c = new Object();
  private static boolean d = false;
  
  private static List<String> a(String paramString)
  {
    ArrayList localArrayList = null;
    if (paramString == null) {
      paramString = localArrayList;
    }
    Object localObject;
    do
    {
      do
      {
        do
        {
          do
          {
            return paramString;
            localObject = new File(paramString);
            paramString = localArrayList;
          } while (!((File)localObject).exists());
          paramString = localArrayList;
        } while (!((File)localObject).isDirectory());
        localObject = ((File)localObject).list();
        paramString = localArrayList;
      } while (localObject == null);
      paramString = localArrayList;
    } while (localObject.length == 0);
    localArrayList = new ArrayList();
    int j = localObject.length;
    int i = 0;
    for (;;)
    {
      paramString = localArrayList;
      if (i >= j) {
        break;
      }
      paramString = localObject[i];
      int k = paramString.lastIndexOf("_");
      if (k != -1) {
        localArrayList.add(paramString.substring(0, k));
      }
      i += 1;
    }
  }
  
  /* Error */
  public static void a()
  {
    // Byte code:
    //   0: getstatic 21	cn/com/xy/sms/sdk/util/e:c	Ljava/lang/Object;
    //   3: astore_1
    //   4: aload_1
    //   5: monitorenter
    //   6: getstatic 16	cn/com/xy/sms/sdk/util/e:b	Z
    //   9: ifeq +14 -> 23
    //   12: aload_1
    //   13: monitorexit
    //   14: iconst_0
    //   15: putstatic 16	cn/com/xy/sms/sdk/util/e:b	Z
    //   18: iconst_1
    //   19: putstatic 14	cn/com/xy/sms/sdk/util/e:a	Z
    //   22: return
    //   23: iconst_1
    //   24: putstatic 16	cn/com/xy/sms/sdk/util/e:b	Z
    //   27: aload_1
    //   28: monitorexit
    //   29: invokestatic 68	cn/com/xy/sms/sdk/util/e:b	()V
    //   32: invokestatic 72	cn/com/xy/sms/sdk/util/i:a	()V
    //   35: invokestatic 77	cn/com/xy/sms/sdk/util/SceneconfigUtil:updateData	()V
    //   38: invokestatic 83	cn/com/xy/sms/sdk/constant/Constant:getContext	()Landroid/content/Context;
    //   41: ldc 85
    //   43: invokestatic 91	cn/com/xy/sms/sdk/db/entity/SysParamEntityManager:getStringParam	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   46: astore_1
    //   47: ldc 93
    //   49: aload_1
    //   50: invokevirtual 96	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   53: ifeq +133 -> 186
    //   56: ldc 98
    //   58: invokestatic 104	cn/com/xy/sms/sdk/util/DateUtils:getCurrentTimeString	(Ljava/lang/String;)Ljava/lang/String;
    //   61: astore_1
    //   62: invokestatic 83	cn/com/xy/sms/sdk/constant/Constant:getContext	()Landroid/content/Context;
    //   65: ldc 106
    //   67: invokestatic 91	cn/com/xy/sms/sdk/db/entity/SysParamEntityManager:getStringParam	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   70: astore_2
    //   71: aload_2
    //   72: ifnonnull +78 -> 150
    //   75: iconst_1
    //   76: istore_0
    //   77: iload_0
    //   78: ifeq +41 -> 119
    //   81: aload_1
    //   82: invokestatic 110	cn/com/xy/sms/sdk/util/o:a	(Ljava/lang/String;)Ljava/lang/String;
    //   85: astore_2
    //   86: aload_2
    //   87: invokestatic 116	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   90: ifne +29 -> 119
    //   93: new 118	cn/com/xy/sms/sdk/util/p
    //   96: dup
    //   97: aload_1
    //   98: invokespecial 119	cn/com/xy/sms/sdk/util/p:<init>	(Ljava/lang/String;)V
    //   101: astore_1
    //   102: invokestatic 124	cn/com/xy/sms/sdk/net/NetUtil:isEnhance	()Z
    //   105: ifeq +14 -> 119
    //   108: aload_2
    //   109: ldc 126
    //   111: aload_1
    //   112: getstatic 130	cn/com/xy/sms/sdk/net/NetUtil:STATSERVICE_URL	Ljava/lang/String;
    //   115: iconst_1
    //   116: invokestatic 134	cn/com/xy/sms/sdk/net/NetUtil:executeLoginBeforeHttpRequest	(Ljava/lang/String;Ljava/lang/String;Lcn/com/xy/sms/sdk/Iservice/XyCallBack;Ljava/lang/String;Z)V
    //   119: invokestatic 137	bs:a	()V
    //   122: iconst_0
    //   123: putstatic 16	cn/com/xy/sms/sdk/util/e:b	Z
    //   126: iconst_1
    //   127: putstatic 14	cn/com/xy/sms/sdk/util/e:a	Z
    //   130: return
    //   131: astore_2
    //   132: aload_1
    //   133: monitorexit
    //   134: aload_2
    //   135: athrow
    //   136: astore_1
    //   137: aload_1
    //   138: invokevirtual 140	java/lang/Throwable:printStackTrace	()V
    //   141: iconst_0
    //   142: putstatic 16	cn/com/xy/sms/sdk/util/e:b	Z
    //   145: iconst_1
    //   146: putstatic 14	cn/com/xy/sms/sdk/util/e:a	Z
    //   149: return
    //   150: aload_1
    //   151: aload_2
    //   152: ldc 98
    //   154: iconst_1
    //   155: invokestatic 144	cn/com/xy/sms/sdk/util/DateUtils:addDays	(Ljava/lang/String;Ljava/lang/String;I)Ljava/lang/String;
    //   158: ldc 98
    //   160: invokestatic 148	cn/com/xy/sms/sdk/util/DateUtils:compareDateString	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   163: istore_0
    //   164: goto -87 -> 77
    //   167: astore_1
    //   168: aload_1
    //   169: invokevirtual 140	java/lang/Throwable:printStackTrace	()V
    //   172: goto -53 -> 119
    //   175: astore_1
    //   176: iconst_0
    //   177: putstatic 16	cn/com/xy/sms/sdk/util/e:b	Z
    //   180: iconst_1
    //   181: putstatic 14	cn/com/xy/sms/sdk/util/e:a	Z
    //   184: aload_1
    //   185: athrow
    //   186: ldc -106
    //   188: aload_1
    //   189: invokevirtual 96	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   192: ifeq -73 -> 119
    //   195: invokestatic 153	cn/com/xy/sms/sdk/util/SceneconfigUtil:postqueryIccidScene	()V
    //   198: goto -79 -> 119
    // Local variable table:
    //   start	length	slot	name	signature
    //   76	88	0	bool	boolean
    //   136	15	1	localThrowable1	Throwable
    //   167	2	1	localThrowable2	Throwable
    //   175	14	1	localObject2	Object
    //   70	39	2	str1	String
    //   131	21	2	str2	String
    // Exception table:
    //   from	to	target	type
    //   6	14	131	finally
    //   23	29	131	finally
    //   0	6	136	java/lang/Throwable
    //   29	71	136	java/lang/Throwable
    //   119	122	136	java/lang/Throwable
    //   132	136	136	java/lang/Throwable
    //   150	164	136	java/lang/Throwable
    //   168	172	136	java/lang/Throwable
    //   186	198	136	java/lang/Throwable
    //   81	119	167	java/lang/Throwable
    //   0	6	175	finally
    //   29	71	175	finally
    //   81	119	175	finally
    //   119	122	175	finally
    //   132	136	175	finally
    //   137	141	175	finally
    //   150	164	175	finally
    //   168	172	175	finally
    //   186	198	175	finally
  }
  
  private static void a(String paramString, int paramInt)
  {
    if (d.a(Constant.getDRAWBLE_PATH() + paramString))
    {
      if (paramInt != 0) {
        break label37;
      }
      TrainManager.importTrainData(Constant.getContext());
    }
    label37:
    while (paramInt != 1) {
      return;
    }
    AirManager.importAirData(Constant.getContext());
  }
  
  private static void a(StringBuilder paramStringBuilder, String paramString1, String paramString2)
  {
    paramStringBuilder.append("SELECT '");
    paramStringBuilder.append(paramString1);
    paramStringBuilder.append("'name,'");
    paramStringBuilder.append(paramString2.trim());
    paramStringBuilder.append("'version,");
    paramStringBuilder.append(cn.com.xy.sms.sdk.db.entity.e.c(paramString1));
    paramStringBuilder.append(" is_use UNION ALL ");
  }
  
  private static void a(List<String> paramList, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    if ((paramList != null) && (paramList.contains(paramString4)))
    {
      d.a(paramString2, paramString4 + "_", ".jar", null);
      d.b(paramString4 + "_", ".dex", null);
      DexUtil.removeClassLoaderBySubname(paramString4);
    }
    paramList = d.b(paramString1 + paramString3);
    if (paramList == null) {}
    do
    {
      return;
      d.c(paramString2 + paramString3);
      d.a(paramString2, paramString3, paramList);
      d.a(paramString2, paramString4 + ".jar", paramString4 + "_" + paramString5 + ".jar");
      if ("parseUtilMain".equals(paramString4))
      {
        DexUtil.init();
        return;
      }
    } while (!"OnlineUpdateCycleConfig".equals(paramString4));
    DexUtil.initOnlineUpdateCycleConfig();
  }
  
  private static boolean a(String paramString1, String paramString2)
  {
    try
    {
      int i = Integer.valueOf(paramString1).intValue();
      try
      {
        int j = Integer.valueOf(paramString2).intValue();
        if (j > i) {
          return true;
        }
      }
      catch (NumberFormatException paramString1)
      {
        return false;
      }
      return false;
    }
    catch (NumberFormatException paramString1) {}
    return true;
  }
  
  private static boolean a(HashMap<String, String> paramHashMap, String paramString1, String paramString2)
  {
    if ((paramHashMap == null) || (paramHashMap.isEmpty()) || (!paramHashMap.containsKey(paramString1))) {
      return true;
    }
    return a((String)paramHashMap.get(paramString1), paramString2);
  }
  
  private static File b(String paramString1, String paramString2)
  {
    String str = paramString1 + paramString2 + ".sql";
    File localFile = new File(str);
    if ((!localFile.exists()) || (!localFile.isFile())) {}
    do
    {
      do
      {
        return null;
        str = StringUtils.getFileMD5(str);
      } while (StringUtils.isNull(str));
      paramString1 = l.a(paramString1, paramString2 + ".txt");
    } while ((StringUtils.isNull(paramString1)) || (!str.equals(paramString1)));
    return localFile;
  }
  
  /* Error */
  public static void b()
  {
    // Byte code:
    //   0: getstatic 21	cn/com/xy/sms/sdk/util/e:c	Ljava/lang/Object;
    //   3: astore 6
    //   5: aload 6
    //   7: monitorenter
    //   8: getstatic 23	cn/com/xy/sms/sdk/util/e:d	Z
    //   11: ifeq +7 -> 18
    //   14: aload 6
    //   16: monitorexit
    //   17: return
    //   18: iconst_1
    //   19: putstatic 23	cn/com/xy/sms/sdk/util/e:d	Z
    //   22: aload 6
    //   24: monitorexit
    //   25: ldc_w 358
    //   28: ldc_w 360
    //   31: invokestatic 364	cn/com/xy/sms/sdk/db/entity/SysParamEntityManager:setParam	(Ljava/lang/String;Ljava/lang/String;)J
    //   34: pop2
    //   35: invokestatic 369	java/lang/System:currentTimeMillis	()J
    //   38: lstore_1
    //   39: invokestatic 371	cn/com/xy/sms/sdk/util/e:c	()V
    //   42: invokestatic 83	cn/com/xy/sms/sdk/constant/Constant:getContext	()Landroid/content/Context;
    //   45: ldc_w 373
    //   48: invokestatic 91	cn/com/xy/sms/sdk/db/entity/SysParamEntityManager:getStringParam	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   51: astore 6
    //   53: ldc_w 375
    //   56: invokestatic 378	cn/com/xy/sms/sdk/util/d:f	(Ljava/lang/String;)Ljava/lang/String;
    //   59: astore 7
    //   61: aload 6
    //   63: invokestatic 116	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   66: ifne +17 -> 83
    //   69: aload 6
    //   71: aload 7
    //   73: invokestatic 339	cn/com/xy/sms/sdk/util/e:a	(Ljava/lang/String;Ljava/lang/String;)Z
    //   76: istore 5
    //   78: iload 5
    //   80: ifeq +39 -> 119
    //   83: invokestatic 83	cn/com/xy/sms/sdk/constant/Constant:getContext	()Landroid/content/Context;
    //   86: invokevirtual 384	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   89: invokevirtual 390	android/content/res/Resources:getAssets	()Landroid/content/res/AssetManager;
    //   92: ldc_w 392
    //   95: invokevirtual 397	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   98: ldc_w 392
    //   101: invokestatic 400	cn/com/xy/sms/sdk/constant/Constant:getINITSQL_PATH	()Ljava/lang/String;
    //   104: invokestatic 406	cn/com/xy/sms/sdk/util/XyUtil:unZip	(Ljava/io/InputStream;Ljava/lang/String;Ljava/lang/String;)V
    //   107: invokestatic 409	cn/com/xy/sms/sdk/db/c:b	()V
    //   110: ldc_w 373
    //   113: aload 7
    //   115: invokestatic 364	cn/com/xy/sms/sdk/db/entity/SysParamEntityManager:setParam	(Ljava/lang/String;Ljava/lang/String;)J
    //   118: pop2
    //   119: invokestatic 83	cn/com/xy/sms/sdk/constant/Constant:getContext	()Landroid/content/Context;
    //   122: ldc_w 411
    //   125: invokestatic 91	cn/com/xy/sms/sdk/db/entity/SysParamEntityManager:getStringParam	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   128: astore 6
    //   130: ldc_w 413
    //   133: invokestatic 378	cn/com/xy/sms/sdk/util/d:f	(Ljava/lang/String;)Ljava/lang/String;
    //   136: astore 7
    //   138: aload 6
    //   140: invokestatic 116	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   143: ifne +17 -> 160
    //   146: aload 6
    //   148: aload 7
    //   150: invokestatic 339	cn/com/xy/sms/sdk/util/e:a	(Ljava/lang/String;Ljava/lang/String;)Z
    //   153: istore 5
    //   155: iload 5
    //   157: ifeq +39 -> 196
    //   160: invokestatic 83	cn/com/xy/sms/sdk/constant/Constant:getContext	()Landroid/content/Context;
    //   163: invokevirtual 384	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   166: invokevirtual 390	android/content/res/Resources:getAssets	()Landroid/content/res/AssetManager;
    //   169: ldc_w 415
    //   172: invokevirtual 397	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   175: ldc_w 415
    //   178: ldc_w 417
    //   181: invokestatic 420	cn/com/xy/sms/sdk/constant/Constant:getPath	(Ljava/lang/String;)Ljava/lang/String;
    //   184: invokestatic 406	cn/com/xy/sms/sdk/util/XyUtil:unZip	(Ljava/io/InputStream;Ljava/lang/String;Ljava/lang/String;)V
    //   187: ldc_w 411
    //   190: aload 7
    //   192: invokestatic 364	cn/com/xy/sms/sdk/db/entity/SysParamEntityManager:setParam	(Ljava/lang/String;Ljava/lang/String;)J
    //   195: pop2
    //   196: invokestatic 83	cn/com/xy/sms/sdk/constant/Constant:getContext	()Landroid/content/Context;
    //   199: ldc_w 422
    //   202: invokestatic 91	cn/com/xy/sms/sdk/db/entity/SysParamEntityManager:getStringParam	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   205: astore 7
    //   207: ldc_w 424
    //   210: invokestatic 378	cn/com/xy/sms/sdk/util/d:f	(Ljava/lang/String;)Ljava/lang/String;
    //   213: astore 6
    //   215: aload 7
    //   217: invokestatic 116	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   220: ifne +17 -> 237
    //   223: aload 7
    //   225: aload 6
    //   227: invokestatic 339	cn/com/xy/sms/sdk/util/e:a	(Ljava/lang/String;Ljava/lang/String;)Z
    //   230: istore 5
    //   232: iload 5
    //   234: ifeq +69 -> 303
    //   237: invokestatic 83	cn/com/xy/sms/sdk/constant/Constant:getContext	()Landroid/content/Context;
    //   240: invokevirtual 384	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   243: invokevirtual 390	android/content/res/Resources:getAssets	()Landroid/content/res/AssetManager;
    //   246: ldc_w 426
    //   249: invokevirtual 397	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   252: ldc_w 428
    //   255: invokestatic 230	cn/com/xy/sms/sdk/constant/Constant:getDRAWBLE_PATH	()Ljava/lang/String;
    //   258: invokestatic 406	cn/com/xy/sms/sdk/util/XyUtil:unZip	(Ljava/io/InputStream;Ljava/lang/String;Ljava/lang/String;)V
    //   261: new 227	java/lang/StringBuilder
    //   264: dup
    //   265: invokestatic 230	cn/com/xy/sms/sdk/constant/Constant:getDRAWBLE_PATH	()Ljava/lang/String;
    //   268: invokestatic 234	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   271: invokespecial 235	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   274: getstatic 431	java/io/File:separator	Ljava/lang/String;
    //   277: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   280: ldc_w 433
    //   283: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   286: invokevirtual 242	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   289: astore 7
    //   291: aload 7
    //   293: invokestatic 246	cn/com/xy/sms/sdk/util/d:a	(Ljava/lang/String;)Z
    //   296: istore 5
    //   298: iload 5
    //   300: ifne +169 -> 469
    //   303: ldc_w 435
    //   306: iconst_0
    //   307: invokestatic 437	cn/com/xy/sms/sdk/util/e:a	(Ljava/lang/String;I)V
    //   310: ldc_w 439
    //   313: iconst_1
    //   314: invokestatic 437	cn/com/xy/sms/sdk/util/e:a	(Ljava/lang/String;I)V
    //   317: invokestatic 369	java/lang/System:currentTimeMillis	()J
    //   320: lstore_3
    //   321: ldc_w 441
    //   324: new 227	java/lang/StringBuilder
    //   327: dup
    //   328: ldc_w 443
    //   331: invokespecial 235	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   334: lload_3
    //   335: lload_1
    //   336: lsub
    //   337: invokevirtual 446	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   340: invokevirtual 242	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   343: aconst_null
    //   344: invokestatic 452	cn/com/xy/sms/sdk/log/LogManager:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   347: ldc_w 358
    //   350: ldc 93
    //   352: invokestatic 364	cn/com/xy/sms/sdk/db/entity/SysParamEntityManager:setParam	(Ljava/lang/String;Ljava/lang/String;)J
    //   355: pop2
    //   356: invokestatic 458	cn/com/xy/sms/sdk/util/DuoquUtils:getSdkDoAction	()Lcn/com/xy/sms/sdk/action/AbsSdkDoAction;
    //   359: iconst_0
    //   360: aconst_null
    //   361: invokevirtual 464	cn/com/xy/sms/sdk/action/AbsSdkDoAction:onEventCallback	(ILjava/util/Map;)V
    //   364: return
    //   365: astore 7
    //   367: aload 6
    //   369: monitorexit
    //   370: aload 7
    //   372: athrow
    //   373: astore 6
    //   375: aload 6
    //   377: invokevirtual 140	java/lang/Throwable:printStackTrace	()V
    //   380: goto -261 -> 119
    //   383: astore 6
    //   385: aload 6
    //   387: invokevirtual 140	java/lang/Throwable:printStackTrace	()V
    //   390: ldc_w 441
    //   393: new 227	java/lang/StringBuilder
    //   396: dup
    //   397: ldc_w 466
    //   400: invokespecial 235	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   403: aload 6
    //   405: invokevirtual 469	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   408: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   411: invokevirtual 242	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   414: aload 6
    //   416: invokestatic 452	cn/com/xy/sms/sdk/log/LogManager:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   419: ldc_w 358
    //   422: ldc 93
    //   424: invokestatic 364	cn/com/xy/sms/sdk/db/entity/SysParamEntityManager:setParam	(Ljava/lang/String;Ljava/lang/String;)J
    //   427: pop2
    //   428: invokestatic 458	cn/com/xy/sms/sdk/util/DuoquUtils:getSdkDoAction	()Lcn/com/xy/sms/sdk/action/AbsSdkDoAction;
    //   431: iconst_0
    //   432: aconst_null
    //   433: invokevirtual 464	cn/com/xy/sms/sdk/action/AbsSdkDoAction:onEventCallback	(ILjava/util/Map;)V
    //   436: return
    //   437: astore 6
    //   439: aload 6
    //   441: invokevirtual 140	java/lang/Throwable:printStackTrace	()V
    //   444: goto -248 -> 196
    //   447: astore 6
    //   449: ldc_w 358
    //   452: ldc 93
    //   454: invokestatic 364	cn/com/xy/sms/sdk/db/entity/SysParamEntityManager:setParam	(Ljava/lang/String;Ljava/lang/String;)J
    //   457: pop2
    //   458: invokestatic 458	cn/com/xy/sms/sdk/util/DuoquUtils:getSdkDoAction	()Lcn/com/xy/sms/sdk/action/AbsSdkDoAction;
    //   461: iconst_0
    //   462: aconst_null
    //   463: invokevirtual 464	cn/com/xy/sms/sdk/action/AbsSdkDoAction:onEventCallback	(ILjava/util/Map;)V
    //   466: aload 6
    //   468: athrow
    //   469: bipush 10
    //   471: invokestatic 474	cn/com/xy/sms/sdk/db/entity/o:b	(I)I
    //   474: istore_0
    //   475: iload_0
    //   476: iconst_m1
    //   477: if_icmpeq -174 -> 303
    //   480: aload 7
    //   482: iconst_1
    //   483: invokestatic 480	cn/com/xy/sms/sdk/db/DBManager:excSql	(Ljava/lang/String;Z)V
    //   486: ldc_w 482
    //   489: ldc_w 484
    //   492: invokestatic 364	cn/com/xy/sms/sdk/db/entity/SysParamEntityManager:setParam	(Ljava/lang/String;Ljava/lang/String;)J
    //   495: pop2
    //   496: ldc_w 422
    //   499: aload 6
    //   501: invokestatic 364	cn/com/xy/sms/sdk/db/entity/SysParamEntityManager:setParam	(Ljava/lang/String;Ljava/lang/String;)J
    //   504: pop2
    //   505: invokestatic 485	cn/com/xy/sms/sdk/db/entity/o:b	()V
    //   508: iconst_0
    //   509: invokestatic 474	cn/com/xy/sms/sdk/db/entity/o:b	(I)I
    //   512: pop
    //   513: goto -210 -> 303
    //   516: astore 6
    //   518: goto -215 -> 303
    //   521: astore 6
    //   523: aload 6
    //   525: invokevirtual 140	java/lang/Throwable:printStackTrace	()V
    //   528: invokestatic 485	cn/com/xy/sms/sdk/db/entity/o:b	()V
    //   531: goto -23 -> 508
    //   534: astore 6
    //   536: aload 6
    //   538: invokevirtual 140	java/lang/Throwable:printStackTrace	()V
    //   541: goto -238 -> 303
    //   544: astore 6
    //   546: invokestatic 485	cn/com/xy/sms/sdk/db/entity/o:b	()V
    //   549: aload 6
    //   551: athrow
    //   552: astore 6
    //   554: goto -358 -> 196
    //   557: astore 6
    //   559: goto -440 -> 119
    // Local variable table:
    //   start	length	slot	name	signature
    //   474	4	0	i	int
    //   38	298	1	l1	long
    //   320	15	3	l2	long
    //   76	223	5	bool	boolean
    //   3	365	6	localObject1	Object
    //   373	3	6	localThrowable1	Throwable
    //   383	32	6	localThrowable2	Throwable
    //   437	3	6	localThrowable3	Throwable
    //   447	53	6	str1	String
    //   516	1	6	localIOException1	IOException
    //   521	3	6	localThrowable4	Throwable
    //   534	3	6	localThrowable5	Throwable
    //   544	6	6	localObject2	Object
    //   552	1	6	localIOException2	IOException
    //   557	1	6	localIOException3	IOException
    //   59	233	7	str2	String
    //   365	116	7	str3	String
    // Exception table:
    //   from	to	target	type
    //   8	17	365	finally
    //   18	25	365	finally
    //   83	119	373	java/lang/Throwable
    //   25	78	383	java/lang/Throwable
    //   119	155	383	java/lang/Throwable
    //   196	232	383	java/lang/Throwable
    //   303	347	383	java/lang/Throwable
    //   375	380	383	java/lang/Throwable
    //   439	444	383	java/lang/Throwable
    //   536	541	383	java/lang/Throwable
    //   160	196	437	java/lang/Throwable
    //   25	78	447	finally
    //   83	119	447	finally
    //   119	155	447	finally
    //   160	196	447	finally
    //   196	232	447	finally
    //   237	298	447	finally
    //   303	347	447	finally
    //   375	380	447	finally
    //   385	419	447	finally
    //   439	444	447	finally
    //   469	475	447	finally
    //   505	508	447	finally
    //   508	513	447	finally
    //   528	531	447	finally
    //   536	541	447	finally
    //   546	552	447	finally
    //   237	298	516	java/io/IOException
    //   469	475	516	java/io/IOException
    //   505	508	516	java/io/IOException
    //   508	513	516	java/io/IOException
    //   528	531	516	java/io/IOException
    //   546	552	516	java/io/IOException
    //   480	505	521	java/lang/Throwable
    //   237	298	534	java/lang/Throwable
    //   469	475	534	java/lang/Throwable
    //   505	508	534	java/lang/Throwable
    //   508	513	534	java/lang/Throwable
    //   528	531	534	java/lang/Throwable
    //   546	552	534	java/lang/Throwable
    //   480	505	544	finally
    //   523	528	544	finally
    //   160	196	552	java/io/IOException
    //   83	119	557	java/io/IOException
  }
  
  /* Error */
  private static void b(File paramFile)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore_3
    //   5: aconst_null
    //   6: astore_1
    //   7: aconst_null
    //   8: astore 4
    //   10: aload_0
    //   11: ifnonnull +4 -> 15
    //   14: return
    //   15: new 158	java/io/BufferedReader
    //   18: dup
    //   19: new 160	java/io/FileReader
    //   22: dup
    //   23: aload_0
    //   24: invokespecial 162	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   27: invokespecial 165	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   30: astore_2
    //   31: invokestatic 170	cn/com/xy/sms/sdk/db/a/a:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   34: astore_0
    //   35: aload_0
    //   36: astore_1
    //   37: aload 4
    //   39: astore 5
    //   41: aload_3
    //   42: astore_0
    //   43: aload_1
    //   44: astore_3
    //   45: aload_2
    //   46: astore 4
    //   48: aload_1
    //   49: ldc -84
    //   51: invokevirtual 178	android/database/sqlite/SQLiteDatabase:compileStatement	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteStatement;
    //   54: astore 6
    //   56: aload 6
    //   58: astore 5
    //   60: aload 6
    //   62: astore_0
    //   63: aload_1
    //   64: astore_3
    //   65: aload_2
    //   66: astore 4
    //   68: aload_1
    //   69: invokevirtual 181	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   72: aload 6
    //   74: astore 5
    //   76: aload 6
    //   78: astore_0
    //   79: aload_1
    //   80: astore_3
    //   81: aload_2
    //   82: astore 4
    //   84: aload_2
    //   85: invokevirtual 185	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   88: astore 8
    //   90: aload 8
    //   92: ifnonnull +47 -> 139
    //   95: aload_1
    //   96: ifnull +32 -> 128
    //   99: aload_1
    //   100: invokevirtual 188	android/database/sqlite/SQLiteDatabase:inTransaction	()Z
    //   103: ifeq +11 -> 114
    //   106: aload_1
    //   107: invokevirtual 191	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   110: aload_1
    //   111: invokevirtual 194	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   114: aload 6
    //   116: ifnull +8 -> 124
    //   119: aload 6
    //   121: invokevirtual 199	android/database/sqlite/SQLiteStatement:close	()V
    //   124: aload_1
    //   125: invokestatic 202	cn/com/xy/sms/sdk/db/a/a:a	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   128: aload_2
    //   129: invokevirtual 203	java/io/BufferedReader:close	()V
    //   132: return
    //   133: astore_0
    //   134: aload_0
    //   135: invokevirtual 224	java/io/IOException:printStackTrace	()V
    //   138: return
    //   139: aload 6
    //   141: astore_0
    //   142: aload_1
    //   143: astore_3
    //   144: aload_2
    //   145: astore 4
    //   147: aload 8
    //   149: iconst_0
    //   150: aload 8
    //   152: ldc -51
    //   154: invokevirtual 208	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   157: invokevirtual 56	java/lang/String:substring	(II)Ljava/lang/String;
    //   160: invokevirtual 211	java/lang/String:trim	()Ljava/lang/String;
    //   163: astore 7
    //   165: aload 6
    //   167: astore_0
    //   168: aload_1
    //   169: astore_3
    //   170: aload_2
    //   171: astore 4
    //   173: aload 8
    //   175: aload 8
    //   177: ldc -51
    //   179: invokevirtual 208	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   182: iconst_1
    //   183: iadd
    //   184: aload 8
    //   186: invokevirtual 215	java/lang/String:length	()I
    //   189: invokevirtual 56	java/lang/String:substring	(II)Ljava/lang/String;
    //   192: astore 5
    //   194: aload 6
    //   196: astore_0
    //   197: aload_1
    //   198: astore_3
    //   199: aload_2
    //   200: astore 4
    //   202: aload 5
    //   204: iconst_0
    //   205: aload 5
    //   207: ldc -51
    //   209: invokevirtual 208	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   212: invokevirtual 56	java/lang/String:substring	(II)Ljava/lang/String;
    //   215: invokevirtual 211	java/lang/String:trim	()Ljava/lang/String;
    //   218: astore 8
    //   220: aload 6
    //   222: astore_0
    //   223: aload_1
    //   224: astore_3
    //   225: aload_2
    //   226: astore 4
    //   228: aload 5
    //   230: aload 5
    //   232: ldc -51
    //   234: invokevirtual 208	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   237: iconst_1
    //   238: iadd
    //   239: aload 5
    //   241: invokevirtual 215	java/lang/String:length	()I
    //   244: invokevirtual 56	java/lang/String:substring	(II)Ljava/lang/String;
    //   247: astore 5
    //   249: aload 6
    //   251: astore_0
    //   252: aload_1
    //   253: astore_3
    //   254: aload_2
    //   255: astore 4
    //   257: aload 5
    //   259: iconst_0
    //   260: aload 5
    //   262: ldc -51
    //   264: invokevirtual 208	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   267: invokevirtual 56	java/lang/String:substring	(II)Ljava/lang/String;
    //   270: invokevirtual 211	java/lang/String:trim	()Ljava/lang/String;
    //   273: astore 9
    //   275: aload 6
    //   277: astore_0
    //   278: aload_1
    //   279: astore_3
    //   280: aload_2
    //   281: astore 4
    //   283: aload 5
    //   285: aload 5
    //   287: ldc -51
    //   289: invokevirtual 208	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   292: iconst_1
    //   293: iadd
    //   294: aload 5
    //   296: invokevirtual 215	java/lang/String:length	()I
    //   299: invokevirtual 56	java/lang/String:substring	(II)Ljava/lang/String;
    //   302: astore 5
    //   304: aload 6
    //   306: astore_0
    //   307: aload_1
    //   308: astore_3
    //   309: aload_2
    //   310: astore 4
    //   312: aload 5
    //   314: iconst_0
    //   315: aload 5
    //   317: ldc -51
    //   319: invokevirtual 208	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   322: invokevirtual 56	java/lang/String:substring	(II)Ljava/lang/String;
    //   325: invokevirtual 211	java/lang/String:trim	()Ljava/lang/String;
    //   328: astore 10
    //   330: aload 6
    //   332: astore_0
    //   333: aload_1
    //   334: astore_3
    //   335: aload_2
    //   336: astore 4
    //   338: aload 5
    //   340: aload 5
    //   342: ldc -51
    //   344: invokevirtual 208	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   347: iconst_1
    //   348: iadd
    //   349: aload 5
    //   351: invokevirtual 215	java/lang/String:length	()I
    //   354: invokevirtual 56	java/lang/String:substring	(II)Ljava/lang/String;
    //   357: invokevirtual 211	java/lang/String:trim	()Ljava/lang/String;
    //   360: astore 11
    //   362: aload 6
    //   364: astore 5
    //   366: aload 6
    //   368: astore_0
    //   369: aload_1
    //   370: astore_3
    //   371: aload_2
    //   372: astore 4
    //   374: aload 6
    //   376: iconst_1
    //   377: aload 7
    //   379: invokevirtual 219	android/database/sqlite/SQLiteStatement:bindString	(ILjava/lang/String;)V
    //   382: aload 6
    //   384: astore 5
    //   386: aload 6
    //   388: astore_0
    //   389: aload_1
    //   390: astore_3
    //   391: aload_2
    //   392: astore 4
    //   394: aload 6
    //   396: iconst_2
    //   397: aload 8
    //   399: invokevirtual 219	android/database/sqlite/SQLiteStatement:bindString	(ILjava/lang/String;)V
    //   402: aload 6
    //   404: astore 5
    //   406: aload 6
    //   408: astore_0
    //   409: aload_1
    //   410: astore_3
    //   411: aload_2
    //   412: astore 4
    //   414: aload 6
    //   416: iconst_3
    //   417: aload 11
    //   419: invokevirtual 219	android/database/sqlite/SQLiteStatement:bindString	(ILjava/lang/String;)V
    //   422: aload 6
    //   424: astore 5
    //   426: aload 6
    //   428: astore_0
    //   429: aload_1
    //   430: astore_3
    //   431: aload_2
    //   432: astore 4
    //   434: aload 6
    //   436: iconst_4
    //   437: aload 10
    //   439: invokevirtual 219	android/database/sqlite/SQLiteStatement:bindString	(ILjava/lang/String;)V
    //   442: aload 6
    //   444: astore 5
    //   446: aload 6
    //   448: astore_0
    //   449: aload_1
    //   450: astore_3
    //   451: aload_2
    //   452: astore 4
    //   454: aload 6
    //   456: iconst_5
    //   457: aload 9
    //   459: invokevirtual 219	android/database/sqlite/SQLiteStatement:bindString	(ILjava/lang/String;)V
    //   462: aload 6
    //   464: astore 5
    //   466: aload 6
    //   468: astore_0
    //   469: aload_1
    //   470: astore_3
    //   471: aload_2
    //   472: astore 4
    //   474: aload 6
    //   476: invokevirtual 223	android/database/sqlite/SQLiteStatement:executeInsert	()J
    //   479: pop2
    //   480: goto -408 -> 72
    //   483: astore 6
    //   485: aload 5
    //   487: astore_0
    //   488: aload_1
    //   489: astore_3
    //   490: aload_2
    //   491: astore 4
    //   493: aload 6
    //   495: invokevirtual 140	java/lang/Throwable:printStackTrace	()V
    //   498: aload_1
    //   499: ifnull +32 -> 531
    //   502: aload_1
    //   503: invokevirtual 188	android/database/sqlite/SQLiteDatabase:inTransaction	()Z
    //   506: ifeq +11 -> 517
    //   509: aload_1
    //   510: invokevirtual 191	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   513: aload_1
    //   514: invokevirtual 194	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   517: aload 5
    //   519: ifnull +8 -> 527
    //   522: aload 5
    //   524: invokevirtual 199	android/database/sqlite/SQLiteStatement:close	()V
    //   527: aload_1
    //   528: invokestatic 202	cn/com/xy/sms/sdk/db/a/a:a	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   531: aload_2
    //   532: ifnull -518 -> 14
    //   535: aload_2
    //   536: invokevirtual 203	java/io/BufferedReader:close	()V
    //   539: return
    //   540: astore_0
    //   541: aload_0
    //   542: invokevirtual 224	java/io/IOException:printStackTrace	()V
    //   545: return
    //   546: astore 7
    //   548: aload 6
    //   550: astore 5
    //   552: aload 6
    //   554: astore_0
    //   555: aload_1
    //   556: astore_3
    //   557: aload_2
    //   558: astore 4
    //   560: aload 7
    //   562: invokevirtual 140	java/lang/Throwable:printStackTrace	()V
    //   565: goto -493 -> 72
    //   568: astore 5
    //   570: aload 4
    //   572: astore_2
    //   573: aload_0
    //   574: astore_1
    //   575: aload 5
    //   577: astore_0
    //   578: aload_3
    //   579: ifnull +30 -> 609
    //   582: aload_3
    //   583: invokevirtual 188	android/database/sqlite/SQLiteDatabase:inTransaction	()Z
    //   586: ifeq +11 -> 597
    //   589: aload_3
    //   590: invokevirtual 191	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   593: aload_3
    //   594: invokevirtual 194	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   597: aload_1
    //   598: ifnull +7 -> 605
    //   601: aload_1
    //   602: invokevirtual 199	android/database/sqlite/SQLiteStatement:close	()V
    //   605: aload_3
    //   606: invokestatic 202	cn/com/xy/sms/sdk/db/a/a:a	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   609: aload_2
    //   610: ifnull +7 -> 617
    //   613: aload_2
    //   614: invokevirtual 203	java/io/BufferedReader:close	()V
    //   617: aload_0
    //   618: athrow
    //   619: astore_0
    //   620: aload_0
    //   621: invokevirtual 140	java/lang/Throwable:printStackTrace	()V
    //   624: goto -107 -> 517
    //   627: astore 4
    //   629: aload 4
    //   631: invokevirtual 140	java/lang/Throwable:printStackTrace	()V
    //   634: goto -37 -> 597
    //   637: astore_1
    //   638: aload_1
    //   639: invokevirtual 224	java/io/IOException:printStackTrace	()V
    //   642: goto -25 -> 617
    //   645: astore_0
    //   646: aload_0
    //   647: invokevirtual 140	java/lang/Throwable:printStackTrace	()V
    //   650: goto -536 -> 114
    //   653: astore_0
    //   654: aconst_null
    //   655: astore_3
    //   656: aconst_null
    //   657: astore_2
    //   658: goto -80 -> 578
    //   661: astore_0
    //   662: aconst_null
    //   663: astore_3
    //   664: goto -86 -> 578
    //   667: astore 6
    //   669: aconst_null
    //   670: astore_1
    //   671: aconst_null
    //   672: astore_2
    //   673: goto -188 -> 485
    //   676: astore 6
    //   678: aconst_null
    //   679: astore_1
    //   680: goto -195 -> 485
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	683	0	paramFile	File
    //   6	596	1	localFile	File
    //   637	2	1	localIOException	IOException
    //   670	10	1	localObject1	Object
    //   30	643	2	localObject2	Object
    //   4	660	3	localObject3	Object
    //   8	563	4	localObject4	Object
    //   627	3	4	localThrowable1	Throwable
    //   1	550	5	localObject5	Object
    //   568	8	5	localObject6	Object
    //   54	421	6	localSQLiteStatement	android.database.sqlite.SQLiteStatement
    //   483	70	6	localThrowable2	Throwable
    //   667	1	6	localThrowable3	Throwable
    //   676	1	6	localThrowable4	Throwable
    //   163	215	7	str1	String
    //   546	15	7	localThrowable5	Throwable
    //   88	310	8	str2	String
    //   273	185	9	str3	String
    //   328	110	10	str4	String
    //   360	58	11	str5	String
    // Exception table:
    //   from	to	target	type
    //   128	132	133	java/io/IOException
    //   48	56	483	java/lang/Throwable
    //   68	72	483	java/lang/Throwable
    //   84	90	483	java/lang/Throwable
    //   374	382	483	java/lang/Throwable
    //   394	402	483	java/lang/Throwable
    //   414	422	483	java/lang/Throwable
    //   434	442	483	java/lang/Throwable
    //   454	462	483	java/lang/Throwable
    //   474	480	483	java/lang/Throwable
    //   560	565	483	java/lang/Throwable
    //   535	539	540	java/io/IOException
    //   147	165	546	java/lang/Throwable
    //   173	194	546	java/lang/Throwable
    //   202	220	546	java/lang/Throwable
    //   228	249	546	java/lang/Throwable
    //   257	275	546	java/lang/Throwable
    //   283	304	546	java/lang/Throwable
    //   312	330	546	java/lang/Throwable
    //   338	362	546	java/lang/Throwable
    //   48	56	568	finally
    //   68	72	568	finally
    //   84	90	568	finally
    //   147	165	568	finally
    //   173	194	568	finally
    //   202	220	568	finally
    //   228	249	568	finally
    //   257	275	568	finally
    //   283	304	568	finally
    //   312	330	568	finally
    //   338	362	568	finally
    //   374	382	568	finally
    //   394	402	568	finally
    //   414	422	568	finally
    //   434	442	568	finally
    //   454	462	568	finally
    //   474	480	568	finally
    //   493	498	568	finally
    //   560	565	568	finally
    //   502	517	619	java/lang/Throwable
    //   582	597	627	java/lang/Throwable
    //   613	617	637	java/io/IOException
    //   99	114	645	java/lang/Throwable
    //   15	31	653	finally
    //   31	35	661	finally
    //   15	31	667	java/lang/Throwable
    //   31	35	676	java/lang/Throwable
  }
  
  /* Error */
  public static void c()
  {
    // Byte code:
    //   0: ldc_w 487
    //   3: invokestatic 378	cn/com/xy/sms/sdk/util/d:f	(Ljava/lang/String;)Ljava/lang/String;
    //   6: astore_3
    //   7: ldc_w 489
    //   10: aload_3
    //   11: invokevirtual 96	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   14: ifne +152 -> 166
    //   17: aload_3
    //   18: ldc_w 491
    //   21: invokevirtual 494	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   24: istore_2
    //   25: iload_2
    //   26: ifeq +140 -> 166
    //   29: iconst_0
    //   30: istore_0
    //   31: iload_0
    //   32: ifeq +239 -> 271
    //   35: ldc_w 304
    //   38: invokestatic 497	cn/com/xy/sms/sdk/db/entity/e:a	(Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/entity/d;
    //   41: astore 5
    //   43: aconst_null
    //   44: astore 4
    //   46: aload 4
    //   48: astore_3
    //   49: aload 5
    //   51: ifnull +56 -> 107
    //   54: aload 4
    //   56: astore_3
    //   57: aload 5
    //   59: getfield 501	cn/com/xy/sms/sdk/db/entity/d:c	Ljava/lang/String;
    //   62: invokestatic 116	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   65: ifne +42 -> 107
    //   68: new 227	java/lang/StringBuilder
    //   71: dup
    //   72: ldc_w 503
    //   75: invokespecial 235	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   78: aload 5
    //   80: getfield 505	cn/com/xy/sms/sdk/db/entity/d:b	Ljava/lang/String;
    //   83: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   86: ldc_w 507
    //   89: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   92: aload 5
    //   94: getfield 501	cn/com/xy/sms/sdk/db/entity/d:c	Ljava/lang/String;
    //   97: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   100: pop
    //   101: aload 5
    //   103: getfield 501	cn/com/xy/sms/sdk/db/entity/d:c	Ljava/lang/String;
    //   106: astore_3
    //   107: ldc_w 487
    //   110: invokestatic 378	cn/com/xy/sms/sdk/util/d:f	(Ljava/lang/String;)Ljava/lang/String;
    //   113: astore 4
    //   115: new 227	java/lang/StringBuilder
    //   118: dup
    //   119: ldc_w 509
    //   122: invokespecial 235	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   125: aload_3
    //   126: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   129: ldc_w 511
    //   132: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   135: aload 4
    //   137: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: pop
    //   141: aload_3
    //   142: invokestatic 116	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   145: ifne +26 -> 171
    //   148: aload_3
    //   149: aload 4
    //   151: invokestatic 339	cn/com/xy/sms/sdk/util/e:a	(Ljava/lang/String;Ljava/lang/String;)Z
    //   154: istore_2
    //   155: iload_2
    //   156: ifne +15 -> 171
    //   159: invokestatic 514	cn/com/xy/sms/sdk/constant/Constant:getTempPARSE_PATH	()Ljava/lang/String;
    //   162: invokestatic 516	cn/com/xy/sms/sdk/util/d:d	(Ljava/lang/String;)V
    //   165: return
    //   166: iconst_1
    //   167: istore_0
    //   168: goto -137 -> 31
    //   171: iconst_0
    //   172: invokestatic 520	cn/com/xy/sms/sdk/db/entity/SysParamEntityManager:clearOldData	(Z)V
    //   175: ldc_w 522
    //   178: ldc_w 360
    //   181: invokestatic 364	cn/com/xy/sms/sdk/db/entity/SysParamEntityManager:setParam	(Ljava/lang/String;Ljava/lang/String;)J
    //   184: pop2
    //   185: invokestatic 83	cn/com/xy/sms/sdk/constant/Constant:getContext	()Landroid/content/Context;
    //   188: invokevirtual 384	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   191: invokevirtual 390	android/content/res/Resources:getAssets	()Landroid/content/res/AssetManager;
    //   194: ldc_w 524
    //   197: invokevirtual 397	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   200: ldc_w 526
    //   203: invokestatic 529	cn/com/xy/sms/sdk/constant/Constant:getPARSE_PATH	()Ljava/lang/String;
    //   206: iconst_1
    //   207: aload 4
    //   209: iconst_1
    //   210: invokestatic 532	cn/com/xy/sms/sdk/util/XyUtil:unZip	(Ljava/io/InputStream;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Z)V
    //   213: invokestatic 83	cn/com/xy/sms/sdk/constant/Constant:getContext	()Landroid/content/Context;
    //   216: invokestatic 537	cn/com/xy/sms/sdk/db/ParseItemManager:updateParse	(Landroid/content/Context;)V
    //   219: ldc_w 522
    //   222: ldc 93
    //   224: invokestatic 364	cn/com/xy/sms/sdk/db/entity/SysParamEntityManager:setParam	(Ljava/lang/String;Ljava/lang/String;)J
    //   227: pop2
    //   228: goto -69 -> 159
    //   231: astore_3
    //   232: aload_3
    //   233: invokevirtual 140	java/lang/Throwable:printStackTrace	()V
    //   236: goto -77 -> 159
    //   239: astore_3
    //   240: aload_3
    //   241: invokevirtual 140	java/lang/Throwable:printStackTrace	()V
    //   244: goto -85 -> 159
    //   247: astore_3
    //   248: aload_3
    //   249: invokevirtual 140	java/lang/Throwable:printStackTrace	()V
    //   252: invokestatic 514	cn/com/xy/sms/sdk/constant/Constant:getTempPARSE_PATH	()Ljava/lang/String;
    //   255: invokestatic 516	cn/com/xy/sms/sdk/util/d:d	(Ljava/lang/String;)V
    //   258: return
    //   259: astore_3
    //   260: aload_3
    //   261: invokevirtual 140	java/lang/Throwable:printStackTrace	()V
    //   264: return
    //   265: astore_3
    //   266: aload_3
    //   267: invokevirtual 140	java/lang/Throwable:printStackTrace	()V
    //   270: return
    //   271: invokestatic 541	cn/com/xy/sms/sdk/util/e:j	()Ljava/util/HashMap;
    //   274: astore 6
    //   276: aload 6
    //   278: ifnonnull +26 -> 304
    //   281: ldc_w 543
    //   284: ldc_w 545
    //   287: aconst_null
    //   288: invokestatic 452	cn/com/xy/sms/sdk/log/LogManager:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   291: invokestatic 514	cn/com/xy/sms/sdk/constant/Constant:getTempPARSE_PATH	()Ljava/lang/String;
    //   294: invokestatic 516	cn/com/xy/sms/sdk/util/d:d	(Ljava/lang/String;)V
    //   297: return
    //   298: astore_3
    //   299: aload_3
    //   300: invokevirtual 140	java/lang/Throwable:printStackTrace	()V
    //   303: return
    //   304: aload 6
    //   306: invokevirtual 330	java/util/HashMap:isEmpty	()Z
    //   309: istore_2
    //   310: iload_2
    //   311: ifeq +16 -> 327
    //   314: invokestatic 514	cn/com/xy/sms/sdk/constant/Constant:getTempPARSE_PATH	()Ljava/lang/String;
    //   317: invokestatic 516	cn/com/xy/sms/sdk/util/d:d	(Ljava/lang/String;)V
    //   320: return
    //   321: astore_3
    //   322: aload_3
    //   323: invokevirtual 140	java/lang/Throwable:printStackTrace	()V
    //   326: return
    //   327: ldc_w 522
    //   330: ldc_w 360
    //   333: invokestatic 364	cn/com/xy/sms/sdk/db/entity/SysParamEntityManager:setParam	(Ljava/lang/String;Ljava/lang/String;)J
    //   336: pop2
    //   337: invokestatic 514	cn/com/xy/sms/sdk/constant/Constant:getTempPARSE_PATH	()Ljava/lang/String;
    //   340: astore 9
    //   342: invokestatic 529	cn/com/xy/sms/sdk/constant/Constant:getPARSE_PATH	()Ljava/lang/String;
    //   345: astore 10
    //   347: invokestatic 83	cn/com/xy/sms/sdk/constant/Constant:getContext	()Landroid/content/Context;
    //   350: invokevirtual 384	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   353: invokevirtual 390	android/content/res/Resources:getAssets	()Landroid/content/res/AssetManager;
    //   356: ldc_w 524
    //   359: invokevirtual 397	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   362: ldc_w 526
    //   365: aload 9
    //   367: iconst_0
    //   368: aconst_null
    //   369: iconst_0
    //   370: invokestatic 532	cn/com/xy/sms/sdk/util/XyUtil:unZip	(Ljava/io/InputStream;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Z)V
    //   373: aload 6
    //   375: invokevirtual 549	java/util/HashMap:entrySet	()Ljava/util/Set;
    //   378: astore_3
    //   379: aload 10
    //   381: invokestatic 551	cn/com/xy/sms/sdk/util/e:a	(Ljava/lang/String;)Ljava/util/List;
    //   384: astore 5
    //   386: new 43	java/util/ArrayList
    //   389: dup
    //   390: invokespecial 44	java/util/ArrayList:<init>	()V
    //   393: astore 7
    //   395: new 227	java/lang/StringBuilder
    //   398: dup
    //   399: invokespecial 552	java/lang/StringBuilder:<init>	()V
    //   402: astore 8
    //   404: invokestatic 558	java/util/concurrent/Executors:newSingleThreadExecutor	()Ljava/util/concurrent/ExecutorService;
    //   407: astore 4
    //   409: aload 5
    //   411: ifnull +17 -> 428
    //   414: aload 4
    //   416: new 560	cn/com/xy/sms/sdk/util/g
    //   419: dup
    //   420: invokespecial 561	cn/com/xy/sms/sdk/util/g:<init>	()V
    //   423: invokeinterface 567 2 0
    //   428: aload_3
    //   429: invokeinterface 573 1 0
    //   434: astore 11
    //   436: iconst_0
    //   437: istore_0
    //   438: aload 11
    //   440: invokeinterface 578 1 0
    //   445: istore_2
    //   446: iload_2
    //   447: ifne +257 -> 704
    //   450: aload 5
    //   452: ifnull +17 -> 469
    //   455: aload 4
    //   457: new 580	cn/com/xy/sms/sdk/util/f
    //   460: dup
    //   461: invokespecial 581	cn/com/xy/sms/sdk/util/f:<init>	()V
    //   464: invokeinterface 567 2 0
    //   469: aload 4
    //   471: invokeinterface 584 1 0
    //   476: aload 8
    //   478: invokevirtual 585	java/lang/StringBuilder:length	()I
    //   481: ifle +29 -> 510
    //   484: aload 8
    //   486: aload 8
    //   488: invokevirtual 585	java/lang/StringBuilder:length	()I
    //   491: bipush 10
    //   493: isub
    //   494: invokevirtual 589	java/lang/StringBuilder:setLength	(I)V
    //   497: aload 7
    //   499: aload 8
    //   501: invokevirtual 242	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   504: invokeinterface 62 2 0
    //   509: pop
    //   510: aload 7
    //   512: invokeinterface 592 1 0
    //   517: ifle +9 -> 526
    //   520: aload 7
    //   522: invokestatic 595	cn/com/xy/sms/sdk/db/entity/e:b	(Ljava/util/List;)Z
    //   525: pop
    //   526: ldc_w 597
    //   529: invokestatic 369	java/lang/System:currentTimeMillis	()J
    //   532: invokestatic 600	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   535: invokestatic 364	cn/com/xy/sms/sdk/db/entity/SysParamEntityManager:setParam	(Ljava/lang/String;Ljava/lang/String;)J
    //   538: pop2
    //   539: aload 6
    //   541: ldc_w 602
    //   544: invokevirtual 333	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   547: ifeq +119 -> 666
    //   550: invokestatic 605	cn/com/xy/sms/sdk/db/entity/SysParamEntityManager:deleteOldFile	()V
    //   553: new 43	java/util/ArrayList
    //   556: dup
    //   557: invokespecial 44	java/util/ArrayList:<init>	()V
    //   560: astore_3
    //   561: aload_3
    //   562: ldc_w 607
    //   565: invokeinterface 62 2 0
    //   570: pop
    //   571: aload_3
    //   572: ldc_w 609
    //   575: invokeinterface 62 2 0
    //   580: pop
    //   581: aload_3
    //   582: ldc_w 611
    //   585: invokeinterface 62 2 0
    //   590: pop
    //   591: aload_3
    //   592: ldc_w 613
    //   595: invokeinterface 62 2 0
    //   600: pop
    //   601: aload_3
    //   602: ldc_w 615
    //   605: invokeinterface 62 2 0
    //   610: pop
    //   611: aload_3
    //   612: ldc_w 617
    //   615: invokeinterface 62 2 0
    //   620: pop
    //   621: aload_3
    //   622: ldc_w 619
    //   625: invokeinterface 62 2 0
    //   630: pop
    //   631: aload_3
    //   632: ldc_w 621
    //   635: invokeinterface 62 2 0
    //   640: pop
    //   641: aload_3
    //   642: ldc_w 623
    //   645: invokeinterface 62 2 0
    //   650: pop
    //   651: aload_3
    //   652: ldc_w 625
    //   655: invokeinterface 62 2 0
    //   660: pop
    //   661: aload_3
    //   662: invokestatic 628	cn/com/xy/sms/sdk/db/entity/e:a	(Ljava/util/List;)I
    //   665: pop
    //   666: aload 4
    //   668: ldc2_w 629
    //   671: getstatic 636	java/util/concurrent/TimeUnit:MILLISECONDS	Ljava/util/concurrent/TimeUnit;
    //   674: invokeinterface 640 4 0
    //   679: ifeq -13 -> 666
    //   682: ldc_w 522
    //   685: ldc 93
    //   687: invokestatic 364	cn/com/xy/sms/sdk/db/entity/SysParamEntityManager:setParam	(Ljava/lang/String;Ljava/lang/String;)J
    //   690: pop2
    //   691: invokestatic 514	cn/com/xy/sms/sdk/constant/Constant:getTempPARSE_PATH	()Ljava/lang/String;
    //   694: invokestatic 516	cn/com/xy/sms/sdk/util/d:d	(Ljava/lang/String;)V
    //   697: return
    //   698: astore_3
    //   699: aload_3
    //   700: invokevirtual 140	java/lang/Throwable:printStackTrace	()V
    //   703: return
    //   704: aload 11
    //   706: invokeinterface 644 1 0
    //   711: checkcast 646	java/util/Map$Entry
    //   714: astore 12
    //   716: aload 12
    //   718: invokeinterface 649 1 0
    //   723: checkcast 48	java/lang/String
    //   726: astore_3
    //   727: aload 12
    //   729: invokeinterface 652 1 0
    //   734: checkcast 48	java/lang/String
    //   737: astore 13
    //   739: aload_3
    //   740: invokestatic 116	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   743: ifne -305 -> 438
    //   746: aload 13
    //   748: invokestatic 116	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   751: ifne -313 -> 438
    //   754: aload_3
    //   755: ldc_w 279
    //   758: ldc_w 654
    //   761: invokevirtual 658	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   764: invokevirtual 211	java/lang/String:trim	()Ljava/lang/String;
    //   767: astore 12
    //   769: aload 5
    //   771: ifnull +76 -> 847
    //   774: aload 5
    //   776: aload 12
    //   778: invokeinterface 277 2 0
    //   783: ifeq +64 -> 847
    //   786: aload 10
    //   788: new 227	java/lang/StringBuilder
    //   791: dup
    //   792: aload 12
    //   794: invokestatic 234	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   797: invokespecial 235	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   800: ldc 46
    //   802: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   805: invokevirtual 242	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   808: ldc_w 279
    //   811: aconst_null
    //   812: invokestatic 282	cn/com/xy/sms/sdk/util/d:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   815: new 227	java/lang/StringBuilder
    //   818: dup
    //   819: aload 12
    //   821: invokestatic 234	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   824: invokespecial 235	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   827: ldc 46
    //   829: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   832: invokevirtual 242	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   835: ldc_w 284
    //   838: aconst_null
    //   839: invokestatic 287	cn/com/xy/sms/sdk/util/d:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   842: aload 12
    //   844: invokestatic 292	cn/com/xy/sms/sdk/dex/DexUtil:removeClassLoaderBySubname	(Ljava/lang/String;)V
    //   847: new 227	java/lang/StringBuilder
    //   850: dup
    //   851: aload 9
    //   853: invokestatic 234	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   856: invokespecial 235	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   859: aload_3
    //   860: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   863: invokevirtual 242	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   866: invokestatic 295	cn/com/xy/sms/sdk/util/d:b	(Ljava/lang/String;)Ljava/io/InputStream;
    //   869: astore 14
    //   871: aload 14
    //   873: ifnull +106 -> 979
    //   876: new 227	java/lang/StringBuilder
    //   879: dup
    //   880: aload 10
    //   882: invokestatic 234	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   885: invokespecial 235	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   888: aload_3
    //   889: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   892: invokevirtual 242	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   895: invokestatic 297	cn/com/xy/sms/sdk/util/d:c	(Ljava/lang/String;)Z
    //   898: pop
    //   899: aload 10
    //   901: aload_3
    //   902: aload 14
    //   904: invokestatic 300	cn/com/xy/sms/sdk/util/d:a	(Ljava/lang/String;Ljava/lang/String;Ljava/io/InputStream;)Ljava/io/File;
    //   907: pop
    //   908: aload 10
    //   910: new 227	java/lang/StringBuilder
    //   913: dup
    //   914: aload 12
    //   916: invokestatic 234	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   919: invokespecial 235	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   922: ldc_w 279
    //   925: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   928: invokevirtual 242	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   931: new 227	java/lang/StringBuilder
    //   934: dup
    //   935: aload 12
    //   937: invokestatic 234	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   940: invokespecial 235	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   943: ldc 46
    //   945: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   948: aload 13
    //   950: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   953: ldc_w 279
    //   956: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   959: invokevirtual 242	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   962: invokestatic 302	cn/com/xy/sms/sdk/util/d:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   965: ldc_w 304
    //   968: aload 12
    //   970: invokevirtual 96	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   973: ifeq +196 -> 1169
    //   976: invokestatic 307	cn/com/xy/sms/sdk/dex/DexUtil:init	()V
    //   979: iload_0
    //   980: iconst_1
    //   981: iadd
    //   982: istore_1
    //   983: iload_1
    //   984: istore_0
    //   985: iload_1
    //   986: sipush 300
    //   989: if_icmple +37 -> 1026
    //   992: aload 8
    //   994: aload 8
    //   996: invokevirtual 585	java/lang/StringBuilder:length	()I
    //   999: bipush 10
    //   1001: isub
    //   1002: invokevirtual 589	java/lang/StringBuilder:setLength	(I)V
    //   1005: aload 7
    //   1007: aload 8
    //   1009: invokevirtual 242	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1012: invokeinterface 62 2 0
    //   1017: pop
    //   1018: aload 8
    //   1020: iconst_0
    //   1021: invokevirtual 589	java/lang/StringBuilder:setLength	(I)V
    //   1024: iconst_0
    //   1025: istore_0
    //   1026: aload 8
    //   1028: ldc_w 260
    //   1031: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1034: pop
    //   1035: aload 8
    //   1037: aload 12
    //   1039: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1042: pop
    //   1043: aload 8
    //   1045: ldc_w 262
    //   1048: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1051: pop
    //   1052: aload 8
    //   1054: aload 13
    //   1056: invokevirtual 211	java/lang/String:trim	()Ljava/lang/String;
    //   1059: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1062: pop
    //   1063: aload 8
    //   1065: ldc_w 264
    //   1068: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1071: pop
    //   1072: aload 8
    //   1074: aload 12
    //   1076: invokestatic 268	cn/com/xy/sms/sdk/db/entity/e:c	(Ljava/lang/String;)I
    //   1079: invokevirtual 271	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1082: pop
    //   1083: aload 8
    //   1085: ldc_w 273
    //   1088: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1091: pop
    //   1092: new 227	java/lang/StringBuilder
    //   1095: dup
    //   1096: aload 9
    //   1098: invokestatic 234	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   1101: invokespecial 235	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1104: aload 12
    //   1106: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1109: ldc_w 343
    //   1112: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1115: invokevirtual 242	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1118: astore 13
    //   1120: new 27	java/io/File
    //   1123: dup
    //   1124: aload 13
    //   1126: invokespecial 30	java/io/File:<init>	(Ljava/lang/String;)V
    //   1129: astore_3
    //   1130: aload_3
    //   1131: invokevirtual 34	java/io/File:exists	()Z
    //   1134: ifeq +198 -> 1332
    //   1137: aload_3
    //   1138: invokevirtual 346	java/io/File:isFile	()Z
    //   1141: ifne +81 -> 1222
    //   1144: goto +188 -> 1332
    //   1147: aload_3
    //   1148: ifnull +189 -> 1337
    //   1151: aload 4
    //   1153: new 660	cn/com/xy/sms/sdk/util/h
    //   1156: dup
    //   1157: aload_3
    //   1158: invokespecial 661	cn/com/xy/sms/sdk/util/h:<init>	(Ljava/io/File;)V
    //   1161: invokeinterface 567 2 0
    //   1166: goto +171 -> 1337
    //   1169: ldc_w 309
    //   1172: aload 12
    //   1174: invokevirtual 96	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1177: ifeq -198 -> 979
    //   1180: invokestatic 312	cn/com/xy/sms/sdk/dex/DexUtil:initOnlineUpdateCycleConfig	()V
    //   1183: goto -204 -> 979
    //   1186: astore_3
    //   1187: aload_3
    //   1188: invokevirtual 140	java/lang/Throwable:printStackTrace	()V
    //   1191: aload 5
    //   1193: ifnull -724 -> 469
    //   1196: aload 4
    //   1198: new 580	cn/com/xy/sms/sdk/util/f
    //   1201: dup
    //   1202: invokespecial 581	cn/com/xy/sms/sdk/util/f:<init>	()V
    //   1205: invokeinterface 567 2 0
    //   1210: goto -741 -> 469
    //   1213: astore_3
    //   1214: invokestatic 514	cn/com/xy/sms/sdk/constant/Constant:getTempPARSE_PATH	()Ljava/lang/String;
    //   1217: invokestatic 516	cn/com/xy/sms/sdk/util/d:d	(Ljava/lang/String;)V
    //   1220: aload_3
    //   1221: athrow
    //   1222: aload 13
    //   1224: invokestatic 349	cn/com/xy/sms/sdk/util/StringUtils:getFileMD5	(Ljava/lang/String;)Ljava/lang/String;
    //   1227: astore 13
    //   1229: aload 13
    //   1231: invokestatic 116	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   1234: ifeq +8 -> 1242
    //   1237: aconst_null
    //   1238: astore_3
    //   1239: goto -92 -> 1147
    //   1242: aload 9
    //   1244: new 227	java/lang/StringBuilder
    //   1247: dup
    //   1248: aload 12
    //   1250: invokestatic 234	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   1253: invokespecial 235	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1256: ldc_w 351
    //   1259: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1262: invokevirtual 242	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1265: invokestatic 356	cn/com/xy/sms/sdk/net/util/l:a	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   1268: astore 12
    //   1270: aload 12
    //   1272: invokestatic 116	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   1275: ifeq +8 -> 1283
    //   1278: aconst_null
    //   1279: astore_3
    //   1280: goto -133 -> 1147
    //   1283: aload 13
    //   1285: aload 12
    //   1287: invokevirtual 96	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1290: istore_2
    //   1291: iload_2
    //   1292: ifne -145 -> 1147
    //   1295: aconst_null
    //   1296: astore_3
    //   1297: goto -150 -> 1147
    //   1300: astore_3
    //   1301: aload 5
    //   1303: ifnull +17 -> 1320
    //   1306: aload 4
    //   1308: new 580	cn/com/xy/sms/sdk/util/f
    //   1311: dup
    //   1312: invokespecial 581	cn/com/xy/sms/sdk/util/f:<init>	()V
    //   1315: invokeinterface 567 2 0
    //   1320: aload_3
    //   1321: athrow
    //   1322: astore 4
    //   1324: aload 4
    //   1326: invokevirtual 140	java/lang/Throwable:printStackTrace	()V
    //   1329: goto -109 -> 1220
    //   1332: aconst_null
    //   1333: astore_3
    //   1334: goto -187 -> 1147
    //   1337: goto -899 -> 438
    // Local variable table:
    //   start	length	slot	name	signature
    //   30	996	0	i	int
    //   982	8	1	j	int
    //   24	1268	2	bool	boolean
    //   6	143	3	localObject1	Object
    //   231	2	3	localThrowable1	Throwable
    //   239	2	3	localThrowable2	Throwable
    //   247	2	3	localThrowable3	Throwable
    //   259	2	3	localThrowable4	Throwable
    //   265	2	3	localThrowable5	Throwable
    //   298	2	3	localThrowable6	Throwable
    //   321	2	3	localThrowable7	Throwable
    //   378	284	3	localObject2	Object
    //   698	2	3	localThrowable8	Throwable
    //   726	432	3	localObject3	Object
    //   1186	2	3	localThrowable9	Throwable
    //   1213	8	3	localObject4	Object
    //   1238	59	3	localObject5	Object
    //   1300	21	3	localObject6	Object
    //   1333	1	3	localObject7	Object
    //   44	1263	4	localObject8	Object
    //   1322	3	4	localThrowable10	Throwable
    //   41	1261	5	localObject9	Object
    //   274	266	6	localHashMap	HashMap
    //   393	613	7	localArrayList	ArrayList
    //   402	682	8	localStringBuilder	StringBuilder
    //   340	903	9	str1	String
    //   345	564	10	str2	String
    //   434	271	11	localIterator	Iterator
    //   714	572	12	localObject10	Object
    //   737	547	13	str3	String
    //   869	34	14	localInputStream	java.io.InputStream
    // Exception table:
    //   from	to	target	type
    //   171	228	231	java/lang/Throwable
    //   35	43	239	java/lang/Throwable
    //   57	107	239	java/lang/Throwable
    //   107	155	239	java/lang/Throwable
    //   232	236	239	java/lang/Throwable
    //   0	25	247	java/lang/Throwable
    //   240	244	247	java/lang/Throwable
    //   271	276	247	java/lang/Throwable
    //   281	291	247	java/lang/Throwable
    //   304	310	247	java/lang/Throwable
    //   327	409	247	java/lang/Throwable
    //   455	469	247	java/lang/Throwable
    //   469	510	247	java/lang/Throwable
    //   510	526	247	java/lang/Throwable
    //   526	666	247	java/lang/Throwable
    //   666	691	247	java/lang/Throwable
    //   1196	1210	247	java/lang/Throwable
    //   1306	1320	247	java/lang/Throwable
    //   1320	1322	247	java/lang/Throwable
    //   252	258	259	java/lang/Throwable
    //   159	165	265	java/lang/Throwable
    //   291	297	298	java/lang/Throwable
    //   314	320	321	java/lang/Throwable
    //   691	697	698	java/lang/Throwable
    //   414	428	1186	java/lang/Throwable
    //   428	436	1186	java/lang/Throwable
    //   438	446	1186	java/lang/Throwable
    //   704	769	1186	java/lang/Throwable
    //   774	847	1186	java/lang/Throwable
    //   847	871	1186	java/lang/Throwable
    //   876	979	1186	java/lang/Throwable
    //   992	1024	1186	java/lang/Throwable
    //   1026	1144	1186	java/lang/Throwable
    //   1151	1166	1186	java/lang/Throwable
    //   1169	1183	1186	java/lang/Throwable
    //   1222	1237	1186	java/lang/Throwable
    //   1242	1278	1186	java/lang/Throwable
    //   1283	1291	1186	java/lang/Throwable
    //   0	25	1213	finally
    //   35	43	1213	finally
    //   57	107	1213	finally
    //   107	155	1213	finally
    //   171	228	1213	finally
    //   232	236	1213	finally
    //   240	244	1213	finally
    //   248	252	1213	finally
    //   271	276	1213	finally
    //   281	291	1213	finally
    //   304	310	1213	finally
    //   327	409	1213	finally
    //   455	469	1213	finally
    //   469	510	1213	finally
    //   510	526	1213	finally
    //   526	666	1213	finally
    //   666	691	1213	finally
    //   1196	1210	1213	finally
    //   1306	1320	1213	finally
    //   1320	1322	1213	finally
    //   414	428	1300	finally
    //   428	436	1300	finally
    //   438	446	1300	finally
    //   704	769	1300	finally
    //   774	847	1300	finally
    //   847	871	1300	finally
    //   876	979	1300	finally
    //   992	1024	1300	finally
    //   1026	1144	1300	finally
    //   1151	1166	1300	finally
    //   1169	1183	1300	finally
    //   1187	1191	1300	finally
    //   1222	1237	1300	finally
    //   1242	1278	1300	finally
    //   1283	1291	1300	finally
    //   1214	1220	1322	java/lang/Throwable
  }
  
  private static void d()
  {
    XyUtil.unZip(Constant.getContext().getResources().getAssets().open("duoqu_nqsql.zip"), "duoqu_nqsql.zip", Constant.getINITSQL_PATH());
    c.b();
  }
  
  private static void e()
  {
    try
    {
      cn.com.xy.sms.sdk.db.entity.d locald = cn.com.xy.sms.sdk.db.entity.e.a("parseUtilMain");
      String str2 = null;
      String str1 = str2;
      if (locald != null)
      {
        str1 = str2;
        if (!StringUtils.isNull(c))
        {
          new StringBuilder("name:").append(b).append(",version:").append(c);
          str1 = c;
        }
      }
      str2 = d.f("duoqu_parse_version.txt");
      new StringBuilder("localVersion=").append(str1).append(" assetVersion=").append(str2);
      if (!StringUtils.isNull(str1))
      {
        boolean bool = a(str1, str2);
        if (!bool) {
          return;
        }
      }
      try
      {
        SysParamEntityManager.clearOldData(false);
        SysParamEntityManager.setParam("sms_sdk_init", "0");
        XyUtil.unZip(Constant.getContext().getResources().getAssets().open("duoqu_parse.zip"), "parse.zip", Constant.getPARSE_PATH(), true, str2, true);
        ParseItemManager.updateParse(Constant.getContext());
        SysParamEntityManager.setParam("sms_sdk_init", "1");
        return;
      }
      catch (Throwable localThrowable1)
      {
        localThrowable1.printStackTrace();
        return;
      }
      return;
    }
    catch (Throwable localThrowable2)
    {
      localThrowable2.printStackTrace();
    }
  }
  
  private static void f()
  {
    String str1 = SysParamEntityManager.getStringParam(Constant.getContext(), "PublicLogoVersion");
    String str2 = d.f("duoqu_publiclogo_version.txt");
    if ((!StringUtils.isNull(str1)) && (!a(str1, str2))) {
      return;
    }
    try
    {
      XyUtil.unZip(Constant.getContext().getResources().getAssets().open("duoqu_publiclogo.zip"), "duoqu_publiclogo.zip", Constant.getPath("duoqu_publiclogo"));
      SysParamEntityManager.setParam("PublicLogoVersion", str2);
      return;
    }
    catch (IOException localIOException) {}catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
  
  /* Error */
  private static void g()
  {
    // Byte code:
    //   0: invokestatic 83	cn/com/xy/sms/sdk/constant/Constant:getContext	()Landroid/content/Context;
    //   3: ldc_w 422
    //   6: invokestatic 91	cn/com/xy/sms/sdk/db/entity/SysParamEntityManager:getStringParam	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   9: astore_2
    //   10: ldc_w 424
    //   13: invokestatic 378	cn/com/xy/sms/sdk/util/d:f	(Ljava/lang/String;)Ljava/lang/String;
    //   16: astore_1
    //   17: aload_2
    //   18: invokestatic 116	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   21: ifne +12 -> 33
    //   24: aload_2
    //   25: aload_1
    //   26: invokestatic 339	cn/com/xy/sms/sdk/util/e:a	(Ljava/lang/String;Ljava/lang/String;)Z
    //   29: ifne +4 -> 33
    //   32: return
    //   33: invokestatic 83	cn/com/xy/sms/sdk/constant/Constant:getContext	()Landroid/content/Context;
    //   36: invokevirtual 384	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   39: invokevirtual 390	android/content/res/Resources:getAssets	()Landroid/content/res/AssetManager;
    //   42: ldc_w 426
    //   45: invokevirtual 397	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   48: ldc_w 428
    //   51: invokestatic 230	cn/com/xy/sms/sdk/constant/Constant:getDRAWBLE_PATH	()Ljava/lang/String;
    //   54: invokestatic 406	cn/com/xy/sms/sdk/util/XyUtil:unZip	(Ljava/io/InputStream;Ljava/lang/String;Ljava/lang/String;)V
    //   57: new 227	java/lang/StringBuilder
    //   60: dup
    //   61: invokestatic 230	cn/com/xy/sms/sdk/constant/Constant:getDRAWBLE_PATH	()Ljava/lang/String;
    //   64: invokestatic 234	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   67: invokespecial 235	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   70: getstatic 431	java/io/File:separator	Ljava/lang/String;
    //   73: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   76: ldc_w 433
    //   79: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   82: invokevirtual 242	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   85: astore_2
    //   86: aload_2
    //   87: invokestatic 246	cn/com/xy/sms/sdk/util/d:a	(Ljava/lang/String;)Z
    //   90: ifeq -58 -> 32
    //   93: bipush 10
    //   95: invokestatic 474	cn/com/xy/sms/sdk/db/entity/o:b	(I)I
    //   98: istore_0
    //   99: iload_0
    //   100: iconst_m1
    //   101: if_icmpeq -69 -> 32
    //   104: aload_2
    //   105: iconst_1
    //   106: invokestatic 480	cn/com/xy/sms/sdk/db/DBManager:excSql	(Ljava/lang/String;Z)V
    //   109: ldc_w 482
    //   112: ldc_w 484
    //   115: invokestatic 364	cn/com/xy/sms/sdk/db/entity/SysParamEntityManager:setParam	(Ljava/lang/String;Ljava/lang/String;)J
    //   118: pop2
    //   119: ldc_w 422
    //   122: aload_1
    //   123: invokestatic 364	cn/com/xy/sms/sdk/db/entity/SysParamEntityManager:setParam	(Ljava/lang/String;Ljava/lang/String;)J
    //   126: pop2
    //   127: invokestatic 485	cn/com/xy/sms/sdk/db/entity/o:b	()V
    //   130: iconst_0
    //   131: invokestatic 474	cn/com/xy/sms/sdk/db/entity/o:b	(I)I
    //   134: pop
    //   135: return
    //   136: astore_1
    //   137: return
    //   138: astore_1
    //   139: aload_1
    //   140: invokevirtual 140	java/lang/Throwable:printStackTrace	()V
    //   143: invokestatic 485	cn/com/xy/sms/sdk/db/entity/o:b	()V
    //   146: goto -16 -> 130
    //   149: astore_1
    //   150: aload_1
    //   151: invokevirtual 140	java/lang/Throwable:printStackTrace	()V
    //   154: return
    //   155: astore_1
    //   156: invokestatic 485	cn/com/xy/sms/sdk/db/entity/o:b	()V
    //   159: aload_1
    //   160: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   98	4	0	i	int
    //   16	107	1	str1	String
    //   136	1	1	localIOException	IOException
    //   138	2	1	localThrowable1	Throwable
    //   149	2	1	localThrowable2	Throwable
    //   155	5	1	localObject	Object
    //   9	96	2	str2	String
    // Exception table:
    //   from	to	target	type
    //   33	99	136	java/io/IOException
    //   127	130	136	java/io/IOException
    //   130	135	136	java/io/IOException
    //   143	146	136	java/io/IOException
    //   156	161	136	java/io/IOException
    //   104	127	138	java/lang/Throwable
    //   33	99	149	java/lang/Throwable
    //   127	130	149	java/lang/Throwable
    //   130	135	149	java/lang/Throwable
    //   143	146	149	java/lang/Throwable
    //   156	161	149	java/lang/Throwable
    //   104	127	155	finally
    //   139	143	155	finally
  }
  
  private static void h()
  {
    String str1 = SysParamEntityManager.getStringParam(Constant.getContext(), "MenuVersion");
    String str2 = d.f("duoqu_nqsql_version.txt");
    if ((!StringUtils.isNull(str1)) && (!a(str1, str2))) {
      return;
    }
    try
    {
      XyUtil.unZip(Constant.getContext().getResources().getAssets().open("duoqu_nqsql.zip"), "duoqu_nqsql.zip", Constant.getINITSQL_PATH());
      c.b();
      SysParamEntityManager.setParam("MenuVersion", str2);
      return;
    }
    catch (IOException localIOException) {}catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
  
  /* Error */
  private static String i()
  {
    // Byte code:
    //   0: ldc_w 489
    //   3: astore_3
    //   4: aconst_null
    //   5: astore_1
    //   6: new 227	java/lang/StringBuilder
    //   9: dup
    //   10: ldc_w 666
    //   13: invokestatic 420	cn/com/xy/sms/sdk/constant/Constant:getPath	(Ljava/lang/String;)Ljava/lang/String;
    //   16: invokestatic 234	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   19: invokespecial 235	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   22: ldc_w 524
    //   25: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   28: invokevirtual 242	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   31: invokestatic 297	cn/com/xy/sms/sdk/util/d:c	(Ljava/lang/String;)Z
    //   34: pop
    //   35: ldc_w 666
    //   38: invokestatic 420	cn/com/xy/sms/sdk/constant/Constant:getPath	(Ljava/lang/String;)Ljava/lang/String;
    //   41: ldc_w 524
    //   44: invokestatic 83	cn/com/xy/sms/sdk/constant/Constant:getContext	()Landroid/content/Context;
    //   47: invokevirtual 384	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   50: invokevirtual 390	android/content/res/Resources:getAssets	()Landroid/content/res/AssetManager;
    //   53: ldc_w 524
    //   56: invokevirtual 397	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   59: invokestatic 300	cn/com/xy/sms/sdk/util/d:a	(Ljava/lang/String;Ljava/lang/String;Ljava/io/InputStream;)Ljava/io/File;
    //   62: astore_2
    //   63: aload_2
    //   64: ifnull +34 -> 98
    //   67: new 668	java/util/zip/ZipFile
    //   70: dup
    //   71: aload_2
    //   72: invokespecial 669	java/util/zip/ZipFile:<init>	(Ljava/io/File;)V
    //   75: astore_1
    //   76: aload_3
    //   77: astore_2
    //   78: aload_1
    //   79: invokevirtual 673	java/util/zip/ZipFile:entries	()Ljava/util/Enumeration;
    //   82: astore 4
    //   84: aload_3
    //   85: astore_2
    //   86: aload 4
    //   88: invokeinterface 678 1 0
    //   93: istore_0
    //   94: iload_0
    //   95: ifne +40 -> 135
    //   98: new 227	java/lang/StringBuilder
    //   101: dup
    //   102: ldc_w 666
    //   105: invokestatic 420	cn/com/xy/sms/sdk/constant/Constant:getPath	(Ljava/lang/String;)Ljava/lang/String;
    //   108: invokestatic 234	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   111: invokespecial 235	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   114: ldc_w 524
    //   117: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   120: invokevirtual 242	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   123: invokestatic 297	cn/com/xy/sms/sdk/util/d:c	(Ljava/lang/String;)Z
    //   126: pop
    //   127: aload_1
    //   128: invokestatic 681	cn/com/xy/sms/sdk/util/d:a	(Ljava/util/zip/ZipFile;)V
    //   131: ldc_w 489
    //   134: areturn
    //   135: aload_3
    //   136: astore_2
    //   137: aload 4
    //   139: invokeinterface 684 1 0
    //   144: checkcast 686	java/util/zip/ZipEntry
    //   147: astore 5
    //   149: aload_3
    //   150: astore_2
    //   151: aload 5
    //   153: invokevirtual 689	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   156: invokestatic 116	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   159: ifne -75 -> 84
    //   162: aload_3
    //   163: astore_2
    //   164: new 48	java/lang/String
    //   167: dup
    //   168: aload 5
    //   170: invokevirtual 689	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   173: ldc_w 691
    //   176: invokevirtual 695	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   179: ldc_w 697
    //   182: invokespecial 700	java/lang/String:<init>	([BLjava/lang/String;)V
    //   185: astore 6
    //   187: aload_3
    //   188: astore_2
    //   189: aload 6
    //   191: invokestatic 116	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   194: ifne -110 -> 84
    //   197: aload_3
    //   198: astore_2
    //   199: aload 6
    //   201: ldc_w 702
    //   204: invokevirtual 705	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   207: ifeq -123 -> 84
    //   210: aload_3
    //   211: astore_2
    //   212: aload_1
    //   213: aload 5
    //   215: invokevirtual 709	java/util/zip/ZipFile:getInputStream	(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;
    //   218: invokestatic 712	cn/com/xy/sms/sdk/util/d:a	(Ljava/io/InputStream;)Ljava/lang/String;
    //   221: astore_3
    //   222: aload_3
    //   223: astore_2
    //   224: aload_3
    //   225: invokevirtual 211	java/lang/String:trim	()Ljava/lang/String;
    //   228: astore_3
    //   229: new 227	java/lang/StringBuilder
    //   232: dup
    //   233: ldc_w 666
    //   236: invokestatic 420	cn/com/xy/sms/sdk/constant/Constant:getPath	(Ljava/lang/String;)Ljava/lang/String;
    //   239: invokestatic 234	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   242: invokespecial 235	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   245: ldc_w 524
    //   248: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   251: invokevirtual 242	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   254: invokestatic 297	cn/com/xy/sms/sdk/util/d:c	(Ljava/lang/String;)Z
    //   257: pop
    //   258: aload_1
    //   259: invokestatic 681	cn/com/xy/sms/sdk/util/d:a	(Ljava/util/zip/ZipFile;)V
    //   262: aload_3
    //   263: areturn
    //   264: astore_1
    //   265: aconst_null
    //   266: astore_1
    //   267: ldc_w 489
    //   270: astore_2
    //   271: new 227	java/lang/StringBuilder
    //   274: dup
    //   275: ldc_w 666
    //   278: invokestatic 420	cn/com/xy/sms/sdk/constant/Constant:getPath	(Ljava/lang/String;)Ljava/lang/String;
    //   281: invokestatic 234	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   284: invokespecial 235	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   287: ldc_w 524
    //   290: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   293: invokevirtual 242	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   296: invokestatic 297	cn/com/xy/sms/sdk/util/d:c	(Ljava/lang/String;)Z
    //   299: pop
    //   300: aload_1
    //   301: invokestatic 681	cn/com/xy/sms/sdk/util/d:a	(Ljava/util/zip/ZipFile;)V
    //   304: aload_2
    //   305: areturn
    //   306: astore_1
    //   307: aconst_null
    //   308: astore_3
    //   309: aload_1
    //   310: astore_2
    //   311: new 227	java/lang/StringBuilder
    //   314: dup
    //   315: ldc_w 666
    //   318: invokestatic 420	cn/com/xy/sms/sdk/constant/Constant:getPath	(Ljava/lang/String;)Ljava/lang/String;
    //   321: invokestatic 234	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   324: invokespecial 235	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   327: ldc_w 524
    //   330: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   333: invokevirtual 242	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   336: invokestatic 297	cn/com/xy/sms/sdk/util/d:c	(Ljava/lang/String;)Z
    //   339: pop
    //   340: aload_3
    //   341: invokestatic 681	cn/com/xy/sms/sdk/util/d:a	(Ljava/util/zip/ZipFile;)V
    //   344: aload_2
    //   345: athrow
    //   346: astore_2
    //   347: aload_1
    //   348: astore_3
    //   349: goto -38 -> 311
    //   352: astore_3
    //   353: goto -82 -> 271
    // Local variable table:
    //   start	length	slot	name	signature
    //   93	2	0	bool	boolean
    //   5	254	1	localZipFile1	java.util.zip.ZipFile
    //   264	1	1	localThrowable1	Throwable
    //   266	35	1	localZipFile2	java.util.zip.ZipFile
    //   306	42	1	localObject1	Object
    //   62	283	2	localObject2	Object
    //   346	1	2	localObject3	Object
    //   3	346	3	localObject4	Object
    //   352	1	3	localThrowable2	Throwable
    //   82	56	4	localEnumeration	java.util.Enumeration
    //   147	67	5	localZipEntry	java.util.zip.ZipEntry
    //   185	15	6	str	String
    // Exception table:
    //   from	to	target	type
    //   6	63	264	java/lang/Throwable
    //   67	76	264	java/lang/Throwable
    //   6	63	306	finally
    //   67	76	306	finally
    //   78	84	346	finally
    //   86	94	346	finally
    //   137	149	346	finally
    //   151	162	346	finally
    //   164	187	346	finally
    //   189	197	346	finally
    //   199	210	346	finally
    //   212	222	346	finally
    //   224	229	346	finally
    //   78	84	352	java/lang/Throwable
    //   86	94	352	java/lang/Throwable
    //   137	149	352	java/lang/Throwable
    //   151	162	352	java/lang/Throwable
    //   164	187	352	java/lang/Throwable
    //   189	197	352	java/lang/Throwable
    //   199	210	352	java/lang/Throwable
    //   212	222	352	java/lang/Throwable
    //   224	229	352	java/lang/Throwable
  }
  
  private static HashMap<String, String> j()
  {
    Object localObject1 = d.g("duoqu_parse_version.txt");
    if ((localObject1 == null) || (((List)localObject1).isEmpty())) {
      return null;
    }
    HashMap localHashMap1 = cn.com.xy.sms.sdk.db.entity.e.b();
    HashMap localHashMap2 = new HashMap();
    localObject1 = ((List)localObject1).iterator();
    label171:
    for (;;)
    {
      if (!((Iterator)localObject1).hasNext()) {
        return localHashMap2;
      }
      Object localObject2 = ((String)((Iterator)localObject1).next()).split("=");
      if (localObject2.length == 2)
      {
        String str1 = localObject2[0];
        localObject2 = localObject2[1];
        if ((!StringUtils.isNull(str1)) && (!StringUtils.isNull((String)localObject2)))
        {
          String str2 = str1.replace(".jar", "");
          if ((localHashMap1 == null) || (localHashMap1.isEmpty()) || (!localHashMap1.containsKey(str2))) {}
          for (boolean bool = true;; bool = a((String)localHashMap1.get(str2), (String)localObject2))
          {
            if (!bool) {
              break label171;
            }
            localHashMap2.put(str1, localObject2);
            break;
          }
        }
      }
    }
  }
  
  private static boolean k()
  {
    String str = d.f("duoqu_parse_version.txt");
    return ("-1".equals(str)) || (!str.contains("="));
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.util.e
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */