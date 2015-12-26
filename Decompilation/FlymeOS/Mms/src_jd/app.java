import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.util.Pair;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class app
{
  private static final String a = app.class.getSimpleName();
  private static app b = null;
  private Context c;
  
  public app(Context paramContext)
  {
    c = paramContext;
  }
  
  public static app a(Context paramContext)
  {
    try
    {
      if (b == null) {
        b = new app(paramContext);
      }
      paramContext = b;
      return paramContext;
    }
    finally {}
  }
  
  private HashSet<Pair<String, String>> a(String paramString)
  {
    apr.a(c);
    paramString = apr.a().a("numcache.db", paramString);
    Cursor localCursor = paramString.rawQuery("SELECT phone, json FROM num", null);
    HashSet localHashSet = new HashSet(localCursor.getCount());
    if ((localCursor != null) && (localCursor.moveToFirst())) {
      do
      {
        localHashSet.add(Pair.create(localCursor.getString(0), localCursor.getString(1)));
      } while (localCursor.moveToNext());
    }
    if (localCursor != null) {
      localCursor.close();
    }
    if (paramString != null) {
      paramString.close();
    }
    return localHashSet;
  }
  
  private void a(String paramString, boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = c.getSharedPreferences("txl.bat", 0).edit();
    localEditor.putBoolean(paramString, paramBoolean);
    localEditor.commit();
  }
  
  private void a(HashSet<Pair<String, String>> paramHashSet)
  {
    aoh localaoh = aoh.a(c);
    localaoh.getWritableDatabase().beginTransaction();
    paramHashSet = paramHashSet.iterator();
    for (;;)
    {
      if (!paramHashSet.hasNext())
      {
        localaoh.getWritableDatabase().setTransactionSuccessful();
        localaoh.getWritableDatabase().endTransaction();
        return;
      }
      Pair localPair = (Pair)paramHashSet.next();
      String str = apm.a((String)second);
      localaoh.a((String)first, str, 0);
    }
  }
  
  private void a(boolean paramBoolean)
  {
    Object localObject3 = c.getCacheDir().getAbsolutePath();
    if ((paramBoolean) && (!ff.a(c, "vcard.dat", (String)localObject3))) {}
    for (;;)
    {
      return;
      Object localObject4 = localObject3 + File.separator + "vcard.dat";
      try
      {
        Object localObject1 = new FileInputStream((String)localObject4);
        if ((localObject1 != null) && (ff.a((InputStream)localObject1, localObject3 + File.separator)))
        {
          localObject1 = new File(localObject3 + File.separator + "numcache.db");
          localObject4 = new File((String)localObject4);
          if ((localObject1 != null) && (((File)localObject1).exists()) && (((File)localObject1).length() == 0L))
          {
            ((File)localObject1).delete();
            if ((localObject4 == null) || (!((File)localObject4).exists())) {
              continue;
            }
            ((File)localObject4).delete();
          }
        }
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        Object localObject2;
        for (;;)
        {
          localFileNotFoundException.printStackTrace();
          localObject2 = null;
        }
        localObject3 = a((String)localObject3);
        if ((localObject2 != null) && (((File)localObject2).exists())) {
          ((File)localObject2).delete();
        }
        if ((localObject4 != null) && (((File)localObject4).exists())) {
          ((File)localObject4).delete();
        }
        a((HashSet)localObject3);
        a("load_local_data", true);
      }
    }
  }
  
  private boolean b(String paramString, boolean paramBoolean)
  {
    return c.getSharedPreferences("txl.bat", 0).getBoolean(paramString, paramBoolean);
  }
  
  public void a(boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((!paramBoolean2) && (b("load_local_data", false))) {
      return;
    }
    ExecutorService localExecutorService = Executors.newSingleThreadExecutor(new app.a(null));
    localExecutorService.execute(new apq(this, paramBoolean1));
    localExecutorService.shutdown();
  }
  
  static class a
    implements ThreadFactory
  {
    public Thread newThread(Runnable paramRunnable)
    {
      paramRunnable = new Thread(paramRunnable);
      paramRunnable.setUncaughtExceptionHandler(new app.b(null));
      return paramRunnable;
    }
  }
  
  static class b
    implements Thread.UncaughtExceptionHandler
  {
    public void uncaughtException(Thread paramThread, Throwable paramThrowable)
    {
      Log.e(app.a(), "caught: " + paramThrowable);
      paramThrowable.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     app
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */