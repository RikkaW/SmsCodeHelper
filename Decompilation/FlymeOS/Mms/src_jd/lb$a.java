import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.poisearch.PoiSearch.Query;
import com.amap.api.services.poisearch.PoiSearch.SearchBound;

public class lb$a
  extends Handler
{
  public lb$a(lb paramlb, Looper paramLooper)
  {
    super(paramLooper);
  }
  
  public void handleMessage(Message paramMessage)
  {
    Log.i("amap/SearchUtils", "handleMessage msg.what = " + what);
    switch (what)
    {
    case 7: 
    case 8: 
    case 10: 
    case 12: 
    case 13: 
    case 14: 
    default: 
      return;
    case 6: 
      paramMessage = (LatLng)obj;
      lb.c(a).setQuery(lb.a(a, lb.a(a), null, lb.b(a)));
      lb.c(a).setBound(new PoiSearch.SearchBound(new LatLonPoint(latitude, longitude), 50000));
      lb.c(a).searchPOIAsyn();
      return;
    case 2: 
    case 4: 
      paramMessage = (LatLng)obj;
      a.a(paramMessage);
      return;
    case 15: 
    case 16: 
      paramMessage = (LatLng)obj;
      lb.c(a).setQuery(lb.a(a, "", "餐饮服务|生活服务|公司|购物|汽车|体育|医疗|政府|科教|建筑", lb.b(a)));
      lb.c(a).setBound(new PoiSearch.SearchBound(new LatLonPoint(latitude, longitude), 50000));
      lb.c(a).searchPOIAsyn();
      return;
    }
    lb.c(a).setQuery(new PoiSearch.Query(lb.a(a), null, lb.b(a)));
    lb.c(a).searchPOIAsyn();
  }
}

/* Location:
 * Qualified Name:     lb.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */