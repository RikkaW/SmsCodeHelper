import android.widget.ImageButton;
import com.android.mms.ui.ComposeMessageActivity;
import com.android.mms.view.AttachmentGroupView.b;

public class tf
  implements AttachmentGroupView.b
{
  public tf(ComposeMessageActivity paramComposeMessageActivity) {}
  
  public void a(int paramInt)
  {
    if (paramInt == 0)
    {
      ComposeMessageActivity.x(a, true);
      ComposeMessageActivity.aj(a).setImageResource(2130837794);
      return;
    }
    ComposeMessageActivity.x(a, false);
    ComposeMessageActivity.aj(a).setImageResource(2130837781);
  }
}

/* Location:
 * Qualified Name:     tf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */