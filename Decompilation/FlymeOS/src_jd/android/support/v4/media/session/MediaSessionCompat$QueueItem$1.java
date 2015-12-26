package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class MediaSessionCompat$QueueItem$1
  implements Parcelable.Creator<MediaSessionCompat.QueueItem>
{
  public MediaSessionCompat.QueueItem createFromParcel(Parcel paramParcel)
  {
    return new MediaSessionCompat.QueueItem(paramParcel, null);
  }
  
  public MediaSessionCompat.QueueItem[] newArray(int paramInt)
  {
    return new MediaSessionCompat.QueueItem[paramInt];
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.session.MediaSessionCompat.QueueItem.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */