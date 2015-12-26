import android.webkit.WebView;
import cn.com.xy.sms.sdk.ui.popu.web.SdkWebJavaScript;

class em
  implements Runnable
{
  em(el paramel, Object[] paramArrayOfObject) {}
  
  public void run()
  {
    SdkWebJavaScript.access$000(b.b).a().loadUrl("javascript:" + b.a + "('" + (String)a[0] + "','" + (String)a[1] + "')");
  }
}

/* Location:
 * Qualified Name:     em
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */