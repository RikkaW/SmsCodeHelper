package com.android.mms.data;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;

class RecipientIdCache$1
  extends Thread
{
  RecipientIdCache$1(RecipientIdCache paramRecipientIdCache, String paramString, ContentResolver paramContentResolver, Uri paramUri, ContentValues paramContentValues, StringBuilder paramStringBuilder)
  {
    super(paramString);
  }
  
  public void run()
  {
    val$cr.update(val$uri, val$values, val$buf.toString(), null);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.data.RecipientIdCache.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */