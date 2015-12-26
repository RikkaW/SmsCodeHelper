package com.xiaomi.smack;

public class XMBinder$BindResult$Type
{
  public static final Type ERROR = new Type("error");
  public static final Type RESULT = new Type("result");
  private String value;
  
  private XMBinder$BindResult$Type(String paramString)
  {
    value = paramString;
  }
  
  public static Type fromString(String paramString)
  {
    if (paramString == null) {}
    do
    {
      return null;
      paramString = paramString.toLowerCase();
      if (ERROR.toString().equals(paramString)) {
        return ERROR;
      }
    } while (!RESULT.toString().equals(paramString));
    return RESULT;
  }
  
  public String toString()
  {
    return value;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smack.XMBinder.BindResult.Type
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */