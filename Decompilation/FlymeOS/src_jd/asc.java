import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.ted.android.message.BubbleUtils;

public class asc
  implements DialogInterface.OnClickListener
{
  public asc(String[] paramArrayOfString, Activity paramActivity) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    paramDialogInterface = a[paramInt];
    BubbleUtils.setClipboard(b, paramDialogInterface);
    BubbleUtils.access$0(b, "已复制");
  }
}

/* Location:
 * Qualified Name:     asc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */