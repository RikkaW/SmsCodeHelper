import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.android.mms.util.TimerMessageHelpler;
import com.android.mms.util.TimerMessageHelpler.a;
import com.android.mms.view.CustomTimePicker;
import java.util.Calendar;

public final class abt
  implements DialogInterface.OnClickListener
{
  public abt(CustomTimePicker paramCustomTimePicker, TimerMessageHelpler.a parama) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    paramDialogInterface = Calendar.getInstance();
    a.a(TimerMessageHelpler.a());
    paramDialogInterface.set(TimerMessageHelpler.a()[0], TimerMessageHelpler.a()[1] - 1, TimerMessageHelpler.a()[2], TimerMessageHelpler.a()[3], TimerMessageHelpler.a()[4], 0);
    long l = paramDialogInterface.getTimeInMillis();
    b.a(l);
  }
}

/* Location:
 * Qualified Name:     abt
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */