package com.android.mms.ui;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.mms.data.FestivalDatabase;
import miui.app.ActionBar;
import miui.app.Activity;

public class FestivalSmsList
  extends Activity
  implements AbsListView.OnScrollListener
{
  private ActionBar mActionBar;
  private Activity mActivity;
  private long mCategoryId;
  private View mDownloadingView;
  private TextView mEmptyView;
  private AsyncTask<Void, Void, Boolean> mGettingMoreTask;
  private boolean mIsAtLastItem;
  private boolean mIsGettingMessages;
  private FestivalSmsListAdapter mListAdapter;
  private ListView mListView;
  private String mMessageBody;
  private AdapterView.OnItemClickListener mOnClickListener = new AdapterView.OnItemClickListener()
  {
    public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
    {
      FestivalSmsList.this.onListItemClick((ListView)paramAnonymousAdapterView, paramAnonymousView, paramAnonymousInt, paramAnonymousLong);
    }
  };
  private boolean mPickerMode;
  private Dialog mShowNetworkingDlg;
  private View mShuffleView;
  
  private void checkAndShowNetworkingDialog()
  {
    View localView = View.inflate(this, 2130968609, null);
    final CheckBox localCheckBox = (CheckBox)localView.findViewById(2131820639);
    localCheckBox.setChecked(true);
    mShowNetworkingDlg = new AlertDialog.Builder(this).setPositiveButton(getString(2131362337), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        if (localCheckBox.isChecked()) {
          FestivalFragment.setAllowNetworkingDialog(mActivity, false);
        }
        FestivalFragment.setAllowNetworking(mActivity, true);
        FestivalSmsList.this.startGettingMoreMessages();
      }
    }).setNegativeButton(17039360, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        if (localCheckBox.isChecked()) {
          FestivalFragment.setAllowNetworkingDialog(mActivity, false);
        }
        FestivalFragment.setAllowNetworking(mActivity, false);
      }
    }).setView(localView).setCancelable(false).show();
  }
  
  private void loadCategoryInfo()
  {
    Object localObject1 = FestivalDatabase.getInstance();
    String str1 = "category_id=" + mCategoryId;
    localObject1 = ((FestivalDatabase)localObject1).query("categories", new String[] { "title", "image_text" }, str1, null, null, null, null);
    if (localObject1 == null) {
      return;
    }
    try
    {
      if (((Cursor)localObject1).moveToFirst())
      {
        str1 = ((Cursor)localObject1).getString(0);
        String str2 = ((Cursor)localObject1).getString(1);
        if (!TextUtils.isEmpty(str2))
        {
          View localView = getLayoutInflater().inflate(2130968608, null);
          ((TextView)localView.findViewById(2131820638)).setText(str2);
          mListView.addHeaderView(localView, null, false);
        }
        mActionBar.setTitle(str1);
      }
      return;
    }
    finally
    {
      ((Cursor)localObject1).close();
    }
  }
  
  private void onListItemClick(ListView paramListView, View paramView, int paramInt, long paramLong)
  {
    paramInt -= mListView.getHeaderViewsCount();
    if (paramInt < 0) {}
    do
    {
      return;
      if (mPickerMode)
      {
        paramListView = mListAdapter.getItemMessage(paramInt);
        paramView = new Intent();
        paramView.putExtra("android.intent.extra.shortcut.NAME", paramListView);
        setResult(-1, paramView);
        finish();
        return;
      }
    } while (paramView.getParent() == null);
    paramView.showContextMenu();
  }
  
  private void startGettingMoreMessages()
  {
    if (mIsGettingMessages) {
      return;
    }
    mIsGettingMessages = true;
    mDownloadingView.setVisibility(0);
    mGettingMoreTask = new AsyncTask()
    {
      protected Boolean doInBackground(Void... paramAnonymousVarArgs)
      {
        return Boolean.valueOf(FestivalDatabase.getInstance().getMoreMessages(mCategoryId));
      }
      
      protected void onPostExecute(Boolean paramAnonymousBoolean)
      {
        FestivalSmsList.access$202(FestivalSmsList.this, null);
        if (!paramAnonymousBoolean.booleanValue()) {
          Toast.makeText(FestivalSmsList.this, 2131362060, 0).show();
        }
        for (;;)
        {
          FestivalSmsList.access$302(FestivalSmsList.this, false);
          mDownloadingView.setVisibility(8);
          return;
          mListAdapter.requery();
        }
      }
    }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
  }
  
  public boolean onContextItemSelected(MenuItem paramMenuItem)
  {
    int i = getMenuInfoposition;
    int j = mListView.getHeaderViewsCount();
    mMessageBody = mListAdapter.getItemMessage(i - j);
    switch (paramMenuItem.getItemId())
    {
    default: 
      return false;
    case 2131361816: 
      paramMenuItem = new Intent(this, NewMessageActivity.class);
      paramMenuItem.putExtra("sms_body", mMessageBody);
      paramMenuItem.putExtra("forwarded_message", true);
      startActivity(paramMenuItem);
      return true;
    }
    ((ClipboardManager)getSystemService("clipboard")).setText(mMessageBody);
    return true;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    mActivity = this;
    setContentView(2130968615);
    paramBundle = getIntent();
    mCategoryId = paramBundle.getLongExtra("category_id", -1L);
    mDownloadingView = findViewById(2131820642);
    mListView = ((ListView)findViewById(2131820644));
    mListView.setOnScrollListener(this);
    mListView.setOnItemClickListener(mOnClickListener);
    mListView.setEmptyView(mEmptyView);
    MessageUtils.setListViewTouchPadding(mListView);
    paramBundle = paramBundle.getAction();
    if ((paramBundle != null) && (paramBundle.equals("android.intent.action.PICK"))) {}
    for (boolean bool = true;; bool = false)
    {
      mPickerMode = bool;
      if (!mPickerMode) {
        registerForContextMenu(mListView);
      }
      mIsAtLastItem = false;
      mIsGettingMessages = false;
      mActionBar = getActionBar();
      mActionBar.setHomeButtonEnabled(true);
      mActionBar.setCustomView(2130968617);
      mActionBar.setDisplayShowCustomEnabled(true);
      mShuffleView = mActionBar.getCustomView().findViewById(2131820646);
      mShuffleView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          mListAdapter.shuffle();
        }
      });
      loadCategoryInfo();
      mListAdapter = new FestivalSmsListAdapter(this, mCategoryId);
      mListView.setAdapter(mListAdapter);
      return;
    }
  }
  
  public void onCreateContextMenu(ContextMenu paramContextMenu, View paramView, ContextMenu.ContextMenuInfo paramContextMenuInfo)
  {
    super.onCreateContextMenu(paramContextMenu, paramView, paramContextMenuInfo);
    paramContextMenu.add(0, 2131361816, 0, 2131361816);
    paramContextMenu.add(0, 2131362080, 0, 2131362080);
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default: 
      return false;
    }
    finish();
    return true;
  }
  
  public void onResume()
  {
    super.onResume();
    if (!MessageUtils.shouldShowFestival(this)) {
      finish();
    }
  }
  
  public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt3 == paramInt1 + paramInt2) {}
    for (boolean bool = true;; bool = false)
    {
      mIsAtLastItem = bool;
      return;
    }
  }
  
  public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
  {
    if ((paramInt == 0) && (mDownloadingView.getVisibility() == 8) && (mIsAtLastItem))
    {
      if (!FestivalFragment.isAllowNetworkingDialog(mActivity)) {
        break label38;
      }
      checkAndShowNetworkingDialog();
    }
    label38:
    while (!FestivalFragment.isAllowNetworking(this)) {
      return;
    }
    startGettingMoreMessages();
  }
  
  protected void onStart()
  {
    super.onStart();
    mListAdapter.requery();
  }
  
  protected void onStop()
  {
    super.onStop();
    if (mGettingMoreTask != null)
    {
      mGettingMoreTask.cancel(true);
      mGettingMoreTask = null;
    }
    if (mShowNetworkingDlg != null)
    {
      mShowNetworkingDlg.dismiss();
      mShowNetworkingDlg = null;
    }
    mListAdapter.close();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.FestivalSmsList
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */