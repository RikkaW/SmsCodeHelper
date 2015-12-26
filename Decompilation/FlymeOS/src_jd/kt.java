import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;
import java.util.List;

public class kt
  extends ArrayAdapter<kr>
{
  protected final LayoutInflater a;
  protected final int b;
  private Filter c;
  
  public kt(Context paramContext, int paramInt, List paramList)
  {
    super(paramContext, paramInt, paramList);
    b = paramInt;
    a = LayoutInflater.from(paramContext);
  }
  
  public Filter getFilter()
  {
    return c;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      paramView = a.inflate(b, null);
      paramViewGroup = new kt.a();
      a = ((TextView)paramView.findViewById(2131886484));
      b = ((TextView)paramView.findViewById(2131886485));
      paramView.setTag(paramViewGroup);
    }
    kr localkr;
    for (;;)
    {
      localkr = (kr)getItem(paramInt);
      if (localkr != null) {
        break;
      }
      return paramView;
      paramViewGroup = (kt.a)paramView.getTag();
    }
    a.setText(localkr.d());
    if ((localkr.e() >= 0.0D) && (localkr.e() < 500000.0D))
    {
      b.setText(localkr.b() + String.format("(%s)", new Object[] { kn.a(getContext(), localkr.e()) }));
      return paramView;
    }
    b.setText(localkr.b());
    return paramView;
  }
  
  static final class a
  {
    public TextView a;
    public TextView b;
  }
}

/* Location:
 * Qualified Name:     kt
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */