package sdk.meizu.traffic.hybird;

import android.text.TextUtils;
import android.webkit.WebView;

public class JsCmd
{
  private static final String JS_COMMAND_PREFIX = "javascript:";
  private String className;
  private String[] methodArgs;
  private String methodName;
  
  private JsCmd(String paramString)
  {
    className = paramString;
  }
  
  public static void execute(WebView paramWebView, String paramString)
  {
    paramWebView.loadUrl("javascript:" + paramString);
  }
  
  public static JsCmd from(String paramString)
  {
    return new JsCmd(paramString);
  }
  
  public void execute(WebView paramWebView)
  {
    paramWebView.loadUrl(toString());
  }
  
  public JsCmd setMethod(String paramString)
  {
    methodName = paramString;
    return this;
  }
  
  public JsCmd setMethodArgs(String... paramVarArgs)
  {
    methodArgs = paramVarArgs;
    return this;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("javascript:");
    if (!TextUtils.isEmpty(className)) {
      localStringBuilder.append(className + ".");
    }
    if (!TextUtils.isEmpty(methodName))
    {
      localStringBuilder.append(methodName + "(");
      if (methodArgs != null)
      {
        int j = methodArgs.length;
        int i = 0;
        while (i < j)
        {
          localStringBuilder.append(methodArgs[i]);
          if (i < j - 1) {
            localStringBuilder.append(",");
          }
          i += 1;
        }
      }
      localStringBuilder.append(")");
      return localStringBuilder.toString();
    }
    throw new IllegalArgumentException("js method required!");
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.hybird.JsCmd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */