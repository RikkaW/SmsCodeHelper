import java.lang.reflect.Method;

public class ahs
{
  public static Object a(Object paramObject, String paramString, Object... paramVarArgs)
  {
    Class localClass = paramObject.getClass();
    Class[] arrayOfClass = new Class[paramVarArgs.length];
    int i = 0;
    int j = paramVarArgs.length;
    while (i < j)
    {
      arrayOfClass[i] = paramVarArgs[i].getClass();
      i += 1;
    }
    return localClass.getMethod(paramString, arrayOfClass).invoke(paramObject, paramVarArgs);
  }
  
  public static int b(Object paramObject, String paramString, Object... paramVarArgs)
  {
    Class localClass = paramObject.getClass();
    Class[] arrayOfClass = new Class[paramVarArgs.length];
    int i = 0;
    int j = paramVarArgs.length;
    while (i < j)
    {
      arrayOfClass[i] = paramVarArgs[i].getClass();
      i += 1;
    }
    return ((Integer)localClass.getMethod(paramString, arrayOfClass).invoke(paramObject, paramVarArgs)).intValue();
  }
}

/* Location:
 * Qualified Name:     ahs
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */