import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import cn.com.xy.sms.sdk.action.AbsSdkDoAction;
import cn.com.xy.sms.sdk.ui.popu.web.NearbyPointList;
import cn.com.xy.sms.sdk.util.DuoquUtils;
import java.util.ArrayList;
import java.util.HashMap;

public class dt
  implements AdapterView.OnItemClickListener
{
  public dt(NearbyPointList paramNearbyPointList) {}
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    DuoquUtils.getSdkDoAction().openMap(a.getApplicationContext(), (String)((HashMap)NearbyPointList.a(a).get(paramInt)).get("name"), (String)((HashMap)NearbyPointList.a(a).get(paramInt)).get("address"), ((Double)((HashMap)NearbyPointList.a(a).get(paramInt)).get("longitude")).doubleValue(), ((Double)((HashMap)NearbyPointList.a(a).get(paramInt)).get("latitude")).doubleValue());
  }
}

/* Location:
 * Qualified Name:     dt
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */