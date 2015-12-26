import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.TextUtils;
import com.android.mms.ui.ComposeMessageActivity;

class acc
  implements DialogInterface.OnClickListener
{
  acc(abu paramabu, String paramString1, String paramString2) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (a == ac).m)
    {
      ((ComposeMessageActivity)abu.c(c)).d(b);
      return;
    }
    paramDialogInterface = TextUtils.split(a, ";");
    paramDialogInterface = new ot(abu.c(c), paramDialogInterface, b, 0L);
    int i = abu.a(c).ag();
    paramInt = i;
    if (i < 0) {
      paramInt = 0;
    }
    paramDialogInterface.a(paramInt);
    try
    {
      paramDialogInterface.a(0L);
      abu.a(c, a, paramInt);
      return;
    }
    catch (Exception paramDialogInterface)
    {
      for (;;)
      {
        paramDialogInterface.printStackTrace();
      }
    }
  }
}

/* Location:
 * Qualified Name:     acc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */