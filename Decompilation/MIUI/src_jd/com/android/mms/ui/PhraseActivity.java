package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnShowListener;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import miui.app.ActionBar;
import miui.app.Activity;
import miui.app.AlertDialog;
import miui.app.AlertDialog.Builder;

public class PhraseActivity
  extends Activity
{
  private ActionBar mActionBar;
  private Activity mActivity;
  private ListView mList;
  private AdapterView.OnItemClickListener mOnClickListener = new AdapterView.OnItemClickListener()
  {
    public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
    {
      PhraseActivity.this.onListItemClick((ListView)paramAnonymousAdapterView, paramAnonymousView, paramAnonymousInt, paramAnonymousLong);
    }
  };
  private PhraseListAdapter mPhraseListAdapter;
  private SelectedMessage mSelectedMessage;
  
  private void onListItemClick(ListView paramListView, View paramView, int paramInt, long paramLong)
  {
    if (paramInt - mList.getHeaderViewsCount() < 0) {}
    while (paramView.getParent() == null) {
      return;
    }
    paramView.showContextMenu();
  }
  
  public void onBackPressed()
  {
    setResult(-1, null);
    super.onBackPressed();
  }
  
  public boolean onContextItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default: 
      return super.onContextItemSelected(paramMenuItem);
    case 1: 
      mSelectedMessage.edit();
      return true;
    }
    mSelectedMessage.delete();
    return true;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968687);
    mActivity = this;
    mSelectedMessage = new SelectedMessage();
    mPhraseListAdapter = new PhraseListAdapter(this, 2130968685);
    mList = ((ListView)findViewById(2131820842));
    mList.setAdapter(mPhraseListAdapter);
    mList.setOnCreateContextMenuListener(this);
    mList.setOnItemClickListener(mOnClickListener);
    MessageUtils.setListViewTouchPadding(mList);
    paramBundle = (TextView)findViewById(2131820564);
    if (paramBundle != null)
    {
      paramBundle.setText(getResources().getString(2131362103));
      mList.setEmptyView(paramBundle);
    }
    mActionBar = getActionBar();
    mActionBar.setTitle(2131362096);
  }
  
  public void onCreateContextMenu(ContextMenu paramContextMenu, View paramView, ContextMenu.ContextMenuInfo paramContextMenuInfo)
  {
    super.onCreateContextMenu(paramContextMenu, paramView, paramContextMenuInfo);
    int i = position;
    paramView = (String)mPhraseListAdapter.getItem(i);
    mSelectedMessage.selectPhrase(i, paramView);
    paramContextMenu.setHeaderTitle(mSelectedMessage.getMessage());
    paramContextMenu.add(0, 1, 0, getResources().getString(2131361960));
    paramContextMenu.add(0, 2, 0, getResources().getString(2131362100));
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131755013, paramMenu);
    return true;
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    switch (paramInt)
    {
    default: 
      return super.onKeyDown(paramInt, paramKeyEvent);
    }
    MessageUtils.launchMessagePreference(this);
    return true;
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    }
    do
    {
      return false;
      setResult(-1, null);
      finish();
      return true;
    } while (mPhraseListAdapter.isFull());
    mSelectedMessage.selectPhrase(-1, null);
    mSelectedMessage.edit();
    return true;
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    return true;
  }
  
  public void onStart()
  {
    super.onStart();
    mPhraseListAdapter.load();
  }
  
  public void onStop()
  {
    super.onStop();
    mPhraseListAdapter.notifyDataSetInvalidated();
  }
  
  private class SelectedMessage
  {
    private AlertDialog mEditNewPhraseDialog;
    private TextView mEditNewPhraseView;
    private View mEditNewPhraseViewContainer;
    private boolean mNewPhrase;
    private Pair<String, Integer> mSelectedPhrase;
    
    public SelectedMessage()
    {
      buildEditNewPhraseDialog();
    }
    
    private void buildEditNewPhraseDialog()
    {
      mEditNewPhraseViewContainer = LayoutInflater.from(mActivity).inflate(2130968684, null);
      mEditNewPhraseView = ((TextView)mEditNewPhraseViewContainer.findViewById(2131820839));
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(mActivity);
      localBuilder.setCancelable(true);
      localBuilder.setView(mEditNewPhraseViewContainer);
      localBuilder.setOnShowListener(new DialogInterface.OnShowListener()
      {
        public void onShow(DialogInterface paramAnonymousDialogInterface)
        {
          MessageUtils.requestInputMethod(mActivity, mEditNewPhraseView);
        }
      });
      localBuilder.setPositiveButton(2131361998, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface = mEditNewPhraseView.getText().toString();
          if (mNewPhrase)
          {
            PhraseActivity.SelectedMessage.access$502(PhraseActivity.SelectedMessage.this, false);
            if (TextUtils.isEmpty(paramAnonymousDialogInterface)) {
              return;
            }
            mPhraseListAdapter.addPhrase(paramAnonymousDialogInterface);
            return;
          }
          if (TextUtils.isEmpty(paramAnonymousDialogInterface))
          {
            delete();
            return;
          }
          mPhraseListAdapter.setPhrase(((Integer)mSelectedPhrase.second).intValue(), paramAnonymousDialogInterface);
        }
      });
      localBuilder.setNegativeButton(2131361892, null);
      mEditNewPhraseDialog = localBuilder.create();
    }
    
    public void delete()
    {
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(mActivity);
      localBuilder.setTitle(2131362100);
      localBuilder.setMessage(2131362101);
      localBuilder.setCancelable(true);
      localBuilder.setPositiveButton(2131361930, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          mPhraseListAdapter.deletePhrase(((Integer)mSelectedPhrase.second).intValue());
        }
      });
      localBuilder.setNegativeButton(2131361892, null);
      localBuilder.show();
    }
    
    public void edit()
    {
      String str;
      if (!mNewPhrase) {
        str = (String)mSelectedPhrase.first;
      }
      for (int i = 2131362098;; i = 2131362097)
      {
        mEditNewPhraseView.setText(str);
        mEditNewPhraseDialog.setTitle(getResources().getString(i));
        mEditNewPhraseDialog.show();
        return;
        str = null;
      }
    }
    
    public String getMessage()
    {
      return (String)mSelectedPhrase.first;
    }
    
    public void selectPhrase(int paramInt, String paramString)
    {
      if (paramString == null) {}
      for (mNewPhrase = true;; mNewPhrase = false)
      {
        mSelectedPhrase = new Pair(paramString, Integer.valueOf(paramInt));
        return;
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.PhraseActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */