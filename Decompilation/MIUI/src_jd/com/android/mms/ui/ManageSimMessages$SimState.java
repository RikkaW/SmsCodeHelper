package com.android.mms.ui;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.util.MSimUtils;
import miui.os.Build;

class ManageSimMessages$SimState
{
  public Context mContext;
  public boolean mInfoChanged;
  public boolean mSim0Inserted;
  public String mSim0Name;
  public boolean mSim1Inserted;
  public String mSim1Name;
  public int mSimCount;
  public boolean mStateChanged;
  
  public ManageSimMessages$SimState(Context paramContext)
  {
    mContext = paramContext;
  }
  
  private boolean checkEquals(String paramString1, String paramString2)
  {
    return (paramString1 != null) && (paramString1.equals(paramString2));
  }
  
  private String getSimDisplayName(int paramInt1, int paramInt2)
  {
    Object localObject2 = MSimUtils.getSimDisplayName(mContext, paramInt1);
    Object localObject1 = localObject2;
    if (TextUtils.isEmpty((CharSequence)localObject2))
    {
      if (!MSimUtils.isMSim()) {
        break label95;
      }
      if (paramInt1 != 1) {
        break label89;
      }
      paramInt1 = 2131362236;
    }
    for (;;)
    {
      localObject1 = mContext.getResources().getString(paramInt1);
      localObject2 = localObject1;
      if (Build.IS_CM_CUSTOMIZATION_TEST) {
        localObject2 = (String)localObject1 + " (" + paramInt2 + ")";
      }
      return (String)localObject2;
      label89:
      paramInt1 = 2131362235;
      continue;
      label95:
      paramInt1 = 2131361798;
    }
  }
  
  public void updateSimInfo(int paramInt1, int paramInt2)
  {
    mInfoChanged = false;
    if (paramInt1 == 0)
    {
      str = getSimDisplayName(paramInt1, paramInt2);
      if (!checkEquals(str, mSim0Name)) {
        mInfoChanged = true;
      }
      mSim0Name = str;
    }
    do
    {
      return;
      if (paramInt1 == 1)
      {
        str = getSimDisplayName(paramInt1, paramInt2);
        if (!checkEquals(str, mSim1Name)) {
          mInfoChanged = true;
        }
        mSim1Name = str;
        return;
      }
    } while (paramInt1 != -1);
    String str = getSimDisplayName(0, paramInt2);
    if (!checkEquals(str, mSim0Name)) {
      mInfoChanged = true;
    }
    mSim0Name = str;
    str = getSimDisplayName(1, paramInt2);
    if (!checkEquals(str, mSim1Name)) {
      mInfoChanged = true;
    }
    mSim1Name = str;
  }
  
  public void updateSimState()
  {
    mSimCount = 0;
    mStateChanged = false;
    boolean bool = MSimUtils.isSimInserted(0);
    if (mSim0Inserted != bool) {
      mStateChanged = true;
    }
    mSim0Inserted = bool;
    if (mSim0Inserted) {
      mSimCount += 1;
    }
    bool = MSimUtils.isSimInserted(1);
    if (mSim1Inserted != bool) {
      mStateChanged = true;
    }
    mSim1Inserted = bool;
    if (mSim1Inserted) {
      mSimCount += 1;
    }
    Log.d("ManageSimMessages", "updateSimState sim count is " + mSimCount);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ManageSimMessages.SimState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */