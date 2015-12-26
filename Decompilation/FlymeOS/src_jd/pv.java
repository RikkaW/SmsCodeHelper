import android.os.Message;
import android.view.MenuItem;
import android.widget.PopupMenu.OnMenuItemClickListener;
import com.android.mms.ui.AttachmentEditor;
import com.android.mms.ui.AttachmentEditor.a;

public class pv
  implements PopupMenu.OnMenuItemClickListener
{
  public pv(AttachmentEditor.a parama) {}
  
  public boolean onMenuItemClick(MenuItem paramMenuItem)
  {
    Message.obtain(AttachmentEditor.b(a.a), AttachmentEditor.a.a(a)).sendToTarget();
    return true;
  }
}

/* Location:
 * Qualified Name:     pv
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */