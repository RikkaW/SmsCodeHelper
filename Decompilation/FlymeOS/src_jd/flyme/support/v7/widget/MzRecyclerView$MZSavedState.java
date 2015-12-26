package flyme.support.v7.widget;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.util.LongSparseArray;
import android.util.SparseBooleanArray;
import android.view.View.BaseSavedState;
import arh;

public class MzRecyclerView$MZSavedState
  extends View.BaseSavedState
{
  public static final Parcelable.Creator<MZSavedState> CREATOR = new arh();
  boolean a;
  int b;
  SparseBooleanArray c;
  LongSparseArray<Integer> d;
  
  private MzRecyclerView$MZSavedState(Parcel paramParcel)
  {
    super(paramParcel);
    if (paramParcel.readByte() != 0) {}
    for (boolean bool = true;; bool = false)
    {
      a = bool;
      b = paramParcel.readInt();
      c = paramParcel.readSparseBooleanArray();
      int j = paramParcel.readInt();
      if (j <= 0) {
        break;
      }
      d = new LongSparseArray();
      while (i < j)
      {
        long l = paramParcel.readLong();
        int k = paramParcel.readInt();
        d.put(l, Integer.valueOf(k));
        i += 1;
      }
    }
  }
  
  public String toString()
  {
    return "MzRecyclerView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " checkState=" + c + "}";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = 0;
    super.writeToParcel(paramParcel, paramInt);
    if (a)
    {
      paramInt = 1;
      paramParcel.writeByte((byte)paramInt);
      paramParcel.writeInt(b);
      paramParcel.writeSparseBooleanArray(c);
      if (d == null) {
        break label106;
      }
    }
    label106:
    for (paramInt = d.size();; paramInt = 0)
    {
      paramParcel.writeInt(paramInt);
      while (i < paramInt)
      {
        paramParcel.writeLong(d.keyAt(i));
        paramParcel.writeInt(((Integer)d.valueAt(i)).intValue());
        i += 1;
      }
      paramInt = 0;
      break;
    }
  }
}

/* Location:
 * Qualified Name:     flyme.support.v7.widget.MzRecyclerView.MZSavedState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */