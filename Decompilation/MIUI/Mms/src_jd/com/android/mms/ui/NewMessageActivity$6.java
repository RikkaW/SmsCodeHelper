package com.android.mms.ui;

import android.database.Cursor;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;

class NewMessageActivity$6
  implements AdapterView.OnItemClickListener
{
  NewMessageActivity$6(NewMessageActivity paramNewMessageActivity) {}
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    paramAdapterView = (Cursor)NewMessageActivity.access$700(this$0).getItem(paramInt);
    NewMessageActivity.access$400(this$0).setText("");
    if (NewMessageActivity.access$1100(this$0, paramAdapterView.getString(3))) {
      NewMessageActivity.access$1200(this$0);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.NewMessageActivity.6
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */