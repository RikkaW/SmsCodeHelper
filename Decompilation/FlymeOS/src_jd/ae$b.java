import java.lang.reflect.Method;

class ae$b
{
  Method a;
  Object b;
  
  public ae$b(ae paramae, Method paramMethod, Object paramObject)
  {
    a = paramMethod;
    b = paramObject;
  }
  
  public Object a(Object[] paramArrayOfObject)
  {
    return a.invoke(b, paramArrayOfObject);
  }
}

/* Location:
 * Qualified Name:     ae.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */