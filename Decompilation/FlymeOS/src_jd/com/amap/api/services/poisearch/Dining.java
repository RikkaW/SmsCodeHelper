package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public final class Dining
  implements Parcelable
{
  public static final Parcelable.Creator<Dining> CREATOR = new b();
  private boolean a;
  private String b;
  private String c;
  private String d;
  private String e;
  private String f;
  private String g;
  private String h;
  private String i;
  private String j;
  private String k;
  private String l;
  private String m;
  private String n;
  private String o;
  private String p;
  private String q;
  private String r;
  private String s;
  private List<Photo> t = new ArrayList();
  
  public Dining() {}
  
  public Dining(Parcel paramParcel)
  {
    boolean[] arrayOfBoolean = new boolean[1];
    paramParcel.readBooleanArray(arrayOfBoolean);
    a = arrayOfBoolean[0];
    b = paramParcel.readString();
    c = paramParcel.readString();
    d = paramParcel.readString();
    e = paramParcel.readString();
    f = paramParcel.readString();
    g = paramParcel.readString();
    h = paramParcel.readString();
    i = paramParcel.readString();
    j = paramParcel.readString();
    k = paramParcel.readString();
    l = paramParcel.readString();
    m = paramParcel.readString();
    n = paramParcel.readString();
    o = paramParcel.readString();
    p = paramParcel.readString();
    q = paramParcel.readString();
    r = paramParcel.readString();
    s = paramParcel.readString();
    t = paramParcel.createTypedArrayList(Photo.CREATOR);
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
        if (paramObject == null) {
          return false;
        }
        if (getClass() != paramObject.getClass()) {
          return false;
        }
        paramObject = (Dining)paramObject;
        if (s == null)
        {
          if (s != null) {
            return false;
          }
        }
        else if (!s.equals(s)) {
          return false;
        }
        if (m == null)
        {
          if (m != null) {
            return false;
          }
        }
        else if (!m.equals(m)) {
          return false;
        }
        if (k == null)
        {
          if (k != null) {
            return false;
          }
        }
        else if (!k.equals(k)) {
          return false;
        }
        if (f == null)
        {
          if (f != null) {
            return false;
          }
        }
        else if (!f.equals(f)) {
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
        if (g == null)
        {
          if (g != null) {
            return false;
          }
        }
        else if (!g.equals(g)) {
          return false;
        }
        if (i == null)
        {
          if (i != null) {
            return false;
          }
        }
        else if (!i.equals(i)) {
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
        if (a != a) {
          return false;
        }
        if (r == null)
        {
          if (r != null) {
            return false;
          }
        }
        else if (!r.equals(r)) {
          return false;
        }
        if (q == null)
        {
          if (q != null) {
            return false;
          }
        }
        else if (!q.equals(q)) {
          return false;
        }
        if (p == null)
        {
          if (p != null) {
            return false;
          }
        }
        else if (!p.equals(p)) {
          return false;
        }
        if (n == null)
        {
          if (n != null) {
            return false;
          }
        }
        else if (!n.equals(n)) {
          return false;
        }
        if (o == null)
        {
          if (o != null) {
            return false;
          }
        }
        else if (!o.equals(o)) {
          return false;
        }
        if (t == null)
        {
          if (t != null) {
            return false;
          }
        }
        else if (!t.equals(t)) {
          return false;
        }
        if (e == null)
        {
          if (e != null) {
            return false;
          }
        }
        else if (!e.equals(e)) {
          return false;
        }
        if (l == null)
        {
          if (l != null) {
            return false;
          }
        }
        else if (!l.equals(l)) {
          return false;
        }
        if (j == null)
        {
          if (j != null) {
            return false;
          }
        }
        else if (!j.equals(j)) {
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
        if (h != null) {
          break;
        }
      } while (h == null);
      return false;
    } while (h.equals(h));
    return false;
  }
  
  public String getAddition()
  {
    return s;
  }
  
  public String getAtmosphere()
  {
    return m;
  }
  
  public String getCost()
  {
    return k;
  }
  
  public String getCpRating()
  {
    return f;
  }
  
  public String getCuisines()
  {
    return b;
  }
  
  public String getDeepsrc()
  {
    return g;
  }
  
  public String getEnvironmentRating()
  {
    return i;
  }
  
  public String getIntro()
  {
    return d;
  }
  
  public String getOpentime()
  {
    return r;
  }
  
  public String getOpentimeGDF()
  {
    return q;
  }
  
  public String getOrderinAppUrl()
  {
    return p;
  }
  
  public String getOrderingWapUrl()
  {
    return n;
  }
  
  public String getOrderingWebUrl()
  {
    return o;
  }
  
  public List<Photo> getPhotos()
  {
    return t;
  }
  
  public String getRating()
  {
    return e;
  }
  
  public String getRecommend()
  {
    return l;
  }
  
  public String getServiceRating()
  {
    return j;
  }
  
  public String getTag()
  {
    return c;
  }
  
  public String getTasteRating()
  {
    return h;
  }
  
  public int hashCode()
  {
    int i20 = 0;
    int i1;
    int i2;
    label21:
    int i3;
    label30:
    int i4;
    label40:
    int i5;
    label50:
    int i6;
    label60:
    int i7;
    label70:
    int i8;
    label80:
    int i9;
    label92:
    int i10;
    label102:
    int i11;
    label112:
    int i12;
    label122:
    int i13;
    label132:
    int i14;
    label142:
    int i15;
    label152:
    int i16;
    label162:
    int i17;
    label172:
    int i18;
    label182:
    int i19;
    if (s == null)
    {
      i1 = 0;
      if (m != null) {
        break label327;
      }
      i2 = 0;
      if (k != null) {
        break label338;
      }
      i3 = 0;
      if (f != null) {
        break label349;
      }
      i4 = 0;
      if (b != null) {
        break label361;
      }
      i5 = 0;
      if (g != null) {
        break label373;
      }
      i6 = 0;
      if (i != null) {
        break label385;
      }
      i7 = 0;
      if (d != null) {
        break label397;
      }
      i8 = 0;
      if (!a) {
        break label409;
      }
      i9 = 1231;
      if (r != null) {
        break label417;
      }
      i10 = 0;
      if (q != null) {
        break label429;
      }
      i11 = 0;
      if (p != null) {
        break label441;
      }
      i12 = 0;
      if (n != null) {
        break label453;
      }
      i13 = 0;
      if (o != null) {
        break label465;
      }
      i14 = 0;
      if (t != null) {
        break label477;
      }
      i15 = 0;
      if (e != null) {
        break label491;
      }
      i16 = 0;
      if (l != null) {
        break label503;
      }
      i17 = 0;
      if (j != null) {
        break label515;
      }
      i18 = 0;
      if (c != null) {
        break label527;
      }
      i19 = 0;
      label192:
      if (h != null) {
        break label539;
      }
    }
    for (;;)
    {
      return (i19 + (i18 + (i17 + (i16 + (i15 + (i14 + (i13 + (i12 + (i11 + (i10 + (i9 + (i8 + (i7 + (i6 + (i5 + (i4 + (i3 + (i2 + (i1 + 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31 + i20;
      i1 = s.hashCode();
      break;
      label327:
      i2 = m.hashCode();
      break label21;
      label338:
      i3 = k.hashCode();
      break label30;
      label349:
      i4 = f.hashCode();
      break label40;
      label361:
      i5 = b.hashCode();
      break label50;
      label373:
      i6 = g.hashCode();
      break label60;
      label385:
      i7 = i.hashCode();
      break label70;
      label397:
      i8 = d.hashCode();
      break label80;
      label409:
      i9 = 1237;
      break label92;
      label417:
      i10 = r.hashCode();
      break label102;
      label429:
      i11 = q.hashCode();
      break label112;
      label441:
      i12 = p.hashCode();
      break label122;
      label453:
      i13 = n.hashCode();
      break label132;
      label465:
      i14 = o.hashCode();
      break label142;
      label477:
      i15 = t.hashCode();
      break label152;
      label491:
      i16 = e.hashCode();
      break label162;
      label503:
      i17 = l.hashCode();
      break label172;
      label515:
      i18 = j.hashCode();
      break label182;
      label527:
      i19 = c.hashCode();
      break label192;
      label539:
      i20 = h.hashCode();
    }
  }
  
  public boolean isMealOrdering()
  {
    return a;
  }
  
  public void setAddition(String paramString)
  {
    s = paramString;
  }
  
  public void setAtmosphere(String paramString)
  {
    m = paramString;
  }
  
  public void setCost(String paramString)
  {
    k = paramString;
  }
  
  public void setCpRating(String paramString)
  {
    f = paramString;
  }
  
  public void setCuisines(String paramString)
  {
    b = paramString;
  }
  
  public void setDeepsrc(String paramString)
  {
    g = paramString;
  }
  
  public void setEnvironmentRating(String paramString)
  {
    i = paramString;
  }
  
  public void setIntro(String paramString)
  {
    d = paramString;
  }
  
  public void setMealOrdering(boolean paramBoolean)
  {
    a = paramBoolean;
  }
  
  public void setOpentime(String paramString)
  {
    r = paramString;
  }
  
  public void setOpentimeGDF(String paramString)
  {
    q = paramString;
  }
  
  public void setOrderinAppUrl(String paramString)
  {
    p = paramString;
  }
  
  public void setOrderingWapUrl(String paramString)
  {
    n = paramString;
  }
  
  public void setOrderingWebUrl(String paramString)
  {
    o = paramString;
  }
  
  public void setPhotos(List<Photo> paramList)
  {
    t = paramList;
  }
  
  public void setRating(String paramString)
  {
    e = paramString;
  }
  
  public void setRecommend(String paramString)
  {
    l = paramString;
  }
  
  public void setServiceRating(String paramString)
  {
    j = paramString;
  }
  
  public void setTag(String paramString)
  {
    c = paramString;
  }
  
  public void setTasteRating(String paramString)
  {
    h = paramString;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeBooleanArray(new boolean[] { a });
    paramParcel.writeString(b);
    paramParcel.writeString(c);
    paramParcel.writeString(d);
    paramParcel.writeString(e);
    paramParcel.writeString(f);
    paramParcel.writeString(g);
    paramParcel.writeString(h);
    paramParcel.writeString(i);
    paramParcel.writeString(j);
    paramParcel.writeString(k);
    paramParcel.writeString(l);
    paramParcel.writeString(m);
    paramParcel.writeString(n);
    paramParcel.writeString(o);
    paramParcel.writeString(p);
    paramParcel.writeString(q);
    paramParcel.writeString(r);
    paramParcel.writeString(s);
    paramParcel.writeTypedList(t);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.poisearch.Dining
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */