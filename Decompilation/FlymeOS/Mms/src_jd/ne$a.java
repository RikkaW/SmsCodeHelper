import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.core.AMapLocException;

class ne$a
  implements AMapLocationListener
{
  private ne$a(ne paramne) {}
  
  public void onLocationChanged(Location paramLocation) {}
  
  public void onLocationChanged(AMapLocation paramAMapLocation)
  {
    Log.d("MySdkDoAction", "onLocationChanged()~~~~");
    if (paramAMapLocation == null)
    {
      ne.a(a).obtainMessage(4100).sendToTarget();
      return;
    }
    if (paramAMapLocation.getAMapException().getErrorCode() != 0)
    {
      Log.d("MySdkDoAction", "errCode = " + paramAMapLocation.getAMapException().getErrorCode() + ", errMsg = " + paramAMapLocation.getAMapException().getErrorMessage());
      ne.a(a).obtainMessage(4100).sendToTarget();
      return;
    }
    Message localMessage = ne.a(a).obtainMessage(4102);
    Bundle localBundle = new Bundle();
    localBundle.putDouble("latitude", paramAMapLocation.getLatitude());
    localBundle.putDouble("longitude", paramAMapLocation.getLongitude());
    localMessage.setData(localBundle);
    localMessage.sendToTarget();
    a.a();
  }
  
  public void onProviderDisabled(String paramString) {}
  
  public void onProviderEnabled(String paramString) {}
  
  public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle) {}
}

/* Location:
 * Qualified Name:     ne.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */