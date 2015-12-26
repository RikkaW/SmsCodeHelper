package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Bitmap.CompressFormat;
import java.io.File;

public class cs$a
{
  private int a = 5120;
  private int b = 10485760;
  private File c;
  private Bitmap.CompressFormat d = cs.f();
  private int e = 100;
  private boolean f = true;
  private boolean g = true;
  private boolean h = false;
  
  public cs$a(Context paramContext, String paramString)
  {
    c = cs.a(paramContext, paramString);
  }
  
  public void a(int paramInt)
  {
    a = paramInt;
  }
  
  public void a(String paramString)
  {
    c = new File(paramString);
  }
  
  public void a(boolean paramBoolean)
  {
    f = paramBoolean;
  }
  
  public void b(int paramInt)
  {
    b = paramInt;
  }
  
  public void b(boolean paramBoolean)
  {
    g = paramBoolean;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.cs.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */