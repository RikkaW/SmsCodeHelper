import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import com.android.mms.ui.MeizuSearchActivity;

public class uy
  extends ma
{
  public uy(MeizuSearchActivity paramMeizuSearchActivity, EditText paramEditText)
  {
    super(paramEditText);
  }
  
  protected boolean a(View paramView, int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (paramKeyEvent.getAction() == 1) && ((TextUtils.isEmpty(MeizuSearchActivity.a(a).getText().toString())) || ((!MeizuSearchActivity.b(a)) && (MeizuSearchActivity.c(a).getCount() == 0)))) {
      a.finish();
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     uy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */