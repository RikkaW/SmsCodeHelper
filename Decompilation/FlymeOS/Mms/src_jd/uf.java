import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.android.mms.ui.DeliveryReportListItem;
import java.util.List;

public class uf
  extends ArrayAdapter<ug>
{
  public uf(Context paramContext, List<ug> paramList)
  {
    super(paramContext, 2130968624, 2131886263, paramList);
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    ug localug = (ug)getItem(paramInt);
    if (paramView == null) {}
    for (paramView = (DeliveryReportListItem)LayoutInflater.from(getContext()).inflate(2130968624, paramViewGroup, false);; paramView = (DeliveryReportListItem)paramView)
    {
      paramView.a(a, b, c);
      paramViewGroup = paramView;
      do
      {
        return paramViewGroup;
        paramViewGroup = paramView;
      } while (!(paramView instanceof DeliveryReportListItem));
    }
  }
}

/* Location:
 * Qualified Name:     uf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */