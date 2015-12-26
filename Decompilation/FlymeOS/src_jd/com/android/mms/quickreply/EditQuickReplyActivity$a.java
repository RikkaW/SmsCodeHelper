package com.android.mms.quickreply;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.database.Cursor;

final class EditQuickReplyActivity$a
  extends AsyncQueryHandler
{
  public EditQuickReplyActivity$a(EditQuickReplyActivity paramEditQuickReplyActivity, ContentResolver paramContentResolver)
  {
    super(paramContentResolver);
  }
  
  protected void onQueryComplete(int paramInt, Object paramObject, Cursor paramCursor)
  {
    if (a.isFinishing())
    {
      if (paramCursor != null) {
        paramCursor.close();
      }
      return;
    }
    switch (paramInt)
    {
    default: 
      return;
    }
    EditQuickReplyActivity.a(a, paramCursor);
    EditQuickReplyActivity.a(a);
  }
  
  protected void onUpdateComplete(int paramInt1, Object paramObject, int paramInt2)
  {
    if (a.isFinishing()) {
      return;
    }
    switch (paramInt1)
    {
    default: 
      return;
    }
    EditQuickReplyActivity.a(a, 1);
    a.d();
    a.invalidateOptionsMenu();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.quickreply.EditQuickReplyActivity.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */