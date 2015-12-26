import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import com.android.mms.ui.ComposeMessageActivity;

public class ti
  implements AbsListView.OnScrollListener
{
  public ti(ComposeMessageActivity paramComposeMessageActivity) {}
  
  public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
  {
    if (2 == paramInt)
    {
      ComposeMessageActivity.y(a, true);
      return;
    }
    ComposeMessageActivity.y(a, false);
  }
}

/* Location:
 * Qualified Name:     ti
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */