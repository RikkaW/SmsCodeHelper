package com.android.mms.service;

import android.net.Uri;

public abstract interface MmsRequest$RequestManager
{
  public abstract void addSimRequest(MmsRequest paramMmsRequest);
  
  public abstract boolean getAutoPersistingPref();
  
  public abstract byte[] readPduFromContentUri(Uri paramUri, int paramInt);
  
  public abstract boolean writePduToContentUri(Uri paramUri, byte[] paramArrayOfByte);
}

/* Location:
 * Qualified Name:     com.android.mms.service.MmsRequest.RequestManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */