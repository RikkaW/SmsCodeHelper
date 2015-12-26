package com.android.mms.ui;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.RecyclerListener;
import android.widget.CursorAdapter;
import com.android.mms.data.Conversation;
import com.android.mms.util.EditableListView.ICheckableAdapter;
import com.google.android.collect.Sets;
import java.util.HashSet;

public class ConversationListAdapter
  extends CursorAdapter
  implements AbsListView.RecyclerListener, EditableListView.ICheckableAdapter
{
  private int mBlockedMsgUnseenCount = 0;
  private HashSet<Long> mCheckedIds = Sets.newHashSet();
  private final LayoutInflater mFactory;
  private boolean mIsCheckMode = false;
  private boolean mIsCompositeMode = false;
  private OnContentChangedListener mOnContentChangedListener;
  private int mPlaceHolderType = 0;
  private int mServiceProviderUnseenCount = 0;
  private long mSpSentinelId = -1L;
  private long mUIChangeCursorTime = 0L;
  
  public ConversationListAdapter(Context paramContext, Cursor paramCursor)
  {
    super(paramContext, paramCursor, false);
    mFactory = LayoutInflater.from(paramContext);
  }
  
  public ConversationListAdapter(Context paramContext, Cursor paramCursor, boolean paramBoolean)
  {
    super(paramContext, paramCursor, false);
    mFactory = LayoutInflater.from(paramContext);
    mIsCompositeMode = paramBoolean;
  }
  
  private void resetListItemMarkUnread(Conversation paramConversation)
  {
    long l = paramConversation.getPostMarkUnread();
    if ((l > 0L) && (mUIChangeCursorTime > l) && (paramConversation.getPreMarkUnread()))
    {
      paramConversation.setPostMarkUnread(0L);
      paramConversation.setPreMarkUnread(false);
    }
  }
  
  public boolean allowChecked(int paramInt)
  {
    boolean bool = true;
    if ((mDataValid) && (mCursor != null) && (mCursor.moveToPosition(paramInt)))
    {
      if (mCursor.getLong(mRowIDColumn) < 0L) {}
      Conversation localConversation;
      do
      {
        return false;
        if (!mIsCompositeMode) {
          break;
        }
        if (mSpSentinelId != -1L)
        {
          if (mSpSentinelId != mCursor.getLong(mRowIDColumn)) {}
          for (;;)
          {
            return bool;
            bool = false;
          }
        }
        localConversation = Conversation.from(mContext, mCursor);
      } while ((localConversation != null) && (localConversation.isServiceProvider()) && (!localConversation.isSticky()));
    }
    return true;
  }
  
  public void bindView(View paramView, Context paramContext, Cursor paramCursor)
  {
    if (!(paramView instanceof ConversationListItem))
    {
      Log.e("ConversationListAdapter", "Unexpected bound view: " + paramView);
      return;
    }
    paramView = (ConversationListItem)paramView;
    Conversation localConversation = Conversation.from(paramContext, paramCursor);
    resetListItemMarkUnread(localConversation);
    localConversation.setIsChecked(mCheckedIds.contains(Long.valueOf(localConversation.getThreadId())));
    boolean bool = isSPThreadSentinel(localConversation);
    paramView.setIsSpSentinel(bool);
    paramView.setPlaceHolderType(0);
    if (bool) {
      mSpSentinelId = localConversation.getThreadId();
    }
    for (;;)
    {
      paramView.bind(paramContext, localConversation, mIsCheckMode, paramCursor, mServiceProviderUnseenCount, mBlockedMsgUnseenCount);
      return;
      long l = localConversation.getThreadId();
      if ((Conversation.isBlockedPlaceHolder(mPlaceHolderType)) && (l == -100L)) {
        paramView.setPlaceHolderType(1);
      } else if ((Conversation.isMipubPlaceHolder(mPlaceHolderType)) && (l == -101L)) {
        paramView.setPlaceHolderType(2);
      }
    }
  }
  
  public void enterCheckMode()
  {
    mIsCheckMode = true;
  }
  
  public void exitCheckMode()
  {
    mIsCheckMode = false;
    mCheckedIds = Sets.newHashSet();
  }
  
  public int getDisableCheckedCount()
  {
    int i = 0;
    if (mIsCompositeMode) {
      i = 0 + 1;
    }
    int j = mPlaceHolderType;
    while (j > 0)
    {
      j &= j - 1;
      i += 1;
    }
    return i;
  }
  
  public boolean isSPThreadSentinel(Conversation paramConversation)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramConversation != null)
    {
      bool1 = bool2;
      if (mIsCompositeMode)
      {
        bool1 = bool2;
        if (paramConversation.isServiceProvider())
        {
          bool1 = bool2;
          if (!paramConversation.isSticky()) {
            bool1 = true;
          }
        }
      }
    }
    return bool1;
  }
  
  public View newView(Context paramContext, Cursor paramCursor, ViewGroup paramViewGroup)
  {
    return mFactory.inflate(2130968595, paramViewGroup, false);
  }
  
  protected void onContentChanged()
  {
    if ((mCursor != null) && (!mCursor.isClosed()) && (mOnContentChangedListener != null)) {
      mOnContentChangedListener.onContentChanged(this);
    }
  }
  
  public void onMovedToScrapHeap(View paramView)
  {
    ((ConversationListItem)paramView).unbind();
  }
  
  public void resetSpSentinelId()
  {
    mSpSentinelId = -1L;
  }
  
  public void setCheckedItem(HashSet<Long> paramHashSet)
  {
    if (paramHashSet == null)
    {
      mCheckedIds = Sets.newHashSet();
      return;
    }
    mCheckedIds = paramHashSet;
  }
  
  public void setCompositeMode(boolean paramBoolean)
  {
    mIsCompositeMode = paramBoolean;
  }
  
  public void setOnContentChangedListener(OnContentChangedListener paramOnContentChangedListener)
  {
    mOnContentChangedListener = paramOnContentChangedListener;
  }
  
  public void setPlaceHolderType(int paramInt)
  {
    mPlaceHolderType = paramInt;
  }
  
  public void setServiceProviderUnseen(int paramInt)
  {
    mServiceProviderUnseenCount = paramInt;
  }
  
  public void setShowBlockedMsgUnseen(int paramInt)
  {
    mBlockedMsgUnseenCount = paramInt;
  }
  
  public void setSpSentinelId(long paramLong)
  {
    mSpSentinelId = paramLong;
  }
  
  public void setUIChangeCursorTime()
  {
    mUIChangeCursorTime = System.currentTimeMillis();
  }
  
  public static abstract interface OnContentChangedListener
  {
    public abstract void onContentChanged(ConversationListAdapter paramConversationListAdapter);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationListAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */