import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import com.android.mms.MmsApp;

public class ir
{
  TextWatcher a = new iw(this);
  private ir.a b;
  private final ActionBar c;
  private int d = 0;
  private final Context e;
  private EditText f;
  private ImageView g;
  private boolean h;
  private Handler i;
  private View j;
  private ActionBar.LayoutParams k;
  private boolean l = true;
  private ma m;
  private ActionBar.Tab n;
  private ActionBar.Tab o;
  private ActionBar.TabListener p = new iv(this);
  private View.OnKeyListener q = new ix(this);
  
  public ir(Context paramContext, ir.a parama, ActionBar paramActionBar, boolean paramBoolean)
  {
    e = paramContext;
    b = parama;
    c = paramActionBar;
    l = paramBoolean;
    i = new Handler();
    e();
  }
  
  private boolean a(boolean paramBoolean1, boolean paramBoolean2)
  {
    if (f == null) {
      return false;
    }
    InputMethodManager localInputMethodManager = (InputMethodManager)e.getSystemService("input_method");
    boolean bool = ((Boolean)aau.b(InputMethodManager.class, localInputMethodManager, "isSoftInputShown")).booleanValue();
    if ((bool) && (!paramBoolean1))
    {
      if (paramBoolean2) {
        return localInputMethodManager.hideSoftInputFromWindow(f.getWindowToken(), 0);
      }
      return localInputMethodManager.hideSoftInputFromWindow(f.getWindowToken(), 0);
    }
    if ((!bool) && (paramBoolean1)) {
      return localInputMethodManager.showSoftInput(f, 0);
    }
    return false;
  }
  
  private void e()
  {
    boolean bool2 = false;
    k = new ActionBar.LayoutParams(-1, -1, 19);
    ActionBar localActionBar;
    ActionBar.Tab localTab;
    if (l)
    {
      c.setDisplayShowTabEnabled(true);
      c.setDisplayShowTitleEnabled(false);
      c.setNavigationMode(2);
      n = c.newTab().setText(2131493458).setTabListener(p);
      localActionBar = c;
      localTab = n;
      if (MmsApp.s() != 0) {
        break label173;
      }
    }
    label173:
    for (boolean bool1 = true;; bool1 = false)
    {
      localActionBar.addTab(localTab, bool1);
      o = c.newTab().setText(2131493457).setTabListener(p);
      localActionBar = c;
      localTab = o;
      bool1 = bool2;
      if (MmsApp.s() == 1) {
        bool1 = true;
      }
      localActionBar.addTab(localTab, bool1);
      c.setDisplayUseLogoEnabled(true);
      aaa.a(c, e);
      i();
      return;
    }
  }
  
  private void f()
  {
    j = LayoutInflater.from(e).inflate(2130968602, null);
    f = ((EditText)j.findViewById(2131886207));
    g = ((ImageView)j.findViewById(2131886576));
    g.setOnClickListener(new is(this));
    m = new it(this, f);
  }
  
  private void g()
  {
    if (j == null) {
      f();
    }
    c.setDisplayOptions(16);
    c.setCustomView(j, k);
    f.setVisibility(0);
    f.setText("");
    f.addTextChangedListener(a);
    ImageView localImageView = g;
    if (TextUtils.isEmpty(f.getText())) {}
    for (int i1 = 8;; i1 = 0)
    {
      localImageView.setVisibility(i1);
      Log.d("ActionBarAdapter", "showSearch(), <----------addTextChangedListener(searchTextWatcher)");
      f.requestFocus();
      m.a(false);
      f.setOnKeyListener(q);
      if (f.postDelayed(new iu(this), 250L)) {}
      h = true;
      return;
    }
  }
  
  private void h()
  {
    if (f != null)
    {
      f.clearFocus();
      f.removeTextChangedListener(a);
      Log.d("ActionBarAdapter", "showTitle(), ---->removeTextChangedListener(searchTextWatcher)");
      f.setText("");
      m.a(true);
    }
    c.setDisplayOptions(8, 26);
    h = false;
  }
  
  private void i()
  {
    if (f != null)
    {
      f.clearFocus();
      f.removeTextChangedListener(a);
      Log.d("ActionBarAdapter", "showTabs(), ---->removeTextChangedListener(searchTextWatcher)");
      f.setText("");
      m.a(true);
    }
    h = false;
  }
  
  public void a()
  {
    d = MmsApp.s();
    c.selectTab(c.getTabAt(MmsApp.s()));
  }
  
  public void a(int paramInt)
  {
    c.selectTab(c.getTabAt(paramInt));
  }
  
  public void a(int paramInt1, float paramFloat, int paramInt2)
  {
    c.setTabScrolled(paramInt1, paramFloat, paramInt2);
  }
  
  public void a(int paramInt1, int paramInt2)
  {
    Resources localResources = e.getResources();
    String str = "";
    if (paramInt2 == 0) {
      if (paramInt1 > 0) {
        str = localResources.getString(2131493849, new Object[] { Integer.valueOf(paramInt1) });
      }
    }
    for (;;)
    {
      c.getTabAt(paramInt2).setText(str);
      return;
      str = localResources.getString(2131493458);
      continue;
      if (paramInt2 == 1) {
        if (paramInt1 > 0) {
          str = localResources.getString(2131493848, new Object[] { Integer.valueOf(paramInt1) });
        } else {
          str = localResources.getString(2131493457);
        }
      }
    }
  }
  
  public boolean a(ir.b paramb, boolean paramBoolean)
  {
    boolean bool = true;
    Log.d("ActionBarAdapter", "openOrCloseSearch(),mIsSearch = " + h + ", searchStatus = " + paramb);
    switch (ir.1.a[paramb.ordinal()])
    {
    default: 
      paramBoolean = false;
    }
    for (;;)
    {
      if ((paramBoolean) && (b != null)) {
        b.m_();
      }
      return paramBoolean;
      a(false, paramBoolean);
      if (!h) {
        break;
      }
      if (l)
      {
        i();
        paramBoolean = bool;
      }
      else
      {
        h();
        paramBoolean = bool;
        continue;
        if (h) {
          break;
        }
        g();
        paramBoolean = bool;
      }
    }
  }
  
  public int b()
  {
    if ((c != null) && (c.getSelectedTab() != null)) {
      return c.getSelectedTab().getPosition();
    }
    return -1;
  }
  
  public boolean c()
  {
    return h;
  }
  
  public void d()
  {
    Log.d("ActionBarAdapter", "onResume(), restoreActionBarOptions(),, mIsSearch = " + h);
    if (h)
    {
      f.requestFocus();
      i.postDelayed(new iy(this), 250L);
      return;
    }
    a(false, true);
  }
  
  public static abstract interface a
  {
    public abstract void a(int paramInt);
    
    public abstract void a(String paramString);
    
    public abstract void b();
    
    public abstract void m_();
  }
  
  public static enum b
  {
    private b() {}
  }
}

/* Location:
 * Qualified Name:     ir
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */