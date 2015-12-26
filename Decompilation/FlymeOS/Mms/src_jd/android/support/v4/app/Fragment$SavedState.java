package android.support.v4.app;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Fragment$SavedState
  implements Parcelable
{
  public static final Parcelable.Creator<SavedState> CREATOR = new Fragment.SavedState.1();
  final Bundle mState;
  
  Fragment$SavedState(Bundle paramBundle)
  {
    mState = paramBundle;
  }
  
  Fragment$SavedState(Parcel paramParcel, ClassLoader paramClassLoader)
  {
    mState = paramParcel.readBundle();
    if ((paramClassLoader != null) && (mState != null)) {
      mState.setClassLoader(paramClassLoader);
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeBundle(mState);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.Fragment.SavedState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */