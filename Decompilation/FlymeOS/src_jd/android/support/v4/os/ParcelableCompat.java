package android.support.v4.os;

import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public class ParcelableCompat
{
  public static <T> Parcelable.Creator<T> newCreator(ParcelableCompatCreatorCallbacks<T> paramParcelableCompatCreatorCallbacks)
  {
    if (Build.VERSION.SDK_INT >= 13) {
      return ParcelableCompatCreatorHoneycombMR2Stub.instantiate(paramParcelableCompatCreatorCallbacks);
    }
    return new CompatCreator(paramParcelableCompatCreatorCallbacks);
  }
  
  static class CompatCreator<T>
    implements Parcelable.Creator<T>
  {
    final ParcelableCompatCreatorCallbacks<T> mCallbacks;
    
    public CompatCreator(ParcelableCompatCreatorCallbacks<T> paramParcelableCompatCreatorCallbacks)
    {
      mCallbacks = paramParcelableCompatCreatorCallbacks;
    }
    
    public T createFromParcel(Parcel paramParcel)
    {
      return (T)mCallbacks.createFromParcel(paramParcel, null);
    }
    
    public T[] newArray(int paramInt)
    {
      return mCallbacks.newArray(paramInt);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.os.ParcelableCompat
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */