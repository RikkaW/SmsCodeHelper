import android.util.Log;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

class gr$a
{
  private static a a = new a();
  private final ConcurrentHashMap<Long, gr> b = new ConcurrentHashMap();
  
  static a a()
  {
    return a;
  }
  
  static gr a(long paramLong)
  {
    gr localgr = (gr)ab.get(Long.valueOf(paramLong));
    if ((localgr != null) && (localgr.e() == paramLong)) {
      return localgr;
    }
    return null;
  }
  
  static gr a(gq paramgq)
  {
    Iterator localIterator = ab.values().iterator();
    while (localIterator.hasNext())
    {
      gr localgr = (gr)localIterator.next();
      if (localgr.h().equals(paramgq)) {
        return localgr;
      }
    }
    return null;
  }
  
  static void a(gm paramgm, HashSet<gr> paramHashSet)
  {
    synchronized (a)
    {
      if (Log.isLoggable("Mms:threadcache", 2)) {
        fl.a("Conversation get with ContactList: " + paramgm, new Object[0]);
      }
      Iterator localIterator = ab.values().iterator();
      while (localIterator.hasNext())
      {
        gr localgr = (gr)localIterator.next();
        if ((localgr.h().contains(paramgm)) && (!paramHashSet.contains(localgr))) {
          paramHashSet.add(localgr);
        }
      }
    }
  }
  
  static void a(gr paramgr)
  {
    if (ab.contains(paramgr)) {
      throw new IllegalStateException("cache already contains " + paramgr + " threadId: " + gr.c(paramgr));
    }
    ab.put(Long.valueOf(paramgr.e()), paramgr);
  }
  
  static void a(Set<Long> paramSet)
  {
    synchronized (a)
    {
      Iterator localIterator = ab.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        Long localLong = (Long)localEntry.getKey();
        if ((!paramSet.contains(Long.valueOf(((gr)localEntry.getValue()).e()))) || (!paramSet.contains(localLong))) {
          localIterator.remove();
        }
      }
    }
    Log.d("Mms/conv", "after keepOnly() mCache size is " + ab.size());
  }
  
  static HashSet<gr> b(gq paramgq)
  {
    HashSet localHashSet = new HashSet();
    paramgq = paramgq.iterator();
    while (paramgq.hasNext()) {
      a((gm)paramgq.next(), localHashSet);
    }
    return localHashSet;
  }
  
  static void b()
  {
    fl.a("Conversation dumpCache: ", new Object[0]);
    Iterator localIterator = ab.values().iterator();
    while (localIterator.hasNext())
    {
      gr localgr = (gr)localIterator.next();
      fl.a("   conv: " + localgr.toString() + " hash: " + localgr.hashCode(), new Object[0]);
    }
  }
  
  static void b(long paramLong)
  {
    ab.remove(Long.valueOf(paramLong));
  }
  
  static boolean b(gr paramgr)
  {
    if (!ab.contains(paramgr)) {
      return false;
    }
    ab.replace(Long.valueOf(paramgr.e()), paramgr);
    return true;
  }
  
  static ConcurrentHashMap<Long, gr> c()
  {
    synchronized (a)
    {
      ConcurrentHashMap localConcurrentHashMap = ab;
      return localConcurrentHashMap;
    }
  }
}

/* Location:
 * Qualified Name:     gr.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */