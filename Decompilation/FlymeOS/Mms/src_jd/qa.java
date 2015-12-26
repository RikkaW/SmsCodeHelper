import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.android.mms.ui.ClassZeroActivity;

public class qa
  implements DialogInterface.OnClickListener
{
  public qa(ClassZeroActivity paramClassZeroActivity) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    ClassZeroActivity.a(a, true);
    ClassZeroActivity.b(a);
    paramDialogInterface.dismiss();
    ClassZeroActivity.c(a);
  }
}

/* Location:
 * Qualified Name:     qa
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */