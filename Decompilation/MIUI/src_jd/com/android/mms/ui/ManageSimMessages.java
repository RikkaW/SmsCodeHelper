package com.android.mms.ui;

import android.app.ActionBar.Tab;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.transaction.MmsSystemEventReceiver;
import com.android.mms.transaction.MmsSystemEventReceiver.SimStateChangedListener;
import com.android.mms.util.MSimUtils;
import miui.app.ActionBar;
import miui.app.Activity;
import miui.os.Build;
import miui.telephony.SubscriptionManager.OnSubscriptionsChangedListener;

public class ManageSimMessages
  extends Activity
  implements ISmsTextSizeAdjustHost
{
  private ActionBar mActionBar;
  private SubscriptionManager.OnSubscriptionsChangedListener mSimInfoChangeListener = new SubscriptionManager.OnSubscriptionsChangedListener()
  {
    public void onSubscriptionsChanged()
    {
      Log.d("ManageSimMessages", "update sim info change");
      ManageSimMessages.this.updateSimState();
    }
  };
  private SimState mSimState = new SimState(this);
  private MmsSystemEventReceiver.SimStateChangedListener mSimStateChagnedListener = new MmsSystemEventReceiver.SimStateChangedListener()
  {
    public void onSimStateChanged(String paramAnonymousString)
    {
      Log.d("ManageSimMessages", "update sim info change");
      if (!MSimUtils.isSimInserted()) {
        finish();
      }
    }
  };
  
  private int getTabPosition(int paramInt)
  {
    if (paramInt == 0) {}
    while (paramInt != 1) {
      return 0;
    }
    return mActionBar.getFragmentTabCount() - 1;
  }
  
  private void registerSimRelatedListener()
  {
    if (MSimUtils.isMSim())
    {
      MSimUtils.registerChangeListener(this, mSimInfoChangeListener);
      return;
    }
    MmsSystemEventReceiver.getInstance().registerSimStateChangedListener(mSimStateChagnedListener);
  }
  
  private void setSelected(int paramInt)
  {
    if (paramInt == 0) {
      if (mSimState.mSim0Inserted)
      {
        paramInt = getTabPosition(paramInt);
        mActionBar.selectTab(mActionBar.getTabAt(paramInt));
        Log.d("ManageSimMessages", "set selected is slotId 0");
      }
    }
    while ((paramInt != 1) || (!mSimState.mSim1Inserted)) {
      return;
    }
    paramInt = getTabPosition(paramInt);
    mActionBar.selectTab(mActionBar.getTabAt(paramInt));
    Log.d("ManageSimMessages", "set selected is slotId 1");
  }
  
  private void unRegisterSimRelatedListener()
  {
    if (MSimUtils.isMSim())
    {
      MSimUtils.unregisterChangeListener(this, mSimInfoChangeListener);
      return;
    }
    MmsSystemEventReceiver.getInstance().unRegisterSimStateChangedListener(mSimStateChagnedListener);
  }
  
  private void updateFragments()
  {
    boolean bool = false;
    if (!mSimState.mStateChanged) {
      return;
    }
    FragmentManager localFragmentManager = getFragmentManager();
    FragmentTransaction localFragmentTransaction = localFragmentManager.beginTransaction();
    if (mActionBar.getFragmentTabCount() > 0) {
      mActionBar.removeAllFragmentTab();
    }
    if (mSimState.mSim0Inserted)
    {
      localObject = new Bundle();
      ((Bundle)localObject).putInt(MSimUtils.SLOT_ID, 0);
      mActionBar.addFragmentTab("sim0", mActionBar.newTab().setText(""), SimMessagesFragment.class, (Bundle)localObject, false);
      Log.d("ManageSimMessages", "updateFragments add sim0 fragment");
    }
    if (mSimState.mSim1Inserted)
    {
      localObject = new Bundle();
      ((Bundle)localObject).putInt(MSimUtils.SLOT_ID, 1);
      mActionBar.addFragmentTab("sim1", mActionBar.newTab().setText(""), SimMessagesFragment.class, (Bundle)localObject, false);
      Log.d("ManageSimMessages", "updateFragments add sim1 fragment");
    }
    Object localObject = mActionBar;
    if (mActionBar.getFragmentTabCount() <= 1) {
      bool = true;
    }
    ((ActionBar)localObject).setTabsMode(bool);
    localFragmentTransaction.commitAllowingStateLoss();
    localFragmentManager.executePendingTransactions();
  }
  
  private void updateSimInfos()
  {
    updateSimInfo(0);
    updateSimInfo(1);
  }
  
  private void updateSimState()
  {
    mSimState.updateSimState();
    if (mSimState.mSimCount == 0)
    {
      Log.d("ManageSimMessages", "updateSimState sim count == 0");
      finish();
      return;
    }
    updateFragments();
    updateSimInfos();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    mActionBar = getActionBar();
    mActionBar.setFragmentViewPagerMode(this, getFragmentManager());
    updateSimState();
    setSelected(MSimUtils.getSlotIdFromIntent(getIntent()));
    MessagingNotification.cancelNotification(getApplicationContext(), 234);
    registerSimRelatedListener();
  }
  
  protected void onDestroy()
  {
    unRegisterSimRelatedListener();
    super.onDestroy();
  }
  
  public void onStart()
  {
    super.onStart();
    SmsTextSizeAdjust.getInstance().init(this, this);
    SmsTextSizeAdjust.getInstance().refresh();
  }
  
  public void onStop()
  {
    super.onStop();
    SmsTextSizeAdjust.getInstance();
    SmsTextSizeAdjust.clear(this);
  }
  
  public void setTextSize(float paramFloat)
  {
    SimMessagesFragment.sTextSize = paramFloat;
  }
  
  public void updateSimInfo(int paramInt)
  {
    int i;
    int j;
    if (paramInt == 0) {
      if (mSimState.mSim0Inserted)
      {
        i = getTabPosition(paramInt);
        j = ((SimMessagesFragment)mActionBar.getFragmentAt(i)).getMessageCount();
        mSimState.updateSimInfo(paramInt, j);
        if (mSimState.mInfoChanged) {
          break label58;
        }
      }
    }
    label58:
    do
    {
      do
      {
        return;
        if (mActionBar.getFragmentTabCount() > 1) {
          mActionBar.getTabAt(i).setText(mSimState.mSim0Name);
        }
        for (;;)
        {
          Log.d("ManageSimMessages", "update sim info for slotId 0");
          return;
          mActionBar.setTitle(mSimState.mSim0Name);
        }
      } while ((paramInt != 1) || (!mSimState.mSim1Inserted));
      i = getTabPosition(paramInt);
      j = ((SimMessagesFragment)mActionBar.getFragmentAt(i)).getMessageCount();
      mSimState.updateSimInfo(paramInt, j);
    } while (!mSimState.mInfoChanged);
    if (mActionBar.getFragmentTabCount() > 1) {
      mActionBar.getTabAt(i).setText(mSimState.mSim1Name);
    }
    for (;;)
    {
      Log.d("ManageSimMessages", "update sim info for slotId 1");
      return;
      mActionBar.setTitle(mSimState.mSim1Name);
    }
  }
  
  static class SimState
  {
    public Context mContext;
    public boolean mInfoChanged;
    public boolean mSim0Inserted;
    public String mSim0Name;
    public boolean mSim1Inserted;
    public String mSim1Name;
    public int mSimCount;
    public boolean mStateChanged;
    
    public SimState(Context paramContext)
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
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ManageSimMessages
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */