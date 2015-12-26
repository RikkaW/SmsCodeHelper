package com.xiaomi.mms.mx.fw.misc;

public class XmppHandlerThread
  extends SerializedAsyncTaskProcessor
{
  private static XmppHandlerThread sInstance;
  
  public static XmppHandlerThread getInstance()
  {
    try
    {
      if (sInstance == null) {
        sInstance = new XmppHandlerThread();
      }
      XmppHandlerThread localXmppHandlerThread = sInstance;
      return localXmppHandlerThread;
    }
    finally {}
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.fw.misc.XmppHandlerThread
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */