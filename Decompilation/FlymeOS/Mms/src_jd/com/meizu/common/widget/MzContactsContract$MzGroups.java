package com.meizu.common.widget;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.ContactsContract.Groups;

public final class MzContactsContract$MzGroups
  implements BaseColumns, MzContactsContract.MzGroupsColumns
{
  public static final Uri CONTENT_ACCOUNT_URI = Uri.withAppendedPath(ContactsContract.Groups.CONTENT_URI, "account");
  public static final Uri CONTENT_SUMMARY_FILTER_URI = Uri.withAppendedPath(ContactsContract.Groups.CONTENT_SUMMARY_URI, "filter");
  public static final String GROUP_SPLIT_MARK_EXTRA = ",";
  public static final String GROUP_SPLIT_MARK_SLASH = "/";
  public static final String GROUP_SPLIT_MARK_VCARD = ";";
  public static final String GROUP_SPLIT_MARK_XML = "|";
  
  public static String getGroupTitle(ContentResolver paramContentResolver, long paramLong)
  {
    Object localObject = null;
    if (paramLong <= 0L)
    {
      localObject = null;
      return (String)localObject;
    }
    for (;;)
    {
      try
      {
        localCursor = paramContentResolver.query(ContactsContract.Groups.CONTENT_URI, new String[] { "title" }, "_id=?", new String[] { String.valueOf(paramLong) }, null);
        if (localCursor == null) {}
      }
      finally
      {
        try
        {
          if (!localCursor.moveToFirst()) {
            break label104;
          }
          paramContentResolver = localCursor.getString(0);
          localObject = paramContentResolver;
          if (localCursor == null) {
            break;
          }
          localCursor.close();
          return paramContentResolver;
        }
        finally
        {
          Cursor localCursor;
          localObject = localCursor;
        }
        paramContentResolver = finally;
        if (localObject != null) {
          ((Cursor)localObject).close();
        }
      }
      label104:
      paramContentResolver = null;
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.MzContactsContract.MzGroups
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */