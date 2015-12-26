package com.xiaomi.mms.mx.fw;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SqliteWrapper;
import miui.provider.ExtraTelephony.Hms;

public class HmsMessageLogicHelper
{
  public static boolean moveHmsToFolder(Context paramContext, int paramInt, String paramString)
  {
    ContentValues localContentValues = new ContentValues(2);
    int j = 0;
    int i = j;
    switch (paramInt)
    {
    default: 
      return false;
    case 2: 
    case 4: 
      i = 1;
    case 1: 
    case 3: 
      localContentValues.put("type", Integer.valueOf(paramInt));
      if (0 != 0)
      {
        localContentValues.put("read", Integer.valueOf(0));
        label86:
        if (1 != SqliteWrapper.update(paramContext, paramContext.getContentResolver(), ExtraTelephony.Hms.CONTENT_URI, localContentValues, paramString, null)) {
          break label135;
        }
      }
      break;
    }
    label135:
    for (boolean bool = true;; bool = false)
    {
      return bool;
      i = j;
      break;
      if (i == 0) {
        break label86;
      }
      localContentValues.put("read", Integer.valueOf(1));
      break label86;
    }
  }
  
  public static void updateHmsTypeByMessageId(Context paramContext, String paramString, int paramInt)
  {
    paramContext = paramContext.getContentResolver();
    ContentValues localContentValues = new ContentValues(1);
    localContentValues.put("type", Integer.valueOf(paramInt));
    paramString = "mx_message_id = " + paramString;
    paramContext.update(ExtraTelephony.Hms.CONTENT_URI, localContentValues, paramString, null);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.fw.HmsMessageLogicHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */