package com.android.mms.ui;

import android.app.Activity;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.android.mms.data.Conversation;
import com.android.mms.data.Conversation.TimedThreads;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.util.DraftCache;
import java.util.Collection;
import miui.app.ProgressDialog;
import miui.widget.MiCloudStateView;

public final class ConversationFragment$ThreadListQueryHandler
  extends AsyncQueryHandler
{
  public ConversationFragment$ThreadListQueryHandler(ConversationFragment paramConversationFragment, ContentResolver paramContentResolver)
  {
    super(paramContentResolver);
  }
  
  private void showBookMark()
  {
    int i = 0;
    if (this$0.getActivity() != null)
    {
      if (!this$0.mBookmarkVisible) {
        break label133;
      }
      if (this$0.mBookmarkView == null) {
        this$0.mBookmarkView = this$0.getActivity().getLayoutInflater().inflate(2130968596, null, false);
      }
      if (!this$0.mBookmarkViewAdded)
      {
        this$0.mListView.addHeaderView(this$0.mBookmarkView);
        this$0.mListView.resetHeaderHeight();
        this$0.mBookmarkViewAdded = true;
      }
      localView = this$0.mBookmarkView.findViewById(2131820599);
      if (ConversationFragment.access$2000(this$0)) {
        i = 8;
      }
      localView.setVisibility(i);
    }
    label133:
    while (this$0.mBookmarkView == null)
    {
      View localView;
      return;
    }
    this$0.mListView.removeHeaderView(this$0.mBookmarkView);
    this$0.mListView.resetHeaderHeight();
    this$0.mBookmarkViewAdded = false;
  }
  
  protected void onDeleteComplete(int paramInt1, Object paramObject, int paramInt2)
  {
    switch (paramInt1)
    {
    }
    do
    {
      return;
      this$0.mListView.exitEditMode();
      DraftCache.getInstance().refresh();
      Conversation.init(this$0.mActivity);
      MessagingNotification.nonBlockingUpdateNewMessageIndicator(this$0.mActivity, false, false);
      MessagingNotification.updateSendFailedNotification(this$0.mActivity);
    } while (ConversationFragment.access$1300() == null);
    ConversationFragment.access$1300().dismiss();
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
                  if (this$0.mListAdapter != null) {
                    break;
                  }
                  Log.v("ConversationFragment", "not init list adapter");
                  this$0.mIsCreateFirstQuery = false;
                } while (paramCursor == null);
                paramCursor.close();
                return;
                this$0.mListAdapter.setCompositeMode(this$0.mIsCompositeMode);
                this$0.mListAdapter.setPlaceHolderType(this$0.mPlaceHolderType);
                this$0.mListView.setEmptyTxtViewText(2131361938);
                if (ConversationFragment.access$300(this$0))
                {
                  if (paramCursor != null) {
                    paramCursor.close();
                  }
                  Log.v("ConversationFragment", "query cursor close for stop");
                }
                for (;;)
                {
                  this$0.mListAdapter.setUIChangeCursorTime();
                  if (!ConversationFragment.access$1800(this$0)) {
                    break;
                  }
                  ConversationFragment.access$1802(this$0, false);
                  this$0.markConversationAsSeen();
                  if (!(this$0.mActivity instanceof MmsTabActivity)) {
                    break;
                  }
                  ConversationFragment.access$600(this$0).removeMessages(1005);
                  ConversationFragment.access$600(this$0).sendEmptyMessageDelayed(1005, 0L);
                  return;
                  this$0.mListAdapter.changeCursor(paramCursor);
                  this$0.mListView.enableEmptyImgView(true);
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
                  ConversationFragment.confirmDeleteThreadDialog(new ConversationFragment.DeleteThreadListener((Collection)paramObject, this$0.mQueryHandler, this$0.mActivity), (Collection)paramObject, bool1, this$0.mActivity);
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
                if (ConversationFragment.access$1904(this$0) != 2) {
                  break label496;
                }
              }
              finally
              {
                paramCursor.close();
              }
              this$0.mListAdapter.notifyDataSetChanged();
              paramCursor.close();
              return;
            } while (paramCursor == null);
            try
            {
              if (this$0.getActivity() != null)
              {
                paramInt = 0;
                if (paramCursor.getCount() > 0)
                {
                  paramCursor.moveToPosition(0);
                  paramInt = paramCursor.getInt(0);
                }
                this$0.mSearchViewHint.setHint(this$0.getResources().getString(2131362212, new Object[] { Integer.valueOf(paramInt) }));
                if (ConversationFragment.access$1200(this$0) != null) {
                  ConversationFragment.access$1200(this$0).setTotalCountText(this$0.getResources().getQuantityString(2131623941, paramInt, new Object[] { Integer.valueOf(paramInt) }));
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
              this$0.mListAdapter.setServiceProviderUnseen(paramCursor.getInt(0));
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
            this$0.mListAdapter.setShowBlockedMsgUnseen(paramCursor.getInt(0));
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
          this$0.mListAdapter.setSpSentinelId(paramCursor.getLong(0));
        }
        return;
      }
      finally
      {
        paramCursor.close();
      }
      this$0.mBookmarkVisible = false;
    } while (paramCursor == null);
    try
    {
      if (paramCursor.getCount() > 0) {
        this$0.mBookmarkVisible = true;
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
    this$0.mListView.exitEditMode();
    this$0.startAsyncQuery(false);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationFragment.ThreadListQueryHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */