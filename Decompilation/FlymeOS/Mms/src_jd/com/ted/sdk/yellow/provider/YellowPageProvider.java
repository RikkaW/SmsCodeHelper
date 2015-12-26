package com.ted.sdk.yellow.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;
import aoh;
import aoi;
import aoj;

public class YellowPageProvider
  extends ContentProvider
{
  public static final Uri a;
  private static final String b = YellowPageProvider.class.getSimpleName();
  private static UriMatcher c;
  private SQLiteOpenHelper d;
  
  static
  {
    a = Uri.parse("content://com.ted.sdk.yellow.provider");
    c = new UriMatcher(-1);
    c.addURI("com.ted.sdk.yellow.provider", "num", 0);
    c.addURI("com.ted.sdk.yellow.provider", "marker", 1);
    c.addURI("com.ted.sdk.yellow.provider", "m_mark", 2);
    c.addURI("com.ted.sdk.yellow.provider", "corrector", 3);
  }
  
  private Uri a(String paramString)
  {
    return Uri.withAppendedPath(a, paramString);
  }
  
  private aoj a(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return new aoi(d, "num");
    case 1: 
      return new aoi(d, "marker");
    case 3: 
      return new aoi(d, "corrector");
    }
    return new aoi(d, "m_mark");
  }
  
  private void b(int paramInt)
  {
    switch (paramInt)
    {
    case 2: 
    default: 
      return;
    }
    String str2;
    for (String str1 = "marker";; str2 = "corrector") {
      try
      {
        getContext().getContentResolver().notifyChange(a(str1), null, false);
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return;
      }
    }
  }
  
  public int delete(Uri paramUri, String paramString, String[] paramArrayOfString)
  {
    int i = c.match(paramUri);
    int j = a(i).a(paramString, paramArrayOfString);
    if (j > -1) {
      b(i);
    }
    return j;
  }
  
  public String getType(Uri paramUri)
  {
    return null;
  }
  
  public Uri insert(Uri paramUri, ContentValues paramContentValues)
  {
    int i = c.match(paramUri);
    if (!a(i).a(paramContentValues)) {
      Log.e(b, "插入数据库失败！");
    }
    for (;;)
    {
      return null;
      b(i);
    }
  }
  
  public boolean onCreate()
  {
    d = aoh.a(getContext());
    return true;
  }
  
  public Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2)
  {
    return a(c.match(paramUri)).a(paramArrayOfString1, paramString1, paramArrayOfString2, paramString2);
  }
  
  public int update(Uri paramUri, ContentValues paramContentValues, String paramString, String[] paramArrayOfString)
  {
    int i = c.match(paramUri);
    int j = a(i).a(paramContentValues, paramString, paramArrayOfString);
    if (j > -1) {
      b(i);
    }
    return j;
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.yellow.provider.YellowPageProvider
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */