package com.android.mms.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.util.Log;
import com.android.mms.LogTag;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RecipientIdCache
{
  private static Uri sAllCanonical = Uri.parse("content://mms-sms/canonical-addresses");
  private static RecipientIdCache sInstance;
  private static Uri sSingleCanonicalAddressUri = Uri.parse("content://mms-sms/canonical-address");
  private final Map<Long, String> mCache = new HashMap();
  private final Context mContext;
  
  RecipientIdCache(Context paramContext)
  {
    mContext = paramContext;
  }
  
  public static void fill()
  {
    LogTag.debug("[RecipientIdCache] fill: begin", new Object[0]);
    Object localObject1 = sInstancemContext;
    localObject1 = SqliteWrapper.query((Context)localObject1, ((Context)localObject1).getContentResolver(), sAllCanonical, null, null, null, null);
    if (localObject1 == null)
    {
      Log.w("Mms/cache", "null Cursor in fill()");
      return;
    }
    try
    {
      synchronized (sInstance)
      {
        if (((Cursor)localObject1).moveToNext())
        {
          long l = ((Cursor)localObject1).getLong(0);
          String str = ((Cursor)localObject1).getString(1);
          sInstancemCache.put(Long.valueOf(l), str);
        }
      }
    }
    finally
    {
      ((Cursor)localObject1).close();
    }
    ((Cursor)localObject1).close();
  }
  
  public static List<Entry> getAddresses(String paramString)
  {
    for (;;)
    {
      ArrayList localArrayList;
      int i;
      long l;
      String str;
      synchronized (sInstance)
      {
        localArrayList = new ArrayList();
        String[] arrayOfString = paramString.split(" ");
        int j = arrayOfString.length;
        i = 0;
        if (i < j) {
          paramString = arrayOfString[i];
        }
      }
      return localArrayList;
      i += 1;
    }
  }
  
  public static String getSingleAddressFromCanonicalAddressInDb(Context paramContext, String paramString)
  {
    paramContext = SqliteWrapper.query(paramContext, paramContext.getContentResolver(), ContentUris.withAppendedId(sSingleCanonicalAddressUri, Long.parseLong(paramString)), null, null, null, null);
    if (paramContext == null) {
      return null;
    }
    try
    {
      if (paramContext.moveToFirst())
      {
        paramString = paramContext.getString(0);
        return paramString;
      }
      return null;
    }
    finally
    {
      paramContext.close();
    }
  }
  
  static void init(Context paramContext)
  {
    paramContext = paramContext.getApplicationContext();
    if (sInstance == null) {
      sInstance = new RecipientIdCache(paramContext);
    }
  }
  
  private void updateCanonicalAddressInDb(long paramLong, final String paramString)
  {
    final ContentValues localContentValues = new ContentValues();
    localContentValues.put("address", paramString);
    paramString = new StringBuilder("_id");
    paramString.append('=').append(paramLong);
    final Uri localUri = ContentUris.withAppendedId(sSingleCanonicalAddressUri, paramLong);
    new Thread("updateCanonicalAddressInDb")
    {
      public void run()
      {
        val$cr.update(localUri, localContentValues, paramString.toString(), null);
      }
    }.start();
  }
  
  public static void updateNumbers(long paramLong, ContactList paramContactList)
  {
    paramContactList = paramContactList.iterator();
    while (paramContactList.hasNext())
    {
      ??? = (Contact)paramContactList.next();
      if (((Contact)???).isNumberModified())
      {
        ((Contact)???).setIsNumberModified(false);
        paramLong = ((Contact)???).getRecipientId();
        if (paramLong != 0L)
        {
          String str = ((Contact)???).getNumber();
          int i = 0;
          synchronized (sInstance)
          {
            if (!str.equalsIgnoreCase((String)sInstancemCache.get(Long.valueOf(paramLong))))
            {
              sInstancemCache.put(Long.valueOf(paramLong), str);
              i = 1;
            }
            if (i != 0) {
              sInstance.updateCanonicalAddressInDb(paramLong, str);
            }
          }
        }
      }
    }
  }
  
  public static class Entry
  {
    public long id;
    public String number;
    
    public Entry(long paramLong, String paramString)
    {
      id = paramLong;
      number = paramString;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.data.RecipientIdCache
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */