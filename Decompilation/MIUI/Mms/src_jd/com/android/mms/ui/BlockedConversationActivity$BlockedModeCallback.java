package com.android.mms.ui;

import android.content.res.Resources;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import com.android.mms.util.EditableListView.EditModeListener;
import com.android.mms.util.EditableListView.EditableListViewCheckable;
import java.util.HashSet;
import java.util.List;
import miui.R.plurals;
import miui.R.string;
import miui.view.EditActionMode;

class BlockedConversationActivity$BlockedModeCallback
  implements EditableListView.EditModeListener
{
  private EditableListView.EditableListViewCheckable mCheckable;
  private EditActionMode mEditActionMode;
  private Menu mRootMenu;
  
  private BlockedConversationActivity$BlockedModeCallback(BlockedConversationActivity paramBlockedConversationActivity) {}
  
  private List<MessageItem> getCheckedMessageItems()
  {
    HashSet localHashSet = mCheckable.getCheckedItemInPositions();
    return this$0.mMsgListAdapter.getCheckedItems(localHashSet);
  }
  
  private void handleMenuItemClick(ActionMode paramActionMode, MenuItem paramMenuItem)
  {
    List localList = getCheckedMessageItems();
    int i = paramMenuItem.getItemId();
    int j = localList.size();
    if (j == 0)
    {
      if (paramMenuItem.getItemId() == 16908313)
      {
        paramActionMode.finish();
        return;
      }
      if (paramMenuItem.getItemId() == 16908314)
      {
        if (mCheckable.isAllChecked())
        {
          mCheckable.checkNothing();
          return;
        }
        mCheckable.checkAll();
        return;
      }
      Log.e("BlockedConversationActivity", String.format("onMenuItemClick: invalid params, checkedItems.size=%d, menuId=%d", new Object[] { Integer.valueOf(j), Integer.valueOf(i) }));
      return;
    }
    if ((j != 1) && (i != 2131820902) && (i != 2131820903) && (i != 2131820904) && (i != 16908313) && (i != 16908314))
    {
      Log.e("BlockedConversationActivity", String.format("onMenuItemClick: invalid params, checkedItems.size=%d, menuId=%d", new Object[] { Integer.valueOf(j), Integer.valueOf(i) }));
      return;
    }
    MessageItem localMessageItem = (MessageItem)localList.get(0);
    switch (paramMenuItem.getItemId())
    {
    default: 
      return;
    case 16908313: 
      paramActionMode.finish();
      return;
    case 2131820902: 
      MessageUtils.copyMessageTextToClipboard(this$0, localList);
      paramActionMode.finish();
      return;
    case 2131820903: 
      if (1 == j)
      {
        paramActionMode = new BlockedConversationActivity.DeleteMessageListener(this$0, localMessageItem.getMessageUri(), localMessageItem.getDate(), localMessageItem.isSms(), paramActionMode);
        BlockedConversationActivity.access$500(this$0, paramActionMode);
        return;
      }
      paramActionMode = new BlockedConversationActivity.BatchDeleteListener(this$0, localList, paramActionMode);
      BlockedConversationActivity.access$600(this$0, paramActionMode, j);
      return;
    case 2131820904: 
      BlockedConversationActivity.access$700(this$0, new BlockedConversationActivity.RestoreListener(this$0, localList, paramActionMode));
      return;
    }
    if (mCheckable.isAllChecked())
    {
      mCheckable.checkNothing();
      return;
    }
    mCheckable.checkAll();
  }
  
  private void prepareNoneSelectionMenu()
  {
    int j = mRootMenu.size();
    int i = 0;
    while (i < j)
    {
      mRootMenu.getItem(i).setEnabled(false);
      i += 1;
    }
  }
  
  private void prepareSelectionMenu()
  {
    int j = mRootMenu.size();
    int i = 0;
    while (i < j)
    {
      mRootMenu.getItem(i).setEnabled(true);
      i += 1;
    }
  }
  
  private void updateMenu(int paramInt)
  {
    String str;
    if (paramInt == 0)
    {
      str = this$0.getString(R.string.action_mode_title_empty);
      ((ActionMode)mEditActionMode).setTitle(str);
      if (!mCheckable.isAllChecked()) {
        break label106;
      }
      mEditActionMode.setButton(16908314, R.string.action_mode_deselect_all);
    }
    for (;;)
    {
      if (paramInt <= 0) {
        break label123;
      }
      prepareSelectionMenu();
      return;
      str = this$0.getResources().getQuantityString(R.plurals.items_selected, mCheckable.count(), new Object[] { Integer.valueOf(mCheckable.count()) });
      break;
      label106:
      mEditActionMode.setButton(16908314, R.string.action_mode_select_all);
    }
    label123:
    prepareNoneSelectionMenu();
  }
  
  public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
  {
    handleMenuItemClick(paramActionMode, paramMenuItem);
    int i = paramMenuItem.getItemId();
    if ((2131820911 == i) || (2131820912 == i) || (2131820916 == i)) {
      paramActionMode.finish();
    }
    return true;
  }
  
  public void onCheckStateChanged(EditableListView.EditableListViewCheckable paramEditableListViewCheckable)
  {
    mCheckable = paramEditableListViewCheckable;
    this$0.mMsgListAdapter.setCheckedItem(mCheckable.getCheckedItemInIds());
    updateMenu(mCheckable.count());
  }
  
  public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    mRootMenu = paramMenu;
    this$0.getMenuInflater().inflate(2131755008, paramMenu);
    prepareNoneSelectionMenu();
    this$0.mMsgListView.setAllowTranscriptOnResize(false);
    this$0.mMsgListAdapter.enterCheckMode();
    mCheckable = this$0.mMsgListView.getEditableListViewCheckable();
    mEditActionMode = ((EditActionMode)paramActionMode);
    updateMenu(0);
    BlockedConversationActivity.access$400(this$0).setVisibility(8);
    this$0.mContactPanel.setVisibility(8);
    this$0.setTopPlaceholderHeight(0);
    this$0.mHistoryView.setForeground(null);
    int i = 0;
    while (i < this$0.mMsgListView.getChildCount())
    {
      paramActionMode = this$0.mMsgListView.getChildAt(i);
      if ((paramActionMode instanceof MessageListItem)) {
        ((MessageListItem)paramActionMode).showEditModeAnimation(true);
      }
      i += 1;
    }
    return true;
  }
  
  public void onDestroyActionMode(ActionMode paramActionMode)
  {
    this$0.mContactPanel.setVisibility(0);
    this$0.mMsgListView.post(new Runnable()
    {
      public void run()
      {
        this$0.mMsgListView.setAllowTranscriptOnResize(true);
      }
    });
    this$0.mMsgListAdapter.exitCheckMode();
    BlockedConversationActivity.access$400(this$0).setVisibility(0);
    this$0.setTopPlaceholderHeight(this$0.mTitleBarTallBgHeight);
    this$0.mHistoryView.setForeground(this$0.getResources().getDrawable(2130837856));
    int i = 0;
    while (i < this$0.mMsgListView.getChildCount())
    {
      paramActionMode = this$0.mMsgListView.getChildAt(i);
      if ((paramActionMode instanceof MessageListItem)) {
        ((MessageListItem)paramActionMode).showEditModeAnimation(false);
      }
      i += 1;
    }
  }
  
  public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    return false;
  }
  
  public void onVisibleViewCheckStateChanged(View paramView, boolean paramBoolean) {}
}

/* Location:
 * Qualified Name:     com.android.mms.ui.BlockedConversationActivity.BlockedModeCallback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */