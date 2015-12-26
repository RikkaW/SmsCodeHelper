package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class BookmarkFragment$SelectedMessage$1
  implements DialogInterface.OnClickListener
{
  BookmarkFragment$SelectedMessage$1(BookmarkFragment.SelectedMessage paramSelectedMessage) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    BookmarkFragment.access$300(this$1.this$0).deleteBookmark(BookmarkFragment.SelectedMessage.access$200(this$1));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.BookmarkFragment.SelectedMessage.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */