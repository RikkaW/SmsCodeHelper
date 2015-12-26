import com.amap.api.maps2d.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;

public class ld
  extends kr
{
  private LatLng h;
  private PoiItem i;
  private boolean j;
  
  public ld(String paramString1, String paramString2, double paramDouble, LatLng paramLatLng)
  {
    super(paramString1, paramString2, paramDouble);
    h = paramLatLng;
    g = false;
  }
  
  public ld(String paramString1, String paramString2, double paramDouble, LatLonPoint paramLatLonPoint)
  {
    super(paramString1, paramString2, paramDouble);
    h = kx.a(paramLatLonPoint);
    g = false;
  }
  
  public void a(LatLng paramLatLng)
  {
    h = paramLatLng;
  }
  
  public void a(PoiItem paramPoiItem)
  {
    i = paramPoiItem;
  }
  
  public void c(boolean paramBoolean)
  {
    j = paramBoolean;
  }
  
  public LatLng h()
  {
    return h;
  }
  
  public boolean i()
  {
    return j;
  }
  
  public PoiItem j()
  {
    return i;
  }
}

/* Location:
 * Qualified Name:     ld
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */