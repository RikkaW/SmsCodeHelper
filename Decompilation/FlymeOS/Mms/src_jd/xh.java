import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class xh
  implements AdapterView.OnItemClickListener
{
  private Context a;
  private Handler b = new Handler();
  private xh.b c;
  private List<xh.a> d;
  private AlertDialog e;
  private ListView f;
  
  public xh(Context paramContext)
  {
    a = paramContext;
    d = new ArrayList();
    c = new xh.b(a);
    paramContext = new AlertDialog.Builder(a, 2131624313);
    paramContext.setTitle(2131493720);
    paramContext.setAdapter(c, null);
    e = paramContext.create();
    f = e.getListView();
    f.setOnItemClickListener(this);
    f.setChoiceMode(1);
  }
  
  private void a(xh.a parama)
  {
    Context localContext = a;
    String str = a;
    if (c > 0L) {}
    for (boolean bool = true;; bool = false)
    {
      wd.a(localContext, str, bool, c, false, e);
      return;
    }
  }
  
  private void b(gq paramgq)
  {
    c();
    if (paramgq.size() == 0) {
      return;
    }
    gm.a(c);
    int i = 0;
    label21:
    xh.a locala;
    gm localgm;
    if (i < paramgq.size())
    {
      locala = new xh.a();
      localgm = gm.a(((gm)paramgq.get(i)).d(), false);
      a = localgm.d();
      if (!TextUtils.isEmpty(a)) {
        break label131;
      }
    }
    label131:
    for (b = a.getString(2131493617);; b = localgm.g())
    {
      c = localgm.n();
      d = localgm.o();
      e = localgm.p();
      d.add(locala);
      i += 1;
      break label21;
      break;
    }
  }
  
  private final void c()
  {
    d.clear();
  }
  
  public void a()
  {
    gm.b(c);
    e.dismiss();
  }
  
  public void a(gq paramgq)
  {
    e.show();
    if (paramgq != null) {
      b(paramgq);
    }
    c.notifyDataSetChanged();
  }
  
  public boolean b()
  {
    return (e != null) && (e.isShowing());
  }
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    a();
    a((xh.a)d.get(paramInt));
  }
  
  static final class a
  {
    public String a;
    public String b;
    public long c;
    public String d;
    public boolean e;
  }
  
  class b
    extends BaseAdapter
    implements gm.b
  {
    private final LayoutInflater b;
    
    public b(Context paramContext)
    {
      b = LayoutInflater.from(paramContext);
    }
    
    public void a(gm paramgm)
    {
      int j = 0;
      int k;
      for (int i = 0; j < xh.a(xh.this).size(); i = k)
      {
        xh.a locala = (xh.a)xh.a(xh.this).get(j);
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
          agetb = paramgm.g();
          agetc = paramgm.n();
          agetd = paramgm.o();
          agete = paramgm.p();
          k = i;
        }
        j += 1;
      }
      if (i != 0) {
        xh.b(xh.this).post(new xi(this));
      }
    }
    
    public void a(xh.a parama, xh.c paramc)
    {
      a.setText(b);
    }
    
    public int getCount()
    {
      return xh.a(xh.this).size();
    }
    
    public Object getItem(int paramInt)
    {
      return xh.a(xh.this).get(paramInt);
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
        a((xh.a)xh.a(xh.this).get(paramInt), paramViewGroup);
        return paramView;
      }
    }
  }
  
  static final class c
  {
    public TextView a;
  }
}

/* Location:
 * Qualified Name:     xh
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */