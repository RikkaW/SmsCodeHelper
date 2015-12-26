package sdk.meizu.traffic;

import android.os.Handler;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import sdk.meizu.traffic.auth.MeizuAccountInfo;
import sdk.meizu.traffic.auth.MzAccountManager;
import sdk.meizu.traffic.hybird.JsCmd;
import sdk.meizu.traffic.hybird.method.BaseNativeInterface.PhoneHandler;
import sdk.meizu.traffic.util.GeoUtil;
import sdk.meizu.traffic.util.PhoneUtils;

class TrafficSellerActivity$5
  implements BaseNativeInterface.PhoneHandler
{
  TrafficSellerActivity$5(TrafficSellerActivity paramTrafficSellerActivity) {}
  
  public void getAreaMISP(String paramString, JsCmd paramJsCmd)
  {
    paramString = GeoUtil.getGeocodedLocationFor(this$0, paramString);
    if (!TextUtils.isEmpty(paramString)) {
      this$0.mUiHandler.post(new TrafficSellerActivity.5.5(this, paramJsCmd, paramString));
    }
  }
  
  public void getDefaultNum(JsCmd paramJsCmd)
  {
    Object localObject1 = (TelephonyManager)this$0.getSystemService("phone");
    Object localObject2 = PhoneUtils.checkAndNormalizePhoneNum(this$0, ((TelephonyManager)localObject1).getLine1Number());
    String str2 = "本机号码";
    String str1 = str2;
    localObject1 = localObject2;
    if (TextUtils.isEmpty((CharSequence)localObject2))
    {
      MeizuAccountInfo localMeizuAccountInfo = MzAccountManager.loadMzAccountInfo(this$0);
      str1 = str2;
      localObject1 = localObject2;
      if (localMeizuAccountInfo != null)
      {
        localObject1 = localMeizuAccountInfo.getPhone();
        str1 = "账户绑定号码";
      }
    }
    if (!TextUtils.isEmpty((CharSequence)localObject1)) {
      localObject2 = new JSONObject();
    }
    try
    {
      ((JSONObject)localObject2).put("pNums", localObject1);
      ((JSONObject)localObject2).put("pName", str1);
      this$0.mUiHandler.post(new TrafficSellerActivity.5.1(this, paramJsCmd, (JSONObject)localObject2));
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        localJSONException.printStackTrace();
      }
    }
  }
  
  public void getPackageName(JsCmd paramJsCmd)
  {
    this$0.mUiHandler.post(new TrafficSellerActivity.5.3(this, paramJsCmd));
  }
  
  public void getPhoneImei(JsCmd paramJsCmd)
  {
    String str = PhoneUtils.getPhoneImei(this$0);
    if (!TextUtils.isEmpty(str)) {
      this$0.mUiHandler.post(new TrafficSellerActivity.5.2(this, str, paramJsCmd));
    }
  }
  
  public void openContacts(JsCmd paramJsCmd)
  {
    TrafficSellerActivity.access$202(this$0, paramJsCmd);
    this$0.mUiHandler.post(new TrafficSellerActivity.5.4(this));
  }
  
  public void suggest(String paramString, JsCmd paramJsCmd)
  {
    this$0.mSearchContactThread = new TrafficSellerActivity.SearchContactThread(this$0, paramString, paramJsCmd);
    if (this$0.mUiHandler.hasMessages(10000)) {
      this$0.mUiHandler.removeMessages(10000);
    }
    this$0.mUiHandler.sendEmptyMessageDelayed(10000, 500L);
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.TrafficSellerActivity.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */