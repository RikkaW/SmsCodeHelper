package org.apache.thrift.transport;

import org.apache.thrift.TException;

public class TTransportException
  extends TException
{
  private static final long serialVersionUID = 1L;
  protected int type_ = 0;
  
  public TTransportException() {}
  
  public TTransportException(int paramInt)
  {
    type_ = paramInt;
  }
  
  public TTransportException(int paramInt, String paramString)
  {
    super(paramString);
    type_ = paramInt;
  }
  
  public TTransportException(int paramInt, Throwable paramThrowable)
  {
    super(paramThrowable);
    type_ = paramInt;
  }
  
  public TTransportException(String paramString)
  {
    super(paramString);
  }
  
  public TTransportException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
  
  public TTransportException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }
}

/* Location:
 * Qualified Name:     org.apache.thrift.transport.TTransportException
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */