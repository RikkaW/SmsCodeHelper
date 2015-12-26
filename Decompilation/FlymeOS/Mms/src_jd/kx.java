import android.content.Context;
import com.amap.api.maps2d.AMapUtils;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.services.core.LatLonPoint;

public class kx
  extends ku
{
  public kx(Context paramContext)
  {
    super(paramContext);
  }
  
  public static double a(LatLng paramLatLng, LatLonPoint paramLatLonPoint)
  {
    if ((paramLatLng != null) && (paramLatLonPoint != null)) {
      return AMapUtils.calculateLineDistance(paramLatLng, new LatLng(paramLatLonPoint.getLatitude(), paramLatLonPoint.getLongitude()));
    }
    return 0.0D;
  }
  
  public static LatLng a(LatLonPoint paramLatLonPoint)
  {
    return new LatLng(paramLatLonPoint.getLatitude(), paramLatLonPoint.getLongitude());
  }
  
  public static boolean a(LatLng paramLatLng1, LatLng paramLatLng2)
  {
    if ((paramLatLng1 == null) || (paramLatLng2 == null)) {}
    while ((latitude != latitude) || (longitude != longitude)) {
      return false;
    }
    return true;
  }
  
  public static double b(LatLng paramLatLng1, LatLng paramLatLng2)
  {
    if ((paramLatLng1 != null) && (paramLatLng2 != null)) {
      return AMapUtils.calculateLineDistance(paramLatLng1, paramLatLng2);
    }
    return 0.0D;
  }
}

/* Location:
 * Qualified Name:     kx
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */