package com.android.mms.fragmentstyle;

import aaa;
import aan;
import aau;
import android.app.ProgressDialog;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.provider.Telephony.Mms;
import android.provider.Telephony.MmsSms;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.MultiChoiceView;
import android.text.TextUtils;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AbsListView.OnScrollListener;
import com.android.mms.MmsApp;
import com.android.mms.MmsApp.g;
import com.android.mms.transaction.MessagingNotification;
import fm;
import ga;
import hb;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import jn;
import jo;
import jp;
import jq;
import jr;
import js;
import jt;
import ju;
import jv;
import jy;
import lr;
import lx;
import pt;
import vv;
import vx;
import vx.c;
import wd;

public class FavoriteActivity
  extends fm
{
  public static final Uri a = Telephony.Mms.CONTENT_URI.buildUpon().appendPath("favorite").build();
  private static final Uri h = Telephony.MmsSms.CONTENT_URI.buildUpon().appendPath("favorite").build();
  MmsApp.g b = new jn(this);
  private jv c;
  private FavoriteListView d;
  private lx e;
  private AbsListView.OnScrollListener f;
  private a g;
  private ProgressDialog i;
  private b j;
  private pt k;
  private Bundle l = null;
  private MultiChoiceView m;
  private final vx.c n = new jq(this);
  private final Handler o = new jr(this);
  private Uri p = null;
  private Uri q = null;
  private String r = "";
  private vv s;
  
  private void a(int paramInt, boolean paramBoolean)
  {
    if ((paramInt >= 20) || (paramBoolean)) {}
    for (paramInt = 1;; paramInt = 0)
    {
      if (paramInt != 0)
      {
        if (i == null)
        {
          i = new ProgressDialog(this);
          i.setCancelable(false);
          i.setIndeterminate(true);
          i.setMessage(getString(2131493821));
        }
        i.show();
      }
      return;
    }
  }
  
  private final void a(Cursor paramCursor)
  {
    c();
    if (j == null) {
      return;
    }
    j.a(paramCursor);
  }
  
  private void a(Uri paramUri, String paramString)
  {
    if ((paramUri == null) || (!paramUri.getAuthority().startsWith("mms"))) {}
    while ((r.equals(paramString)) || (TextUtils.isEmpty(paramString))) {
      return;
    }
    s.a(paramString);
    MmsApp.c().e().a(paramUri);
    new Thread(new js(this, paramString, paramUri), "ComposeMessageActivity.updateFlymeMmsAttachPath").start();
  }
  
  private boolean a(vv paramvv)
  {
    s = paramvv;
    p = t;
    q = paramvv.ac();
    Object localObject2 = paramvv.Z();
    r = ((String)localObject2);
    if ((TextUtils.isEmpty((CharSequence)localObject2)) || (p == null)) {
      return false;
    }
    Object localObject1 = new File((String)localObject2);
    if ((!((File)localObject1).exists()) || (!((File)localObject1).isFile()))
    {
      wd.a(2131493296, this, 0, 1, true, 0);
      return false;
    }
    localObject1 = ((File)localObject1).getParent();
    if (((String)localObject2).startsWith("/sdcard/Download/FMessage/")) {
      localObject1 = wd.e();
    }
    localObject2 = lr.a(r);
    Intent localIntent = new Intent();
    localIntent.addFlags(524288);
    localIntent.setAction("com.meizu.action.SAVE_FILE");
    localIntent.putExtra("init_directory", (String)localObject1);
    localIntent.putExtra("SAVEATTACHMENT", true);
    localIntent.putExtra("android.intent.extra.TITLE", x);
    localIntent.putExtra("android.intent.extra.STREAM", ((Uri)localObject2).toString());
    localIntent.putExtra("title", getString(2131493088));
    startActivityForResult(localIntent, 114);
    return true;
  }
  
  private void d()
  {
    c = new jv(this, null, d, true, null, false);
    c.a(n);
    c.a(o);
    if (d.getEmptyView() != null) {
      d.getEmptyView().setVisibility(8);
    }
    d.setAdapter(c);
  }
  
  private void e()
  {
    d = ((FavoriteListView)findViewById(2131886419));
    if (d.getEmptyView() == null) {
      d.setEmptyView(findViewById(16908292));
    }
    d.setMultiChoiceModeListener(h());
    d.setChoiceMode(4);
    e = i();
    d.setListViewProxy(e);
    e.a(true);
    d.setOnScrollListener(f);
    d.setFooterDividersEnabled(false);
    d.setEnableDivider(true);
  }
  
  private void f()
  {
    ActionBar localActionBar = getSupportActionBar();
    localActionBar.setDisplayShowTabEnabled(false);
    localActionBar.setDisplayShowTitleEnabled(true);
    localActionBar.setTitle(getString(2131493714));
    localActionBar.setDisplayOptions(12);
    aaa.a(localActionBar, this);
  }
  
  private void g()
  {
    try
    {
      g.startQuery(1810, null, h.buildUpon().appendQueryParameter("addPartText", "true").build(), vx.a, "is_favorite == 1 OR is_favorite == 2", new String[] { "GROUP BY group_msg_id" }, "association_id DESC");
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      aau.a(this, localSQLiteException);
    }
  }
  
  private b h()
  {
    if (j == null) {
      j = new b();
    }
    return j;
  }
  
  private lx i()
  {
    if (e == null) {
      e = new jo(this, d);
    }
    return e;
  }
  
  private void j()
  {
    if (i == null) {
      return;
    }
    if (i.isShowing()) {
      i.dismiss();
    }
    i = null;
  }
  
  public void a(boolean paramBoolean, vv[] paramArrayOfvv)
  {
    new jp(this, paramArrayOfvv, paramBoolean).execute(new Void[0]);
  }
  
  public pt b()
  {
    if (k == null) {
      k = new pt(this);
    }
    return k;
  }
  
  public final boolean c()
  {
    if (l == null) {
      return false;
    }
    Parcelable localParcelable = l.getParcelable("FavoriteActivity.state.listState");
    if (localParcelable != null) {
      d.onRestoreInstanceState(localParcelable);
    }
    l = null;
    return true;
  }
  
  public void finish()
  {
    super.finish();
    aaa.b(this);
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt2 != -1) {}
    do
    {
      do
      {
        return;
        switch (paramInt1)
        {
        default: 
          return;
        }
      } while (paramIntent == null);
      paramIntent.setClassName(this, "com.android.mms.ui.ComposeMessageActivity");
      startActivity(paramIntent);
      return;
    } while (paramIntent == null);
    String str = paramIntent.getStringExtra("attach_name");
    wd.e(paramIntent.getData().getPath());
    a(p, str);
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if (d != null) {
      aaa.a(this, d);
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (MmsApp.n) {
      setTheme(2131624161);
    }
    for (;;)
    {
      l = paramBundle;
      f();
      setContentView(2130968660);
      g = new a(getContentResolver());
      e();
      aaa.a(d, true);
      d();
      return;
      setTheme(2131624162);
    }
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    if (c != null) {
      c.changeCursor(null);
    }
    l = null;
  }
  
  public void onPause()
  {
    super.onPause();
    if (k != null) {
      k.a();
    }
  }
  
  public void onResume()
  {
    super.onResume();
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    if ((j != null) && (d != null)) {
      paramBundle.putParcelable("FavoriteActivity.state.listState", d.onSaveInstanceState());
    }
  }
  
  public void onStart()
  {
    super.onStart();
    g();
  }
  
  public void onStop()
  {
    super.onStop();
  }
  
  public class a
    extends AsyncQueryHandler
  {
    public a(ContentResolver paramContentResolver)
    {
      super();
    }
    
    protected void onDeleteComplete(int paramInt1, Object paramObject, int paramInt2)
    {
      switch (paramInt1)
      {
      default: 
        return;
      }
      paramObject = (List)paramObject;
      Long[] arrayOfLong = new Long[((List)paramObject).size()];
      paramInt1 = 0;
      if (paramInt1 < ((List)paramObject).size())
      {
        if (((vv)((List)paramObject).get(paramInt1)).E()) {
          hb.a(((vv)((List)paramObject).get(paramInt1)).K());
        }
        for (;;)
        {
          arrayOfLong[paramInt1] = Long.valueOf(getf);
          paramInt1 += 1;
          break;
          hb.a(((vv)((List)paramObject).get(paramInt1)).Q());
          MmsApp.c().e().a(gett);
        }
      }
      MessagingNotification.a(FavoriteActivity.this, -2L, false);
      jy.a(arrayOfLong, FavoriteActivity.this);
      FavoriteActivity.d(FavoriteActivity.this);
    }
    
    protected void onQueryComplete(int paramInt, Object paramObject, Cursor paramCursor)
    {
      if (paramCursor == null) {
        return;
      }
      switch (paramInt)
      {
      default: 
        return;
      }
      paramInt = paramCursor.getCount();
      if (FavoriteActivity.b(FavoriteActivity.this).getEmptyView() != null)
      {
        paramObject = FavoriteActivity.b(FavoriteActivity.this).getEmptyView();
        if (paramInt != 0) {
          break label126;
        }
      }
      label126:
      for (paramInt = 0;; paramInt = 8)
      {
        ((View)paramObject).setVisibility(paramInt);
        paramInt = FavoriteActivity.a(FavoriteActivity.this).getCount();
        FavoriteActivity.a(FavoriteActivity.this).changeCursor(paramCursor);
        int i = FavoriteActivity.a(FavoriteActivity.this).getCount();
        if ((FavoriteActivity.c(FavoriteActivity.this) == null) || (paramInt == i)) {
          break;
        }
        FavoriteActivity.a(FavoriteActivity.this, paramCursor);
        return;
      }
    }
    
    protected void onUpdateComplete(int paramInt1, Object paramObject, int paramInt2)
    {
      switch (paramInt1)
      {
      default: 
        return;
      }
      FavoriteActivity.d(FavoriteActivity.this);
    }
  }
  
  public class b
    implements AbsListView.MultiChoiceModeListener
  {
    private MenuItem b;
    private MenuItem c;
    private MenuItem d;
    private HashMap<Long, FavoriteActivity.c> e;
    private int f = 0;
    private int g = 0;
    private int h = 0;
    private int i = 0;
    private int j = 0;
    
    public b() {}
    
    private void a(int paramInt)
    {
      boolean bool2 = true;
      MenuItem localMenuItem;
      if ((paramInt == 0) || (!ga.C()))
      {
        aaa.a(b, false);
        localMenuItem = c;
        if ((paramInt <= 0) || (!ga.C())) {
          break label185;
        }
      }
      label185:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        aaa.a(localMenuItem, bool1);
        aaa.a(FavoriteActivity.this, FavoriteActivity.e(FavoriteActivity.this), FavoriteActivity.b(FavoriteActivity.this).getCheckedItemCount(), FavoriteActivity.a(FavoriteActivity.this).getCount());
        return;
        if ((i > 0) || (h > 0) || ((paramInt > 1) && (f > 0)) || (g > 0))
        {
          aaa.a(b, false);
          break;
        }
        if ((f == 1) && (paramInt == 1))
        {
          localMenuItem = b;
          if ((ga.C()) && (j == 1)) {}
          for (bool1 = true;; bool1 = false)
          {
            aaa.a(localMenuItem, bool1);
            break;
          }
        }
        aaa.a(b, ga.C());
        break;
      }
    }
    
    private void a(HashMap<Long, FavoriteActivity.c> paramHashMap)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      int m = paramHashMap.size();
      if (m == 0) {
        return;
      }
      List localList = jy.a(paramHashMap);
      Object localObject = null;
      int k = 0;
      if (k < m)
      {
        vv localvv = getc;
        if (k == 0) {
          localObject = localvv;
        }
        if (k < m - 1) {
          localStringBuilder.append(o).append("\n");
        }
        for (;;)
        {
          k += 1;
          break;
          localStringBuilder.append(o);
        }
      }
      paramHashMap.clear();
      FavoriteActivity.b(FavoriteActivity.this).a();
      if ((m == 1) && (((vv)localObject).j()))
      {
        jy.a((vv)localObject, FavoriteActivity.this, FavoriteActivity.this);
        return;
      }
      jy.a(localStringBuilder.toString(), FavoriteActivity.this, FavoriteActivity.this);
    }
    
    private void a(vv paramvv, long paramLong)
    {
      e.remove(Long.valueOf(paramLong));
      if (u == 130) {
        i -= 1;
      }
      do
      {
        do
        {
          return;
          if (!paramvv.j()) {
            break;
          }
          if (paramvv.o())
          {
            g -= 1;
            return;
          }
          f -= 1;
        } while (!y);
        j -= 1;
        return;
      } while (M != vv.a);
      h -= 1;
    }
    
    private void a(vv paramvv, boolean paramBoolean)
    {
      long l = vx.a(paramvv.j(), e);
      boolean bool = e.containsKey(Long.valueOf(l));
      if ((!bool) && (paramBoolean)) {
        b(paramvv, l);
      }
      while ((!bool) || (paramBoolean)) {
        return;
      }
      a(paramvv, l);
    }
    
    private void a(boolean paramBoolean)
    {
      if (paramBoolean) {
        e = null;
      }
      f = 0;
      g = 0;
      h = 0;
      i = 0;
      j = 0;
    }
    
    private void b(Cursor paramCursor)
    {
      long[] arrayOfLong = FavoriteActivity.b(FavoriteActivity.this).getCheckedItemIds();
      int m = arrayOfLong.length;
      vv localvv = null;
      int k = 0;
      if (k < m)
      {
        long l = arrayOfLong[k];
        Log.i("FavoriteActivity", "restoreSelectedItem id = " + l);
        if (l > 0L) {
          localvv = FavoriteActivity.a(FavoriteActivity.this).a("sms", l, paramCursor);
        }
        for (;;)
        {
          Log.i("FavoriteActivity", "restoreSelectedItem msgItem = " + localvv);
          if (localvv != null) {
            b(localvv, l);
          }
          k += 1;
          break;
          if (l < 0L) {
            localvv = FavoriteActivity.a(FavoriteActivity.this).a("mms", -l, paramCursor);
          }
        }
      }
    }
    
    private void b(HashMap<Long, FavoriteActivity.c> paramHashMap)
    {
      int k = paramHashMap.size();
      if (k == 0) {
        return;
      }
      vv[] arrayOfvv = new vv[k];
      Iterator localIterator = paramHashMap.values().iterator();
      k = 0;
      boolean bool = false;
      while (localIterator.hasNext())
      {
        arrayOfvv[k] = nextc;
        bool = arrayOfvv[k].j();
        k += 1;
      }
      FavoriteActivity.a(FavoriteActivity.this, paramHashMap.size(), bool);
      a(false, arrayOfvv);
    }
    
    private void b(vv paramvv, long paramLong)
    {
      if (u == 130) {
        i += 1;
      }
      for (;;)
      {
        e.put(Long.valueOf(paramLong), new FavoriteActivity.c(FavoriteActivity.this, paramLong, paramvv));
        return;
        if (paramvv.j())
        {
          if (paramvv.o())
          {
            g += 1;
          }
          else
          {
            f += 1;
            if (y) {
              j += 1;
            }
          }
        }
        else if (M == vv.a) {
          h += 1;
        }
      }
    }
    
    public void a()
    {
      int k = FavoriteActivity.b(FavoriteActivity.this).getCheckedItemCount();
      int m = FavoriteActivity.a(FavoriteActivity.this).getCount();
      if (k < m) {}
      for (k = 1; k == 0; k = 0)
      {
        e.clear();
        FavoriteActivity.f(FavoriteActivity.this).f();
        a(false);
        a(e.size());
        return;
      }
      FavoriteActivity.f(FavoriteActivity.this).b();
      k = 0;
      label85:
      Object localObject;
      if (k < m)
      {
        localObject = (Cursor)FavoriteActivity.a(FavoriteActivity.this).getItem(k);
        if (localObject != null) {
          break label118;
        }
      }
      for (;;)
      {
        k += 1;
        break label85;
        break;
        label118:
        localObject = FavoriteActivity.a(FavoriteActivity.this).a((Cursor)localObject);
        if (localObject != null)
        {
          long l = vx.a(((vv)localObject).j(), e);
          if (!e.containsKey(Long.valueOf(l))) {
            b((vv)localObject, l);
          }
        }
      }
    }
    
    public void a(Cursor paramCursor)
    {
      if ((e == null) || (FavoriteActivity.a(FavoriteActivity.this).getCount() == 0)) {}
      do
      {
        HashMap localHashMap;
        do
        {
          return;
          if (e.size() <= 0) {
            break;
          }
          localHashMap = (HashMap)e.clone();
          paramCursor.moveToFirst();
          do
          {
            localHashMap.remove(Long.valueOf(vx.a(paramCursor.getString(0).equals("mms"), paramCursor.getLong(1))));
          } while ((paramCursor.moveToNext()) && (localHashMap.size() > 0));
        } while (localHashMap.size() == 0);
        paramCursor = localHashMap.values().iterator();
        while (paramCursor.hasNext()) {
          a(nextc, false);
        }
        if (FavoriteActivity.b(FavoriteActivity.this).getCheckedItemCount() > 0)
        {
          Log.i("FavoriteActivity", "refreshSelectedStatus mListView.getCheckedItemCount() = " + FavoriteActivity.b(FavoriteActivity.this).getCheckedItemCount());
          paramCursor.moveToFirst();
          b(paramCursor);
        }
      } while (!FavoriteActivity.b(FavoriteActivity.this).m());
      a(e.size());
    }
    
    public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
    {
      switch (paramMenuItem.getItemId())
      {
      }
      for (;;)
      {
        return true;
        if (e.size() > 0)
        {
          try
          {
            a(e);
          }
          catch (Exception paramActionMode)
          {
            Log.e("FavoriteActivity", "forward exception : ", paramActionMode);
          }
          continue;
          if (e.size() > 0)
          {
            b(e);
            paramActionMode.finish();
          }
        }
      }
    }
    
    public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      if ((FavoriteActivity.a(FavoriteActivity.this) == null) || (FavoriteActivity.a(FavoriteActivity.this).getCursor() == null) || (FavoriteActivity.a(FavoriteActivity.this).getCount() <= 0)) {
        return false;
      }
      if (e == null) {
        e = new HashMap();
      }
      getMenuInflater().inflate(2131951623, paramMenu);
      b = paramMenu.findItem(2131886797);
      c = paramMenu.findItem(2131886798);
      d = paramMenu.findItem(2131886809);
      FavoriteActivity.a(FavoriteActivity.this, new MultiChoiceView(FavoriteActivity.this));
      FavoriteActivity.e(FavoriteActivity.this).setOnCloseItemClickListener(new jt(this, paramActionMode));
      FavoriteActivity.e(FavoriteActivity.this).setOnSelectAllItemClickListener(new ju(this));
      paramActionMode.setCustomView(FavoriteActivity.e(FavoriteActivity.this));
      aaa.a(FavoriteActivity.this, FavoriteActivity.b(FavoriteActivity.this), true);
      FavoriteActivity.b(FavoriteActivity.this).c();
      return true;
    }
    
    public void onDestroyActionMode(ActionMode paramActionMode)
    {
      if (e != null)
      {
        paramActionMode = e.values().iterator();
        int k = 0;
        while (paramActionMode.hasNext())
        {
          vv localvv = nextc;
          if (localvv != null) {
            localvv.b(false);
          }
          k += 1;
        }
      }
      a(true);
      aaa.a(FavoriteActivity.this, FavoriteActivity.b(FavoriteActivity.this), false);
      FavoriteActivity.b(FavoriteActivity.this).d();
    }
    
    public void onItemCheckedStateChanged(ActionMode paramActionMode, int paramInt, long paramLong, boolean paramBoolean)
    {
      paramActionMode = (Cursor)FavoriteActivity.a(FavoriteActivity.this).getItem(paramInt);
      if ((paramActionMode == null) || (paramInt >= FavoriteActivity.a(FavoriteActivity.this).getCount())) {}
      do
      {
        return;
        paramActionMode = FavoriteActivity.a(FavoriteActivity.this).a(paramActionMode);
      } while (paramActionMode == null);
      a(paramActionMode, paramBoolean);
      if (e == null) {}
      for (paramInt = 0;; paramInt = e.size())
      {
        a(paramInt);
        return;
      }
    }
    
    public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      if ((e != null) && (FavoriteActivity.a(FavoriteActivity.this).getCount() > 0)) {
        a(e.size());
      }
      return true;
    }
  }
  
  public class c
    implements Comparable<c>
  {
    public long a;
    public long b;
    public vv c;
    
    public c(long paramLong, vv paramvv)
    {
      a = paramLong;
      c = paramvv;
      if (c != null)
      {
        b = c.N;
        return;
      }
      b = 0L;
    }
    
    public int a(c paramc)
    {
      if (c == null) {
        return -1;
      }
      if (c == null) {
        return 1;
      }
      return (int)(b - b);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.fragmentstyle.FavoriteActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */