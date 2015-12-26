package com.amap.api.mapcore2d;

import android.content.Context;
import android.util.SparseArray;

public class bl$c
{
  private final Context b;
  private SparseArray<bi> c = new SparseArray();
  
  private bl$c(bl parambl1, bl parambl2, Context paramContext)
  {
    b = paramContext;
    c.put(0, new cd(parambl2, paramContext));
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.bl.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */