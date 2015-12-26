import android.widget.ImageView;
import android.widget.RelativeLayout;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;

public class ep
  extends eo
{
  public RelativeLayout f;
  
  public void a(int paramInt)
  {
    try
    {
      if (f != null)
      {
        f.setVisibility(paramInt);
        e.setVisibility(paramInt);
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void a(int paramInt, BusinessSmsMessage paramBusinessSmsMessage, String paramString, boolean paramBoolean)
  {
    try
    {
      super.a(paramInt, paramBusinessSmsMessage, paramString, paramBoolean);
      return;
    }
    catch (Exception paramBusinessSmsMessage)
    {
      paramBusinessSmsMessage.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     ep
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */