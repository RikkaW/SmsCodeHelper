import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.util.Log;

final class gt
  implements Runnable
{
  gt(Context paramContext, String paramString) {}
  
  public void run()
  {
    String str3 = null;
    String str1 = null;
    Cursor localCursor = a.getContentResolver().query(gr.a, gr.c, DatabaseUtils.concatenateWhere(b, "(read=0)"), null, null);
    if (localCursor != null) {
      Log.d("Mms/conv", "markAllThreadAsRead unreadthreadcursor not null, unreadthreadcursor.getCount() = " + localCursor.getCount());
    }
    for (;;)
    {
      try
      {
        if (localCursor.getCount() <= 0) {
          break label195;
        }
        bool = true;
        if ((bool) && (localCursor.moveToNext()))
        {
          str3 = localCursor.getString(0);
          if (str1 == null)
          {
            str1 = str3 + ";";
            break label192;
          }
          str1 = str1 + str3 + ";";
          break label192;
        }
        localCursor.close();
        gr.a(a, bool, str1);
        return;
      }
      finally
      {
        localCursor.close();
      }
      boolean bool = false;
      String str2 = str3;
      continue;
      label192:
      continue;
      label195:
      bool = false;
    }
  }
}

/* Location:
 * Qualified Name:     gt
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */