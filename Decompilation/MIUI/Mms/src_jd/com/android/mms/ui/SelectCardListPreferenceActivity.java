package com.android.mms.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.util.Log;
import com.android.mms.util.MSimUtils;
import miui.app.ActionBar;
import miui.preference.PreferenceActivity;
import miui.telephony.SubscriptionManager.OnSubscriptionsChangedListener;

public class SelectCardListPreferenceActivity
  extends PreferenceActivity
  implements Preference.OnPreferenceChangeListener, GetSimInfo
{
  private Context mContext;
  private String mPreferenceKey;
  private AdvancedValueListPreference mSim1Pref;
  private AdvancedValueListPreference mSim2Pref;
  private SubscriptionManager.OnSubscriptionsChangedListener mSimInfoChangeListener = new SubscriptionManager.OnSubscriptionsChangedListener()
  {
    public void onSubscriptionsChanged()
    {
      if (!MSimUtils.isMSimInserted())
      {
        Log.d("SelectCardListPreferenceActivity", "onChange not multi sim is inserted");
        finish();
        return;
      }
      Log.d("SelectCardListPreferenceActivity", "onChange update sim state");
      SelectCardListPreferenceActivity.this.updateSimState();
    }
  };
  
  public static void fillMmsValidityValue(Context paramContext, ValueListPreference paramValueListPreference, String paramString)
  {
    int i = getMmsValidityPerioIndex(paramString);
    paramValueListPreference.setRightValue(paramContext.getResources().getStringArray(2131230733)[i]);
  }
  
  public static void fillSmsValidityValue(Context paramContext, ValueListPreference paramValueListPreference, String paramString)
  {
    int i = getSmsValidityPerioIndex(paramString);
    paramValueListPreference.setRightValue(paramContext.getResources().getStringArray(2131230731)[i]);
  }
  
  public static int getMmsValidityPerioIndex(String paramString)
  {
    int j = Integer.parseInt(paramString);
    int i;
    if (j == 0) {
      i = 0;
    }
    do
    {
      return i;
      i = j;
    } while (j != 172800);
    return 1;
  }
  
  public static int getSmsValidityPerioIndex(String paramString)
  {
    int i = Integer.parseInt(paramString);
    if (i == -1) {
      return 0;
    }
    if (i == 60) {
      return 1;
    }
    if (i == 360) {
      return 2;
    }
    if (i == 720) {
      return 3;
    }
    if (i == 1440) {
      return 4;
    }
    if (i == 635040) {
      return 5;
    }
    return 0;
  }
  
  private void initPreference()
  {
    mSim1Pref = ((AdvancedValueListPreference)findPreference("pref_key_sim1"));
    mSim2Pref = ((AdvancedValueListPreference)findPreference("pref_key_sim2"));
    SharedPreferences localSharedPreferences = getSharedPreferences("com.android.mms_preferences", 1);
    if (mSim1Pref != null)
    {
      mSim1Pref.init(mContext, 0);
      setPreference(mSim1Pref, localSharedPreferences);
      mSim1Pref.setOnPreferenceChangeListener(this);
    }
    if (mSim2Pref != null)
    {
      mSim2Pref.init(mContext, 1);
      setPreference(mSim2Pref, localSharedPreferences);
      mSim2Pref.setOnPreferenceChangeListener(this);
    }
  }
  
  private void setPreference(AdvancedValueListPreference paramAdvancedValueListPreference, SharedPreferences paramSharedPreferences)
  {
    String str = MSimUtils.createKeyWithSimId(MSimUtils.getSimIdBySlotId(paramAdvancedValueListPreference.getSlotId()), mPreferenceKey);
    paramAdvancedValueListPreference.setKey(str);
    paramAdvancedValueListPreference.setPersistent(true);
    if ("pref_key_mms_validity_period".equals(mPreferenceKey))
    {
      paramAdvancedValueListPreference.setEntries(2131230733);
      paramAdvancedValueListPreference.setEntryValues(2131230734);
      paramAdvancedValueListPreference.setListTitleId(2131362368);
      fillMmsValidityValue(this, paramAdvancedValueListPreference, paramSharedPreferences.getString(str, "0"));
    }
    while (!"pref_key_sms_validity_period".equals(mPreferenceKey)) {
      return;
    }
    paramAdvancedValueListPreference.setEntries(2131230731);
    paramAdvancedValueListPreference.setEntryValues(2131230732);
    paramAdvancedValueListPreference.setListTitleId(2131362367);
    fillSmsValidityValue(this, paramAdvancedValueListPreference, paramSharedPreferences.getString(paramAdvancedValueListPreference.getKey(), "-1"));
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
    addPreferencesFromResource(2131099652);
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
  
  public boolean onPreferenceChange(Preference paramPreference, Object paramObject)
  {
    if ("pref_key_mms_validity_period".equals(mPreferenceKey)) {
      fillMmsValidityValue(this, (ValueListPreference)paramPreference, (String)paramObject);
    }
    for (;;)
    {
      return true;
      fillSmsValidityValue(this, (ValueListPreference)paramPreference, (String)paramObject);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SelectCardListPreferenceActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */