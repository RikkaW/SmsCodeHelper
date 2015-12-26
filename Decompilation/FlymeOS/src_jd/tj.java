import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import com.android.mms.ui.ComposeMessageActivity;

public class tj
  implements DialogInterface.OnCancelListener
{
  public tj(ComposeMessageActivity paramComposeMessageActivity) {}
  
  public void onCancel(DialogInterface paramDialogInterface)
  {
    a.finish();
  }
}

/* Location:
 * Qualified Name:     tj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */