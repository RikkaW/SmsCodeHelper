import android.os.Message;
import com.android.mms.ui.MeizuSearchActivity;
import com.android.mms.ui.MeizuSearchActivity.a;
import com.android.mms.ui.MeizuSearchActivity.c;

public class vl
  implements Runnable
{
  public vl(MeizuSearchActivity paramMeizuSearchActivity) {}
  
  public void run()
  {
    MeizuSearchActivity.c(a, true);
    MeizuSearchActivity.z(a);
    MeizuSearchActivity.s(a).a(MeizuSearchActivity.p(a));
    MeizuSearchActivity.s(a).a(false);
    Message localMessage = new Message();
    what = 290;
    MeizuSearchActivity.d(a).sendMessage(localMessage);
  }
}

/* Location:
 * Qualified Name:     vl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */