import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.database.sqlite.SQLiteDatabase;
import java.util.HashMap;
import java.util.Map;

public class apr
{
  private static apr c = null;
  private Map<String, SQLiteDatabase> a = new HashMap();
  private Context b = null;
  
  private apr(Context paramContext)
  {
    b = paramContext;
  }
  
  public static apr a()
  {
    return c;
  }
  
  private String a(String paramString)
  {
    return String.format(paramString, new Object[] { b.getApplicationInfo().packageName });
  }
  
  public static void a(Context paramContext)
  {
    if (c == null) {
      c = new apr(paramContext);
    }
  }
  
  private String b(String paramString1, String paramString2)
  {
    return a(paramString2) + "/" + paramString1;
  }
  
  public SQLiteDatabase a(String paramString1, String paramString2)
  {
    if ((a.get(paramString1) != null) && (((SQLiteDatabase)a.get(paramString1)).isOpen())) {
      paramString2 = (SQLiteDatabase)a.get(paramString1);
    }
    SQLiteDatabase localSQLiteDatabase;
    do
    {
      return paramString2;
      if (b == null) {
        return null;
      }
      localSQLiteDatabase = SQLiteDatabase.openDatabase(b(paramString1, paramString2), null, 16);
      paramString2 = localSQLiteDatabase;
    } while (localSQLiteDatabase == null);
    a.put(paramString1, localSQLiteDatabase);
    return localSQLiteDatabase;
  }
}

/* Location:
 * Qualified Name:     apr
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */