package com.ted.android.contacts.netparser.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import aoe;
import api;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class NumItem
{
  private static final String a = NumItem.class.getSimpleName();
  private String A;
  private String B;
  private List<RelevantNumber> C;
  private MarkerData D;
  private MarkerData E;
  private ArrayList<DealItem> F;
  private float G;
  private String b;
  private String c;
  private String d;
  private String e;
  private String f;
  private String g;
  private String h;
  private String i;
  private String j;
  private String[] k;
  private LinkedList<String> l;
  private String m;
  private float[] n;
  private float o;
  private int p;
  private int q;
  private ArrayList<MenuItem> r;
  private ArrayList<MenuItem> s;
  private ArrayList<MenuItem> t;
  private float u;
  private boolean v = true;
  private int w;
  private String x;
  private IconData y;
  private String z;
  
  public String a()
  {
    return b;
  }
  
  public ArrayList<DealItem> a(boolean paramBoolean)
  {
    if (paramBoolean) {
      return F;
    }
    Iterator localIterator = F.iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return F;
      }
      long l1 = ((DealItem)localIterator.next()).getEndTime() * 1000 + 86400000;
      if (System.currentTimeMillis() >= l1) {
        localIterator.remove();
      }
    }
  }
  
  public void a(float paramFloat)
  {
    o = paramFloat;
  }
  
  public void a(int paramInt)
  {
    w = paramInt;
  }
  
  public void a(IconData paramIconData)
  {
    y = paramIconData;
  }
  
  public void a(MarkerData paramMarkerData)
  {
    E = paramMarkerData;
  }
  
  public void a(String paramString)
  {
    A = paramString;
  }
  
  public void a(ArrayList<MenuItem> paramArrayList)
  {
    r = paramArrayList;
  }
  
  public void a(LinkedList<String> paramLinkedList)
  {
    l = paramLinkedList;
  }
  
  public void a(List<RelevantNumber> paramList)
  {
    C = paramList;
  }
  
  public void a(float[] paramArrayOfFloat)
  {
    n = paramArrayOfFloat;
  }
  
  public void a(String[] paramArrayOfString)
  {
    k = paramArrayOfString;
  }
  
  public String b()
  {
    if (!TextUtils.isEmpty(d)) {
      return d;
    }
    return c;
  }
  
  public void b(float paramFloat)
  {
    u = paramFloat;
  }
  
  public void b(int paramInt)
  {
    p = paramInt;
  }
  
  public void b(MarkerData paramMarkerData)
  {
    D = paramMarkerData;
  }
  
  public void b(String paramString)
  {
    B = paramString;
  }
  
  public void b(ArrayList<MenuItem> paramArrayList)
  {
    s = paramArrayList;
  }
  
  public String c()
  {
    return d;
  }
  
  public void c(float paramFloat)
  {
    G = paramFloat;
  }
  
  public void c(int paramInt)
  {
    q = paramInt;
  }
  
  public void c(String paramString)
  {
    b = paramString;
  }
  
  public void c(ArrayList<MenuItem> paramArrayList)
  {
    t = paramArrayList;
  }
  
  public String d()
  {
    return f;
  }
  
  public void d(String paramString)
  {
    d = paramString;
  }
  
  public void d(ArrayList<DealItem> paramArrayList)
  {
    F = paramArrayList;
  }
  
  public String e()
  {
    return i;
  }
  
  public void e(String paramString)
  {
    c = paramString;
  }
  
  public int f()
  {
    return p;
  }
  
  public void f(String paramString)
  {
    e = paramString;
  }
  
  public void g(String paramString)
  {
    f = paramString;
  }
  
  public boolean g()
  {
    return p != -1;
  }
  
  public void h(String paramString)
  {
    g = paramString;
  }
  
  public boolean h()
  {
    return System.currentTimeMillis() / 1000L - q > aoe.a;
  }
  
  public ArrayList<MenuItem> i()
  {
    return s;
  }
  
  public void i(String paramString)
  {
    h = paramString;
  }
  
  public ArrayList<MenuItem> j()
  {
    return t;
  }
  
  public void j(String paramString)
  {
    i = paramString;
  }
  
  public IconData k()
  {
    return y;
  }
  
  public void k(String paramString)
  {
    j = paramString;
  }
  
  public String l()
  {
    return x;
  }
  
  public void l(String paramString)
  {
    m = paramString;
  }
  
  public MarkerData m()
  {
    if (E != null) {
      return E;
    }
    return D;
  }
  
  public void m(String paramString)
  {
    z = paramString;
  }
  
  public MarkerData n()
  {
    return E;
  }
  
  public void n(String paramString)
  {
    x = paramString;
  }
  
  public List<RelevantNumber> o()
  {
    return C;
  }
  
  public ArrayList<DealItem> p()
  {
    return a(true);
  }
  
  public boolean q()
  {
    return (!TextUtils.isEmpty(d)) || (E != null);
  }
  
  public static class IconData
  {
    public String a;
    public String b;
    public String c;
    
    public IconData() {}
    
    public IconData(String paramString1, String paramString2, String paramString3)
    {
      a = paramString1;
      b = paramString2;
      c = paramString3;
    }
  }
  
  public static class MarkerData
  {
    public int a;
    public String b;
    public int c;
    public boolean d;
    public boolean e;
    public boolean f;
    
    public MarkerData(int paramInt1, String paramString, int paramInt2)
    {
      a = paramInt1;
      b = paramString;
      c = paramInt2;
    }
  }
  
  public static class RelevantNumber
    implements Parcelable
  {
    public static final Parcelable.Creator<RelevantNumber> CREATOR = new api();
    public String a;
    public String b;
    public String c;
    
    private RelevantNumber(Parcel paramParcel)
    {
      a = paramParcel.readString();
      b = paramParcel.readString();
      c = paramParcel.readString();
    }
    
    public RelevantNumber(String paramString1, String paramString2, String paramString3)
    {
      a = paramString1;
      b = paramString2;
      c = paramString3;
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      paramParcel.writeString(a);
      paramParcel.writeString(b);
      paramParcel.writeString(c);
    }
  }
}

/* Location:
 * Qualified Name:     com.ted.android.contacts.netparser.model.NumItem
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */