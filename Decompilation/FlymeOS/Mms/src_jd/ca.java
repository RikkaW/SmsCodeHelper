import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;

final class ca
  extends Thread
{
  ca(String paramString1, String paramString2, String paramString3)
  {
    super(paramString1);
  }
  
  public final void run()
  {
    bx.b(a, b, false);
    bx.a(a, b, false);
    synchronized (bx.a)
    {
      SysParamEntityManager.setParam("LastPublicUpdate", String.valueOf(System.currentTimeMillis()));
      bx.b = false;
      return;
    }
  }
}

/* Location:
 * Qualified Name:     ca
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */