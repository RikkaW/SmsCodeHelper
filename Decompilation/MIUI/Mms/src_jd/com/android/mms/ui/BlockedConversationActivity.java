package com.android.mms.ui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.provider.Telephony.Mms.Inbox;
import android.provider.Telephony.Sms.Inbox;
import android.text.TextUtils;
import android.util.Log;
import android.view.ActionMode;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.understand.UnderstandLoader;
import com.android.mms.understand.UnderstandLoader.RequestCallback;
import com.android.mms.util.EditableListView.EditModeListener;
import com.android.mms.util.EditableListView.EditableListViewCheckable;
import com.android.mms.util.EditableListView.OnItemDoubleClickListener;
import com.android.mms.util.MSimUtils;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import miui.R.plurals;
import miui.R.string;
import miui.app.ProgressDialog;
import miui.provider.ExtraTelephony;
import miui.provider.ExtraTelephony.MmsSms;
import miui.provider.ExtraTelephony.Phonelist;
import miui.telephony.PhoneNumberUtils.PhoneNumber;
import miui.view.EditActionMode;

public class BlockedConversationActivity
  extends ConversationBase
{
  private static final Uri BLOCKED_MMS_URI = Telephony.Mms.Inbox.CONTENT_URI.buildUpon().appendQueryParameter("blocked_flag", "1").build();
  private static final Uri BLOCKED_SMS_URI = Telephony.Sms.Inbox.CONTENT_URI.buildUpon().appendQueryParameter("blocked_flag", "1").build();
  private AlertDialog mAlertDialog;
  private View mBlockedBottomPanel;
  private SingleRecipientConversationHeader mHeader;
  private boolean mIsFromSms = false;
  private Button mNoBlockButton;
  private View.OnClickListener mNoBlockClickListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if (mIsFromSms)
      {
        paramAnonymousView = new Intent("android.intent.action.VIEW");
        paramAnonymousView.putExtra("number", mNumber);
        paramAnonymousView.putExtra("is_from_blocked", true);
        paramAnonymousView.putExtra("reply_address", mNumber);
        paramAnonymousView.putExtra("thread_id", mConversation.getThreadId());
        paramAnonymousView.setPackage("com.android.mms");
        ComposeMessageRouterActivity.processIntent(BlockedConversationActivity.this, paramAnonymousView);
        startActivity(paramAnonymousView);
        return;
      }
      new AlertDialog.Builder(BlockedConversationActivity.this).setTitle(2131361924).setMessage(2131361923).setPositiveButton(2131361924, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
        {
          BlockedConversationActivity.this.unblockMessage(mNumber);
        }
      }).setNegativeButton(17039360, null).show();
    }
  };
  private String mNumber;
  private UnderstandLoader.RequestCallback mRequestCallback;
  
  private void confirmBatchDeleteDialog(BatchDeleteListener paramBatchDeleteListener, int paramInt)
  {
    View localView = View.inflate(this, 2130968602, null);
    ((TextView)localView.findViewById(2131820614)).setText(getString(2131362130, new Object[] { Integer.valueOf(paramInt) }));
    ((CheckBox)localView.findViewById(2131820615)).setVisibility(8);
    new AlertDialog.Builder(this).setTitle(2131361915).setIconAttribute(16843605).setCancelable(true).setPositiveButton(2131361930, paramBatchDeleteListener).setNegativeButton(2131361892, null).setView(localView).show();
  }
  
  private void confirmDeleteDialog(DialogInterface.OnClickListener paramOnClickListener)
  {
    String str1 = getString(2131361915);
    String str2 = getString(2131361920);
    new AlertDialog.Builder(this).setTitle(str1).setIconAttribute(16843605).setCancelable(true).setMessage(str2).setPositiveButton(2131361930, paramOnClickListener).setNegativeButton(2131361892, null).show();
  }
  
  private void confirmRestoreDialog(final RestoreListener paramRestoreListener)
  {
    String str1 = getString(2131361926);
    String str2 = getString(2131361927);
    View localView = LayoutInflater.from(this).inflate(2130968713, null);
    final Object localObject = (CheckBox)localView.findViewById(2131820890);
    ((CheckBox)localObject).setChecked(false);
    ((CheckBox)localObject).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (paramRestoreListener != null) {
          paramRestoreListener.setUnblockChecked(localObject.isChecked());
        }
      }
    });
    localObject = new AlertDialog.Builder(this);
    if (mAlertDialog == null)
    {
      mAlertDialog = ((AlertDialog.Builder)localObject).setTitle(str1).setMessage(str2).setView(localView).setIconAttribute(16843605).setCancelable(true).setPositiveButton(2131361928, paramRestoreListener).setNegativeButton(17039360, null).show();
      return;
    }
    mAlertDialog.show();
  }
  
  private void unblockMessage(String paramString)
  {
    Object localObject = PhoneNumberUtils.PhoneNumber.parse(paramString).getNormalizedNumber(false, true);
    paramString = getContentResolver().query(ExtraTelephony.Phonelist.CONTENT_URI, null, "number='" + (String)localObject + "' and " + "type" + "='" + "1" + "' and " + "sync_dirty" + "<> " + 1, null, null);
    if (paramString != null) {}
    for (;;)
    {
      try
      {
        if (!paramString.moveToNext()) {
          break label471;
        }
        i = paramString.getInt(paramString.getColumnIndex("state"));
        j = paramString.getInt(paramString.getColumnIndex("sync_dirty"));
        if (i != 0) {
          continue;
        }
        localObject = new ContentValues();
        ((ContentValues)localObject).put("state", Integer.valueOf(2));
        i = j;
        if (j == 3) {
          i = 2;
        }
        ((ContentValues)localObject).put("sync_dirty", Integer.valueOf(i));
        getContentResolver().update(ContentUris.withAppendedId(ExtraTelephony.Phonelist.CONTENT_URI, paramString.getLong(0)), (ContentValues)localObject, null, null);
      }
      catch (Exception localException)
      {
        int i;
        int j;
        localException.printStackTrace();
        if (paramString == null) {
          continue;
        }
        paramString.close();
        continue;
        getContentResolver().delete(ContentUris.withAppendedId(ExtraTelephony.Phonelist.CONTENT_URI, paramString.getLong(0)), null, null);
        continue;
      }
      finally
      {
        if (paramString == null) {
          continue;
        }
        paramString.close();
      }
      if (paramString != null) {
        paramString.close();
      }
      paramString = new ContentValues();
      paramString.put("block_type", Integer.valueOf(0));
      getContentResolver().update(Uri.withAppendedPath(ExtraTelephony.MmsSms.BLOCKED_CONVERSATION_CONTENT_URI, String.valueOf(mConversation.getThreadId())), paramString, null, null);
      return;
      if (i == 1) {
        if ((j == 3) || (j == 2))
        {
          localObject = new ContentValues();
          ((ContentValues)localObject).put("sync_dirty", Integer.valueOf(1));
          getContentResolver().update(Uri.withAppendedPath(ExtraTelephony.Phonelist.CONTENT_URI, String.valueOf(paramString.getLong(0))), (ContentValues)localObject, null, null);
          continue;
        }
      }
      if (!ExtraTelephony.isInHidingWhite(this, str))
      {
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("number", str);
        localContentValues.put("state", Integer.valueOf(0));
        localContentValues.put("isdisplay", Integer.valueOf(0));
        localContentValues.put("type", "2");
        getContentResolver().insert(ExtraTelephony.Phonelist.CONTENT_URI, localContentValues);
        continue;
        label471:
        if (!ExtraTelephony.isInHidingWhite(this, str))
        {
          localContentValues = new ContentValues();
          localContentValues.put("number", str);
          localContentValues.put("state", Integer.valueOf(0));
          localContentValues.put("type", "2");
          localContentValues.put("isdisplay", Integer.valueOf(0));
          getContentResolver().insert(ExtraTelephony.Phonelist.CONTENT_URI, localContentValues);
        }
      }
    }
  }
  
  private void updateIntent(Context paramContext)
  {
    Intent localIntent = getIntent();
    localIntent.putExtra("thread_id", Conversation.getBlockedConv(paramContext, -1L, localIntent.getStringExtra("number")).getThreadId());
  }
  
  protected void drawBottomPanel() {}
  
  protected void drawTopPanel() {}
  
  protected void exit()
  {
    postExit();
  }
  
  protected int getBottomPanelHeight()
  {
    return mBlockedBottomPanel.getHeight();
  }
  
  protected int getContentViewResId()
  {
    return 2130968585;
  }
  
  protected void handleIntent(Intent paramIntent) {}
  
  protected void initBottomPanelResourceRefs() {}
  
  protected void initMessageList()
  {
    super.initMessageList();
    enableNotShowAllMsgList();
    mMsgListAdapter.setThreadAddress(mNumber);
    if (!TextUtils.isEmpty(mNumber))
    {
      mWaitingResource = true;
      Log.v("BlockedConversationActivity", " begin request loading resources");
      if (mRequestCallback == null) {
        mRequestCallback = new UnderstandLoader.RequestCallback()
        {
          public void onRequestDone(boolean paramAnonymousBoolean)
          {
            Log.v("BlockedConversationActivity", " request loading resources done");
            mWaitingResource = false;
            applyPendingCursor();
          }
        };
      }
      UnderstandLoader.request(mNumber, mRequestCallback);
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
  
  protected void initializeBottomPanel() {}
  
  protected void initializeConversation(long paramLong)
  {
    mConversation = Conversation.getBlockedConv(this, paramLong, null);
  }
  
  public void onBackPressed()
  {
    exit();
  }
  
  protected void onBottomPanelStop() {}
  
  protected void onBottomPanelUpdate() {}
  
  protected void onContactAdded(Contact paramContact) {}
  
  protected void onCreate(Bundle paramBundle)
  {
    updateIntent(this);
    super.onCreate(paramBundle);
    mIsFromSms = getIntent().getBooleanExtra("isFromSms", false);
    mNumber = "";
    if ((getRecipients() != null) && (getRecipients().size() > 0)) {
      mNumber = ((Contact)getRecipients().get(0)).getNumber();
    }
    mBlockedBottomPanel = findViewById(2131820562);
    mNoBlockButton = ((Button)findViewById(2131820563));
    paramBundle = mNoBlockButton;
    if (mIsFromSms) {}
    for (int i = 2131362253;; i = 2131362252)
    {
      paramBundle.setText(i);
      mNoBlockButton.setOnClickListener(mNoBlockClickListener);
      return;
    }
  }
  
  protected void onDestroy()
  {
    if (!TextUtils.isEmpty(mNumber)) {
      UnderstandLoader.destroy(mNumber, mRequestCallback);
    }
    super.onDestroy();
  }
  
  protected void onPause()
  {
    super.onPause();
    MessagingNotification.setCurrentMessageThreadId(0L);
  }
  
  public void onPreMeasure(SizeAwareLinearLayout paramSizeAwareLinearLayout, int paramInt1, int paramInt2)
  {
    paramSizeAwareLinearLayout = (ViewGroup.MarginLayoutParams)mContentParent.getLayoutParams();
    paramInt2 = topMargin;
    int i = bottomMargin;
    paramSizeAwareLinearLayout = getWindow().getWindowManager().getDefaultDisplay();
    int j = paramSizeAwareLinearLayout.getHeight() - paramInt2 - i - mContentView.getPaddingTop() - mTopPlaceholderHeight;
    View[] arrayOfView = new View[1];
    arrayOfView[0] = mBlockedBottomPanel;
    i = 0;
    if (i < arrayOfView.length)
    {
      View localView = arrayOfView[i];
      paramInt2 = j;
      if (isVisible(localView))
      {
        localView.measure(paramInt1, View.MeasureSpec.makeMeasureSpec(Integer.MIN_VALUE, paramSizeAwareLinearLayout.getHeight()));
        if (j >= localView.getMeasuredHeight()) {
          break label159;
        }
        localView.setLayoutParams(new LinearLayout.LayoutParams(-1, j, 0.0F));
      }
      for (paramInt2 = 0;; paramInt2 = j - localView.getMeasuredHeight())
      {
        i += 1;
        j = paramInt2;
        break;
        label159:
        localView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2, 0.0F));
      }
    }
    mHistoryView.setLayoutParams(new LinearLayout.LayoutParams(-1, j, 0.0F));
  }
  
  protected void onResume()
  {
    super.onResume();
    Object localObject = getRecipients();
    if ((localObject != null) && (!((ContactList)localObject).isEmpty()))
    {
      localObject = ((ContactList)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        super.onContactAdded((Contact)((Iterator)localObject).next());
      }
    }
    MessagingNotification.setCurrentMessageThreadId(mConversation.getThreadId());
  }
  
  protected void onStop()
  {
    super.onStop();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("read", Integer.valueOf(1));
    Uri localUri = ExtraTelephony.MmsSms.BLOCKED_CONVERSATION_CONTENT_URI.buildUpon().appendQueryParameter("blocked_flag", "1").build();
    getContentResolver().update(ContentUris.withAppendedId(localUri, mConversation.getThreadId()), localContentValues, null, null);
  }
  
  protected void postSwitchMsgType() {}
  
  protected void registerSimRelatedListener()
  {
    if (MSimUtils.isMSim()) {
      MSimUtils.registerChangeListener(this, mSimInfoChangeListener);
    }
  }
  
  protected void setListViewEditModeListener()
  {
    mMsgListView.setEditModeListener(new BlockedModeCallback(null));
  }
  
  protected void startMsgListQuery()
  {
    Log.v("BlockedConversationActivity", "querying blocked message list");
    Uri localUri = mConversation.getBlockedUri();
    if (localUri == null)
    {
      Log.i("BlockedConversationActivity", "conversation uri is null, it is a new conv");
      return;
    }
    localUri = localUri.buildUpon().appendQueryParameter("limit", buildMsgListQueryLimit()).build();
    if (Log.isLoggable("Mms:app", 2)) {
      Log.v("BlockedConversationActivity", "startMsgListQuery for " + localUri);
    }
    mBackgroundQueryHandler.cancelOperation(9527);
    try
    {
      mBackgroundQueryHandler.startQuery(9527, null, localUri, MessageListAdapter.PROJECTION, null, null, null);
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      SqliteWrapper.checkSQLiteException(this, localSQLiteException);
    }
  }
  
  protected void unRegisterSimRelatedListener()
  {
    if (MSimUtils.isMSim()) {
      MSimUtils.unregisterChangeListener(this, mSimInfoChangeListener);
    }
  }
  
  protected void updateSlotButtonInfo() {}
  
  protected void updateSlotButtonStateBySlotId(Context paramContext, int paramInt) {}
  
  protected void updateSlotRelatedState() {}
  
  protected void updateTitle(ContactList paramContactList)
  {
    mHeader.updateTitle(paramContactList);
  }
  
  private class BatchDeleteListener
    implements DialogInterface.OnClickListener
  {
    private ActionMode mActionMode;
    private List<MessageItem> mSelectedMsgs;
    
    public BatchDeleteListener(ActionMode paramActionMode)
    {
      mSelectedMsgs = paramActionMode;
      ActionMode localActionMode;
      mActionMode = localActionMode;
    }
    
    public void onClick(DialogInterface paramDialogInterface, int paramInt)
    {
      Object localObject1 = new HashSet();
      paramDialogInterface = new HashSet();
      Object localObject2 = mSelectedMsgs.iterator();
      while (((Iterator)localObject2).hasNext())
      {
        MessageItem localMessageItem = (MessageItem)((Iterator)localObject2).next();
        if (localMessageItem.getType().equals("mms")) {
          paramDialogInterface.add(Long.valueOf(localMessageItem.getMsgId()));
        } else if (localMessageItem.getType().equals("sms")) {
          ((HashSet)localObject1).add(Long.valueOf(localMessageItem.getMsgId()));
        }
      }
      mBatchDeleteTaskCount = 0;
      if (!((HashSet)localObject1).isEmpty())
      {
        localObject1 = "_id IN (" + TextUtils.join(",", (Iterable)localObject1) + ")";
        localObject2 = BlockedConversationActivity.this;
        mBatchDeleteTaskCount += 1;
        mBackgroundQueryHandler.startDelete(9701, null, BlockedConversationActivity.BLOCKED_SMS_URI, (String)localObject1, null);
      }
      if (!paramDialogInterface.isEmpty())
      {
        paramDialogInterface = "_id IN (" + TextUtils.join(",", paramDialogInterface) + ")";
        localObject1 = BlockedConversationActivity.this;
        mBatchDeleteTaskCount += 1;
        mBackgroundQueryHandler.startDelete(9701, null, BlockedConversationActivity.BLOCKED_MMS_URI, paramDialogInterface, null);
      }
      if (mBatchDeleteTaskCount > 0) {
        ConversationBase.mBatchDeleteProgressDialog = ProgressDialog.show(BlockedConversationActivity.this, null, getString(2131362132), true, false);
      }
      mActionMode.finish();
    }
  }
  
  private class BlockedModeCallback
    implements EditableListView.EditModeListener
  {
    private EditableListView.EditableListViewCheckable mCheckable;
    private EditActionMode mEditActionMode;
    private Menu mRootMenu;
    
    private BlockedModeCallback() {}
    
    private List<MessageItem> getCheckedMessageItems()
    {
      HashSet localHashSet = mCheckable.getCheckedItemInPositions();
      return mMsgListAdapter.getCheckedItems(localHashSet);
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
        MessageUtils.copyMessageTextToClipboard(BlockedConversationActivity.this, localList);
        paramActionMode.finish();
        return;
      case 2131820903: 
        if (1 == j)
        {
          paramActionMode = new BlockedConversationActivity.DeleteMessageListener(BlockedConversationActivity.this, localMessageItem.getMessageUri(), localMessageItem.getDate(), localMessageItem.isSms(), paramActionMode);
          BlockedConversationActivity.this.confirmDeleteDialog(paramActionMode);
          return;
        }
        paramActionMode = new BlockedConversationActivity.BatchDeleteListener(BlockedConversationActivity.this, localList, paramActionMode);
        BlockedConversationActivity.this.confirmBatchDeleteDialog(paramActionMode, j);
        return;
      case 2131820904: 
        BlockedConversationActivity.this.confirmRestoreDialog(new BlockedConversationActivity.RestoreListener(BlockedConversationActivity.this, localList, paramActionMode));
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
        str = getString(R.string.action_mode_title_empty);
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
        str = getResources().getQuantityString(R.plurals.items_selected, mCheckable.count(), new Object[] { Integer.valueOf(mCheckable.count()) });
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
      mMsgListAdapter.setCheckedItem(mCheckable.getCheckedItemInIds());
      updateMenu(mCheckable.count());
    }
    
    public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      mRootMenu = paramMenu;
      getMenuInflater().inflate(2131755008, paramMenu);
      prepareNoneSelectionMenu();
      mMsgListView.setAllowTranscriptOnResize(false);
      mMsgListAdapter.enterCheckMode();
      mCheckable = mMsgListView.getEditableListViewCheckable();
      mEditActionMode = ((EditActionMode)paramActionMode);
      updateMenu(0);
      mBlockedBottomPanel.setVisibility(8);
      mContactPanel.setVisibility(8);
      setTopPlaceholderHeight(0);
      mHistoryView.setForeground(null);
      int i = 0;
      while (i < mMsgListView.getChildCount())
      {
        paramActionMode = mMsgListView.getChildAt(i);
        if ((paramActionMode instanceof MessageListItem)) {
          ((MessageListItem)paramActionMode).showEditModeAnimation(true);
        }
        i += 1;
      }
      return true;
    }
    
    public void onDestroyActionMode(ActionMode paramActionMode)
    {
      mContactPanel.setVisibility(0);
      mMsgListView.post(new Runnable()
      {
        public void run()
        {
          mMsgListView.setAllowTranscriptOnResize(true);
        }
      });
      mMsgListAdapter.exitCheckMode();
      mBlockedBottomPanel.setVisibility(0);
      setTopPlaceholderHeight(mTitleBarTallBgHeight);
      mHistoryView.setForeground(getResources().getDrawable(2130837856));
      int i = 0;
      while (i < mMsgListView.getChildCount())
      {
        paramActionMode = mMsgListView.getChildAt(i);
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
  
  private class DeleteMessageListener
    implements DialogInterface.OnClickListener
  {
    private final ActionMode mActionMode;
    private final Uri mDeleteUri;
    
    public DeleteMessageListener(Uri paramUri, long paramLong, boolean paramBoolean, ActionMode paramActionMode)
    {
      mDeleteUri = paramUri.buildUpon().appendQueryParameter("blocked_flag", "1").build();
      mActionMode = paramActionMode;
    }
    
    public void onClick(DialogInterface paramDialogInterface, int paramInt)
    {
      mBackgroundQueryHandler.startDelete(9701, null, mDeleteUri, null, null);
      paramDialogInterface.dismiss();
      mActionMode.finish();
    }
  }
  
  private class RestoreListener
    implements DialogInterface.OnClickListener
  {
    private ActionMode mActionMode;
    private boolean mChecked;
    private List<MessageItem> mSelectedMsgs;
    
    public RestoreListener(ActionMode paramActionMode)
    {
      mSelectedMsgs = paramActionMode;
      ActionMode localActionMode;
      mActionMode = localActionMode;
      mChecked = false;
    }
    
    public void onClick(DialogInterface paramDialogInterface, int paramInt)
    {
      if (mChecked) {
        BlockedConversationActivity.this.unblockMessage(mNumber);
      }
      for (;;)
      {
        mActionMode.finish();
        return;
        Object localObject1 = new HashSet();
        paramDialogInterface = new HashSet();
        Object localObject2 = mSelectedMsgs.iterator();
        while (((Iterator)localObject2).hasNext())
        {
          MessageItem localMessageItem = (MessageItem)((Iterator)localObject2).next();
          if (localMessageItem.getType().equals("mms")) {
            paramDialogInterface.add(Long.valueOf(localMessageItem.getMsgId()));
          } else if (localMessageItem.getType().equals("sms")) {
            ((HashSet)localObject1).add(Long.valueOf(localMessageItem.getMsgId()));
          }
        }
        localObject2 = new ContentValues();
        ((ContentValues)localObject2).put("block_type", Integer.valueOf(0));
        if (!((HashSet)localObject1).isEmpty())
        {
          localObject1 = "_id IN (" + TextUtils.join(",", (Iterable)localObject1) + ")";
          mBackgroundQueryHandler.startUpdate(9702, null, BlockedConversationActivity.BLOCKED_SMS_URI, (ContentValues)localObject2, (String)localObject1, null);
        }
        if (!paramDialogInterface.isEmpty())
        {
          paramDialogInterface = "_id IN (" + TextUtils.join(",", paramDialogInterface) + ")";
          mBackgroundQueryHandler.startUpdate(9702, null, BlockedConversationActivity.BLOCKED_MMS_URI, (ContentValues)localObject2, paramDialogInterface, null);
        }
      }
    }
    
    public void setUnblockChecked(boolean paramBoolean)
    {
      mChecked = paramBoolean;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.BlockedConversationActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */