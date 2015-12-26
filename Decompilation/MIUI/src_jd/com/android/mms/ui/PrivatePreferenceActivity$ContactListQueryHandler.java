package com.android.mms.ui;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.database.Cursor;

final class PrivatePreferenceActivity$ContactListQueryHandler
  extends AsyncQueryHandler
{
  public PrivatePreferenceActivity$ContactListQueryHandler(PrivatePreferenceActivity paramPrivatePreferenceActivity, ContentResolver paramContentResolver)
  {
    super(paramContentResolver);
  }
  
  protected void onQueryComplete(int paramInt, Object paramObject, Cursor paramCursor)
  {
    switch (paramInt)
    {
    }
    do
    {
      return;
    } while (paramCursor == null);
    try
    {
      PrivatePreferenceActivity.access$1400(this$0, paramCursor);
      return;
    }
    finally
    {
      paramCursor.close();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.PrivatePreferenceActivity.ContactListQueryHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */