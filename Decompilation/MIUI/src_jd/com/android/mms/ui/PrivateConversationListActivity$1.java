package com.android.mms.ui;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import miui.view.SearchActionMode;
import miui.view.SearchActionMode.Callback;

class PrivateConversationListActivity$1
  implements SearchActionMode.Callback
{
  PrivateConversationListActivity$1(PrivateConversationListActivity paramPrivateConversationListActivity) {}
  
  public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
  {
    return true;
  }
  
  public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    if (PrivateConversationListActivity.access$000(this$0) == null) {
      PrivateConversationListActivity.access$100(this$0, true, false, true);
    }
    paramActionMode = (SearchActionMode)paramActionMode;
    paramActionMode.setAnchorView(PrivateConversationListActivity.access$200(this$0));
    paramActionMode.setAnimateView(PrivateConversationListActivity.access$300(this$0).getView());
    paramActionMode.setResultView(PrivateConversationListActivity.access$000(this$0).getView());
    paramActionMode.getSearchInput().addTextChangedListener(PrivateConversationListActivity.access$400(this$0));
    PrivateConversationListActivity.access$500(this$0, true);
    return true;
  }
  
  public void onDestroyActionMode(ActionMode paramActionMode)
  {
    ((SearchActionMode)paramActionMode).getSearchInput().removeTextChangedListener(PrivateConversationListActivity.access$400(this$0));
    PrivateConversationListActivity.access$602(this$0, null);
    if (PrivateConversationListActivity.access$000(this$0) != null) {
      PrivateConversationListActivity.access$100(this$0, false, true, false);
    }
    PrivateConversationListActivity.access$002(this$0, null);
    this$0.onBackPressed();
  }
  
  public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.PrivateConversationListActivity.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */