package com.ted.android.smscard;

import android.text.TextUtils;
import aqo;
import asj;
import com.ted.android.data.BubbleEntity;
import com.ted.android.data.SmsEntity;
import com.ted.android.data.bubbleAction.ActionBase;
import com.ted.android.data.bubbleAction.DateReminderAction;
import com.ted.android.message.BubbleUtils;
import com.ted.android.utils.TedSDKLog;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CardBase
  implements Serializable
{
  public static final int DEFAULT_HIGHLIGHT = -16711936;
  private static final String KEY_ACTIONS = "actions";
  private static final String KEY_DATA = "data";
  private static final String KEY_SUBTITLE = "subtitle";
  private static final String KEY_TITLE = "title";
  private static final String KEY_TYPE = "type";
  private static final long MONTH = 2592000000L;
  private static SimpleDateFormat STANDARD_DATE_FORMAT = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
  private static final String TAG = CardBase.class.getSimpleName();
  private static final long serialVersionUID = 10L;
  private List<ActionBase> actions;
  public Map<String, String> data;
  public int mCardType;
  public String mIconUri;
  public String mMessage;
  public Map<String, String> mSubTitle;
  public Map<String, String> mTitle;
  private int matchedId = -1;
  public ArrayList<ActionMenu> menus;
  private SmsEntity parent;
  
  private long calculateRealTimeStamp(long paramLong)
  {
    if (parent != null) {}
    for (long l1 = parent.getDate();; l1 = System.currentTimeMillis())
    {
      long l2 = paramLong;
      if (l1 > paramLong)
      {
        long l3 = getDayInNextYear(paramLong);
        l2 = paramLong;
        if (l3 - l1 < 5184000000L) {
          l2 = l3;
        }
      }
      return l2;
      TedSDKLog.e(TAG, "CardBase's parent is null");
    }
  }
  
  private Map<String, String> convertCnKeyToEnKey(Map<String, String> paramMap)
  {
    if ((paramMap != null) && (paramMap.size() > 0))
    {
      HashMap localHashMap = new HashMap();
      paramMap = paramMap.entrySet().iterator();
      for (;;)
      {
        if (!paramMap.hasNext()) {
          return localHashMap;
        }
        Map.Entry localEntry = (Map.Entry)paramMap.next();
        localHashMap.put(asj.a((String)localEntry.getKey()), (String)localEntry.getValue());
      }
    }
    return null;
  }
  
  private static Map<String, String> convertJsonToMap(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null) {
      return null;
    }
    HashMap localHashMap = new HashMap();
    Iterator localIterator = paramJSONObject.keys();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return localHashMap;
      }
      String str = (String)localIterator.next();
      localHashMap.put(str, paramJSONObject.getString(str));
    }
  }
  
  private JSONObject convertMapToJsonObject(Map<String, String> paramMap)
  {
    if ((paramMap != null) && (paramMap.size() > 0))
    {
      JSONObject localJSONObject = new JSONObject();
      paramMap = paramMap.entrySet().iterator();
      for (;;)
      {
        if (!paramMap.hasNext()) {
          return localJSONObject;
        }
        Map.Entry localEntry = (Map.Entry)paramMap.next();
        localJSONObject.put((String)localEntry.getKey(), localEntry.getValue());
      }
    }
    return null;
  }
  
  public static CardBase fromJSON(String paramString)
  {
    if (!TextUtils.isEmpty(paramString)) {
      try
      {
        paramString = new JSONObject(paramString);
        CardBase localCardBase = new CardBase();
        Object localObject = paramString.getJSONObject("title");
        JSONObject localJSONObject1 = paramString.getJSONObject("subtitle");
        JSONObject localJSONObject2 = paramString.getJSONObject("data");
        localCardBase.setTitle(convertJsonToMap((JSONObject)localObject));
        localCardBase.setSubTitle(convertJsonToMap(localJSONObject1));
        localCardBase.addData(convertJsonToMap(localJSONObject2));
        localObject = BubbleUtils.getStringWithIgnore(paramString, "actions");
        if (!TextUtils.isEmpty((CharSequence)localObject)) {
          localCardBase.setActions(ActionBase.fromJSONArray(null, (String)localObject));
        }
        mCardType = paramString.getInt("type");
        return localCardBase;
      }
      catch (JSONException paramString)
      {
        paramString.printStackTrace();
      }
    }
    return null;
  }
  
  private long getDayInNextYear(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(new Date(paramLong));
    localCalendar.add(1, 1);
    return localCalendar.getTimeInMillis();
  }
  
  private String getFormattedSubtitle(Map<String, String> paramMap)
  {
    if ((paramMap != null) && (paramMap.size() > 0))
    {
      paramMap = paramMap.entrySet().iterator();
      if ((paramMap != null) && (paramMap.hasNext()))
      {
        paramMap = (Map.Entry)paramMap.next();
        return (String)paramMap.getKey() + " " + (String)paramMap.getValue();
      }
    }
    return "";
  }
  
  private String getFormattedTitle(Map<String, String> paramMap)
  {
    if ((paramMap != null) && (paramMap.size() > 0))
    {
      paramMap = paramMap.entrySet().iterator();
      if ((paramMap != null) && (paramMap.hasNext()))
      {
        paramMap = (Map.Entry)paramMap.next();
        return (String)paramMap.getValue() + (String)paramMap.getKey();
      }
    }
    return "";
  }
  
  private long getOriginTimeStamp(String paramString)
  {
    paramString = aqo.a().a(paramString, null);
    if ((paramString != null) && (paramString.size() > 0))
    {
      paramString = ((BubbleEntity)paramString.get(0)).getActions();
      if ((paramString != null) && (paramString.size() > 0))
      {
        paramString = (ActionBase)paramString.get(0);
        if ((paramString instanceof DateReminderAction)) {
          return startTime;
        }
      }
    }
    return -1L;
  }
  
  private String getTimeString()
  {
    Object localObject2 = getTimeStringFromMap(mTitle);
    Object localObject1 = localObject2;
    if (TextUtils.isEmpty((CharSequence)localObject2)) {
      localObject1 = getTimeStringFromMap(mSubTitle);
    }
    localObject2 = localObject1;
    if (TextUtils.isEmpty((CharSequence)localObject1)) {
      localObject2 = getTimeStringFromMap(data);
    }
    return (String)localObject2;
  }
  
  private String getTimeStringFromMap(Map<String, String> paramMap)
  {
    if (paramMap != null) {
      paramMap = paramMap.entrySet().iterator();
    }
    Map.Entry localEntry;
    do
    {
      if (!paramMap.hasNext()) {
        return null;
      }
      localEntry = (Map.Entry)paramMap.next();
    } while ((!"出发时间".equals(localEntry.getKey())) && (!"起飞时间".equals(localEntry.getKey())));
    return (String)localEntry.getValue();
  }
  
  public void addData(String paramString1, String paramString2)
  {
    if (data == null) {
      data = new LinkedHashMap();
    }
    data.put(paramString1, paramString2);
  }
  
  public void addData(Map<String, String> paramMap)
  {
    if (data == null) {
      data = new LinkedHashMap();
    }
    data.putAll(paramMap);
  }
  
  public void addMenu(int paramInt, ActionMenu paramActionMenu)
  {
    if (menus == null) {
      menus = new ArrayList();
    }
    Iterator localIterator = menus.iterator();
    if (!localIterator.hasNext()) {}
    for (;;)
    {
      menus.add(paramInt, paramActionMenu);
      return;
      ActionMenu localActionMenu = (ActionMenu)localIterator.next();
      if (!localActionMenu.getText().equalsIgnoreCase(paramActionMenu.getText())) {
        break;
      }
      menus.remove(localActionMenu);
    }
  }
  
  public void addMenu(ActionMenu paramActionMenu)
  {
    if (menus == null) {
      menus = new ArrayList();
    }
    menus.add(paramActionMenu);
  }
  
  public String find(String paramString)
  {
    return (String)data.get(paramString);
  }
  
  public List<ActionBase> getActions()
  {
    return actions;
  }
  
  public int getCardType()
  {
    return mCardType;
  }
  
  public Map<String, String> getData()
  {
    return data;
  }
  
  public Map<String, String> getDataWithEnglishKey()
  {
    return convertCnKeyToEnKey(data);
  }
  
  public String getIconUri()
  {
    return mIconUri;
  }
  
  public int getMatchedId()
  {
    return matchedId;
  }
  
  public List<ActionMenu> getMenuList()
  {
    return menus;
  }
  
  public String getMessage()
  {
    return mMessage;
  }
  
  public Map<String, String> getSubTitle()
  {
    return mSubTitle;
  }
  
  public Map<String, String> getSubTitleWithEnglishKey()
  {
    return convertCnKeyToEnKey(mSubTitle);
  }
  
  public long getTimeStamp()
  {
    long l2 = -1L;
    String str = getTimeString();
    long l1 = l2;
    if (!TextUtils.isEmpty(str))
    {
      long l3 = getOriginTimeStamp(str);
      l1 = l2;
      if (l3 != -1L) {
        l1 = calculateRealTimeStamp(l3);
      }
    }
    return l1;
  }
  
  public Map<String, String> getTitle()
  {
    return mTitle;
  }
  
  public Map<String, String> getTitleWithEnglishKey()
  {
    return convertCnKeyToEnKey(mTitle);
  }
  
  public void setActions(List<ActionBase> paramList)
  {
    actions = paramList;
  }
  
  public void setMatchedId(int paramInt)
  {
    matchedId = paramInt;
  }
  
  public void setMessage(String paramString)
  {
    mMessage = paramString;
  }
  
  public void setParent(SmsEntity paramSmsEntity)
  {
    parent = paramSmsEntity;
  }
  
  public void setSubTitle(Map<String, String> paramMap)
  {
    mSubTitle = paramMap;
  }
  
  public void setTitle(Map<String, String> paramMap)
  {
    mTitle = paramMap;
  }
  
  public String toExcelFormatString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getFormattedTitle(getTitle())).append("\t").append(getFormattedSubtitle(getSubTitle())).append("\t");
    Iterator localIterator;
    if ((data != null) && (data.size() > 0))
    {
      localIterator = data.entrySet().iterator();
      if (localIterator.hasNext()) {}
    }
    for (;;)
    {
      localStringBuilder.append("\t").append(matchedId);
      return localStringBuilder.toString();
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localStringBuilder.append((String)localEntry.getKey()).append(":").append((String)localEntry.getValue());
      localStringBuilder.append("<ted>");
      break;
      localStringBuilder.append("null");
    }
  }
  
  public String toFormedString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("title:").append(getFormattedTitle(getTitle())).append("\t");
    localStringBuilder.append("subtitle:").append(getFormattedSubtitle(getSubTitle())).append("\t");
    Iterator localIterator;
    if ((data != null) && (data.size() > 0))
    {
      localIterator = data.entrySet().iterator();
      if (localIterator.hasNext()) {}
    }
    for (;;)
    {
      return localStringBuilder.toString();
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localStringBuilder.append((String)localEntry.getKey()).append(":").append((String)localEntry.getValue());
      localStringBuilder.append("\t");
      break;
      localStringBuilder.append("null");
    }
  }
  
  public String toJSON()
  {
    Object localObject = new JSONObject();
    try
    {
      ((JSONObject)localObject).put("type", mCardType);
      ((JSONObject)localObject).put("title", convertMapToJsonObject(mTitle));
      ((JSONObject)localObject).put("subtitle", convertMapToJsonObject(mSubTitle));
      ((JSONObject)localObject).put("data", convertMapToJsonObject(data));
      if ((actions != null) && (actions.size() > 0)) {
        ((JSONObject)localObject).put("actions", ActionBase.toJSONArray(actions).toString());
      }
      localObject = ((JSONObject)localObject).toString();
      return (String)localObject;
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
    localStringBuilder.append("Cardbase, Type: ").append(mCardType).append(" Matched ID: ").append(matchedId).append(" TimeStamp: ");
    long l = getTimeStamp();
    Iterator localIterator;
    if (l != -1L)
    {
      localStringBuilder.append(STANDARD_DATE_FORMAT.format(new Date(l)));
      localStringBuilder.append("\n");
      if ((data != null) && (data.size() > 0)) {
        localIterator = data.entrySet().iterator();
      }
    }
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        localStringBuilder.append("\n");
        return localStringBuilder.toString();
        localStringBuilder.append("null");
        break;
      }
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localStringBuilder.append("  |  ").append((String)localEntry.getKey()).append(":").append((String)localEntry.getValue());
    }
  }
}

/* Location:
 * Qualified Name:     com.ted.android.smscard.CardBase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */