package com.meizu.cloud.pushsdk.notification;

import android.util.Log;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class MPushMessage
  implements Serializable
{
  private static final String TAG = "MPushMessage";
  private String clickType;
  private String content;
  private Map<String, String> extra = new HashMap();
  private String isDiscard;
  private String notifyType;
  private String packageName;
  private Map<String, String> params = new HashMap();
  private String pushType;
  private String taskId;
  private String title;
  
  private static Map<String, String> getParamsMap(JSONObject paramJSONObject)
  {
    localHashMap = new HashMap();
    try
    {
      Iterator localIterator = paramJSONObject.keys();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        localHashMap.put(str, paramJSONObject.getString(str));
      }
      return localHashMap;
    }
    catch (JSONException paramJSONObject)
    {
      paramJSONObject.printStackTrace();
    }
  }
  
  public static MPushMessage parsePushMessage(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    MPushMessage localMPushMessage = new MPushMessage();
    try
    {
      paramString2 = new JSONObject(paramString2);
      localMPushMessage.setTaskId(paramString4);
      localMPushMessage.setPushType(paramString1);
      localMPushMessage.setContent(paramString2.getString("content"));
      localMPushMessage.setPackageName(paramString3);
      localMPushMessage.setIsDiscard(paramString2.getString("isDiscard"));
      localMPushMessage.setTitle(paramString2.getString("title"));
      localMPushMessage.setClickType(paramString2.getString("clickType"));
      paramString1 = paramString2.getJSONObject("extra");
      if (paramString1 != null)
      {
        paramString2 = paramString1.getJSONObject("parameters");
        if (paramString2 != null)
        {
          localMPushMessage.setParams(getParamsMap(paramString2));
          paramString1.remove("parameters");
        }
        localMPushMessage.setExtra(getParamsMap(paramString1));
      }
    }
    catch (JSONException paramString1)
    {
      for (;;)
      {
        paramString1.printStackTrace();
      }
    }
    Log.i("MPushMessage", " parsePushMessage " + localMPushMessage);
    return localMPushMessage;
  }
  
  public String getClickType()
  {
    return clickType;
  }
  
  public String getContent()
  {
    return content;
  }
  
  public Map<String, String> getExtra()
  {
    return extra;
  }
  
  public String getIsDiscard()
  {
    return isDiscard;
  }
  
  public String getNotifyType()
  {
    return notifyType;
  }
  
  public String getPackageName()
  {
    return packageName;
  }
  
  public Map<String, String> getParams()
  {
    return params;
  }
  
  public String getPushType()
  {
    return pushType;
  }
  
  public String getTaskId()
  {
    return taskId;
  }
  
  public String getTitle()
  {
    return title;
  }
  
  public void setClickType(String paramString)
  {
    clickType = paramString;
  }
  
  public void setContent(String paramString)
  {
    content = paramString;
  }
  
  public void setExtra(Map<String, String> paramMap)
  {
    extra = paramMap;
  }
  
  public void setIsDiscard(String paramString)
  {
    isDiscard = paramString;
  }
  
  public void setNotifyType(String paramString)
  {
    notifyType = paramString;
  }
  
  public void setPackageName(String paramString)
  {
    packageName = paramString;
  }
  
  public void setParams(Map<String, String> paramMap)
  {
    params = paramMap;
  }
  
  public void setPushType(String paramString)
  {
    pushType = paramString;
  }
  
  public void setTaskId(String paramString)
  {
    taskId = paramString;
  }
  
  public void setTitle(String paramString)
  {
    title = paramString;
  }
  
  public String toString()
  {
    return "MPushMessage{taskId='" + taskId + '\'' + ", pushType='" + pushType + '\'' + ", packageName='" + packageName + '\'' + ", title='" + title + '\'' + ", content='" + content + '\'' + ", notifyType='" + notifyType + '\'' + ", clickType='" + clickType + '\'' + ", isDiscard='" + isDiscard + '\'' + ", extra=" + extra + ", params=" + params + '}';
  }
}

/* Location:
 * Qualified Name:     com.meizu.cloud.pushsdk.notification.MPushMessage
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */