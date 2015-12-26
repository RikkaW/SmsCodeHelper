package com.android.mms.ui;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;

class ConversationFragment$13
  implements View.OnKeyListener
{
  ConversationFragment$13(ConversationFragment paramConversationFragment) {}
  
  public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent.getAction() == 0) {}
    switch (paramInt)
    {
    default: 
      return false;
    }
    long l = this$0.mListView.getSelectedItemId();
    if (l > 0L) {
      ConversationFragment.confirmDeleteThread(l, this$0.mQueryHandler);
    }
    return true;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationFragment.13
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */