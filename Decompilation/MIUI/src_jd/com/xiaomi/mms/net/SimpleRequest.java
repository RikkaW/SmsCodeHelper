package com.xiaomi.mms.net;

import java.io.Closeable;
import java.io.IOException;

public abstract interface SimpleRequest
  extends Closeable
{
  public abstract void close();
  
  public abstract void connect()
    throws IOException;
  
  public abstract byte[] getResponseBody()
    throws IOException;
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.net.SimpleRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */