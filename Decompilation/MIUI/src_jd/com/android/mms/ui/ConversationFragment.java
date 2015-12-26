package com.android.mms.ui;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.OnAccountsUpdateListener;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Application;
import android.app.Fragment;
import android.content.AsyncQueryHandler;
import android.content.ComponentName;
import android.content.ContentProviderOperation;
import android.content.ContentProviderOperation.Builder;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SqliteWrapper;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.preference.PreferenceManager;
import android.provider.MiuiSettings.AntiSpam;
import android.provider.MiuiSettings.MiuiVoip;
import android.provider.MiuiSettings.System;
import android.provider.Settings.System;
import android.provider.Telephony.Mms;
import android.provider.Telephony.MmsSms;
import android.provider.Telephony.Sms;
import android.text.TextUtils;
import android.util.Log;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.android.mms.MmsApp;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import com.android.mms.data.Conversation.TimedThreads;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.util.DraftCache;
import com.android.mms.util.DraftCache.OnDraftChangedListener;
import com.android.mms.util.EditableListView.EditModeListener;
import com.android.mms.util.EditableListView.EditableListViewCheckable;
import com.android.mms.util.MSimUtils;
import com.android.mms.util.MiStatSdkHelper;
import com.android.mms.util.SmsSyncInfoProviderWrapper;
import com.xiaomi.mms.transaction.MxActivateService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import miui.R.drawable;
import miui.R.layout;
import miui.R.plurals;
import miui.R.string;
import miui.accounts.ExtraAccountManager;
import miui.app.ProgressDialog;
import miui.cloud.util.SyncAlertHelper;
import miui.os.Build;
import miui.provider.ExtraTelephony;
import miui.provider.ExtraTelephony.MmsSms;
import miui.provider.ExtraTelephony.Phonelist;
import miui.telephony.PhoneNumberUtils.PhoneNumber;
import miui.telephony.SubscriptionManager.OnSubscriptionsChangedListener;
import miui.view.EditActionMode;
import miui.widget.MiCloudStateView;
import miui.widget.ScrollableViewDrawer;
import miui.yellowpage.YellowPageImgLoader;

public class ConversationFragment
  extends Fragment
  implements AbsListView.OnScrollListener, DraftCache.OnDraftChangedListener
{
  private static final String[] MMS_THREAD_ID_PROJECTION = { "thread_id" };
  private static final String[] SMS_THREAD_ID_PROJECTION;
  protected static final Uri UNSEEN_BLOCKED_MSG_COUNT_URI;
  protected static final Uri UNSEEN_SP_MSG_COUNT_URI = Uri.withAppendedPath(Telephony.MmsSms.CONTENT_URI, "unseenSpMsgCount");
  private static ProgressDialog mAddBlackListProgressDialog;
  private static ProgressDialog mDeleteProgressDialog;
  private static int mPrivateSmsUnlockDistance;
  protected Activity mActivity;
  private AsyncTask<Void, Void, Void> mAddBlackListTask;
  protected View mBookmarkView;
  protected boolean mBookmarkViewAdded;
  protected boolean mBookmarkVisible;
  protected View mCloudRecommendView;
  private MiCloudStateView mCloudStateView;
  private final ConversationListAdapter.OnContentChangedListener mContentChangedListener = new ConversationListAdapter.OnContentChangedListener()
  {
    public void onContentChanged(ConversationListAdapter paramAnonymousConversationListAdapter)
    {
      mUIHandler.removeMessages(1003);
      mUIHandler.sendEmptyMessageDelayed(1003, 50L);
    }
  };
  private int mFinishedTimedQuery = 0;
  protected boolean mIsCompositeMode;
  protected boolean mIsCreateFirstQuery = false;
  private boolean mIsMSimInserted;
  private boolean mIsSimpleMode;
  private boolean mIsStoped = false;
  protected ConversationListAdapter mListAdapter;
  protected MessageListPullView mListView;
  private boolean mNeedToMarkAsSeen;
  private miui.app.AlertDialog mNetworkDialog;
  private android.app.AlertDialog mOldMsgPromptDialog;
  private OnAccountsUpdateListener mOnAccountsUpdateListener;
  private final AdapterView.OnItemClickListener mOnClickListener = new AdapterView.OnItemClickListener()
  {
    public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
    {
      if (paramAnonymousInt == 1)
      {
        if ((mRootView instanceof ScrollableViewDrawer)) {
          ((ScrollableViewDrawer)mRootView).resetState();
        }
        ((MmsTabActivity)mActivity).startSearchMode(mSearchView, mRootView);
      }
      do
      {
        return;
        if ((paramAnonymousInt == 2) && (mBookmarkVisible))
        {
          paramAnonymousAdapterView = new Intent(mActivity, BookmarkActivity.class);
          startActivity(paramAnonymousAdapterView);
          return;
        }
      } while (paramAnonymousInt == mListView.getAdapter().getCount() - 1);
      paramAnonymousAdapterView = (Cursor)mListView.getItemAtPosition(paramAnonymousInt);
      paramAnonymousAdapterView = Conversation.from(mActivity, paramAnonymousAdapterView);
      paramAnonymousLong = paramAnonymousAdapterView.getThreadId();
      if (paramAnonymousLong == -100L)
      {
        paramAnonymousAdapterView = new Intent(MiuiSettings.AntiSpam.ACTION_SOURCE_SMS);
        paramAnonymousAdapterView.setComponent(new ComponentName("com.miui.antispam", "com.miui.antispam.ui.activity.MainActivity"));
        mActivity.startActivity(paramAnonymousAdapterView);
        return;
      }
      if (paramAnonymousLong == -101L)
      {
        paramAnonymousAdapterView = new Intent();
        paramAnonymousAdapterView.setAction("com.miui.mipub.action_open_threadlist");
        paramAnonymousAdapterView.setPackage("com.miui.mipub");
        startActivity(paramAnonymousAdapterView);
        return;
      }
      Log.d("ConversationFragment", "onListItemClick: pos=" + paramAnonymousInt + ", view=" + paramAnonymousView + ", tid=" + paramAnonymousLong);
      if ((mIsCompositeMode) && (paramAnonymousAdapterView.isServiceProvider()) && (!paramAnonymousAdapterView.isSticky()))
      {
        paramAnonymousAdapterView = new Intent(mActivity, ServiceProviderConversationListActivity.class);
        startActivity(paramAnonymousAdapterView);
        return;
      }
      paramAnonymousAdapterView = ComposeMessageRouterActivity.createIntent(mActivity, paramAnonymousLong);
      startActivity(paramAnonymousAdapterView);
    }
  };
  private Runnable mOnDraftChanged = new Runnable()
  {
    public void run()
    {
      mListAdapter.notifyDataSetChanged();
    }
  };
  protected int mPlaceHolderType = 0;
  protected ThreadListQueryHandler mQueryHandler;
  private int mRecommendVisible = 8;
  protected View mRootView;
  protected View mSearchView;
  protected TextView mSearchViewHint;
  private boolean mShowCloudHeader = false;
  private boolean mShowCloudStateView;
  private SubscriptionManager.OnSubscriptionsChangedListener mSimInfoChangeListener = new SubscriptionManager.OnSubscriptionsChangedListener()
  {
    public void onSubscriptionsChanged()
    {
      boolean bool = MSimUtils.isMSimInserted();
      if (mIsMSimInserted != bool)
      {
        ConversationFragment.access$2702(ConversationFragment.this, bool);
        startAsyncQuery(false);
      }
    }
  };
  protected final View.OnKeyListener mThreadListKeyListener = new View.OnKeyListener()
  {
    public boolean onKey(View paramAnonymousView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
    {
      if (paramAnonymousKeyEvent.getAction() == 0) {}
      switch (paramAnonymousInt)
      {
      default: 
        return false;
      }
      long l = mListView.getSelectedItemId();
      if (l > 0L) {
        ConversationFragment.confirmDeleteThread(l, mQueryHandler);
      }
      return true;
    }
  };
  private Handler mUIHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      Log.v("ConversationFragment", "handle msg on main thread, msg: " + what);
      switch (what)
      {
      }
      do
      {
        do
        {
          do
          {
            do
            {
              return;
            } while (!ConversationFragment.this.shouldAsk(mActivity));
            ConversationFragment.this.askUploadOldMessages(mActivity);
            ConversationFragment.this.askDownloadWildMessages(mActivity);
            return;
          } while (mIsStoped);
          if (((Boolean)obj).booleanValue())
          {
            ConversationFragment.this.showCloudRecommendView();
            return;
          }
          ConversationFragment.this.hideCloudRecommendView();
          return;
          startAsyncQuery(true);
          return;
          if (DraftCache.getInstance().getSavingDraft())
          {
            mUIHandler.removeMessages(1005);
            mUIHandler.sendEmptyMessageDelayed(1005, 1000L);
            return;
          }
          Conversation.asyncDeleteObsoleteThreads(mQueryHandler, 1803);
          return;
        } while (mIsStoped);
        ConversationFragment.this.checkAndShowNetworkingDialog();
        return;
      } while (mIsStoped);
      ConversationFragment.this.showYellowpageRecommend();
    }
  };
  private boolean mUnlockPrivateMode;
  private DialogInterface.OnClickListener mUserNoticeAgreedListener = new DialogInterface.OnClickListener()
  {
    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
    {
      MessageUtils.setYpRecommendEnabled(mActivity, false);
      ConversationFragment.this.enableSmartMessage();
      MessageUtils.setNetworkRecommendDate(mActivity, Long.valueOf(System.currentTimeMillis()));
      MiStatSdkHelper.recordNetworkAllowRecommend("network_recommend_goto");
    }
  };
  private DialogInterface.OnClickListener mUserNoticeCancelledListener = new DialogInterface.OnClickListener()
  {
    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
    {
      MessageUtils.setYpRecommendEnabled(mActivity, true);
      MessageUtils.setNetworkRecommendDate(mActivity, Long.valueOf(System.currentTimeMillis()));
      MiStatSdkHelper.recordNetworkAllowRecommend("network_recommend_close");
      ConversationFragment.this.showYellowpageRecommend();
    }
  };
  private WildMsgDialog mWildMsgDialog;
  private Handler mWorkerHander = null;
  private HandlerThread mWorkerThread = null;
  private View mYellowpageRecommendView;
  private int mYpRecommendVisible = 8;
  protected final Uri sSearchHintUri = Uri.withAppendedPath(Telephony.MmsSms.CONTENT_URI, "searchCount");
  
  static
  {
    UNSEEN_BLOCKED_MSG_COUNT_URI = Uri.withAppendedPath(Telephony.MmsSms.CONTENT_URI, "unseenBlockedMsgCount");
    SMS_THREAD_ID_PROJECTION = new String[] { "thread_id" };
  }
  
  private void addBlackList(final Context paramContext, final Set<Long> paramSet)
  {
    new AlertDialog.Builder(paramContext).setIconAttribute(16843605).setTitle(2131362261).setMessage(2131362262).setCancelable(true).setPositiveButton(17039370, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        if (mAddBlackListTask != null)
        {
          Log.e("ConversationFragment", "add black list task is running");
          return;
        }
        ConversationFragment.access$2502(ConversationFragment.this, new AsyncTask()
        {
          private ContentResolver mContentResolver;
          private Context mContext;
          private Set<Long> mThreadIds;
          
          private Uri getBlockedConvUriByNumber(String paramAnonymous2String)
          {
            return ContentUris.withAppendedId(ExtraTelephony.MmsSms.BLOCKED_CONVERSATION_CONTENT_URI.buildUpon().appendQueryParameter("blocked_conv_addr", paramAnonymous2String).build(), 0L);
          }
          
          protected Void doInBackground(Void... paramAnonymous2VarArgs)
          {
            paramAnonymous2VarArgs = new ArrayList();
            ArrayList localArrayList = new ArrayList();
            int j = 0;
            int i = 0;
            Iterator localIterator = mThreadIds.iterator();
            while (localIterator.hasNext())
            {
              Object localObject = (Long)localIterator.next();
              localObject = ((Contact)Conversation.get(MmsApp.getApp(), ((Long)localObject).longValue()).getRecipients().get(0)).getNumber().replace("+86", "");
              if (TextUtils.isEmpty((CharSequence)localObject))
              {
                Log.d("ConversationFragment", "number is null");
              }
              else
              {
                localObject = PhoneNumberUtils.PhoneNumber.parse((CharSequence)localObject).getNormalizedNumber(false, true);
                ContentValues localContentValues;
                if (!ExtraTelephony.isInBlacklist(mContext, (String)localObject))
                {
                  localContentValues = new ContentValues(1);
                  localContentValues.put("number", (String)localObject);
                  localContentValues.put("type", "1");
                  localContentValues.put("state", Integer.valueOf(0));
                  localContentValues.put("isdisplay", Integer.valueOf(0));
                  localContentValues.put("notes", Integer.valueOf(0));
                  paramAnonymous2VarArgs.add(ContentProviderOperation.newInsert(ExtraTelephony.Phonelist.CONTENT_URI).withValues(localContentValues).build());
                }
                for (;;)
                {
                  localContentValues = new ContentValues(1);
                  localContentValues.put("block_type", Integer.valueOf(5));
                  localArrayList.add(ContentProviderOperation.newUpdate(getBlockedConvUriByNumber((String)localObject)).withValues(localContentValues).build());
                  int k = paramAnonymous2VarArgs.size();
                  int m = localArrayList.size();
                  if (k <= 10)
                  {
                    j = k;
                    i = m;
                    if (m <= 10) {
                      break;
                    }
                  }
                  if (k > 0) {}
                  try
                  {
                    mContentResolver.applyBatch("antispam", paramAnonymous2VarArgs);
                    mContentResolver.applyBatch("mms-sms", localArrayList);
                  }
                  catch (RemoteException localRemoteException)
                  {
                    for (;;)
                    {
                      localRemoteException.printStackTrace();
                    }
                  }
                  catch (OperationApplicationException localOperationApplicationException)
                  {
                    for (;;)
                    {
                      localOperationApplicationException.printStackTrace();
                    }
                  }
                  paramAnonymous2VarArgs.clear();
                  localArrayList.clear();
                  j = k;
                  i = m;
                  break;
                  localContentValues = new ContentValues(1);
                  localContentValues.put("state", Integer.valueOf(0));
                  paramAnonymous2VarArgs.add(ContentProviderOperation.newUpdate(ExtraTelephony.Phonelist.CONTENT_URI).withSelection("number=?", new String[] { localObject }).withValues(localContentValues).build());
                  Log.d("ConversationFragment", "number has been in black list");
                }
              }
            }
            if (((j <= 0) && (i <= 0)) || (j > 0)) {}
            try
            {
              mContentResolver.applyBatch("antispam", paramAnonymous2VarArgs);
              mContentResolver.applyBatch("mms-sms", localArrayList);
            }
            catch (RemoteException paramAnonymous2VarArgs)
            {
              for (;;)
              {
                paramAnonymous2VarArgs.printStackTrace();
              }
            }
            catch (OperationApplicationException paramAnonymous2VarArgs)
            {
              for (;;)
              {
                paramAnonymous2VarArgs.printStackTrace();
              }
            }
            return null;
          }
          
          protected void onPostExecute(Void paramAnonymous2Void)
          {
            ConversationFragment.access$2502(ConversationFragment.this, null);
            if (ConversationFragment.mAddBlackListProgressDialog != null) {
              ConversationFragment.mAddBlackListProgressDialog.dismiss();
            }
            ConversationFragment.access$2602(null);
            mListView.exitEditMode();
          }
          
          protected void onPreExecute()
          {
            mContext = val$context;
            mThreadIds = val$threadIds;
            mContentResolver = val$context.getContentResolver();
            ConversationFragment.access$2602(ProgressDialog.show(mContext, null, mContext.getString(2131362263), true, false));
          }
        });
        mAddBlackListTask.execute(new Void[0]);
        paramAnonymousDialogInterface.dismiss();
      }
    }).setNegativeButton(2131361892, null).show();
  }
  
  private void askCloudRecommend()
  {
    if (!mShowCloudHeader) {
      return;
    }
    mWorkerHander.removeMessages(1002);
    mWorkerHander.sendEmptyMessageDelayed(1002, 300L);
  }
  
  private void askDownloadWildMessages(Context paramContext)
  {
    if (Settings.System.getInt(paramContext.getContentResolver(), "mms_sync_wild_msg_state", 0) != 1) {
      return;
    }
    if (mWildMsgDialog != null) {
      mWildMsgDialog.cancel();
    }
    mWildMsgDialog = new WildMsgDialog(paramContext, false);
    mWildMsgDialog.show();
  }
  
  private void askSmartMessageRecommend()
  {
    mWorkerHander.removeMessages(1006);
    mWorkerHander.sendEmptyMessageDelayed(1006, 0L);
  }
  
  private void askUploadOldMessages(final Context paramContext)
  {
    if (Settings.System.getInt(paramContext.getContentResolver(), "mms_upload_old_msg_state", 0) != 1) {}
    do
    {
      do
      {
        return;
      } while ((MessageUtils.isPrivacyModeEnabled(paramContext)) || (!MSimUtils.isSimInserted()));
      localObject = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    } while ((localObject == null) || (!((NetworkInfo)localObject).isAvailable()));
    Object localObject = Settings.System.getString(paramContext.getContentResolver(), "mms_upload_old_msg_accounts");
    final String str = queryXiaomiAccountName(paramContext);
    if ((mOldMsgPromptDialog != null) && (mOldMsgPromptDialog.isShowing())) {
      mOldMsgPromptDialog.dismiss();
    }
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    localBuilder.setTitle(2131362199);
    localBuilder.setMessage(getString(2131362200, new Object[] { localObject, str }));
    localBuilder.setIconAttribute(16843605);
    localBuilder.setCancelable(true);
    localBuilder.setPositiveButton(2131361891, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        Settings.System.putInt(paramContext.getContentResolver(), "mms_upload_old_msg_state", 0);
        Settings.System.putString(paramContext.getContentResolver(), "mms_upload_old_msg_accounts", null);
        paramAnonymousDialogInterface = new ContentValues();
        paramAnonymousDialogInterface.put("account", str);
        paramAnonymousDialogInterface.put("bind_id", Integer.valueOf(0));
        paramContext.getContentResolver().update(Telephony.Sms.CONTENT_URI, paramAnonymousDialogInterface, "account is not null and account != ?", new String[] { str });
        paramContext.getContentResolver().update(Telephony.Mms.CONTENT_URI, paramAnonymousDialogInterface, "account is not null and account != ?", new String[] { str });
        MessageUtils.forceSync(paramContext);
      }
    });
    localBuilder.setNegativeButton(2131361892, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        Settings.System.putInt(paramContext.getContentResolver(), "mms_upload_old_msg_state", 0);
        Settings.System.putString(paramContext.getContentResolver(), "mms_upload_old_msg_accounts", null);
      }
    });
    mOldMsgPromptDialog = localBuilder.show();
  }
  
  private void checkAndShowNetworkingDialog()
  {
    if (((this instanceof ServiceProviderConversationFragment)) || ((this instanceof PrivateConversationFragment))) {
      return;
    }
    MiStatSdkHelper.recordNetworkAllowRecommend("network_recommend_show");
    MessageUtils.setNetworkRecommendDate(mActivity, Long.valueOf(0L));
    if (mNetworkDialog != null)
    {
      mNetworkDialog.show();
      return;
    }
    mNetworkDialog = AlertDialogHelper.showUserNoticeDialog(mActivity, 2131362401, 2131362402, 2131362403, 0, mUserNoticeAgreedListener, mUserNoticeCancelledListener);
  }
  
  public static void confirmDeleteThread(long paramLong, AsyncQueryHandler paramAsyncQueryHandler)
  {
    ArrayList localArrayList = null;
    if (paramLong != -1L)
    {
      localArrayList = new ArrayList();
      localArrayList.add(Long.valueOf(paramLong));
    }
    confirmDeleteThreads(localArrayList, paramAsyncQueryHandler);
  }
  
  public static void confirmDeleteThreadDialog(DeleteThreadListener paramDeleteThreadListener, final Collection<Long> paramCollection, boolean paramBoolean, Context paramContext)
  {
    View localView = View.inflate(paramContext, 2130968602, null);
    TextView localTextView = (TextView)localView.findViewById(2131820614);
    if (paramCollection == null)
    {
      localTextView.setText(2131361918);
      paramCollection = (CheckBox)localView.findViewById(2131820615);
      if (paramBoolean) {
        break label145;
      }
      paramCollection.setVisibility(8);
    }
    for (;;)
    {
      new AlertDialog.Builder(paramContext).setTitle(2131361915).setIconAttribute(16843605).setCancelable(true).setPositiveButton(2131361930, paramDeleteThreadListener).setNegativeButton(2131361892, null).setView(localView).show();
      return;
      int i = paramCollection.size();
      localTextView.setText(paramContext.getResources().getQuantityString(2131623936, i, new Object[] { Integer.valueOf(i) }));
      break;
      label145:
      paramDeleteThreadListener.setDeleteLockedMessage(paramCollection.isChecked());
      paramCollection.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          val$listener.setDeleteLockedMessage(paramCollection.isChecked());
        }
      });
    }
  }
  
  public static void confirmDeleteThreads(Collection<Long> paramCollection, AsyncQueryHandler paramAsyncQueryHandler)
  {
    Conversation.startQueryHaveLockedMessages(paramAsyncQueryHandler, paramCollection, 1802);
  }
  
  private void enableCloudState(boolean paramBoolean)
  {
    if (!(mRootView instanceof ScrollableViewDrawer)) {
      return;
    }
    ScrollableViewDrawer localScrollableViewDrawer = (ScrollableViewDrawer)mRootView;
    if (paramBoolean)
    {
      mShowCloudStateView = true;
      if (mCloudStateView == null)
      {
        mCloudStateView = ((MiCloudStateView)mRootView.findViewById(2131820603));
        mCloudStateView.setSyncInfoProvider(new SmsSyncInfoProviderWrapper());
        mCloudStateView.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            paramAnonymousView = new Intent("com.xiaomi.action.MICLOUD_MAIN");
            startActivity(paramAnonymousView);
          }
        });
      }
      mCloudStateView.registerObserver();
      localScrollableViewDrawer.setDragEnabled(true);
      mListView.setOnPrivateColorChangeListener(new PrivateEntryView.OnPrivateColorChangeListener()
      {
        public void onColorChange(int paramAnonymousInt)
        {
          mCloudStateView.setBackgroundColor(paramAnonymousInt);
        }
      });
      return;
    }
    if (mCloudStateView != null) {
      mCloudStateView.unregisterObserver();
    }
    localScrollableViewDrawer.resetState();
    mShowCloudStateView = false;
    localScrollableViewDrawer.setDragEnabled(false);
    mListView.setOnPrivateColorChangeListener(null);
  }
  
  private void enableSmartMessage()
  {
    MessageUtils.setAllowNetworkAccess(mActivity, true);
    MessageUtils.enableYellowPage(mActivity);
    new QueryActivateStatusTask(mActivity).execute(new Void[0]);
  }
  
  private void hideCloudRecommendView()
  {
    if (mCloudRecommendView != null) {
      mCloudRecommendView.setVisibility(8);
    }
  }
  
  private void hideYellowpageRecommend()
  {
    MessageUtils.setYpRecommendEnabled(mActivity, false);
    if (mYellowpageRecommendView != null) {
      mYellowpageRecommendView.setVisibility(8);
    }
    refreshCloudStateView();
  }
  
  private boolean isSticky(Set<Long> paramSet)
  {
    paramSet = paramSet.iterator();
    while (paramSet.hasNext()) {
      if (!Conversation.get(((Long)paramSet.next()).longValue()).isSticky()) {
        return false;
      }
    }
    return true;
  }
  
  private void log(String paramString, Object... paramVarArgs)
  {
    paramString = String.format(paramString, paramVarArgs);
    Log.d("ConversationFragment", "[" + Thread.currentThread().getId() + "] " + paramString);
  }
  
  private String queryXiaomiAccountName(Context paramContext)
  {
    paramContext = AccountManager.get(paramContext).getAccountsByType("com.xiaomi");
    if (paramContext.length == 0) {
      return null;
    }
    return 0name;
  }
  
  private void refreshCloudStateView()
  {
    Activity localActivity = getActivity();
    if (localActivity != null) {
      if (ExtraAccountManager.getXiaomiAccount(localActivity) == null) {
        break label24;
      }
    }
    label24:
    for (boolean bool = true;; bool = false)
    {
      enableCloudState(bool);
      return;
    }
  }
  
  private void refreshListViewReadItem()
  {
    int i = 0;
    for (;;)
    {
      if (i < mListView.getChildCount())
      {
        View localView = mListView.getChildAt(i);
        if ((!(localView instanceof ConversationListItem)) || (!((ConversationListItem)localView).updatePreMarkUnReadView())) {}
      }
      else
      {
        return;
      }
      i += 1;
    }
  }
  
  private boolean shouldAsk(Context paramContext)
  {
    if (MessageUtils.isPrivacyModeEnabled(paramContext)) {
      return false;
    }
    if (!MSimUtils.isSimInserted()) {
      return false;
    }
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.isAvailable());
  }
  
  private void showCloudRecommendView()
  {
    if ((mYellowpageRecommendView != null) && (mYellowpageRecommendView.getVisibility() == 0)) {}
    do
    {
      Object localObject;
      do
      {
        do
        {
          return;
          enableCloudState(false);
          if (mCloudRecommendView != null) {
            break;
          }
          localObject = getView();
        } while (localObject == null);
        localObject = (ViewStub)((View)localObject).findViewById(2131820604);
      } while (localObject == null);
      mCloudRecommendView = ((ViewStub)localObject).inflate();
    } while (mCloudRecommendView == null);
    mCloudRecommendView.findViewById(2131820573).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (getActivity() != null) {
          SyncAlertHelper.handleSyncAlert(getActivity(), "sms");
        }
      }
    });
    mCloudRecommendView.findViewById(2131820574).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (getActivity() != null) {
          SyncAlertHelper.recordTime(MmsApp.getApp(), "sms");
        }
        ConversationFragment.this.refreshCloudStateView();
        ConversationFragment.this.hideCloudRecommendView();
      }
    });
    return;
    mCloudRecommendView.setVisibility(0);
  }
  
  private void showYellowpageRecommend()
  {
    if ((mCloudRecommendView != null) && (mCloudRecommendView.getVisibility() == 0)) {}
    do
    {
      do
      {
        return;
        if (mYellowpageRecommendView != null) {
          break;
        }
        localObject = getView();
      } while (localObject == null);
      Object localObject = (ViewStub)((View)localObject).findViewById(2131820605);
      if (localObject != null) {
        mYellowpageRecommendView = ((ViewStub)localObject).inflate();
      }
      if (mYellowpageRecommendView != null)
      {
        mYellowpageRecommendView.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            ConversationFragment.this.hideYellowpageRecommend();
            MiStatSdkHelper.recordNetworkAllowRecommend("yellowpage_recommend_goto");
            ConversationFragment.this.checkAndShowNetworkingDialog();
          }
        });
        mYellowpageRecommendView.findViewById(2131820574).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            ConversationFragment.this.hideYellowpageRecommend();
            MiStatSdkHelper.recordNetworkAllowRecommend("yellowpage_recommend_close");
            MessageUtils.setNetworkRecommendDate(mActivity, Long.valueOf(System.currentTimeMillis()));
          }
        });
      }
    } while (mYellowpageRecommendView == null);
    enableCloudState(false);
    mYellowpageRecommendView.setVisibility(0);
    MiStatSdkHelper.recordNetworkAllowRecommend("yellowpage_recommend_show");
  }
  
  private void startQueryUnSeenBlockedCount()
  {
    mQueryHandler.cancelOperation(1904);
    mQueryHandler.startQuery(1904, null, UNSEEN_BLOCKED_MSG_COUNT_URI, null, null, null, null);
  }
  
  private void startQueryUnSeenSPCount()
  {
    mQueryHandler.cancelOperation(1901);
    mQueryHandler.startQuery(1901, null, UNSEEN_SP_MSG_COUNT_URI, null, null, null, null);
  }
  
  private void stickThread(Set<Long> paramSet, boolean paramBoolean)
  {
    ContentValues localContentValues = new ContentValues(1);
    if (paramBoolean) {}
    for (long l = System.currentTimeMillis();; l = 0L)
    {
      Long localLong = Long.valueOf(l);
      localContentValues.put("stick_time", localLong);
      Conversation.startUpdateThreads(mQueryHandler, 1804, paramSet, localContentValues);
      Conversation.updateCachedThreadStickTime(paramSet, localLong.longValue());
      return;
    }
  }
  
  private void updateStickMenu(MenuItem paramMenuItem, boolean paramBoolean1, boolean paramBoolean2)
  {
    int i = 2131361931;
    if (paramBoolean2)
    {
      if (paramBoolean1)
      {
        paramMenuItem.setTitle(i);
        if (!paramBoolean1) {
          break label49;
        }
      }
      label49:
      for (i = R.drawable.action_button_stick_light;; i = R.drawable.action_button_unstick_light)
      {
        paramMenuItem.setIcon(i);
        return;
        i = 2131361932;
        break;
      }
    }
    paramMenuItem.setTitle(2131361931);
    paramMenuItem.setIcon(R.drawable.action_button_stick_light);
  }
  
  public void createFirstQuery(Context paramContext)
  {
    mQueryHandler = new ThreadListQueryHandler(paramContext.getContentResolver());
    initListCompositeMode(paramContext);
    startAsyncQuery(true);
    mIsCreateFirstQuery = true;
  }
  
  public void forceHideHintView()
  {
    if (mCloudRecommendView != null)
    {
      mRecommendVisible = mCloudRecommendView.getVisibility();
      mCloudRecommendView.setVisibility(8);
    }
    if (mYellowpageRecommendView != null)
    {
      mYpRecommendVisible = mYellowpageRecommendView.getVisibility();
      mYellowpageRecommendView.setVisibility(8);
    }
  }
  
  protected void initListCompositeMode(Context paramContext)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext);
    if ((Build.IS_INTERNATIONAL_BUILD) && (!Build.checkRegion("IN")))
    {
      mIsCompositeMode = false;
      if (!paramContext.getBoolean("pref_key_show_blocked_messages", true)) {
        break label76;
      }
    }
    label76:
    for (mPlaceHolderType |= 0x1;; mPlaceHolderType &= 0xFFFFFFFE)
    {
      mPlaceHolderType |= 0x2;
      return;
      mIsCompositeMode = paramContext.getBoolean("pref_key_collapse_sp_messages", true);
      break;
    }
  }
  
  protected void initialize()
  {
    setHasOptionsMenu(true);
    mIsMSimInserted = MSimUtils.isMSimInserted();
    MSimUtils.registerChangeListener(mActivity.getApplicationContext(), mSimInfoChangeListener);
    mWorkerThread = new HandlerThread("Conv Thread", 10);
    mWorkerThread.start();
    mWorkerHander = new Handler(mWorkerThread.getLooper())
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        Log.v("ConversationFragment", "handle msg on Worker thread, msg: " + what);
        switch (what)
        {
        }
        do
        {
          return;
          boolean bool = SyncAlertHelper.isNeedAlert(MmsApp.getApp().getApplicationContext(), "sms");
          Log.v("ConversationFragment", "isNeedAlert ? " + bool);
          paramAnonymousMessage = new Message();
          what = 1002;
          obj = Boolean.valueOf(bool);
          mUIHandler.sendMessage(paramAnonymousMessage);
          return;
          if ((MessageUtils.isNetworkRecommendDateLong(mActivity)) && (MessageUtils.isSmartMessageNotReady(mActivity)))
          {
            paramAnonymousMessage = new Message();
            what = 1006;
            mUIHandler.sendMessage(paramAnonymousMessage);
            return;
          }
        } while ((!MessageUtils.isYpRecommendEnabled(mActivity)) || (!MessageUtils.isSmartMessageNotReady(mActivity)));
        paramAnonymousMessage = new Message();
        what = 1007;
        mUIHandler.sendMessage(paramAnonymousMessage);
      }
    };
  }
  
  protected void markConversationAsSeen()
  {
    if (mIsCompositeMode)
    {
      Conversation.markConversationAsNotified(mActivity.getApplicationContext());
      return;
    }
    Conversation.markAllConversationsAsSeen(mActivity.getApplicationContext());
  }
  
  public void onCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater)
  {
    paramMenuInflater.inflate(2131755009, paramMenu);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    mActivity = getActivity();
    mRootView = paramLayoutInflater.inflate(2130968598, null);
    mListView = ((MessageListPullView)mRootView.findViewById(16908298));
    mListView.setOnKeyListener(mThreadListKeyListener);
    mListView.setEditModeListener(new ModeCallback());
    mListView.enableEmptyImgView(false);
    mListView.setEmptyImgViewImageResource(2130837594);
    mListView.setAllowTranscriptOnResize(false);
    mPrivateSmsUnlockDistance = (int)mActivity.getResources().getDimension(2131427388);
    mListView.setScrolledListener(new MessageListPullView.ScrolledListener()
    {
      public void onScrolled(int paramAnonymousInt)
      {
        if ((!mUnlockPrivateMode) && (paramAnonymousInt > ConversationFragment.mPrivateSmsUnlockDistance) && (((MmsTabActivity)mActivity).enterPrivateActivity()))
        {
          ConversationFragment.access$902(ConversationFragment.this, true);
          mActivity.overridePendingTransition(2131034118, 17432577);
        }
      }
    });
    refreshCloudStateView();
    mSearchView = paramLayoutInflater.inflate(R.layout.search_stub, null, false);
    mBookmarkView = null;
    mBookmarkViewAdded = false;
    mBookmarkVisible = false;
    mListView.addHeaderView(mSearchView);
    mSearchViewHint = ((TextView)mSearchView.findViewById(16908297));
    mSearchViewHint.setHint(getResources().getString(2131362212, new Object[] { Integer.valueOf(0) }));
    mListAdapter = new ConversationListAdapter(mActivity, null, true);
    mListView.setAdapter(mListAdapter);
    mListView.setRecyclerListener(mListAdapter);
    mListView.setOnItemClickListener(mOnClickListener);
    mListView.setOnScrollListener(this);
    mListView.enableDragEvent(true);
    mOnAccountsUpdateListener = new OnAccountsUpdateListener()
    {
      public void onAccountsUpdated(Account[] paramAnonymousArrayOfAccount)
      {
        ConversationFragment.this.refreshCloudStateView();
      }
    };
    AccountManager.get(mActivity).addOnAccountsUpdatedListener(mOnAccountsUpdateListener, new Handler(), true);
    mShowCloudHeader = true;
    initialize();
    return mRootView;
  }
  
  public void onDestroy()
  {
    if (mOnAccountsUpdateListener != null) {
      AccountManager.get(getActivity()).removeOnAccountsUpdatedListener(mOnAccountsUpdateListener);
    }
    super.onDestroy();
  }
  
  public void onDestroyView()
  {
    if (mAddBlackListTask != null) {
      mAddBlackListTask.cancel(true);
    }
    mAddBlackListTask = null;
    if (mAddBlackListProgressDialog != null) {
      mAddBlackListProgressDialog.dismiss();
    }
    mAddBlackListProgressDialog = null;
    if (mDeleteProgressDialog != null) {
      mDeleteProgressDialog.dismiss();
    }
    mDeleteProgressDialog = null;
    mWorkerThread.quit();
    mWorkerThread = null;
    mUIHandler.removeMessages(1002);
    mUIHandler.removeMessages(1003);
    mUIHandler.removeMessages(1001);
    mUIHandler.removeMessages(1005);
    mUIHandler.removeMessages(1006);
    mListAdapter.changeCursor(null);
    MSimUtils.unregisterChangeListener(mActivity.getApplicationContext(), mSimInfoChangeListener);
    super.onDestroyView();
    if (!mIsSimpleMode) {
      Contact.stopCaching();
    }
  }
  
  public void onDraftChanged(long paramLong, boolean paramBoolean)
  {
    if (Log.isLoggable("Mms:app", 2)) {
      log("onDraftChanged: threadId=" + paramLong + ", hasDraft=" + paramBoolean, new Object[0]);
    }
    mQueryHandler.removeCallbacks(mOnDraftChanged);
    mQueryHandler.postDelayed(mOnDraftChanged, 20L);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default: 
      return false;
    }
    paramMenuItem = ComposeMessageRouterActivity.createIntent(mActivity, 0L);
    paramMenuItem.putExtra("exit_animation_in", 17432576);
    paramMenuItem.putExtra("exit_animation_out", 2131034120);
    ComposeMessageRouterActivity.route(mActivity, paramMenuItem);
    mActivity.overridePendingTransition(2131034121, 17432577);
    return true;
  }
  
  public void onPause()
  {
    mListView.clearDragAnimation();
    if (mNetworkDialog != null) {
      mNetworkDialog.dismiss();
    }
    if (mYellowpageRecommendView != null) {
      mYellowpageRecommendView.setVisibility(8);
    }
    super.onPause();
  }
  
  public void onResume()
  {
    askCloudRecommend();
    askSmartMessageRecommend();
    super.onResume();
    if (!mIsSimpleMode) {
      Contact.resumeCaching();
    }
  }
  
  public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
  {
    if (!mIsSimpleMode)
    {
      if (paramInt == 2)
      {
        Contact.pauseCaching();
        YellowPageImgLoader.pauseLoading(getActivity());
      }
    }
    else {
      return;
    }
    Contact.resumeCaching();
    YellowPageImgLoader.resumeLoading(getActivity());
  }
  
  public void onStart()
  {
    if ((mRootView instanceof ScrollableViewDrawer)) {
      ((ScrollableViewDrawer)mRootView).resetState();
    }
    super.onStart();
    refreshListViewReadItem();
    mIsSimpleMode = MiuiSettings.System.isSimpleMode(mActivity);
    if (!mIsSimpleMode) {
      Contact.invalidatePhotoCache();
    }
    MessagingNotification.cancelNotification(mActivity.getApplicationContext(), 239);
    DraftCache.getInstance().addOnDraftChangedListener(this);
    mListAdapter.setOnContentChangedListener(mContentChangedListener);
    mNeedToMarkAsSeen = true;
    mUnlockPrivateMode = false;
    mIsStoped = false;
    if (!mIsCreateFirstQuery)
    {
      initListCompositeMode(mActivity);
      mUIHandler.removeMessages(1003);
      mUIHandler.sendEmptyMessageDelayed(1003, 10L);
    }
    mIsCreateFirstQuery = false;
    if ((mActivity instanceof MmsTabActivity))
    {
      mUIHandler.removeMessages(1001);
      mUIHandler.sendEmptyMessageDelayed(1001, 100L);
    }
    askSmartMessageRecommend();
    Contact.asyncloadAllInBackground();
    if ((mCloudStateView != null) && (mShowCloudStateView))
    {
      mCloudStateView.registerObserver();
      mCloudStateView.updateState(true);
    }
  }
  
  public void onStop()
  {
    super.onStop();
    if (mWildMsgDialog != null) {
      mWildMsgDialog.cancel();
    }
    mWildMsgDialog = null;
    mListAdapter.setOnContentChangedListener(null);
    DraftCache.getInstance().removeOnDraftChangedListener(this);
    Contact.removeAllListener();
    if (mUIHandler != null)
    {
      mUIHandler.removeMessages(1001);
      mUIHandler.removeMessages(1003);
      mUIHandler.removeMessages(1006);
    }
    mIsStoped = true;
    if ((mCloudStateView != null) && (mShowCloudStateView)) {
      mCloudStateView.unregisterObserver();
    }
    mListView.enableEmptyImgView(false);
    mListView.clearEmptyImgAndTxt();
  }
  
  public void restoreHintView()
  {
    if (mCloudRecommendView != null) {
      mCloudRecommendView.setVisibility(mRecommendVisible);
    }
    if ((mYellowpageRecommendView != null) && (mRecommendVisible == 8)) {
      mYellowpageRecommendView.setVisibility(mYpRecommendVisible);
    }
  }
  
  protected void startAsyncQuery(boolean paramBoolean)
  {
    try
    {
      mQueryHandler.cancelOperation(1701);
      Conversation.startQueryForAll(mQueryHandler, 1701, false, mIsCompositeMode, mPlaceHolderType);
      if ((mIsCompositeMode) && (paramBoolean))
      {
        startQueryUnSeenSPCount();
        if (mListAdapter != null) {
          mListAdapter.resetSpSentinelId();
        }
        mQueryHandler.cancelOperation(1902);
        Conversation.startQuerySpSentinelId(mQueryHandler, 1902);
      }
      if (paramBoolean)
      {
        if (Conversation.isBlockedPlaceHolder(mPlaceHolderType)) {
          startQueryUnSeenBlockedCount();
        }
        mQueryHandler.cancelOperation(1903);
        Conversation.startQueryHaveLockedMessages(mQueryHandler, -1L, 1903, false);
      }
      if (paramBoolean)
      {
        startQueryTimedThreads();
        startQuerySearchHintCount();
      }
      if (mCloudStateView != null) {
        mCloudStateView.updateState();
      }
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      SqliteWrapper.checkSQLiteException(mActivity, localSQLiteException);
    }
  }
  
  protected void startQuerySearchHintCount()
  {
    mQueryHandler.cancelOperation(1704);
    Uri localUri = sSearchHintUri.buildUpon().appendQueryParameter("privacy_flag", "0").build();
    mQueryHandler.startQuery(1704, null, localUri, null, null, null, null);
  }
  
  protected void startQueryTimedThreads()
  {
    mQueryHandler.cancelOperation(1702);
    mQueryHandler.cancelOperation(1703);
    Conversation.TimedThreads.clear();
    mFinishedTimedQuery = 0;
    mQueryHandler.startQuery(1702, null, Telephony.Sms.CONTENT_URI, SMS_THREAD_ID_PROJECTION, "timed>0", null, null);
    mQueryHandler.startQuery(1703, null, Telephony.Mms.CONTENT_URI, MMS_THREAD_ID_PROJECTION, "timed>0", null, null);
  }
  
  public static class DeleteThreadListener
    implements DialogInterface.OnClickListener
  {
    private final Context mContext;
    private boolean mDeleteLockedMessages;
    private final AsyncQueryHandler mHandler;
    private final Collection<Long> mThreadIds;
    
    public DeleteThreadListener(Collection<Long> paramCollection, AsyncQueryHandler paramAsyncQueryHandler, Context paramContext)
    {
      mThreadIds = paramCollection;
      mHandler = paramAsyncQueryHandler;
      mContext = paramContext;
    }
    
    public void onClick(DialogInterface paramDialogInterface, int paramInt)
    {
      MessageUtils.handleReadReport(mContext, mThreadIds, 129, new Runnable()
      {
        public void run()
        {
          ConversationFragment.access$1302(ProgressDialog.show(mContext, null, mContext.getString(2131362131), true, false));
          if (mThreadIds == null)
          {
            Conversation.startDeleteAll(mHandler, 1801, mDeleteLockedMessages);
            return;
          }
          Conversation.startDelete(mHandler, 1801, mDeleteLockedMessages, mThreadIds);
        }
      });
      paramDialogInterface.dismiss();
    }
    
    public void setDeleteLockedMessage(boolean paramBoolean)
    {
      mDeleteLockedMessages = paramBoolean;
    }
  }
  
  protected class ModeCallback
    implements EditableListView.EditModeListener
  {
    private ConversationListAdapter mAdapter;
    private EditableListView.EditableListViewCheckable mCheckable;
    private EditActionMode mEditActionMode;
    private HeaderViewListAdapter mHeaderAdapter;
    private Menu mRootMenu;
    
    protected ModeCallback() {}
    
    private void updateMenu(int paramInt, boolean paramBoolean, Set<Long> paramSet)
    {
      getActivity();
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
          if ((!mIsCompositeMode) || (!localConversation.isServiceProvider()) || (localConversation.isSticky())) {
            break label265;
          }
          bool6 = false;
          bool4 = false;
          bool5 = false;
        }
        if (paramInt != 0) {
          break label308;
        }
        paramSet = getString(R.string.action_mode_title_empty);
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
        paramSet = mActivity.getResources().getQuantityString(R.plurals.items_selected, mCheckable.count(), new Object[] { Integer.valueOf(mCheckable.count()) });
        mRootMenu.findItem(2131820908).setEnabled(true);
        break label142;
        label374:
        mEditActionMode.setButton(16908314, R.string.action_mode_select_all);
        break label179;
        ConversationFragment.this.updateStickMenu(paramSet, paramBoolean, bool4);
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
        ConversationFragment.confirmDeleteThreads(paramActionMode, mQueryHandler);
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
        paramMenuItem = ConversationFragment.this;
        if (!ConversationFragment.this.isSticky(paramActionMode)) {}
        for (boolean bool = true;; bool = false)
        {
          paramMenuItem.stickThread(paramActionMode, bool);
          return true;
        }
      }
      paramActionMode = mCheckable.getCheckedItemInIds();
      ConversationFragment.this.addBlackList(mActivity, paramActionMode);
      return true;
    }
    
    public void onCheckStateChanged(EditableListView.EditableListViewCheckable paramEditableListViewCheckable)
    {
      HashSet localHashSet = paramEditableListViewCheckable.getCheckedItemInIds();
      mAdapter.setCheckedItem(localHashSet);
      boolean bool = ConversationFragment.this.isSticky(localHashSet);
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
      mActivity.getMenuInflater().inflate(2131755010, paramMenu);
      mHeaderAdapter = ((HeaderViewListAdapter)mListView.getAdapter());
      mAdapter = ((ConversationListAdapter)mHeaderAdapter.getWrappedAdapter());
      mAdapter.enterCheckMode();
      mCheckable = mListView.getEditableListViewCheckable();
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
  
  private static class QueryActivateStatusTask
    extends AsyncTask<Void, Void, Integer>
  {
    private Activity mActivity;
    private boolean mHasVoip;
    private boolean mVoipHasActivated;
    
    public QueryActivateStatusTask(Activity paramActivity)
    {
      mActivity = paramActivity;
      mHasVoip = false;
      mVoipHasActivated = false;
    }
    
    protected Integer doInBackground(Void... paramVarArgs)
    {
      mHasVoip = MessageUtils.hasVoipPackage(mActivity);
      mVoipHasActivated = MiuiSettings.MiuiVoip.isVoipActivated(mActivity);
      return Integer.valueOf(MxActivateService.getSimActivateStatus(mActivity));
    }
    
    protected void onPostExecute(Integer paramInteger)
    {
      if (paramInteger.intValue() == 3)
      {
        if (MessageUtils.isMxDisabledBySlotId(mActivity, 0)) {
          MxActivateService.enableMx(mActivity, 0, true, true);
        }
        if ((MSimUtils.isMSim()) && (MessageUtils.isMxDisabledBySlotId(mActivity, 1))) {
          MxActivateService.enableMx(mActivity, 1, true, true);
        }
        if ((mHasVoip) && (!mVoipHasActivated))
        {
          paramInteger = new Intent("com.miui.voip.action.TURN_ON_VOIP");
          paramInteger.putExtra("extra_turn_on_voip_from", 2);
          mActivity.sendBroadcast(paramInteger);
        }
      }
      while (paramInteger.intValue() != 1) {
        return;
      }
      new AlertDialog.Builder(mActivity).setTitle(2131362401).setCancelable(false).setMessage(2131362404).setPositiveButton(2131362403, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          MessageUtils.activatePhone(mActivity, mHasVoip);
        }
      }).setNegativeButton(2131361892, null).show();
    }
  }
  
  protected final class ThreadListQueryHandler
    extends AsyncQueryHandler
  {
    public ThreadListQueryHandler(ContentResolver paramContentResolver)
    {
      super();
    }
    
    private void showBookMark()
    {
      int i = 0;
      if (getActivity() != null)
      {
        if (!mBookmarkVisible) {
          break label133;
        }
        if (mBookmarkView == null) {
          mBookmarkView = getActivity().getLayoutInflater().inflate(2130968596, null, false);
        }
        if (!mBookmarkViewAdded)
        {
          mListView.addHeaderView(mBookmarkView);
          mListView.resetHeaderHeight();
          mBookmarkViewAdded = true;
        }
        localView = mBookmarkView.findViewById(2131820599);
        if (mIsSimpleMode) {
          i = 8;
        }
        localView.setVisibility(i);
      }
      label133:
      while (mBookmarkView == null)
      {
        View localView;
        return;
      }
      mListView.removeHeaderView(mBookmarkView);
      mListView.resetHeaderHeight();
      mBookmarkViewAdded = false;
    }
    
    protected void onDeleteComplete(int paramInt1, Object paramObject, int paramInt2)
    {
      switch (paramInt1)
      {
      }
      do
      {
        return;
        mListView.exitEditMode();
        DraftCache.getInstance().refresh();
        Conversation.init(mActivity);
        MessagingNotification.nonBlockingUpdateNewMessageIndicator(mActivity, false, false);
        MessagingNotification.updateSendFailedNotification(mActivity);
      } while (ConversationFragment.mDeleteProgressDialog == null);
      ConversationFragment.mDeleteProgressDialog.dismiss();
      ConversationFragment.access$1302(null);
    }
    
    protected void onQueryComplete(int paramInt, Object paramObject, Cursor paramCursor)
    {
      Log.v("ConversationFragment", "onQueryComplete token is " + paramInt);
      switch (paramInt)
      {
      default: 
        Log.e("ConversationFragment", "onQueryComplete called with unknown token " + paramInt);
      }
      label496:
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    return;
                    if (mListAdapter != null) {
                      break;
                    }
                    Log.v("ConversationFragment", "not init list adapter");
                    mIsCreateFirstQuery = false;
                  } while (paramCursor == null);
                  paramCursor.close();
                  return;
                  mListAdapter.setCompositeMode(mIsCompositeMode);
                  mListAdapter.setPlaceHolderType(mPlaceHolderType);
                  mListView.setEmptyTxtViewText(2131361938);
                  if (mIsStoped)
                  {
                    if (paramCursor != null) {
                      paramCursor.close();
                    }
                    Log.v("ConversationFragment", "query cursor close for stop");
                  }
                  for (;;)
                  {
                    mListAdapter.setUIChangeCursorTime();
                    if (!mNeedToMarkAsSeen) {
                      break;
                    }
                    ConversationFragment.access$1802(ConversationFragment.this, false);
                    markConversationAsSeen();
                    if (!(mActivity instanceof MmsTabActivity)) {
                      break;
                    }
                    mUIHandler.removeMessages(1005);
                    mUIHandler.sendEmptyMessageDelayed(1005, 0L);
                    return;
                    mListAdapter.changeCursor(paramCursor);
                    mListView.enableEmptyImgView(true);
                  }
                  boolean bool1 = false;
                  boolean bool2 = false;
                  if (paramCursor != null) {}
                  try
                  {
                    paramInt = paramCursor.getCount();
                    bool1 = bool2;
                    if (paramInt > 0) {
                      bool1 = true;
                    }
                    paramCursor.close();
                    paramObject = (Collection)paramObject;
                    ConversationFragment.confirmDeleteThreadDialog(new ConversationFragment.DeleteThreadListener((Collection)paramObject, mQueryHandler, mActivity), (Collection)paramObject, bool1, mActivity);
                    return;
                  }
                  finally
                  {
                    paramCursor.close();
                  }
                } while (paramCursor == null);
                try
                {
                  paramCursor.moveToPosition(-1);
                  while (paramCursor.moveToNext()) {
                    Conversation.TimedThreads.setHasTimedMessage(paramCursor.getLong(0));
                  }
                  if (ConversationFragment.access$1904(ConversationFragment.this) != 2) {
                    break label496;
                  }
                }
                finally
                {
                  paramCursor.close();
                }
                mListAdapter.notifyDataSetChanged();
                paramCursor.close();
                return;
              } while (paramCursor == null);
              try
              {
                if (getActivity() != null)
                {
                  paramInt = 0;
                  if (paramCursor.getCount() > 0)
                  {
                    paramCursor.moveToPosition(0);
                    paramInt = paramCursor.getInt(0);
                  }
                  mSearchViewHint.setHint(getResources().getString(2131362212, new Object[] { Integer.valueOf(paramInt) }));
                  if (mCloudStateView != null) {
                    mCloudStateView.setTotalCountText(getResources().getQuantityString(2131623941, paramInt, new Object[] { Integer.valueOf(paramInt) }));
                  }
                }
                return;
              }
              finally
              {
                paramCursor.close();
              }
            } while (paramCursor == null);
            try
            {
              if (paramCursor.moveToFirst()) {
                mListAdapter.setServiceProviderUnseen(paramCursor.getInt(0));
              }
              return;
            }
            finally
            {
              paramCursor.close();
            }
          } while (paramCursor == null);
          try
          {
            if (paramCursor.moveToFirst()) {
              mListAdapter.setShowBlockedMsgUnseen(paramCursor.getInt(0));
            }
            return;
          }
          finally
          {
            paramCursor.close();
          }
        } while (paramCursor == null);
        try
        {
          if (paramCursor.moveToFirst()) {
            mListAdapter.setSpSentinelId(paramCursor.getLong(0));
          }
          return;
        }
        finally
        {
          paramCursor.close();
        }
        mBookmarkVisible = false;
      } while (paramCursor == null);
      try
      {
        if (paramCursor.getCount() > 0) {
          mBookmarkVisible = true;
        }
        paramCursor.close();
        showBookMark();
        return;
      }
      finally
      {
        paramCursor.close();
      }
    }
    
    protected void onUpdateComplete(int paramInt1, Object paramObject, int paramInt2)
    {
      switch (paramInt1)
      {
      default: 
        return;
      }
      mListView.exitEditMode();
      startAsyncQuery(false);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationFragment
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */