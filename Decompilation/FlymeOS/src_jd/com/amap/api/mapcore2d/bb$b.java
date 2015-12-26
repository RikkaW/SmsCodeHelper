package com.amap.api.mapcore2d;

import android.graphics.Point;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import java.util.LinkedList;

class bb$b
  implements Animation.AnimationListener
{
  private LinkedList<Animation> b = new LinkedList();
  private co c = null;
  
  private bb$b(bb parambb) {}
  
  private void a(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    if (c == null) {
      c = new co(aa).b.g(), this);
    }
    c.e = paramBoolean;
    c.d = paramInt1;
    c.a(paramInt1, false, paramInt2, paramInt3);
  }
  
  private void b(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    if (c == null) {
      c = new co(aa).b.g(), this);
    }
    c.d = paramInt1;
    c.e = paramBoolean;
    if (c.e == true)
    {
      Point localPoint = new Point(paramInt2, paramInt3);
      ae localae = aa).b.g().s().a(paramInt2, paramInt3);
      aa).h.i = aa).h.a(localae);
      aa).h.a(localPoint);
    }
    c.a(paramInt1, true, paramInt2, paramInt3);
  }
  
  public void a()
  {
    b.clear();
  }
  
  public void a(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (!paramBoolean1)
    {
      a(paramInt3, paramInt1, paramInt2, paramBoolean2);
      return;
    }
    b(paramInt3, paramInt1, paramInt2, paramBoolean2);
  }
  
  public void onAnimationEnd(Animation paramAnimation)
  {
    paramAnimation = aa).b.g();
    if (b.size() == 0)
    {
      aa).d.b();
      return;
    }
    paramAnimation.startAnimation((Animation)b.remove());
  }
  
  public void onAnimationRepeat(Animation paramAnimation) {}
  
  public void onAnimationStart(Animation paramAnimation) {}
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.bb.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */