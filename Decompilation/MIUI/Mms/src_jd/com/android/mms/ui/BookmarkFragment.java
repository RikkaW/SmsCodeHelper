package com.android.mms.ui;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import com.google.android.collect.Lists;
import java.util.ArrayList;

public class BookmarkFragment
  extends ListFragment
  implements ISmsTextSizeAdjustHost
{
  private Activity mActivity;
  private BookmarkListAdapter mBookmarkListAdapter;
  private ListView mList;
  private boolean mPickerMode = false;
  private View mRootView;
  private SelectedMessage mSelectedMessage;
  
  public boolean onContextItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default: 
      return super.onContextItemSelected(paramMenuItem);
    case 2: 
      mSelectedMessage.forwardMessage();
      return true;
    case 3: 
      mSelectedMessage.delete();
      return true;
    case 0: 
      mSelectedMessage.viewOrigin();
      return true;
    }
    mSelectedMessage.copy();
    return true;
  }
  
  public void onCreateContextMenu(ContextMenu paramContextMenu, View paramView, ContextMenu.ContextMenuInfo paramContextMenuInfo)
  {
    super.onCreateContextMenu(paramContextMenu, paramView, paramContextMenuInfo);
    if (mPickerMode) {}
    do
    {
      do
      {
        return;
        int i = position;
        paramView = (Cursor)mBookmarkListAdapter.getItem(i);
        paramView = mBookmarkListAdapter.getCachedMessageItem(paramView);
      } while (paramView == null);
      mSelectedMessage.selectBookmark(paramView);
      paramContextMenu.setHeaderTitle(mSelectedMessage.getMessage());
      paramContextMenu.add(0, 0, 0, getResources().getString(2131362093));
      paramContextMenu.add(0, 1, 0, getResources().getString(2131361820));
      paramContextMenu.add(0, 2, 0, getResources().getString(2131361816));
      paramContextMenu.add(0, 3, 0, getResources().getString(2131362092));
    } while (paramView.getMx2Type() != 3);
    paramContextMenu.getItem(2).setEnabled(false);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    mRootView = paramLayoutInflater.inflate(2130968587, null);
    mActivity = getActivity();
    if ((mActivity instanceof PickerActivity)) {
      mPickerMode = true;
    }
    mSelectedMessage = new SelectedMessage(null);
    mBookmarkListAdapter = new BookmarkListAdapter(mActivity);
    mList = ((ListView)mRootView.findViewById(16908298));
    mList.setAdapter(mBookmarkListAdapter);
    mList.setOnCreateContextMenuListener(this);
    MessageUtils.setListViewTouchPadding(mList);
    paramLayoutInflater = mRootView.findViewById(2131820564);
    if (paramLayoutInflater != null) {
      mList.setEmptyView(paramLayoutInflater);
    }
    setHasOptionsMenu(true);
    return mRootView;
  }
  
  public void onDestroyView()
  {
    if (mBookmarkListAdapter != null) {
      mBookmarkListAdapter.changeCursor(null);
    }
    super.onDestroyView();
  }
  
  public void onListItemClick(ListView paramListView, View paramView, int paramInt, long paramLong)
  {
    paramInt -= mList.getHeaderViewsCount();
    if (paramInt < 0) {}
    do
    {
      return;
      if (mPickerMode)
      {
        paramListView = (Cursor)mBookmarkListAdapter.getItem(paramInt);
        mSelectedMessage.selectBookmark(mBookmarkListAdapter.getCachedMessageItem(paramListView));
        mSelectedMessage.returnPickerResult();
        return;
      }
    } while (paramView.getParent() == null);
    paramView.showContextMenu();
  }
  
  public void onStart()
  {
    super.onStart();
    mBookmarkListAdapter.load();
    SmsTextSizeAdjust.getInstance().init(this, mActivity);
    SmsTextSizeAdjust.getInstance().refresh();
  }
  
  public void onStop()
  {
    if (mBookmarkListAdapter != null) {
      mBookmarkListAdapter.stop();
    }
    super.onStop();
    SmsTextSizeAdjust.clear(this);
  }
  
  public void setTextSize(float paramFloat)
  {
    if (mBookmarkListAdapter != null) {
      mBookmarkListAdapter.setTextSize(paramFloat);
    }
  }
  
  private class SelectedMessage
  {
    private MessageItem mSelectedBookmark;
    
    private SelectedMessage() {}
    
    public void copy()
    {
      ArrayList localArrayList = new ArrayList(1);
      localArrayList.add(mSelectedBookmark);
      MessageUtils.copyMessageTextToClipboard(mActivity, localArrayList);
    }
    
    public void delete()
    {
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(mActivity);
      localBuilder.setTitle(2131362092);
      localBuilder.setMessage(2131362094);
      localBuilder.setIconAttribute(16843605);
      localBuilder.setCancelable(true);
      localBuilder.setPositiveButton(2131361891, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          mBookmarkListAdapter.deleteBookmark(mSelectedBookmark);
        }
      });
      localBuilder.setNegativeButton(2131361892, null);
      localBuilder.show();
    }
    
    public void forwardMessage()
    {
      MessageUtils.forwardMessage(mActivity, Lists.newArrayList(new MessageItem[] { mSelectedBookmark }), false);
    }
    
    public String getMessage()
    {
      return mSelectedBookmark.getBody();
    }
    
    public void returnPickerResult()
    {
      Intent localIntent = new Intent();
      if ((mSelectedBookmark.isMms()) && (mSelectedBookmark.isDownloaded()))
      {
        localIntent.putExtra("msg_uri", mSelectedBookmark.getMessageUri());
        localIntent.putExtra("subject", mSelectedBookmark.getSubject());
      }
      for (;;)
      {
        if (mPickerMode) {
          ((PickerActivity)mActivity).returnPickerResult(localIntent);
        }
        return;
        String str2 = mSelectedBookmark.getBody();
        String str1 = str2;
        if (str2 == null) {
          str1 = "";
        }
        localIntent.putExtra("android.intent.extra.shortcut.NAME", str1);
      }
    }
    
    public void selectBookmark(MessageItem paramMessageItem)
    {
      mSelectedBookmark = paramMessageItem;
    }
    
    public void viewOrigin()
    {
      long l = mSelectedBookmark.getThreadId();
      Intent localIntent = ComposeMessageRouterActivity.createIntent(mActivity, l);
      localIntent.setData(ComposeMessageRouterActivity.createViewOrginDataUri(l, mSelectedBookmark.getMsgId()));
      ComposeMessageRouterActivity.route(mActivity, localIntent);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.BookmarkFragment
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */