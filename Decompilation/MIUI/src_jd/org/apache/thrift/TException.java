package org.apache.thrift;

public class TException
  extends Exception
{
  private static final long serialVersionUID = 1L;
  
  public TException() {}
  
  public TException(String paramString)
  {
    super(paramString);
  }
  
  public TException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
  
  public TException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }
}

/* Location:
 * Qualified Name:     org.apache.thrift.TException
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */