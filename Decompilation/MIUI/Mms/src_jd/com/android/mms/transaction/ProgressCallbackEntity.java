package com.android.mms.transaction;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.http.entity.ByteArrayEntity;

public class ProgressCallbackEntity
  extends ByteArrayEntity
{
  private final byte[] mContent;
  private final ProgressReceiver mReceiver;
  
  public ProgressCallbackEntity(ProgressReceiver paramProgressReceiver, byte[] paramArrayOfByte)
  {
    super(paramArrayOfByte);
    mContent = paramArrayOfByte;
    mReceiver = paramProgressReceiver;
  }
  
  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    if (paramOutputStream == null) {
      throw new IllegalArgumentException("Output stream may not be null");
    }
    int j = 0;
    for (;;)
    {
      int m;
      int i;
      try
      {
        m = mContent.length;
        i = j;
        if (mReceiver != null)
        {
          mReceiver.onProgress(-1L, m);
          i = j;
        }
        if (i < m)
        {
          if (mReceiver != null)
          {
            mReceiver.onProgress(i, m);
            break label170;
            paramOutputStream.write(mContent, i, j);
            paramOutputStream.flush();
            i += j;
          }
        }
        else
        {
          if (mReceiver != null) {
            mReceiver.onProgress(m, m);
          }
          if (1 == 0) {
            mReceiver.onProgress(-2L, mContent.length);
          }
          return;
        }
      }
      finally
      {
        if (0 == 0) {
          mReceiver.onProgress(-2L, mContent.length);
        }
      }
      label170:
      int k = m - i;
      j = k;
      if (k > 4096) {
        j = 4096;
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.ProgressCallbackEntity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */