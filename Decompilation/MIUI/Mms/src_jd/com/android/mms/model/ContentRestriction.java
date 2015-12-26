package com.android.mms.model;

import android.content.ContentResolver;
import com.android.mms.ContentRestrictionException;

public abstract interface ContentRestriction
{
  public abstract void checkAudioContentType(String paramString)
    throws ContentRestrictionException;
  
  public abstract void checkImageContentType(String paramString)
    throws ContentRestrictionException;
  
  public abstract void checkMessageSize(int paramInt1, int paramInt2, ContentResolver paramContentResolver)
    throws ContentRestrictionException;
  
  public abstract void checkVideoContentType(String paramString)
    throws ContentRestrictionException;
}

/* Location:
 * Qualified Name:     com.android.mms.model.ContentRestriction
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */