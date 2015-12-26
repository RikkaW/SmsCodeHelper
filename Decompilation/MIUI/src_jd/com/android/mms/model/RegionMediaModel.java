package com.android.mms.model;

import android.content.Context;
import android.net.Uri;
import com.google.android.mms.MmsException;

public abstract class RegionMediaModel
  extends MediaModel
{
  protected RegionModel mRegion;
  protected boolean mVisible = true;
  
  public RegionMediaModel(Context paramContext, String paramString, Uri paramUri, RegionModel paramRegionModel)
    throws MmsException
  {
    this(paramContext, paramString, null, null, paramUri, paramRegionModel);
  }
  
  public RegionMediaModel(Context paramContext, String paramString1, String paramString2, String paramString3, Uri paramUri, RegionModel paramRegionModel)
    throws MmsException
  {
    super(paramContext, paramString1, paramString2, paramString3, paramUri);
    mRegion = paramRegionModel;
  }
  
  public RegionMediaModel(Context paramContext, String paramString1, String paramString2, String paramString3, byte[] paramArrayOfByte, RegionModel paramRegionModel)
  {
    super(paramContext, paramString1, paramString2, paramString3, paramArrayOfByte);
    mRegion = paramRegionModel;
  }
  
  public RegionModel getRegion()
  {
    return mRegion;
  }
  
  public boolean isVisible()
  {
    return mVisible;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.model.RegionMediaModel
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */