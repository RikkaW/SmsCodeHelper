import android.view.MenuItem;
import android.widget.ListView;
import com.android.mms.ui.MeizuSearchActivity;
import com.android.mms.ui.MeizuSearchActivity.b;

public class vk
  extends lx
{
  public vk(MeizuSearchActivity paramMeizuSearchActivity, ListView paramListView)
  {
    super(paramListView);
  }
  
  protected int a(int paramInt, long paramLong)
  {
    return MeizuSearchActivity.h(a).a(paramInt, paramLong);
  }
  
  protected int a(MenuItem paramMenuItem)
  {
    return MeizuSearchActivity.h(a).a(paramMenuItem);
  }
  
  protected void a(MenuItem paramMenuItem, int paramInt, long paramLong)
  {
    MeizuSearchActivity.h(a).a(paramMenuItem, paramInt, paramLong);
  }
  
  protected void b(int paramInt, long paramLong)
  {
    MeizuSearchActivity.h(a).b(paramInt, paramLong);
  }
}

/* Location:
 * Qualified Name:     vk
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */