import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.android.mms.ui.ComposeMessageActivity;

public class ra
  implements DialogInterface.OnClickListener
{
  public ra(ComposeMessageActivity paramComposeMessageActivity) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    paramDialogInterface.dismiss();
    a.c.a(true);
  }
}

/* Location:
 * Qualified Name:     ra
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */