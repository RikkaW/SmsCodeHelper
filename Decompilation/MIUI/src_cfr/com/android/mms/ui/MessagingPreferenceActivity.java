/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.PendingIntent
 *  android.content.BroadcastReceiver
 *  android.content.ComponentName
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.IntentFilter
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.res.Configuration
 *  android.content.res.MiuiConfiguration
 *  android.content.res.Resources
 *  android.net.Uri
 *  android.os.Bundle
 *  android.os.Parcelable
 *  android.preference.CheckBoxPreference
 *  android.preference.Preference
 *  android.preference.Preference$OnPreferenceChangeListener
 *  android.preference.Preference$OnPreferenceClickListener
 *  android.preference.PreferenceCategory
 *  android.preference.PreferenceManager
 *  android.preference.PreferenceScreen
 *  android.provider.Settings
 *  android.provider.Settings$System
 *  android.util.Log
 *  android.view.MenuItem
 *  com.xiaomi.accountsdk.activate.ActivateStatusReceiver
 *  com.xiaomi.accountsdk.activate.ActivateStatusReceiver$ActivateStatusListener
 *  com.xiaomi.accountsdk.activate.ActivateStatusReceiver$Event
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.Locale
 *  miui.os.Build
 *  miui.preference.PreferenceActivity
 *  miui.telephony.SubscriptionManager
 *  miui.telephony.SubscriptionManager$OnSubscriptionsChangedListener
 */
package com.android.mms.ui;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.MiuiConfiguration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import com.android.mms.ServiceCategoryUpdateAsyncTask;
import com.android.mms.transaction.MmsSystemEventReceiver;
import com.android.mms.ui.MessageUtils;
import com.android.mms.ui.MultiSimPreferenceAcitvity;
import com.android.mms.ui.SelectCardListPreferenceActivity;
import com.android.mms.ui.ValueListPreference;
import com.android.mms.ui.WildMsgDialog;
import com.android.mms.understand.UnderstandLoader;
import com.android.mms.util.MSimUtils;
import com.android.mms.util.MiStatSdkHelper;
import com.xiaomi.accountsdk.activate.ActivateStatusReceiver;
import com.xiaomi.mms.transaction.MxActivateService;
import com.xiaomi.mms.transaction.MxMessageService;
import com.xiaomi.mms.transaction.PushSession;
import com.xiaomi.mms.utils.MxActivateSimpleDialog;
import java.util.Locale;
import miui.os.Build;
import miui.preference.PreferenceActivity;
import miui.telephony.SubscriptionManager;

public class MessagingPreferenceActivity
extends PreferenceActivity
implements Preference.OnPreferenceChangeListener,
Preference.OnPreferenceClickListener,
ServiceCategoryUpdateAsyncTask.IServiceCategoryUpdateListener,
ActivateStatusReceiver.ActivateStatusListener {
    private CheckBoxPreference mAllowNetworkAccessPref;
    private IntentFilter mBroadcastFilter;
    private CheckBoxPreference mCollapseSPMessagePref;
    private ValueListPreference mDateTypePref;
    private Preference mDeliveryRptPref;
    private PreferenceScreen mDownLoadWildMsgPref;
    private PreferenceScreen mMessageRecoveryPref;
    private CheckBoxPreference mMx1EnabledPref;
    private CheckBoxPreference mMx2EnabledPref;
    private CheckBoxPreference mMxEnabledPref;
    private PreferenceScreen mMxFeedbackPref;
    private PreferenceCategory mMxMsgPref;
    private PreferenceScreen mMxStatusPref;
    private CheckBoxPreference mNotificationBodyPref;
    private ValueListPreference mNotificationRingTonePref;
    private PreferenceCategory mNotificationSettingsPref;
    private BroadcastReceiver mReceiver;
    private Preference mSendDeliveryRptPref;
    private CheckBoxPreference mShowAntispamPref;
    private CheckBoxPreference mShowTemplateInPref;
    private CheckBoxPreference mShowTemplatePref;
    private int mSimCount = 0;
    private SubscriptionManager.OnSubscriptionsChangedListener mSimInfoChangeListener;
    private MmsSystemEventReceiver.SimStateChangedListener mSimStateChagnedListener;
    private Preference mSmsValidityPeriodPref;
    private CheckBoxPreference mTimeStyleGroup;
    private WildMsgDialog mWildMsgDialog;

    public MessagingPreferenceActivity() {
        this.mSimInfoChangeListener = new SubscriptionManager.OnSubscriptionsChangedListener(){

            public void onSubscriptionsChanged() {
                Log.d((String)"MessagingPreferenceActivity", (String)"update sim info change");
                MessagingPreferenceActivity.this.updateSimRelatedPrefs();
            }
        };
        this.mSimStateChagnedListener = new MmsSystemEventReceiver.SimStateChangedListener(){

            @Override
            public void onSimStateChanged(String string2) {
                Log.d((String)"MessagingPreferenceActivity", (String)"update sim info change");
                MessagingPreferenceActivity.this.updateSimRelatedPrefs();
            }
        };
    }

    private void cleanSimRelatedPrefs() {
        if (this.mDeliveryRptPref != null) {
            this.mDeliveryRptPref.setOnPreferenceClickListener(null);
            this.mNotificationSettingsPref.removePreference(this.mDeliveryRptPref);
            this.mDeliveryRptPref = null;
        }
        if (this.mSmsValidityPeriodPref != null) {
            this.mSmsValidityPeriodPref.setOnPreferenceChangeListener(null);
            this.mSmsValidityPeriodPref.setOnPreferenceClickListener(null);
            this.mNotificationSettingsPref.removePreference(this.mSmsValidityPeriodPref);
            this.mSmsValidityPeriodPref = null;
        }
        if (this.mSendDeliveryRptPref != null) {
            this.mSendDeliveryRptPref.setOnPreferenceClickListener(null);
            this.mNotificationSettingsPref.removePreference(this.mSendDeliveryRptPref);
            this.mSendDeliveryRptPref = null;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void createDeliveryRptPref(SharedPreferences sharedPreferences, long l) {
        String string2 = "pref_key_delivery_reports";
        if (this.mSimCount > 1) {
            this.mDeliveryRptPref = new Preference((Context)this);
        } else {
            boolean bl = this.getDefaultDeliveryReportMode();
            CheckBoxPreference checkBoxPreference = new CheckBoxPreference((Context)this);
            checkBoxPreference.setDefaultValue((Object)bl);
            if (MSimUtils.isMSim()) {
                string2 = MSimUtils.createKeyWithSimId(l, "pref_key_delivery_reports");
            }
            checkBoxPreference.setChecked(sharedPreferences.getBoolean(string2, bl));
            this.mDeliveryRptPref = checkBoxPreference;
        }
        this.mDeliveryRptPref.setTitle(2131362109);
        this.mDeliveryRptPref.setSummary(2131362110);
        this.mDeliveryRptPref.setKey(string2);
        this.mDeliveryRptPref.setOnPreferenceClickListener((Preference.OnPreferenceClickListener)this);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void createSendDeliveryRptPref(SharedPreferences sharedPreferences, long l) {
        String string2 = "pref_key_send_delivery_reports";
        if (this.mSimCount > 1) {
            this.mSendDeliveryRptPref = new Preference((Context)this);
        } else {
            CheckBoxPreference checkBoxPreference = new CheckBoxPreference((Context)this);
            checkBoxPreference.setDefaultValue((Object)false);
            if (MSimUtils.isMSim()) {
                string2 = MSimUtils.createKeyWithSimId(l, "pref_key_send_delivery_reports");
                checkBoxPreference.setChecked(sharedPreferences.getBoolean(string2, false));
            }
            this.mSendDeliveryRptPref = checkBoxPreference;
        }
        this.mSendDeliveryRptPref.setTitle(2131362111);
        this.mSendDeliveryRptPref.setSummary(2131362112);
        this.mSendDeliveryRptPref.setKey(string2);
        this.mSendDeliveryRptPref.setOnPreferenceClickListener((Preference.OnPreferenceClickListener)this);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void createValidityPeriodPref(SharedPreferences object, long l) {
        String string2 = "pref_key_sms_validity_period";
        if (this.mSimCount > 1) {
            this.mSmsValidityPeriodPref = new Preference((Context)this);
            this.mSmsValidityPeriodPref.setOnPreferenceClickListener((Preference.OnPreferenceClickListener)this);
            object = string2;
        } else {
            ValueListPreference valueListPreference = new ValueListPreference((Context)this);
            valueListPreference.setEntries(2131230731);
            valueListPreference.setEntryValues(2131230732);
            valueListPreference.setListTitleId(2131362367);
            string2 = MSimUtils.createKeyWithSimId(l, "pref_key_sms_validity_period");
            SelectCardListPreferenceActivity.fillSmsValidityValue((Context)this, valueListPreference, object.getString(string2, "-1"));
            this.mSmsValidityPeriodPref = valueListPreference;
            this.mSmsValidityPeriodPref.setOnPreferenceChangeListener((Preference.OnPreferenceChangeListener)this);
            object = string2;
        }
        this.mSmsValidityPeriodPref.setTitle(2131362367);
        this.mSmsValidityPeriodPref.setKey((String)object);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void enableNotifications(boolean bl, Context context) {
        context = context.getContentResolver();
        int n = bl ? 1 : 0;
        Settings.System.putInt((ContentResolver)context, (String)"pref_key_enable_notification", (int)n);
    }

    private void fillDateTypeSummary(String arrstring) {
        int n = Integer.parseInt((String)arrstring);
        arrstring = this.getResources().getStringArray(2131230727);
        this.mDateTypePref.setRightValue(arrstring[n]);
    }

    private void fillMxStatusSummary() {
        this.mMxStatusPref.setSummary((CharSequence)this.getString(2131362173, new Object[]{MxMessageService.getMxSmsCount((Context)this), MxMessageService.getMxMmsCount((Context)this)}));
    }

    /*
     * Enabled aggressive block sorting
     */
    private void fillRingToneSummary(String arrstring) {
        int n = Integer.parseInt((String)arrstring);
        if (n > 0) {
            int n2 = n - 1;
            if (n2 == 4) {
                n = 3;
            } else {
                n = n2;
                if (n2 == 9) {
                    n = 4;
                }
            }
        } else {
            n = 0;
        }
        Object object = this.getResources();
        arrstring = object.getStringArray(2131230722);
        object = object.getString(2131362114);
        this.mNotificationRingTonePref.setSummary((CharSequence)object);
        this.mNotificationRingTonePref.setRightValue(arrstring[n]);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean getDefaultDeliveryReportMode() {
        boolean bl = true;
        if (Build.IS_CM_CUSTOMIZATION_TEST) {
            return false;
        }
        boolean bl2 = bl;
        if (!Build.IS_INTERNATIONAL_BUILD) return bl2;
        bl2 = bl;
        if (!Build.checkRegion((String)"BR")) return bl2;
        return false;
    }

    private String getMessageRecoveryIntroductionUri() {
        String string2 = this.getResources().getConfiguration().locale.toString();
        if ("zh_CN".equals((Object)string2) || "zh_TW".equals((Object)string2)) {
            return String.format((String)"https://i.mi.com/static2?filename=MicloudWebHelpStatic/www/sms/recovery.html&timestamp=%d&lang=%s", (Object[])new Object[]{System.currentTimeMillis(), string2});
        }
        return String.format((String)"https://i.mi.com/static2?filename=MicloudWebHelpStatic/www/sms/recovery.html&timestamp=%d&lang=%s", (Object[])new Object[]{System.currentTimeMillis(), "en"});
    }

    public static boolean getNotificationEnabled(Context context) {
        return MessageUtils.getPrefNotificationEnabled(context);
    }

    private boolean handleDeliveryRptRelatedPrefsClick(Preference preference) {
        if (this.mSimCount > 1) {
            Intent intent = new Intent((Context)this, (Class)MultiSimPreferenceAcitvity.class);
            intent.putExtra("preference_key", preference.getKey());
            intent.putExtra("preference_title", preference.getTitleRes());
            this.startActivity(intent);
            return true;
        }
        return false;
    }

    private void handleDownloadWildMsgPref() {
        if (this.mWildMsgDialog != null) {
            this.mWildMsgDialog.cancel();
        }
        this.mWildMsgDialog = new WildMsgDialog((Context)this, true);
        this.mWildMsgDialog.show();
    }

    private void handleIntent() {
        if (MxActivateService.getLastFailureCode((Context)this) > 0) {
            // empty if block
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void handleMxSwitchPref(CheckBoxPreference object, final int n) {
        if (!MxActivateService.isMxEnabled((Context)this, n) && n >= 0) {
            object = ActivateStatusReceiver.getActivateInfo((int)n);
            if (object == null) return;
            {
                if (object.getInt("activate_status") == 3) {
                    MxActivateService.enableMx((Context)this, n, true, true);
                    this.updateMxPrefStatus();
                    return;
                }
                object = new MxActivateSimpleDialog((Context)this, 0, "Mms_Pref", n);
                object.setActivationCallBack(new MxActivateSimpleDialog.ActivationCallBack(){

                    @Override
                    public void onResult(int n2) {
                        if (n2 != 1) {
                            MxActivateService.setEnableAfterActivation(n, true);
                        }
                        MessagingPreferenceActivity.this.updateMxPrefStatus();
                    }
                });
                object.show();
                PreferenceManager.getDefaultSharedPreferences((Context)this).edit().putBoolean("already_remind_activate_voip", true).commit();
                return;
            }
        } else {
            MxActivateService.enableMx((Context)this, n, false, true);
            if (this.findPreference((CharSequence)"pref_key_mx_status") == null || MxActivateService.isMxEnabled((Context)this, 0) || MxActivateService.isMxEnabled((Context)this, 1)) return;
            {
                this.mMxMsgPref.removePreference((Preference)this.mMxStatusPref);
                return;
            }
        }
    }

    private boolean handleSmsValidityPeriod(Preference preference, String string2) {
        if (this.mSimCount > 1) {
            string2 = new Intent((Context)this, (Class)SelectCardListPreferenceActivity.class);
            string2.putExtra("preference_key", preference.getKey());
            string2.putExtra("preference_title", preference.getTitleRes());
            this.startActivity((Intent)string2);
            return true;
        }
        SelectCardListPreferenceActivity.fillSmsValidityValue((Context)this, (ValueListPreference)preference, string2);
        return false;
    }

    private boolean hasMxSettings() {
        if (!Build.IS_CM_CUSTOMIZATION) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void initPreference() {
        PreferenceCategory preferenceCategory;
        this.mNotificationBodyPref = (CheckBoxPreference)this.findPreference((CharSequence)"pref_key_enable_notification_body");
        this.mNotificationBodyPref.setOnPreferenceChangeListener((Preference.OnPreferenceChangeListener)this);
        this.mMxMsgPref = (PreferenceCategory)this.findPreference((CharSequence)"pref_key_mx_msg");
        this.mMxEnabledPref = (CheckBoxPreference)this.findPreference((CharSequence)"pref_key_enable_mx_switch");
        this.mMxEnabledPref.setOnPreferenceClickListener((Preference.OnPreferenceClickListener)this);
        this.mMx1EnabledPref = (CheckBoxPreference)this.findPreference((CharSequence)"pref_key_enable_mx1_switch");
        this.mMx1EnabledPref.setOnPreferenceClickListener((Preference.OnPreferenceClickListener)this);
        this.mMx2EnabledPref = (CheckBoxPreference)this.findPreference((CharSequence)"pref_key_enable_mx2_switch");
        this.mMx2EnabledPref.setOnPreferenceClickListener((Preference.OnPreferenceClickListener)this);
        this.mMxStatusPref = (PreferenceScreen)this.findPreference((CharSequence)"pref_key_mx_status");
        this.mMxFeedbackPref = (PreferenceScreen)this.findPreference((CharSequence)"pref_key_mx_feedback");
        if (!this.hasMxSettings()) {
            this.getPreferenceScreen().removePreference((Preference)this.mMxMsgPref);
        } else {
            this.showMxSwitches();
        }
        this.mNotificationRingTonePref = (ValueListPreference)this.findPreference((CharSequence)"pref_key_ringtone_repeat");
        this.mNotificationRingTonePref.setOnPreferenceChangeListener((Preference.OnPreferenceChangeListener)this);
        this.mNotificationRingTonePref.setListTitleId(2131362113);
        this.fillRingToneSummary(MessageUtils.getNotificationRingToneRepeat(PreferenceManager.getDefaultSharedPreferences((Context)this)));
        this.mDateTypePref = (ValueListPreference)this.findPreference((CharSequence)"pref_key_date_type");
        this.mDateTypePref.setOnPreferenceChangeListener((Preference.OnPreferenceChangeListener)this);
        this.mDateTypePref.setListTitleId(2131362162);
        this.fillDateTypeSummary(MessageUtils.getDateType(PreferenceManager.getDefaultSharedPreferences((Context)this)));
        this.mCollapseSPMessagePref = (CheckBoxPreference)this.findPreference((CharSequence)"pref_key_collapse_sp_messages");
        if (Build.checkRegion((String)"IN") || !Build.IS_INTERNATIONAL_BUILD) {
            this.mCollapseSPMessagePref.setOnPreferenceChangeListener((Preference.OnPreferenceChangeListener)this);
        } else {
            preferenceCategory = (PreferenceCategory)this.findPreference((CharSequence)"pref_key_display_settings");
            if (preferenceCategory != null) {
                preferenceCategory.removePreference((Preference)this.mCollapseSPMessagePref);
            }
        }
        this.mAllowNetworkAccessPref = (CheckBoxPreference)this.findPreference((CharSequence)"pref_key_allow_network_access");
        this.mAllowNetworkAccessPref.setOnPreferenceChangeListener((Preference.OnPreferenceChangeListener)this);
        this.mShowTemplatePref = (CheckBoxPreference)this.findPreference((CharSequence)"pref_key_show_template");
        this.mShowTemplateInPref = (CheckBoxPreference)this.findPreference((CharSequence)"pref_key_show_template_in");
        preferenceCategory = (PreferenceCategory)this.findPreference((CharSequence)"pref_key_display_settings");
        if (MessageUtils.isUnderstandSupported()) {
            if (Build.checkRegion((String)"IN")) {
                this.mShowTemplateInPref.setOnPreferenceChangeListener((Preference.OnPreferenceChangeListener)this);
                if (preferenceCategory != null) {
                    preferenceCategory.removePreference((Preference)this.mShowTemplatePref);
                    preferenceCategory.removePreference((Preference)this.mAllowNetworkAccessPref);
                }
            } else {
                this.mShowTemplatePref.setOnPreferenceChangeListener((Preference.OnPreferenceChangeListener)this);
                if (preferenceCategory != null) {
                    preferenceCategory.removePreference((Preference)this.mShowTemplateInPref);
                }
            }
        } else if (preferenceCategory != null) {
            preferenceCategory.removePreference((Preference)this.mShowTemplatePref);
            preferenceCategory.removePreference((Preference)this.mShowTemplateInPref);
            preferenceCategory.removePreference((Preference)this.mAllowNetworkAccessPref);
        }
        this.mAllowNetworkAccessPref.setEnabled(this.mShowTemplatePref.isChecked());
        this.mShowAntispamPref = (CheckBoxPreference)this.findPreference((CharSequence)"pref_key_show_blocked_messages");
        this.mShowAntispamPref.setOnPreferenceChangeListener((Preference.OnPreferenceChangeListener)this);
        this.mNotificationSettingsPref = (PreferenceCategory)this.findPreference((CharSequence)"pref_key_notification_settings");
        this.mDeliveryRptPref = this.findPreference((CharSequence)"pref_key_delivery_reports");
        this.mSendDeliveryRptPref = this.findPreference((CharSequence)"pref_key_send_delivery_reports");
        if (this.mSendDeliveryRptPref != null) {
            this.mNotificationSettingsPref.removePreference(this.mSendDeliveryRptPref);
        }
        if (Build.IS_CM_CUSTOMIZATION_TEST) {
            if (this.mDeliveryRptPref != null) {
                this.mNotificationSettingsPref.removePreference(this.mDeliveryRptPref);
            }
            this.updateSimRelatedPrefs();
        } else if (this.mDeliveryRptPref != null) {
            this.mDeliveryRptPref.setDefaultValue((Object)this.getDefaultDeliveryReportMode());
            preferenceCategory = this.getSharedPreferences("com.android.mms_preferences", 1);
            ((CheckBoxPreference)this.mDeliveryRptPref).setChecked(preferenceCategory.getBoolean("pref_key_delivery_reports", this.getDefaultDeliveryReportMode()));
        }
        this.mDownLoadWildMsgPref = (PreferenceScreen)this.findPreference((CharSequence)"pref_key_download_wild_msg");
        this.mDownLoadWildMsgPref.setOnPreferenceClickListener((Preference.OnPreferenceClickListener)this);
        this.mTimeStyleGroup = (CheckBoxPreference)this.findPreference((CharSequence)"pref_key_time_style_auto_group");
        this.mMessageRecoveryPref = (PreferenceScreen)this.findPreference((CharSequence)"pref_message_recovery_introduction");
        this.mMessageRecoveryPref.setOnPreferenceClickListener((Preference.OnPreferenceClickListener)this);
        this.updateTimeSytle();
    }

    private void registerSimRelatedListener() {
        if (MSimUtils.isMSim()) {
            MSimUtils.registerChangeListener((Context)this, this.mSimInfoChangeListener);
            return;
        }
        MmsSystemEventReceiver.getInstance().registerSimStateChangedListener(this.mSimStateChagnedListener);
    }

    private void setActivateCallBack(int n, String string2) {
        Intent intent = new Intent("com.xiaomi.action.ACTION_SET_ACTIVATE_PROGRESS_CALLBACK");
        intent.setPackage("com.xiaomi.xmsf");
        intent.putExtra("EXTRA_CALLBACK", (Parcelable)PendingIntent.getBroadcast((Context)this.getApplicationContext(), (int)0, (Intent)new Intent(string2), (int)134217728));
        intent.putExtra("extra_sim_index", n);
        this.startService(intent);
    }

    private void showMessageRecoveryIntroduction() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse((String)this.getMessageRecoveryIntroductionUri()));
        this.startActivity(intent);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void showMxSwitches() {
        if (this.hasMxSettings()) {
            this.mMxMsgPref.removeAll();
            if (MSimUtils.isMSimInserted()) {
                this.mMxMsgPref.addPreference((Preference)this.mMx1EnabledPref);
                this.mMxMsgPref.addPreference((Preference)this.mMx2EnabledPref);
                this.mMxEnabledPref = null;
            } else {
                this.mMxMsgPref.addPreference((Preference)this.mMxEnabledPref);
                this.mMx1EnabledPref = null;
                this.mMx2EnabledPref = null;
            }
            if (!Build.IS_STABLE_VERSION) {
                this.mMxMsgPref.addPreference((Preference)this.mMxFeedbackPref);
            }
        }
    }

    private void unRegisterSimRelatedListener() {
        if (MSimUtils.isMSim()) {
            MSimUtils.unregisterChangeListener((Context)this, this.mSimInfoChangeListener);
            return;
        }
        MmsSystemEventReceiver.getInstance().unRegisterSimStateChangedListener(this.mSimStateChagnedListener);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void updateEnabledMxPrefStatusSummary(Preference preference, int n) {
        if (preference.isEnabled()) {
            boolean bl = false;
            Bundle bundle = ActivateStatusReceiver.getActivateInfo((int)n);
            if (bundle != null) {
                bl = bundle.getInt("activate_status") == 3;
            }
            boolean bl2 = PushSession.getInstance((Context)this).isConnected(n);
            if (!bl) {
                preference.setSummary(2131362171);
                return;
            }
            n = bl2 ? 2131362203 : 2131362204;
            preference.setSummary(n);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void updateMxPrefStatus() {
        if (this.hasMxSettings()) {
            if (this.mMxEnabledPref != null) {
                int n;
                int n2 = n = MSimUtils.getInsertedSlotId();
                if (n < 0) {
                    n2 = 0;
                }
                this.updateMxPrefStatus(this.mMxEnabledPref, n2);
            } else {
                this.updateMxPrefStatus(this.mMx1EnabledPref, 0);
                this.updateMxPrefStatus(this.mMx2EnabledPref, 1);
            }
            this.updateMxStatus();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void updateMxPrefStatus(CheckBoxPreference checkBoxPreference, int n) {
        if (n < 0) {
            return;
        }
        Bundle bundle = ActivateStatusReceiver.getActivateInfo((int)n);
        if (bundle == null) {
            Log.w((String)"MessagingPreferenceActivity", (String)("updateMxPrefStatus: info is unready for slot " + n + ", bail."));
            return;
        }
        int n2 = bundle.getInt("activate_status");
        boolean bl = bundle.getBoolean("sim_inserted") && !MSimUtils.isVirtualSimbySlotId(n);
        if (n2 == -1 || !bl) {
            Log.v((String)"MessagingPreferenceActivity", (String)"updateMxPrefStatus: Activate status is unready");
            checkBoxPreference.setChecked(false);
            checkBoxPreference.setEnabled(false);
            return;
        }
        if (!bl) {
            Log.v((String)"MessagingPreferenceActivity", (String)"updateMxPrefStatus: Sim card is not inserted");
            checkBoxPreference.setEnabled(false);
            checkBoxPreference.setChecked(false);
            return;
        }
        if (MxActivateService.isBusy(n)) {
            Log.v((String)"MessagingPreferenceActivity", (String)"updateMxPrefStatus: Enable is in process");
            checkBoxPreference.setEnabled(false);
            checkBoxPreference.setChecked(true);
            return;
        }
        Log.v((String)"MessagingPreferenceActivity", (String)"updateMxPrefStatus: Normal");
        checkBoxPreference.setEnabled(true);
        checkBoxPreference.setChecked(MxActivateService.isMxEnabled((Context)this, n));
    }

    /*
     * Enabled aggressive block sorting
     */
    private void updateMxStatus() {
        if (MSimUtils.isMSim() && this.mMxEnabledPref == null) {
            this.updateEnabledMxPrefStatusSummary((Preference)this.mMx1EnabledPref, 0);
            this.updateEnabledMxPrefStatusSummary((Preference)this.mMx2EnabledPref, 1);
            if (this.mMx1EnabledPref.isChecked() && this.mMx1EnabledPref.isEnabled() || this.mMx2EnabledPref.isChecked() && this.mMx2EnabledPref.isEnabled()) {
                if (this.findPreference((CharSequence)"pref_key_mx_status") == null) {
                    this.mMxMsgPref.addPreference((Preference)this.mMxStatusPref);
                }
                this.mMxStatusPref.setEnabled(true);
            } else if (this.findPreference((CharSequence)"pref_key_mx_status") != null && !MxActivateService.isMxEnabled((Context)this, 0) && !MxActivateService.isMxEnabled((Context)this, 1)) {
                this.mMxMsgPref.removePreference((Preference)this.mMxStatusPref);
            }
        } else {
            this.updateEnabledMxPrefStatusSummary((Preference)this.mMxEnabledPref, MSimUtils.getInsertedSlotId());
            if (this.mMxEnabledPref.isChecked() && this.mMxEnabledPref.isEnabled()) {
                if (this.findPreference((CharSequence)"pref_key_mx_status") == null) {
                    this.mMxMsgPref.addPreference((Preference)this.mMxStatusPref);
                }
                this.mMxStatusPref.setEnabled(true);
            } else if (this.findPreference((CharSequence)"pref_key_mx_status") != null && !MxActivateService.isMxEnabled((Context)this, 0)) {
                this.mMxMsgPref.removePreference((Preference)this.mMxStatusPref);
            }
        }
        this.fillMxStatusSummary();
    }

    private void updateSimRelatedPrefs() {
        this.updateSimState();
        this.cleanSimRelatedPrefs();
        int n = MSimUtils.getInsertedSlotId();
        Log.d((String)"MessagingPreferenceActivity", (String)("updateDeliveryRptPrefs slotId is " + String.valueOf((int)n)));
        if (this.mSimCount == 0 || n == -1) {
            Log.d((String)"MessagingPreferenceActivity", (String)"updateDeliveryRptPrefs nothing");
            return;
        }
        long l = MSimUtils.getSimIdBySlotId(n);
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.android.mms_preferences", 1);
        this.createDeliveryRptPref(sharedPreferences, l);
        this.mNotificationSettingsPref.addPreference(this.mDeliveryRptPref);
        this.createValidityPeriodPref(sharedPreferences, l);
        this.mNotificationSettingsPref.addPreference(this.mSmsValidityPeriodPref);
        this.createSendDeliveryRptPref(sharedPreferences, l);
        this.mNotificationSettingsPref.addPreference(this.mSendDeliveryRptPref);
    }

    private void updateSimState() {
        this.mSimCount = MSimUtils.getInsertedSimCount();
        Log.d((String)"MessagingPreferenceActivity", (String)("updateSimState sim count is " + this.mSimCount));
    }

    private void updateTimeSytle() {
        if (MiuiConfiguration.getScaleMode() == 11 || MiuiConfiguration.getScaleMode() == 15) {
            this.mTimeStyleGroup.setEnabled(false);
            this.mTimeStyleGroup.setSummary(2131362090);
            return;
        }
        this.mTimeStyleGroup.setEnabled(true);
        this.mTimeStyleGroup.setSummary(2131362089);
    }

    public void onActivateStatusChanged(int n, ActivateStatusReceiver.Event event, Bundle bundle) {
        this.updateMxPrefStatus();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.addPreferencesFromResource(2131099654);
        this.initPreference();
        if (this.hasMxSettings()) {
            this.mBroadcastFilter = new IntentFilter();
            this.mBroadcastFilter.addAction("com.xiaomi.mms.action.ENBALE_RESULT");
            this.mBroadcastFilter.addAction("com.xiaomi.mms.action.DISABLE_RESULT");
            this.mBroadcastFilter.addAction("com.xiaomi.mms.action.STATUS_START");
            this.mBroadcastFilter.addAction("com.xiaomi.mms.PUSH_STATUS_CHANGED");
            this.mBroadcastFilter.addAction("miui.intent.action.MX_STATUS_SLOT_1_DETAIL");
            this.mBroadcastFilter.addAction("miui.intent.action.MX_STATUS_SLOT_2_DETAIL");
            this.mReceiver = new MxStatusReceiver();
            this.setActivateCallBack(0, "miui.intent.action.MX_STATUS_SLOT_1_DETAIL");
            this.setActivateCallBack(1, "miui.intent.action.MX_STATUS_SLOT_2_DETAIL");
        }
        this.handleIntent();
        if (Build.IS_CM_CUSTOMIZATION_TEST) {
            this.registerSimRelatedListener();
        }
    }

    protected void onDestroy() {
        if (Build.IS_CM_CUSTOMIZATION_TEST) {
            this.unRegisterSimRelatedListener();
        }
        super.onDestroy();
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.setIntent(intent);
        this.handleIntent();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            default: {
                return false;
            }
            case 16908332: 
        }
        this.finish();
        return true;
    }

    protected void onPause() {
        if (this.hasMxSettings()) {
            this.unregisterReceiver(this.mReceiver);
            ActivateStatusReceiver.removeListener((ActivateStatusReceiver.ActivateStatusListener)this);
        }
        super.onPause();
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean onPreferenceChange(Preference preference, Object object) {
        if (preference == this.mDateTypePref) {
            this.fillDateTypeSummary((String)object);
            return true;
        } else {
            if (preference == this.mNotificationRingTonePref) {
                this.fillRingToneSummary((String)object);
                return true;
            }
            if (preference == this.mNotificationBodyPref) {
                boolean bl = (Boolean)object;
                preference = this.getContentResolver();
                int n = bl ? 1 : 0;
                Settings.System.putInt((ContentResolver)preference, (String)"pref_key_enable_notification_body", (int)n);
                return true;
            }
            if (preference == this.mShowTemplatePref || preference == this.mShowTemplateInPref) {
                if (((Boolean)object).booleanValue()) {
                    UnderstandLoader.update();
                } else {
                    UnderstandLoader.destroy();
                }
                this.mAllowNetworkAccessPref.setChecked(((Boolean)object).booleanValue());
                this.mAllowNetworkAccessPref.setEnabled(((Boolean)object).booleanValue());
                MiStatSdkHelper.recordStringPropertyEvent("network_recommend", "open_template_pref", String.valueOf((Object)((Boolean)object)));
                return true;
            }
            if (preference == this.mSmsValidityPeriodPref) {
                if (!Build.IS_CM_CUSTOMIZATION_TEST) return true;
                {
                    this.handleSmsValidityPeriod(preference, (String)object);
                    return true;
                }
            } else {
                if (preference != this.mAllowNetworkAccessPref) return true;
                {
                    MiStatSdkHelper.recordStringPropertyEvent("network_recommend", "open_network_access_pref", String.valueOf((Object)((Boolean)object)));
                    return true;
                }
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean onPreferenceClick(Preference preference) {
        if (preference == this.mMxEnabledPref) {
            int n = MSimUtils.getInsertedSlotId();
            if (n == -1) return false;
            {
                this.handleMxSwitchPref(this.mMxEnabledPref, n);
                return false;
            }
        } else {
            if (preference == this.mMx1EnabledPref) {
                this.handleMxSwitchPref(this.mMx1EnabledPref, 0);
                return true;
            }
            if (preference == this.mMx2EnabledPref) {
                this.handleMxSwitchPref(this.mMx2EnabledPref, 1);
                return true;
            }
            if (preference == this.mCollapseSPMessagePref) {
                return ServiceCategoryUpdateAsyncTask.startUpdateAsyncTask((Context)this, false, this);
            }
            if (preference == this.mDeliveryRptPref || preference == this.mSendDeliveryRptPref) {
                if (!Build.IS_CM_CUSTOMIZATION_TEST) return false;
                {
                    this.handleDeliveryRptRelatedPrefsClick(preference);
                    return true;
                }
            } else if (preference == this.mSmsValidityPeriodPref) {
                if (!Build.IS_CM_CUSTOMIZATION_TEST) return false;
                {
                    this.handleSmsValidityPeriod(preference, null);
                    return true;
                }
            } else {
                if (preference == this.mDownLoadWildMsgPref) {
                    this.handleDownloadWildMsgPref();
                    return true;
                }
                if (preference != this.mMessageRecoveryPref) return false;
                {
                    this.showMessageRecoveryIntroduction();
                    return true;
                }
            }
        }
    }

    protected void onResume() {
        super.onResume();
        if (this.hasMxSettings()) {
            this.registerReceiver(this.mReceiver, this.mBroadcastFilter);
            ActivateStatusReceiver.addListener((ActivateStatusReceiver.ActivateStatusListener)this);
        }
        this.mNotificationBodyPref.setChecked(MessageUtils.getPrefNotificationBodyEnabled((Context)this));
        this.updateMxPrefStatus();
        if (MessageUtils.isAccessControlled((Context)this)) {
            this.mNotificationBodyPref.setEnabled(false);
            this.mNotificationBodyPref.setSummary(2131362106);
            return;
        }
        this.mNotificationBodyPref.setEnabled(true);
        this.mNotificationBodyPref.setSummary(2131362105);
    }

    public void onStop() {
        super.onStop();
        ServiceCategoryUpdateAsyncTask.unRegisterListener(this);
        if (this.mWildMsgDialog != null) {
            this.mWildMsgDialog.cancel();
        }
        this.mWildMsgDialog = null;
    }

    @Override
    public void onUpdateSuccess() {
    }

    private class MxStatusReceiver
    extends BroadcastReceiver {
        private MxStatusReceiver() {
        }

        /*
         * Enabled aggressive block sorting
         */
        private void updateMxPrefStatusSummary(int n, int n2) {
            if (!MessagingPreferenceActivity.this.hasMxSettings() || n == 0) return;
            {
                if (MessagingPreferenceActivity.this.mMxEnabledPref != null) {
                    MessagingPreferenceActivity.this.mMxEnabledPref.setSummary(n);
                    return;
                } else {
                    if (MessagingPreferenceActivity.this.mMx1EnabledPref != null && n2 == 0) {
                        MessagingPreferenceActivity.this.mMx1EnabledPref.setSummary(n);
                        return;
                    }
                    if (MessagingPreferenceActivity.this.mMx2EnabledPref == null || 1 != n2) return;
                    {
                        MessagingPreferenceActivity.this.mMx2EnabledPref.setSummary(n);
                        return;
                    }
                }
            }
        }

        int getDsptResId(int n) {
            switch (n) {
                default: {
                    return 0;
                }
                case 2: 
                case 5: 
                case 8: 
                case 14: 
                case 16: 
                case 18: 
                case 20: 
                case 22: 
                case 23: 
                case 25: {
                    return 2131362389;
                }
                case 3: 
                case 15: {
                    return 2131362393;
                }
                case 4: 
                case 6: 
                case 7: 
                case 11: 
                case 12: 
                case 13: 
                case 17: 
                case 19: 
                case 21: 
                case 24: {
                    return 2131362388;
                }
                case 9: {
                    return 2131362392;
                }
                case 10: 
            }
            return 2131362387;
        }

        /*
         * Enabled aggressive block sorting
         */
        public void onReceive(Context context, Intent intent) {
            int n = intent.getIntExtra("message", 1);
            if ("miui.intent.action.MX_STATUS_SLOT_1_DETAIL".equals((Object)intent.getAction())) {
                this.updateMxPrefStatusSummary(this.getDsptResId(n), 0);
            } else if ("miui.intent.action.MX_STATUS_SLOT_2_DETAIL".equals((Object)intent.getAction())) {
                this.updateMxPrefStatusSummary(this.getDsptResId(n), 1);
            }
            MessagingPreferenceActivity.this.updateMxPrefStatus();
        }
    }

}

