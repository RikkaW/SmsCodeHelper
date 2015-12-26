import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import com.android.mms.ui.SelectConversationList;

public class xp
  extends lx
{
  public xp(SelectConversationList paramSelectConversationList, ListView paramListView)
  {
    super(paramListView);
  }
  
  public int a(int paramInt, long paramLong)
  {
    Log.i("SelectConversationList", "onActionItemDragStart...");
    return 0;
  }
  
  public int a(MenuItem paramMenuItem)
  {
    Log.i("SelectConversationList", "getActionItemType...");
    return 0;
  }
  
  public void a(MenuItem paramMenuItem, int paramInt, long paramLong)
  {
    Log.i("SelectConversationList", "onActionItemDrop...");
  }
  
  public void b(int paramInt, long paramLong)
  {
    Log.i("SelectConversationList", "onActionItemDragEnd...");
  }
  
  public boolean b(View paramView, int paramInt, long paramLong)
  {
    return false;
  }
}

/* Location:
 * Qualified Name:     xp
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */