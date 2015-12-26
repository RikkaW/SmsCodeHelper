package sdk.meizu.traffic.hybird;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.widget.FrameLayout;

public class HyBirdView
  extends FrameLayout
{
  private WebView mWebView;
  
  public HyBirdView(Context paramContext)
  {
    super(paramContext);
    setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
    mWebView = new WebView(paramContext);
    paramContext = new ViewGroup.LayoutParams(-1, -1);
    mWebView.setLayoutParams(paramContext);
    mWebView.setOnLongClickListener(new HyBirdView.1(this));
    paramContext = mWebView.getSettings();
    if (Build.VERSION.SDK_INT >= 21) {
      paramContext.setMixedContentMode(2);
    }
    paramContext.setJavaScriptEnabled(true);
    paramContext.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
    paramContext.setUseWideViewPort(true);
    paramContext.setAllowUniversalAccessFromFileURLs(true);
    paramContext.setDomStorageEnabled(true);
    addView(mWebView);
  }
  
  public WebView getWebView()
  {
    return mWebView;
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.hybird.HyBirdView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */