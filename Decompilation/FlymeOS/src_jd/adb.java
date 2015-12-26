import com.android.mms.view.MessageListItem;
import com.android.mms.view.MmsFoldableTextView;
import com.android.mms.view.MmsFoldableTextView.a;

public class adb
  implements MmsFoldableTextView.a
{
  public adb(MessageListItem paramMessageListItem) {}
  
  public boolean a(MmsFoldableTextView paramMmsFoldableTextView)
  {
    if ((a.M == null) || (a.M.q())) {
      return false;
    }
    MessageListItem.a(a, a.M, true, paramMmsFoldableTextView);
    return false;
  }
}

/* Location:
 * Qualified Name:     adb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */