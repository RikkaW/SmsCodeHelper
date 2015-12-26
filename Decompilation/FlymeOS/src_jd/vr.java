import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;
import android.widget.TextView;

public class vr
  implements View.OnClickListener
{
  private PopupWindow a;
  private View b;
  private TextView c;
  private TextView d;
  private View e;
  private vr.a f;
  
  public vr(Context paramContext, View paramView)
  {
    a = new PopupWindow(paramContext, null, 16843519, 0);
    a.setHeight(-2);
    a.setWidth(-2);
    a.setOutsideTouchable(true);
    a.setFocusable(true);
    a.setTouchable(true);
    e = paramView;
    b = LayoutInflater.from(paramContext).inflate(2130968740, null);
    c = ((TextView)b.findViewById(2131886597));
    c.setOnClickListener(this);
    d = ((TextView)b.findViewById(2131886598));
    d.setOnClickListener(this);
    a.setContentView(b);
    a.setOnDismissListener(new vs(this));
  }
  
  public void a()
  {
    int[] arrayOfInt = new int[2];
    e.getLocationOnScreen(arrayOfInt);
    a.showAtLocation(e, 0, arrayOfInt[0] + e.getWidth(), arrayOfInt[1]);
  }
  
  public void a(int paramInt1, int paramInt2)
  {
    c.setText(b.getContext().getText(paramInt1));
    d.setText(b.getContext().getText(paramInt2));
  }
  
  public void a(View paramView)
  {
    e = paramView;
  }
  
  public void a(vr.a parama)
  {
    f = parama;
  }
  
  public boolean b()
  {
    return a.isShowing();
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    }
    for (;;)
    {
      a.dismiss();
      f = null;
      return;
      if (f != null)
      {
        f.a(0);
        continue;
        if (f != null) {
          f.a(1);
        }
      }
    }
  }
  
  public static abstract interface a
  {
    public abstract void a(int paramInt);
  }
}

/* Location:
 * Qualified Name:     vr
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */