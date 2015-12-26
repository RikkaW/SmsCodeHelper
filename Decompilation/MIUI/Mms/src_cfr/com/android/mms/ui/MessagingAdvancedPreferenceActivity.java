/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.res.Resources
 *  android.os.Bundle
 *  android.preference.CheckBoxPreference
 *  android.preference.Preference
 *  android.preference.Preference$OnPreferenceChangeListener
 *  android.preference.Preference$OnPreferenceClickListener
 *  android.preference.PreferenceCategory
 *  android.preference.PreferenceScreen
 *  android.util.Log
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  miui.app.AlertDialog
 *  miui.app.AlertDialog$Builder
 *  miui.os.Build
 *  miui.preference.PreferenceActivity
 *  miui.telephony.SubscriptionManager
 *  miui.telephony.SubscriptionManager$OnSubscriptionsChangedListener
 */
package com.android.mms.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.util.Log;
import com.android.mms.MmsConfig;
import com.android.mms.transaction.MmsSystemEventReceiver;
import com.android.mms.ui.ManageSimMessages;
import com.android.mms.ui.MessageUtils;
import com.android.mms.ui.MultiSimPreferenceAcitvity;
import com.android.mms.ui.SelectCardListPreferenceActivity;
import com.android.mms.ui.SelectCardPreferenceActivity;
import com.android.mms.ui.ValueListPreference;
import com.android.mms.util.MSimUtils;
import miui.app.AlertDialog;
import miui.os.Build;
import miui.preference.PreferenceActivity;
import miui.telephony.SubscriptionManager;

public class MessagingAdvancedPreferenceActivity
extends PreferenceActivity
implements Preference.OnPreferenceChangeListener,
Preference.OnPreferenceClickListener {
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
    private SubscriptionManager.OnSubscriptionsChangedListener mSimInfoChangeListener;
    private MmsSystemEventReceiver.SimStateChangedListener mSimStateChagnedListener;
    private Preference mSmscAddressPref;
    private Preference mStorageStatusPref;

    public MessagingAdvancedPreferenceActivity() {
        this.mSimInfoChangeListener = new SubscriptionManager.OnSubscriptionsChangedListener(){

            public void onSubscriptionsChanged() {
                Log.d((String)"MessagingAdvancedPreferenceActivity", (String)"update sim info change");
                MessagingAdvancedPreferenceActivity.this.updateSimRelatedPrefs();
            }
        };
        this.mSimStateChagnedListener = new MmsSystemEventReceiver.SimStateChangedListener(){

            @Override
            public void onSimStateChanged(String string2) {
                Log.d((String)"MessagingAdvancedPreferenceActivity", (String)"update sim info change");
                MessagingAdvancedPreferenceActivity.this.updateSimRelatedPrefs();
            }
        };
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean canSetSmscAddressPref() {
        if (this.mSimCount == 1 ? MSimUtils.getPhoneType(MSimUtils.getInsertedSlotId()) != 2 : this.mSimCount > 1) {
            return true;
        }
        return false;
    }

    private void cleanMmsRelatedPrefs() {
        this.mSettingsCat.removeAll();
        this.mReadReportPref = null;
        this.mAutoRetrievalPref = null;
        this.mRetrievalRoamingPref = null;
        this.mMmsValidityPeriodPref = null;
        this.getPreferenceScreen().removePreference((Preference)this.mSettingsCat);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void createAutoRetrievalPref(SharedPreferences sharedPreferences, long l) {
        String string2 = "pref_key_mms_auto_retrieval";
        if (this.mSimCount > 1) {
            this.mAutoRetrievalPref = new Preference((Context)this);
        } else {
            CheckBoxPreference checkBoxPreference = new CheckBoxPreference((Context)this);
            checkBoxPreference.setDefaultValue((Object)true);
            if (MSimUtils.isMSim()) {
                string2 = MSimUtils.createKeyWithSimId(l, "pref_key_mms_auto_retrieval");
                checkBoxPreference.setChecked(sharedPreferences.getBoolean(string2, true));
            }
            this.mAutoRetrievalPref = checkBoxPreference;
        }
        this.mAutoRetrievalPref.setTitle(2131361910);
        this.mAutoRetrievalPref.setSummary(2131361911);
        this.mAutoRetrievalPref.setKey(string2);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void createReadReportPref(SharedPreferences sharedPreferences, long l) {
        String string2 = "pref_key_mms_read_reports";
        if (this.mSimCount > 1) {
            this.mReadReportPref = new Preference((Context)this);
        } else {
            CheckBoxPreference checkBoxPreference = new CheckBoxPreference((Context)this);
            checkBoxPreference.setDefaultValue((Object)false);
            if (MSimUtils.isMSim()) {
                string2 = MSimUtils.createKeyWithSimId(l, "pref_key_mms_read_reports");
                checkBoxPreference.setChecked(sharedPreferences.getBoolean(string2, false));
            }
            this.mReadReportPref = checkBoxPreference;
        }
        this.mReadReportPref.setTitle(2131361902);
        this.mReadReportPref.setSummary(2131361900);
        this.mReadReportPref.setKey(string2);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void createRetrievalRoamingPref(SharedPreferences sharedPreferences, long l) {
        String string2 = "pref_key_mms_retrieval_during_roaming";
        if (this.mSimCount > 1) {
            this.mRetrievalRoamingPref = new Preference((Context)this);
        } else {
            CheckBoxPreference checkBoxPreference = new CheckBoxPreference((Context)this);
            checkBoxPreference.setDefaultValue((Object)false);
            if (MSimUtils.isMSim()) {
                string2 = MSimUtils.createKeyWithSimId(l, "pref_key_mms_retrieval_during_roaming");
                checkBoxPreference.setChecked(sharedPreferences.getBoolean(string2, false));
            }
            this.mRetrievalRoamingPref = checkBoxPreference;
        }
        this.mRetrievalRoamingPref.setTitle(2131361912);
        this.mRetrievalRoamingPref.setSummary(2131361913);
        this.mRetrievalRoamingPref.setKey(string2);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void createValidityPeriodPref(SharedPreferences object, long l) {
        String string2 = "pref_key_mms_validity_period";
        if (this.mSimCount > 1) {
            this.mMmsValidityPeriodPref = new Preference((Context)this);
            this.mMmsValidityPeriodPref.setOnPreferenceClickListener((Preference.OnPreferenceClickListener)this);
            object = string2;
        } else {
            ValueListPreference valueListPreference = new ValueListPreference((Context)this);
            valueListPreference.setEntries(2131230733);
            valueListPreference.setEntryValues(2131230734);
            valueListPreference.setListTitleId(2131362368);
            string2 = MSimUtils.createKeyWithSimId(l, "pref_key_mms_validity_period");
            SelectCardListPreferenceActivity.fillMmsValidityValue((Context)this, valueListPreference, object.getString(string2, "0"));
            this.mMmsValidityPeriodPref = valueListPreference;
            this.mMmsValidityPeriodPref.setOnPreferenceChangeListener((Preference.OnPreferenceChangeListener)this);
            object = string2;
        }
        this.mMmsValidityPeriodPref.setTitle(2131362368);
        this.mMmsValidityPeriodPref.setKey((String)object);
    }

    private void dependOnAutoRetrieval() {
        if (this.mRetrievalRoamingPref != null && this.mAutoRetrievalPref != null && this.mAutoRetrievalPref instanceof CheckBoxPreference) {
            CheckBoxPreference checkBoxPreference = (CheckBoxPreference)this.mAutoRetrievalPref;
            this.mRetrievalRoamingPref.setEnabled(checkBoxPreference.isChecked());
        }
    }

    private void initPreferences() {
        this.addPreferencesFromResource(2131099648);
        this.mOtherCat = (PreferenceCategory)this.findPreference((CharSequence)"pref_key_other_settings");
        this.mFestivalNetWorkingPref = this.findPreference((CharSequence)"pref_key_festival_networking");
        this.mSmscAddressPref = this.findPreference((CharSequence)"pref_key_edit_sim_smsc_address");
        this.mManageSimPref = this.findPreference((CharSequence)"pref_key_manage_sim_messages");
        this.mHideFestivalPref = (CheckBoxPreference)this.findPreference((CharSequence)"pref_key_hide_festival");
        this.mForwardSettingsCat = (PreferenceCategory)this.findPreference((CharSequence)"pref_key_forward_settings");
        this.mCardFormatCat = (PreferenceCategory)this.findPreference((CharSequence)"pref_key_card_format");
        this.mSettingsCat = (PreferenceCategory)this.findPreference((CharSequence)"pref_key_mms_settings");
        this.mStorageStatusPref = this.findPreference((CharSequence)"pref_key_storage_status");
        this.updateSimRelatedPrefs();
        this.removeUnavailablePrefs();
    }

    private void onManageSimPrefClick() {
        Intent intent = new Intent((Context)this, (Class)ManageSimMessages.class);
        intent.putExtra(MSimUtils.SLOT_ID, 0);
        this.startActivity(intent);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void onMmsRelatedPrefsClick(Preference preference) {
        if (this.mSimCount > 1) {
            Intent intent = new Intent((Context)this, (Class)MultiSimPreferenceAcitvity.class);
            intent.putExtra("preference_key", preference.getKey());
            intent.putExtra("preference_title", preference.getTitleRes());
            this.startActivity(intent);
            return;
        } else {
            if (preference != this.mAutoRetrievalPref) return;
            {
                this.dependOnAutoRetrieval();
                return;
            }
        }
    }

    private void onMmsValidityPeriodPrefClick(Preference preference, String string2) {
        if (this.mSimCount > 1) {
            string2 = new Intent((Context)this, (Class)SelectCardListPreferenceActivity.class);
            string2.putExtra("preference_key", preference.getKey());
            string2.putExtra("preference_title", preference.getTitleRes());
            this.startActivity((Intent)string2);
            return;
        }
        SelectCardListPreferenceActivity.fillMmsValidityValue((Context)this, (ValueListPreference)preference, string2);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void onSmscAddressPrefClick() {
        if (this.mSimCount == 1) {
            MSimUtils.startSmscAddressActivity((Context)this, MSimUtils.getInsertedSlotId());
            return;
        } else {
            if (this.mSimCount <= 1) return;
            {
                Intent intent = new Intent((Context)this, (Class)SelectCardPreferenceActivity.class);
                intent.putExtra("preference_key", this.mSmscAddressPref.getKey());
                intent.putExtra("preference_title", this.mSmscAddressPref.getTitleRes());
                this.startActivity(intent);
                return;
            }
        }
    }

    private void onStorageStatusPrefClick() {
        if (Build.IS_CM_CUSTOMIZATION_TEST) {
            String string2 = MessageUtils.getStorageStatus((Context)this);
            new AlertDialog.Builder((Context)this).setTitle(2131361906).setMessage((CharSequence)string2).setPositiveButton(17039370, null).setCancelable(true).show();
        }
    }

    private void registerSimRelatedListener() {
        if (MSimUtils.isMSim()) {
            MSimUtils.registerChangeListener((Context)this, this.mSimInfoChangeListener);
            return;
        }
        MmsSystemEventReceiver.getInstance().registerSimStateChangedListener(this.mSimStateChagnedListener);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void removeUnavailablePrefs() {
        PreferenceScreen preferenceScreen;
        boolean bl = true;
        if (!Build.IS_CM_CUSTOMIZATION) {
            preferenceScreen = this.getPreferenceScreen();
            if (this.mForwardSettingsCat != null) {
                preferenceScreen.removePreference((Preference)this.mForwardSettingsCat);
            }
            this.mForwardSettingsCat = null;
            if (this.mCardFormatCat != null) {
                preferenceScreen.removePreference((Preference)this.mCardFormatCat);
            }
            this.mCardFormatCat = null;
        }
        if (MessageUtils.isZhCnLanguage() || Build.IS_INTERNATIONAL_BUILD) {
            this.mOtherCat.removePreference((Preference)this.mHideFestivalPref);
            if (!Build.IS_INTERNATIONAL_BUILD) {
                this.mFestivalNetWorkingPref.setEnabled(true);
                return;
            }
            this.mOtherCat.removePreference(this.mFestivalNetWorkingPref);
            return;
        }
        preferenceScreen = this.mFestivalNetWorkingPref;
        if (this.mHideFestivalPref.isChecked()) {
            bl = false;
        }
        preferenceScreen.setEnabled(bl);
    }

    private void unRegisterSimRelatedListener() {
        if (MSimUtils.isMSim()) {
            MSimUtils.unregisterChangeListener((Context)this, this.mSimInfoChangeListener);
            return;
        }
        MmsSystemEventReceiver.getInstance().unRegisterSimStateChangedListener(this.mSimStateChagnedListener);
    }

    private void updateMmsRelatedPrefs() {
        this.cleanMmsRelatedPrefs();
        int n = MSimUtils.getInsertedSlotId();
        Log.d((String)"MessagingAdvancedPreferenceActivity", (String)("updateMmsRelatedPrefs slotId is " + String.valueOf((int)n)));
        if (this.mSimCount == 0 || n == -1 || !MmsConfig.getMmsEnabled()) {
            Log.d((String)"MessagingAdvancedPreferenceActivity", (String)"updateMmsRelatedPreference nothing");
            return;
        }
        long l = MSimUtils.getSimIdBySlotId(n);
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.android.mms_preferences", 1);
        this.getPreferenceScreen().addPreference((Preference)this.mSettingsCat);
        this.createReadReportPref(sharedPreferences, l);
        this.mSettingsCat.addPreference(this.mReadReportPref);
        this.createAutoRetrievalPref(sharedPreferences, l);
        this.mSettingsCat.addPreference(this.mAutoRetrievalPref);
        this.createRetrievalRoamingPref(sharedPreferences, l);
        this.mSettingsCat.addPreference(this.mRetrievalRoamingPref);
        if (Build.IS_CM_CUSTOMIZATION_TEST) {
            this.createValidityPeriodPref(sharedPreferences, l);
            this.mSettingsCat.addPreference(this.mMmsValidityPeriodPref);
        }
        this.dependOnAutoRetrieval();
    }

    private void updateOtherSettingsPrefs() {
        this.mOtherCat.removePreference(this.mSmscAddressPref);
        if (this.getResources().getBoolean(2131492865) && this.canSetSmscAddressPref()) {
            this.mOtherCat.addPreference(this.mSmscAddressPref);
        }
        this.mOtherCat.removePreference(this.mManageSimPref);
        if (this.mSimCount >= 1) {
            this.mOtherCat.addPreference(this.mManageSimPref);
        }
        this.mOtherCat.removePreference(this.mStorageStatusPref);
        if (Build.IS_CM_CUSTOMIZATION_TEST) {
            this.mOtherCat.addPreference(this.mStorageStatusPref);
        }
    }

    private void updateSimRelatedPrefs() {
        this.updateSimState();
        this.updateMmsRelatedPrefs();
        this.updateOtherSettingsPrefs();
    }

    private void updateSimState() {
        this.mSimCount = MSimUtils.getInsertedSimCount();
        Log.d((String)"MessagingAdvancedPreferenceActivity", (String)("updateSimState sim count is " + this.mSimCount));
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.initPreferences();
        this.registerSimRelatedListener();
    }

    protected void onDestroy() {
        this.unRegisterSimRelatedListener();
        super.onDestroy();
    }

    public boolean onPreferenceChange(Preference preference, Object object) {
        if (preference == this.mMmsValidityPeriodPref && Build.IS_CM_CUSTOMIZATION_TEST) {
            this.onMmsValidityPeriodPrefClick(preference, (String)object);
        }
        return true;
    }

    public boolean onPreferenceClick(Preference preference) {
        if (preference == this.mMmsValidityPeriodPref) {
            if (Build.IS_CM_CUSTOMIZATION_TEST) {
                this.onMmsValidityPeriodPrefClick(preference, null);
            }
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if (preference == this.mManageSimPref) {
            this.onManageSimPrefClick();
            return super.onPreferenceTreeClick(preferenceScreen, preference);
        }
        if (preference == this.mSmscAddressPref) {
            this.onSmscAddressPrefClick();
            return super.onPreferenceTreeClick(preferenceScreen, preference);
        }
        if (preference == this.mStorageStatusPref) {
            this.onStorageStatusPrefClick();
            return super.onPreferenceTreeClick(preferenceScreen, preference);
        }
        if (preference != this.mReadReportPref && preference != this.mAutoRetrievalPref) {
            if (preference != this.mRetrievalRoamingPref) return super.onPreferenceTreeClick(preferenceScreen, preference);
        }
        this.onMmsRelatedPrefsClick(preference);
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

}

