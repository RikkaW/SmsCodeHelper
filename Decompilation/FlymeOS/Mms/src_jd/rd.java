import com.android.mms.recipient.MessageRecipient;
import com.android.mms.ui.ComposeMessageActivity;
import java.util.ArrayList;

public class rd
  implements Runnable
{
  public rd(ComposeMessageActivity paramComposeMessageActivity, ArrayList paramArrayList) {}
  
  public void run()
  {
    if (ComposeMessageActivity.j(b) != null) {
      ComposeMessageActivity.j(b).a(a);
    }
    a.clear();
    ComposeMessageActivity.J(b);
  }
}

/* Location:
 * Qualified Name:     rd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */