import android.view.View;
import android.view.View.OnFocusChangeListener;
import com.android.mms.quickreply.EditQuickReplyView;
import com.android.mms.quickreply.EditQuickReplyView.a;

public class mn
  implements View.OnFocusChangeListener
{
  public mn(EditQuickReplyView paramEditQuickReplyView) {}
  
  public void onFocusChange(View paramView, boolean paramBoolean)
  {
    EditQuickReplyView.a(a, paramBoolean);
    EditQuickReplyView.b(a).a(paramBoolean, EditQuickReplyView.a(a));
  }
}

/* Location:
 * Qualified Name:     mn
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */