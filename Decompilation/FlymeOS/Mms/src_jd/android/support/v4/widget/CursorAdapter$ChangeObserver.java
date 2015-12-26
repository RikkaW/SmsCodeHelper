package android.support.v4.widget;

import android.database.ContentObserver;
import android.os.Handler;

class CursorAdapter$ChangeObserver
  extends ContentObserver
{
  public CursorAdapter$ChangeObserver(CursorAdapter paramCursorAdapter)
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
 * Qualified Name:     android.support.v4.widget.CursorAdapter.ChangeObserver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */