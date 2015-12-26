package com.android.mms.service.exception;

public class MmsHttpException
  extends Exception
{
  private final int mStatusCode;
  
  public MmsHttpException(int paramInt, String paramString)
  {
    super(paramString);
    mStatusCode = paramInt;
  }
  
  public MmsHttpException(int paramInt, String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
    mStatusCode = paramInt;
  }
  
  public MmsHttpException(int paramInt, Throwable paramThrowable)
  {
    super(paramThrowable);
    mStatusCode = paramInt;
  }
  
  public int getStatusCode()
  {
    return mStatusCode;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.service.exception.MmsHttpException
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */