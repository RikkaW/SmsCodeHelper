package com.xiaomi.mms.utils;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.android.mms.util.MSimUtils;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import com.xiaomi.accountsdk.activate.ActivateManager;
import com.xiaomi.accountsdk.activate.ActivateManager.ActivateManagerFuture;
import com.xiaomi.micloudsdk.request.DefaultRequestEnv;
import com.xiaomi.mms.transaction.PushSession;
import com.xiaomi.mms.utils.logger.MyLog;

public class MxRequestEnv
  extends DefaultRequestEnv
{
  private static MxRequestEnv sMxRequestEnv = null;
  private Context mContext;
  private ExtendedAuthToken[] mExtendedAuthToken;
  private volatile boolean mIsMxV2 = false;
  private volatile int mSimIndex = 0;
  
  private MxRequestEnv(Context paramContext)
  {
    mContext = paramContext;
    mExtendedAuthToken = new ExtendedAuthToken[MSimUtils.getMultiSimCount()];
  }
  
  private String getFileTokenPref()
  {
    if (mIsMxV2) {
      return "pref_mx2_file_token";
    }
    return "pref_file_token";
  }
  
  public static MxRequestEnv getMxRequestEnv(Context paramContext)
  {
    try
    {
      if (sMxRequestEnv == null) {
        sMxRequestEnv = new MxRequestEnv(paramContext);
      }
      paramContext = sMxRequestEnv;
      return paramContext;
    }
    finally {}
  }
  
  public String getAccountName()
  {
    return PushSession.getInstance(mContext).getMyMid(mSimIndex);
  }
  
  public void invalidateAuthToken()
  {
    mExtendedAuthToken[mSimIndex] = null;
    PrefUtils.remove(mContext, getFileTokenPref() + mSimIndex);
  }
  
  public ExtendedAuthToken queryAuthToken()
  {
    for (;;)
    {
      try
      {
        str = getFileTokenPref();
        str = PrefUtils.getString(mContext, str + mSimIndex);
        mExtendedAuthToken[mSimIndex] = ExtendedAuthToken.parse(str);
        if (mExtendedAuthToken[mSimIndex] != null) {
          return mExtendedAuthToken[mSimIndex];
        }
        str = PushSession.getInstance(mContext).getMyMid(mSimIndex);
        if (!TextUtils.isEmpty(str))
        {
          Object localObject = ActivateManager.get(mContext);
          int i = mSimIndex;
          if (!mIsMxV2) {
            continue;
          }
          str = MxConfigCompat.getMxV2Sid(mContext, str);
          localObject = (Bundle)((ActivateManager)localObject).getSimAuthToken(i, str).getResult();
          str = ((Bundle)localObject).getString("user_token");
          localObject = ((Bundle)localObject).getString("user_security");
          if ((!TextUtils.isEmpty((CharSequence)localObject)) && (!TextUtils.isEmpty(str))) {
            mExtendedAuthToken[mSimIndex] = ExtendedAuthToken.build(str, (String)localObject);
          }
        }
      }
      catch (Exception localException)
      {
        String str;
        MyLog.e("failed to get file upload token");
        continue;
      }
      return mExtendedAuthToken[mSimIndex];
      str = MxConfigCompat.getMxV1Sid(mContext, str);
    }
  }
  
  public MxRequestEnv setIsMxV2(boolean paramBoolean)
  {
    mIsMxV2 = paramBoolean;
    return this;
  }
  
  public MxRequestEnv setSimIndex(int paramInt)
  {
    mSimIndex = paramInt;
    return this;
  }
  
  public boolean shouldUpdateHost()
  {
    return !mIsMxV2;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.utils.MxRequestEnv
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */