import android.text.TextUtils;
import android.util.Log;
import com.android.mms.MmsApp;
import com.android.mms.model.Model;
import com.meizu.android.mms.pdu.smil.SmilSimpleParse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class lq
  extends Model
  implements atv, List<lm>
{
  private final ArrayList<lm> a = new ArrayList();
  private lm b;
  private lm c;
  private lm d;
  private lm e;
  private lm f;
  private lm g;
  private lm h;
  private boolean i = true;
  private boolean j = true;
  private boolean k = true;
  private boolean l = true;
  private boolean m = true;
  private boolean o = true;
  private int p;
  private boolean q = true;
  private short r;
  private long s;
  private lr t;
  private int u;
  
  public lq(int paramInt, ArrayList<lm> paramArrayList)
  {
    p = paramInt;
    paramArrayList = paramArrayList.iterator();
    paramInt = 0;
    if (paramArrayList.hasNext())
    {
      lm locallm = (lm)paramArrayList.next();
      b(locallm);
      int n = locallm.i();
      if (n <= paramInt) {
        break label110;
      }
      paramInt = n;
    }
    label110:
    for (;;)
    {
      break;
      d(paramInt);
      return;
    }
  }
  
  public lq(int paramInt, lr paramlr)
  {
    p = paramInt;
    t = paramlr;
  }
  
  public lq(lr paramlr)
  {
    this(5000, paramlr);
  }
  
  private void a(lm paramlm1, lm paramlm2)
  {
    long l2 = 0L;
    boolean bool = y();
    long l1;
    if (bool) {
      l1 = paramlm2.p();
    }
    for (;;)
    {
      Log.i("Mms/slideshow", "internalAddOrReplace addSize = " + l1 + ", mediasize = " + paramlm2.p());
      if (paramlm1 != null) {
        break;
      }
      if (t != null) {
        t.d(l1);
      }
      a.add(paramlm2);
      a(l1);
      c(l1);
      paramlm1 = n.iterator();
      while (paramlm1.hasNext()) {
        paramlm2.c((lj)paramlm1.next());
      }
      if (paramlm2.e()) {
        l1 = 0L;
      } else {
        l1 = paramlm2.p();
      }
    }
    if (bool)
    {
      l2 = paramlm1.p();
      label158:
      if (l1 <= l2) {
        break label239;
      }
      if (t != null) {
        t.d(l1 - l2);
      }
      a(l1 - l2);
      c(l1 - l2);
    }
    for (;;)
    {
      a.set(a.indexOf(paramlm1), paramlm2);
      paramlm1.B();
      break;
      if (paramlm1.e()) {
        break label158;
      }
      l2 = paramlm1.p();
      break label158;
      label239:
      b(l2 - l1);
      d(l2 - l1);
    }
  }
  
  private boolean a(Object paramObject)
  {
    if (a.remove(paramObject))
    {
      long l1;
      if ((paramObject instanceof lu))
      {
        b = null;
        if (!y()) {
          break label192;
        }
        l1 = ((lm)paramObject).p();
      }
      for (;;)
      {
        b(l1);
        d(l1);
        ((Model)paramObject).B();
        return true;
        if ((paramObject instanceof lk))
        {
          c = null;
          k = true;
          break;
        }
        if ((paramObject instanceof le))
        {
          d = null;
          k = true;
          break;
        }
        if ((paramObject instanceof lw))
        {
          e = null;
          i = true;
          j = true;
          break;
        }
        if ((paramObject instanceof lv))
        {
          f = null;
          i = true;
          j = true;
          k = true;
          o = true;
          break;
        }
        if (!(paramObject instanceof li)) {
          break;
        }
        h = null;
        i = true;
        j = true;
        k = true;
        l = true;
        break;
        label192:
        if (((lm)paramObject).e()) {
          l1 = 0L;
        } else {
          l1 = ((lm)paramObject).p();
        }
      }
    }
    return false;
  }
  
  private void b(lm paramlm)
  {
    if (paramlm == null) {}
    do
    {
      return;
      if (paramlm.q())
      {
        String str = paramlm.j();
        if ((TextUtils.isEmpty(str)) || ("text/plain".equals(str)) || ("text/html".equals(str)))
        {
          a(b, paramlm);
          b = paramlm;
          return;
        }
        Log.w("Mms/slideshow", "[SlideModel] content type " + paramlm.j() + " isn't supported (as text)");
        return;
      }
      if (paramlm.r())
      {
        if (i)
        {
          a(c, paramlm);
          c = paramlm;
          o = false;
          m = false;
          return;
        }
        Log.w("Mms/slideshow", "[SlideModel] content type " + paramlm.j() + " - can't add image in this state");
        return;
      }
      if (paramlm.t())
      {
        if (j)
        {
          a(d, paramlm);
          d = paramlm;
          o = false;
          m = false;
          return;
        }
        Log.w("Mms/slideshow", "[SlideModel] content type " + paramlm.j() + " - can't add audio in this state");
        return;
      }
      if (paramlm.s())
      {
        if (k)
        {
          a(e, paramlm);
          e = paramlm;
          o = false;
          m = false;
          return;
        }
        Log.w("Mms/slideshow", "[SlideModel] content type " + paramlm.j() + " - can't add video in this state");
        return;
      }
      if (paramlm.u())
      {
        if (l)
        {
          a(f, paramlm);
          f = paramlm;
          o = false;
          m = false;
          return;
        }
        Log.w("Mms/slideshow", "[SlideModel] content type " + paramlm.j() + " - can't add vcard in this state");
        return;
      }
      if (paramlm.v())
      {
        if (m)
        {
          a(g, paramlm);
          g = paramlm;
          i = false;
          j = false;
          k = false;
          l = false;
          o = false;
          return;
        }
        Log.w("Mms/slideshow", "[SlideModel] content type " + paramlm.j() + " - can't add vcard in this state");
        return;
      }
    } while (!paramlm.w());
    if (o)
    {
      a(h, paramlm);
      h = paramlm;
      i = false;
      j = false;
      k = false;
      l = false;
      m = false;
      return;
    }
    Log.w("Mms/slideshow", "[SlideModel] content type " + paramlm.j() + " - can't add vcard in this state");
  }
  
  private boolean y()
  {
    return MmsApp.a;
  }
  
  public int a()
  {
    return p;
  }
  
  public void a(int paramInt)
  {
    p = paramInt;
    a(true);
  }
  
  public void a(int paramInt, lm paramlm)
  {
    throw new UnsupportedOperationException("Operation not supported.");
  }
  
  public void a(long paramLong)
  {
    if (paramLong > 0L) {
      s += paramLong;
    }
  }
  
  public void a(att paramatt)
  {
    if (paramatt.b().equals("SmilSlideStart")) {
      q = true;
    }
    for (;;)
    {
      a(false);
      return;
      if (r != 1) {
        q = false;
      }
    }
  }
  
  protected void a(lj paramlj)
  {
    Iterator localIterator = a.iterator();
    while (localIterator.hasNext()) {
      ((lm)localIterator.next()).c(paramlj);
    }
  }
  
  public void a(lr paramlr)
  {
    t = paramlr;
  }
  
  public void a(short paramShort)
  {
    r = paramShort;
    a(true);
  }
  
  public boolean a(lm paramlm)
  {
    b(paramlm);
    a(true);
    return true;
  }
  
  public boolean addAll(int paramInt, Collection<? extends lm> paramCollection)
  {
    throw new UnsupportedOperationException("Operation not supported.");
  }
  
  public boolean addAll(Collection<? extends lm> paramCollection)
  {
    throw new UnsupportedOperationException("Operation not supported.");
  }
  
  public long b()
  {
    return s;
  }
  
  public lm b(int paramInt)
  {
    if (a.size() == 0) {
      return null;
    }
    return (lm)a.get(paramInt);
  }
  
  public lm b(int paramInt, lm paramlm)
  {
    throw new UnsupportedOperationException("Operation not supported.");
  }
  
  public void b(long paramLong)
  {
    if (paramLong > 0L)
    {
      s -= paramLong;
      if (s < 0L) {
        s = 0L;
      }
    }
  }
  
  protected void b(lj paramlj)
  {
    Iterator localIterator = a.iterator();
    while (localIterator.hasNext()) {
      ((lm)localIterator.next()).d(paramlj);
    }
  }
  
  public lm c(int paramInt)
  {
    lm locallm = (lm)a.get(paramInt);
    if ((locallm != null) && (a(locallm))) {
      a(true);
    }
    return locallm;
  }
  
  public void c(long paramLong)
  {
    if ((paramLong > 0L) && (t != null))
    {
      long l1 = t.d();
      t.a(l1 + paramLong);
    }
  }
  
  public boolean c()
  {
    return q;
  }
  
  public void clear()
  {
    if (a.size() > 0)
    {
      Iterator localIterator = a.iterator();
      while (localIterator.hasNext())
      {
        lm locallm = (lm)localIterator.next();
        locallm.B();
        long l1 = locallm.p();
        b(l1);
        d(l1);
      }
      a.clear();
      b = null;
      c = null;
      d = null;
      e = null;
      f = null;
      g = null;
      h = null;
      i = true;
      j = true;
      k = true;
      l = true;
      m = true;
      o = true;
      a(true);
    }
  }
  
  public boolean contains(Object paramObject)
  {
    return a.contains(paramObject);
  }
  
  public boolean containsAll(Collection<?> paramCollection)
  {
    return a.containsAll(paramCollection);
  }
  
  public void d(int paramInt)
  {
    if (paramInt <= 0) {}
    while ((paramInt <= p) && (p != 5000)) {
      return;
    }
    p = paramInt;
  }
  
  public void d(long paramLong)
  {
    long l1 = 0L;
    if ((paramLong > 0L) && (t != null))
    {
      paramLong = t.d() - paramLong;
      if (paramLong >= 0L) {
        break label42;
      }
      paramLong = l1;
    }
    label42:
    for (;;)
    {
      t.a(paramLong);
      return;
    }
  }
  
  public boolean d()
  {
    return b != null;
  }
  
  public void e(int paramInt)
  {
    u = paramInt;
  }
  
  public boolean e()
  {
    return c != null;
  }
  
  public boolean f()
  {
    return d != null;
  }
  
  public boolean g()
  {
    return e != null;
  }
  
  protected void h()
  {
    Iterator localIterator = a.iterator();
    while (localIterator.hasNext()) {
      ((lm)localIterator.next()).B();
    }
  }
  
  public boolean i()
  {
    return f != null;
  }
  
  public int indexOf(Object paramObject)
  {
    return a.indexOf(paramObject);
  }
  
  public boolean isEmpty()
  {
    return a.isEmpty();
  }
  
  public Iterator<lm> iterator()
  {
    return a.iterator();
  }
  
  public boolean j()
  {
    return h != null;
  }
  
  public boolean k()
  {
    return (e()) && (SmilSimpleParse.isLocationUri(c.o()));
  }
  
  public boolean l()
  {
    return remove(b);
  }
  
  public int lastIndexOf(Object paramObject)
  {
    return a.lastIndexOf(paramObject);
  }
  
  public ListIterator<lm> listIterator()
  {
    return a.listIterator();
  }
  
  public ListIterator<lm> listIterator(int paramInt)
  {
    return a.listIterator(paramInt);
  }
  
  public boolean m()
  {
    return remove(c);
  }
  
  public boolean n()
  {
    boolean bool = remove(d);
    v();
    return bool;
  }
  
  public boolean o()
  {
    boolean bool = remove(e);
    v();
    return bool;
  }
  
  public lu p()
  {
    return (lu)b;
  }
  
  public lk q()
  {
    return (lk)c;
  }
  
  public le r()
  {
    return (le)d;
  }
  
  public boolean remove(Object paramObject)
  {
    if ((paramObject != null) && ((paramObject instanceof lm)) && (a(paramObject)))
    {
      a(true);
      return true;
    }
    return false;
  }
  
  public boolean removeAll(Collection<?> paramCollection)
  {
    throw new UnsupportedOperationException("Operation not supported.");
  }
  
  public boolean retainAll(Collection<?> paramCollection)
  {
    throw new UnsupportedOperationException("Operation not supported.");
  }
  
  public lw s()
  {
    return (lw)e;
  }
  
  public int size()
  {
    return a.size();
  }
  
  public List<lm> subList(int paramInt1, int paramInt2)
  {
    return a.subList(paramInt1, paramInt2);
  }
  
  public lv t()
  {
    return (lv)f;
  }
  
  public Object[] toArray()
  {
    return a.toArray();
  }
  
  public <T> T[] toArray(T[] paramArrayOfT)
  {
    return a.toArray(paramArrayOfT);
  }
  
  public li u()
  {
    return (li)h;
  }
  
  public void v()
  {
    if ((!f()) && (!g())) {
      p = 5000;
    }
  }
  
  public boolean w()
  {
    if (e()) {}
    for (int i1 = 1;; i1 = 0)
    {
      int n = i1;
      if (g()) {
        n = i1 + 1;
      }
      i1 = n;
      if (f()) {
        i1 = n + 1;
      }
      n = i1;
      if (i()) {
        n = i1 + 1;
      }
      i1 = n;
      if (j()) {
        i1 = n + 1;
      }
      return i1 > 1;
    }
  }
  
  public int x()
  {
    return u;
  }
}

/* Location:
 * Qualified Name:     lq
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */