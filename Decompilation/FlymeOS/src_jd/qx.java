import android.content.Intent;
import com.android.mms.ui.ComposeMessageActivity;
import com.android.mms.ui.SlideshowEditActivity;

public class qx
  implements Runnable
{
  public qx(ComposeMessageActivity paramComposeMessageActivity) {}
  
  public void run()
  {
    if (ComposeMessageActivity.a(a) == null) {
      return;
    }
    Intent localIntent = new Intent(a, SlideshowEditActivity.class);
    localIntent.setData(ComposeMessageActivity.a(a));
    a.startActivityForResult(localIntent, 106);
  }
}

/* Location:
 * Qualified Name:     qx
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */