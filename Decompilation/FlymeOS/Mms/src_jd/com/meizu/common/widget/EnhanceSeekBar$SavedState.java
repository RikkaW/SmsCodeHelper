package com.meizu.common.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.view.View.BaseSavedState;

class EnhanceSeekBar$SavedState
  extends View.BaseSavedState
{
  public static final Parcelable.Creator<SavedState> CREATOR = new EnhanceSeekBar.SavedState.1();
  int progress;
  
  private EnhanceSeekBar$SavedState(Parcel paramParcel)
  {
    super(paramParcel);
    progress = paramParcel.readInt();
  }
  
  EnhanceSeekBar$SavedState(Parcelable paramParcelable)
  {
    super(paramParcelable);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeInt(progress);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.EnhanceSeekBar.SavedState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */