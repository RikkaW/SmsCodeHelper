package com.android.mms.ui;

import aaa;
import aau;
import android.app.ProgressDialog;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Telephony.Sms.Inbox;
import android.provider.Telephony.Sms.Sent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.MultiChoiceView;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.ListView;
import com.android.mms.MmsApp;
import com.android.mms.transaction.MessagingNotification;
import com.meizu.common.widget.EmptyView;
import fm;
import ga;
import gg;
import gr;
import lx;
import ut;
import uu;
import uv;
import uw;
import ux;
import vv;
import wd;
import zv;

public class ManageSimMessages
  extends fm
  implements DialogInterface.OnCancelListener
{
  private static final Uri a = Uri.parse("content://sms/icc");
  private static final Uri b = Uri.parse("content://sms/icc2");
  private ContentResolver c;
  private ListView d;
  private EmptyView e;
  private lx f;
  private ProgressDialog g;
  private gg h = null;
  private AsyncQueryHandler i = null;
  private boolean j = false;
  private b k = null;
  private Bundle l = null;
  private a m;
  private int n;
  private Uri o;
  private MultiChoiceView p;
  
  private final void a(int paramInt)
  {
    g();
    g = new uu(this, this);
    switch (paramInt)
    {
    }
    for (;;)
    {
      g.setCancelable(true);
      g.setCanceledOnTouchOutside(false);
      g.setIndeterminate(true);
      g.setOnCancelListener(this);
      WindowManager.LayoutParams localLayoutParams = new WindowManager.LayoutParams();
      aau.a(localLayoutParams, 512);
      g.getWindow().setAttributes(localLayoutParams);
      g.show();
      return;
      g.setMessage(getString(2131493822));
      continue;
      g.setMessage(getString(2131493539));
      continue;
      g.setMessage(getString(2131493538));
    }
  }
  
  private void a(Intent paramIntent)
  {
    n = zv.a(paramIntent, "sim_id");
    Log.i("ManageSimMessages", "initSimMessageUri mSlotId = " + n);
    if (n == 0)
    {
      o = a;
      return;
    }
    o = b;
  }
  
  private void a(Cursor paramCursor)
  {
    h = new gg(this, paramCursor, k);
    d.setAdapter(h);
    b();
  }
  
  private void a(Bundle paramBundle)
  {
    boolean bool = false;
    d.setCacheColorHint(0);
    d.setMultiChoiceModeListener(c());
    f.a(4);
    f.a();
    MessagingNotification.a(getApplicationContext(), 234);
    j = false;
    l = paramBundle;
    if (paramBundle == null) {
      bool = true;
    }
    a(bool);
  }
  
  private void a(boolean paramBoolean)
  {
    if (paramBoolean) {}
    try
    {
      a(110);
      i.startQuery(101, null, o, null, null, null, null);
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      aau.a(this, localSQLiteException);
    }
  }
  
  private void a(long[] paramArrayOfLong)
  {
    if ((h == null) || (paramArrayOfLong == null) || (paramArrayOfLong.length == 0)) {
      return;
    }
    wd.a(true);
    int i1 = 0;
    while (i1 < paramArrayOfLong.length)
    {
      a(paramArrayOfLong[i1]);
      i1 += 1;
    }
    gr.b(MmsApp.c());
    wd.a(false);
  }
  
  private void a(long[] paramArrayOfLong, int paramInt)
  {
    if ((paramArrayOfLong == null) || (paramArrayOfLong.length == 0)) {
      return;
    }
    m = new a(paramArrayOfLong, paramInt);
    m.execute(new Integer[0]);
  }
  
  private boolean a(long paramLong)
  {
    Object localObject5 = h.a(paramLong);
    if (localObject5 == null) {
      return false;
    }
    Object localObject4 = m;
    String str = o;
    Object localObject3 = Long.valueOf(((vv)localObject5).R());
    Object localObject1 = localObject3;
    if (((Long)localObject3).longValue() <= 0L) {
      localObject1 = Long.valueOf(System.currentTimeMillis());
    }
    try
    {
      if (((vv)localObject5).T())
      {
        localObject3 = Boolean.TYPE;
        localObject5 = c;
      }
      for (localObject1 = (Uri)aau.a(Telephony.Sms.Inbox.class, "addMessage", new Class[] { ContentResolver.class, String.class, String.class, String.class, Long.class, localObject3 }, new Object[] { localObject5, localObject4, str, null, localObject1, Boolean.valueOf(true) });; localObject1 = (Uri)aau.a(Telephony.Sms.Sent.class, "addMessage", new Class[] { ContentResolver.class, String.class, String.class, String.class, Long.class }, new Object[] { localObject3, localObject4, str, null, localObject1 }))
      {
        localObject3 = zv.c(n);
        localObject4 = new ContentValues(2);
        ((ContentValues)localObject4).put("imsi", (String)localObject3);
        ((ContentValues)localObject4).put("sim_id", Integer.valueOf(n));
        c.update((Uri)localObject1, (ContentValues)localObject4, null, null);
        if (localObject1 == null) {
          break;
        }
        bool = true;
        return bool;
        localObject3 = c;
      }
    }
    catch (SQLiteException localSQLiteException)
    {
      for (;;)
      {
        aau.a(this, localSQLiteException);
        Object localObject2 = null;
        continue;
        boolean bool = false;
      }
    }
  }
  
  private int b(long[] paramArrayOfLong)
  {
    int i3 = 0;
    if ((h == null) || (paramArrayOfLong == null) || (paramArrayOfLong.length == 0)) {}
    StringBuilder localStringBuilder;
    do
    {
      return 0;
      localStringBuilder = new StringBuilder();
      i2 = 1;
      int i4 = paramArrayOfLong.length;
      i1 = 0;
      if (i1 < i4)
      {
        long l1 = paramArrayOfLong[i1];
        if (i2 != 0) {
          i2 = 0;
        }
        for (;;)
        {
          localStringBuilder.append(l1);
          i1 += 1;
          break;
          localStringBuilder.append(";");
        }
      }
    } while (j);
    int i2 = c.delete(o, localStringBuilder.toString(), null);
    int i1 = i3;
    while (i1 < paramArrayOfLong.length)
    {
      h.b(paramArrayOfLong[i1]);
      i1 += 1;
    }
    return i2;
  }
  
  private void d()
  {
    if (f == null) {
      f = new ut(this, d);
    }
  }
  
  private boolean e()
  {
    long[] arrayOfLong = d.getCheckedItemIds();
    boolean bool = true;
    int i1 = 0;
    if (i1 < arrayOfLong.length)
    {
      vv localvv = h.a(arrayOfLong[i1]);
      if (localvv != null) {
        if ((localvv.W() != null) && (!localvv.W().equals(""))) {}
      }
      for (bool = false;; bool = false)
      {
        i1 += 1;
        break;
      }
    }
    return bool;
  }
  
  private void f()
  {
    ActionBar localActionBar = getSupportActionBar();
    localActionBar.setDisplayShowTabEnabled(false);
    localActionBar.setDisplayShowTitleEnabled(true);
    localActionBar.setDisplayOptions(12);
    aaa.a(localActionBar, this);
  }
  
  private final void g()
  {
    if (g == null) {
      return;
    }
    if (g.isShowing()) {
      g.dismiss();
    }
    g = null;
  }
  
  public final void b()
  {
    if (l == null) {
      return;
    }
    Parcelable localParcelable = l.getParcelable("ManageSimMessages.state.listState");
    if (localParcelable != null) {
      d.onRestoreInstanceState(localParcelable);
    }
    l = null;
  }
  
  public final b c()
  {
    if (k == null) {
      k = new b();
    }
    return k;
  }
  
  public void onBackPressed()
  {
    j = true;
    if (m != null) {
      m.cancel(true);
    }
    super.onBackPressed();
  }
  
  public void onCancel(DialogInterface paramDialogInterface)
  {
    j = true;
    finish();
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if (d != null) {
      aaa.a(this, d);
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    int i1 = 2131493797;
    super.onCreate(paramBundle);
    c = getContentResolver();
    i = new c(c);
    setContentView(2130968834);
    d = ((ListView)findViewById(2131886375));
    d.setPadding(d.getPaddingLeft(), aaa.a(), d.getPaddingRight(), d.getPaddingBottom());
    d();
    f.a(true);
    e = ((EmptyView)findViewById(2131886729));
    e.setVisibility(8);
    f();
    a(getIntent());
    a(paramBundle);
    if (zv.e > 1)
    {
      if (!MmsApp.b) {
        break label192;
      }
      if (n != 0) {
        break label185;
      }
    }
    label185:
    for (i1 = 2131493797;; i1 = 2131493798)
    {
      setTitle(getString(2131493779, new Object[] { getString(i1) }));
      return;
    }
    label192:
    if (n == 0) {}
    for (;;)
    {
      setTitle(getString(2131493778, new Object[] { getString(i1) }));
      return;
      i1 = 2131493798;
    }
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    if (h != null)
    {
      h.a();
      h.changeCursor(null);
    }
    l = null;
    g();
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    setIntent(paramIntent);
    a(null);
    a(paramIntent);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    Log.d("ManageSimMessages", "onOptionsItemSelected -> " + paramMenuItem.getItemId());
    switch (paramMenuItem.getItemId())
    {
    default: 
      return false;
    }
    onBackPressed();
    return true;
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    if ((k != null) && (k.a()) && (d != null)) {
      paramBundle.putParcelable("ManageSimMessages.state.listState", d.onSaveInstanceState());
    }
  }
  
  class a
    extends AsyncTask<Integer, Void, Integer>
  {
    private long[] b;
    private int c;
    
    public a(long[] paramArrayOfLong, int paramInt)
    {
      b = paramArrayOfLong;
      c = paramInt;
    }
    
    protected Integer a(Integer... paramVarArgs)
    {
      int i = -1;
      switch (c)
      {
      }
      for (;;)
      {
        return Integer.valueOf(i);
        ManageSimMessages.a(ManageSimMessages.this, b);
        continue;
        i = ManageSimMessages.b(ManageSimMessages.this, b);
      }
    }
    
    protected void a(Integer paramInteger)
    {
      b = null;
      if (paramInteger.intValue() < 1)
      {
        ManageSimMessages.h(ManageSimMessages.this);
        return;
      }
      ManageSimMessages.a(ManageSimMessages.this, false);
    }
    
    protected void onPreExecute()
    {
      ManageSimMessages.a(ManageSimMessages.this, c);
    }
  }
  
  public class b
    implements AbsListView.MultiChoiceModeListener
  {
    private MenuItem b;
    private MenuItem c;
    
    public b() {}
    
    public int a(int paramInt, long paramLong)
    {
      Object localObject = (Cursor)ManageSimMessages.c(ManageSimMessages.this).getItemAtPosition(paramInt);
      if ((localObject == null) || (paramInt >= ManageSimMessages.c(ManageSimMessages.this).getCount())) {}
      do
      {
        return 0;
        localObject = ManageSimMessages.b(ManageSimMessages.this).a((Cursor)localObject);
      } while ((((vv)localObject).W() != null) && (!((vv)localObject).W().equals("")));
      c.setEnabled(false);
      return 0;
    }
    
    public int a(MenuItem paramMenuItem)
    {
      switch (paramMenuItem.getItemId())
      {
      case 2131886811: 
      default: 
        return 0;
      }
      return 1;
    }
    
    public void a(MenuItem paramMenuItem, int paramInt, long paramLong) {}
    
    public boolean a()
    {
      if (ManageSimMessages.c(ManageSimMessages.this) == null) {}
      while (ManageSimMessages.c(ManageSimMessages.this).getCheckedItemCount() <= 0) {
        return false;
      }
      return true;
    }
    
    public void b(int paramInt, long paramLong) {}
    
    public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
    {
      int i = 0;
      switch (paramMenuItem.getItemId())
      {
      default: 
        paramActionMode.finish();
        return true;
      case 2131886798: 
        i = ManageSimMessages.c(ManageSimMessages.this).getCheckedItemIds().length;
        paramMenuItem = getResources().getQuantityString(2131427333, i, new Object[] { Integer.valueOf(i) });
        String str = getResources().getString(17039360);
        ManageSimMessages localManageSimMessages = ManageSimMessages.this;
        paramActionMode = new ux(this, paramActionMode);
        wd.a(localManageSimMessages, new CharSequence[] { paramMenuItem, str }, paramActionMode).show();
        return true;
      }
      if (ManageSimMessages.c(ManageSimMessages.this).getCheckedItemIds() != null) {
        while (i < ManageSimMessages.c(ManageSimMessages.this).getCheckedItemIds().length)
        {
          Log.i("ManageSimMessages", "MessageSimListAdapter getCachedMessageItem mSimList.getCheckedItemIds[" + i + "] = " + ManageSimMessages.c(ManageSimMessages.this).getCheckedItemIds()[i]);
          i += 1;
        }
      }
      ManageSimMessages.a(ManageSimMessages.this, ManageSimMessages.c(ManageSimMessages.this).getCheckedItemIds(), 112);
      paramActionMode.finish();
      return true;
    }
    
    public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      if ((ManageSimMessages.b(ManageSimMessages.this) == null) || (ManageSimMessages.b(ManageSimMessages.this).getCursor() == null) || (ManageSimMessages.b(ManageSimMessages.this).getCount() == 0)) {
        return false;
      }
      ManageSimMessages.c(ManageSimMessages.this).setPadding(ManageSimMessages.c(ManageSimMessages.this).getPaddingLeft(), aaa.a(), ManageSimMessages.c(ManageSimMessages.this).getPaddingRight(), ManageSimMessages.c(ManageSimMessages.this).getPaddingBottom() + getResources().getDimensionPixelSize(2131558606));
      getMenuInflater().inflate(2131951628, paramMenu);
      b = paramMenu.findItem(2131886798);
      c = paramMenu.findItem(2131886811);
      ManageSimMessages.a(ManageSimMessages.this, new MultiChoiceView(ManageSimMessages.this));
      ManageSimMessages.d(ManageSimMessages.this).setOnCloseItemClickListener(new uv(this, paramActionMode));
      ManageSimMessages.d(ManageSimMessages.this).setOnSelectAllItemClickListener(new uw(this));
      paramActionMode.setCustomView(ManageSimMessages.d(ManageSimMessages.this));
      return true;
    }
    
    public void onDestroyActionMode(ActionMode paramActionMode)
    {
      ManageSimMessages.c(ManageSimMessages.this).setPadding(ManageSimMessages.c(ManageSimMessages.this).getPaddingLeft(), aaa.a(), ManageSimMessages.c(ManageSimMessages.this).getPaddingRight(), ManageSimMessages.c(ManageSimMessages.this).getPaddingBottom() - getResources().getDimensionPixelSize(2131558606));
    }
    
    public void onItemCheckedStateChanged(ActionMode paramActionMode, int paramInt, long paramLong, boolean paramBoolean)
    {
      boolean bool = true;
      Log.i("ManageSimMessages", "ManageSimMessages onItemCheckedStateChanged id = " + paramLong + ", position = " + paramInt);
      aaa.a(ManageSimMessages.this, ManageSimMessages.d(ManageSimMessages.this), ManageSimMessages.c(ManageSimMessages.this).getCheckedItemCount(), ManageSimMessages.b(ManageSimMessages.this).getCount());
      paramActionMode = c;
      if ((ManageSimMessages.c(ManageSimMessages.this).getCheckedItemCount() > 0) && (ManageSimMessages.f(ManageSimMessages.this)))
      {
        paramBoolean = true;
        aaa.a(paramActionMode, paramBoolean);
        paramActionMode = b;
        if ((ManageSimMessages.c(ManageSimMessages.this).getCheckedItemCount() <= 0) || (!ga.C())) {
          break label149;
        }
      }
      label149:
      for (paramBoolean = bool;; paramBoolean = false)
      {
        aaa.a(paramActionMode, paramBoolean);
        return;
        paramBoolean = false;
        break;
      }
    }
    
    public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      return true;
    }
  }
  
  class c
    extends AsyncQueryHandler
  {
    public c(ContentResolver paramContentResolver)
    {
      super();
    }
    
    protected void onQueryComplete(int paramInt, Object paramObject, Cursor paramCursor)
    {
      if (ManageSimMessages.g(ManageSimMessages.this))
      {
        if (paramCursor != null) {
          paramCursor.close();
        }
        ManageSimMessages.h(ManageSimMessages.this);
        return;
      }
      if (paramCursor != null)
      {
        paramInt = paramCursor.getCount();
        if (ManageSimMessages.i(ManageSimMessages.this) != null)
        {
          paramObject = ManageSimMessages.i(ManageSimMessages.this);
          if (paramInt != 0) {
            break label94;
          }
          paramInt = 0;
          ((EmptyView)paramObject).setVisibility(paramInt);
        }
        if (ManageSimMessages.b(ManageSimMessages.this) != null) {
          break label100;
        }
        ManageSimMessages.a(ManageSimMessages.this, paramCursor);
      }
      for (;;)
      {
        ManageSimMessages.h(ManageSimMessages.this);
        return;
        label94:
        paramInt = 8;
        break;
        label100:
        ManageSimMessages.b(ManageSimMessages.this).changeCursor(paramCursor);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ManageSimMessages
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */