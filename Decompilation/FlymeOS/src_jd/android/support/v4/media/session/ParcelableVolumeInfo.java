package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class ParcelableVolumeInfo
  implements Parcelable
{
  public static final Parcelable.Creator<ParcelableVolumeInfo> CREATOR = new ParcelableVolumeInfo.1();
  public int audioStream;
  public int controlType;
  public int currentVolume;
  public int maxVolume;
  public int volumeType;
  
  public ParcelableVolumeInfo(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    volumeType = paramInt1;
    audioStream = paramInt2;
    controlType = paramInt3;
    maxVolume = paramInt4;
    currentVolume = paramInt5;
  }
  
  public ParcelableVolumeInfo(Parcel paramParcel)
  {
    volumeType = paramParcel.readInt();
    controlType = paramParcel.readInt();
    maxVolume = paramParcel.readInt();
    currentVolume = paramParcel.readInt();
    audioStream = paramParcel.readInt();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(volumeType);
    paramParcel.writeInt(controlType);
    paramParcel.writeInt(maxVolume);
    paramParcel.writeInt(currentVolume);
    paramParcel.writeInt(audioStream);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.session.ParcelableVolumeInfo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */