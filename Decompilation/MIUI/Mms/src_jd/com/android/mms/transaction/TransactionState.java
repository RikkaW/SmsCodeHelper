package com.android.mms.transaction;

import android.net.Uri;

public class TransactionState
{
  private Uri mContentUri = null;
  private int mState = 0;
  
  public Uri getContentUri()
  {
    try
    {
      Uri localUri = mContentUri;
      return localUri;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public int getState()
  {
    try
    {
      int i = mState;
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  void setContentUri(Uri paramUri)
  {
    try
    {
      mContentUri = paramUri;
      return;
    }
    finally
    {
      paramUri = finally;
      throw paramUri;
    }
  }
  
  void setState(int paramInt)
  {
    if ((paramInt < 0) && (paramInt > 2)) {
      try
      {
        throw new IllegalArgumentException("Bad state: " + paramInt);
      }
      finally {}
    }
    mState = paramInt;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.TransactionState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */