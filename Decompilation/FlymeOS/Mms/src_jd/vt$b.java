import android.content.Context;
import android.content.res.Resources;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

final class vt$b
  extends BaseAdapter
{
  private final LayoutInflater b;
  private int c = 0;
  
  public vt$b(vt paramvt, Context paramContext)
  {
    b = LayoutInflater.from(paramContext);
    c = paramContext.getResources().getDimensionPixelSize(2131559031);
  }
  
  private View a(int paramInt, ViewGroup paramViewGroup)
  {
    paramViewGroup = b.inflate(2130968672, paramViewGroup, false);
    if (paramViewGroup != null)
    {
      vt.f localf = new vt.f();
      a = ((TextView)paramViewGroup.findViewById(2131886471));
      b = ((TextView)paramViewGroup.findViewById(2131886472));
      c = ((TextView)paramViewGroup.findViewById(2131886473));
      d = ((TextView)paramViewGroup.findViewById(2131886474));
      e = paramViewGroup.findViewById(2131886475);
      e.setOnClickListener(a.getResendOnclickListener());
      paramViewGroup.setTag(localf);
    }
    return paramViewGroup;
  }
  
  private CharSequence a(boolean paramBoolean, String paramString)
  {
    if (!paramBoolean) {
      return paramString;
    }
    paramString = new SpannableStringBuilder(paramString);
    paramString.setSpan(new ForegroundColorSpan(-1420734), 0, paramString.length(), 0);
    return paramString;
  }
  
  private void a(View paramView, int paramInt)
  {
    paramView = (vt.f)paramView.getTag();
    a((vt.c)vt.b(a).get(paramInt), paramView);
    e.setTag(Integer.valueOf(paramInt));
  }
  
  private void a(vt.c paramc, vt.f paramf)
  {
    if (TextUtils.isEmpty(f))
    {
      b.setText(a.replaceAll(" ", ""));
      a.setVisibility(8);
    }
    for (;;)
    {
      c.setText(c);
      d.setText(a(d, b));
      if (!d) {
        break;
      }
      e.setVisibility(0);
      wd.a(e, ga.C());
      return;
      a.setVisibility(0);
      a.setText(f);
      b.setVisibility(0);
      b.setText(a.replaceAll(" ", ""));
    }
    e.setVisibility(8);
  }
  
  public int getCount()
  {
    if (vt.b(a) == null) {
      return 0;
    }
    return vt.b(a).size();
  }
  
  public Object getItem(int paramInt)
  {
    return vt.b(a).get(paramInt);
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView = paramView;
    if (paramView == null) {
      localView = a(paramInt, paramViewGroup);
    }
    a(localView, paramInt);
    return localView;
  }
}

/* Location:
 * Qualified Name:     vt.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */