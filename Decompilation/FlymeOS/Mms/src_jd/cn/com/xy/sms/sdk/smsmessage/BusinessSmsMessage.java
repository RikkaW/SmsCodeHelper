package cn.com.xy.sms.sdk.smsmessage;

import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.util.JsonUtil;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class BusinessSmsMessage
  implements Serializable, Cloneable
{
  private static final long a = 1L;
  public static BusinessSmsMessage emptyObj = null;
  private HashMap<String, List<Map<String, String>>> b = null;
  public JSONObject bubbleJsonObj = null;
  private HashMap<String, JSONArray> c = null;
  public HashMap<String, Object> extendParamMap = null;
  public HashMap<String, String> imagePathMap = null;
  public boolean isBgVis;
  public boolean isPopByWeishi = false;
  public String messageBody;
  public long msgTime = 0L;
  public String originatingAddress;
  public boolean reBindData = false;
  public int simIndex = -1;
  public String simName = "";
  public long smsId = -1L;
  public String titleNo;
  public Map<String, Object> valueMap = null;
  public byte viewType = 0;
  
  public static BusinessSmsMessage createMsgObj()
  {
    try
    {
      if (emptyObj == null) {
        emptyObj = new BusinessSmsMessage();
      }
      BusinessSmsMessage localBusinessSmsMessage = (BusinessSmsMessage)emptyObj.clone();
      return localBusinessSmsMessage;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      LogManager.e("duoqu_xiaoyuan", "BusinessSmsMessage createMsgObj: " + localCloneNotSupportedException.getMessage(), localCloneNotSupportedException);
      localCloneNotSupportedException.printStackTrace();
    }
    return new BusinessSmsMessage();
  }
  
  public Object clone()
  {
    return super.clone();
  }
  
  public JSONArray getActionJsonArray()
  {
    Object localObject = getValue("ADACTION");
    if (localObject == null) {
      return null;
    }
    if ((localObject instanceof JSONArray)) {
      return (JSONArray)localObject;
    }
    localObject = JsonUtil.parseStrToJsonArray((String)localObject);
    putValue("ADACTION", localObject);
    return (JSONArray)localObject;
  }
  
  public String getCenterAddress()
  {
    return "";
  }
  
  public Object getExtendParamValue(String paramString)
  {
    if (extendParamMap != null) {
      return extendParamMap.get(paramString);
    }
    return null;
  }
  
  public String getImgNameByKey(String paramString)
  {
    if (viewType == 0)
    {
      if (imagePathMap != null) {
        return (String)imagePathMap.get(paramString);
      }
    }
    else if ((bubbleJsonObj != null) && (bubbleJsonObj.has(paramString))) {
      try
      {
        paramString = (String)bubbleJsonObj.get(paramString);
        return paramString;
      }
      catch (Throwable paramString)
      {
        paramString.printStackTrace();
      }
    }
    return null;
  }
  
  public String getMessageBody()
  {
    return messageBody;
  }
  
  public String getOriginatingAddress()
  {
    return originatingAddress;
  }
  
  public long getSmsId()
  {
    return smsId;
  }
  
  public Object getTableData(int paramInt, String paramString)
  {
    if (viewType == 0) {
      if (b != null)
      {
        paramString = (List)b.get(paramString);
        if ((paramString != null) && (paramInt >= 0)) {
          try
          {
            if (paramString.size() > paramInt)
            {
              paramString = paramString.get(paramInt);
              return paramString;
            }
          }
          catch (Throwable paramString)
          {
            paramString.printStackTrace();
          }
        }
      }
    }
    for (;;)
    {
      return null;
      if (c != null) {
        try
        {
          paramString = (JSONArray)c.get(paramString);
          if ((paramString != null) && (paramInt >= 0) && (paramString.length() > paramInt))
          {
            paramString = paramString.get(paramInt);
            return paramString;
          }
        }
        catch (Throwable paramString)
        {
          paramString.printStackTrace();
        }
      }
    }
  }
  
  public int getTableDataSize(String paramString)
  {
    int j;
    try
    {
      Object localObject2;
      Object localObject1;
      Object localObject3;
      if (viewType == 0)
      {
        if (b == null) {
          b = new HashMap();
        }
        localObject2 = (List)b.get(paramString);
        localObject1 = localObject2;
        if (localObject2 == null)
        {
          localObject3 = getValue(paramString);
          localObject1 = localObject2;
          if (localObject3 != null)
          {
            localObject1 = localObject2;
            if ((localObject3 instanceof List))
            {
              localObject1 = (List)localObject3;
              b.put(paramString, localObject1);
            }
          }
        }
        if (localObject1 != null) {
          return ((List)localObject1).size();
        }
      }
      else
      {
        if (c == null) {
          c = new HashMap();
        }
        localObject2 = (JSONArray)c.get(paramString);
        localObject1 = localObject2;
        if (localObject2 == null)
        {
          localObject3 = getValue(paramString);
          localObject1 = localObject2;
          if (localObject3 != null)
          {
            localObject1 = localObject2;
            if ((localObject3 instanceof JSONArray))
            {
              localObject1 = (JSONArray)localObject3;
              c.put(paramString, localObject1);
            }
          }
        }
        if (localObject1 != null)
        {
          int i = ((JSONArray)localObject1).length();
          j = i;
          if (i <= 5) {
            return j;
          }
          return 5;
        }
      }
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
      j = 0;
    }
    return j;
  }
  
  public String getTitleNo()
  {
    return titleNo;
  }
  
  public Object getValue(String paramString)
  {
    if (viewType == 0)
    {
      if (valueMap != null) {
        return valueMap.get(paramString);
      }
    }
    else if ((bubbleJsonObj != null) && (bubbleJsonObj.has(paramString))) {
      try
      {
        paramString = bubbleJsonObj.get(paramString);
        return paramString;
      }
      catch (Throwable paramString)
      {
        paramString.printStackTrace();
      }
    }
    return null;
  }
  
  public boolean isDataNull(String paramString)
  {
    if (viewType == 0)
    {
      if (b != null)
      {
        paramString = (List)b.get(paramString);
        if ((paramString != null) && (paramString.size() > 0)) {
          return false;
        }
      }
    }
    else if (c != null)
    {
      paramString = (JSONArray)c.get(paramString);
      if ((paramString != null) && (paramString.length() > 0)) {
        return false;
      }
    }
    return true;
  }
  
  public void putValue(String paramString, Object paramObject)
  {
    if ((paramString == null) || (paramObject == null)) {
      return;
    }
    if (viewType == 0)
    {
      if (valueMap == null) {
        valueMap = new HashMap();
      }
      valueMap.put(paramString, paramObject);
      return;
    }
    if (bubbleJsonObj == null) {
      bubbleJsonObj = new JSONObject();
    }
    try
    {
      bubbleJsonObj.put(paramString, paramObject);
      return;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public void setMessageBody(String paramString)
  {
    messageBody = paramString;
  }
  
  public void setOriginatingAddress(String paramString)
  {
    originatingAddress = paramString;
  }
  
  public void setSmsId(long paramLong)
  {
    smsId = paramLong;
  }
  
  public void setTitleNo(String paramString)
  {
    titleNo = paramString;
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */