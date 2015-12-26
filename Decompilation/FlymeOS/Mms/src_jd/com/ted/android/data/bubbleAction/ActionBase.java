package com.ted.android.data.bubbleAction;

import android.content.Context;
import android.text.TextUtils;
import android.view.View.OnClickListener;
import arz;
import com.ted.android.data.BubbleEntity;
import com.ted.android.utils.TedSDKLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class ActionBase
  implements Comparable<ActionBase>
{
  public static final String FIELD_ACTION_TYPE = "action";
  public static final String FIELD_ADDRESS = "addr";
  public static final String FIELD_APP_NAME = "appName";
  public static final String FIELD_BUTTON_TEXT = "buttonText";
  public static final String FIELD_CLIP = "clip";
  public static final String FIELD_ICON_URL = "icon";
  public static final String FIELD_IS_SHOW_BUTTON = "isShowButton";
  public static final String FIELD_NUMBER = "number";
  public static final String FIELD_PACKAGE_NAME = "packageName";
  protected static final String FIELD_PRIORITY = "priority";
  public static final String FIELD_SHOW_TYPE = "showType";
  public static final String FIELD_TOAST = "toast";
  public static final String FIELD_URL = "url";
  private static final String TAG = ActionBase.class.getSimpleName();
  public int action;
  public String address;
  public String appName;
  protected String body;
  public String buttonText;
  public String clip;
  public String icon;
  public boolean isShowButton = true;
  public String jsonString;
  public String number;
  public Map<String, String> originValues = new HashMap();
  public String packageName;
  protected BubbleEntity parent;
  public int priority;
  public int showType = 0;
  public String toast;
  public String url;
  
  public ActionBase(BubbleEntity paramBubbleEntity)
  {
    parent = paramBubbleEntity;
  }
  
  public ActionBase(BubbleEntity paramBubbleEntity, String paramString)
  {
    parent = paramBubbleEntity;
  }
  
  public static List<ActionBase> fromJSONArray(BubbleEntity paramBubbleEntity, String paramString)
  {
    TedSDKLog.d(TAG, paramString);
    try
    {
      JSONArray localJSONArray = new JSONArray(paramString);
      ArrayList localArrayList = new ArrayList();
      int i = 0;
      if (i >= localJSONArray.length()) {
        return localArrayList;
      }
      String str = localJSONArray.getString(i);
      CommonAction localCommonAction = new CommonAction(paramBubbleEntity, str);
      if (action == 19) {
        paramString = QuickReplyAction.fromJSON(paramBubbleEntity, str);
      }
      for (;;)
      {
        localArrayList.add(paramString);
        i += 1;
        break;
        if (action == 7)
        {
          paramString = TimeReminderAction.fromJSON(paramBubbleEntity, str);
        }
        else if (action == 21)
        {
          paramString = DateReminderAction.fromJSON(paramBubbleEntity, str);
        }
        else if (action == 22)
        {
          paramString = PhoneNumberAction.fromJSON(paramBubbleEntity, str);
        }
        else if (action == 12)
        {
          paramString = CarrierAction.fromJSON(paramBubbleEntity, str);
        }
        else
        {
          paramString = localCommonAction;
          if (action == 16) {
            paramString = VerificationCodeAction.fromJSON(paramBubbleEntity, str);
          }
        }
      }
      return null;
    }
    catch (JSONException paramBubbleEntity)
    {
      paramBubbleEntity.printStackTrace();
    }
  }
  
  private String getJsonString()
  {
    return jsonString;
  }
  
  private Map<String, String> getOriginValues()
  {
    return originValues;
  }
  
  private String getValue(String paramString)
  {
    return (String)originValues.get(paramString);
  }
  
  public static JSONArray toJSONArray(List<ActionBase> paramList)
  {
    if ((paramList != null) && (paramList.size() > 0))
    {
      JSONArray localJSONArray = new JSONArray();
      paramList = paramList.iterator();
      for (;;)
      {
        if (!paramList.hasNext()) {
          return localJSONArray;
        }
        localJSONArray.put(((ActionBase)paramList.next()).toJSON());
      }
    }
    return null;
  }
  
  public int compareTo(ActionBase paramActionBase)
  {
    if (priority > priority) {
      return 1;
    }
    if (priority < priority) {
      return -1;
    }
    return 0;
  }
  
  public abstract boolean doAction(Context paramContext);
  
  public View.OnClickListener getDefaultOnClickListener(Context paramContext)
  {
    return new arz(this, paramContext);
  }
  
  public int getShowType()
  {
    return showType;
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
  
  protected void putIntoOriginValues(String paramString1, String paramString2)
  {
    if (!TextUtils.isEmpty(paramString1)) {
      originValues.put(paramString1, paramString2);
    }
  }
  
  protected void setPairs(Map<String, String> paramMap)
  {
    if (paramMap != null) {
      originValues = paramMap;
    }
  }
  
  public void setParent(BubbleEntity paramBubbleEntity)
  {
    parent = paramBubbleEntity;
  }
  
  public abstract JSONObject toJSON();
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("buttonText: ").append(buttonText).append("  ").append(" Action:").append(action);
    return localStringBuilder.toString();
  }
}

/* Location:
 * Qualified Name:     com.ted.android.data.bubbleAction.ActionBase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */