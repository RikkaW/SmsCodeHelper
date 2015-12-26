package com.ted.android.data.bubbleAction;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;
import com.ted.android.data.BubbleEntity;
import com.ted.android.data.SmsEntity;
import com.ted.android.message.BubbleUtils;
import com.ted.android.utils.TedSDKLog;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class CommonAction
  extends ActionBase
{
  private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
  private static final String TAG = CommonAction.class.getSimpleName();
  
  public CommonAction(BubbleEntity paramBubbleEntity)
  {
    super(paramBubbleEntity);
  }
  
  public CommonAction(BubbleEntity paramBubbleEntity, String paramString)
  {
    super(paramBubbleEntity, paramString);
    parseJson(paramString);
  }
  
  private void parseJson(String paramString)
  {
    paramString = new JSONObject(paramString);
    packageName = BubbleUtils.getStringWithIgnore(paramString, "packageName");
    putIntoOriginValues("packageName", packageName);
    url = BubbleUtils.getStringWithIgnore(paramString, "url");
    putIntoOriginValues("url", url);
    icon = BubbleUtils.getStringWithIgnore(paramString, "icon");
    putIntoOriginValues("icon", icon);
    appName = BubbleUtils.getStringWithIgnore(paramString, "appName");
    putIntoOriginValues("appName", appName);
    clip = BubbleUtils.getStringWithIgnore(paramString, "clip");
    putIntoOriginValues("clip", clip);
    buttonText = BubbleUtils.getStringWithIgnore(paramString, "buttonText");
    putIntoOriginValues("buttonText", buttonText);
    action = BubbleUtils.getIntWithIgnore(paramString, "action");
    putIntoOriginValues("action", String.valueOf(action));
    toast = BubbleUtils.getStringWithIgnore(paramString, "toast");
    putIntoOriginValues("toast", toast);
    number = BubbleUtils.getStringWithIgnore(paramString, "number");
    putIntoOriginValues("number", number);
    address = BubbleUtils.getStringWithIgnore(paramString, "addr");
    putIntoOriginValues("addr", address);
    priority = BubbleUtils.getIntWithIgnore(paramString, "priority");
    putIntoOriginValues("addr", address);
    isShowButton = BubbleUtils.getBooleanWithIgnore(paramString, "isShowButton");
    showType = BubbleUtils.getIntWithIgnore(paramString, "showType");
    if (showType == -1) {
      showType = 0;
    }
  }
  
  private void testAction(Context paramContext)
  {
    Toast.makeText(paramContext, "Action " + action, 0).show();
  }
  
  public boolean doAction(Context paramContext)
  {
    boolean bool2 = true;
    switch (action)
    {
    }
    for (;;)
    {
      boolean bool1 = false;
      do
      {
        do
        {
          do
          {
            return bool1;
            return false;
            return false;
            if (!TextUtils.isEmpty(url))
            {
              String str2 = url;
              str1 = str2;
              if (url.contains("type=hb"))
              {
                DateReminderAction localDateReminderAction = getFirstDateAction();
                str1 = str2;
                if (localDateReminderAction != null)
                {
                  long l = startTime;
                  str1 = DATE_FORMAT.format(new Date(l));
                  str1 = url + "&date=" + str1;
                  TedSDKLog.d(TAG, "Flight URL = " + str1);
                }
              }
              BubbleUtils.openUrl(paramContext, str1);
              return false;
            }
            TedSDKLog.e(TAG, "URL is null");
            return false;
            BubbleUtils.openUrl(paramContext, url);
            return false;
            return false;
            BubbleUtils.openApp(paramContext, packageName, appName);
            bool1 = bool2;
          } while (TextUtils.isEmpty(clip));
          BubbleUtils.setClipboard(paramContext, clip);
          bool1 = bool2;
        } while (TextUtils.isEmpty(packageName));
        bool1 = bool2;
      } while (!packageName.contains("tencent"));
      Toast.makeText(paramContext, "商家名复制至剪贴板，请在微信公众号中搜索", 0).show();
      return true;
      String str1 = null;
      if (!TextUtils.isEmpty(number)) {
        str1 = number;
      }
      while (!TextUtils.isEmpty(str1))
      {
        BubbleUtils.call(paramContext, str1);
        return false;
        if (parent != null) {
          str1 = parent.getMatchedWords();
        } else {
          Toast.makeText(paramContext, "未发现有效号码", 0).show();
        }
      }
      if (parent != null)
      {
        BubbleUtils.sendMailTo(paramContext, parent.getMatchedWords());
        return false;
        if (!TextUtils.isEmpty(address)) {}
        for (str1 = address;; str1 = parent.getMatchedWords())
        {
          BubbleUtils.openMapAppWithAddress(paramContext, str1);
          return false;
        }
        return false;
        if (parent != null)
        {
          BubbleUtils.openUrl(paramContext, String.format("http://m.kuaidi100.com/result.jsp?nu=%s", new Object[] { parent.getMatchedWords() }));
          return false;
        }
        Toast.makeText(paramContext, "未发现快递单号", 0).show();
        return false;
        if (!TextUtils.isEmpty(clip))
        {
          BubbleUtils.setClipboard(paramContext, clip);
          Toast.makeText(paramContext, clip + "已复制至剪贴板", 0).show();
          return false;
        }
        Toast.makeText(paramContext, "未发现需要复制的关键词", 0).show();
        return false;
        if (parent != null)
        {
          BubbleUtils.setClipboard(paramContext, parent.getMatchedWords());
          Toast.makeText(paramContext, "验证码已复制至剪贴板", 0).show();
          return false;
        }
        Toast.makeText(paramContext, "未发现验证码", 0).show();
      }
    }
  }
  
  public DateReminderAction getFirstDateAction()
  {
    Object localObject;
    if (parent != null)
    {
      localObject = parent.getParent();
      if (localObject != null)
      {
        localObject = ((SmsEntity)localObject).getAllActions();
        if ((localObject != null) && (((List)localObject).size() > 0)) {
          localObject = ((List)localObject).iterator();
        }
      }
    }
    ActionBase localActionBase;
    do
    {
      if (!((Iterator)localObject).hasNext()) {
        return null;
      }
      localActionBase = (ActionBase)((Iterator)localObject).next();
    } while (!(localActionBase instanceof DateReminderAction));
    return (DateReminderAction)localActionBase;
  }
  
  public boolean isAsButton()
  {
    boolean bool2 = true;
    boolean bool1 = bool2;
    if (showType != 0)
    {
      bool1 = bool2;
      if (showType != 1) {
        bool1 = false;
      }
    }
    return bool1;
  }
  
  public JSONObject toJSON()
  {
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("appName", appName);
    localJSONObject.put("packageName", packageName);
    localJSONObject.put("action", action);
    localJSONObject.put("addr", address);
    localJSONObject.put("buttonText", buttonText);
    localJSONObject.put("clip", clip);
    localJSONObject.put("icon", icon);
    localJSONObject.put("number", number);
    localJSONObject.put("toast", toast);
    localJSONObject.put("url", url);
    localJSONObject.put("isShowButton", isShowButton);
    localJSONObject.put("showType", showType);
    return localJSONObject;
  }
}

/* Location:
 * Qualified Name:     com.ted.android.data.bubbleAction.CommonAction
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */