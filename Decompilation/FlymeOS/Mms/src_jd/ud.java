import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import com.android.mms.ui.ConversationList;

public class ud
  implements AbsListView.OnScrollListener
{
  public ud(ConversationList paramConversationList) {}
  
  public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
  {
    if (paramInt == 1) {
      a.a(ir.b.a, false);
    }
  }
}

/* Location:
 * Qualified Name:     ud
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */