import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.android.mms.ui.DialogFromNotifyActivity;

public class ui
  implements DialogInterface.OnClickListener
{
  public ui(DialogFromNotifyActivity paramDialogFromNotifyActivity) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    paramDialogInterface.dismiss();
    a.finish();
  }
}

/* Location:
 * Qualified Name:     ui
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */