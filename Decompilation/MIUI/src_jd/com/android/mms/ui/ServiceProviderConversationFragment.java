package com.android.mms.ui;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SqliteWrapper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.android.mms.MmsApp;
import com.android.mms.data.Conversation;
import java.util.ArrayList;
import java.util.List;
import miui.R.drawable;
import miui.os.Build;
import miui.widget.DropDownSingleChoiceMenu;
import miui.widget.DropDownSingleChoiceMenu.OnMenuListener;

public class ServiceProviderConversationFragment
  extends ConversationFragment
{
  private View mCategoryTitle;
  private boolean mHasMenu;
  protected final AdapterView.OnItemClickListener mOnClickListener = new AdapterView.OnItemClickListener()
  {
    public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
    {
      if ((paramAnonymousInt == 0) && (mHasMenu)) {
        ServiceProviderConversationFragment.this.showServiceCategoryList();
      }
      while (paramAnonymousInt == mListView.getAdapter().getCount() - 1) {
        return;
      }
      paramAnonymousAdapterView = (Cursor)mListView.getItemAtPosition(paramAnonymousInt);
      paramAnonymousLong = Conversation.from(mActivity, paramAnonymousAdapterView).getThreadId();
      Log.d("ServiceProviderConversationFragment", "onListItemClick: pos=" + paramAnonymousInt + ", view=" + paramAnonymousView + ", tid=" + paramAnonymousLong);
      paramAnonymousAdapterView = ComposeMessageRouterActivity.createIntent(mActivity, paramAnonymousLong);
      startActivity(paramAnonymousAdapterView);
    }
  };
  public int mSelectedCategory = 1;
  private List<Integer> mServiceCategoryList = new ArrayList(4);
  private DropDownSingleChoiceMenu mServiceCategoryMenu;
  private List<String> mServiceCategoryNameList = new ArrayList(4);
  private TextView mServiceCategoryText;
  private ImageView mTitleArrow;
  
  public ServiceProviderConversationFragment()
  {
    initServiceCategoryList();
  }
  
  private int getSelectedCategoryPosition(int paramInt)
  {
    int i = 0;
    while (i < mServiceCategoryList.size())
    {
      if (paramInt == ((Integer)mServiceCategoryList.get(i)).intValue()) {
        return i;
      }
      i += 1;
    }
    return 0;
  }
  
  private void initServiceCategoryList()
  {
    mServiceCategoryList.add(Integer.valueOf(1));
    mServiceCategoryList.add(Integer.valueOf(2));
    Application localApplication = MmsApp.getApp();
    mServiceCategoryNameList.add(localApplication.getString(2131362280));
    mServiceCategoryNameList.add(localApplication.getString(2131362281));
    mSelectedCategory = 1;
  }
  
  private void initServiceCategoryMenu()
  {
    mServiceCategoryMenu = new DropDownSingleChoiceMenu(mActivity);
    mServiceCategoryMenu.setItems(mServiceCategoryNameList);
    mServiceCategoryMenu.setAnchorView(mCategoryTitle);
    mServiceCategoryMenu.setOnMenuListener(new DropDownSingleChoiceMenu.OnMenuListener()
    {
      public void onDismiss()
      {
        mTitleArrow.setImageResource(R.drawable.expander_open_light);
      }
      
      public void onItemSelected(DropDownSingleChoiceMenu paramAnonymousDropDownSingleChoiceMenu, int paramAnonymousInt)
      {
        ServiceProviderConversationFragment.this.onPopupItemClicked(paramAnonymousInt);
      }
      
      public void onShow() {}
    });
  }
  
  private void onPopupItemClicked(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= mServiceCategoryList.size()) || (mSelectedCategory == ((Integer)mServiceCategoryList.get(paramInt)).intValue())) {
      return;
    }
    mSelectedCategory = ((Integer)mServiceCategoryList.get(paramInt)).intValue();
    if (mSelectedCategory != 1) {
      mListView.setHeaderHidden(false);
    }
    for (;;)
    {
      updateCategoryTitle((String)mServiceCategoryNameList.get(paramInt));
      startAsyncQuery(false);
      return;
      mListView.setHeaderHidden(true);
    }
  }
  
  private void showServiceCategoryList()
  {
    mTitleArrow.setImageResource(R.drawable.expander_close_light);
    mServiceCategoryMenu.setSelectedItem(getSelectedCategoryPosition(mSelectedCategory));
    mServiceCategoryMenu.show();
  }
  
  private void updateCategoryTitle(String paramString)
  {
    paramString = String.format(getActivity().getString(2131362279), new Object[] { paramString });
    mServiceCategoryText.setText(paramString);
  }
  
  protected void initListCompositeMode(Context paramContext)
  {
    mIsCompositeMode = false;
    mPlaceHolderType &= 0xFFFFFFFE;
    mPlaceHolderType &= 0xFFFFFFFD;
    if (mListAdapter != null)
    {
      mListAdapter.setCompositeMode(mIsCompositeMode);
      mListAdapter.setPlaceHolderType(mPlaceHolderType);
    }
  }
  
  protected void markConversationAsSeen()
  {
    Conversation.markServiceProviderConversationAsSeen(mActivity.getApplicationContext(), mSelectedCategory);
  }
  
  public void onCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater) {}
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    mActivity = getActivity();
    mQueryHandler = new ConversationFragment.ThreadListQueryHandler(this, mActivity.getContentResolver());
    mRootView = paramLayoutInflater.inflate(2130968600, null);
    mListView = ((MessageListPullView)mRootView.findViewById(16908298));
    mListView.setOnKeyListener(mThreadListKeyListener);
    mListView.setEditModeListener(new ConversationFragment.ModeCallback(this));
    mListView.setAllowTranscriptOnResize(false);
    boolean bool;
    if (!Build.checkRegion("IN"))
    {
      bool = true;
      mHasMenu = bool;
      if (!mHasMenu) {
        break label327;
      }
      mCategoryTitle = paramLayoutInflater.inflate(2130968702, null);
      mServiceCategoryText = ((TextView)mCategoryTitle.findViewById(2131820863));
      mListView.addHeaderView(mCategoryTitle);
      if (mSelectedCategory == 1) {
        break label316;
      }
      mListView.setHeaderHidden(false);
      label169:
      mTitleArrow = ((ImageView)mCategoryTitle.findViewById(2131820864));
      int i = getSelectedCategoryPosition(mSelectedCategory);
      updateCategoryTitle((String)mServiceCategoryNameList.get(i));
      initServiceCategoryMenu();
    }
    for (;;)
    {
      mListView.enableEmptyImgView(false);
      mListView.setEmptyImgViewImageResource(2130837594);
      mListAdapter = new ConversationListAdapter(mActivity, null);
      mListView.setAdapter(mListAdapter);
      mListView.setRecyclerListener(mListAdapter);
      mListView.setOnItemClickListener(mOnClickListener);
      mListView.setOnScrollListener(this);
      mListView.enableDragEvent(true);
      initialize();
      return mRootView;
      bool = false;
      break;
      label316:
      mListView.setHeaderHidden(true);
      break label169;
      label327:
      mListView.setHeaderHidden(false);
    }
  }
  
  protected void startAsyncQuery(boolean paramBoolean)
  {
    try
    {
      mQueryHandler.cancelOperation(1701);
      if (mHasMenu) {}
      for (int i = mSelectedCategory;; i = 1)
      {
        Conversation.startQueryForServiceProvider(mQueryHandler, 1701, i);
        if (paramBoolean) {
          startQueryTimedThreads();
        }
        return;
      }
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      SqliteWrapper.checkSQLiteException(mActivity, localSQLiteException);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ServiceProviderConversationFragment
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */