import android.content.Intent;
import android.net.Uri;
import android.webkit.DownloadListener;
import cn.com.xy.sms.sdk.ui.popu.web.SdkWebActivity;

public class ed
  implements DownloadListener
{
  public ed(SdkWebActivity paramSdkWebActivity) {}
  
  public void onDownloadStart(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong)
  {
    paramString1 = new Intent("android.intent.action.VIEW", Uri.parse(paramString1));
    a.startActivity(paramString1);
  }
}

/* Location:
 * Qualified Name:     ed
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */