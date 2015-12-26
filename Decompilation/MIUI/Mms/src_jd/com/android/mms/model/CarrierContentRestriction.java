package com.android.mms.model;

import android.content.ContentResolver;
import com.android.mms.ContentRestrictionException;
import com.android.mms.ExceedMessageSizeException;
import com.android.mms.MmsConfig;
import com.android.mms.UnsupportContentTypeException;
import com.google.android.mms.ContentType;
import java.util.ArrayList;

public class CarrierContentRestriction
  implements ContentRestriction
{
  private static final ArrayList<String> sSupportedAudioTypes = ContentType.getAudioTypes();
  private static final ArrayList<String> sSupportedImageTypes = ;
  private static final ArrayList<String> sSupportedVideoTypes = ContentType.getVideoTypes();
  
  public void checkAudioContentType(String paramString)
    throws ContentRestrictionException
  {
    if (paramString == null) {
      throw new ContentRestrictionException("Null content type to be check");
    }
    if (!sSupportedAudioTypes.contains(paramString)) {
      throw new UnsupportContentTypeException("Unsupported audio content type : " + paramString);
    }
  }
  
  public void checkImageContentType(String paramString)
    throws ContentRestrictionException
  {
    if (paramString == null) {
      throw new ContentRestrictionException("Null content type to be check");
    }
    if (!sSupportedImageTypes.contains(paramString)) {
      throw new UnsupportContentTypeException("Unsupported image content type : " + paramString);
    }
  }
  
  public void checkMessageSize(int paramInt1, int paramInt2, ContentResolver paramContentResolver)
    throws ContentRestrictionException
  {
    if ((paramInt1 < 0) || (paramInt2 < 0)) {
      throw new ContentRestrictionException("Negative message size or increase size");
    }
    paramInt1 += paramInt2;
    if ((paramInt1 < 0) || (paramInt1 > MmsConfig.getMaxMessageSize())) {
      throw new ExceedMessageSizeException("Exceed message size limitation");
    }
  }
  
  public void checkVideoContentType(String paramString)
    throws ContentRestrictionException
  {
    if (paramString == null) {
      throw new ContentRestrictionException("Null content type to be check");
    }
    if (!sSupportedVideoTypes.contains(paramString)) {
      throw new UnsupportContentTypeException("Unsupported video content type : " + paramString);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.model.CarrierContentRestriction
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */