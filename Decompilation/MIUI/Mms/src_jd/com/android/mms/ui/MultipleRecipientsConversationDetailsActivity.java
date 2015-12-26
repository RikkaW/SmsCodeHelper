package com.android.mms.ui;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.android.mms.data.Conversation;

public class MultipleRecipientsConversationDetailsActivity
  extends MultipleRecipientsConversationActivity
{
  private long mTimedValue;
  private long mTimestamp;
  
  protected int getContentViewResId()
  {
    return 2130968672;
  }
  
  protected void initMessageList()
  {
    super.initMessageList();
    mMsgContentView.setOnMeasureListener(null);
    mMsgListView.setItemsCanFocus(false);
    mMsgListView.setClickable(false);
    mMsgListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        if ((paramAnonymousView != null) && ((paramAnonymousView instanceof MessageListItem))) {
          ((MessageListItem)paramAnonymousView).onMessageListItemClick();
        }
      }
    });
    mBottomPanel.setVisibility(8);
    disableAttachmentPanel();
  }
  
  protected void initResourceRefs()
  {
    super.initResourceRefs();
    mMsgListView.setEditModeListener(null);
  }
  
  protected void initialize(long paramLong)
  {
    super.initialize(paramLong);
    Intent localIntent = getIntent();
    mTimestamp = localIntent.getLongExtra("timestamp", -1L);
    mTimedValue = localIntent.getLongExtra("timed_value", -1L);
    if ((paramLong <= 0L) || (mTimestamp < 0L) || (mTimedValue < 0L)) {
      finish();
    }
  }
  
  protected boolean isGroup()
  {
    return false;
  }
  
  protected boolean isReadOnly()
  {
    return true;
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    return false;
  }
  
  protected void saveDraft(boolean paramBoolean) {}
  
  protected void startMsgListQuery()
  {
    Uri localUri = mConversation.getUri();
    if (localUri == null) {
      return;
    }
    mBackgroundQueryHandler.cancelOperation(9527);
    try
    {
      mBackgroundQueryHandler.startQuery(9527, null, localUri, MessageListAdapter.PROJECTION, "normalized_date=" + mTimestamp + " AND timed=" + mTimedValue, null, null);
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      SqliteWrapper.checkSQLiteException(this, localSQLiteException);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MultipleRecipientsConversationDetailsActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */