import android.database.ContentObserver;
import android.os.Handler;
import android.util.Log;

final class gn
  extends ContentObserver
{
  gn(Handler paramHandler)
  {
    super(paramHandler);
  }
  
  public void onChange(boolean paramBoolean)
  {
    if (Log.isLoggable("Mms:app", 2)) {
      gm.b("presence changed, invalidate cache");
    }
    gm.b();
  }
}

/* Location:
 * Qualified Name:     gn
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */