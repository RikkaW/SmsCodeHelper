/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.res.Resources
 *  android.os.Bundle
 *  android.preference.Preference
 *  android.preference.Preference$OnPreferenceChangeListener
 *  android.util.Log
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  miui.app.ActionBar
 *  miui.preference.PreferenceActivity
 *  miui.telephony.SubscriptionManager
 *  miui.telephony.SubscriptionManager$OnSubscriptionsChangedListener
 */
package com.android.mms.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.Preference;
import android.util.Log;
import com.android.mms.ui.AdvancedValueListPreference;
import com.android.mms.ui.GetSimInfo;
import com.android.mms.ui.ValueListPreference;
import com.android.mms.util.MSimUtils;
import miui.app.ActionBar;
import miui.preference.PreferenceActivity;
import miui.telephony.SubscriptionManager;

public class SelectCardListPreferenceActivity
extends PreferenceActivity
implements Preference.OnPreferenceChangeListener,
GetSimInfo {
    private Context mContext;
    private String mPreferenceKey;
    private AdvancedValueListPreference mSim1Pref;
    private AdvancedValueListPreference mSim2Pref;
    private SubscriptionManager.OnSubscriptionsChangedListener mSimInfoChangeListener;

    public SelectCardListPreferenceActivity() {
        this.mSimInfoChangeListener = new SubscriptionManager.OnSubscriptionsChangedListener(){

            public void onSubscriptionsChanged() {
                if (!MSimUtils.isMSimInserted()) {
                    Log.d((String)"SelectCardListPreferenceActivity", (String)"onChange not multi sim is inserted");
                    SelectCardListPreferenceActivity.this.finish();
                    return;
                }
                Log.d((String)"SelectCardListPreferenceActivity", (String)"onChange update sim state");
                SelectCardListPreferenceActivity.this.updateSimState();
            }
        };
    }

    public static void fillMmsValidityValue(Context context, ValueListPreference valueListPreference, String string2) {
        int n = SelectCardListPreferenceActivity.getMmsValidityPerioIndex(string2);
        valueListPreference.setRightValue(context.getResources().getStringArray(2131230733)[n]);
    }

    public static void fillSmsValidityValue(Context context, ValueListPreference valueListPreference, String string2) {
        int n = SelectCardListPreferenceActivity.getSmsValidityPerioIndex(string2);
        valueListPreference.setRightValue(context.getResources().getStringArray(2131230731)[n]);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int getMmsValidityPerioIndex(String string2) {
        int n = Integer.parseInt((String)string2);
        if (n == 0) {
            return 0;
        }
        int n2 = n;
        if (n != 172800) return n2;
        return 1;
    }

    public static int getSmsValidityPerioIndex(String string2) {
        int n = Integer.parseInt((String)string2);
        if (n == -1) {
            return 0;
        }
        if (n == 60) {
            return 1;
        }
        if (n == 360) {
            return 2;
        }
        if (n == 720) {
            return 3;
        }
        if (n == 1440) {
            return 4;
        }
        if (n == 635040) {
            return 5;
        }
        return 0;
    }

    private void initPreference() {
        this.mSim1Pref = (AdvancedValueListPreference)this.findPreference((CharSequence)"pref_key_sim1");
        this.mSim2Pref = (AdvancedValueListPreference)this.findPreference((CharSequence)"pref_key_sim2");
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.android.mms_preferences", 1);
        if (this.mSim1Pref != null) {
            this.mSim1Pref.init(this.mContext, 0);
            this.setPreference(this.mSim1Pref, sharedPreferences);
            this.mSim1Pref.setOnPreferenceChangeListener((Preference.OnPreferenceChangeListener)this);
        }
        if (this.mSim2Pref != null) {
            this.mSim2Pref.init(this.mContext, 1);
            this.setPreference(this.mSim2Pref, sharedPreferences);
            this.mSim2Pref.setOnPreferenceChangeListener((Preference.OnPreferenceChangeListener)this);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void setPreference(AdvancedValueListPreference advancedValueListPreference, SharedPreferences sharedPreferences) {
        String string2 = MSimUtils.createKeyWithSimId(MSimUtils.getSimIdBySlotId(advancedValueListPreference.getSlotId()), this.mPreferenceKey);
        advancedValueListPreference.setKey(string2);
        advancedValueListPreference.setPersistent(true);
        if ("pref_key_mms_validity_period".equals((Object)this.mPreferenceKey)) {
            advancedValueListPreference.setEntries(2131230733);
            advancedValueListPreference.setEntryValues(2131230734);
            advancedValueListPreference.setListTitleId(2131362368);
            SelectCardListPreferenceActivity.fillMmsValidityValue((Context)this, advancedValueListPreference, sharedPreferences.getString(string2, "0"));
            return;
        } else {
            if (!"pref_key_sms_validity_period".equals((Object)this.mPreferenceKey)) return;
            {
                advancedValueListPreference.setEntries(2131230731);
                advancedValueListPreference.setEntryValues(2131230732);
                advancedValueListPreference.setListTitleId(2131362367);
                SelectCardListPreferenceActivity.fillSmsValidityValue((Context)this, advancedValueListPreference, sharedPreferences.getString(advancedValueListPreference.getKey(), "-1"));
                return;
            }
        }
    }

    private void updateSimState() {
        if (this.mSim1Pref != null) {
            this.mSim1Pref.setNotifyChanged(this.mContext, 0);
        }
        if (this.mSim2Pref != null) {
            this.mSim2Pref.setNotifyChanged(this.mContext, 1);
        }
    }

    @Override
    public int getSimDisplayIcon(int n) {
        return MSimUtils.getSimIconResId(n);
    }

    @Override
    public String getSimDisplayName(int n) {
        return MSimUtils.getSimDisplayName(this.mContext, n);
    }

    @Override
    public String getSimNumber(int n) {
        return MSimUtils.getSimNumber(this.mContext, n);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = this;
        bundle = this.getActionBar();
        if (bundle != null) {
            bundle.setDisplayHomeAsUpEnabled(true);
        }
        this.addPreferencesFromResource(2131099652);
        bundle = this.getIntent();
        this.mPreferenceKey = bundle.getStringExtra("preference_key");
        this.setTitle(bundle.getIntExtra("preference_title", 0));
        this.initPreference();
        MSimUtils.registerChangeListener(this.mContext, this.mSimInfoChangeListener);
    }

    protected void onDestroy() {
        MSimUtils.unregisterChangeListener(this.mContext, this.mSimInfoChangeListener);
        super.onDestroy();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean onPreferenceChange(Preference preference, Object object) {
        if ("pref_key_mms_validity_period".equals((Object)this.mPreferenceKey)) {
            SelectCardListPreferenceActivity.fillMmsValidityValue((Context)this, (ValueListPreference)preference, (String)object);
            do {
                return true;
                break;
            } while (true);
        }
        SelectCardListPreferenceActivity.fillSmsValidityValue((Context)this, (ValueListPreference)preference, (String)object);
        return true;
    }

}

