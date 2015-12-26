package com.android.mms.data;

import android.app.Activity;
import android.net.Uri;

public abstract interface WorkingMessage$MessageStatusListener
{
  public abstract Activity getHostedActivity();
  
  public abstract void onAttachmentChanged();
  
  public abstract void onAttachmentError(int paramInt, Uri paramUri, boolean paramBoolean);
  
  public abstract void onMaxPendingMessagesReached();
  
  public abstract void onMessageSent();
  
  public abstract void onPreMessageSent();
  
  public abstract void onProtocolChanged(boolean paramBoolean);
}

/* Location:
 * Qualified Name:     com.android.mms.data.WorkingMessage.MessageStatusListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */