import android.webkit.WebView;
import cn.com.xy.sms.sdk.ui.popu.web.SdkWebJavaScript;

class ek
  implements Runnable
{
  ek(ej paramej, Object[] paramArrayOfObject) {}
  
  public void run()
  {
    SdkWebJavaScript.access$000(b.b).a().loadUrl("javascript:" + b.a + "('" + a[0] + "','" + a[1] + "')");
  }
}

/* Location:
 * Qualified Name:     ek
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */