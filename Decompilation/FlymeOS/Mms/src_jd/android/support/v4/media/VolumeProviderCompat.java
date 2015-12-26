package android.support.v4.media;

import android.os.Build.VERSION;

public abstract class VolumeProviderCompat
{
  public static final int VOLUME_CONTROL_ABSOLUTE = 2;
  public static final int VOLUME_CONTROL_FIXED = 0;
  public static final int VOLUME_CONTROL_RELATIVE = 1;
  private Callback mCallback;
  private final int mControlType;
  private int mCurrentVolume;
  private final int mMaxVolume;
  private Object mVolumeProviderObj;
  
  public VolumeProviderCompat(int paramInt1, int paramInt2, int paramInt3)
  {
    mControlType = paramInt1;
    mMaxVolume = paramInt2;
    mCurrentVolume = paramInt3;
  }
  
  public final int getCurrentVolume()
  {
    return mCurrentVolume;
  }
  
  public final int getMaxVolume()
  {
    return mMaxVolume;
  }
  
  public final int getVolumeControl()
  {
    return mControlType;
  }
  
  public Object getVolumeProvider()
  {
    if ((mVolumeProviderObj != null) || (Build.VERSION.SDK_INT < 21)) {
      return mVolumeProviderObj;
    }
    mVolumeProviderObj = VolumeProviderCompatApi21.createVolumeProvider(mControlType, mMaxVolume, mCurrentVolume, new VolumeProviderCompat.1(this));
    return mVolumeProviderObj;
  }
  
  public void onAdjustVolume(int paramInt) {}
  
  public void onSetVolumeTo(int paramInt) {}
  
  public void setCallback(Callback paramCallback)
  {
    mCallback = paramCallback;
  }
  
  public final void setCurrentVolume(int paramInt)
  {
    if (mCallback != null) {
      mCallback.onVolumeChanged(this);
    }
  }
  
  public static abstract class Callback
  {
    public abstract void onVolumeChanged(VolumeProviderCompat paramVolumeProviderCompat);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.VolumeProviderCompat
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */