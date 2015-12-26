import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.ted.android.data.SmsEntity;
import org.json.JSONObject;

public class bo
  extends SQLiteOpenHelper
{
  private static final String a = bo.class.getSimpleName();
  private static bo b = null;
  private static final String[] d = { "msg_index", "_id", "json_string", "version" };
  private static final String e;
  private static final String f = "DROP TABLE IF EXISTS entity_cache";
  private Context c;
  
  static
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("CREATE TABLE IF NOT EXISTS ");
    localStringBuilder.append("entity_cache");
    localStringBuilder.append(" (");
    localStringBuilder.append("msg_index");
    localStringBuilder.append(" LONG PRIMARY KEY, ");
    localStringBuilder.append("_id");
    localStringBuilder.append(" VARCHAR(50), ");
    localStringBuilder.append("json_string");
    localStringBuilder.append(" VARCHAR(1024),");
    localStringBuilder.append("version");
    localStringBuilder.append(" INTEGER );");
    e = localStringBuilder.toString();
  }
  
  private bo(Context paramContext)
  {
    super(paramContext, "entity_cache.db", null, 2);
    c = paramContext;
  }
  
  public static bo a(Context paramContext)
  {
    try
    {
      if (b == null) {
        b = new bo(paramContext);
      }
      paramContext = b;
      return paramContext;
    }
    finally {}
  }
  
  private SmsEntity a(Cursor paramCursor)
  {
    if (paramCursor != null) {
      return SmsEntity.fromJSON(paramCursor.getString(2));
    }
    return null;
  }
  
  private ContentValues b(SmsEntity paramSmsEntity)
  {
    if (paramSmsEntity != null)
    {
      ContentValues localContentValues = new ContentValues(9);
      localContentValues.put("msg_index", paramSmsEntity.getMsgId());
      localContentValues.put("_id", paramSmsEntity.getMsgId());
      localContentValues.put("json_string", paramSmsEntity.toJSON().toString());
      localContentValues.put("version", Integer.valueOf(9));
      return localContentValues;
    }
    return null;
  }
  
  public SmsEntity a(long paramLong)
  {
    localObject3 = null;
    localObject4 = null;
    Object localObject1 = d;
    localObject5 = "msg_index=? " + " LIMIT 1";
    localObject5 = getReadableDatabase().query("entity_cache", (String[])localObject1, (String)localObject5, new String[] { String.valueOf(paramLong) }, null, null, null);
    localObject1 = localObject3;
    if (localObject5 != null) {
      localObject1 = localObject3;
    }
    label144:
    label151:
    do
    {
      do
      {
        try
        {
          if (!((Cursor)localObject5).moveToFirst()) {
            break label151;
          }
          if (((Cursor)localObject5).getInt(3) >= 9) {
            break label144;
          }
          b(((Cursor)localObject5).getLong(0));
          localObject3 = localObject4;
          if (localObject5 != null)
          {
            localObject3 = localObject4;
            if (!((Cursor)localObject5).isClosed())
            {
              ((Cursor)localObject5).close();
              localObject3 = localObject4;
            }
          }
        }
        catch (SQLiteException localSQLiteException)
        {
          do
          {
            do
            {
              localSQLiteException.printStackTrace();
              localObject3 = localObject4;
            } while (localObject5 == null);
            localObject3 = localObject4;
          } while (((Cursor)localObject5).isClosed());
          ((Cursor)localObject5).close();
          return null;
        }
        finally
        {
          if ((localObject5 == null) || (((Cursor)localObject5).isClosed())) {
            break;
          }
          ((Cursor)localObject5).close();
        }
        return (SmsEntity)localObject3;
        localObject1 = a((Cursor)localObject5);
        localObject3 = localObject1;
      } while (localObject5 == null);
      localObject3 = localObject1;
    } while (((Cursor)localObject5).isClosed());
    ((Cursor)localObject5).close();
    return (SmsEntity)localObject1;
  }
  
  public boolean a(SmsEntity paramSmsEntity)
  {
    if (paramSmsEntity != null)
    {
      paramSmsEntity = b(paramSmsEntity);
      if (getWritableDatabase().insert("entity_cache", null, paramSmsEntity) > 0L) {
        return true;
      }
    }
    return false;
  }
  
  public boolean b(long paramLong)
  {
    return getWritableDatabase().delete("entity_cache", "msg_index=? ", new String[] { String.valueOf(paramLong) }) > 0;
  }
  
  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    paramSQLiteDatabase.execSQL(e);
  }
  
  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    if (paramInt2 <= 1) {}
    while (paramInt2 <= paramInt1) {
      return;
    }
    paramSQLiteDatabase.execSQL(f);
    paramSQLiteDatabase.execSQL(e);
  }
}

/* Location:
 * Qualified Name:     bo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */