import android.net.Uri;
import android.os.Parcelable;
import com.android.mms.ui.ComposeMessageActivity;
import java.util.ArrayList;

public class rl
  implements Runnable
{
  public rl(ComposeMessageActivity paramComposeMessageActivity, int paramInt, ArrayList paramArrayList, String paramString) {}
  
  public void run()
  {
    int i = 0;
    while (i < a)
    {
      Parcelable localParcelable = (Parcelable)b.get(i);
      ComposeMessageActivity.a(d, c, (Uri)localParcelable, true);
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     rl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */