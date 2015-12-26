import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.database.Cursor;

final class mq$a
  extends AsyncQueryHandler
{
  public mq$a(mq parammq, ContentResolver paramContentResolver)
  {
    super(paramContentResolver);
  }
  
  protected void onQueryComplete(int paramInt, Object paramObject, Cursor paramCursor)
  {
    switch (paramInt)
    {
    default: 
      return;
    }
    mq.a(a).changeCursor(paramCursor);
  }
}

/* Location:
 * Qualified Name:     mq.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */