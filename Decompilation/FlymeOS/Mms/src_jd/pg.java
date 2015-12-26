import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.android.mms.transaction.flyme.FlymeTransactionService;

public class pg
  extends Handler
{
  public pg(FlymeTransactionService paramFlymeTransactionService) {}
  
  public void handleMessage(Message paramMessage)
  {
    if (what == 2)
    {
      Log.i("FlymeTransactionService", "handleMessage TOAST_DOWNLOAD_LATER");
      wd.c(a, 2131492945, 2131492945);
    }
  }
}

/* Location:
 * Qualified Name:     pg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */