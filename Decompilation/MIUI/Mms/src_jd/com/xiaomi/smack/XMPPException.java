package com.xiaomi.smack;

import com.xiaomi.smack.packet.StreamError;
import com.xiaomi.smack.packet.XMPPError;
import java.io.PrintStream;
import java.io.PrintWriter;

public class XMPPException
  extends Exception
{
  private XMPPError error = null;
  private StreamError streamError = null;
  private Throwable wrappedThrowable = null;
  
  public XMPPException() {}
  
  public XMPPException(StreamError paramStreamError)
  {
    streamError = paramStreamError;
  }
  
  public XMPPException(String paramString)
  {
    super(paramString);
  }
  
  public XMPPException(String paramString, Throwable paramThrowable)
  {
    super(paramString);
    wrappedThrowable = paramThrowable;
  }
  
  public XMPPException(Throwable paramThrowable)
  {
    wrappedThrowable = paramThrowable;
  }
  
  public String getMessage()
  {
    String str2 = super.getMessage();
    String str1;
    if ((str2 == null) && (error != null)) {
      str1 = error.toString();
    }
    do
    {
      do
      {
        return str1;
        str1 = str2;
      } while (str2 != null);
      str1 = str2;
    } while (streamError == null);
    return streamError.toString();
  }
  
  public Throwable getWrappedThrowable()
  {
    return wrappedThrowable;
  }
  
  public void printStackTrace()
  {
    printStackTrace(System.err);
  }
  
  public void printStackTrace(PrintStream paramPrintStream)
  {
    super.printStackTrace(paramPrintStream);
    if (wrappedThrowable != null)
    {
      paramPrintStream.println("Nested Exception: ");
      wrappedThrowable.printStackTrace(paramPrintStream);
    }
  }
  
  public void printStackTrace(PrintWriter paramPrintWriter)
  {
    super.printStackTrace(paramPrintWriter);
    if (wrappedThrowable != null)
    {
      paramPrintWriter.println("Nested Exception: ");
      wrappedThrowable.printStackTrace(paramPrintWriter);
    }
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    String str = super.getMessage();
    if (str != null) {
      localStringBuilder.append(str).append(": ");
    }
    if (error != null) {
      localStringBuilder.append(error);
    }
    if (streamError != null) {
      localStringBuilder.append(streamError);
    }
    if (wrappedThrowable != null) {
      localStringBuilder.append("\n  -- caused by: ").append(wrappedThrowable);
    }
    return localStringBuilder.toString();
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smack.XMPPException
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */