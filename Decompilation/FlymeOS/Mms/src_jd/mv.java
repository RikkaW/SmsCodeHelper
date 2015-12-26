import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import com.android.mms.recipient.MessageRecipient;

public class mv
  implements AbsListView.OnScrollListener
{
  public mv(MessageRecipient paramMessageRecipient) {}
  
  public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
  {
    if ((paramInt == 1) && (MessageRecipient.a(a)))
    {
      a.f();
      MessageRecipient.a(a, false);
    }
  }
}

/* Location:
 * Qualified Name:     mv
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */