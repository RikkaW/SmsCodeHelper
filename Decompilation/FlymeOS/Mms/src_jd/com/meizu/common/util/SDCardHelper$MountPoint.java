package com.meizu.common.util;

import android.annotation.SuppressLint;
import android.os.StatFs;

public class SDCardHelper$MountPoint
{
  private String mDescription;
  private boolean mIsExternal;
  private String mMountedState;
  private String mPath;
  
  public SDCardHelper$MountPoint(SDCardHelper paramSDCardHelper) {}
  
  private void setDescription(String paramString)
  {
    mDescription = paramString;
  }
  
  private void setExternal(boolean paramBoolean)
  {
    mIsExternal = paramBoolean;
  }
  
  private void setMountedState(String paramString)
  {
    mMountedState = paramString;
  }
  
  private void setPath(String paramString)
  {
    mPath = paramString;
  }
  
  @SuppressLint({"NewApi"})
  public long availableSpace()
  {
    if (!isMounted()) {
      return 0L;
    }
    StatFs localStatFs = new StatFs(mPath);
    long l = localStatFs.getBlockSizeLong();
    return localStatFs.getAvailableBlocksLong() * l;
  }
  
  public String getDescription()
  {
    return mDescription;
  }
  
  public String getMountedState()
  {
    return mMountedState;
  }
  
  public String getPath()
  {
    return mPath;
  }
  
  @SuppressLint({"NewApi"})
  public long getTotalBlocks()
  {
    if (!isMounted()) {
      return 0L;
    }
    StatFs localStatFs = new StatFs(mPath);
    long l = localStatFs.getBlockSizeLong();
    return localStatFs.getBlockCountLong() * l;
  }
  
  public boolean isExternal()
  {
    return mIsExternal;
  }
  
  public boolean isMounted()
  {
    return mMountedState.equals("mounted");
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.util.SDCardHelper.MountPoint
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */