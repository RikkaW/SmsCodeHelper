class ard$a
{
  long a = 0L;
  a b;
  
  private void b()
  {
    if (b == null) {
      b = new a();
    }
  }
  
  void a()
  {
    a = 0L;
    if (b != null) {
      b.a();
    }
  }
  
  void a(int paramInt)
  {
    if (paramInt >= 64)
    {
      b();
      b.a(paramInt - 64);
      return;
    }
    a |= 1L << paramInt;
  }
  
  void a(int paramInt, boolean paramBoolean)
  {
    if (paramInt >= 64)
    {
      b();
      b.a(paramInt - 64, paramBoolean);
    }
    label113:
    label119:
    for (;;)
    {
      return;
      boolean bool;
      if ((a & 0x8000000000000000) != 0L)
      {
        bool = true;
        long l1 = (1L << paramInt) - 1L;
        long l2 = a;
        a = (((l1 ^ 0xFFFFFFFFFFFFFFFF) & a) << 1 | l2 & l1);
        if (!paramBoolean) {
          break label113;
        }
        a(paramInt);
      }
      for (;;)
      {
        if ((!bool) && (b == null)) {
          break label119;
        }
        b();
        b.a(0, bool);
        return;
        bool = false;
        break;
        b(paramInt);
      }
    }
  }
  
  void b(int paramInt)
  {
    if (paramInt >= 64)
    {
      if (b != null) {
        b.b(paramInt - 64);
      }
      return;
    }
    a &= (1L << paramInt ^ 0xFFFFFFFFFFFFFFFF);
  }
  
  boolean c(int paramInt)
  {
    if (paramInt >= 64)
    {
      b();
      return b.c(paramInt - 64);
    }
    return (a & 1L << paramInt) != 0L;
  }
  
  boolean d(int paramInt)
  {
    boolean bool2;
    if (paramInt >= 64)
    {
      b();
      bool2 = b.d(paramInt - 64);
      return bool2;
    }
    long l1 = 1L << paramInt;
    if ((a & l1) != 0L) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      a &= (l1 ^ 0xFFFFFFFFFFFFFFFF);
      l1 -= 1L;
      long l2 = a;
      a = (Long.rotateRight((l1 ^ 0xFFFFFFFFFFFFFFFF) & a, 1) | l2 & l1);
      bool2 = bool1;
      if (b == null) {
        break;
      }
      if (b.c(0)) {
        a(63);
      }
      b.d(0);
      return bool1;
    }
  }
  
  int e(int paramInt)
  {
    if (b == null)
    {
      if (paramInt >= 64) {
        return Long.bitCount(a);
      }
      return Long.bitCount(a & (1L << paramInt) - 1L);
    }
    if (paramInt < 64) {
      return Long.bitCount(a & (1L << paramInt) - 1L);
    }
    return b.e(paramInt - 64) + Long.bitCount(a);
  }
  
  public String toString()
  {
    if (b == null) {
      return Long.toBinaryString(a);
    }
    return b.toString() + "xx" + Long.toBinaryString(a);
  }
}

/* Location:
 * Qualified Name:     ard.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */