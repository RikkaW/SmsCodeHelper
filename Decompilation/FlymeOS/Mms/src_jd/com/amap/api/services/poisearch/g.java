package com.amap.api.services.poisearch;

import android.content.Context;
import com.amap.api.services.core.r;

abstract class g<T, V>
  extends r<T, V>
{
  public g(Context paramContext, T paramT)
  {
    super(paramContext, paramT);
  }
  
  protected boolean a(String paramString)
  {
    return (paramString == null) || (paramString.equals("")) || (paramString.equals("[]"));
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.poisearch.g
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */