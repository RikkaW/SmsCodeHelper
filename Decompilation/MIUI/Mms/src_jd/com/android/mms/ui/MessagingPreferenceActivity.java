package com.android.mms.ui;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.content.res.MiuiConfiguration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceCategory;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.provider.Settings.System;
import android.util.Log;
import android.view.MenuItem;
import com.android.mms.ServiceCategoryUpdateAsyncTask;
import com.android.mms.ServiceCategoryUpdateAsyncTask.IServiceCategoryUpdateListener;
import com.android.mms.transaction.MmsSystemEventReceiver;
import com.android.mms.transaction.MmsSystemEventReceiver.SimStateChangedListener;
import com.android.mms.understand.UnderstandLoader;
import com.android.mms.util.MSimUtils;
import com.android.mms.util.MiStatSdkHelper;
import com.xiaomi.accountsdk.activate.ActivateStatusReceiver;
import com.xiaomi.accountsdk.activate.ActivateStatusReceiver.ActivateStatusListener;
import com.xiaomi.accountsdk.activate.ActivateStatusReceiver.Event;
import com.xiaomi.mms.transaction.MxActivateService;
import com.xiaomi.mms.transaction.MxMessageService;
import com.xiaomi.mms.transaction.PushSession;
import com.xiaomi.mms.utils.MxActivateSimpleDialog;
import com.xiaomi.mms.utils.MxActivateSimpleDialog.ActivationCallBack;
import java.util.Locale;
import miui.os.Build;
import miui.preference.PreferenceActivity;
import miui.telephony.SubscriptionManager.OnSubscriptionsChangedListener;

public class MessagingPreferenceActivity
  extends PreferenceActivity
  implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener, ServiceCategoryUpdateAsyncTask.IServiceCategoryUpdateListener, ActivateStatusReceiver.ActivateStatusListener
{
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
  private SubscriptionManager.OnSubscriptionsChangedListener mSimInfoChangeListener = new SubscriptionManager.OnSubscriptionsChangedListener()
  {
    public void onSubscriptionsChanged()
    {
      Log.d("MessagingPreferenceActivity", "update sim info change");
      MessagingPreferenceActivity.this.updateSimRelatedPrefs();
    }
  };
  private MmsSystemEventReceiver.SimStateChangedListener mSimStateChagnedListener = new MmsSystemEventReceiver.SimStateChangedListener()
  {
    public void onSimStateChanged(String paramAnonymousString)
    {
      Log.d("MessagingPreferenceActivity", "update sim info change");
      MessagingPreferenceActivity.this.updateSimRelatedPrefs();
    }
  };
  private Preference mSmsValidityPeriodPref;
  private CheckBoxPreference mTimeStyleGroup;
  private WildMsgDialog mWildMsgDialog;
  
  private void cleanSimRelatedPrefs()
  {
    if (mDeliveryRptPref != null)
    {
      mDeliveryRptPref.setOnPreferenceClickListener(null);
      mNotificationSettingsPref.removePreference(mDeliveryRptPref);
      mDeliveryRptPref = null;
    }
    if (mSmsValidityPeriodPref != null)
    {
      mSmsValidityPeriodPref.setOnPreferenceChangeListener(null);
      mSmsValidityPeriodPref.setOnPreferenceClickListener(null);
      mNotificationSettingsPref.removePreference(mSmsValidityPeriodPref);
      mSmsValidityPeriodPref = null;
    }
    if (mSendDeliveryRptPref != null)
    {
      mSendDeliveryRptPref.setOnPreferenceClickListener(null);
      mNotificationSettingsPref.removePreference(mSendDeliveryRptPref);
      mSendDeliveryRptPref = null;
    }
  }
  
  private void createDeliveryRptPref(SharedPreferences paramSharedPreferences, long paramLong)
  {
    String str = "pref_key_delivery_reports";
    if (mSimCount > 1) {}
    CheckBoxPreference localCheckBoxPreference;
    for (mDeliveryRptPref = new Preference(this);; mDeliveryRptPref = localCheckBoxPreference)
    {
      mDeliveryRptPref.setTitle(2131362109);
      mDeliveryRptPref.setSummary(2131362110);
      mDeliveryRptPref.setKey(str);
      mDeliveryRptPref.setOnPreferenceClickListener(this);
      return;
      boolean bool = getDefaultDeliveryReportMode();
      localCheckBoxPreference = new CheckBoxPreference(this);
      localCheckBoxPreference.setDefaultValue(Boolean.valueOf(bool));
      if (MSimUtils.isMSim()) {
        str = MSimUtils.createKeyWithSimId(paramLong, "pref_key_delivery_reports");
      }
      localCheckBoxPreference.setChecked(paramSharedPreferences.getBoolean(str, bool));
    }
  }
  
  private void createSendDeliveryRptPref(SharedPreferences paramSharedPreferences, long paramLong)
  {
    String str = "pref_key_send_delivery_reports";
    if (mSimCount > 1) {}
    CheckBoxPreference localCheckBoxPreference;
    for (mSendDeliveryRptPref = new Preference(this);; mSendDeliveryRptPref = localCheckBoxPreference)
    {
      mSendDeliveryRptPref.setTitle(2131362111);
      mSendDeliveryRptPref.setSummary(2131362112);
      mSendDeliveryRptPref.setKey(str);
      mSendDeliveryRptPref.setOnPreferenceClickListener(this);
      return;
      localCheckBoxPreference = new CheckBoxPreference(this);
      localCheckBoxPreference.setDefaultValue(Boolean.valueOf(false));
      if (MSimUtils.isMSim())
      {
        str = MSimUtils.createKeyWithSimId(paramLong, "pref_key_send_delivery_reports");
        localCheckBoxPreference.setChecked(paramSharedPreferences.getBoolean(str, false));
      }
    }
  }
  
  private void createValidityPeriodPref(SharedPreferences paramSharedPreferences, long paramLong)
  {
    String str = "pref_key_sms_validity_period";
    if (mSimCount > 1)
    {
      mSmsValidityPeriodPref = new Preference(this);
      mSmsValidityPeriodPref.setOnPreferenceClickListener(this);
    }
    for (paramSharedPreferences = str;; paramSharedPreferences = str)
    {
      mSmsValidityPeriodPref.setTitle(2131362367);
      mSmsValidityPeriodPref.setKey(paramSharedPreferences);
      return;
      ValueListPreference localValueListPreference = new ValueListPreference(this);
      localValueListPreference.setEntries(2131230731);
      localValueListPreference.setEntryValues(2131230732);
      localValueListPreference.setListTitleId(2131362367);
      str = MSimUtils.createKeyWithSimId(paramLong, "pref_key_sms_validity_period");
      SelectCardListPreferenceActivity.fillSmsValidityValue(this, localValueListPreference, paramSharedPreferences.getString(str, "-1"));
      mSmsValidityPeriodPref = localValueListPreference;
      mSmsValidityPeriodPref.setOnPreferenceChangeListener(this);
    }
  }
  
  public static void enableNotifications(boolean paramBoolean, Context paramContext)
  {
    paramContext = paramContext.getContentResolver();
    if (paramBoolean) {}
    for (int i = 1;; i = 0)
    {
      Settings.System.putInt(paramContext, "pref_key_enable_notification", i);
      return;
    }
  }
  
  private void fillDateTypeSummary(String paramString)
  {
    int i = Integer.parseInt(paramString);
    paramString = getResources().getStringArray(2131230727);
    mDateTypePref.setRightValue(paramString[i]);
  }
  
  private void fillMxStatusSummary()
  {
    mMxStatusPref.setSummary(getString(2131362173, new Object[] { Integer.valueOf(MxMessageService.getMxSmsCount(this)), Integer.valueOf(MxMessageService.getMxMmsCount(this)) }));
  }
  
  private void fillRingToneSummary(String paramString)
  {
    int i = Integer.parseInt(paramString);
    int j;
    if (i > 0)
    {
      j = i - 1;
      if (j == 4) {
        i = 3;
      }
    }
    for (;;)
    {
      Object localObject = getResources();
      paramString = ((Resources)localObject).getStringArray(2131230722);
      localObject = ((Resources)localObject).getString(2131362114);
      mNotificationRingTonePref.setSummary((CharSequence)localObject);
      mNotificationRingTonePref.setRightValue(paramString[i]);
      return;
      i = j;
      if (j == 9)
      {
        i = 4;
        continue;
        i = 0;
      }
    }
  }
  
  private boolean getDefaultDeliveryReportMode()
  {
    boolean bool2 = true;
    boolean bool1;
    if (Build.IS_CM_CUSTOMIZATION_TEST) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (!Build.IS_INTERNATIONAL_BUILD);
      bool1 = bool2;
    } while (!Build.checkRegion("BR"));
    return false;
  }
  
  private String getMessageRecoveryIntroductionUri()
  {
    String str = getResourcesgetConfigurationlocale.toString();
    if (("zh_CN".equals(str)) || ("zh_TW".equals(str))) {
      return String.format("https://i.mi.com/static2?filename=MicloudWebHelpStatic/www/sms/recovery.html&timestamp=%d&lang=%s", new Object[] { Long.valueOf(System.currentTimeMillis()), str });
    }
    return String.format("https://i.mi.com/static2?filename=MicloudWebHelpStatic/www/sms/recovery.html&timestamp=%d&lang=%s", new Object[] { Long.valueOf(System.currentTimeMillis()), "en" });
  }
  
  public static boolean getNotificationEnabled(Context paramContext)
  {
    return MessageUtils.getPrefNotificationEnabled(paramContext);
  }
  
  private boolean handleDeliveryRptRelatedPrefsClick(Preference paramPreference)
  {
    if (mSimCount > 1)
    {
      Intent localIntent = new Intent(this, MultiSimPreferenceAcitvity.class);
      localIntent.putExtra("preference_key", paramPreference.getKey());
      localIntent.putExtra("preference_title", paramPreference.getTitleRes());
      startActivity(localIntent);
      return true;
    }
    return false;
  }
  
  private void handleDownloadWildMsgPref()
  {
    if (mWildMsgDialog != null) {
      mWildMsgDialog.cancel();
    }
    mWildMsgDialog = new WildMsgDialog(this, true);
    mWildMsgDialog.show();
  }
  
  private void handleIntent()
  {
    if (MxActivateService.getLastFailureCode(this) > 0) {}
  }
  
  private void handleMxSwitchPref(CheckBoxPreference paramCheckBoxPreference, final int paramInt)
  {
    if ((!MxActivateService.isMxEnabled(this, paramInt)) && (paramInt >= 0))
    {
      paramCheckBoxPreference = ActivateStatusReceiver.getActivateInfo(paramInt);
      if (paramCheckBoxPreference != null)
      {
        if (paramCheckBoxPreference.getInt("activate_status") == 3) {
          break label88;
        }
        paramCheckBoxPreference = new MxActivateSimpleDialog(this, 0, "Mms_Pref", paramInt);
        paramCheckBoxPreference.setActivationCallBack(new MxActivateSimpleDialog.ActivationCallBack()
        {
          public void onResult(int paramAnonymousInt)
          {
            if (paramAnonymousInt != 1) {
              MxActivateService.setEnableAfterActivation(paramInt, true);
            }
            MessagingPreferenceActivity.this.updateMxPrefStatus();
          }
        });
        paramCheckBoxPreference.show();
        PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean("already_remind_activate_voip", true).commit();
      }
    }
    label88:
    do
    {
      return;
      MxActivateService.enableMx(this, paramInt, true, true);
      updateMxPrefStatus();
      return;
      MxActivateService.enableMx(this, paramInt, false, true);
    } while ((findPreference("pref_key_mx_status") == null) || (MxActivateService.isMxEnabled(this, 0)) || (MxActivateService.isMxEnabled(this, 1)));
    mMxMsgPref.removePreference(mMxStatusPref);
  }
  
  private boolean handleSmsValidityPeriod(Preference paramPreference, String paramString)
  {
    if (mSimCount > 1)
    {
      paramString = new Intent(this, SelectCardListPreferenceActivity.class);
      paramString.putExtra("preference_key", paramPreference.getKey());
      paramString.putExtra("preference_title", paramPreference.getTitleRes());
      startActivity(paramString);
      return true;
    }
    SelectCardListPreferenceActivity.fillSmsValidityValue(this, (ValueListPreference)paramPreference, paramString);
    return false;
  }
  
  private boolean hasMxSettings()
  {
    return !Build.IS_CM_CUSTOMIZATION;
  }
  
  private void initPreference()
  {
    mNotificationBodyPref = ((CheckBoxPreference)findPreference("pref_key_enable_notification_body"));
    mNotificationBodyPref.setOnPreferenceChangeListener(this);
    mMxMsgPref = ((PreferenceCategory)findPreference("pref_key_mx_msg"));
    mMxEnabledPref = ((CheckBoxPreference)findPreference("pref_key_enable_mx_switch"));
    mMxEnabledPref.setOnPreferenceClickListener(this);
    mMx1EnabledPref = ((CheckBoxPreference)findPreference("pref_key_enable_mx1_switch"));
    mMx1EnabledPref.setOnPreferenceClickListener(this);
    mMx2EnabledPref = ((CheckBoxPreference)findPreference("pref_key_enable_mx2_switch"));
    mMx2EnabledPref.setOnPreferenceClickListener(this);
    mMxStatusPref = ((PreferenceScreen)findPreference("pref_key_mx_status"));
    mMxFeedbackPref = ((PreferenceScreen)findPreference("pref_key_mx_feedback"));
    label272:
    Object localObject;
    if (!hasMxSettings())
    {
      getPreferenceScreen().removePreference(mMxMsgPref);
      mNotificationRingTonePref = ((ValueListPreference)findPreference("pref_key_ringtone_repeat"));
      mNotificationRingTonePref.setOnPreferenceChangeListener(this);
      mNotificationRingTonePref.setListTitleId(2131362113);
      fillRingToneSummary(MessageUtils.getNotificationRingToneRepeat(PreferenceManager.getDefaultSharedPreferences(this)));
      mDateTypePref = ((ValueListPreference)findPreference("pref_key_date_type"));
      mDateTypePref.setOnPreferenceChangeListener(this);
      mDateTypePref.setListTitleId(2131362162);
      fillDateTypeSummary(MessageUtils.getDateType(PreferenceManager.getDefaultSharedPreferences(this)));
      mCollapseSPMessagePref = ((CheckBoxPreference)findPreference("pref_key_collapse_sp_messages"));
      if ((!Build.checkRegion("IN")) && (Build.IS_INTERNATIONAL_BUILD)) {
        break label566;
      }
      mCollapseSPMessagePref.setOnPreferenceChangeListener(this);
      mAllowNetworkAccessPref = ((CheckBoxPreference)findPreference("pref_key_allow_network_access"));
      mAllowNetworkAccessPref.setOnPreferenceChangeListener(this);
      mShowTemplatePref = ((CheckBoxPreference)findPreference("pref_key_show_template"));
      mShowTemplateInPref = ((CheckBoxPreference)findPreference("pref_key_show_template_in"));
      localObject = (PreferenceCategory)findPreference("pref_key_display_settings");
      if (!MessageUtils.isUnderstandSupported()) {
        break label617;
      }
      if (!Build.checkRegion("IN")) {
        break label593;
      }
      mShowTemplateInPref.setOnPreferenceChangeListener(this);
      if (localObject != null)
      {
        ((PreferenceCategory)localObject).removePreference(mShowTemplatePref);
        ((PreferenceCategory)localObject).removePreference(mAllowNetworkAccessPref);
      }
      label378:
      mAllowNetworkAccessPref.setEnabled(mShowTemplatePref.isChecked());
      mShowAntispamPref = ((CheckBoxPreference)findPreference("pref_key_show_blocked_messages"));
      mShowAntispamPref.setOnPreferenceChangeListener(this);
      mNotificationSettingsPref = ((PreferenceCategory)findPreference("pref_key_notification_settings"));
      mDeliveryRptPref = findPreference("pref_key_delivery_reports");
      mSendDeliveryRptPref = findPreference("pref_key_send_delivery_reports");
      if (mSendDeliveryRptPref != null) {
        mNotificationSettingsPref.removePreference(mSendDeliveryRptPref);
      }
      if (!Build.IS_CM_CUSTOMIZATION_TEST) {
        break label651;
      }
      if (mDeliveryRptPref != null) {
        mNotificationSettingsPref.removePreference(mDeliveryRptPref);
      }
      updateSimRelatedPrefs();
    }
    for (;;)
    {
      mDownLoadWildMsgPref = ((PreferenceScreen)findPreference("pref_key_download_wild_msg"));
      mDownLoadWildMsgPref.setOnPreferenceClickListener(this);
      mTimeStyleGroup = ((CheckBoxPreference)findPreference("pref_key_time_style_auto_group"));
      mMessageRecoveryPref = ((PreferenceScreen)findPreference("pref_message_recovery_introduction"));
      mMessageRecoveryPref.setOnPreferenceClickListener(this);
      updateTimeSytle();
      return;
      showMxSwitches();
      break;
      label566:
      localObject = (PreferenceCategory)findPreference("pref_key_display_settings");
      if (localObject == null) {
        break label272;
      }
      ((PreferenceCategory)localObject).removePreference(mCollapseSPMessagePref);
      break label272;
      label593:
      mShowTemplatePref.setOnPreferenceChangeListener(this);
      if (localObject == null) {
        break label378;
      }
      ((PreferenceCategory)localObject).removePreference(mShowTemplateInPref);
      break label378;
      label617:
      if (localObject == null) {
        break label378;
      }
      ((PreferenceCategory)localObject).removePreference(mShowTemplatePref);
      ((PreferenceCategory)localObject).removePreference(mShowTemplateInPref);
      ((PreferenceCategory)localObject).removePreference(mAllowNetworkAccessPref);
      break label378;
      label651:
      if (mDeliveryRptPref != null)
      {
        mDeliveryRptPref.setDefaultValue(Boolean.valueOf(getDefaultDeliveryReportMode()));
        localObject = getSharedPreferences("com.android.mms_preferences", 1);
        ((CheckBoxPreference)mDeliveryRptPref).setChecked(((SharedPreferences)localObject).getBoolean("pref_key_delivery_reports", getDefaultDeliveryReportMode()));
      }
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
  
  private void setActivateCallBack(int paramInt, String paramString)
  {
    Intent localIntent = new Intent("com.xiaomi.action.ACTION_SET_ACTIVATE_PROGRESS_CALLBACK");
    localIntent.setPackage("com.xiaomi.xmsf");
    localIntent.putExtra("EXTRA_CALLBACK", PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(paramString), 134217728));
    localIntent.putExtra("extra_sim_index", paramInt);
    startService(localIntent);
  }
  
  private void showMessageRecoveryIntroduction()
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.VIEW");
    localIntent.setData(Uri.parse(getMessageRecoveryIntroductionUri()));
    startActivity(localIntent);
  }
  
  private void showMxSwitches()
  {
    if (hasMxSettings())
    {
      mMxMsgPref.removeAll();
      if (!MSimUtils.isMSimInserted()) {
        break label68;
      }
      mMxMsgPref.addPreference(mMx1EnabledPref);
      mMxMsgPref.addPreference(mMx2EnabledPref);
      mMxEnabledPref = null;
    }
    for (;;)
    {
      if (!Build.IS_STABLE_VERSION) {
        mMxMsgPref.addPreference(mMxFeedbackPref);
      }
      return;
      label68:
      mMxMsgPref.addPreference(mMxEnabledPref);
      mMx1EnabledPref = null;
      mMx2EnabledPref = null;
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
  
  private void updateEnabledMxPrefStatusSummary(Preference paramPreference, int paramInt)
  {
    int i;
    if (paramPreference.isEnabled())
    {
      i = 0;
      Bundle localBundle = ActivateStatusReceiver.getActivateInfo(paramInt);
      if (localBundle != null)
      {
        if (localBundle.getInt("activate_status") != 3) {
          break label63;
        }
        i = 1;
      }
      boolean bool = PushSession.getInstance(this).isConnected(paramInt);
      if (i == 0) {
        break label75;
      }
      if (!bool) {
        break label68;
      }
    }
    label63:
    label68:
    for (paramInt = 2131362203;; paramInt = 2131362204)
    {
      paramPreference.setSummary(paramInt);
      return;
      i = 0;
      break;
    }
    label75:
    paramPreference.setSummary(2131362171);
  }
  
  private void updateMxPrefStatus()
  {
    if (hasMxSettings())
    {
      if (mMxEnabledPref == null) {
        break label40;
      }
      int j = MSimUtils.getInsertedSlotId();
      int i = j;
      if (j < 0) {
        i = 0;
      }
      updateMxPrefStatus(mMxEnabledPref, i);
    }
    for (;;)
    {
      updateMxStatus();
      return;
      label40:
      updateMxPrefStatus(mMx1EnabledPref, 0);
      updateMxPrefStatus(mMx2EnabledPref, 1);
    }
  }
  
  private void updateMxPrefStatus(CheckBoxPreference paramCheckBoxPreference, int paramInt)
  {
    if (paramInt < 0) {
      return;
    }
    Bundle localBundle = ActivateStatusReceiver.getActivateInfo(paramInt);
    if (localBundle == null)
    {
      Log.w("MessagingPreferenceActivity", "updateMxPrefStatus: info is unready for slot " + paramInt + ", bail.");
      return;
    }
    int j = localBundle.getInt("activate_status");
    if ((localBundle.getBoolean("sim_inserted")) && (!MSimUtils.isVirtualSimbySlotId(paramInt))) {}
    for (int i = 1; (j == -1) || (i == 0); i = 0)
    {
      Log.v("MessagingPreferenceActivity", "updateMxPrefStatus: Activate status is unready");
      paramCheckBoxPreference.setChecked(false);
      paramCheckBoxPreference.setEnabled(false);
      return;
    }
    if (i == 0)
    {
      Log.v("MessagingPreferenceActivity", "updateMxPrefStatus: Sim card is not inserted");
      paramCheckBoxPreference.setEnabled(false);
      paramCheckBoxPreference.setChecked(false);
      return;
    }
    if (MxActivateService.isBusy(paramInt))
    {
      Log.v("MessagingPreferenceActivity", "updateMxPrefStatus: Enable is in process");
      paramCheckBoxPreference.setEnabled(false);
      paramCheckBoxPreference.setChecked(true);
      return;
    }
    Log.v("MessagingPreferenceActivity", "updateMxPrefStatus: Normal");
    paramCheckBoxPreference.setEnabled(true);
    paramCheckBoxPreference.setChecked(MxActivateService.isMxEnabled(this, paramInt));
  }
  
  private void updateMxStatus()
  {
    if ((MSimUtils.isMSim()) && (mMxEnabledPref == null))
    {
      updateEnabledMxPrefStatusSummary(mMx1EnabledPref, 0);
      updateEnabledMxPrefStatusSummary(mMx2EnabledPref, 1);
      if (((mMx1EnabledPref.isChecked()) && (mMx1EnabledPref.isEnabled())) || ((mMx2EnabledPref.isChecked()) && (mMx2EnabledPref.isEnabled())))
      {
        if (findPreference("pref_key_mx_status") == null) {
          mMxMsgPref.addPreference(mMxStatusPref);
        }
        mMxStatusPref.setEnabled(true);
      }
    }
    for (;;)
    {
      fillMxStatusSummary();
      return;
      if ((findPreference("pref_key_mx_status") != null) && (!MxActivateService.isMxEnabled(this, 0)) && (!MxActivateService.isMxEnabled(this, 1)))
      {
        mMxMsgPref.removePreference(mMxStatusPref);
        continue;
        updateEnabledMxPrefStatusSummary(mMxEnabledPref, MSimUtils.getInsertedSlotId());
        if ((mMxEnabledPref.isChecked()) && (mMxEnabledPref.isEnabled()))
        {
          if (findPreference("pref_key_mx_status") == null) {
            mMxMsgPref.addPreference(mMxStatusPref);
          }
          mMxStatusPref.setEnabled(true);
        }
        else if ((findPreference("pref_key_mx_status") != null) && (!MxActivateService.isMxEnabled(this, 0)))
        {
          mMxMsgPref.removePreference(mMxStatusPref);
        }
      }
    }
  }
  
  private void updateSimRelatedPrefs()
  {
    updateSimState();
    cleanSimRelatedPrefs();
    int i = MSimUtils.getInsertedSlotId();
    Log.d("MessagingPreferenceActivity", "updateDeliveryRptPrefs slotId is " + String.valueOf(i));
    if ((mSimCount == 0) || (i == -1))
    {
      Log.d("MessagingPreferenceActivity", "updateDeliveryRptPrefs nothing");
      return;
    }
    long l = MSimUtils.getSimIdBySlotId(i);
    SharedPreferences localSharedPreferences = getSharedPreferences("com.android.mms_preferences", 1);
    createDeliveryRptPref(localSharedPreferences, l);
    mNotificationSettingsPref.addPreference(mDeliveryRptPref);
    createValidityPeriodPref(localSharedPreferences, l);
    mNotificationSettingsPref.addPreference(mSmsValidityPeriodPref);
    createSendDeliveryRptPref(localSharedPreferences, l);
    mNotificationSettingsPref.addPreference(mSendDeliveryRptPref);
  }
  
  private void updateSimState()
  {
    mSimCount = MSimUtils.getInsertedSimCount();
    Log.d("MessagingPreferenceActivity", "updateSimState sim count is " + mSimCount);
  }
  
  private void updateTimeSytle()
  {
    if ((MiuiConfiguration.getScaleMode() == 11) || (MiuiConfiguration.getScaleMode() == 15))
    {
      mTimeStyleGroup.setEnabled(false);
      mTimeStyleGroup.setSummary(2131362090);
      return;
    }
    mTimeStyleGroup.setEnabled(true);
    mTimeStyleGroup.setSummary(2131362089);
  }
  
  public void onActivateStatusChanged(int paramInt, ActivateStatusReceiver.Event paramEvent, Bundle paramBundle)
  {
    updateMxPrefStatus();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    addPreferencesFromResource(2131099654);
    initPreference();
    if (hasMxSettings())
    {
      mBroadcastFilter = new IntentFilter();
      mBroadcastFilter.addAction("com.xiaomi.mms.action.ENBALE_RESULT");
      mBroadcastFilter.addAction("com.xiaomi.mms.action.DISABLE_RESULT");
      mBroadcastFilter.addAction("com.xiaomi.mms.action.STATUS_START");
      mBroadcastFilter.addAction("com.xiaomi.mms.PUSH_STATUS_CHANGED");
      mBroadcastFilter.addAction("miui.intent.action.MX_STATUS_SLOT_1_DETAIL");
      mBroadcastFilter.addAction("miui.intent.action.MX_STATUS_SLOT_2_DETAIL");
      mReceiver = new MxStatusReceiver(null);
      setActivateCallBack(0, "miui.intent.action.MX_STATUS_SLOT_1_DETAIL");
      setActivateCallBack(1, "miui.intent.action.MX_STATUS_SLOT_2_DETAIL");
    }
    handleIntent();
    if (Build.IS_CM_CUSTOMIZATION_TEST) {
      registerSimRelatedListener();
    }
  }
  
  protected void onDestroy()
  {
    if (Build.IS_CM_CUSTOMIZATION_TEST) {
      unRegisterSimRelatedListener();
    }
    super.onDestroy();
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    setIntent(paramIntent);
    handleIntent();
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default: 
      return false;
    }
    finish();
    return true;
  }
  
  protected void onPause()
  {
    if (hasMxSettings())
    {
      unregisterReceiver(mReceiver);
      ActivateStatusReceiver.removeListener(this);
    }
    super.onPause();
  }
  
  public boolean onPreferenceChange(Preference paramPreference, Object paramObject)
  {
    if (paramPreference == mDateTypePref) {
      fillDateTypeSummary((String)paramObject);
    }
    do
    {
      do
      {
        return true;
        if (paramPreference == mNotificationRingTonePref)
        {
          fillRingToneSummary((String)paramObject);
          return true;
        }
        if (paramPreference == mNotificationBodyPref)
        {
          boolean bool = ((Boolean)paramObject).booleanValue();
          paramPreference = getContentResolver();
          if (bool) {}
          for (int i = 1;; i = 0)
          {
            Settings.System.putInt(paramPreference, "pref_key_enable_notification_body", i);
            return true;
          }
        }
        if ((paramPreference == mShowTemplatePref) || (paramPreference == mShowTemplateInPref))
        {
          if (((Boolean)paramObject).booleanValue()) {
            UnderstandLoader.update();
          }
          for (;;)
          {
            mAllowNetworkAccessPref.setChecked(((Boolean)paramObject).booleanValue());
            mAllowNetworkAccessPref.setEnabled(((Boolean)paramObject).booleanValue());
            MiStatSdkHelper.recordStringPropertyEvent("network_recommend", "open_template_pref", String.valueOf((Boolean)paramObject));
            return true;
            UnderstandLoader.destroy();
          }
        }
        if (paramPreference != mSmsValidityPeriodPref) {
          break;
        }
      } while (!Build.IS_CM_CUSTOMIZATION_TEST);
      handleSmsValidityPeriod(paramPreference, (String)paramObject);
      return true;
    } while (paramPreference != mAllowNetworkAccessPref);
    MiStatSdkHelper.recordStringPropertyEvent("network_recommend", "open_network_access_pref", String.valueOf((Boolean)paramObject));
    return true;
  }
  
  public boolean onPreferenceClick(Preference paramPreference)
  {
    if (paramPreference == mMxEnabledPref)
    {
      int i = MSimUtils.getInsertedSlotId();
      if (i != -1) {
        handleMxSwitchPref(mMxEnabledPref, i);
      }
    }
    do
    {
      do
      {
        do
        {
          return false;
          if (paramPreference == mMx1EnabledPref)
          {
            handleMxSwitchPref(mMx1EnabledPref, 0);
            return true;
          }
          if (paramPreference == mMx2EnabledPref)
          {
            handleMxSwitchPref(mMx2EnabledPref, 1);
            return true;
          }
          if (paramPreference == mCollapseSPMessagePref) {
            return ServiceCategoryUpdateAsyncTask.startUpdateAsyncTask(this, false, this);
          }
          if ((paramPreference != mDeliveryRptPref) && (paramPreference != mSendDeliveryRptPref)) {
            break;
          }
        } while (!Build.IS_CM_CUSTOMIZATION_TEST);
        handleDeliveryRptRelatedPrefsClick(paramPreference);
        return true;
        if (paramPreference != mSmsValidityPeriodPref) {
          break;
        }
      } while (!Build.IS_CM_CUSTOMIZATION_TEST);
      handleSmsValidityPeriod(paramPreference, null);
      return true;
      if (paramPreference == mDownLoadWildMsgPref)
      {
        handleDownloadWildMsgPref();
        return true;
      }
    } while (paramPreference != mMessageRecoveryPref);
    showMessageRecoveryIntroduction();
    return true;
  }
  
  protected void onResume()
  {
    super.onResume();
    if (hasMxSettings())
    {
      registerReceiver(mReceiver, mBroadcastFilter);
      ActivateStatusReceiver.addListener(this);
    }
    mNotificationBodyPref.setChecked(MessageUtils.getPrefNotificationBodyEnabled(this));
    updateMxPrefStatus();
    if (MessageUtils.isAccessControlled(this))
    {
      mNotificationBodyPref.setEnabled(false);
      mNotificationBodyPref.setSummary(2131362106);
      return;
    }
    mNotificationBodyPref.setEnabled(true);
    mNotificationBodyPref.setSummary(2131362105);
  }
  
  public void onStop()
  {
    super.onStop();
    ServiceCategoryUpdateAsyncTask.unRegisterListener(this);
    if (mWildMsgDialog != null) {
      mWildMsgDialog.cancel();
    }
    mWildMsgDialog = null;
  }
  
  public void onUpdateSuccess() {}
  
  private class MxStatusReceiver
    extends BroadcastReceiver
  {
    private MxStatusReceiver() {}
    
    private void updateMxPrefStatusSummary(int paramInt1, int paramInt2)
    {
      if ((MessagingPreferenceActivity.this.hasMxSettings()) && (paramInt1 != 0))
      {
        if (mMxEnabledPref == null) {
          break label36;
        }
        mMxEnabledPref.setSummary(paramInt1);
      }
      label36:
      do
      {
        return;
        if ((mMx1EnabledPref != null) && (paramInt2 == 0))
        {
          mMx1EnabledPref.setSummary(paramInt1);
          return;
        }
      } while ((mMx2EnabledPref == null) || (1 != paramInt2));
      mMx2EnabledPref.setSummary(paramInt1);
    }
    
    int getDsptResId(int paramInt)
    {
      switch (paramInt)
      {
      default: 
        return 0;
      case 2: 
      case 5: 
      case 8: 
      case 14: 
      case 16: 
      case 18: 
      case 20: 
      case 22: 
      case 23: 
      case 25: 
        return 2131362389;
      case 3: 
      case 15: 
        return 2131362393;
      case 4: 
      case 6: 
      case 7: 
      case 11: 
      case 12: 
      case 13: 
      case 17: 
      case 19: 
      case 21: 
      case 24: 
        return 2131362388;
      case 9: 
        return 2131362392;
      }
      return 2131362387;
    }
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      int i = paramIntent.getIntExtra("message", 1);
      if ("miui.intent.action.MX_STATUS_SLOT_1_DETAIL".equals(paramIntent.getAction())) {
        updateMxPrefStatusSummary(getDsptResId(i), 0);
      }
      for (;;)
      {
        MessagingPreferenceActivity.this.updateMxPrefStatus();
        return;
        if ("miui.intent.action.MX_STATUS_SLOT_2_DETAIL".equals(paramIntent.getAction())) {
          updateMxPrefStatusSummary(getDsptResId(i), 1);
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessagingPreferenceActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */