import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

class it
  extends ma
{
  it(ir paramir, EditText paramEditText)
  {
    super(paramEditText);
  }
  
  protected boolean a(View paramView, int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (paramKeyEvent.getAction() == 1))
    {
      a.a(ir.b.a, true);
      return true;
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     it
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */