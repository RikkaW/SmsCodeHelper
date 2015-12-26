package com.meizu.common.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.view.View.BaseSavedState;

class ProgressBar$SavedState
  extends View.BaseSavedState
{
  public static final Parcelable.Creator<SavedState> CREATOR = new ProgressBar.SavedState.1();
  int progress;
  int secondaryProgress;
  
  private ProgressBar$SavedState(Parcel paramParcel)
  {
    super(paramParcel);
    progress = paramParcel.readInt();
    secondaryProgress = paramParcel.readInt();
  }
  
  ProgressBar$SavedState(Parcelable paramParcelable)
  {
    super(paramParcelable);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeInt(progress);
    paramParcel.writeInt(secondaryProgress);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.ProgressBar.SavedState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */