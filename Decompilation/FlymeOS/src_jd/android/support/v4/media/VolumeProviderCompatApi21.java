package android.support.v4.media;

import android.media.VolumeProvider;

class VolumeProviderCompatApi21
{
  public static Object createVolumeProvider(int paramInt1, int paramInt2, int paramInt3, Delegate paramDelegate)
  {
    return new VolumeProviderCompatApi21.1(paramInt1, paramInt2, paramInt3, paramDelegate);
  }
  
  public static void setCurrentVolume(Object paramObject, int paramInt)
  {
    ((VolumeProvider)paramObject).setCurrentVolume(paramInt);
  }
  
  public static abstract interface Delegate
  {
    public abstract void onAdjustVolume(int paramInt);
    
    public abstract void onSetVolumeTo(int paramInt);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.VolumeProviderCompatApi21
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */