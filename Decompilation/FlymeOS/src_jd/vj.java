import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import com.android.mms.ui.MeizuSearchActivity;

public class vj
  implements AbsListView.OnScrollListener
{
  public vj(MeizuSearchActivity paramMeizuSearchActivity) {}
  
  public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
  {
    if (paramInt == 1) {
      MeizuSearchActivity.a(a, false);
    }
  }
}

/* Location:
 * Qualified Name:     vj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */