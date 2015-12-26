import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;

class ix
  implements View.OnKeyListener
{
  ix(ir paramir) {}
  
  public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent)
  {
    boolean bool = false;
    if ((paramInt == 84) || (paramInt == 66))
    {
      if ((!TextUtils.isEmpty(ir.a(a).getText())) && (ir.a(a).getText().toString().trim().length() > 0) && (ir.c(a) != null)) {
        ir.c(a).a(ir.a(a).getText().toString().trim());
      }
      ir.a(a, false, false);
      bool = true;
    }
    return bool;
  }
}

/* Location:
 * Qualified Name:     ix
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */