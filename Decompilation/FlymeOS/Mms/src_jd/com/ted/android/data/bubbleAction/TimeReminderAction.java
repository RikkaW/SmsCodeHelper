package com.ted.android.data.bubbleAction;

import android.content.Context;
import com.ted.android.data.BubbleEntity;
import com.ted.android.message.BubbleUtils;
import org.json.JSONObject;

public class TimeReminderAction
  extends CommonAction
{
  protected static final String KEY_BODY = "body";
  private static final String TAG = TimeReminderAction.class.getSimpleName();
  protected String body;
  
  public TimeReminderAction(BubbleEntity paramBubbleEntity)
  {
    super(paramBubbleEntity);
    action = 7;
    buttonText = "时间提醒";
  }
  
  public TimeReminderAction(BubbleEntity paramBubbleEntity, String paramString)
  {
    super(paramBubbleEntity, paramString);
    action = 7;
    buttonText = "时间提醒";
  }
  
  public static CommonAction fromJSON(BubbleEntity paramBubbleEntity, String paramString)
  {
    JSONObject localJSONObject = new JSONObject(paramString);
    paramBubbleEntity = new TimeReminderAction(paramBubbleEntity, paramString);
    body = localJSONObject.getString("body");
    return paramBubbleEntity;
  }
  
  public boolean doAction(Context paramContext)
  {
    BubbleUtils.remindEvent(paramContext, body, number);
    return true;
  }
  
  public boolean isAsButton()
  {
    return false;
  }
  
  public void setBody(String paramString)
  {
    body = paramString;
  }
  
  public JSONObject toJSON()
  {
    JSONObject localJSONObject = super.toJSON();
    localJSONObject.put("body", body);
    return localJSONObject;
  }
}

/* Location:
 * Qualified Name:     com.ted.android.data.bubbleAction.TimeReminderAction
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */