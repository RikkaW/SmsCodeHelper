package com.android.mms.ui;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.database.Cursor;
import miui.app.ProgressDialog;

public final class ConversationBase$BackgroundQueryHandler
  extends AsyncQueryHandler
{
  public ConversationBase$BackgroundQueryHandler(ConversationBase paramConversationBase, ContentResolver paramContentResolver)
  {
    super(paramContentResolver);
  }
  
  protected void onDeleteComplete(int paramInt1, Object paramObject, int paramInt2)
  {
    paramObject = this$0;
    mBatchDeleteTaskCount -= 1;
    if ((this$0.mBatchDeleteTaskCount <= 0) && (ConversationBase.mBatchDeleteProgressDialog != null))
    {
      ConversationBase.mBatchDeleteProgressDialog.dismiss();
      ConversationBase.mBatchDeleteProgressDialog = null;
    }
  }
  
  protected void onQueryComplete(int paramInt, Object paramObject, Cursor paramCursor)
  {
    switch (paramInt)
    {
    }
    for (;;)
    {
      return;
      if (ConversationBase.access$1300(this$0)) {
        if (paramCursor != null) {
          paramCursor.close();
        }
      }
      while (ConversationBase.access$1500(this$0))
      {
        ConversationBase.access$1502(this$0, false);
        ConversationBase.access$1600(this$0);
        return;
        if (paramCursor != null) {
          ConversationBase.access$1400(this$0, paramCursor);
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationBase.BackgroundQueryHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */