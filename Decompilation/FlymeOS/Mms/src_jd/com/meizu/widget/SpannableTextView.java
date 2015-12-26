package com.meizu.widget;

import android.content.Context;
import android.text.Spannable;
import android.text.method.ArrowKeyMovementMethod;
import android.text.method.MovementMethod;
import android.util.AttributeSet;
import android.widget.TextView;
import android.widget.TextView.BufferType;

public class SpannableTextView
  extends TextView
{
  public SpannableTextView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public SpannableTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 16842884);
  }
  
  public SpannableTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    setFocusableInTouchMode(true);
  }
  
  protected boolean getDefaultMagnifierVisible()
  {
    return false;
  }
  
  protected MovementMethod getDefaultMovementMethod()
  {
    return ArrowKeyMovementMethod.getInstance();
  }
  
  protected boolean getDefaultOptionsVisible()
  {
    return true;
  }
  
  public Spannable getText()
  {
    return (Spannable)super.getText();
  }
  
  public boolean isTextSelecting()
  {
    return false;
  }
  
  public void setText(CharSequence paramCharSequence, TextView.BufferType paramBufferType)
  {
    super.setText(paramCharSequence, TextView.BufferType.SPANNABLE);
  }
  
  public boolean startTextSelection()
  {
    return onTextContextMenuItem(16908328);
  }
  
  public boolean stopTextSelection()
  {
    return onTextContextMenuItem(16908329);
  }
}

/* Location:
 * Qualified Name:     com.meizu.widget.SpannableTextView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */