package com.amap.api.mapcore2d;

public class ae
{
  private long a = Long.MIN_VALUE;
  private long b = Long.MIN_VALUE;
  private double c = Double.MIN_VALUE;
  private double d = Double.MIN_VALUE;
  
  public ae()
  {
    a = 0L;
    b = 0L;
  }
  
  private ae(double paramDouble1, double paramDouble2, long paramLong1, long paramLong2)
  {
    c = paramDouble1;
    d = paramDouble2;
    a = paramLong1;
    b = paramLong2;
  }
  
  ae(double paramDouble1, double paramDouble2, boolean paramBoolean)
  {
    if (paramBoolean == true)
    {
      a = ((paramDouble1 * 1000000.0D));
      b = ((paramDouble2 * 1000000.0D));
      return;
    }
    c = paramDouble1;
    d = paramDouble2;
  }
  
  public ae(int paramInt1, int paramInt2)
  {
    a = paramInt1;
    b = paramInt2;
  }
  
  public int a()
  {
    return (int)b;
  }
  
  public void a(double paramDouble)
  {
    d = paramDouble;
  }
  
  public int b()
  {
    return (int)a;
  }
  
  public void b(double paramDouble)
  {
    c = paramDouble;
  }
  
  public long c()
  {
    return b;
  }
  
  public long d()
  {
    return a;
  }
  
  public double e()
  {
    if (Double.doubleToLongBits(d) == Double.doubleToLongBits(Double.MIN_VALUE)) {
      d = (z.a(b) * 2.003750834E7D / 180.0D);
    }
    return d;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (paramObject == null) {
        return false;
      }
      if (getClass() != paramObject.getClass()) {
        return false;
      }
      paramObject = (ae)paramObject;
      if (a != a) {
        return false;
      }
      if (b != b) {
        return false;
      }
      if (Double.doubleToLongBits(c) != Double.doubleToLongBits(c)) {
        return false;
      }
    } while (Double.doubleToLongBits(d) == Double.doubleToLongBits(d));
    return false;
  }
  
  public double f()
  {
    if (Double.doubleToLongBits(c) == Double.doubleToLongBits(Double.MIN_VALUE)) {
      c = (Math.log(Math.tan((z.a(a) + 90.0D) * 3.141592653589793D / 360.0D)) / 0.017453292519943295D * 2.003750834E7D / 180.0D);
    }
    return c;
  }
  
  public ae g()
  {
    return new ae(c, d, a, b);
  }
  
  public int hashCode()
  {
    int i = (int)(a ^ a >>> 32);
    int j = (int)(b ^ b >>> 32);
    long l = Double.doubleToLongBits(c);
    int k = (int)(l ^ l >>> 32);
    l = Double.doubleToLongBits(d);
    return (((i + 31) * 31 + j) * 31 + k) * 31 + (int)(l ^ l >>> 32);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.ae
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */