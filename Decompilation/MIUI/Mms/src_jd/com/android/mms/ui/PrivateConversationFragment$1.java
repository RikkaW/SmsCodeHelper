package com.android.mms.ui;

import android.view.ActionMode;
import android.view.Menu;

class PrivateConversationFragment$1
  extends ConversationFragment.ModeCallback
{
  PrivateConversationFragment$1(PrivateConversationFragment paramPrivateConversationFragment)
  {
    super(paramPrivateConversationFragment);
  }
  
  public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    paramMenu.removeItem(2131820906);
    return true;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.PrivateConversationFragment.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */