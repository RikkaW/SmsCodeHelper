import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import com.android.mms.ui.ManageSimMessages;

public class uu
  extends ProgressDialog
{
  public uu(ManageSimMessages paramManageSimMessages, Context paramContext)
  {
    super(paramContext);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (getWindow() != null) {
      getWindow().setDimAmount(0.0F);
    }
  }
}

/* Location:
 * Qualified Name:     uu
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */