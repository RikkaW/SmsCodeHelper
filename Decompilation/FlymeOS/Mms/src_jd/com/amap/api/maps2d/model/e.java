package com.amap.api.maps2d.model;

import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.amap.api.mapcore2d.cy;

class e
  implements Parcelable.Creator<LatLngBounds>
{
  static void a(LatLngBounds paramLatLngBounds, Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(paramLatLngBounds.a());
    paramParcel.writeParcelable(southwest, paramInt);
    paramParcel.writeParcelable(northeast, paramInt);
  }
  
  public LatLngBounds a(Parcel paramParcel)
  {
    int i = paramParcel.readInt();
    for (;;)
    {
      try
      {
        LatLng localLatLng1 = (LatLng)paramParcel.readParcelable(LatLngBounds.class.getClassLoader());
        cy.a(localBadParcelableException1, "LatLngBoundsCreator", "createFromParcel");
      }
      catch (BadParcelableException localBadParcelableException1)
      {
        try
        {
          localLatLng2 = (LatLng)paramParcel.readParcelable(LatLngBounds.class.getClassLoader());
          paramParcel = localLatLng1;
          return new LatLngBounds(i, paramParcel, localLatLng2);
        }
        catch (BadParcelableException localBadParcelableException2)
        {
          for (;;)
          {
            LatLng localLatLng2;
            paramParcel = localBadParcelableException1;
            Object localObject = localBadParcelableException2;
          }
        }
        localBadParcelableException1 = localBadParcelableException1;
        paramParcel = null;
      }
      localLatLng2 = null;
    }
  }
  
  public LatLngBounds[] a(int paramInt)
  {
    return new LatLngBounds[paramInt];
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.model.e
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */