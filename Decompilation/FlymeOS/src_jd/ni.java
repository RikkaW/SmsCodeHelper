import android.app.Activity;
import android.view.MenuItem;
import android.view.View;
import com.android.mms.smartmessage.SmartMessageViewContainer;
import java.util.ArrayList;

public class ni
  extends mc
{
  public ni(SmartMessageViewContainer paramSmartMessageViewContainer, Object paramObject, View paramView)
  {
    super(paramObject);
  }
  
  public void a(Object paramObject)
  {
    super.a(paramObject);
  }
  
  public boolean a(Object paramObject, MenuItem paramMenuItem)
  {
    ((aaw.a)a.getTag()).d.get(paramMenuItem.getItemId())).a((Activity)SmartMessageViewContainer.a(b));
    return true;
  }
}

/* Location:
 * Qualified Name:     ni
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */