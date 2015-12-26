import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.db.entity.a.e;
import cn.com.xy.sms.sdk.db.entity.g;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.net.util.i;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONObject;

public final class bs
{
  private static final ExecutorService a = Executors.newFixedThreadPool(1);
  private static boolean b = false;
  private static long c = 0L;
  
  protected static Object a(boolean paramBoolean, String paramString, XyCallBack paramXyCallBack, int paramInt, Object... paramVarArgs)
  {
    Object localObject2 = null;
    JSONArray localJSONArray = null;
    Object localObject1;
    if (paramVarArgs == null) {
      if (paramXyCallBack == null) {
        localObject1 = localJSONArray;
      }
    }
    String str;
    label378:
    do
    {
      Map localMap;
      do
      {
        do
        {
          do
          {
            return localObject1;
            paramXyCallBack.execute(new Object[] { Integer.valueOf(-1) });
            return null;
            localObject1 = localJSONArray;
          } while (!paramVarArgs[0].toString().equals("0"));
          localObject1 = localJSONArray;
        } while (paramVarArgs.length != 2);
        localMap = i.b(paramVarArgs[1].toString());
        if ((localMap != null) && (localMap.size() != 0)) {
          break;
        }
        localObject1 = localJSONArray;
      } while (paramXyCallBack == null);
      paramXyCallBack.execute(new Object[0]);
      return null;
      paramVarArgs = (JSONObject)localMap.get(localMap.keySet().iterator().next());
      str = paramVarArgs.optString("id");
      if ("0".equals(str))
      {
        paramString = (String)localObject2;
        if (paramXyCallBack != null) {
          switch (paramInt)
          {
          default: 
            paramString = (String)localObject2;
          }
        }
        for (;;)
        {
          paramXyCallBack = localMap.keySet().iterator();
          for (;;)
          {
            localObject1 = paramString;
            if (!paramXyCallBack.hasNext()) {
              break;
            }
            e.a((JSONObject)localMap.get((String)paramXyCallBack.next()));
          }
          paramString = paramVarArgs;
          continue;
          try
          {
            localObject1 = new String[2];
            localJSONArray = paramVarArgs.optJSONArray("pubMenuInfolist");
            paramString = (String)localObject2;
            if (localJSONArray == null) {
              continue;
            }
            paramString = (String)localObject2;
            if (localJSONArray.length() <= 0) {
              continue;
            }
            if (!paramBoolean) {
              break label378;
            }
            localObject1[0] = paramVarArgs.optString("pubName");
            localObject1[1] = localJSONArray.toString();
            XyUtil.doXycallBackResult(paramXyCallBack, new Object[] { localObject1[0], localObject1[1] });
            paramString = (String)localObject2;
          }
          catch (Throwable paramString)
          {
            paramString.printStackTrace();
            paramString = (String)localObject2;
          }
          if (paramXyCallBack != null)
          {
            paramXyCallBack.execute(new Object[] { "-1" });
            paramString = (String)localObject2;
            continue;
            XyUtil.doXycallBackResult(paramXyCallBack, new Object[] { localJSONArray.toString(), paramVarArgs.optString("pubId") });
            paramString = (String)localObject2;
            continue;
            paramXyCallBack.execute(new Object[] { localMap });
            paramString = (String)localObject2;
          }
        }
      }
      localObject1 = localJSONArray;
    } while (!"1".equals(str));
    NetUtil.QueryTokenRequest(paramString);
    return null;
  }
  
  public static void a()
  {
    synchronized (bx.a)
    {
      if (b) {
        return;
      }
      b = true;
      a.execute(new bw());
      return;
    }
  }
  
  public static void a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, XyCallBack paramXyCallBack, int paramInt, String paramString6)
  {
    try
    {
      if (!g.a(paramString1, paramString3))
      {
        XyUtil.doXycallBackResult(paramXyCallBack, new Object[] { Integer.valueOf(-1), null, paramString6 });
        return;
      }
      NetUtil.requestTokenIfNeed(paramString4);
      bv localbv = new bv(paramXyCallBack, 0, paramString6, paramString4);
      paramString1 = i.a(paramString1, paramString2, paramString3, paramString4, paramString5);
      if (NetUtil.isEnhance())
      {
        NetUtil.executePubNumServiceHttpRequest(paramString1, "990005", localbv, paramString2, true, false, "pubinfo", true);
        return;
      }
    }
    catch (Throwable paramString1)
    {
      paramString1.printStackTrace();
      return;
    }
    XyUtil.doXycallBackResult(paramXyCallBack, new Object[] { Integer.valueOf(-1), null, paramString6 });
  }
  
  public static void a(List<String> paramList, String paramString1, String paramString2, String paramString3, XyCallBack paramXyCallBack, boolean paramBoolean)
  {
    try
    {
      paramXyCallBack = new bu(paramString1, paramString2, paramXyCallBack);
      NetUtil.requestTokenIfNeed(paramString2);
      paramList = i.a(paramList, paramString1, paramString2, paramString3);
      LogManager.e("queryPubInfo", "queryPubInfoByList dataString=" + paramList);
      if (StringUtils.isNull(paramList)) {
        return;
      }
      if (NetUtil.checkAccessNetWork(1))
      {
        NetUtil.executePubNumServiceHttpRequest(paramList, "990005", paramXyCallBack, null, paramBoolean, false, "pubinfo", true);
        return;
      }
    }
    catch (Throwable paramList)
    {
      paramList.printStackTrace();
    }
  }
  
  public static void a(boolean paramBoolean1, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, XyCallBack paramXyCallBack, int paramInt, boolean paramBoolean2, boolean paramBoolean3)
  {
    try
    {
      if (!g.a(paramString1, paramString3))
      {
        LogManager.e("pubInfo", "!!!!!!!!!! queryPubInfoRequest num: " + paramString1 + " areaCode: " + paramString3);
        XyUtil.doXycallBack(paramXyCallBack, "");
        return;
      }
      LogManager.e("pubInfo", "########## queryPubInfoRequest num: " + paramString1 + " areaCode: " + paramString3);
      NetUtil.requestTokenIfNeed(paramString4);
      bt localbt = new bt(false, paramString1, paramString2, paramString3, paramString4, paramXyCallBack, paramInt, paramString5);
      paramString1 = i.a(paramString1, paramString2, paramString3, paramString4, paramString5);
      if (NetUtil.isEnhance())
      {
        NetUtil.executePubNumServiceHttpRequest(paramString1, "990005", localbt, paramString2, false, false, "pubinfo", true);
        return;
      }
    }
    catch (Throwable paramString1)
    {
      paramString1.printStackTrace();
      return;
    }
    XyUtil.doXycallBack(paramXyCallBack, "");
  }
}

/* Location:
 * Qualified Name:     bs
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */