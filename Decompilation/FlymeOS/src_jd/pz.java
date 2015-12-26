import android.os.Handler;
import android.os.Message;
import android.telephony.SmsMessage;
import com.android.mms.ui.ClassZeroActivity;
import java.util.ArrayList;

public class pz
  extends Handler
{
  public pz(ClassZeroActivity paramClassZeroActivity) {}
  
  public void handleMessage(Message paramMessage)
  {
    if ((what != 2) || (!ClassZeroActivity.a(a, a.getIntent()))) {}
    while (ClassZeroActivity.a(a).size() != 1) {
      return;
    }
    ClassZeroActivity.a(a, (SmsMessage)ClassZeroActivity.a(a).get(0));
  }
}

/* Location:
 * Qualified Name:     pz
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */