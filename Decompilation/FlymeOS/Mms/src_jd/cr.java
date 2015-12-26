import android.view.View;
import android.view.View.OnClickListener;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import cn.com.xy.sms.sdk.ui.popu.util.ViewUtil;
import java.util.List;

class cr
  implements View.OnClickListener
{
  cr(cp paramcp) {}
  
  public void onClick(View paramView)
  {
    int i = paramView.getId();
    paramView.setOnTouchListener(a.c);
    ViewUtil.setTextViewValue(cp.b(a), (String)cp.c(a).get(i));
    a.g.putValue("db_air_data_index", String.valueOf(i));
    cp.a(a, a.g);
  }
}

/* Location:
 * Qualified Name:     cr
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */