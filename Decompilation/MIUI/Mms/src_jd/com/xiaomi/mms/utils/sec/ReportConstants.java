package com.xiaomi.mms.utils.sec;

public class ReportConstants
{
  public static class ReturnCode
  {
    public static String NOTSUPPORT;
    public static String OK = "0";
    public static String OPERATE_FAIL;
    public static String SERVICE_NOTAVAILABLE = "1";
    public static String SESSION_TIMEOUT = "5";
    public static String TOKEN_ILLEGAL;
    
    static
    {
      NOTSUPPORT = "2";
      OPERATE_FAIL = "3";
      TOKEN_ILLEGAL = "4";
    }
  }
  
  public static class ReturnRSA
  {
    public static String JSON_KEY_PUBLIC_KEY = "key";
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.utils.sec.ReportConstants
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */