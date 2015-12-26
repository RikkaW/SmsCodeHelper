import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.net.NetUtil;

final class cd
  implements Runnable
{
  cd(boolean paramBoolean, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt, XyCallBack paramXyCallBack) {}
  
  public final void run()
  {
    try
    {
      if (((NetUtil.isEnhance()) && (a)) || (NetUtil.checkAccessNetWork(1))) {
        bs.a(false, b, c, d, e, String.valueOf(f), g, 0, false, false);
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     cd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */