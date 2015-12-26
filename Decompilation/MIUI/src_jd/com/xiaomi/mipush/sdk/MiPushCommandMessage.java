package com.xiaomi.mipush.sdk;

import java.util.List;

public class MiPushCommandMessage
  implements PushMessageHandler.PushMessageInterface
{
  private static final long serialVersionUID = 1L;
  private String category;
  private String command;
  private List<String> commandArguments;
  private String reason;
  private long resultCode;
  
  public String getCategory()
  {
    return category;
  }
  
  public String getCommand()
  {
    return command;
  }
  
  public List<String> getCommandArguments()
  {
    return commandArguments;
  }
  
  public String getReason()
  {
    return reason;
  }
  
  public long getResultCode()
  {
    return resultCode;
  }
  
  public void setCategory(String paramString)
  {
    category = paramString;
  }
  
  public void setCommand(String paramString)
  {
    command = paramString;
  }
  
  public void setCommandArguments(List<String> paramList)
  {
    commandArguments = paramList;
  }
  
  public void setReason(String paramString)
  {
    reason = paramString;
  }
  
  public void setResultCode(long paramLong)
  {
    resultCode = paramLong;
  }
  
  public String toString()
  {
    return "command={" + command + "}, resultCode={" + resultCode + "}, reason={" + reason + "}, category={" + category + "}, commandArguments={" + commandArguments + "}";
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mipush.sdk.MiPushCommandMessage
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */