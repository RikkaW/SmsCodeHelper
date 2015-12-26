import android.view.MenuItem;
import android.widget.ListView;
import com.android.mms.ui.ManageSimMessages;
import com.android.mms.ui.ManageSimMessages.b;

public class ut
  extends lx
{
  public ut(ManageSimMessages paramManageSimMessages, ListView paramListView)
  {
    super(paramListView);
  }
  
  protected int a(int paramInt, long paramLong)
  {
    return ManageSimMessages.a(a).a(paramInt, paramLong);
  }
  
  protected int a(MenuItem paramMenuItem)
  {
    return ManageSimMessages.a(a).a(paramMenuItem);
  }
  
  protected void a(MenuItem paramMenuItem, int paramInt, long paramLong)
  {
    ManageSimMessages.a(a).a(paramMenuItem, paramInt, paramLong);
  }
  
  protected void b(int paramInt, long paramLong)
  {
    ManageSimMessages.a(a).b(paramInt, paramLong);
  }
}

/* Location:
 * Qualified Name:     ut
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */