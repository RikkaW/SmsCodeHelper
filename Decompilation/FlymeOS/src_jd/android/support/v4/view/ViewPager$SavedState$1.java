package android.support.v4.view;

import android.os.Parcel;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;

final class ViewPager$SavedState$1
  implements ParcelableCompatCreatorCallbacks<ViewPager.SavedState>
{
  public ViewPager.SavedState createFromParcel(Parcel paramParcel, ClassLoader paramClassLoader)
  {
    return new ViewPager.SavedState(paramParcel, paramClassLoader);
  }
  
  public ViewPager.SavedState[] newArray(int paramInt)
  {
    return new ViewPager.SavedState[paramInt];
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.ViewPager.SavedState.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */