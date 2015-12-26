import android.content.Context;
import android.text.format.DateFormat;
import android.widget.TextView;
import com.android.mms.util.TimerMessageHelpler;
import com.android.mms.view.CustomTimePicker;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class abr
  implements Runnable
{
  public abr(CustomTimePicker paramCustomTimePicker, Context paramContext, String paramString1, String paramString2, TextView paramTextView) {}
  
  public void run()
  {
    Calendar localCalendar = Calendar.getInstance();
    a.a(TimerMessageHelpler.a());
    localCalendar.set(TimerMessageHelpler.a()[0], TimerMessageHelpler.a()[1] - 1, TimerMessageHelpler.a()[2], TimerMessageHelpler.a()[3], TimerMessageHelpler.a()[4], 0);
    if (DateFormat.is24HourFormat(b)) {}
    for (Object localObject = c;; localObject = d)
    {
      localObject = new SimpleDateFormat((String)localObject);
      e.setText(((SimpleDateFormat)localObject).format(localCalendar.getTime()));
      return;
    }
  }
}

/* Location:
 * Qualified Name:     abr
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */