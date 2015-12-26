import android.content.Context;
import android.content.res.Resources;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import com.android.mms.changeslot.ChangeSlotItem.a;
import com.android.mms.changeslot.ChangeSlotPopup;

public class gk
  implements View.OnClickListener
{
  private int a;
  private ImageButton b;
  private PopupWindow c;
  private ChangeSlotPopup d;
  private Context e;
  private boolean f;
  private boolean g = false;
  private int h = 0;
  private int i;
  private int j;
  private gk.c k;
  private gk.b l;
  private gk.a m;
  private boolean n = false;
  
  private void b(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      b.setVisibility(8);
      c(false);
      return;
    }
    if (b())
    {
      b.setVisibility(0);
      c(true);
      return;
    }
    b.setVisibility(8);
    c(false);
  }
  
  private gk.b c()
  {
    if (l == null) {
      l = new gk.b(null);
    }
    return l;
  }
  
  private void c(boolean paramBoolean)
  {
    gk.a locala;
    if (m != null)
    {
      locala = m;
      if (!paramBoolean) {
        break label26;
      }
    }
    label26:
    for (int i1 = 0;; i1 = 1)
    {
      locala.a(i1);
      return;
    }
  }
  
  public void a()
  {
    if ((c != null) && (c.isShowing())) {
      c.dismiss();
    }
  }
  
  public void a(int paramInt)
  {
    int i1 = 2130838665;
    a = paramInt;
    switch (paramInt)
    {
    default: 
      paramInt = 2130838672;
    }
    for (;;)
    {
      b.setImageResource(paramInt);
      return;
      paramInt = i1;
      if (!zv.k())
      {
        paramInt = 2130838653;
        continue;
        paramInt = i1;
        if (!zv.k())
        {
          paramInt = 2130838654;
          continue;
          paramInt = 2130838655;
        }
      }
    }
  }
  
  public void a(ImageButton paramImageButton, Context paramContext, boolean paramBoolean)
  {
    e = paramContext;
    b = paramImageButton;
    b.setOnClickListener(this);
    b(paramBoolean);
    i = e.getResources().getDimensionPixelSize(2131558656);
    j = ((WindowManager)e.getSystemService("window")).getDefaultDisplay().getWidth();
  }
  
  public void a(gk.a parama)
  {
    m = parama;
  }
  
  public void a(gk.c paramc)
  {
    k = paramc;
  }
  
  public void a(boolean paramBoolean)
  {
    n = paramBoolean;
  }
  
  public void a(boolean paramBoolean, int paramInt)
  {
    int i1 = a;
    if (zv.k())
    {
      ImageButton localImageButton = b;
      if (paramBoolean)
      {
        i1 = 0;
        localImageButton.setVisibility(i1);
        b.setImageResource(2130838655);
        c(paramBoolean);
      }
    }
    for (;;)
    {
      f = paramBoolean;
      a = paramInt;
      return;
      i1 = 8;
      break;
      if (zv.j()) {
        if (paramInt == -10) {
          b.setImageResource(2130838655);
        } else if (i1 == -10) {
          b.setImageResource(2130838672);
        }
      }
    }
  }
  
  public void b(int paramInt)
  {
    if (zv.l())
    {
      b.setVisibility(8);
      c(false);
    }
    for (;;)
    {
      a = paramInt;
      return;
      if (zv.j())
      {
        b.setVisibility(0);
        a(paramInt);
        c(true);
      }
      else if (f)
      {
        b.setVisibility(0);
        b.setImageResource(2130838655);
        c(true);
      }
      else
      {
        b.setVisibility(8);
        c(false);
      }
    }
  }
  
  public boolean b()
  {
    return ((zv.k()) && (f)) || ((zv.j()) && (!n));
  }
  
  public void onClick(View paramView)
  {
    if ((c != null) && (c.isShowing())) {
      return;
    }
    b.setSelected(true);
    if (c == null)
    {
      c = new PopupWindow(e);
      c.setHeight(-2);
      c.setWidth(-2);
      c.setBackgroundDrawable(e.getResources().getDrawable(2130837566));
      c.setOutsideTouchable(true);
      c.setFocusable(true);
      c.setTouchable(true);
      c.setInputMethodMode(2);
      c.setOnDismissListener(new gl(this));
    }
    d = ((ChangeSlotPopup)LayoutInflater.from(e).inflate(2130968609, null));
    d.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
    if (zv.k()) {
      if (f)
      {
        paramView = d;
        if ((a != 0) && (a != 1)) {
          break label319;
        }
        bool = true;
        paramView.a(bool, -11, 0, c());
        g = true;
        paramView = d;
        if (a != -10) {
          break label324;
        }
        bool = true;
        label238:
        paramView.a(bool, -10, 1, c());
        h = 2;
      }
    }
    label319:
    label324:
    label349:
    label375:
    do
    {
      do
      {
        c.setContentView(d);
        paramView = new int[2];
        b.getLocationOnScreen(paramView);
        c.showAtLocation(b, 0, j - i - h * ChangeSlotPopup.a, paramView[1] - ChangeSlotPopup.b - 74);
        return;
        bool = false;
        break;
        bool = false;
        break label238;
      } while (!zv.j());
      paramView = d;
      if (a != 0) {
        break label439;
      }
      bool = true;
      paramView.a(bool, 0, 0, c());
      paramView = d;
      if (a != 1) {
        break label444;
      }
      bool = true;
      paramView.a(bool, 1, 1, c());
      h = 2;
    } while (!f);
    g = true;
    paramView = d;
    if (a == -10) {}
    for (boolean bool = true;; bool = false)
    {
      paramView.a(bool, -10, 2, c());
      h = 3;
      break;
      label439:
      bool = false;
      break label349;
      label444:
      bool = false;
      break label375;
    }
  }
  
  public static abstract interface a
  {
    public abstract void a(int paramInt);
  }
  
  class b
    implements ChangeSlotItem.a
  {
    private b() {}
    
    public void a(int paramInt)
    {
      gk.b(gk.this).a(paramInt);
      gk.c(gk.this).dismiss();
      gk.a(gk.this, paramInt);
      gk.this.a(paramInt);
    }
  }
  
  public static abstract interface c
  {
    public abstract void a(int paramInt);
  }
}

/* Location:
 * Qualified Name:     gk
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */