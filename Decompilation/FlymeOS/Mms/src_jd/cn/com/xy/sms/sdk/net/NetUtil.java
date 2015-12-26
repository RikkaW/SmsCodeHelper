package cn.com.xy.sms.sdk.net;

import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.entity.IccidInfo;
import cn.com.xy.sms.sdk.db.entity.IccidInfoManager;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.util.KeyManager;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NetUtil
{
  public static String APPVERSION;
  public static final String BIZPORT_DOWN_URL = "http://down2.bizport.cn/publicnum/upload/";
  public static final String CheckResourseRequest = "checkResourseRequest";
  public static final int HTTP_ACCESS_FALIE = -8;
  public static final int HTTP_CONN_OUTTIME = -6;
  public static final int HTTP_PACKAGE_TO_BIG = -9;
  public static final int HTTP_THROWS_EXCEPTION = -7;
  public static String POPUP_SERVER_URL_HTTPS;
  public static String PUBINFO_SERVER_URL_HTTPS;
  public static final String QuerySceneRequest = "queryscene";
  public static final String REQ_QUERY_CHECI = "checi";
  public static final String REQ_QUERY_LOCATION = "location";
  public static final String REQ_QUERY_MENUINFO = "menuinfo";
  public static final String REQ_QUERY_PUBINFO = "pubinfo";
  public static final String REQ_QUERY_TOEKN = "token";
  public static String STATSERVICE_URL = "http://scene" + (int)(Math.random() * 10.0D + 1.0D) + ".bizport.cn:8981/statservice/stat/";
  public static final String UpdatePublicInfoRequest = "updatepublic";
  public static final String UpdateRecognitionJarRequest = "updatejar";
  private static String a;
  private static int b = 0;
  public static ExecutorService pool;
  public static String prex;
  public static String serverUrl;
  public static String serverUrl2 = "http://pubserver" + (int)(Math.random() * 10.0D + 1.0D) + ".bizport.cn:9998/pubNumService/";
  
  static
  {
    serverUrl = "http://smssdk" + (int)(Math.random() * 10.0D + 1.0D) + ".bizport.cn/popupservice/api/";
    prex = "http://down1.bizport.cn";
    PUBINFO_SERVER_URL_HTTPS = "https://pubapi" + (int)(Math.random() * 10.0D + 1.0D) + ".bizport.cn:9443/pubNumService/";
    POPUP_SERVER_URL_HTTPS = "https://sdkapi" + (int)(Math.random() * 10.0D + 1.0D) + ".bizport.cn:8943/popupservice/";
    APPVERSION = "201512021632";
    pool = Executors.newFixedThreadPool(1);
  }
  
  public static void QueryTokenRequest(String paramString)
  {
    try
    {
      f localf = new f();
      executeAllNetHttpRequest(cn.com.xy.sms.sdk.net.util.i.a(paramString), "990005", localf, false, true, "token", false);
      return;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static boolean checkAccessNetWork()
  {
    return checkAccessNetWork(2);
  }
  
  public static boolean checkAccessNetWork(int paramInt)
  {
    int i = SysParamEntityManager.getIntParam(Constant.getContext(), "SUPPORT_NETWORK_TYPE");
    if (i == 0) {}
    for (;;)
    {
      return false;
      if (paramInt == 2) {
        if (i == 2) {
          paramInt = XyUtil.checkNetWork(Constant.getContext(), 2);
        }
      }
      while (paramInt == 0)
      {
        return true;
        paramInt = XyUtil.checkNetWork(Constant.getContext(), 1);
        continue;
        paramInt = XyUtil.checkNetWork(Constant.getContext(), 1);
      }
    }
  }
  
  public static boolean checkAccessNetWork(Map<String, String> paramMap)
  {
    for (;;)
    {
      try
      {
        int j = XyUtil.checkNetWork(Constant.getContext());
        if (j == -1) {
          return false;
        }
        new StringBuilder("extend=").append(paramMap);
        if (paramMap != null)
        {
          bool = paramMap.isEmpty();
          if (!bool) {
            try
            {
              paramMap = (String)paramMap.get("SUPPORT_NETWORK_TYPE");
              if (StringUtils.isNull(paramMap)) {
                break label133;
              }
              i = Integer.valueOf(paramMap).intValue();
              if ((i != 0) && ((j != 1) || (i != 1))) {
                break label131;
              }
              LogManager.e("HTTP", "不支持网络连,或只支持wifi: type: " + i + " netType=" + j, null);
              return false;
            }
            catch (Throwable paramMap)
            {
              return checkAccessNetWork(2);
            }
          }
        }
        boolean bool = checkAccessNetWork(2);
        return bool;
      }
      catch (Throwable paramMap) {}
      label131:
      return true;
      label133:
      int i = 1;
    }
  }
  
  public static void executeAllNetHttpRequest(String paramString1, String paramString2, XyCallBack paramXyCallBack, boolean paramBoolean1, boolean paramBoolean2, String paramString3, boolean paramBoolean3)
  {
    if (!isEnhance()) {
      return;
    }
    String str2 = SysParamEntityManager.getStringParam(Constant.getContext(), "CUSTOM_PUBLIC_SERVER_URL");
    String str1 = paramString3;
    if (paramString3 == null) {
      str1 = "";
    }
    if (StringUtils.isNull(str2)) {}
    for (paramString3 = getPubNumServiceUrl() + str1 + "/";; paramString3 = str2 + str1 + "/")
    {
      paramString1 = new i(paramString3, paramString1, "", paramString2, paramBoolean2, paramXyCallBack, Boolean.valueOf(paramBoolean3));
      if (!paramBoolean1) {
        break;
      }
      executeRunnable(paramString1);
      return;
    }
    paramString1.run();
  }
  
  public static void executeHttpPublicRequest(String paramString1, String paramString2, XyCallBack paramXyCallBack, String paramString3, Map<String, String> paramMap)
  {
    executeRunnable(new i(paramString3, paramString2, null, "990005", false, paramXyCallBack, Boolean.valueOf(true)));
  }
  
  public static void executeHttpRequest(int paramInt1, int paramInt2, String paramString1, XyCallBack paramXyCallBack, String paramString2, boolean paramBoolean)
  {
    paramString1 = new h(paramInt2, paramString2, paramString1, paramXyCallBack, true);
    if (paramBoolean)
    {
      executeRunnable(paramString1);
      return;
    }
    paramString1.run();
  }
  
  public static void executeHttpRequest(int paramInt, String paramString1, XyCallBack paramXyCallBack, String paramString2, Map<String, String> paramMap, boolean paramBoolean)
  {
    paramString1 = new h(-1, paramString2, paramString1, paramXyCallBack, true);
    if (paramBoolean)
    {
      executeRunnable(paramString1);
      return;
    }
    paramString1.run();
  }
  
  public static void executeLoginBeforeHttpRequest(String paramString1, String paramString2, XyCallBack paramXyCallBack, String paramString3, boolean paramBoolean)
  {
    executeRunnable(new a(paramString3, null, paramString1, false, paramString2, paramXyCallBack, paramBoolean));
  }
  
  public static void executePubNumServiceHttpRequest(String paramString1, String paramString2, XyCallBack paramXyCallBack, String paramString3, boolean paramBoolean1, boolean paramBoolean2, String paramString4, boolean paramBoolean3)
  {
    String str = SysParamEntityManager.getStringParam(Constant.getContext(), "CUSTOM_PUBLIC_SERVER_URL");
    paramString3 = paramString4;
    if (paramString4 == null) {
      paramString3 = "";
    }
    if (StringUtils.isNull(str)) {}
    for (paramString3 = getPubNumServiceUrl() + paramString3 + "/";; paramString3 = str + paramString3 + "/")
    {
      paramString1 = new i(paramString3, paramString1, "", paramString2, paramBoolean2, paramXyCallBack, Boolean.valueOf(paramBoolean3));
      if (!paramBoolean1) {
        break;
      }
      executeRunnable(paramString1);
      return;
    }
    paramString1.run();
  }
  
  public static void executeRunnable(Runnable paramRunnable)
  {
    pool.execute(paramRunnable);
  }
  
  public static String getPopupServiceUrl()
  {
    if (isUseHttps()) {
      return POPUP_SERVER_URL_HTTPS;
    }
    return serverUrl;
  }
  
  public static String getPubNumServiceUrl()
  {
    if (isUseHttps()) {
      return PUBINFO_SERVER_URL_HTTPS;
    }
    return serverUrl2;
  }
  
  public static String getUrlWithPara(String paramString)
  {
    return paramString;
  }
  
  public static boolean isEnhance()
  {
    boolean bool2 = SysParamEntityManager.getBooleanParam(Constant.getContext(), "smartsms_enhance", true);
    boolean bool1;
    if (("VMhlWdEwVNEW_LENOVO".equals(KeyManager.channel)) && (bool2)) {
      bool1 = checkAccessNetWork(2);
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (!"1w36SBLwVNEW_ZTE".equals(KeyManager.channel));
      bool1 = bool2;
    } while (!bool2);
    return checkAccessNetWork(2);
  }
  
  public static boolean isUseHttps()
  {
    if (b != 0) {
      return b == 1;
    }
    try
    {
      KeyManager.initAppKey();
      i = 0;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        int i;
        localThrowable.printStackTrace();
        continue;
        i += 1;
      }
    }
    if (i >= 7)
    {
      b = 2;
      return false;
    }
    if (new String[] { "3GdfMSKwHUAWEI", "5Mj22a4wHUAWEICARD", "J8KeTyOROASamsungReminder", "SAMBANKVwIDAQAB", "SAMCLASSFIYVwIDAQAB", "5xKI47wSAMALL", "XYTEST" }[i].equals(KeyManager.channel))
    {
      b = 1;
      return true;
    }
  }
  
  public static void requestTokenIfNeed(String paramString)
  {
    if (StringUtils.isNull(SysParamEntityManager.getStringParam(Constant.getContext(), "HTTPTOKEN")))
    {
      String str = paramString;
      if (StringUtils.isNull(paramString))
      {
        IccidInfo localIccidInfo = IccidInfoManager.queryDeftIccidInfo(Constant.getContext());
        str = paramString;
        if (localIccidInfo != null) {
          str = iccid;
        }
      }
      QueryTokenRequest(str);
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.net.NetUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */