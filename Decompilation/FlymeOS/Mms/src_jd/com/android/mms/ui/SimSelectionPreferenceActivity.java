package com.android.mms.ui;

import aab;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.util.Log;
import android.view.MenuItem;
import com.android.mms.MmsApp;
import com.meizu.common.preference.SwitchPreference;
import fz;
import zv;
import zv.a;

public class SimSelectionPreferenceActivity
  extends fz
{
  private static final boolean h;
  private int a = -1;
  private PreferenceScreen b;
  private a c;
  private boolean d = false;
  private boolean e = false;
  private Preference f;
  private Preference g;
  
  static
  {
    if (!MmsApp.d) {}
    for (boolean bool = true;; bool = false)
    {
      h = bool;
      return;
    }
  }
  
  private Preference a(int paramInt, String paramString1, String paramString2, boolean paramBoolean)
  {
    switch (paramInt)
    {
    default: 
      return null;
    case 0: 
      localObject = new SwitchPreference(this, null, 2130772029);
      ((Preference)localObject).setKey(paramString1);
      ((Preference)localObject).setTitle(paramString2);
      ((Preference)localObject).setEnabled(paramBoolean);
      if ((paramString1.equals("pref_key_sim1_auto_download_switch")) || (paramString1.equals("pref_key_sim2_auto_download_switch"))) {
        ((Preference)localObject).setDefaultValue(Boolean.valueOf(true));
      }
      for (;;)
      {
        b.addPreference((Preference)localObject);
        return (Preference)localObject;
        if ((paramString1.equals("pref_key_sim1_delivery_reports")) || (paramString1.equals("pref_key_sim2_delivery_reports"))) {
          ((Preference)localObject).setDefaultValue(Boolean.valueOf(h));
        }
      }
    case 1: 
      localObject = getPreferenceManager().createPreferenceScreen(this);
      ((Preference)localObject).setWidgetLayoutResource(2130968795);
      ((Preference)localObject).setKey(paramString1);
      ((Preference)localObject).setTitle(paramString2);
      ((Preference)localObject).setEnabled(paramBoolean);
      b.addPreference((Preference)localObject);
      return (Preference)localObject;
    }
    Object localObject = new SmsCenterNumberEditTextPreference(this, null);
    ((Preference)localObject).setKey(paramString1);
    ((Preference)localObject).setTitle(paramString2);
    ((Preference)localObject).setEnabled(paramBoolean);
    ((Preference)localObject).setWidgetLayoutResource(2130968790);
    b.addPreference((Preference)localObject);
    return (Preference)localObject;
  }
  
  public static boolean a(int paramInt)
  {
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(MmsApp.c());
    if (zv.e > 1)
    {
      if (paramInt == 0) {
        return localSharedPreferences.getBoolean("pref_key_sim1_delivery_reports", h);
      }
      if (paramInt == 1) {
        return localSharedPreferences.getBoolean("pref_key_sim2_delivery_reports", h);
      }
    }
    else
    {
      return localSharedPreferences.getBoolean("pref_key_sms_delivery_reports", h);
    }
    return h;
  }
  
  private void b()
  {
    c();
    f.setTitle(zv.a(getBaseContext(), 0));
    f.setEnabled(d);
    g.setTitle(zv.a(getBaseContext(), 1));
    g.setEnabled(e);
  }
  
  public static boolean b(int paramInt)
  {
    boolean bool = false;
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(MmsApp.c());
    if (zv.e > 1)
    {
      if (paramInt == 0) {
        bool = localSharedPreferences.getBoolean("pref_key_sim1_auto_download_roaming_switch", false);
      }
      while (paramInt != 1) {
        return bool;
      }
      return localSharedPreferences.getBoolean("pref_key_sim2_auto_download_roaming_switch", false);
    }
    return localSharedPreferences.getBoolean("pref_key_mms_retrieval_during_roaming", false);
  }
  
  private void c()
  {
    d = zv.e(0);
    e = zv.e(1);
  }
  
  public static boolean c(int paramInt)
  {
    boolean bool = false;
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(MmsApp.c());
    if (zv.e > 1)
    {
      if (paramInt == 0) {
        bool = localSharedPreferences.getBoolean("pref_key_sim1_auto_download_switch", true);
      }
      while (paramInt != 1) {
        return bool;
      }
      return localSharedPreferences.getBoolean("pref_key_sim2_auto_download_switch", true);
    }
    return localSharedPreferences.getBoolean("pref_key_mms_auto_retrieval", false);
  }
  
  private a d()
  {
    if (c == null) {
      c = new a(null);
    }
    return c;
  }
  
  public static boolean d(int paramInt)
  {
    boolean bool = false;
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(MmsApp.c());
    if (zv.e > 1)
    {
      if (paramInt == 0) {
        bool = localSharedPreferences.getBoolean("pref_key_sim1_mms_read_reports", false);
      }
      while (paramInt != 1) {
        return bool;
      }
      return localSharedPreferences.getBoolean("pref_key_sim2_mms_read_reports", false);
    }
    return localSharedPreferences.getBoolean("pref_key_mms_read_reports", false);
  }
  
  private void e(int paramInt)
  {
    Log.i("SimSelectionPreferenceActivity", "initPrefByType type = " + paramInt);
    String str1 = zv.a(getBaseContext(), 0);
    String str2 = zv.a(getBaseContext(), 1);
    c();
    switch (paramInt)
    {
    default: 
      return;
    case 0: 
      f = a(0, "pref_key_sim1_delivery_reports", str1, d);
      g = a(0, "pref_key_sim2_delivery_reports", str2, e);
      setTitle(getString(2131493065));
      return;
    case 1: 
      f = a(0, "pref_key_sim1_auto_download_roaming_switch", str1, d);
      g = a(0, "pref_key_sim2_auto_download_roaming_switch", str2, e);
      setTitle(getString(2131493060));
      return;
    case 2: 
      f = a(1, "pref_key_sim1_sms", str1, d);
      g = a(1, "pref_key_sim2_sms", str2, e);
      if (MmsApp.b)
      {
        setTitle(getString(2131493780));
        return;
      }
      setTitle(getString(2131493054));
      return;
    case 3: 
      f = a(2, "pref_key_sim1_center", str1, d);
      g = a(2, "pref_key_sim2_center", str2, e);
      setTitle(getString(2131493338));
      return;
    case 4: 
      f = a(0, "pref_key_sim1_auto_download_switch", str1, d);
      g = a(0, "pref_key_sim2_auto_download_switch", str2, e);
      setTitle(getString(2131493055));
      return;
    case 5: 
      f = a(0, "pref_key_sim1_mms_read_reports", str1, d);
      g = a(0, "pref_key_sim2_mms_read_reports", str2, e);
      setTitle(getString(2131493059));
      return;
    }
    f = a(0, "pref_key_sim1_allow_return_read_reports", str1, d);
    g = a(0, "pref_key_sim2_allow_return_read_reports", str2, e);
    setTitle(getString(2131493824));
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    addPreferencesFromResource(2131230726);
    int i = getIntent().getIntExtra("sim_selection_page_type", -1);
    b = getPreferenceScreen();
    a = i;
    e(i);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default: 
      return false;
    }
    onBackPressed();
    return true;
  }
  
  public boolean onPreferenceTreeClick(PreferenceScreen paramPreferenceScreen, Preference paramPreference)
  {
    Intent localIntent;
    if (a == 2)
    {
      aab.a(this, "manage_sim_message_click", "MessagingPreferenceActivity");
      localIntent = new Intent(this, ManageSimMessages.class);
      if (paramPreference == f)
      {
        localIntent.putExtra("sim_id", 0);
        startActivityIfNeeded(localIntent, -1);
      }
    }
    for (;;)
    {
      return super.onPreferenceTreeClick(paramPreferenceScreen, paramPreference);
      if (paramPreference != g) {
        break;
      }
      localIntent.putExtra("sim_id", 1);
      break;
      if (a == 0)
      {
        if ((paramPreference instanceof SwitchPreference)) {
          aab.a(this, "enable_delivery_report_click", "SimSelectionPreferenceActivity", ((SwitchPreference)paramPreference).isChecked());
        }
      }
      else if (a == 1)
      {
        if ((paramPreference instanceof SwitchPreference)) {
          aab.a(this, "enable_mms_download_when_roaming_click", "SimSelectionPreferenceActivity", ((SwitchPreference)paramPreference).isChecked());
        }
      }
      else if (a == 3) {
        aab.a(this, "manage_sms_center_click", "MessagingPreferenceActivity");
      }
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    b();
  }
  
  protected void onStart()
  {
    super.onStart();
    zv.a(d());
  }
  
  protected void onStop()
  {
    super.onStop();
    if (c != null) {
      zv.b(c);
    }
  }
  
  class a
    implements zv.a
  {
    private a() {}
    
    public void a(int paramInt1, int paramInt2)
    {
      Log.d("SimSelectionPreferenceActivity", "onCountChanged CustomeUseableSimCountChangedListener CURRENT_USEABLE_SIM_COUNT = " + zv.e);
      SimSelectionPreferenceActivity.a(SimSelectionPreferenceActivity.this);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SimSelectionPreferenceActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */