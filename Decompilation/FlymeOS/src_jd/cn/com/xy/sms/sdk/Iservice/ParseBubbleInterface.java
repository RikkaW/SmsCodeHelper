package cn.com.xy.sms.sdk.Iservice;

import java.util.Map;

public abstract interface ParseBubbleInterface
{
  public abstract String getBubbleViewVersion(Map<String, Object> paramMap);
  
  public abstract Map<String, Object> handerValueMap(Map<String, Object> paramMap);
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.Iservice.ParseBubbleInterface
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */