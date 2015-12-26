import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import cn.com.xy.sms.sdk.ui.popu.web.SdkWebActivity;
import java.util.Locale;

public class ee
  extends WebViewClient
{
  public ee(SdkWebActivity paramSdkWebActivity) {}
  
  public void onPageFinished(WebView paramWebView, String paramString)
  {
    String str1 = a.a("menuName");
    String str2 = paramWebView.getTitle();
    a.a(str2, str1);
    SdkWebActivity.a(a).getSettings().setBlockNetworkImage(false);
    super.onPageFinished(paramWebView, paramString);
  }
  
  public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
  {
    super.onPageStarted(paramWebView, paramString, paramBitmap);
  }
  
  public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
  {
    if (paramInt != -10)
    {
      paramWebView.stopLoading();
      paramWebView.clearView();
      a.g();
    }
  }
  
  public void onReceivedSslError(WebView paramWebView, SslErrorHandler paramSslErrorHandler, SslError paramSslError)
  {
    paramSslErrorHandler.proceed();
  }
  
  public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
  {
    if ((paramString != null) && (!paramString.toLowerCase(Locale.getDefault()).startsWith("http"))) {
      try
      {
        if (paramString.indexOf("tel:") >= 0)
        {
          paramWebView = new Intent("android.intent.action.DIAL", Uri.parse(paramString));
          paramWebView.setFlags(268435456);
          a.startActivity(paramWebView);
          return true;
        }
        paramWebView = new Intent("android.intent.action.VIEW");
        paramWebView.setData(Uri.parse(paramString));
        a.startActivity(paramWebView);
        return true;
      }
      catch (Exception paramWebView)
      {
        paramWebView.printStackTrace();
        return true;
      }
    }
    paramWebView.loadUrl(paramString);
    return true;
  }
}

/* Location:
 * Qualified Name:     ee
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */