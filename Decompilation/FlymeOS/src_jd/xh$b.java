import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

class xh$b
  extends BaseAdapter
  implements gm.b
{
  private final LayoutInflater b;
  
  public xh$b(xh paramxh, Context paramContext)
  {
    b = LayoutInflater.from(paramContext);
  }
  
  public void a(gm paramgm)
  {
    int j = 0;
    int k;
    for (int i = 0; j < xh.a(a).size(); i = k)
    {
      xh.a locala = (xh.a)xh.a(a).get(j);
      k = i;
      if (paramgm.d().equals(a))
      {
        if (!paramgm.g().equals(b))
        {
          b = paramgm.g();
          i = 1;
        }
        if (paramgm.n() != c)
        {
          c = paramgm.n();
          i = 1;
        }
        aa).get(j)).b = paramgm.g();
        aa).get(j)).c = paramgm.n();
        aa).get(j)).d = paramgm.o();
        aa).get(j)).e = paramgm.p();
        k = i;
      }
      j += 1;
    }
    if (i != 0) {
      xh.b(a).post(new xi(this));
    }
  }
  
  public void a(xh.a parama, xh.c paramc)
  {
    a.setText(b);
  }
  
  public int getCount()
  {
    return xh.a(a).size();
  }
  
  public Object getItem(int paramInt)
  {
    return xh.a(a).get(paramInt);
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    xh.c localc;
    if (paramView == null)
    {
      localc = new xh.c();
      paramView = b.inflate(2130968761, paramViewGroup, false);
      a = ((TextView)paramView.findViewById(2131886471));
      paramView.setTag(localc);
    }
    for (paramViewGroup = localc;; paramViewGroup = (xh.c)paramView.getTag())
    {
      a((xh.a)xh.a(a).get(paramInt), paramViewGroup);
      return paramView;
    }
  }
}

/* Location:
 * Qualified Name:     xh.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */