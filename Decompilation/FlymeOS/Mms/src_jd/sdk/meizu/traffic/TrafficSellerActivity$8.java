package sdk.meizu.traffic;

import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

class TrafficSellerActivity$8
  extends WebViewClient
{
  TrafficSellerActivity$8(TrafficSellerActivity paramTrafficSellerActivity) {}
  
  public void onPageFinished(WebView paramWebView, String paramString)
  {
    super.onPageFinished(paramWebView, paramString);
  }
  
  public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
  {
    super.onReceivedError(paramWebView, paramInt, paramString1, paramString2);
  }
  
  public void onReceivedSslError(WebView paramWebView, SslErrorHandler paramSslErrorHandler, SslError paramSslError)
  {
    if (paramSslError.getUrl().startsWith("https://magneto.res.meizu.com")) {
      paramSslErrorHandler.proceed();
    }
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.TrafficSellerActivity.8
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */