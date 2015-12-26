package com.xiaomi.channel.commonutils.network;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class Network$DoneHandlerInputStream
  extends FilterInputStream
{
  private boolean done;
  
  public Network$DoneHandlerInputStream(InputStream paramInputStream)
  {
    super(paramInputStream);
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (!done)
    {
      paramInt1 = super.read(paramArrayOfByte, paramInt1, paramInt2);
      if (paramInt1 != -1) {
        return paramInt1;
      }
    }
    done = true;
    return -1;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.channel.commonutils.network.Network.DoneHandlerInputStream
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */