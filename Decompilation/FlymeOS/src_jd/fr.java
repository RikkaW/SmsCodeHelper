import android.database.ContentObserver;
import android.os.Handler;
import com.android.mms.MmsApp;

public class fr
  extends ContentObserver
{
  public fr(MmsApp paramMmsApp, Handler paramHandler)
  {
    super(paramHandler);
  }
  
  public void onChange(boolean paramBoolean)
  {
    super.onChange(paramBoolean);
    MmsApp.a(a, paramBoolean);
  }
}

/* Location:
 * Qualified Name:     fr
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */