package com.android.mms.ui;

import aaa;
import aab;
import aai;
import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.provider.Telephony.Sms;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.Window;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.mms.MmsApp;
import com.android.mms.fragmentstyle.ConversationListFragment;
import com.android.mms.fragmentstyle.ConversationListFragment.c;
import com.android.mms.fragmentstyle.CustomeViewPager;
import com.android.mms.fragmentstyle.FavoriteActivity;
import com.meizu.common.app.SlideNotice;
import fl;
import fm;
import ga;
import gj;
import ir;
import ir.a;
import ir.b;
import ty;
import tz;
import ua;
import ub;
import uc;
import ud;
import ue;
import wd;

public class ConversationList
  extends fm
  implements ir.a
{
  public static final boolean b;
  private static int v;
  protected ir a;
  protected ConversationListFragment c;
  protected ConversationListFragment.c d = new a(null);
  public AbsListView.OnScrollListener e = new ud(this);
  private boolean f = false;
  private boolean g;
  private SlideNotice h;
  private boolean i = false;
  private MenuItem j;
  private MenuItem k;
  private Bundle l;
  private Handler m;
  private Runnable n = new ty(this);
  private boolean o = false;
  private CustomeViewPager p;
  private b q;
  private final c r = new c(null);
  private ConversationListFragment s;
  private View t;
  private View u;
  private Bundle w;
  
  static
  {
    if (!MmsApp.d) {}
    for (boolean bool = true;; bool = false)
    {
      b = bool;
      v = 0;
      return;
    }
  }
  
  private void b(String paramString)
  {
    Log.d("ConversationList", "onTextChanged(), beginSearch(), queryWord = " + paramString + ", isWatingSubPage = " + f);
    if (f) {
      return;
    }
    Intent localIntent = new Intent();
    localIntent.setClass(this, MeizuSearchActivity.class);
    localIntent.putExtra("search_from", 1);
    localIntent.putExtra("query", paramString);
    startActivityIfNeeded(localIntent, -1);
    aaa.f(this);
    f = true;
  }
  
  private void b(boolean paramBoolean)
  {
    Log.d("ConversationList", "onstop(), saveActionBarOptions(), isWatingSubPage = " + f);
    if (!f) {
      return;
    }
    a(ir.b.a, paramBoolean);
    f = false;
  }
  
  private void i()
  {
    a = new ir(this, this, getSupportActionBar(), b);
  }
  
  private void j()
  {
    a.d();
    f = false;
  }
  
  private void k()
  {
    Intent localIntent = ComposeMessageActivity.a(this, 0L);
    localIntent.putExtra("extra_from_create_new_btn", true);
    startActivity(localIntent);
    aaa.d(this);
  }
  
  private void l()
  {
    Intent localIntent = new Intent(this, MessagingPreferenceActivity.class);
    if (i)
    {
      localIntent.putExtra("isSetDefaultApp", true);
      i = false;
    }
    startActivityIfNeeded(localIntent, -1);
    aaa.a(this);
    f = true;
    Log.d("ConversationList", "startMmsSettingActivity, isWatingSubPage = " + f);
  }
  
  private void m()
  {
    boolean bool2 = true;
    boolean bool3 = wd.c(getContentResolver());
    boolean bool4 = a.c();
    MenuItem localMenuItem;
    if (j != null)
    {
      localMenuItem = j;
      if ((!bool4) && (!bool3))
      {
        bool1 = true;
        localMenuItem.setVisible(bool1);
      }
    }
    else if (k != null)
    {
      localMenuItem = k;
      if ((bool4) || (bool3)) {
        break label91;
      }
    }
    label91:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      localMenuItem.setVisible(bool1);
      return;
      bool1 = false;
      break;
    }
  }
  
  private void n()
  {
    PackageManager localPackageManager = getPackageManager();
    String str = Telephony.Sms.getDefaultSmsPackage(this);
    try
    {
      localObject1 = localPackageManager.getApplicationIcon(str);
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException1)
    {
      for (;;)
      {
        Object localObject1;
        Object localObject4;
        label104:
        Object localObject2 = null;
        Object localObject3 = localObject2;
        localObject2 = null;
        continue;
        t.setOnClickListener(new uc(this));
      }
    }
    try
    {
      localObject4 = localPackageManager.getApplicationInfo(str, 0);
      localObject3 = localObject1;
      localObject1 = localObject4;
      localObject4 = localPackageManager.getLaunchIntentForPackage(str);
      if ((localObject3 == null) || (localObject1 == null) || (localObject4 != null) || (!packageName.equals("com.meizu.flyme.easylauncher"))) {
        break label225;
      }
      localObject4 = new Intent("android.intent.action.MAIN");
      ((Intent)localObject4).addCategory("android.intent.category.LAUNCHER");
      ((Intent)localObject4).setComponent(new ComponentName(packageName, "com.android.launcher3.Launcher"));
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException2)
    {
      break label196;
      break label104;
    }
    if ((localObject3 != null) && (localObject1 != null) && (localObject4 != null))
    {
      ((ImageView)t.findViewById(2131886220)).setImageDrawable((Drawable)localObject3);
      ((TextView)t.findViewById(2131886221)).setText(getResources().getString(2131493236, new Object[] { ((ApplicationInfo)localObject1).loadLabel(localPackageManager) }));
      t.setOnClickListener(new ub(this, (Intent)localObject4));
      o();
      return;
    }
  }
  
  private void o()
  {
    if ((!g) && (!MmsApp.k))
    {
      new AlertDialog.Builder(this).setTitle(2131493655).setMessage(2131493654).setPositiveButton(2131493113, new ue(this)).setNegativeButton(2131493022, null).show();
      MmsApp.k = true;
    }
  }
  
  private void p()
  {
    boolean bool2 = ga.C();
    StringBuilder localStringBuilder = new StringBuilder().append("refreshCreateNew mCreateNewArea=");
    if (u == null) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      Log.v("ConversationList", bool1 + ", smsEnabled=" + bool2);
      if (u != null)
      {
        if (!bool2) {
          break;
        }
        u.setVisibility(0);
      }
      return;
    }
    u.setVisibility(4);
  }
  
  public void a(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return;
    case 0: 
      c.c();
      c.d();
      return;
    }
    s.c();
    s.d();
  }
  
  public void a(Bundle paramBundle)
  {
    w = paramBundle;
  }
  
  public void a(ir.b paramb, boolean paramBoolean)
  {
    a.a(paramb, paramBoolean);
  }
  
  public void a(String paramString)
  {
    b(paramString);
  }
  
  public void a(boolean paramBoolean)
  {
    boolean bool = ga.C();
    Log.v("ConversationList", "showCreateNewArea bShow=" + paramBoolean + ", smsEnabled=" + bool);
    if (!bool) {
      return;
    }
    if (paramBoolean)
    {
      gj.a(u, 240, u.getHeight() + v);
      return;
    }
    gj.b(u, 240, u.getHeight() + v);
  }
  
  public void b()
  {
    if (a == null) {}
    int i1;
    do
    {
      return;
      i1 = a.b();
    } while ((p == null) || (p.getCurrentItem() == i1));
    a(p.getCurrentItem());
    p.setCurrentItem(i1);
  }
  
  protected void c()
  {
    setContentView(2130968620);
    u = findViewById(2131886259);
    v = getResources().getDimensionPixelOffset(2131558861);
    u.setOnClickListener(new tz(this));
    d();
  }
  
  public void d()
  {
    e();
    getWindow().getDecorView().setBackgroundColor(0);
    if (b) {
      a.a();
    }
  }
  
  protected void e()
  {
    FragmentManager localFragmentManager = getFragmentManager();
    FragmentTransaction localFragmentTransaction = localFragmentManager.beginTransaction();
    a(l);
    l = null;
    int i1;
    if (b)
    {
      p = ((CustomeViewPager)findViewById(2131886257));
      p.setVisibility(0);
      q = new b();
      p.setAdapter(q);
      p.setOnPageChangeListener(r);
      c = ((ConversationListFragment)localFragmentManager.findFragmentByTag("tab-pager-person"));
      s = ((ConversationListFragment)localFragmentManager.findFragmentByTag("tab-pager-notice"));
      if (c == null)
      {
        c = new ConversationListFragment();
        s = new ConversationListFragment();
        localFragmentTransaction.add(2131886257, c, "tab-pager-person");
        localFragmentTransaction.add(2131886257, s, "tab-pager-notice");
        if (w != null)
        {
          i1 = w.getInt("ConversationListFragment.state.tabState", -1);
          if (i1 != 0) {
            break label374;
          }
          c.a(w);
          w = null;
        }
      }
      c.a(e);
      c.a(d);
      c.a("threads.type !=267");
      c.b(0);
      c.c(2131493458);
      s.a(e);
      s.a(d);
      s.a("threads.type ==267");
      s.b(1);
      s.c(2131493457);
      localFragmentTransaction.hide(c);
      localFragmentTransaction.hide(s);
      localFragmentTransaction.commitAllowingStateLoss();
      localFragmentManager.executePendingTransactions();
    }
    for (;;)
    {
      if ((c != null) && (ga.d(this)) && (wd.j()) && (!MmsApp.f)) {
        c.c(true);
      }
      return;
      label374:
      if (i1 != 1) {
        break;
      }
      s.a(w);
      break;
      if (c == null)
      {
        c = new ConversationListFragment();
        if (w != null)
        {
          c.a(w);
          w = null;
        }
      }
      c.a(d);
      c.a(null);
      c.b(0);
      c.c(2131493458);
      localFragmentTransaction.replace(16908290, c);
      localFragmentTransaction.commit();
      u.post(new ua(this));
    }
  }
  
  public void f()
  {
    if (ga.C())
    {
      aab.a = true;
      k();
      f = true;
      Log.d("ConversationList", "onOptionsItemSelected(), createNewMessage, isWatingSubPage = " + f);
    }
    for (;;)
    {
      aab.a(this, "create_new_message_click", "ConversationList");
      return;
      if (h == null) {
        h = SlideNotice.makeNotice(this, getResources().getString(2131493264), 0, 0);
      }
      h.show();
    }
  }
  
  public void g()
  {
    c.c();
  }
  
  public Handler h()
  {
    return m;
  }
  
  public void m_()
  {
    invalidateOptionsMenu();
  }
  
  public void onBackPressed()
  {
    if (!moveTaskToBack(false))
    {
      finish();
      return;
    }
    o = true;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    l = paramBundle;
    m = new Handler();
    if (MmsApp.n) {
      setTheme(2131624164);
    }
    for (;;)
    {
      i();
      c();
      if (!wd.n(this)) {
        break;
      }
      wd.a(this);
      return;
      setTheme(2131624165);
    }
    wd.b(this);
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131951619, paramMenu);
    return true;
  }
  
  protected void onDestroy()
  {
    m.removeCallbacks(n);
    super.onDestroy();
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    a(ir.b.a, true);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default: 
      return true;
    case 2131886799: 
      f();
      return true;
    case 2131886801: 
      l();
      aab.a(this, "settings_menu_click", "ConversationList");
      Log.d("ConversationList", "onOptionsItemSelected(), action_settings, isWatingSubPage = " + f);
      return true;
    case 2131886800: 
      b("");
      g();
      aab.a(this, "search_menu_click", "ConversationList");
      return true;
    case 2131886802: 
      startActivityIfNeeded(new Intent(this, FavoriteActivity.class), -1);
      aaa.a(this);
      f = true;
      aab.a(this, "favorite_menu_click", "ConversationList");
      return true;
    }
    fl.a(this);
    return true;
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    super.onPrepareOptionsMenu(paramMenu);
    boolean bool2 = a.c();
    boolean bool3 = wd.c(getContentResolver());
    j = paramMenu.findItem(2131886801);
    MenuItem localMenuItem;
    if (j != null)
    {
      localMenuItem = j;
      if ((!bool2) && (!bool3))
      {
        bool1 = true;
        localMenuItem.setVisible(bool1);
      }
    }
    else
    {
      k = paramMenu.findItem(2131886800);
      if (k != null)
      {
        localMenuItem = k;
        if ((bool2) || (bool3)) {
          break label144;
        }
      }
    }
    label144:
    for (boolean bool1 = true;; bool1 = false)
    {
      localMenuItem.setVisible(bool1);
      paramMenu = paramMenu.findItem(2131886803);
      if (paramMenu != null) {
        paramMenu.setVisible(false);
      }
      return true;
      bool1 = false;
      break;
    }
  }
  
  protected void onRestart()
  {
    super.onRestart();
    if (o) {
      o = false;
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    j();
    boolean bool = ga.C();
    if (bool != g)
    {
      g = bool;
      invalidateOptionsMenu();
    }
    if (b) {
      a.a();
    }
    aai.a(this, 1000);
    m();
    if ((g) || (ga.c(this))) {
      if (t != null) {
        t.setVisibility(8);
      }
    }
    for (;;)
    {
      p();
      return;
      if (t == null) {
        t = ((ViewStub)findViewById(2131886258)).inflate();
      }
      n();
      t.setVisibility(0);
    }
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    if (b)
    {
      if (a.b() == 0) {
        c.onSaveInstanceState(paramBundle);
      }
      while (a.b() != 1) {
        return;
      }
      s.onSaveInstanceState(paramBundle);
      return;
    }
    c.onSaveInstanceState(paramBundle);
  }
  
  public boolean onSearchRequested()
  {
    return true;
  }
  
  protected void onStop()
  {
    super.onStop();
    b(false);
  }
  
  public void onTrimMemory(int paramInt)
  {
    super.onTrimMemory(paramInt);
    if ((paramInt >= 60) && (o)) {}
  }
  
  class a
    implements ConversationListFragment.c
  {
    private a() {}
    
    public void a()
    {
      a(ir.b.a, true);
      a(false);
      if (ConversationList.c(ConversationList.this) != null) {
        ConversationList.c(ConversationList.this).setScrollEnable(false);
      }
    }
    
    public void a(int paramInt1, int paramInt2)
    {
      if (a != null) {
        a.a(paramInt1, paramInt2);
      }
    }
    
    public void a(long paramLong, String paramString)
    {
      aab.b = true;
      startActivity(ComposeMessageActivity.a(ConversationList.this, paramLong, paramString));
      ConversationList.a(ConversationList.this, true);
      Log.d("ConversationList", "beginSearch(), openThread( " + paramLong + "),  isWatingSubPage = " + ConversationList.d(ConversationList.this));
    }
    
    public void b()
    {
      a(true);
      if (ConversationList.c(ConversationList.this) != null) {
        ConversationList.c(ConversationList.this).setScrollEnable(true);
      }
    }
  }
  
  class b
    extends PagerAdapter
  {
    private final FragmentManager b = getFragmentManager();
    private FragmentTransaction c = null;
    private Fragment d;
    
    public b() {}
    
    private Fragment a(int paramInt)
    {
      switch (paramInt)
      {
      default: 
        throw new IllegalArgumentException("position: " + paramInt);
      case 0: 
        return c;
      }
      return ConversationList.b(ConversationList.this);
    }
    
    public void destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject)
    {
      if (c == null) {
        c = b.beginTransaction();
      }
      c.hide((Fragment)paramObject);
    }
    
    public void finishUpdate(ViewGroup paramViewGroup)
    {
      if (c != null)
      {
        c.commitAllowingStateLoss();
        c = null;
        b.executePendingTransactions();
      }
    }
    
    public int getCount()
    {
      return 2;
    }
    
    public int getItemPosition(Object paramObject)
    {
      if (paramObject == c) {
        return 0;
      }
      if (paramObject == ConversationList.b(ConversationList.this)) {
        return 1;
      }
      return -2;
    }
    
    public Object instantiateItem(ViewGroup paramViewGroup, int paramInt)
    {
      if (c == null) {
        c = b.beginTransaction();
      }
      paramViewGroup = a(paramInt);
      c.show(paramViewGroup);
      if (paramViewGroup == d) {}
      for (boolean bool = true;; bool = false)
      {
        paramViewGroup.setUserVisibleHint(bool);
        return paramViewGroup;
      }
    }
    
    public boolean isViewFromObject(View paramView, Object paramObject)
    {
      if (paramObject == null) {}
      while (((Fragment)paramObject).getView() != paramView) {
        return false;
      }
      return true;
    }
    
    public void restoreState(Parcelable paramParcelable, ClassLoader paramClassLoader) {}
    
    public Parcelable saveState()
    {
      return null;
    }
    
    public void setPrimaryItem(ViewGroup paramViewGroup, int paramInt, Object paramObject)
    {
      paramViewGroup = (Fragment)paramObject;
      if (d != paramViewGroup)
      {
        if (d != null) {
          d.setUserVisibleHint(false);
        }
        if (paramViewGroup != null) {
          paramViewGroup.setUserVisibleHint(true);
        }
        d = paramViewGroup;
      }
    }
    
    public void startUpdate(ViewGroup paramViewGroup) {}
  }
  
  class c
    implements ViewPager.OnPageChangeListener
  {
    private int b;
    
    private c() {}
    
    public void onPageScrollStateChanged(int paramInt)
    {
      b = paramInt;
    }
    
    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
    {
      if (a != null) {
        a.a(paramInt1, paramFloat, b);
      }
    }
    
    public void onPageSelected(int paramInt)
    {
      if (a == null) {}
      do
      {
        return;
        a.a(paramInt);
      } while (paramInt != 1);
      aab.a(ConversationList.this, "notice_conversation_show", "ConversationList");
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationList
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */