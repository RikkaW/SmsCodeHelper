import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.ActionMode;
import android.widget.ListView;
import com.android.mms.ui.ManageSimMessages;
import com.android.mms.ui.ManageSimMessages.b;

public class ux
  implements DialogInterface.OnClickListener
{
  public ux(ManageSimMessages.b paramb, ActionMode paramActionMode) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    switch (paramInt)
    {
    }
    do
    {
      return;
    } while (ManageSimMessages.c(b.a).getCheckedItemIds().length <= 0);
    ManageSimMessages.a(b.a, ManageSimMessages.c(b.a).getCheckedItemIds(), 111);
    a.finish();
  }
}

/* Location:
 * Qualified Name:     ux
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */