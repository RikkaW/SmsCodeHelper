package com.ted.android.data.bubbleAction;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.ted.android.data.BubbleEntity;
import com.ted.android.message.BubbleUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONObject;

public class DateReminderAction
  extends TimeReminderAction
{
  private static final String KEY_ALL_DAY = "all_day";
  private static final String KEY_END_TIME = "end_time_key";
  private static final String KEY_TIME = "time_key";
  private static final long ONE_DAY = 86400000L;
  private static SimpleDateFormat STANDARD_DATE_FORMAT = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
  public long endTime = -1L;
  public boolean isAllDay = true;
  public long startTime;
  
  public DateReminderAction(BubbleEntity paramBubbleEntity)
  {
    super(paramBubbleEntity);
    isShowButton = false;
    showType = 2;
    action = 21;
  }
  
  public DateReminderAction(BubbleEntity paramBubbleEntity, String paramString)
  {
    super(paramBubbleEntity, paramString);
    isShowButton = false;
    showType = 2;
    action = 21;
  }
  
  public static CommonAction fromJSON(BubbleEntity paramBubbleEntity, String paramString)
  {
    JSONObject localJSONObject = new JSONObject(paramString);
    paramBubbleEntity = new DateReminderAction(paramBubbleEntity, paramString);
    body = localJSONObject.getString("body");
    startTime = localJSONObject.getLong("time_key");
    isAllDay = localJSONObject.getBoolean("all_day");
    if (localJSONObject.has("end_time_key")) {
      endTime = localJSONObject.getLong("end_time_key");
    }
    return paramBubbleEntity;
  }
  
  public boolean doAction(Context paramContext)
  {
    String str1 = "短信提醒";
    String str2 = BubbleUtils.getCalenderEventUrl();
    Object localObject = str1;
    if (!TextUtils.isEmpty(number))
    {
      localObject = str1;
      if (!TextUtils.isDigitsOnly(number)) {
        localObject = number + "提醒";
      }
    }
    try
    {
      if (endTime < startTime) {
        endTime = startTime;
      }
      localObject = new Intent("android.intent.action.INSERT").setData(Uri.parse(str2)).putExtra("beginTime", startTime).putExtra("endTime", endTime).putExtra("title", (String)localObject).putExtra("description", body).putExtra("hasAlarm", true).putExtra("allDay", isAllDay);
      if (!isAllDay) {
        ((Intent)localObject).putExtra("minutes", "60");
      }
      ((Intent)localObject).addFlags(268435456);
      paramContext.startActivity((Intent)localObject);
      return true;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return true;
  }
  
  public boolean isAsButton()
  {
    return false;
  }
  
  public JSONObject toJSON()
  {
    JSONObject localJSONObject = super.toJSON();
    localJSONObject.put("body", body);
    localJSONObject.put("time_key", startTime);
    localJSONObject.put("all_day", isAllDay);
    localJSONObject.put("end_time_key", endTime);
    return localJSONObject;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Date localDate = new Date(startTime);
    localStringBuilder.append("buttonText: ").append(buttonText).append("  ").append(" Action:").append(action).append(" 提醒日期：").append(STANDARD_DATE_FORMAT.format(localDate));
    return localStringBuilder.toString();
  }
}

/* Location:
 * Qualified Name:     com.ted.android.data.bubbleAction.DateReminderAction
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */