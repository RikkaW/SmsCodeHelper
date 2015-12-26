import android.content.ContentResolver;
import android.net.Uri;
import com.android.mms.transaction.MessagePopupService;

public class nv
  implements Runnable
{
  public nv(MessagePopupService paramMessagePopupService, Uri paramUri, String paramString, String[] paramArrayOfString) {}
  
  public void run()
  {
    d.getContentResolver().delete(a, b, c);
  }
}

/* Location:
 * Qualified Name:     nv
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */