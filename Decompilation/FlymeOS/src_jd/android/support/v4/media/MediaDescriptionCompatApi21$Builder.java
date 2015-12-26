package android.support.v4.media;

import android.graphics.Bitmap;
import android.media.MediaDescription.Builder;
import android.net.Uri;
import android.os.Bundle;

public class MediaDescriptionCompatApi21$Builder
{
  public static Object build(Object paramObject)
  {
    return ((MediaDescription.Builder)paramObject).build();
  }
  
  public static Object newInstance()
  {
    return new MediaDescription.Builder();
  }
  
  public static void setDescription(Object paramObject, CharSequence paramCharSequence)
  {
    ((MediaDescription.Builder)paramObject).setDescription(paramCharSequence);
  }
  
  public static void setExtras(Object paramObject, Bundle paramBundle)
  {
    ((MediaDescription.Builder)paramObject).setExtras(paramBundle);
  }
  
  public static void setIconBitmap(Object paramObject, Bitmap paramBitmap)
  {
    ((MediaDescription.Builder)paramObject).setIconBitmap(paramBitmap);
  }
  
  public static void setIconUri(Object paramObject, Uri paramUri)
  {
    ((MediaDescription.Builder)paramObject).setIconUri(paramUri);
  }
  
  public static void setMediaId(Object paramObject, String paramString)
  {
    ((MediaDescription.Builder)paramObject).setMediaId(paramString);
  }
  
  public static void setSubtitle(Object paramObject, CharSequence paramCharSequence)
  {
    ((MediaDescription.Builder)paramObject).setSubtitle(paramCharSequence);
  }
  
  public static void setTitle(Object paramObject, CharSequence paramCharSequence)
  {
    ((MediaDescription.Builder)paramObject).setTitle(paramCharSequence);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.MediaDescriptionCompatApi21.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */