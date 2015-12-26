import android.text.Editable;
import android.text.TextWatcher;
import com.android.mms.ui.ComposeMessageActivity;

public class rq
  implements TextWatcher
{
  public rq(ComposeMessageActivity paramComposeMessageActivity) {}
  
  public void afterTextChanged(Editable paramEditable) {}
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    a.c.a(paramCharSequence, true);
    ComposeMessageActivity.p(a);
    ComposeMessageActivity.A(a);
  }
}

/* Location:
 * Qualified Name:     rq
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */