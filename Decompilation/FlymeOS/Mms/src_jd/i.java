public class i
  extends Exception
{
  private static final long serialVersionUID = -382075370364295450L;
  private String a;
  private int b = -1;
  
  public i() {}
  
  public i(String paramString)
  {
    super(paramString);
  }
  
  public i(String paramString1, String paramString2, int paramInt)
  {
    super(paramString1);
    b = paramInt;
    a = paramString2;
  }
  
  public i(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer1 = new StringBuffer(getMessage());
    localStringBuffer1.append("\r\n处理对象：").append(a);
    StringBuffer localStringBuffer2 = localStringBuffer1.append("\r\n处理位置：");
    if (b == -1) {}
    for (Object localObject = " unknow ";; localObject = Integer.valueOf(b))
    {
      localStringBuffer2.append(localObject);
      return localStringBuffer1.toString();
    }
  }
}

/* Location:
 * Qualified Name:     i
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */