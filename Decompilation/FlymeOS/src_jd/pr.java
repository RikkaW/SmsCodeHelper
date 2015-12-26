import android.os.Handler;
import android.os.Message;
import com.android.mms.transaction.sns.SnsTransactionService;

public class pr
  extends Handler
{
  public pr(SnsTransactionService paramSnsTransactionService) {}
  
  public void handleMessage(Message paramMessage)
  {
    String str = null;
    if (what == 1) {
      str = (String)obj;
    }
    if (str != null) {
      wd.a(str, a, 1, 2, true, 0);
    }
  }
}

/* Location:
 * Qualified Name:     pr
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */