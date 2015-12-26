class arc$b
{
  int a;
  int b;
  int c;
  
  arc$b(int paramInt1, int paramInt2, int paramInt3)
  {
    a = paramInt1;
    b = paramInt2;
    c = paramInt3;
  }
  
  String a()
  {
    switch (a)
    {
    default: 
      return "??";
    case 0: 
      return "add";
    case 1: 
      return "rm";
    case 2: 
      return "up";
    }
    return "mv";
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      do
      {
        return true;
        if ((paramObject == null) || (getClass() != paramObject.getClass())) {
          return false;
        }
        paramObject = (b)paramObject;
        if (a != a) {
          return false;
        }
      } while ((a == 3) && (Math.abs(c - b) == 1) && (c == b) && (b == c));
      if (c != c) {
        return false;
      }
    } while (b == b);
    return false;
  }
  
  public int hashCode()
  {
    return (a * 31 + b) * 31 + c;
  }
  
  public String toString()
  {
    return "[" + a() + ",s:" + b + "c:" + c + "]";
  }
}

/* Location:
 * Qualified Name:     arc.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */