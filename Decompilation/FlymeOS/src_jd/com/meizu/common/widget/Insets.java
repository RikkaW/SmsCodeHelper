package com.meizu.common.widget;

import android.graphics.Rect;

class Insets
{
  public static final Insets NONE = new Insets(0, 0, 0, 0);
  public final int bottom;
  public final int left;
  public final int right;
  public final int top;
  
  private Insets(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    left = paramInt1;
    top = paramInt2;
    right = paramInt3;
    bottom = paramInt4;
  }
  
  public static Insets of(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((paramInt1 == 0) && (paramInt2 == 0) && (paramInt3 == 0) && (paramInt4 == 0)) {
      return NONE;
    }
    return new Insets(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public static Insets of(Rect paramRect)
  {
    if (paramRect == null) {
      return NONE;
    }
    return of(left, top, right, bottom);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if ((paramObject == null) || (getClass() != paramObject.getClass())) {
        return false;
      }
      paramObject = (Insets)paramObject;
      if (bottom != bottom) {
        return false;
      }
      if (left != left) {
        return false;
      }
      if (right != right) {
        return false;
      }
    } while (top == top);
    return false;
  }
  
  public int hashCode()
  {
    return ((left * 31 + top) * 31 + right) * 31 + bottom;
  }
  
  public String toString()
  {
    return "Insets{left=" + left + ", top=" + top + ", right=" + right + ", bottom=" + bottom + '}';
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.Insets
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */