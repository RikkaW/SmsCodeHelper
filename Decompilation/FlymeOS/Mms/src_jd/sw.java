import android.net.Uri;
import com.android.mms.ui.ComposeMessageActivity;

public class sw
  implements aco.a
{
  public sw(ComposeMessageActivity paramComposeMessageActivity) {}
  
  public void a()
  {
    if (ComposeMessageActivity.R(a) == null) {
      return;
    }
    ComposeMessageActivity.b(a, true, 0);
  }
  
  public void a(Uri paramUri)
  {
    ComposeMessageActivity.a(a, paramUri, true, false, false);
  }
  
  public void b() {}
  
  public void b(Uri paramUri)
  {
    ComposeMessageActivity.b(a, paramUri);
  }
}

/* Location:
 * Qualified Name:     sw
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */