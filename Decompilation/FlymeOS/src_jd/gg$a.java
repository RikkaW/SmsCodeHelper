import android.database.Cursor;
import android.util.Log;

public class gg$a
  extends vx.a
{
  public gg$a(Cursor paramCursor)
  {
    super(paramCursor);
    try
    {
      b = paramCursor.getColumnIndexOrThrow("index_on_icc");
      return;
    }
    catch (IllegalArgumentException paramCursor)
    {
      Log.w("colsMap", paramCursor.getMessage());
    }
  }
}

/* Location:
 * Qualified Name:     gg.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */