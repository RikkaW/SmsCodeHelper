import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.android.mms.view.TwoTextViewListItem;
import java.util.List;

public class gi
  extends BaseAdapter
{
  private List<ze> a;
  private LayoutInflater b;
  
  public gi(List<ze> paramList, Context paramContext)
  {
    a = paramList;
    b = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
  }
  
  public int getCount()
  {
    return a.size();
  }
  
  public Object getItem(int paramInt)
  {
    return a.get(paramInt);
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null) {
      paramView = b.inflate(2130968675, paramViewGroup, false);
    }
    for (;;)
    {
      if ((paramView instanceof TwoTextViewListItem)) {
        ((TwoTextViewListItem)paramView).a((ze)a.get(paramInt));
      }
      return paramView;
    }
  }
}

/* Location:
 * Qualified Name:     gi
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */