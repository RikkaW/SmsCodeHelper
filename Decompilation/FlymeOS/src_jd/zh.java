import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

abstract class zh
{
  protected final Set<Uri> a = new HashSet();
  protected final HashMap<Uri, Set<zy>> b = new HashMap();
  protected final Executor c;
  protected final Handler d;
  
  zh(Context paramContext)
  {
    paramContext = new LinkedBlockingQueue();
    c = new ThreadPoolExecutor(2, 2, 5L, TimeUnit.SECONDS, paramContext, new zh.a(c()));
    d = new Handler();
  }
  
  protected static <T> ArrayList<T> a(Set<T> paramSet)
  {
    return new ArrayList(paramSet);
  }
  
  public void a()
  {
    b();
  }
  
  public void a(zy paramzy)
  {
    if (Log.isLoggable("BackgroundLoaderManager", 3)) {
      Log.d("BackgroundLoaderManager", "Cancelling image callback " + paramzy);
    }
    Iterator localIterator = b.keySet().iterator();
    while (localIterator.hasNext())
    {
      Uri localUri = (Uri)localIterator.next();
      ((Set)b.get(localUri)).remove(paramzy);
    }
  }
  
  public boolean a(Uri paramUri, zy paramzy)
  {
    if (Log.isLoggable("BackgroundLoaderManager", 3)) {
      Log.d("BackgroundLoaderManager", "Adding image callback " + paramzy);
    }
    if (paramUri == null) {
      throw new NullPointerException("uri is null");
    }
    if (paramzy == null) {
      throw new NullPointerException("callback is null");
    }
    Set localSet = (Set)b.get(paramUri);
    Object localObject = localSet;
    if (localSet == null)
    {
      localObject = new HashSet(4);
      b.put(paramUri, localObject);
    }
    ((Set)localObject).add(paramzy);
    return true;
  }
  
  public void b() {}
  
  public abstract String c();
  
  static class a
    implements ThreadFactory
  {
    private final AtomicInteger a = new AtomicInteger(1);
    private final String b;
    
    public a(String paramString)
    {
      b = paramString;
    }
    
    public Thread newThread(Runnable paramRunnable)
    {
      paramRunnable = new Thread(paramRunnable, b + "-" + a.getAndIncrement());
      if (paramRunnable.getPriority() != 1) {
        paramRunnable.setPriority(1);
      }
      return paramRunnable;
    }
  }
}

/* Location:
 * Qualified Name:     zh
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */