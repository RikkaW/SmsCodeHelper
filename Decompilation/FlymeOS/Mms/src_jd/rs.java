import android.widget.EditText;
import com.android.mms.ui.ComposeMessageActivity;
import com.android.mms.view.MmsRichContainer.e;

public class rs
  implements MmsRichContainer.e
{
  public rs(ComposeMessageActivity paramComposeMessageActivity) {}
  
  public void a()
  {
    ComposeMessageActivity.k(a).removeTextChangedListener(ComposeMessageActivity.N(a));
    ComposeMessageActivity.k(a).setOnFocusChangeListener(null);
  }
}

/* Location:
 * Qualified Name:     rs
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */