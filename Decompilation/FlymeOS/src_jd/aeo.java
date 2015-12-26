import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import com.android.mms.ui.AttachmentEditor;
import com.android.mms.view.EditTextEx;
import com.android.mms.view.MmsRichContainer;

public class aeo
  implements View.OnKeyListener
{
  public aeo(MmsRichContainer paramMmsRichContainer) {}
  
  public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramKeyEvent.getAction() != 0) || (paramInt != 67) || (paramView == null)) {}
    do
    {
      return false;
      if ((paramView == MmsRichContainer.c(a)) && ((MmsRichContainer.c(a).length() == 0) || ((MmsRichContainer.c(a).getSelectionStart() == 0) && (MmsRichContainer.c(a).getSelectionEnd() == 0))))
      {
        if (MmsRichContainer.d(a).getVisibility() == 0) {
          a.a(0);
        }
        for (;;)
        {
          return true;
          if (a.f()) {
            a.b(false);
          }
        }
      }
    } while ((paramView != MmsRichContainer.c(a)) || (MmsRichContainer.c(a).length() <= 0));
    return MmsRichContainer.e(a);
  }
}

/* Location:
 * Qualified Name:     aeo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */