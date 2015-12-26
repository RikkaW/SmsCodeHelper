package com.android.mms.view;

import aau;
import aer;
import aes;
import aet;
import aeu;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.mms.MmsApp;
import com.android.mms.ui.ComposeMessageActivity;
import gm;
import gq;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import nd.a;
import nd.b;
import nd.c;
import wd;
import xh;
import zv;
import zw;

public class MzContactHeaderWidget
  extends LinearLayout
{
  protected String a;
  public gq b;
  protected ContentResolver c;
  protected HashMap<String, nd.c> d;
  protected HashMap<String, nd.c> e;
  private ViewGroup f;
  private TextView g;
  private TextView h;
  private TextView i;
  private String j;
  private boolean k = false;
  private gq l;
  private nd.b m;
  private nd.c n;
  private int o;
  private int p;
  private int q;
  private ArrayList<String> r;
  private boolean s = true;
  private boolean t = true;
  private nd.a u;
  private Context v;
  private xh w;
  private a x;
  private AlertDialog y;
  private String[] z;
  
  public MzContactHeaderWidget(Context paramContext)
  {
    super(paramContext);
    v = paramContext;
  }
  
  public MzContactHeaderWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    v = paramContext;
  }
  
  public MzContactHeaderWidget(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    v = paramContext;
  }
  
  public MzContactHeaderWidget(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
    v = paramContext;
  }
  
  private void a(int paramInt)
  {
    if ((y != null) && (y.isShowing()))
    {
      y.hide();
      y = null;
      z = null;
    }
    z = new String[paramInt];
    if (paramInt < 100)
    {
      int i1 = 0;
      while (i1 < paramInt)
      {
        z[i1] = ((gm)b.get(i1)).g();
        i1 += 1;
      }
      l();
      return;
    }
    new b(z).execute(new Integer[] { Integer.valueOf(paramInt) });
  }
  
  private void a(String paramString)
  {
    paramString = new Intent("android.intent.action.CALL", Uri.parse("tel:" + paramString));
    v.startActivity(paramString);
  }
  
  private void d(gm paramgm)
  {
    if (paramgm.k())
    {
      g.setText(paramgm.g());
      h.setText(paramgm.d());
      h.setVisibility(0);
      b.add(paramgm);
      return;
    }
    setDisplayName(paramgm.g());
    b.add(paramgm);
  }
  
  private void e(gm paramgm)
  {
    if ((paramgm.k()) || (!TextUtils.isEmpty(paramgm.h())))
    {
      g.setText(paramgm.g());
      String str = paramgm.d();
      if (str.equals("4007883333"))
      {
        str = MmsApp.c().getString(2131493732);
        h.setText(str);
        if ((!paramgm.p()) || (!TextUtils.isEmpty(paramgm.h()))) {
          break label106;
        }
        h.setVisibility(8);
      }
      for (;;)
      {
        b.add(paramgm);
        return;
        h.setText(str);
        break;
        label106:
        h.setVisibility(0);
      }
    }
    setDisplayName(paramgm.g());
    b.add(paramgm);
    n();
  }
  
  private nd.c getSipDestState()
  {
    if (s) {
      return getSipState();
    }
    return nd.c.a;
  }
  
  private nd.c getSipState()
  {
    HashMap localHashMap = d;
    if ((b.size() > 1) && (!r.isEmpty()))
    {
      if (e.isEmpty())
      {
        if (n != null) {
          return n;
        }
        return nd.c.a;
      }
      localHashMap = e;
    }
    for (;;)
    {
      Iterator localIterator = b.iterator();
      int i1 = 0;
      if (localIterator.hasNext())
      {
        nd.c localc = (nd.c)localHashMap.get(((gm)localIterator.next()).d());
        if ((localc == null) && (n != null)) {
          return n;
        }
        if ((localc == null) || ((localc != nd.c.c) && (localc != nd.c.b))) {
          return nd.c.a;
        }
        if (localc != nd.c.b) {
          break label164;
        }
        i1 = 1;
      }
      label164:
      for (;;)
      {
        break;
        if (i1 != 0) {
          return nd.c.b;
        }
        return nd.c.c;
      }
    }
  }
  
  private void l()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(v, 2131624313);
    localBuilder.setTitle(2131493709);
    localBuilder.setItems(z, new aeu(this));
    y = localBuilder.create();
    y.show();
  }
  
  private void m()
  {
    w = new xh(v);
  }
  
  private void n()
  {
    if ((k) && (!TextUtils.isEmpty(j)))
    {
      h.setText(j);
      h.setVisibility(0);
    }
    do
    {
      return;
      j = zw.a(v, a);
      k = true;
    } while (TextUtils.isEmpty(j));
    h.setText(j);
    h.setVisibility(0);
  }
  
  public void a()
  {
    if (f != null) {
      f.setOnClickListener(new aer(this));
    }
  }
  
  public void a(MenuItem paramMenuItem)
  {
    if ((getVisibility() == 0) && (zv.k())) {}
    for (boolean bool = true;; bool = false)
    {
      paramMenuItem.setVisible(bool);
      return;
    }
  }
  
  public void a(gm paramgm)
  {
    String str = paramgm.d();
    if ((wd.h(str)) || (wd.g(str)))
    {
      c(paramgm);
      return;
    }
    b(paramgm);
  }
  
  public void a(gq paramgq)
  {
    h.setText("");
    h.setVisibility(8);
    a = null;
    b = paramgq;
    l.clear();
    if ((paramgq == null) || (paramgq.size() == 0))
    {
      setDisplayName(null);
      return;
    }
    StringBuffer localStringBuffer = new StringBuffer();
    int i2 = paramgq.size();
    paramgq = paramgq.iterator();
    int i1 = 0;
    while (paramgq.hasNext())
    {
      gm localgm = (gm)paramgq.next();
      l.add(localgm);
      localStringBuffer.append(localgm.g());
      if (i1 < i2 - 1) {
        localStringBuffer.append("，");
      }
      i1 += 1;
    }
    g.setText(localStringBuffer);
    h.setText(v.getString(2131493719, new Object[] { Integer.valueOf(i2) }));
    h.setVisibility(0);
  }
  
  public void a(String paramString, nd.c paramc, int paramInt)
  {
    if (TextUtils.isEmpty(paramString)) {
      return;
    }
    Message.obtain(x, 2, new Object[] { paramString, paramc, Integer.valueOf(paramInt) }).sendToTarget();
  }
  
  public void a(nd.c paramc, int paramInt)
  {
    n = paramc;
    o = paramInt;
  }
  
  public void b()
  {
    boolean bool = true;
    if (b == null) {
      return;
    }
    if (b.size() == 1)
    {
      gm localgm = gm.a(((gm)b.get(0)).d(), false);
      Context localContext = v;
      String str = localgm.d();
      if (localgm.n() > 0L) {}
      for (;;)
      {
        wd.a(localContext, str, bool, localgm.n(), false, localgm.p());
        return;
        bool = false;
      }
    }
    if (w == null) {
      m();
    }
    if ((v instanceof ComposeMessageActivity)) {
      ((ComposeMessageActivity)v).d(false);
    }
    w.a(b);
  }
  
  public void b(MenuItem paramMenuItem)
  {
    if (getVisibility() == 0) {}
    for (boolean bool = true;; bool = false)
    {
      paramMenuItem.setVisible(bool);
      return;
    }
  }
  
  public void b(gm paramgm)
  {
    h.setText("");
    h.setVisibility(8);
    a = null;
    b = new gq();
    l = new gq();
    d(paramgm);
  }
  
  public void c(gm paramgm)
  {
    h.setText("");
    h.setVisibility(8);
    a = paramgm.d();
    b = new gq();
    l = new gq();
    e(paramgm);
  }
  
  public boolean c()
  {
    Iterator localIterator = b.iterator();
    while (localIterator.hasNext()) {
      if (aau.d(((gm)localIterator.next()).d())) {
        return true;
      }
    }
    return false;
  }
  
  public boolean d()
  {
    Iterator localIterator = b.iterator();
    while (localIterator.hasNext()) {
      if (((gm)localIterator.next()).d().startsWith("@")) {
        return true;
      }
    }
    return false;
  }
  
  public boolean e()
  {
    return m != null;
  }
  
  public void f()
  {
    if ((m != null) && (r.isEmpty()))
    {
      if ((b.size() > 1) && (!d.isEmpty()))
      {
        e = ((HashMap)d.clone());
        q = p;
      }
      Object localObject = b;
      if (l.size() > b.size()) {
        localObject = l;
      }
      localObject = ((gq)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        gm localgm = (gm)((Iterator)localObject).next();
        r.add(localgm.d());
      }
      if (!r.isEmpty())
      {
        p = Integer.MAX_VALUE;
        new Thread(new aes(this)).start();
      }
    }
    else
    {
      return;
    }
    i.setVisibility(8);
  }
  
  public void g()
  {
    if ((m != null) && (b != null) && (b.size() < 3))
    {
      r.clear();
      if ((b.size() > 1) && (!d.isEmpty()))
      {
        e = ((HashMap)d.clone());
        q = p;
      }
      Object localObject = b;
      if (l.size() > b.size()) {
        localObject = l;
      }
      localObject = ((gq)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        gm localgm = (gm)((Iterator)localObject).next();
        r.add(localgm.d());
      }
      if (!r.isEmpty())
      {
        p = Integer.MAX_VALUE;
        new Thread(new aet(this)).start();
        return;
      }
      i.setVisibility(8);
      return;
    }
    Log.d("MzContactHeaderWidget", "don't reCheckSipDestNumber...");
  }
  
  public int getCount()
  {
    return b.size();
  }
  
  public List<String> getNumbers()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = b.iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(((gm)localIterator.next()).d());
    }
    if (localArrayList.size() > 0) {
      return localArrayList;
    }
    return null;
  }
  
  public int getSipVersion()
  {
    if (!r.isEmpty()) {
      return o;
    }
    if (p == Integer.MAX_VALUE) {
      return -1;
    }
    return p;
  }
  
  public boolean h()
  {
    return s;
  }
  
  public void i()
  {
    int i1;
    if (b != null)
    {
      i1 = b.size();
      if (i1 != 1) {
        break label39;
      }
      a(((gm)b.get(0)).d());
    }
    label39:
    while (i1 <= 1) {
      return;
    }
    a(i1);
  }
  
  public void j()
  {
    if (w != null) {
      w.a();
    }
  }
  
  public void k()
  {
    if ((w != null) && (w.b())) {
      w.a();
    }
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    inflate(v, 2130968782, this);
    c = v.getContentResolver();
    f = ((ViewGroup)findViewById(2131886668));
    g = ((TextView)findViewById(2131886669));
    h = ((TextView)findViewById(2131886671));
    i = ((TextView)findViewById(2131886670));
    b = new gq();
    l = new gq();
    d = new HashMap();
    e = new HashMap();
    r = new ArrayList();
    x = new a(null);
    p = Integer.MAX_VALUE;
    q = Integer.MAX_VALUE;
    a();
  }
  
  public void setDisplayName(CharSequence paramCharSequence)
  {
    g.setText(paramCharSequence);
  }
  
  public void setFlymeState(boolean paramBoolean)
  {
    s = paramBoolean;
    t = true;
  }
  
  public void setOnSipStateChangedListener(nd.a parama)
  {
    u = parama;
  }
  
  public void setSipDestChecker(nd.b paramb)
  {
    m = paramb;
    if (paramb == null)
    {
      r.clear();
      i.setVisibility(8);
    }
  }
  
  class a
    extends Handler
  {
    private a() {}
    
    public void handleMessage(Message paramMessage)
    {
      switch (what)
      {
      }
      label85:
      int n;
      int i1;
      label248:
      label282:
      label294:
      label297:
      label364:
      for (;;)
      {
        return;
        Object localObject1 = (Object[])obj;
        paramMessage = (String)localObject1[0];
        nd.c localc = (nd.c)localObject1[1];
        int i2 = ((Integer)localObject1[2]).intValue();
        localObject1 = b.iterator();
        int k = 0;
        i = 0;
        int j = 0;
        int m = k;
        n = i;
        i1 = j;
        Object localObject2;
        if (((Iterator)localObject1).hasNext())
        {
          localObject2 = (gm)((Iterator)localObject1).next();
          if (!paramMessage.equals(((gm)localObject2).d().replaceAll(" ", ""))) {
            break label297;
          }
          d.put(((gm)localObject2).d(), localc);
          MzContactHeaderWidget.b(MzContactHeaderWidget.this).remove(((gm)localObject2).d());
          MzContactHeaderWidget localMzContactHeaderWidget = MzContactHeaderWidget.this;
          if (i2 >= MzContactHeaderWidget.c(MzContactHeaderWidget.this)) {
            break label282;
          }
          k = i2;
          MzContactHeaderWidget.a(localMzContactHeaderWidget, k);
          if (i == 0) {
            break label294;
          }
          m = 1;
          i1 = j;
          n = i;
        }
        for (;;)
        {
          if ((m == 0) || (MzContactHeaderWidget.b(MzContactHeaderWidget.this).size() != 0)) {
            break label364;
          }
          if (i1 == 0) {
            break label371;
          }
          MzContactHeaderWidget.d(MzContactHeaderWidget.this).setVisibility(8);
          if (MzContactHeaderWidget.g(MzContactHeaderWidget.this) == null) {
            break label478;
          }
          MzContactHeaderWidget.g(MzContactHeaderWidget.this).a(MzContactHeaderWidget.this, MzContactHeaderWidget.h(MzContactHeaderWidget.this));
          return;
          k = MzContactHeaderWidget.c(MzContactHeaderWidget.this);
          break;
          k = 1;
          localObject2 = (nd.c)d.get(((gm)localObject2).d());
          if (localObject2 == nd.c.b) {
            j = 1;
          }
          if ((localObject2 != null) && ((localObject2 == nd.c.c) || (localObject2 == nd.c.b))) {
            break label480;
          }
          if (k == 0) {
            break label366;
          }
          n = 1;
          m = k;
          i1 = j;
        }
      }
      label366:
      int i = 1;
      label371:
      label478:
      label480:
      for (;;)
      {
        break label85;
        if ((i1 != 0) && (!c()))
        {
          paramMessage = MzContactHeaderWidget.this;
          if (MzContactHeaderWidget.e(MzContactHeaderWidget.this)) {}
          for (boolean bool = false;; bool = MzContactHeaderWidget.f(MzContactHeaderWidget.this))
          {
            MzContactHeaderWidget.a(paramMessage, bool);
            MzContactHeaderWidget.d(MzContactHeaderWidget.this).setVisibility(0);
            MzContactHeaderWidget.b(MzContactHeaderWidget.this, false);
            break;
          }
        }
        if (n == 0)
        {
          MzContactHeaderWidget.d(MzContactHeaderWidget.this).setVisibility(0);
          break label248;
        }
        MzContactHeaderWidget.d(MzContactHeaderWidget.this).setVisibility(8);
        break label248;
        break;
      }
    }
  }
  
  class b
    extends AsyncTask<Integer, Void, Void>
  {
    private String[] b;
    
    public b(String[] paramArrayOfString)
    {
      b = paramArrayOfString;
    }
    
    protected Void a(Integer... paramVarArgs)
    {
      int i = 0;
      while (i < paramVarArgs[0].intValue())
      {
        b[i] = ((gm)b.get(i)).g();
        i += 1;
      }
      return null;
    }
    
    protected void a(Void paramVoid)
    {
      MzContactHeaderWidget.i(MzContactHeaderWidget.this);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.view.MzContactHeaderWidget
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */