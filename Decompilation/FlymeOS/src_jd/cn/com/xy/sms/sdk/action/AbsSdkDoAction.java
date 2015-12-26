package cn.com.xy.sms.sdk.action;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.ClipboardManager;
import android.view.View;
import android.widget.Toast;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.entity.IccidInfo;
import cn.com.xy.sms.sdk.db.entity.IccidInfoManager;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.iccid.IccidLocationUtil;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.KeyManager;
import cn.com.xy.sms.sdk.util.PopupUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import cn.com.xy.sms.sdk.util.a;
import cn.com.xy.sms.sdk.util.y;
import cn.com.xy.sms.util.ParseManager;
import cn.com.xy.sms.util.SdkCallBack;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class AbsSdkDoAction
{
  public static final int DO_SEND_MAP_QUERY_URL = 4102;
  public static final int SDK_DOACTION_ERROR = -1;
  public static final int SDK_EVENT_INIT_COMPLETE = 0;
  
  private static int a(String paramString)
  {
    int i = -1;
    if (paramString != null) {}
    try
    {
      i = Integer.valueOf(paramString).intValue();
      return i;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
    return -1;
  }
  
  private int a(Map<String, String> paramMap)
  {
    if (paramMap != null) {
      return a((String)paramMap.get("simIndex"));
    }
    return -1;
  }
  
  private static void a(Context paramContext, String paramString)
  {
    String str = paramString;
    try
    {
      if (!StringUtils.isNull(paramString))
      {
        paramString = paramString.trim();
        str = paramString;
        if (!paramString.startsWith("http")) {
          str = "http://" + paramString;
        }
      }
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
      return;
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  private void a(Context paramContext, JSONObject paramJSONObject)
  {
    zfbRecharge(paramContext, paramJSONObject, null);
  }
  
  private static void a(Context paramContext, JSONObject paramJSONObject, String paramString, Map<String, String> paramMap)
  {
    JSONObject localJSONObject = paramJSONObject;
    if (paramJSONObject == null) {
      localJSONObject = new JSONObject();
    }
    localJSONObject.put("extend", new JSONObject(paramMap));
    localJSONObject.put("type", paramString);
    if (paramMap.containsKey("exphone")) {
      localJSONObject.put("exphone", paramMap.get("exphone"));
    }
    if (paramMap.containsKey("simIndex")) {
      localJSONObject.put("simIndex", paramMap.get("simIndex"));
    }
    PopupUtil.startWebActivity(paramContext, localJSONObject, paramString, null);
  }
  
  private static boolean a(String paramString1, String paramString2)
  {
    new StringBuilder("channel:").append(paramString1).append(" type:").append(paramString2);
    int i = DexUtil.isServiceChoose(paramString1, paramString2);
    if (i == 0) {
      new StringBuilder("DexUtil %%% SERVICE_TYPE_DISABLE channel:").append(paramString1).append(" type:").append(paramString2);
    }
    do
    {
      return false;
      if (i >= 0) {
        break;
      }
      new StringBuilder("SDK ###  channel:").append(paramString1).append(" type:").append(paramString2);
    } while (("VMhlWdEwVNEW_LENOVO".equals(paramString1)) || ("1i1BDH2wONE+".equals(paramString1)) || (("3GdfMSKwHUAWEI".equals(paramString1)) && (!"repayment".equalsIgnoreCase(paramString2)) && (!"zfb_repayment".equalsIgnoreCase(paramString2))));
    return true;
  }
  
  private static Map<String, Integer> b(String paramString1, String paramString2)
  {
    HashMap localHashMap = new HashMap();
    try
    {
      int i = Integer.parseInt(paramString1.split("年")[0]);
      int j = Integer.parseInt(paramString1.split("月")[0].split("年")[1]);
      int k = Integer.parseInt(paramString1.split("月")[1].split("日")[0]);
      int m = Integer.parseInt(paramString2.split(":")[0]);
      int n = Integer.parseInt(paramString2.split(":")[1]);
      localHashMap.put("company_meetingreminder_date_year", Integer.valueOf(i));
      localHashMap.put("company_meetingreminder_date_month", Integer.valueOf(j));
      localHashMap.put("company_meetingreminder_date_day", Integer.valueOf(k));
      localHashMap.put("company_meetingreminder_time_hour", Integer.valueOf(m));
      localHashMap.put("company_meetingreminder_time_minute", Integer.valueOf(n));
      return localHashMap;
    }
    catch (Throwable paramString1)
    {
      paramString1.printStackTrace();
    }
    return localHashMap;
  }
  
  private static JSONObject b(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    try
    {
      paramString = new JSONObject(StringUtils.decode(paramString));
      return paramString;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  private static String c(String paramString)
  {
    String[] arrayOfString;
    int j;
    int i;
    if (paramString != null)
    {
      arrayOfString = paramString.split("__ARR__");
      j = arrayOfString.length;
      i = 0;
    }
    for (;;)
    {
      if (i >= j) {
        paramString = "";
      }
      String str;
      do
      {
        return paramString;
        str = arrayOfString[i];
        paramString = str;
      } while (!"null".equalsIgnoreCase(str));
      i += 1;
    }
  }
  
  public static void startDisplaySceneActivity(Context paramContext, JSONObject paramJSONObject, String paramString)
  {
    try
    {
      Intent localIntent = new Intent();
      localIntent.putExtra("actionType", paramString);
      if (paramJSONObject != null) {
        localIntent.putExtra("JSONDATA", paramJSONObject.toString());
      }
      localIntent.setClassName(paramContext, "cn.com.xy.sms.sdk.ui.popu.web.SdkDisplaySceneActivity");
      localIntent.setFlags(268435456);
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Throwable paramContext) {}
  }
  
  public void callPhone(Context paramContext, String paramString, int paramInt)
  {
    callPhone(paramContext, paramString, paramInt, null);
  }
  
  public void callPhone(Context paramContext, String paramString, int paramInt, Map<String, String> paramMap)
  {
    paramString = paramString.replace("-", "");
    paramMap = new Intent();
    paramMap.setAction("android.intent.action.CALL");
    paramMap.setData(Uri.parse("tel:" + paramString));
    try
    {
      paramContext.startActivity(paramMap);
      return;
    }
    catch (Throwable paramContext) {}
  }
  
  public boolean checkHasAppName(Context paramContext, String paramString)
  {
    try
    {
      paramContext.getPackageManager().getPackageInfo(paramString, 1);
      return true;
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public void closePopupWindow() {}
  
  public void copyCode(Context paramContext, String paramString)
  {
    try
    {
      ((ClipboardManager)paramContext.getSystemService("clipboard")).setText(paramString);
      Toast.makeText(paramContext, "验证码已复制", 1).show();
      return;
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public void copyCode(Context paramContext, String paramString, Map<String, String> paramMap)
  {
    ((ClipboardManager)paramContext.getSystemService("clipboard")).setText(paramString);
    Toast.makeText(paramContext, "验证码已复制", 1).show();
  }
  
  public long createCard(JSONObject paramJSONObject, int paramInt, Map<String, String> paramMap)
  {
    return 0L;
  }
  
  public abstract void deleteMsgForDatabase(Context paramContext, String paramString);
  
  public void doAction(Context paramContext, String paramString, Map<String, String> paramMap)
  {
    String str1 = null;
    Object localObject2 = paramMap;
    Object localObject1 = paramMap;
    for (;;)
    {
      Object localObject3;
      try
      {
        if (StringUtils.isNull(paramString))
        {
          localObject2 = paramMap;
          localObject1 = paramMap;
          throw new Exception("doAction  actionData is null");
        }
      }
      catch (Throwable paramString)
      {
        localObject1 = localObject2;
        new StringBuilder("doAction ERROR: ").append(paramString.getMessage());
        if (localObject2 == null)
        {
          return;
          localObject2 = paramMap;
          localObject1 = paramMap;
          localObject3 = b(paramString);
          if (localObject3 == null)
          {
            localObject2 = paramMap;
            localObject1 = paramMap;
            throw new Exception("please valid  actionData ");
          }
        }
      }
      finally
      {
        if (localObject1 == null) {
          continue;
        }
      }
      try
      {
        if ("0".equals((String)((Map)localObject1).get("viewType")))
        {
          paramString = "";
          if ((localObject1 != null) && (!((Map)localObject1).isEmpty()))
          {
            if (StringUtils.isNull("")) {
              paramString = (String)((Map)localObject1).get("phone");
            }
            if (StringUtils.isNull(paramString)) {
              ((Map)localObject1).get("phoneNum");
            }
          }
          finishActivity(paramContext, (Map)localObject1);
        }
      }
      catch (Throwable paramContext)
      {
        for (;;)
        {
          String str3;
          new StringBuilder("doAction ERROR: ").append(paramContext.getMessage());
          continue;
          continue;
          continue;
          continue;
          paramString = "";
        }
      }
      throw paramMap;
      localObject2 = paramMap;
      localObject1 = paramMap;
      str3 = ((JSONObject)localObject3).optString("type");
      localObject2 = paramMap;
      localObject1 = paramMap;
      new StringBuilder("actionType=").append(str3).append(" data=").append(localObject3).append(" extend=").append(paramMap);
      localObject2 = paramMap;
      localObject1 = paramMap;
      if (StringUtils.isNull(str3))
      {
        localObject2 = paramMap;
        localObject1 = paramMap;
        throw new Exception("actionType is  null");
      }
      localObject2 = paramMap;
      localObject1 = paramMap;
      if (str3.toLowerCase().startsWith("ex_"))
      {
        localObject2 = paramMap;
        localObject1 = paramMap;
        doExAction(paramContext, str3, (JSONObject)localObject3, paramMap);
        paramString = paramMap;
      }
      for (;;)
      {
        if (paramString == null) {
          break label2689;
        }
        try
        {
          if (!"0".equals((String)paramString.get("viewType"))) {
            break;
          }
          paramMap = "";
          if ((paramString != null) && (!paramString.isEmpty()))
          {
            if (StringUtils.isNull("")) {
              paramMap = (String)paramString.get("phone");
            }
            if (StringUtils.isNull(paramMap)) {
              paramString.get("phoneNum");
            }
          }
          finishActivity(paramContext, paramString);
          return;
        }
        catch (Throwable paramContext)
        {
          new StringBuilder("doAction ERROR: ").append(paramContext.getMessage());
          return;
        }
        localObject2 = paramMap;
        localObject1 = paramMap;
        String str2;
        if ("reply_sms".equalsIgnoreCase(str3))
        {
          localObject2 = paramMap;
          localObject1 = paramMap;
          str1 = (String)JsonUtil.getValueFromJsonObject((JSONObject)localObject3, "send_code");
          localObject2 = paramMap;
          localObject1 = paramMap;
          paramString = (String)JsonUtil.getValueFromJsonObject((JSONObject)localObject3, "phone");
          localObject2 = paramMap;
          localObject1 = paramMap;
          if (!StringUtils.isNull(paramString))
          {
            localObject2 = paramMap;
            localObject1 = paramMap;
            if (!"null".equalsIgnoreCase(paramString)) {
              break label2827;
            }
          }
          localObject2 = paramMap;
          localObject1 = paramMap;
          paramString = (String)paramMap.get("phoneNum");
          localObject2 = paramMap;
          localObject1 = paramMap;
          sendSms(paramContext, paramString, str1, a(paramMap), paramMap);
          paramString = paramMap;
        }
        else
        {
          localObject2 = paramMap;
          localObject1 = paramMap;
          if ("send_sms".equalsIgnoreCase(str3))
          {
            localObject2 = paramMap;
            localObject1 = paramMap;
            paramString = (String)JsonUtil.getValueFromJsonObject((JSONObject)localObject3, "send_code");
            localObject2 = paramMap;
            localObject1 = paramMap;
            sendSms(paramContext, (String)JsonUtil.getValueFromJsonObject((JSONObject)localObject3, "phone"), paramString, a(paramMap), paramMap);
            paramString = paramMap;
          }
          else
          {
            localObject2 = paramMap;
            localObject1 = paramMap;
            if (!"access_url".equalsIgnoreCase(str3))
            {
              localObject2 = paramMap;
              localObject1 = paramMap;
              if (!"open_url".equalsIgnoreCase(str3)) {}
            }
            else
            {
              localObject2 = paramMap;
              localObject1 = paramMap;
              openUrl(paramContext, (String)JsonUtil.getValueFromJsonObject((JSONObject)localObject3, "url"), (JSONObject)localObject3);
              paramString = paramMap;
              continue;
            }
            localObject2 = paramMap;
            localObject1 = paramMap;
            if ("down_url".equalsIgnoreCase(str3))
            {
              localObject2 = paramMap;
              localObject1 = paramMap;
              downLoadUrl(paramContext, (String)JsonUtil.getValueFromJsonObject((JSONObject)localObject3, "url"));
              paramString = paramMap;
            }
            else
            {
              localObject2 = paramMap;
              localObject1 = paramMap;
              if ("download".equalsIgnoreCase(str3))
              {
                localObject2 = paramMap;
                localObject1 = paramMap;
                str1 = (String)JsonUtil.getValueFromJsonObject((JSONObject)localObject3, "appName");
                localObject2 = paramMap;
                localObject1 = paramMap;
                if (!StringUtils.isNull(str1)) {
                  break label2824;
                }
                localObject2 = paramMap;
                localObject1 = paramMap;
                str1 = (String)JsonUtil.getValueFromJsonObject((JSONObject)localObject3, "extend");
                localObject2 = paramMap;
                localObject1 = paramMap;
                str3 = (String)JsonUtil.getValueFromJsonObject((JSONObject)localObject3, "url");
                paramString = paramMap;
                if (paramMap == null)
                {
                  localObject2 = paramMap;
                  localObject1 = paramMap;
                  paramString = new HashMap();
                }
                localObject2 = paramString;
                localObject1 = paramString;
                paramString.put("menuName", (String)JsonUtil.getValueFromJsonObject((JSONObject)localObject3, "menuName"));
                localObject2 = paramString;
                localObject1 = paramString;
                downLoadApp(paramContext, str1, str3, paramString);
              }
              else
              {
                localObject2 = paramMap;
                localObject1 = paramMap;
                if ("weibo_url".equalsIgnoreCase(str3))
                {
                  localObject2 = paramMap;
                  localObject1 = paramMap;
                  openWeiBoUrl(paramContext, (String)JsonUtil.getValueFromJsonObject((JSONObject)localObject3, "url"));
                  paramString = paramMap;
                }
                else
                {
                  localObject2 = paramMap;
                  localObject1 = paramMap;
                  if (!"call_phone".equalsIgnoreCase(str3))
                  {
                    localObject2 = paramMap;
                    localObject1 = paramMap;
                    if (!"call".equalsIgnoreCase(str3)) {}
                  }
                  else
                  {
                    localObject2 = paramMap;
                    localObject1 = paramMap;
                    if (((JSONObject)localObject3).has("phone"))
                    {
                      localObject2 = paramMap;
                      localObject1 = paramMap;
                      paramString = ((JSONObject)localObject3).getString("phone");
                    }
                    for (;;)
                    {
                      localObject2 = paramMap;
                      localObject1 = paramMap;
                      callPhone(paramContext, c(paramString), a(paramMap));
                      paramString = paramMap;
                      break;
                      localObject2 = paramMap;
                      localObject1 = paramMap;
                      paramString = str1;
                      if (((JSONObject)localObject3).has("phoneNum"))
                      {
                        localObject2 = paramMap;
                        localObject1 = paramMap;
                        paramString = ((JSONObject)localObject3).getString("phoneNum");
                      }
                    }
                  }
                  localObject2 = paramMap;
                  localObject1 = paramMap;
                  paramString = paramMap;
                  if (!"send_email".equalsIgnoreCase(str3))
                  {
                    localObject2 = paramMap;
                    localObject1 = paramMap;
                    if (!"map_site".equalsIgnoreCase(str3))
                    {
                      localObject2 = paramMap;
                      localObject1 = paramMap;
                      if (!"open_map".equalsIgnoreCase(str3)) {}
                    }
                    else
                    {
                      localObject2 = paramMap;
                      localObject1 = paramMap;
                      openMap(paramContext, (String)JsonUtil.getValueFromJsonObject((JSONObject)localObject3, "address"));
                      paramString = paramMap;
                      continue;
                    }
                    localObject2 = paramMap;
                    localObject1 = paramMap;
                    if ("recharge".equalsIgnoreCase(str3))
                    {
                      localObject2 = paramMap;
                      localObject1 = paramMap;
                      if (a(KeyManager.channel, str3))
                      {
                        localObject2 = paramMap;
                        localObject1 = paramMap;
                        a(paramContext, (JSONObject)localObject3, "WEB_RECHARGE_CHOOSE", paramMap);
                        paramString = paramMap;
                      }
                      else
                      {
                        localObject2 = paramMap;
                        localObject1 = paramMap;
                        paramString = paramMap;
                        if (recharge(paramContext, (JSONObject)localObject3, paramMap) == -1)
                        {
                          localObject2 = paramMap;
                          localObject1 = paramMap;
                          a(paramContext, (JSONObject)localObject3, "WEB_RECHARGE_CHOOSE", paramMap);
                          paramString = paramMap;
                        }
                      }
                    }
                    else
                    {
                      localObject2 = paramMap;
                      localObject1 = paramMap;
                      if ("repayment".equalsIgnoreCase(str3))
                      {
                        localObject2 = paramMap;
                        localObject1 = paramMap;
                        if (a(KeyManager.channel, str3))
                        {
                          localObject2 = paramMap;
                          localObject1 = paramMap;
                          a(paramContext, (JSONObject)localObject3, "WEB_REPAYMENT_CHOOSE", paramMap);
                          paramString = paramMap;
                        }
                        else
                        {
                          localObject2 = paramMap;
                          localObject1 = paramMap;
                          if (checkHasAppName(paramContext, ((JSONObject)localObject3).getString("appName")))
                          {
                            localObject2 = paramMap;
                            localObject1 = paramMap;
                            repayment(paramContext, (JSONObject)localObject3);
                            paramString = paramMap;
                          }
                          else
                          {
                            localObject2 = paramMap;
                            localObject1 = paramMap;
                            ((JSONObject)localObject3).put("type", "WEB_REPAYMENT");
                            localObject2 = paramMap;
                            localObject1 = paramMap;
                            PopupUtil.startWebActivity(paramContext, (JSONObject)localObject3, "WEB_REPAYMENT", null);
                            paramString = paramMap;
                          }
                        }
                      }
                      else
                      {
                        localObject2 = paramMap;
                        localObject1 = paramMap;
                        if ("zfb_recharge".equalsIgnoreCase(str3))
                        {
                          localObject2 = paramMap;
                          localObject1 = paramMap;
                          if (a(KeyManager.channel, str3))
                          {
                            localObject2 = paramMap;
                            localObject1 = paramMap;
                            a(paramContext, (JSONObject)localObject3, "WEB_RECHARGE_CHOOSE", paramMap);
                            paramString = paramMap;
                          }
                          else
                          {
                            localObject2 = paramMap;
                            localObject1 = paramMap;
                            zfbRecharge(paramContext, (JSONObject)localObject3, paramMap);
                            paramString = paramMap;
                          }
                        }
                        else
                        {
                          localObject2 = paramMap;
                          localObject1 = paramMap;
                          if ("zfb_repayment".equalsIgnoreCase(str3))
                          {
                            localObject2 = paramMap;
                            localObject1 = paramMap;
                            if (a(KeyManager.channel, str3))
                            {
                              localObject2 = paramMap;
                              localObject1 = paramMap;
                              a(paramContext, (JSONObject)localObject3, "WEB_REPAYMENT_CHOOSE", paramMap);
                              paramString = paramMap;
                            }
                            else
                            {
                              localObject2 = paramMap;
                              localObject1 = paramMap;
                              zfbRepayment(paramContext, (JSONObject)localObject3);
                              paramString = paramMap;
                            }
                          }
                          else
                          {
                            localObject2 = paramMap;
                            localObject1 = paramMap;
                            if ("open_app".equalsIgnoreCase(str3))
                            {
                              localObject2 = paramMap;
                              localObject1 = paramMap;
                              str1 = ((JSONObject)localObject3).getString("appName");
                              localObject2 = paramMap;
                              localObject1 = paramMap;
                              paramString = str1;
                              if (StringUtils.isNull(str1))
                              {
                                localObject2 = paramMap;
                                localObject1 = paramMap;
                                paramString = ((JSONObject)localObject3).getString("exthend");
                              }
                              localObject1 = paramMap;
                              try
                              {
                                str1 = ((JSONObject)localObject3).getString("appDownUrl");
                                localObject2 = paramMap;
                                localObject1 = paramMap;
                                if (!StringUtils.isNull(paramString))
                                {
                                  localObject2 = paramMap;
                                  localObject1 = paramMap;
                                  openAppByAppName(paramContext, paramString, str1);
                                  paramString = paramMap;
                                }
                              }
                              catch (Throwable localThrowable)
                              {
                                for (;;)
                                {
                                  localObject2 = paramMap;
                                  localObject1 = paramMap;
                                  localThrowable.printStackTrace();
                                  str2 = null;
                                }
                                localObject2 = paramMap;
                                localObject1 = paramMap;
                                localObject3 = ((JSONObject)localObject3).getString("url");
                                localObject2 = paramMap;
                                localObject1 = paramMap;
                                paramString = paramMap;
                              }
                              if (!StringUtils.isNull((String)localObject3))
                              {
                                localObject2 = paramMap;
                                localObject1 = paramMap;
                                openAppByUrl(paramContext, (String)localObject3, str2);
                                paramString = paramMap;
                              }
                            }
                            else
                            {
                              localObject2 = paramMap;
                              localObject1 = paramMap;
                              boolean bool = "time_remind".equalsIgnoreCase(str3);
                              if (bool)
                              {
                                localObject1 = paramMap;
                                try
                                {
                                  paramString = (String)paramMap.get("phoneNum");
                                  localObject1 = paramMap;
                                  localObject2 = (String)paramMap.get("content");
                                  localObject1 = paramMap;
                                  new StringBuilder("phoneNum=").append(paramString).append("content=").append((String)localObject2);
                                  localObject1 = paramMap;
                                  new StringBuilder("extend=").append(paramMap);
                                  localObject1 = paramMap;
                                  paramString = ParseManager.parseMsgToMap(Constant.getContext(), paramString, "", (String)localObject2, null);
                                  localObject1 = paramMap;
                                  doRemind(paramContext, (String)paramMap.get("msgId"), paramString);
                                  paramString = paramMap;
                                }
                                catch (Throwable paramString)
                                {
                                  localObject2 = paramMap;
                                  localObject1 = paramMap;
                                  paramString.printStackTrace();
                                  paramString = paramMap;
                                }
                              }
                              else
                              {
                                localObject2 = paramMap;
                                localObject1 = paramMap;
                                bool = "sdk_time_remind".equalsIgnoreCase(str3);
                                if (bool)
                                {
                                  localObject2 = "";
                                  paramString = (String)localObject2;
                                  if (paramMap != null)
                                  {
                                    localObject1 = paramMap;
                                    paramString = (String)localObject2;
                                  }
                                  try
                                  {
                                    if (!paramMap.isEmpty())
                                    {
                                      localObject1 = paramMap;
                                      paramString = (String)paramMap.get("msgId");
                                    }
                                    localObject1 = paramMap;
                                    doRemind(paramContext, paramString, (String)JsonUtil.getValueFromJsonObject((JSONObject)localObject3, "title"), (String)JsonUtil.getValueFromJsonObject((JSONObject)localObject3, "eventLocation"), (String)JsonUtil.getValueFromJsonObject((JSONObject)localObject3, "description"), (String)JsonUtil.getValueFromJsonObject((JSONObject)localObject3, "startTime"), (String)JsonUtil.getValueFromJsonObject((JSONObject)localObject3, "endTime"), paramMap);
                                    paramString = paramMap;
                                  }
                                  catch (Throwable paramString)
                                  {
                                    localObject2 = paramMap;
                                    localObject1 = paramMap;
                                    paramString.printStackTrace();
                                    paramString = paramMap;
                                  }
                                }
                                else
                                {
                                  localObject2 = paramMap;
                                  localObject1 = paramMap;
                                  if ("copy_code".equalsIgnoreCase(str3))
                                  {
                                    localObject1 = paramMap;
                                    try
                                    {
                                      if (!((JSONObject)localObject3).has("code")) {
                                        break label2830;
                                      }
                                      localObject1 = paramMap;
                                      paramString = ((JSONObject)localObject3).getString("code");
                                      localObject2 = paramMap;
                                      localObject1 = paramMap;
                                      copyCode(paramContext, paramString, paramMap);
                                      paramString = paramMap;
                                    }
                                    catch (Throwable paramString)
                                    {
                                      localObject2 = paramMap;
                                      localObject1 = paramMap;
                                      paramString.printStackTrace();
                                      break label2830;
                                    }
                                  }
                                  else
                                  {
                                    localObject2 = paramMap;
                                    localObject1 = paramMap;
                                    bool = "del_msg".equalsIgnoreCase(str3);
                                    if (bool)
                                    {
                                      localObject1 = paramMap;
                                      try
                                      {
                                        deleteMsgForDatabase(paramContext, (String)JsonUtil.getValueFromJsonObject((JSONObject)localObject3, "msgId"));
                                        localObject1 = paramMap;
                                        finishActivity(paramContext, paramMap);
                                        paramString = paramMap;
                                      }
                                      catch (Throwable paramString)
                                      {
                                        paramString = paramMap;
                                      }
                                    }
                                    else
                                    {
                                      localObject2 = paramMap;
                                      localObject1 = paramMap;
                                      bool = "open_map_list".equalsIgnoreCase(str3);
                                      if (bool) {
                                        localObject1 = paramMap;
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
        try
        {
          paramString = (String)JsonUtil.getValueFromJsonObject((JSONObject)localObject3, "address");
          localObject1 = paramMap;
          localObject2 = (String)paramMap.get("latitude");
          localObject1 = paramMap;
          str2 = (String)paramMap.get("longitude");
          localObject1 = paramMap;
          if ("3GdfMSKwHUAWEI".equals(KeyManager.channel))
          {
            localObject1 = paramMap;
            paramMap.put("menuName", (String)JsonUtil.getValueFromJsonObject((JSONObject)localObject3, "menuName"));
            localObject1 = paramMap;
            nearSite(paramContext, paramString, (String)localObject2, str2, paramMap);
            paramString = paramMap;
            continue;
          }
          localObject1 = paramMap;
          nearSite(paramContext, paramString, (String)localObject2, str2);
          paramString = paramMap;
        }
        catch (Throwable paramString)
        {
          paramString = paramMap;
        }
        localObject2 = paramMap;
        localObject1 = paramMap;
        if ("open_map_browser".equalsIgnoreCase(str3))
        {
          localObject2 = paramMap;
          localObject1 = paramMap;
          openMapOnBrowser(paramContext, (String)JsonUtil.getValueFromJsonObject((JSONObject)localObject3, "address"));
          paramString = paramMap;
        }
        else
        {
          localObject2 = paramMap;
          localObject1 = paramMap;
          if ("pay_water_gas".equalsIgnoreCase(str3))
          {
            localObject2 = paramMap;
            localObject1 = paramMap;
            payWaterGas(paramContext, (JSONObject)localObject3, paramMap);
            paramString = paramMap;
          }
          else
          {
            localObject2 = paramMap;
            localObject1 = paramMap;
            if (str3.toUpperCase().startsWith("WEB_"))
            {
              localObject2 = paramMap;
              localObject1 = paramMap;
              if ("WEB_TRAFFIC_ORDER".equalsIgnoreCase(str3))
              {
                localObject2 = paramMap;
                localObject1 = paramMap;
                if (a(KeyManager.channel, str3))
                {
                  localObject2 = paramMap;
                  localObject1 = paramMap;
                  a(paramContext, (JSONObject)localObject3, "WEB_TRAFFIC_CHOOSE", paramMap);
                  paramString = paramMap;
                }
                else
                {
                  localObject2 = paramMap;
                  localObject1 = paramMap;
                  paramString = paramMap;
                  if (orderTraiffc(paramContext, (JSONObject)localObject3, paramMap) == -1)
                  {
                    localObject2 = paramMap;
                    localObject1 = paramMap;
                    a(paramContext, (JSONObject)localObject3, "WEB_TRAFFIC_CHOOSE", paramMap);
                    paramString = paramMap;
                  }
                }
              }
              else
              {
                str2 = "";
                paramString = str2;
                if (paramMap == null) {
                  break label2821;
                }
                localObject2 = paramMap;
                localObject1 = paramMap;
                paramString = str2;
                if (paramMap.isEmpty()) {
                  break label2821;
                }
                localObject2 = paramMap;
                localObject1 = paramMap;
                if (StringUtils.isNull(""))
                {
                  localObject2 = paramMap;
                  localObject1 = paramMap;
                  str2 = (String)paramMap.get("phone");
                }
                localObject2 = paramMap;
                localObject1 = paramMap;
                paramString = str2;
                if (!StringUtils.isNull(str2)) {
                  break label2821;
                }
                localObject2 = paramMap;
                localObject1 = paramMap;
                paramString = (String)paramMap.get("phoneNum");
                if (paramMap != null)
                {
                  localObject2 = paramMap;
                  localObject1 = paramMap;
                  str2 = (String)paramMap.get("simIndex");
                  if (str2 != null)
                  {
                    localObject2 = paramMap;
                    localObject1 = paramMap;
                  }
                }
                try
                {
                  ((JSONObject)localObject3).put("simIndex", str2);
                  localObject2 = paramMap;
                  localObject1 = paramMap;
                  PopupUtil.startWebActivity(paramContext, (JSONObject)localObject3, str3, paramString);
                  paramString = paramMap;
                }
                catch (JSONException localJSONException)
                {
                  for (;;)
                  {
                    localObject2 = paramMap;
                    localObject1 = paramMap;
                    localJSONException.printStackTrace();
                  }
                }
              }
            }
            else
            {
              localObject2 = paramMap;
              localObject1 = paramMap;
              paramString = paramMap;
              if ("display_scene_result".equalsIgnoreCase(str3))
              {
                localObject2 = paramMap;
                localObject1 = paramMap;
                startDisplaySceneActivity(paramContext, (JSONObject)localObject3, str3);
                paramString = paramMap;
              }
            }
          }
        }
      }
      label2689:
      continue;
      try
      {
        if (!"0".equals((String)((Map)localObject2).get("viewType"))) {
          continue;
        }
        paramString = "";
        if ((localObject2 != null) && (!((Map)localObject2).isEmpty()))
        {
          if (StringUtils.isNull("")) {
            paramString = (String)((Map)localObject2).get("phone");
          }
          if (StringUtils.isNull(paramString)) {
            ((Map)localObject2).get("phoneNum");
          }
        }
        finishActivity(paramContext, (Map)localObject2);
        return;
      }
      catch (Throwable paramContext)
      {
        new StringBuilder("doAction ERROR: ").append(paramContext.getMessage());
        return;
      }
    }
  }
  
  public void doExAction(Context paramContext, String paramString, JSONObject paramJSONObject, Map<String, String> paramMap) {}
  
  public void doRemind(Activity paramActivity, Map<String, Object> paramMap) {}
  
  public void doRemind(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, Map<String, String> paramMap)
  {
    try
    {
      paramString1 = new Intent();
      paramString1.setType("vnd.android.cursor.item/event");
      paramString1.setAction("android.intent.action.EDIT");
      if (!StringUtils.isNull(paramString2)) {
        paramString1.putExtra("title", paramString2);
      }
      if (!StringUtils.isNull(paramString3)) {
        paramString1.putExtra("eventLocation", paramString3);
      }
      if (!StringUtils.isNull(paramString4)) {
        paramString1.putExtra("description", paramString4);
      }
      long l = StringUtils.getLongByString(paramString5);
      if (l != -1L) {
        paramString1.putExtra("beginTime", l);
      }
      l = StringUtils.getLongByString(paramString6);
      if (l != -1L) {
        paramString1.putExtra("endTime", l);
      }
      paramString1.putExtra("accessLevel", 0);
      paramContext.startActivity(paramString1);
      return;
    }
    catch (Throwable paramContext) {}
  }
  
  public void doRemind(Context paramContext, String paramString, Map<String, Object> paramMap)
  {
    if ((paramContext == null) || (paramMap == null) || (paramMap.size() == 0))
    {
      if (LogManager.debug) {
        LogManager.e("doRemind", "activity为null或extend为空", null);
      }
      return;
    }
    if (paramMap.containsKey("title_name")) {}
    for (String str2 = paramMap.get("title_name").toString();; str2 = null)
    {
      if (paramMap.containsKey("company_meetingreminder_add")) {}
      for (String str3 = paramMap.get("company_meetingreminder_add").toString();; str3 = null)
      {
        if (paramMap.containsKey("company_meetingreminder_Convener")) {}
        for (String str1 = "召集人:" + paramMap.get("company_meetingreminder_Convener") + "  ";; str1 = null)
        {
          paramString = str1;
          if (paramMap.containsKey("company_meetingreminder_theme")) {
            paramString = str1 + "主题:" + paramMap.get("company_meetingreminder_theme").toString();
          }
          str1 = paramString;
          if (paramMap.containsKey("company_meetingreminder_date")) {
            str1 = paramString + "  时间:" + paramMap.get("company_meetingreminder_date");
          }
          if (paramMap.containsKey("company_meetingreminder_time")) {}
          for (paramString = str1 + " " + paramMap.get("company_meetingreminder_time");; paramString = str1)
          {
            paramMap = b(paramMap.get("company_meetingreminder_date").toString(), paramMap.get("company_meetingreminder_time").toString());
            if (paramMap.containsKey("company_meetingreminder_date_year")) {}
            for (int i = ((Integer)paramMap.get("company_meetingreminder_date_year")).intValue();; i = -1)
            {
              if (paramMap.containsKey("company_meetingreminder_date_month")) {}
              for (int j = ((Integer)paramMap.get("company_meetingreminder_date_month")).intValue();; j = -1)
              {
                if (paramMap.containsKey("company_meetingreminder_date_day")) {}
                for (int k = ((Integer)paramMap.get("company_meetingreminder_date_day")).intValue();; k = -1)
                {
                  if (paramMap.containsKey("company_meetingreminder_time_hour")) {}
                  for (int m = ((Integer)paramMap.get("company_meetingreminder_time_hour")).intValue();; m = -1)
                  {
                    if (paramMap.containsKey("company_meetingreminder_time_minute")) {}
                    for (int n = ((Integer)paramMap.get("company_meetingreminder_time_minute")).intValue();; n = -1)
                    {
                      if (paramMap.containsKey("company_meetingreminder_minutes_early")) {}
                      for (int i1 = ((Integer)paramMap.get("company_meetingreminder_minutes_early")).intValue();; i1 = 120)
                      {
                        paramMap = new Intent();
                        paramMap.setType("vnd.android.cursor.item/event");
                        paramMap.setAction("android.intent.action.EDIT");
                        paramMap.putExtra("title", str2);
                        paramMap.putExtra("eventLocation", str3);
                        paramMap.putExtra("description", paramString);
                        if ((i != -1) && (j != -1) && (k != -1) && (m != -1) && (n != -1) && (i1 != -1))
                        {
                          paramString = Calendar.getInstance();
                          paramString.set(i, j - 1, k, m, n);
                          paramString.add(12, -i1);
                          paramMap.putExtra("beginTime", paramString.getTimeInMillis());
                          paramString.set(i, j - 1, k, m, n);
                          paramString.add(12, -10);
                          paramMap.putExtra("endTime", paramString.getTimeInMillis());
                        }
                        paramMap.putExtra("accessLevel", 0);
                        paramMap.putExtra("availability", 2);
                        paramContext.startActivity(paramMap);
                        return;
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  
  public void downLoadApp(Context paramContext, String paramString1, String paramString2, Map<String, String> paramMap)
  {
    int j = 0;
    int i = j;
    try
    {
      if (!StringUtils.isNull(paramString1))
      {
        if (checkHasAppName(paramContext, paramString1))
        {
          openAppByAppName(paramContext, paramString1);
          i = 1;
        }
      }
      else if (i == 0)
      {
        Object localObject = null;
        paramString1 = (String)localObject;
        if (paramMap != null)
        {
          paramString1 = (String)localObject;
          if (paramMap.containsKey("menuName")) {
            paramString1 = new JSONObject();
          }
        }
      }
    }
    catch (Throwable paramString1)
    {
      try
      {
        for (;;)
        {
          paramString1.put("menuName", paramMap.get("menuName"));
          openUrl(paramContext, paramString2, paramString1);
          return;
          paramString1 = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramString1));
          paramString1.addFlags(268435456);
          paramContext.startActivity(paramString1);
        }
        paramString1 = paramString1;
        i = j;
      }
      catch (JSONException paramMap)
      {
        for (;;)
        {
          paramMap.printStackTrace();
        }
      }
    }
  }
  
  public void downLoadUrl(Context paramContext, String paramString)
  {
    a(paramContext, paramString);
  }
  
  public void finishActivity(Context paramContext)
  {
    finishActivity(paramContext, null);
  }
  
  public void finishActivity(Context paramContext, Map<String, String> paramMap)
  {
    if ((paramContext != null) && ((paramContext instanceof Activity))) {
      ((Activity)paramContext).finish();
    }
  }
  
  public abstract String getContactName(Context paramContext, String paramString);
  
  public JSONObject getContactObj(Context paramContext, String paramString)
  {
    return null;
  }
  
  public Drawable getDrawableByNumber(Context paramContext, String paramString, Map<String, Object> paramMap)
  {
    return null;
  }
  
  public JSONObject getExtendValue(int paramInt, JSONObject paramJSONObject)
  {
    switch (paramInt)
    {
    default: 
      return null;
    }
    paramInt = -1;
    if (paramJSONObject != null) {
      paramInt = a((String)JsonUtil.getValFromJsonObject(paramJSONObject, "simIndex"));
    }
    paramJSONObject = getProviceAndSP(paramInt);
    if (paramJSONObject == null) {}
    try
    {
      IccidInfo localIccidInfo = IccidInfoManager.queryDeftIccidInfo(Constant.getContext());
      if (localIccidInfo == null) {
        return paramJSONObject;
      }
      JSONObject localJSONObject = new JSONObject();
      try
      {
        localJSONObject.put("provice", areaCode);
        localJSONObject.put("sp", operator);
        return localJSONObject;
      }
      catch (Throwable localThrowable1)
      {
        paramJSONObject = localJSONObject;
      }
    }
    catch (Throwable localThrowable2)
    {
      String str;
      for (;;) {}
    }
    localThrowable1.printStackTrace();
    return paramJSONObject;
    str = IccidInfoManager.getProviceCode((String)JsonUtil.getValueFromJsonObject(paramJSONObject, "provice"));
    if (str != null)
    {
      paramJSONObject.put("provice", str);
      return paramJSONObject;
    }
    return paramJSONObject;
  }
  
  public Drawable getHeadDrawableByNumber(Context paramContext, String paramString, Map<String, Object> paramMap)
  {
    return null;
  }
  
  public int[] getHeadDrawableColorByNumber(Context paramContext, String paramString, Map<String, Object> paramMap)
  {
    return null;
  }
  
  public String getIccidBySimIndex(int paramInt)
  {
    return null;
  }
  
  public void getLocation(Context paramContext, Handler paramHandler)
  {
    Object localObject = (LocationManager)paramContext.getSystemService("location");
    if ((!((LocationManager)localObject).isProviderEnabled("gps")) && (!((LocationManager)localObject).isProviderEnabled("network")))
    {
      paramHandler.obtainMessage(4100).sendToTarget();
      return;
    }
    paramContext = new Criteria();
    paramContext.setAccuracy(2);
    paramContext.setAltitudeRequired(false);
    paramContext.setBearingRequired(false);
    paramContext.setCostAllowed(true);
    paramContext.setPowerRequirement(3);
    paramContext.setSpeedRequired(false);
    paramContext = ((LocationManager)localObject).getLastKnownLocation(((LocationManager)localObject).getBestProvider(paramContext, false));
    if (paramContext == null)
    {
      localObject = ((LocationManager)localObject).getLastKnownLocation("network");
      new StringBuilder().append(localObject);
      paramContext = (Context)localObject;
      if (localObject == null)
      {
        paramHandler.obtainMessage(4100).sendToTarget();
        return;
      }
    }
    paramHandler = paramHandler.obtainMessage(4102);
    localObject = new Bundle();
    ((Bundle)localObject).putDouble("latitude", paramContext.getLatitude());
    ((Bundle)localObject).putDouble("longitude", paramContext.getLongitude());
    paramHandler.setData((Bundle)localObject);
    paramHandler.sendToTarget();
  }
  
  public String getPhoneNumberBySimIndex(int paramInt)
  {
    return null;
  }
  
  /* Error */
  public JSONObject getProviceAndSP(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: iload_1
    //   2: invokevirtual 797	cn/com/xy/sms/sdk/action/AbsSdkDoAction:getIccidBySimIndex	(I)Ljava/lang/String;
    //   5: astore_2
    //   6: aload_2
    //   7: ifnonnull +5 -> 12
    //   10: aconst_null
    //   11: areturn
    //   12: aload_2
    //   13: invokestatic 489	cn/com/xy/sms/sdk/constant/Constant:getContext	()Landroid/content/Context;
    //   16: invokestatic 801	cn/com/xy/sms/sdk/db/entity/IccidInfoManager:queryIccidInfo	(Ljava/lang/String;Landroid/content/Context;)Lcn/com/xy/sms/sdk/db/entity/IccidInfo;
    //   19: astore_3
    //   20: aload_3
    //   21: ifnull +51 -> 72
    //   24: new 105	org/json/JSONObject
    //   27: dup
    //   28: invokespecial 106	org/json/JSONObject:<init>	()V
    //   31: astore_2
    //   32: aload_2
    //   33: ldc_w 700
    //   36: aload_3
    //   37: getfield 705	cn/com/xy/sms/sdk/db/entity/IccidInfo:areaCode	Ljava/lang/String;
    //   40: invokevirtual 115	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   43: pop
    //   44: aload_2
    //   45: ldc_w 707
    //   48: aload_3
    //   49: getfield 710	cn/com/xy/sms/sdk/db/entity/IccidInfo:operator	Ljava/lang/String;
    //   52: invokevirtual 115	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   55: pop
    //   56: aload_2
    //   57: areturn
    //   58: astore_3
    //   59: aconst_null
    //   60: astore_2
    //   61: aload_3
    //   62: invokevirtual 33	java/lang/Throwable:printStackTrace	()V
    //   65: goto -9 -> 56
    //   68: astore_3
    //   69: goto -8 -> 61
    //   72: aconst_null
    //   73: astore_2
    //   74: goto -18 -> 56
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	77	0	this	AbsSdkDoAction
    //   0	77	1	paramInt	int
    //   5	69	2	localObject	Object
    //   19	30	3	localIccidInfo	IccidInfo
    //   58	4	3	localThrowable1	Throwable
    //   68	1	3	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   12	20	58	java/lang/Throwable
    //   24	32	58	java/lang/Throwable
    //   32	56	68	java/lang/Throwable
  }
  
  public List<JSONObject> getReceiveMsgByReceiveTime(String paramString, long paramLong1, long paramLong2, int paramInt)
  {
    return null;
  }
  
  public View getThirdPopupView(Context paramContext, String paramString, Map<String, Object> paramMap, SdkCallBack paramSdkCallBack)
  {
    return null;
  }
  
  public int getWifiType(Context paramContext)
  {
    return 0;
  }
  
  public boolean isContact(Context paramContext, String paramString)
  {
    return false;
  }
  
  public boolean isDoubleSimPhone()
  {
    return true;
  }
  
  /* Error */
  public java.util.Set<String> loadPublicNumbers(Context paramContext)
  {
    // Byte code:
    //   0: new 815	java/util/HashSet
    //   3: dup
    //   4: invokespecial 816	java/util/HashSet:<init>	()V
    //   7: astore 6
    //   9: aload_1
    //   10: invokevirtual 820	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   13: ldc_w 822
    //   16: invokestatic 88	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   19: iconst_1
    //   20: anewarray 44	java/lang/String
    //   23: dup
    //   24: iconst_0
    //   25: ldc_w 427
    //   28: aastore
    //   29: aconst_null
    //   30: aconst_null
    //   31: aconst_null
    //   32: invokevirtual 828	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   35: astore 4
    //   37: aload 4
    //   39: ifnull +32 -> 71
    //   42: aload 4
    //   44: astore_1
    //   45: aload 4
    //   47: ldc_w 427
    //   50: invokeinterface 833 2 0
    //   55: istore_2
    //   56: aload 4
    //   58: astore_1
    //   59: aload 4
    //   61: invokeinterface 836 1 0
    //   66: istore_3
    //   67: iload_3
    //   68: ifne +13 -> 81
    //   71: aload 4
    //   73: invokeinterface 839 1 0
    //   78: aload 6
    //   80: areturn
    //   81: aload 4
    //   83: astore_1
    //   84: aload 4
    //   86: iload_2
    //   87: invokeinterface 841 2 0
    //   92: astore 5
    //   94: aload 4
    //   96: astore_1
    //   97: aload 5
    //   99: invokestatic 55	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   102: ifne -46 -> 56
    //   105: aload 4
    //   107: astore_1
    //   108: aload 5
    //   110: invokestatic 844	cn/com/xy/sms/sdk/util/StringUtils:replaceBlank	(Ljava/lang/String;)Ljava/lang/String;
    //   113: invokestatic 847	cn/com/xy/sms/sdk/util/StringUtils:getPhoneNumberNo86	(Ljava/lang/String;)Ljava/lang/String;
    //   116: astore 5
    //   118: aload 4
    //   120: astore_1
    //   121: aload 5
    //   123: invokestatic 850	cn/com/xy/sms/sdk/util/StringUtils:isNumber	(Ljava/lang/String;)Z
    //   126: ifeq -70 -> 56
    //   129: aload 4
    //   131: astore_1
    //   132: aload 5
    //   134: invokestatic 853	cn/com/xy/sms/sdk/util/StringUtils:isPhoneNumber	(Ljava/lang/String;)Z
    //   137: ifne -81 -> 56
    //   140: aload 4
    //   142: astore_1
    //   143: aload 6
    //   145: aload 5
    //   147: invokevirtual 855	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   150: pop
    //   151: goto -95 -> 56
    //   154: astore 5
    //   156: aload 4
    //   158: astore_1
    //   159: aload 5
    //   161: invokevirtual 33	java/lang/Throwable:printStackTrace	()V
    //   164: aload 4
    //   166: invokeinterface 839 1 0
    //   171: aload 6
    //   173: areturn
    //   174: astore_1
    //   175: aload_1
    //   176: invokevirtual 33	java/lang/Throwable:printStackTrace	()V
    //   179: aload 6
    //   181: areturn
    //   182: astore 4
    //   184: aconst_null
    //   185: astore_1
    //   186: aload_1
    //   187: invokeinterface 839 1 0
    //   192: aload 4
    //   194: athrow
    //   195: astore_1
    //   196: aload_1
    //   197: invokevirtual 33	java/lang/Throwable:printStackTrace	()V
    //   200: goto -8 -> 192
    //   203: astore_1
    //   204: aload_1
    //   205: invokevirtual 33	java/lang/Throwable:printStackTrace	()V
    //   208: aload 6
    //   210: areturn
    //   211: astore 4
    //   213: goto -27 -> 186
    //   216: astore 5
    //   218: aconst_null
    //   219: astore 4
    //   221: goto -65 -> 156
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	224	0	this	AbsSdkDoAction
    //   0	224	1	paramContext	Context
    //   55	32	2	i	int
    //   66	2	3	bool	boolean
    //   35	130	4	localCursor	android.database.Cursor
    //   182	11	4	localObject1	Object
    //   211	1	4	localObject2	Object
    //   219	1	4	localObject3	Object
    //   92	54	5	str	String
    //   154	6	5	localThrowable1	Throwable
    //   216	1	5	localThrowable2	Throwable
    //   7	202	6	localHashSet	java.util.HashSet
    // Exception table:
    //   from	to	target	type
    //   45	56	154	java/lang/Throwable
    //   59	67	154	java/lang/Throwable
    //   84	94	154	java/lang/Throwable
    //   97	105	154	java/lang/Throwable
    //   108	118	154	java/lang/Throwable
    //   121	129	154	java/lang/Throwable
    //   132	140	154	java/lang/Throwable
    //   143	151	154	java/lang/Throwable
    //   164	171	174	java/lang/Throwable
    //   9	37	182	finally
    //   186	192	195	java/lang/Throwable
    //   71	78	203	java/lang/Throwable
    //   45	56	211	finally
    //   59	67	211	finally
    //   84	94	211	finally
    //   97	105	211	finally
    //   108	118	211	finally
    //   121	129	211	finally
    //   132	140	211	finally
    //   143	151	211	finally
    //   159	164	211	finally
    //   9	37	216	java/lang/Throwable
  }
  
  public void logError(String paramString1, String paramString2, Throwable paramThrowable) {}
  
  public abstract void markAsReadForDatabase(Context paramContext, String paramString);
  
  public void nearList(Context paramContext, String paramString)
  {
    a(paramContext, paramString);
  }
  
  public void nearSite(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    nearSite(paramContext, paramString1, paramString2, paramString3, null);
  }
  
  public void nearSite(Context paramContext, String paramString1, String paramString2, String paramString3, Map<String, String> paramMap)
  {
    Intent localIntent = new Intent();
    localIntent.setClassName(paramContext, "cn.com.xy.sms.sdk.ui.popu.web.NearbyPointList");
    localIntent.putExtra("address", paramString1);
    localIntent.putExtra("locationLatitude", paramString2);
    localIntent.putExtra("locationLongitude", paramString3);
    if ((paramMap != null) && (paramMap.containsKey("menuName"))) {
      localIntent.putExtra("menuName", (String)paramMap.get("menuName"));
    }
    paramContext.startActivity(localIntent);
  }
  
  public void onEventCallback(int paramInt, Map<String, Object> paramMap) {}
  
  public void openAppByAppName(Context paramContext, String paramString)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    new Intent();
    paramContext.startActivity(localPackageManager.getLaunchIntentForPackage(paramString));
  }
  
  public void openAppByAppName(Context paramContext, String paramString1, String paramString2)
  {
    if (checkHasAppName(paramContext, paramString1))
    {
      paramString2 = paramContext.getPackageManager();
      new Intent();
      paramContext.startActivity(paramString2.getLaunchIntentForPackage(paramString1));
      return;
    }
    if (!StringUtils.isNull(paramString2))
    {
      downLoadUrl(paramContext, paramString2);
      return;
    }
    Toast.makeText(paramContext, "please check is installed  " + paramString1, 1).show();
  }
  
  public void openAppByUrl(Context paramContext, String paramString) {}
  
  public void openAppByUrl(Context paramContext, String paramString1, String paramString2) {}
  
  public void openMap(Context paramContext, String paramString)
  {
    openMap(paramContext, null, paramString, 0.0D, 0.0D);
  }
  
  public void openMap(Context paramContext, String paramString1, String paramString2, double paramDouble1, double paramDouble2)
  {
    new StringBuilder("lon=").append(paramString2).append(",lat=").append(paramDouble2);
    String str = paramString2;
    try
    {
      paramString2 = paramString2.replace(" ", ",").replace("（", ",").replace("(", ",").replace("，", ",").replace("）", "").replace(")", "").replace("?", "").replace("&", "").replace("#", "").trim();
      str = paramString2;
      if (!StringUtils.isNull(paramString2))
      {
        str = paramString2;
        Intent localIntent = new Intent("android.intent.action.VIEW");
        str = paramString2;
        localIntent.setData(Uri.parse("geo:" + paramDouble2 + "," + paramDouble1 + "?q=" + paramString2));
        str = paramString2;
        localIntent.addFlags(268435456);
        str = paramString2;
        paramContext.startActivity(localIntent);
      }
      return;
    }
    catch (Throwable paramString2)
    {
      do
      {
        paramString2.printStackTrace();
        for (;;)
        {
          try
          {
            if (StringUtils.isNull(str)) {
              break;
            }
            if ((paramDouble1 != 0.0D) && (paramDouble2 != 0.0D))
            {
              paramString1 = "http://api.map.baidu.com/marker?location=" + paramDouble2 + "," + paramDouble1 + "&title=" + paramString1 + "&content=" + str;
              paramString1 = new Intent("android.intent.action.VIEW", Uri.parse(paramString1 + "&output=html&src=xiaoyuan|多趣"));
              paramString1.addFlags(268435456);
              paramContext.startActivity(paramString1);
              return;
            }
          }
          catch (Throwable paramContext)
          {
            paramContext.printStackTrace();
            return;
          }
          paramString1 = "http://api.map.baidu.com/geocoder?address=" + str;
        }
      } while ((paramDouble1 == 0.0D) || (paramDouble2 == 0.0D));
      paramString1 = new Intent("android.intent.action.VIEW", Uri.parse("http://api.map.baidu.com/geocoder?location=" + paramDouble2 + "," + paramDouble1 + "&coord_type=gcj02&output=html&src=xiaoyuan|多趣"));
      paramString1.addFlags(268435456);
      paramContext.startActivity(paramString1);
    }
  }
  
  public void openMapOnBrowser(Context paramContext, String paramString)
  {
    openMapOnBrowser(paramContext, null, paramString, 0.0D, 0.0D);
  }
  
  public void openMapOnBrowser(Context paramContext, String paramString1, String paramString2, double paramDouble1, double paramDouble2)
  {
    try
    {
      if (!StringUtils.isNull(paramString2))
      {
        if ((paramDouble1 != 0.0D) && (paramDouble2 != 0.0D)) {}
        for (paramString1 = "http://api.map.baidu.com/marker?location=" + paramDouble2 + "," + paramDouble1 + "&title=" + paramString1 + "&content=" + paramString2;; paramString1 = "http://api.map.baidu.com/geocoder?address=" + paramString2)
        {
          paramString1 = new Intent("android.intent.action.VIEW", Uri.parse(paramString1 + "&output=html&src=xiaoyuan|多趣"));
          paramString1.addFlags(268435456);
          paramContext.startActivity(paramString1);
          return;
        }
      }
      if ((paramDouble1 != 0.0D) && (paramDouble2 != 0.0D))
      {
        paramString1 = new Intent("android.intent.action.VIEW", Uri.parse("http://api.map.baidu.com/geocoder?location=" + paramDouble2 + "," + paramDouble1 + "&coord_type=gcj02&output=html&src=xiaoyuan|多趣"));
        paramString1.addFlags(268435456);
        paramContext.startActivity(paramString1);
        return;
      }
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public abstract void openSms(Context paramContext, String paramString, Map<String, String> paramMap);
  
  public void openSmsDetail(Context paramContext, String paramString, Map paramMap) {}
  
  public void openUrl(Context paramContext, String paramString, JSONObject paramJSONObject)
  {
    JSONObject localJSONObject = paramJSONObject;
    if (paramJSONObject == null) {
      paramJSONObject = paramString;
    }
    try
    {
      localJSONObject = new JSONObject();
      String str = paramString;
      paramJSONObject = paramString;
      if (!StringUtils.isNull(paramString))
      {
        paramJSONObject = paramString;
        paramString = paramString.replaceAll("&amp;", "&");
        paramJSONObject = paramString;
        paramString = paramString.trim();
        str = paramString;
        paramJSONObject = paramString;
        if (!paramString.startsWith("http"))
        {
          paramJSONObject = paramString;
          str = "http://" + paramString;
        }
      }
      paramJSONObject = str;
      localJSONObject.put("url", str);
      paramJSONObject = str;
      bool = PopupUtil.startWebActivity(paramContext, localJSONObject, "WEB_URL", "");
      paramJSONObject = str;
    }
    catch (JSONException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
        boolean bool = false;
      }
    }
    if (!bool) {
      a(paramContext, paramJSONObject);
    }
  }
  
  public void openWeiBoUrl(Context paramContext, String paramString)
  {
    openUrl(paramContext, paramString, null);
  }
  
  public int orderTraiffc(Context paramContext, JSONObject paramJSONObject, Map<String, String> paramMap)
  {
    try
    {
      a(paramContext, paramJSONObject, "WEB_TRAFFIC_CHOOSE", paramMap);
      return 0;
    }
    catch (JSONException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
  }
  
  public int otherOrderTraffic(Context paramContext, JSONObject paramJSONObject, Map<String, String> paramMap)
  {
    return 0;
  }
  
  public int otherRecharge(Context paramContext, JSONObject paramJSONObject, Map<String, String> paramMap)
  {
    return 0;
  }
  
  public int otherRepayment(Context paramContext, JSONObject paramJSONObject, Map<String, String> paramMap)
  {
    return 0;
  }
  
  public int otherService(Context paramContext, JSONObject paramJSONObject, Map<String, String> paramMap)
  {
    return 0;
  }
  
  public void parseMsgCallBack(int paramInt, Map<String, Object> paramMap) {}
  
  public void parseVersionChange(int paramInt) {}
  
  public int payWaterGas(Context paramContext, JSONObject paramJSONObject, Map<String, String> paramMap)
  {
    return 0;
  }
  
  public int recharge(Context paramContext, JSONObject paramJSONObject, Map<String, String> paramMap)
  {
    zfbRecharge(paramContext, paramJSONObject, paramMap);
    return 0;
  }
  
  public void rechargeService(Context paramContext, JSONObject paramJSONObject, Map<String, String> paramMap)
  {
    Object localObject2 = null;
    if (paramJSONObject.has("type")) {
      label469:
      label472:
      for (;;)
      {
        Object localObject1;
        try
        {
          if (!"ZFB_RECHARGE".equalsIgnoreCase(paramJSONObject.getString("type"))) {
            break label472;
          }
          if (paramMap != null) {
            break label469;
          }
          localObject1 = new HashMap();
          if (paramJSONObject.has("simIndex")) {
            ((Map)localObject1).put("simIndex", paramJSONObject.getString("simIndex"));
          }
          paramMap = (Map<String, String>)localObject1;
          if (paramJSONObject.has("exphone"))
          {
            ((Map)localObject1).put("exphone", paramJSONObject.getString("exphone"));
            paramMap = (Map<String, String>)localObject1;
          }
          zfbRecharge(paramContext, paramJSONObject, paramMap);
          if ("ALIPAY_RECHARGE".equalsIgnoreCase(paramJSONObject.getString("type")))
          {
            localObject1 = paramMap;
            if (paramMap == null)
            {
              paramMap = new HashMap();
              if (paramJSONObject.has("simIndex")) {
                paramMap.put("simIndex", paramJSONObject.getString("simIndex"));
              }
              localObject1 = paramMap;
              if (paramJSONObject.has("exphone"))
              {
                paramMap.put("exphone", paramJSONObject.getString("exphone"));
                localObject1 = paramMap;
              }
            }
            String str = JsonUtil.getValFromJsonObject(paramJSONObject, "appName").toString();
            paramMap = (Map<String, String>)localObject2;
            if (localObject1 != null) {
              paramMap = (String)((Map)localObject1).get("exphone");
            }
            localObject1 = paramMap;
            if (StringUtils.isNull(paramMap)) {
              localObject1 = XyUtil.getPhoneNumber(paramContext);
            }
            if ((!StringUtils.isNull(str)) && (checkHasAppName(paramContext, str)))
            {
              y.a(paramContext, (String)localObject1);
              return;
            }
            paramJSONObject.put("type", "WEB_URL");
            PopupUtil.startWebActivity(paramContext, paramJSONObject, "WEB_URL", null);
            return;
          }
        }
        catch (JSONException paramContext)
        {
          paramContext.printStackTrace();
          return;
        }
        if ("WEXIN_RECHARGE".equalsIgnoreCase(paramJSONObject.getString("type")))
        {
          paramMap = paramJSONObject.getString("appName");
          if (checkHasAppName(paramContext, paramMap))
          {
            a.a(paramContext, paramMap);
            return;
          }
          paramJSONObject.put("type", "WEB_URL");
          PopupUtil.startWebActivity(paramContext, paramJSONObject, "WEB_URL", null);
          return;
        }
        if ("TENPAY_RECHARGE".equalsIgnoreCase(paramJSONObject.getString("type")))
        {
          paramMap = paramJSONObject.getString("appName");
          if (checkHasAppName(paramContext, paramMap))
          {
            a.a(paramContext, paramMap);
            return;
          }
          paramJSONObject.put("type", "WEB_URL");
          PopupUtil.startWebActivity(paramContext, paramJSONObject, "WEB_URL", null);
          return;
        }
        if ("BAIDU_RECHARGE".equalsIgnoreCase(paramJSONObject.getString("type")))
        {
          localObject1 = paramJSONObject.optString("url");
          paramMap = (Map<String, String>)localObject1;
          if (StringUtils.isNull((String)localObject1)) {
            paramMap = paramJSONObject.optString("appName");
          }
          openUrl(paramContext, paramMap, paramJSONObject);
          return;
        }
        otherRecharge(paramContext, paramJSONObject, paramMap);
        return;
      }
    }
  }
  
  public void repayment(Context paramContext, JSONObject paramJSONObject)
  {
    zfbRepayment(paramContext, paramJSONObject);
  }
  
  public void repaymentService(Context paramContext, JSONObject paramJSONObject, Map<String, String> paramMap)
  {
    if (paramJSONObject.has("type"))
    {
      String str;
      try
      {
        if ("ZFB_REPAYMENT".equalsIgnoreCase(paramJSONObject.getString("type")))
        {
          zfbRepayment(paramContext, paramJSONObject);
          return;
        }
        if (!"ALIPAY_REPAYMENT".equalsIgnoreCase(paramJSONObject.getString("type"))) {
          break label141;
        }
        if (checkHasAppName(paramContext, paramJSONObject.getString("appName")))
        {
          paramMap = (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "repayAmount");
          str = (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "bankCode");
          y.a(paramContext, (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "cardNumber"), paramMap, (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "holderName"), str, false);
          return;
        }
      }
      catch (JSONException paramContext)
      {
        paramContext.printStackTrace();
        return;
      }
      paramJSONObject.put("type", "WEB_URL");
      PopupUtil.startWebActivity(paramContext, paramJSONObject, "WEB_URL", null);
      return;
      label141:
      if ("WEXIN_REPAYMENT".equalsIgnoreCase(paramJSONObject.getString("type")))
      {
        paramMap = paramJSONObject.getString("appName");
        if (checkHasAppName(paramContext, paramMap))
        {
          a.a(paramContext, paramMap);
          return;
        }
        paramJSONObject.put("type", "WEB_URL");
        PopupUtil.startWebActivity(paramContext, paramJSONObject, "WEB_URL", null);
        return;
      }
      if ("TENPAY_REPAYMENT".equalsIgnoreCase(paramJSONObject.getString("type")))
      {
        paramMap = paramJSONObject.getString("appName");
        if (checkHasAppName(paramContext, paramMap))
        {
          a.a(paramContext, paramMap);
          return;
        }
        paramJSONObject.put("type", "WEB_URL");
        PopupUtil.startWebActivity(paramContext, paramJSONObject, "WEB_URL", null);
        return;
      }
      if ("BAIDU_REPAYMENT".equalsIgnoreCase(paramJSONObject.getString("type")))
      {
        str = paramJSONObject.optString("url");
        paramMap = str;
        if (StringUtils.isNull(str)) {
          paramMap = paramJSONObject.optString("appName");
        }
        openUrl(paramContext, paramMap, paramJSONObject);
        return;
      }
      otherRepayment(paramContext, paramJSONObject, paramMap);
    }
  }
  
  public void replySms(Context paramContext, String paramString1, String paramString2, String paramString3, Map<String, String> paramMap) {}
  
  public abstract void sendSms(Context paramContext, String paramString1, String paramString2, int paramInt, Map<String, String> paramMap);
  
  public void simChange()
  {
    IccidLocationUtil.changeIccidAreaCode(true);
  }
  
  public void statisticAction(String paramString, int paramInt, Map<String, Object> paramMap) {}
  
  public void toService(Context paramContext, String paramString, JSONObject paramJSONObject)
  {
    HashMap localHashMap = new HashMap();
    label247:
    for (;;)
    {
      try
      {
        if (paramJSONObject.has("extend")) {
          JsonUtil.putJsonToMap(new JSONObject(paramJSONObject.getString("extend")), localHashMap);
        }
        String str3 = paramJSONObject.optString("type");
        if ((!StringUtils.isNull(str3)) && (str3.toUpperCase().startsWith("WEB_")))
        {
          String str2 = "";
          String str1 = str2;
          if (localHashMap.isEmpty()) {
            break label247;
          }
          if (StringUtils.isNull("")) {
            str2 = (String)localHashMap.get("phone");
          }
          str1 = str2;
          if (!StringUtils.isNull(str2)) {
            break label247;
          }
          str1 = (String)localHashMap.get("phoneNum");
          str2 = (String)localHashMap.get("simIndex");
          if (str2 != null) {
            paramJSONObject.put("simIndex", str2);
          }
          PopupUtil.startWebActivity(paramContext, paramJSONObject, str3, str1);
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        if ("repaymentService".equalsIgnoreCase(paramString))
        {
          repaymentService(paramContext, paramJSONObject, localHashMap);
          return;
        }
        if ("rechargeService".equalsIgnoreCase(paramString))
        {
          rechargeService(paramContext, paramJSONObject, localHashMap);
          return;
        }
        if ("trafficService".equalsIgnoreCase(paramString))
        {
          trafficService(paramContext, paramJSONObject, localHashMap);
          return;
        }
        otherService(paramContext, paramJSONObject, localHashMap);
        return;
      }
    }
  }
  
  public void trafficService(Context paramContext, JSONObject paramJSONObject, Map<String, String> paramMap)
  {
    if (paramJSONObject.has("type")) {
      label203:
      label332:
      for (;;)
      {
        try
        {
          if ("WEB_TRAFFIC_ORDER".equalsIgnoreCase(paramJSONObject.getString("type")))
          {
            String str2 = "";
            String str1 = str2;
            if (paramMap == null) {
              break label332;
            }
            str1 = str2;
            if (paramMap.isEmpty()) {
              break label332;
            }
            if (StringUtils.isNull("")) {
              str2 = (String)paramMap.get("phone");
            }
            str1 = str2;
            if (!StringUtils.isNull(str2)) {
              break label332;
            }
            str1 = (String)paramMap.get("phoneNum");
            if (paramMap != null)
            {
              paramMap = (String)paramMap.get("simIndex");
              if (paramMap != null) {
                paramJSONObject.put("simIndex", paramMap);
              }
            }
            PopupUtil.startWebActivity(paramContext, paramJSONObject, "WEB_TRAFFIC_ORDER", str1);
            return;
          }
          if (!"TAOBAO_TRAFFIC".equalsIgnoreCase(paramJSONObject.getString("type"))) {
            break label203;
          }
          paramMap = paramJSONObject.getString("appName");
          if (checkHasAppName(paramContext, paramMap))
          {
            a.a(paramContext, paramMap);
            return;
          }
        }
        catch (JSONException paramContext)
        {
          paramContext.printStackTrace();
          return;
        }
        paramJSONObject.put("type", "WEB_URL");
        PopupUtil.startWebActivity(paramContext, paramJSONObject, "WEB_URL", null);
        return;
        if ("WEIXIN_TRAFFIC".equalsIgnoreCase(paramJSONObject.getString("type")))
        {
          paramMap = paramJSONObject.getString("appName");
          if (checkHasAppName(paramContext, paramMap))
          {
            a.a(paramContext, paramMap);
            return;
          }
          paramJSONObject.put("type", "WEB_URL");
          PopupUtil.startWebActivity(paramContext, paramJSONObject, "WEB_URL", null);
          return;
        }
        if ("TENPAY_TRAFFIC".equalsIgnoreCase(paramJSONObject.getString("type")))
        {
          paramMap = paramJSONObject.getString("appName");
          if (checkHasAppName(paramContext, paramMap))
          {
            a.a(paramContext, paramMap);
            return;
          }
          paramJSONObject.put("type", "WEB_URL");
          PopupUtil.startWebActivity(paramContext, paramJSONObject, "WEB_URL", null);
          return;
        }
        otherOrderTraffic(paramContext, paramJSONObject, paramMap);
        return;
      }
    }
  }
  
  public void zfbRecharge(Context paramContext, JSONObject paramJSONObject, Map<String, String> paramMap)
  {
    String str1 = null;
    for (;;)
    {
      try
      {
        String str3 = JsonUtil.getValFromJsonObject(paramJSONObject, "appName").toString();
        if (paramMap != null) {
          str1 = (String)paramMap.get("exphone");
        }
        if (!StringUtils.isNull(str1)) {
          break label143;
        }
        str2 = XyUtil.getPhoneNumber(paramContext);
        if ((!StringUtils.isNull(str3)) && (checkHasAppName(paramContext, str3)))
        {
          y.a(paramContext, str2);
          return;
        }
        str1 = "-1";
        if (paramMap == null) {
          break label150;
        }
        str1 = (String)paramMap.get("simIndex");
      }
      catch (Throwable paramContext)
      {
        return;
      }
      paramJSONObject.put("simIndex", paramMap);
      if (str2 != null) {
        paramJSONObject.put("chang_phone", str2);
      }
      paramJSONObject.put("type", "WEB_CHONG_ZHI");
      PopupUtil.startWebActivity(paramContext, paramJSONObject, "WEB_CHONG_ZHI", null);
      return;
      label143:
      String str2 = str1;
      continue;
      label150:
      paramMap = str1;
      if (str1 == null) {
        paramMap = "-1";
      }
    }
  }
  
  public void zfbRepayment(Context paramContext, JSONObject paramJSONObject)
  {
    try
    {
      if (checkHasAppName(paramContext, paramJSONObject.getString("appName")))
      {
        String str1 = (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "repayAmount");
        String str2 = (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "bankCode");
        y.a(paramContext, (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "cardNumber"), str1, (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "holderName"), str2, false);
        return;
      }
      paramJSONObject.put("type", "WEB_REPAYMENT");
      PopupUtil.startWebActivity(paramContext, paramJSONObject, "WEB_REPAYMENT", null);
      return;
    }
    catch (Throwable paramContext) {}
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.action.AbsSdkDoAction
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */