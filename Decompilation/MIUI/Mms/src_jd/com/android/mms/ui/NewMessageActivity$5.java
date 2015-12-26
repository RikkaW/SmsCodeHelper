package com.android.mms.ui;

import android.database.DataSetObserver;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

class NewMessageActivity$5
  extends DataSetObserver
{
  NewMessageActivity$5(NewMessageActivity paramNewMessageActivity) {}
  
  public void onChanged()
  {
    if ((NewMessageActivity.access$700(this$0).getCount() > 0) && (NewMessageActivity.access$400(this$0).getText().length() > 0))
    {
      NewMessageActivity.access$800(this$0).setVisibility(0);
      NewMessageActivity.access$900(this$0).setVisibility(8);
    }
    for (;;)
    {
      NewMessageActivity.access$1000(this$0);
      return;
      NewMessageActivity.access$800(this$0).setVisibility(8);
      NewMessageActivity.access$900(this$0).setVisibility(0);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.NewMessageActivity.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */