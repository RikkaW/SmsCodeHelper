import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.location.BaseGetLocationView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class kw
{
  private HashSet<kr> a = new HashSet();
  private HashSet<kr> b = new HashSet();
  private String c = "";
  private kt d;
  private BaseGetLocationView e;
  private volatile boolean f = false;
  private Handler g;
  private Handler h;
  private final Object i = new Object();
  
  public kw(kt paramkt)
  {
    d = paramkt;
    g = new kw.d(null);
  }
  
  protected kw.a a(CharSequence paramCharSequence)
  {
    kw.a locala = new kw.a();
    c = paramCharSequence.toString();
    kf.a(true, 3, "SuggestionLocationFilter", "performFiltering(start)-->mCacheLastData.size() = " + b.size() + ", prefix = " + paramCharSequence);
    if (TextUtils.isEmpty(c))
    {
      f = false;
      return locala;
    }
    f = true;
    e.c(c);
    Object localObject3;
    synchronized (e.n)
    {
      try
      {
        e.n.wait(5000L);
        f = false;
        ??? = new ArrayList();
        kf.a(true, 3, "SuggestionLocationFilter", "SuggestionLocationFilter performFiltering mCurrentSearchData.size() = " + a.size());
        if (a.size() == 0)
        {
          kf.a(true, 4, "SuggestionLocationFilter", "performFiltering--> no search result, use cache");
          Iterator localIterator = b.iterator();
          while (localIterator.hasNext())
          {
            localObject3 = (kr)localIterator.next();
            if ((((kr)localObject3).a().contains(paramCharSequence)) || (((kr)localObject3).b().contains(paramCharSequence))) {
              ((ArrayList)???).add(localObject3);
            }
          }
        }
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;)
        {
          kf.a(true, 3, "SuggestionLocationFilter", "performFiltering--> wait 5000, " + localInterruptedException.getMessage());
        }
      }
    }
    for (;;)
    {
      synchronized (e.o)
      {
        a.clear();
        a = ???;
        b = ((ArrayList)???).size();
        kf.a(false, 4, "SuggestionLocationFilter", "performFiltering(end)--> count = " + b + ", prefix = " + paramCharSequence);
        return locala;
      }
      synchronized (e.o)
      {
        localObject3 = a.iterator();
        if (((Iterator)localObject3).hasNext()) {
          ((ArrayList)???).add(((Iterator)localObject3).next());
        }
      }
      b.addAll(a);
      a.clear();
      Collections.sort((List)???);
    }
  }
  
  public void a()
  {
    synchronized (e.n)
    {
      if (a != null) {
        a.clear();
      }
      if (b != null) {
        b.clear();
      }
      if (d != null)
      {
        d.clear();
        d = null;
      }
      return;
    }
  }
  
  public void a(BaseGetLocationView paramBaseGetLocationView)
  {
    e = paramBaseGetLocationView;
  }
  
  protected void a(CharSequence paramCharSequence, kw.a parama)
  {
    kf.a(false, 4, "SuggestionLocationFilter", "publishResults(show for UI)-->count = " + b);
    if (d != null)
    {
      d.clear();
      if (b > 0) {
        d.addAll((ArrayList)a);
      }
    }
  }
  
  public void a(kr paramkr)
  {
    synchronized (e.o)
    {
      a.add(paramkr);
      paramkr.a(c);
      return;
    }
  }
  
  public void b()
  {
    synchronized (e.n)
    {
      if (a != null)
      {
        a.clear();
        a = null;
      }
      if (b != null)
      {
        b.clear();
        b = null;
      }
      if (d != null)
      {
        d.clear();
        d = null;
      }
      return;
    }
  }
  
  public void b(CharSequence paramCharSequence)
  {
    String str = null;
    synchronized (i)
    {
      if (h == null)
      {
        localObject2 = new HandlerThread("search_location", 10);
        ((HandlerThread)localObject2).start();
        h = new kw.c(((HandlerThread)localObject2).getLooper());
      }
      Object localObject2 = h.obtainMessage(1);
      kw.b localb = new kw.b(null);
      if (paramCharSequence != null) {
        str = paramCharSequence.toString();
      }
      a = str;
      obj = localb;
      h.removeMessages(1);
      h.removeMessages(2);
      h.sendMessageDelayed((Message)localObject2, 250L);
      return;
    }
  }
  
  static class a
  {
    public Object a;
    public int b;
  }
  
  static class b
  {
    CharSequence a;
    kw.a b;
  }
  
  class c
    extends Handler
  {
    public c(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message arg1)
    {
      int i = what;
      switch (i)
      {
      default: 
        return;
      case 1: 
        ??? = (kw.b)obj;
        for (;;)
        {
          try
          {
            b = a(a);
          }
          catch (Exception localException)
          {
            Message localMessage1;
            b = new kw.a();
            Log.w("SearchLocationHelper", "An exception occured during performFiltering()!", localException);
            Message localMessage2 = kw.a(kw.this).obtainMessage(i);
            obj = ???;
            localMessage2.sendToTarget();
            continue;
          }
          finally
          {
            Message localMessage3 = kw.a(kw.this).obtainMessage(i);
            obj = ???;
            localMessage3.sendToTarget();
          }
          synchronized (kw.b(kw.this))
          {
            if (kw.c(kw.this) != null)
            {
              localMessage1 = kw.c(kw.this).obtainMessage(2);
              kw.c(kw.this).sendMessageDelayed(localMessage1, 3000L);
            }
            return;
          }
        }
      }
      synchronized (kw.b(kw.this))
      {
        if (kw.c(kw.this) != null)
        {
          kw.c(kw.this).getLooper().quit();
          kw.a(kw.this, null);
        }
        return;
      }
    }
  }
  
  class d
    extends Handler
  {
    private d() {}
    
    public void handleMessage(Message paramMessage)
    {
      paramMessage = (kw.b)obj;
      a(a, b);
    }
  }
}

/* Location:
 * Qualified Name:     kw
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */