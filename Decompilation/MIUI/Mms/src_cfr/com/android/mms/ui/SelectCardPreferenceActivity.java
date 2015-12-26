/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Bundle
 *  android.preference.Preference
 *  android.preference.PreferenceScreen
 *  android.util.Log
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
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.util.Log;
import com.android.mms.ui.AdvancedEditorPreference;
import com.android.mms.ui.GetSimInfo;
import com.android.mms.util.MSimUtils;
import miui.app.ActionBar;
import miui.preference.PreferenceActivity;
import miui.telephony.SubscriptionManager;

public class SelectCardPreferenceActivity
extends PreferenceActivity
implements GetSimInfo {
    private Context mContext;
    private String mPreferenceKey;
    private AdvancedEditorPreference mSim1Pref;
    private AdvancedEditorPreference mSim2Pref;
    private SubscriptionManager.OnSubscriptionsChangedListener mSimInfoChangeListener;

    public SelectCardPreferenceActivity() {
        this.mSimInfoChangeListener = new SubscriptionManager.OnSubscriptionsChangedListener(){

            public void onSubscriptionsChanged() {
                if (!MSimUtils.isMSimInserted()) {
                    Log.d((String)"SelectCardPreferenceActivity", (String)"onChange not multi sim is inserted");
                    SelectCardPreferenceActivity.this.finish();
                    return;
                }
                Log.d((String)"SelectCardPreferenceActivity", (String)"onChange update sim state");
                SelectCardPreferenceActivity.this.updateSimState();
            }
        };
    }

    private boolean canSetSmscAddressPref(int n) {
        if (MSimUtils.getPhoneType(n) == 2) {
            return false;
        }
        return true;
    }

    private void initPreference() {
        this.mSim1Pref = (AdvancedEditorPreference)this.findPreference((CharSequence)"pref_key_sim1");
        this.mSim2Pref = (AdvancedEditorPreference)this.findPreference((CharSequence)"pref_key_sim2");
        if (this.mSim1Pref != null) {
            this.mSim1Pref.init(this.mContext, 0);
            this.mSim1Pref.setEnabled(this.canSetSmscAddressPref(0));
        }
        if (this.mSim2Pref != null) {
            this.mSim2Pref.init(this.mContext, 1);
            this.mSim2Pref.setEnabled(this.canSetSmscAddressPref(1));
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
        this.addPreferencesFromResource(2131099651);
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

    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if ("pref_key_edit_sim_smsc_address".equals((Object)this.mPreferenceKey)) {
            MSimUtils.startSmscAddressActivity((Context)this, ((AdvancedEditorPreference)preference).getSlotId());
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

}

