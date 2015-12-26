package com.xiaomi.mipush.sdk;

import java.util.HashMap;
import java.util.Map;

public class MiPushMessage
  implements PushMessageHandler.PushMessageInterface
{
  private static final long serialVersionUID = 1L;
  private String alias;
  private boolean arrived = false;
  private String category;
  private String content;
  private String description;
  private HashMap<String, String> extra = new HashMap();
  private boolean isNotified;
  private String messageId;
  private int messageType;
  private int notifyId;
  private int notifyType;
  private int passThrough;
  private String title;
  private String topic;
  private String userAccount;
  
  public String getAlias()
  {
    return alias;
  }
  
  public String getCategory()
  {
    return category;
  }
  
  public String getContent()
  {
    return content;
  }
  
  public Map<String, String> getExtra()
  {
    return extra;
  }
  
  public String getMessageId()
  {
    return messageId;
  }
  
  public int getPassThrough()
  {
    return passThrough;
  }
  
  public String getTopic()
  {
    return topic;
  }
  
  public boolean isArrivedMessage()
  {
    return arrived;
  }
  
  public boolean isNotified()
  {
    return isNotified;
  }
  
  public void setAlias(String paramString)
  {
    alias = paramString;
  }
  
  public void setArrivedMessage(boolean paramBoolean)
  {
    arrived = paramBoolean;
  }
  
  public void setCategory(String paramString)
  {
    category = paramString;
  }
  
  public void setContent(String paramString)
  {
    content = paramString;
  }
  
  public void setDescription(String paramString)
  {
    description = paramString;
  }
  
  public void setExtra(Map<String, String> paramMap)
  {
    extra.clear();
    if (paramMap != null) {
      extra.putAll(paramMap);
    }
  }
  
  public void setMessageId(String paramString)
  {
    messageId = paramString;
  }
  
  public void setMessageType(int paramInt)
  {
    messageType = paramInt;
  }
  
  public void setNotified(boolean paramBoolean)
  {
    isNotified = paramBoolean;
  }
  
  public void setNotifyId(int paramInt)
  {
    notifyId = paramInt;
  }
  
  public void setNotifyType(int paramInt)
  {
    notifyType = paramInt;
  }
  
  public void setPassThrough(int paramInt)
  {
    passThrough = paramInt;
  }
  
  public void setTitle(String paramString)
  {
    title = paramString;
  }
  
  public void setTopic(String paramString)
  {
    topic = paramString;
  }
  
  public void setUserAccount(String paramString)
  {
    userAccount = paramString;
  }
  
  public String toString()
  {
    return "messageId={" + messageId + "},passThrough={" + passThrough + "},alias={" + alias + "},topic={" + topic + "},userAccount={" + userAccount + "},content={" + content + "},description={" + description + "},title={" + title + "},isNotified={" + isNotified + "},notifyId={" + notifyId + "},notifyType={" + notifyType + "}, category={" + category + "}, extra={" + extra + "}";
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mipush.sdk.MiPushMessage
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */