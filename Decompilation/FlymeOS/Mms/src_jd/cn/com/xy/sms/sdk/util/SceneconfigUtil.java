package cn.com.xy.sms.sdk.util;

import android.content.ContentValues;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.XyCursor;
import cn.com.xy.sms.sdk.db.entity.SceneRule;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.db.entity.l;
import cn.com.xy.sms.sdk.db.entity.m;
import cn.com.xy.sms.sdk.db.entity.o;
import cn.com.xy.sms.sdk.db.entity.p;
import cn.com.xy.sms.sdk.db.entity.q;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.iccid.IccidLocationUtil;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.ui.popu.util.ViewUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SceneconfigUtil
{
  public static SceneRule getSceneRule(String paramString, int paramInt)
  {
    boolean bool = SysParamEntityManager.getBooleanParam(Constant.getContext(), "hasImportDrawableData");
    Object localObject1 = q.a(paramString, paramInt);
    new StringBuilder("titleNo =").append(paramString).append("hasImportData: ").append(bool);
    if ((localObject1 != null) || (bool))
    {
      if (ViewUtil.getChannelType() == 7) {
        localObject1 = o.a(paramString, paramInt, true);
      }
      Object localObject2;
      for (;;)
      {
        if ((localObject1 == null) || (((List)localObject1).isEmpty())) {
          break label402;
        }
        new StringBuilder("titleNo =").append(paramString).append("sceneRuleList is not Empty");
        localObject2 = querySceneRuleByCondition((List)localObject1);
        if (localObject2 == null) {
          break label375;
        }
        localObject1 = "";
        if (localObject2 != null) {
          localObject1 = res_urls;
        }
        if ((isDownload == 1) || (isResDownloaded((String)localObject1)))
        {
          if (LogManager.debug) {
            new StringBuilder("titleNo =").append(paramString).append("全部下载了. urls: ").append((String)localObject1);
          }
          if ((localObject2 != null) && (isDownload == 0) && (localObject2 != null)) {}
          try
          {
            paramString = new ContentValues();
            paramString.put("isdownload", Integer.valueOf(1));
            DBManager.update("tb_scenerule_config", paramString, "id = ? ", new String[] { id });
            return (SceneRule)localObject2;
            localObject1 = o.a(paramString, paramInt, false);
          }
          catch (Throwable paramString)
          {
            for (;;)
            {
              paramString.printStackTrace();
            }
          }
        }
      }
      localObject1 = m.a((String)localObject1);
      int i;
      if ((localObject1 != null) && (!((List)localObject1).isEmpty()))
      {
        i = ((List)localObject1).size();
        paramInt = 0;
      }
      for (;;)
      {
        if (paramInt >= i)
        {
          z.a(false);
          if (LogManager.debug) {
            new StringBuilder("titleNo =").append(paramString).append("urls---还没有全部下载了");
          }
          return null;
        }
        localObject2 = (String)((List)localObject1).get(paramInt);
        z.a((String)localObject2);
        if (LogManager.debug) {
          new StringBuilder("titleNo =").append(paramString).append(" download urls: ").append((String)localObject2);
        }
        paramInt += 1;
      }
      label375:
      if (LogManager.debug) {
        new StringBuilder("titleNo =").append(paramString).append(" 根据条件找不到对应的，不弹窗，也不请求");
      }
      return null;
      label402:
      new StringBuilder("titleNo =").append(paramString).append("情景不存在。需要获取情景配置数据。");
      localObject1 = new p();
      b = "-1";
      a = paramString;
      paramString = new ArrayList();
      paramString.add(localObject1);
      d = 0;
      q.a((p)localObject1, paramInt);
      requestScenceconfig(paramString, paramInt, false);
      return null;
    }
    localObject1 = new p();
    b = "-1";
    a = paramString;
    d = 0;
    if (XyUtil.checkNetWork(Constant.getContext()) == 0)
    {
      new StringBuilder("titleNo =").append(paramString).append(" 加入情景队列 wifi，直接下载");
      paramString = new ArrayList();
      paramString.add(localObject1);
      q.a((p)localObject1, paramInt);
      requestScenceconfig(paramString, paramInt, false);
    }
    for (;;)
    {
      return null;
      new StringBuilder("titleNo =").append(paramString).append(" 加入情景队列");
      q.a((p)localObject1, paramInt);
    }
  }
  
  public static List<String> getUrls(String paramString)
  {
    if (!StringUtils.isNull(paramString)) {
      return Arrays.asList(paramString.replaceAll("；", ";").split(";"));
    }
    return null;
  }
  
  public static void handleSceneUrllist(List<p> paramList, ArrayList<String> paramArrayList, int paramInt)
  {
    int j = 1;
    if (paramArrayList != null) {}
    for (int i = 1;; i = 0) {
      try
      {
        if (paramArrayList.isEmpty()) {
          j = 0;
        }
        Object localObject;
        if ((j & i) != 0)
        {
          j = paramArrayList.size();
          i = 0;
          for (;;)
          {
            if (i >= j)
            {
              w.a(false);
              return;
            }
            String str = (String)paramArrayList.get(i);
            if (!cn.com.xy.sms.sdk.db.entity.w.a(str))
            {
              localObject = null;
              paramList = null;
            }
            try
            {
              XyCursor localXyCursor = DBManager.query("tb_xml_res_download", new String[] { "id", "scene_id", "url", "status", "pos" }, "url = ? ", new String[] { str });
              if (localXyCursor != null)
              {
                paramList = localXyCursor;
                localObject = localXyCursor;
                if (localXyCursor.getCount() > 0) {}
              }
              else
              {
                paramList = localXyCursor;
                localObject = localXyCursor;
                ContentValues localContentValues = new ContentValues();
                paramList = localXyCursor;
                localObject = localXyCursor;
                localContentValues.put("scene_id", "");
                paramList = localXyCursor;
                localObject = localXyCursor;
                localContentValues.put("url", str);
                paramList = localXyCursor;
                localObject = localXyCursor;
                localContentValues.put("status", Integer.valueOf(0));
                paramList = localXyCursor;
                localObject = localXyCursor;
                localContentValues.put("pos", Integer.valueOf(0));
                paramList = localXyCursor;
                localObject = localXyCursor;
                localContentValues.put("sceneType", Integer.valueOf(paramInt));
                paramList = localXyCursor;
                localObject = localXyCursor;
                localContentValues.put("insert_time", Long.valueOf(System.currentTimeMillis()));
                paramList = localXyCursor;
                localObject = localXyCursor;
                DBManager.insert("tb_xml_res_download", localContentValues);
              }
              XyCursor.closeCursor(localXyCursor, true);
            }
            catch (Throwable localThrowable)
            {
              for (;;)
              {
                localObject = paramList;
                localThrowable.printStackTrace();
                XyCursor.closeCursor(paramList, true);
              }
            }
            finally
            {
              XyCursor.closeCursor((XyCursor)localObject, true);
            }
            w.a(str);
            i += 1;
          }
        }
        return;
      }
      catch (Throwable paramList) {}
    }
  }
  
  public static void handleSceneconfig(List<p> paramList, int paramInt)
  {
    int i;
    if (paramList != null) {
      i = 1;
    }
    for (;;)
    {
      int j;
      try
      {
        if (paramList.isEmpty())
        {
          j = 0;
          if ((j & i) != 0)
          {
            int m = paramList.size();
            i = 0;
            continue;
            Object localObject2 = (p)paramList.get(i);
            Object localObject3;
            if (localObject2 != null)
            {
              d = 1;
              localObject3 = a;
              if (paramInt != 1) {
                continue;
              }
            }
            try
            {
              localObject1 = "scene_id=? and sceneType = " + paramInt;
              DBManager.delete("tb_scenerule_config", (String)localObject1, new String[] { localObject3 });
            }
            catch (Throwable localThrowable)
            {
              Object localObject1;
              int n;
              localThrowable.printStackTrace();
              continue;
              localObject2 = (SceneRule)localThrowable.get(j);
              o.b((SceneRule)localObject2, paramInt);
              localObject3 = getUrls(res_urls);
              if (localObject3 == null) {
                break label395;
              }
            }
            new StringBuilder("insertOrUpdate=").append(localObject2);
            q.a((p)localObject2, paramInt);
            localObject1 = f;
            if (f != null)
            {
              if (localObject1 == null) {
                break label384;
              }
              j = 1;
              if (!((List)localObject1).isEmpty()) {
                break label389;
              }
              k = 0;
              if ((k & j) != 0)
              {
                n = ((List)localObject1).size();
                j = 0;
                if (j < n) {
                  continue;
                }
              }
            }
            i += 1;
            continue;
            localObject1 = "scene_id=? and sceneType != 1";
            continue;
            if (((List)localObject3).isEmpty()) {
              break label395;
            }
            int i1 = ((List)localObject3).size();
            k = 0;
            if (k >= i1)
            {
              z.a(false);
              break label395;
            }
            String str = (String)((List)localObject3).get(k);
            l locall = new l();
            e = 0;
            b = scene_id;
            d = 0;
            c = str;
            if (!m.b(str))
            {
              m.a(locall);
              z.a(str);
            }
            k += 1;
            continue;
            if (i < m) {
              continue;
            }
          }
          else
          {
            return;
            i = 0;
          }
        }
        else
        {
          j = 1;
          continue;
        }
        j = 0;
      }
      catch (Throwable paramList)
      {
        return;
      }
      label384:
      continue;
      label389:
      int k = 1;
      continue;
      label395:
      j += 1;
    }
  }
  
  public static boolean isResDownloaded(String paramString)
  {
    if (!StringUtils.isNull(paramString))
    {
      paramString = Arrays.asList(paramString.replaceAll("；", ";").split(";"));
      if ((paramString != null) && (!paramString.isEmpty()))
      {
        int j = paramString.size();
        int i = 0;
        for (;;)
        {
          if (i >= j) {
            return true;
          }
          String str = (String)paramString.get(i);
          if (!m.b(str))
          {
            if (LogManager.debug) {
              new StringBuilder("url =").append(str).append("还没下载");
            }
            return false;
          }
          i += 1;
        }
      }
    }
    else
    {
      return true;
    }
    return false;
  }
  
  public static void postqueryIccidScene()
  {
    int j = 1;
    label298:
    label367:
    for (int i = 1;; i = 0)
    {
      for (;;)
      {
        Object localObject2;
        try
        {
          long l3 = SysParamEntityManager.getLongParam("PostCount", 0L, Constant.getContext());
          long l2 = SysParamEntityManager.getLongParam("LastPostIccidSceneTime", 0L, Constant.getContext());
          long l1 = l2;
          if (l2 == 0L)
          {
            SysParamEntityManager.setParam("LastPostIccidSceneTime", System.currentTimeMillis());
            l1 = System.currentTimeMillis();
          }
          new StringBuilder("System.currentTimeMillis()=").append(System.currentTimeMillis());
          if (Long.valueOf(l3).longValue() == 0L)
          {
            if (System.currentTimeMillis() <= DexUtil.getUpdateCycleByType(12, 1209600000L) + l1) {
              break label367;
            }
            new StringBuilder("time+Constant.FirstpostqueryIccidScene=").append(l1).append(DexUtil.getUpdateCycleByType(12, 1209600000L));
            if (i == 0) {
              break;
            }
            localObject2 = cn.com.xy.sms.sdk.queue.i.a();
            Object localObject1 = new StringBuffer();
            if (((List)localObject2).isEmpty()) {
              break;
            }
            i = 0;
            if (i < ((List)localObject2).size()) {
              break label298;
            }
            localObject2 = new u();
            localObject1 = cn.com.xy.sms.sdk.net.util.i.a(StringUtils.getMD5(IccidLocationUtil.getICCID(Constant.getContext())), "1", XyUtil.getImeiAndXinghao(Constant.getContext()), ((StringBuffer)localObject1).toString());
            if (!NetUtil.isEnhance()) {
              break;
            }
            NetUtil.executeLoginBeforeHttpRequest((String)localObject1, "990005", (XyCallBack)localObject2, NetUtil.STATSERVICE_URL, true);
            return;
          }
          if (System.currentTimeMillis() > DexUtil.getUpdateCycleByType(13, 5184000000L) + l1)
          {
            i = j;
            new StringBuilder("time+Constant.postqueryIccidScene=").append(l1).append(DexUtil.getUpdateCycleByType(13, 5184000000L));
            continue;
          }
          i = 0;
        }
        catch (Throwable localThrowable)
        {
          localThrowable.printStackTrace();
          return;
        }
        continue;
        localThrowable.append(geta + "," + getc + ";");
        i += 1;
      }
      return;
    }
  }
  
  public static SceneRule querySceneRuleByCondition(List<SceneRule> paramList)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramList != null)
    {
      localObject1 = localObject2;
      if (!paramList.isEmpty())
      {
        localObject1 = localObject2;
        if (paramList.size() > 0) {
          localObject1 = (SceneRule)paramList.get(0);
        }
      }
    }
    return (SceneRule)localObject1;
  }
  
  public static void requestQuerySceneRuleRequest(List<SceneRule> paramList, int paramInt)
  {
    t localt = new t(paramInt);
    try
    {
      paramList = cn.com.xy.sms.sdk.net.util.i.a(paramList);
      if ((!StringUtils.isNull(paramList)) && (NetUtil.checkAccessNetWork(2))) {
        NetUtil.executeHttpRequest(0, paramInt, paramList, localt, NetUtil.getPopupServiceUrl(), true);
      }
      return;
    }
    catch (Throwable paramList)
    {
      paramList.printStackTrace();
    }
  }
  
  /* Error */
  public static void requestScenceconfig(List<p> paramList, int paramInt, boolean paramBoolean)
  {
    // Byte code:
    //   0: new 456	cn/com/xy/sms/sdk/util/s
    //   3: dup
    //   4: aload_0
    //   5: iload_1
    //   6: iload_2
    //   7: invokespecial 458	cn/com/xy/sms/sdk/util/s:<init>	(Ljava/util/List;IZ)V
    //   10: astore 5
    //   12: aload_0
    //   13: ifnull +416 -> 429
    //   16: aload_0
    //   17: invokeinterface 66 1 0
    //   22: ifne +407 -> 429
    //   25: aload_0
    //   26: invokestatic 460	cn/com/xy/sms/sdk/net/util/i:b	(Ljava/util/List;)Ljava/lang/String;
    //   29: astore 6
    //   31: iconst_2
    //   32: invokestatic 446	cn/com/xy/sms/sdk/net/NetUtil:checkAccessNetWork	(I)Z
    //   35: ifeq +35 -> 70
    //   38: iconst_0
    //   39: iload_1
    //   40: aload 6
    //   42: aload 5
    //   44: new 35	java/lang/StringBuilder
    //   47: dup
    //   48: invokestatic 449	cn/com/xy/sms/sdk/net/NetUtil:getPopupServiceUrl	()Ljava/lang/String;
    //   51: invokestatic 425	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   54: invokespecial 40	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   57: ldc_w 462
    //   60: invokevirtual 44	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: invokevirtual 295	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   66: iconst_1
    //   67: invokestatic 453	cn/com/xy/sms/sdk/net/NetUtil:executeHttpRequest	(IILjava/lang/String;Lcn/com/xy/sms/sdk/Iservice/XyCallBack;Ljava/lang/String;Z)V
    //   70: aload_0
    //   71: invokeinterface 137 1 0
    //   76: istore 4
    //   78: iconst_0
    //   79: istore_3
    //   80: goto +343 -> 423
    //   83: aload_0
    //   84: iload_3
    //   85: invokeinterface 148 2 0
    //   90: checkcast 158	cn/com/xy/sms/sdk/db/entity/p
    //   93: astore 5
    //   95: aload 5
    //   97: getfield 166	cn/com/xy/sms/sdk/db/entity/p:a	Ljava/lang/String;
    //   100: astore 10
    //   102: aload 5
    //   104: getfield 164	cn/com/xy/sms/sdk/db/entity/p:b	Ljava/lang/String;
    //   107: astore 11
    //   109: aconst_null
    //   110: astore 8
    //   112: aconst_null
    //   113: astore 9
    //   115: aconst_null
    //   116: astore 5
    //   118: aload 8
    //   120: astore 7
    //   122: aload 9
    //   124: astore 6
    //   126: aload 10
    //   128: invokestatic 199	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   131: ifne +69 -> 200
    //   134: aload 8
    //   136: astore 7
    //   138: aload 9
    //   140: astore 6
    //   142: new 35	java/lang/StringBuilder
    //   145: dup
    //   146: iload_1
    //   147: invokestatic 465	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   150: invokespecial 40	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   153: invokevirtual 295	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   156: astore 5
    //   158: aload 8
    //   160: astore 7
    //   162: aload 9
    //   164: astore 6
    //   166: ldc_w 467
    //   169: iconst_1
    //   170: anewarray 117	java/lang/String
    //   173: dup
    //   174: iconst_0
    //   175: ldc -20
    //   177: aastore
    //   178: ldc_w 469
    //   181: iconst_2
    //   182: anewarray 117	java/lang/String
    //   185: dup
    //   186: iconst_0
    //   187: aload 10
    //   189: aastore
    //   190: dup
    //   191: iconst_1
    //   192: aload 5
    //   194: aastore
    //   195: invokestatic 248	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   198: astore 5
    //   200: aload 5
    //   202: astore 7
    //   204: aload 5
    //   206: astore 6
    //   208: new 98	android/content/ContentValues
    //   211: dup
    //   212: invokespecial 99	android/content/ContentValues:<init>	()V
    //   215: astore 8
    //   217: aload 5
    //   219: astore 7
    //   221: aload 5
    //   223: astore 6
    //   225: aload 8
    //   227: ldc -20
    //   229: aload 10
    //   231: invokevirtual 256	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   234: aload 5
    //   236: astore 7
    //   238: aload 5
    //   240: astore 6
    //   242: aload 8
    //   244: ldc_w 258
    //   247: iload_1
    //   248: invokestatic 107	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   251: invokevirtual 111	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   254: aload 5
    //   256: astore 7
    //   258: aload 5
    //   260: astore 6
    //   262: aload 11
    //   264: invokestatic 199	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   267: ifne +21 -> 288
    //   270: aload 5
    //   272: astore 7
    //   274: aload 5
    //   276: astore 6
    //   278: aload 8
    //   280: ldc_w 471
    //   283: aload 11
    //   285: invokevirtual 256	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   288: aload 5
    //   290: ifnull +78 -> 368
    //   293: aload 5
    //   295: astore 7
    //   297: aload 5
    //   299: astore 6
    //   301: aload 5
    //   303: invokevirtual 253	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   306: ifle +62 -> 368
    //   309: aload 5
    //   311: astore 7
    //   313: aload 5
    //   315: astore 6
    //   317: ldc_w 467
    //   320: aload 8
    //   322: ldc_w 469
    //   325: iconst_2
    //   326: anewarray 117	java/lang/String
    //   329: dup
    //   330: iconst_0
    //   331: aload 10
    //   333: aastore
    //   334: dup
    //   335: iconst_1
    //   336: new 35	java/lang/StringBuilder
    //   339: dup
    //   340: iload_1
    //   341: invokestatic 465	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   344: invokespecial 40	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   347: invokevirtual 295	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   350: aastore
    //   351: invokestatic 126	cn/com/xy/sms/sdk/db/DBManager:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   354: pop
    //   355: aload 5
    //   357: iconst_1
    //   358: invokestatic 282	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   361: iload_3
    //   362: iconst_1
    //   363: iadd
    //   364: istore_3
    //   365: goto +58 -> 423
    //   368: aload 5
    //   370: astore 7
    //   372: aload 5
    //   374: astore 6
    //   376: ldc_w 467
    //   379: aload 8
    //   381: invokestatic 278	cn/com/xy/sms/sdk/db/DBManager:insert	(Ljava/lang/String;Landroid/content/ContentValues;)J
    //   384: pop2
    //   385: goto -30 -> 355
    //   388: astore 5
    //   390: aload 7
    //   392: astore 6
    //   394: aload 5
    //   396: invokevirtual 129	java/lang/Throwable:printStackTrace	()V
    //   399: aload 7
    //   401: iconst_1
    //   402: invokestatic 282	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   405: goto -44 -> 361
    //   408: astore_0
    //   409: aload_0
    //   410: invokevirtual 129	java/lang/Throwable:printStackTrace	()V
    //   413: return
    //   414: astore_0
    //   415: aload 6
    //   417: iconst_1
    //   418: invokestatic 282	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   421: aload_0
    //   422: athrow
    //   423: iload_3
    //   424: iload 4
    //   426: if_icmplt -343 -> 83
    //   429: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	430	0	paramList	List<p>
    //   0	430	1	paramInt	int
    //   0	430	2	paramBoolean	boolean
    //   79	348	3	i	int
    //   76	351	4	j	int
    //   10	363	5	localObject1	Object
    //   388	7	5	localThrowable	Throwable
    //   29	387	6	localObject2	Object
    //   120	280	7	localObject3	Object
    //   110	270	8	localContentValues	ContentValues
    //   113	50	9	localObject4	Object
    //   100	232	10	str1	String
    //   107	177	11	str2	String
    // Exception table:
    //   from	to	target	type
    //   126	134	388	java/lang/Throwable
    //   142	158	388	java/lang/Throwable
    //   166	200	388	java/lang/Throwable
    //   208	217	388	java/lang/Throwable
    //   225	234	388	java/lang/Throwable
    //   242	254	388	java/lang/Throwable
    //   262	270	388	java/lang/Throwable
    //   278	288	388	java/lang/Throwable
    //   301	309	388	java/lang/Throwable
    //   317	355	388	java/lang/Throwable
    //   376	385	388	java/lang/Throwable
    //   0	12	408	java/lang/Throwable
    //   16	70	408	java/lang/Throwable
    //   70	78	408	java/lang/Throwable
    //   83	109	408	java/lang/Throwable
    //   355	361	408	java/lang/Throwable
    //   399	405	408	java/lang/Throwable
    //   415	423	408	java/lang/Throwable
    //   126	134	414	finally
    //   142	158	414	finally
    //   166	200	414	finally
    //   208	217	414	finally
    //   225	234	414	finally
    //   242	254	414	finally
    //   262	270	414	finally
    //   278	288	414	finally
    //   301	309	414	finally
    //   317	355	414	finally
    //   376	385	414	finally
    //   394	399	414	finally
  }
  
  public static void updateData()
  {
    long l = SysParamEntityManager.getLongParam("LastSceneConfigUpdate", 0L, Constant.getContext());
    if (System.currentTimeMillis() > l + DexUtil.getUpdateCycleByType(10, 1209600000L))
    {
      requestScenceconfig(q.a(0), 0, true);
      requestScenceconfig(q.a(1), 1, true);
      SysParamEntityManager.setParam("LastSceneConfigUpdate", System.currentTimeMillis());
    }
    l = SysParamEntityManager.getLongParam("LastSceneRuleUpdate", 0L, Constant.getContext());
    if (System.currentTimeMillis() > l + DexUtil.getUpdateCycleByType(11, 1209600000L))
    {
      requestQuerySceneRuleRequest(o.a(0), 0);
      requestQuerySceneRuleRequest(o.a(1), 1);
      SysParamEntityManager.setParam("LastSceneRuleUpdate", System.currentTimeMillis());
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.util.SceneconfigUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */