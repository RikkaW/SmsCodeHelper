import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.android.mms.transaction.TransactionService;

public class oy
  extends Handler
{
  public oy(TransactionService paramTransactionService) {}
  
  public void handleMessage(Message paramMessage)
  {
    if (what == 2)
    {
      Log.i("TransactionService", "handleMessage TOAST_DOWNLOAD_LATER");
      wd.c(a, 2131492945, 2131492945);
    }
    while (what != 5) {
      return;
    }
    wd.a(a.getString(2131493204), a, 1, 2, true, 0);
  }
}

/* Location:
 * Qualified Name:     oy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */