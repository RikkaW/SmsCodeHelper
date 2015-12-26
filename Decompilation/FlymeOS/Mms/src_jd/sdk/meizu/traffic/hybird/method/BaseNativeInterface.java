package sdk.meizu.traffic.hybird.method;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.webkit.WebView;
import org.json.JSONObject;
import sdk.meizu.traffic.TrafficOrder;
import sdk.meizu.traffic.hybird.JsCmd;

public class BaseNativeInterface
  implements INativeInterface
{
  private static final String TAG = BaseNativeInterface.class.getSimpleName();
  private JsCmd mAuthCallBack;
  private AuthHandler mAuthHandler;
  private JsCmd mContactCallback;
  private EventHandler mEventHandler = null;
  private ImHandler mImHandler = null;
  private LoadingHandler mLoadingHandler;
  private PayHandler mPayHandler = null;
  private PhoneHandler mPhoneHandler;
  private SystemBarHandler mSystemBarHandler;
  private ToastHandler mToastHandler;
  private Handler mUiHandler = new Handler(Looper.getMainLooper());
  
  @NativeMethod
  public void addMenuItem(@Parameter("key") String paramString1, @Parameter("text") String paramString2, @Parameter("enable") boolean paramBoolean, @CallBack String paramString3)
  {
    mUiHandler.post(new BaseNativeInterface.8(this, paramString3, paramString1, paramString2, paramBoolean));
  }
  
  @NativeMethod
  public void addPullRefresh()
  {
    if (mLoadingHandler != null) {}
  }
  
  @NativeMethod
  public void closeInputMethod()
  {
    if (mImHandler != null) {
      mImHandler.closeInputMethod();
    }
  }
  
  @NativeMethod
  public void getAreaMISP(@Parameter("phoneNum") String paramString1, @Parameter("misp") String paramString2, @CallBack String paramString3)
  {
    if (mPhoneHandler != null) {
      mPhoneHandler.getAreaMISP(paramString1, JsCmd.from("").setMethod(paramString3));
    }
  }
  
  @NativeMethod
  public void getDefaultNumber(@CallBack String paramString)
  {
    if (mPhoneHandler != null) {
      mPhoneHandler.getDefaultNum(JsCmd.from("").setMethod(paramString));
    }
  }
  
  public JsToAndroidBridge getJsToAndroidBridge()
  {
    return new JsToAndroidBridge(this);
  }
  
  @NativeMethod
  public void getPackageName(@CallBack String paramString)
  {
    if (mPhoneHandler != null) {
      mPhoneHandler.getPackageName(JsCmd.from("").setMethod(paramString));
    }
  }
  
  @NativeMethod
  public void getPhoneImei(@CallBack String paramString)
  {
    if (mPhoneHandler != null) {
      mPhoneHandler.getPhoneImei(JsCmd.from("").setMethod(paramString));
    }
  }
  
  @NativeMethod
  public void getToken(@Parameter("isForceUpdate") String paramString1, @CallBack String paramString2)
  {
    if (mAuthCallBack == null) {
      mAuthCallBack = JsCmd.from("").setMethod(paramString2);
    }
    if (mAuthHandler != null)
    {
      mAuthHandler.getAuthToken(Boolean.valueOf(paramString1).booleanValue(), mAuthCallBack);
      return;
    }
    Log.e(TAG, "getToken must have native handler");
  }
  
  public void handleAuthCallback(WebView paramWebView, String paramString)
  {
    mUiHandler.post(new BaseNativeInterface.6(this, paramString, paramWebView));
  }
  
  @NativeMethod
  public void log(String paramString)
  {
    Log.v(TAG, paramString);
  }
  
  @NativeMethod
  public void logError(@Parameter("error") String paramString)
  {
    Log.e(TAG, paramString);
  }
  
  @NativeMethod
  public void openBuyComp(@Parameter("order") JSONObject paramJSONObject, @CallBack String paramString)
  {
    if (mPayHandler != null) {
      mPayHandler.doPayAction(new TrafficOrder(paramJSONObject), JsCmd.from("").setMethod(paramString));
    }
  }
  
  @NativeMethod
  public void openContacts(@CallBack String paramString)
  {
    if (mPhoneHandler != null) {
      mPhoneHandler.openContacts(JsCmd.from("").setMethod(paramString));
    }
  }
  
  @NativeMethod
  public void removePullRefresh()
  {
    mUiHandler.post(new BaseNativeInterface.5(this));
  }
  
  public void reset()
  {
    mToastHandler = null;
    mLoadingHandler = null;
    mAuthHandler = null;
    mSystemBarHandler = null;
    mPhoneHandler = null;
    mAuthCallBack = null;
    mUiHandler = null;
    mEventHandler = null;
    mPayHandler = null;
  }
  
  public void setAuthHandler(AuthHandler paramAuthHandler)
  {
    mAuthHandler = paramAuthHandler;
  }
  
  public void setEventHandler(EventHandler paramEventHandler)
  {
    mEventHandler = paramEventHandler;
  }
  
  public void setImHandler(ImHandler paramImHandler)
  {
    mImHandler = paramImHandler;
  }
  
  public void setLoadingHandler(LoadingHandler paramLoadingHandler)
  {
    mLoadingHandler = paramLoadingHandler;
  }
  
  public void setPayHandler(PayHandler paramPayHandler)
  {
    mPayHandler = paramPayHandler;
  }
  
  public void setPhoneHandler(PhoneHandler paramPhoneHandler)
  {
    mPhoneHandler = paramPhoneHandler;
  }
  
  public void setSystemBarHandler(SystemBarHandler paramSystemBarHandler)
  {
    mSystemBarHandler = paramSystemBarHandler;
  }
  
  @NativeMethod
  public void setTitle(@Parameter("title") String paramString)
  {
    mUiHandler.post(new BaseNativeInterface.7(this, paramString));
  }
  
  public void setToastHandler(ToastHandler paramToastHandler)
  {
    mToastHandler = paramToastHandler;
  }
  
  @NativeMethod
  public void showInputMethod()
  {
    if (mImHandler != null) {
      mImHandler.showInputMethod();
    }
  }
  
  @NativeMethod
  public void startLoading(@Parameter("mes") String paramString)
  {
    mUiHandler.post(new BaseNativeInterface.3(this, paramString));
  }
  
  @NativeMethod
  public void stopLoading()
  {
    mUiHandler.post(new BaseNativeInterface.4(this));
  }
  
  @NativeMethod
  public void suggest(@Parameter("pNums") String paramString1, @CallBack String paramString2)
  {
    if (mPhoneHandler != null) {
      mPhoneHandler.suggest(paramString1, JsCmd.from("").setMethod(paramString2));
    }
  }
  
  @NativeMethod
  public void toast(@Parameter("toastString") String paramString)
  {
    mUiHandler.post(new BaseNativeInterface.1(this, paramString));
  }
  
  @NativeMethod
  public void toastError(@Parameter("toastString") String paramString)
  {
    mUiHandler.post(new BaseNativeInterface.2(this, paramString));
  }
  
  @NativeMethod
  public void updateMenuItem(@Parameter("key") String paramString1, @Parameter("text") String paramString2, @Parameter("enable") boolean paramBoolean)
  {
    mUiHandler.post(new BaseNativeInterface.9(this, paramString1, paramString2, paramBoolean));
  }
  
  @NativeMethod
  public void usageEvent(@Parameter("pageName") String paramString1, @Parameter("eventName") String paramString2, @Parameter("eventData") String paramString3)
  {
    if (mEventHandler != null) {
      mEventHandler.usageEvent(paramString1, paramString2, paramString3);
    }
  }
  
  public static abstract interface AuthHandler
  {
    public abstract void getAuthToken(boolean paramBoolean, JsCmd paramJsCmd);
  }
  
  public static abstract interface EventHandler
  {
    public abstract void usageEvent(String paramString1, String paramString2, String paramString3);
  }
  
  public static abstract interface ImHandler
  {
    public abstract void closeInputMethod();
    
    public abstract void showInputMethod();
  }
  
  public static abstract interface LoadingHandler
  {
    public abstract void startLoading(String paramString);
    
    public abstract void stopLoading();
  }
  
  public static abstract interface PageHandler
  {
    public abstract void finishModule(boolean paramBoolean, String paramString);
    
    public abstract void finishPage(String paramString1, String paramString2);
    
    public abstract void handleBackPressed(String paramString);
    
    public abstract void handleHomePressed(String paramString);
    
    public abstract void startPage(String paramString1, String paramString2, JsCmd paramJsCmd);
  }
  
  public static abstract interface PayHandler
  {
    public abstract void doPayAction(TrafficOrder paramTrafficOrder, JsCmd paramJsCmd);
  }
  
  public static abstract interface PhoneHandler
  {
    public abstract void getAreaMISP(String paramString, JsCmd paramJsCmd);
    
    public abstract void getDefaultNum(JsCmd paramJsCmd);
    
    public abstract void getPackageName(JsCmd paramJsCmd);
    
    public abstract void getPhoneImei(JsCmd paramJsCmd);
    
    public abstract void openContacts(JsCmd paramJsCmd);
    
    public abstract void suggest(String paramString, JsCmd paramJsCmd);
  }
  
  public static abstract interface SystemBarHandler
  {
    public abstract void addMenuItem(String paramString1, String paramString2, boolean paramBoolean, JsCmd paramJsCmd);
    
    public abstract void handleMarginCallback(JsCmd paramJsCmd);
    
    public abstract void setTitle(String paramString);
    
    public abstract void updateMenuItem(String paramString1, String paramString2, boolean paramBoolean);
  }
  
  public static abstract interface ToastHandler
  {
    public abstract void toast(String paramString);
    
    public abstract void toastError(String paramString);
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.hybird.method.BaseNativeInterface
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */