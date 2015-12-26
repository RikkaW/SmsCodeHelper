package com.ted.android.data;

import android.text.TextUtils;
import asf;
import com.ted.android.data.bubbleAction.ActionBase;
import com.ted.android.data.bubbleAction.DateReminderAction;
import com.ted.android.message.BubbleUtils;
import com.ted.android.utils.TedSDKLog;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

public class BubbleEntity
  implements Comparable<BubbleEntity>
{
  private static final String KEY_ACTIONS = "actions";
  private static final String KEY_BUTTONS = "buttons";
  private static final String KEY_CATEGORY = "category";
  private static final String KEY_ID = "id";
  private static final String KEY_INDEX = "index";
  private static final String KEY_KEYWORDS = "keywords";
  private static final String KEY_MATCHED_WORDS = "matchedWords";
  private static final String KEY_NUMBER = "number";
  private static final String KEY_PRIORITY = "priority";
  private static final String KEY_SHOW_TYPE = "showType";
  private static final String KEY_TO_CONTACT_FORCE = "toContactForce";
  public static final int TYPE_ALL = 0;
  public static final int TYPE_BUTTON = 1;
  public static final int TYPE_NONE = 3;
  public static final int TYPE_UNDERLINE = 2;
  private static boolean isLimitTime = true;
  private List<ActionBase> actions;
  private String id;
  private int index = 1;
  private String keywords;
  private String matchedWords;
  private SmsEntity parent;
  private int priority;
  private int showType = 0;
  private int toContactForce;
  
  public static BubbleEntity fromJSON(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    paramString = new JSONObject(paramString);
    BubbleEntity localBubbleEntity = new BubbleEntity();
    matchedWords = BubbleUtils.getStringWithIgnore(paramString, "matchedWords");
    index = BubbleUtils.getIntWithIgnore(paramString, "index");
    id = BubbleUtils.getStringWithIgnore(paramString, "id");
    keywords = BubbleUtils.getStringWithIgnore(paramString, "keywords");
    showType = BubbleUtils.getIntWithIgnore(paramString, "showType");
    if (showType == -1) {
      showType = 0;
    }
    actions = ActionBase.fromJSONArray(localBubbleEntity, paramString.getString("actions"));
    return localBubbleEntity;
  }
  
  public static List<BubbleEntity> fromJSONArray(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    paramString = new JSONArray(paramString);
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    for (;;)
    {
      if (i >= paramString.length()) {
        return localArrayList;
      }
      BubbleEntity localBubbleEntity = fromJSON(paramString.getString(i));
      if (localBubbleEntity != null) {
        localArrayList.add(localBubbleEntity);
      }
      i += 1;
    }
  }
  
  public static void setIsLimitTime(boolean paramBoolean)
  {
    isLimitTime = paramBoolean;
  }
  
  private JSONObject toJSON()
  {
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("id", id);
    localJSONObject.put("priority", priority);
    localJSONObject.put("index", index);
    localJSONObject.put("matchedWords", matchedWords);
    localJSONObject.put("keywords", keywords);
    localJSONObject.put("showType", showType);
    if ((actions != null) && (actions.size() > 0)) {
      localJSONObject.put("actions", ActionBase.toJSONArray(actions));
    }
    return localJSONObject;
  }
  
  public static JSONArray toJSONArray(List<BubbleEntity> paramList)
  {
    JSONArray localJSONArray = new JSONArray();
    paramList = paramList.iterator();
    for (;;)
    {
      if (!paramList.hasNext()) {
        return localJSONArray;
      }
      localJSONArray.put(((BubbleEntity)paramList.next()).toJSON());
    }
  }
  
  public void addAction(ActionBase paramActionBase)
  {
    if (actions == null) {
      actions = new ArrayList();
    }
    actions.add(paramActionBase);
  }
  
  public void addActions(List<ActionBase> paramList)
  {
    if (actions == null) {
      actions = new ArrayList();
    }
    actions.addAll(paramList);
  }
  
  public int compareTo(BubbleEntity paramBubbleEntity)
  {
    if (priority > priority) {
      return 1;
    }
    if (priority < priority) {
      return -1;
    }
    return 0;
  }
  
  public List<ActionBase> getActions()
  {
    return actions;
  }
  
  public String getId()
  {
    return id;
  }
  
  public int getIndex()
  {
    return index;
  }
  
  public String getKeywords()
  {
    return keywords;
  }
  
  public String getMatchedWords()
  {
    return matchedWords;
  }
  
  public SmsEntity getParent()
  {
    return parent;
  }
  
  public int getShowType()
  {
    if ((asf.a().b()) && (!TextUtils.equals("-4", id)) && (!TextUtils.equals("-6", id)) && (!TextUtils.equals("-7", id))) {
      return asf.a().a(id);
    }
    long l;
    if ((actions != null) && (actions.size() > 0))
    {
      Object localObject = (ActionBase)actions.get(0);
      if (((localObject instanceof DateReminderAction)) && (isLimitTime))
      {
        localObject = (DateReminderAction)localObject;
        l = startTime;
        if (!isAllDay) {
          break label205;
        }
        l = l / 86400000L * 86400000L + 172800000L - 1000L;
        localObject = new SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.CHINA).format(new Date(l));
        TedSDKLog.d("GetShowType", "Compare startTime:" + (String)localObject);
      }
    }
    label205:
    for (;;)
    {
      if (l - System.currentTimeMillis() >= 3600000L) {
        return 2;
      }
      return 3;
      return showType;
    }
  }
  
  public int getToContactForce()
  {
    return toContactForce;
  }
  
  public void setActions(List<ActionBase> paramList)
  {
    actions = paramList;
  }
  
  public void setId(String paramString)
  {
    id = paramString;
  }
  
  public void setIndex(int paramInt)
  {
    index = paramInt;
  }
  
  public void setKeywords(String paramString)
  {
    keywords = paramString;
  }
  
  public void setMatchedWords(String paramString)
  {
    matchedWords = paramString;
  }
  
  public void setParent(SmsEntity paramSmsEntity)
  {
    parent = paramSmsEntity;
  }
  
  public void setPriority(int paramInt)
  {
    priority = paramInt;
  }
  
  public void setShowType(int paramInt)
  {
    showType = paramInt;
    if (showType == -1) {
      showType = 0;
    }
  }
  
  public void setToContactForce(int paramInt)
  {
    toContactForce = paramInt;
  }
  
  public String toFormedString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i;
    if ((actions != null) && (actions.size() > 0)) {
      i = 0;
    }
    for (;;)
    {
      if (i >= actions.size()) {
        return localStringBuilder.toString();
      }
      localStringBuilder.append(matchedWords).append("\t").append(actions.get(i)).buttonText).append(" | ");
      i += 1;
    }
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Matched Words: ").append(matchedWords).append(" ShowType: ").append(getShowType()).append(" id:").append(id).append("\n");
    int i;
    if ((actions != null) && (actions.size() > 0)) {
      i = 0;
    }
    for (;;)
    {
      if (i >= actions.size()) {
        return localStringBuilder.toString();
      }
      localStringBuilder.append(" | Action (").append(i).append(")").append(": ").append(actions.get(i)).append(" ShowButton: ").append(((ActionBase)actions.get(i)).isAsButton()).append("\n");
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     com.ted.android.data.BubbleEntity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */