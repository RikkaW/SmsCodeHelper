import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class aoi
  implements aoj
{
  private final String a;
  private final SQLiteOpenHelper b;
  
  public aoi(SQLiteOpenHelper paramSQLiteOpenHelper, String paramString)
  {
    b = paramSQLiteOpenHelper;
    a = paramString;
  }
  
  public int a(ContentValues paramContentValues, String paramString, String[] paramArrayOfString)
  {
    if (b == null) {
      return 0;
    }
    try
    {
      int i = b.getWritableDatabase().update(a, paramContentValues, paramString, paramArrayOfString);
      return i;
    }
    catch (Exception paramContentValues) {}
    return 0;
  }
  
  public int a(String paramString, String[] paramArrayOfString)
  {
    return b.getWritableDatabase().delete(a, paramString, paramArrayOfString);
  }
  
  public Cursor a(String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2)
  {
    if ((paramArrayOfString1 == null) || (!a.equals("num"))) {
      return b.getWritableDatabase().query(a, paramArrayOfString1, paramString1, paramArrayOfString2, null, null, paramString2);
    }
    int j = paramArrayOfString1.length;
    String[] arrayOfString = new String[j + 2];
    int i = 0;
    for (;;)
    {
      if (i >= j)
      {
        paramArrayOfString1 = new StringBuilder("(SELECT ");
        paramArrayOfString1.append("classify");
        paramArrayOfString1.append(" FROM ");
        paramArrayOfString1.append("marker");
        paramArrayOfString1.append(" WHERE ");
        paramArrayOfString1.append("marker");
        paramArrayOfString1.append(".");
        paramArrayOfString1.append("phone");
        paramArrayOfString1.append("=");
        paramArrayOfString1.append(a);
        paramArrayOfString1.append(".");
        paramArrayOfString1.append("phone");
        paramArrayOfString1.append(") AS ");
        paramArrayOfString1.append("classify");
        arrayOfString[j] = paramArrayOfString1.toString();
        paramArrayOfString1 = new StringBuilder("(SELECT ");
        paramArrayOfString1.append("name");
        paramArrayOfString1.append(" FROM ");
        paramArrayOfString1.append("corrector");
        paramArrayOfString1.append(" WHERE ");
        paramArrayOfString1.append("corrector");
        paramArrayOfString1.append(".");
        paramArrayOfString1.append("phone");
        paramArrayOfString1.append("=");
        paramArrayOfString1.append(a);
        paramArrayOfString1.append(".");
        paramArrayOfString1.append("phone");
        paramArrayOfString1.append(") AS ");
        paramArrayOfString1.append("name");
        arrayOfString[(j + 1)] = paramArrayOfString1.toString();
        return b.getWritableDatabase().query(a, arrayOfString, paramString1, paramArrayOfString2, null, null, paramString2);
      }
      arrayOfString[i] = paramArrayOfString1[i];
      i += 1;
    }
  }
  
  public boolean a(ContentValues paramContentValues)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (b != null)
    {
      bool1 = bool2;
      if (b.getWritableDatabase().replace(a, null, paramContentValues) != -1L) {
        bool1 = true;
      }
    }
    return bool1;
  }
}

/* Location:
 * Qualified Name:     aoi
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */