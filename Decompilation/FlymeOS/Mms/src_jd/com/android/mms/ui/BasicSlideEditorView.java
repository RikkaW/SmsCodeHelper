package com.android.mms.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Map;
import py;

public class BasicSlideEditorView
  extends AttachMentViewBase
{
  private ImageView a;
  private View b;
  private TextView c;
  private EditText d;
  private boolean e = true;
  private a f;
  private Context g;
  
  public BasicSlideEditorView(Context paramContext)
  {
    super(paramContext);
    g = paramContext;
  }
  
  public BasicSlideEditorView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    g = paramContext;
  }
  
  public void a(Uri paramUri, String paramString, Map<String, ?> paramMap)
  {
    b.setVisibility(0);
    c.setText(paramString);
  }
  
  public void a(String paramString, Bitmap paramBitmap)
  {
    paramString = paramBitmap;
    if (paramBitmap == null) {}
    try
    {
      paramString = BitmapFactory.decodeResource(getResources(), 2130837744);
      a.setImageBitmap(paramString);
      return;
    }
    catch (OutOfMemoryError paramString)
    {
      Log.e("BasicSlideEditorView", "setImage: out of memory: ", paramString);
    }
  }
  
  public void a(String paramString, Uri paramUri)
  {
    try
    {
      paramUri = VideoAttachmentView.a(g, paramUri);
      paramString = paramUri;
      if (paramUri == null) {
        paramString = BitmapFactory.decodeResource(getResources(), 2130837745);
      }
      a.setImageBitmap(paramString);
      return;
    }
    catch (OutOfMemoryError paramString)
    {
      Log.e("BasicSlideEditorView", "setVideo: out of memory: ", paramString);
    }
  }
  
  public void a(String paramString1, String paramString2)
  {
    e = false;
    if ((paramString2 != null) && (!paramString2.equals(d.getText().toString())))
    {
      d.setText(paramString2);
      d.setSelection(paramString2.length());
    }
    e = true;
  }
  
  public void b(String paramString, Bitmap paramBitmap)
  {
    a.setImageBitmap(paramBitmap);
  }
  
  public void h()
  {
    a.setImageDrawable(null);
    b.setVisibility(8);
    e = false;
    d.setText("");
    e = true;
  }
  
  public void onFinishInflate()
  {
    a = ((ImageView)findViewById(2131886169));
    b = findViewById(2131886436);
    c = ((TextView)findViewById(2131886437));
    d = ((EditText)findViewById(2131886445));
    d.addTextChangedListener(new py(this));
  }
  
  public void setOnTextChangedListener(a parama)
  {
    f = parama;
  }
  
  public static abstract interface a
  {
    public abstract void a(String paramString);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.BasicSlideEditorView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */