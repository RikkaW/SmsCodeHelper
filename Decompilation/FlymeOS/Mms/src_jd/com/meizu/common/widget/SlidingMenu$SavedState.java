package com.meizu.common.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.view.View.BaseSavedState;

public class SlidingMenu$SavedState
  extends View.BaseSavedState
{
  public static final Parcelable.Creator<SavedState> CREATOR = new SlidingMenu.SavedState.1();
  private final int mItem;
  
  private SlidingMenu$SavedState(Parcel paramParcel)
  {
    super(paramParcel);
    mItem = paramParcel.readInt();
  }
  
  public SlidingMenu$SavedState(Parcelable paramParcelable, int paramInt)
  {
    super(paramParcelable);
    mItem = paramInt;
  }
  
  public int getItem()
  {
    return mItem;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeInt(mItem);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.SlidingMenu.SavedState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */