import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class ur
  extends ArrayAdapter<ur.a>
{
  protected LayoutInflater a;
  private ur.b b;
  
  public ur(Context paramContext, List<ur.a> paramList)
  {
    super(paramContext, 2130968673, paramList);
    a = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      paramView = a.inflate(2130968673, paramViewGroup, false);
      b = new ur.b(paramView);
      paramView.setTag(b);
    }
    for (;;)
    {
      b.a().setText(((ur.a)getItem(paramInt)).a());
      b.b().setImageResource(((ur.a)getItem(paramInt)).b());
      return paramView;
      b = ((ur.b)paramView.getTag());
    }
  }
  
  public static class a
  {
    private final String a;
    private final int b;
    
    public a(String paramString, int paramInt)
    {
      b = paramInt;
      a = paramString;
    }
    
    public String a()
    {
      return a;
    }
    
    public int b()
    {
      return b;
    }
  }
  
  static class b
  {
    private View a;
    private TextView b;
    private ImageView c;
    
    public b(View paramView)
    {
      a = paramView;
    }
    
    public TextView a()
    {
      if (b == null) {
        b = ((TextView)a.findViewById(2131886476));
      }
      return b;
    }
    
    public ImageView b()
    {
      if (c == null) {
        c = ((ImageView)a.findViewById(2131886172));
      }
      return c;
    }
  }
}

/* Location:
 * Qualified Name:     ur
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */