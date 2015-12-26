package cn.com.xy.sms.sdk.Iservice;

import android.content.Context;
import java.util.Map;

public abstract interface OnlineParseInterface
{
  public abstract int getActionCode(String paramString);
  
  public abstract String getCorp(String paramString);
  
  public abstract String getReqVersion(String paramString);
  
  public abstract String getSceneVersion(String paramString);
  
  public abstract int getSmsTypeByMap(Map<String, Object> paramMap, int paramInt);
  
  public abstract boolean isAppChannel(String paramString);
  
  public abstract boolean isEnterpriseSms(Context paramContext, String paramString1, String paramString2, Map<String, String> paramMap);
  
  public abstract int isServiceChoose(String paramString1, String paramString2);
  
  public abstract Map<String, Object> parseMessage(String paramString1, String paramString2, Map<String, String> paramMap);
  
  public abstract String[] parseMsgToNewContacts(String paramString1, String paramString2, String paramString3, String[] paramArrayOfString);
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.Iservice.OnlineParseInterface
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */