import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

class fa
  implements View.OnClickListener
{
  fa(ex.a parama, int paramInt) {}
  
  public void onClick(View paramView)
  {
    ex.c(b.a).putValue("db_air_data_index", String.valueOf(a));
    Iterator localIterator = ex.a.a(b).keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      ex.a.a(b).put(str, Boolean.valueOf(false));
    }
    ex.a.a(b).put(String.valueOf(a), Boolean.valueOf(((RadioButton)paramView).isChecked()));
    b.notifyDataSetChanged();
  }
}

/* Location:
 * Qualified Name:     fa
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */