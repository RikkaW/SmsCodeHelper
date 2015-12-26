import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.meizu.android.mms.pdu.MzGenericPdu;
import com.meizu.android.mms.pdu.MzPduPersister;
import com.meizu.android.mms.util.MzPduCache;
import com.meizu.android.mms.util.MzPduCacheEntry;
import java.util.Set;
import java.util.concurrent.Executor;

public class aan
  extends zh
{
  private static MzPduCache e;
  private final MzPduPersister f;
  private final aay<Uri, lr> g = new aay(8, 16, 0.75F, true);
  private final Context h;
  
  public aan(Context paramContext)
  {
    super(paramContext);
    e = MzPduCache.getInstance();
    f = MzPduPersister.getPduPersister(paramContext);
    h = paramContext;
  }
  
  public zz a(Uri paramUri, boolean paramBoolean, zy<aan.a> paramzy)
  {
    int m = 1;
    if (paramUri == null) {
      throw new NullPointerException();
    }
    for (;;)
    {
      synchronized (e)
      {
        if (e.isUpdating(paramUri)) {
          break label266;
        }
        localMzPduCacheEntry = (MzPduCacheEntry)e.get(paramUri);
        if (paramBoolean)
        {
          ??? = (lr)g.a(paramUri);
          if ((paramBoolean) && (??? == null)) {
            break label183;
          }
          i = 1;
          if ((localMzPduCacheEntry == null) || (localMzPduCacheEntry.getPdu() == null)) {
            break label189;
          }
          j = 1;
          boolean bool = a.contains(paramUri);
          if (((j != 0) && (i != 0)) || (bool)) {
            break label195;
          }
          k = 1;
          if (paramzy == null) {
            break label201;
          }
          if ((j == 0) || (i == 0)) {
            break label207;
          }
          if (m != 0) {
            paramzy.a(new aan.a(localMzPduCacheEntry.getPdu(), (lr)???), null);
          }
          return new aam();
        }
      }
      ??? = null;
      continue;
      label183:
      int i = 0;
      continue;
      label189:
      int j = 0;
      continue;
      label195:
      int k = 0;
      continue;
      label201:
      m = 0;
      continue;
      label207:
      if (m != 0) {
        a(paramUri, paramzy);
      }
      if (k != 0)
      {
        a.add(paramUri);
        paramUri = new aan.b(paramUri, paramBoolean);
        c.execute(paramUri);
      }
      return new aao(this, paramzy);
      label266:
      MzPduCacheEntry localMzPduCacheEntry = null;
    }
  }
  
  public void a(Uri paramUri)
  {
    if (Log.isLoggable("Mms:PduLoaderManager", 3)) {
      Log.d("Mms:PduLoaderManager", "removePdu: " + paramUri);
    }
    if (paramUri != null) {}
    synchronized (e)
    {
      e.purge(paramUri);
      g.b(paramUri);
      return;
    }
  }
  
  public void b()
  {
    super.b();
    synchronized (e)
    {
      e.purgeAll();
      g.a();
      return;
    }
  }
  
  public String c()
  {
    return "Mms:PduLoaderManager";
  }
  
  public static class a
  {
    public final MzGenericPdu a;
    public final lr b;
    
    public a(MzGenericPdu paramMzGenericPdu, lr paramlr)
    {
      a = paramMzGenericPdu;
      b = paramlr;
    }
  }
  
  public class b
    implements Runnable
  {
    private final Uri b;
    private final boolean c;
    
    public b(Uri paramUri, boolean paramBoolean)
    {
      if (paramUri == null) {
        throw new NullPointerException();
      }
      b = paramUri;
      c = paramBoolean;
    }
    
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore_3
      //   2: aload_0
      //   3: getfield 18	aan$b:a	Laan;
      //   6: invokestatic 36	aan:a	(Laan;)Lcom/meizu/android/mms/pdu/MzPduPersister;
      //   9: aload_0
      //   10: getfield 26	aan$b:b	Landroid/net/Uri;
      //   13: iconst_1
      //   14: invokevirtual 42	com/meizu/android/mms/pdu/MzPduPersister:load	(Landroid/net/Uri;Z)Lcom/meizu/android/mms/util/MzPduCacheEntry;
      //   17: astore_2
      //   18: aload_2
      //   19: invokevirtual 48	com/meizu/android/mms/util/MzPduCacheEntry:getPdu	()Lcom/meizu/android/mms/pdu/MzGenericPdu;
      //   22: astore_1
      //   23: aload_1
      //   24: ifnull +92 -> 116
      //   27: aload_0
      //   28: getfield 28	aan$b:c	Z
      //   31: ifeq +85 -> 116
      //   34: aload_0
      //   35: getfield 18	aan$b:a	Laan;
      //   38: invokestatic 51	aan:b	(Laan;)Landroid/content/Context;
      //   41: aload_2
      //   42: invokestatic 56	lr:a	(Landroid/content/Context;Lcom/meizu/android/mms/util/MzPduCacheEntry;)Llr;
      //   45: astore_2
      //   46: aload_0
      //   47: getfield 18	aan$b:a	Laan;
      //   50: getfield 60	aan:d	Landroid/os/Handler;
      //   53: new 62	aap
      //   56: dup
      //   57: aload_0
      //   58: aload_1
      //   59: aload_2
      //   60: aload_3
      //   61: invokespecial 65	aap:<init>	(Laan$b;Lcom/meizu/android/mms/pdu/MzGenericPdu;Llr;Ljava/lang/Throwable;)V
      //   64: invokevirtual 71	android/os/Handler:post	(Ljava/lang/Runnable;)Z
      //   67: pop
      //   68: return
      //   69: astore_2
      //   70: aconst_null
      //   71: astore_1
      //   72: ldc 73
      //   74: new 75	java/lang/StringBuilder
      //   77: dup
      //   78: invokespecial 76	java/lang/StringBuilder:<init>	()V
      //   81: ldc 78
      //   83: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   86: aload_0
      //   87: getfield 26	aan$b:b	Landroid/net/Uri;
      //   90: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   93: invokevirtual 89	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   96: aload_2
      //   97: invokestatic 95	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   100: pop
      //   101: aconst_null
      //   102: astore 4
      //   104: aload_2
      //   105: astore_3
      //   106: aload 4
      //   108: astore_2
      //   109: goto -63 -> 46
      //   112: astore_2
      //   113: goto -41 -> 72
      //   116: aconst_null
      //   117: astore_2
      //   118: goto -72 -> 46
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	121	0	this	b
      //   22	50	1	localMzGenericPdu	MzGenericPdu
      //   17	43	2	localObject1	Object
      //   69	36	2	localMmsException1	com.google.android.mms.MmsException
      //   108	1	2	localObject2	Object
      //   112	1	2	localMmsException2	com.google.android.mms.MmsException
      //   117	1	2	localObject3	Object
      //   1	105	3	localObject4	Object
      //   102	5	4	localObject5	Object
      // Exception table:
      //   from	to	target	type
      //   2	23	69	com/google/android/mms/MmsException
      //   27	46	112	com/google/android/mms/MmsException
    }
  }
}

/* Location:
 * Qualified Name:     aan
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */