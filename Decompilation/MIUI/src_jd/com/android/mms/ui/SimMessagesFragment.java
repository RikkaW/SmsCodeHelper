package com.android.mms.ui;

import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Telephony.Sms.Inbox;
import android.provider.Telephony.Sms.Sent;
import android.text.TextUtils;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.mms.util.EditableListView;
import com.android.mms.util.EditableListView.EditModeListener;
import com.android.mms.util.EditableListView.EditableListViewCheckable;
import com.android.mms.util.MSimUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import miui.R.id;
import miui.R.plurals;
import miui.R.string;
import miui.app.ActionBar;
import miui.app.Activity;
import miui.os.Build;
import miui.view.EditActionMode;
import miui.view.ViewPager;

public class SimMessagesFragment
  extends Fragment
{
  public static float sTextSize;
  private Activity mActivity;
  private ContentResolver mContentResolver;
  private Uri mCurrentIccUri = null;
  private int mCurrentSlotId = 0;
  private TextView mEmptyView;
  private MessageListAdapter mListAdapter = null;
  private EditableListView mListView;
  private int mMessageCount = 0;
  private ProgressBar mProgressBar;
  private QueryHandler mQueryHandler = null;
  private final ContentObserver mSimChangeObserver = new ContentObserver(new Handler())
  {
    public void onChange(boolean paramAnonymousBoolean)
    {
      Log.d("SimMessagesFragment", "sim message change");
      SimMessagesFragment.this.startQuery();
    }
  };
  private int mState;
  
  private void asyncDelete(String paramString)
  {
    if (MSimUtils.isMSim()) {}
    for (paramString = mCurrentIccUri.buildUpon().appendQueryParameter("msgIndex", paramString).build();; paramString = mCurrentIccUri.buildUpon().appendPath(paramString).build())
    {
      mQueryHandler.startDelete(1, null, paramString, null, null);
      return;
    }
  }
  
  private void confirmDeleteDialog(DialogInterface.OnClickListener paramOnClickListener, CharSequence paramCharSequence)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(mActivity);
    localBuilder.setTitle(2131361915);
    localBuilder.setIcon(17301543);
    localBuilder.setCancelable(true);
    localBuilder.setPositiveButton(2131361891, paramOnClickListener);
    localBuilder.setNegativeButton(2131361892, null);
    localBuilder.setMessage(paramCharSequence);
    localBuilder.show();
  }
  
  private void copyToPhoneMemory(Cursor paramCursor)
  {
    String str1 = paramCursor.getString(paramCursor.getColumnIndexOrThrow("address"));
    String str2 = paramCursor.getString(paramCursor.getColumnIndexOrThrow("body"));
    long l1 = paramCursor.getLong(paramCursor.getColumnIndexOrThrow("date"));
    boolean bool = isIncomingMessage(paramCursor);
    i = 2131362243;
    if (bool) {}
    for (;;)
    {
      try
      {
        paramCursor = Telephony.Sms.Inbox.CONTENT_URI;
        long l2 = MSimUtils.getSimIdBySlotId(mCurrentSlotId);
        paramCursor = paramCursor.buildUpon().appendQueryParameter("check_duplication", "1").build();
        ContentResolver localContentResolver = mContentResolver;
        if (!bool) {
          continue;
        }
        l1 = Long.valueOf(l1).longValue();
        MSimUtils.addMessageToUri(localContentResolver, paramCursor, str1, str2, null, Long.valueOf(l1), true, false, -1L, l2);
      }
      catch (SQLiteException paramCursor)
      {
        SqliteWrapper.checkSQLiteException(mActivity, paramCursor);
        i = 2131362244;
        continue;
      }
      Toast.makeText(mActivity, i, 0).show();
      return;
      paramCursor = Telephony.Sms.Sent.CONTENT_URI;
      continue;
      l1 = System.currentTimeMillis();
    }
  }
  
  private void deleteFromSim(HashSet<Integer> paramHashSet)
  {
    Object localObject = mListAdapter.getCursor();
    if (localObject != null)
    {
      ArrayList localArrayList = new ArrayList();
      paramHashSet = paramHashSet.iterator();
      while (paramHashSet.hasNext())
      {
        ((Cursor)localObject).moveToPosition(((Integer)paramHashSet.next()).intValue());
        localArrayList.add(((Cursor)localObject).getString(((Cursor)localObject).getColumnIndexOrThrow("index_on_icc")));
      }
      paramHashSet = localArrayList.iterator();
      while ((paramHashSet.hasNext()) && (!mActivity.isFinishing()))
      {
        localObject = (String)paramHashSet.next();
        if (!TextUtils.isEmpty((CharSequence)localObject))
        {
          localObject = TextUtils.split((String)localObject, ";");
          int j = localObject.length;
          int i = 0;
          while (i < j)
          {
            localArrayList = localObject[i];
            if (!TextUtils.isEmpty(localArrayList)) {
              asyncDelete(localArrayList);
            }
            i += 1;
          }
        }
      }
    }
    mListView.exitEditMode();
  }
  
  private void forwardMessage(Cursor paramCursor)
  {
    paramCursor = paramCursor.getString(paramCursor.getColumnIndexOrThrow("body"));
    Intent localIntent = new Intent(mActivity, NewMessageActivity.class);
    localIntent.putExtra("exit_on_sent", true);
    localIntent.putExtra("sms_body", paramCursor);
    localIntent.putExtra("forwarded_message", true);
    startActivity(localIntent);
  }
  
  private int getState()
  {
    return mState;
  }
  
  private boolean isIncomingMessage(Cursor paramCursor)
  {
    int i = paramCursor.getInt(paramCursor.getColumnIndexOrThrow("status"));
    return (i == 1) || (i == 3);
  }
  
  private void setCurrentSlotInfo()
  {
    mCurrentSlotId = getArguments().getInt(MSimUtils.SLOT_ID);
    if (mCurrentSlotId == 0) {
      mCurrentIccUri = Uri.parse("content://sms/icc");
    }
    while (mCurrentSlotId != 1) {
      return;
    }
    mCurrentIccUri = Uri.parse("content://sms/icc2");
  }
  
  private void startQuery()
  {
    updateState(2);
    try
    {
      mQueryHandler.cancelOperation(0);
      mQueryHandler.startQuery(0, null, mCurrentIccUri, null, null, null, null);
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      SqliteWrapper.checkSQLiteException(mActivity, localSQLiteException);
      updateState(1);
    }
  }
  
  private void updateState(int paramInt)
  {
    if (mState == paramInt) {
      return;
    }
    mState = paramInt;
    switch (paramInt)
    {
    default: 
      Log.e("SimMessagesFragment", "Invalid State");
      return;
    case 0: 
      mListView.setVisibility(0);
      mEmptyView.setVisibility(8);
      mProgressBar.setVisibility(8);
      mListView.requestFocus();
      return;
    case 1: 
      mListView.setVisibility(8);
      mEmptyView.setVisibility(0);
      mProgressBar.setVisibility(8);
      return;
    }
    mListView.setVisibility(8);
    mEmptyView.setVisibility(8);
    mProgressBar.setVisibility(0);
  }
  
  public int getMessageCount()
  {
    return mMessageCount;
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    mActivity = ((Activity)getActivity());
    mContentResolver = mActivity.getContentResolver();
    mQueryHandler = new QueryHandler(mContentResolver);
    paramLayoutInflater = paramLayoutInflater.inflate(2130968703, null);
    mListView = ((EditableListView)paramLayoutInflater.findViewById(2131820865));
    mEmptyView = ((TextView)paramLayoutInflater.findViewById(2131820866));
    mProgressBar = ((ProgressBar)paramLayoutInflater.findViewById(2131820795));
    mListView.setEditModeListener(new ModeCallback(null));
    if (Build.IS_CM_CUSTOMIZATION_TEST) {
      mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          paramAnonymousInt -= mListView.getFirstVisiblePosition();
          if ((paramAnonymousInt >= 0) && (paramAnonymousInt < mListView.getChildCount()) && ((mListView.getChildAt(paramAnonymousInt) instanceof MessageListItem))) {
            ((MessageListItem)mListView.getChildAt(paramAnonymousInt)).onMessageListItemClick();
          }
        }
      });
    }
    setCurrentSlotInfo();
    startQuery();
    return paramLayoutInflater;
  }
  
  public void onDestroyView()
  {
    if ((mListAdapter != null) && (mListAdapter.getCursor() != null)) {
      mListAdapter.changeCursor(null);
    }
    mListView.exitEditMode();
    super.onDestroyView();
  }
  
  public void onPause()
  {
    mContentResolver.unregisterContentObserver(mSimChangeObserver);
    super.onPause();
  }
  
  public void onResume()
  {
    super.onResume();
    if (getState() == 1)
    {
      Log.d("SimMessagesFragment", "onResume state is show empty");
      startQuery();
    }
    mContentResolver.registerContentObserver(mCurrentIccUri, true, mSimChangeObserver);
  }
  
  private class ModeCallback
    implements EditableListView.EditModeListener
  {
    private EditableListView.EditableListViewCheckable mCheckable;
    private EditActionMode mEditActionMode;
    private Menu mRootMenu;
    
    private ModeCallback() {}
    
    private void setTabsMode(boolean paramBoolean)
    {
      ActionBar localActionBar = mActivity.getActionBar();
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
      View localView = mActivity.getWindow().findViewById(R.id.view_pager);
      if ((localView instanceof ViewPager)) {
        ((ViewPager)localView).setDraggable(paramBoolean);
      }
    }
    
    private void updateMenu(int paramInt)
    {
      String str;
      if (paramInt == 0)
      {
        str = getString(R.string.action_mode_title_empty);
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
        str = getResources().getQuantityString(R.plurals.items_selected, mCheckable.count(), new Object[] { Integer.valueOf(mCheckable.count()) });
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
          paramMenuItem = (Cursor)mListAdapter.getItem(i);
          SimMessagesFragment.this.copyToPhoneMemory(paramMenuItem);
          paramActionMode.finish();
          continue;
          paramMenuItem = mCheckable.getCheckedItemInPositions();
          if (paramMenuItem.size() > 0)
          {
            i = ((Integer)paramMenuItem.iterator().next()).intValue();
            paramMenuItem = (Cursor)mListAdapter.getItem(i);
            SimMessagesFragment.this.forwardMessage(paramMenuItem);
            paramActionMode.finish();
            continue;
            paramActionMode = mCheckable.getCheckedItemInPositions();
            SimMessagesFragment.this.confirmDeleteDialog(new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
              {
                SimMessagesFragment.this.updateState(2);
                SimMessagesFragment.this.deleteFromSim(paramActionMode);
                paramAnonymousDialogInterface.dismiss();
              }
            }, getResources().getString(2131361797));
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
      mListAdapter.setCheckedItem(localHashSet);
      mCheckable = paramEditableListViewCheckable;
      updateMenu(mCheckable.count());
    }
    
    public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      mRootMenu = paramMenu;
      mActivity.getMenuInflater().inflate(2131755011, paramMenu);
      mListAdapter.enterCheckMode();
      mCheckable = mListView.getEditableListViewCheckable();
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
      mListAdapter.exitCheckMode();
    }
    
    public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      return false;
    }
    
    public void onVisibleViewCheckStateChanged(View paramView, boolean paramBoolean) {}
  }
  
  private class QueryHandler
    extends AsyncQueryHandler
  {
    public QueryHandler(ContentResolver paramContentResolver)
    {
      super();
    }
    
    protected void onQueryComplete(int paramInt, Object paramObject, Cursor paramCursor)
    {
      if (!isResumed())
      {
        if (paramCursor != null) {}
        try
        {
          paramCursor.close();
          Log.d("SimMessagesFragment", "onQueryComplete cursor close");
          SimMessagesFragment.this.updateState(1);
          return;
        }
        finally
        {
          Log.d("SimMessagesFragment", "onQueryComplete cursor close");
        }
      }
      int j;
      int i;
      if (paramCursor != null)
      {
        j = 0;
        i = 0;
        paramInt = j;
      }
      for (;;)
      {
        try
        {
          if (!paramCursor.moveToFirst())
          {
            paramInt = j;
            SimMessagesFragment.access$302(SimMessagesFragment.this, 0);
            paramInt = j;
            SimMessagesFragment.this.updateState(1);
            paramInt = 1;
            i = 1;
            Log.d("SimMessagesFragment", "cursor is empty");
          }
          if (i != 0) {
            paramCursor.close();
          }
          if (i == 0)
          {
            if (mListAdapter == null)
            {
              SimMessagesFragment.access$402(SimMessagesFragment.this, new MessageListAdapter(mActivity, paramCursor, mListView, false, null, null, 0L));
              mListAdapter.setMsgListItemHandler(new Handler());
              mListView.setAdapter(mListAdapter);
              SimMessagesFragment.this.updateState(0);
              mListAdapter.setTextSize(SimMessagesFragment.sTextSize);
              SimMessagesFragment.access$302(SimMessagesFragment.this, mListAdapter.getCount());
              Log.d("SimMessagesFragment", "onQueryComplete change cursor");
            }
          }
          else
          {
            if (!Build.IS_CM_CUSTOMIZATION_TEST) {
              break;
            }
            ((ManageSimMessages)mActivity).updateSimInfo(mCurrentSlotId);
            return;
          }
        }
        finally
        {
          if (paramInt != 0) {
            paramCursor.close();
          }
        }
        mListAdapter.changeCursor(paramCursor);
        SimMessagesFragment.this.updateState(0);
        continue;
        SimMessagesFragment.access$302(SimMessagesFragment.this, 0);
        SimMessagesFragment.this.updateState(1);
        Log.d("SimMessagesFragment", "cursor is null");
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SimMessagesFragment
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */