import com.ted.android.contacts.bubble.SmsCoreEngine;
import com.ted.android.core.SmsParserEngine;
import com.ted.android.core.SmsParserEngine.OnInitialiseListener;

public class aqi
  implements Runnable
{
  public aqi(SmsParserEngine paramSmsParserEngine) {}
  
  public void run()
  {
    SmsCoreEngine.init(SmsParserEngine.a(a));
    if (SmsParserEngine.b(a) != null) {
      SmsParserEngine.b(a).onCompleted();
    }
    asf.a().a(SmsParserEngine.a(a));
  }
}

/* Location:
 * Qualified Name:     aqi
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */