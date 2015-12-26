package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PoiItemDetail
  extends PoiItem
  implements Parcelable
{
  public static final Parcelable.Creator<PoiItemDetail> CREATOR = new h();
  private List<Groupbuy> a = new ArrayList();
  private List<Discount> b = new ArrayList();
  private Dining c;
  private Hotel d;
  private Cinema e;
  private Scenic f;
  private DeepType g;
  
  private PoiItemDetail(Parcel paramParcel)
  {
    super(paramParcel);
    a = paramParcel.readArrayList(Groupbuy.class.getClassLoader());
    b = paramParcel.readArrayList(Discount.class.getClassLoader());
  }
  
  public PoiItemDetail(String paramString1, LatLonPoint paramLatLonPoint, String paramString2, String paramString3)
  {
    super(paramString1, paramLatLonPoint, paramString2, paramString3);
  }
  
  public void addDiscount(Discount paramDiscount)
  {
    b.add(paramDiscount);
  }
  
  public void addGroupbuy(Groupbuy paramGroupbuy)
  {
    a.add(paramGroupbuy);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      do
      {
        return true;
        if (!super.equals(paramObject)) {
          return false;
        }
        if (getClass() != paramObject.getClass()) {
          return false;
        }
        paramObject = (PoiItemDetail)paramObject;
        if (e == null)
        {
          if (e != null) {
            return false;
          }
        }
        else if (!e.equals(e)) {
          return false;
        }
        if (g != g) {
          return false;
        }
        if (c == null)
        {
          if (c != null) {
            return false;
          }
        }
        else if (!c.equals(c)) {
          return false;
        }
        if (b == null)
        {
          if (b != null) {
            return false;
          }
        }
        else if (!b.equals(b)) {
          return false;
        }
        if (a == null)
        {
          if (a != null) {
            return false;
          }
        }
        else if (!a.equals(a)) {
          return false;
        }
        if (d == null)
        {
          if (d != null) {
            return false;
          }
        }
        else if (!d.equals(d)) {
          return false;
        }
        if (f != null) {
          break;
        }
      } while (f == null);
      return false;
    } while (f.equals(f));
    return false;
  }
  
  public Cinema getCinema()
  {
    return e;
  }
  
  public DeepType getDeepType()
  {
    return g;
  }
  
  public Dining getDining()
  {
    return c;
  }
  
  public List<Discount> getDiscounts()
  {
    return b;
  }
  
  public List<Groupbuy> getGroupbuys()
  {
    return a;
  }
  
  public Hotel getHotel()
  {
    return d;
  }
  
  public Scenic getScenic()
  {
    return f;
  }
  
  public int hashCode()
  {
    int i2 = 0;
    int i3 = super.hashCode();
    int i;
    int j;
    label27:
    int k;
    label36:
    int m;
    label46:
    int n;
    label56:
    int i1;
    if (e == null)
    {
      i = 0;
      if (g != null) {
        break label126;
      }
      j = 0;
      if (c != null) {
        break label137;
      }
      k = 0;
      if (b != null) {
        break label148;
      }
      m = 0;
      if (a != null) {
        break label162;
      }
      n = 0;
      if (d != null) {
        break label176;
      }
      i1 = 0;
      label66:
      if (f != null) {
        break label188;
      }
    }
    for (;;)
    {
      return (i1 + (n + (m + (k + (j + (i + i3 * 31) * 31) * 31) * 31) * 31) * 31) * 31 + i2;
      i = e.hashCode();
      break;
      label126:
      j = g.hashCode();
      break label27;
      label137:
      k = c.hashCode();
      break label36;
      label148:
      m = b.hashCode();
      break label46;
      label162:
      n = a.hashCode();
      break label56;
      label176:
      i1 = d.hashCode();
      break label66;
      label188:
      i2 = f.hashCode();
    }
  }
  
  public void initDiscounts(List<Discount> paramList)
  {
    if ((paramList == null) || (paramList.size() == 0)) {}
    for (;;)
    {
      return;
      b.clear();
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        Discount localDiscount = (Discount)paramList.next();
        b.add(localDiscount);
      }
    }
  }
  
  public void initGroupbuys(List<Groupbuy> paramList)
  {
    if ((paramList == null) || (paramList.size() == 0)) {}
    for (;;)
    {
      return;
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        Groupbuy localGroupbuy = (Groupbuy)paramList.next();
        a.add(localGroupbuy);
      }
    }
  }
  
  public void setCinema(Cinema paramCinema)
  {
    e = paramCinema;
  }
  
  public void setDeepType(DeepType paramDeepType)
  {
    g = paramDeepType;
  }
  
  public void setDining(Dining paramDining)
  {
    c = paramDining;
  }
  
  public void setHotel(Hotel paramHotel)
  {
    d = paramHotel;
  }
  
  public void setScenic(Scenic paramScenic)
  {
    f = paramScenic;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeList(a);
    paramParcel.writeList(b);
  }
  
  public static enum DeepType
  {
    static
    {
      DINING = new DeepType("DINING", 1);
      HOTEL = new DeepType("HOTEL", 2);
    }
    
    private DeepType() {}
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.poisearch.PoiItemDetail
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */