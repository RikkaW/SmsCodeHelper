package android.support.v4.content;

import android.database.ContentObserver;
import android.os.Handler;

public final class Loader$ForceLoadContentObserver
  extends ContentObserver
{
  public Loader$ForceLoadContentObserver(Loader paramLoader)
  {
    super(new Handler());
  }
  
  public boolean deliverSelfNotifications()
  {
    return true;
  }
  
  public void onChange(boolean paramBoolean)
  {
    this$0.onContentChanged();
  }
}

/* Location:
 * Qualified Name:     android.support.v4.content.Loader.ForceLoadContentObserver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */