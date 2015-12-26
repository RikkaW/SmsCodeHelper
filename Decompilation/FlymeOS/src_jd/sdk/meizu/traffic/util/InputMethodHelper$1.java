package sdk.meizu.traffic.util;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;

final class InputMethodHelper$1
  implements Runnable
{
  InputMethodHelper$1(Context paramContext, WebView paramWebView) {}
  
  public void run()
  {
    ((InputMethodManager)val$context.getSystemService("input_method")).showSoftInput(val$webView, 1);
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.util.InputMethodHelper.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */