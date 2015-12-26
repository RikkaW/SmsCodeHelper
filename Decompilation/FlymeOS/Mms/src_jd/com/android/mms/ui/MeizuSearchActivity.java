package com.android.mms.ui;

import aaa;
import aan;
import aau;
import abl;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MzContactsContract.MzNetContacts;
import android.provider.Telephony.Mms;
import android.provider.Telephony.MmsSms;
import android.provider.Telephony.Sms;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.LayoutParams;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.LongSparseArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.android.mms.MmsApp;
import com.android.mms.MmsApp.g;
import com.android.mms.transaction.MessagingNotification;
import com.meizu.common.widget.SelectionButton;
import fm;
import ga;
import gf;
import gm;
import gq;
import gr;
import gr.b;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import ka;
import lx;
import ma;
import mb;
import uy;
import uz;
import va;
import vb;
import vc;
import vd;
import ve;
import vf;
import vg;
import vh;
import vi;
import vj;
import vk;
import vl;
import vm;
import wd;

public class MeizuSearchActivity
  extends fm
  implements AdapterView.OnItemClickListener
{
  private ProgressDialog A;
  private int B = -1;
  private boolean C = false;
  private boolean D;
  private boolean E;
  private SelectionButton F;
  public boolean a = false;
  MmsApp.g b = new vf(this);
  public Runnable c = new vl(this);
  Runnable d = new uz(this);
  private int e = 0;
  private String f = "";
  private boolean g = true;
  private EditText h;
  private ma i;
  private ImageView j;
  private ListView k;
  private lx l;
  private gf m;
  private Uri n;
  private String o = "";
  private a p;
  private int q;
  private View r;
  private View s;
  private boolean t;
  private boolean u = true;
  private ContentObserver v = null;
  private boolean w = false;
  private c x;
  private TextWatcher y = new ve(this);
  private b z = null;
  
  private long a(long paramLong1, long paramLong2, boolean paramBoolean)
  {
    Object localObject1;
    if (paramBoolean) {
      localObject1 = Uri.parse("content://mms-sms/meizuMessageIdToThread").buildUpon();
    }
    for (;;)
    {
      localObject1 = ((Uri.Builder)localObject1).appendQueryParameter("row_id", String.valueOf(paramLong1)).appendQueryParameter("table_to_use", String.valueOf(paramLong2)).build().toString();
      localObject1 = getContentResolver().query(Uri.parse((String)localObject1), null, null, null, null);
      if (localObject1 != null) {}
      try
      {
        if (((Cursor)localObject1).moveToFirst())
        {
          paramLong1 = ((Cursor)localObject1).getLong(((Cursor)localObject1).getColumnIndex("thread_id"));
          return paramLong1;
          localObject1 = Uri.parse("content://mms-sms/messageIdToThread").buildUpon();
          continue;
        }
        return -1L;
      }
      finally
      {
        ((Cursor)localObject1).close();
      }
    }
  }
  
  private String a(Context paramContext, String paramString)
  {
    long l1 = System.currentTimeMillis();
    paramContext = new StringBuilder();
    Iterator localIterator = gr.c(gq.a(paramString.toLowerCase(), true)).iterator();
    while (localIterator.hasNext())
    {
      gr localgr = (gr)localIterator.next();
      if (localgr != null) {
        paramContext.append(localgr.e()).append(";");
      }
    }
    Log.d("MeizuSearchActivity", "getThreadIdByLookupName  lookupname: " + paramString + ", use time: " + (System.currentTimeMillis() - l1));
    return paramContext.toString();
  }
  
  public static final String a(Cursor paramCursor)
  {
    if ((paramCursor == null) || (paramCursor.isClosed())) {
      return null;
    }
    return paramCursor.getLong(6) + ";" + paramCursor.getPosition();
  }
  
  private ma a(EditText paramEditText)
  {
    return new uy(this, paramEditText);
  }
  
  private void a(LongSparseArray<Long> paramLongSparseArray, long[] paramArrayOfLong)
  {
    if (((Long)paramLongSparseArray.get(paramArrayOfLong[1])).longValue() != 0L)
    {
      paramLongSparseArray.put(paramArrayOfLong[1], Long.valueOf(((Long)paramLongSparseArray.get(paramArrayOfLong[1])).longValue() + 1L));
      return;
    }
    paramLongSparseArray.put(paramArrayOfLong[1], Long.valueOf(1L));
  }
  
  private void a(HashMap<String, String> paramHashMap, boolean paramBoolean)
  {
    int i1 = 5;
    if (paramHashMap.size() == 0) {}
    StringBuilder localStringBuilder;
    do
    {
      return;
      b(true);
      localObject = paramHashMap.keySet().iterator();
      localStringBuilder = null;
      if (((Iterator)localObject).hasNext())
      {
        long l1 = b((String)((Iterator)localObject).next(), 1, 0L, 5);
        if (localStringBuilder == null)
        {
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("thread_id in (").append(l1);
        }
        for (;;)
        {
          break;
          localStringBuilder.append(",").append(l1);
        }
      }
    } while (localStringBuilder == null);
    localStringBuilder.append(")");
    localStringBuilder.append(" and type=1");
    Object localObject = p;
    if (paramBoolean) {}
    for (;;)
    {
      ((a)localObject).startQuery(i1, paramHashMap, Telephony.Sms.CONTENT_URI, null, localStringBuilder.toString(), null, null);
      return;
      i1 = 3;
    }
  }
  
  private boolean a(boolean paramBoolean)
  {
    InputMethodManager localInputMethodManager = (InputMethodManager)getSystemService("input_method");
    if ((((Boolean)aau.b(InputMethodManager.class, localInputMethodManager, "isSoftInputShown")).booleanValue()) && (!paramBoolean))
    {
      localInputMethodManager.hideSoftInputFromWindow(h.getWindowToken(), 0);
      return true;
    }
    if ((!((Boolean)aau.b(InputMethodManager.class, localInputMethodManager, "isSoftInputShown")).booleanValue()) && (paramBoolean))
    {
      localInputMethodManager.showSoftInput(h, 0);
      return true;
    }
    return false;
  }
  
  private static final long b(String paramString, int paramInt1, long paramLong, int paramInt2)
  {
    paramString = paramString.split(";");
    if ((paramString == null) || (paramString.length != paramInt2)) {
      return paramLong;
    }
    return Long.parseLong(paramString[paramInt1]);
  }
  
  public static final String b(Cursor paramCursor)
  {
    if ((paramCursor == null) || (paramCursor.isClosed())) {
      return "";
    }
    return paramCursor.getLong(0) + ";" + paramCursor.getLong(1) + ";" + paramCursor.getLong(7) + ";" + paramCursor.getLong(8) + ";" + paramCursor.getLong(12);
  }
  
  private void b()
  {
    View localView = LayoutInflater.from(this).inflate(2130968710, null);
    localView.setPadding(getResources().getDimensionPixelOffset(2131558610), 0, getResources().getDimensionPixelOffset(2131558611), 0);
    h = ((EditText)localView.findViewById(2131886209));
    h.setHint(2131493095);
    j = ((ImageView)localView.findViewById(2131886210));
    F = ((SelectionButton)localView.findViewById(2131886769));
    localView.findViewById(2131886563);
    j.setOnClickListener(new vg(this));
    ActionBar localActionBar = getSupportActionBar();
    localActionBar.setDisplayOptions(20);
    localActionBar.setCustomView(localView, new ActionBar.LayoutParams(-1, -1, 19));
    aaa.a(getSupportActionBar(), this);
  }
  
  private void b(HashMap<String, String> paramHashMap, boolean paramBoolean)
  {
    if (paramHashMap.size() == 0) {
      return;
    }
    new vd(this, paramHashMap, paramBoolean).execute(new Void[0]);
  }
  
  private void b(boolean paramBoolean)
  {
    A = new ProgressDialog(this);
    A.setCancelable(false);
    A.setIndeterminate(true);
    if (paramBoolean) {
      A.setMessage(getString(2131493366));
    }
    for (;;)
    {
      A.show();
      return;
      A.setMessage(getString(2131493280));
    }
  }
  
  private final boolean b(HashMap<String, String> paramHashMap)
  {
    if (paramHashMap.size() == 0) {
      return false;
    }
    paramHashMap = paramHashMap.entrySet().iterator();
    while (paramHashMap.hasNext()) {
      if (b((String)((Map.Entry)paramHashMap.next()).getValue(), 0, 1L, 2) == 0L) {
        return true;
      }
    }
    return false;
  }
  
  private void c()
  {
    e = getIntent().getIntExtra("search_from", 0);
    String str = getIntent().getStringExtra("query");
    switch (e)
    {
    default: 
      return;
    case 0: 
      localObject = str;
      if (str == null) {
        localObject = getIntent().getStringExtra("intent_extra_data_key");
      }
      if (localObject != null) {}
      for (localObject = ((String)localObject).trim();; localObject = "")
      {
        f = ((String)localObject);
        localObject = getIntent().getData();
        if ((localObject == null) || (((Uri)localObject).getQueryParameter("source_id") == null)) {
          break;
        }
        new Thread(new vh(this, (Uri)localObject)).start();
        return;
      }
    }
    if (str != null) {}
    for (Object localObject = str.trim();; localObject = "")
    {
      f = ((String)localObject);
      return;
    }
  }
  
  private final boolean c(Cursor paramCursor)
  {
    return abl.a(this, paramCursor.getLong(1));
  }
  
  private final boolean c(HashMap<String, String> paramHashMap)
  {
    int i1 = paramHashMap.size();
    if (i1 == 0) {
      return false;
    }
    long[] arrayOfLong = new long[i1];
    paramHashMap = paramHashMap.entrySet().iterator();
    i1 = 0;
    while (paramHashMap.hasNext())
    {
      arrayOfLong[i1] = b((String)((Map.Entry)paramHashMap.next()).getKey(), 1, 0L, 5);
      i1 += 1;
    }
    return abl.a(this, arrayOfLong);
  }
  
  private static final long[] c(String paramString)
  {
    paramString = paramString.split(";");
    if ((paramString == null) || (paramString.length != 5)) {
      return null;
    }
    long[] arrayOfLong = new long[5];
    int i1 = 0;
    while (i1 < arrayOfLong.length)
    {
      arrayOfLong[i1] = Long.parseLong(paramString[i1]);
      i1 += 1;
    }
    return arrayOfLong;
  }
  
  private final String d(HashMap<String, String> paramHashMap)
  {
    String str = getString(2131493593);
    Iterator localIterator = paramHashMap.keySet().iterator();
    gq localgq = null;
    int i2 = paramHashMap.size();
    long[] arrayOfLong = new long[i2];
    int i1 = 0;
    while (i1 < i2)
    {
      arrayOfLong[i1] = -1L;
      i1 += 1;
    }
    for (;;)
    {
      i2 = i1;
      long l1;
      if (localIterator.hasNext())
      {
        l1 = b((String)localIterator.next(), 1, 0L, 5);
        localgq = gr.a(MmsApp.c(), l1, true).h();
        i2 = 0;
      }
      for (;;)
      {
        if ((i2 >= i1) || (l1 == arrayOfLong[i2]))
        {
          if (i2 < i1) {
            break;
          }
          arrayOfLong[i1] = l1;
          i1 += 1;
          if (i1 <= 3) {
            break label177;
          }
          i2 = i1;
          if (i2 <= 3) {
            break label251;
          }
          return paramHashMap.toString() + getString(2131493591);
        }
        i2 += 1;
      }
      label177:
      if (paramHashMap == null) {
        paramHashMap = new StringBuilder(((gm)localgq.get(0)).d().replaceAll(" ", "").trim());
      }
      for (;;)
      {
        break;
        paramHashMap.append(str).append(((gm)localgq.get(0)).d().replaceAll(" ", "").trim());
      }
      label251:
      return paramHashMap.toString() + getString(2131493590);
      i1 = 0;
      paramHashMap = localgq;
    }
  }
  
  private void d()
  {
    l = new vk(this, k);
  }
  
  private static final long[] d(String paramString)
  {
    paramString = paramString.split(";");
    if ((paramString == null) || (paramString.length != 2)) {
      return null;
    }
    long[] arrayOfLong = new long[2];
    int i1 = 0;
    while (i1 < arrayOfLong.length)
    {
      arrayOfLong[i1] = Long.parseLong(paramString[i1]);
      i1 += 1;
    }
    return arrayOfLong;
  }
  
  private void e()
  {
    if (isFinishing()) {
      return;
    }
    if (TextUtils.isEmpty(o))
    {
      x.a(true);
      p.removeCallbacks(d);
      s.setVisibility(8);
      r.setVisibility(8);
      t = false;
      return;
    }
    p.postDelayed(c, 500L);
  }
  
  private void e(HashMap<String, String> paramHashMap)
  {
    if (paramHashMap.size() == 0) {
      return;
    }
    new vb(this, paramHashMap).execute(new Void[0]);
  }
  
  private void f()
  {
    int i2 = k.getChildCount();
    int i1 = 0;
    if (i1 < i2)
    {
      View localView = k.getChildAt(i1);
      if ((localView == null) || (localView.isShown())) {}
      for (;;)
      {
        i1 += 1;
        break;
        localView.setVisibility(0);
      }
    }
  }
  
  private final void g()
  {
    if (A == null) {
      return;
    }
    if (A.isShowing()) {
      A.dismiss();
    }
    A = null;
  }
  
  public void a(HashMap<String, String> paramHashMap)
  {
    View localView = View.inflate(this, 2130968621, null);
    ((TextView)localView.findViewById(2131886260)).setVisibility(8);
    CheckBox localCheckBox = (CheckBox)localView.findViewById(2131886261);
    localCheckBox.setVisibility(0);
    if (paramHashMap.size() == 0) {
      return;
    }
    String str = d(paramHashMap);
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    if (paramHashMap.size() == 1) {}
    for (int i1 = 2131493269;; i1 = 2131493270)
    {
      localBuilder.setTitle(i1).setMessage(str).setPositiveButton(getString(2131493256), new vc(this, paramHashMap, localCheckBox)).setNegativeButton(getString(2131493255), null).setView(localView).show();
      return;
    }
  }
  
  public void finish()
  {
    super.finish();
    aaa.g(this);
    a(false);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    c();
    setContentView(2130968728);
    if (MmsApp.n) {
      setTheme(2131624164);
    }
    for (;;)
    {
      b();
      o = f;
      t = false;
      q = 6;
      x = new c(q, false);
      p = new a(getContentResolver());
      s = findViewById(2131886375);
      r = findViewById(2131886591);
      h.setOnKeyListener(new vi(this));
      h.addTextChangedListener(y);
      i = a(h);
      i.a(false);
      k = ((ListView)findViewById(16908298));
      k.setOnItemClickListener(this);
      d();
      l.a(true);
      l.a();
      k.setOnScrollListener(new vj(this));
      m = new gf(this, o);
      k.setAdapter(m);
      k.setRecyclerListener(m);
      k.setEmptyView(findViewById(16908292));
      if (TextUtils.isEmpty(o)) {
        k.getEmptyView().setVisibility(8);
      }
      aaa.a(k, true);
      s.setVisibility(8);
      r.setVisibility(8);
      if ((e == 0) && (!TextUtils.isEmpty(f))) {
        g = false;
      }
      h.setText(o);
      h.setSelection(o.length());
      E = ga.C();
      return;
      setTheme(2131624165);
    }
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    if (h != null) {
      h.removeTextChangedListener(y);
    }
    p.cancelOperation(290);
    if (v != null) {
      getContentResolver().unregisterContentObserver(v);
    }
  }
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    paramAdapterView = paramView.findViewById(2131886592);
    if ((paramAdapterView == null) || (paramAdapterView.getTag() == null)) {
      return;
    }
    if (new mb((InputMethodManager)getSystemService("input_method")).c()) {
      a(false);
    }
    for (paramLong = 50L;; paramLong = 0L)
    {
      paramView = new va(this, paramAdapterView);
      if (paramLong > 0L)
      {
        paramAdapterView.postDelayed(paramView, paramLong);
        return;
      }
      paramView.run();
      return;
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
      onBackPressed();
    }
  }
  
  protected void onPause()
  {
    super.onPause();
    g = wd.g(this);
  }
  
  protected void onResume()
  {
    u = false;
    super.onResume();
    Window localWindow = getWindow();
    if (g) {}
    for (int i1 = 5;; i1 = 3)
    {
      localWindow.setSoftInputMode(i1 | 0x10);
      return;
    }
  }
  
  protected void onStart()
  {
    super.onStart();
    if ((a) && (!t)) {
      e();
    }
    a = false;
  }
  
  public void onStop()
  {
    super.onStop();
    u = true;
  }
  
  public class a
    extends gr.b
  {
    public a(ContentResolver paramContentResolver)
    {
      super();
    }
    
    private void a(long paramLong)
    {
      Object localObject = gr.a(MmsApp.c(), paramLong, false);
      if (localObject != null)
      {
        localObject = ((gr)localObject).h().iterator();
        while (((Iterator)localObject).hasNext()) {
          ((gm)((Iterator)localObject).next()).a();
        }
      }
    }
    
    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      switch (what)
      {
      }
      do
      {
        return;
        MeizuSearchActivity.d(MeizuSearchActivity.this).removeCallbacks(d);
        MeizuSearchActivity.d(MeizuSearchActivity.this).postDelayed(d, 500L);
        if (MeizuSearchActivity.n(MeizuSearchActivity.this) == null) {
          MeizuSearchActivity.a(MeizuSearchActivity.this, new vm(this, new Handler()));
        }
        for (;;)
        {
          MeizuSearchActivity.a(MeizuSearchActivity.this, Telephony.MmsSms.SEARCH_URI.buildUpon().appendQueryParameter("pattern", MeizuSearchActivity.m(MeizuSearchActivity.this)).appendQueryParameter("meizuMmsSearch", "true").build());
          paramMessage = MeizuSearchActivity.a(MeizuSearchActivity.this, getApplicationContext(), MeizuSearchActivity.m(MeizuSearchActivity.this));
          MeizuSearchActivity.d(MeizuSearchActivity.this).startQuery(MeizuSearchActivity.p(MeizuSearchActivity.this), null, MeizuSearchActivity.q(MeizuSearchActivity.this), null, null, new String[] { paramMessage }, null);
          getContentResolver().registerContentObserver(MeizuSearchActivity.q(MeizuSearchActivity.this), true, MeizuSearchActivity.n(MeizuSearchActivity.this));
          return;
          getContentResolver().unregisterContentObserver(MeizuSearchActivity.n(MeizuSearchActivity.this));
        }
        MeizuSearchActivity.r(MeizuSearchActivity.this).setVisibility(8);
      } while (MeizuSearchActivity.c.a(MeizuSearchActivity.s(MeizuSearchActivity.this)));
      MeizuSearchActivity.t(MeizuSearchActivity.this).setVisibility(0);
    }
    
    protected void onDeleteComplete(int paramInt1, Object paramObject, int paramInt2)
    {
      super.onDeleteComplete(paramInt1, paramObject, paramInt2);
      switch (paramInt1)
      {
      }
      do
      {
        do
        {
          return;
          if (paramInt2 <= 0)
          {
            MeizuSearchActivity.u(MeizuSearchActivity.this);
            MeizuSearchActivity.b(MeizuSearchActivity.this, false);
          }
          paramObject = (Collection[])paramObject;
        } while (paramObject.length != 3);
        if (paramObject[0].size() > 0) {}
        for (paramInt1 = 1;; paramInt1 = 0)
        {
          Iterator localIterator = paramObject[0].iterator();
          while (localIterator.hasNext())
          {
            a(((Long)localIterator.next()).longValue());
            MmsApp.c().e().a(ContentUris.withAppendedId(Telephony.MmsSms.CONTENT_CONVERSATIONS_URI, ((Long)localIterator.next()).longValue()));
          }
        }
        paramObject = paramObject[1].iterator();
        while (((Iterator)paramObject).hasNext()) {
          MmsApp.c().e().a(ContentUris.withAppendedId(Telephony.Mms.CONTENT_URI, ((Long)((Iterator)paramObject).next()).longValue()));
        }
        if (paramInt1 != 0) {
          gr.b(MmsApp.c());
        }
        MessagingNotification.a(MeizuSearchActivity.this, -2L, false);
        MessagingNotification.e(MeizuSearchActivity.this);
      } while (paramInt2 <= 0);
      MeizuSearchActivity.d(MeizuSearchActivity.this, false);
      MeizuSearchActivity.f(MeizuSearchActivity.this);
      MeizuSearchActivity.d(MeizuSearchActivity.this, true);
    }
    
    protected void onQueryComplete(int paramInt, Object paramObject, Cursor paramCursor)
    {
      MeizuSearchActivity.d(MeizuSearchActivity.this).removeCallbacks(d);
      switch (paramInt)
      {
      case 4: 
      default: 
        if (MeizuSearchActivity.v(MeizuSearchActivity.this))
        {
          MeizuSearchActivity.u(MeizuSearchActivity.this);
          MeizuSearchActivity.b(MeizuSearchActivity.this, false);
        }
        if ((paramInt == MeizuSearchActivity.p(MeizuSearchActivity.this)) && (!MeizuSearchActivity.s(MeizuSearchActivity.this).a()))
        {
          MeizuSearchActivity.t(MeizuSearchActivity.this).setVisibility(8);
          MeizuSearchActivity.r(MeizuSearchActivity.this).setVisibility(0);
          MeizuSearchActivity.c(MeizuSearchActivity.this, false);
          if (paramCursor != null)
          {
            MeizuSearchActivity.g(MeizuSearchActivity.this).a(paramCursor, MeizuSearchActivity.m(MeizuSearchActivity.this));
            paramInt = paramCursor.getCount();
            if (MeizuSearchActivity.h(MeizuSearchActivity.this) != null)
            {
              if (MeizuSearchActivity.w(MeizuSearchActivity.this))
              {
                MeizuSearchActivity.d(MeizuSearchActivity.this, false);
                MeizuSearchActivity.h(MeizuSearchActivity.this).c();
              }
              MeizuSearchActivity.h(MeizuSearchActivity.this).a(paramInt);
            }
          }
          if (MeizuSearchActivity.x(MeizuSearchActivity.this))
          {
            MeizuSearchActivity.e(MeizuSearchActivity.this, false);
            if ((MeizuSearchActivity.y(MeizuSearchActivity.this) > 0) && (MeizuSearchActivity.g(MeizuSearchActivity.this).getCount() > MeizuSearchActivity.y(MeizuSearchActivity.this)))
            {
              MeizuSearchActivity.c(MeizuSearchActivity.this).setSelection(MeizuSearchActivity.y(MeizuSearchActivity.this));
              MeizuSearchActivity.a(MeizuSearchActivity.this, 0);
            }
          }
          return;
        }
        break;
      }
      for (int i = 1; ((paramObject != null) || ((paramObject instanceof Collection))) && (paramCursor != null); i = 0)
      {
        HashMap localHashMap = (HashMap)paramObject;
        paramCursor.close();
        paramCursor = localHashMap.keySet().iterator();
        int j = localHashMap.size();
        long[] arrayOfLong = new long[j];
        paramInt = 0;
        while (paramInt < j)
        {
          arrayOfLong[paramInt] = -1L;
          paramInt += 1;
        }
        for (;;)
        {
          if (paramCursor.hasNext())
          {
            long l = MeizuSearchActivity.a((String)paramCursor.next(), 1, 0L, 5);
            j = 0;
            for (;;)
            {
              if ((j >= paramInt) || (l == arrayOfLong[j]))
              {
                if (j < paramInt) {
                  break;
                }
                j = paramInt + 1;
                arrayOfLong[paramInt] = l;
                paramObject = gr.a(MmsApp.c(), l, true);
                if (paramObject == null) {
                  break label517;
                }
                paramObject = ((gr)paramObject).h();
                if (((gq)paramObject).size() == 1) {
                  break label469;
                }
                paramCursor.remove();
                paramInt = j;
                break;
              }
              j += 1;
            }
            label469:
            gm localgm = (gm)((gq)paramObject).get(0);
            String str = localgm.d();
            if (MzContactsContract.MzNetContacts.isYPContact(localgm.n())) {}
            for (paramObject = ((gm)((gq)paramObject).get(0)).g();; paramObject = null)
            {
              abl.a(str, (String)paramObject, null);
              label517:
              paramInt = j;
              break;
            }
          }
          if ((localHashMap.size() == 0) || (i == 0))
          {
            MeizuSearchActivity.u(MeizuSearchActivity.this);
            MeizuSearchActivity.c(MeizuSearchActivity.this).invalidateViews();
            return;
          }
          if (i == 0) {
            break;
          }
          MeizuSearchActivity.a(MeizuSearchActivity.this, localHashMap, true);
          return;
          if (paramCursor == null) {
            break;
          }
          paramCursor.close();
          return;
          paramInt = 0;
        }
      }
    }
    
    protected void onUpdateComplete(int paramInt1, Object paramObject, int paramInt2)
    {
      super.onUpdateComplete(paramInt1, paramObject, paramInt2);
      switch (paramInt1)
      {
      }
      do
      {
        return;
      } while (paramInt2 <= 0);
      Log.d("MeizuSearchActivity", "--更新未读标识：result = " + paramInt2);
      MeizuSearchActivity.f(MeizuSearchActivity.this);
    }
  }
  
  public class b
    extends ka
  {
    private MenuItem f;
    private MenuItem g;
    private MenuItem h;
    private MenuItem i;
    private HashMap<String, String> j;
    private boolean k;
    private boolean l;
    
    public int a(int paramInt, long paramLong)
    {
      boolean bool2 = true;
      paramInt = MeizuSearchActivity.i(e).e() - MeizuSearchActivity.c(e).getHeaderViewsCount();
      Cursor localCursor;
      if ((MeizuSearchActivity.g(e) != null) && (paramInt >= 0))
      {
        localCursor = (Cursor)MeizuSearchActivity.g(e).getItem(paramInt);
        if ((localCursor != null) && (!localCursor.isClosed())) {}
      }
      else
      {
        return 0;
      }
      MenuItem localMenuItem = f;
      if ((MeizuSearchActivity.a(e, localCursor)) && (MeizuSearchActivity.B(e)))
      {
        bool1 = true;
        localMenuItem.setEnabled(bool1);
        localMenuItem = g;
        if ((localCursor.getInt(6) != 0) || (!MeizuSearchActivity.B(e))) {
          break label163;
        }
      }
      label163:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        localMenuItem.setEnabled(bool1);
        return 0;
        bool1 = false;
        break;
      }
    }
    
    public int a(MenuItem paramMenuItem)
    {
      switch (paramMenuItem.getItemId())
      {
      case 2131886804: 
      case 2131886805: 
      default: 
        return 0;
      }
      return 1;
    }
    
    protected void a(int paramInt1, int paramInt2)
    {
      int m = 0;
      boolean bool;
      SelectionButton localSelectionButton;
      if (d != null)
      {
        d.setCurrentCount(paramInt1);
        d.setTotalCount(paramInt2);
        if (!c)
        {
          if (paramInt1 <= 0) {
            break label65;
          }
          bool = true;
          c = bool;
          localSelectionButton = d;
          if (!bool) {
            break label71;
          }
        }
      }
      label65:
      label71:
      for (paramInt1 = m;; paramInt1 = 4)
      {
        localSelectionButton.setVisibility(paramInt1);
        return;
        bool = false;
        break;
      }
    }
    
    public void a(MenuItem paramMenuItem, int paramInt, long paramLong)
    {
      switch (paramMenuItem.getItemId())
      {
      }
      for (;;)
      {
        MeizuSearchActivity.C(e);
        return;
        paramMenuItem = new HashMap();
        Cursor localCursor = (Cursor)MeizuSearchActivity.g(e).getItem(paramInt);
        if ((localCursor != null) && (!localCursor.isClosed()))
        {
          paramMenuItem.put(MeizuSearchActivity.b(localCursor), MeizuSearchActivity.a(localCursor));
          MeizuSearchActivity.c(e).setItemChecked(paramInt, false);
          MeizuSearchActivity.f(e, false);
          MeizuSearchActivity.a(e, paramMenuItem, false);
          continue;
          paramMenuItem = new HashMap();
          localCursor = (Cursor)MeizuSearchActivity.g(e).getItem(paramInt);
          if ((localCursor != null) && (!localCursor.isClosed()))
          {
            paramMenuItem.put(MeizuSearchActivity.b(localCursor), MeizuSearchActivity.a(localCursor));
            MeizuSearchActivity.c(e).setItemChecked(paramInt, false);
            MeizuSearchActivity.c(e, paramMenuItem);
          }
        }
      }
    }
    
    public boolean a(int paramInt)
    {
      if ((!b) || (j == null) || (paramInt == 0) || (MeizuSearchActivity.c(e).getCheckedItemIds().length == 0)) {
        return false;
      }
      if (d != null)
      {
        d.setCurrentCount(j.size());
        d.setTotalCount(paramInt);
      }
      return true;
    }
    
    public void b()
    {
      boolean bool3 = true;
      if (d.isAllSelected())
      {
        MeizuSearchActivity.i(e).d();
        return;
      }
      MeizuSearchActivity.i(e).b();
      int m = MeizuSearchActivity.g(e).getCount();
      d.setCurrentCount(m);
      Object localObject1 = MeizuSearchActivity.g(e).getCursor();
      ((Cursor)localObject1).moveToPosition(-1);
      m = 0;
      boolean bool1 = true;
      while (((Cursor)localObject1).moveToNext())
      {
        long l1 = ((Cursor)localObject1).getLong(1);
        Object localObject2 = MeizuSearchActivity.b((Cursor)localObject1);
        if (!j.containsKey(localObject2)) {
          j.put(localObject2, MeizuSearchActivity.a((Cursor)localObject1));
        }
        if ((bool1) || (m == 0))
        {
          localObject2 = gr.a(MmsApp.c(), l1, true);
          if (localObject2 != null)
          {
            boolean bool2 = bool1;
            if (bool1 == true)
            {
              bool2 = bool1;
              if (((gr)localObject2).h().size() != 1) {
                bool2 = false;
              }
            }
            bool1 = bool2;
            if (m == 0) {
              if (((Cursor)localObject1).getLong(6) == 0L)
              {
                m = 1;
                bool1 = bool2;
              }
              else
              {
                m = 0;
                bool1 = bool2;
              }
            }
          }
        }
      }
      k = bool1;
      localObject1 = f;
      if ((bool1) && (MeizuSearchActivity.B(e)))
      {
        bool1 = true;
        ((MenuItem)localObject1).setEnabled(bool1);
        localObject1 = g;
        if ((m == 0) || (!MeizuSearchActivity.B(e))) {
          break label289;
        }
      }
      label289:
      for (bool1 = bool3;; bool1 = false)
      {
        ((MenuItem)localObject1).setEnabled(bool1);
        return;
        bool1 = false;
        break;
      }
    }
    
    public void b(int paramInt, long paramLong)
    {
      MeizuSearchActivity.C(e);
      if (f != null) {
        f.setEnabled(k);
      }
      if (g != null) {
        g.setEnabled(l);
      }
    }
    
    protected void b(boolean paramBoolean)
    {
      if (paramBoolean)
      {
        MeizuSearchActivity.A(e).setVisibility(0);
        return;
      }
      MeizuSearchActivity.A(e).setVisibility(4);
    }
    
    public void c()
    {
      Object localObject1 = MeizuSearchActivity.g(e).getCursor();
      if ((localObject1 == null) || (((Cursor)localObject1).getCount() == 0) || (j.size() == 0)) {}
      for (;;)
      {
        return;
        Object localObject2 = (HashMap)j.clone();
        ((Cursor)localObject1).moveToFirst();
        do
        {
          ((HashMap)localObject2).remove(MeizuSearchActivity.b((Cursor)localObject1));
        } while ((((Cursor)localObject1).moveToNext()) && (((HashMap)localObject2).size() > 0));
        if (((HashMap)localObject2).size() != 0)
        {
          localObject1 = ((HashMap)localObject2).keySet().iterator();
          while (((Iterator)localObject1).hasNext())
          {
            localObject2 = (String)((Iterator)localObject1).next();
            j.remove(localObject2);
          }
        }
      }
    }
    
    public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
    {
      if (!MeizuSearchActivity.B(e)) {}
      do
      {
        do
        {
          return true;
          switch (paramMenuItem.getItemId())
          {
          default: 
            return true;
          case 2131886798: 
            i.setTitle(wd.b(e, j.size(), 2131493281));
            return true;
          }
        } while (j.size() <= 0);
        e.a(j);
        return true;
      } while (j.size() <= 0);
      MeizuSearchActivity.f(e, false);
      MeizuSearchActivity.a(e, (HashMap)j.clone(), false);
      paramActionMode.finish();
      return true;
      MeizuSearchActivity.c(e, (HashMap)j.clone());
      paramActionMode.finish();
      return true;
    }
    
    public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      MeizuSearchActivity.a(e, false);
      if ((MeizuSearchActivity.g(e) == null) || (MeizuSearchActivity.g(e).getCursor() == null) || (MeizuSearchActivity.g(e).getCount() == 0)) {
        return false;
      }
      if (j == null) {
        j = new HashMap();
      }
      a(2131951625, paramMenu, e);
      g = paramMenu.findItem(2131886804);
      h = paramMenu.findItem(2131886798);
      i = paramMenu.findItem(2131886810);
      f = paramMenu.findItem(2131886805);
      a(paramMenu, MeizuSearchActivity.g(e).getCount(), MeizuSearchActivity.A(e));
      return true;
    }
    
    public void onDestroyActionMode(ActionMode paramActionMode)
    {
      super.onDestroyActionMode(paramActionMode);
      l = false;
      if (j != null) {
        j.clear();
      }
      if ((MeizuSearchActivity.A(e) != null) && (MeizuSearchActivity.A(e).getVisibility() == 0)) {
        MeizuSearchActivity.A(e).setVisibility(4);
      }
    }
    
    public void onItemCheckedStateChanged(ActionMode paramActionMode, int paramInt, long paramLong, boolean paramBoolean)
    {
      boolean bool = true;
      paramActionMode = (Cursor)MeizuSearchActivity.g(e).getItem(paramInt);
      if ((paramActionMode == null) || (paramActionMode.isClosed())) {}
      while (MeizuSearchActivity.g(e).getItemId(paramInt) != paramLong) {
        return;
      }
      String str = MeizuSearchActivity.b(paramActionMode);
      if (paramBoolean)
      {
        j.put(str, MeizuSearchActivity.a(paramActionMode));
        if (j.size() > 0)
        {
          d.setCurrentCount(j.size());
          if (j.size() <= 0) {
            break label229;
          }
          paramBoolean = true;
          label110:
          a(paramBoolean);
        }
        k = MeizuSearchActivity.a(e, j);
        paramActionMode = f;
        if ((!k) || (!MeizuSearchActivity.B(e))) {
          break label235;
        }
        paramBoolean = true;
        label156:
        paramActionMode.setEnabled(paramBoolean);
        l = MeizuSearchActivity.b(e, j);
        paramActionMode = g;
        if ((!l) || (!MeizuSearchActivity.B(e))) {
          break label241;
        }
      }
      label229:
      label235:
      label241:
      for (paramBoolean = bool;; paramBoolean = false)
      {
        paramActionMode.setEnabled(paramBoolean);
        return;
        j.remove(str);
        break;
        paramBoolean = false;
        break label110;
        paramBoolean = false;
        break label156;
      }
    }
    
    public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      boolean bool2 = false;
      b(true);
      if (j != null) {
        a(j.size(), MeizuSearchActivity.g(e).getCount());
      }
      k = MeizuSearchActivity.a(e, j);
      paramActionMode = f;
      if ((k) && (MeizuSearchActivity.B(e))) {}
      for (boolean bool1 = true;; bool1 = false)
      {
        paramActionMode.setEnabled(bool1);
        l = MeizuSearchActivity.b(e, j);
        paramActionMode = g;
        bool1 = bool2;
        if (l)
        {
          bool1 = bool2;
          if (MeizuSearchActivity.B(e)) {
            bool1 = true;
          }
        }
        paramActionMode.setEnabled(bool1);
        h.setEnabled(MeizuSearchActivity.B(e));
        return true;
      }
    }
  }
  
  public class c
  {
    private int b;
    private boolean c;
    
    public c(int paramInt, boolean paramBoolean)
    {
      b = paramInt;
      c = paramBoolean;
    }
    
    public void a(int paramInt)
    {
      b = paramInt;
    }
    
    public void a(boolean paramBoolean)
    {
      c = paramBoolean;
    }
    
    public boolean a()
    {
      return c;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MeizuSearchActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */