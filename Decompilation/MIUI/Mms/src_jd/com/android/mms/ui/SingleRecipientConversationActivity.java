package com.android.mms.ui;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SqliteWrapper;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MiuiSettings.MiuiVoip;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.android.mms.MmsApp;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.understand.UnderstandLoader;
import com.android.mms.understand.UnderstandLoader.RequestCallback;
import com.android.mms.util.EditableListView.OnItemDoubleClickListener;
import com.android.mms.util.MiStatSdkHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import miui.provider.VoipContract;
import miui.provider.VoipContract.UsersColumn;
import miui.yellowpage.MiPubUtils;
import miui.yellowpage.ModuleIntent;

public class SingleRecipientConversationActivity
  extends ConversationBase
{
  private IntentFilter mActivateMXBCFilter;
  protected Contact mContact;
  private BroadcastReceiver mFinishReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      paramAnonymousContext = paramAnonymousIntent.getAction();
      if ("android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(paramAnonymousContext)) {
        if (("homekey".equals(paramAnonymousIntent.getStringExtra("reason"))) && (mConversation.isPrivate())) {
          finish();
        }
      }
      while ((!"android.intent.action.SCREEN_OFF".equals(paramAnonymousContext)) || (!mConversation.isPrivate())) {
        return;
      }
      finish();
    }
  };
  private SingleRecipientConversationHeader mHeader;
  private AsyncTask<Void, Void, Void> mLoadLocalMenuTask;
  protected ArrayList<ModuleIntent> mMenuList;
  private BroadcastReceiver mMxStatusReceiver;
  protected PopupWindow mPopupMenu;
  private AsyncTask<Void, Void, Void> mRemoteMenuTask;
  private UnderstandLoader.RequestCallback mRequestCallback;
  private AsyncTask<Void, Void, Boolean> mVoipQueryTask;
  protected Long mYellowPageId;
  protected String mYellowPageName;
  
  private void createHeaderMenu(Contact paramContact)
  {
    if ((paramContact.isB2cNumber()) || (paramContact.isEmail())) {
      mHeader.hideCallMenu();
    }
    while (!MiuiSettings.MiuiVoip.isVoipEnabled(this)) {
      return;
    }
    Log.v("SingleRecipientCA", " query voip status ");
    queryVoipStatus(paramContact.getNumber());
  }
  
  private void onModuleIntentClick(ModuleIntent paramModuleIntent)
  {
    MiStatSdkHelper.recordBottomMenuClick(mYellowPageName, paramModuleIntent.getTitle());
    paramModuleIntent = paramModuleIntent.getIntent();
    paramModuleIntent.putExtra("source", "sms_bottom_menu");
    paramModuleIntent.putExtra("com.miui.yellowpage.extra.yid", mYellowPageId);
    startActivity(paramModuleIntent);
  }
  
  private void registerFinishReceiver()
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");
    localIntentFilter.addAction("android.intent.action.SCREEN_OFF");
    registerReceiver(mFinishReceiver, localIntentFilter);
  }
  
  private void requestRemoteMenu()
  {
    if (mRemoteMenuTask != null) {
      return;
    }
    mRemoteMenuTask = new AsyncTask()
    {
      protected Void doInBackground(Void... paramAnonymousVarArgs)
      {
        MiPubUtils.getYellowPageMenu(SingleRecipientConversationActivity.this, mYellowPageId.longValue(), true, 4);
        return null;
      }
      
      protected void onPostExecute(Void paramAnonymousVoid)
      {
        SingleRecipientConversationActivity.access$702(SingleRecipientConversationActivity.this, null);
      }
    };
    mRemoteMenuTask.execute(new Void[0]);
  }
  
  private void toggleSubMenu(View paramView, final ModuleIntent paramModuleIntent)
  {
    MiStatSdkHelper.recordBottomMenuClick(mYellowPageName, paramModuleIntent.getTitle());
    if ((mPopupMenu == null) || (!mPopupMenu.isShowing()))
    {
      Object localObject1 = LayoutInflater.from(MmsApp.getApp().getApplicationContext()).inflate(2130968636, null);
      mPopupMenu = new PopupWindow((View)localObject1, -2, -2);
      paramModuleIntent = paramModuleIntent.getSubModuleIntent();
      Object localObject3 = new ArrayList();
      Object localObject2 = paramModuleIntent.iterator();
      while (((Iterator)localObject2).hasNext()) {
        ((List)localObject3).add(((ModuleIntent)((Iterator)localObject2).next()).getTitle());
      }
      localObject2 = (ListView)((View)localObject1).findViewById(2131820705);
      ((ListView)localObject2).setAdapter(new ArrayAdapter(this, 2130968637, 2131820629, (List)localObject3));
      ((ListView)localObject2).setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          paramAnonymousAdapterView = (ModuleIntent)paramModuleIntent.get(paramAnonymousInt);
          mPopupMenu.dismiss();
          SingleRecipientConversationActivity.this.onModuleIntentClick(paramAnonymousAdapterView);
        }
      });
      ((ListView)localObject2).setOverScrollMode(2);
      mPopupMenu.setFocusable(true);
      mPopupMenu.setBackgroundDrawable(new BitmapDrawable());
      mPopupMenu.setOutsideTouchable(true);
      ((ListView)localObject2).measure(0, 0);
      localObject1 = ((View)localObject1).getBackground();
      localObject3 = new Rect();
      if (localObject1 != null) {
        ((Drawable)localObject1).getPadding((Rect)localObject3);
      }
      int i = getResources().getDimensionPixelSize(2131427479);
      mPopupMenu.setHeight(((ListView)localObject2).getMeasuredHeight() * paramModuleIntent.size() + top + bottom);
      mPopupMenu.setWidth(i);
      mPopupMenu.showAsDropDown(paramView, (paramView.getWidth() - i) / 2, 0);
    }
  }
  
  private void unRegisterFinishReceiver()
  {
    unregisterReceiver(mFinishReceiver);
  }
  
  protected int getContentViewResId()
  {
    return 2130968707;
  }
  
  protected void initMessageList()
  {
    super.initMessageList();
    enableNotShowAllMsgList();
    if ((getRecipients() != null) && (getRecipients().size() > 0))
    {
      String str = ((Contact)getRecipients().get(0)).getNumber();
      if ((MessageUtils.allowMenuMode()) && (((Contact)getRecipients().get(0)).isYellowPageNumber())) {
        prepareMenuMode();
      }
      createHeaderMenu((Contact)getRecipients().get(0));
      mMsgListAdapter.setThreadAddress(str);
      mWaitingResource = true;
      Log.v("SingleRecipientCA", " begin request loading resources");
      if (mRequestCallback == null) {
        mRequestCallback = new UnderstandLoader.RequestCallback()
        {
          public void onRequestDone(boolean paramAnonymousBoolean)
          {
            Log.v("SingleRecipientCA", " request loading resources done");
            mWaitingResource = false;
            applyPendingCursor();
          }
        };
      }
      UnderstandLoader.request(str, mRequestCallback);
    }
    mMsgListView.setOnItemDoubleClickListener(new EditableListView.OnItemDoubleClickListener()
    {
      public void onDoubleClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        Log.v("TAG", "onDoubleClick " + paramAnonymousInt);
        paramAnonymousInt -= mMsgListView.getFirstVisiblePosition();
        if ((paramAnonymousInt >= 0) && (paramAnonymousInt < mMsgListView.getChildCount()) && ((mMsgListView.getChildAt(paramAnonymousInt) instanceof MessageListItem))) {
          ((MessageListItem)mMsgListView.getChildAt(paramAnonymousInt)).onMessageListItemDoubleClick();
        }
      }
      
      public void onSingleClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        Log.v("TAG", "onSingleClick " + paramAnonymousInt);
        paramAnonymousInt -= mMsgListView.getFirstVisiblePosition();
        if ((paramAnonymousInt >= 0) && (paramAnonymousInt < mMsgListView.getChildCount()) && ((mMsgListView.getChildAt(paramAnonymousInt) instanceof MessageListItem))) {
          ((MessageListItem)mMsgListView.getChildAt(paramAnonymousInt)).onMessageListItemClick();
        }
      }
    });
  }
  
  protected void initResourceRefs()
  {
    super.initResourceRefs();
    mHeader = ((SingleRecipientConversationHeader)findViewById(2131820798));
  }
  
  protected void initialize(long paramLong)
  {
    super.initialize(paramLong);
    cancelFailedDownloadNotification(getIntent(), this);
  }
  
  protected void onContactAdded(Contact paramContact) {}
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    mActivateMXBCFilter = new IntentFilter();
    mActivateMXBCFilter.addAction("com.xiaomi.mms.action.ENBALE_RESULT");
    mMxStatusReceiver = new MxStatusReceiver(null);
    registerFinishReceiver();
  }
  
  protected void onDestroy()
  {
    if ((getRecipients() != null) && (getRecipients().size() > 0)) {
      UnderstandLoader.destroy(((Contact)getRecipients().get(0)).getNumber(), mRequestCallback);
    }
    unRegisterFinishReceiver();
    super.onDestroy();
  }
  
  public void onMxIdAdded(String paramString1, String paramString2)
  {
    super.onMxIdAdded(paramString1, paramString2);
    postSwitchMsgType();
  }
  
  protected void onPause()
  {
    super.onPause();
    MiStatSdkHelper.recordPageEnd();
    MessagingNotification.setCurrentMessageThreadId(0L);
    if (mMxStatusReceiver != null) {
      unregisterReceiver(mMxStatusReceiver);
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    MiStatSdkHelper.recordPageStart(this, "SingRecipient");
    Object localObject = getRecipients();
    if ((localObject != null) && (!((ContactList)localObject).isEmpty()))
    {
      localObject = ((ContactList)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        super.onContactAdded((Contact)((Iterator)localObject).next());
      }
    }
    MessagingNotification.setCurrentMessageThreadId(mConversation.getThreadId());
    if ((mMxStatusReceiver != null) && (mActivateMXBCFilter != null)) {
      registerReceiver(mMxStatusReceiver, mActivateMXBCFilter);
    }
  }
  
  protected void prepareMenuMode()
  {
    if (mConversation == null) {}
    while ((mConversation.getRecipients() == null) || (mConversation.getRecipients().size() < 1)) {
      return;
    }
    if (mLoadLocalMenuTask != null) {
      mLoadLocalMenuTask.cancel(true);
    }
    mLoadLocalMenuTask = new AsyncTask()
    {
      protected Void doInBackground(Void... paramAnonymousVarArgs)
      {
        mMenuList = MiPubUtils.getYellowPageMenu(SingleRecipientConversationActivity.this, mYellowPageId.longValue(), false, 4);
        return null;
      }
      
      protected void onPostExecute(Void paramAnonymousVoid)
      {
        SingleRecipientConversationActivity.access$502(SingleRecipientConversationActivity.this, null);
        if (isFinishing()) {
          return;
        }
        findViewById(2131820694).setVisibility(0);
        showMenuMode();
        SingleRecipientConversationActivity.this.requestRemoteMenu();
      }
      
      protected void onPreExecute()
      {
        mContact = ((Contact)mConversation.getRecipients().get(0));
        mYellowPageId = Long.valueOf(mContact.getYellowPageId());
        mYellowPageName = mContact.getName();
      }
    };
    mLoadLocalMenuTask.execute(new Void[0]);
  }
  
  protected void queryVoipStatus(final String paramString)
  {
    if (mVoipQueryTask != null) {
      mVoipQueryTask.cancel(true);
    }
    mVoipQueryTask = new AsyncTask()
    {
      protected Boolean doInBackground(Void... paramAnonymousVarArgs)
      {
        boolean bool = true;
        paramAnonymousVarArgs = getContentResolver().query(VoipContract.USERS_URI, VoipContract.UsersColumn.PROJECTION, null, new String[] { paramString }, null);
        Log.v("SingleRecipientCA", " query result cursor is " + paramAnonymousVarArgs);
        if (paramAnonymousVarArgs != null) {}
        for (;;)
        {
          if (paramAnonymousVarArgs != null) {
            paramAnonymousVarArgs.close();
          }
          return Boolean.valueOf(bool);
          bool = false;
        }
      }
      
      protected void onPostExecute(Boolean paramAnonymousBoolean)
      {
        SingleRecipientConversationActivity.access$102(SingleRecipientConversationActivity.this, null);
        if (isFinishing()) {}
        while (!paramAnonymousBoolean.booleanValue()) {
          return;
        }
        mHeader.updateState(true);
      }
    };
    mVoipQueryTask.execute(new Void[0]);
  }
  
  protected void showInputMode()
  {
    findViewById(2131820688).setVisibility(0);
    findViewById(2131820681).setVisibility(8);
    if (mTextEditor != null) {
      mTextEditor.requestFocus();
    }
    mSwitchBtn.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        showMenuMode();
      }
    });
    mSwitchBtn.setBackgroundResource(2130837851);
    mIsSwitchMenuBtn = true;
  }
  
  protected void showMenuMode()
  {
    if (mMenuList == null) {}
    do
    {
      return;
      hideSoftKeyboard();
      if (mSimButtonContainer != null) {
        mSimButtonContainer.setVisibility(8);
      }
      localObject = findViewById(2131820681);
    } while (localObject == null);
    TextView[] arrayOfTextView = new TextView[4];
    arrayOfTextView[0] = ((TextView)((View)localObject).findViewById(2131820683));
    arrayOfTextView[1] = ((TextView)((View)localObject).findViewById(2131820684));
    arrayOfTextView[2] = ((TextView)((View)localObject).findViewById(2131820685));
    arrayOfTextView[3] = ((TextView)((View)localObject).findViewById(2131820686));
    if (mMenuList.size() == 0)
    {
      Log.w("SingleRecipientCA", "no menu item found");
      return;
    }
    MiStatSdkHelper.recordBottomMenuShown(mYellowPageName);
    int j = mMenuList.size();
    int i = j;
    if (j > 4) {
      i = 4;
    }
    j = 0;
    while (j < i)
    {
      final ModuleIntent localModuleIntent = (ModuleIntent)mMenuList.get(j);
      if (localModuleIntent.getSubItemsFlag()) {
        arrayOfTextView[j].setBackgroundResource(2130837852);
      }
      arrayOfTextView[j].setText(localModuleIntent.getTitle());
      arrayOfTextView[j].setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (localModuleIntent.getSubItemsFlag())
          {
            SingleRecipientConversationActivity.this.toggleSubMenu(paramAnonymousView, localModuleIntent);
            return;
          }
          SingleRecipientConversationActivity.this.onModuleIntentClick(localModuleIntent);
        }
      });
      j += 1;
    }
    i = mMenuList.size();
    while (i < 4)
    {
      arrayOfTextView[i].setVisibility(8);
      i += 1;
    }
    ((View)localObject).setVisibility(0);
    Object localObject = (Button)((View)localObject).findViewById(2131820682);
    if (localObject != null) {
      ((Button)localObject).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          showInputMode();
        }
      });
    }
    findViewById(2131820688).setVisibility(8);
  }
  
  protected void startMsgListQuery()
  {
    Log.v("SingleRecipientCA", "querying message list");
    Uri localUri = mConversation.getUri();
    if (localUri == null)
    {
      Log.i("SingleRecipientCA", "conversation uri is null, it is a new conv");
      return;
    }
    Object localObject1 = null;
    try
    {
      localObject2 = ((Contact)getRecipients().get(0)).getNumber();
      localObject1 = localObject2;
    }
    catch (Exception localException)
    {
      Object localObject2;
      String str;
      for (;;) {}
    }
    localObject2 = localUri;
    if (!TextUtils.isEmpty((CharSequence)localObject1)) {
      localObject2 = Uri.withAppendedPath(localUri, Uri.encode((String)localObject1));
    }
    localObject2 = ((Uri)localObject2).buildUpon().appendQueryParameter("limit", buildMsgListQueryLimit());
    if (mConversation.isPrivate()) {}
    for (localObject1 = "1";; str = "0")
    {
      localObject1 = ((Uri.Builder)localObject2).appendQueryParameter("privacy_flag", (String)localObject1).build();
      if (Log.isLoggable("Mms:app", 2)) {
        Log.v("SingleRecipientCA", "startMsgListQuery for " + localObject1);
      }
      mBackgroundQueryHandler.cancelOperation(9527);
      try
      {
        mBackgroundQueryHandler.startQuery(9527, null, (Uri)localObject1, MessageListAdapter.PROJECTION, null, null, null);
        return;
      }
      catch (SQLiteException localSQLiteException)
      {
        SqliteWrapper.checkSQLiteException(this, localSQLiteException);
        return;
      }
    }
  }
  
  protected void updateTitle(ContactList paramContactList)
  {
    mHeader.updateTitle(paramContactList);
  }
  
  private class MxStatusReceiver
    extends BroadcastReceiver
  {
    private MxStatusReceiver() {}
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (("com.xiaomi.mms.action.ENBALE_RESULT".equals(paramIntent.getAction())) && (paramIntent.getBooleanExtra("success", false))) {
        postSwitchMsgType();
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SingleRecipientConversationActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */