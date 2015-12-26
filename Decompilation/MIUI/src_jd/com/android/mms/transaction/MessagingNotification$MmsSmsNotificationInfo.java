package com.android.mms.transaction;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import com.android.mms.util.VibratorManager;
import com.xiaomi.mms.transaction.MsgThread;
import java.util.Set;

final class MessagingNotification$MmsSmsNotificationInfo
{
  public int mBlockType;
  public Intent mClickIntent;
  public int mCount;
  public String mDescription;
  public Intent mFullScreenIntent;
  public int mIconResourceId;
  public Uri mMsgUri;
  public int mSlotId;
  public MsgThread mThread;
  public CharSequence mTicker;
  public long mTimeMillis;
  public String mTitle;
  
  public MessagingNotification$MmsSmsNotificationInfo(Intent paramIntent1, Intent paramIntent2, String paramString1, int paramInt1, CharSequence paramCharSequence, long paramLong, String paramString2, int paramInt2, int paramInt3, MsgThread paramMsgThread, int paramInt4, Uri paramUri)
  {
    mClickIntent = paramIntent1;
    mFullScreenIntent = paramIntent2;
    mDescription = paramString1;
    mIconResourceId = paramInt1;
    mTicker = paramCharSequence;
    mTimeMillis = paramLong;
    mTitle = paramString2;
    mCount = paramInt2;
    mBlockType = paramInt3;
    mThread = paramMsgThread;
    mSlotId = paramInt4;
    mMsgUri = paramUri;
  }
  
  public void deliver(final Context paramContext, boolean paramBoolean, int paramInt, Set<MsgThread> paramSet)
  {
    if ((paramBoolean) && (MessagingNotification.access$700(mThread)))
    {
      Log.d("Mms:app", "deliver notification for threaId " + mThread.mThreadId + " but only play sms sound, package is:" + mThread.getPackageName());
      MessagingNotification.access$400().post(new Runnable()
      {
        public void run()
        {
          MessagingNotification.access$800(paramContext, mBlockType, mSlotId);
          if (mBlockType != 1) {
            VibratorManager.vibrate(paramContext);
          }
        }
      });
      if ((MessagingNotification.access$900(paramContext)) && (MessagingNotification.access$1000(paramContext)) && (mFullScreenIntent != null)) {
        paramContext.startActivity(mFullScreenIntent);
      }
      return;
    }
    MessagingNotification.access$1100(paramContext, mClickIntent, mFullScreenIntent, mDescription, mIconResourceId, paramBoolean, mTimeMillis, mTitle, mMsgUri);
    Intent localIntent1 = mClickIntent;
    Intent localIntent2 = mFullScreenIntent;
    String str = mDescription;
    int i = mIconResourceId;
    if (paramBoolean) {}
    for (CharSequence localCharSequence = mTicker;; localCharSequence = null)
    {
      MessagingNotification.access$1200(paramContext, localIntent1, localIntent2, str, i, paramBoolean, localCharSequence, mTimeMillis, mTitle, paramInt, mThread, paramSet, mBlockType, mSlotId);
      return;
    }
  }
  
  public long getTime()
  {
    return mTimeMillis;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.MessagingNotification.MmsSmsNotificationInfo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */