package com.ted.sdk.yellow.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.database.sqlite.SQLiteDatabase;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class AssetsDatabaseManager
{
  private static AssetsDatabaseManager mInstance = null;
  private Context mContext = null;
  private Map<String, SQLiteDatabase> mDatabases = new HashMap();
  
  private AssetsDatabaseManager(Context paramContext)
  {
    mContext = paramContext;
  }
  
  public static void closeAllDatabase()
  {
    Iterator localIterator;
    if (mInstance != null) {
      localIterator = mInstancemDatabases.entrySet().iterator();
    }
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        mInstancemDatabases.clear();
        return;
      }
      ((SQLiteDatabase)((Map.Entry)localIterator.next()).getValue()).close();
    }
  }
  
  private String getDatabaseFile(String paramString1, String paramString2)
  {
    return getDatabaseFilepath(paramString2) + "/" + paramString1;
  }
  
  private String getDatabaseFilepath(String paramString)
  {
    return String.format(paramString, new Object[] { mContext.getApplicationInfo().packageName });
  }
  
  public static AssetsDatabaseManager getManager()
  {
    return mInstance;
  }
  
  public static void initManager(Context paramContext)
  {
    if (mInstance == null) {
      mInstance = new AssetsDatabaseManager(paramContext);
    }
  }
  
  public boolean closeDatabase(String paramString)
  {
    if (mDatabases.get(paramString) != null)
    {
      ((SQLiteDatabase)mDatabases.get(paramString)).close();
      mDatabases.remove(paramString);
      return true;
    }
    return false;
  }
  
  public SQLiteDatabase getDatabase(String paramString1, String paramString2)
  {
    Object localObject = null;
    if (mDatabases.get(paramString1) != null) {
      localObject = (SQLiteDatabase)mDatabases.get(paramString1);
    }
    do
    {
      do
      {
        return (SQLiteDatabase)localObject;
      } while (mContext == null);
      paramString2 = SQLiteDatabase.openDatabase(getDatabaseFile(paramString1, paramString2), null, 16);
      localObject = paramString2;
    } while (paramString2 == null);
    mDatabases.put(paramString1, paramString2);
    return paramString2;
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.yellow.util.AssetsDatabaseManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */