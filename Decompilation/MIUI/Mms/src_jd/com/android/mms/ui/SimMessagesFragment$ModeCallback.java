package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.database.Cursor;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import com.android.mms.util.EditableListView;
import com.android.mms.util.EditableListView.EditModeListener;
import com.android.mms.util.EditableListView.EditableListViewCheckable;
import java.util.HashSet;
import java.util.Iterator;
import miui.R.id;
import miui.R.plurals;
import miui.R.string;
import miui.app.ActionBar;
import miui.app.Activity;
import miui.view.EditActionMode;
import miui.view.ViewPager;

class SimMessagesFragment$ModeCallback
  implements EditableListView.EditModeListener
{
  private EditableListView.EditableListViewCheckable mCheckable;
  private EditActionMode mEditActionMode;
  private Menu mRootMenu;
  
  private SimMessagesFragment$ModeCallback(SimMessagesFragment paramSimMessagesFragment) {}
  
  private void setTabsMode(boolean paramBoolean)
  {
    ActionBar localActionBar = SimMessagesFragment.access$500(this$0).getActionBar();
    if (localActionBar != null)
    {
      bool = paramBoolean;
      if (!paramBoolean) {
        if (localActionBar.getFragmentTabCount() > 1) {
          break label37;
        }
      }
    }
    label37:
    for (boolean bool = true;; bool = false)
    {
      localActionBar.setTabsMode(bool);
      return;
    }
  }
  
  private void setViewPagerDraggable(boolean paramBoolean)
  {
    View localView = SimMessagesFragment.access$500(this$0).getWindow().findViewById(R.id.view_pager);
    if ((localView instanceof ViewPager)) {
      ((ViewPager)localView).setDraggable(paramBoolean);
    }
  }
  
  private void updateMenu(int paramInt)
  {
    String str;
    if (paramInt == 0)
    {
      str = this$0.getString(R.string.action_mode_title_empty);
      mRootMenu.findItem(2131820908).setEnabled(false);
      mRootMenu.findItem(2131820909).setEnabled(false);
      mRootMenu.findItem(2131820910).setEnabled(false);
    }
    for (;;)
    {
      ((ActionMode)mEditActionMode).setTitle(str);
      if (!mCheckable.isAllChecked()) {
        break;
      }
      mEditActionMode.setButton(16908314, R.string.action_mode_deselect_all);
      return;
      str = this$0.getResources().getQuantityString(R.plurals.items_selected, mCheckable.count(), new Object[] { Integer.valueOf(mCheckable.count()) });
      mRootMenu.findItem(2131820908).setEnabled(true);
      if (paramInt == 1)
      {
        mRootMenu.findItem(2131820909).setEnabled(true);
        mRootMenu.findItem(2131820910).setEnabled(true);
      }
      else
      {
        mRootMenu.findItem(2131820909).setEnabled(false);
        mRootMenu.findItem(2131820910).setEnabled(false);
      }
    }
    mEditActionMode.setButton(16908314, R.string.action_mode_select_all);
  }
  
  public boolean onActionItemClicked(final ActionMode paramActionMode, MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    }
    for (;;)
    {
      return true;
      paramMenuItem = mCheckable.getCheckedItemInPositions();
      if (paramMenuItem.size() > 0)
      {
        int i = ((Integer)paramMenuItem.iterator().next()).intValue();
        paramMenuItem = (Cursor)SimMessagesFragment.access$400(this$0).getItem(i);
        SimMessagesFragment.access$700(this$0, paramMenuItem);
        paramActionMode.finish();
        continue;
        paramMenuItem = mCheckable.getCheckedItemInPositions();
        if (paramMenuItem.size() > 0)
        {
          i = ((Integer)paramMenuItem.iterator().next()).intValue();
          paramMenuItem = (Cursor)SimMessagesFragment.access$400(this$0).getItem(i);
          SimMessagesFragment.access$800(this$0, paramMenuItem);
          paramActionMode.finish();
          continue;
          paramActionMode = mCheckable.getCheckedItemInPositions();
          SimMessagesFragment.access$1000(this$0, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
              SimMessagesFragment.access$200(this$0, 2);
              SimMessagesFragment.access$900(this$0, paramActionMode);
              paramAnonymousDialogInterface.dismiss();
            }
          }, this$0.getResources().getString(2131361797));
          continue;
          paramActionMode.finish();
          continue;
          if (mCheckable.isAllChecked()) {
            mCheckable.checkNothing();
          } else {
            mCheckable.checkAll();
          }
        }
      }
    }
  }
  
  public void onCheckStateChanged(EditableListView.EditableListViewCheckable paramEditableListViewCheckable)
  {
    HashSet localHashSet = paramEditableListViewCheckable.getCheckedItemInIds();
    SimMessagesFragment.access$400(this$0).setCheckedItem(localHashSet);
    mCheckable = paramEditableListViewCheckable;
    updateMenu(mCheckable.count());
  }
  
  public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    mRootMenu = paramMenu;
    SimMessagesFragment.access$500(this$0).getMenuInflater().inflate(2131755011, paramMenu);
    SimMessagesFragment.access$400(this$0).enterCheckMode();
    mCheckable = SimMessagesFragment.access$100(this$0).getEditableListViewCheckable();
    mEditActionMode = ((EditActionMode)paramActionMode);
    paramActionMode.setTitle(R.string.action_mode_title_empty);
    mEditActionMode.setButton(16908313, 17039360);
    mEditActionMode.setButton(16908314, R.string.action_mode_select_all);
    setViewPagerDraggable(false);
    setTabsMode(true);
    return true;
  }
  
  public void onDestroyActionMode(ActionMode paramActionMode)
  {
    setViewPagerDraggable(true);
    setTabsMode(false);
    SimMessagesFragment.access$400(this$0).exitCheckMode();
  }
  
  public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    return false;
  }
  
  public void onVisibleViewCheckStateChanged(View paramView, boolean paramBoolean) {}
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SimMessagesFragment.ModeCallback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */