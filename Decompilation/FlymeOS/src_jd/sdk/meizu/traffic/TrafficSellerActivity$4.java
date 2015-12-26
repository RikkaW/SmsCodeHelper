package sdk.meizu.traffic;

import android.os.Handler;
import android.text.TextUtils;
import sdk.meizu.traffic.auth.CUserManager;
import sdk.meizu.traffic.auth.MzAuthException;
import sdk.meizu.traffic.hybird.JsCmd;
import sdk.meizu.traffic.hybird.method.BaseNativeInterface.AuthHandler;

class TrafficSellerActivity$4
  implements BaseNativeInterface.AuthHandler
{
  TrafficSellerActivity$4(TrafficSellerActivity paramTrafficSellerActivity) {}
  
  public void getAuthToken(boolean paramBoolean, JsCmd paramJsCmd)
  {
    try
    {
      String str = CUserManager.getInstance(this$0).getToken(paramBoolean);
      if (!TextUtils.isEmpty(str)) {
        this$0.mUiHandler.postDelayed(new TrafficSellerActivity.4.1(this, paramJsCmd, str), 200L);
      }
      return;
    }
    catch (MzAuthException paramJsCmd)
    {
      while (!paramJsCmd.needLogin()) {}
      this$0.mUiHandler.post(new TrafficSellerActivity.4.2(this, paramJsCmd));
    }
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.TrafficSellerActivity.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */