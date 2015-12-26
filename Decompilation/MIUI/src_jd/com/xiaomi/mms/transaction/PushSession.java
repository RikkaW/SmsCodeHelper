package com.xiaomi.mms.transaction;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.android.mms.util.MSimUtils;

public class PushSession
{
  private static PushSession mInstance;
  private final PushAccountInfo[] mPushAccountInfos = new PushAccountInfo[MSimUtils.getMultiSimCount()];
  
  private PushSession(Context paramContext)
  {
    int i = 0;
    while (i < mPushAccountInfos.length)
    {
      mPushAccountInfos[i] = new PushAccountInfo(null);
      i += 1;
    }
  }
  
  public static PushSession getInstance(Context paramContext)
  {
    try
    {
      if (mInstance == null) {
        mInstance = new PushSession(paramContext);
      }
      paramContext = mInstance;
      return paramContext;
    }
    finally {}
  }
  
  private void notifyChannelChanged(Context paramContext, int paramInt)
  {
    Intent localIntent = new Intent("com.xiaomi.mms.PUSH_STATUS_CHANGED");
    localIntent.setPackage(paramContext.getPackageName());
    localIntent.putExtra("extra_sim_index", paramInt);
    paramContext.sendBroadcast(localIntent);
  }
  
  private void resetPushAccountInfo(int paramInt)
  {
    mPushAccountInfos[paramInt].myMid = null;
    mPushAccountInfos[paramInt].myFullMid = null;
    mPushAccountInfos[paramInt].myPhone = null;
    mPushAccountInfos[paramInt].status = Status.DISCONNECTED;
  }
  
  private void setMyFullMid(int paramInt, String paramString1, String paramString2)
  {
    mPushAccountInfos[paramInt].myMid = paramString1;
    mPushAccountInfos[paramInt].myFullMid = (paramString1 + "@xiaomi.com" + "/" + paramString2);
  }
  
  private void setMyPhone(int paramInt, String paramString)
  {
    mPushAccountInfos[paramInt].myPhone = paramString;
  }
  
  public int getConnectedSimIndex()
  {
    int i = 0;
    while (i < mPushAccountInfos.length)
    {
      if (isConnected(i)) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  public String getMyFullMid(int paramInt)
  {
    return mPushAccountInfos[paramInt].myFullMid;
  }
  
  public String getMyMid(int paramInt)
  {
    if (MSimUtils.isMSimSlotIdValid(paramInt)) {
      return mPushAccountInfos[paramInt].myMid;
    }
    return null;
  }
  
  public int getSimIdByMid(String paramString)
  {
    int j;
    if ((paramString == null) || (TextUtils.isEmpty(paramString)))
    {
      j = -1;
      return j;
    }
    int i = 0;
    for (;;)
    {
      if (i >= mPushAccountInfos.length) {
        break label51;
      }
      j = i;
      if (paramString.equals(mPushAccountInfos[i].myMid)) {
        break;
      }
      i += 1;
    }
    label51:
    return -1;
  }
  
  public Status getStatus(int paramInt)
  {
    return mPushAccountInfos[paramInt].status;
  }
  
  public boolean isConnected(int paramInt)
  {
    PushAccountInfo localPushAccountInfo = mPushAccountInfos[paramInt];
    return (status == Status.CONNECTED) && (myFullMid != null) && (myMid != null) && (myPhone != null);
  }
  
  void resetAllStatus(Context paramContext, Status paramStatus)
  {
    paramContext = mPushAccountInfos;
    int j = paramContext.length;
    int i = 0;
    while (i < j)
    {
      status = paramStatus;
      i += 1;
    }
  }
  
  void setMyFullMidAndPhone(int paramInt, String paramString1, String paramString2, String paramString3)
  {
    int i = 0;
    if (i < mPushAccountInfos.length)
    {
      if (!MSimUtils.isSimInserted(i)) {
        resetPushAccountInfo(i);
      }
      for (;;)
      {
        i += 1;
        break;
        if ((i != paramInt) && ((TextUtils.equals(paramString1, mPushAccountInfos[i].myMid)) || (TextUtils.equals(paramString3, mPushAccountInfos[i].myPhone)))) {
          resetPushAccountInfo(i);
        }
      }
    }
    if (MSimUtils.isSimInserted(paramInt))
    {
      setMyFullMid(paramInt, paramString1, paramString2);
      setMyPhone(paramInt, paramString3);
    }
  }
  
  void setStatus(Context paramContext, int paramInt, Status paramStatus)
  {
    if (mPushAccountInfos[paramInt].status != paramStatus) {}
    for (int i = 1;; i = 0)
    {
      mPushAccountInfos[paramInt].status = paramStatus;
      if (i != 0) {
        notifyChannelChanged(paramContext, paramInt);
      }
      return;
    }
  }
  
  private static class PushAccountInfo
  {
    public volatile String myFullMid;
    public volatile String myMid;
    public volatile String myPhone;
    public volatile PushSession.Status status = PushSession.Status.DISCONNECTED;
  }
  
  public static enum Status
  {
    CONNECTED,  DISCONNECTED;
    
    private Status() {}
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.transaction.PushSession
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */