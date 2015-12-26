package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

final class FragmentManagerState
  implements Parcelable
{
  public static final Parcelable.Creator<FragmentManagerState> CREATOR = new FragmentManagerState.1();
  FragmentState[] mActive;
  int[] mAdded;
  BackStackState[] mBackStack;
  
  public FragmentManagerState() {}
  
  public FragmentManagerState(Parcel paramParcel)
  {
    mActive = ((FragmentState[])paramParcel.createTypedArray(FragmentState.CREATOR));
    mAdded = paramParcel.createIntArray();
    mBackStack = ((BackStackState[])paramParcel.createTypedArray(BackStackState.CREATOR));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeTypedArray(mActive, paramInt);
    paramParcel.writeIntArray(mAdded);
    paramParcel.writeTypedArray(mBackStack, paramInt);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.FragmentManagerState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */