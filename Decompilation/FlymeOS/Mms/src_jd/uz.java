import android.os.Message;
import com.android.mms.ui.MeizuSearchActivity;
import com.android.mms.ui.MeizuSearchActivity.a;

public class uz
  implements Runnable
{
  public uz(MeizuSearchActivity paramMeizuSearchActivity) {}
  
  public void run()
  {
    Message localMessage = new Message();
    what = 291;
    MeizuSearchActivity.d(a).sendMessage(localMessage);
  }
}

/* Location:
 * Qualified Name:     uz
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */