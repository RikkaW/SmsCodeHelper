package flyme.support.v7.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import ars;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StaggeredGridLayoutManager$LazySpanLookup
{
  int[] a;
  List<FullSpanItem> b;
  
  private int g(int paramInt)
  {
    if (b == null) {
      return -1;
    }
    FullSpanItem localFullSpanItem = f(paramInt);
    if (localFullSpanItem != null) {
      b.remove(localFullSpanItem);
    }
    int j = b.size();
    int i = 0;
    if (i < j) {
      if (b.get(i)).a < paramInt) {}
    }
    for (;;)
    {
      if (i != -1)
      {
        localFullSpanItem = (FullSpanItem)b.get(i);
        b.remove(i);
        return a;
        i += 1;
        break;
      }
      return -1;
      i = -1;
    }
  }
  
  int a(int paramInt)
  {
    if (b != null)
    {
      int i = b.size() - 1;
      while (i >= 0)
      {
        if (b.get(i)).a >= paramInt) {
          b.remove(i);
        }
        i -= 1;
      }
    }
    return b(paramInt);
  }
  
  public FullSpanItem a(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    Object localObject;
    if (b == null)
    {
      localObject = null;
      return (FullSpanItem)localObject;
    }
    int j = b.size();
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        break label117;
      }
      FullSpanItem localFullSpanItem = (FullSpanItem)b.get(i);
      if (a >= paramInt2) {
        return null;
      }
      if (a >= paramInt1)
      {
        localObject = localFullSpanItem;
        if (paramInt3 == 0) {
          break;
        }
        localObject = localFullSpanItem;
        if (b == paramInt3) {
          break;
        }
        if (paramBoolean)
        {
          localObject = localFullSpanItem;
          if (d) {
            break;
          }
        }
      }
      i += 1;
    }
    label117:
    return null;
  }
  
  void a()
  {
    if (a != null) {
      Arrays.fill(a, -1);
    }
    b = null;
  }
  
  void a(int paramInt, StaggeredGridLayoutManager.b paramb)
  {
    e(paramInt);
    a[paramInt] = d;
  }
  
  public void a(FullSpanItem paramFullSpanItem)
  {
    if (b == null) {
      b = new ArrayList();
    }
    int j = b.size();
    int i = 0;
    while (i < j)
    {
      FullSpanItem localFullSpanItem = (FullSpanItem)b.get(i);
      if (a == a) {
        b.remove(i);
      }
      if (a >= a)
      {
        b.add(i, paramFullSpanItem);
        return;
      }
      i += 1;
    }
    b.add(paramFullSpanItem);
  }
  
  int b(int paramInt)
  {
    if (a == null) {}
    while (paramInt >= a.length) {
      return -1;
    }
    int i = g(paramInt);
    if (i == -1)
    {
      Arrays.fill(a, paramInt, a.length, -1);
      return a.length;
    }
    Arrays.fill(a, paramInt, i + 1, -1);
    return i + 1;
  }
  
  int c(int paramInt)
  {
    if ((a == null) || (paramInt >= a.length)) {
      return -1;
    }
    return a[paramInt];
  }
  
  int d(int paramInt)
  {
    int i = a.length;
    while (i <= paramInt) {
      i *= 2;
    }
    return i;
  }
  
  void e(int paramInt)
  {
    if (a == null)
    {
      a = new int[Math.max(paramInt, 10) + 1];
      Arrays.fill(a, -1);
    }
    while (paramInt < a.length) {
      return;
    }
    int[] arrayOfInt = a;
    a = new int[d(paramInt)];
    System.arraycopy(arrayOfInt, 0, a, 0, arrayOfInt.length);
    Arrays.fill(a, arrayOfInt.length, a.length, -1);
  }
  
  public FullSpanItem f(int paramInt)
  {
    Object localObject;
    if (b == null)
    {
      localObject = null;
      return (FullSpanItem)localObject;
    }
    int i = b.size() - 1;
    for (;;)
    {
      if (i < 0) {
        break label61;
      }
      FullSpanItem localFullSpanItem = (FullSpanItem)b.get(i);
      localObject = localFullSpanItem;
      if (a == paramInt) {
        break;
      }
      i -= 1;
    }
    label61:
    return null;
  }
  
  public static class FullSpanItem
    implements Parcelable
  {
    public static final Parcelable.Creator<FullSpanItem> CREATOR = new ars();
    int a;
    int b;
    int[] c;
    boolean d;
    
    public FullSpanItem() {}
    
    public FullSpanItem(Parcel paramParcel)
    {
      a = paramParcel.readInt();
      b = paramParcel.readInt();
      if (paramParcel.readInt() == 1) {}
      for (;;)
      {
        d = bool;
        int i = paramParcel.readInt();
        if (i > 0)
        {
          c = new int[i];
          paramParcel.readIntArray(c);
        }
        return;
        bool = false;
      }
    }
    
    int a(int paramInt)
    {
      if (c == null) {
        return 0;
      }
      return c[paramInt];
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    public String toString()
    {
      return "FullSpanItem{mPosition=" + a + ", mGapDir=" + b + ", mHasUnwantedGapAfter=" + d + ", mGapPerSpan=" + Arrays.toString(c) + '}';
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      paramParcel.writeInt(a);
      paramParcel.writeInt(b);
      if (d) {}
      for (paramInt = 1;; paramInt = 0)
      {
        paramParcel.writeInt(paramInt);
        if ((c == null) || (c.length <= 0)) {
          break;
        }
        paramParcel.writeInt(c.length);
        paramParcel.writeIntArray(c);
        return;
      }
      paramParcel.writeInt(0);
    }
  }
}

/* Location:
 * Qualified Name:     flyme.support.v7.widget.StaggeredGridLayoutManager.LazySpanLookup
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */