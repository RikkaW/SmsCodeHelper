package com.android.mms.ui;

import android.database.sqlite.SQLiteException;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.net.Uri.Builder;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import java.util.Iterator;

public class MultipleRecipientsConversationActivity
  extends ConversationBase
{
  private MultipleRecipientsConversationHeader mHeader;
  private long mThreadId;
  
  protected int getContentViewResId()
  {
    return 2130968671;
  }
  
  protected void initMessageList()
  {
    super.initMessageList();
    mMsgListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
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
    mHeader = ((MultipleRecipientsConversationHeader)findViewById(2131820798));
  }
  
  protected void initialize(long paramLong)
  {
    super.initialize(paramLong);
    mThreadId = paramLong;
  }
  
  protected boolean isGroup()
  {
    return true;
  }
  
  protected void onContactAdded(Contact paramContact) {}
  
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
  }
  
  protected void startMsgListQuery()
  {
    Uri localUri = mConversation.getUri();
    if (localUri == null) {
      return;
    }
    localUri = localUri.buildUpon().appendPath("group").build();
    if (Log.isLoggable("Mms:app", 2)) {
      Log.v("MultipleRecipientsCA", "startMsgListQuery MultiRecipientCA for " + localUri);
    }
    mBackgroundQueryHandler.cancelOperation(9527);
    try
    {
      mBackgroundQueryHandler.startQuery(9527, null, localUri, MessageListAdapter.GROUP_MSG_PROJECTION, null, null, null);
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      SqliteWrapper.checkSQLiteException(this, localSQLiteException);
    }
  }
  
  protected void updateTitle(ContactList paramContactList)
  {
    mHeader.updateTitle(paramContactList);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MultipleRecipientsConversationActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */