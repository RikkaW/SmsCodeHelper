package com.android.mms.fragmentstyle;

import aaa;
import aat;
import aau;
import android.app.Activity;
import android.app.ListFragment;
import android.app.ProgressDialog;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.provider.Telephony.Mms;
import android.provider.Telephony.Threads;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.MultiChoiceView;
import android.text.format.Time;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.TextView;
import com.android.mms.MmsApp;
import com.android.mms.MmsApp.g;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.ui.ConversationList;
import com.android.mms.ui.SelectConversationList;
import fl;
import ga;
import ge;
import ge.a;
import gj;
import gr;
import gr.b;
import gx;
import iz;
import ja;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import jb;
import jc;
import jd;
import je;
import jf;
import jg;
import jh;
import ji;
import jj;
import jk;
import jl;
import jm;
import lx;
import wd;
import zt;
import zt.a;
import zv;
import zv.a;

public class ConversationListFragment
  extends ListFragment
  implements zt.a
{
  private static long O = 0L;
  static final String[] d = { "thread_update_count", "database_finger_print" };
  private c A;
  private String B;
  private int C;
  private int D;
  private boolean E;
  private View F;
  private TextView G;
  private View H;
  private TextView I;
  private boolean J = false;
  private boolean K = false;
  private View L = null;
  private f M = new f(null);
  private h N = new h(null);
  private Runnable P = new jd(this);
  private final ge.a Q = new je(this);
  private final View.OnKeyListener R = new jh(this);
  private final Runnable S = new ji(this);
  private Runnable T;
  private boolean U = false;
  private boolean V = false;
  public g a;
  a b = null;
  MmsApp.g c = new jf(this);
  private ge e;
  private Handler f;
  private ProgressDialog g;
  private boolean h;
  private long i = 0L;
  private String j;
  private boolean k;
  private boolean l;
  private boolean m = true;
  private Time n;
  private int o;
  private ListView p;
  private lx q;
  private boolean r = false;
  private boolean s = true;
  private iz t = null;
  private Bundle u = null;
  private int v = -1;
  private int w;
  private Context x;
  private View y;
  private AbsListView.OnScrollListener z;
  
  private int a(Cursor paramCursor)
  {
    int i3 = 0;
    int i2 = 0;
    int i1 = i3;
    if (paramCursor != null)
    {
      i1 = i3;
      if (paramCursor.moveToFirst()) {
        do
        {
          i1 = i2;
          if (paramCursor.getInt(paramCursor.getColumnIndex("read")) == 0) {
            i1 = i2 + 1;
          }
          i2 = i1;
        } while (paramCursor.moveToNext());
      }
    }
    return i1;
  }
  
  public static AlertDialog a(b paramb, Collection<Long> paramCollection, Context paramContext)
  {
    return wd.a(paramContext, new String[] { paramContext.getResources().getQuantityString(2131427328, paramCollection.size(), new Object[] { Integer.valueOf(paramCollection.size()) }), paramContext.getString(17039360) }, paramb).show();
  }
  
  private void a(String paramString, Object... paramVarArgs)
  {
    paramString = String.format(paramString, paramVarArgs);
    Log.d("ConversationList", "[" + Thread.currentThread().getId() + "] " + paramString);
  }
  
  public static final void a(Collection<Long> paramCollection, boolean paramBoolean)
  {
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext())
    {
      long l1 = ((Long)paramCollection.next()).longValue();
      if (l1 != -1L)
      {
        paramCollection.remove();
        zt.c().a(l1, paramBoolean);
      }
    }
  }
  
  private boolean a(int paramInt1, int paramInt2)
  {
    if ((paramInt1 == 2) && (paramInt2 != 2)) {}
    while ((paramInt1 != 2) && (paramInt2 == 2)) {
      return true;
    }
    return false;
  }
  
  private void d(boolean paramBoolean)
  {
    if ((!paramBoolean) || (U) || (i())) {
      U = false;
    }
    try
    {
      gr.a(a, 1701, B);
      if ((!ConversationList.b) || (!u())) {
        wd.p(x);
      }
      gr.c(a, 1702, t());
      n.setToNow();
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      aau.a(x, localSQLiteException);
    }
  }
  
  private final boolean d(int paramInt)
  {
    boolean bool2 = b();
    if (t == null) {
      return false;
    }
    if ((!J) && ((t instanceof e))) {}
    for (boolean bool1 = ((e)t).a(paramInt);; bool1 = false)
    {
      if ((bool2) && (bool1)) {}
      for (bool1 = true;; bool1 = false) {
        return bool1;
      }
    }
  }
  
  private void e(int paramInt)
  {
    int i1 = p.getHeaderViewsCount();
    r();
    if (paramInt > 0)
    {
      if (i1 > 0) {
        break label110;
      }
      p.addHeaderView(F);
      gj.b(p, 300, -F.getMeasuredHeight(), null);
      paramInt = 1;
    }
    for (;;)
    {
      if (paramInt != 0)
      {
        aaa.a(getListView(), true);
        p.setSelection(0);
      }
      return;
      if (i1 > 0)
      {
        gj.a(p, 300, -F.getMeasuredHeight(), new jk(this));
        paramInt = 1;
      }
      else
      {
        label110:
        paramInt = 0;
      }
    }
  }
  
  private void f()
  {
    p = getListView();
    p.setOnKeyListener(R);
    p.setMultiChoiceModeListener(l());
    p.setChoiceMode(4);
    q = m();
    q.a(true);
    q.a();
    p.setOnScrollListener(z);
    setListAdapter(e);
    p.setRecyclerListener(e);
    if (V) {
      s();
    }
    p.setEmptyView(y.findViewById(16908292));
    if (p.getEmptyView() != null) {
      p.getEmptyView().setVisibility(8);
    }
    if (L == null)
    {
      L = new View(x);
      L.setMinimumHeight(getResources().getDimensionPixelOffset(2131558860));
    }
    p.addFooterView(L);
  }
  
  private void g()
  {
    e = new ge(x, null);
    e.a(Q);
  }
  
  private void h()
  {
    long l1 = 500L;
    a.removeMessages(1808);
    Time localTime = new Time();
    localTime.setToNow();
    if (localTime.toMillis(true) - n.toMillis(true) < 500L) {}
    for (;;)
    {
      n = localTime;
      a.sendEmptyMessageDelayed(1808, l1);
      return;
      l1 = 0L;
    }
  }
  
  private boolean i()
  {
    if (l) {
      return false;
    }
    long l1 = j();
    Log.d("ConversationList", "isNeedUpdateThreadList dbThreadVer = " + l1 + ", mThreadVer = " + i);
    int i1;
    if (l1 == i)
    {
      i1 = 0;
      label64:
      if (k) {
        break label109;
      }
    }
    label109:
    for (int i2 = 0;; i2 = 1)
    {
      k = false;
      boolean bool = k();
      if ((i1 == 0) && (i2 == 0) && (!bool)) {
        break;
      }
      return true;
      i = l1;
      i1 = 1;
      break label64;
    }
  }
  
  private long j()
  {
    long l1 = i + 1L;
    Object localObject1 = Uri.parse("content://mms-sms/mmssms-modify-count");
    if (x == null) {
      return l1;
    }
    localObject1 = x.getContentResolver().query((Uri)localObject1, d, null, null, null);
    if (localObject1 == null)
    {
      Log.e("Mms", "getthread_ver error");
      return l1;
    }
    for (;;)
    {
      try
      {
        if (((Cursor)localObject1).getCount() > 0)
        {
          ((Cursor)localObject1).moveToFirst();
          String str = ((Cursor)localObject1).getString(1);
          long l2 = ((Cursor)localObject1).getLong(0);
          l1 = l2;
          if (str != null)
          {
            l1 = l2;
            if (!j.equals(str))
            {
              j = str;
              l1 = l2;
            }
          }
          return l1;
        }
      }
      finally
      {
        ((Cursor)localObject1).close();
      }
    }
  }
  
  private boolean k()
  {
    Time localTime = new Time();
    localTime.setToNow();
    return Time.getJulianDay(n.toMillis(true), 0L) != Time.getJulianDay(localTime.toMillis(true), 0L);
  }
  
  private final iz l()
  {
    if (t == null) {
      if (!J) {
        break label33;
      }
    }
    label33:
    for (t = ((SelectConversationList)getActivity()).c();; t = new e(null)) {
      return t;
    }
  }
  
  private lx m()
  {
    if (q == null) {
      q = new jj(this, p);
    }
    return q;
  }
  
  private HashSet<Long> n()
  {
    HashSet localHashSet = new HashSet();
    long[] arrayOfLong = p.getCheckedItemIds();
    int i2 = arrayOfLong.length;
    int i1 = 0;
    while (i1 < i2)
    {
      localHashSet.add(Long.valueOf(arrayOfLong[i1]));
      i1 += 1;
    }
    return localHashSet;
  }
  
  private void o()
  {
    HashSet localHashSet = p();
    if ((localHashSet == null) || (localHashSet.size() == 0))
    {
      Log.e("ConversationList", "markCheckedItemAsRead. not exit threadId");
      return;
    }
    new d(localHashSet).execute(new Void[0]);
  }
  
  private HashSet<Long> p()
  {
    HashSet localHashSet1 = new HashSet();
    HashSet localHashSet2 = n();
    int i3 = p.getHeaderViewsCount();
    int i4 = p.getCount();
    int i5 = p.getFooterViewsCount();
    int i1 = 0;
    if (i1 < i4 - i3 - i5)
    {
      long l1 = e.getItemId(i1);
      if (l1 == 0L) {}
      label146:
      for (;;)
      {
        i1 += 1;
        break;
        if (localHashSet2.contains(Long.valueOf(l1)))
        {
          Cursor localCursor = (Cursor)p.getItemAtPosition(i1 + i3);
          if (localCursor != null)
          {
            if (localCursor.getInt(6) == 0) {}
            for (int i2 = 1;; i2 = 0)
            {
              if (i2 == 0) {
                break label146;
              }
              localHashSet1.add(Long.valueOf(l1));
              break;
            }
          }
        }
      }
    }
    return localHashSet1;
  }
  
  private void q()
  {
    if (T != null) {
      T.run();
    }
  }
  
  private void r()
  {
    if ((F == null) && (p != null) && (x != null))
    {
      F = LayoutInflater.from(x).inflate(2130968677, p, false);
      G = ((TextView)F.findViewById(2131886487));
      F.setOnClickListener(new jb(this));
    }
  }
  
  private void s()
  {
    H = LayoutInflater.from(x).inflate(2130968677, p, false);
    I = ((TextView)H.findViewById(2131886487));
    I.setText(2131493722);
    p.addHeaderView(H);
    H.setOnClickListener(new jc(this));
  }
  
  private String t()
  {
    long l1 = MmsApp.c().k();
    String str1 = "";
    if (l1 > 0L) {
      str1 = " AND threads._id != " + l1;
    }
    String str2 = str1;
    if (B != null) {
      str2 = B + str1;
    }
    return str2;
  }
  
  private boolean u()
  {
    return C == 1;
  }
  
  public AlertDialog a(b paramb, Collection<Long> paramCollection, boolean paramBoolean, Context paramContext)
  {
    return a(paramb, paramCollection, paramContext);
  }
  
  public void a()
  {
    if (g == null) {
      return;
    }
    if ((!getActivity().isFinishing()) && (g.isShowing())) {
      g.dismiss();
    }
    g = null;
  }
  
  public void a(int paramInt)
  {
    g = new ProgressDialog(x);
    g.setCancelable(false);
    g.setIndeterminate(true);
    g.setMessage(getString(paramInt));
    g.show();
  }
  
  public void a(long paramLong, AsyncQueryHandler paramAsyncQueryHandler)
  {
    ArrayList localArrayList = null;
    if (paramLong != -1L)
    {
      localArrayList = new ArrayList();
      localArrayList.add(Long.valueOf(paramLong));
    }
    a(localArrayList, paramAsyncQueryHandler);
  }
  
  public void a(long paramLong, boolean paramBoolean)
  {
    a.post(new ja(this, paramLong, paramBoolean));
  }
  
  public void a(Bundle paramBundle)
  {
    u = paramBundle;
  }
  
  public void a(AbsListView.OnScrollListener paramOnScrollListener)
  {
    z = paramOnScrollListener;
  }
  
  public void a(c paramc)
  {
    A = paramc;
  }
  
  public void a(Runnable paramRunnable)
  {
    T = paramRunnable;
  }
  
  public void a(String paramString)
  {
    B = paramString;
  }
  
  public void a(Collection<Long> paramCollection, AsyncQueryHandler paramAsyncQueryHandler)
  {
    paramAsyncQueryHandler = new b(paramCollection, a, x);
    paramAsyncQueryHandler.a(new jg(this));
    a(paramAsyncQueryHandler, paramCollection, false, x);
  }
  
  public void a(HashSet<Long> paramHashSet, boolean paramBoolean)
  {
    gr.a(x, paramBoolean, paramHashSet);
  }
  
  public void a(boolean paramBoolean)
  {
    if ((F != null) && (G != null))
    {
      if (paramBoolean)
      {
        G.setEnabled(true);
        F.setEnabled(true);
      }
    }
    else {
      return;
    }
    G.setEnabled(false);
    F.setEnabled(false);
  }
  
  public void b(int paramInt)
  {
    C = paramInt;
  }
  
  public void b(boolean paramBoolean)
  {
    J = paramBoolean;
  }
  
  public final boolean b()
  {
    if (u == null) {
      return false;
    }
    Parcelable localParcelable = u.getParcelable("ConversationListFragment.state.listState");
    o = u.getInt("ConversationListFragment.unread.msgTotal", 0);
    if (localParcelable != null) {
      p.onRestoreInstanceState(localParcelable);
    }
    u = null;
    return true;
  }
  
  public void c()
  {
    if (p != null) {
      q.d();
    }
  }
  
  public void c(int paramInt)
  {
    D = paramInt;
  }
  
  public void c(boolean paramBoolean)
  {
    V = paramBoolean;
  }
  
  public void d() {}
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    x = paramActivity;
    g();
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    getActivity().invalidateOptionsMenu();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    a = new g(getActivity().getContentResolver());
    f = new Handler();
    k = true;
    n = new Time();
    n.setToNow();
    l = false;
    j = "";
    o = -1;
    if (u != null) {
      v = u.getInt("last_list_pos", -1);
    }
    for (w = u.getInt("last_list_offset", 0);; w = 0)
    {
      setHasOptionsMenu(true);
      return;
      v = -1;
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    y = paramLayoutInflater.inflate(2130968616, paramViewGroup, false);
    return y;
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    if (e != null) {
      e.changeCursor(null);
    }
    if (b != null) {
      b.cancel(true);
    }
    a.removeMessages(1701);
    a.removeMessages(1702);
    a.a(true);
    a = null;
    u = null;
  }
  
  public void onDetach()
  {
    super.onDetach();
    x = null;
  }
  
  public void onListItemClick(ListView paramListView, View paramView, int paramInt, long paramLong)
  {
    paramListView = (Cursor)getListView().getItemAtPosition(paramInt);
    if (paramListView != null)
    {
      paramListView = gr.a(MmsApp.c(), paramListView);
      paramListView.e();
      paramListView = paramListView.x();
      if (A != null) {
        A.a(paramLong, paramListView);
      }
      return;
    }
    Log.d("ConversationList", "onListItemClick: cursor is null!");
  }
  
  public void onPause()
  {
    int i1 = 0;
    super.onPause();
    ListView localListView = getListView();
    v = localListView.getFirstVisiblePosition();
    View localView = localListView.getChildAt(0);
    if (localView == null) {}
    for (;;)
    {
      w = i1;
      MmsApp.c().b(c);
      f.removeCallbacks(P);
      return;
      i1 = localView.getTop() - localListView.getPaddingTop();
    }
  }
  
  public void onResume()
  {
    super.onResume();
    e.a(Q);
    E = ga.C();
    if (u()) {
      a(E);
    }
    MmsApp.c().a(c);
    zv.c(M);
    zv.a(N);
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    if ((t != null) && (t.b()) && (p != null))
    {
      paramBundle.putParcelable("ConversationListFragment.state.listState", p.onSaveInstanceState());
      paramBundle.putInt("ConversationListFragment.unread.msgTotal", o);
      paramBundle.putInt("ConversationListFragment.state.tabState", C);
      paramBundle.putInt("last_list_pos", v);
      paramBundle.putInt("last_list_offset", w);
    }
  }
  
  public void onStart()
  {
    super.onStart();
    if (MmsApp.c().j())
    {
      if (b != null) {
        b.cancel(true);
      }
      b = new a(x);
      b.execute(new Intent[] { new Intent("com.meizu.delmsg") });
    }
    if (!MmsApp.c().l()) {
      if (C == 0)
      {
        MessagingNotification.a(x.getApplicationContext(), 239);
        gr.a(x.getApplicationContext(), true);
      }
    }
    for (s = false;; s = true)
    {
      zt.c().a(this);
      d(false);
      MessagingNotification.b(x);
      return;
    }
  }
  
  public void onStop()
  {
    super.onStop();
    e.a(null);
    zt.c().b(this);
    zv.d(M);
    zv.b(N);
  }
  
  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    f();
    aaa.a(getListView(), true);
  }
  
  class a
    extends AsyncTask<Intent, Void, Void>
  {
    private Context b;
    
    public a(Context paramContext)
    {
      b = paramContext;
    }
    
    private boolean a(ContentResolver paramContentResolver, long paramLong, String[] paramArrayOfString)
    {
      String str = "thread_id = " + paramLong + " and " + "msg_box" + " = " + 3;
      paramArrayOfString = paramContentResolver.query(Telephony.Mms.CONTENT_URI, paramArrayOfString, str, null, null);
      if (paramArrayOfString != null)
      {
        boolean bool = false;
        try
        {
          while (paramArrayOfString.moveToNext())
          {
            if (paramContentResolver.delete(ContentUris.withAppendedId(Telephony.Mms.CONTENT_URI, paramArrayOfString.getInt(0)), null, null) > 0) {
              bool = true;
            }
            Log.v("ConversationList", "del draft threadId " + paramLong + "--- Mms " + paramArrayOfString.getInt(0));
          }
        }
        finally
        {
          paramArrayOfString.close();
        }
        return bool;
      }
      return false;
    }
    
    protected Void a(Intent... paramVarArgs)
    {
      Object localObject;
      int j;
      if (paramVarArgs[0].getAction().equals("com.meizu.delmsg"))
      {
        localObject = Telephony.Threads.CONTENT_URI.buildUpon().appendQueryParameter("simple", "true").build();
        paramVarArgs = b.getContentResolver();
        localObject = paramVarArgs.query((Uri)localObject, new String[] { "_id", "recipient_ids", "message_count" }, "recipient_ids = '' ", null, null);
        if (localObject == null) {
          break label311;
        }
        j = 0;
      }
      int k;
      for (int i = 0;; i = k)
      {
        for (;;)
        {
          try
          {
            if (((Cursor)localObject).moveToNext())
            {
              long l = ((Cursor)localObject).getLong(0);
              if (((Cursor)localObject).getInt(2) == 0)
              {
                paramVarArgs.delete(ContentUris.withAppendedId(Telephony.Threads.CONTENT_URI, l), null, null);
                Log.v("ConversationList", "del draft thread " + ((Cursor)localObject).getInt(0));
                k = i;
                break;
              }
              k = i;
              if (l <= 0L) {
                break;
              }
              boolean bool = a(paramVarArgs, l, new String[] { "_id", "thread_id", "msg_box" });
              k = i;
              if (!bool) {
                break;
              }
              k = 1;
              break;
            }
            ((Cursor)localObject).close();
            if (MmsApp.a)
            {
              if ((i != 0) || ((j > 0) && (i == 0))) {
                ConversationListFragment.f(ConversationListFragment.this).run();
              }
              if (j > 0) {
                ConversationListFragment.c(ConversationListFragment.this, false);
              }
              MmsApp.c().a(false);
              b = null;
              return null;
            }
          }
          finally
          {
            ((Cursor)localObject).close();
          }
          if (i != 0)
          {
            ConversationListFragment.f(ConversationListFragment.this).run();
            continue;
            label311:
            j = 0;
            i = 0;
          }
        }
        j += 1;
      }
    }
  }
  
  public static class b
    implements DialogInterface.OnClickListener
  {
    private final Collection<Long> a;
    private final gr.b b;
    private final Context c;
    private boolean d;
    private Runnable e;
    
    public b(Collection<Long> paramCollection, gr.b paramb, Context paramContext)
    {
      a = paramCollection;
      b = paramb;
      c = paramContext;
    }
    
    public void a()
    {
      if (a == null)
      {
        gr.a(b, 1801, d);
        zt.c().a();
      }
      for (;;)
      {
        if (e != null) {
          e.run();
        }
        return;
        gr.a(b, 1801, d, a);
        ConversationListFragment.a(a, false);
      }
    }
    
    public void a(Runnable paramRunnable)
    {
      e = paramRunnable;
    }
    
    public void onClick(DialogInterface paramDialogInterface, int paramInt)
    {
      a();
      paramDialogInterface.dismiss();
    }
  }
  
  public static abstract interface c
  {
    public abstract void a();
    
    public abstract void a(int paramInt1, int paramInt2);
    
    public abstract void a(long paramLong, String paramString);
    
    public abstract void b();
  }
  
  class d
    extends AsyncTask<Void, Void, Void>
  {
    private HashSet<Long> b;
    
    public d()
    {
      HashSet localHashSet;
      b = localHashSet;
    }
    
    protected Void a(Void... paramVarArgs)
    {
      gr.a(MmsApp.c(), b);
      return null;
    }
    
    protected void a(Void paramVoid)
    {
      if (b.size() > 20) {
        a();
      }
    }
    
    protected void onPreExecute()
    {
      if (b.size() > 20) {
        a(2131493852);
      }
    }
  }
  
  public class e
    extends iz
  {
    private MenuItem e;
    private MenuItem f;
    private MenuItem g;
    private MenuItem h;
    private MenuItem i;
    private boolean j = true;
    private boolean k = false;
    private MultiChoiceView l;
    
    private e() {}
    
    private void a(boolean paramBoolean)
    {
      boolean bool;
      if ((ConversationListFragment.s(ConversationListFragment.this)) && (ConversationListFragment.u(ConversationListFragment.this).size() > 0))
      {
        bool = true;
        if ((paramBoolean) || (!bool)) {
          break label78;
        }
        g.setTitle(getString(2131493240));
        g.setIcon(2130837803);
      }
      for (;;)
      {
        aaa.a(g, bool);
        return;
        bool = false;
        break;
        label78:
        g.setTitle(getString(2131493601));
        g.setIcon(2130837805);
      }
    }
    
    private void d()
    {
      boolean bool4 = false;
      ConversationListFragment.d(ConversationListFragment.this, false);
      if (ConversationListFragment.s(ConversationListFragment.this))
      {
        localObject = ConversationListFragment.u(ConversationListFragment.this);
        int n = ConversationListFragment.b(ConversationListFragment.this).getHeaderViewsCount();
        int i1 = ConversationListFragment.b(ConversationListFragment.this).getCount();
        int i2 = ConversationListFragment.b(ConversationListFragment.this).getFooterViewsCount();
        int m = 0;
        boolean bool2 = false;
        bool1 = bool2;
        if (m < i1 - n - i2)
        {
          long l1 = ConversationListFragment.a(ConversationListFragment.this).getItemId(m);
          boolean bool3;
          if (l1 == 0L) {
            bool3 = bool2;
          }
          label155:
          Cursor localCursor;
          label206:
          do
          {
            do
            {
              do
              {
                do
                {
                  m += 1;
                  bool2 = bool3;
                  break;
                  bool3 = bool2;
                } while (!((HashSet)localObject).contains(Long.valueOf(l1)));
                if (!bool2) {
                  break label155;
                }
                bool3 = bool2;
              } while (ConversationListFragment.w(ConversationListFragment.this));
              localCursor = (Cursor)ConversationListFragment.b(ConversationListFragment.this).getItemAtPosition(m + n);
              bool3 = bool2;
            } while (localCursor == null);
            bool1 = bool2;
            if (!bool2)
            {
              if (localCursor.getInt(6) != 0) {
                break label257;
              }
              bool1 = true;
            }
            bool3 = bool1;
          } while (ConversationListFragment.w(ConversationListFragment.this));
          ConversationListFragment localConversationListFragment = ConversationListFragment.this;
          if (localCursor.getInt(11) != 1) {}
          for (bool2 = true;; bool2 = false)
          {
            ConversationListFragment.d(localConversationListFragment, bool2);
            bool3 = bool1;
            break;
            label257:
            bool1 = false;
            break label206;
          }
        }
      }
      else
      {
        bool1 = false;
      }
      k = bool1;
      Object localObject = f;
      if ((ConversationListFragment.s(ConversationListFragment.this)) && (k))
      {
        bool1 = true;
        ((MenuItem)localObject).setEnabled(bool1);
        localObject = h;
        if ((!ConversationListFragment.s(ConversationListFragment.this)) || (ConversationListFragment.u(ConversationListFragment.this).size() <= 0)) {
          break label430;
        }
      }
      label430:
      for (boolean bool1 = true;; bool1 = false)
      {
        aaa.a((MenuItem)localObject, bool1);
        if (!ConversationListFragment.w(ConversationListFragment.this))
        {
          bool1 = bool4;
          if (ConversationListFragment.u(ConversationListFragment.this).size() != 0) {}
        }
        else
        {
          bool1 = true;
        }
        a(bool1);
        aaa.a(getActivity(), l, ConversationListFragment.b(ConversationListFragment.this).getCheckedItemCount(), ConversationListFragment.a(ConversationListFragment.this).getCount());
        return;
        bool1 = false;
        break;
      }
    }
    
    public int a(MenuItem paramMenuItem)
    {
      switch (paramMenuItem.getItemId())
      {
      case 2131886158: 
      case 2131886804: 
      case 2131886805: 
      default: 
        return 0;
      }
      return 1;
    }
    
    public boolean a(int paramInt)
    {
      if ((!b) || (paramInt == 0)) {}
      Cursor localCursor;
      do
      {
        do
        {
          return false;
        } while (ConversationListFragment.u(ConversationListFragment.this).size() == 0);
        localCursor = ConversationListFragment.a(ConversationListFragment.this).getCursor();
      } while ((localCursor == null) || (localCursor.getCount() == 0));
      d();
      return true;
    }
    
    public void c()
    {
      if (ConversationListFragment.b(ConversationListFragment.this).getCheckedItemCount() == ConversationListFragment.a(ConversationListFragment.this).getCount()) {
        ConversationListFragment.q(ConversationListFragment.this).f();
      }
      for (;;)
      {
        d();
        return;
        ConversationListFragment.q(ConversationListFragment.this).b();
      }
    }
    
    public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
    {
      if (!ConversationListFragment.s(ConversationListFragment.this)) {}
      do
      {
        return true;
        switch (paramMenuItem.getItemId())
        {
        default: 
          return true;
        case 2131886158: 
          paramActionMode = ConversationListFragment.u(ConversationListFragment.this);
        }
      } while (paramActionMode.size() == 0);
      a(paramActionMode, ConversationListFragment.w(ConversationListFragment.this));
      ConversationListFragment.this.c();
      return true;
      paramActionMode = ConversationListFragment.b(ConversationListFragment.this).getCheckedItemIds();
      paramMenuItem = new ArrayList();
      int m = 0;
      while (m < paramActionMode.length)
      {
        paramMenuItem.add(Long.valueOf(paramActionMode[m]));
        m += 1;
      }
      a(paramMenuItem, a);
      return true;
      ConversationListFragment.v(ConversationListFragment.this);
      paramActionMode.finish();
      return true;
    }
    
    public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      if ((ConversationListFragment.b(ConversationListFragment.this) == null) || (ConversationListFragment.a(ConversationListFragment.this) == null) || (ConversationListFragment.a(ConversationListFragment.this).getCursor() == null) || (ConversationListFragment.a(ConversationListFragment.this).getCount() == 0)) {
        return false;
      }
      super.onCreateActionMode(paramActionMode, paramMenu);
      ConversationListFragment.m(ConversationListFragment.this).a();
      a(2131951620, paramMenu, getActivity());
      f = paramMenu.findItem(2131886804);
      h = paramMenu.findItem(2131886798);
      i = paramMenu.findItem(2131886810);
      e = paramMenu.findItem(2131886805);
      g = paramMenu.findItem(2131886158);
      if (!ConversationListFragment.s(ConversationListFragment.this)) {
        g.setEnabled(false);
      }
      paramMenu = g.getIcon();
      if (ConversationListFragment.s(ConversationListFragment.this)) {}
      for (int m = 255;; m = 127)
      {
        paramMenu.setAlpha(m);
        l = new MultiChoiceView(getActivity());
        l.setOnCloseItemClickListener(new jl(this));
        l.setOnSelectAllItemClickListener(new jm(this));
        paramActionMode.setCustomView(l);
        a();
        aaa.a(getActivity(), ConversationListFragment.b(ConversationListFragment.this), true);
        ConversationListFragment.a(ConversationListFragment.this).a(false);
        if (ConversationListFragment.b(ConversationListFragment.this).getCheckedItemCount() == 0) {
          d();
        }
        if (ConversationListFragment.t(ConversationListFragment.this) != null) {
          ConversationListFragment.b(ConversationListFragment.this).removeFooterView(ConversationListFragment.t(ConversationListFragment.this));
        }
        return true;
      }
    }
    
    public void onDestroyActionMode(ActionMode paramActionMode)
    {
      super.onDestroyActionMode(paramActionMode);
      j = false;
      k = false;
      aaa.a(getActivity(), ConversationListFragment.b(ConversationListFragment.this), false);
      ConversationListFragment.m(ConversationListFragment.this).b();
      ConversationListFragment.a(ConversationListFragment.this).a(true);
      l.setOnCloseItemClickListener(null);
      l.setOnSelectAllItemClickListener(null);
      if (ConversationListFragment.t(ConversationListFragment.this) != null) {
        ConversationListFragment.b(ConversationListFragment.this).addFooterView(ConversationListFragment.t(ConversationListFragment.this));
      }
    }
    
    public void onItemCheckedStateChanged(ActionMode paramActionMode, int paramInt, long paramLong, boolean paramBoolean)
    {
      d();
    }
    
    public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      d();
      if (ConversationListFragment.a(ConversationListFragment.this).getCount() > 0) {
        a(ConversationListFragment.b(ConversationListFragment.this).getCheckedItemCount(), ConversationListFragment.a(ConversationListFragment.this).getCount());
      }
      return true;
    }
  }
  
  class f
    implements zv.a
  {
    private f() {}
    
    public void a(int paramInt1, int paramInt2)
    {
      if (ConversationListFragment.a(ConversationListFragment.this, paramInt1, paramInt2)) {
        ConversationListFragment.a(ConversationListFragment.this).notifyDataSetChanged();
      }
    }
  }
  
  public final class g
    extends gr.b
  {
    private boolean b = false;
    
    public g(ContentResolver paramContentResolver)
    {
      super();
    }
    
    public void a(boolean paramBoolean)
    {
      b = paramBoolean;
    }
    
    public void handleMessage(Message paramMessage)
    {
      if (b) {
        return;
      }
      switch (what)
      {
      default: 
        super.handleMessage(paramMessage);
        return;
      }
      ConversationListFragment.c(ConversationListFragment.this, true);
    }
    
    protected void onDeleteComplete(int paramInt1, Object paramObject, int paramInt2)
    {
      super.onDeleteComplete(paramInt1, paramObject, paramInt2);
      switch (paramInt1)
      {
      case 1802: 
      default: 
      case 1801: 
        do
        {
          return;
          gr.a(MmsApp.c(), (Collection)paramObject);
          if (paramInt2 > 0) {
            ConversationListFragment.c(ConversationListFragment.this, false);
          }
        } while (ConversationListFragment.q(ConversationListFragment.this) == null);
        ConversationListFragment.q(ConversationListFragment.this).d();
        return;
      }
      fl.a("onQueryComplete finished DELETE_OBSOLETE_THREADS_TOKEN", new Object[0]);
      MmsApp.c().a(false);
    }
    
    protected void onQueryComplete(int paramInt, Object paramObject, Cursor paramCursor)
    {
      int i = 1;
      int k = 0;
      int j = 0;
      if (ConversationListFragment.g(ConversationListFragment.this))
      {
        a();
        ConversationListFragment.b(ConversationListFragment.this, false);
      }
      if ((!ConversationListFragment.h(ConversationListFragment.this)) || ((paramCursor == null) && (paramInt != 1702))) {
        if (paramCursor != null) {
          paramCursor.close();
        }
      }
      do
      {
        return;
        switch (paramInt)
        {
        default: 
          if (paramCursor != null) {
            paramCursor.close();
          }
          Log.e("ConversationList", "onQueryComplete called with unknown token " + paramInt);
        }
      } while (!ConversationListFragment.p(ConversationListFragment.this));
      ConversationListFragment.b(ConversationListFragment.this).invalidateViews();
      return;
      if (((!ConversationList.b) || (!ConversationListFragment.i(ConversationListFragment.this))) && (aat.a)) {
        aat.a(a, 1809);
      }
      j = paramCursor.getCount();
      if (j < 1) {
        gr.t();
      }
      for (paramInt = i;; paramInt = 0)
      {
        if (paramInt != 0) {
          gx.a().a(false);
        }
        ConversationListFragment.a(ConversationListFragment.this).changeCursor(paramCursor);
        boolean bool = ConversationListFragment.a(ConversationListFragment.this, j);
        if ((ConversationListFragment.b(ConversationListFragment.this) != null) && ((ConversationListFragment.j(ConversationListFragment.this)) || (-1 == ConversationListFragment.k(ConversationListFragment.this)) || (bool))) {}
        for (paramInt = ConversationListFragment.a(ConversationListFragment.this, paramCursor);; paramInt = 0)
        {
          if ((ConversationListFragment.b(ConversationListFragment.this) != null) && ((ConversationListFragment.j(ConversationListFragment.this)) || (-1 == ConversationListFragment.k(ConversationListFragment.this)) || (bool)))
          {
            if (paramInt > ConversationListFragment.k(ConversationListFragment.this)) {
              ConversationListFragment.b(ConversationListFragment.this).setSelection(0);
            }
            ConversationListFragment.b(ConversationListFragment.this, paramInt);
            ConversationListFragment.a(ConversationListFragment.this, false);
          }
          ConversationListFragment.l(ConversationListFragment.this);
          break;
          paramInt = k;
          if (paramCursor != null)
          {
            paramInt = j;
            if (paramCursor.moveToNext()) {
              paramInt = paramCursor.getInt(0);
            }
            paramCursor.close();
          }
          if (!ConversationList.b) {
            break;
          }
          if (ConversationListFragment.i(ConversationListFragment.this)) {
            ConversationListFragment.c(ConversationListFragment.this, paramInt);
          }
          if (ConversationListFragment.m(ConversationListFragment.this) == null) {
            break;
          }
          ConversationListFragment.m(ConversationListFragment.this).a(paramInt, ConversationListFragment.n(ConversationListFragment.this));
          break;
          aat.a(paramCursor);
          if (!aat.c()) {
            break;
          }
          wd.q(ConversationListFragment.o(ConversationListFragment.this));
          break;
        }
      }
    }
  }
  
  class h
    implements zv.a
  {
    private h() {}
    
    public void a(int paramInt1, int paramInt2)
    {
      if (ConversationListFragment.a(ConversationListFragment.this, paramInt1, paramInt2)) {
        ConversationListFragment.a(ConversationListFragment.this).notifyDataSetChanged();
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.fragmentstyle.ConversationListFragment
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */