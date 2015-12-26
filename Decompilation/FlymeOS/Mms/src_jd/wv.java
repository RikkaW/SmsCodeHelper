import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import com.android.mms.ui.MiniPreferenceActivity;

public class wv
  implements DialogInterface.OnDismissListener
{
  public wv(MiniPreferenceActivity paramMiniPreferenceActivity) {}
  
  public void onDismiss(DialogInterface paramDialogInterface)
  {
    if (!a.isFinishing()) {
      a.finish();
    }
  }
}

/* Location:
 * Qualified Name:     wv
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */