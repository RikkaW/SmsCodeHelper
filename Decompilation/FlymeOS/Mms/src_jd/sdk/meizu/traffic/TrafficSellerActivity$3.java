package sdk.meizu.traffic;

import android.widget.Toast;
import sdk.meizu.traffic.hybird.method.BaseNativeInterface.ToastHandler;

class TrafficSellerActivity$3
  implements BaseNativeInterface.ToastHandler
{
  TrafficSellerActivity$3(TrafficSellerActivity paramTrafficSellerActivity) {}
  
  public void toast(String paramString)
  {
    Toast.makeText(this$0, paramString, 0).show();
  }
  
  public void toastError(String paramString)
  {
    Toast.makeText(this$0, paramString, 0).show();
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.TrafficSellerActivity.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */