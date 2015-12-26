import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.LayoutParams;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import com.android.mms.location.BaseGetLocationView;
import java.util.ArrayList;

public class kf
  extends fx
  implements View.OnTouchListener, AdapterView.OnItemClickListener
{
  public BaseGetLocationView a;
  protected EditText b;
  protected ImageView c;
  public kt d;
  protected boolean e;
  protected boolean f;
  protected boolean g = true;
  protected kf.a h;
  protected kf.b i;
  protected boolean j = true;
  protected ListView k;
  protected kv l;
  private View m;
  private ListView n;
  private View o;
  private int p = -1;
  private DataSetObserver q;
  private View r;
  private ActionBar s;
  private boolean t = false;
  private MenuItem u;
  private View.OnKeyListener v = new ki(this);
  
  private void a(View paramView)
  {
    InputMethodManager localInputMethodManager = (InputMethodManager)getSystemService("input_method");
    if (!((Boolean)aau.b(InputMethodManager.class, localInputMethodManager, "isSoftInputShown")).booleanValue()) {
      localInputMethodManager.showSoftInput(paramView, 0);
    }
  }
  
  public static final void a(boolean paramBoolean, int paramInt, String paramString1, String paramString2)
  {
    if (!paramBoolean) {}
  }
  
  private void a(boolean paramBoolean, ListView paramListView)
  {
    if (paramBoolean)
    {
      paramListView = new md(paramListView);
      int i1 = getResources().getDimensionPixelOffset(2131559816);
      paramListView.a(new int[] { i1, i1 });
      paramListView.a();
    }
  }
  
  private void c(int paramInt)
  {
    s.setDisplayOptions(paramInt);
  }
  
  private boolean s()
  {
    return (l != null) && (l.getCount() > 0);
  }
  
  private void t()
  {
    if (m == null)
    {
      m = LayoutInflater.from(this).inflate(2130968710, null);
      m.setPadding(getResources().getDimensionPixelOffset(2131558610), 0, getResources().getDimensionPixelOffset(2131558611), 0);
      b = ((EditText)m.findViewById(2131886209));
      b.setOnKeyListener(v);
      b.addTextChangedListener(new kl(this));
      c = ((ImageView)m.findViewById(2131886210));
      c.setOnClickListener(new km(this));
      n = ((ListView)findViewById(2131886580));
      d = new kt(this, 2130968676, new ArrayList());
      n.setAdapter(d);
      n.setOnItemClickListener(this);
      n.setOnTouchListener(this);
      a(true, n);
      a.b(d);
      o = findViewById(2131886577);
    }
    n.setVisibility(0);
    o.setVisibility(8);
    s.setCustomView(m, new ActionBar.LayoutParams(-1, -1, 19));
    c(20);
    u.setVisible(false);
    t = true;
    b.requestFocus();
    a(b);
  }
  
  private void u()
  {
    r.setEnabled(r());
  }
  
  private void v()
  {
    i();
    c(12);
    t = false;
    u.setVisible(true);
    o.setVisibility(0);
    n.setVisibility(8);
  }
  
  public void a(int paramInt)
  {
    wd.a(paramInt, this, 0, 1, true, 0);
  }
  
  public void b()
  {
    a = ((BaseGetLocationView)findViewById(2131886578));
    a.setActivity(this);
    View localView = findViewById(2131886584);
    wd.a(localView);
    localView.setOnClickListener(new kg(this));
    r = findViewById(2131886585);
    wd.a(r);
    r.setOnClickListener(new kh(this));
    u();
  }
  
  public void b(int paramInt)
  {
    p = paramInt;
  }
  
  public boolean c()
  {
    return g;
  }
  
  protected void d()
  {
    e = getIntent().getBooleanExtra("send_as_mms", false);
    f = getIntent().getBooleanExtra("send_out_directly", false);
    Log.i("location/BaseGetLocationActivity", "getIntentData mSendAsMms = " + e + ", mSendDirectly = " + f);
  }
  
  public final boolean e()
  {
    return e;
  }
  
  public final boolean f()
  {
    return f;
  }
  
  protected void g()
  {
    s = getSupportActionBar();
    c(12);
    aaa.a(s, this);
  }
  
  public void h()
  {
    i();
  }
  
  public void i()
  {
    InputMethodManager localInputMethodManager = (InputMethodManager)getSystemService("input_method");
    if (((Boolean)aau.b(InputMethodManager.class, localInputMethodManager, "isSoftInputShown")).booleanValue()) {
      localInputMethodManager.hideSoftInputFromWindow(b.getWindowToken(), 0);
    }
  }
  
  protected boolean j()
  {
    return false;
  }
  
  protected void k()
  {
    l();
    m();
  }
  
  protected void l()
  {
    if (h == null) {
      h = new kf.a(null);
    }
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
    registerReceiver(h, localIntentFilter);
  }
  
  protected void m()
  {
    if (i == null) {
      i = new kf.b(null);
    }
    zv.a(i);
  }
  
  protected void n()
  {
    if (h != null) {
      unregisterReceiver(h);
    }
    if (i != null) {
      zv.b(i);
    }
  }
  
  public void o()
  {
    k = ((ListView)findViewById(2131886579));
    a(true, k);
    q = new kj(this);
    l = new kv(this, a.getPoiList());
    k.setAdapter(l);
    k.setOnItemClickListener(new kk(this));
  }
  
  public void onBackPressed()
  {
    if (t)
    {
      v();
      return;
    }
    super.onBackPressed();
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    g();
    getWindow().setSoftInputMode(2);
    d();
    wd.a(this);
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131951624, paramMenu);
    u = paramMenu.findItem(2131886800);
    return true;
  }
  
  public void onDestroy()
  {
    Log.i("location/BaseGetLocationActivity", "BaseGetLocationActivity onDestroy");
    a.setHasDestory(true);
    if (a != null)
    {
      a.f();
      a = null;
    }
    if (d != null) {
      d = null;
    }
    if (l != null)
    {
      l.clear();
      l = null;
    }
    super.onDestroy();
  }
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    i();
    if (j)
    {
      a.a(13);
      j = false;
    }
    a.b(paramInt);
    a(true, 4, "location/BaseGetLocationActivity", "onItemClick position = " + paramInt + ", city = " + ((kr)d.getItem(paramInt)).c());
    v();
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    }
    for (;;)
    {
      return true;
      t();
      continue;
      onBackPressed();
    }
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    super.onPrepareOptionsMenu(paramMenu);
    return true;
  }
  
  protected void onStart()
  {
    super.onStart();
    k();
    if (l != null) {
      l.registerDataSetObserver(q);
    }
  }
  
  protected void onStop()
  {
    super.onStop();
    n();
    if (l != null) {
      l.unregisterDataSetObserver(q);
    }
  }
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    if (paramView == n) {
      i();
    }
    return false;
  }
  
  public kv p()
  {
    return l;
  }
  
  protected boolean q()
  {
    return (e) || (zv.e > 0);
  }
  
  public boolean r()
  {
    return (g) && (q()) && (s());
  }
  
  class a
    extends BroadcastReceiver
  {
    private a() {}
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      boolean bool = paramIntent.getBooleanExtra("noConnectivity", false);
      paramContext = (NetworkInfo)paramIntent.getParcelableExtra("networkInfo");
      Log.d("location/BaseGetLocationActivity", "onReceive -> networkInfo : " + paramContext);
      if ((paramContext == null) || ((paramContext.getType() != 1) && (paramContext.getType() != 0))) {}
      for (;;)
      {
        return;
        if ((!bool) && (paramContext.isConnected())) {}
        for (bool = true; (!j()) && (bool != g); bool = false)
        {
          g = bool;
          if (!g) {
            a.a(2131493349, "onReceive");
          }
          kf.a(kf.this);
          a.a(g);
          a.b(g);
          return;
        }
      }
    }
  }
  
  class b
    implements zv.a
  {
    private b() {}
    
    public void a(int paramInt1, int paramInt2)
    {
      Log.d("location/BaseGetLocationActivity", "onCountChanged CustomeUseableSimCountChangedListener CURRENT_USEABLE_SIM_COUNT = " + zv.e);
      kf.a(kf.this);
    }
  }
}

/* Location:
 * Qualified Name:     kf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */