package com.android.common.contacts;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DataUsageStatUpdater
{
  private static final String TAG = DataUsageStatUpdater.class.getSimpleName();
  private final ContentResolver mResolver;
  
  public DataUsageStatUpdater(Context paramContext)
  {
    mResolver = paramContext.getContentResolver();
  }
  
  private boolean update(Collection<Long> paramCollection1, Collection<Long> paramCollection2, String paramString)
  {
    long l = System.currentTimeMillis();
    if (Build.VERSION.SDK_INT >= 14) {
      if (paramCollection2.isEmpty()) {
        if (Log.isLoggable(TAG, 3)) {
          Log.d(TAG, "Given list for data IDs is null. Ignoring.");
        }
      }
    }
    do
    {
      do
      {
        do
        {
          return false;
          paramCollection1 = DataUsageFeedback.FEEDBACK_URI.buildUpon().appendPath(TextUtils.join(",", paramCollection2)).appendQueryParameter("type", paramString).build();
          if (mResolver.update(paramCollection1, new ContentValues(), null, null) > 0) {
            return true;
          }
        } while (!Log.isLoggable(TAG, 3));
        Log.d(TAG, "update toward data rows " + paramCollection2 + " failed");
        return false;
        if (!paramCollection1.isEmpty()) {
          break;
        }
      } while (!Log.isLoggable(TAG, 3));
      Log.d(TAG, "Given list for contact IDs is null. Ignoring.");
      return false;
      paramCollection2 = new StringBuilder();
      paramString = new ArrayList();
      Object localObject = new String[paramCollection1.size()];
      Iterator localIterator = paramCollection1.iterator();
      while (localIterator.hasNext()) {
        paramString.add(String.valueOf(((Long)localIterator.next()).longValue()));
      }
      Arrays.fill((Object[])localObject, "?");
      paramCollection2.append("_id IN (").append(TextUtils.join(",", (Object[])localObject)).append(")");
      if (Log.isLoggable(TAG, 3))
      {
        Log.d(TAG, "contactId where: " + paramCollection2.toString());
        Log.d(TAG, "contactId selection: " + paramString);
      }
      localObject = new ContentValues();
      ((ContentValues)localObject).put("last_time_contacted", Long.valueOf(l));
      if (mResolver.update(ContactsContract.Contacts.CONTENT_URI, (ContentValues)localObject, paramCollection2.toString(), (String[])paramString.toArray(new String[0])) > 0) {
        return true;
      }
    } while (!Log.isLoggable(TAG, 3));
    Log.d(TAG, "update toward raw contacts " + paramCollection1 + " failed");
    return false;
  }
  
  public boolean updateWithPhoneNumber(Collection<String> paramCollection)
  {
    if (Log.isLoggable(TAG, 3)) {
      Log.d(TAG, "updateWithPhoneNumber: " + Arrays.toString(paramCollection.toArray()));
    }
    if ((paramCollection != null) && (!paramCollection.isEmpty()))
    {
      localObject1 = new ArrayList();
      localObject2 = new StringBuilder();
      Object localObject3 = new String[paramCollection.size()];
      ((ArrayList)localObject1).addAll(paramCollection);
      Arrays.fill((Object[])localObject3, "?");
      ((StringBuilder)localObject2).append("data1 IN (").append(TextUtils.join(",", (Object[])localObject3)).append(")");
      paramCollection = mResolver;
      localObject3 = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
      localObject2 = ((StringBuilder)localObject2).toString();
      localObject1 = (String[])((ArrayList)localObject1).toArray(new String[0]);
      paramCollection = paramCollection.query((Uri)localObject3, new String[] { "contact_id", "_id" }, (String)localObject2, (String[])localObject1, null);
      if (paramCollection == null) {
        Log.w(TAG, "Cursor for Phone.CONTENT_URI became null.");
      }
    }
    else
    {
      return false;
    }
    Object localObject1 = new HashSet(paramCollection.getCount());
    Object localObject2 = new HashSet(paramCollection.getCount());
    try
    {
      paramCollection.move(-1);
      while (paramCollection.moveToNext())
      {
        ((Set)localObject1).add(Long.valueOf(paramCollection.getLong(0)));
        ((Set)localObject2).add(Long.valueOf(paramCollection.getLong(1)));
      }
    }
    finally
    {
      paramCollection.close();
    }
    return update(localCollection, (Collection)localObject2, "short_text");
  }
  
  public static final class DataUsageFeedback
  {
    static final Uri FEEDBACK_URI = Uri.withAppendedPath(ContactsContract.Data.CONTENT_URI, "usagefeedback");
  }
}

/* Location:
 * Qualified Name:     com.android.common.contacts.DataUsageStatUpdater
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */