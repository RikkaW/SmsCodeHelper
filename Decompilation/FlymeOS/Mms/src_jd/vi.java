import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import com.android.mms.ui.MeizuSearchActivity;

public class vi
  implements View.OnKeyListener
{
  public vi(MeizuSearchActivity paramMeizuSearchActivity) {}
  
  public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent)
  {
    boolean bool = false;
    if ((paramInt == 84) || (paramInt == 66))
    {
      paramView = MeizuSearchActivity.m(a);
      MeizuSearchActivity.a(a, MeizuSearchActivity.a(a).getText().toString().trim());
      if (!paramView.equals(MeizuSearchActivity.m(a))) {
        MeizuSearchActivity.f(a);
      }
      MeizuSearchActivity.a(a, false);
      bool = true;
    }
    return bool;
  }
}

/* Location:
 * Qualified Name:     vi
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */