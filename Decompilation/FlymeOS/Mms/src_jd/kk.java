import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.android.mms.location.BaseGetLocationView;

class kk
  implements AdapterView.OnItemClickListener
{
  kk(kf paramkf) {}
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    paramAdapterView = (kr)a.l.getItem(paramInt);
    if (kf.b(a) != paramInt)
    {
      if ((kf.b(a) >= 0) && (kf.b(a) < a.l.getCount()) && (a.l.getItem(kf.b(a)) != null)) {
        ((kr)a.l.getItem(kf.b(a))).b(false);
      }
      paramAdapterView.b(true);
      kf.a(a, paramInt);
      a.a.a(paramAdapterView);
      a.l.notifyDataSetChanged();
    }
  }
}

/* Location:
 * Qualified Name:     kk
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */