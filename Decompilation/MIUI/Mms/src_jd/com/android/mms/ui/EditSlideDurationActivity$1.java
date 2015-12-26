package com.android.mms.ui;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;

class EditSlideDurationActivity$1
  implements View.OnKeyListener
{
  EditSlideDurationActivity$1(EditSlideDurationActivity paramEditSlideDurationActivity) {}
  
  public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent.getAction() != 0) {
      return false;
    }
    switch (paramInt)
    {
    default: 
      return false;
    }
    this$0.editDone();
    return false;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.EditSlideDurationActivity.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */