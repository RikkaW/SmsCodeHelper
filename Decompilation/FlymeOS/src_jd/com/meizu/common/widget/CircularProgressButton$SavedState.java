package com.meizu.common.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.view.View.BaseSavedState;

class CircularProgressButton$SavedState
  extends View.BaseSavedState
{
  public static final Parcelable.Creator<SavedState> CREATOR = new CircularProgressButton.SavedState.1();
  private boolean mConfigurationChanged;
  private boolean mIndeterminateProgressMode;
  private int mProgress;
  
  private CircularProgressButton$SavedState(Parcel paramParcel)
  {
    super(paramParcel);
    mProgress = paramParcel.readInt();
    if (paramParcel.readInt() == 1)
    {
      bool1 = true;
      mIndeterminateProgressMode = bool1;
      if (paramParcel.readInt() != 1) {
        break label51;
      }
    }
    label51:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      mConfigurationChanged = bool1;
      return;
      bool1 = false;
      break;
    }
  }
  
  public CircularProgressButton$SavedState(Parcelable paramParcelable)
  {
    super(paramParcelable);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = 1;
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeInt(mProgress);
    if (mIndeterminateProgressMode)
    {
      paramInt = 1;
      paramParcel.writeInt(paramInt);
      if (!mConfigurationChanged) {
        break label50;
      }
    }
    label50:
    for (paramInt = i;; paramInt = 0)
    {
      paramParcel.writeInt(paramInt);
      return;
      paramInt = 0;
      break;
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.CircularProgressButton.SavedState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */