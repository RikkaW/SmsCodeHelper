import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.android.mms.quickreply.EditQuickReplyView;
import com.android.mms.quickreply.EditQuickReplyView.a;

public class mo
  implements TextView.OnEditorActionListener
{
  public mo(EditQuickReplyView paramEditQuickReplyView) {}
  
  public boolean onEditorAction(TextView paramTextView, int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 5)
    {
      EditQuickReplyView.b(a).a(EditQuickReplyView.a(a));
      return true;
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     mo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */