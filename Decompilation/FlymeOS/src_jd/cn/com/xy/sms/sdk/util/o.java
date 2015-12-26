package cn.com.xy.sms.sdk.util;

import android.content.Context;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.db.entity.i;
import cn.com.xy.sms.sdk.db.entity.j;
import cn.com.xy.sms.sdk.db.entity.k;
import cn.com.xy.sms.sdk.iccid.IccidLocationUtil;
import cn.com.xy.sms.sdk.net.NetUtil;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public final class o
{
  private static String a(Context paramContext)
  {
    return SysParamEntityManager.getStringParam(paramContext, "LastSceneCountActionUpdate");
  }
  
  public static String a(String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    paramString = j.a(paramString);
    localStringBuffer.append("<SceneStat>");
    Object localObject1;
    List localList;
    int i;
    if (paramString.size() > 0)
    {
      paramString = paramString.iterator();
      if (!paramString.hasNext())
      {
        localStringBuffer.append("</SceneStat>");
        return localStringBuffer.toString();
      }
      localObject1 = (i)paramString.next();
      localStringBuffer.append("t1;");
      localObject1 = b;
      localStringBuffer.append(localObject1 + ";");
      localStringBuffer.append(StringUtils.getMD5(IccidLocationUtil.getICCID(Constant.getContext())) + ";");
      localList = j.b((String)localObject1);
      i = 0;
    }
    for (;;)
    {
      if (i >= localList.size())
      {
        localStringBuffer.append("\n");
        break;
      }
      Object localObject2 = (i)localList.get(i);
      if (i != 0) {
        localStringBuffer.append("&amp;");
      }
      Object localObject3 = a;
      localStringBuffer.append(localObject3 + ",");
      localStringBuffer.append(c + ",");
      localStringBuffer.append(d + ",");
      localObject2 = k.a((String)localObject3, (String)localObject1);
      if (localObject2 != null)
      {
        int j = 0;
        try
        {
          while (j < ((JSONArray)localObject2).length())
          {
            localObject3 = ((JSONArray)localObject2).getJSONObject(j);
            if (j != 0) {
              localStringBuffer.append("#");
            }
            localStringBuffer.append(((JSONObject)localObject3).getString("action_type") + "=");
            localStringBuffer.append(((JSONObject)localObject3).getString("times"));
            j += 1;
            continue;
            return null;
          }
        }
        catch (Throwable localThrowable)
        {
          localThrowable.printStackTrace();
        }
      }
      i += 1;
    }
  }
  
  public static void a()
  {
    boolean bool = true;
    Object localObject = DateUtils.getCurrentTimeString("yyyyMMdd");
    String str = SysParamEntityManager.getStringParam(Constant.getContext(), "LastSceneCountActionUpdate");
    if (str == null) {}
    for (;;)
    {
      if (bool) {}
      try
      {
        str = a((String)localObject);
        if (!StringUtils.isNull(str))
        {
          localObject = new p((String)localObject);
          if (NetUtil.isEnhance()) {
            NetUtil.executeLoginBeforeHttpRequest(str, "990005", (XyCallBack)localObject, NetUtil.STATSERVICE_URL, true);
          }
        }
        return;
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
      }
      bool = DateUtils.compareDateString((String)localObject, DateUtils.addDays(str, "yyyyMMdd", 1), "yyyyMMdd");
    }
  }
  
  private static void b(String paramString)
  {
    SysParamEntityManager.setParam("LastSceneCountActionUpdate", paramString);
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.util.o
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */