package com.android.mms.recipient;

import aau;
import aig;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MzContactsContract.MzCommonDataKinds.MzEmail;
import android.provider.MzContactsContract.MzCommonDataKinds.MzPhone;
import android.provider.MzContactsContract.MzDirectory;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView.OnItemClickListener;
import com.meizu.commonwidget.RecipientEdit;
import com.meizu.commonwidget.RecipientEdit.RecipientAutoCompleteTextView;
import com.meizu.commonwidget.RecipientEdit.e;
import com.meizu.commonwidget.RecipientEdit.h;
import com.meizu.commonwidget.RecipientEdit.i;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import mu;
import mv;
import mw;
import mx;
import my;
import mz;
import na;
import nc;
import nd.a;
import nd.b;
import nd.c;

public class MessageRecipient
  extends RecipientEdit
{
  private static final String[] E = { "display_name", "data1", "organization_note", "_id" };
  private static c q;
  private static c s;
  private boolean A = true;
  private RecipientEdit.h B = new mx(this);
  private Runnable C = new my(this);
  private AdapterView.OnItemClickListener D = new mz(this);
  private HashMap<String, nd.c> k;
  private HashMap<String, Integer> l;
  private ArrayList<String> m;
  private ArrayList<String> n;
  private ContentObserver o;
  private Handler p;
  private b r;
  private ContentResolver t;
  private int u;
  private boolean v = false;
  private nd.b w;
  private nd.a x;
  private na y;
  private RecipientEdit.RecipientAutoCompleteTextView z;
  
  public MessageRecipient(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public MessageRecipient(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public MessageRecipient(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, 2130771968);
    i();
  }
  
  private int a(long paramLong)
  {
    Object localObject;
    switch (u)
    {
    default: 
      localObject = ContentUris.withAppendedId(MzContactsContract.MzCommonDataKinds.MzPhone.CONTENT_GROUP_URI, paramLong);
      localObject = t.query((Uri)localObject, E, "is_primary = 1", null, "sort_key");
    }
    while ((localObject != null) && (((Cursor)localObject).getCount() > 0))
    {
      aig localaig = new aig();
      ((Cursor)localObject).moveToPosition(-1);
      int i = 0;
      for (;;)
      {
        if (((Cursor)localObject).moveToNext())
        {
          String str1 = ((Cursor)localObject).getString(1);
          String str2 = ((Cursor)localObject).getString(0);
          paramLong = ((Cursor)localObject).getLong(3);
          localaig.a();
          localaig.a(str2, null, Long.valueOf(paramLong));
          getRecipientsInfo().put(str1, localaig);
          if (a(str1, str2))
          {
            i += 1;
            continue;
            localObject = ContentUris.withAppendedId(MzContactsContract.MzCommonDataKinds.MzEmail.CONTENT_GROUP_URI, paramLong);
            localObject = t.query((Uri)localObject, E, null, null, "sort_key");
            break;
          }
        }
      }
      if (i > 0)
      {
        if (d != null) {
          d.a();
        }
        if ((getRecipientDataList().size() > 1) && ((f & 0x6) > 0) && (b != null)) {
          b.a(f & u);
        }
        if (!getmIsFirstLayout()) {
          b(hasFocus());
        }
      }
      return i;
    }
    if (localObject != null) {
      ((Cursor)localObject).close();
    }
    return 0;
  }
  
  private void i()
  {
    setIsValidateRecognition(false);
    k = new HashMap();
    l = new HashMap();
    m = new ArrayList();
    n = new ArrayList();
    y = new na(a, u);
    setAdapter(y);
    setOnItemClickListener(D);
    t = a.getContentResolver();
    setRecipientSipStateCheckListener(B);
    setRecipientColorType(1);
    z = getRecipientAutoCompleteTextView();
    z.setOnDismissListener(new mu(this));
    setScrollListener(new mv(this));
    z.setDropDownHeight(1404);
    z.setDropDownBackgroundResource(2130837966);
  }
  
  private void j()
  {
    if ((v) && (w != null) && (m.isEmpty()))
    {
      Iterator localIterator = k.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        if ((k.get(str) == nd.c.e) && (m.isEmpty()))
        {
          k.put(str, nd.c.d);
          n.add(str);
          r.obtainMessage(1, str).sendToTarget();
        }
      }
    }
  }
  
  public String a()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Object localObject = getRecipientDataList().iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = (String)((Iterator)localObject).next();
      if (!nc.a(str, u))
      {
        if (localStringBuilder.length() != 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append(str);
      }
    }
    localObject = getInputText();
    if ((!TextUtils.isEmpty((CharSequence)localObject)) && (!getRecipientDataList().contains(localObject)) && (!nc.a((String)localObject, u)))
    {
      if (localStringBuilder.length() != 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append((String)localObject);
    }
    return localStringBuilder.toString();
  }
  
  public void a(int paramInt)
  {
    Object localObject = b(paramInt);
    if (localObject != null)
    {
      boolean bool = false;
      localObject = ((ArrayList)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        String str = (String)((Iterator)localObject).next();
        if (r != null) {
          r.removeMessages(1, str);
        }
        n.remove(str);
        k.remove(str);
        l.remove(str);
        bool = m.remove(str);
      }
      if (bool) {
        j();
      }
      if (x != null) {
        x.a(this, getSipState());
      }
    }
  }
  
  public void a(String paramString, nd.c paramc, int paramInt)
  {
    paramString = nc.b(paramString, u);
    n.remove(paramString);
    String str = getInputText();
    if ((TextUtils.isEmpty(paramString)) || ((!getRecipientDataList().contains(paramString)) && (!TextUtils.equals(paramString, str)))) {}
    do
    {
      return;
      k.put(paramString, paramc);
      l.put(paramString, Integer.valueOf(paramInt));
      if ((paramc == nd.c.a) && (!m.contains(paramString))) {
        m.add(paramString);
      }
    } while (x == null);
    x.a(this, getSipState());
  }
  
  public void a(boolean paramBoolean)
  {
    v = paramBoolean;
    if (v)
    {
      if (s == null) {
        s = new c("mz_recipient_worker");
      }
      if (r == null)
      {
        c.a(s);
        r = new b(s.a());
      }
    }
  }
  
  public boolean b()
  {
    if (u == 2)
    {
      localObject = getRecipientDataList().iterator();
      do
      {
        if (!((Iterator)localObject).hasNext()) {
          break;
        }
      } while (!nc.d((String)((Iterator)localObject).next()));
      return true;
    }
    if ((u & 0x2) == 2)
    {
      if ((f & 0x2) == 2) {
        return true;
      }
    }
    else {
      return false;
    }
    Object localObject = getInputText();
    return (!TextUtils.isEmpty((CharSequence)localObject)) && (nc.d((String)localObject));
  }
  
  public boolean c()
  {
    String str = getInputText();
    return (!TextUtils.isEmpty(str)) && (nc.b(str));
  }
  
  public void d()
  {
    super.d();
    if (r != null) {
      r.removeMessages(1);
    }
    k.clear();
    l.clear();
    n.clear();
    m.clear();
    if (x != null) {
      x.a(this, nd.c.a);
    }
  }
  
  public boolean e()
  {
    Iterator localIterator = getRecipientDataList().iterator();
    while (localIterator.hasNext()) {
      if (((String)localIterator.next()).equals("4007883333")) {
        return true;
      }
    }
    return false;
  }
  
  public void f()
  {
    InputMethodManager localInputMethodManager = (InputMethodManager)a.getSystemService("input_method");
    if (((Boolean)aau.b(InputMethodManager.class, localInputMethodManager, "isSoftInputShown")).booleanValue()) {
      localInputMethodManager.hideSoftInputFromWindow(z.getWindowToken(), 0);
    }
  }
  
  public List<String> getInvalidNumbers()
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = getRecipientDataList().iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = (String)((Iterator)localObject).next();
      if (!nc.a(str, u)) {
        localArrayList.add(str);
      }
    }
    localObject = getInputText();
    if ((!TextUtils.isEmpty((CharSequence)localObject)) && (!getRecipientDataList().contains(localObject)) && (!nc.a((String)localObject, u))) {
      localArrayList.add(localObject);
    }
    if (localArrayList.size() > 0) {
      return localArrayList;
    }
    return null;
  }
  
  public nd.c getSipState()
  {
    if (getRecipientDataList().size() == 0) {
      return nd.c.a;
    }
    if ((m.size() > 0) || (n.size() > 0)) {
      return nd.c.a;
    }
    Object localObject1 = getRecipientDataList().iterator();
    int i = 0;
    if (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = (String)((Iterator)localObject1).next();
      localObject2 = (nd.c)k.get(localObject2);
      if ((localObject2 == null) || ((localObject2 != nd.c.c) && (localObject2 != nd.c.b))) {
        return nd.c.a;
      }
      if (localObject2 != nd.c.b) {
        break label204;
      }
      i = 1;
    }
    label204:
    for (;;)
    {
      break;
      localObject1 = getInputText();
      int j = i;
      if (!TextUtils.isEmpty((CharSequence)localObject1))
      {
        j = i;
        if (!getRecipientDataList().contains(localObject1))
        {
          localObject1 = (nd.c)k.get(localObject1);
          if ((localObject1 == null) || ((localObject1 != nd.c.c) && (localObject1 != nd.c.b))) {
            return nd.c.a;
          }
          j = i;
          if (localObject1 == nd.c.b) {
            j = 1;
          }
        }
      }
      if (j != 0) {
        return nd.c.b;
      }
      return nd.c.c;
    }
  }
  
  public int getSipVersion()
  {
    Iterator localIterator = l.values().iterator();
    int i = Integer.MAX_VALUE;
    int j;
    if (localIterator.hasNext())
    {
      j = ((Integer)localIterator.next()).intValue();
      if (j >= i) {
        break label62;
      }
      i = j;
    }
    label62:
    for (;;)
    {
      break;
      j = i;
      if (i == Integer.MAX_VALUE) {
        j = -1;
      }
      return j;
    }
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    if (q == null) {
      q = new c("mz_recipient_content");
    }
    if (p == null)
    {
      c.a(q);
      p = new mw(this, q.a());
    }
    o = new a(p);
    t.registerContentObserver(MzContactsContract.MzDirectory.CONTENT_NOTIFY_URI, false, o);
    p.sendEmptyMessage(4);
  }
  
  protected void onDetachedFromWindow()
  {
    t.unregisterContentObserver(o);
    if ((s != null) && (c.b(s) == 0))
    {
      s.b();
      s = null;
    }
    if ((q != null) && (c.b(q) == 0))
    {
      q.b();
      q = null;
    }
    super.onDetachedFromWindow();
  }
  
  public void setInputType(int paramInt)
  {
    u = paramInt;
    super.setInputType(paramInt);
    if (y != null) {
      y.a(u);
    }
  }
  
  public void setOnSipStateChangedListener(nd.a parama)
  {
    x = parama;
  }
  
  public void setSipDestChecker(nd.b paramb)
  {
    a(true);
    w = paramb;
    if ((w == null) && (!n.isEmpty()))
    {
      r.removeMessages(1);
      paramb = n.iterator();
      while (paramb.hasNext())
      {
        String str = (String)paramb.next();
        k.put(str, nd.c.e);
      }
      n.clear();
    }
  }
  
  class a
    extends ContentObserver
  {
    private Handler b;
    
    public a(Handler paramHandler)
    {
      super();
      b = paramHandler;
    }
    
    public void onChange(boolean paramBoolean)
    {
      b.removeMessages(4);
      b.sendEmptyMessageDelayed(4, 500L);
    }
  }
  
  public class b
    extends Handler
  {
    public b(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      switch (what)
      {
      }
      do
      {
        return;
        paramMessage = String.valueOf(obj);
      } while (MessageRecipient.h(MessageRecipient.this) == null);
      MessageRecipient.h(MessageRecipient.this).a(new String[] { paramMessage });
    }
  }
  
  static class c
    implements Runnable
  {
    private final Object a = new Object();
    private Looper b;
    private int c;
    
    public c(String arg1)
    {
      new Thread(this, ???).start();
      synchronized (a)
      {
        for (;;)
        {
          Looper localLooper = b;
          if (localLooper != null) {
            break;
          }
          try
          {
            a.wait();
          }
          catch (InterruptedException localInterruptedException) {}
        }
        return;
      }
    }
    
    public Looper a()
    {
      return b;
    }
    
    public void b()
    {
      b.quit();
    }
    
    public void run()
    {
      synchronized (a)
      {
        Looper.prepare();
        b = Looper.myLooper();
        a.notifyAll();
        Looper.loop();
        return;
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.recipient.MessageRecipient
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */