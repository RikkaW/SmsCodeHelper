import android.view.View;
import android.view.View.OnClickListener;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

class fb
  implements View.OnClickListener
{
  fb(ex.a parama, int paramInt) {}
  
  public void onClick(View paramView)
  {
    ex.c(b.a).putValue("db_air_data_index", String.valueOf(a));
    paramView = ex.a.a(b).keySet().iterator();
    while (paramView.hasNext())
    {
      String str = (String)paramView.next();
      ex.a.a(b).put(str, Boolean.valueOf(false));
    }
    ex.a.a(b).put(String.valueOf(a), Boolean.valueOf(true));
    b.notifyDataSetChanged();
  }
}

/* Location:
 * Qualified Name:     fb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */