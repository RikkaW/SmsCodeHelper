package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class FragmentManagerState$1
  implements Parcelable.Creator<FragmentManagerState>
{
  public FragmentManagerState createFromParcel(Parcel paramParcel)
  {
    return new FragmentManagerState(paramParcel);
  }
  
  public FragmentManagerState[] newArray(int paramInt)
  {
    return new FragmentManagerState[paramInt];
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.FragmentManagerState.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */