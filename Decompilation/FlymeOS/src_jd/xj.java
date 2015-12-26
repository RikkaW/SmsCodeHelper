import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewStub;
import android.widget.ImageButton;
import android.widget.TextView;
import com.android.mms.MmsApp;
import com.android.mms.view.SwitchImageButton;
import com.android.mms.view.SwitchImageButton.a;

public class xj
  implements View.OnClickListener
{
  private TextView a;
  private SwitchImageButton b;
  private ImageButton c;
  private TextView d;
  private SwitchImageButton.a[] e;
  
  public xj(Activity paramActivity)
  {
    paramActivity = a(paramActivity, 2131886237);
    a = ((TextView)paramActivity.findViewById(2131886723));
    if (MmsApp.n)
    {
      c = ((ImageButton)paramActivity.findViewById(2131886724));
      d = ((TextView)paramActivity.findViewById(2131886725));
      return;
    }
    b = ((SwitchImageButton)paramActivity.findViewById(2131886724));
  }
  
  private View a(Activity paramActivity, int paramInt)
  {
    return ((ViewStub)paramActivity.findViewById(paramInt)).inflate();
  }
  
  public TextView a()
  {
    return a;
  }
  
  public void a(int paramInt)
  {
    if (MmsApp.n)
    {
      if (paramInt == 0)
      {
        c.setVisibility(8);
        d.setVisibility(0);
        return;
      }
      c.setVisibility(0);
      d.setVisibility(8);
      return;
    }
    b.a(paramInt);
  }
  
  public void a(int paramInt1, int paramInt2)
  {
    if (MmsApp.n)
    {
      c.setImageResource(paramInt2);
      return;
    }
    b.a(paramInt1, paramInt2);
  }
  
  public void a(boolean paramBoolean, int paramInt)
  {
    if (MmsApp.n)
    {
      d.setEnabled(paramBoolean);
      return;
    }
    b.a(paramBoolean, paramInt);
  }
  
  public void a(SwitchImageButton.a[] paramArrayOfa)
  {
    if (MmsApp.n)
    {
      e = paramArrayOfa;
      c.setOnClickListener(this);
      d.setOnClickListener(this);
      return;
    }
    b.a(new int[] { 2130837806, 2130837819 }, new boolean[] { 1, 1 });
    b.setOnClickListeners(paramArrayOfa);
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
      return;
    case 2131886724: 
      e[1].a();
      return;
    }
    e[0].a();
  }
}

/* Location:
 * Qualified Name:     xj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */