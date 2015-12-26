import android.content.Intent;
import com.android.mms.ui.ComposeMessageActivity;
import com.android.mms.util.TimerMessageHelpler.a;

public class sv
  implements TimerMessageHelpler.a
{
  public sv(ComposeMessageActivity paramComposeMessageActivity) {}
  
  public void a(long paramLong)
  {
    Intent localIntent = new Intent();
    localIntent.putExtra("extra_time", paramLong);
    a.onActivityResult(116, -1, localIntent);
  }
}

/* Location:
 * Qualified Name:     sv
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */