import android.view.KeyEvent;
import android.view.View;
import com.android.mms.view.MmsRichContainer;
import com.android.mms.view.MmsRichContainer.a;

public class aeq
  extends ma
{
  public aeq(MmsRichContainer paramMmsRichContainer) {}
  
  protected boolean a(View paramView, int paramInt, KeyEvent paramKeyEvent)
  {
    if (MmsRichContainer.f(a) != null) {
      return MmsRichContainer.f(a).a(paramView, paramInt, paramKeyEvent);
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     aeq
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */