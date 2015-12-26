import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

public class dn
  extends PopupWindow
{
  private View a;
  private TextView b;
  private TextView c;
  private View d;
  private String e;
  
  public dn(Activity paramActivity, View.OnClickListener paramOnClickListener)
  {
    super(paramActivity);
    a = ((LayoutInflater)paramActivity.getSystemService("layout_inflater")).inflate(br.e.duoqu_popup_menu, null);
    c = ((TextView)a.findViewById(br.d.duoqu_menu_abourtus));
    b = ((TextView)a.findViewById(br.d.duoqu_menu_reset));
    d = a.findViewById(br.d.duoqu_menu_splitline);
    e = paramActivity.getResources().getString(br.f.duoqu_popmenu_content);
    b.setOnClickListener(new do(this, paramActivity));
    c.setOnClickListener(new dr(this, paramActivity));
    int i = paramActivity.getWindowManager().getDefaultDisplay().getWidth();
    setContentView(a);
    setWidth(i / 3);
    setHeight(-2);
    setFocusable(true);
    setAnimationStyle(16973826);
    setBackgroundDrawable(new ColorDrawable(0));
    a.setOnTouchListener(new ds(this));
  }
  
  public View a()
  {
    return d;
  }
  
  public View b()
  {
    return b;
  }
}

/* Location:
 * Qualified Name:     dn
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */