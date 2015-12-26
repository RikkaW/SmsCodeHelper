package android.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class Country$1
  implements Parcelable.Creator<Country>
{
  public Country createFromParcel(Parcel paramParcel)
  {
    return new Country(paramParcel.readString(), paramParcel.readInt(), paramParcel.readLong(), null);
  }
  
  public Country[] newArray(int paramInt)
  {
    return new Country[paramInt];
  }
}

/* Location:
 * Qualified Name:     android.location.Country.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */