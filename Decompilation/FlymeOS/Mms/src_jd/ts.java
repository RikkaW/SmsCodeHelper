import android.content.ContentResolver;
import android.net.Uri;
import com.android.mms.ui.ComposeMessageActivity;
import com.android.mms.ui.ComposeMessageActivity.k;

public class ts
  implements Runnable
{
  public ts(ComposeMessageActivity.k paramk, Uri paramUri) {}
  
  public void run()
  {
    ComposeMessageActivity.g(b.a).delete(a, null, null);
  }
}

/* Location:
 * Qualified Name:     ts
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */