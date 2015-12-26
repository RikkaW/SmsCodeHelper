package com.android.mms.ui;

import android.app.Activity;
import android.content.res.Resources;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.HeaderViewListAdapter;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import com.android.mms.util.EditableListView.EditModeListener;
import com.android.mms.util.EditableListView.EditableListViewCheckable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import miui.R.plurals;
import miui.R.string;
import miui.view.EditActionMode;

public class ConversationFragment$ModeCallback
  implements EditableListView.EditModeListener
{
  private ConversationListAdapter mAdapter;
  private EditableListView.EditableListViewCheckable mCheckable;
  private EditActionMode mEditActionMode;
  private HeaderViewListAdapter mHeaderAdapter;
  private Menu mRootMenu;
  
  protected ConversationFragment$ModeCallback(ConversationFragment paramConversationFragment) {}
  
  private void updateMenu(int paramInt, boolean paramBoolean, Set<Long> paramSet)
  {
    this$0.getActivity();
    boolean bool3;
    boolean bool1;
    label22:
    boolean bool2;
    label29:
    label36:
    boolean bool6;
    boolean bool5;
    boolean bool4;
    Conversation localConversation;
    if (paramInt > 0)
    {
      bool3 = true;
      if (paramInt <= 0) {
        break label253;
      }
      bool1 = true;
      if (paramInt <= 0) {
        break label259;
      }
      bool2 = true;
      paramSet = paramSet.iterator();
      bool6 = bool3;
      bool5 = bool1;
      bool4 = bool2;
      if (paramSet.hasNext())
      {
        localConversation = Conversation.get(((Long)paramSet.next()).longValue());
        if ((!this$0.mIsCompositeMode) || (!localConversation.isServiceProvider()) || (localConversation.isSticky())) {
          break label265;
        }
        bool6 = false;
        bool4 = false;
        bool5 = false;
      }
      if (paramInt != 0) {
        break label308;
      }
      paramSet = this$0.getString(R.string.action_mode_title_empty);
      mRootMenu.findItem(2131820908).setEnabled(false);
      label142:
      ((ActionMode)mEditActionMode).setTitle(paramSet);
      if (!mCheckable.isAllChecked()) {
        break label374;
      }
      mEditActionMode.setButton(16908314, R.string.action_mode_deselect_all);
      label179:
      int i = mRootMenu.size();
      paramInt = 0;
      label192:
      if (paramInt >= i) {
        return;
      }
      paramSet = mRootMenu.getItem(paramInt);
      switch (paramSet.getItemId())
      {
      }
    }
    for (;;)
    {
      paramInt += 1;
      break label192;
      bool3 = false;
      break;
      label253:
      bool1 = false;
      break label22;
      label259:
      bool2 = false;
      break label29;
      label265:
      ContactList localContactList = localConversation.getRecipients();
      if ((localContactList != null) && (localContactList.size() == 1) && (!localContactList.containsEmail()) && (!localConversation.isB2c())) {
        break label36;
      }
      bool3 = false;
      break label36;
      label308:
      paramSet = this$0.mActivity.getResources().getQuantityString(R.plurals.items_selected, mCheckable.count(), new Object[] { Integer.valueOf(mCheckable.count()) });
      mRootMenu.findItem(2131820908).setEnabled(true);
      break label142;
      label374:
      mEditActionMode.setButton(16908314, R.string.action_mode_select_all);
      break label179;
      ConversationFragment.access$2400(this$0, paramSet, paramBoolean, bool4);
      paramSet.setEnabled(bool4);
      continue;
      paramSet.setEnabled(bool5);
      continue;
      paramSet.setEnabled(bool6);
    }
  }
  
  public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default: 
    case 2131820908: 
      do
      {
        return true;
        paramActionMode = mCheckable.getCheckedItemInIds();
      } while (paramActionMode.size() <= 0);
      ConversationFragment.confirmDeleteThreads(paramActionMode, this$0.mQueryHandler);
      return true;
    case 16908313: 
      paramActionMode.finish();
      return true;
    case 16908314: 
      if (mCheckable.isAllChecked())
      {
        mCheckable.checkNothing();
        return true;
      }
      mCheckable.checkAll();
      return true;
    case 2131820907: 
      paramActionMode = mCheckable.getCheckedItemInIds();
      paramMenuItem = this$0;
      if (!ConversationFragment.access$2100(this$0, paramActionMode)) {}
      for (boolean bool = true;; bool = false)
      {
        ConversationFragment.access$2200(paramMenuItem, paramActionMode, bool);
        return true;
      }
    }
    paramActionMode = mCheckable.getCheckedItemInIds();
    ConversationFragment.access$2300(this$0, this$0.mActivity, paramActionMode);
    return true;
  }
  
  public void onCheckStateChanged(EditableListView.EditableListViewCheckable paramEditableListViewCheckable)
  {
    HashSet localHashSet = paramEditableListViewCheckable.getCheckedItemInIds();
    mAdapter.setCheckedItem(localHashSet);
    boolean bool = ConversationFragment.access$2100(this$0, localHashSet);
    mCheckable = paramEditableListViewCheckable;
    int i = mCheckable.count();
    if (!bool) {}
    for (bool = true;; bool = false)
    {
      updateMenu(i, bool, localHashSet);
      return;
    }
  }
  
  public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    mRootMenu = paramMenu;
    this$0.mActivity.getMenuInflater().inflate(2131755010, paramMenu);
    mHeaderAdapter = ((HeaderViewListAdapter)this$0.mListView.getAdapter());
    mAdapter = ((ConversationListAdapter)mHeaderAdapter.getWrappedAdapter());
    mAdapter.enterCheckMode();
    mCheckable = this$0.mListView.getEditableListViewCheckable();
    mEditActionMode = ((EditActionMode)paramActionMode);
    paramActionMode.setTitle(R.string.action_mode_title_empty);
    mEditActionMode.setButton(16908313, 17039360);
    mEditActionMode.setButton(16908314, R.string.action_mode_select_all);
    updateMenu(0, false, new HashSet(0));
    return true;
  }
  
  public void onDestroyActionMode(ActionMode paramActionMode)
  {
    mAdapter.exitCheckMode();
  }
  
  public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    return false;
  }
  
  public void onVisibleViewCheckStateChanged(View paramView, boolean paramBoolean) {}
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationFragment.ModeCallback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */