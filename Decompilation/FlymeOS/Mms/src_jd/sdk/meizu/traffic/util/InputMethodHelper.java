package sdk.meizu.traffic.util;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;

public class InputMethodHelper
{
  public static boolean closeInputMethod(Context paramContext, WebView paramWebView)
  {
    paramContext = (InputMethodManager)paramContext.getSystemService("input_method");
    if ((paramContext.isActive()) && (paramWebView.hasFocus())) {
      return paramContext.hideSoftInputFromWindow(paramWebView.getApplicationWindowToken(), 0);
    }
    return false;
  }
  
  public static void showInputMethod(Context paramContext, WebView paramWebView)
  {
    paramWebView.post(new InputMethodHelper.1(paramContext, paramWebView));
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.util.InputMethodHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */