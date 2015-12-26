package com.xiaomi.mms.transaction;

import android.net.Uri;

class MxMmsTransactionService$MxTransaction
{
  final int mMsgBox;
  final Uri mUri;
  
  private MxMmsTransactionService$MxTransaction(Uri paramUri, int paramInt)
  {
    mUri = paramUri;
    mMsgBox = paramInt;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if ((paramObject == null) || (getClass() != paramObject.getClass())) {
        return false;
      }
      paramObject = (MxTransaction)paramObject;
      if (mMsgBox != mMsgBox) {
        return false;
      }
      if (mUri == null) {
        break;
      }
    } while (mUri.equals(mUri));
    for (;;)
    {
      return false;
      if (mUri == null) {
        break;
      }
    }
  }
  
  public int hashCode()
  {
    if (mUri != null) {}
    for (int i = mUri.hashCode();; i = 0) {
      return i * 31 + mMsgBox;
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.transaction.MxMmsTransactionService.MxTransaction
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */