package cn.com.xy.sms.sdk.db.entity.a;

import android.content.ContentValues;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.XyCursor;

public final class j
{
  private static String e = "select_pub_time";
  private static String f = " DROP TABLE IF EXISTS select_pub_time";
  private static String g = "create table  if not exists select_pub_time ( id INTEGER PRIMARY KEY AUTOINCREMENT, num TEXT, areaCode TEXT, selectTime long default 0 )";
  public String a;
  public String b;
  private int c;
  private long d;
  
  public static long a(String paramString1, String paramString2)
  {
    Object localObject = null;
    String str = null;
    try
    {
      paramString1 = DBManager.rawQuery("SELECT * from select_pub_time where num = '" + paramString1 + "' and areaCode='" + paramString2 + "'", null);
      if (paramString1 != null)
      {
        str = paramString1;
        localObject = paramString1;
        int i = paramString1.getColumnIndex("selectTime");
        str = paramString1;
        localObject = paramString1;
        if (paramString1.moveToNext())
        {
          str = paramString1;
          localObject = paramString1;
          long l = paramString1.getLong(i);
          XyCursor.closeCursor(paramString1, true);
          return l;
        }
      }
    }
    catch (Throwable paramString1)
    {
      localObject = str;
      paramString1.printStackTrace();
      XyCursor.closeCursor(str, true);
      return 0L;
    }
    finally
    {
      XyCursor.closeCursor((XyCursor)localObject, true);
    }
    XyCursor.closeCursor(paramString1, true);
    return 0L;
  }
  
  public static ContentValues a(j paramj)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("num", a);
    localContentValues.put("areaCode", b);
    localContentValues.put("selectTime", Long.valueOf(System.currentTimeMillis()));
    return localContentValues;
  }
  
  public static void b(j paramj)
  {
    try
    {
      DBManager.insert("select_pub_time", a(paramj));
      return;
    }
    catch (Throwable paramj)
    {
      paramj.printStackTrace();
    }
  }
  
  public static int c(j paramj)
  {
    ContentValues localContentValues = a(paramj);
    try
    {
      int i = DBManager.update("select_pub_time", localContentValues, "num = ? and areaCode= ? ", new String[] { a, b });
      return i;
    }
    catch (Throwable paramj)
    {
      paramj.printStackTrace();
    }
    return -1;
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.entity.a.j
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */