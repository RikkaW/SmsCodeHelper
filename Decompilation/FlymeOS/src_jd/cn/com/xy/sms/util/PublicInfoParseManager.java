package cn.com.xy.sms.util;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import bx;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.entity.a.c;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.net.util.l;
import cn.com.xy.sms.sdk.queue.g;
import cn.com.xy.sms.sdk.queue.i;
import cn.com.xy.sms.sdk.ui.popu.util.ViewUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import cn.com.xy.sms.sdk.util.d;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

public class PublicInfoParseManager
{
  private static HashMap<String, Long> a = new HashMap();
  private static ExecutorService b = Executors.newFixedThreadPool(1);
  public static long mins = 1L;
  
  private static BitmapDrawable b(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt1, int paramInt2, SdkCallBack paramSdkCallBack)
  {
    Object localObject3 = null;
    Object localObject2 = null;
    Object localObject1 = localObject3;
    try
    {
      if (!NetUtil.checkAccessNetWork(2))
      {
        paramContext = (Context)localObject2;
        if (paramSdkCallBack != null)
        {
          localObject1 = localObject3;
          paramSdkCallBack.execute(null);
          return null;
        }
      }
      else
      {
        localObject1 = localObject3;
        a.put(paramString4, Long.valueOf(System.currentTimeMillis()));
        localObject1 = localObject3;
        if (d.g(paramString1, paramString2, paramString3) == 0)
        {
          localObject1 = localObject3;
          a.remove(paramString4);
          localObject1 = localObject3;
          paramString1 = ViewUtil.createBitmapByPath2(paramContext, paramString4, paramInt1, paramInt2);
          if (paramSdkCallBack != null)
          {
            localObject1 = paramString1;
            paramSdkCallBack.execute(new Object[] { paramString1 });
          }
          paramContext = paramString1;
          if (paramString1 != null)
          {
            localObject1 = paramString1;
            a.remove(paramString4);
            return paramString1;
          }
        }
        else
        {
          paramContext = (Context)localObject2;
          if (paramSdkCallBack != null)
          {
            localObject1 = localObject3;
            paramSdkCallBack.execute(null);
            paramContext = (Context)localObject2;
          }
        }
      }
      return paramContext;
    }
    catch (Throwable paramContext) {}
    return (BitmapDrawable)localObject1;
  }
  
  public static BitmapDrawable findBitmapLogoByLogoName(Context paramContext, String paramString, int paramInt1, int paramInt2, Map<String, String> paramMap, SdkCallBack paramSdkCallBack)
  {
    for (;;)
    {
      try
      {
        str = Constant.getPath("duoqu_publiclogo");
        paramMap = str + paramString;
        localObject = new File(paramMap);
        if (((File)localObject).exists())
        {
          new StringBuilder("logoPath=").append(paramMap).append("文件存在，decode");
          paramContext = ViewUtil.createBitmapByPath2(paramContext, (File)localObject, paramInt1, paramInt2);
          if (paramContext == null) {}
        }
      }
      catch (Throwable paramContext)
      {
        String str;
        Object localObject;
        paramContext = null;
        return paramContext;
      }
      finally
      {
        paramString = (Long)a.get("runResourseQueue");
        if ((paramString == null) || (System.currentTimeMillis() > paramString.longValue() + DexUtil.getUpdateCycleByType(20, 3600000L)))
        {
          g.a(new i(7, new String[0]));
          a.put("runResourseQueue", Long.valueOf(System.currentTimeMillis()));
        }
      }
      try
      {
        XyUtil.doXycallBackResult(paramSdkCallBack, new Object[] { paramContext });
        paramString = (Long)a.get("runResourseQueue");
        if ((paramString == null) || (System.currentTimeMillis() > paramString.longValue() + DexUtil.getUpdateCycleByType(20, 3600000L)))
        {
          g.a(new i(7, new String[0]));
          a.put("runResourseQueue", Long.valueOf(System.currentTimeMillis()));
        }
        return paramContext;
      }
      catch (Throwable paramString) {}
    }
    a.put(paramMap, Long.valueOf(System.currentTimeMillis()));
    XyUtil.doXycallBackResult(paramSdkCallBack, null);
    paramContext = (Long)a.get("runResourseQueue");
    if ((paramContext == null) || (System.currentTimeMillis() > paramContext.longValue() + DexUtil.getUpdateCycleByType(20, 3600000L)))
    {
      g.a(new i(7, new String[0]));
      a.put("runResourseQueue", Long.valueOf(System.currentTimeMillis()));
    }
    return null;
    localObject = (Long)a.get(paramMap);
    if ((localObject != null) && (System.currentTimeMillis() < ((Long)localObject).longValue() + DexUtil.getUpdateCycleByType(19, 60L * mins * 1000L)))
    {
      new StringBuilder("logoPath=").append(paramMap).append(" 还没到时间");
      XyUtil.doXycallBackResult(paramSdkCallBack, null);
      paramContext = (Long)a.get("runResourseQueue");
      if ((paramContext == null) || (System.currentTimeMillis() > paramContext.longValue() + DexUtil.getUpdateCycleByType(20, 3600000L)))
      {
        g.a(new i(7, new String[0]));
        a.put("runResourseQueue", Long.valueOf(System.currentTimeMillis()));
      }
      return null;
    }
    localObject = "http://down2.bizport.cn/publicnum/upload/" + paramString;
    b.execute(new s(paramContext, (String)localObject, str, paramString, paramMap, paramInt1, paramInt2, paramSdkCallBack));
    paramContext = (Long)a.get("runResourseQueue");
    if ((paramContext == null) || (System.currentTimeMillis() > paramContext.longValue() + DexUtil.getUpdateCycleByType(20, 3600000L)))
    {
      g.a(new i(7, new String[0]));
      a.put("runResourseQueue", Long.valueOf(System.currentTimeMillis()));
    }
    return null;
    return paramContext;
  }
  
  public static String getLogoNameByNum(Context paramContext, String paramString1, int paramInt1, int paramInt2, String paramString2, Map<String, String> paramMap, SdkCallBack paramSdkCallBack)
  {
    try
    {
      if (!l.a()) {
        return null;
      }
      paramContext = bx.a(paramString1, paramInt1, paramString2, paramMap, new t(paramInt2, paramSdkCallBack));
      if (!StringUtils.isNull(paramContext))
      {
        paramContext = new JSONObject(paramContext);
        if (paramInt2 == 1) {
          return paramContext.optString("logo");
        }
        paramContext = paramContext.optString("logoc");
        return paramContext;
      }
    }
    catch (Throwable paramContext)
    {
      LogManager.e("XIAOYUAN", "findBitmapLogoByLogoName" + paramContext.getLocalizedMessage(), paramContext);
    }
    return "";
  }
  
  public static String queryLocalSmsSignByNum(String paramString)
  {
    try
    {
      paramString = c.a(paramString);
      boolean bool = StringUtils.isNull(paramString);
      if (bool) {
        return "";
      }
      paramString = paramString.split(";");
      paramString = paramString[(paramString.length - 1)];
      return paramString;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
    return "";
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.util.PublicInfoParseManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */