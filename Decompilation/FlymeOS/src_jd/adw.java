import android.net.Uri;
import android.os.Handler;
import com.android.mms.view.MessageListItemTalk;

public class adw
  implements ach.a
{
  public adw(MessageListItemTalk paramMessageListItemTalk) {}
  
  public void a(Uri paramUri)
  {
    if ((a.M != null) && (paramUri != null) && (paramUri.equals(a.M.t)))
    {
      MessageListItemTalk.a(true);
      MessageListItemTalk.a(a, true);
      if (a.b == null) {
        a.b = new Handler();
      }
    }
  }
  
  public void b(Uri paramUri)
  {
    if ((a.M != null) && (paramUri != null) && (paramUri.equals(a.M.t)))
    {
      MessageListItemTalk.a(false);
      MessageListItemTalk.a(a, false);
    }
  }
  
  public void c(Uri paramUri)
  {
    if ((a.M != null) && (paramUri != null) && (paramUri.equals(a.M.t)))
    {
      MessageListItemTalk.a(false);
      MessageListItemTalk.a(a, false);
      if (a.b != null)
      {
        a.b.removeCallbacks(a.c);
        a.b = null;
      }
    }
  }
}

/* Location:
 * Qualified Name:     adw
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */