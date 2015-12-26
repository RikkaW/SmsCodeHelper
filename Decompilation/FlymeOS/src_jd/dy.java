import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;

public class dy
  extends BaseAdapter
{
  private Context a;
  private ArrayList<HashMap<String, Object>> b;
  private LayoutInflater c;
  
  public dy(Context paramContext, ArrayList<HashMap<String, Object>> paramArrayList)
  {
    a = paramContext;
    c = LayoutInflater.from(a);
    b = paramArrayList;
  }
  
  private String a(double paramDouble)
  {
    if (paramDouble > 1000.0D) {
      return String.format("%.1fkm", new Object[] { Double.valueOf(paramDouble / 1000.0D) }).replace(".0", "");
    }
    return String.format("%.0fm", new Object[] { Double.valueOf(paramDouble) });
  }
  
  public int getCount()
  {
    if (b == null) {
      return 0;
    }
    return b.size();
  }
  
  public Object getItem(int paramInt)
  {
    return Integer.valueOf(paramInt);
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      paramViewGroup = new dy.a();
      paramView = c.inflate(br.e.duoqu_nearby_point_list_item, null);
      a = ((TextView)paramView.findViewById(br.d.duoqu_tv_nearby_point_name));
      b = ((TextView)paramView.findViewById(br.d.duoqu_tv_nearby_point_address));
      c = ((TextView)paramView.findViewById(br.d.duoqu_tv_nearby_point_distance));
      paramView.setTag(paramViewGroup);
    }
    for (;;)
    {
      a.setText((String)((HashMap)b.get(paramInt)).get("name"));
      b.setText((String)((HashMap)b.get(paramInt)).get("address"));
      c.setText(a(Double.parseDouble(((HashMap)b.get(paramInt)).get("distance").toString())));
      d = ((Double)((HashMap)b.get(paramInt)).get("longitude")).doubleValue();
      e = ((Double)((HashMap)b.get(paramInt)).get("latitude")).doubleValue();
      return paramView;
      paramViewGroup = (dy.a)paramView.getTag();
    }
  }
  
  public final class a
  {
    public TextView a;
    public TextView b;
    public TextView c;
    public double d;
    public double e;
    
    public a() {}
  }
}

/* Location:
 * Qualified Name:     dy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */