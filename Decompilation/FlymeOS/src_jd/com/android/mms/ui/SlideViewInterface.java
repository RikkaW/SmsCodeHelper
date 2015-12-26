package com.android.mms.ui;

import android.graphics.Bitmap;
import android.net.Uri;
import java.util.Map;

public abstract interface SlideViewInterface
  extends ViewInterface
{
  public abstract void a(int paramInt);
  
  public abstract void a(long paramLong);
  
  public abstract void a(Uri paramUri, String paramString, Map<String, ?> paramMap);
  
  public abstract void a(Uri paramUri, String paramString, Map<String, ?> paramMap, long paramLong);
  
  public abstract void a(Uri paramUri, String paramString1, Map<String, ?> paramMap, String paramString2);
  
  public abstract void a(String paramString, long paramLong);
  
  public abstract void a(String paramString, Bitmap paramBitmap);
  
  public abstract void a(String paramString, Uri paramUri);
  
  public abstract void a(String paramString1, String paramString2);
  
  public abstract void b(int paramInt);
  
  public abstract void b(Uri paramUri, String paramString, Map<String, ?> paramMap);
  
  public abstract void b(Uri paramUri, String paramString, Map<String, ?> paramMap, long paramLong);
  
  public abstract void b(String paramString, Bitmap paramBitmap);
  
  public abstract void c(Uri paramUri, String paramString, Map<String, ?> paramMap, long paramLong);
  
  public abstract void i();
  
  public abstract void j();
  
  public abstract void k();
  
  public abstract void l();
  
  public abstract void m();
  
  public abstract void n();
  
  public abstract void setImageRegionFit(String paramString);
  
  public abstract void setImageVisibility(boolean paramBoolean);
  
  public abstract void setTextVisibility(boolean paramBoolean);
  
  public abstract void setVideoVisibility(boolean paramBoolean);
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideViewInterface
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */