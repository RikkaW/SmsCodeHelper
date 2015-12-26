package com.android.mms.transaction;

import android.content.Context;

final class MessagingNotification$MmsSmsDeliveryInfo
{
  public int mSlotId;
  public CharSequence mTicker;
  public long mTimeMillis;
  
  public MessagingNotification$MmsSmsDeliveryInfo(CharSequence paramCharSequence, long paramLong, int paramInt)
  {
    mTicker = paramCharSequence;
    mTimeMillis = paramLong;
    mSlotId = paramInt;
  }
  
  public void deliver(Context paramContext, boolean paramBoolean)
  {
    MessagingNotification.access$600(paramContext, paramBoolean, mTicker, mTimeMillis, mSlotId);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.MessagingNotification.MmsSmsDeliveryInfo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */