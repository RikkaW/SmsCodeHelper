package cn.com.xy.sms.sdk.Iservice;

import java.util.Map;
import org.json.JSONObject;

public abstract interface ParseWatchInterface
{
  public abstract JSONObject handerValueMap(Map<String, Object> paramMap);
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.Iservice.ParseWatchInterface
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */