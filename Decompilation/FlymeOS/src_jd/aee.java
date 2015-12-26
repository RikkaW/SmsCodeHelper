import android.animation.ObjectAnimator;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.animation.LinearInterpolator;
import com.android.mms.transaction.MessagePopupService;
import com.android.mms.view.MessagePopupGroupView.a;

public class aee
{
  public static float a = 0.35F;
  public static float b = 0.0F;
  private static LinearInterpolator c = new LinearInterpolator();
  private float d = 100.0F;
  private int e = 200;
  private int f = 400;
  private int g = 2000;
  private float h = 0.0F;
  private float i;
  private aee.a j;
  private int k;
  private VelocityTracker l;
  private float m;
  private volatile boolean n;
  private MessagePopupGroupView.a o;
  private boolean p;
  private float q;
  
  public aee(int paramInt, aee.a parama, float paramFloat1, float paramFloat2)
  {
    j = parama;
    k = paramInt;
    l = VelocityTracker.obtain();
    q = paramFloat1;
    i = (paramFloat2 / q);
  }
  
  private float a(VelocityTracker paramVelocityTracker)
  {
    if (k == 0) {
      return paramVelocityTracker.getXVelocity();
    }
    return paramVelocityTracker.getYVelocity();
  }
  
  private float a(MessagePopupGroupView.a parama)
  {
    if (k == 0) {
      return parama.c();
    }
    return parama.d();
  }
  
  private ObjectAnimator a(MessagePopupGroupView.a parama, float paramFloat)
  {
    if (k == 0) {}
    for (String str = "translationX";; str = "translationY") {
      return ObjectAnimator.ofFloat(parama, str, new float[] { paramFloat });
    }
  }
  
  private float b(VelocityTracker paramVelocityTracker)
  {
    if (k == 0) {
      return paramVelocityTracker.getYVelocity();
    }
    return paramVelocityTracker.getXVelocity();
  }
  
  private float b(MessagePopupGroupView.a parama)
  {
    if (k == 0) {
      return parama.a();
    }
    return parama.b();
  }
  
  private void b(MessagePopupGroupView.a parama, float paramFloat)
  {
    if (k == 0)
    {
      parama.a(paramFloat);
      return;
    }
    parama.b(paramFloat);
  }
  
  private float c(MotionEvent paramMotionEvent)
  {
    if (k == 0) {
      return paramMotionEvent.getRawX();
    }
    return paramMotionEvent.getRawY();
  }
  
  private float c(MessagePopupGroupView.a parama)
  {
    float f1 = 1.0F;
    float f2 = b(parama);
    float f3 = 0.5F * f2;
    float f4 = a(parama);
    if (f4 >= b * f2) {
      f1 = 1.0F - (f4 - f2 * b) / f3;
    }
    for (;;)
    {
      return Math.max(h, f1);
      if (f4 < (1.0F - b) * f2) {
        f1 = 1.0F + (f2 * b + f4) / f3;
      }
    }
  }
  
  public void a(float paramFloat)
  {
    q = paramFloat;
  }
  
  public boolean a(MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction())
    {
    }
    for (;;)
    {
      return n;
      n = false;
      o = j.getAnimatorObject();
      l.clear();
      if (o != null)
      {
        p = j.b();
        l.addMovement(paramMotionEvent);
        m = c(paramMotionEvent);
        MessagePopupService.a(false, 2, "MessagePopupService.SwipeHelper", "onInterceptTouchEvent()->Action_Dwon, mInitialTouchPos = " + m + ", mPagingTouchSlop = " + i);
        continue;
        if (o != null)
        {
          l.addMovement(paramMotionEvent);
          if (Math.abs(c(paramMotionEvent) - m) > i)
          {
            j.a(o);
            n = true;
            m = (c(paramMotionEvent) - a(o));
            MessagePopupService.a(false, 2, "MessagePopupService.SwipeHelper", "onInterceptTouchEvent()->Action_Move_mDragging, mInitialTouchPos = " + m + ", mPagingTouchSlop = " + i);
            continue;
            n = false;
            o = null;
          }
        }
      }
    }
  }
  
  public void b(float paramFloat)
  {
    i = (paramFloat / q);
  }
  
  public boolean b(MotionEvent paramMotionEvent)
  {
    int i4 = 0;
    if (!n) {
      return false;
    }
    l.addMovement(paramMotionEvent);
    float f1;
    float f3;
    float f2;
    switch (paramMotionEvent.getAction())
    {
    default: 
    case 2: 
    case 4: 
      do
      {
        return true;
      } while (o == null);
      f1 = c(paramMotionEvent) - m;
      if (!j.b())
      {
        f3 = b(o);
        f2 = 1024.0F;
        if (Math.abs(f1) >= f3) {
          if (f1 > 0.0F) {
            f1 = f2;
          }
        }
      }
      break;
    }
    for (;;)
    {
      b(o, f1);
      if (!p) {
        break;
      }
      o.c(c(o));
      break;
      f1 = -1024.0F;
      continue;
      f1 = 1024.0F * (float)Math.sin(f1 / f3 * 1.5707963267948966D);
      continue;
      if (o == null) {
        break;
      }
      f1 = g;
      f2 = q;
      l.computeCurrentVelocity(1000, f1 * f2);
      f2 = d;
      f3 = q;
      f1 = a(l);
      float f4 = b(l);
      int i1;
      label273:
      int i2;
      label307:
      int i3;
      if (Math.abs(a(o)) > a * b(o))
      {
        i1 = 1;
        if ((Math.abs(f1) <= f3 * f2) || (Math.abs(f1) <= Math.abs(f4))) {
          break label402;
        }
        if (f1 <= 0.0F) {
          break label390;
        }
        i2 = 1;
        if (a(o) <= 0.0F) {
          break label396;
        }
        i3 = 1;
        label323:
        if (i2 != i3) {
          break label402;
        }
        i2 = 1;
        label333:
        i3 = i4;
        if (j.b()) {
          if (i2 == 0)
          {
            i3 = i4;
            if (i1 == 0) {}
          }
          else
          {
            i3 = 1;
          }
        }
        if (i3 == 0) {
          break label413;
        }
        if (i2 == 0) {
          break label408;
        }
      }
      for (;;)
      {
        c(f1);
        break;
        i1 = 0;
        break label273;
        label390:
        i2 = 0;
        break label307;
        label396:
        i3 = 0;
        break label323;
        label402:
        i2 = 0;
        break label333;
        label408:
        f1 = 0.0F;
      }
      label413:
      j.b(o);
      d(f1);
      break;
    }
  }
  
  public void c(float paramFloat)
  {
    MessagePopupGroupView.a locala = j.getAnimatorObject();
    boolean bool = j.b();
    float f1;
    if ((paramFloat < 0.0F) || ((paramFloat == 0.0F) && (a(locala) < 0.0F)) || ((paramFloat == 0.0F) && (a(locala) == 0.0F) && (k == 1)))
    {
      f1 = -b(locala);
      i1 = f;
      if (paramFloat == 0.0F) {
        break label185;
      }
    }
    label185:
    for (int i1 = Math.min(i1, (int)(Math.abs(f1 - a(locala)) * 1000.0F / Math.abs(paramFloat)));; i1 = e)
    {
      ObjectAnimator localObjectAnimator = a(locala, f1);
      localObjectAnimator.setInterpolator(c);
      localObjectAnimator.setDuration(i1);
      localObjectAnimator.addListener(new aef(this));
      localObjectAnimator.addUpdateListener(new aeg(this, bool, locala));
      localObjectAnimator.start();
      return;
      f1 = b(locala);
      break;
    }
  }
  
  public void d(float paramFloat)
  {
    MessagePopupGroupView.a locala = j.getAnimatorObject();
    boolean bool = j.b();
    ObjectAnimator localObjectAnimator = a(locala, 0.0F);
    localObjectAnimator.setInterpolator(c);
    localObjectAnimator.setDuration('');
    localObjectAnimator.addListener(new aeh(this, locala));
    localObjectAnimator.addUpdateListener(new aei(this, bool, locala));
    localObjectAnimator.start();
  }
  
  public static abstract interface a
  {
    public abstract void a(MessagePopupGroupView.a parama);
    
    public abstract void b(MessagePopupGroupView.a parama);
    
    public abstract boolean b();
    
    public abstract void c();
    
    public abstract MessagePopupGroupView.a getAnimatorObject();
  }
}

/* Location:
 * Qualified Name:     aee
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */