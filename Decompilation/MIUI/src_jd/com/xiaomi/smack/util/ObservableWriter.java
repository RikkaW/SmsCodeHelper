package com.xiaomi.smack.util;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ObservableWriter
  extends Writer
{
  List listeners = new ArrayList();
  Writer wrappedWriter = null;
  
  public ObservableWriter(Writer paramWriter)
  {
    wrappedWriter = paramWriter;
  }
  
  private void notifyListeners(String paramString)
  {
    synchronized (listeners)
    {
      WriterListener[] arrayOfWriterListener = new WriterListener[listeners.size()];
      listeners.toArray(arrayOfWriterListener);
      int i = 0;
      if (i < arrayOfWriterListener.length)
      {
        arrayOfWriterListener[i].write(paramString);
        i += 1;
      }
    }
  }
  
  public void addWriterListener(WriterListener paramWriterListener)
  {
    if (paramWriterListener == null) {
      return;
    }
    synchronized (listeners)
    {
      if (!listeners.contains(paramWriterListener)) {
        listeners.add(paramWriterListener);
      }
      return;
    }
  }
  
  public void close()
    throws IOException
  {
    wrappedWriter.close();
  }
  
  public void flush()
    throws IOException
  {
    wrappedWriter.flush();
  }
  
  public void removeWriterListener(WriterListener paramWriterListener)
  {
    synchronized (listeners)
    {
      listeners.remove(paramWriterListener);
      return;
    }
  }
  
  public void write(int paramInt)
    throws IOException
  {
    wrappedWriter.write(paramInt);
  }
  
  public void write(String paramString)
    throws IOException
  {
    wrappedWriter.write(paramString);
    notifyListeners(paramString);
  }
  
  public void write(String paramString, int paramInt1, int paramInt2)
    throws IOException
  {
    wrappedWriter.write(paramString, paramInt1, paramInt2);
    notifyListeners(paramString.substring(paramInt1, paramInt1 + paramInt2));
  }
  
  public void write(char[] paramArrayOfChar)
    throws IOException
  {
    wrappedWriter.write(paramArrayOfChar);
    notifyListeners(new String(paramArrayOfChar));
  }
  
  public void write(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    wrappedWriter.write(paramArrayOfChar, paramInt1, paramInt2);
    notifyListeners(new String(paramArrayOfChar, paramInt1, paramInt2));
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smack.util.ObservableWriter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */