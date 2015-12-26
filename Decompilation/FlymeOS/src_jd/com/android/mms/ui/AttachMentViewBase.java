package com.android.mms.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import java.util.Map;

public class AttachMentViewBase
  extends LinearLayout
  implements SlideViewInterface
{
  public AttachMentViewBase(Context paramContext)
  {
    super(paramContext);
  }
  
  public AttachMentViewBase(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public void a(int paramInt) {}
  
  public void a(long paramLong) {}
  
  public void a(Uri paramUri, String paramString, Map<String, ?> paramMap) {}
  
  public void a(Uri paramUri, String paramString, Map<String, ?> paramMap, long paramLong) {}
  
  public void a(Uri paramUri, String paramString1, Map<String, ?> paramMap, String paramString2)
  {
    c(paramUri, paramString1, paramMap);
  }
  
  public void a(String paramString, long paramLong) {}
  
  public void a(String paramString, Bitmap paramBitmap) {}
  
  public void a(String paramString, Uri paramUri) {}
  
  public void a(String paramString1, String paramString2) {}
  
  public void b(int paramInt) {}
  
  public void b(Uri paramUri, String paramString, Map<String, ?> paramMap) {}
  
  public void b(Uri paramUri, String paramString, Map<String, ?> paramMap, long paramLong) {}
  
  public void b(String paramString, Bitmap paramBitmap) {}
  
  public void c(Uri paramUri, String paramString, Map<String, ?> paramMap) {}
  
  public void c(Uri paramUri, String paramString, Map<String, ?> paramMap, long paramLong) {}
  
  public void h() {}
  
  public void i() {}
  
  public void j() {}
  
  public void k() {}
  
  public void l() {}
  
  public void m() {}
  
  public void n() {}
  
  public void setImageRegionFit(String paramString) {}
  
  public void setImageVisibility(boolean paramBoolean) {}
  
  public void setTextVisibility(boolean paramBoolean) {}
  
  public void setVideoVisibility(boolean paramBoolean) {}
  
  public void setVisibility(boolean paramBoolean) {}
}

/* Location:
 * Qualified Name:     com.android.mms.ui.AttachMentViewBase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */