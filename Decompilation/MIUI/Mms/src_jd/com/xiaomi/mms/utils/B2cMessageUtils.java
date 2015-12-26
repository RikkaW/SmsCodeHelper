package com.xiaomi.mms.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.net.Uri.Builder;
import android.provider.Telephony.Sms;
import android.text.TextUtils;
import com.android.mms.data.Contact;
import java.util.HashMap;
import java.util.List;
import miui.yellowpage.MiPubUtils;

public class B2cMessageUtils
{
  private static final Uri B2C_ADDRESS_URI = Uri.parse("content://b2c-address");
  
  public static HashMap<String, String> getB2cAddessDisplayNames(Context paramContext, List<String> paramList)
  {
    HashMap localHashMap = new HashMap();
    int m = paramList.size();
    int i = 0;
    Object localObject1 = new StringBuilder();
    int j = 0;
    while (j < m)
    {
      if (((StringBuilder)localObject1).length() > 0) {
        ((StringBuilder)localObject1).append(",");
      }
      ((StringBuilder)localObject1).append("'").append((String)paramList.get(j)).append("'");
      int k = i + 1;
      Object localObject2;
      Cursor localCursor;
      if (k < 50)
      {
        localObject2 = localObject1;
        i = k;
        if (j != m - 1) {}
      }
      else
      {
        localCursor = null;
        localObject2 = localCursor;
      }
      try
      {
        String str1 = "address IN (" + ((StringBuilder)localObject1).toString() + ")";
        localObject2 = localCursor;
        localCursor = SqliteWrapper.query(paramContext, paramContext.getContentResolver(), B2C_ADDRESS_URI, null, str1, null, null);
        if (localCursor != null)
        {
          localObject2 = localCursor;
          if (localCursor.moveToFirst())
          {
            localObject2 = localCursor;
            i = localCursor.getColumnIndex("display_name");
            localObject2 = localCursor;
            int n = localCursor.getColumnIndex("address");
            boolean bool;
            do
            {
              localObject2 = localCursor;
              str1 = localCursor.getString(n);
              localObject2 = localCursor;
              String str2 = localCursor.getString(i);
              localObject2 = localCursor;
              if (!TextUtils.isEmpty(str2))
              {
                localObject2 = localCursor;
                if (!TextUtils.isEmpty(str1))
                {
                  localObject2 = localCursor;
                  localHashMap.put(str1, str2);
                }
              }
              localObject2 = localCursor;
              bool = localCursor.moveToNext();
            } while (bool);
          }
        }
        if (localCursor != null) {
          localCursor.close();
        }
        localObject2 = localObject1;
        i = k;
        if (j < m - 1)
        {
          i = 0;
          localObject2 = new StringBuilder();
        }
        j += 1;
        localObject1 = localObject2;
      }
      finally
      {
        if (localObject2 != null) {
          ((Cursor)localObject2).close();
        }
      }
    }
    return localHashMap;
  }
  
  public static String getB2cAddressDisplayName(Context paramContext, String paramString)
  {
    Object localObject1 = null;
    Object localObject2 = B2C_ADDRESS_URI.buildUpon().appendPath(paramString).build();
    paramString = null;
    try
    {
      localObject2 = SqliteWrapper.query(paramContext, paramContext.getContentResolver(), (Uri)localObject2, null, null, null, null);
      paramContext = (Context)localObject1;
      if (localObject2 != null)
      {
        paramContext = (Context)localObject1;
        paramString = (String)localObject2;
        if (((Cursor)localObject2).moveToFirst())
        {
          paramString = (String)localObject2;
          paramContext = ((Cursor)localObject2).getString(((Cursor)localObject2).getColumnIndex("display_name"));
        }
      }
      if (localObject2 != null) {
        ((Cursor)localObject2).close();
      }
      return paramContext;
    }
    finally
    {
      if (paramString != null) {
        paramString.close();
      }
    }
  }
  
  public static Intent getB2cContactDetailIntent(String paramString)
  {
    return new Intent("android.intent.action.VIEW", Uri.parse(String.format("yellowpage://details?vipid=%s&source=miMessage", new Object[] { Uri.encode(paramString) })));
  }
  
  private static String getLastB2cNumber(Context paramContext, String paramString)
  {
    Object localObject = null;
    String str1 = null;
    try
    {
      paramString = paramContext.getContentResolver().query(Telephony.Sms.CONTENT_URI, new String[] { "b2c_numbers" }, "address=?", new String[] { paramString }, "type,date desc limit 1");
      paramContext = (Context)localObject;
      if (paramString != null)
      {
        paramContext = (Context)localObject;
        str1 = paramString;
        if (paramString.moveToFirst())
        {
          str1 = paramString;
          String str2 = paramString.getString(0);
          paramContext = (Context)localObject;
          str1 = paramString;
          if (!TextUtils.isEmpty(str2))
          {
            str1 = paramString;
            paramContext = str2.split(";")[0];
          }
        }
      }
      return paramContext;
    }
    finally
    {
      if (str1 != null) {
        str1.close();
      }
    }
  }
  
  public static String getPossibleLastB2cNumber(Context paramContext, String paramString)
  {
    if (isB2cNumber(Contact.get(paramString))) {
      return getLastB2cNumber(paramContext, paramString);
    }
    return null;
  }
  
  public static void insertOrUpdateB2cAddress(Context paramContext, String paramString1, String paramString2)
  {
    ContentValues localContentValues = new ContentValues();
    Uri localUri = B2C_ADDRESS_URI.buildUpon().appendPath(paramString1).build();
    localContentValues.put("address", paramString1);
    localContentValues.put("display_name", paramString2);
    SqliteWrapper.insert(paramContext, paramContext.getContentResolver(), localUri, localContentValues);
  }
  
  public static boolean isB2cNumber(Contact paramContact)
  {
    return (paramContact != null) && ((paramContact.isB2cNumber()) || (isB2cNumber(paramContact.getNumber())));
  }
  
  public static boolean isB2cNumber(String paramString)
  {
    return (!TextUtils.isEmpty(paramString)) && (MiPubUtils.isXiaomiAccount(paramString));
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.utils.B2cMessageUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */