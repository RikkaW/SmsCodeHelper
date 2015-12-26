package com.android.mms.ui;

public abstract interface MessageListAdapter$OnDataSetChangedListener
{
  public abstract boolean needHoldCache();
  
  public abstract void onContentChanged(MessageListAdapter paramMessageListAdapter);
  
  public abstract void onDataSetChanged(MessageListAdapter paramMessageListAdapter);
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageListAdapter.OnDataSetChangedListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */