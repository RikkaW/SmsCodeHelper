import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;
import com.ted.android.contacts.netparser.model.NumItem;
import com.ted.android.contacts.netparser.model.NumItem.MarkerData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class aoh
  extends SQLiteOpenHelper
{
  private static final String a = aoh.class.getSimpleName();
  private static aoh b = null;
  private static final String[] c = { "json", "m_t", "c_t", "phone" };
  private static final String d;
  private static final String e;
  private static final String f;
  private static final String g;
  private static final String h;
  private Context i;
  private aoj j = new aoi(this, "num");
  private aoj k = new aoi(this, "marker");
  private aoj l = new aoi(this, "m_mark");
  private aoj m = new aoi(this, "corrector");
  private aoj n = new aoi(this, "spam");
  
  static
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("CREATE TABLE IF NOT EXISTS ");
    localStringBuilder.append("num");
    localStringBuilder.append(" (");
    localStringBuilder.append("phone");
    localStringBuilder.append(" VARCHAR(20) PRIMARY KEY, ");
    localStringBuilder.append("json");
    localStringBuilder.append(" VARCHAR(1024), ");
    localStringBuilder.append("m_t");
    localStringBuilder.append(" INTEGER, ");
    localStringBuilder.append("c_t");
    localStringBuilder.append(" INTEGER);");
    d = localStringBuilder.toString();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("CREATE TABLE IF NOT EXISTS ");
    localStringBuilder.append("marker");
    localStringBuilder.append(" (");
    localStringBuilder.append("phone");
    localStringBuilder.append(" VARCHAR(20) PRIMARY KEY, ");
    localStringBuilder.append("classify");
    localStringBuilder.append(" TEXT, ");
    localStringBuilder.append("is_manual");
    localStringBuilder.append(" INTEGER, ");
    localStringBuilder.append("data_type");
    localStringBuilder.append(" INTEGER, ");
    localStringBuilder.append("operation_type");
    localStringBuilder.append(" INTEGER, ");
    localStringBuilder.append("uploaded");
    localStringBuilder.append(" INTEGER);");
    e = localStringBuilder.toString();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("CREATE TABLE IF NOT EXISTS ");
    localStringBuilder.append("m_mark");
    localStringBuilder.append(" (");
    localStringBuilder.append("classify");
    localStringBuilder.append(" TEXT PRIMARY KEY);");
    f = localStringBuilder.toString();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("CREATE TABLE IF NOT EXISTS ");
    localStringBuilder.append("corrector");
    localStringBuilder.append(" (");
    localStringBuilder.append("phone");
    localStringBuilder.append(" VARCHAR(20) PRIMARY KEY, ");
    localStringBuilder.append("name");
    localStringBuilder.append(" TEXT, ");
    localStringBuilder.append("old_name");
    localStringBuilder.append(" TEXT, ");
    localStringBuilder.append("remark");
    localStringBuilder.append(" TEXT, ");
    localStringBuilder.append("contact_info");
    localStringBuilder.append(" TEXT, ");
    localStringBuilder.append("uploaded");
    localStringBuilder.append(" INTEGER);");
    g = localStringBuilder.toString();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("CREATE TABLE IF NOT EXISTS ");
    localStringBuilder.append("spam");
    localStringBuilder.append(" (");
    localStringBuilder.append("id");
    localStringBuilder.append(" VARCHAR(20) PRIMARY KEY, ");
    localStringBuilder.append("json");
    localStringBuilder.append(" TEXT, ");
    localStringBuilder.append("uploaded");
    localStringBuilder.append(" INTEGER);");
    h = localStringBuilder.toString();
  }
  
  private aoh(Context paramContext)
  {
    super(paramContext, "numcache.db", null, 3);
    i = paramContext;
  }
  
  public static aoh a(Context paramContext)
  {
    try
    {
      if (b == null) {
        b = new aoh(paramContext);
      }
      paramContext = b;
      return paramContext;
    }
    finally {}
  }
  
  private NumItem a(Cursor paramCursor)
  {
    Object localObject = paramCursor.getString(0);
    int i1 = paramCursor.getInt(1);
    int i2 = paramCursor.getInt(2);
    String str1 = paramCursor.getString(4);
    String str2 = paramCursor.getString(5);
    if (!TextUtils.isEmpty((CharSequence)localObject))
    {
      String str3 = apm.b((String)localObject);
      NumItem localNumItem = apj.a(str3);
      localObject = localNumItem;
      if (localNumItem == null)
      {
        Log.e("json", "parse error, number: " + paramCursor.getString(3) + ", json : " + str3);
        localObject = localNumItem;
      }
    }
    for (;;)
    {
      if (localObject != null)
      {
        if (!TextUtils.isEmpty(str1))
        {
          paramCursor = new NumItem.MarkerData(0, str1, apn.a(str1));
          f = true;
          d = "无标记".equals(str1);
          ((NumItem)localObject).a(paramCursor);
        }
        if (!TextUtils.isEmpty(str2)) {
          ((NumItem)localObject).d(str2);
        }
        ((NumItem)localObject).b(i1);
        ((NumItem)localObject).c(i2);
      }
      return (NumItem)localObject;
      localObject = new NumItem();
      ((NumItem)localObject).c(paramCursor.getString(3));
    }
  }
  
  private boolean h(String paramString)
  {
    if ((l == null) || (apn.a(paramString) != Integer.MIN_VALUE)) {
      return false;
    }
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("classify", paramString);
    return l.a(localContentValues);
  }
  
  public int a(ContentValues paramContentValues, String paramString, String[] paramArrayOfString)
  {
    if (j != null) {
      return j.a(paramContentValues, paramString, paramArrayOfString);
    }
    return 0;
  }
  
  public Cursor a()
  {
    return getReadableDatabase().query("marker", new String[] { "phone", "classify", "data_type", "operation_type", "uploaded" }, "uploaded=0", null, null, null, null);
  }
  
  public NumItem a(String paramString)
  {
    Object localObject2 = null;
    Object localObject1;
    String str;
    if (j != null)
    {
      localObject1 = c;
      str = "phone=? " + " LIMIT 1";
    }
    for (paramString = j.a((String[])localObject1, str, new String[] { paramString }, null);; paramString = null)
    {
      localObject1 = localObject2;
      if (paramString != null)
      {
        localObject1 = localObject2;
        if (paramString.moveToFirst()) {
          localObject1 = a(paramString);
        }
      }
      anr.a(paramString);
      if ((paramString != null) && (!paramString.isClosed())) {
        paramString.close();
      }
      return (NumItem)localObject1;
    }
  }
  
  public List<NumItem> a(List<String> paramList)
  {
    localObject = null;
    if ((paramList == null) || (paramList.size() == 0)) {
      return null;
    }
    ArrayList localArrayList = new ArrayList(paramList.size());
    StringBuilder localStringBuilder;
    if (j != null)
    {
      localObject = c;
      localStringBuilder = new StringBuilder("phone");
      localStringBuilder.append(" in(");
      paramList = paramList.iterator();
    }
    for (;;)
    {
      if (!paramList.hasNext())
      {
        localStringBuilder.replace(localStringBuilder.length() - 1, localStringBuilder.length(), "");
        localStringBuilder.append(")");
        localStringBuilder.append(" and m_t!=");
        localStringBuilder.append(-1);
        localObject = j.a((String[])localObject, localStringBuilder.toString(), null, null);
        if (localObject == null) {}
      }
      try
      {
        if (((Cursor)localObject).moveToFirst())
        {
          boolean bool;
          do
          {
            localArrayList.add(a((Cursor)localObject));
            bool = ((Cursor)localObject).moveToNext();
          } while (bool);
        }
      }
      catch (Exception paramList)
      {
        for (;;)
        {
          String str;
          Log.e(a, "getNum():读取号码识别列表数据失败！");
          anr.a((Cursor)localObject);
        }
      }
      finally
      {
        anr.a((Cursor)localObject);
      }
      return localArrayList;
      str = (String)paramList.next();
      localStringBuilder.append("'");
      localStringBuilder.append(str);
      localStringBuilder.append("',");
    }
  }
  
  public void a(aoj paramaoj)
  {
    j = paramaoj;
  }
  
  public boolean a(String paramString1, String paramString2, int paramInt)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("phone", paramString1);
    localContentValues.put("json", paramString2);
    localContentValues.put("m_t", Integer.valueOf(paramInt));
    localContentValues.put("c_t", Integer.valueOf((int)(System.currentTimeMillis() / 1000L)));
    if (j != null) {
      return j.a(localContentValues);
    }
    return false;
  }
  
  public boolean a(String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    int i1 = 1;
    h(paramString2);
    if (k == null) {
      return false;
    }
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("phone", paramString1);
    localContentValues.put("classify", paramString2);
    if (apn.a(paramString2) == Integer.MIN_VALUE)
    {
      localContentValues.put("is_manual", Integer.valueOf(1));
      localContentValues.put("data_type", Integer.valueOf(paramInt1));
      localContentValues.put("operation_type", Integer.valueOf(paramInt2));
      if (!"无标记".equals(paramString2)) {
        break label135;
      }
    }
    label135:
    for (paramInt1 = i1;; paramInt1 = 0)
    {
      localContentValues.put("uploaded", Integer.valueOf(paramInt1));
      return k.a(localContentValues);
      localContentValues.put("is_manual", Integer.valueOf(0));
      break;
    }
  }
  
  public boolean a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    if (m == null) {
      return false;
    }
    String str;
    if (!TextUtils.isEmpty(paramString2))
    {
      str = paramString2;
      if (paramString2.trim().length() != 0) {}
    }
    else
    {
      str = paramString3;
    }
    paramString2 = new ContentValues();
    paramString2.put("phone", paramString1);
    paramString2.put("name", str);
    paramString2.put("old_name", paramString3);
    paramString2.put("remark", paramString4);
    paramString2.put("uploaded", Integer.valueOf(0));
    paramString2.put("contact_info", paramString5);
    return m.a(paramString2);
  }
  
  public Cursor b()
  {
    return getReadableDatabase().query("corrector", new String[] { "phone", "name", "remark", "uploaded", "contact_info" }, "uploaded=0", null, null, null, null);
  }
  
  public void b(aoj paramaoj)
  {
    k = paramaoj;
  }
  
  public void b(String paramString)
  {
    l.a("classify=?", new String[] { paramString });
  }
  
  public int c(String paramString)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("json", "");
    localContentValues.put("m_t", Integer.valueOf(0));
    localContentValues.put("c_t", Integer.valueOf((int)(System.currentTimeMillis() / 1000L)));
    for (;;)
    {
      try
      {
        if (j != null)
        {
          i1 = j.a(localContentValues, "phone=? and m_t=-1", new String[] { paramString });
          i2 = i1;
          if (i1 == 0)
          {
            localContentValues = new ContentValues();
            localContentValues.put("c_t", Integer.valueOf((int)(System.currentTimeMillis() / 1000L)));
            i2 = i1;
          }
        }
      }
      catch (Exception localException)
      {
        try
        {
          int i2;
          if (j != null) {
            i2 = j.a(localContentValues, "phone=? ", new String[] { paramString });
          }
          return i2;
        }
        catch (Exception paramString)
        {
          return i1;
        }
        localException = localException;
        i1 = 0;
        continue;
      }
      int i1 = 0;
    }
  }
  
  public List<String> c()
  {
    Object localObject3 = null;
    Object localObject1 = null;
    ArrayList localArrayList = new ArrayList();
    if (l == null) {
      return localArrayList;
    }
    try
    {
      Cursor localCursor = l.a(new String[] { "classify" }, null, null, null);
      if (localCursor != null)
      {
        localObject1 = localCursor;
        localObject3 = localCursor;
        if (localCursor.moveToFirst())
        {
          boolean bool;
          do
          {
            localObject1 = localCursor;
            localObject3 = localCursor;
            String str = localCursor.getString(0);
            localObject1 = localCursor;
            localObject3 = localCursor;
            if (!TextUtils.isEmpty(str))
            {
              localObject1 = localCursor;
              localObject3 = localCursor;
              localArrayList.add(str);
            }
            localObject1 = localCursor;
            localObject3 = localCursor;
            bool = localCursor.moveToNext();
          } while (bool);
        }
      }
      anr.a(localCursor);
      return localArrayList;
    }
    catch (Exception localException)
    {
      localObject4 = localObject1;
      Log.e(a, "getAllManualClassifies() 获取手动输入标记失败");
      return localArrayList;
    }
    finally
    {
      Object localObject4;
      anr.a((Cursor)localObject4);
    }
  }
  
  public void c(aoj paramaoj)
  {
    l = paramaoj;
  }
  
  public int d(String paramString)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("json", "");
    localContentValues.put("m_t", Integer.valueOf(0));
    localContentValues.put("c_t", Integer.valueOf((int)(System.currentTimeMillis() / 1000L)));
    for (;;)
    {
      try
      {
        if (j != null)
        {
          i1 = j.a(localContentValues, "phone=? ", new String[] { paramString });
          i2 = i1;
          if (i1 == 0)
          {
            localContentValues = new ContentValues();
            localContentValues.put("c_t", Integer.valueOf((int)(System.currentTimeMillis() / 1000L)));
            i2 = i1;
          }
        }
      }
      catch (Exception localException)
      {
        try
        {
          int i2;
          if (j != null) {
            i2 = j.a(localContentValues, "phone=? ", new String[] { paramString });
          }
          return i2;
        }
        catch (Exception paramString)
        {
          return i1;
        }
        localException = localException;
        i1 = 0;
        continue;
      }
      int i1 = 0;
    }
  }
  
  public void d(aoj paramaoj)
  {
    m = paramaoj;
  }
  
  public boolean e(String paramString)
  {
    return a(paramString, "", -1);
  }
  
  public int f(String paramString)
  {
    if (k == null) {
      return 0;
    }
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("uploaded", Integer.valueOf(1));
    return k.a(localContentValues, "phone=?", new String[] { paramString });
  }
  
  public int g(String paramString)
  {
    if (m == null) {
      return 0;
    }
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("uploaded", Integer.valueOf(1));
    return m.a(localContentValues, "phone=?", new String[] { paramString });
  }
  
  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    paramSQLiteDatabase.execSQL(d);
    paramSQLiteDatabase.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS i_n_p on num (phone);");
    paramSQLiteDatabase.execSQL(e);
    paramSQLiteDatabase.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS i_m_p on marker (phone);");
    paramSQLiteDatabase.execSQL(f);
    paramSQLiteDatabase.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS i_m_c on m_mark (classify);");
    paramSQLiteDatabase.execSQL(g);
    paramSQLiteDatabase.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS i_c_p on corrector (phone);");
    paramSQLiteDatabase.execSQL(h);
  }
  
  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    if (paramInt2 <= 1)
    {
      return;
      String str = String.format("nc%d.sql", new Object[] { Integer.valueOf(paramInt1) });
      aok.a(paramSQLiteDatabase, i, str);
      switch (paramInt1)
      {
      }
    }
    for (;;)
    {
      paramInt1 += 1;
      if (paramInt1 <= paramInt2) {
        break;
      }
      return;
      paramSQLiteDatabase.execSQL(e);
      paramSQLiteDatabase.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS i_m_p on marker (phone);");
      paramSQLiteDatabase.execSQL(f);
      paramSQLiteDatabase.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS i_m_c on m_mark (classify);");
      paramSQLiteDatabase.execSQL(g);
      paramSQLiteDatabase.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS i_c_p on corrector (phone);");
      continue;
      paramSQLiteDatabase.execSQL(h);
    }
  }
}

/* Location:
 * Qualified Name:     aoh
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */