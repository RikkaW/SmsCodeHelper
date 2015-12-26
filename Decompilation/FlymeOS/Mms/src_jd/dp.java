import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.util.ParseManager;

class dp
  implements View.OnClickListener
{
  dp(do paramdo, dl paramdl) {}
  
  public void onClick(View paramView)
  {
    long l = ParseManager.setDefServiceSwitch(b.a, "0");
    LogManager.i("DefServiceSwitch", "result: " + l);
    dn.b(b.b).setVisibility(8);
    dn.c(b.b).setVisibility(8);
    a.cancel();
    b.b.dismiss();
  }
}

/* Location:
 * Qualified Name:     dp
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */