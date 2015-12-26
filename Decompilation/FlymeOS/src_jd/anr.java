import android.database.Cursor;

public class anr
{
  public static void a(Cursor paramCursor)
  {
    if (paramCursor != null) {}
    try
    {
      if (!paramCursor.isClosed()) {
        paramCursor.close();
      }
      return;
    }
    catch (Exception paramCursor) {}
  }
}

/* Location:
 * Qualified Name:     anr
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */