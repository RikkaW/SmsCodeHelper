import android.app.AlertDialog.Builder;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import cn.com.xy.sms.sdk.ui.popu.web.SdkWebActivity;

public class ef
  extends WebChromeClient
{
  public ef(SdkWebActivity paramSdkWebActivity) {}
  
  @Deprecated
  public void onConsoleMessage(String paramString1, int paramInt, String paramString2)
  {
    super.onConsoleMessage(paramString1, paramInt, paramString2);
  }
  
  public boolean onConsoleMessage(ConsoleMessage paramConsoleMessage)
  {
    return true;
  }
  
  public void onGeolocationPermissionsShowPrompt(String paramString, GeolocationPermissions.Callback paramCallback)
  {
    paramCallback.invoke(paramString, true, false);
    super.onGeolocationPermissionsShowPrompt(paramString, paramCallback);
  }
  
  public boolean onJsAlert(WebView paramWebView, String paramString1, String paramString2, JsResult paramJsResult)
  {
    paramWebView = new AlertDialog.Builder(a);
    paramWebView.setTitle("Alert");
    paramWebView.setMessage(paramString2);
    paramWebView.setPositiveButton(17039370, new eg(this, paramJsResult));
    paramWebView.setCancelable(false);
    paramWebView.create();
    paramWebView.show();
    return true;
  }
  
  public boolean onJsConfirm(WebView paramWebView, String paramString1, String paramString2, JsResult paramJsResult)
  {
    paramWebView = new AlertDialog.Builder(a);
    paramWebView.setTitle("confirm");
    paramWebView.setMessage(paramString2);
    paramWebView.setPositiveButton(17039370, new eh(this, paramJsResult));
    paramWebView.setNegativeButton(17039360, new ei(this, paramJsResult));
    paramWebView.setCancelable(false);
    paramWebView.create();
    paramWebView.show();
    return true;
  }
  
  public void onProgressChanged(WebView paramWebView, int paramInt)
  {
    if (paramInt == 100) {
      SdkWebActivity.b(a).setVisibility(8);
    }
    for (;;)
    {
      super.onProgressChanged(paramWebView, paramInt);
      paramWebView.requestFocus();
      return;
      if (8 == SdkWebActivity.b(a).getVisibility()) {
        SdkWebActivity.b(a).setVisibility(0);
      }
      SdkWebActivity.b(a).setProgress(paramInt);
    }
  }
  
  public void onReceivedTitle(WebView paramWebView, String paramString)
  {
    String str = a.a("menuName");
    a.a(paramString, str);
    super.onReceivedTitle(paramWebView, paramString);
  }
}

/* Location:
 * Qualified Name:     ef
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */