import com.android.mms.ui.ComposeMessageActivity;
import com.android.mms.view.EditTextEx;

public class rv
  extends mb
{
  public rv(ComposeMessageActivity paramComposeMessageActivity) {}
  
  public void a(boolean paramBoolean)
  {
    if (ComposeMessageActivity.l(a) == null) {
      return;
    }
    ComposeMessageActivity.l(a, paramBoolean);
    EditTextEx localEditTextEx = ComposeMessageActivity.l(a);
    rw localrw = new rw(this, paramBoolean);
    if (paramBoolean) {}
    for (long l = 0L;; l = 200L)
    {
      localEditTextEx.postDelayed(localrw, l);
      ComposeMessageActivity.n(a, paramBoolean);
      return;
    }
  }
}

/* Location:
 * Qualified Name:     rv
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */