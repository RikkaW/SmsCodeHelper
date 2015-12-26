import java.util.ArrayList;
import java.util.Iterator;

public abstract class on
{
  private final ArrayList<oo> a = new ArrayList();
  private Iterator<oo> b;
  
  public void a(oo paramoo)
  {
    a.add(paramoo);
  }
  
  public void b(oo paramoo)
  {
    if (b != null)
    {
      b.remove();
      return;
    }
    a.remove(paramoo);
  }
  
  /* Error */
  public void d()
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_0
    //   2: getfield 19	on:a	Ljava/util/ArrayList;
    //   5: invokevirtual 39	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   8: putfield 27	on:b	Ljava/util/Iterator;
    //   11: aload_0
    //   12: getfield 27	on:b	Ljava/util/Iterator;
    //   15: invokeinterface 43 1 0
    //   20: ifeq +32 -> 52
    //   23: aload_0
    //   24: getfield 27	on:b	Ljava/util/Iterator;
    //   27: invokeinterface 47 1 0
    //   32: checkcast 49	oo
    //   35: aload_0
    //   36: invokeinterface 52 2 0
    //   41: goto -30 -> 11
    //   44: astore_1
    //   45: aload_0
    //   46: aconst_null
    //   47: putfield 27	on:b	Ljava/util/Iterator;
    //   50: aload_1
    //   51: athrow
    //   52: aload_0
    //   53: aconst_null
    //   54: putfield 27	on:b	Ljava/util/Iterator;
    //   57: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	58	0	this	on
    //   44	7	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   11	41	44	finally
  }
}

/* Location:
 * Qualified Name:     on
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */