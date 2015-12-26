import com.amap.api.maps2d.AMap.OnCameraChangeListener;
import com.amap.api.maps2d.model.CameraPosition;
import com.android.mms.location.amap.GetLocationView;

public class ky
  implements AMap.OnCameraChangeListener
{
  public ky(GetLocationView paramGetLocationView) {}
  
  public void onCameraChange(CameraPosition paramCameraPosition) {}
  
  public void onCameraChangeFinish(CameraPosition paramCameraPosition)
  {
    if ((a.q) || (!GetLocationView.o(a)) || (!a.d())) {
      return;
    }
    GetLocationView.p(a);
  }
}

/* Location:
 * Qualified Name:     ky
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */