package com.android.mms.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.util.Log;
import com.android.mms.MmsConfig;
import com.android.mms.transaction.MmsSystemEventReceiver;
import com.android.mms.transaction.MmsSystemEventReceiver.SimStateChangedListener;
import com.android.mms.util.MSimUtils;
import miui.app.AlertDialog.Builder;
import miui.os.Build;
import miui.preference.PreferenceActivity;
import miui.telephony.SubscriptionManager.OnSubscriptionsChangedListener;

public class MessagingAdvancedPreferenceActivity
  extends PreferenceActivity
  implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener
{
  private Preference mAutoRetrievalPref;
  private PreferenceCategory mCardFormatCat;
  private Preference mFestivalNetWorkingPref;
  private PreferenceCategory mForwardSettingsCat;
  private CheckBoxPreference mHideFestivalPref;
  private Preference mManageSimPref;
  private Preference mMmsValidityPeriodPref;
  private PreferenceCategory mOtherCat;
  private Preference mReadReportPref;
  private Preference mRetrievalRoamingPref;
  private PreferenceCategory mSettingsCat;
  private int mSimCount = 0;
  private SubscriptionManager.OnSubscriptionsChangedListener mSimInfoChangeListener = new SubscriptionManager.OnSubscriptionsChangedListener()
  {
    public void onSubscriptionsChanged()
    {
      Log.d("MessagingAdvancedPreferenceActivity", "update sim info change");
      MessagingAdvancedPreferenceActivity.this.updateSimRelatedPrefs();
    }
  };
  private MmsSystemEventReceiver.SimStateChangedListener mSimStateChagnedListener = new MmsSystemEventReceiver.SimStateChangedListener()
  {
    public void onSimStateChanged(String paramAnonymousString)
    {
      Log.d("MessagingAdvancedPreferenceActivity", "update sim info change");
      MessagingAdvancedPreferenceActivity.this.updateSimRelatedPrefs();
    }
  };
  private Preference mSmscAddressPref;
  private Preference mStorageStatusPref;
  
  private boolean canSetSmscAddressPref()
  {
    if (mSimCount == 1)
    {
      if (MSimUtils.getPhoneType(MSimUtils.getInsertedSlotId()) == 2) {}
    }
    else {
      while (mSimCount > 1) {
        return true;
      }
    }
    return false;
  }
  
  private void cleanMmsRelatedPrefs()
  {
    mSettingsCat.removeAll();
    mReadReportPref = null;
    mAutoRetrievalPref = null;
    mRetrievalRoamingPref = null;
    mMmsValidityPeriodPref = null;
    getPreferenceScreen().removePreference(mSettingsCat);
  }
  
  private void createAutoRetrievalPref(SharedPreferences paramSharedPreferences, long paramLong)
  {
    String str = "pref_key_mms_auto_retrieval";
    if (mSimCount > 1) {}
    CheckBoxPreference localCheckBoxPreference;
    for (mAutoRetrievalPref = new Preference(this);; mAutoRetrievalPref = localCheckBoxPreference)
    {
      mAutoRetrievalPref.setTitle(2131361910);
      mAutoRetrievalPref.setSummary(2131361911);
      mAutoRetrievalPref.setKey(str);
      return;
      localCheckBoxPreference = new CheckBoxPreference(this);
      localCheckBoxPreference.setDefaultValue(Boolean.valueOf(true));
      if (MSimUtils.isMSim())
      {
        str = MSimUtils.createKeyWithSimId(paramLong, "pref_key_mms_auto_retrieval");
        localCheckBoxPreference.setChecked(paramSharedPreferences.getBoolean(str, true));
      }
    }
  }
  
  private void createReadReportPref(SharedPreferences paramSharedPreferences, long paramLong)
  {
    String str = "pref_key_mms_read_reports";
    if (mSimCount > 1) {}
    CheckBoxPreference localCheckBoxPreference;
    for (mReadReportPref = new Preference(this);; mReadReportPref = localCheckBoxPreference)
    {
      mReadReportPref.setTitle(2131361902);
      mReadReportPref.setSummary(2131361900);
      mReadReportPref.setKey(str);
      return;
      localCheckBoxPreference = new CheckBoxPreference(this);
      localCheckBoxPreference.setDefaultValue(Boolean.valueOf(false));
      if (MSimUtils.isMSim())
      {
        str = MSimUtils.createKeyWithSimId(paramLong, "pref_key_mms_read_reports");
        localCheckBoxPreference.setChecked(paramSharedPreferences.getBoolean(str, false));
      }
    }
  }
  
  private void createRetrievalRoamingPref(SharedPreferences paramSharedPreferences, long paramLong)
  {
    String str = "pref_key_mms_retrieval_during_roaming";
    if (mSimCount > 1) {}
    CheckBoxPreference localCheckBoxPreference;
    for (mRetrievalRoamingPref = new Preference(this);; mRetrievalRoamingPref = localCheckBoxPreference)
    {
      mRetrievalRoamingPref.setTitle(2131361912);
      mRetrievalRoamingPref.setSummary(2131361913);
      mRetrievalRoamingPref.setKey(str);
      return;
      localCheckBoxPreference = new CheckBoxPreference(this);
      localCheckBoxPreference.setDefaultValue(Boolean.valueOf(false));
      if (MSimUtils.isMSim())
      {
        str = MSimUtils.createKeyWithSimId(paramLong, "pref_key_mms_retrieval_during_roaming");
        localCheckBoxPreference.setChecked(paramSharedPreferences.getBoolean(str, false));
      }
    }
  }
  
  private void createValidityPeriodPref(SharedPreferences paramSharedPreferences, long paramLong)
  {
    String str = "pref_key_mms_validity_period";
    if (mSimCount > 1)
    {
      mMmsValidityPeriodPref = new Preference(this);
      mMmsValidityPeriodPref.setOnPreferenceClickListener(this);
    }
    for (paramSharedPreferences = str;; paramSharedPreferences = str)
    {
      mMmsValidityPeriodPref.setTitle(2131362368);
      mMmsValidityPeriodPref.setKey(paramSharedPreferences);
      return;
      ValueListPreference localValueListPreference = new ValueListPreference(this);
      localValueListPreference.setEntries(2131230733);
      localValueListPreference.setEntryValues(2131230734);
      localValueListPreference.setListTitleId(2131362368);
      str = MSimUtils.createKeyWithSimId(paramLong, "pref_key_mms_validity_period");
      SelectCardListPreferenceActivity.fillMmsValidityValue(this, localValueListPreference, paramSharedPreferences.getString(str, "0"));
      mMmsValidityPeriodPref = localValueListPreference;
      mMmsValidityPeriodPref.setOnPreferenceChangeListener(this);
    }
  }
  
  private void dependOnAutoRetrieval()
  {
    if ((mRetrievalRoamingPref != null) && (mAutoRetrievalPref != null) && ((mAutoRetrievalPref instanceof CheckBoxPreference)))
    {
      CheckBoxPreference localCheckBoxPreference = (CheckBoxPreference)mAutoRetrievalPref;
      mRetrievalRoamingPref.setEnabled(localCheckBoxPreference.isChecked());
    }
  }
  
  private void initPreferences()
  {
    addPreferencesFromResource(2131099648);
    mOtherCat = ((PreferenceCategory)findPreference("pref_key_other_settings"));
    mFestivalNetWorkingPref = findPreference("pref_key_festival_networking");
    mSmscAddressPref = findPreference("pref_key_edit_sim_smsc_address");
    mManageSimPref = findPreference("pref_key_manage_sim_messages");
    mHideFestivalPref = ((CheckBoxPreference)findPreference("pref_key_hide_festival"));
    mForwardSettingsCat = ((PreferenceCategory)findPreference("pref_key_forward_settings"));
    mCardFormatCat = ((PreferenceCategory)findPreference("pref_key_card_format"));
    mSettingsCat = ((PreferenceCategory)findPreference("pref_key_mms_settings"));
    mStorageStatusPref = findPreference("pref_key_storage_status");
    updateSimRelatedPrefs();
    removeUnavailablePrefs();
  }
  
  private void onManageSimPrefClick()
  {
    Intent localIntent = new Intent(this, ManageSimMessages.class);
    localIntent.putExtra(MSimUtils.SLOT_ID, 0);
    startActivity(localIntent);
  }
  
  private void onMmsRelatedPrefsClick(Preference paramPreference)
  {
    if (mSimCount > 1)
    {
      localIntent = new Intent(this, MultiSimPreferenceAcitvity.class);
      localIntent.putExtra("preference_key", paramPreference.getKey());
      localIntent.putExtra("preference_title", paramPreference.getTitleRes());
      startActivity(localIntent);
    }
    while (paramPreference != mAutoRetrievalPref)
    {
      Intent localIntent;
      return;
    }
    dependOnAutoRetrieval();
  }
  
  private void onMmsValidityPeriodPrefClick(Preference paramPreference, String paramString)
  {
    if (mSimCount > 1)
    {
      paramString = new Intent(this, SelectCardListPreferenceActivity.class);
      paramString.putExtra("preference_key", paramPreference.getKey());
      paramString.putExtra("preference_title", paramPreference.getTitleRes());
      startActivity(paramString);
      return;
    }
    SelectCardListPreferenceActivity.fillMmsValidityValue(this, (ValueListPreference)paramPreference, paramString);
  }
  
  private void onSmscAddressPrefClick()
  {
    if (mSimCount == 1) {
      MSimUtils.startSmscAddressActivity(this, MSimUtils.getInsertedSlotId());
    }
    while (mSimCount <= 1) {
      return;
    }
    Intent localIntent = new Intent(this, SelectCardPreferenceActivity.class);
    localIntent.putExtra("preference_key", mSmscAddressPref.getKey());
    localIntent.putExtra("preference_title", mSmscAddressPref.getTitleRes());
    startActivity(localIntent);
  }
  
  private void onStorageStatusPrefClick()
  {
    if (Build.IS_CM_CUSTOMIZATION_TEST)
    {
      String str = MessageUtils.getStorageStatus(this);
      new AlertDialog.Builder(this).setTitle(2131361906).setMessage(str).setPositiveButton(17039370, null).setCancelable(true).show();
    }
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
  
  private void removeUnavailablePrefs()
  {
    boolean bool = true;
    if (!Build.IS_CM_CUSTOMIZATION)
    {
      localObject = getPreferenceScreen();
      if (mForwardSettingsCat != null) {
        ((PreferenceScreen)localObject).removePreference(mForwardSettingsCat);
      }
      mForwardSettingsCat = null;
      if (mCardFormatCat != null) {
        ((PreferenceScreen)localObject).removePreference(mCardFormatCat);
      }
      mCardFormatCat = null;
    }
    if ((MessageUtils.isZhCnLanguage()) || (Build.IS_INTERNATIONAL_BUILD))
    {
      mOtherCat.removePreference(mHideFestivalPref);
      if (!Build.IS_INTERNATIONAL_BUILD)
      {
        mFestivalNetWorkingPref.setEnabled(true);
        return;
      }
      mOtherCat.removePreference(mFestivalNetWorkingPref);
      return;
    }
    Object localObject = mFestivalNetWorkingPref;
    if (!mHideFestivalPref.isChecked()) {}
    for (;;)
    {
      ((Preference)localObject).setEnabled(bool);
      return;
      bool = false;
    }
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
  
  private void updateMmsRelatedPrefs()
  {
    cleanMmsRelatedPrefs();
    int i = MSimUtils.getInsertedSlotId();
    Log.d("MessagingAdvancedPreferenceActivity", "updateMmsRelatedPrefs slotId is " + String.valueOf(i));
    if ((mSimCount == 0) || (i == -1) || (!MmsConfig.getMmsEnabled()))
    {
      Log.d("MessagingAdvancedPreferenceActivity", "updateMmsRelatedPreference nothing");
      return;
    }
    long l = MSimUtils.getSimIdBySlotId(i);
    SharedPreferences localSharedPreferences = getSharedPreferences("com.android.mms_preferences", 1);
    getPreferenceScreen().addPreference(mSettingsCat);
    createReadReportPref(localSharedPreferences, l);
    mSettingsCat.addPreference(mReadReportPref);
    createAutoRetrievalPref(localSharedPreferences, l);
    mSettingsCat.addPreference(mAutoRetrievalPref);
    createRetrievalRoamingPref(localSharedPreferences, l);
    mSettingsCat.addPreference(mRetrievalRoamingPref);
    if (Build.IS_CM_CUSTOMIZATION_TEST)
    {
      createValidityPeriodPref(localSharedPreferences, l);
      mSettingsCat.addPreference(mMmsValidityPeriodPref);
    }
    dependOnAutoRetrieval();
  }
  
  private void updateOtherSettingsPrefs()
  {
    mOtherCat.removePreference(mSmscAddressPref);
    if ((getResources().getBoolean(2131492865)) && (canSetSmscAddressPref())) {
      mOtherCat.addPreference(mSmscAddressPref);
    }
    mOtherCat.removePreference(mManageSimPref);
    if (mSimCount >= 1) {
      mOtherCat.addPreference(mManageSimPref);
    }
    mOtherCat.removePreference(mStorageStatusPref);
    if (Build.IS_CM_CUSTOMIZATION_TEST) {
      mOtherCat.addPreference(mStorageStatusPref);
    }
  }
  
  private void updateSimRelatedPrefs()
  {
    updateSimState();
    updateMmsRelatedPrefs();
    updateOtherSettingsPrefs();
  }
  
  private void updateSimState()
  {
    mSimCount = MSimUtils.getInsertedSimCount();
    Log.d("MessagingAdvancedPreferenceActivity", "updateSimState sim count is " + mSimCount);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    initPreferences();
    registerSimRelatedListener();
  }
  
  protected void onDestroy()
  {
    unRegisterSimRelatedListener();
    super.onDestroy();
  }
  
  public boolean onPreferenceChange(Preference paramPreference, Object paramObject)
  {
    if ((paramPreference == mMmsValidityPeriodPref) && (Build.IS_CM_CUSTOMIZATION_TEST)) {
      onMmsValidityPeriodPrefClick(paramPreference, (String)paramObject);
    }
    return true;
  }
  
  public boolean onPreferenceClick(Preference paramPreference)
  {
    if (paramPreference == mMmsValidityPeriodPref)
    {
      if (Build.IS_CM_CUSTOMIZATION_TEST) {
        onMmsValidityPeriodPrefClick(paramPreference, null);
      }
      return true;
    }
    return false;
  }
  
  public boolean onPreferenceTreeClick(PreferenceScreen paramPreferenceScreen, Preference paramPreference)
  {
    if (paramPreference == mManageSimPref) {
      onManageSimPrefClick();
    }
    for (;;)
    {
      return super.onPreferenceTreeClick(paramPreferenceScreen, paramPreference);
      if (paramPreference == mSmscAddressPref) {
        onSmscAddressPrefClick();
      } else if (paramPreference == mStorageStatusPref) {
        onStorageStatusPrefClick();
      } else if ((paramPreference == mReadReportPref) || (paramPreference == mAutoRetrievalPref) || (paramPreference == mRetrievalRoamingPref)) {
        onMmsRelatedPrefsClick(paramPreference);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessagingAdvancedPreferenceActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */