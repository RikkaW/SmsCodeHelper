package com.xiaomi.smack.util;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class ObservableReader
  extends Reader
{
  List listeners = new ArrayList();
  Reader wrappedReader = null;
  
  public ObservableReader(Reader paramReader)
  {
    wrappedReader = paramReader;
  }
  
  public void addReaderListener(ReaderListener paramReaderListener)
  {
    if (paramReaderListener == null) {
      return;
    }
    synchronized (listeners)
    {
      if (!listeners.contains(paramReaderListener)) {
        listeners.add(paramReaderListener);
      }
      return;
    }
  }
  
  public void close()
    throws IOException
  {
    wrappedReader.close();
  }
  
  public void mark(int paramInt)
    throws IOException
  {
    wrappedReader.mark(paramInt);
  }
  
  public boolean markSupported()
  {
    return wrappedReader.markSupported();
  }
  
  public int read()
    throws IOException
  {
    return wrappedReader.read();
  }
  
  public int read(char[] paramArrayOfChar)
    throws IOException
  {
    return wrappedReader.read(paramArrayOfChar);
  }
  
  public int read(char[] arg1, int paramInt1, int paramInt2)
    throws IOException
  {
    paramInt2 = wrappedReader.read(???, paramInt1, paramInt2);
    if (paramInt2 > 0)
    {
      String str = new String(???, paramInt1, paramInt2);
      synchronized (listeners)
      {
        ReaderListener[] arrayOfReaderListener = new ReaderListener[listeners.size()];
        listeners.toArray(arrayOfReaderListener);
        paramInt1 = 0;
        if (paramInt1 < arrayOfReaderListener.length)
        {
          arrayOfReaderListener[paramInt1].read(str);
          paramInt1 += 1;
        }
      }
    }
    return paramInt2;
  }
  
  public boolean ready()
    throws IOException
  {
    return wrappedReader.ready();
  }
  
  public void removeReaderListener(ReaderListener paramReaderListener)
  {
    synchronized (listeners)
    {
      listeners.remove(paramReaderListener);
      return;
    }
  }
  
  public void reset()
    throws IOException
  {
    wrappedReader.reset();
  }
  
  public long skip(long paramLong)
    throws IOException
  {
    return wrappedReader.skip(paramLong);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smack.util.ObservableReader
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */