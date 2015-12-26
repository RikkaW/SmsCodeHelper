package com.android.mms.ui;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.database.Cursor;
import android.util.Log;

final class BookmarkListAdapter$BackgroundQueryHandler
  extends AsyncQueryHandler
{
  public BookmarkListAdapter$BackgroundQueryHandler(BookmarkListAdapter paramBookmarkListAdapter, ContentResolver paramContentResolver)
  {
    super(paramContentResolver);
  }
  
  protected void onQueryComplete(int paramInt, Object paramObject, Cursor paramCursor)
  {
    switch (paramInt)
    {
    default: 
      return;
    }
    if (BookmarkListAdapter.access$000(this$0))
    {
      if (paramCursor != null) {
        paramCursor.close();
      }
      Log.v("BookmarkListAdapter", "query cursor close for stop");
      return;
    }
    this$0.changeCursor(paramCursor);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.BookmarkListAdapter.BackgroundQueryHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */