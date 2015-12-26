import android.view.View;
import android.widget.AbsListView.RecyclerListener;
import com.android.mms.view.MessageListItemBase;

class vy
  implements AbsListView.RecyclerListener
{
  vy(vx paramvx) {}
  
  public void onMovedToScrapHeap(View paramView)
  {
    if ((paramView instanceof MessageListItemBase)) {
      ((MessageListItemBase)paramView).e();
    }
  }
}

/* Location:
 * Qualified Name:     vy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */