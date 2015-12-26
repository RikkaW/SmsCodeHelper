package android.support.v7.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

class ActionMenuPresenter$SavedState
  implements Parcelable
{
  public static final Parcelable.Creator<SavedState> CREATOR = new ActionMenuPresenter.SavedState.1();
  public int openSubMenuId;
  
  ActionMenuPresenter$SavedState() {}
  
  ActionMenuPresenter$SavedState(Parcel paramParcel)
  {
    openSubMenuId = paramParcel.readInt();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(openSubMenuId);
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.ActionMenuPresenter.SavedState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */