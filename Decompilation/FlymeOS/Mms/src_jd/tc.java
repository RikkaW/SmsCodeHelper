import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.android.mms.ui.ComposeMessageActivity;

public class tc
  implements DialogInterface.OnClickListener
{
  public tc(ComposeMessageActivity paramComposeMessageActivity) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    ComposeMessageActivity.a(a, new td(this));
  }
}

/* Location:
 * Qualified Name:     tc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */