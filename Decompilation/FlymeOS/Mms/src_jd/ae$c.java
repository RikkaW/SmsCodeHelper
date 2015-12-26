import java.lang.reflect.Constructor;

class ae$c
{
  Class a;
  Object b;
  
  public ae$c(ae paramae, String paramString)
  {
    try
    {
      a = a(paramString);
      return;
    }
    catch (Exception paramae)
    {
      paramae.printStackTrace();
    }
  }
  
  public ae$c(ae paramae, String paramString1, String paramString2)
  {
    try
    {
      a = a(paramString1);
      b = a.getConstructor(new Class[] { String.class }).newInstance(new Object[] { paramString2 });
      return;
    }
    catch (Exception paramae)
    {
      paramae.printStackTrace();
    }
  }
  
  private Class a(String paramString)
  {
    if ("boolean".equals(paramString)) {
      return Boolean.TYPE;
    }
    if ("byte".equals(paramString)) {
      return Byte.TYPE;
    }
    if ("char".equals(paramString)) {
      return Character.TYPE;
    }
    if ("double".equals(paramString)) {
      return Double.TYPE;
    }
    if ("float".equals(paramString)) {
      return Float.TYPE;
    }
    if ("int".equals(paramString)) {
      return Integer.TYPE;
    }
    if ("long".equals(paramString)) {
      return Long.TYPE;
    }
    if ("short".equals(paramString)) {
      return Short.TYPE;
    }
    return Class.forName(paramString);
  }
}

/* Location:
 * Qualified Name:     ae.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */