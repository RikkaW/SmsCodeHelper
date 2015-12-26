package com.android.mms.data;

import android.database.sqlite.SqliteWrapper;
import android.net.Uri;

class WorkingMessage$3
  implements Runnable
{
  WorkingMessage$3(WorkingMessage paramWorkingMessage, Uri paramUri, String paramString, String[] paramArrayOfString) {}
  
  public void run()
  {
    SqliteWrapper.delete(this$0.mContext, this$0.mContentResolver, val$uri, val$selection, val$selectionArgs);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.data.WorkingMessage.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */