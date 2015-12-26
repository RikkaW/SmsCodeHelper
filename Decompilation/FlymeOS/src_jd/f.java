import java.text.ParseException;

public class f
{
  public static Object a(String paramString)
  {
    try
    {
      paramString = a(paramString, new e());
      return paramString;
    }
    catch (Exception paramString) {}
    return Boolean.valueOf(false);
  }
  
  public static Object a(String paramString, e parame)
  {
    return a(paramString, parame, null);
  }
  
  public static Object a(String paramString, e parame, d paramd)
  {
    if (paramString == null) {
      return null;
    }
    e locale = parame;
    if (parame == null) {
      locale = new e();
    }
    if (paramd != null) {
      locale.a(paramd);
    }
    parame = new g(locale);
    try
    {
      parame = parame.b(parame.a(parame.a(paramString))).o();
      return parame;
    }
    catch (i parame)
    {
      parame.printStackTrace();
      throw new RuntimeException("表达式：\"" + paramString + "\" 执行异常");
    }
    catch (ParseException parame)
    {
      parame.printStackTrace();
      throw new RuntimeException("表达式：\"" + paramString + "\" 执行异常");
    }
  }
}

/* Location:
 * Qualified Name:     f
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */