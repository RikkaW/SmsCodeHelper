package com.android.mms.ui;

import aaa;
import aab;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.provider.Telephony.Sms;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import com.android.internal.telephony.SmsApplication;
import com.android.internal.telephony.SmsApplication.SmsApplicationData;
import com.android.mms.MmsApp;
import com.android.mms.quickreply.EditQuickReplyActivity;
import com.meizu.common.preference.SwitchPreference;
import fz;
import ga;
import java.util.Collection;
import java.util.Iterator;
import wd;
import ws;
import wt;
import wu;
import zv;

public class MessagingPreferenceActivity
  extends fz
  implements Preference.OnPreferenceChangeListener
{
  private SwitchPreference a;
  private Preference b;
  private Preference c;
  private Preference d;
  private Preference e;
  private Preference f;
  private Preference g;
  private Preference h;
  private Preference i;
  private ListPreference j;
  private Preference k;
  private ListView l;
  private boolean m = false;
  
  public static void a(int paramInt, Context paramContext)
  {
    Intent localIntent = new Intent("com.android.mms.intent.action.SIM_SELCTION_PREF");
    localIntent.putExtra("sim_selection_page_type", paramInt);
    paramContext.startActivity(localIntent);
  }
  
  public static void a(boolean paramBoolean, Context paramContext)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    paramContext.putBoolean("pref_key_enable_notifications", paramBoolean);
    paramContext.apply();
  }
  
  public static boolean a(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("pref_key_enable_notifications", true);
  }
  
  private void b()
  {
    boolean bool2 = true;
    label46:
    label87:
    Object localObject;
    int n;
    if (zv.e > 1)
    {
      addPreferencesFromResource(2131230724);
      d = findPreference("pref_key_mms_retrieval_during_roaming");
      if (zv.e <= 1) {
        break label377;
      }
      d.setSummary(zv.d(getApplicationContext()));
      c = findPreference("pref_key_mms_auto_retrieval");
      k = findPreference("pref_key_more");
      if ((!MmsApp.a) && (!MmsApp.b)) {
        break label394;
      }
      d.setDependency("pref_key_mms_auto_retrieval");
      e = findPreference("pref_key_manage_flyme_message");
      if ((!wd.j()) || (MmsApp.f) || (MmsApp.a) || (MmsApp.d)) {
        getPreferenceScreen().removePreference(e);
      }
      f = findPreference("pref_key_manage_messages_spam");
      if (MmsApp.d) {
        getPreferenceScreen().removePreference(f);
      }
      h = findPreference("pref_key_manage_sim_messages");
      localObject = h;
      if (!MmsApp.b) {
        break label421;
      }
      n = 2131493780;
      label186:
      ((Preference)localObject).setTitle(n);
      a = ((SwitchPreference)findPreference("pref_key_enable_notifications"));
      b = findPreference("pref_key_sms_delivery_reports");
      i = findPreference("pref_key_mms_center_number");
      d();
      if (zv.e == 0)
      {
        getPreferenceScreen().removePreference(h);
        b.setEnabled(false);
        i.setEnabled(false);
      }
      g = findPreference("pref_key_edit_quick_reply");
      j = ((ListPreference)findPreference("pre_key_change_default_message_app"));
      j.setOnPreferenceChangeListener(this);
      if (((b instanceof SwitchPreference)) && (!PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).contains("pref_key_sms_delivery_reports")))
      {
        localObject = (SwitchPreference)b;
        if (MmsApp.d) {
          break label427;
        }
        bool1 = true;
        label338:
        ((SwitchPreference)localObject).setChecked(bool1);
        localObject = b;
        if (MmsApp.d) {
          break label432;
        }
      }
    }
    label377:
    label394:
    label421:
    label427:
    label432:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      ((Preference)localObject).setDefaultValue(Boolean.valueOf(bool1));
      return;
      addPreferencesFromResource(2131230723);
      break;
      if (zv.e != 0) {
        break label46;
      }
      d.setEnabled(false);
      break label46;
      getPreferenceScreen().removePreference(c);
      getPreferenceScreen().removePreference(k);
      break label87;
      n = 2131493054;
      break label186;
      bool1 = false;
      break label338;
    }
  }
  
  public static boolean b(Context paramContext)
  {
    return false;
  }
  
  private void c()
  {
    a.setChecked(a(this));
    if (zv.e > 1) {
      d.setSummary(zv.d(getApplicationContext()));
    }
  }
  
  public static boolean c(Context paramContext)
  {
    return true;
  }
  
  private void d()
  {
    if ((MmsApp.a) || (!wd.j()) || (MmsApp.f)) {
      return;
    }
    if (wd.j(this))
    {
      e.setSummary(2131493304);
      return;
    }
    e.setSummary(2131493303);
  }
  
  private void e()
  {
    Object localObject1 = SmsApplication.getApplicationCollection(this);
    int n = ((Collection)localObject1).size();
    if (n > 1)
    {
      CharSequence[] arrayOfCharSequence1 = new CharSequence[n];
      CharSequence[] arrayOfCharSequence2 = new CharSequence[n];
      localObject1 = ((Collection)localObject1).iterator();
      n = 0;
      if (((Iterator)localObject1).hasNext())
      {
        Object localObject2 = (SmsApplication.SmsApplicationData)((Iterator)localObject1).next();
        arrayOfCharSequence2[n] = mPackageName;
        if (!mPackageName.equals("com.android.mms")) {
          arrayOfCharSequence1[n] = mApplicationName;
        }
        for (;;)
        {
          n += 1;
          break;
          arrayOfCharSequence1[n] = getString(2131493664);
          if (n != 0)
          {
            localObject2 = arrayOfCharSequence1[0];
            CharSequence localCharSequence = arrayOfCharSequence2[0];
            arrayOfCharSequence1[0] = arrayOfCharSequence1[n];
            arrayOfCharSequence2[0] = arrayOfCharSequence2[n];
            arrayOfCharSequence1[n] = localObject2;
            arrayOfCharSequence2[n] = localCharSequence;
          }
        }
      }
      j.setEntries(arrayOfCharSequence1);
      j.setEntryValues(arrayOfCharSequence2);
      getPreferenceScreen().addPreference(j);
      f();
      return;
    }
    getPreferenceScreen().removePreference(j);
  }
  
  private void f()
  {
    String str = Telephony.Sms.getDefaultSmsPackage(this);
    CharSequence[] arrayOfCharSequence;
    int n;
    if (!TextUtils.isEmpty(str))
    {
      arrayOfCharSequence = j.getEntryValues();
      n = 0;
    }
    for (;;)
    {
      if (n < arrayOfCharSequence.length)
      {
        if (str.contentEquals(arrayOfCharSequence[n]))
        {
          j.setValueIndex(n);
          j.setSummary(j.getEntries()[n]);
        }
      }
      else {
        return;
      }
      n += 1;
    }
  }
  
  private void g()
  {
    m = getIntent().getBooleanExtra("isSetDefaultApp", false);
    if (m)
    {
      l = getListView();
      l.postDelayed(new ws(this), 50L);
      m = false;
    }
  }
  
  private void h()
  {
    int n = i();
    l.postDelayed(new wt(this, n), 50L);
    l.postDelayed(new wu(this, n), 2000L);
  }
  
  private int i()
  {
    if ((MmsApp.a) || (MmsApp.b)) {
      return getListView().getChildCount() - 2;
    }
    return getListView().getChildCount() - 1;
  }
  
  public void finish()
  {
    super.finish();
    aaa.b(this);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    g();
    b();
    getListView().setScrollBarStyle(0);
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
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
  
  public boolean onPreferenceChange(Preference paramPreference, Object paramObject)
  {
    if ((paramPreference == j) && (paramObject != null))
    {
      startActivityForResult(new Intent(ga.a(paramObject.toString(), getResources().getString(2131493639))), 0);
      aab.a(this, "default_message_app_changed", "MessagingPreferenceActivity", "default_message_app_name", paramObject.toString());
    }
    return true;
  }
  
  public boolean onPreferenceTreeClick(PreferenceScreen paramPreferenceScreen, Preference paramPreference)
  {
    Intent localIntent;
    if (zv.e > 1) {
      if (paramPreference == b)
      {
        a(0, this);
        if (paramPreference != f) {
          break label292;
        }
        localIntent = new Intent("android.intent.action.BLOCK_SERVICE_MAIN_ACTIVITY");
      }
    }
    for (;;)
    {
      try
      {
        startActivity(localIntent);
        aaa.c(this);
        return super.onPreferenceTreeClick(paramPreferenceScreen, paramPreference);
        if (paramPreference == d)
        {
          a(1, this);
          break;
        }
        if (paramPreference == h)
        {
          a(2, this);
          break;
        }
        if (paramPreference == i)
        {
          a(3, this);
          break;
        }
        if (paramPreference != c) {
          break;
        }
        a(4, this);
        break;
        if (paramPreference == h)
        {
          localIntent = new Intent(this, ManageSimMessages.class);
          localIntent.putExtra("sim_id", zv.i());
          startActivity(localIntent);
          aab.a(this, "manage_sim_message_click", "MessagingPreferenceActivity");
          break;
        }
        if (paramPreference == i)
        {
          aab.a(this, "manage_sms_center_click", "MessagingPreferenceActivity");
          break;
        }
        if (paramPreference == b)
        {
          if (!(b instanceof SwitchPreference)) {
            break;
          }
          aab.a(this, "enable_delivery_report_click", "MessagingPreferenceActivity", ((SwitchPreference)b).isChecked());
          break;
        }
        if ((paramPreference != d) || (!(d instanceof SwitchPreference))) {
          break;
        }
        aab.a(this, "enable_mms_download_when_roaming_click", "MessagingPreferenceActivity", ((SwitchPreference)d).isChecked());
      }
      catch (Exception localException)
      {
        Log.d("MessagingPreferenceActivity", "Unable to start BlockServiceMainActivity", new Throwable());
        continue;
      }
      label292:
      if (paramPreference == a)
      {
        aab.a(this, "enable_notification_click", "MessagingPreferenceActivity", a.isChecked());
      }
      else if (paramPreference == g)
      {
        startActivity(new Intent(this, EditQuickReplyActivity.class));
        aab.a(this, "edit_quick_reply_click", "MessagingPreferenceActivity");
      }
      else if (paramPreference == e)
      {
        if (wd.c(getContentResolver()))
        {
          wd.m(this);
          return true;
        }
        wd.b(this);
      }
      else if (paramPreference == k)
      {
        startActivity(new Intent(this, OtherPreferenceActivity.class));
      }
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    c();
    d();
    e();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessagingPreferenceActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */