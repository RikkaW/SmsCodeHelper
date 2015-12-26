package com.android.mms.ui;

import android.content.Context;
import android.util.Log;
import android.widget.ScrollView;
import android.widget.TextView;

class NewMessageActivity$11
  implements Runnable
{
  NewMessageActivity$11(NewMessageActivity paramNewMessageActivity) {}
  
  public void run()
  {
    Log.v("NewMessageActivity", "run mDrawContactPanelRunnable");
    int i = NewMessageActivity.access$1000(this$0);
    if (this$0.isVisible(NewMessageActivity.access$300(this$0)))
    {
      NewMessageActivity.access$2300(this$0).setText(this$0.getBaseContext().getString(2131362055, new Object[] { Integer.valueOf(this$0.recipientCount()) }));
      if (i > 2)
      {
        NewMessageActivity.access$2300(this$0).setVisibility(0);
        NewMessageActivity.access$300(this$0).setVerticalScrollBarEnabled(true);
        return;
      }
      NewMessageActivity.access$2300(this$0).setVisibility(8);
      NewMessageActivity.access$300(this$0).setVerticalScrollBarEnabled(false);
      return;
    }
    NewMessageActivity.access$2300(this$0).setVisibility(8);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.NewMessageActivity.11
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */