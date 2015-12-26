import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.provider.Telephony.Mms.Rate;

public class aar
{
  private static aar a;
  private final Context b;
  private int c;
  private final BroadcastReceiver d = new aas(this);
  
  private aar(Context paramContext)
  {
    b = paramContext;
  }
  
  public static aar a(Context paramContext)
  {
    if (a == null) {
      a = new aar(paramContext);
    }
    return a;
  }
  
  public final void a()
  {
    ContentValues localContentValues = new ContentValues(1);
    localContentValues.put("sent_time", Long.valueOf(System.currentTimeMillis()));
    b.getContentResolver().insert(Telephony.Mms.Rate.CONTENT_URI, localContentValues);
  }
}

/* Location:
 * Qualified Name:     aar
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */