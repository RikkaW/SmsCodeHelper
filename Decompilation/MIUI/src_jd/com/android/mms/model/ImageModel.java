package com.android.mms.model;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.ContentRestrictionException;
import com.android.mms.ExceedMessageSizeException;
import com.android.mms.MmsConfig;
import com.android.mms.ui.UriImage;
import com.google.android.mms.MmsException;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.google.android.mms.pdu.PduPart;
import java.lang.ref.SoftReference;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import miui.graphics.drawable.GifAnimationDrawable;
import miui.os.Build;
import miui.util.IOUtils;
import org.w3c.dom.events.Event;

public class ImageModel
  extends RegionMediaModel
{
  private static final Set<String> SUPPORTED_MMS_IMAGE_CONTENT_TYPES = new HashSet(Arrays.asList(new String[] { "image/jpeg" }));
  private SoftReference<Drawable> mDrawableCache = new SoftReference(null);
  private int mHeight;
  private int mWidth;
  
  public ImageModel(Context paramContext, Uri paramUri, RegionModel paramRegionModel)
    throws MmsException
  {
    super(paramContext, "img", paramUri, paramRegionModel);
    initModelFromUri(paramUri);
    checkContentRestriction();
  }
  
  public ImageModel(Context paramContext, String paramString1, String paramString2, Uri paramUri, RegionModel paramRegionModel)
    throws MmsException
  {
    super(paramContext, "img", paramString1, paramString2, paramUri, paramRegionModel);
    decodeImageBounds(paramUri);
  }
  
  private Drawable createThumbnailDrawable(int paramInt, Uri paramUri)
  {
    int j = mWidth;
    int k = mHeight;
    int i = 1;
    while ((j / i > paramInt) || (k / i > paramInt)) {
      i *= 2;
    }
    if (Log.isLoggable("Mms:app", 2)) {
      Log.v("Mms/image", "createThumbnailBitmap: scale=" + i + ", w=" + j / i + ", h=" + k / i);
    }
    try
    {
      GifAnimationDrawable localGifAnimationDrawable = new GifAnimationDrawable();
      boolean bool = localGifAnimationDrawable.load(mContext, paramUri);
      if (bool) {
        return localGifAnimationDrawable;
      }
      paramUri = new UriImage(mContext, paramUri).getBitmap(i);
      if (paramUri == null) {
        return null;
      }
      paramUri = new BitmapDrawable(mContext.getResources(), paramUri);
      return paramUri;
    }
    catch (OutOfMemoryError paramUri)
    {
      throw paramUri;
    }
    finally
    {
      IOUtils.closeQuietly(null);
    }
  }
  
  private void decodeImageBounds(Uri paramUri)
  {
    paramUri = new UriImage(mContext, paramUri);
    mWidth = paramUri.getWidth();
    mHeight = paramUri.getHeight();
  }
  
  private void initModelFromUri(Uri paramUri)
    throws MmsException
  {
    paramUri = new UriImage(mContext, paramUri);
    mContentType = paramUri.getContentType();
    if (TextUtils.isEmpty(mContentType)) {
      throw new MmsException("Type of media is unknown.");
    }
    setInternalSrc(paramUri.getSrc());
    mWidth = paramUri.getWidth();
    mHeight = paramUri.getHeight();
  }
  
  private Drawable internalGetDrawable(Uri paramUri)
  {
    Drawable localDrawable = (Drawable)mDrawableCache.get();
    Object localObject = localDrawable;
    if (localDrawable == null) {
      localObject = localDrawable;
    }
    try
    {
      paramUri = createThumbnailDrawable(480, paramUri);
      localObject = paramUri;
      if (paramUri != null)
      {
        localObject = paramUri;
        mDrawableCache = new SoftReference(paramUri);
        localObject = paramUri;
      }
      return (Drawable)localObject;
    }
    catch (OutOfMemoryError paramUri) {}
    return (Drawable)localObject;
  }
  
  protected void checkContentRestriction()
    throws ContentRestrictionException
  {
    ContentRestrictionFactory.getContentRestriction().checkImageContentType(mContentType);
  }
  
  public Drawable getDrawable()
  {
    return internalGetDrawable(getUri());
  }
  
  public boolean getMediaResizable()
  {
    return (!Build.IS_CM_CUSTOMIZATION) && (!Build.IS_CU_CUSTOMIZATION);
  }
  
  public void handleEvent(Event paramEvent)
  {
    if (paramEvent.getType().equals("SmilMediaStart")) {
      mVisible = true;
    }
    for (;;)
    {
      notifyModelChanged(false);
      return;
      if (mFill != 1) {
        mVisible = false;
      }
    }
  }
  
  protected void resizeMedia(int paramInt, long paramLong)
    throws MmsException
  {
    Object localObject = new UriImage(mContext, getUri());
    int i = MmsConfig.getMaxImageWidth();
    int k = MmsConfig.getMaxImageHeight();
    int n = getMediaSize();
    int m = k;
    int j = i;
    if (((UriImage)localObject).getHeight() > ((UriImage)localObject).getWidth())
    {
      j = k;
      m = i;
    }
    if (Log.isLoggable("Mms:app", 2)) {
      Log.v("Mms/image", "resizeMedia size: " + n + " image.getWidth(): " + ((UriImage)localObject).getWidth() + " widthLimit: " + j + " image.getHeight(): " + ((UriImage)localObject).getHeight() + " heightLimit: " + m + " image.getContentType(): " + ((UriImage)localObject).getContentType());
    }
    if ((n != 0) && (n <= paramInt) && (((UriImage)localObject).getWidth() <= j) && (((UriImage)localObject).getHeight() <= m) && (SUPPORTED_MMS_IMAGE_CONTENT_TYPES.contains(((UriImage)localObject).getContentType())))
    {
      if (Log.isLoggable("Mms:app", 2)) {
        Log.v("Mms/image", "resizeMedia - already sized");
      }
      return;
    }
    PduPart localPduPart = ((UriImage)localObject).getResizedImageAsPart(j, m, paramInt);
    if (localPduPart == null) {
      throw new ExceedMessageSizeException("Not enough memory to turn image into part: " + getUri());
    }
    mContentType = new String(localPduPart.getContentType());
    String str = getSrc();
    localObject = str.getBytes();
    localPduPart.setContentLocation((byte[])localObject);
    paramInt = str.lastIndexOf(".");
    if (paramInt != -1) {
      localObject = str.substring(0, paramInt).getBytes();
    }
    for (;;)
    {
      localPduPart.setContentId((byte[])localObject);
      localObject = MiuiPduPersister.getPduPersister(mContext);
      mSize = localPduPart.getData().length;
      if (Log.isLoggable("Mms:app", 2)) {
        Log.v("Mms/image", "resizeMedia mSize: " + mSize);
      }
      setUri(((MiuiPduPersister)localObject).persistPart(localPduPart, paramLong));
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.model.ImageModel
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */