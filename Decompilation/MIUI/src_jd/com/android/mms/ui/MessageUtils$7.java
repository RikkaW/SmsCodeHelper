package com.android.mms.ui;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.ImageView;

final class MessageUtils$7
  implements ViewTreeObserver.OnPreDrawListener
{
  MessageUtils$7(Drawable paramDrawable, ImageView paramImageView) {}
  
  public boolean onPreDraw()
  {
    ((AnimationDrawable)val$drawable).start();
    val$imageView.getViewTreeObserver().removeOnPreDrawListener(this);
    return true;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageUtils.7
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */