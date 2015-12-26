import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import org.w3c.dom.NodeList;

public class ig
  implements Runnable
{
  private static final Comparator<ig.c> a = new ih();
  private static ig b;
  private long c;
  private int d;
  private int e;
  private ArrayList<ig.c> f;
  private aua g;
  private Thread h;
  private ig.b i = ig.b.a;
  private ig.a j = ig.a.a;
  private ArrayList<aua> k;
  private att l;
  
  private void A()
  {
    try
    {
      int m = k.size() - 1;
      while (m >= 0)
      {
        ((aua)k.get(m)).d();
        m -= 1;
      }
      return;
    }
    finally {}
  }
  
  private void B()
  {
    try
    {
      int m = k.size() - 1;
      while (m >= 0)
      {
        ((aua)k.get(m)).h_();
        m -= 1;
      }
      return;
    }
    finally {}
  }
  
  private void C()
  {
    try
    {
      int n = k.size();
      int m = 0;
      while (m < n)
      {
        ((aua)k.get(m)).i_();
        m += 1;
      }
      return;
    }
    finally {}
  }
  
  /* Error */
  private void D()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial 88	ig:s	()Z
    //   6: ifne +54 -> 60
    //   9: aload_0
    //   10: invokespecial 91	ig:t	()Z
    //   13: ifne +47 -> 60
    //   16: aload_0
    //   17: invokespecial 94	ig:u	()Z
    //   20: ifne +40 -> 60
    //   23: aload_0
    //   24: invokespecial 97	ig:v	()Z
    //   27: ifne +33 -> 60
    //   30: aload_0
    //   31: invokespecial 100	ig:w	()Z
    //   34: ifne +26 -> 60
    //   37: aload_0
    //   38: ldc2_w 101
    //   41: invokevirtual 106	java/lang/Object:wait	(J)V
    //   44: goto -42 -> 2
    //   47: astore_1
    //   48: ldc 108
    //   50: ldc 110
    //   52: aload_1
    //   53: invokestatic 115	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   56: pop
    //   57: aload_0
    //   58: monitorexit
    //   59: return
    //   60: aload_0
    //   61: invokespecial 88	ig:s	()Z
    //   64: ifeq -7 -> 57
    //   67: aload_0
    //   68: getstatic 54	ig$a:a	Lig$a;
    //   71: putfield 56	ig:j	Lig$a;
    //   74: aload_0
    //   75: getstatic 117	ig$b:b	Lig$b;
    //   78: putfield 52	ig:i	Lig$b;
    //   81: goto -24 -> 57
    //   84: astore_1
    //   85: aload_0
    //   86: monitorexit
    //   87: aload_1
    //   88: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	89	0	this	ig
    //   47	6	1	localInterruptedException	InterruptedException
    //   84	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	44	47	java/lang/InterruptedException
    //   60	81	47	java/lang/InterruptedException
    //   2	44	84	finally
    //   48	57	84	finally
    //   60	81	84	finally
  }
  
  /* Error */
  private ig.c E()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 121	ig:d	I
    //   6: aload_0
    //   7: getfield 123	ig:f	Ljava/util/ArrayList;
    //   10: invokevirtual 65	java/util/ArrayList:size	()I
    //   13: if_icmpge +22 -> 35
    //   16: aload_0
    //   17: getfield 123	ig:f	Ljava/util/ArrayList;
    //   20: aload_0
    //   21: getfield 121	ig:d	I
    //   24: invokevirtual 69	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   27: checkcast 14	ig$c
    //   30: astore_1
    //   31: aload_0
    //   32: monitorexit
    //   33: aload_1
    //   34: areturn
    //   35: aconst_null
    //   36: astore_1
    //   37: goto -6 -> 31
    //   40: astore_1
    //   41: aload_0
    //   42: monitorexit
    //   43: aload_1
    //   44: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	45	0	this	ig
    //   30	7	1	localc	ig.c
    //   40	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	31	40	finally
  }
  
  private void F()
  {
    HashSet localHashSet = new HashSet();
    int n = f.size();
    int m = d;
    ig.c localc;
    int i1;
    if (m < n)
    {
      localc = (ig.c)f.get(m);
      i1 = localc.c();
      if (((localc.b() instanceof ie)) && (i1 == 1))
      {
        b(localc);
        d = m;
      }
    }
    else
    {
      return;
    }
    if ((i1 == 1) && (!localHashSet.contains(localc))) {
      b(localc);
    }
    for (;;)
    {
      m += 1;
      break;
      if (i1 == 0) {
        localHashSet.add(localc);
      }
    }
  }
  
  private ig.c G()
  {
    int n = f.size();
    int m = d;
    if (m < n)
    {
      localc = (ig.c)f.get(m);
      if (a(localc))
      {
        d = m;
        e = m;
        c = ((localc.a() * 1000.0D));
      }
    }
    do
    {
      return localc;
      m += 1;
      break;
      d += 1;
      localc = null;
    } while (d >= n);
    ig.c localc = (ig.c)f.get(d);
    c = ((localc.a() * 1000.0D));
    return localc;
  }
  
  private ig.c H()
  {
    int n = 1;
    int m = e;
    int i1 = -1;
    if (m >= 0)
    {
      ig.c localc = (ig.c)f.get(m);
      if (!a(localc)) {
        break label119;
      }
      i1 = n - 1;
      if (n == 0)
      {
        d = m;
        e = m;
        c = ((localc.a() * 1000.0D));
        return localc;
      }
      n = m;
    }
    for (;;)
    {
      m -= 1;
      int i2 = i1;
      i1 = n;
      n = i2;
      break;
      if (i1 != -1)
      {
        d = i1;
        e = i1;
        return (ig.c)f.get(d);
      }
      return null;
      label119:
      i2 = n;
      n = i1;
      i1 = i2;
    }
  }
  
  private ig.c I()
  {
    try
    {
      F();
      ig.c localc = G();
      return localc;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  private ig.c J()
  {
    try
    {
      F();
      ig.c localc = H();
      return localc;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  private void K()
  {
    try
    {
      B();
      i = ig.b.d;
      j = ig.a.a;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  private void L()
  {
    try
    {
      A();
      c = 0L;
      d = 0;
      e = 0;
      i = ig.b.e;
      j = ig.a.a;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  private void M()
  {
    try
    {
      x();
      j = ig.a.a;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public static ig a()
  {
    if (b == null) {
      b = new ig();
    }
    return b;
  }
  
  private static ArrayList<ig.c> a(aty paramaty, double paramDouble1, double paramDouble2)
  {
    int n = 0;
    ArrayList localArrayList = new ArrayList();
    double d1 = paramaty.g().a(0).c() + paramDouble1;
    if (d1 > paramDouble2) {
      return localArrayList;
    }
    localArrayList.add(new ig.c(d1, paramaty, 0));
    d1 = paramaty.h().a(0).c() + paramDouble1;
    if (d1 > paramDouble2) {}
    for (;;)
    {
      ig.c localc = new ig.c(paramDouble2, paramaty, 1);
      NodeList localNodeList = paramaty.j_();
      int m = 0;
      while (m < localNodeList.getLength())
      {
        localArrayList.addAll(a((aua)localNodeList.item(m), paramDouble1, paramDouble2));
        m += 1;
      }
      Collections.sort(localArrayList, a);
      paramaty = paramaty.a((float)(paramDouble2 - paramDouble1) * 1000.0F);
      m = n;
      while (m < paramaty.getLength())
      {
        localArrayList.add(new ig.c(paramDouble2, (aua)paramaty.item(m), 1));
        m += 1;
      }
      localArrayList.add(localc);
      return localArrayList;
      paramDouble2 = d1;
    }
  }
  
  private static ArrayList<ig.c> a(atz paramatz, double paramDouble1, double paramDouble2)
  {
    int n = 0;
    ArrayList localArrayList1 = new ArrayList();
    double d1 = paramatz.g().a(0).c() + paramDouble1;
    if (d1 > paramDouble2) {
      return localArrayList1;
    }
    localArrayList1.add(new ig.c(d1, paramatz, 0));
    d1 = paramatz.h().a(0).c() + paramDouble1;
    if (d1 > paramDouble2) {}
    for (;;)
    {
      ig.c localc = new ig.c(paramDouble2, paramatz, 1);
      NodeList localNodeList = paramatz.j_();
      int m = 0;
      d1 = paramDouble1;
      while (m < localNodeList.getLength())
      {
        ArrayList localArrayList2 = a((aua)localNodeList.item(m), d1, paramDouble2);
        localArrayList1.addAll(localArrayList2);
        d1 = ((ig.c)localArrayList2.get(localArrayList2.size() - 1)).a();
        m += 1;
      }
      paramatz = paramatz.a((float)(paramDouble2 - paramDouble1));
      m = n;
      while (m < paramatz.getLength())
      {
        localArrayList1.add(new ig.c(paramDouble2, (aua)paramatz.item(m), 1));
        m += 1;
      }
      localArrayList1.add(localc);
      return localArrayList1;
      paramDouble2 = d1;
    }
  }
  
  private static ArrayList<ig.c> a(aua paramaua, double paramDouble1, double paramDouble2)
  {
    int n = 0;
    if ((paramaua instanceof aty)) {
      return a((aty)paramaua, paramDouble1, paramDouble2);
    }
    if ((paramaua instanceof atz)) {
      return a((atz)paramaua, paramDouble1, paramDouble2);
    }
    ArrayList localArrayList = new ArrayList();
    aun localaun = paramaua.g();
    int m = 0;
    aum localaum;
    double d1;
    while (m < localaun.a())
    {
      localaum = localaun.a(m);
      if (localaum.b())
      {
        d1 = localaum.c() + paramDouble1;
        if (d1 <= paramDouble2) {
          localArrayList.add(new ig.c(d1, paramaua, 0));
        }
      }
      m += 1;
    }
    localaun = paramaua.h();
    m = n;
    while (m < localaun.a())
    {
      localaum = localaun.a(m);
      if (localaum.b())
      {
        d1 = localaum.c() + paramDouble1;
        if (d1 <= paramDouble2) {
          localArrayList.add(new ig.c(d1, paramaua, 1));
        }
      }
      m += 1;
    }
    Collections.sort(localArrayList, a);
    return localArrayList;
  }
  
  private void a(long paramLong)
  {
    long l1 = 0L;
    for (;;)
    {
      if (paramLong > 0L) {}
      try
      {
        long l3 = System.currentTimeMillis();
        long l2 = Math.min(paramLong, 200L);
        if (l1 < l2)
        {
          wait(l2 - l1);
          c += l2;
        }
        for (l1 = l2;; l1 = 0L)
        {
          if ((!t()) && (!u()) && (!r()) && (!v()))
          {
            boolean bool = w();
            if (!bool) {
              break;
            }
          }
          return;
          c += l1;
        }
        ((atw)g).a(l);
        paramLong -= 200L;
        l2 = System.currentTimeMillis();
        l1 = l2 - l3 - l1;
      }
      finally {}
    }
  }
  
  /* Error */
  private boolean a(ig.c paramc)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: invokevirtual 129	ig$c:c	()I
    //   6: ifne +21 -> 27
    //   9: aload_1
    //   10: invokevirtual 132	ig$c:b	()Laua;
    //   13: instanceof 134
    //   16: istore_2
    //   17: iload_2
    //   18: ifeq +9 -> 27
    //   21: iconst_1
    //   22: istore_2
    //   23: aload_0
    //   24: monitorexit
    //   25: iload_2
    //   26: ireturn
    //   27: iconst_0
    //   28: istore_2
    //   29: goto -6 -> 23
    //   32: astore_1
    //   33: aload_0
    //   34: monitorexit
    //   35: aload_1
    //   36: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	37	0	this	ig
    //   0	37	1	paramc	ig.c
    //   16	13	2	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   2	17	32	finally
  }
  
  /* Error */
  private double b(aua paramaua)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 150	ig:e	I
    //   6: istore 4
    //   8: iload 4
    //   10: aload_0
    //   11: getfield 121	ig:d	I
    //   14: if_icmpge +54 -> 68
    //   17: aload_0
    //   18: getfield 123	ig:f	Ljava/util/ArrayList;
    //   21: iload 4
    //   23: invokevirtual 69	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   26: checkcast 14	ig$c
    //   29: astore 5
    //   31: aload_1
    //   32: aload 5
    //   34: invokevirtual 132	ig$c:b	()Laua;
    //   37: invokevirtual 283	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   40: ifeq +19 -> 59
    //   43: aload 5
    //   45: invokevirtual 153	ig$c:a	()D
    //   48: dstore_2
    //   49: dload_2
    //   50: ldc2_w 154
    //   53: dmul
    //   54: dstore_2
    //   55: aload_0
    //   56: monitorexit
    //   57: dload_2
    //   58: dreturn
    //   59: iload 4
    //   61: iconst_1
    //   62: iadd
    //   63: istore 4
    //   65: goto -57 -> 8
    //   68: ldc2_w 284
    //   71: dstore_2
    //   72: goto -17 -> 55
    //   75: astore_1
    //   76: aload_0
    //   77: monitorexit
    //   78: aload_1
    //   79: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	80	0	this	ig
    //   0	80	1	paramaua	aua
    //   48	24	2	d1	double
    //   6	58	4	m	int
    //   29	15	5	localc	ig.c
    // Exception table:
    //   from	to	target	type
    //   2	8	75	finally
    //   8	49	75	finally
  }
  
  private void b(ig.c paramc)
  {
    for (;;)
    {
      try
      {
        int m = paramc.c();
        switch (m)
        {
        default: 
          return;
        }
      }
      finally {}
      paramc.b().c();
      k.add(paramc.b());
      continue;
      paramc.b().d();
      k.remove(paramc.b());
    }
  }
  
  /* Error */
  private boolean r()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 56	ig:j	Lig$a;
    //   6: astore_2
    //   7: getstatic 292	ig$a:d	Lig$a;
    //   10: astore_3
    //   11: aload_2
    //   12: aload_3
    //   13: if_acmpne +9 -> 22
    //   16: iconst_1
    //   17: istore_1
    //   18: aload_0
    //   19: monitorexit
    //   20: iload_1
    //   21: ireturn
    //   22: iconst_0
    //   23: istore_1
    //   24: goto -6 -> 18
    //   27: astore_2
    //   28: aload_0
    //   29: monitorexit
    //   30: aload_2
    //   31: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	32	0	this	ig
    //   17	7	1	bool	boolean
    //   6	6	2	locala1	ig.a
    //   27	4	2	localObject	Object
    //   10	3	3	locala2	ig.a
    // Exception table:
    //   from	to	target	type
    //   2	11	27	finally
  }
  
  /* Error */
  private boolean s()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 56	ig:j	Lig$a;
    //   6: astore_2
    //   7: getstatic 294	ig$a:e	Lig$a;
    //   10: astore_3
    //   11: aload_2
    //   12: aload_3
    //   13: if_acmpne +9 -> 22
    //   16: iconst_1
    //   17: istore_1
    //   18: aload_0
    //   19: monitorexit
    //   20: iload_1
    //   21: ireturn
    //   22: iconst_0
    //   23: istore_1
    //   24: goto -6 -> 18
    //   27: astore_2
    //   28: aload_0
    //   29: monitorexit
    //   30: aload_2
    //   31: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	32	0	this	ig
    //   17	7	1	bool	boolean
    //   6	6	2	locala1	ig.a
    //   27	4	2	localObject	Object
    //   10	3	3	locala2	ig.a
    // Exception table:
    //   from	to	target	type
    //   2	11	27	finally
  }
  
  /* Error */
  private boolean t()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 56	ig:j	Lig$a;
    //   6: astore_2
    //   7: getstatic 296	ig$a:c	Lig$a;
    //   10: astore_3
    //   11: aload_2
    //   12: aload_3
    //   13: if_acmpne +9 -> 22
    //   16: iconst_1
    //   17: istore_1
    //   18: aload_0
    //   19: monitorexit
    //   20: iload_1
    //   21: ireturn
    //   22: iconst_0
    //   23: istore_1
    //   24: goto -6 -> 18
    //   27: astore_2
    //   28: aload_0
    //   29: monitorexit
    //   30: aload_2
    //   31: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	32	0	this	ig
    //   17	7	1	bool	boolean
    //   6	6	2	locala1	ig.a
    //   27	4	2	localObject	Object
    //   10	3	3	locala2	ig.a
    // Exception table:
    //   from	to	target	type
    //   2	11	27	finally
  }
  
  /* Error */
  private boolean u()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 56	ig:j	Lig$a;
    //   6: astore_2
    //   7: getstatic 298	ig$a:b	Lig$a;
    //   10: astore_3
    //   11: aload_2
    //   12: aload_3
    //   13: if_acmpne +9 -> 22
    //   16: iconst_1
    //   17: istore_1
    //   18: aload_0
    //   19: monitorexit
    //   20: iload_1
    //   21: ireturn
    //   22: iconst_0
    //   23: istore_1
    //   24: goto -6 -> 18
    //   27: astore_2
    //   28: aload_0
    //   29: monitorexit
    //   30: aload_2
    //   31: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	32	0	this	ig
    //   17	7	1	bool	boolean
    //   6	6	2	locala1	ig.a
    //   27	4	2	localObject	Object
    //   10	3	3	locala2	ig.a
    // Exception table:
    //   from	to	target	type
    //   2	11	27	finally
  }
  
  /* Error */
  private boolean v()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 56	ig:j	Lig$a;
    //   6: astore_2
    //   7: getstatic 300	ig$a:f	Lig$a;
    //   10: astore_3
    //   11: aload_2
    //   12: aload_3
    //   13: if_acmpne +9 -> 22
    //   16: iconst_1
    //   17: istore_1
    //   18: aload_0
    //   19: monitorexit
    //   20: iload_1
    //   21: ireturn
    //   22: iconst_0
    //   23: istore_1
    //   24: goto -6 -> 18
    //   27: astore_2
    //   28: aload_0
    //   29: monitorexit
    //   30: aload_2
    //   31: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	32	0	this	ig
    //   17	7	1	bool	boolean
    //   6	6	2	locala1	ig.a
    //   27	4	2	localObject	Object
    //   10	3	3	locala2	ig.a
    // Exception table:
    //   from	to	target	type
    //   2	11	27	finally
  }
  
  /* Error */
  private boolean w()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 56	ig:j	Lig$a;
    //   6: astore_2
    //   7: getstatic 302	ig$a:g	Lig$a;
    //   10: astore_3
    //   11: aload_2
    //   12: aload_3
    //   13: if_acmpne +9 -> 22
    //   16: iconst_1
    //   17: istore_1
    //   18: aload_0
    //   19: monitorexit
    //   20: iload_1
    //   21: ireturn
    //   22: iconst_0
    //   23: istore_1
    //   24: goto -6 -> 18
    //   27: astore_2
    //   28: aload_0
    //   29: monitorexit
    //   30: aload_2
    //   31: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	32	0	this	ig
    //   17	7	1	bool	boolean
    //   6	6	2	locala1	ig.a
    //   27	4	2	localObject	Object
    //   10	3	3	locala2	ig.a
    // Exception table:
    //   from	to	target	type
    //   2	11	27	finally
  }
  
  private void x()
  {
    try
    {
      k.clear();
      y();
      int m = e;
      while (m < d)
      {
        b((ig.c)f.get(m));
        m += 1;
      }
      z();
      return;
    }
    finally {}
  }
  
  private void y()
  {
    try
    {
      b((ig.c)f.get(0));
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  private void z()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 59	ig:k	Ljava/util/ArrayList;
    //   6: invokevirtual 65	java/util/ArrayList:size	()I
    //   9: iconst_1
    //   10: isub
    //   11: istore_3
    //   12: iload_3
    //   13: iflt +28 -> 41
    //   16: aload_0
    //   17: getfield 59	ig:k	Ljava/util/ArrayList;
    //   20: iload_3
    //   21: invokevirtual 69	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   24: checkcast 71	aua
    //   27: astore 5
    //   29: aload 5
    //   31: instanceof 134
    //   34: istore 4
    //   36: iload 4
    //   38: ifeq +6 -> 44
    //   41: aload_0
    //   42: monitorexit
    //   43: return
    //   44: aload_0
    //   45: aload 5
    //   47: invokespecial 313	ig:b	(Laua;)D
    //   50: dstore_1
    //   51: dload_1
    //   52: dconst_0
    //   53: dcmpl
    //   54: iflt +28 -> 82
    //   57: dload_1
    //   58: aload_0
    //   59: getfield 157	ig:c	J
    //   62: l2d
    //   63: dcmpg
    //   64: ifgt +18 -> 82
    //   67: aload 5
    //   69: aload_0
    //   70: getfield 157	ig:c	J
    //   73: l2d
    //   74: dload_1
    //   75: dsub
    //   76: d2f
    //   77: invokeinterface 316 2 0
    //   82: iload_3
    //   83: iconst_1
    //   84: isub
    //   85: istore_3
    //   86: goto -74 -> 12
    //   89: astore 5
    //   91: aload_0
    //   92: monitorexit
    //   93: aload 5
    //   95: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	96	0	this	ig
    //   50	25	1	d1	double
    //   11	75	3	m	int
    //   34	3	4	bool	boolean
    //   27	41	5	localaua	aua
    //   89	5	5	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	12	89	finally
    //   16	36	89	finally
    //   44	51	89	finally
    //   57	82	89	finally
  }
  
  public void a(aua paramaua)
  {
    try
    {
      g = paramaua;
      f = a(g, 0.0D, 9.223372036854776E18D);
      l = ((ats)g).a("Event");
      l.a("mediaTimeUpdated", false, false);
      k = new ArrayList();
      return;
    }
    finally
    {
      paramaua = finally;
      throw paramaua;
    }
  }
  
  /* Error */
  public boolean b()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 52	ig:i	Lig$b;
    //   6: astore_2
    //   7: getstatic 117	ig$b:b	Lig$b;
    //   10: astore_3
    //   11: aload_2
    //   12: aload_3
    //   13: if_acmpne +9 -> 22
    //   16: iconst_1
    //   17: istore_1
    //   18: aload_0
    //   19: monitorexit
    //   20: iload_1
    //   21: ireturn
    //   22: iconst_0
    //   23: istore_1
    //   24: goto -6 -> 18
    //   27: astore_2
    //   28: aload_0
    //   29: monitorexit
    //   30: aload_2
    //   31: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	32	0	this	ig
    //   17	7	1	bool	boolean
    //   6	6	2	localb1	ig.b
    //   27	4	2	localObject	Object
    //   10	3	3	localb2	ig.b
    // Exception table:
    //   from	to	target	type
    //   2	11	27	finally
  }
  
  /* Error */
  public boolean c()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 52	ig:i	Lig$b;
    //   6: astore_2
    //   7: getstatic 335	ig$b:c	Lig$b;
    //   10: astore_3
    //   11: aload_2
    //   12: aload_3
    //   13: if_acmpne +9 -> 22
    //   16: iconst_1
    //   17: istore_1
    //   18: aload_0
    //   19: monitorexit
    //   20: iload_1
    //   21: ireturn
    //   22: iconst_0
    //   23: istore_1
    //   24: goto -6 -> 18
    //   27: astore_2
    //   28: aload_0
    //   29: monitorexit
    //   30: aload_2
    //   31: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	32	0	this	ig
    //   17	7	1	bool	boolean
    //   6	6	2	localb1	ig.b
    //   27	4	2	localObject	Object
    //   10	3	3	localb2	ig.b
    // Exception table:
    //   from	to	target	type
    //   2	11	27	finally
  }
  
  /* Error */
  public boolean d()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 52	ig:i	Lig$b;
    //   6: astore_2
    //   7: getstatic 169	ig$b:d	Lig$b;
    //   10: astore_3
    //   11: aload_2
    //   12: aload_3
    //   13: if_acmpne +9 -> 22
    //   16: iconst_1
    //   17: istore_1
    //   18: aload_0
    //   19: monitorexit
    //   20: iload_1
    //   21: ireturn
    //   22: iconst_0
    //   23: istore_1
    //   24: goto -6 -> 18
    //   27: astore_2
    //   28: aload_0
    //   29: monitorexit
    //   30: aload_2
    //   31: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	32	0	this	ig
    //   17	7	1	bool	boolean
    //   6	6	2	localb1	ig.b
    //   27	4	2	localObject	Object
    //   10	3	3	localb2	ig.b
    // Exception table:
    //   from	to	target	type
    //   2	11	27	finally
  }
  
  /* Error */
  public boolean e()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 52	ig:i	Lig$b;
    //   6: astore_2
    //   7: getstatic 174	ig$b:e	Lig$b;
    //   10: astore_3
    //   11: aload_2
    //   12: aload_3
    //   13: if_acmpne +9 -> 22
    //   16: iconst_1
    //   17: istore_1
    //   18: aload_0
    //   19: monitorexit
    //   20: iload_1
    //   21: ireturn
    //   22: iconst_0
    //   23: istore_1
    //   24: goto -6 -> 18
    //   27: astore_2
    //   28: aload_0
    //   29: monitorexit
    //   30: aload_2
    //   31: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	32	0	this	ig
    //   17	7	1	bool	boolean
    //   6	6	2	localb1	ig.b
    //   27	4	2	localObject	Object
    //   10	3	3	localb2	ig.b
    // Exception table:
    //   from	to	target	type
    //   2	11	27	finally
  }
  
  /* Error */
  public void f()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual 336	ig:b	()Z
    //   6: ifne +50 -> 56
    //   9: aload_0
    //   10: lconst_0
    //   11: putfield 157	ig:c	J
    //   14: aload_0
    //   15: iconst_0
    //   16: putfield 121	ig:d	I
    //   19: aload_0
    //   20: iconst_0
    //   21: putfield 150	ig:e	I
    //   24: aload_0
    //   25: new 338	java/lang/Thread
    //   28: dup
    //   29: aload_0
    //   30: ldc_w 340
    //   33: invokespecial 343	java/lang/Thread:<init>	(Ljava/lang/Runnable;Ljava/lang/String;)V
    //   36: putfield 345	ig:h	Ljava/lang/Thread;
    //   39: aload_0
    //   40: getstatic 117	ig$b:b	Lig$b;
    //   43: putfield 52	ig:i	Lig$b;
    //   46: aload_0
    //   47: getfield 345	ig:h	Ljava/lang/Thread;
    //   50: invokevirtual 348	java/lang/Thread:start	()V
    //   53: aload_0
    //   54: monitorexit
    //   55: return
    //   56: ldc 108
    //   58: ldc_w 350
    //   61: invokestatic 353	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   64: pop
    //   65: goto -12 -> 53
    //   68: astore_1
    //   69: aload_0
    //   70: monitorexit
    //   71: aload_1
    //   72: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	73	0	this	ig
    //   68	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	53	68	finally
    //   56	65	68	finally
  }
  
  /* Error */
  public void g()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual 336	ig:b	()Z
    //   6: ifeq +17 -> 23
    //   9: aload_0
    //   10: getstatic 292	ig$a:d	Lig$a;
    //   13: putfield 56	ig:j	Lig$a;
    //   16: aload_0
    //   17: invokevirtual 356	java/lang/Object:notifyAll	()V
    //   20: aload_0
    //   21: monitorexit
    //   22: return
    //   23: ldc 108
    //   25: ldc_w 358
    //   28: invokestatic 353	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   31: pop
    //   32: goto -12 -> 20
    //   35: astore_1
    //   36: aload_0
    //   37: monitorexit
    //   38: aload_1
    //   39: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	40	0	this	ig
    //   35	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	20	35	finally
    //   23	32	35	finally
  }
  
  public void h()
  {
    for (;;)
    {
      try
      {
        if (d())
        {
          C();
          j = ig.a.e;
          notifyAll();
          return;
        }
        if (c()) {
          f();
        } else {
          Log.w("Mms/smil", "Error State: Playback can not be started!");
        }
      }
      finally {}
    }
  }
  
  /* Error */
  public void i()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual 336	ig:b	()Z
    //   6: ifne +10 -> 16
    //   9: aload_0
    //   10: invokevirtual 359	ig:d	()Z
    //   13: ifeq +17 -> 30
    //   16: aload_0
    //   17: getstatic 296	ig$a:c	Lig$a;
    //   20: putfield 56	ig:j	Lig$a;
    //   23: aload_0
    //   24: invokevirtual 356	java/lang/Object:notifyAll	()V
    //   27: aload_0
    //   28: monitorexit
    //   29: return
    //   30: aload_0
    //   31: invokevirtual 362	ig:c	()Z
    //   34: ifeq -7 -> 27
    //   37: aload_0
    //   38: invokespecial 368	ig:L	()V
    //   41: goto -14 -> 27
    //   44: astore_1
    //   45: aload_0
    //   46: monitorexit
    //   47: aload_1
    //   48: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	49	0	this	ig
    //   44	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	16	44	finally
    //   16	27	44	finally
    //   30	41	44	finally
  }
  
  public void j()
  {
    try
    {
      A();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  public void k()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual 336	ig:b	()Z
    //   6: ifne +10 -> 16
    //   9: aload_0
    //   10: invokevirtual 359	ig:d	()Z
    //   13: ifeq +17 -> 30
    //   16: aload_0
    //   17: getstatic 298	ig$a:b	Lig$a;
    //   20: putfield 56	ig:j	Lig$a;
    //   23: aload_0
    //   24: invokevirtual 356	java/lang/Object:notifyAll	()V
    //   27: aload_0
    //   28: monitorexit
    //   29: return
    //   30: aload_0
    //   31: invokevirtual 362	ig:c	()Z
    //   34: ifeq -7 -> 27
    //   37: aload_0
    //   38: invokespecial 370	ig:M	()V
    //   41: goto -14 -> 27
    //   44: astore_1
    //   45: aload_0
    //   46: monitorexit
    //   47: aload_1
    //   48: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	49	0	this	ig
    //   44	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	16	44	finally
    //   16	27	44	finally
    //   30	41	44	finally
  }
  
  public void l()
  {
    try
    {
      if ((b()) || (d()))
      {
        j = ig.a.f;
        notifyAll();
      }
      return;
    }
    finally {}
  }
  
  public void m()
  {
    try
    {
      if ((b()) || (d()))
      {
        j = ig.a.g;
        notifyAll();
      }
      return;
    }
    finally {}
  }
  
  /* Error */
  public int n()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 123	ig:f	Ljava/util/ArrayList;
    //   6: ifnull +47 -> 53
    //   9: aload_0
    //   10: getfield 123	ig:f	Ljava/util/ArrayList;
    //   13: invokevirtual 375	java/util/ArrayList:isEmpty	()Z
    //   16: ifne +37 -> 53
    //   19: aload_0
    //   20: getfield 123	ig:f	Ljava/util/ArrayList;
    //   23: aload_0
    //   24: getfield 123	ig:f	Ljava/util/ArrayList;
    //   27: invokevirtual 65	java/util/ArrayList:size	()I
    //   30: iconst_1
    //   31: isub
    //   32: invokevirtual 69	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   35: checkcast 14	ig$c
    //   38: invokestatic 378	ig$c:a	(Lig$c;)D
    //   41: dstore_1
    //   42: dload_1
    //   43: d2i
    //   44: sipush 1000
    //   47: imul
    //   48: istore_3
    //   49: aload_0
    //   50: monitorexit
    //   51: iload_3
    //   52: ireturn
    //   53: iconst_0
    //   54: istore_3
    //   55: goto -6 -> 49
    //   58: astore 4
    //   60: aload_0
    //   61: monitorexit
    //   62: aload 4
    //   64: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	65	0	this	ig
    //   41	2	1	d1	double
    //   48	7	3	m	int
    //   58	5	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	42	58	finally
  }
  
  public int o()
  {
    try
    {
      long l1 = c;
      int m = (int)l1;
      return m;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void p()
  {
    try
    {
      j = ig.a.e;
      notifyAll();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public int q()
  {
    return e;
  }
  
  public void run()
  {
    if (e()) {
      return;
    }
    int m = f.size();
    for (d = 0;; d += 1)
    {
      if (d >= m) {
        break label401;
      }
      Object localObject3 = (ig.c)f.get(d);
      if (a((ig.c)localObject3)) {
        e = d;
      }
      long l2 = (((ig.c)localObject3).a() * 1000.0D);
      label74:
      if (l2 > c)
      {
        long l1;
        label286:
        label295:
        do
        {
          try
          {
            a(l2 - c);
            Object localObject1 = localObject3;
            l1 = l2;
            if ((!r()) && (!t()) && (!u()) && (!v()))
            {
              l2 = l1;
              localObject3 = localObject1;
              if (!w()) {
                break label74;
              }
            }
            if (r())
            {
              K();
              D();
            }
            if (t())
            {
              L();
              return;
            }
          }
          catch (InterruptedException localInterruptedException)
          {
            for (;;)
            {
              Log.e("Mms/smil", "Unexpected InterruptedException.", localInterruptedException);
              l1 = l2;
              localObject2 = localObject3;
            }
            if (u())
            {
              M();
              localObject3 = E();
              if (localObject3 == null) {
                break;
              }
              localObject2 = localObject3;
              if (d())
              {
                j = ig.a.d;
                localObject2 = localObject3;
              }
            }
            localObject3 = localObject2;
            if (!v()) {
              break label295;
            }
          }
          localObject3 = I();
          if (localObject3 != null) {
            localObject2 = localObject3;
          }
          if (i != ig.b.d) {
            break label356;
          }
          j = ig.a.d;
          b((ig.c)localObject2);
          l1 = c;
          localObject3 = localObject2;
          localObject2 = localObject3;
        } while (!w());
        ig.c localc = J();
        Object localObject2 = localObject3;
        if (localc != null) {
          localObject2 = localc;
        }
        if (i == ig.b.d)
        {
          j = ig.a.d;
          b((ig.c)localObject2);
        }
        for (;;)
        {
          l1 = c;
          break;
          label356:
          j = ig.a.a;
          break label286;
          j = ig.a.a;
        }
      }
      c = l2;
      b((ig.c)localObject3);
    }
    label401:
    i = ig.b.c;
  }
  
  static enum a
  {
    private a() {}
  }
  
  static enum b
  {
    private b() {}
  }
  
  static final class c
  {
    private final double a;
    private final aua b;
    private final int c;
    
    public c(double paramDouble, aua paramaua, int paramInt)
    {
      a = paramDouble;
      b = paramaua;
      c = paramInt;
    }
    
    public double a()
    {
      return a;
    }
    
    public aua b()
    {
      return b;
    }
    
    public int c()
    {
      return c;
    }
    
    public String toString()
    {
      return "Type = " + b + " offset = " + a() + " action = " + c();
    }
  }
}

/* Location:
 * Qualified Name:     ig
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */