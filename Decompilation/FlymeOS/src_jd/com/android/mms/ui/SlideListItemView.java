package com.android.mms.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Map;

public class SlideListItemView
  extends AttachMentViewBase
{
  private TextView a;
  private ImageView b;
  private TextView c;
  private ImageView d;
  private Context e;
  
  public SlideListItemView(Context paramContext)
  {
    super(paramContext);
    e = paramContext;
  }
  
  public SlideListItemView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    e = paramContext;
  }
  
  public void a(Uri paramUri, String paramString, Map<String, ?> paramMap)
  {
    if (paramString != null)
    {
      c.setText(paramString);
      d.setImageResource(2130837748);
      return;
    }
    c.setText("");
    d.setImageDrawable(null);
  }
  
  public void a(String paramString, Bitmap paramBitmap)
  {
    paramString = paramBitmap;
    if (paramBitmap == null) {}
    try
    {
      paramString = BitmapFactory.decodeResource(getResources(), 2130837744);
      b.setImageBitmap(paramString);
      return;
    }
    catch (OutOfMemoryError paramString)
    {
      Log.e("SlideListItemView", "setImage: out of memory: ", paramString);
    }
  }
  
  public void a(String paramString, Uri paramUri)
  {
    if (paramString != null)
    {
      c.setText(paramString);
      d.setImageResource(2130837954);
    }
    for (;;)
    {
      try
      {
        paramUri = VideoAttachmentView.a(e, paramUri);
        paramString = paramUri;
        if (paramUri == null) {
          paramString = BitmapFactory.decodeResource(getResources(), 2130837745);
        }
        b.setImageBitmap(paramString);
        return;
      }
      catch (OutOfMemoryError paramString)
      {
        Log.e("SlideListItemView", "setVideo: out of memory: ", paramString);
      }
      c.setText("");
      d.setImageDrawable(null);
    }
  }
  
  public void a(String paramString1, String paramString2)
  {
    a.setText(paramString2);
    paramString1 = a;
    if (TextUtils.isEmpty(paramString2)) {}
    for (int i = 8;; i = 0)
    {
      paramString1.setVisibility(i);
      return;
    }
  }
  
  protected void onFinishInflate()
  {
    a = ((TextView)findViewById(2131886744));
    a.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
    b = ((ImageView)findViewById(2131886741));
    c = ((TextView)findViewById(2131886745));
    d = ((ImageView)findViewById(2131886746));
  }
  
  public void setTextVisibility(boolean paramBoolean) {}
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideListItemView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */