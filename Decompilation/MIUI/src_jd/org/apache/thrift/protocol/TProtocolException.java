package org.apache.thrift.protocol;

import org.apache.thrift.TException;

public class TProtocolException
  extends TException
{
  private static final long serialVersionUID = 1L;
  protected int type_ = 0;
  
  public TProtocolException() {}
  
  public TProtocolException(int paramInt, String paramString)
  {
    super(paramString);
    type_ = paramInt;
  }
  
  public TProtocolException(String paramString)
  {
    super(paramString);
  }
  
  public TProtocolException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
  
  public TProtocolException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }
}

/* Location:
 * Qualified Name:     org.apache.thrift.protocol.TProtocolException
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */