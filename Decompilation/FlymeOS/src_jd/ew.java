import android.view.View;
import android.view.View.OnClickListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

class ew
  implements View.OnClickListener
{
  ew(es.a parama, int paramInt) {}
  
  public void onClick(View paramView)
  {
    paramView = es.a.a(b).keySet().iterator();
    while (paramView.hasNext())
    {
      String str = (String)paramView.next();
      es.a.a(b).put(str, Boolean.valueOf(false));
    }
    es.a.a(b).put(String.valueOf(a), Boolean.valueOf(true));
    b.notifyDataSetChanged();
  }
}

/* Location:
 * Qualified Name:     ew
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */