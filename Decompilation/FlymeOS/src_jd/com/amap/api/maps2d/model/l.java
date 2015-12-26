package com.amap.api.maps2d.model;

import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.amap.api.mapcore2d.cy;

class l
  implements Parcelable.Creator<VisibleRegion>
{
  static void a(VisibleRegion paramVisibleRegion, Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(paramVisibleRegion.a());
    paramParcel.writeParcelable(nearLeft, paramInt);
    paramParcel.writeParcelable(nearRight, paramInt);
    paramParcel.writeParcelable(farLeft, paramInt);
    paramParcel.writeParcelable(farRight, paramInt);
    paramParcel.writeParcelable(latLngBounds, paramInt);
  }
  
  public VisibleRegion a(Parcel paramParcel)
  {
    Parcel localParcel = null;
    int i = paramParcel.readInt();
    for (;;)
    {
      try
      {
        localObject1 = (LatLng)paramParcel.readParcelable(LatLng.class.getClassLoader());
        Object localObject4;
        cy.a(localBadParcelableException1, "VisibleRegionCreator", "createFromParcel");
      }
      catch (BadParcelableException localBadParcelableException1)
      {
        try
        {
          localObject2 = (LatLng)paramParcel.readParcelable(LatLng.class.getClassLoader());
        }
        catch (BadParcelableException localBadParcelableException2)
        {
          for (;;)
          {
            Object localObject7;
            Object localObject5;
            localObject3 = null;
            localObject2 = null;
            localObject6 = null;
            paramParcel = (Parcel)localObject1;
            localObject1 = localObject6;
          }
        }
        try
        {
          localObject3 = (LatLng)paramParcel.readParcelable(LatLng.class.getClassLoader());
        }
        catch (BadParcelableException localBadParcelableException3)
        {
          localObject3 = null;
          localObject6 = null;
          paramParcel = (Parcel)localObject1;
          localObject1 = localObject2;
          localObject2 = localObject6;
          break label123;
        }
        try
        {
          localObject6 = (LatLng)paramParcel.readParcelable(LatLng.class.getClassLoader());
        }
        catch (BadParcelableException localBadParcelableException4)
        {
          localObject6 = null;
          paramParcel = (Parcel)localObject1;
          localObject1 = localObject2;
          localObject2 = localObject3;
          localObject3 = localObject6;
          break label123;
        }
        try
        {
          paramParcel = (LatLngBounds)paramParcel.readParcelable(LatLngBounds.class.getClassLoader());
          localParcel = paramParcel;
          localObject7 = localObject6;
          localObject6 = localObject3;
          localObject4 = localObject2;
          paramParcel = (Parcel)localObject1;
          return new VisibleRegion(i, paramParcel, (LatLng)localObject4, (LatLng)localObject6, (LatLng)localObject7, localParcel);
        }
        catch (BadParcelableException localBadParcelableException5)
        {
          paramParcel = (Parcel)localObject1;
          localObject1 = localObject2;
          localObject2 = localObject3;
          localObject3 = localObject6;
          break label123;
        }
        localBadParcelableException1 = localBadParcelableException1;
        localObject3 = null;
        localObject2 = null;
        localObject1 = null;
        paramParcel = null;
      }
      label123:
      localObject5 = localObject1;
      localObject6 = localObject2;
      localObject7 = localObject3;
    }
  }
  
  public VisibleRegion[] a(int paramInt)
  {
    return new VisibleRegion[paramInt];
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.model.l
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */