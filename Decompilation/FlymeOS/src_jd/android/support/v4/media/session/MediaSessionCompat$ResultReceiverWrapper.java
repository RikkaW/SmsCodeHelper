package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.ResultReceiver;

final class MediaSessionCompat$ResultReceiverWrapper
  implements Parcelable
{
  public static final Parcelable.Creator<ResultReceiverWrapper> CREATOR = new MediaSessionCompat.ResultReceiverWrapper.1();
  private ResultReceiver mResultReceiver;
  
  MediaSessionCompat$ResultReceiverWrapper(Parcel paramParcel)
  {
    mResultReceiver = ((ResultReceiver)ResultReceiver.CREATOR.createFromParcel(paramParcel));
  }
  
  public MediaSessionCompat$ResultReceiverWrapper(ResultReceiver paramResultReceiver)
  {
    mResultReceiver = paramResultReceiver;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    mResultReceiver.writeToParcel(paramParcel, paramInt);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.session.MediaSessionCompat.ResultReceiverWrapper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */