import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.Telephony.MmsSms;
import android.util.Log;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class zt
{
  static final String[] a = { "thread_id" };
  private static zt b;
  private final Context c;
  private boolean d;
  private final Object e = new Object();
  private HashSet<Long> f = new HashSet(4);
  private final Object g = new Object();
  private final HashSet<zt.a> h = new HashSet(1);
  private final Object i = new Object();
  
  private zt(Context paramContext)
  {
    if (Log.isLoggable("Mms:app", 3)) {
      a("DraftCache.constructor", new Object[0]);
    }
    c = paramContext;
    a();
  }
  
  public static void a(Context paramContext)
  {
    b = new zt(paramContext);
  }
  
  private void a(String paramString, Object... paramVarArgs)
  {
    paramString = String.format(paramString, paramVarArgs);
    Log.d("Mms/draft", "[DraftCache/" + Thread.currentThread().getId() + "] " + paramString);
  }
  
  public static zt c()
  {
    return b;
  }
  
  private void e()
  {
    if (Log.isLoggable("Mms:app", 3)) {
      a("rebuildCache", new Object[0]);
    }
    Object localObject7 = new HashSet();
    ??? = c.getContentResolver().query(Telephony.MmsSms.CONTENT_DRAFT_URI, a, null, null, null);
    if (??? != null) {
      try
      {
        if (((Cursor)???).moveToFirst()) {
          while (!((Cursor)???).isAfterLast())
          {
            long l = ((Cursor)???).getLong(0);
            ((HashSet)localObject7).add(Long.valueOf(l));
            if (Log.isLoggable("Mms:app", 3)) {
              a("rebuildCache: add tid=" + l, new Object[0]);
            }
            ((Cursor)???).moveToNext();
          }
        }
      }
      finally
      {
        ((Cursor)???).close();
      }
    }
    for (;;)
    {
      zt.a locala;
      synchronized (g)
      {
        HashSet localHashSet = f;
        f = ((HashSet)localObject7);
        if (Log.isLoggable("Mms:app", 2)) {
          d();
        }
        synchronized (i)
        {
          if (h.size() < 1) {
            return;
          }
          ??? = new HashSet((Collection)localObject7);
          ((Set)???).removeAll(localHashSet);
          localHashSet = new HashSet(localHashSet);
          localHashSet.removeAll((Collection)localObject7);
          synchronized (i)
          {
            localObject7 = h.iterator();
            if (!((Iterator)localObject7).hasNext()) {
              break;
            }
            locala = (zt.a)((Iterator)localObject7).next();
            localIterator = ((Set)???).iterator();
            if (localIterator.hasNext()) {
              locala.a(((Long)localIterator.next()).longValue(), true);
            }
          }
        }
      }
      Iterator localIterator = ((Set)localObject5).iterator();
      while (localIterator.hasNext()) {
        locala.a(((Long)localIterator.next()).longValue(), false);
      }
    }
  }
  
  public void a()
  {
    if (Log.isLoggable("Mms:app", 3)) {
      a("refresh", new Object[0]);
    }
    Thread localThread = new Thread(new zu(this), "DraftCache.refresh");
    localThread.setPriority(1);
    localThread.start();
  }
  
  public void a(long paramLong, boolean paramBoolean)
  {
    if (paramLong <= 0L) {}
    for (;;)
    {
      return;
      ??? = g;
      if (paramBoolean) {}
      try
      {
        for (boolean bool = f.add(Long.valueOf(paramLong));; bool = f.remove(Long.valueOf(paramLong)))
        {
          if (Log.isLoggable("Mms:app", 3)) {
            a("setDraftState: tid=" + paramLong + ", value=" + paramBoolean + ", changed=" + bool, new Object[0]);
          }
          if (Log.isLoggable("Mms:app", 2)) {
            d();
          }
          if (!bool) {
            break;
          }
          synchronized (i)
          {
            Iterator localIterator = h.iterator();
            if (!localIterator.hasNext()) {
              break label189;
            }
            ((zt.a)localIterator.next()).a(paramLong, paramBoolean);
          }
        }
      }
      finally {}
    }
    label189:
  }
  
  public void a(zt.a parama)
  {
    if (Log.isLoggable("Mms:app", 3)) {
      a("addOnDraftChangedListener " + parama, new Object[0]);
    }
    synchronized (i)
    {
      h.add(parama);
      return;
    }
  }
  
  public void a(boolean paramBoolean)
  {
    synchronized (e)
    {
      d = paramBoolean;
      return;
    }
  }
  
  public boolean a(long paramLong)
  {
    synchronized (g)
    {
      boolean bool = f.contains(Long.valueOf(paramLong));
      return bool;
    }
  }
  
  public void b(zt.a parama)
  {
    if (Log.isLoggable("Mms:app", 3)) {
      a("removeOnDraftChangedListener " + parama, new Object[0]);
    }
    synchronized (i)
    {
      h.remove(parama);
      return;
    }
  }
  
  public boolean b()
  {
    synchronized (e)
    {
      boolean bool = d;
      return bool;
    }
  }
  
  public void d()
  {
    Log.i("Mms/draft", "dump:");
    Iterator localIterator = f.iterator();
    while (localIterator.hasNext())
    {
      Long localLong = (Long)localIterator.next();
      Log.i("Mms/draft", "  tid: " + localLong);
    }
  }
  
  public static abstract interface a
  {
    public abstract void a(long paramLong, boolean paramBoolean);
  }
}

/* Location:
 * Qualified Name:     zt
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */