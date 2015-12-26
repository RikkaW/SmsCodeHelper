package com.android.mms.quickreply;

import aaa;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.AsyncQueryHandler;
import android.content.ContentProviderOperation;
import android.content.ContentProviderOperation.Builder;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.internal.view.menu.MenuItemImpl;
import android.support.v7.widget.ActionMenuView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import fx;
import java.util.ArrayList;
import java.util.Iterator;
import mb;
import mg;
import mh;
import mi;
import mj;
import ml;
import mm;
import mt;

public class EditQuickReplyActivity
  extends fx
  implements CustomeScrollView.a, EditQuickReplyView.a
{
  private a a;
  private Cursor b;
  private int c;
  private int d;
  private ArrayList<String> e;
  private ArrayList<Integer> f;
  private CustomeScrollView g;
  private boolean h = false;
  private boolean i = false;
  private boolean j;
  private boolean k = false;
  private LinearLayout l;
  private int m = 0;
  private String[] n;
  private ArrayList<EditQuickReplyView> o;
  private LayoutInflater p;
  private MenuItem q;
  private MenuItem r;
  private ActionBar s;
  private mb t = new mj(this);
  private final Handler u = new mm(this);
  
  private void a(EditQuickReplyView paramEditQuickReplyView, int paramInt)
  {
    paramEditQuickReplyView.setEditHint(n[(paramInt % 4)]);
  }
  
  private void a(Runnable paramRunnable)
  {
    paramRunnable.run();
  }
  
  private void a(String paramString)
  {
    if (e == null) {
      e = new ArrayList();
    }
    e.add(paramString);
  }
  
  private void a(ArrayList<Integer> paramArrayList)
  {
    ArrayList localArrayList = new ArrayList();
    int i1 = 0;
    while (i1 < paramArrayList.size())
    {
      localArrayList.add(ContentProviderOperation.newUpdate(ContentUris.withAppendedId(Uri.parse("content://smsquickreply"), ((Integer)paramArrayList.get(i1)).intValue() + 1)).withValue("content", b(((Integer)paramArrayList.get(i1)).intValue())).withValue("dirty", Integer.valueOf(1)).build());
      i1 += 1;
    }
    try
    {
      getContentResolver().applyBatch("smsquickreply", localArrayList);
      return;
    }
    catch (Exception paramArrayList)
    {
      throw new RuntimeException("Failed to store new contact", paramArrayList);
    }
  }
  
  private String b(int paramInt)
  {
    if (paramInt < m) {
      return ((EditQuickReplyView)o.get(paramInt)).getNewText();
    }
    return null;
  }
  
  private void b(boolean paramBoolean)
  {
    if (!j) {
      return;
    }
    k = paramBoolean;
  }
  
  private void f()
  {
    l = ((LinearLayout)findViewById(2131886432));
    p = ((LayoutInflater)getSystemService("layout_inflater"));
    g = ((CustomeScrollView)findViewById(2131886431));
    n = getResources().getStringArray(2131361797);
  }
  
  private void g()
  {
    a.cancelOperation(10000);
    String[] arrayOfString = getResources().getStringArray(2131361797);
    String str = mt.a();
    a.startQuery(10000, null, mt.a, arrayOfString, str, null, null);
  }
  
  private void h()
  {
    if ((b != null) && (!b.isClosed()) && (b.getCount() > 0))
    {
      b.moveToPosition(-1);
      if (m != b.getCount()) {
        i();
      }
    }
  }
  
  private void i()
  {
    m = b.getCount();
    o = new ArrayList();
    int i1 = 0;
    while (b.moveToNext())
    {
      String str = b.getString(1);
      EditQuickReplyView localEditQuickReplyView = (EditQuickReplyView)p.inflate(2130968656, l, false);
      localEditQuickReplyView.setText(str);
      localEditQuickReplyView.setIndex(i1);
      localEditQuickReplyView.setChangeModeCallback(this);
      a(localEditQuickReplyView, i1);
      o.add(localEditQuickReplyView);
      l.addView(localEditQuickReplyView);
      l.addView(p.inflate(2130968818, l, false));
      a(str);
      i1 += 1;
    }
  }
  
  private void j()
  {
    if (k())
    {
      a(f);
      l();
      return;
    }
    l();
  }
  
  private boolean k()
  {
    if (f != null) {
      f.clear();
    }
    int i1 = 0;
    boolean bool2;
    for (boolean bool1 = false; i1 < m; bool1 = bool2)
    {
      String str = ((EditQuickReplyView)o.get(i1)).getNewText();
      bool2 = bool1;
      if (!TextUtils.isEmpty(str))
      {
        bool2 = bool1;
        if (!((String)e.get(i1)).equals(str))
        {
          if (f == null) {
            f = new ArrayList();
          }
          f.add(Integer.valueOf(i1));
          bool2 = true;
        }
      }
      i1 += 1;
    }
    return bool1;
  }
  
  private void l()
  {
    a(new mi(this));
  }
  
  private void m()
  {
    InputMethodManager localInputMethodManager = (InputMethodManager)getSystemService("input_method");
    t.a(localInputMethodManager);
    t.b();
    if ((k) && (!t.c()) && (getCurrentFocus() != null)) {
      u.postDelayed(new ml(this, localInputMethodManager), 250L);
    }
  }
  
  public void a(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= m)) {
      return;
    }
    ((EditQuickReplyView)o.get(paramInt + 1)).a();
  }
  
  public void a(boolean paramBoolean)
  {
    h = paramBoolean;
    if (i) {
      return;
    }
    if (paramBoolean)
    {
      c = 0;
      i = true;
    }
    for (;;)
    {
      invalidateOptionsMenu();
      return;
      c = 1;
    }
  }
  
  public void a(boolean paramBoolean, int paramInt)
  {
    if (paramBoolean)
    {
      d = paramInt;
      return;
    }
    d = -1;
  }
  
  protected void b()
  {
    s = getSupportActionBar();
    s.setDisplayShowTitleEnabled(true);
    s.setDisplayOptions(12);
    aaa.a(s, this);
  }
  
  public void c() {}
  
  public void d()
  {
    InputMethodManager localInputMethodManager = (InputMethodManager)getSystemService("input_method");
    if ((d > -1) && (d < m)) {
      localInputMethodManager.hideSoftInputFromWindow(((EditQuickReplyView)o.get(d)).getWindowToken(), 0);
    }
  }
  
  public void e()
  {
    boolean bool = k();
    if (bool)
    {
      c = 0;
      invalidateOptionsMenu();
    }
    while ((bool) || (c == 1)) {
      return;
    }
    c = 1;
    invalidateOptionsMenu();
  }
  
  public void onBackPressed()
  {
    if (k())
    {
      Object localObject = new AlertDialog.Builder(this);
      ((AlertDialog.Builder)localObject).setMessage(2131493544);
      ((AlertDialog.Builder)localObject).setPositiveButton(2131493787, new mg(this));
      ((AlertDialog.Builder)localObject).setNegativeButton(2131493022, new mh(this));
      localObject = ((AlertDialog.Builder)localObject).create();
      ((Dialog)localObject).show();
      localObject = ((AlertDialog)localObject).getButton(-1);
      if (c == 0) {}
      for (boolean bool = true;; bool = false)
      {
        ((Button)localObject).setEnabled(bool);
        return;
      }
    }
    l();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setUiOptions(1);
    setContentView(2130968655);
    b();
    f();
    c = 1;
    a = new a(getContentResolver());
    g();
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131951621, paramMenu);
    Object localObject = paramMenu.findItem(2131886807);
    if ((localObject instanceof MenuItemImpl)) {
      ((MenuItemImpl)localObject).setTitleColor(getResources().getColorStateList(2131820890));
    }
    localObject = (ActionMenuView)findViewById(2131886090);
    if (localObject != null) {
      ((ActionMenuView)localObject).setButtonBarStyleDivider();
    }
    q = paramMenu.findItem(2131886807);
    r = paramMenu.findItem(2131886806);
    return true;
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    if (b != null) {
      b.close();
    }
    if (f != null) {
      f.clear();
    }
    if (e != null) {
      e.clear();
    }
    if (n != null) {
      n = null;
    }
    if (o != null)
    {
      Iterator localIterator = o.iterator();
      while (localIterator.hasNext()) {
        ((EditQuickReplyView)localIterator.next()).b();
      }
      o.clear();
    }
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    }
    for (;;)
    {
      return true;
      d();
      onBackPressed();
      continue;
      j();
      continue;
      d();
      onBackPressed();
    }
  }
  
  protected void onPause()
  {
    super.onPause();
    j = false;
    InputMethodManager localInputMethodManager = (InputMethodManager)getSystemService("input_method");
    t.a(localInputMethodManager);
    t.d();
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    super.onPrepareOptionsMenu(paramMenu);
    paramMenu = q;
    if (c == 0) {}
    for (boolean bool = true;; bool = false)
    {
      paramMenu.setEnabled(bool);
      return true;
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    m();
    j = true;
  }
  
  protected void onStart()
  {
    super.onStart();
  }
  
  protected void onStop()
  {
    super.onStop();
    a.cancelOperation(10000);
    a.cancelOperation(10001);
  }
  
  final class a
    extends AsyncQueryHandler
  {
    public a(ContentResolver paramContentResolver)
    {
      super();
    }
    
    protected void onQueryComplete(int paramInt, Object paramObject, Cursor paramCursor)
    {
      if (isFinishing())
      {
        if (paramCursor != null) {
          paramCursor.close();
        }
        return;
      }
      switch (paramInt)
      {
      default: 
        return;
      }
      EditQuickReplyActivity.a(EditQuickReplyActivity.this, paramCursor);
      EditQuickReplyActivity.a(EditQuickReplyActivity.this);
    }
    
    protected void onUpdateComplete(int paramInt1, Object paramObject, int paramInt2)
    {
      if (isFinishing()) {
        return;
      }
      switch (paramInt1)
      {
      default: 
        return;
      }
      EditQuickReplyActivity.a(EditQuickReplyActivity.this, 1);
      d();
      invalidateOptionsMenu();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.quickreply.EditQuickReplyActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */