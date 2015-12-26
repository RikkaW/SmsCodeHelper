import android.content.ComponentName;
import android.content.Intent;
import android.os.Build.VERSION;
import android.view.View;
import android.view.View.OnClickListener;
import cn.com.xy.sms.sdk.ui.popu.web.SdkWebActivity;

public class ec
  implements View.OnClickListener
{
  public ec(SdkWebActivity paramSdkWebActivity) {}
  
  public void onClick(View paramView)
  {
    try
    {
      if (Integer.valueOf(Build.VERSION.SDK).intValue() > 10) {
        paramView = new Intent("android.settings.SETTINGS");
      }
      for (;;)
      {
        a.startActivity(paramView);
        return;
        paramView = new Intent();
        paramView.setComponent(new ComponentName("com.android.settings", "com.android.settings.WirelessSettings"));
        paramView.setAction("android.intent.action.VIEW");
      }
      return;
    }
    catch (Exception paramView)
    {
      paramView.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     ec
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */