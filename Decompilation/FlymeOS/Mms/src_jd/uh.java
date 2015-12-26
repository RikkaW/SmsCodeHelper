import android.os.Handler;
import android.os.Message;
import com.android.mms.ui.DialogFromNotifyActivity;

public class uh
  extends Handler
{
  public uh(DialogFromNotifyActivity paramDialogFromNotifyActivity) {}
  
  public void handleMessage(Message paramMessage)
  {
    if (what == 2) {
      DialogFromNotifyActivity.a(a, null);
    }
  }
}

/* Location:
 * Qualified Name:     uh
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */