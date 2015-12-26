import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Environment;
import com.android.mms.ui.ComposeMessageActivity;
import java.io.File;

public class sg
  implements DialogInterface.OnClickListener
{
  public sg(ComposeMessageActivity paramComposeMessageActivity) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    wd.d(a, Environment.getExternalStorageDirectory().getPath());
  }
}

/* Location:
 * Qualified Name:     sg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */