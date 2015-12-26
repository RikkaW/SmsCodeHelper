package com.android.mms.ui;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

public class NewMessagePopupListAdapter
  extends BaseAdapter
{
  private Activity mActivity;
  private MessageListAdapter.GetMsgItem mGetMsgItem = new MessageListAdapter.GetMsgItem()
  {
    public MessageItem getCurrMessageItem(int paramAnonymousInt)
    {
      return NewMessagePopupListAdapter.this.getCachedMessageItem(paramAnonymousInt);
    }
    
    public MessageItem getNextMessageItem(int paramAnonymousInt)
    {
      return NewMessagePopupListAdapter.this.getCachedMessageItem(paramAnonymousInt + 1);
    }
    
    public MessageItem getPreMessageItem(int paramAnonymousInt)
    {
      return NewMessagePopupListAdapter.this.getCachedMessageItem(paramAnonymousInt - 1);
    }
  };
  private LayoutInflater mInflater;
  private String mItemStyle = MessageListItem.Style.bubble.toString();
  private NewMessagePopupActivity.MessageThread mMessageThread;
  private float mTextSize;
  
  NewMessagePopupListAdapter(Activity paramActivity)
  {
    mActivity = paramActivity;
    mInflater = ((LayoutInflater)paramActivity.getSystemService("layout_inflater"));
  }
  
  private MessageItem getCachedMessageItem(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= mMessageThread.messageList.size())) {
      return null;
    }
    return (MessageItem)mMessageThread.messageList.get(paramInt);
  }
  
  private boolean isMessageThreadNull()
  {
    return (mMessageThread == null) || (mMessageThread.messageList == null);
  }
  
  public int getCount()
  {
    if (isMessageThreadNull()) {
      return 0;
    }
    return mMessageThread.messageList.size();
  }
  
  public Object getItem(int paramInt)
  {
    return null;
  }
  
  public long getItemId(int paramInt)
  {
    return 0L;
  }
  
  public int getItemViewType(int paramInt)
  {
    if (isMessageThreadNull()) {
      return -1;
    }
    return MessageListAdapter.getItemLayoutStyle(mActivity, mItemStyle, true, 0, false, mGetMsgItem, paramInt);
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (isMessageThreadNull()) {
      return null;
    }
    MessageListItem localMessageListItem = (MessageListItem)paramView;
    MessageItem localMessageItem = (MessageItem)mMessageThread.messageList.get(paramInt);
    paramView = localMessageListItem;
    if (localMessageListItem == null)
    {
      paramView = (MessageListItem)mInflater.inflate(2130968659, paramViewGroup, false);
      paramView.bind(localMessageItem);
    }
    paramView.rebind(localMessageItem, false, false, null, null);
    if (mTextSize != 0.0F) {
      paramView.setBodyTextSize(mTextSize);
    }
    return paramView;
  }
  
  public int getViewTypeCount()
  {
    return 12;
  }
  
  public void notifyDataSetChanged()
  {
    int i = 0;
    while (i < getCount())
    {
      MessageItem localMessageItem = getCachedMessageItem(i);
      if (localMessageItem != null) {
        localMessageItem.resetLayoutStyle();
      }
      i += 1;
    }
    super.notifyDataSetChanged();
  }
  
  public void setTextSize(float paramFloat)
  {
    mTextSize = paramFloat;
  }
  
  public void setThread(NewMessagePopupActivity.MessageThread paramMessageThread)
  {
    mMessageThread = paramMessageThread;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.NewMessagePopupListAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */