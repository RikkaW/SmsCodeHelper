import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.mms.location.PoiListItem;
import java.util.ArrayList;

public class kv
  extends kt
{
  public kv(Context paramContext, ArrayList<kr> paramArrayList)
  {
    super(paramContext, 2130968727, paramArrayList);
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null) {
      paramView = a.inflate(b, null);
    }
    for (;;)
    {
      paramViewGroup = (kr)getItem(paramInt);
      if (paramViewGroup != null) {
        break;
      }
      return paramView;
    }
    ((PoiListItem)paramView).a(paramViewGroup);
    return paramView;
  }
}

/* Location:
 * Qualified Name:     kv
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */