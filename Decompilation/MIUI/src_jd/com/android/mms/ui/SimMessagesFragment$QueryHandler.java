package com.android.mms.ui;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Handler;
import android.util.Log;
import com.android.mms.util.EditableListView;
import miui.os.Build;

class SimMessagesFragment$QueryHandler
  extends AsyncQueryHandler
{
  public SimMessagesFragment$QueryHandler(SimMessagesFragment paramSimMessagesFragment, ContentResolver paramContentResolver)
  {
    super(paramContentResolver);
  }
  
  protected void onQueryComplete(int paramInt, Object paramObject, Cursor paramCursor)
  {
    if (!this$0.isResumed())
    {
      if (paramCursor != null) {}
      try
      {
        paramCursor.close();
        Log.d("SimMessagesFragment", "onQueryComplete cursor close");
        SimMessagesFragment.access$200(this$0, 1);
        return;
      }
      finally
      {
        Log.d("SimMessagesFragment", "onQueryComplete cursor close");
      }
    }
    int j;
    int i;
    if (paramCursor != null)
    {
      j = 0;
      i = 0;
      paramInt = j;
    }
    for (;;)
    {
      try
      {
        if (!paramCursor.moveToFirst())
        {
          paramInt = j;
          SimMessagesFragment.access$302(this$0, 0);
          paramInt = j;
          SimMessagesFragment.access$200(this$0, 1);
          paramInt = 1;
          i = 1;
          Log.d("SimMessagesFragment", "cursor is empty");
        }
        if (i != 0) {
          paramCursor.close();
        }
        if (i == 0)
        {
          if (SimMessagesFragment.access$400(this$0) == null)
          {
            SimMessagesFragment.access$402(this$0, new MessageListAdapter(SimMessagesFragment.access$500(this$0), paramCursor, SimMessagesFragment.access$100(this$0), false, null, null, 0L));
            SimMessagesFragment.access$400(this$0).setMsgListItemHandler(new Handler());
            SimMessagesFragment.access$100(this$0).setAdapter(SimMessagesFragment.access$400(this$0));
            SimMessagesFragment.access$200(this$0, 0);
            SimMessagesFragment.access$400(this$0).setTextSize(SimMessagesFragment.sTextSize);
            SimMessagesFragment.access$302(this$0, SimMessagesFragment.access$400(this$0).getCount());
            Log.d("SimMessagesFragment", "onQueryComplete change cursor");
          }
        }
        else
        {
          if (!Build.IS_CM_CUSTOMIZATION_TEST) {
            break;
          }
          ((ManageSimMessages)SimMessagesFragment.access$500(this$0)).updateSimInfo(SimMessagesFragment.access$600(this$0));
          return;
        }
      }
      finally
      {
        if (paramInt != 0) {
          paramCursor.close();
        }
      }
      SimMessagesFragment.access$400(this$0).changeCursor(paramCursor);
      SimMessagesFragment.access$200(this$0, 0);
      continue;
      SimMessagesFragment.access$302(this$0, 0);
      SimMessagesFragment.access$200(this$0, 1);
      Log.d("SimMessagesFragment", "cursor is null");
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SimMessagesFragment.QueryHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */