import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;

class xy
  implements DialogInterface.OnClickListener
{
  xy(xv paramxv) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    Log.i("SendTypeChooseMessageHelper", "showSendTypeChooseDialog onClick which = " + paramInt);
    if (paramInt < 2)
    {
      xv.b(a).a(paramInt, zv.c(paramInt), 0);
      return;
    }
    xv.b(a).a(-10, "-10", 1);
  }
}

/* Location:
 * Qualified Name:     xy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */