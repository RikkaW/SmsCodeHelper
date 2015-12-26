package com.android.mms.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import com.android.mms.util.MSimUtils;
import miui.app.ActionBar;
import miui.os.Build;
import miui.preference.PreferenceActivity;
import miui.telephony.SubscriptionManager.OnSubscriptionsChangedListener;

public class MultiSimPreferenceAcitvity
  extends PreferenceActivity
  implements GetSimInfo
{
  private Context mContext;
  private AdvancedCheckBoxPreference mSim1Pref;
  private AdvancedCheckBoxPreference mSim2Pref;
  private SubscriptionManager.OnSubscriptionsChangedListener mSimInfoChangeListener = new SubscriptionManager.OnSubscriptionsChangedListener()
  {
    public void onSubscriptionsChanged()
    {
      if (!MSimUtils.isMSimInserted())
      {
        Log.d("MultiSimPreferenceAcitvity", "onChange not multi sim is inserted");
        finish();
        return;
      }
      Log.d("MultiSimPreferenceAcitvity", "onChange update sim state");
      MultiSimPreferenceAcitvity.this.updateSimState();
    }
  };
  
  private void initPreference(String paramString)
  {
    mSim1Pref = ((AdvancedCheckBoxPreference)findPreference("pref_key_sim1"));
    mSim2Pref = ((AdvancedCheckBoxPreference)findPreference("pref_key_sim2"));
    mSim1Pref.init(mContext, 0);
    mSim2Pref.init(mContext, 1);
    setPreferenceDefaultValue(paramString);
  }
  
  private void setPreferenceDefaultValue(String paramString)
  {
    boolean bool = false;
    SharedPreferences localSharedPreferences = getSharedPreferences("com.android.mms_preferences", 1);
    long l1 = MSimUtils.getSimIdBySlotId(0);
    long l2 = MSimUtils.getSimIdBySlotId(1);
    String str = MSimUtils.createKeyWithSimId(l1, paramString);
    mSim1Pref.setKey(str);
    str = MSimUtils.createKeyWithSimId(l2, paramString);
    mSim2Pref.setKey(str);
    if ("pref_key_mms_retrieval_during_roaming".equals(paramString))
    {
      paramString = MSimUtils.createKeyWithSimId(l1, "pref_key_mms_auto_retrieval");
      mSim1Pref.setEnabled(localSharedPreferences.getBoolean(paramString, true));
      paramString = MSimUtils.createKeyWithSimId(l2, "pref_key_mms_auto_retrieval");
      mSim2Pref.setEnabled(localSharedPreferences.getBoolean(paramString, true));
      mSim1Pref.setChecked(localSharedPreferences.getBoolean(mSim1Pref.getKey(), true));
      mSim2Pref.setChecked(localSharedPreferences.getBoolean(mSim2Pref.getKey(), true));
      return;
    }
    if ("pref_key_mms_auto_retrieval".equals(paramString))
    {
      mSim1Pref.setChecked(localSharedPreferences.getBoolean(mSim1Pref.getKey(), true));
      mSim2Pref.setChecked(localSharedPreferences.getBoolean(mSim2Pref.getKey(), true));
      return;
    }
    if ("pref_key_delivery_reports".equals(paramString))
    {
      if (Build.IS_CM_CUSTOMIZATION_TEST) {}
      for (;;)
      {
        mSim1Pref.setChecked(localSharedPreferences.getBoolean(mSim1Pref.getKey(), bool));
        mSim2Pref.setChecked(localSharedPreferences.getBoolean(mSim2Pref.getKey(), bool));
        return;
        bool = true;
      }
    }
    mSim1Pref.setChecked(localSharedPreferences.getBoolean(mSim1Pref.getKey(), false));
    mSim2Pref.setChecked(localSharedPreferences.getBoolean(mSim2Pref.getKey(), false));
  }
  
  private void updateSimState()
  {
    if (mSim1Pref != null) {
      mSim1Pref.setNotifyChanged(mContext, 0);
    }
    if (mSim2Pref != null) {
      mSim2Pref.setNotifyChanged(mContext, 1);
    }
  }
  
  public int getSimDisplayIcon(int paramInt)
  {
    return MSimUtils.getSimIconResId(paramInt);
  }
  
  public String getSimDisplayName(int paramInt)
  {
    return MSimUtils.getSimDisplayName(this, paramInt);
  }
  
  public String getSimNumber(int paramInt)
  {
    return MSimUtils.getSimNumber(this, paramInt);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    mContext = this;
    paramBundle = getActionBar();
    if (paramBundle != null) {
      paramBundle.setDisplayHomeAsUpEnabled(true);
    }
    addPreferencesFromResource(2131099650);
    paramBundle = getIntent();
    String str = paramBundle.getStringExtra("preference_key");
    setTitle(paramBundle.getIntExtra("preference_title", 0));
    initPreference(str);
    MSimUtils.registerChangeListener(mContext, mSimInfoChangeListener);
  }
  
  protected void onDestroy()
  {
    MSimUtils.unregisterChangeListener(mContext, mSimInfoChangeListener);
    super.onDestroy();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MultiSimPreferenceAcitvity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */