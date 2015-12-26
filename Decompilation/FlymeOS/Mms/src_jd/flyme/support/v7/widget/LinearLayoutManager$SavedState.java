package flyme.support.v7.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import arf;

public class LinearLayoutManager$SavedState
  implements Parcelable
{
  public static final Parcelable.Creator<SavedState> CREATOR = new arf();
  int a;
  int b;
  boolean c;
  
  public LinearLayoutManager$SavedState() {}
  
  public LinearLayoutManager$SavedState(Parcel paramParcel)
  {
    a = paramParcel.readInt();
    b = paramParcel.readInt();
    if (paramParcel.readInt() == 1) {}
    for (;;)
    {
      c = bool;
      return;
      bool = false;
    }
  }
  
  public LinearLayoutManager$SavedState(SavedState paramSavedState)
  {
    a = a;
    b = b;
    c = c;
  }
  
  boolean a()
  {
    return a >= 0;
  }
  
  void b()
  {
    a = -1;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(a);
    paramParcel.writeInt(b);
    if (c) {}
    for (paramInt = 1;; paramInt = 0)
    {
      paramParcel.writeInt(paramInt);
      return;
    }
  }
}

/* Location:
 * Qualified Name:     flyme.support.v7.widget.LinearLayoutManager.SavedState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */