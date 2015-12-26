import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.ActionMode;
import com.android.mms.ui.ComposeMessageActivity;
import com.android.mms.ui.ComposeMessageActivity.h;
import java.util.HashMap;

public class to
  implements DialogInterface.OnClickListener
{
  public to(ComposeMessageActivity.h paramh, ActionMode paramActionMode) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    switch (paramInt)
    {
    }
    do
    {
      return;
    } while (ComposeMessageActivity.h.a(b).size() <= 0);
    ComposeMessageActivity.b(b.a, ComposeMessageActivity.h.a(b));
    a.finish();
  }
}

/* Location:
 * Qualified Name:     to
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */