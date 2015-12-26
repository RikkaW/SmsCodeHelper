import android.content.Context;
import java.lang.ref.WeakReference;
import java.util.LinkedList;

public class akd
{
  private static LinkedList<WeakReference<Context>> a;
  
  /* Error */
  public static Context a()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 10	akd:a	Ljava/util/LinkedList;
    //   6: ifnull +58 -> 64
    //   9: getstatic 10	akd:a	Ljava/util/LinkedList;
    //   12: invokevirtual 16	java/util/LinkedList:size	()I
    //   15: ifle +49 -> 64
    //   18: getstatic 10	akd:a	Ljava/util/LinkedList;
    //   21: invokevirtual 16	java/util/LinkedList:size	()I
    //   24: iconst_1
    //   25: isub
    //   26: istore_0
    //   27: iload_0
    //   28: iflt +36 -> 64
    //   31: getstatic 10	akd:a	Ljava/util/LinkedList;
    //   34: iload_0
    //   35: invokevirtual 20	java/util/LinkedList:get	(I)Ljava/lang/Object;
    //   38: checkcast 22	java/lang/ref/WeakReference
    //   41: invokevirtual 25	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   44: checkcast 27	android/content/Context
    //   47: astore_1
    //   48: aload_1
    //   49: ifnull +8 -> 57
    //   52: ldc 2
    //   54: monitorexit
    //   55: aload_1
    //   56: areturn
    //   57: iload_0
    //   58: iconst_1
    //   59: isub
    //   60: istore_0
    //   61: goto -34 -> 27
    //   64: aconst_null
    //   65: astore_1
    //   66: goto -14 -> 52
    //   69: astore_1
    //   70: ldc 2
    //   72: monitorexit
    //   73: aload_1
    //   74: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   26	35	0	i	int
    //   47	19	1	localContext	Context
    //   69	5	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   3	27	69	finally
    //   31	48	69	finally
  }
  
  /* Error */
  public static final void a(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: invokestatic 33	akd:b	()V
    //   6: aload_0
    //   7: invokestatic 37	akd:c	(Landroid/content/Context;)I
    //   10: iconst_m1
    //   11: if_icmpne +44 -> 55
    //   14: getstatic 10	akd:a	Ljava/util/LinkedList;
    //   17: new 22	java/lang/ref/WeakReference
    //   20: dup
    //   21: aload_0
    //   22: invokespecial 41	java/lang/ref/WeakReference:<init>	(Ljava/lang/Object;)V
    //   25: invokevirtual 45	java/util/LinkedList:add	(Ljava/lang/Object;)Z
    //   28: pop
    //   29: new 47	java/lang/StringBuilder
    //   32: dup
    //   33: invokespecial 49	java/lang/StringBuilder:<init>	()V
    //   36: ldc 51
    //   38: invokevirtual 55	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: aload_0
    //   42: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   45: invokevirtual 62	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   48: invokestatic 67	anf:b	(Ljava/lang/String;)V
    //   51: ldc 2
    //   53: monitorexit
    //   54: return
    //   55: new 47	java/lang/StringBuilder
    //   58: dup
    //   59: invokespecial 49	java/lang/StringBuilder:<init>	()V
    //   62: ldc 69
    //   64: invokevirtual 55	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: aload_0
    //   68: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   71: invokevirtual 62	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   74: invokestatic 67	anf:b	(Ljava/lang/String;)V
    //   77: goto -26 -> 51
    //   80: astore_0
    //   81: ldc 2
    //   83: monitorexit
    //   84: aload_0
    //   85: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	86	0	paramContext	Context
    // Exception table:
    //   from	to	target	type
    //   3	51	80	finally
    //   55	77	80	finally
  }
  
  private static void b()
  {
    if (a == null)
    {
      anf.b("init com list");
      a = new LinkedList();
    }
  }
  
  /* Error */
  public static final void b(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: invokestatic 37	akd:c	(Landroid/content/Context;)I
    //   7: istore_1
    //   8: iload_1
    //   9: iconst_m1
    //   10: if_icmpeq +40 -> 50
    //   13: getstatic 10	akd:a	Ljava/util/LinkedList;
    //   16: iload_1
    //   17: invokevirtual 75	java/util/LinkedList:remove	(I)Ljava/lang/Object;
    //   20: pop
    //   21: new 47	java/lang/StringBuilder
    //   24: dup
    //   25: invokespecial 49	java/lang/StringBuilder:<init>	()V
    //   28: ldc 77
    //   30: invokevirtual 55	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   33: aload_0
    //   34: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   37: invokevirtual 62	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   40: invokestatic 67	anf:b	(Ljava/lang/String;)V
    //   43: invokestatic 79	akd:c	()V
    //   46: ldc 2
    //   48: monitorexit
    //   49: return
    //   50: new 47	java/lang/StringBuilder
    //   53: dup
    //   54: invokespecial 49	java/lang/StringBuilder:<init>	()V
    //   57: ldc 81
    //   59: invokevirtual 55	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: aload_0
    //   63: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   66: invokevirtual 62	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   69: invokestatic 67	anf:b	(Ljava/lang/String;)V
    //   72: goto -29 -> 43
    //   75: astore_0
    //   76: ldc 2
    //   78: monitorexit
    //   79: aload_0
    //   80: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	81	0	paramContext	Context
    //   7	10	1	i	int
    // Exception table:
    //   from	to	target	type
    //   3	8	75	finally
    //   13	43	75	finally
    //   43	46	75	finally
    //   50	72	75	finally
  }
  
  private static int c(Context paramContext)
  {
    if ((a != null) && (a.size() > 0))
    {
      int i = a.size() - 1;
      while (i >= 0)
      {
        if ((Context)((WeakReference)a.get(i)).get() == paramContext) {
          return i;
        }
        i -= 1;
      }
    }
    return -1;
  }
  
  private static void c()
  {
    if (a != null)
    {
      int i = a.size() - 1;
      while (i >= 0)
      {
        if ((Context)((WeakReference)a.get(i)).get() == null)
        {
          anf.b("discard no reference list index:" + i);
          a.remove(i);
        }
        i -= 1;
      }
      if (a.size() == 0)
      {
        anf.b("discard com list");
        a = null;
      }
    }
  }
}

/* Location:
 * Qualified Name:     akd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */