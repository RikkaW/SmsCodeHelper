package cn.com.xy.sms.sdk.db;

import android.content.ContentResolver;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

public class XyCursor
{
  private SQLiteDatabase a = null;
  private Cursor b = null;
  private int c = 0;
  
  public XyCursor(SQLiteDatabase paramSQLiteDatabase, Cursor paramCursor)
  {
    a = paramSQLiteDatabase;
    b = paramCursor;
  }
  
  public XyCursor(SQLiteDatabase paramSQLiteDatabase, Cursor paramCursor, int paramInt)
  {
    a = paramSQLiteDatabase;
    b = paramCursor;
    c = paramInt;
  }
  
  /* Error */
  private void a(boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 19	cn/com/xy/sms/sdk/db/XyCursor:b	Landroid/database/Cursor;
    //   4: ifnull +12 -> 16
    //   7: aload_0
    //   8: getfield 19	cn/com/xy/sms/sdk/db/XyCursor:b	Landroid/database/Cursor;
    //   11: invokeinterface 31 1 0
    //   16: iload_1
    //   17: ifeq +47 -> 64
    //   20: aload_0
    //   21: getfield 17	cn/com/xy/sms/sdk/db/XyCursor:a	Landroid/database/sqlite/SQLiteDatabase;
    //   24: ifnull +40 -> 64
    //   27: aload_0
    //   28: getfield 21	cn/com/xy/sms/sdk/db/XyCursor:c	I
    //   31: iconst_1
    //   32: if_icmpne +16 -> 48
    //   35: aload_0
    //   36: getfield 17	cn/com/xy/sms/sdk/db/XyCursor:a	Landroid/database/sqlite/SQLiteDatabase;
    //   39: invokestatic 36	cn/com/xy/sms/sdk/db/a/a:a	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   42: aload_0
    //   43: aconst_null
    //   44: putfield 17	cn/com/xy/sms/sdk/db/XyCursor:a	Landroid/database/sqlite/SQLiteDatabase;
    //   47: return
    //   48: aload_0
    //   49: getfield 17	cn/com/xy/sms/sdk/db/XyCursor:a	Landroid/database/sqlite/SQLiteDatabase;
    //   52: invokestatic 40	cn/com/xy/sms/sdk/db/DBManager:close	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   55: goto -13 -> 42
    //   58: astore_2
    //   59: return
    //   60: astore_2
    //   61: goto -45 -> 16
    //   64: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	65	0	this	XyCursor
    //   0	65	1	paramBoolean	boolean
    //   58	1	2	localThrowable1	Throwable
    //   60	1	2	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   20	42	58	java/lang/Throwable
    //   42	47	58	java/lang/Throwable
    //   48	55	58	java/lang/Throwable
    //   0	16	60	java/lang/Throwable
  }
  
  /* Error */
  public static void closeCursor(XyCursor paramXyCursor, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +67 -> 68
    //   4: aload_0
    //   5: getfield 19	cn/com/xy/sms/sdk/db/XyCursor:b	Landroid/database/Cursor;
    //   8: ifnull +12 -> 20
    //   11: aload_0
    //   12: getfield 19	cn/com/xy/sms/sdk/db/XyCursor:b	Landroid/database/Cursor;
    //   15: invokeinterface 31 1 0
    //   20: iload_1
    //   21: ifeq +47 -> 68
    //   24: aload_0
    //   25: getfield 17	cn/com/xy/sms/sdk/db/XyCursor:a	Landroid/database/sqlite/SQLiteDatabase;
    //   28: ifnull +40 -> 68
    //   31: aload_0
    //   32: getfield 21	cn/com/xy/sms/sdk/db/XyCursor:c	I
    //   35: iconst_1
    //   36: if_icmpne +16 -> 52
    //   39: aload_0
    //   40: getfield 17	cn/com/xy/sms/sdk/db/XyCursor:a	Landroid/database/sqlite/SQLiteDatabase;
    //   43: invokestatic 36	cn/com/xy/sms/sdk/db/a/a:a	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   46: aload_0
    //   47: aconst_null
    //   48: putfield 17	cn/com/xy/sms/sdk/db/XyCursor:a	Landroid/database/sqlite/SQLiteDatabase;
    //   51: return
    //   52: aload_0
    //   53: getfield 17	cn/com/xy/sms/sdk/db/XyCursor:a	Landroid/database/sqlite/SQLiteDatabase;
    //   56: invokestatic 40	cn/com/xy/sms/sdk/db/DBManager:close	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   59: goto -13 -> 46
    //   62: astore_0
    //   63: return
    //   64: astore_2
    //   65: goto -45 -> 20
    //   68: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	69	0	paramXyCursor	XyCursor
    //   0	69	1	paramBoolean	boolean
    //   64	1	2	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   24	46	62	java/lang/Throwable
    //   46	51	62	java/lang/Throwable
    //   52	59	62	java/lang/Throwable
    //   4	20	64	java/lang/Throwable
  }
  
  public void copyStringToBuffer(int paramInt, CharArrayBuffer paramCharArrayBuffer)
  {
    if (b != null) {
      b.copyStringToBuffer(paramInt, paramCharArrayBuffer);
    }
  }
  
  public void deactivate()
  {
    if (b != null) {
      b.deactivate();
    }
  }
  
  public byte[] getBlob(int paramInt)
  {
    if (b != null) {
      return b.getBlob(paramInt);
    }
    return null;
  }
  
  public int getColumnCount()
  {
    if (b != null) {
      return b.getColumnCount();
    }
    return 0;
  }
  
  public int getColumnIndex(String paramString)
  {
    if (b != null) {
      return b.getColumnIndex(paramString);
    }
    return -1;
  }
  
  public int getColumnIndexOrThrow(String paramString)
  {
    if (b != null) {
      return b.getColumnIndexOrThrow(paramString);
    }
    return -1;
  }
  
  public String getColumnName(int paramInt)
  {
    if (b != null) {
      return b.getColumnName(paramInt);
    }
    return null;
  }
  
  public String[] getColumnNames()
  {
    if (b != null) {
      return b.getColumnNames();
    }
    return null;
  }
  
  public int getCount()
  {
    if (b != null) {
      return b.getCount();
    }
    return 0;
  }
  
  public Cursor getCur()
  {
    return b;
  }
  
  public double getDouble(int paramInt)
  {
    if (b != null) {
      return b.getDouble(paramInt);
    }
    return 0.0D;
  }
  
  public Bundle getExtras()
  {
    if (b != null) {
      return b.getExtras();
    }
    return null;
  }
  
  public float getFloat(int paramInt)
  {
    if (b != null) {
      return b.getFloat(paramInt);
    }
    return 0.0F;
  }
  
  public int getInt(int paramInt)
  {
    if (b != null) {
      return b.getInt(paramInt);
    }
    return 0;
  }
  
  public long getLong(int paramInt)
  {
    if (b != null) {
      return b.getLong(paramInt);
    }
    return 0L;
  }
  
  public SQLiteDatabase getMySQLiteDatabase()
  {
    return a;
  }
  
  public int getPosition()
  {
    if (b != null) {
      return b.getPosition();
    }
    return -1;
  }
  
  public short getShort(int paramInt)
  {
    if (b != null) {
      return b.getShort(paramInt);
    }
    return 0;
  }
  
  public String getString(int paramInt)
  {
    if (b != null) {
      return b.getString(paramInt);
    }
    return null;
  }
  
  public boolean getWantsAllOnMoveCalls()
  {
    if (b != null) {
      return b.getWantsAllOnMoveCalls();
    }
    return true;
  }
  
  public boolean isAfterLast()
  {
    if (b != null) {
      return b.isAfterLast();
    }
    return true;
  }
  
  public boolean isBeforeFirst()
  {
    if (b != null) {
      return b.isBeforeFirst();
    }
    return true;
  }
  
  public boolean isClosed()
  {
    if (b != null) {
      return b.isClosed();
    }
    return true;
  }
  
  public boolean isFirst()
  {
    if (b != null) {
      return b.isFirst();
    }
    return false;
  }
  
  public boolean isLast()
  {
    if (b != null) {
      return b.isLast();
    }
    return false;
  }
  
  public boolean isNull(int paramInt)
  {
    if (b != null) {
      return b.isNull(paramInt);
    }
    return false;
  }
  
  public boolean move(int paramInt)
  {
    if (b != null) {
      return b.move(paramInt);
    }
    return false;
  }
  
  public boolean moveToFirst()
  {
    if (b != null) {
      return b.moveToFirst();
    }
    return false;
  }
  
  public boolean moveToLast()
  {
    if (b != null) {
      return b.moveToLast();
    }
    return false;
  }
  
  public boolean moveToNext()
  {
    if (b != null) {
      return b.moveToNext();
    }
    return false;
  }
  
  public boolean moveToPosition(int paramInt)
  {
    if (b != null) {
      return b.moveToPosition(paramInt);
    }
    return false;
  }
  
  public boolean moveToPrevious()
  {
    if (b != null) {
      return b.moveToPrevious();
    }
    return false;
  }
  
  public void registerContentObserver(ContentObserver paramContentObserver)
  {
    if (b != null) {
      b.registerContentObserver(paramContentObserver);
    }
  }
  
  public void registerDataSetObserver(DataSetObserver paramDataSetObserver)
  {
    if (b != null) {
      b.registerDataSetObserver(paramDataSetObserver);
    }
  }
  
  public boolean requery()
  {
    if (b != null) {
      return b.requery();
    }
    return false;
  }
  
  public Bundle respond(Bundle paramBundle)
  {
    if (b != null) {
      return b.respond(paramBundle);
    }
    return null;
  }
  
  public void setCur(Cursor paramCursor)
  {
    b = paramCursor;
  }
  
  public void setMySQLiteDatabase(SQLiteDatabase paramSQLiteDatabase)
  {
    a = paramSQLiteDatabase;
  }
  
  public void setNotificationUri(ContentResolver paramContentResolver, Uri paramUri)
  {
    if (b != null) {
      b.setNotificationUri(paramContentResolver, paramUri);
    }
  }
  
  public void unregisterContentObserver(ContentObserver paramContentObserver)
  {
    if (b != null) {
      b.unregisterContentObserver(paramContentObserver);
    }
  }
  
  public void unregisterDataSetObserver(DataSetObserver paramDataSetObserver)
  {
    if (b != null) {
      b.unregisterDataSetObserver(paramDataSetObserver);
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.XyCursor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */