import android.net.Uri;
import android.widget.ProgressBar;
import com.android.mms.view.MessageListItem;

public class adj
  implements vv.b
{
  public adj(MessageListItem paramMessageListItem) {}
  
  public void a(vv paramvv, Uri paramUri, long paramLong1, long paramLong2)
  {
    if ((paramvv != null) && (a.M != null) && (paramvv.M() == a.M.M()))
    {
      if ((a.G != null) && (a.G.getVisibility() != 0)) {
        a.G.setVisibility(0);
      }
      MessageListItem.a(a, Math.max(paramLong2, a.M.C), paramLong1);
    }
  }
}

/* Location:
 * Qualified Name:     adj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */