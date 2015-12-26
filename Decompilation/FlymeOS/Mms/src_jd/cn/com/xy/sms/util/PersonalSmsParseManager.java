package cn.com.xy.sms.util;

import cn.com.xy.sms.sdk.dex.DexUtil;

public class PersonalSmsParseManager
{
  public static String[] parseMsgToNewContacts(String paramString1, String paramString2, String paramString3, String[] paramArrayOfString)
  {
    return DexUtil.parseMsgToNewContacts(paramString1, paramString2, paramString3, paramArrayOfString);
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.util.PersonalSmsParseManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */