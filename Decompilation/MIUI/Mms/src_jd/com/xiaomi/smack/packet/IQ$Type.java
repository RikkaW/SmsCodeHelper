package com.xiaomi.smack.packet;

public class IQ$Type
{
  public static final Type COMMAND = new Type("command");
  public static final Type ERROR;
  public static final Type GET = new Type("get");
  public static final Type RESULT;
  public static final Type SET = new Type("set");
  private String value;
  
  static
  {
    RESULT = new Type("result");
    ERROR = new Type("error");
  }
  
  private IQ$Type(String paramString)
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
      if (GET.toString().equals(paramString)) {
        return GET;
      }
      if (SET.toString().equals(paramString)) {
        return SET;
      }
      if (ERROR.toString().equals(paramString)) {
        return ERROR;
      }
      if (RESULT.toString().equals(paramString)) {
        return RESULT;
      }
    } while (!COMMAND.toString().equals(paramString));
    return COMMAND;
  }
  
  public String toString()
  {
    return value;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smack.packet.IQ.Type
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */