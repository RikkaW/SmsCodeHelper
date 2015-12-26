import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

class ev
  implements View.OnClickListener
{
  ev(es.a parama, int paramInt) {}
  
  public void onClick(View paramView)
  {
    Iterator localIterator = es.a.a(b).keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      es.a.a(b).put(str, Boolean.valueOf(false));
    }
    es.a.a(b).put(String.valueOf(a), Boolean.valueOf(((RadioButton)paramView).isChecked()));
    b.notifyDataSetChanged();
  }
}

/* Location:
 * Qualified Name:     ev
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */