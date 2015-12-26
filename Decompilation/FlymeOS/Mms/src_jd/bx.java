import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.ParseItemManager;
import cn.com.xy.sms.sdk.db.c;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.db.entity.a.e;
import cn.com.xy.sms.sdk.db.entity.f;
import cn.com.xy.sms.sdk.db.entity.r;
import cn.com.xy.sms.sdk.db.entity.s;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.iccid.IccidLocationUtil;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import cn.com.xy.sms.util.SdkCallBack;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import org.json.JSONArray;
import org.json.JSONObject;

public final class bx
{
  public static final Object a = new Object();
  static boolean b = false;
  
  public static String a(String paramString1, int paramInt, String paramString2, Map<String, String> paramMap, XyCallBack paramXyCallBack)
  {
    if (StringUtils.isNull(paramString1))
    {
      paramMap = "";
      return paramMap;
    }
    if (!ParseItemManager.isInitData())
    {
      if (paramXyCallBack != null) {
        paramXyCallBack.execute(new Object[] { null, "-1" });
      }
      return "-1";
    }
    for (;;)
    {
      String str;
      Object localObject1;
      try
      {
        str = StringUtils.getValueByKey(paramMap, "cnum");
        Object localObject2 = StringUtils.getValueByKey(paramMap, "code");
        int i = e.c(StringUtils.getValueByKey(paramMap, "simIndex"));
        localObject1 = localObject2;
        if (StringUtils.isNull((String)localObject2)) {
          localObject1 = IccidLocationUtil.getAreaCodeByCnumOrIccid(str, i, paramString2, paramString1, true, false);
        }
        localObject2 = e.a(paramString1, (String)localObject1, paramInt);
        if (localObject2 != null)
        {
          paramString1 = JsonUtil.PubInfoToJson((JSONObject)localObject2);
          if (paramXyCallBack != null) {
            paramXyCallBack.execute(new Object[] { paramString1 });
          }
          paramMap = paramString1;
          if (SysParamEntityManager.getIntParam(Constant.getContext(), "QUERY_ONLINE") == 0) {
            break;
          }
          IccidLocationUtil.iccidPool.execute(new cc((String)localObject1, paramString2));
          return paramString1;
        }
      }
      catch (Throwable paramString1)
      {
        LogManager.e("PubInfoService", "queryPublicInfo error: " + paramString1.getMessage(), paramString1);
        if (paramXyCallBack != null) {
          paramXyCallBack.execute(new Object[] { "" });
        }
        return "";
      }
      if ((paramMap != null) && (paramMap.containsKey("SUPPORT_NET_QUERY")))
      {
        a(paramString1, str, paramInt, (String)localObject1, paramString2, paramXyCallBack, true);
        paramString1 = "";
      }
      else
      {
        a(paramString1, str, paramInt, (String)localObject1, paramString2, paramXyCallBack, false);
        paramString1 = "";
      }
    }
  }
  
  public static String a(String paramString1, int paramInt, String paramString2, Map<String, String> paramMap, SdkCallBack paramSdkCallBack)
  {
    if (StringUtils.isPhoneNumber(paramString1))
    {
      if (paramSdkCallBack != null) {
        paramSdkCallBack.execute(new Object[] { "" });
      }
      return "";
    }
    String str3 = StringUtils.getValueByKey(paramMap, "cnum");
    String str2 = StringUtils.getValueByKey(paramMap, "code");
    int i = e.c(StringUtils.getValueByKey(paramMap, "simIndex"));
    new StringBuilder("prev cnum:").append(str3).append(" simIccid: ").append(paramString2).append(" areaCode=").append(str2).append(" simIndex=").append(i);
    String str1 = str2;
    if (StringUtils.isNull(str2)) {
      str1 = IccidLocationUtil.getAreaCodeByCnumOrIccid(str3, i, paramString2, paramString1, true, false);
    }
    new StringBuilder("next cnum:").append(str3).append(" simIccid: ").append(paramString2).append(" areaCode: ").append(str1).append(" numType=").append(paramInt).append(" phoneNum=").append(paramString1).append(" simIndex=").append(i);
    boolean bool = false;
    if (paramMap != null) {
      bool = paramMap.containsKey("EXCLUDE_CN");
    }
    paramMap = e.a(paramString1, str1, paramInt, bool, paramString2, str3, i, paramSdkCallBack);
    if ((paramMap != null) && (paramMap.length() > 0))
    {
      paramString1 = "";
      if (paramMap.length() >= 3) {
        paramString1 = paramMap.toString();
      }
      if (paramSdkCallBack != null) {
        paramSdkCallBack.execute(new Object[] { paramString1 });
      }
      NetUtil.executeRunnable(new cb(str1, paramString2));
      return paramString1;
    }
    return "";
  }
  
  public static String a(Map<String, Object> paramMap)
  {
    if (paramMap != null)
    {
      paramMap = (String)paramMap.get("title_num");
      if (!StringUtils.isNull(paramMap)) {
        try
        {
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put("scenetype", paramMap);
          paramMap = localJSONObject.toString();
          return paramMap;
        }
        catch (Throwable paramMap)
        {
          paramMap.printStackTrace();
        }
      }
    }
    return "";
  }
  
  private static void a()
  {
    if (!NetUtil.checkAccessNetWork(2)) {}
    f localf;
    do
    {
      do
      {
        return;
        localf = c.c();
        new StringBuilder(" menuInfo=").append(localf);
        if (localf == null) {
          c.c("menuMain");
        }
        localf = c.c();
      } while (localf == null);
      if (System.currentTimeMillis() > e + DexUtil.getUpdateCycleByType(5, 172800000L))
      {
        c.a(localf, null, true, null);
        return;
      }
    } while ((SysParamEntityManager.getIntParam(Constant.getContext(), "AUTO_UPDATE_DATA") != 0) || (!NetUtil.checkAccessNetWork(1)));
    c.b(localf);
  }
  
  public static void a(String paramString1, String paramString2)
  {
    try
    {
      long l2 = SysParamEntityManager.getLongParam("LastPublicUpdate", -1L, Constant.getContext());
      long l1 = l2;
      if (l2 == -1L)
      {
        SysParamEntityManager.setParam("LastPublicUpdate", String.valueOf(System.currentTimeMillis()));
        l1 = System.currentTimeMillis();
      }
      l2 = DexUtil.getUpdateCycleByType(1, 2592000000L);
      if (System.currentTimeMillis() > l1 + l2)
      {
        e.e();
        b(paramString1, paramString2);
      }
      return;
    }
    catch (Throwable paramString1)
    {
      paramString1.printStackTrace();
      LogManager.e("PubInfoService", "checkPubInfoUpdate " + paramString1.getMessage(), paramString1);
    }
  }
  
  private static void a(String paramString1, String paramString2, int paramInt, String paramString3, String paramString4, XyCallBack paramXyCallBack, boolean paramBoolean)
  {
    if (SysParamEntityManager.getIntParam(Constant.getContext(), "QUERY_ONLINE") == 0) {
      return;
    }
    IccidLocationUtil.pubInfoPool.execute(new cd(paramBoolean, paramString1, paramString2, paramString3, paramString4, paramInt, paramXyCallBack));
  }
  
  public static void a(Map<String, String> paramMap, XyCallBack paramXyCallBack)
  {
    f localf = c.c();
    new StringBuilder(" menuInfo=").append(localf);
    if (localf == null) {
      c.c("menuMain");
    }
    c.a(paramMap, paramXyCallBack);
  }
  
  public static void a(String[] paramArrayOfString)
  {
    if (paramArrayOfString == null) {}
    int k;
    do
    {
      return;
      k = paramArrayOfString.length;
    } while (k == 0);
    StringBuffer localStringBuffer = new StringBuffer();
    int j = 0;
    int i = 0;
    if (j >= k)
    {
      if (i > 0)
      {
        paramArrayOfString = new r();
        localStringBuffer.deleteCharAt(localStringBuffer.length() - 1);
        a = localStringBuffer.toString();
        c = "1";
        s.a(paramArrayOfString);
      }
      s.b("1");
      return;
    }
    localStringBuffer.append(paramArrayOfString[j]);
    i += 1;
    if (i == 10)
    {
      r localr = new r();
      a = localStringBuffer.toString();
      c = "1";
      s.a(localr);
      localStringBuffer.setLength(0);
      i = 0;
    }
    for (;;)
    {
      j += 1;
      break;
      localStringBuffer.append(",");
    }
  }
  
  public static String b(String paramString1, int paramInt, String paramString2, Map<String, String> paramMap, XyCallBack paramXyCallBack)
  {
    if (StringUtils.isNull(paramString1)) {
      return "";
    }
    String str1 = StringUtils.getValueByKey(paramMap, "cnum");
    Object localObject = StringUtils.getValueByKey(paramMap, "code");
    int i = e.c(StringUtils.getValueByKey(paramMap, "simIndex"));
    String str2 = StringUtils.getValueByKey(paramMap, "id");
    paramMap = (Map<String, String>)localObject;
    if (StringUtils.isNull((String)localObject)) {
      paramMap = IccidLocationUtil.getAreaCodeByCnumOrIccid(str1, i, paramString2, paramString1, true, false);
    }
    i = SysParamEntityManager.getIntParam(Constant.getContext(), "QUERY_ONLINE");
    localObject = e.a(paramString1, paramMap, paramInt);
    new StringBuilder("pubInfo=").append(localObject);
    if (localObject == null) {
      if (i == 1)
      {
        if (NetUtil.isEnhance()) {
          bs.a(paramString1, str1, paramMap, paramString2, paramInt, paramXyCallBack, 0, str2);
        }
        a();
        paramString1 = "";
      }
    }
    for (;;)
    {
      IccidLocationUtil.iccidPool.execute(new by(paramMap, paramString2));
      return paramString1;
      a();
      paramString1 = "";
      continue;
      paramString1 = JsonUtil.PubInfoToJson((JSONObject)localObject);
      XyUtil.doXycallBackResult(paramXyCallBack, new Object[] { Integer.valueOf(0), paramString1, str2 });
      a();
    }
  }
  
  public static void b(String paramString1, String paramString2)
  {
    synchronized (a)
    {
      if (b) {
        return;
      }
      b = true;
      new ca("updatePubInfo-Thread", paramString1, paramString2).start();
      return;
    }
  }
}

/* Location:
 * Qualified Name:     bx
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */