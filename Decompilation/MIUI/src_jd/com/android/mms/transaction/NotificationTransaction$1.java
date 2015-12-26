package com.android.mms.transaction;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Telephony.MmsSms;
import com.android.mms.MmsConfig;
import com.google.android.mms.pdu.NotificationInd;

class NotificationTransaction$1
  implements ProgressReceiver
{
  NotificationTransaction$1(NotificationTransaction paramNotificationTransaction) {}
  
  public void onProgress(long paramLong1, long paramLong2)
  {
    if (paramLong1 < 0L) {
      return;
    }
    long l = paramLong2;
    if (paramLong2 <= 0L) {
      l = NotificationTransaction.access$000(this$0).getMessageSize();
    }
    paramLong2 = l;
    if (l <= 0L) {
      paramLong2 = MmsConfig.getMaxMessageSize();
    }
    l = paramLong1;
    if (paramLong1 > paramLong2) {
      l = paramLong2;
    }
    Transaction.sCurrentTransactionProgress = (int)(100L * l / paramLong2);
    this$0.mContext.getContentResolver().notifyChange(Telephony.MmsSms.CONTENT_CONVERSATIONS_URI, null);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.NotificationTransaction.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */