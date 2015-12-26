package com.android.mms.model;

import android.content.Context;
import android.net.Uri;
import com.google.android.mms.MmsException;

public class VCardModel
  extends FileAttachmentModel
{
  public VCardModel(Context paramContext, Uri paramUri)
    throws MmsException
  {
    super(paramContext, paramUri, "text/x-vCard");
  }
  
  public VCardModel(Context paramContext, String paramString1, String paramString2, Uri paramUri)
    throws MmsException
  {
    super(paramContext, paramString1, paramString2, paramUri);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.model.VCardModel
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */