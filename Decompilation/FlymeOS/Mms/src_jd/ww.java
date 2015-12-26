import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.android.mms.ui.MessagingPreferenceActivity;
import com.android.mms.ui.MiniPreferenceActivity;

public class ww
  implements DialogInterface.OnClickListener
{
  public ww(MiniPreferenceActivity paramMiniPreferenceActivity) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (paramInt == -1)
    {
      MessagingPreferenceActivity.a(false, a);
      a.setResult(-1);
    }
    for (;;)
    {
      a.finish();
      return;
      a.setResult(0);
    }
  }
}

/* Location:
 * Qualified Name:     ww
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */