package com.android.mms.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.util.Log;
import com.android.mms.util.MSimUtils;
import miui.app.ActionBar;
import miui.preference.PreferenceActivity;
import miui.telephony.SubscriptionManager.OnSubscriptionsChangedListener;

public class SelectCardPreferenceActivity
  extends PreferenceActivity
  implements GetSimInfo
{
  private Context mContext;
  private String mPreferenceKey;
  private AdvancedEditorPreference mSim1Pref;
  private AdvancedEditorPreference mSim2Pref;
  private SubscriptionManager.OnSubscriptionsChangedListener mSimInfoChangeListener = new SubscriptionManager.OnSubscriptionsChangedListener()
  {
    public void onSubscriptionsChanged()
    {
      if (!MSimUtils.isMSimInserted())
      {
        Log.d("SelectCardPreferenceActivity", "onChange not multi sim is inserted");
        finish();
        return;
      }
      Log.d("SelectCardPreferenceActivity", "onChange update sim state");
      SelectCardPreferenceActivity.this.updateSimState();
    }
  };
  
  private boolean canSetSmscAddressPref(int paramInt)
  {
    return MSimUtils.getPhoneType(paramInt) != 2;
  }
  
  private void initPreference()
  {
    mSim1Pref = ((AdvancedEditorPreference)findPreference("pref_key_sim1"));
    mSim2Pref = ((AdvancedEditorPreference)findPreference("pref_key_sim2"));
    if (mSim1Pref != null)
    {
      mSim1Pref.init(mContext, 0);
      mSim1Pref.setEnabled(canSetSmscAddressPref(0));
    }
    if (mSim2Pref != null)
    {
      mSim2Pref.init(mContext, 1);
      mSim2Pref.setEnabled(canSetSmscAddressPref(1));
    }
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
    return MSimUtils.getSimDisplayName(mContext, paramInt);
  }
  
  public String getSimNumber(int paramInt)
  {
    return MSimUtils.getSimNumber(mContext, paramInt);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    mContext = this;
    paramBundle = getActionBar();
    if (paramBundle != null) {
      paramBundle.setDisplayHomeAsUpEnabled(true);
    }
    addPreferencesFromResource(2131099651);
    paramBundle = getIntent();
    mPreferenceKey = paramBundle.getStringExtra("preference_key");
    setTitle(paramBundle.getIntExtra("preference_title", 0));
    initPreference();
    MSimUtils.registerChangeListener(mContext, mSimInfoChangeListener);
  }
  
  protected void onDestroy()
  {
    MSimUtils.unregisterChangeListener(mContext, mSimInfoChangeListener);
    super.onDestroy();
  }
  
  public boolean onPreferenceTreeClick(PreferenceScreen paramPreferenceScreen, Preference paramPreference)
  {
    if ("pref_key_edit_sim_smsc_address".equals(mPreferenceKey)) {
      MSimUtils.startSmscAddressActivity(this, ((AdvancedEditorPreference)paramPreference).getSlotId());
    }
    return super.onPreferenceTreeClick(paramPreferenceScreen, paramPreference);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SelectCardPreferenceActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */