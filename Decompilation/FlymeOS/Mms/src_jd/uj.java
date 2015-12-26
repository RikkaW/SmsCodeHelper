import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.android.mms.ui.DialogFromNotifyActivity;

public class uj
  implements DialogInterface.OnClickListener
{
  public uj(DialogFromNotifyActivity paramDialogFromNotifyActivity) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (DialogFromNotifyActivity.a(a) == 1) {
      new Thread(new uk(this), "delUriMessage").start();
    }
    paramDialogInterface.dismiss();
    a.finish();
  }
}

/* Location:
 * Qualified Name:     uj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */