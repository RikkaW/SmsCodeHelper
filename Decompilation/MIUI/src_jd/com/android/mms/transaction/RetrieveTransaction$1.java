package com.android.mms.transaction;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Telephony.MmsSms;
import com.android.mms.MmsConfig;

class RetrieveTransaction$1
  implements ProgressReceiver
{
  RetrieveTransaction$1(RetrieveTransaction paramRetrieveTransaction) {}
  
  public void onProgress(long paramLong1, long paramLong2)
  {
    if (paramLong1 < 0L) {
      return;
    }
    long l = paramLong2;
    if (paramLong2 <= 0L) {
      l = RetrieveTransaction.access$000(this$0);
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
 * Qualified Name:     com.android.mms.transaction.RetrieveTransaction.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */