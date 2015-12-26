import android.webkit.WebView;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.ui.popu.web.SdkWebJavaScript;

public class el
  implements XyCallBack
{
  public el(SdkWebJavaScript paramSdkWebJavaScript, String paramString) {}
  
  public void execute(Object... paramVarArgs)
  {
    SdkWebJavaScript.access$000(b).a().post(new em(this, paramVarArgs));
  }
}

/* Location:
 * Qualified Name:     el
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */