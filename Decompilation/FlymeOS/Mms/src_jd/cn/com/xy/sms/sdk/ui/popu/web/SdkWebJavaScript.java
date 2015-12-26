package cn.com.xy.sms.sdk.ui.popu.web;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.webkit.JavascriptInterface;
import cn.com.xy.sms.sdk.action.AbsSdkDoAction;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.net.NetWebUtil;
import cn.com.xy.sms.sdk.util.DuoquUtils;
import cn.com.xy.sms.util.ParseManager;
import dm;
import ej;
import el;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;

public class SdkWebJavaScript
{
  private dm mActivityParam;
  
  public SdkWebJavaScript(dm paramdm)
  {
    mActivityParam = paramdm;
  }
  
  @JavascriptInterface
  public void asyncRequest(String paramString1, String paramString2, String paramString3)
  {
    NetWebUtil.sendPostRequest(paramString1, paramString2, new ej(this, paramString3));
  }
  
  @JavascriptInterface
  public void asyncRequestByParamKey(String paramString1, String paramString2, String paramString3)
  {
    paramString3 = new el(this, paramString3);
    NetWebUtil.sendPostRequest(paramString1, mActivityParam.a(paramString2), paramString3);
  }
  
  @JavascriptInterface
  public boolean checkHasAppName(String paramString)
  {
    try
    {
      mActivityParam.b().getPackageManager().getPackageInfo(paramString, 1);
      return true;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  @JavascriptInterface
  public int checkOrientation()
  {
    return mActivityParam.c();
  }
  
  @JavascriptInterface
  public long closeDefService()
  {
    return ParseManager.setDefServiceSwitch(mActivityParam.b(), "0");
  }
  
  @JavascriptInterface
  public void closeWebView()
  {
    mActivityParam.b().finish();
  }
  
  @JavascriptInterface
  public boolean doAction(String paramString1, String paramString2)
  {
    HashMap localHashMap;
    if (paramString2 != null) {
      try
      {
        paramString2 = new JSONObject(paramString2);
        Iterator localIterator = paramString2.keys();
        if (localIterator != null)
        {
          localHashMap = new HashMap();
          while (localIterator.hasNext())
          {
            String str = (String)localIterator.next();
            localHashMap.put(str, (String)paramString2.get(str));
          }
        }
        return false;
      }
      catch (Exception paramString1)
      {
        paramString1.printStackTrace();
      }
    }
    boolean bool = DuoquUtils.doCustomAction(mActivityParam.b(), paramString1, localHashMap);
    return bool;
  }
  
  @JavascriptInterface
  public String getConfigByKey(String paramString)
  {
    return mActivityParam.a(paramString);
  }
  
  @JavascriptInterface
  public String getExtendValue(int paramInt, String paramString)
  {
    try
    {
      paramString = new JSONObject(paramString);
      paramString = DuoquUtils.getSdkDoAction().getExtendValue(paramInt, paramString);
      if (paramString != null)
      {
        paramString = paramString.toString();
        return paramString;
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  @JavascriptInterface
  public String getValueByKey(String paramString)
  {
    return SysParamEntityManager.queryValueParamKey(mActivityParam.b(), paramString);
  }
  
  @JavascriptInterface
  public long openDefService()
  {
    return ParseManager.setDefServiceSwitch(mActivityParam.b(), "1");
  }
  
  @JavascriptInterface
  public String queryDefServiceSwitch()
  {
    return ParseManager.queryDefService(mActivityParam.b());
  }
  
  @JavascriptInterface
  public void runOnAndroidJavaScript(String paramString) {}
  
  @JavascriptInterface
  public long saveValueByKey(String paramString1, String paramString2)
  {
    try
    {
      SysParamEntityManager.insertOrUpdateKeyValue(mActivityParam.b(), paramString1, paramString2, null);
      return 0L;
    }
    catch (Exception paramString1) {}
    return -2L;
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.ui.popu.web.SdkWebJavaScript
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */