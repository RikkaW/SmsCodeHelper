package com.android.mms.fragmentstyle;

import vv;

public class FavoriteActivity$c
  implements Comparable<c>
{
  public long a;
  public long b;
  public vv c;
  
  public FavoriteActivity$c(FavoriteActivity paramFavoriteActivity, long paramLong, vv paramvv)
  {
    a = paramLong;
    c = paramvv;
    if (c != null)
    {
      b = c.N;
      return;
    }
    b = 0L;
  }
  
  public int a(c paramc)
  {
    if (c == null) {
      return -1;
    }
    if (c == null) {
      return 1;
    }
    return (int)(b - b);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.fragmentstyle.FavoriteActivity.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */