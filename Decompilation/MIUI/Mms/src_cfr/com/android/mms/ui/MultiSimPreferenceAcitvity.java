/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.os.Bundle
 *  android.preference.Preference
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  miui.app.ActionBar
 *  miui.os.Build
 *  miui.preference.PreferenceActivity
 *  miui.telephony.SubscriptionManager
 *  miui.telephony.SubscriptionManager$OnSubscriptionsChangedListener
 */
package com.android.mms.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.util.Log;
import com.android.mms.ui.AdvancedCheckBoxPreference;
import com.android.mms.ui.GetSimInfo;
import com.android.mms.util.MSimUtils;
import miui.app.ActionBar;
import miui.os.Build;
import miui.preference.PreferenceActivity;
import miui.telephony.SubscriptionManager;

public class MultiSimPreferenceAcitvity
extends PreferenceActivity
implements GetSimInfo {
    private Context mContext;
    private AdvancedCheckBoxPreference mSim1Pref;
    private AdvancedCheckBoxPreference mSim2Pref;
    private SubscriptionManager.OnSubscriptionsChangedListener mSimInfoChangeListener;

    public MultiSimPreferenceAcitvity() {
        this.mSimInfoChangeListener = new SubscriptionManager.OnSubscriptionsChangedListener(){

            public void onSubscriptionsChanged() {
                if (!MSimUtils.isMSimInserted()) {
                    Log.d((String)"MultiSimPreferenceAcitvity", (String)"onChange not multi sim is inserted");
                    MultiSimPreferenceAcitvity.this.finish();
                    return;
                }
                Log.d((String)"MultiSimPreferenceAcitvity", (String)"onChange update sim state");
                MultiSimPreferenceAcitvity.this.updateSimState();
            }
        };
    }

    private void initPreference(String string2) {
        this.mSim1Pref = (AdvancedCheckBoxPreference)this.findPreference((CharSequence)"pref_key_sim1");
        this.mSim2Pref = (AdvancedCheckBoxPreference)this.findPreference((CharSequence)"pref_key_sim2");
        this.mSim1Pref.init(this.mContext, 0);
        this.mSim2Pref.init(this.mContext, 1);
        this.setPreferenceDefaultValue(string2);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void setPreferenceDefaultValue(String string2) {
        boolean bl = false;
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.android.mms_preferences", 1);
        long l = MSimUtils.getSimIdBySlotId(0);
        long l2 = MSimUtils.getSimIdBySlotId(1);
        String string3 = MSimUtils.createKeyWithSimId(l, string2);
        this.mSim1Pref.setKey(string3);
        string3 = MSimUtils.createKeyWithSimId(l2, string2);
        this.mSim2Pref.setKey(string3);
        if ("pref_key_mms_retrieval_during_roaming".equals((Object)string2)) {
            string2 = MSimUtils.createKeyWithSimId(l, "pref_key_mms_auto_retrieval");
            this.mSim1Pref.setEnabled(sharedPreferences.getBoolean(string2, true));
            string2 = MSimUtils.createKeyWithSimId(l2, "pref_key_mms_auto_retrieval");
            this.mSim2Pref.setEnabled(sharedPreferences.getBoolean(string2, true));
            this.mSim1Pref.setChecked(sharedPreferences.getBoolean(this.mSim1Pref.getKey(), true));
            this.mSim2Pref.setChecked(sharedPreferences.getBoolean(this.mSim2Pref.getKey(), true));
            return;
        }
        if ("pref_key_mms_auto_retrieval".equals((Object)string2)) {
            this.mSim1Pref.setChecked(sharedPreferences.getBoolean(this.mSim1Pref.getKey(), true));
            this.mSim2Pref.setChecked(sharedPreferences.getBoolean(this.mSim2Pref.getKey(), true));
            return;
        }
        if (!"pref_key_delivery_reports".equals((Object)string2)) {
            this.mSim1Pref.setChecked(sharedPreferences.getBoolean(this.mSim1Pref.getKey(), false));
            this.mSim2Pref.setChecked(sharedPreferences.getBoolean(this.mSim2Pref.getKey(), false));
            return;
        }
        if (!Build.IS_CM_CUSTOMIZATION_TEST) {
            bl = true;
        }
        this.mSim1Pref.setChecked(sharedPreferences.getBoolean(this.mSim1Pref.getKey(), bl));
        this.mSim2Pref.setChecked(sharedPreferences.getBoolean(this.mSim2Pref.getKey(), bl));
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
        return MSimUtils.getSimDisplayName((Context)this, n);
    }

    @Override
    public String getSimNumber(int n) {
        return MSimUtils.getSimNumber((Context)this, n);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = this;
        bundle = this.getActionBar();
        if (bundle != null) {
            bundle.setDisplayHomeAsUpEnabled(true);
        }
        this.addPreferencesFromResource(2131099650);
        bundle = this.getIntent();
        String string2 = bundle.getStringExtra("preference_key");
        this.setTitle(bundle.getIntExtra("preference_title", 0));
        this.initPreference(string2);
        MSimUtils.registerChangeListener(this.mContext, this.mSimInfoChangeListener);
    }

    protected void onDestroy() {
        MSimUtils.unregisterChangeListener(this.mContext, this.mSimInfoChangeListener);
        super.onDestroy();
    }

}

