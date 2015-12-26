package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class ParcelableVolumeInfo$1
  implements Parcelable.Creator<ParcelableVolumeInfo>
{
  public ParcelableVolumeInfo createFromParcel(Parcel paramParcel)
  {
    return new ParcelableVolumeInfo(paramParcel);
  }
  
  public ParcelableVolumeInfo[] newArray(int paramInt)
  {
    return new ParcelableVolumeInfo[paramInt];
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.session.ParcelableVolumeInfo.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */