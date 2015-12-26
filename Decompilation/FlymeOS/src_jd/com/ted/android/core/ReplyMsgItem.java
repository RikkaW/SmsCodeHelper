package com.ted.android.core;

import android.text.TextUtils;
import asu;
import com.ted.android.data.BubbleEntity;
import com.ted.android.data.bubbleAction.ActionBase;
import com.ted.android.data.bubbleAction.QuickReplyAction;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReplyMsgItem
{
  private static final String DESCRIPTION_KEY = "description";
  public static final int EDIT_TYPE = 0;
  private static final String MESSAGE_KEY = "message";
  private static final String NUMBER_KEY = "number";
  private static final String REPLY_ICON = "http://img.teddymobile.cn/2015/03/23/ba7b8e8596c6d0dbfd91796f8dd3aa35_60X60.png";
  public static final int REPLY_TYPE = 1;
  private static final String TYPE_KEY = "type";
  public String description;
  public String message;
  public String number;
  public int type = 0;
  
  public static List<ReplyMsgItem> fromJSONArray(String paramString)
  {
    try
    {
      paramString = new JSONArray(paramString);
      ArrayList localArrayList = new ArrayList();
      int i = 0;
      for (;;)
      {
        if (i >= paramString.length()) {
          return localArrayList;
        }
        localArrayList.add(fromJson(paramString.getString(i)));
        i += 1;
      }
      return null;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static ReplyMsgItem fromJson(String paramString)
  {
    try
    {
      paramString = new JSONObject(paramString);
      ReplyMsgItem localReplyMsgItem = new ReplyMsgItem();
      description = paramString.getString("description");
      message = paramString.getString("message");
      number = paramString.getString("number");
      type = paramString.getInt("type");
      return localReplyMsgItem;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static ReplyMsgItem fromReply(asu paramasu, String paramString)
  {
    if (paramasu != null)
    {
      ReplyMsgItem localReplyMsgItem = new ReplyMsgItem();
      description = paramasu.e();
      type = 1;
      message = paramasu.a();
      if ((TextUtils.isEmpty(paramasu.f())) || (TextUtils.equals("0", paramasu.f()))) {}
      for (number = paramString;; number = paramasu.f())
      {
        if (paramasu.c()) {
          type = 0;
        }
        return localReplyMsgItem;
      }
    }
    return null;
  }
  
  public static String toJSONArrayString(List<ReplyMsgItem> paramList)
  {
    if ((paramList == null) && (paramList.size() == 0)) {
      return null;
    }
    JSONArray localJSONArray = new JSONArray();
    paramList = paramList.iterator();
    for (;;)
    {
      if (!paramList.hasNext()) {
        return localJSONArray.toString();
      }
      localJSONArray.put(((ReplyMsgItem)paramList.next()).toJSONObject());
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof ReplyMsgItem))
    {
      paramObject = (ReplyMsgItem)paramObject;
      if (TextUtils.equals(message, message)) {
        return true;
      }
    }
    return false;
  }
  
  public int hashCode()
  {
    return 17;
  }
  
  public ActionBase toAction(BubbleEntity paramBubbleEntity)
  {
    paramBubbleEntity = new QuickReplyAction(paramBubbleEntity);
    jsonString = toJSONObject().toString();
    action = 19;
    icon = "http://img.teddymobile.cn/2015/03/23/ba7b8e8596c6d0dbfd91796f8dd3aa35_60X60.png";
    buttonText = "快捷回复";
    number = number;
    paramBubbleEntity.setItem(this);
    return paramBubbleEntity;
  }
  
  public JSONObject toJSONObject()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("description", description);
      localJSONObject.put("number", number);
      localJSONObject.put("message", message);
      localJSONObject.put("type", type);
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return null;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("description=").append(description).append("  ").append("number=").append(number).append("  ").append("message=").append(message);
    return localStringBuilder.toString();
  }
}

/* Location:
 * Qualified Name:     com.ted.android.core.ReplyMsgItem
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */