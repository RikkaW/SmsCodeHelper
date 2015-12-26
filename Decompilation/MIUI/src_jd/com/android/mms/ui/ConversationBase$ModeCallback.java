package com.android.mms.ui;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import com.android.mms.util.EditableListView.EditModeListener;
import com.android.mms.util.EditableListView.EditableListViewCheckable;
import com.android.mms.util.MSimUtils;
import com.xiaomi.mms.mx.data.Attachment;
import com.xiaomi.mms.utils.MxMessageUtils;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import miui.R.plurals;
import miui.R.string;
import miui.os.Build;
import miui.view.EditActionMode;

public class ConversationBase$ModeCallback
  implements EditableListView.EditModeListener
{
  private EditableListView.EditableListViewCheckable mCheckable;
  private EditActionMode mEditActionMode;
  private Menu mRootMenu;
  
  protected ConversationBase$ModeCallback(ConversationBase paramConversationBase) {}
  
  private List<MessageItem> getCheckedMessageItems()
  {
    HashSet localHashSet = mCheckable.getCheckedItemInPositions();
    return this$0.mMsgListAdapter.getCheckedItems(localHashSet);
  }
  
  private void handleMenuItemClick(final ActionMode paramActionMode, MenuItem paramMenuItem)
  {
    List localList = getCheckedMessageItems();
    int i = paramMenuItem.getItemId();
    int j = localList.size();
    if (j == 0) {
      if (paramMenuItem.getItemId() == 16908313) {
        paramActionMode.finish();
      }
    }
    label760:
    do
    {
      return;
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
      Log.e("ConversationBase", String.format("onMenuItemClick: invalid params, checkedItems.size=%d, menuId=%d", new Object[] { Integer.valueOf(j), Integer.valueOf(i) }));
      return;
      if ((j != 1) && (i != 2131820914) && (i != 2131820915) && (i != 2131820913) && (i != 2131820912) && (i != 2131820911) && (i != 16908313) && (i != 16908314) && (i != 2131820919))
      {
        Log.e("ConversationBase", String.format("onMenuItemClick: invalid params, checkedItems.size=%d, menuId=%d", new Object[] { Integer.valueOf(j), Integer.valueOf(i) }));
        return;
      }
      DialogInterface.OnClickListener local2 = new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramActionMode.finish();
        }
      };
      Object localObject = (MessageItem)localList.get(0);
      switch (paramMenuItem.getItemId())
      {
      default: 
        return;
      case 16908313: 
        paramActionMode.finish();
        return;
      case 2131820912: 
        MessageUtils.copyMessageTextToClipboard(this$0, localList);
        paramActionMode.finish();
        return;
      case 2131820911: 
        MessageUtils.forwardMessage(this$0, localList, this$0.mConversation.isPrivate());
        paramActionMode.finish();
        return;
      case 2131820916: 
        i = ((Integer)mCheckable.getCheckedItemInPositions().iterator().next()).intValue();
        try
        {
          paramActionMode = (Cursor)this$0.mMsgListAdapter.getItem(i);
          paramActionMode = MessageUtils.getMessageDetails(this$0, paramActionMode, (MessageItem)localObject);
          new AlertDialog.Builder(this$0).setTitle(2131361941).setMessage(paramActionMode).setPositiveButton(17039370, local2).setCancelable(true).show();
          return;
        }
        catch (Exception paramActionMode)
        {
          Log.e("ConversationBase", "onMenuItemClick: get cursor failed", paramActionMode);
          return;
        }
      case 2131820913: 
        if (1 == j)
        {
          paramActionMode = new ConversationBase.DeleteMessageListener(this$0, ((MessageItem)localObject).getMessageUri(), ((MessageItem)localObject).isLocked(), ((MessageItem)localObject).getDate(), ((MessageItem)localObject).isSms(), paramActionMode);
          ConversationBase.access$2000(this$0, paramActionMode, ((MessageItem)localObject).isLocked(), ((MessageItem)localObject).isGroup());
          return;
        }
        boolean bool2 = false;
        paramActionMode = new ConversationBase.BatchDeleteListener(this$0, localList, paramActionMode);
        paramMenuItem = localList.iterator();
        do
        {
          bool1 = bool2;
          if (!paramMenuItem.hasNext()) {
            break;
          }
        } while (!((MessageItem)paramMenuItem.next()).isLocked());
        boolean bool1 = true;
        ConversationBase.access$2100(this$0, paramActionMode, j, bool1);
        return;
      case 2131820917: 
        paramMenuItem = null;
        if (TextUtils.isEmpty(ConversationBase.access$2200())) {
          ConversationBase.access$2300(this$0);
        }
        if (((MessageItem)localObject).getMx2Type() > 0)
        {
          localObject = ((MessageItem)localObject).getMx2Attachments();
          paramActionMode = paramMenuItem;
          if (localObject != null)
          {
            paramActionMode = paramMenuItem;
            if (((List)localObject).size() > 0)
            {
              paramActionMode = (Attachment)((List)localObject).get(0);
              paramActionMode = MxMessageUtils.saveAttachmentFileToDisk(this$0, paramActionMode);
            }
          }
          paramMenuItem = new AlertDialog.Builder(this$0);
          paramMenuItem.setTitle(2131362012);
          paramMenuItem.setIconAttribute(16843605);
          if (paramActionMode == null) {
            break label760;
          }
          paramMenuItem.setMessage(this$0.getString(2131362013, new Object[] { paramActionMode }));
        }
        for (;;)
        {
          paramMenuItem.setNeutralButton(17039370, local2);
          paramMenuItem.show();
          return;
          paramActionMode = ConversationBase.access$2400(this$0, ((MessageItem)localObject).getMsgId());
          break;
          paramMenuItem.setMessage(2131362014);
        }
      case 2131820914: 
        if (1 == j) {
          ConversationBase.access$2500(this$0, (MessageItem)localObject, true);
        }
        for (;;)
        {
          paramActionMode.finish();
          return;
          ConversationBase.access$2600(this$0, localList, true);
        }
      case 2131820915: 
        if (1 == j) {
          ConversationBase.access$2500(this$0, (MessageItem)localObject, false);
        }
        for (;;)
        {
          paramActionMode.finish();
          return;
          ConversationBase.access$2600(this$0, localList, false);
        }
      case 2131820918: 
        new ConversationBase.SelectedMessage(this$0, this$0, (MessageItem)localObject).saveMessageToSim();
        paramActionMode.finish();
        return;
      case 16908314: 
        if (mCheckable.isAllChecked())
        {
          mCheckable.checkNothing();
          return;
        }
        mCheckable.checkAll();
        return;
      }
    } while (!Build.IS_CM_CUSTOMIZATION_TEST);
    ConversationBase.access$2700(this$0, localList);
  }
  
  private void prepareMultipleSelectionMenu()
  {
    if (this$0.getRecipients().size() > 1) {}
    boolean bool5;
    int m;
    boolean bool4;
    boolean bool3;
    int n;
    Object localObject;
    for (int k = 1;; k = 0)
    {
      bool5 = this$0.mConversation.isPrivate();
      m = 1;
      j = 1;
      bool4 = false;
      boolean bool2 = false;
      bool3 = false;
      boolean bool1 = false;
      long l = this$0.mConversation.getThreadId();
      n = 0;
      i = 0;
      if (k != 0) {
        break;
      }
      localObject = getCheckedMessageItems().iterator();
      for (;;)
      {
        m = j;
        bool3 = bool1;
        n = i;
        bool4 = bool2;
        if (!((Iterator)localObject).hasNext()) {
          break;
        }
        MessageItem localMessageItem = (MessageItem)((Iterator)localObject).next();
        m = j;
        if (!localMessageItem.isLocked())
        {
          m = j;
          if (localMessageItem.getThreadId() == l)
          {
            m = j;
            if (localMessageItem.isDownloaded()) {
              m = 0;
            }
          }
        }
        bool3 = bool2;
        if (localMessageItem.hasText()) {
          bool3 = true;
        }
        bool4 = bool1;
        if (localMessageItem.isDownloaded()) {
          bool4 = true;
        }
        j = m;
        bool1 = bool4;
        bool2 = bool3;
        if (localMessageItem.getMx2Type() == 3)
        {
          i = 1;
          j = m;
          bool1 = bool4;
          bool2 = bool3;
        }
      }
    }
    int j = mRootMenu.size();
    int i = 0;
    if (i < j)
    {
      localObject = mRootMenu.getItem(i);
      switch (((MenuItem)localObject).getItemId())
      {
      case 2131820916: 
      case 2131820917: 
      default: 
        ((MenuItem)localObject).setEnabled(false);
      }
      for (;;)
      {
        i += 1;
        break;
        if (bool5)
        {
          ((MenuItem)localObject).setVisible(false);
        }
        else if ((!bool3) || (k != 0))
        {
          ((MenuItem)localObject).setVisible(true);
          ((MenuItem)localObject).setEnabled(false);
        }
        else if (m != 0)
        {
          ((MenuItem)localObject).setVisible(false);
        }
        else
        {
          ((MenuItem)localObject).setVisible(true);
          ((MenuItem)localObject).setEnabled(true);
          continue;
          if (bool5)
          {
            ((MenuItem)localObject).setVisible(false);
          }
          else if ((!bool3) || (k != 0))
          {
            ((MenuItem)localObject).setVisible(false);
          }
          else if (m != 0)
          {
            ((MenuItem)localObject).setVisible(true);
            ((MenuItem)localObject).setEnabled(true);
          }
          else
          {
            ((MenuItem)localObject).setVisible(false);
            continue;
            ((MenuItem)localObject).setEnabled(true);
            continue;
            ((MenuItem)localObject).setEnabled(bool4);
            continue;
            ((MenuItem)localObject).setEnabled(bool3);
            if (n != 0)
            {
              ((MenuItem)localObject).setEnabled(false);
              continue;
              ((MenuItem)localObject).setVisible(false);
              continue;
              ((MenuItem)localObject).setVisible(false);
            }
          }
        }
      }
    }
  }
  
  private void prepareNoneSelectionMenu()
  {
    boolean bool;
    int i;
    label37:
    MenuItem localMenuItem;
    if (this$0.getRecipients().size() > 1)
    {
      bool = this$0.mConversation.isPrivate();
      int j = mRootMenu.size();
      i = 0;
      if (i >= j) {
        return;
      }
      localMenuItem = mRootMenu.getItem(i);
      switch (localMenuItem.getItemId())
      {
      case 2131820916: 
      case 2131820917: 
      default: 
        localMenuItem.setEnabled(false);
      }
    }
    for (;;)
    {
      i += 1;
      break label37;
      break;
      if (bool) {
        localMenuItem.setVisible(false);
      }
      for (;;)
      {
        localMenuItem.setEnabled(false);
        break;
        localMenuItem.setVisible(true);
      }
      localMenuItem.setVisible(false);
      continue;
      localMenuItem.setVisible(false);
      continue;
      localMenuItem.setVisible(false);
    }
  }
  
  private void prepareSingleSelectionMenu()
  {
    Object localObject = getCheckedMessageItems();
    if (((List)localObject).size() != 1)
    {
      Log.e("ConversationBase", "prepareSingleSelectionMenu: expect size=1 but size=" + ((List)localObject).size());
      return;
    }
    localObject = (MessageItem)((List)localObject).get(0);
    boolean bool;
    if (this$0.mConversation.isPrivate())
    {
      mRootMenu.findItem(2131820914).setVisible(false);
      mRootMenu.findItem(2131820915).setVisible(false);
      mRootMenu.findItem(2131820913).setEnabled(true);
      mRootMenu.findItem(2131820912).setEnabled(((MessageItem)localObject).hasText());
      MenuItem localMenuItem = mRootMenu.findItem(2131820916);
      if (this$0.mConversation.isB2c()) {
        break label534;
      }
      bool = true;
      label174:
      localMenuItem.setEnabled(bool);
      if ((Build.IS_CM_CUSTOMIZATION_TEST) && (!((MessageItem)localObject).isGroup()))
      {
        mRootMenu.findItem(2131820919).setVisible(true);
        mRootMenu.findItem(2131820919).setEnabled(((MessageItem)localObject).hasText());
      }
      if (!((MessageItem)localObject).isMms()) {
        break label585;
      }
      if (!ConversationBase.access$1900(this$0, (MessageItem)localObject)) {
        break label539;
      }
      mRootMenu.findItem(2131820917).setEnabled(true);
      label271:
      if (((MessageItem)localObject).getMx2Type() != 3) {
        break label561;
      }
      mRootMenu.findItem(2131820911).setEnabled(false);
    }
    for (;;)
    {
      mRootMenu.findItem(2131820918).setVisible(false);
      return;
      if ((!((MessageItem)localObject).isGroup()) && (((MessageItem)localObject).isDownloaded()) && (this$0.mConversation.getThreadId() == ((MessageItem)localObject).getThreadId()))
      {
        if (((MessageItem)localObject).isLocked())
        {
          mRootMenu.findItem(2131820915).setVisible(true);
          mRootMenu.findItem(2131820915).setEnabled(true);
          mRootMenu.findItem(2131820914).setVisible(false);
          break;
        }
        if (((MessageItem)localObject).isLocked()) {
          break;
        }
        mRootMenu.findItem(2131820915).setVisible(false);
        mRootMenu.findItem(2131820914).setVisible(true);
        mRootMenu.findItem(2131820914).setEnabled(true);
        break;
      }
      mRootMenu.findItem(2131820915).setVisible(false);
      mRootMenu.findItem(2131820914).setVisible(true);
      mRootMenu.findItem(2131820914).setEnabled(false);
      break;
      label534:
      bool = false;
      break label174;
      label539:
      mRootMenu.findItem(2131820917).setEnabled(false);
      break label271;
      label561:
      mRootMenu.findItem(2131820911).setEnabled(((MessageItem)localObject).isDownloaded());
    }
    label585:
    mRootMenu.findItem(2131820917).setEnabled(false);
    mRootMenu.findItem(2131820911).setEnabled(true);
    if ((MSimUtils.getInsertedSimCount() > 0) && (!this$0.mConversation.isB2c()))
    {
      mRootMenu.findItem(2131820918).setVisible(true);
      return;
    }
    mRootMenu.findItem(2131820918).setVisible(false);
  }
  
  private void showEditModeAnimation(boolean paramBoolean)
  {
    int i = 0;
    while (i < this$0.mMsgListView.getChildCount())
    {
      View localView = this$0.mMsgListView.getChildAt(i);
      if ((localView instanceof MessageListItem)) {
        ((MessageListItem)localView).showEditModeAnimation(paramBoolean);
      }
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
        break label107;
      }
      mEditActionMode.setButton(16908314, R.string.action_mode_deselect_all);
    }
    for (;;)
    {
      if (paramInt <= 1) {
        break label124;
      }
      prepareMultipleSelectionMenu();
      return;
      str = this$0.getResources().getQuantityString(R.plurals.items_selected, mCheckable.count(), new Object[] { Integer.valueOf(mCheckable.count()) });
      break;
      label107:
      mEditActionMode.setButton(16908314, R.string.action_mode_select_all);
    }
    label124:
    if (paramInt == 1)
    {
      prepareSingleSelectionMenu();
      return;
    }
    prepareNoneSelectionMenu();
  }
  
  public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
  {
    handleMenuItemClick(paramActionMode, paramMenuItem);
    int i = paramMenuItem.getItemId();
    if ((2131820911 == i) || (2131820912 == i) || (2131820916 == i) || (2131820919 == i)) {
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
    this$0.getMenuInflater().inflate(2131755012, paramMenu);
    this$0.mMsgListView.setAllowTranscriptOnResize(false);
    this$0.mMsgListAdapter.enterCheckMode();
    mCheckable = this$0.mMsgListView.getEditableListViewCheckable();
    mEditActionMode = ((EditActionMode)paramActionMode);
    updateMenu(0);
    this$0.disableAttachmentPanel();
    this$0.hideSoftKeyboard();
    this$0.mBottomPanel.setVisibility(8);
    this$0.mContactPanel.setVisibility(8);
    this$0.setTopPlaceholderHeight(0);
    this$0.mMessageListTopForeground.setVisibility(8);
    this$0.mMessageListBottomForeground.setVisibility(8);
    showEditModeAnimation(true);
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
    this$0.mBottomPanel.setVisibility(0);
    this$0.setTopPlaceholderHeight(this$0.mTitleBarTallBgHeight);
    this$0.mMessageListTopForeground.setVisibility(0);
    this$0.mMessageListBottomForeground.setVisibility(0);
    showEditModeAnimation(false);
  }
  
  public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    return false;
  }
  
  public void onVisibleViewCheckStateChanged(View paramView, boolean paramBoolean) {}
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationBase.ModeCallback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */