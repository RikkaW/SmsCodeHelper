import android.view.View;
import android.view.View.OnClickListener;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;

class ez
  implements View.OnClickListener
{
  ez(ex paramex) {}
  
  public void onClick(View paramView)
  {
    try
    {
      ex.c(a).putValue("db_air_data_index", String.valueOf(ex.b(a)));
      a.dismiss();
      return;
    }
    catch (NumberFormatException paramView)
    {
      paramView.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     ez
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */