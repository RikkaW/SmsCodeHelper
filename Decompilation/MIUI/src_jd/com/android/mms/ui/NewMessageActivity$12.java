package com.android.mms.ui;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import com.android.mms.data.ContactList;

class NewMessageActivity$12
  implements View.OnKeyListener
{
  NewMessageActivity$12(NewMessageActivity paramNewMessageActivity) {}
  
  public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent.getAction() != 0) {}
    do
    {
      return false;
      switch (paramInt)
      {
      default: 
        return false;
      }
    } while ((NewMessageActivity.access$400(this$0).getSelectionEnd() != 0) || (NewMessageActivity.access$1700(this$0).isEmpty()));
    NewMessageActivity.access$2400(this$0, NewMessageActivity.access$1700(this$0).size() - 1);
    return true;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.NewMessageActivity.12
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */