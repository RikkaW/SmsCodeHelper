import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.ted.android.message.BubbleUtils;

public class asb
  implements DialogInterface.OnClickListener
{
  public asb(String paramString, Context paramContext) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    paramDialogInterface = String.format("http://search.teddymobile.cn/v1/apk.api?package=%s", new Object[] { a });
    BubbleUtils.openUrl(b, paramDialogInterface);
  }
}

/* Location:
 * Qualified Name:     asb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */