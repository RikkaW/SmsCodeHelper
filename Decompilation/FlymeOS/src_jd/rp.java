import android.text.Editable;
import android.text.TextWatcher;
import com.android.mms.MmsApp;
import com.android.mms.ui.ComposeMessageActivity;
import com.android.mms.view.EditTextEx;
import com.android.mms.view.MmsRichContainer;
import com.android.mms.view.MzContactHeaderWidget;

public class rp
  implements TextWatcher
{
  public rp(ComposeMessageActivity paramComposeMessageActivity) {}
  
  public void afterTextChanged(Editable paramEditable)
  {
    if ((ComposeMessageActivity.l(a) != null) && (ComposeMessageActivity.l(a).length() == 0)) {
      a.i();
    }
  }
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    if ((ComposeMessageActivity.L(a)) && (!a.p()) && (a.j != null) && (a.j.h()) && (!a.r()))
    {
      ComposeMessageActivity.k(a, false);
      a.j.f();
    }
    a.onUserInteraction();
    if (ga.C()) {
      a.c.a(paramCharSequence);
    }
    if (MmsApp.a) {
      ComposeMessageActivity.M(a);
    }
    if (paramCharSequence.length() > 0) {
      ComposeMessageActivity.E(a).a(false, 0);
    }
    ComposeMessageActivity.p(a);
    ComposeMessageActivity.A(a);
    ComposeMessageActivity.a(a, paramCharSequence, paramInt1, paramInt2, paramInt3);
  }
}

/* Location:
 * Qualified Name:     rp
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */