import android.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.amap.api.services.core.SuggestionCity;
import java.util.List;

class lc
  implements AdapterView.OnItemClickListener
{
  lc(lb paramlb, List paramList) {}
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    if (lb.d(b) != null) {
      lb.d(b).dismiss();
    }
    lb.a(b, ((SuggestionCity)a.get(paramInt)).getCityName());
    b.b(11);
  }
}

/* Location:
 * Qualified Name:     lc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */