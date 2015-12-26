package cn.com.xy.sms.sdk.log;

public class TimeLog
{
  public long currentTime = 0L;
  public long startTime = 0L;
  
  public void log(String paramString1, String paramString2)
  {
    long l = System.currentTimeMillis();
    new StringBuilder("timeLog: ").append(paramString2).append(" stTime: ").append(l - startTime).append(" lastTime: ").append(l - currentTime);
    currentTime = l;
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.log.TimeLog
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */